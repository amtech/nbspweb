package com.cabletech.business.sysmanager.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.cabletech.business.sysmanager.model.WorkorderControlInfo;
import com.cabletech.common.base.BaseDao;

/**
 * 
 * @author Administrator
 * 
 */
@Repository
public class WorkorderControlInfoDao extends
		BaseDao<WorkorderControlInfo, String> implements Serializable {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 获取所有的信息列表
	 * 
	 * @return
	 */
	public List<Map<String, Object>> selectAllOrders() {
		String sql = "select wci.id,wci.workorder_id,wci.workorder_type,"
				+ "wci.workorder_title,wci.handle_limit,wci.profession_type,"
				+ "wci.handle_personid,wci.sms_send_flg"
				+ "    from workorder_control_info wci where wci.flag=0 order by wci.id desc";
		return this.getSQLALL(sql);
	}

	/**
	 * 修改job状态
	 * 
	 * @param workorderId
	 *            String
	 */
	public void updateSchedulerState(String workorderId) {
		String sql = "update workorder_control_info t set t.flag=1 where workorder_id='"
				+ workorderId + "'";// 置1
		// 说明是已经存在job中了
		this.jdbcTemplate.execute(sql);

	}

	/**
	 * 根据工单执行人 sid 获取电话号码。
	 * 
	 * @param handlePersonId
	 *            String
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getPhoneBYWorkId(String handlePersonId) {
		String sql = "select t.phone from view_userinfo t where t.sid='"
				+ handlePersonId + "'";
		List l = this.jdbcTemplate.queryForList(sql);
		String phone = "";
		if (l.size() > 0) {
			phone = (String) l.get(0);
		}
		return phone;
	}
}
