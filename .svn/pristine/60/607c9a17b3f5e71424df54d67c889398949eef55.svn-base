package com.cabletech.business.workflow.fault.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.fault.model.FaultAlert;
import com.cabletech.business.workflow.fault.service.FaultAlertService;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 故障告警单处理Action
 * 
 * @author 杨隽 2011-10-26 创建
 * @author 杨隽 2011-10-27 提取公共的代码部分，并补充“忽略”方法内容
 * @author 杨隽 2011-11-02 添加list方法
 * @author 杨隽 2012-02-07 修改unDispatchedList()方法和list()方法
 * 
 */
@Namespace("/workflow")
@Results({
		@Result(name = "undispatched_list", location = "/workflow/fault/fault_alert_undispatched_list.jsp"),
		@Result(name = "view", location = "/workflow/fault/fault_alert_view.jsp"),
		@Result(name = "list", location = "/workflow/fault/fault_alert_list.jsp") })
@Action("/faultAlertAction")
public class FaultAlertAction extends FaultBaseAction<FaultAlert, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 未派单故障告警单列表跳转页面名称
	private static final String UNDISPATCHED_LIST = "undispatched_list";
	// 故障告警单业务处理服务
	@Resource(name = "faultAlertServiceImpl")
	private FaultAlertService faultAlertManager;

	/**
	 * 进入故障告警单列表页面
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 故障告警数据
	 */
	@SuppressWarnings("rawtypes")
	public void listdata() {
		UserInfo userInfo = super.getUser();
		Page page = super.initPage();
		super.parameter.setPage(page);
		Page pagedate = faultAlertManager.getList(super.parameter, userInfo);
		super.setExcelParameter(pagedate);
		convertObjToJson(pagedate);
	}

	/**
	 * 未派单故障告警单列表页面
	 * 
	 * @return String
	 */
	public String unDispatchedList() {
		return UNDISPATCHED_LIST;
	}

	/**
	 * 未派单故障告警单列表数据
	 */
	@SuppressWarnings("rawtypes")
	public void unDispatchedListdata() {
		UserInfo userInfo = super.getUser();
		super.preSetListQuery(userInfo);
		Page page = faultAlertManager.getUnDispatchedList(super.parameter,
				userInfo);
		super.setExcelParameter(page);
		convertObjToJson(page);
	}

	@Override
	public String view() {
		FaultAlert faultAlert = faultAlertManager
				.viewFaultAlert(super.parameter.getId());
		// 获取故障告警单的资源名称
		String resourceName = super.faultResourceManager.viewResourceInfo(
				faultAlert.getStationId()).getResourceName();
		faultAlert.setStationName(resourceName);
		super.getRequest().setAttribute("fault_alert", faultAlert);
		return VIEW;
	}

	/**
	 * 故障忽略操作
	 * 
	 * @return String
	 */
	public String ignore() {
		UserInfo userInfo = super.getUser();
		faultAlertManager.ignore(super.parameter.getId(), userInfo);
		super.addMessage("提示：故障告警单忽略成功！", UNDISPATCHED_PAGE_URL,
				SysConstant.SUCCESS);
		return SUCCESS;
	}

	@Override
	public FaultAlert getModel() {
		return null;
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}
}
