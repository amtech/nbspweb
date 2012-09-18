package com.cabletech.business.workflow.electricity.security.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.cabletech.business.workflow.electricity.security.model.OeDispatchTask;
import com.cabletech.business.workflow.electricity.security.service.OeOilengineSearchService;
import com.cabletech.common.util.Page;

/**
 * 搜索油机信息Action
 * 
 * @author 杨隽 2012-05-09 创建
 * 
 */
@Namespace("/workflow")
@Action("/oeOilengineSearchAction")
public class OeOilengineSearchAction extends
		OeDispatchTaskBaseAction<OeDispatchTask, String> {
	// 供电保障派单表单数据
	private OeDispatchTask oeDispatchTask;
	// 搜索油机信息业务处理
	@Resource(name = "oeOilengineSearchServiceImpl")
	private OeOilengineSearchService oeOilengineSearchService;
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 查询油机列表数据
	 */
	@SuppressWarnings("rawtypes")
	public void listData() {
		Page page = super.initPage();
		oeDispatchTask.setPage(page);
		page = oeOilengineSearchService.getList(oeDispatchTask);
		convertObjToJson(page);
	}

	@Override
	public OeDispatchTask getModel() {
		return oeDispatchTask;
	}

	public OeDispatchTask getOeDispatchTask() {
		return oeDispatchTask;
	}

	public void setOeDispatchTask(OeDispatchTask oeDispatchTask) {
		this.oeDispatchTask = oeDispatchTask;
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}
}
