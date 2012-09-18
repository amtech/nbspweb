package com.cabletech.business.ah.rating.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import com.cabletech.baseinfo.business.Service.BaseInfoProvider;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.ah.rating.model.PersonFlow;
import com.cabletech.business.ah.rating.service.PersonFlowService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 
 * 人员流程定义
 * 
 * @author wj
 * 
 */
@Namespace("/ah")
@Results({
		@Result(name = "list", location = "/ah/rating/person_flow_list.jsp"),
		@Result(name = "input", location = "/ah/rating/person_flow_input.jsp"),
		@Result(name = "edit", location = "/ah/rating/person_flow_edit.jsp") })
@Action("/personFlowAction")
public class PersonFlowAction extends BaseAction<PersonFlow, String> {

	// 提示页面跳转路径
	public static final String LIST_PAGE_URL = "/ah/rating/person_flow_list.jsp";

	private PersonFlow entity;

	@Resource(name = "personFlowServiceImpl")
	private PersonFlowService personFlowService;

	@Resource(name = "baseInfoProvider")
	private BaseInfoProvider baseInfoProvider;

	/**
	 * 列表界面
	 * 
	 * @return String
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 添加界面
	 * 
	 * @return String
	 */
	public String input() {
		return INPUT;
	}

	/**
	 * 编辑界面
	 * 
	 * @return String
	 */
	public String edit() {
		Map<String, Object> parameters = initCondition();
		List<Map<String, Object>> processers = this.personFlowService
				.searchProcesserByPid(parameters);
		Map personInfo = this.baseInfoProvider.getPersonService()
				.getPersonInfo((String) parameters.get("personId"));
		getRequest().setAttribute("processers", processers);
		getRequest().setAttribute("personInfo", personInfo);
		getRequest().setAttribute("processerIndex", processers.size());
		return "edit";
	}

	/**
	 * 删除数据
	 * 
	 * @return String
	 */
	public String delete() {
		Map<String, Object> parameters = initCondition();
		personFlowService
				.deletePersonFlows((String) parameters.get("personId"));
		super.addMessage("流程定义删除成功!", LIST_PAGE_URL, SysConstant.SUCCESS);
		return SUCCESS;
	}

	/**
	 * 列表数据
	 */
	public void listData() {
		Map<String, Object> parameters = initCondition();
		Page<Map<String, Object>> page = this.initPage();
		Page ret = personFlowService.searchPersonFlows(parameters, page);
		convertObjToJson(ret);
	}

	/**
	 * 筛选考核人
	 */
	public void searchRatingPersons() {
		Map<String, Object> parameters = initCondition();
		String ratingPersons = personFlowService
				.searchRatingPersons(parameters);
		try {
			this.outPrint(ratingPersons, true);
		} catch (IOException e) {
			logger.error(e);
		}
	}

	/**
	 * 保存
	 * 
	 * @return
	 */
	public String save() {
		Map<String, Object> parameters = initCondition();
		personFlowService.save(parameters);
		addMessage("流程定义保存成功!", LIST_PAGE_URL, SysConstant.SUCCESS);
		return SUCCESS;
	}

	/**
	 * 封装查询条件
	 * 
	 * @return Map
	 */
	private Map<String, Object> initCondition() {
		Map<String, Object> map = new HashMap<String, Object>();
		UserInfo userInfo = super.getUser();
		String regioId = getRequest().getParameter("regionId");
		if (StringUtils.isBlank(regioId)) {
			regioId = this.getUser().getRegionId();
		}
		map.put("regionId", regioId);
		map.put("orgId", getRequest().getParameter("orgId"));
		map.put("businessType", getRequest().getParameter("businessType"));
		map.put("postOffice", getRequest().getParameter("postOffice"));
		map.put("ratingPersons", getRequest()
				.getParameterValues("newResources"));
		map.put("processer", getRequest().getParameterValues("processer"));
		map.put("personId", getRequest().getParameter("personId"));
		map.put("personName", getRequest().getParameter("personName"));
		return map;
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	public PersonFlow getModel() {
		return null;
	}

	public PersonFlow getEntity() {
		return entity;
	}

	public void setEntity(PersonFlow entity) {
		this.entity = entity;
	}
}