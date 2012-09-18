package com.cabletech.business.ah.excelreport.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.cabletech.business.ah.excelreport.model.AhExcelReportSheet;
import com.cabletech.common.base.BaseDao;

/**
 * 
 * 地市每个月上传的excel报表中sheet类型DAO
 * 
 * @author 杨隽 2012-06-27 创建
 * 
 */
@Repository
public class AhExcelReportSheetTypeDao extends
		BaseDao<AhExcelReportSheet, String> {
	/**
	 * 根据查询条件获取地市每个月上传的excel报表中sheet类型数据列表
	 * 
	 * @param condition
	 *            String 查询条件
	 * @return List<Map<String,Object>> 地市每个月上传的excel报表中sheet类型数据列表
	 */
	public List<Map<String, Object>> queryListByCondition(String condition) {
		StringBuffer sqlBuf = new StringBuffer("");
		sqlBuf.append(" SELECT to_char(sheetnum) AS sheetnum, ");
		sqlBuf.append(" to_char(start_column) AS start_column,id,sheetname,");
		sqlBuf.append(" to_char(start_row) AS start_row,to_char(flg) AS flg ");
		sqlBuf.append(" FROM ah_excelreport_sheetnum  ");
		sqlBuf.append(" WHERE 1=1 ");
		sqlBuf.append(condition);
		sqlBuf.append(" ORDER BY id ");
		return super.getJdbcTemplate().queryForList(sqlBuf.toString());
	}
}
