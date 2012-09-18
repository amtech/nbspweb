package com.cabletech.business.workflow.fault.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.fault.condition.parameter.FaultQueryParameter;
import com.cabletech.business.workflow.fault.model.FaultAlert;
import com.cabletech.business.workflow.fault.model.FaultDispatch;
import com.cabletech.business.workflow.fault.model.FaultReply;
import com.cabletech.business.workflow.fault.service.FaultAlertService;
import com.cabletech.business.workflow.fault.service.FaultDispatchService;
import com.cabletech.business.workflow.fault.service.FaultReplyService;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 故障派单处理Action
 * 
 * @author 杨隽 2011-10-26 创建
 * @author 杨隽 2011-10-27 修改“故障派单填写页面跳转”方法、“故障派单操作”方法和“待办故障派单列表页面跳转”方法
 * @author 杨隽 2011-11-02 修改list方法
 * @author 杨隽 2011-11-04 添加“获取待办数量”的方法
 * @author 杨隽 2011-11-04 修改input方法和dispatch方法以支持手动派单
 * @author 杨隽 2011-11-05 添加“发现方式”变量属性
 * @author 杨隽 2011-11-29 修改waitHandledList()方法（将获取待办任务的巡检组编号改为代维单位编号）
 * @author 杨隽 2012-02-07 修改waitHandledList()方法和list()方法
 * @author 杨隽 2012-02-22 添加进入编辑派单页面时读取保存派单信息的业务功能
 * @author 杨隽 2012-05-04 更改使用公共的表单提交标识
 * @author 杨隽 2012-07-18 添加系统定义的“是否选择”Map
 * 
 */
@Namespace("/workflow")
@Results({
		@Result(name = "input", location = "/workflow/fault/fault_dispatch_input.jsp"),
		@Result(name = "waithandled_list", location = "/workflow/fault/fault_dispatch_waithandled_list.jsp"),
		@Result(name = "view", location = "/workflow/fault/fault_dispatch_viewdetail.jsp"),
		@Result(name = "list", location = "/workflow/fault/fault_dispatch_list.jsp") })
