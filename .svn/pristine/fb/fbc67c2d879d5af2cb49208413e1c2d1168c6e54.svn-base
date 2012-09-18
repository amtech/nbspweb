package com.cabletech.business.wplan.plan.action;

import java.util.Calendar;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.wplan.plan.model.Patrolinfo;
import com.cabletech.business.wplan.plan.service.PatrolanalysisService;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.DateUtil;
import com.cabletech.common.util.Page;

/**
 * 巡检分析Action
 * 
 * @author zhaobi
 * 
 */
@Namespace("/wplan")
@Results({
		@Result(name = "orglistshow", location = "/wplan/plan/patrolanalysis_orgquery.jsp"),
		@Result(name = "orglist", location = "/wplan/plan/patrolanalysis_org.jsp"),
		@Result(name = "regionlistshow", location = "/wplan/plan/patrolanalysis_regionquery.jsp"),
		@Result(name = "regionlist", location = "/wplan/plan/patrolanalysis_region.jsp") })
@Action("/patrolanalysisAction")
public class PatrolanalysisAction extends
		PatrolinfoBaseAction<Patrolinfo, String> {

	/**
	 * 巡检分析服务
	 */
	@Resource(name = "patrolanalysisServiceImpl")
	private PatrolanalysisService patrolanalysisService;
	/**
	 * 按照代维公司分析结果页面
	 */
	private static final String SHOW_ORG_LIST = "orglist";

	/**
	 * 按照区域分析结果页面
	 */
	private static final String SHOW_REGION_LIST = "regionlist";

	/**
	 * 巡检计划表单信息
	 */
	private Patrolinfo patrolinfo = new Patrolinfo();

	@Override
	public Patrolinfo getModel() {
		return patrolinfo;
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
	 * 按照代维公司分析结果页面
	 * 
	 * @return
	 */
	public String showorg() {
		initparam(patrolinfo);
		getallpatrolcount(patrolinfo);
		this.getRequest().setAttribute("PatrolGroupChartMap",
				patrolanalysisService.getPatrolGroupChart(patrolinfo));
		return SHOW_ORG_LIST;
	}

	/**
	 * 转到代维公司页面分析查询页面
	 * 
	 * @return
	 */
	public String orgshow() {
		getparam();
		return "orglistshow";
	}

	/**
	 * 按照区域分析页面
	 * 
	 * @return
	 */
	public String showregion() {
		initparam(patrolinfo);
		getallpatrolcount(patrolinfo);
		this.getRequest().setAttribute("PatrolOrgChartMap",
				patrolanalysisService.getPatrolOrgChart(patrolinfo));
		return SHOW_REGION_LIST;
	}

	/**
	 * 转到区域页面分析查询页面
	 * 
	 * @return
	 */
	public String regionshow() {
		getparam();
		return "regionlistshow";
	}

	/**
	 * 获取巡检组分析列表
	 */
	public void getpatrolgrouppatrollist() {
		Page<Map<String, Object>> page = this.initPage();
		page = patrolanalysisService.getPatrolGroupPatrolInfo(
				initparam(patrolinfo), page);
		convertObjToJson(page);
	}

	/**
	 * 获取区域组分析列表
	 */
	public void getregionpatrollist() {
		Page<Map<String, Object>> page = this.initPage();
		page = patrolanalysisService.getRegionPatrolInfo(initparam(patrolinfo),
				page);
		convertObjToJson(page);
	}

	/**
	 * 获取巡检计划相关完成信息
	 */
	public void getpatrolinfolist() {

		Page<Map<String, Object>> page = this.initPage();
		page = patrolanalysisService.getPatrolInfo(initparam(patrolinfo), page);
		convertObjToJson(page);
	}

	/**
	 * 获取所有巡检记录数据
	 * 
	 * @param patrolinfo
	 *            实体
	 */
	private void getallpatrolcount(Patrolinfo patrolinfo) {
		Map<String, Object> map = patrolanalysisService
				.getAllPatrolCount(patrolinfo);
		this.getRequest().setAttribute("patrolanalyMap", map);
	}

	/**
	 * 获取参数
	 */
	private void getparam() {
		// 获取按年，按季度，按月,按年处理参数
		String flag = this.getRequest().getParameter("flg");
		this.getRequest().setAttribute("flg", flag);
		// 计划类型TAG
		this.getRequest().setAttribute("plantypeMap", this.getPlanTypeMap());
		// 计划年份TAG
		this.getRequest().setAttribute("planyearMap", this.getPlanYearMap());
		// 季度类型TAG
		this.getRequest()
				.setAttribute("seasonTypeMap", this.getSeasonTypeMap());
		patrolinfo.setYear(Integer.toString(Calendar.getInstance().get(
				Calendar.YEAR)));

	}

	/**
	 * 处理前台传过来的参数
	 * 
	 * @param patrolinfo
	 *            实体
	 * @return
	 */
	private Patrolinfo initparam(Patrolinfo patrolinfo) {
		String orgid = this.getRequest().getParameter("orgid");
		UserInfo user = this.getUser();
		// 年计划
		if (SysConstant.WPLAN_YEAR.equals(patrolinfo.getPlantype())) {
			patrolinfo.setStarttime(patrolinfo.getYear() + "-01-01");
			patrolinfo.setEndtime(patrolinfo.getYear() + "-12-31");
		}
		// 季度
		else if (SysConstant.WPLAN_SEASON.equals(patrolinfo.getPlantype())) {
			patrolinfo.setStarttime(DateUtil.getSeasonTime(
					patrolinfo.getYear(),
					Integer.parseInt(patrolinfo.getSeasontype()), true));
			patrolinfo.setEndtime(DateUtil.getSeasonTime(
					patrolinfo.getYear(),
					Integer.parseInt(patrolinfo.getSeasontype()), false));
		}
		if (!StringUtils.isNotBlank(patrolinfo.getRegionid())) {
			patrolinfo.setRegionid(user.getRegionId());
		}
		if (!StringUtils.isNotBlank(patrolinfo.getContractorid())) {
			if (user.isContractor()) {
				patrolinfo.setContractorid(user.getOrgId());
			} else {
				patrolinfo.setContractorid(orgid);
			}
		}
		patrolinfo.setPlanstate(SysConstant.PASSED_STATE);
		return patrolinfo;
	}
}
