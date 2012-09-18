package com.cabletech.business.assess.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.assess.model.AssessAppealForm;
import com.cabletech.business.assess.model.AssessExaminationResult;
import com.cabletech.business.assess.service.AssessAppealFormService;
import com.cabletech.business.assess.service.AssessAppraiseService;
import com.cabletech.business.assess.service.AssessExaminationService;
import com.cabletech.business.assess.service.AssessTemplateContentService;
import com.cabletech.business.flowservice.util.ProMockPo;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 考核管理-申诉记录
 * 
 * @author wj 2012-08-02 创建
 * 
 */
@Namespace("/assess")
@Results({
		@Result(name = "list", location = "/assess/appealform/assess_appealform_list.jsp"),
		@Result(name = "input", location = "/assess/appealform/assess_appealform_input.jsp"),
		@Result(name = "view", location = "/assess/appealform/assess_appealform_view.jsp"),
		@Result(name = "feedback", location = "/assess/appealform/assess_appealform_feedback.jsp"),
		@Result(name = "inputsecond", location = "/assess/examination/assess_examination_inputsecond.jsp"),
		@Result(name = "detail", location = "/assess/examination/assess_examination_details.jsp")
})
@Action("/assessAppealFormAction")
@SuppressWarnings("all")
public class AssessAppealFormAction extends BaseAction<AssessAppealForm, String> {
	
	
	@Resource(name = "assessAppealFormServiceImpl")
	private AssessAppealFormService assessAppealFormService;
	
	@Resource(name = "assessExaminationServiceImpl")
	private AssessExaminationService assessExaminationService;
	
	/**
	 * 考核/检查结果业务服务
	 */
	@Resource(name = "assessAppraiseServiceImpl")
	private AssessAppraiseService assessAppraiseService;
	/**
	 * 考核模版服务
	 */
	@Resource(name = "assessTemplateContentServiceImpl")
	private AssessTemplateContentService assessTemplateContentService;
	
	private AssessAppealForm appealForm;
	
	/**
	 * 问题反馈
	 */
	public String feedback() {
		Map<String,Object> result = assessAppealFormService.queryAppealForm(this.getRequest().getParameter("result"));
		this.getRequest().setAttribute("result",result);
		return INPUT;
	}
	
	/**
	 * 添加
	 */
	public String input() {
		Map<String, String> parameters = this.initConditions();
		List<Map<String,Object>> resultList = assessAppealFormService.queryCanAppealResultList(parameters);
		if(StringUtils.isNotBlank(parameters.get("resultId"))&&resultList.size()>0){
			Map<String,Object> resultMap = resultList.get(0);
			resultMap.put("CAUSE", parameters.get("cause"));
			getRequest().setAttribute("resultMap",resultMap);
			return "feedback";
		}
		getRequest().setAttribute("resultList",resultList);
		return INPUT;
	}
	
	/**
	 * 保存 数据
	 */
	public String save() {
		assessAppealFormService.startFlow(this.getAppealForm(),this.getUser());
		super.addMessage("保存成功","/assess/assessAppealFormAction!input.action", SysConstant.SUCCESS);
		return SUCCESS;
	}
	
	/**
	 * 列表 跳转
	 */
	public String list() {
		Map<String, String> parameters = this.initConditions();
		List<Map<String, Object>> ls = assessExaminationService.queryAppraiseTables(parameters);
		getRequest().setAttribute("appraiseTables", ls);
		return LIST;
	}
	
	/**
	 * 列表 数据
	 */
	public void listData() {
		Page<Map<String, Object>> page = this.initPage();
		Map<String, String> parameters = this.initConditions();
		page = assessAppealFormService.queryAppealFormList(parameters, page);
		setExcelParameter(page);
		convertObjToJson(page);
	}
	
	/**
	 * 查看 明细 数据
	 */
	public String view() {
		Map<String, Object> appealMap = assessAppealFormService.queryAppealForm(getRequest().getParameter("id"));
		List<Map<String,Object>> adjusstmentList = assessAppealFormService.queryAdjusstmentList(getRequest().getParameter("id"));
		List<ProMockPo> hisList = assessAppealFormService.queryApproveHisList(getRequest().getParameter("id"));
		AssessExaminationResult assessExaminationResult = assessAppraiseService.view((String)appealMap.get("EXAM_RESULT_ID"));
		List<Map<String,Object>> newlist=assessExaminationResult.getDetailList();
		int maxItemCount=assessExaminationResult.getLevel();
		List<Map<String, Object>> list = assessTemplateContentService.processList(maxItemCount,newlist);
		this.getRequest().setAttribute("appealMap", appealMap);
		this.getRequest().setAttribute("adjusstmentList", adjusstmentList);
		this.getRequest().setAttribute("hisList", hisList);
		this.getRequest().setAttribute("maxitemcount", maxItemCount);
		this.getRequest().setAttribute("templatecontent", list);
		return VIEW;
	}
	
	/**
	 * @return the appealForm
	 */
	public AssessAppealForm getAppealForm() {
		return appealForm;
	}

	/**
	 * @param appealForm the appealForm to set
	 */
	public void setAppealForm(AssessAppealForm appealForm) {
		this.appealForm = appealForm;
	}
	
	/**
	 * 封装查询条件
	 * @return Map
	 */
	private Map<String, String> initConditions() {
		HttpServletRequest request = this.getRequest();
		Map<String, String> map = new HashMap<String, String>();
		UserInfo userInfo = super.getUser();
		String orgId = request.getParameter("orgId");
		String regionId = request.getParameter("regionId");
		if(StringUtils.isBlank(orgId)&&userInfo.isContractor()){
			orgId = userInfo.getOrgId();
		}
		if(StringUtils.isBlank(regionId)){
			regionId = userInfo.getRegionId();
		}
		map.put("tableTypes", "'01','02'");
		map.put("resultId", request.getParameter("resultId"));
		map.put("cause", request.getParameter("cause"));
		map.put("appraiseMonth", request.getParameter("appraiseMonth"));
		map.put("orgId", orgId);
		map.put("regionId",regionId);
		map.put("tableId", request.getParameter("tableId"));
		
		return map;
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	public AssessAppealForm getModel() {
		return null;
	}
}