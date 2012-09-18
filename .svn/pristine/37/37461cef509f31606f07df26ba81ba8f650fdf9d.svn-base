package com.cabletech.business.workflow.electricity.oilengine.dao;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cabletech.business.workflow.electricity.oilengine.model.OilRecord;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.Page;

/**
 * 加油记录Dao
 * 
 * @author wangt
 * @author 杨隽 2012-05-14 添加getList()方法和getSqlBuffer()方法
 * 
 */
@Repository
public class OilRecordDao extends BaseDao<OilRecord, String> {
	/**
	 * 获取列表
	 * 
	 * @param engineId
	 *            String
	 * @param page
	 *            Page
	 * @return Page
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Page getList(String engineId, Page page) {
		StringBuffer sql = getSqlBuffer(engineId);
		return getSQLPageAll(page, sql.toString());
	}

	/**
	 * 根据油机编号构建油机加油记录列表查询sql
	 * 
	 * @param engineId
	 *            String 油机编号
	 * @return StringBuffer 油机加油记录列表查询sql
	 */
	private StringBuffer getSqlBuffer(String engineId) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" SELECT t.*, ");
		sqlBuffer.append(" vu.USERNAME AS RECORDER_NAME, ");
		sqlBuffer.append(" to_char(t.RECORD_DATE,'yyyy-mm-dd hh24:mi:ss') ");
		sqlBuffer.append(" AS RECORD_DATE_DIS ");
		sqlBuffer.append(" FROM OE_OILRECORD t ");
		sqlBuffer.append(" JOIN VIEW_USERINFO vu ON vu.SID=t.RECORDER ");
		sqlBuffer.append(" WHERE 1=1 ");
		if (StringUtils.isNotBlank(engineId)) {
			sqlBuffer.append(" AND t.OE_ID='");
			sqlBuffer.append(engineId);
			sqlBuffer.append("' ");
		}
		return sqlBuffer;
	}
}
