package com.cabletech.business.base.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cabletech.business.base.model.SmParameter;

import com.cabletech.common.base.BaseDao;

/**
 * 短信发送记录信息
 * 
 * @author Administrator
 * 
 */
@Repository
public class SmParameterDao extends BaseDao<SmParameter, String> {
	/**
	 * 获取短信提醒有效性记录列表
	 * 
	 * @param param
	 *            SmParameter
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> getSmValidList(SmParameter param) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT TSV.* ");
		sql.append(" FROM TASK_SMS_VALIDITY TSV ");
		sql.append(" WHERE 1=1 ");
		sql.append(" AND TSV.VALIDITY='1' ");
		if (SmParameter.isNotEmpty(param)) {
			if (StringUtils.isNotBlank(param.getWorkorderType())) {
				sql.append(" AND TSV.WORKORDER_TYPE='");
				sql.append(param.getWorkorderType());
				sql.append("' ");
			}
		}
		return super.getSQLALL(sql.toString());
	}

	/**
	 * 获取短信提醒时间配置列表
	 * 
	 * @param param
	 *            SmParameter
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> getSmTimeSetList(SmParameter param) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT RTC.*,TO_CHAR(RTC.TIMEOUT_NUM) AS TIMEOUT_NUM_DIS ");
		sql.append(" FROM REMIND_TIME_CONFIGURE RTC ");
		sql.append(" WHERE 1=1 ");
		if (SmParameter.isNotEmpty(param)) {
			if (StringUtils.isNotBlank(param.getWorkorderType())) {
				sql.append(" AND RTC.WORKORDER_TYPE='");
				sql.append(param.getWorkorderType());
				sql.append("' ");
			}
			if (StringUtils.isNotBlank(param.getBusinessType())) {
				sql.append(" AND RTC.PROFESSION_TYPE='");
				sql.append(param.getBusinessType());
				sql.append("' ");
			}
		}
		return super.getSQLALL(sql.toString());
	}
}
