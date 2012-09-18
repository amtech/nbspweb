package com.cabletech.business.workflow.fault.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.fault.service.FaultDispatchCancelService;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 
 * 取消任务ACTION
 * 
 * @author 杨隽 2012-02-22 创建
 * 
 */
@Namespace("/workflow")
@Results({
		@Result(name = "list", location = "/workflow/fault/fault_dispatch_waitcanceled_list.jsp"),
		@Result(name = "canceledlist", location = "/workflow/fault/fault_dispatch_canceled_list.jsp") })
@Action("/faultDispatchCancelAction")
public class FaultDispatchCancelAction extends FaultBaseAction<String, String> {
	// 已取消工单列表跳转页面
	private static final String CANCELED_LIST = "canceledlist";
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 取消任务业务处理
	@Resource(name = "faultDispatchCancelServiceImpl")
	private FaultDispatchCancelService faultDispatchCancelService;

	/**
	 * 
	 * 待取消任务列表页面
	 * 
	 * @return String
	 */
	public String waitCanceledList() {
		return LIST;
	}

	/**
	 * 待取消列表数据
	 */
	@SuppressWarnings("rawtypes")
	public void waitCanceledListdata() {
		UserInfo userInfo = super.getUser();
		super.preSetListQuery(userInfo);
		Page page = faultDispatchCancelService.getWaitCanceledList(
				super.parameter, userInfo);

		super.setExcelParameter(page);
		convertObjToJson(page);
	}

	/**
	 * 
	 * 已取消任务列表页面
	 * 
	 * @return String
	 */
	public String canceledList() {
		return CANCELED_LIST;
	}

	/**
	 * 已取消列表数据
	 */
	@SuppressWarnings("rawtypes")
	public void canceledListdata() {
		UserInfo userInfo = super.getUser();
		super.preSetListQuery(userInfo);
		Page page = faultDispatchCancelService.getCanceledList(super.parameter,
				userInfo);

		super.setExcelParameter(page);
		convertObjToJson(page);
	}

	/**
	 * 
	 * 取消任务
	 * 
	 * @return String
	 */
	public String cancel() {
		faultDispatchCancelService.cancelDispatch(super.parameter.getId());
		String message = "提示：故障派单取消成功！";
		super.addMessage(message, WAIT_CANCELED_PAGE_URL, SysConstant.SUCCESS);
		return SUCCESS;
	}

	public String getModel() {
		return null;
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}
}