package com.cabletech.business.wplan.plan.action;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.base.BaseUtil;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.baseinfo.excel.ExportUtil;
import com.cabletech.baseinfo.excel.WorkBookData;
import com.cabletech.business.wplan.plan.model.Patrolinfo;
import com.cabletech.business.wplan.plan.service.PatrolinfoExecuteService;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 巡检计划执行Action
 * 
 * @author zhaobi
 * 
 */
@Action("/patrolinfoExecuteAction")
@Results({
		@Result(name = "show_executeschedule_list", location = "/wplan/plan/patrolinfo_executeschedule_list.jsp"),
		@Result(name = "lostdetail", location = "/wplan/plan/patrolinfo_lostresource_list.jsp"),
		@Result(name = "overdetail", location = "/wplan/plan/patrolinfo_overresource_list.jsp"),
		@Result(name = "rfiddetail", location = "/wplan/plan/patrolinfo_rfid_list.jsp"),
		@Result(name = "itemdetail", location = "/wplan/plan/patrolinfo_itemdetail_list.jsp"),
		@Result(name = "show_executeresult_list", location = "/wplan/plan/patrolinfo_executeresult_list.jsp") })
public class PatrolinfoExecuteAction extends
		PatrolinfoBaseAction<Patrolinfo, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 执行情况列表名称
	 */
	private static final String SHOW_EXECUTESCHEDULE_LIST = "show_executeschedule_list";

	/**
	 * 执行结果列表名称
	 */
	private static final String SHOW_EXECUTERESULT_LIST = "show_executeresult_list";
	/**
	 * 未巡检明细
	 */
	private static final String SHOW_LOST_DETAIL_LIST = "lostdetail";
	/**
	 * 已巡检明细
	 */
	private static final String SHOW_OVER_DETAIL_LIST = "overdetail";

	/**
	 * RFID明细
	 */
	private static final String SHOW_RFID_DETAIL_LIST = "rfiddetail";

	/**
	 * 巡检表明细
	 */
	private static final String SHOW_ITEM_DETAIL_LIST = "itemdetail";
	/**
	 * 巡检计划信息
	 */
	private Patrolinfo patrolinfo = new Patrolinfo();

	/**
	 * 巡检执行信息服务
	 */
	@Resource(name = "patrolinfoExecuteServiceImpl")
	private PatrolinfoExecuteService patrolinfoExecuteService;
	@Override
	public Patrolinfo getModel() {
		return patrolinfo;
	}
	@Override
	protected void prepareViewModel() throws Exception {
		if (null == patrolinfo) {
			patrolinfo = new Patrolinfo();
		}
	}

	@Override
	protected void prepareSaveModel() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 显示执行计划情况页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String showExecuteScheduleList() {
		try {
			prepareViewModel();
			String businesstype = this.getRequest().getParameter("type");
			this.getRequest().setAttribute("businesstype", businesstype);
			return SHOW_EXECUTESCHEDULE_LIST;
		} catch (Exception e) {
			logger.error("执行综合查询异常:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 显示执行计划情况列表
	 * 
	 * @throws Exception
	 */
	public void queryshowschedule() throws Exception {
		prepareViewModel();
		UserInfo userinfo = this.getUser();
		String businesstype = this.getRequest().getParameter("type");
		patrolinfo.setPlanstate(SysConstant.PASSED_STATE);
		if (!StringUtils.isNotBlank(patrolinfo.getRegionid())) {
			patrolinfo.setRegionid(userinfo.getRegionId());
		}
		if (!StringUtils.isNotBlank(patrolinfo.getContractorid())) {
			if (userinfo.isContractor()) {
				patrolinfo.setContractorid(userinfo.getOrgId());
			}
		}
		patrolinfo.setBusinesstype(businesstype);
		Page<Map<String, Object>> page = this.initPage();
		page = patrolinfoExecuteService.getAllPatrolScheduleInfo(patrolinfo,
				page);
		super.setExcelParameter(page);
		convertObjToJson(page);
	}

	/**
	 * 显示执行计划结果页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String showExecuteResultList() {
		try {
			String businesstype = this.getRequest().getParameter("type");
			this.getRequest().setAttribute("businesstype", businesstype);
			return SHOW_EXECUTERESULT_LIST;
		} catch (Exception e) {
			logger.error("执行计划结果异常:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 执行计划结果列表
	 */
	public void queryshowresult() {
		String businesstype = this.getRequest().getParameter("type");
		patrolinfo.setPlanstate(SysConstant.PASSED_STATE);
		UserInfo userinfo = this.getUser();
		if (!StringUtils.isNotBlank(patrolinfo.getRegionid())) {
			patrolinfo.setRegionid(userinfo.getRegionId());
		}
		if (!StringUtils.isNotBlank(patrolinfo.getContractorid())) {
			if (userinfo.isContractor()) {
				patrolinfo.setContractorid(userinfo.getOrgId());
			}
		}
		patrolinfo.setBusinesstype(businesstype);
		Page<Map<String, Object>> page = this.initPage();
		page = patrolinfoExecuteService
				.getAllPatrolResultInfo(patrolinfo, page);
		super.setExcelParameter(page);
		convertObjToJson(page);
	}

	/**
	 * 未巡检明细页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String lostdetail() {
		String planid = this.getRequest().getParameter("id");
		try {

			super.getRequest().setAttribute("planid", planid);
			return SHOW_LOST_DETAIL_LIST;
		} catch (Exception e) {
			logger.error("执行未巡检明细页面异常:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 未巡检明细LIST
	 */
	public void querylostdetail() {
		String planid = this.getRequest().getParameter("id");
		Page<Map<String, Object>> page = this.initPage();
		page = patrolinfoExecuteService.getAllLostDetail(planid, page);
		super.getRequest().setAttribute("page", page);
		convertObjToJson(page);
	}

	/**
	 * 已巡检明细页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String overdetail() {
		String planid = this.getRequest().getParameter("id");
		try {
			super.getRequest().setAttribute("planid", planid);
			return SHOW_OVER_DETAIL_LIST;
		} catch (Exception e) {
			logger.error("执行已巡检明细页面异常:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 已巡检明细LIST
	 */
	public void queryoverdetail() {
		String planid = this.getRequest().getParameter("id");
		Page<Map<String, Object>> page = this.initPage();
		page = patrolinfoExecuteService.getAllOverDetail(planid, page);
		super.setExcelParameter(page);
		convertObjToJson(page);
	}

	/**
	 * RFID明细Action
	 * 
	 * @return
	 * @throws Exception
	 */
	public String rfiddetail() {
		// 巡检结果ID;
		String rid = this.getRequest().getParameter("id");
		try {
			Map<String, Object> map = patrolinfoExecuteService
					.getPatrolResourceDetail(rid);
			super.getRequest().setAttribute("rfidMap", map);
			return SHOW_RFID_DETAIL_LIST;
		} catch (Exception e) {
			logger.error("执行已巡检明细页面异常:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 已完成RFID巡检列表
	 */
	public void rfidover() {
		// 巡检结果ID;
		String rid = this.getRequest().getParameter("id");
		Page<Map<String, Object>> page = this.initPage();
		page = patrolinfoExecuteService.getOverRFIDDetail(rid, page);
		convertObjToJson(page);
	}

	/**
	 * 未完成RFID巡检列表
	 */
	public void rfidlost() {
		// 巡检结果ID;
		String rid = this.getRequest().getParameter("id");
		String resourceid = this.getRequest().getParameter("rid");
		String rtype = this.getRequest().getParameter("rtype");
		Page<Map<String, Object>> page = this.initPage();
		page = patrolinfoExecuteService.getLostRFIDDetail(rid, resourceid,
				rtype, page);
		convertObjToJson(page);
	}

	/**
	 * 巡检表明细Action
	 * 
	 * @return
	 */
	public String patrolitemdetail() {
		// 巡检结果ID;
		String rid = this.getRequest().getParameter("id");
		try {
			Map<String, Object> map = patrolinfoExecuteService
					.getPatrolResourceDetail(rid);
			super.getRequest().setAttribute("patrolinfoMap", map);
			Map<String, Object> emap = patrolinfoExecuteService
					.getExceptionItemCount(rid);
			super.getRequest().setAttribute("exceptionMap", emap);
			return SHOW_ITEM_DETAIL_LIST;
		} catch (Exception e) {
			logger.error("执行已巡检明细页面异常:" + e.getMessage());
			return ERROR;
		}
	}
	
	/**
	 * 巡检表明细 导出
	 * 
	 * 后期优化
	 * 
	 * @return
	 */
	public void patrolitemdetailexport() {
		String rid = this.getRequest().getParameter("id");
		String[] ids = BaseUtil.compart(rid, ",");
		try {
			WorkBookData workBookData = new WorkBookData();
			workBookData.setCommonData(new HashMap());
			String palanName="计划执行结果列表.xls";
			for(String sid:ids){
				Map<String, Object> emap = patrolinfoExecuteService.getExceptionItemCount(sid);
				Map<String, Object> patrolinfo = patrolinfoExecuteService.getPatrolResourceDetail(sid);
				palanName = (String)patrolinfo.get("PLAN_NAME")+".xls";
				patrolinfo.put("EXCEPTIONCOUNT", emap.get("EXCEPTIONCOUNT"));
				String sheetName = (String)patrolinfo.get("RESOURCE_NAME");
				String id = (String)patrolinfo.get("ID");
				List itemDetails = patrolinfoExecuteService.getItemDetailForExport(id);
				Map recode = new HashMap();
				recode.put("ls", itemDetails);
				recode.put("info", patrolinfo);
				workBookData.addSheet(sheetName, recode);
			}
			
			BaseUtil.setResponseHeader(this.getRequest(), this.getResponse(), palanName);
			
			
			OutputStream output  = getResponse().getOutputStream();
			
			String templateName = SysConstant.EXCEL_DOWNLOAD_TEMPLATES_PACKAGE_PATH+"/patrolinfo_execute_template.xls";
			String templateFile = getClass().getResource(templateName).getPath();
			InputStream is = new BufferedInputStream(new FileInputStream(templateFile));
			workBookData.setRes(is);		
			workBookData.setDest(output);
			ExportUtil.multiSheet(workBookData);
		} catch (Exception e) {
			logger.error("导出执行已巡检明细异常:" + e.getMessage());
		}
	}

	/**
	 * 巡检项明细List
	 */
	public void itemdetaillist() {
		// 巡检结果ID;
		String rid = this.getRequest().getParameter("id");
		Page<Map<String, Object>> page = this.initPage();
		page = patrolinfoExecuteService.getItemDetail(rid, page);
		super.setExcelParameter(page);
		convertObjToJson(page);
	}

}
