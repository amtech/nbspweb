package com.cabletech.business.desktop.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.desktop.service.BaseWorkService;
import com.cabletech.common.base.BaseAction;

/**
 * 基础工作
 * 
 * @author zhaobi
 * 
 */
@Namespace("/desktop")
@Results({ @Result(name = "index", location = "/frames/default/basework.jsp")
		   ,@Result(name = "certificatesmore", location = "/frames/default/more/validperioded_certificates_more_list.jsp")
})
@Action("/basework")
public class BaseWorkAction extends BaseAction<String, String> {

	@Resource(name = "baseWorkServiceImpl")
	private BaseWorkService baseWorkService;

	/**
	 * 基础模块信息
	 * 
	 * @return
	 */
	public String index() {

		UserInfo user = this.getUser();
		List<Map<String, Object>> validperiodedCertificatesList = baseWorkService
				.getValidperiodedCertificatesList(user);
		// 证书到期提醒
		this.getRequest().setAttribute("validperiodedCertificatesList",
				validperiodedCertificatesList);
		// 设备图表
		this.getRequest().setAttribute("terminalchart",
				baseWorkService.getTerminalChartData(user));
		return "index";
	}
	
	
	/**
	 * 证书到期提醒 --更多列表
	 * 
	 * @return
	 */
	public String validperiodedCertificatesMoreList() {
		UserInfo user = this.getUser();
		List<Map<String, Object>> validperiodedCertificatesList = baseWorkService.getValidperiodedCertificatesList(user);
		// 证书到期提醒
		this.getRequest().setAttribute("validperiodedCertificatesList",validperiodedCertificatesList);
		return "certificatesmore";
	}

	/**
	 * 获取资源数据
	 */
	public void getreslinechart() {
		UserInfo user = this.getUser();
		Map<String, Object> map = baseWorkService.getResLineCountList(user);
		convertObjToJson(map);

	}

	/**
	 * 获取离职人员图表数据
	 */
	public void getleavechart(){
		UserInfo user = this.getUser();
		List list = baseWorkService.getLeavePersonStatisticResult(user);
		convertObjToJson(list);
	}
	
	@Override
	public String getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareViewModel() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void prepareSaveModel() throws Exception {
		// TODO Auto-generated method stub

	}

}
