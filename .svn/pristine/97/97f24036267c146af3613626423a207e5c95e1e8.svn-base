package com.cabletech.business.desktop.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.common.base.BaseDao;

/**
 * 离职人员统计Dao
 * 
 * @author 杨隽 2012-03-14 创建
 * 
 */
@Repository
@SuppressWarnings("rawtypes")
public class LeavePersonStatisticDao extends BaseDao {
	protected final Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 获取截止到当前月份的代维单位人员总数（按区域分组统计）
	 * 
	 * @param userInfo
	 *            UserInfo
	 * @param yearMonth
	 *            String
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAllPersonNumberGroupByRegion(
			UserInfo userInfo, String yearMonth) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select r.regionid as gid,r.regionname as gname, ");
		sqlBuffer.append(" to_char(count(*)) as all_num ");
		sqlBuffer.append(" from ( ");
		sqlBuffer.append(" select * from view_region  ");
		sqlBuffer.append(" where lv=3  ");
		sqlBuffer.append(" start with regionid='");
		sqlBuffer.append(userInfo.getRegionId());
		sqlBuffer.append("'  ");
		sqlBuffer.append(" connect by prior regionid=parentid ");
		sqlBuffer.append(" ) r join (");
		getAllPersonListSql(userInfo, yearMonth, sqlBuffer);
		sqlBuffer.append(" ) result_ on r.regionid=result_.regionid ");
		sqlBuffer.append(" group by r.regionid,r.regionname ");
		sqlBuffer.append(" order by r.regionid ");
		logger.info("获取截止到当前月份的代维单位人员总数（按区域分组统计）SQL:" + sqlBuffer.toString());
		return super.getSQLALL(sqlBuffer.toString());
	}

	/**
	 * 获取截止到当前月份的代维单位人员总数（按组织分组统计）
	 * 
	 * @param userInfo
	 *            UserInfo
	 * @param yearMonth
	 *            String
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAllPersonNumberGroupByOrg(
			UserInfo userInfo, String yearMonth) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select v.id as gid,v.name as gname, ");
		sqlBuffer.append(" to_char(count(*)) as all_num ");
		sqlBuffer.append(" from view_org v join ( ");
		getAllPersonListSql(userInfo, yearMonth, sqlBuffer);
		sqlBuffer.append(" ) result_ on v.id=result_.id ");
		sqlBuffer.append(" group by v.id,v.name ");
		sqlBuffer.append(" order by v.id ");
		logger.info("获取截止到当前月份的代维单位人员总数（按组织分组统计）SQL:" + sqlBuffer.toString());
		return super.getSQLALL(sqlBuffer.toString());
	}

	/**
	 * 获取当前月份的离职代维单位人员总数（按区域分组统计）
	 * 
	 * @param userInfo
	 *            UserInfo
	 * @param yearMonth
	 *            String
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getLeavePersonNumberByRegion(
			UserInfo userInfo, String yearMonth) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select r.regionid as gid,r.regionname as gname, ");
		sqlBuffer.append(" to_char(count(*)) as leave_num ");
		sqlBuffer.append(" from ( ");
		sqlBuffer.append(" select * from view_region  ");
		sqlBuffer.append(" where lv=3  ");
		sqlBuffer.append(" start with regionid='");
		sqlBuffer.append(userInfo.getRegionId());
		sqlBuffer.append("'  ");
		sqlBuffer.append(" connect by prior regionid=parentid ");
		sqlBuffer.append(" ) r join (");
		getLeavePersonListSql(userInfo, yearMonth, sqlBuffer);
		sqlBuffer.append(" ) result_ on r.regionid=result_.regionid ");
		sqlBuffer.append(" group by r.regionid,r.regionname ");
		sqlBuffer.append(" order by r.regionid ");
		logger.info("获取当前月份的离职代维单位人员总数（按区域分组统计）SQL:" + sqlBuffer.toString());
		return super.getSQLALL(sqlBuffer.toString());
	}

	/**
	 * 获取当前月份的离职代维单位人员总数（按组织分组统计）
	 * 
	 * @param userInfo
	 *            UserInfo
	 * @param yearMonth
	 *            String
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getLeavePersonNumberByOrg(
			UserInfo userInfo, String yearMonth) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select v.id as gid,v.name as gname, ");
		sqlBuffer.append(" to_char(count(*)) as leave_num ");
		sqlBuffer.append(" from view_org v join ( ");
		getLeavePersonListSql(userInfo, yearMonth, sqlBuffer);
		sqlBuffer.append(" ) result_ on v.id=result_.id ");
		sqlBuffer.append(" group by v.id,v.name ");
		sqlBuffer.append(" order by v.id ");
		logger.info("获取当前月份的离职代维单位人员总数（按组织分组统计）SQL:" + sqlBuffer.toString());
		return super.getSQLALL(sqlBuffer.toString());
	}

	/**
	 * 获取所有当月在职人员列表的sql
	 * 
	 * @param userInfo
	 *            UserInfo
	 * @param yearMonth
	 *            String
	 * @param sqlBuffer
	 *            StringBuffer
	 */
	private void getAllPersonListSql(UserInfo userInfo, String yearMonth,
			StringBuffer sqlBuffer) {
		sqlBuffer.append(" select vo.regionid,vo.id,op.id as pid ");
		sqlBuffer.append(" from base_person op ");
		sqlBuffer.append(" join view_org vo on op.contractorid=vo.id ");
		sqlBuffer.append(" where 1=1 ");
		sqlBuffer.append(" and vo.orgtype='2' ");
		sqlBuffer.append(" and ( ");
		sqlBuffer.append(" op.leave_time is null ");
		sqlBuffer.append(" or ");
		sqlBuffer.append(" op.leave_time>=add_months(to_date('");
		sqlBuffer.append(yearMonth);
		sqlBuffer.append("-1','yyyy-mm-dd'),1) ");
		sqlBuffer.append(" ) ");
		sqlBuffer.append(" and ( ");
		sqlBuffer.append(" op.enter_time is null  ");
		sqlBuffer.append(" or  ");
		sqlBuffer.append(" op.enter_time<add_months(to_date('");
		sqlBuffer.append(yearMonth);
		sqlBuffer.append("-1','yyyy-mm-dd'),1) ");
		sqlBuffer.append(" ) ");
		sqlBuffer.append(getUserCondition(userInfo));
	}

	/**
	 * 获取所有当月离职人员列表的sql
	 * 
	 * @param userInfo
	 *            UserInfo
	 * @param yearMonth
	 *            String
	 * @param sqlBuffer
	 *            StringBuffer
	 */
	private void getLeavePersonListSql(UserInfo userInfo, String yearMonth,
			StringBuffer sqlBuffer) {
		sqlBuffer.append(" select vo.regionid,vo.id,op.id as pid ");
		sqlBuffer.append(" from base_person op ");
		sqlBuffer.append(" join view_org vo on op.contractorid=vo.id ");
		sqlBuffer.append(" where 1=1 ");
		sqlBuffer.append(" and vo.orgtype='2' ");
		sqlBuffer.append(" and op.leave_time>=to_date('");
		sqlBuffer.append(yearMonth);
		sqlBuffer.append("-1','yyyy-mm-dd') ");
		sqlBuffer.append(" and op.leave_time<add_months(to_date('");
		sqlBuffer.append(yearMonth);
		sqlBuffer.append("-1','yyyy-mm-dd'),1) ");
		sqlBuffer.append(getUserCondition(userInfo));
	}

	/**
	 * 获取用户查询条件
	 * 
	 * @param userInfo
	 *            UserInfo
	 * @return
	 */
	private String getUserCondition(UserInfo userInfo) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer("");
		if (userInfo.isMobile()) {
			sqlBuffer.append(" and vo.regionid=any( ");
			sqlBuffer.append(" select r.regionid from view_region r ");
			sqlBuffer.append(" start with r.regionid='");
			sqlBuffer.append(userInfo.getRegionId());
			sqlBuffer.append("' ");
			sqlBuffer.append(" connect by prior r.regionid=r.parentid ");
			sqlBuffer.append(" ) ");
		}
		if (userInfo.isContractor()) {
			sqlBuffer.append(" and vo.id=any( ");
			sqlBuffer.append(" select v.id from view_org v ");
			sqlBuffer.append(" start with v.id='");
			sqlBuffer.append(userInfo.getOrgId());
			sqlBuffer.append("' ");
			sqlBuffer.append(" connect by prior v.id=v.parentid ");
			sqlBuffer.append(" ) ");
		}
		return sqlBuffer.toString();
	}
}
