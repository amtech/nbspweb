package com.cabletech.business.workflow.fault.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.fault.service.FaultDispatchHandledService;
import com.cabletech.common.util.Page;

/**
 * 
 * 已办工作ACTION
 * 
 * @author 杨隽 2012-02-22 创建
 * 
 */
@Namespace("/workflow")
@Results({ @Result(name = "list", location = "/workflow/fault/fault_dispatch_handled_list.jsp") })
@Action("/faultDispatchHandledAction")
public class FaultDispatchHandledAction extends FaultBaseAction<String, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 已办任务业务处理
	@Resource(name = "faultDispatchHandledServiceImpl")
	private FaultDispatchHandledService faultDispatchHandledService;

	/**
	 * 转到故障已处理界面
	 * 
	 * @return String
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 获取故障已处理数据
	 */
	@SuppressWarnings("rawtypes")
	public void listdata() {
		UserInfo userInfo = super.getUser();
		super.preSetListQuery(userInfo);
		Page page = faultDispatchHandledService.getHandledList(super.parameter,
				userInfo);
		
		super.setExcelParameter(page);
		convertObjToJson(page);
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	public String getModel() {
		// TODO Auto-generated method stub
		return null;
	}
}