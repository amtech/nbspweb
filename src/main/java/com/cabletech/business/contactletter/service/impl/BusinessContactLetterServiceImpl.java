package com.cabletech.business.contactletter.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.baseinfo.excel.ExportSupport;
import com.cabletech.business.contactletter.constant.Constant4Bcl;
import com.cabletech.business.contactletter.dao.BusinessContactLetterDao;
import com.cabletech.business.contactletter.model.BusinessContactLetter;
import com.cabletech.business.contactletter.model.BusinessContactLetterSendee;
import com.cabletech.business.contactletter.model.ContactLetterAudit;
import com.cabletech.business.contactletter.service.BusinessContactLetterSendEEService;
import com.cabletech.business.contactletter.service.BusinessContactLetterService;
import com.cabletech.business.contactletter.service.ContactLetterAuditService;
import com.cabletech.business.flowservice.util.ProMockPo;
import com.cabletech.business.flowservice.util.WorkFlowServiceClient;
import com.cabletech.business.workflow.common.model.CommonWorkflowResult;
import com.cabletech.business.base.model.ModuleCatalog;
import com.cabletech.business.base.model.SmParameter;
import com.cabletech.business.base.service.UploadFileService;
import com.cabletech.business.base.service.UserInfoService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 业务处理实现
 * 
 * @author zg
 * 
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@Service
public class BusinessContactLetterServiceImpl extends BaseServiceImpl implements
		BusinessContactLetterService, ExportSupport {
	// 上传附件业务处理
	@Resource(name = "uploadFileServiceImpl")
	private UploadFileService uploadFileService;
	// 短信发送信息编号
	public static final String SEND_TROUBLE_MSG_ID = "send_contactletter";
	// 联系函审核服务类
	@Resource(name = "contactLetterAuditService")
	private ContactLetterAuditService contactLetterAuditService;
	// 联系函处理类
	@Resource(name = "businessContactLetterDao")
	private BusinessContactLetterDao dao;
	// 联系函分发人处理服务类
	@Resource(name = "businessContactLetterSendEEServiceImpl")
	private BusinessContactLetterSendEEService businessContactlettersendeeservice;
	// 用户信息类
	@Resource(name = "userInfoServiceImpl")
	private UserInfoService userinfoService;
	// 短信发送信息配置文件编号常量
	public static final String XML_FILE_ID = "contactletter";

	// 工作流webservice服务
	@Resource(name = "workFlowServiceClient")
	private WorkFlowServiceClient workflowClient;
	// 流程处理结果的key与value的Map
	protected Map<String, String> processResultMap = new HashMap<String, String>();

	/**
	 * 直接发布审核工作流
	 * 
	 * @param entity
	 *            实体
	 * @param smParameter
	 *            发送短信对象
	 */
	@SuppressWarnings("static-access")
	@Transactional
	private void doTaskTwoWorkflow(BusinessContactLetter entity,
			SmParameter smParameter) {
		ProMockPo pro = new ProMockPo();
		String sendUserId = entity.getReleaseUserId();
		String processMaintenanceId = entity.getAuditUserId();
		pro.setBzid(entity.getId());
		pro.setUserId(sendUserId);
		pro.setUserName(userinfoService.getUserInfoByPersonId(sendUserId)
				.getUserName());
		pro.setDealUsers(processMaintenanceId);
		pro.setDealUsersName(userinfoService.getUserInfoByPersonId(
				processMaintenanceId).getUserName());
		pro.setProcessName(getContactLetterAuditService().WORKFLOW_NAME);
		getContactLetterAuditService().sendTaskTwoSteps(pro, smParameter);
	}

	@Override
	@Transactional
	public BusinessContactLetter saveBusinessContactletter(
			BusinessContactLetter entity, UserInfo user, List<FileItem> files)
			throws Exception {
		try {
			String[] issuerAreaUserIds = getIssuerUsers(entity, user);
			dao.saveBusinessContactletter(entity);
			if (StringUtils.isNotBlank(entity.getId())) { // 保存至数据库表中
				logger.info(entity.getId());
				saveToSendee(entity, issuerAreaUserIds);
			}
			uploadFileService.saveFiles(files, ModuleCatalog.OTHER, "",
					entity.getId(), "CONTACTLETTER_CLOB",
					entity.getReleaseUserId());
			logger.info(entity.getId());
			if (entity.getIsAudit() != null) {
				if (entity.getIsAudit().equals("1")) {
					this.sendToJBPM(entity);
				}
			}
			return entity;
		} catch (Exception e) {
		}
		return null;

	}

	/**
	 * 获取分派用户ids
	 * 
	 * @param entity
	 *            对象
	 * @param user
	 *            用户对象
	 * @return
	 */
	public String[] getIssuerUsers(BusinessContactLetter entity, UserInfo user) {
		String[] issuerAreaUserIds = null;
		if (StringUtils.isNotBlank(entity.getIssuerAreaUserIds())) {
			issuerAreaUserIds = entity.getIssuerAreaUserIds().split(",");
			boolean flags = true;
			for (int i = 0; i < issuerAreaUserIds.length; i++) {
				if (issuerAreaUserIds[i].equals(user.getPersonId())) {
					flags = false;
					break;
				}
			}
			if (flags) {
				entity.setIssuerAreaUserIds(entity.getIssuerAreaUserIds());
			}
			issuerAreaUserIds = entity.getIssuerAreaUserIds().split(",");
		}
		return issuerAreaUserIds;
	}

	/**
	 * 生成工作流 调用其他接口
	 * 
	 * @param entity
	 *            业务实体
	 */
	public void sendToJBPM(BusinessContactLetter entity) {
		int taskcount = checkInViewJBPM(entity.getId());
		SmParameter smParameter = getSmParameter(entity);
		if (taskcount != 0) {// 说明 是打回的 需要修改 那么提交之后就 dotask了
			doWorkflow(entity, smParameter);
		} else {
			doTaskTwoWorkflow(entity, smParameter);
		}
	}

	/**
	 * 保存发送人关系
	 * 
	 * @param entity
	 *            实体
	 * @param issuerAreaUserIds
	 *            分发用户ids
	 */
	@Transactional
	public void saveToSendee(BusinessContactLetter entity,
			String[] issuerAreaUserIds) {
		long counts = businessContactlettersendeeservice
				.getCountByLetterId(entity.getId());
		if (counts > 0) {
			businessContactlettersendeeservice.delete(entity.getId());// 先删除原先已有的对应关系
		}
		if (null != issuerAreaUserIds) {
			for (int j = 0; j < issuerAreaUserIds.length; j++) {
				BusinessContactLetterSendee sendentity = new BusinessContactLetterSendee();
				sendentity.setIsread("0");
				sendentity.setObjectId(issuerAreaUserIds[j]);
				sendentity.setLetterId(entity.getId());
				businessContactlettersendeeservice.save(sendentity);
			}
		}
	}

	/**
	 * 直接执行任务
	 * 
	 * @param entity
	 *            实体
	 * @param smParameter
	 *            发送短信实体对象
	 */
	@Transactional
	private void doWorkflow(BusinessContactLetter entity,
			SmParameter smParameter) {
		ProMockPo pro = new ProMockPo();
		String sendUserId = entity.getReleaseUserId();
		String processMaintenanceId = entity.getAuditUserId();
		pro.setBzid(entity.getId());
		pro.setUserId(sendUserId);
		pro.setUserName(userinfoService.getUserInfoByPersonId(sendUserId)
				.getUserName());
		pro.setDealUsers(processMaintenanceId);
		getContactLetterAuditService().doTask(pro, smParameter);
	}

	/**
	 * 组装短信息 不发送
	 * 
	 * @param entity
	 *            业务联系函对象
	 * @return
	 */
	private SmParameter getSmParameter(BusinessContactLetter entity) {
		String simId = userinfoService.getUserInfoByPersonId(
				entity.getAuditUserId()).getMobile();
		String[] contentParameters = new String[] { userinfoService
				.getUserInfoByPersonId(entity.getAuditUserId())
				+ "您好，现在有联系函："
				+ entity.getTitle() + "， 需要您审核!	请及时处理！" };
		SmParameter parameter = SmParameter.getInstance(XML_FILE_ID,
				SEND_TROUBLE_MSG_ID, simId, contentParameters);
		if (StringUtils.isNotBlank(entity.getIsSend())) {
			if (entity.getIsSend().equals("0")) {
				parameter.setSentSm(false);
			} else {
				parameter.setSentSm(true);
			}
		} else {
			parameter.setSentSm(false);
		}
		return parameter;
	}

	/**
	 * 获取短信发送参数
	 * 
	 * @param contactLetterAudit
	 *            审核结果实体
	 */
	private SmParameter getSmParameter(ContactLetterAudit contactLetterAudit) {
		String simId = "18055150696";
		String[] contentParameters = new String[] { "您好，现在有联系函： 请及时处理！" };
		SmParameter parameter = SmParameter.getInstance(XML_FILE_ID,
				SEND_TROUBLE_MSG_ID, simId, contentParameters);
		return parameter;
	}

	/*
	 * 阅读联系函 将isread 设置为1
	 */
	@Transactional
	@Override
	public BusinessContactLetter readContactletter(String id, String personId) {
		BusinessContactLetterSendee entityEE = new BusinessContactLetterSendee();
		entityEE.setIsread("1");// 已读了
		entityEE.setLetterId(id);
		entityEE.setObjectId(personId);
		entityEE.setReadtime(new Date());
		businessContactlettersendeeservice.updateIsread(entityEE);
		BusinessContactLetter entity = dao.getById(id);
		return entity;
	}

	/*
	 * 审核当前联系函
	 * 
	 * @see
	 */
	@Transactional
	@Override
	public void auditThis(BusinessContactLetter entity,
			ContactLetterAudit contactLetterAudit, UserInfo userInfo) {
		contactLetterAudit.setAuditor(userInfo.getPersonId());
		contactLetterAudit.setAuditingTime(new Date());
		contactLetterAudit.setTransferApprover(userInfo.getPersonId());
		SmParameter parameter = getSmParameter(contactLetterAudit);
		ProMockPo pro = doWorkflow(contactLetterAudit, parameter);
		if (pro.isFlowOver()) {
			logger.info("工作流执行完成！");
			// 执行成功之后 需要修改联系函状态 审核通过 2
			dao.changeStatus(entity.getId(), Constant4Bcl.CHECKOFF);
		} else {// 驳回 3
			dao.changeStatus(entity.getId(), Constant4Bcl.CHECKOUT);
		}
	}

	/**
	 * 执行审核工作流
	 * 
	 * @param contactLetterAudit
	 *            审核信息
	 * @param smParameter
	 *            短信对象
	 * @return ProMockPo 审核工作流执行后的结果
	 */
	private ProMockPo doWorkflow(ContactLetterAudit contactLetterAudit,
			SmParameter smParameter) {
		ProMockPo pro = new ProMockPo();
		String sendUserId = contactLetterAudit.getAuditor();
		pro.setUserId(sendUserId);
		pro.setUserName(userinfoService.getUserInfoByPersonId(sendUserId)
				.getUserName());
		pro.setTaskId(contactLetterAudit.getTaskId());
		pro.setDealUsers(sendUserId);
		pro.setDealUsersName(userinfoService.getUserInfoByPersonId(sendUserId)
				.getUserName());
		pro.setTransition(contactLetterAudit.getIsAuditing());
		pro.setComment(contactLetterAudit.getAuditMsg());
		pro.setTaskName("审核联系函");
		return getContactLetterAuditService().doTask(pro, smParameter);
	}

	/*
	 * 执行查询列表
	 */
	@Override
	public Page queryPage4Query(BusinessContactLetter entity, Page page,
			UserInfo user) {
		return dao.queryPage4Query(entity, page, user);
	}

	public ContactLetterAuditService getContactLetterAuditService() {
		return contactLetterAuditService;
	}

	public void setContactLetterAuditService(
			ContactLetterAuditService contactLetterAuditService) {
		this.contactLetterAuditService = contactLetterAuditService;
	}

	@Override
	@Transactional
	public boolean deleteBusinessContactletter(String id) {
		return dao.deleteBusinessContactletter(dao.get(id));
	}

	@Transactional(readOnly = true)
	@Override
	public BusinessContactLetter loadBusinessContactletter(String id) {
		return dao.getById(id);
	}

	@Override
	public Page queryPage4WaitHand(BusinessContactLetter businessContactletter,
			Page page, UserInfo user) {
		return dao.queryPage4WaitHand(businessContactletter, page, user);
	}

	@Override
	protected BaseDao getBaseDao() {
		return dao;
	}

	/*
	 * 查询当前的联系函任务是否存在于 view_jbpm_usertask中
	 */
	@Override
	public Integer checkInViewJBPM(String id) {
		return dao.checkInViewJBPM(id);
	}

	@Override
	public Map<String, List<CommonWorkflowResult>> getProcessHistoryList(
			String id) {
		initMap();
		Map<String, List<CommonWorkflowResult>> map = new LinkedHashMap<String, List<CommonWorkflowResult>>();
		List<ProMockPo> list = getHandledProcessList(id);
		if (CollectionUtils.isEmpty(list)) {
			return map;
		}
		ProMockPo po;
		for (int i = 0; i < list.size(); i++) {
			po = (ProMockPo) list.get(i);
			if (po == null) {
				continue;
			}
			putProcessHistoryMap(po, map);
		}
		return map;
	}

	private void putProcessHistoryMap(ProMockPo po,
			Map<String, List<CommonWorkflowResult>> map) {
		List<CommonWorkflowResult> list = new ArrayList<CommonWorkflowResult>();
		if (map.containsKey(po.getTaskName())) {
			list = map.get(po.getTaskName());
		}
		CommonWorkflowResult history = new CommonWorkflowResult();
		String key = po.getTaskName() + po.getTransition();
		String processResult = "";
		if (processResultMap.containsKey(key)) {
			processResult = (String) processResultMap.get(key);
		} else if (StringUtils.isNotBlank(po.getTransition())) {
			processResult = po.getTransition();
			String prefix = "";
			if (processResult.indexOf("(") != -1) {
				prefix = processResult.substring(0, processResult.indexOf("("));
			}
			String suffix = "";
			if (processResult.indexOf(")") != -1) {
				suffix = processResult
						.substring(processResult.indexOf(")") + 1);
			}
			processResult = prefix + suffix;
		}
		history.setProcessResult(processResult);
		history.setProcessComment(po.getComment());
		history.setProcessUser(po.getUserName());
		history.setProcessDate(po.getEndTime());
		list.add(history);
		map.put(po.getTaskName(), list);
	}

	/**
	 * 根据实体业务编号获取实体业务的工作流信息列表
	 * 
	 * @param taskId
	 *            String 实体业务编号
	 * @return List<ProMockPo> 已办工作流列表
	 */
	public List<ProMockPo> getHandledProcessList(String taskId) {
		List<ProMockPo> list = workflowClient.getTaskHisByBzid(taskId);
		return list;
	}
	/**
	 * 初始化map
	 */
	public void initMap() {
		processResultMap.put("审核" + SysConstant.PASS_WORKFLOW_TRANSTION,
				SysConstant.PASS_WORKFLOW_TRANSTIONNAME);
		processResultMap.put("审核" + SysConstant.REJECT_WORKFLOW_TRANSITION,
				SysConstant.REJECT_WORKFLOW_CONTACTNAME);
	}

}
