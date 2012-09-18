package com.cabletech.business.assess.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.assess.dao.AssessAppealFormDao;
import com.cabletech.business.assess.model.AssessAppealForm;
import com.cabletech.business.assess.model.AssessResultAdjusstment;
import com.cabletech.business.assess.service.AssessAppealFormService;
import com.cabletech.business.flowservice.util.ProMockPo;
import com.cabletech.business.flowservice.util.WorkFlowServiceClient;
import com.cabletech.business.workflow.common.model.CommonWorkflowResult;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.DateUtil;
import com.cabletech.common.util.Page;

/**
 * 考核管理-申诉记录
 * 
 * @author wj 2012-08-02 创建
 * 
 */
@Service
@Transactional
public class AssessAppealFormServiceImpl extends BaseServiceImpl implements
		AssessAppealFormService {

	@Resource(name = "assessAppealFormDao")
	private AssessAppealFormDao assessAppealFormDao;

	// 工作流webservice服务
	@Resource(name = "workFlowServiceClient")
	private WorkFlowServiceClient workflowClient;

	@Override
	protected BaseDao getBaseDao() {
		return assessAppealFormDao;
	}

	@Override
	public void startFlow(AssessAppealForm appealForm, UserInfo userInfo) {
		appealForm.setAppealTime(DateUtil.getNowDate());
		appealForm.setComplainant(userInfo.getPersonId());
		appealForm.setContractorId(userInfo.getOrgId());
		assessAppealFormDao.save(appealForm);

		ProMockPo po = new ProMockPo();
		po.setProcessName(AssessAppealForm.FLOW_NAME_APPEALFORM);
		po.setBzid(appealForm.getId());
		po.setUserId(userInfo.getPersonId());
		Map<String, Object> bzdate = new HashMap<String, Object>();
		if ("00".equals(userInfo.getRegionId().substring(4))) {
			bzdate.put("regiontype", AssessAppealForm.REGIONTYPE_CITY);
		} else {
			bzdate.put("regiontype", AssessAppealForm.REGIONTYPE_COUNTY);
		}
		po.setBzdate(bzdate);
		po = workflowClient.startProcesssignalFirst(po);
	}

	@Override
	public void doTask(AssessAppealForm appealForm, UserInfo userInfo) {
		AssessAppealForm entity = (AssessAppealForm) assessAppealFormDao
				.getSession().get(AssessAppealForm.class, appealForm.getId());
		if (AssessAppealForm.APPROVE_CHECK.equals(appealForm.getStep())) {// 复核
			entity.setCheckResult(appealForm.getTransition()); // 复核结果
			entity.setReviewer(userInfo.getPersonId());// 复核人
			entity.setCheckTime(DateUtil.getNowDate());// 复核时间
			entity.setCheckOpinion(appealForm.getComment());// 复核意见
			String[] causes = appealForm.getAdjusstmentCauses();
			String[] scores = appealForm.getAdjusstmentScores();
			double allScore = 0;
			assessAppealFormDao.delAdjusstmentByAppealId(appealForm.getId());
			for (int i = 0; i < causes.length; i++) {
				AssessResultAdjusstment ment = new AssessResultAdjusstment();
				ment.setAdjusstmentCause(causes[i]);
				ment.setAdjusstmentScore(Float.valueOf(scores[i]));
				ment.setAppealFormId(appealForm.getId());
				allScore += ment.getAdjusstmentScore();
				assessAppealFormDao.save(ment);
			}
			entity.setScore(allScore);
		}
		if (AssessAppealForm.APPROVE_CONFIRM.equals(appealForm.getStep())) {// 确认
			entity.setConformResult(appealForm.getTransition());// 确认结果
			entity.setOpinion(appealForm.getComment());// 确认意见
			entity.setAuditingState(AssessAppealForm.FLOW_OVER_STATE);
		}
		ProMockPo po = new ProMockPo();
		po.setUserId(userInfo.getPersonId());
		po.setUserName(userInfo.getUserName());
		po.setTaskId(appealForm.getTaskId());
		po.setTransition(appealForm.getTransition());
		po.setComment(appealForm.getComment());
		po = workflowClient.doExcution(po);
		assessAppealFormDao.save(entity);
	}

	@Override
	public List<ProMockPo> queryApproveHisList(String bzId) {
		List<ProMockPo> his = workflowClient.getTaskHisByBzid(bzId);
		List<ProMockPo> list = new ArrayList<ProMockPo>();
		for (ProMockPo po : his) {
			ProMockPo temp = new ProMockPo();
			if ("pass".equals(po.getTransition())) {
				temp.setTransition("通过");
			} else {
				temp.setTransition("不通过");
			}
			temp.setTaskName(po.getTaskName());
			temp.setUserName(po.getUserName());
			temp.setEndTime(po.getEndTime());
			temp.setDescription(po.getComment());
			if (po.getTaskName().contains("审核")) {
				list.add(temp);
			}
		}
		return list;
	}

	@Override
	public Page queryAppealFormList(Map<String, String> parameters, Page page) {
		return assessAppealFormDao.queryAppealFormList(parameters, page);
	}

	@Override
	public Page queryWaitHandledList(Map<String, String> parameters, Page page) {
		return assessAppealFormDao.queryWaitHandledList(parameters, page);
	}

	@Override
	public Map<String, Object> queryAppealForm(String id) {
		return assessAppealFormDao.queryAppealForm(id);
	}

	@Override
	public List<Map<String, Object>> queryAdjusstmentList(String appealFormId) {
		return assessAppealFormDao.queryAdjusstmentList(appealFormId);
	}

	@Override
	public List<Map<String, Object>> queryCanAppealResultList(
			Map<String, String> parameter) {
		return assessAppealFormDao.queryCanAppealResultList(parameter);
	}

}