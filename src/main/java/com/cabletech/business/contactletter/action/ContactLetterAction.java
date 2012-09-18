package com.cabletech.business.contactletter.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.service.CommonOrderCodeService;
import com.cabletech.business.base.service.UserInfoService;
import com.cabletech.business.contactletter.constant.Constant4Bcl;
import com.cabletech.business.contactletter.model.BusinessContactLetter;
import com.cabletech.business.contactletter.model.ContactLetterAudit;
import com.cabletech.business.contactletter.service.BusinessContactLetterSendEEService;
import com.cabletech.business.contactletter.service.BusinessContactLetterService;
import com.cabletech.business.flowservice.util.ProMockPo;
import com.cabletech.business.flowservice.util.WorkFlowServiceClient;
import com.cabletech.business.workflow.common.model.CommonWorkflowResult;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * @author 周剛
 * 
 */
@Namespace("/contactletter")
@Results({
		@Result(name = "queryList", location = "/contactletter/query_list.jsp"),
		@Result(name = "input", location = "/contactletter/input.jsp"),
		@Result(name = "showcontactletter", location = "/contactletter/view.jsp"),
		@Result(name = "auditContactLetter", location = "/contactletter/audit.jsp"),
		@Result(name = "waitHandledlist", location = "/contactletter/waithandedlist.jsp") })
