package com.cabletech.business.assess.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.baseinfo.excel.ExportUtil;
import com.cabletech.business.assess.service.AssessMonthSummaryService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.util.DateUtil;

/**
 * 月考核汇总Action
 * 
 * @author zhaobi
 * @date 2012-8-9
 */
@Namespace("/assess")
@Results({
		@Result(name = "monthrank", location = "/assess/monthsummary/assess_monthrank_list.jsp"),
		@Result(name = "monthsummary", location = "/assess/monthsummary/assess_monthsummary_list.jsp") })
@Action("/assessMonthSummaryAction")
public class AssessMonthSummaryAction extends BaseAction {

	/**
	 * 月考核汇总服务
	 */
	@Resource(name = "assessMonthSummaryServiceImpl")
	private AssessMonthSummaryService assessMonthSummaryService;

	@Override
	public Object getModel() {
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
	 * 获取月考核汇总
	 * 
	 * @return
	 */
	public String getmonthsummary() {
		Map<String, Object> map = getParameterMap();
		Map<String, Object> queryDatas = assessMonthSummaryService.getMonthSummary(map);
		this.getRequest().setAttribute("scorecontent", (List<Map<String, Object>>)queryDatas.get("result"));
		//excel 导出参数
		queryDatas.remove("result");
		ExportUtil.intExportParameters(getRequest(), queryDatas);
		return "monthsummary";
	}

	/**
	 * 获取月度考核排名
	 * 
	 * @return
	 */
	public String getmonthrank() {
		Map<String, Object> map = getParameterMap();
		String businesstype = this.getParameter("businesstype");
		if (StringUtils.isNotBlank(businesstype)) {
			map.put("businesstype", businesstype);
		} else {
			if (null != this.getUser().getBusinessTypes()) {
				map.put("businesstype", this.getUser().getBusinessTypes()
						.get(0));
			}
		}
		Map<String, Object> queryDatas = assessMonthSummaryService.getMonthRank(map);
		this.getRequest().setAttribute("scorecontent", (List<Map<String, Object>>)queryDatas.get("result"));
		this.getRequest().setAttribute("businesstype", businesstype);
		//excel 导出参数
		queryDatas.remove("result");
		ExportUtil.intExportParameters(getRequest(), queryDatas);
		return "monthrank";
	}

	/**
	 * 获取前台传递条件
	 * 
	 * @return
	 */
	public Map<String, Object> getParameterMap() {
		UserInfo user = this.getUser();
		String regionid = this.getParameter("regionid");
		String regionname = this.getParameter("regionname");
		String month = this.getParameter("month");
		String orgid = "";
		if (StringUtils.isBlank(regionid) && StringUtils.isBlank(regionname)) {
			regionid = user.getRegionId();
			regionname = user.getRegionName();
		}
		if (StringUtils.isBlank(month)) {
			month = DateUtil.getNowDateString("yyyy-MM");
		}
		if (user.isContractor()) {
			orgid = user.getOrgId();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("regionid", regionid);
		map.put("month", month);
		map.put("orgid", orgid);
		this.getRequest().setAttribute("regionid", regionid);
		this.getRequest().setAttribute("regionname", regionname);
		this.getRequest().setAttribute("month", month);
		return map;
	}
}
