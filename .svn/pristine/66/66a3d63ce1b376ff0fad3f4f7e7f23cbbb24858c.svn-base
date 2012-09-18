package com.cabletech.business.assess.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.assess.model.AssessExaminationResult;
import com.cabletech.business.assess.service.AssessAppraiseWaitHandledService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.util.Page;

/**
 * 月度考核待办工作列表ACTION
 * 
 * @author 杨隽 2012-08-04 创建
 * 
 */
@Namespace("/assess")
@Results({ @Result(name = "list", location = "/assess/monthappraise/assess_month_appraise_waithandled_list.jsp") })
@Action("/assessMonthAppraiseWaitHandledAction")
public class AssessMonthAppraiseWaitHandledAction extends
		BaseAction<AssessExaminationResult, String> {
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 考核/检查结果表单数据
	 */
	private AssessExaminationResult oeDispatchTask = new AssessExaminationResult();
	/**
	 * 考核/检查结果业务服务
	 */
	@Resource(name = "assessAppraiseWaitHandledServiceImpl")
	private AssessAppraiseWaitHandledService assessAppraiseWaitHandledService;

	/**
	 * 进入待办工作列表页面
	 * 
	 * @return String
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 获取待办工作数据
	 */
	@SuppressWarnings("rawtypes")
	public void listData() {
		Page page = super.initPage();
		UserInfo user = super.getUser();
		Map<String, String> parameter = getCondition(user);
		assessAppraiseWaitHandledService.queryResultPage(parameter, page);
		super.setExcelParameter(page);
		convertObjToJson(page);
	}

	public AssessExaminationResult getModel() {
		return oeDispatchTask;
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}
	
	/**
	 * 获取查询条件的map
	 * 
	 * @param user
	 *            UserInfo
	 * @return Map<String, String>
	 */
	private Map<String, String> getCondition(UserInfo user) {
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put("userId", user.getPersonId());
		parameter.put("tableType", AssessExaminationResult.MONTH_ASSESS_);
		if (StringUtils.isNotBlank(super.getParameter("regionId"))) {
			parameter.put("regionId", super.getParameter("regionId"));
		} else {
			parameter.put("regionId", user.getRegionId());
		}
		if (StringUtils.isNotBlank(super.getParameter("orgId"))) {
			parameter.put("orgId", super.getParameter("orgId"));
		} else {
			if (user.isContractor()) {
				parameter.put("orgId", user.getOrgId());
			}
		}
		if (StringUtils.isNotBlank(super.getParameter("businessType"))) {
			parameter.put("businessType", super.getParameter("businessType"));
		}
		if (StringUtils.isNotBlank(super.getParameter("yearmonth"))) {
			parameter.put("appraiseMonth", super.getParameter("yearmonth"));
		}
		return parameter;
	}
}