@Action("/contactletter")
public class ContactLetterAction extends
		BaseAction<BusinessContactLetter, String> {
	private static final long serialVersionUID = 1L;
	// 流程处理历史key
	public static final String PROCESS_HISTORY_MAP = "PROCESS_HISTORY_MAP";
	/**
	 * 待审核列表路径
	 */
	private static final String WAIT_HANDLED_PAGE_URL = "/contactletter/contactletter!waithandledlist.action?parameter.isQuery=1&businessType=";
	private BusinessContactLetter entity = new BusinessContactLetter();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 业务类
	 */
	@Resource(name = "businessContactLetterServiceImpl")
	private BusinessContactLetterService businessContactletterservice;
	/**
	 * 分发业务类
	 */
	@Resource(name = "businessContactLetterSendEEServiceImpl")
	private BusinessContactLetterSendEEService businessContactlettersendeeservice;
	/**
	 * 用户信息类
	 */
	@Resource(name = "userInfoServiceImpl")
	private UserInfoService userinfoService;
	/**
	 * 审核类
	 */
	private ContactLetterAudit contactLetterAudit = new ContactLetterAudit();

	/**
	 * 为了获取公文编号引用汪杰的业务类
	 */
	@Resource(name = "commonOrderCodeServiceImpl")
	private CommonOrderCodeService commonOrderCodeService;

	/**
	 * 引导入 查询页面
	 * 
	 * @return
	 */
	public String query() {
		return "queryList";
	}

	/**
	 * 审核过程
	 */
	public String audit() {
		UserInfo userInfo = super.getUser();
		logger.info(contactLetterAudit);
		businessContactletterservice.auditThis(entity, contactLetterAudit,
				userInfo);
		super.addMessage("提示：审核成功！", WAIT_HANDLED_PAGE_URL, SysConstant.SUCCESS);
		return SUCCESS;
	}

	/**
	 * 引导进入待审核页面
	 * 
	 * @return
	 */
	public String waithandledlist() {
		return "waitHandledlist";
	}

	/**
	 * 转向审核页面
	 * 
	 * @return auditContactLetter
	 */
	public String toCheck() {
		String id = super.getRequest().getParameter("id");
		String taskid = super.getRequest().getParameter("taskId");
		if (id != null) {
			entity = businessContactletterservice.loadBusinessContactletter(id);
		}
		if (entity != null) {
			StringBuffer sb = new StringBuffer();
			UserInfo userinfo = userinfoService.getUserInfoByPersonId(entity
					.getReleaseUserId());
			entity.setReleaseUserName(userinfo.getUserName());
			entity.setReleaseUserDept(userinfo.getDeptName());
			String[] str = entity.getIssuerAreaUserIds().split(",");
			if (str != null && str.length > 0) {
				for (int i = 0; i < str.length; i++) {
					sb.append(userinfoService.getUserInfoByPersonId(str[i])
							.getUserName() + ";");
				}
			}
			entity.setIssuerAreaUserNames(sb.toString());
		}

		StringBuffer readNameStr = getReadNameStr4Audit(entity);
		if (readNameStr != null) {
			entity.setIssuerAreaUserNames(readNameStr.toString());
		}
		if (StringUtils.isNotBlank(entity.getDocumentType())) {
			if (entity.getDocumentType().equals("1")) {
				entity.setDocumentType("通告");
			} else {
				entity.setDocumentType("通知");
			}
		}
		contactLetterAudit.setTaskId(taskid);
		Map<String, List<CommonWorkflowResult>> checkresultMap = businessContactletterservice
				.getProcessHistoryList(id);// 获取任务id
		super.getRequest().setAttribute(PROCESS_HISTORY_MAP, checkresultMap);
		this.getRequest()
				.setAttribute("contactLetterAudit", contactLetterAudit);
		this.getRequest().setAttribute("entity", entity);
		return "auditContactLetter";
	}

	/**
	 * 获取用户已读信息
	 * 
	 * @param entity
	 *            实体
	 * @return
	 */
	public String getReadNameStr(BusinessContactLetter entity) throws Exception {
		StringBuffer str = new StringBuffer();
		if (StringUtils.isNotBlank(entity.getIssuerAreaUserIds())) {
			String[] ids = entity.getIssuerAreaUserIds().split(",");
			if (ids != null && ids.length > 0) {
				for (int i = 0; i < ids.length; i++) {
					String isread = businessContactlettersendeeservice
							.checkReadById(entity.getId(), ids[i]);
					String issuserNames = userinfoService
							.getUserInfoByPersonId(ids[i]).getUserName();
					if (StringUtils.isNotBlank(isread)) {
						if (isread.equals("1")) {
							str.append(issuserNames)
									.append("[<font color=\"red\">已读</font>]")
									.append(";");
							if ((i + 1) % 6 == 0) {
								str.append("</br>");
							}
						} else {
							str.append(issuserNames)
									.append("[<font color=\"green\">未读</font>]")
									.append(";");
							if ((i + 1) % 6 == 0) {
								str.append("</br>");
							}
						}
					}
				}
			}
		}
		return str.toString();
	}

	/**
	 * 获取用户已读信息
	 * 
	 * @param entity
	 *            实体
	 * @return
	 */
	public StringBuffer getReadNameStr4Audit(BusinessContactLetter entity) {
		String issuerUserIds = entity.getIssuerAreaUserIds();
		String issuserNames = "";
		StringBuffer str = new StringBuffer();
		str.append("姓名：");
		if (StringUtils.isNotBlank(issuerUserIds)) {
			String[] ids = issuerUserIds.split(",");
			if (ids != null && ids.length > 0) {
				for (int i = 0; i < ids.length; i++) {
					issuserNames = userinfoService
							.getUserInfoByPersonId(ids[i]).getUserName();
					str.append(issuserNames).append(";");
				}
			}
		}
		logger.info(str);
		return str;
	}

	/**
	 * 查询列表页面
	 * 
	 * @throws ParseException
	 */
	public void listdate4query() throws ParseException {
		@SuppressWarnings("rawtypes")
		Page page = super.initPage();
		UserInfo user = this.getUser();
		page = businessContactletterservice.queryPage4Query(entity, page, user);
		this.getRequest().setAttribute("user", user);
		setExcelParameter(page);
		convertObjToJson(page);
	}

	/**
	 * 待审核列表页面
	 */
	public void listdate4handList() {
		@SuppressWarnings("rawtypes")
		Page page = super.initPage();
		UserInfo user = super.getUser();
		page = businessContactletterservice.queryPage4WaitHand(entity, page,
				user);
		convertObjToJson(page);
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String delete() {
		String id = super.getRequest().getParameter("id");
		businessContactletterservice.deleteBusinessContactletter(id);// 逻辑删
																		// 将status=9
		super.addMessage("删除信息成功!",
				"/contactletter/contactletter!query.action",
				SysConstant.SUCCESS);
		return "queryList";
	}

	/**
	 * 查看详细信息。
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Override
	public String view() throws Exception {
		String id = super.getRequest().getParameter("id");
		UserInfo user = super.getUser();
		Boolean flag = false;
		entity = businessContactletterservice.loadBusinessContactletter(id);
		if (entity.getStatus().equals(Constant4Bcl.CHECKOFF)) {
			flag = true;
		}
		if (flag) {
			entity = businessContactletterservice.readContactletter(id,
					user.getPersonId());
		}
		if (StringUtils.isNotBlank(entity.getAuditUserId())) {
			entity.setAuditUserName(userinfoService.getUserInfoByPersonId(
					entity.getAuditUserId()).getUserName());
		}
		if (StringUtils.isNotBlank(entity.getReleaseUserId())) {
			entity.setReleaseUserName(userinfoService.getUserInfoByPersonId(
					entity.getReleaseUserId()).getUserName());
		}
		String str = getReadNameStr(entity);
		if (StringUtils.isNotBlank(str.toString())) {
			entity.setIssuerAreaUserNames(str.toString());
		}
		if (StringUtils.isNotBlank(entity.getDocumentType())) {
			if (entity.getDocumentType().equals("1")) {
				entity.setDocumentType("通告");
			} else {
				entity.setDocumentType("通知");
			}
		}
		Date now = new Date();
		String timeOutstr = "未过期";
		if (entity.getExpirationTime() != null) {
			if (entity.getExpirationTime().before(now)) {
				timeOutstr = "<font color='red'>已过期</font>";
			}
		}
		Map<String, List<CommonWorkflowResult>> checkresultMap = businessContactletterservice
				.getProcessHistoryList(id);
		super.getRequest().setAttribute(PROCESS_HISTORY_MAP, checkresultMap);
		super.getRequest().setAttribute("timeOutstr", timeOutstr);
		super.getRequest().setAttribute("entity", entity);
		return "showcontactletter";

	}

	/*
	 * 转到添加页
	 */
	@Override
	public String input() {
		UserInfo userInfo = super.getUser();
		String id = super.getRequest().getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			entity = businessContactletterservice.loadBusinessContactletter(id);
		}
		entity.setReleaseUserName(userInfo.getUserName());
		entity.setReleaseUserDept(userInfo.getOrgName());
		entity.setReleaseUserId(userInfo.getPersonId());
		setSomething(entity);
		entity.setAuditUserId(entity.getAuditUserId());
		super.getRequest().setAttribute("entity", entity);
		super.getRequest().setAttribute("user", this.getUser());
		return INPUT;
	}

	/**
	 * 设置一些值
	 * 
	 * @param entity
	 */
	public void setSomething(BusinessContactLetter entity) {
		UserInfo userInfo = super.getUser();
		String documentNumber = commonOrderCodeService
				.generatorLetterCode(userInfo.getRegionId());
		if (entity.getId() == null) {
			entity.setDocumentNumber("");
		} else {
			StringBuffer issuerUserNames = new StringBuffer();
			String name = "";
			entity.setDocumentNumber(documentNumber);
			if (StringUtils.isNotBlank(entity.getAuditUserId())) {
				entity.setAuditUserName(userinfoService.getUserInfoByPersonId(
						entity.getAuditUserId()).getUserName());
			}
			if (StringUtils.isNotBlank(entity.getIssuerAreaUserIds())) {
				String[] ids = entity.getIssuerAreaUserIds().split(",");
				for (int i = 0; i < ids.length; i++) {
					if (userinfoService.getUserInfoByPersonId(ids[i]) != null) {
						name = userinfoService.getUserInfoByPersonId(ids[i])
								.getUserName();
					}
					if (name != null) {
						issuerUserNames.append(name).append(";");
					}
				}
			}
			entity.setIssuerAreaUserNames(issuerUserNames.toString());
		}
	}

	/**
	 * 保存/提交业务联系函
	 * 
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		@SuppressWarnings("unchecked")
		List<FileItem> files = (List<FileItem>) super.sessionManager
				.get("FILES");
		String ids = super.getRequest().getParameter("idff");
		UserInfo userInfo = this.getUser();
		String isSend = this.getRequest().getParameter("entity.isSend");
		String isemegency = this.getRequest().getParameter("entity.isemegency");
		if (null == entity) {
			entity = new BusinessContactLetter();
		}
		if (StringUtils.isBlank(isemegency)) {
			isemegency = "0";
		}
		if (StringUtils.isNotBlank(ids)) {
			entity.setId(ids);
		}
		entity.setDocumentNumber(commonOrderCodeService
				.generatorLetterCode(userInfo.getRegionId()));
		entity.setIsSend(isSend);// 是否发送短信
		entity.setIsEmergency(isemegency);// 是否紧急
		String isneedCheck = this.getRequest().getParameter("isneedCheck");
		entity.setReleaseTime(new Date());// 发布时间
		entity.setIsAudit(isneedCheck);// 是否需要审核
		String status = this.getRequest().getParameter("entity.status");// 状态
		String auditUser = this.getRequest().getParameter("entity.auditUserId");
		if (StringUtils.isNotBlank(auditUser)) {// 如果选择了审核人员 那就要审核了。
			entity.setStatus(Constant4Bcl.WAITCHECK);// 待审核
		} else {
			entity.setStatus(Constant4Bcl.CHECKOFF);// 直接发布
		}
		if (status.equals("0")) {
			entity.setStatus("0");// 如果点击保存 则依旧是保存处理
		}
		entity = businessContactletterservice.saveBusinessContactletter(entity,
				userInfo, files);
		boolean b = true;
		String retu = "queryList";
		if (entity != null) {
			b = true;
			retu = setSaveReturn(entity.getId(), b);
		}
		return retu;
	}

	/**
	 * 设置此方法的返回值
	 * 
	 * @param id
	 *            z主键
	 * @param b
	 *            标示
	 * @return
	 */
	private String setSaveReturn(String id, boolean b) {
		String url = "";
		if ("1".equals(entity.getStatus()) || "2".equals(entity.getStatus())) {
			url = "/contactletter/contactletter!query.action?t="
					+ Math.random();
			assertResult(b, "联系函提交成功!", "联系函提交失败!", url);
		}
		if ("0".equals(entity.getStatus())) {
			url = "/contactletter/contactletter!query.action?t="
					+ Math.random();
			assertResult(b, "联系函保存成功!", "联系函保存失败!", url);
		}
		return SUCCESS;
	}

	/**
	 * 返回值
	 * 
	 * @param b
	 *            标示
	 * @param s_msg
	 *            成功信息
	 * @param f_msg
	 *            错误信息
	 * @param url
	 *            返回路径
	 */
	private void assertResult(boolean b, String s_msg, String f_msg, String url) {
		if (b) {
			super.addMessage(s_msg, url, SysConstant.SUCCESS);
		} else {
			super.addMessage(f_msg, url, SysConstant.ERROR);
		}
	}

	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	public BusinessContactLetter getBusinessContactletter() {
		return entity;
	}

	public void setBusinessContactletter(
			BusinessContactLetter businessContactletter) {
		this.entity = businessContactletter;
	}

	@Override
	public BusinessContactLetter getModel() {
		return entity;
	}

	@Override
	protected void prepareViewModel() throws Exception {
		if (entity == null) {
			entity = new BusinessContactLetter();
		}
	}

	@Override
	protected void prepareSaveModel() throws Exception {
		logger.info("save BusinessContactLetter");
	}

	public BusinessContactLetter getEntity() {
		return entity;
	}

	public void setEntity(BusinessContactLetter entity) {
		this.entity = entity;
	}

	public ContactLetterAudit getContactLetterAudit() {
		return contactLetterAudit;
	}

	public void setContactLetterAudit(ContactLetterAudit contactLetterAudit) {
		this.contactLetterAudit = contactLetterAudit;
	}

	public BusinessContactLetterService getBusinessContactletterservice() {
		return businessContactletterservice;
	}

	public void setBusinessContactletterservice(
			BusinessContactLetterService businessContactletterservice) {
		this.businessContactletterservice = businessContactletterservice;
	}
}
