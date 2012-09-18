package com.cabletech.business.sysmanager.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.business.sysmanager.model.GprsMo;
import com.cabletech.business.sysmanager.service.GprsMoService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.util.Page;

/**
 * GPRS日志查询
 * @author wangt
 *
 */
@Namespace("/sysmanager")
@Results({
		@Result(name = "list", location = "/sysmanager/gprsmo_list.jsp")
})
@Action("/gprsmoaction")
public class GprsMoAction extends
BaseAction<GprsMo, String> {

	// GPRS日志查询
	@Resource(name = "gprsMoServiceImpl")
	private GprsMoService service;
	/**
	 * 列表
	 * @return 列表
	 */
	public String list() {
		
		return "list";
	}
	/**
	 * 考核之查询统计
	 * 
	 * @return
	 */
	public void listData() {
		Map<String, Object> parameters = initCondition();
		Page<Map<String, Object>> page = this.initPage();
		page = service.getQueryList(page, parameters);
		convertObjToJson(page);
	}
	/**
	 * 条件
	 * @return
	 */
	private Map<String, Object> initCondition() {
		Map<String,Object>map = new HashMap<String,Object>();
		map.put("orgid", super.getParameter("orgid"));
		map.put("regionid", super.getParameter("regionid"));
		if(StringUtils.isBlank(getRequest().getParameter("regionid"))){
			map.put("regionid", super.getUser().getRegionId());
		}
		if(super.getUser().isContractor() && StringUtils.isBlank(getRequest().getParameter("orgid"))){
			map.put("orgid", super.getUser().getOrgId());
		}
		map.put("patrolid", getRequest().getParameter("patrolid"));
		map.put("begindate", getRequest().getParameter("begindate"));
		map.put("enddate", super.getParameter("enddate"));
		if(map.get("begindate")==null || map.get("begindate").toString().equals("")){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String endString = formatter.format(new Date());
			String beginString = formatter.format(new Date(new Date().getTime()-3*24*60*60*1000));
			map.put("begindate", beginString);
			map.put("enddate", endString);
		}
		return map;
	}
	@Override
	public GprsMo getModel() {
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
