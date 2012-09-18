package com.cabletech.business.assess.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.assess.model.AssessExaminationResult;
import com.cabletech.business.assess.model.AssessTemplate;
import com.cabletech.business.assess.service.AssessAppraiseService;
import com.cabletech.business.assess.service.AssessTemplateContentService;
import com.cabletech.business.assess.service.AssessTemplateService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.externalresources.ExternalResourcesAccessService;
import com.cabletech.common.util.DateUtil;

/**
 * 年度考核评分管理
 * 
 * @author 杨隽 2012-08-06 创建
 */
@Namespace("/assess")
@Results({
		@Result(name = "view", location = "/assess/yearappraise/assess_year_appraise_view.jsp"),
		@Result(name = "input", location = "/assess/yearappraise/assess_year_appraise_input.jsp"),
		@Result(name = "input_second", location = "/assess/yearappraise/assess_year_appraise_input_second.jsp") })
@Action("/assessYearAppraiseAction")
public class AssessYearAppraiseAction extends
		BaseAction<AssessExaminationResult, String> {
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 第二步输入页面路径
	 */
	private static final String INPUT_SECOND = "input_second";
	/**
	 * 考核模版业务服务
	 */
	@Resource(name = "assessTemplateServiceImpl")
	private AssessTemplateService assessTemplateService;
	/**
	 * 考核模版服务
	 */
	@Resource(name = "assessTemplateContentServiceImpl")
	private AssessTemplateContentService assessTemplateContentService;
	/**
	 * 考核/检查结果业务服务
	 */
	@Resource(name = "assessAppraiseServiceImpl")
	private AssessAppraiseService assessAppraiseService;
	/**
	 * 外部资源访问
	 */
	@Resource(name = "externalResourcesAccessService")
	private ExternalResourcesAccessService externalResourcesAccessService;
	/**
	 * 考核/检查结果表单数据
	 */
	private AssessExaminationResult assessExaminationResult = new AssessExaminationResult();

	/**
	 * 进入考核模板选择页面
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String input() {
		if (StringUtils.isNotBlank(super.getParameter("id"))) {
			assessExaminationResult = assessAppraiseService.view(super
					.getParameter("id"));
		}
		AssessTemplate template = new AssessTemplate();
		template.setTableType(super.getParameter("tableType"));
		List<Map<String, Object>> list = assessTemplateService
				.queryAssessTemplate(template);
		assessExaminationResult.setTableType(super.getParameter("tableType"));
		super.getRequest().setAttribute("templateList", list);
		if (StringUtils.isNotBlank(super.getParameter("taskId"))) {
			super.getRequest().setAttribute("taskId",
					super.getParameter("taskId"));
		}
		return INPUT;
	}

	/**
	 * 进入考核内容输入页面
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String inputSecond() {
		String taskId = assessExaminationResult.getTaskId();
		String tableType = assessExaminationResult.getTableType();
		super.getRequest().setAttribute("regionName",
				externalResourcesAccessService.getSystemregionname());
		AssessTemplate template = assessTemplateService
				.getTemplate(assessExaminationResult.getTableId());
		super.getRequest().setAttribute("tableName", template.getTableName());
		// 获取模板内容
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableId", assessExaminationResult.getTableId());
		map.put("month", DateUtil.UtilDate2Str(
				assessExaminationResult.getAppraiseMonth(), "yyyy-MM-dd"));
		map.put("orgId", assessExaminationResult.getContractorId());
		List<Map<String, Object>> newlist = assessTemplateContentService
				.getTableItemList(map);
		// 获取模板最大列数
		int maxItemCount = assessTemplateContentService.getMaxTableItem(map);
		if (StringUtils.isNotBlank(assessExaminationResult.getId())) {
			assessExaminationResult = assessAppraiseService
					.view(assessExaminationResult.getId());
			assessExaminationResult.setTaskId(taskId);
			assessExaminationResult.setTableType(tableType);
			maxItemCount = assessExaminationResult.getLevel();
			newlist = assessTemplateContentService.processList(
					assessExaminationResult.getLevel(),
					assessExaminationResult.getDetailList());
		}
		this.getRequest().setAttribute("maxitemcount", maxItemCount);
		this.getRequest().setAttribute("templatecontent", newlist);
		return INPUT_SECOND;
	}

	/**
	 * 进行考核评分的唯一性判断
	 */
	public void isExist() throws Exception {
		Map<String, String> parameter = getCondition();
		List<Map<String, Object>> list = assessAppraiseService
				.queryResultList(parameter);
		if (CollectionUtils.isEmpty(list)) {
			super.outPrint("0", true);
		} else {
			super.outPrint("1", true);
		}
	}

	/**
	 * 保存
	 * 
	 * @return String
	 */
	public String save() {
		UserInfo user = super.getUser();
		assessAppraiseService.save(assessExaminationResult, user);
		String url = "/assess/assessYearAppraiseAction!input.action?tableType="
				+ assessExaminationResult.getTableType();
		if (StringUtils.isNotBlank(assessExaminationResult.getTaskId())) {
			url = "/assess/assessMonthAppraiseWaitHandledAction!list.action";
		}
		super.addMessage("年度考核评分成功！", url, SysConstant.SUCCESS);
		return SUCCESS;
	}

	/**
	 * 查看
	 * 
	 * @return String
	 */
	public String view() {
		super.getRequest().setAttribute("regionName",
				externalResourcesAccessService.getSystemregionname());
		assessExaminationResult = assessAppraiseService.view(super
				.getParameter("id"));
		AssessTemplate template = assessTemplateService
				.getTemplate(assessExaminationResult.getTableId());
		super.getRequest().setAttribute("tableName", template.getTableName());
		List<Map<String, Object>> list = assessTemplateContentService
				.processList(assessExaminationResult.getLevel(),
						assessExaminationResult.getDetailList());
		assessExaminationResult.setDetailList(list);
		return VIEW;
	}

	@Override
	public AssessExaminationResult getModel() {
		return assessExaminationResult;
	}

	/**
	 * 获取查询条件Map
	 * 
	 * @return Map<String, String>
	 */
	private Map<String, String> getCondition() {
		Map<String, String> parameter = new HashMap<String, String>();
		if (StringUtils.isNotBlank(super.getParameter("id"))) {
			parameter.put("id", super.getParameter("id"));
		}
		if (StringUtils.isNotBlank(super.getParameter("appraiseMonth"))) {
			parameter.put("appraiseMonth", super.getParameter("appraiseMonth"));
		}
		if (StringUtils.isNotBlank(super.getParameter("contractorId"))) {
			parameter.put("contractorId", super.getParameter("contractorId"));
		}
		if (StringUtils.isNotBlank(super.getParameter("tableId"))) {
			parameter.put("tableId", super.getParameter("tableId"));
		}
		if (StringUtils.isNotBlank(super.getParameter("regionId"))) {
			parameter.put("regionId", super.getParameter("regionId"));
		}
		return parameter;
	}

	@Override
	protected void prepareViewModel() {
	}

	@Override
	protected void prepareSaveModel() {
	}

	public AssessExaminationResult getAssessExaminationResult() {
		return assessExaminationResult;
	}

	public void setAssessExaminationResult(
			AssessExaminationResult assessExaminationResult) {
		this.assessExaminationResult = assessExaminationResult;
	}
}
