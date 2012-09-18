package com.cabletech.business.sysmanager.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.cabletech.business.sysmanager.model.TaskSmsValidity;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.Page;

/**
 * @author zg
 * 
 */
@SuppressWarnings("rawtypes")
@Repository
public class TaskSmsValidityDao extends BaseDao<TaskSmsValidity, String>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 查询
	 * 
	 * @param page
	 *            Page
	 * @param parameters
	 *            Map<String,Object>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page getQueryList(Page page, Map<String, Object> parameters) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select t.id, fn_getnamebycode(t.workorder_type,'TASK_TYPE') workorder_type,"
						+ "fn_getnamebycode(t.sms_type,'SMS_TYPE') sms_type,")
				.append("CASE T.VALIDITY WHEN '0' THEN '关闭' WHEN '1' THEN '开启' end VALIDITY from task_sms_validity t");
		this.logger.info(sql);
		return getSQLPageAll(page, sql.toString());
	}

	/**
	 * 关闭
	 * 
	 * @param id
	 *            String
	 */
	public void shutDown(String id) {
		String sql = "update task_sms_validity set validity=0 where id=" + id;
		this.jdbcTemplate.execute(sql);
	}

	/**
	 * 开启方法
	 * 
	 * @param id
	 *            String
	 */
	public void startUp(String id) {
		String sql = "update task_sms_validity set validity=1 where id=" + id;
		this.jdbcTemplate.execute(sql);
	}

	/**
	 * 獲取有效性
	 * 
	 * @param smsType
	 *            String
	 * @param workorderType
	 *            String
	 * @return
	 */
	public String getValidityByType(String smsType, String workorderType) {
		String hql = "from TaskSmsValidity t where t.workorderType='"
				+ workorderType + "' and t.smsType ='" + smsType + "'";
		logger.info(hql);
		List<TaskSmsValidity> ss = this.getHQLAll(hql);
		TaskSmsValidity object = null;
		if (ss != null && ss.size() > 0) {
			object = ss.get(0);
		}
		String validity = "";
		if (object != null) {
			validity = object.getValidity();
		}
		return validity;
	}

}
