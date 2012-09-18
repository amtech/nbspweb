package com.cabletech.business.workflow.fault.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.fault.service.FaultDispatchWaitDeletedService;
import com.cabletech.common.util.Page;

/**
 * 待删除工作ACTION
 * 
 * @author 杨隽 2012-02-22 创建
 * 
 */
@Namespace("/workflow")
@Results({ @Result(name = "list", location = "/workflow/fault/fault_dispatch_waitdeleted_list.jsp") })
@Action("/faultDispatchWaitDeletedAction")
public class FaultDispatchWaitDeletedAction extends
		FaultBaseAction<String, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 待删除工作业务接口
	@Resource(name = "faultDispatchWaitDeletedServiceImpl")
	private FaultDispatchWaitDeletedService faultDispatchWaitDeletedService;

	/**
	 * 转到待删除列表页面
	 * 
	 * @return String
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 获取待删除列表数据
	 */
	@SuppressWarnings("rawtypes")
	public void listdata() {
		UserInfo userInfo = super.getUser();
		super.preSetListQuery(userInfo);
		Page page = faultDispatchWaitDeletedService.getWaitDeletedList(
				super.parameter, userInfo);
		
		super.setExcelParameter(page);
		convertObjToJson(page);
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
