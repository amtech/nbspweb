package com.cabletech.business.workflow.electricity.oilengine.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.electricity.oilengine.model.OilRecord;
import com.cabletech.business.workflow.electricity.oilengine.service.OilRecordService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 油机加油
 * 
 * @author wangt
 * @author 杨隽 2012-05-14 添加listData()方法
 * 
 */
@Namespace("/oil")
@Results({ @Result(name = "list", location = "/workflow/electricity/oilengine/oilenginemanage-list.jsp") })
@Action("/oilRecordAction")
public class OilRecordAction extends BaseAction<OilRecord, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 油机实体
	private OilRecord entity = new OilRecord();
	// 油机管理业务实现
	@Resource(name = "oilRecordServiceImpl")
	private OilRecordService service;

	@Override
	public OilRecord getModel() {
		return entity;
	}

	/**
	 * 保存
	 * 
	 * @return
	 */
	public String save() {
		UserInfo user = (UserInfo) super.getUser();
		service.saveOilRecord(entity, user);
		super.addMessage("加油成功!", "/oil/oilEngineManageAction!list.action",
				SysConstant.SUCCESS);
		return SUCCESS;
	}

	/**
	 * 查看加油记录列表数据信息
	 */
	@SuppressWarnings("rawtypes")
	public void listData() {
		String engineId = super.getParameter("engineId");
		Page page = super.initPage();
		page = service.getList(engineId, page);
		convertObjToJson(page);
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	protected void prepareSaveModel() throws Exception {
		if (entity == null) {
			entity = new OilRecord();
		}
	}
}
