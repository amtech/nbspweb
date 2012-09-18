package com.cabletech.business.monthcost.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.monthcost.model.MonthOtherCost;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.Page;
import com.cabletech.common.util.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author 周刚
 * 
 * 
 *         其他费用处理对象类
 * 
 */
@Repository
public class MonthOtherCostDao extends BaseDao<MonthOtherCost, String> {
	private static Logger logger = Logger.getLogger("MonthOtherCostDao");

	/**
	 * 获取page对象
	 * 
	 * @param entity
	 *            MonthOtherCost
	 * @param page
	 *            Page
	 * @param user
	 *            UserInfo
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Page queryPage(MonthOtherCost entity, Page page, UserInfo user) {
		String sql = "select t.id,t.regionid,reg.REGIONNAME,t.months,"
				+ "t.contractorid v,vo.orgname CONTRACTORNAME,t.shouldmoney ,"
				+ "case t.typet when 'o111' then '奖励' when 'o112' then '补贴' when 'o113' then '报销' when 'o114' then '其他' end typet,"
				+ "t.factmoney from month_other_cost t,view_org vo, view_region reg where "
				+ "  vo.id=t.contractorid  and t.regionid= reg.REGIONID   ";

		StringBuffer buf = new StringBuffer("");

		if (StringUtils.isNotBlank(entity.getContractorId())) {
			buf.append(" and t.contractorid = '")
					.append(entity.getContractorId()).append("'");

		}
		if (StringUtils.isNotBlank(entity.getMonths())) {
			buf.append(" and t.months = '").append(entity.getMonths())
					.append("'");

		}
		if (StringUtils.isNotBlank(entity.getRegionId())) {
			buf.append(" and t.regionid ='").append(entity.getRegionId())
					.append("'");

		}
		if (StringUtils.isNotBlank(entity.getTypet())) {
			buf.append(" and  t.typet ='").append(entity.getTypet())
					.append("'");

		}
		sql += buf.toString();
		logger.info(sql);
		return getSQLPageAll(page, sql);
	}

	/**
	 * 根据id获取对象
	 * 
	 * @param id
	 *            String
	 * @return
	 */
	public MonthOtherCost getEntityById(String id) {
		return this.get(id);
	}

	/**
	 * 删除对象
	 * 
	 * @param id
	 *            String
	 */
	public void deleteEntityById(String id) {
		this.delete(id);
	}

	/**
	 * 保存对象
	 * 
	 * @param entity
	 *            MonthOtherCost
	 * @return
	 */
	public boolean saveEntity(MonthOtherCost entity) {
		this.save(entity);
		return true;
	}

	/**
	 * 按照传入的月份和年份查询出所有的结果
	 * 
	 * @param month
	 *            String
	 * @param year
	 *            String
	 * @param lab
	 *            String
	 * @return
	 */
	public List<Map<String, Object>> getData(String month, String year,
			String lab) {
		String orderStr = "";
		if (Integer.parseInt(lab) == 1) {
			orderStr = " order by reg.REGIONNAME, vo.orgname ,typet";
		}
		if (Integer.parseInt(lab) == 2) {
			orderStr = " order by vo.orgname ,reg.REGIONNAME,typet";
		}
		if (Integer.parseInt(lab) == 3) {
			orderStr = " order by typet,reg.REGIONNAME ,vo.orgname";
		}
		String sql = "select  reg.REGIONNAME,vo.orgname CONTRACTORNAME,t.shouldmoney ,"
				+ "case t.typet when 'o111' then '奖励' when 'o112' then '补贴' when 'o113' then '报销' when 'o114' then '其他' end typet,"
				+ "t.factmoney,t.remark from month_other_cost t,view_org vo, view_region reg where "
				+ "vo.id=t.contractorid  and t.regionid= reg.REGIONID    and t.months='"
				+ year + "-" + StringUtil.getMonth(month) + "' " + orderStr;
		logger.info(sql);
		return getSQLALL(sql);
	}

	/**
	 * 
	 * @param month
	 *            String
	 * @param year
	 *            String
	 * @return
	 */
	public List<Map<String, Object>> getTotalData(String month, String year) {
		String months = year + "-" + StringUtil.getMonth(month);
		String sql = "select sum(shouldmoney) sum1,sum(factMoney) sum2 from month_other_cost   where  months ='"
				+ months + "'";
		logger.info(sql);
		return getSQLALL(sql);
	}