@Action("/faultDispatchAction")
public class FaultDispatchAction extends FaultBaseAction<FaultDispatch, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 待办故障派单列表跳转页面
	private static final String WAIT_HANDLED_LIST = "waithandled_list";
	// 故障发现方式
	private String findType;
	// 故障告警单实体
	private FaultAlert faultAlert;
	// 故障派单实体
	private FaultDispatch faultDispatch;
	// 故障告警单业务处理服务
	@Resource(name = "faultAlertServiceImpl")
	private FaultAlertService faultAlertManager;
	// 故障派单业务处理服务
	@Resource(name = "faultDispatchServiceImpl")
	private FaultDispatchService faultDispatchManager;
	// 故障派单回复业务处理服务
	@Resource(name = "faultReplyServiceImpl")
	private FaultReplyService faultReplyManager;

	/**
	 * 转到故障查询页面
	 * 
	 * @return String
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 获取故障查询数据
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public void listdata() {
		UserInfo userInfo = super.getUser();
		super.preSetListQuery(userInfo);
		Page page = faultDispatchManager.getList(super.parameter, userInfo);
		super.setExcelParameter(page);
		convertObjToJson(page);
	}

	/**
	 * 故障待办任务列表页面
	 * 
	 * @return String
	 */
	public String waitHandledList() {
		return WAIT_HANDLED_LIST;
	}

	/**
	 * 故障待办任务列表数据
	 * 
	 */
	@SuppressWarnings({ "rawtypes" })
	public void waitHandledListdata() {
		UserInfo userInfo = super.getUser();
		super.preSetListQuery(userInfo);
		Page page = faultDispatchManager.getWaitHandledList(super.parameter,
				userInfo);
		super.setExcelParameter(page);
		convertObjToJson(page);
	}

	@Override
	public String view() {
		FaultDispatch faultDispatch = faultDispatchManager
				.viewFaultDispatch(super.parameter.getId());
		FaultAlert faultAlert = faultAlertManager.viewFaultAlert(faultDispatch
				.getAlarmId());
		FaultReply faultReply = faultReplyManager.getFaultReply(super.parameter
				.getId());
		// 获取故障告警单的资源名称
		String resourceName = super.faultResourceManager.viewResourceInfo(
				faultAlert.getStationId()).getResourceName();
		faultAlert.setStationName(resourceName);
		Map<String, String> whetherMap = SysConstant.getWhetherMap();
		super.getRequest().setAttribute("whetherMap", whetherMap);
		super.getRequest().setAttribute("fault_alert", faultAlert);
		super.getRequest().setAttribute("fault_dispatch", faultDispatch);
		super.getRequest().setAttribute("fault_reply", faultReply);
		return VIEW;
	}

	@Override
	public String input() {
		faultAlert = new FaultAlert();
		if (StringUtils.isNotBlank(super.parameter.getId())) {
			faultAlert = faultAlertManager.viewFaultAlert(super.parameter
					.getId());
			String resourceName = super.faultResourceManager.viewResourceInfo(
					faultAlert.getStationId()).getResourceName();
			faultAlert.setStationName(resourceName);
		} else {
			faultAlert.setFindType(getFindType());
		}
		if (StringUtils.isNotBlank(super.parameter.getDispatchId())) {
			String dispatchId = super.parameter.getDispatchId();
			faultDispatch = faultDispatchManager.viewFaultDispatch(dispatchId);
			faultAlert = faultAlertManager.viewFaultAlert(faultDispatch
					.getAlarmId());
			String resourceName = super.faultResourceManager.viewResourceInfo(
					faultAlert.getStationId()).getResourceName();
			faultAlert.setStationName(resourceName);
		}
		return INPUT;
	}

	/**
	 * 故障派单操作
	 * 
	 * @return String
	 */
	public String dispatch() throws Exception {
		UserInfo userInfo = super.getUser();
		if (StringUtils.isBlank(faultDispatch.getId())) {
			faultDispatch.setId(null);
		}
		faultDispatchManager.dispatch(faultAlert, faultDispatch, userInfo);
		String message = "提示：故障保存成功！";
		if (SysConstant.FORM_IS_SUBMITED.equals(faultDispatch.getIsSubmited())) {
			message = "提示：故障派单成功！";
		}
		String url = UNDISPATCHED_PAGE_URL;
		if (FaultQueryParameter.DRAFT_ORIGIN
				.equals(super.parameter.getOrigin())) {
			url = DISPATCH_DRAFT_PAGE_URL;
		}
		super.addMessage(message, url, SysConstant.SUCCESS);
		return SUCCESS;
	}

	/**
	 * 故障删除
	 * 
	 * @return String
	 */
	public String delete() {
		faultDispatchManager.deleteDispatch(super.parameter.getId());
		String message = "提示：故障删除成功！";
		String url = UNDISPATCHED_PAGE_URL;
		if (FaultQueryParameter.DRAFT_ORIGIN
				.equals(super.parameter.getOrigin())) {
			url = DISPATCH_DRAFT_PAGE_URL;
		}
		if (FaultQueryParameter.WAIT_DELETED_ORIGIN.equals(super.parameter
				.getOrigin())) {
			url = WAIT_DELETED_PAGE_URL;
		}
		super.addMessage(message, url, SysConstant.SUCCESS);
		return SUCCESS;
	}

	@Override
	public FaultDispatch getModel() {
		return null;
	}

	public String getFindType() {
		return findType;
	}

	public void setFindType(String findType) {
		this.findType = findType;
	}

	public FaultAlert getFaultAlert() {
		return faultAlert;
	}

	public void setFaultAlert(FaultAlert faultAlert) {
		this.faultAlert = faultAlert;
	}

	public FaultDispatch getFaultDispatch() {
		return faultDispatch;
	}

	public void setFaultDispatch(FaultDispatch faultDispatch) {
		this.faultDispatch = faultDispatch;
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}
}
