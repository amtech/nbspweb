package com.cabletech.business.assess.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import com.cabletech.business.assess.model.AssessExaminationResult;
import com.cabletech.business.assess.model.AssessTemplate;
import com.cabletech.business.assess.service.AssessAppraiseService;
import com.cabletech.business.assess.service.AssessExaminationService;
import com.cabletech.business.assess.service.AssessTemplateContentService;
import com.cabletech.business.assess.service.AssessTemplateService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.DateUtil;
import com.cabletech.common.util.Page;

/**
 * 现场检查
 * 
 * @author wj 2012-07-31 创建
 * 
 */
@Namespace("/assess")
@Results({
		@Result(name = "list", location = "/assess/examination/assess_examination_list.jsp"),
		@Result(name = "inputfirst", location = "/assess/examination/assess_examination_inputfirst.jsp"),
		@Result(name = "inputsecond", location = "/assess/examination/assess_examination_inputsecond.jsp"),
		@Result(name = "view", location = "/assess/examination/assess_examination_view.jsp")
})
@Action("/assessExaminationAction")
@SuppressWarnings("all")
public class AssessExaminationAction extends BaseAction<AssessExaminationResult, String> {

	private AssessExaminationResult examination;

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
	
	/**
	 * 考核模版业务服务
	 */
	@Resource(name = "assessTemplateServiceImpl")
	private AssessTemplateService assessTemplateService;
	
	public static final String TABLE_TYPE_EXAMINATION = "03";//考核表类型-现场检查
	
	/**
	 * 添加 第一步 跳转
	 */
	public String inputFirst() {
		Map<String, String> parameter = this.initConditions();
		List<Map<String, Object>> ls = assessExaminationService.queryAppraiseTables(parameter);
		getRequest().setAttribute("appraiseTables", ls);
		return "inputfirst";
	}
	
	/**
	 * 添加 第二步 跳转
	 */
	public String inputSecond() {
		Map<String, String> parameter = this.initConditions();
		getRequest().setAttribute("tableId", parameter.get("appraiseTableId"));
		getRequest().setAttribute("tableName", parameter.get("appraiseTableName"));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableId", parameter.get("appraiseTableId"));
		//获取模板内容
		List<Map<String,Object>> newlist=assessTemplateContentService.getTableItemList(map);
		//获取模板最大列数
		int maxItemCount=assessTemplateContentService.getMaxTableItem(map);
		this.getRequest().setAttribute("maxitemcount", maxItemCount);
		this.getRequest().setAttribute("templatecontent", newlist);
		return "inputsecond";
	}
	
	/**
	 * 列表 跳转
	 */
	public String list() {
		Map<String,String> monthSlot = DateUtil.getMonthSlot();//当前月的时间段
		getRequest().setAttribute("monthSlot", monthSlot);
		return LIST;
	}
	
	/**
	 * 列表 数据
	 */
	public void listData() {
		Map<String, String> parameters = initConditions();
		Page<Map<String, Object>> page = this.initPage();
		page = assessExaminationService.queryResultList(parameters, page);
		setExcelParameter(page);
		convertObjToJson(page);
	}

	/**
	 * 明细 跳转
	 */
	public String view() {
		AssessExaminationResult assessExaminationResult = assessAppraiseService.view(super.getParameter("id"));
		String siteName = assessExaminationService.viewResourceInfo(assessExaminationResult.getSiteId());
		AssessTemplate template = assessTemplateService.getTemplate(assessExaminationResult.getTableId());
		//获取模板内容
		List<Map<String,Object>> newlist=assessExaminationResult.getDetailList();
		//获取模板最大列数
		int maxItemCount=assessExaminationResult.getLevel();
		List<Map<String, Object>> list = assessTemplateContentService.processList(maxItemCount,newlist);
		this.getRequest().setAttribute("maxitemcount", maxItemCount);
		this.getRequest().setAttribute("templatecontent", list);
		if(null!=template)
			this.getRequest().setAttribute("appraiseTableName",template.getTableName());
		this.getRequest().setAttribute("assessExaminationResult", assessExaminationResult);
		this.getRequest().setAttribute("siteName", siteName);
		return this.VIEW;
	}

	/**
	 * 明细 数据
	 */
	public String save() {
		assessExaminationService.save(getExamination());
		super.addMessage("保存成功","/assess/assessExaminationAction!inputFirst.action", SysConstant.SUCCESS);
		return SUCCESS;
	}
	
	/**
	 * 封装查询条件
	 * @return Map
	 */
	private Map<String, String> initConditions() {
		HttpServletRequest request = this.getRequest();
		Map<String, String> map = new HashMap<String, String>();
		UserInfo userInfo = super.getUser();
		String regionId = request.getParameter("regionId");//区域
		if(StringUtils.isBlank(regionId)){
			regionId = userInfo.getRegionId();
		}
		String orgId = request.getParameter("orgId");//组织
		String inspector = request.getParameter("inspector");//检查人
		String stationType = request.getParameter("stationType");//站点类型
		String stationId = request.getParameter("stationId");//站点
		String principal = request.getParameter("principal");//维护责任人
		String stationName = request.getParameter("stationName");//站点名称
		String inspectionDate = request.getParameter("inspectionDate");//检查日期
		String startTime = request.getParameter("startTime");//开始时间
		String endTime = request.getParameter("endTime");//结束时间
		if(StringUtils.isBlank(startTime)&&StringUtils.isBlank(endTime)){
			startTime = DateUtil.getMonthSlot().get("startTime");
			endTime   = DateUtil.getMonthSlot().get("endTime");
		}
		String appraiseTableId = request.getParameter("appraiseTableId");//模板ID
		String appraiseTableName = request.getParameter("appraiseTableName");//模板名称
		map.put("tableType",TABLE_TYPE_EXAMINATION);
		map.put("regionId", regionId);
		map.put("orgId", orgId);
		map.put("inspector", inspector);
		map.put("stationType", stationType);
		map.put("stationId", stationId);
		map.put("principal", principal);
		map.put("stationName", stationName);
		map.put("inspectionDate", inspectionDate);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("appraiseTableId", appraiseTableId);
		map.put("appraiseTableName", appraiseTableName);
		return map;
	}
	
	@Override
	protected void prepareSaveModel() throws Exception {
	}

	/**
	 * @return the examination
	 */
	public AssessExaminationResult getExamination() {
		return examination;
	}

	/**
	 * @param examination the examination to set
	 */
	public void setExamination(AssessExaminationResult examination) {
		this.examination = examination;
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	public AssessExaminationResult getModel() {
		return null;
	}
}