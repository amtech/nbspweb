package com.cabletech.business.workflow.accident.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.business.workflow.accident.model.MmAccident;
import com.cabletech.business.workflow.accident.service.MmAccidentService;
import com.cabletech.common.base.BaseAction;

/**
 * 隐患信息处理Action
 * 
 * @author 杨隽 2012-08-27 创建
 * 
 */
@Namespace("/workflow")
@Results({ @Result(name = "view", location = "/workflow/accident/mm_accident_view.jsp") })
@Action("/mmAccidentAction")
public class MmAccidentAction extends BaseAction<MmAccident, String> {
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 隐患业务处理服务
	 */
	@Resource(name = "mmAccidentServiceImpl")
	private MmAccidentService accidentService;

	@Override
	public String view() {
		String id = super.getParameter("id");
		MmAccident accident = accidentService.viewAccident(id);
		super.getRequest().setAttribute("accident", accident);
		return VIEW;
	}

	@Override
	public MmAccident getModel() {
		return null;
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}
}
