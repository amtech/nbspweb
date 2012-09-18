package com.cabletech.business.monthcost.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.monthcost.model.MonthTimesCost;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.Page;
import com.cabletech.common.util.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 
 * @author Administrator
 *
 */
@Repository
public class MonthTimesCostDao extends BaseDao<MonthTimesCost, String> {
	private static Logger logger = Logger.getLogger("MonthTimesCostDao");

	/**
	 * 获取实体
	 * 
	 * @param id
	 *            String
	 * @return
	 */
	public MonthTimesCost getEntityById(String id) {
		return get(id);
	}

	/**
	 * 删除对象
	 * 
	 * @param id
	 *            String
	 */
	public void deleteEntityById(String id) {
		delete(id);
	}

	/**
	 * 查询对 page对象
	 * 
	 * @param entity
	 *            MonthTimesCost
	 * @param page
	 *            Page
	 * @param user
	 *            UserInfo
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Page queryPage(MonthTimesCost entity, Page page, UserInfo user) {
		String sql = " select t.id,t.regionid,reg.REGIONNAME,vs.lable"
				+ " specialty,t.months,  t.specialty ss,t.contractorid v,vo.orgname CONTRACTORNAME,"
				+ "t.unitprice,t.numbers,case t.typet when 't111' then '发电' when 't112' then '看护' when 't113' then '随工' when 't114' then '网优配合' end typet,t.shouldmoney,t.factmoney from month_times_cost t,"
				+ " view_org vo,view_region reg,view_sysdictionary vs where t.regionid= reg.REGIONID and "
				+ " vs.codevalue=t.specialty and vo.id=t.contractorid and vs.columntype='BUSINESSTYPE'  ";

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
		if (StringUtils.isNotBlank(entity.getSpecialty())) {
			buf.append(" and  t.specialty ='").append(entity.getSpecialty())
					.append("'");

		}
		sql += buf.toString();
		logger.info("sql" + sql);
		return getSQLPageAll(page, sql);
	}

	/**
	 * 保存实体至 数据库
	 * 
	 * @param entity
	 *            MonthTimesCost
	 * @return
	 */
	public boolean saveEntity(MonthTimesCost entity) {
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
			orderStr = " order by reg.REGIONNAME, vo.orgname ";
		}
		if (Integer.parseInt(lab) == 2) {
			orderStr = " order by vo.orgname ,reg.REGIONNAME";
		}
		if (Integer.parseInt(lab) == 3) {
			orderStr = " order by vs.lable,vo.orgname,reg.REGIONNAME";
		}
		if (Integer.parseInt(lab) == 4) {
			orderStr = " order by t.typet,vs.lable,vo.orgname,reg.REGIONNAME ";
		}
		String sql = " select reg.REGIONNAME,vs.lable"
				+ " specialty,  vo.orgname CONTRACTORNAME,"
				+ "t.unitprice,t.numbers,t.typet d ,"
				+ "case t.typet when 't111' then '发电' when 't112' then '看护' when 't113' then '随工' when 't114' then '网优配合' end typet,"
				+ "t.factmoney from month_times_cost t,"
				+ " view_org vo,view_region reg,view_sysdictionary vs where t.regionid= reg.REGIONID and "
				+ " vs.codevalue=t.specialty and vo.id=t.contractorid and vs.columntype='BUSINESSTYPE' and t.months='"
				+ year + "-" + StringUtil.getMonth(month) + "'" + orderStr;
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
		String sql = "select sum(numbers) sum1,sum(factMoney) sum2 from month_times_cost   where  months ='"
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
			orderStr = " order by reg.REGIONNAME, vo.orgname ";
		}
		if (Integer.parseInt(lab) == 2) {
			orderStr = " order by vo.orgname ,reg.REGIONNAME";
		}
		if (Integer.parseInt(lab) == 3) {
			orderStr = " order by vs.lable,vo.orgname,reg.REGIONNAME";
		}
		if (Integer.parseInt(lab) == 4) {
			orderStr = " order by t.typet,vs.lable,vo.orgname,reg.REGIONNAME ";
		}
		String sql = " select reg.REGIONNAME,vs.lable"
				+ " specialty,  vo.orgname CONTRACTORNAME,"
				+ "t.unitprice,t.numbers,t.typet d ,"
				+ "case t.typet when 't111' then '发电' when 't112' then '看护' when 't113' then '随工' when 't114' then '网优配合' end typet,"
				+ "t.factmoney from month_times_cost t,"
				+ " view_org vo,view_region reg,view_sysdictionary vs where t.regionid= reg.REGIONID and "
				+ " vs.codevalue=t.specialty and vo.id=t.contractorid and vs.columntype='BUSINESSTYPE' and t.months='"
				+ year + "-" + StringUtil.getMonth(month) + "'" + orderStr;
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
			orderStr = " order by reg.REGIONNAME, vo.orgname ";
		}
		if (Integer.parseInt(lab) == 2) {
			orderStr = " order by vo.orgname ,reg.REGIONNAME";
		}
		if (Integer.parseInt(lab) == 3) {
			orderStr = " order by vs.lable,vo.orgname,reg.REGIONNAME";
		}
		if (Integer.parseInt(lab) == 4) {
			orderStr = " order by typet,vs.lable,vo.orgname,reg.REGIONNAME ";
		}
		String sql = " select reg.REGIONNAME,vs.lable"
				+ " specialty,  vo.orgname CONTRACTORNAME,"
				+ " sum(t.numbers) numbers, "
				+ "case t.typet when 't111' then '发电' when 't112' then '看护' when 't113' then '随工' when 't114' then '网优配合' end typet,"
				+ "sum(t.factmoney) factmoney from month_times_cost t,"
				+ " view_org vo,view_region reg,view_sysdictionary vs where t.regionid= reg.REGIONID and "
				+ " vs.codevalue=t.specialty and vo.id=t.contractorid and vs.columntype='BUSINESSTYPE' and t.months='"
				+ year + "-" + StringUtil.getMonth(month)
				+ "' group by reg.REGIONNAME,vo.orgname,vs.lable,typet "
				+ orderStr;
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
		String sql = "select sum(numbers) sum1,sum(factMoney) sum2 from month_times_cost   where  months ='"
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
			strName = "specialty";
		}
		if (Integer.parseInt(lab) == 4) {
			strName = "typet";
		}
		sql = " select count(distinct t." + strName
				+ ")  from month_times_cost t  where t.months ='" + months
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
		String sql = "select count(*) from base_sysdictionary t where t.columntype='TIMESTYPE' and  t.lable='"
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
		String sql = "select t.codevalue from base_sysdictionary t where t.columntype='TIMESTYPE' and t.lable='"
				+ typet + "'";
		String codevalue = "";
		List<Map<String, Object>> lm = getSQLALL(sql);
		if (lm != null && lm.size() > 0) {
			Map<String, Object> map = lm.get(0);
			codevalue = (String) map.get("CODEVALUE");
		}
		return codevalue;
	}

	public String getSpecialtyByInput(String specialty) {
		String sql = "select t.codevalue from base_sysdictionary t where t.columntype='BUSINESSTYPE' and t.lable='"
				+ specialty + "'";
		String codevalue = "";
		List<Map<String, Object>> lm = getSQLALL(sql);
		if (lm != null && lm.size() > 0) {
			Map<String, Object> map = lm.get(0);
			codevalue = (String) map.get("CODEVALUE");
		}
		return codevalue;
	}
}
