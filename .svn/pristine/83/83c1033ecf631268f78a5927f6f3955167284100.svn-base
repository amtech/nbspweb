package com.cabletech.business.workflow.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.common.base.BaseDao;

/**
 * 首页待办数量获取dao
 * 
 * @author 杨隽 2012-03-13 创建
 * 
 */
@Repository
@SuppressWarnings("rawtypes")
public class WaitHandledWorkDao extends BaseDao {
	// 日志输出
	protected final Logger logger = Logger.getLogger("WaitHandledWorkDao");

	/**
	 * 根据当前登录用户获取待办工作数量信息
	 * 
	 * @param userInfo
	 *            UserInfo 当前登录用户
	 * @return int 待办工作数量信息
	 */
	public int getWaitHandledWorkNumber(UserInfo userInfo) {
		String userId = userInfo.getUserId();
		String personId = userInfo.getPersonId();
		String groupId = userInfo.getOrgId();
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select count(*) as wait_handled_number ");
		sqlBuffer.append(" from view_jbpm_usertask t ");
		sqlBuffer.append(" where 1=1 ");
		sqlBuffer.append(" and (");
		sqlBuffer.append(" t.userid_='");
		sqlBuffer.append(userId);
		sqlBuffer.append("' ");
		sqlBuffer.append(" or t.userid_='");
		sqlBuffer.append(personId);
		sqlBuffer.append("' ");
		sqlBuffer.append(" or t.groupid_='");
		sqlBuffer.append(groupId);
		sqlBuffer.append("' ");
		sqlBuffer.append(" ) ");
		
		logger.info(" ------------------------------ test1::: "+sqlBuffer.toString());
		
		return super.getJdbcTemplate().queryForInt(sqlBuffer.toString());
	}

	/**
	 * 根据当前登录用户获取待办工作列表信息
	 * 
	 * @param userInfo
	 *            UserInfo 当前登录用户
	 * @return List<Map<String, Object>> 待办工作列表信息
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getWaitHandledWorkNumberList(
			UserInfo userInfo) {
		String userId = userInfo.getUserId();
		String personId = userInfo.getPersonId();
		String groupId = userInfo.getOrgId();
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select substr(p.id_,1,instr(p.id_,'.')-1) as deploymentkey,to_char(count(*)) as wait_handled_num ");
		sqlBuffer.append(" from view_jbpm_usertask t ");
		sqlBuffer.append(" join jbpm4_execution p on t.piid=p.id_ ");
		sqlBuffer.append(" where 1=1 ");
		sqlBuffer.append(" and (");
		sqlBuffer.append(" t.userid_='");
		sqlBuffer.append(userId);
		sqlBuffer.append("' ");
		sqlBuffer.append(" or t.userid_='");
		sqlBuffer.append(personId);
		sqlBuffer.append("' ");
		sqlBuffer.append(" or t.assignee_='");
		sqlBuffer.append(personId);
		sqlBuffer.append("' ");
		
		
	
		
		
		sqlBuffer.append(" or t.groupid_='");
		sqlBuffer.append(groupId);
		sqlBuffer.append("' ");
		sqlBuffer.append(" ) ");
		sqlBuffer.append(" group by substr(p.id_,1,instr(p.id_,'.')-1) ");
		
		logger.info(" ------------------------------ test2::: "+sqlBuffer.toString());
		
		return super.getSQLALL(sqlBuffer.toString());
	}
}
