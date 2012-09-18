package com.cabletech.business.analysis.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import com.cabletech.business.analysis.dao.PollingColligateReportDao;
import com.cabletech.business.analysis.service.PollingColligateReportService;

/**
 * 综合巡检报表
 * 
 * @author 汪杰 2012-03-27
 * 
 */
public class PollingColligateReportServiceImpl  implements PollingColligateReportService{
	@Resource(name = "pollingColligateReportDao")
	private PollingColligateReportDao pollingColligateReportDao;
	
	
	/**
	 * 综合巡检报表 
	 * @param parameters 参数
	 * @return list
	 */
	public List<Map<String, Object>> getPollingColligateReportList(Map<String, String> parameters){
		List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();
		ret.addAll(pollingColligateReportDao.getPollingColligateReportByRegionList(parameters));//区域分组
		ret.addAll(pollingColligateReportDao.getPollingColligateReportByOrgList(parameters)); //组织分组
		ret.addAll(pollingColligateReportDao.getPollingColligateReportByPatrolList(parameters));//巡检组分组
		return ret;
	}
}
