package com.cabletech.business.sysmanager.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cabletech.business.sysmanager.model.RemindTimeConfigure;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.Page;

/**
 * 
 * @author zg
 * 
 */
@Repository
public class RemindTimeConfigureDao extends
		BaseDao<RemindTimeConfigure, String> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 查询处page 对象
	 * 
	 * @param page
	 *            Page
	 * @param parameters
	 *            Map<String,Object>
	 * @return Page
	 */
	public Page<RemindTimeConfigure> getQueryList(
			Page<RemindTimeConfigure> page, Map<String, Object> parameters) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				" select fn_getnamebycode(t.workorder_type,'TASK_TYPE')"
						+ " workorder_type,fn_getnamebycode(t.sms_type,'SMS_TYPE') "
						+ "sms_type,fn_getnamebycode(t.profession_type,'businesstype')"
						+ " profession_type,t.id,t.timeout_num from remind_time_configure t ")
				.append(" where 1=1 ").append(getCondition(parameters));
		this.logger.info(sql);
		return super.getSQLPageAll(page, sql.toString());
	}

	/**
	 * 封装条件
	 * 
	 * @param parameters
	 *            Map<String,Object>
	 * @return
	 */
	private String getCondition(Map<String, Object> parameters) {
		StringBuffer sql = new StringBuffer();
		String taskType = (String) parameters.get("taskType");
		String smsType = (String) parameters.get("smsType");
		String businessType = (String) parameters.get("businessType");
		if (StringUtils.isNotBlank(taskType)) {
			sql.append(" and t.workorder_type = '");
			sql.append(taskType);
			sql.append("' ");
		}
		if (StringUtils.isNotBlank(smsType)) {
			sql.append(" and t.sms_type = '");
			sql.append(smsType);
			sql.append("' ");
		}
		if (StringUtils.isNotBlank(businessType)) {
			sql.append(" and t.profession_type = '");
			sql.append(businessType);
			sql.append("' ");
		}
		return sql.toString();
	}

	/**
	 * 根据id 获取对象
	 * 
	 * @param id
	 *            String
	 * @return
	 */
	public RemindTimeConfigure getEntityById(String id) {
		return this.get(id);
	}

	/**
	 * 保存对象
	 * 
	 * @param remindTimeConfigure
	 *            RemindTimeConfigure
	 */
	public void saveEntity(RemindTimeConfigure remindTimeConfigure) {
		try {
			this.save(remindTimeConfigure);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * 查詢相同記錄的數量
	 * 
	 * @param taskType
	 *            String
	 * @param smsType
	 *            String
	 * @param businessType
	 *            String
	 * @return num
	 */
	public int checkNum(String taskType, String smsType, String businessType) {
		String sql = "select count(*) from remind_time_configure "
				+ "where workorder_type='" + taskType + "' and sms_type='"
				+ smsType + "'" + " and profession_type='" + businessType + "'";
		return this.jdbcTemplate.queryForInt(sql);
	}

	/**
	 * 根據id 刪除對象
	 * 
	 * @param id
	 *            String
	 */
	public void deleteEntityByid(String id) {
		try {
			this.delete(id);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * 根據工單類型 專業類型 獲取list
	 * 
	 * @param workorderType
	 *            String
	 * @param professionType
	 *            String
	 * @return list
	 */
	public List<Map<String, Object>> getListByType(String workorderType,
			String professionType) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.workorder_type," + " workorder_type,t.sms_type, "
				+ "sms_type,t.profession_type," + " profession_type,t.id,"
				+ "t.timeout_num from remind_time_configure t ");
		this.logger.info(sql);
		return super.getSQLALL(sql.toString());
	}
}
