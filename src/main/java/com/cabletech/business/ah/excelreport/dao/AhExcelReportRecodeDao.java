package com.cabletech.business.ah.excelreport.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import com.cabletech.business.ah.excelreport.model.AhExcelReportRecode;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.Page;

/**
 * 
 * 地市每个月上传的excel报表DAO
 * 
 * @author wj
 * 
 */
@Repository
public class AhExcelReportRecodeDao extends
		BaseDao<AhExcelReportRecode, String> {

	/**
	 * 检索报表记录列表
	 * @param parameters 查询参数封装
	 * @param page 分页对象
	 * @return
	 */
	public Page searchReportRecodes(Map<String, Object> parameters, Page page) {
		String qSql = getReportRecodesSql(parameters);
		return super.findSQLPage(page, qSql);
	}

	/**
	 * 检索报表记录
	 * @param parameters 查询参数
	 */
	public Map<String, Object> searchReportRecode(Map<String, Object> parameters) {
		String qSql = getReportRecodesSql(parameters);
		List<Map<String, Object>> ls = super.getJdbcTemplate().queryForList(
				qSql);
		if (ls.size() > 0)
			return ls.get(0);
		return null;
	}

	/**
	 * 获取查询语句
	 * @param parameters 查询参数
	 * @return
	 */
	private String getReportRecodesSql(Map<String, Object> parameters) {
		String regionId = (String) parameters.get("regionId");
		String reportDate = (String) parameters.get("reportDate");
		String id = (String) parameters.get("id");
		StringBuffer sqlBuf = new StringBuffer("");
		sqlBuf.append(" select t.id,r.REGIONNAME,t.reportdate,t.fileurl,to_char(t.uploadtime,'yyyy-mm-dd hh24:mi:ss') as uploadtime,vu.username from ah_excelreport_recode t ");
		sqlBuf.append(" left join view_region r on r.REGIONID = t.regionid ");
		sqlBuf.append(" left join view_userinfo vu on vu.sid=t.uploaduserid ");
		sqlBuf.append(" where 1=1 ");
		if (StringUtils.isNotBlank(regionId)) {
			sqlBuf.append(" and t.regionid in (select regionid from base_region start with regionid= '"
					+ regionId + "' connect by prior regionid=parentid) ");
		}
		if (StringUtils.isNotBlank(reportDate)) {
			sqlBuf.append(" and t.reportdate = to_date('" + reportDate
					+ "','yyyy-mm-dd') ");
		}
		if (StringUtils.isNotBlank(id)) {
			sqlBuf.append(" and t.id = '" + id + "' ");
		}
		sqlBuf.append(" order by t.regionid,t.reportdate ");
		return sqlBuf.toString();
	}

	/**
	 * 根据查询条件获取上传的月报表数据信息
	 * 
	 * @param report
	 *            AhExcelReportRecode 查询条件
	 * @return AhExcelReportRecode 上传的月报表数据信息
	 */
	public AhExcelReportRecode viewahExcelReportRecode(
			AhExcelReportRecode report) {
		StringBuffer hqlBuf = getHql(report);
		return super.findHQLUnique(hqlBuf.toString());
	}

	/**
	 * 根据查询条件获取上传的月报表数据列表信息
	 * 
	 * @param report
	 *            AhExcelReportRecode 查询条件
	 * @return AhExcelReportRecode 上传的月报表数据信息
	 */
	public List<AhExcelReportRecode> queryAhExcelReportRecodeList(
			AhExcelReportRecode report) {
		StringBuffer hqlBuf = getHql(report);
		return super.find(hqlBuf.toString());
	}

	/**
	 * 根据查询条件组织hql语句
	 * 
	 * @param report
	 *            AhExcelReportRecode 查询条件
	 * @return StringBuffer hql语句
	 */
	private StringBuffer getHql(AhExcelReportRecode report) {
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer hqlBuf = new StringBuffer(
				" from AhExcelReportRecode where 1=1 ");
		if (StringUtils.isNotBlank(report.getRegionId())) {
			hqlBuf.append(" and regionId='");
			hqlBuf.append(report.getRegionId());
			hqlBuf.append("' ");
		}
		hqlBuf.append(" and reportDate=to_date('");
		hqlBuf.append(f.format(report.getReportDate()));
		hqlBuf.append("','yyyy-mm-dd') ");
		return hqlBuf;
	}
}
