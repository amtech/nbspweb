package com.cabletech.business.resource.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.resource.model.ResourceAllotForm;
import com.cabletech.business.resource.service.ResourceAllotService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;

/**
 * 资源分配
 * 
 * @author wj
 */
@Namespace("/resource")
@Results({
		@Result(name = "input", location = "/resource/resource_allot_input.jsp"),
		@Result(name = "confirm", location = "/resource/resource_allot_confirm.jsp") })
@Action("/resourceAllotAction")
public class ResourceAllotAction extends BaseAction<ResourceAllotForm, String> {
	private static final long serialVersionUID = 1L;
	/**
	 * 分配回收表单数据
	 */
	private ResourceAllotForm parameter;
	/**
	 * 资源分配回收业务处理
	 */
	@Resource(name = "resourceAllotServiceImpl")
	private ResourceAllotService resourceAllotService;

	/**
	 * 资源分配输入信息载入页面
	 */
	public String input() throws Exception {
		Map<String, String> whetherMap = SysConstant.getWhetherMap();
		super.getRequest().setAttribute("whetherMap", whetherMap);
		return INPUT;
	}

	/**
	 * 获取可供分配的资源
	 */
	public void getResForSelect() throws Exception {
		UserInfo userInfo = super.getUser();
		parameter.setOrgType(userInfo.getOrgType());
		parameter.setOrgId(userInfo.getOrgId());
		parameter.setUserRegionId(userInfo.getRegionId());
		convertObjToJson(resourceAllotService.queryResourcesString(parameter));
	}

	/**
	 * 资源分配信息确认页面
	 */
	public String confirm() throws Exception {
		Map<String, String> whetherMap = SysConstant.getWhetherMap();
		super.getRequest().setAttribute("whetherMap", whetherMap);
		super.getRequest().getSession().setAttribute("PARAMETER", parameter);
		return "confirm";
	}

	/**
	 * 资源分配列表数据
	 */
	public void confirmListData() {
		parameter = (ResourceAllotForm) super.getRequest().getSession()
				.getAttribute("PARAMETER");
		List<Map<String, Object>> list = resourceAllotService
				.queryResourceList(parameter);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("root", list);
		super.convertObjToJson(map);
	}

	/**
	 * 判断资源是否被分配给同一个对象的同一个专业
	 */
	public void isAllotedToSelf() {
		String result = resourceAllotService.isAllotedToSelf(parameter);
		try {
			super.outPrint(result, false);
		} catch (Exception ex) {
		}
	}

	/**
	 * 执行资源分配
	 */
	public String allot() throws Exception {
		UserInfo user = super.getUser();
		resourceAllotService.allotResources(parameter, user);
		super.addMessage("资源分配成功",
				"/resource/resourceAllotAction!input.action",
				SysConstant.SUCCESS);
		return SUCCESS;
	}

	/**
	 * 执行资源回收
	 */
	public String recycle() throws Exception {
		UserInfo user = super.getUser();
		resourceAllotService.recycleResources(parameter, user);
		super.addMessage("资源回收成功",
				"/resource/resourceAllotAction!input.action",
				SysConstant.SUCCESS);
		return SUCCESS;
	}

	public ResourceAllotForm getParameter() {
		return parameter;
	}

	public void setParameter(ResourceAllotForm parameter) {
		this.parameter = parameter;
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	public ResourceAllotForm getModel() {
		return parameter;
	}
}