	/**
	 * 
	 * @param month
	 *            String
	 * @param year
	 *            String
	 * @param lab
	 *            String
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getDataList(String month, String year, String lab) {
		String orderStr = "";
		if (Integer.parseInt(lab) == 1) {
			orderStr = " order by reg.REGIONNAME, vo.orgname ,typet";
		}
		if (Integer.parseInt(lab) == 2) {
			orderStr = " order by vo.orgname ,reg.REGIONNAME,typet";
		}
		if (Integer.parseInt(lab) == 3) {
			orderStr = " order by typet,reg.REGIONNAME ,vo.orgname";
		}
		String sql = "select  reg.REGIONNAME,vo.orgname CONTRACTORNAME,t.shouldmoney ,"
				+ "case t.typet when 'o111' then '奖励' when 'o112' then '补贴' when 'o113' then '报销' when 'o114' then '其他' end typet,"
				+ "t.factmoney,t.remark from month_other_cost t,view_org vo, view_region reg where "
				+ "vo.id=t.contractorid  and t.regionid= reg.REGIONID    and t.months='"
				+ year + "-" + StringUtil.getMonth(month) + "' " + orderStr;
		logger.info(sql);
		return super.getJdbcTemplate().queryForList(sql);
	}

	/**
	 * 
	 * @param month
	 *            String
	 * @param year
	 *            String
	 * @param lab
	 *            String
	 * @return
	 */
	public List<Map<String, Object>> getlitteltotalData(String month,
			String year, String lab) {
		String orderStr = "";
		if (Integer.parseInt(lab) == 1) {
			orderStr = " order by reg.REGIONNAME, vo.orgname ,typet";
		}
		if (Integer.parseInt(lab) == 2) {
			orderStr = " order by vo.orgname ,reg.REGIONNAME,typet";
		}
		if (Integer.parseInt(lab) == 3) {
			orderStr = " order by typet,reg.REGIONNAME ,vo.orgname";
		}
		String sql = "select  reg.REGIONNAME,vo.orgname CONTRACTORNAME,sum(t.shouldmoney) shouldmoney,"
				+ "case t.typet when 'o111' then '奖励' when 'o112' then '补贴' when 'o113' then '报销' when 'o114' then '其他' end typet,"
				+ "sum(t.factmoney) factmoney from month_other_cost t,view_org vo, view_region reg where "
				+ "vo.id=t.contractorid  and t.regionid= reg.REGIONID and t.months='"
				+ year
				+ "-"
				+ StringUtil.getMonth(month)
				+ "'group by reg.REGIONNAME,vo.orgname,typet " + orderStr;
		logger.info(sql);
		return getSQLALL(sql);
	}

	/**
	 * 
	 * @param month
	 *            String
	 * @param year
	 *            String
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getTotalDataList(String month, String year) {
		String months = year + "-" + StringUtil.getMonth(month);
		String sql = "select sum(shouldmoney) sum1,sum(factMoney) sum2 from month_other_cost   where  months ='"
				+ months + "'";
		logger.info(sql);
		return super.getJdbcTemplate().queryForList(sql);
	}

	/**
	 * 
	 * @param month
	 *            String
	 * @param year
	 *            String
	 * @param lab
	 *            String
	 * @return
	 */
	public Integer getCount4Total(String month, String year, String lab) {
		String sql = "";
		String strName = "";
		String months = year + "-" + StringUtil.getMonth(month);
		if (Integer.parseInt(lab) == 1) {
			strName = "regionid";
		}
		if (Integer.parseInt(lab) == 2) {
			strName = "contractorid";
		}

		if (Integer.parseInt(lab) == 3) {
			strName = "typet";
		}
		sql = " select count(distinct t." + strName
				+ ")  from month_other_cost t  where t.months ='" + months
				+ "'";
		logger.info(sql);
		return super.getJdbcTemplate().queryForInt(sql);
	}

	/**
	 * 
	 * @param contractorId
	 *            String
	 * @return
	 */
	public Integer checkContractorId(String contractorId) {
		String sql = "select count(*) from view_org t where t.orgname='"
				+ contractorId + "'";
		return super.getJdbcTemplate().queryForInt(sql);
	}

	/**
	 * 
	 * @param typet
	 *            String
	 * @return
	 */
	public Integer checkTypetName(String typet) {
		String sql = "select count(*) from base_sysdictionary t where t.columntype='OTHERTYPE' and  t.lable='"
				+ typet + "'";
		return super.getJdbcTemplate().queryForInt(sql);
	}

	/**
	 * 
	 * @param contractorId
	 *            String
	 * @return
	 */
	public String getContractorIdByInput(String contractorId) {
		String id = "";
		String sql = "select t.id from view_org t where t.orgname='"
				+ contractorId + "'";
		List<Map<String, Object>> lm = getSQLALL(sql);
		if (lm != null && lm.size() > 0) {
			Map<String, Object> map = lm.get(0);
			id = (String) map.get("ID");
		}
		return id;
	}

	/**
	 * 
	 * @param typet
	 *            String
	 * @return
	 */
	public String getTypetByInput(String typet) {
		String sql = "select t.codevalue from base_sysdictionary t where t.columntype='OTHERTYPE' and t.lable='"
				+ typet + "'";
		String codevalue = "";
		List<Map<String, Object>> lm = getSQLALL(sql);
		if (lm != null && lm.size() > 0) {
			Map<String, Object> map = lm.get(0);
			codevalue = (String) map.get("CODEVALUE");
		}
		return codevalue;
	}
}
