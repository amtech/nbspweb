package com.cabletech.business.desktop.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.cabletech.common.base.BaseDao;

/**
 * 
 * 个人工作台--用户快捷方式
 * 
 * @author wj
 * 
 */
@Repository
public class DeskTopWorkBenchDao extends BaseDao {
	/**
	 * 保存用户快捷方式
	 * 
	 * @param userId
	 *            用户信息
	 * @param menuId
	 *            菜单IDS
	 */
	public void saveUserShortcuts(String userId, String menuId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" insert into BASE_USER_SHORTCUTS bus (user_id,menu_id) values('"
				+ userId + "','" + menuId + "') ");
		this.getJdbcTemplate().update(sb.toString());
	}

	/**
	 * 保存用户快捷方式
	 * 
	 * @param userId
	 *            用户ID
	 */
	public void deleteUserShortcuts(String userId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" delete BASE_USER_SHORTCUTS bus where bus.user_id = '"
				+ userId + "' ");
		this.getJdbcTemplate().update(sb.toString());
	}

	/**
	 * 查询用户快捷方式
	 * 
	 * @param userId
	 *            用户ID
	 */
	public List<Map<String, Object>> queryUserShortcuts(String userId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select bm.* from base_menu bm ");
		sb.append(" join BASE_USER_SHORTCUTS bus on bm.id = bus.menu_id ");
		sb.append(" where bus.user_id = '" + userId + "' ");
		return this.getJdbcTemplate().queryForList(sb.toString());
	}

}