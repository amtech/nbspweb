package com.cabletech.business.ah.rating.action;

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

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.ah.rating.service.MobileExamService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.util.Page;

/**
 * 移动考核 待审核 待确认
 * 
 * @author zskang
 * 
 */
@Namespace("/ah")
@Results({
		@Result(name = "ToqueryAnalysisPage", location = "/ah/rating/mobile_queryAnalysis_list.jsp"),
		@Result(name = "listWaitCheckData", location = "/ah/rating/mobile_checkout_list.jsp") })
@Action("/MobileExamFormAction")
public class MobileExamFormAction extends BaseAction<String, String> {
	private static final long serialVersionUID = 1L;
	@Resource(name = "mobileExamServiceImpl")
	private MobileExamService service;

	/**
	 * 查询待考核列表的数据 1==flag 转向 考核列表待审核的数据列表2 转向代维确认页面列表 3
	 * 
	 * @return
	 */
	public String TolistWaitCheckData() {
		String flag = this.getParameter("flag");
		this.getRequest().setAttribute("flag", flag);
		return "listWaitCheckData";
	}

	/**
	 * 转向 统计分析页面列表
	 * 
	 * @return String
	 */
	public String ToqueryAnalysisPage() {
		return "ToqueryAnalysisPage";
	}

	/**
	 * 查询 考核列表待审核的数据列表
	 * 
	 * @return
	 */
	public void listWaitCheckData() {
		Page<Map<String, Object>> page = this.initPage();
		UserInfo userInfo = super.getUser();
		String flag = super.getParameter("flag");
		convertObjToJson(service.getPageList(page, userInfo, flag));
	}

	/**
	 * 考核之查询统计
	 * 
	 * @return
	 */
	public void queryAnalysisData() {
		Map<String, Object> parameters = initCondition();
		Page<Map<String, Object>> page = this.initPage();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("hasedCheckUserCount",
				service.getHasedCheckUserCount(parameters));
		map.put("noneCheckUserCount",
				service.getNoneCheckUserCount(parameters));
		map.put("daiweiOKCount",
				service.getDaiweiOKCount(parameters));
		map.put("status", parameters.get("status"));
		Map<String, Object> mapjson = new HashMap<String, Object>();
		mapjson.put("mapJson", map);
		page = service.getQueryAnalysisList(page, parameters);
		mapjson.put("listJson",page );
		super.setExcelParameter(page);
		convertObjToJson(mapjson);
	}

	/**
	 * 封装查询条件
	 * 
	 * @return Map
	 */
	private Map<String, Object> initCondition() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("regionId", getRequest().getParameter("regionId"));
		if(StringUtils.isBlank(getRequest().getParameter("regionId"))){
			map.put("regionId", super.getUser().getRegionId());
		}
		map.put("orgId", getRequest().getParameter("orgId"));
		if(super.getUser().isContractor() && StringUtils.isBlank(getRequest().getParameter("orgId"))){
			map.put("orgId", super.getUser().getOrgId());
		}
		map.put("businessType", getRequest().getParameter("businessType"));
		map.put("status", getRequest().getParameter("Status"));
		map.put("postOffice", getRequest().getParameter("postOffice"));
		map.put("yearMonth", getRequest().getParameter("yearMonth"));
		if(map.get("yearMonth")==null || map.get("yearMonth").toString().equals("")){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
			String dateString = formatter.format(new Date());
			map.put("yearMonth", dateString);
		}
		return map;
	}

	/**
	 * 实现BaseAction 自带方法
	 * 
	 * @return String
	 */
	@Override
	public String getModel() {
		return null;
	}

	/**
	 * 实现BaseAction 自带方法
	 * 
	 * @return
	 */

	@Override
	protected void prepareViewModel() throws Exception {
	}

	/**
	 * 实现BaseAction 自带方法
	 * 
	 * @return
	 */

	@Override
	protected void prepareSaveModel() throws Exception {
	}

}
