package com.cabletech.business.ah.rating.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.ah.rating.model.PersonRatingForm;
import com.cabletech.business.ah.rating.service.PersonRatingFormService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;
import com.cabletech.common.util.StringUtil;


/**
 * 人员考核表定义
 * @author wangt
 *
 */
@Namespace("/ah")
@Results({
		@Result(name = "list", location = "/ah/rating/personratingform_list.jsp"),
		@Result(name = "assignpage", location = "/ah/rating/personratingform_input.jsp")})
@Action("/PersonRatingFormAction")
public class PersonRatingFormAction extends BaseAction<PersonRatingForm, String>{
	private static final long serialVersionUID = 1L;

	private PersonRatingForm entity;

	@Resource(name = "personRatingFormServiceImpl")
	private PersonRatingFormService service;
	
	/**
	 * 列表页面
	 * @return 
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 查询列表的数据
	 */
	@SuppressWarnings("rawtypes")
	public void listData(){
		Page page = super.initPage();
		page = service.queryPage(entity, page);
	    convertObjToJson(page);
	}
	
	@Override
	public PersonRatingForm getModel() {
		return entity;
	}

	@Override
	protected void prepareViewModel() throws Exception {
		
	}

	@Override
	protected void prepareSaveModel() throws Exception {
		if (entity == null) {
			entity = new PersonRatingForm();
		}
	}

	/**
	 * 转到分配考核表页面
	 */
	public String toassignPersonPage() {
		super.getRequest().setAttribute("id", super.getRequest().getParameter("id").toString());
		super.getRequest().setAttribute("tablename", super.getParameter("table_name"));
		return "assignpage";
	}
	/**
	 * 查询已分配人员
	 */
	public void getPersonsAssigned() {
		UserInfo user = super.getUser();
		String jobtype = super.getRequest().getParameter("jobtype");
		String tableid = super.getRequest().getParameter("tableid");
		List<Map<String, Object>> ls = service.getPersonsAssigned(user.getRegionId(),jobtype,tableid);
		if(ls==null){
			super.convertObjToJson("");
		}else{
			String Persons = StringUtil.selectedOtionsAjaxStr(ls);
			super.convertObjToJson(Persons);
		}
	}
	/**
	 * 查询可分配人员
	 */
	public void getPersons() {
		UserInfo user = super.getUser();
		String jobtype = super.getRequest().getParameter("jobtype");
		List<Map<String, Object>> ls = service.getPersons(user.getRegionId(),jobtype);
		if(ls==null){
			super.convertObjToJson("");
		}else{
			String Persons = StringUtil.selectedOtionsAjaxStr(ls);
			super.convertObjToJson(Persons);
		}
	}
	/**
	 * 分配人员
	 */
	public String setPersonsForm() {
		service.saveRelationShip(entity);
		super.addMessage("设置参考人员成功!",
				"/ah/PersonRatingFormAction!list.action", SysConstant.SUCCESS);
		return SysConstant.SUCCESS;

	}

	public PersonRatingForm getEntity() {
		return entity;
	}

	public void setEntity(PersonRatingForm entity) {
		this.entity = entity;
	}
	
	
}
