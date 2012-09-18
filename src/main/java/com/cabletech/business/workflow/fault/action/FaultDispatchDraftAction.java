package com.cabletech.business.workflow.fault.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.fault.service.FaultDispatchDraftService;
import com.cabletech.common.util.Page;

/**
 * 故障派单草稿箱列表ACTION
 * 
 * @author 杨隽 2012-02-22 创建
 * 
 */
@Namespace("/workflow")
@Results({ @Result(name = "list", location = "/workflow/fault/fault_dispatch_drafts_list.jsp") })
@Action("/faultDispatchDraftAction")
@Scope("prototype")
public class FaultDispatchDraftAction extends FaultBaseAction<String, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 故障单草稿箱业务处理
	@Resource(name = "faultDispatchDraftServiceImpl")
	private FaultDispatchDraftService faultDispatchDraftService;

	public String getModel() {
		return null;
	}

	/**
	 * 转向草稿箱列表页面
	 * 
	 * @return String
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 草稿箱数据页面
	 */
	@SuppressWarnings("rawtypes")
	public void listdata() {
		UserInfo userInfo = super.getUser();
		super.preSetListQuery(userInfo);
		Page page = faultDispatchDraftService.getDraftList(super.parameter,
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
}