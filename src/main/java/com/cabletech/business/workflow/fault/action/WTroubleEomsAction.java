package com.cabletech.business.workflow.fault.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.business.workflow.fault.model.WTroubleEoms;
import com.cabletech.business.workflow.fault.service.WtroubleEomsService;
import com.cabletech.common.base.BaseAction;

/**
 * 故障告警单处理Action
 * 
 * @author 杨隽 2012-07-18 创建
 * 
 */
@Namespace("/workflow")
@Results({ @Result(name = "view", location = "/workflow/fault/wtrouble_eoms_view.jsp") })
@Action("/wtroubleEomsAction")
public class WTroubleEomsAction extends BaseAction<WTroubleEoms, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 故障EOMS工单业务处理服务
	@Resource(name = "wtroubleEomsServiceImpl")
	private WtroubleEomsService wtroubleEomsManager;

	/**
	 * 读取故障派单的EOMS信息
	 */
	public String view() {
		String eomsId = super.getRequest().getParameter("eomsId");
		WTroubleEoms wtroubleEoms = wtroubleEomsManager.viewEomsInfo(eomsId);
		super.getRequest().setAttribute("wtroubleEoms", wtroubleEoms);
		return VIEW;
	}

	@Override
	public WTroubleEoms getModel() {
		return null;
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}
}
