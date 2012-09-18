package com.cabletech.business.assess.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.assess.dao.AssessExaminationDao;
import com.cabletech.business.assess.dao.AssessExaminationDetailDao;
import com.cabletech.business.assess.model.AssessExaminationResult;
import com.cabletech.business.assess.model.AssessMentDetail;
import com.cabletech.business.assess.service.AssessAppraiseService;
import com.cabletech.business.assess.service.AssessMonthAppraiseWorkflowService;
import com.cabletech.business.assess.service.AssessYearAppraiseWorkflowService;
import com.cabletech.business.base.model.SmParameter;
import com.cabletech.business.flowservice.util.ProMockPo;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.base.SysConstant;

/**
 * 月度考核业务接口实现
 * 
 * @author 杨隽 2012-08-03 创建
 * 
 */
@SuppressWarnings("all")
@Service
@Transactional
public class AssessAppraiseServiceImpl extends BaseServiceImpl implements
		AssessAppraiseService {
	@Resource(name = "assessExaminationDao")
	private AssessExaminationDao assessExaminationDao;
	@Resource(name = "assessExaminationDetailDao")
	private AssessExaminationDetailDao assessExaminationDetailDao;
	@Resource(name = "assessMonthAppraiseWorkflowService")
	private AssessMonthAppraiseWorkflowService monthWorkflowService;
	@Resource(name = "assessYearAppraiseWorkflowService")
	private AssessYearAppraiseWorkflowService yearWorkflowService;

	@Override
	protected AssessExaminationDao getBaseDao() {
		return assessExaminationDao;
	}

	@Override
	public void save(AssessExaminationResult result, UserInfo user) {
		if (StringUtils.isBlank(result.getId())) {
			result.setId(null);
		}
		String[] contentId = result.getContentId();
		double sumScore = 0d;
		Double[] scores = result.getItemScore();
		for (int i = 0; i < contentId.length; i++) {
			sumScore += scores[i];
		}
		result.setInspectionDate(new Date());
		result.setInspector(user.getPersonId());
		result.setScore(sumScore);
		assessExaminationDao.save(result);
		String resultId = result.getId();
		assessExaminationDetailDao.deleteAll(resultId);
		Double[] indicatorsValue = result.getIndicatorsValue();
		String[] ratingDescs = result.getRatingDesc();
		for (int i = 0; i < contentId.length; i++) {
			AssessMentDetail detail = new AssessMentDetail();
			detail.setResultId(resultId);
			detail.setScore(scores[i]);
			detail.setRatingDesc(ratingDescs[i]);
			detail.setContentId(contentId[i]);
			detail.setIndicatorsValue(indicatorsValue[i]);
			assessExaminationDetailDao.save(detail);
		}
		if (AssessExaminationResult.IS_SUBMITED.equals(result.getIsSubmited())) {
			SmParameter parameter = getSmParameter();
			if (StringUtils.isBlank(result.getTaskId())) {
				doWorkflowStart(result, user, parameter);
			} else {
				result.setApproveResult(SysConstant.PASS_WORKFLOW_TRANSTION);
				doWorkflow(result, user);
			}
		}
	}

	@Override
	public AssessExaminationResult view(String id) {
		AssessExaminationResult result = assessExaminationDao.get(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		List<Map<String, Object>> detailList = assessExaminationDetailDao
				.getTableItemContent(map);
		int level = assessExaminationDetailDao.getMaxLvItem(map);
		result.setDetailList(detailList);
		result.setLevel(level);
		return result;
	}

	@Override
	public List<Map<String, Object>> queryResultList(
			Map<String, String> parameter) {
		return assessExaminationDao.queryResultList(parameter);
	}

	@Override
	public void doWorkflow(AssessExaminationResult result, UserInfo user) {
		Map<String, Object> map = new HashMap<String, Object>();
		ProMockPo taskPi = new ProMockPo();
		taskPi.setTaskId(result.getTaskId());
		taskPi.setBzid(result.getId());
		taskPi.setUserId(user.getId());
		taskPi.setUserName(user.getUserName());
		taskPi.setTransition(result.getApproveResult());
		taskPi.setComment(result.getApproveRemark());
		SmParameter smParameter = getSmParameter();
		if (AssessExaminationResult.MONTH_ASSESS_.equals(result.getTableType())) {
			taskPi = monthWorkflowService.doTask(taskPi, smParameter);
		}
		if (AssessExaminationResult.YEAR_ASSESS_.equals(result.getTableType())) {
			taskPi = yearWorkflowService.doTask(taskPi, smParameter);
		}
		if (taskPi.isFlowOver()) {
			AssessExaminationResult assessExaminationResult = assessExaminationDao
					.get(result.getId());
			assessExaminationResult.setState(AssessExaminationResult.END_STATE);
			assessExaminationDao.save(assessExaminationResult);
		}
	}

	/**
	 * 获取短信发送参数
	 * 
	 * @return SmParameter 短信发送参数
	 */
	private SmParameter getSmParameter() {
		return null;
	}

	/**
	 * 执行月度考核工作流
	 * 
	 * @param user
	 *            UserInfo
	 * @param result
	 *            AssessExaminationResult
	 * @param smParameter
	 *            SmParameter 短信发送参数
	 */
	private void doWorkflowStart(AssessExaminationResult result, UserInfo user,
			SmParameter smParameter) {
		Map<String, Object> map = new HashMap<String, Object>();
		ProMockPo taskPi = new ProMockPo();
		taskPi.setBzid(result.getId());
		taskPi.setUserId(user.getPersonId());
		taskPi.setUserName(user.getUserName());
		map.put("regiontype", "1");
		if (user.isCityMobile()) {
			map.put("regiontype", "2");
		}
		if (user.isProvinceMobile()) {
			map.put("regiontype", "3");
		}
		taskPi.setBzdate(map);
		if (AssessExaminationResult.MONTH_ASSESS_.equals(result.getTableType())) {
			monthWorkflowService.sendTaskTwoSteps(taskPi, smParameter);
		}
		if (AssessExaminationResult.YEAR_ASSESS_.equals(result.getTableType())) {
			yearWorkflowService.sendTaskTwoSteps(taskPi, smParameter);
		}
	}
}