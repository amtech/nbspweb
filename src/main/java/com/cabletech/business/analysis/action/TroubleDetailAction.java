package com.cabletech.business.analysis.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.analysis.service.TroubleLevelsNumberService;
import com.cabletech.business.analysis.service.TroubleProcessInTimeRateService;
import com.cabletech.business.analysis.service.TroubleResponseInTimeRateService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.util.Page;

/**
 * 故障质量指标统计
 * 
 * @author Administrator
 * 
 */
@Namespace("/analysis")
@Results({
		@Result(name = "prossetimelydetail", location = "/analysis/troubleprossetimelyrate_detail.jsp"),
		@Result(name = "responsetimelydetail", location = "/analysis/troubleresponsetimelyrate_detail.jsp"),
		@Result(name = "levelsnumberdetail", location = "/analysis/troublelevelsnumber_detail.jsp")		
})
@Action("/troubleDetailAction")
public class TroubleDetailAction extends BaseAction<String, String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 故障处理及时率详细
	 */
	private static final String SHOW_PROSSETIMELY_DETAIL = "prossetimelydetail";
	/**
	 * 故障响应及时率明细
	 */
	private static final String SHOW_RESPONSETIMELY_DETAIL = "responsetimelydetail";
	/**
	 * 故障分级统计明细
	 */
	private static final String SHOW_LEVELSNUMBER_DETAIL = "levelsnumberdetail";

	/**
	 * 故障处理及时率
	 */
	@Resource(name = "troubleProcessInTimeRateServiceImpl")
	private TroubleProcessInTimeRateService troubleProcessInTimeRateService;

	/**
	 * 故障响应及时率
	 */
	@Resource(name = "troubleResponseInTimeRateServiceImpl")
	private TroubleResponseInTimeRateService troubleResponseInTimeRateService;
	/**
	 * 故障分级
	 */
	@Resource(name = "troubleLevelsNumberServiceImpl")
	private TroubleLevelsNumberService troubleLevelsNumberService;

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

	/**
	 * 转换到故障处理及时率详细页面
	 * 
	 * @return
	 */
	public String prossetimelydetail() {
		this.getRequest().setAttribute("paramMap",
				convertObjToJsonStr(this.getParameters()));
		return SHOW_PROSSETIMELY_DETAIL;
	}

	/**
	 * 获取故障处理及时明细
	 */
	@SuppressWarnings("unchecked")
	public void timelyprosseinlist() {
		Page<Map<String, Object>> page = this.initPage();
		convertObjToJson(troubleProcessInTimeRateService.getInTimeTroubleList(
				this.getParameters(), page));
	}

	/**
	 * 获取故障处理超时明细
	 */
	@SuppressWarnings("unchecked")
	public void timelyprosseoverlist() {
		Page<Map<String, Object>> page = this.initPage();
		convertObjToJson(troubleProcessInTimeRateService
				.getOverTimeTroubleList(this.getParameters(), page));
	}

	/**
	 * 转换到故障响应及时率明细页面
	 * 
	 * @return
	 */
	public String responsetimelydetail() {
		this.getRequest().setAttribute("paramMap",
				convertObjToJsonStr(this.getParameters()));
		return SHOW_RESPONSETIMELY_DETAIL;
	}

	/**
	 * 获取故障处理及时明细
	 */
	@SuppressWarnings("unchecked")
	public void responsetimelyinlist() {
		Page<Map<String, Object>> page = this.initPage();
		convertObjToJson(troubleResponseInTimeRateService.getInTimeTroubleList(
				this.getParameters(), page));
	}

	/**
	 * 获取故障处理超时明细
	 */
	@SuppressWarnings("unchecked")
	public void responsetimelyoverlist() {
		Page<Map<String, Object>> page = this.initPage();
		convertObjToJson(troubleResponseInTimeRateService
				.getOverTimeTroubleList(this.getParameters(), page));
	}

	/**
	 * 转换到故障分级统计明细页面
	 * 
	 * @return
	 */
	public String levelsnumberdetail() {
		this.getRequest().setAttribute("paramMap",
				convertObjToJsonStr(this.getParameters()));
		return SHOW_LEVELSNUMBER_DETAIL;
	}

	/**
	 * 获取重大故障明细
	 */
	@SuppressWarnings("unchecked")
	public void onelevelnumberlist() {
		Page<Map<String, Object>> page = this.initPage();
		Map<String, String> map = this.getParameters();
		map.put("isInstancy", "重大故障");
		convertObjToJson(troubleLevelsNumberService.getTroubleListByLevel(map,
				page));
	}

	/**
	 * 获取严重故障明细
	 */
	@SuppressWarnings("unchecked")
	public void twolevelnumberlist() {
		Page<Map<String, Object>> page = this.initPage();
		Map<String, String> map = this.getParameters();
		map.put("isInstancy", "严重故障");
		convertObjToJson(troubleLevelsNumberService.getTroubleListByLevel(map,
				page));
	}

	/**
	 * 获取一般故障明细
	 */
	@SuppressWarnings("unchecked")
	public void threenumberlist() {
		Page<Map<String, Object>> page = this.initPage();
		Map<String, String> map = this.getParameters();
		map.put("isInstancy", "一般故障");
		convertObjToJson(troubleLevelsNumberService.getTroubleListByLevel(map,
				page));
	}

	/**
	 * 获取查询参数
	 */
	public Map<String, String> getParameters() {
		Map<String, String> map = new HashMap<String, String>();
		UserInfo user = this.getUser();
		if (StringUtils.isNotBlank(this.getParameter("orgId"))) {
			map.put("orgId", this.getParameter("orgId"));
		} else {
			if (user.isContractor()) {
				map.put("orgId", user.getOrgId());
			}
		}
		map.put("patrolGroupId", this.getParameter("patrolGroupId"));
		map.put("regionId", user.getRegionId());
		map.put("startTime", this.getParameter("startTime"));
		map.put("endTime", this.getParameter("endTime"));
		map.put("businessType", this.getParameter("businessType"));
		return map;
	}

}