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
import com.cabletech.business.analysis.service.TroubleApprovePassedRateService;
import com.cabletech.business.analysis.service.TroubleLevelsNumberService;
import com.cabletech.business.analysis.service.TroubleLevelsTimeLengthService;
import com.cabletech.business.analysis.service.TroubleProcessInTimeRateService;
import com.cabletech.business.analysis.service.TroubleResponseInTimeRateService;
import com.cabletech.common.base.BaseAction;

/**
 * 故障质量指标统计
 * @author 赵璧
 * 
 */
@Namespace("/analysis")
@Results({
		@Result(name = "prossetimelyrate", location = "/analysis/troubleprossetimelyrate_analysis.jsp"),
		@Result(name = "responsetimelyrate", location = "/analysis/troubleresponsetimelyrate_analysis.jsp"),
		@Result(name = "levelsnumber", location = "/analysis/troublelevelsnumber_analysis.jsp"),
		@Result(name = "timelength", location = "/analysis/troubletimelength_analysis.jsp"),
		@Result(name = "approvepass", location = "/analysis/troublepassedrate_analysis.jsp") })
@Action("/troubleAnalysisAction")
public class TroubleAnalysisAction extends BaseAction<String, String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 故障处理及时率
	 */
	private static final String SHOW_PROSSETIMELY_RATE = "prossetimelyrate";
	/**
	 * 故障处响应及时率
	 */
	private static final String SHOW_RESPONSETIMELY_RATE = "responsetimelyrate";

	/**
	 * 故障分级统计
	 */
	private static final String SHOW_LEVELS_NUMBER = "levelsnumber";

	/**
	 * 平均时长统计
	 */
	private static final String SHOW_TIME_LENGTH = "timelength";

	/**
	 * 故障通过率
	 */
	private static final String SHOW_APPROVE_PASS = "approvepass";

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
	/**
	 * 故障平均时长
	 */
	@Resource(name = "troubleLevelsTimeLengthServiceImpl")
	private TroubleLevelsTimeLengthService troubleLevelsTimeLengthService;
	/**
	 * 故障通过率
	 */
	@Resource(name = "troubleApprovePassedRateServiceImpl")
	private TroubleApprovePassedRateService troubleApprovePassedRateService;

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
	 * 转换到故障处理及时率分析页面
	 * 
	 * @return
	 */
	public String prossetimelyrate() {
		return SHOW_PROSSETIMELY_RATE;
	}

	/**
	 * 获取故障处理及时率分析数据
	 */
	public void timelyprosseratelist() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("root", troubleProcessInTimeRateService
				.getTroubleProcessInTimeMainRateList(getParameters()));
		convertObjToJson(data);
	}

	/**
	 * 获取故障处理及时率分析数据
	 */
	public void timelyprosseratesublist() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("root", troubleProcessInTimeRateService
				.getTroubleProcessInTimeSubRateList(getParameters()));
		convertObjToJson(data);
	}

	/**
	 * 转换到故障响应及时率分析页面
	 * 
	 * @return
	 */
	public String responsestimelyrate() {
		return SHOW_RESPONSETIMELY_RATE;
	}

	/**
	 * 获取故障响应主数据
	 */
	public void timelyresponseratelist() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("root", troubleResponseInTimeRateService
				.getTroubleResponseInTimeMainRateList(getParameters()));
		convertObjToJson(data);
	}

	/**
	 * 获取故障响应从数据
	 */
	public void timelyresponseratesublist() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("root", troubleResponseInTimeRateService
				.getTroubleResponseInTimeSubRateList(getParameters()));
		convertObjToJson(data);
	}

	/**
	 * 转换到故障级别页面
	 * 
	 * @return
	 */
	public String levelsnumber() {
		return SHOW_LEVELS_NUMBER;
	}

	/**
	 * 故障分级主数据
	 */
	public void levelnumberlist() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("root", troubleLevelsNumberService
				.getTroubleLevelsMainNumberList(getParameters()));
		convertObjToJson(data);
	}

	/**
	 * 故障分级从数据
	 */
	public void levelnumbersublist() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("root", troubleLevelsNumberService
				.getTroubleLevelsSubNumberList(getParameters()));
		convertObjToJson(data);
	}

	/**
	 * 转到平均时长页面
	 * 
	 * @return
	 */
	public String timelength() {
		return SHOW_TIME_LENGTH;
	}

	/**
	 * 平均时长主数据
	 */
	public void timelengthlist() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("root", troubleLevelsTimeLengthService
				.getTroubleLevelsMainTimeLengthList(getParameters()));
		convertObjToJson(data);
	}

	/**
	 * 平均时长从数据
	 */
	public void timelengthsublist() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("root", troubleLevelsTimeLengthService
				.getTroubleLevelsSubTimeLengthList(getParameters()));
		convertObjToJson(data);
	}

	/**
	 * 转到故障通过页面
	 * 
	 * @return
	 */
	public String approvepass() {
		return SHOW_APPROVE_PASS;
	}

	/**
	 * 故障通过率
	 */
	public void approvepasslist() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("root", troubleApprovePassedRateService
				.getTroubleApprovePassedMainRateList(getParameters()));
		convertObjToJson(data);
	}

	/**
	 * 故障通过率从数据
	 */
	public void approvepasssublist() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("root", troubleApprovePassedRateService
				.getTroubleApprovePassedSubRateList(getParameters()));
		convertObjToJson(data);
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
		map.put("regionId", user.getRegionId());
		map.put("startTime", this.getParameter("startTime"));
		map.put("endTime", this.getParameter("endTime"));
		map.put("businessType", this.getParameter("businessType"));
		return map;
	}

}