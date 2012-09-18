package com.cabletech.business.assess.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.baseinfo.excel.ExportUtil;
import com.cabletech.business.assess.service.AssessExaminationService;
import com.cabletech.common.base.BaseAction;


/**
 * 现场检查汇总
 * 
 * @author wj 2012-08-03 创建
 */
@Namespace("/assess")
@Results({
	@Result(name = "list", location = "/assess/examination/assess_examination_summary.jsp"),
})
@Action("/assessExaminationSummaryAction")
public class AssessExaminationSummaryAction extends BaseAction {

	@Resource(name = "assessExaminationServiceImpl")
	private AssessExaminationService assessExaminationService;
	
	/**
	 * 列表 跳转
	 */
	public String list() {
		Map<String, String> parameters = initConditions();
		Map<String,Object> ret = assessExaminationService.queryExaminationSummaryList(parameters);
		List<Map<String, Object>> list =  (List<Map<String, Object>>)ret.get("result");
		ret.remove("result");
		getRequest().setAttribute("list", list);
		getRequest().setAttribute("parameters", parameters);
		ExportUtil.intExportParameters(getRequest(), ret);
		return LIST;
	}

	/**
	 * 封装查询条件
	 * @return Map
	 */
	private Map<String, String> initConditions() {
		HttpServletRequest request = this.getRequest();
		Map<String, String> map = new HashMap<String, String>();
		UserInfo userInfo = super.getUser();
		String regionId = request.getParameter("regionId");//区域ID
		String regionName = request.getParameter("regionName");//区域名称
		String startTime = request.getParameter("startTime");//开始时间
		String endTime = request.getParameter("endTime");//结束时间
		map.put("tableType","03");
		map.put("regionId", regionId);
		map.put("regionName", regionName);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return map;
	}

	@Override
	protected void prepareViewModel() {
	}

	@Override
	protected void prepareSaveModel() {
	}

	@Override
	public Object getModel() {
		return null;
	}
}