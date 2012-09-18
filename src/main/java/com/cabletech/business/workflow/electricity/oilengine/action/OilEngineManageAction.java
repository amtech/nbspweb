package com.cabletech.business.workflow.electricity.oilengine.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.business.workflow.electricity.oilengine.model.OilEngine;
import com.cabletech.business.workflow.electricity.oilengine.service.OilEngineManageService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;
import com.cabletech.common.util.StringUtil;

/**
 * 油机管理
 * 
 * @author 王甜 2012年5月2日
 * @author 杨隽 2012-05-14 添加viewOilRecord()方法
 * @author 杨隽 2012-05-15 提取setExcelParameter()方法并放入基类
 * 
 */
@Namespace("/oil")
@Results({
		@Result(name = "input", location = "/workflow/electricity/oilengine/oilenginemanage-input.jsp"),
		@Result(name = "recordaddoil", location = "/workflow/electricity/oilengine/oilrecord-input.jsp"),
		@Result(name = "viewoilrecord", location = "/workflow/electricity/oilengine/oilenginemanage-viewoilrecord.jsp"),
		@Result(name = "assign-input", location = "/workflow/electricity/oilengine/oilenginemanage-assign.jsp"),
		@Result(name = "view", location = "/workflow/electricity/oilengine/oilenginemanage-view.jsp"),
		@Result(name = "list", location = "/workflow/electricity/oilengine/oilenginemanage-list.jsp") })
@Action("/oilEngineManageAction")
public class OilEngineManageAction extends BaseAction<OilEngine, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 油机实体
	private OilEngine entity;
	// 油机管理业务实现
	@Resource(name = "oilEngineManageServiceImpl")
	private OilEngineManageService service;

	/**
	 * 列表页面
	 * 
	 * @return
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 列表数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes" })
	public void listData() throws Exception {
		prepareSaveModel();
		Page page = super.initPage();
		entity.setPage(page);
		page = service.getOilEngineList(entity, super.getUser());
		super.setExcelParameter(page);
		convertObjToJson(page);
	}

	@Override
	public String input() {
		return INPUT;
	}

	/**
	 * 保存
	 * 
	 * @return
	 */
	public String save() {
		String origin = "update";
		if (StringUtils.isBlank(entity.getId())) {
			origin = "save";
		}
		service.saveOilEngine(entity);
		if (origin.equals("update")) {
			super.addMessage("修改油机信息成功!",
					"/oil/oilEngineManageAction!list.action",
					SysConstant.SUCCESS);
		} else {
			super.addMessage("保存油机信息成功!",
					"/oil/oilEngineManageAction!input.action",
					SysConstant.SUCCESS);
		}
		return SUCCESS;
	}

	/**
	 * 删除
	 */
	public String del() {
		String[] id = super.getRequest().getParameterValues("id");
		service.deleteOilEngine(id);
		super.addMessage("删除油机信息成功!", "/oil/oilEngineManageAction!list.action",
				SysConstant.SUCCESS);
		return SUCCESS;
	}

	/**
	 * 检测编码是否已经存在
	 * 
	 * @return
	 */
	public String checkCode() {
		String id = super.getRequest().getParameter("id");
		String codevalue = super.getRequest().getParameter("code");
		long n = service.getCodeNumber(id, codevalue);
		super.convertObjToJson(n);
		return null;
	}

	/**
	 * 记录加油
	 * 
	 * @return
	 * @throws Exception
	 */
	public String recordAddOil() throws Exception {
		prepareViewModel();
		return "recordaddoil";
	}

	/**
	 * 进入查看加油记录列表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewOilRecord() throws Exception {
		prepareViewModel();
		return "viewoilrecord";
	}

	/**
	 * 分配油机
	 * 
	 * @return
	 */
	public String assign() {
		return "assign-input";
	}

	/**
	 * 获取可分配滴油机列表
	 */
	public String getOilEngine() {
		String property_right = super.getRequest().getParameter(
				"property_right");
		String oilengine_code = super.getRequest().getParameter(
				"oilengine_code");
		List<Map<String, Object>> ls = service.getOilEngine(property_right,
				oilengine_code);
		String oilEngine = StringUtil.selectedOtionsAjaxStr(ls);
		super.convertObjToJson(oilEngine);
		return null;
	}

	/**
	 * 油机分配
	 * 
	 * @return
	 */
	public String assEngine() {
		String id = entity.getId();
		String maintenanceId = entity.getMaintenanceId();
		service.assEngine(id, maintenanceId);
		super.addMessage("分配油机信息成功!",
				"/oil/oilEngineManageAction!assign.action", SysConstant.SUCCESS);
		return SysConstant.SUCCESS;
	}

	@Override
	public OilEngine getModel() {
		return entity;
	}

	public OilEngine getEntity() {
		return entity;
	}

	public void setEntity(OilEngine entity) {
		this.entity = entity;
	}

	@Override
	protected void prepareViewModel() throws Exception {
		String id = super.getRequest().getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			entity = service.viewOilEngine(id);
			super.getRequest().setAttribute("entity", entity);
			super.getRequest().setAttribute("id", id);
		} else {
			entity = new OilEngine();
		}
	}

	@Override
	protected void prepareSaveModel() throws Exception {
		if (entity == null) {
			entity = new OilEngine();
		}
	}
}
