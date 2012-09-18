package com.cabletech.business.monthcost.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.monthcost.model.MonthCheckCost;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.Page;
import com.cabletech.common.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author 周刚 月度考核dao处理数据
 * 
 */
@Repository
public class MonthCheckCostDao extends BaseDao<MonthCheckCost, String> {
	private static Logger logger = Logger.getLogger("MonthCheckCostDao");

	/**
	 * 根据id获取实体对象
	 * 
	 * @param id
	 *            String
	 * @return
	 */
	public MonthCheckCost getEntityById(String id) {
		return get(id);
	}

	/**
	 * 查詢出page返回至表格
	 * 
	 * @param entity
	 *            MonthCheckCost
	 * @param page
	 *            Page
	 * @param user
	 *            UserInfo
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Page queryPage(MonthCheckCost entity, Page page, UserInfo user) {
		String sql = "select t.id,t.regionid,reg.REGIONNAME,vs.lable specialty,t.months,"
				+ " t.specialty ss,t.contractorid v,vo.orgname CONTRACTORNAME,"
				+ " t.unitprice,t.numbers,t.shouldmoney,t.checkfraction,t.subtractmoney "
				+ " from month_check_cost t, view_region reg,view_sysdictionary vs,view_org vo "
				+ " where  t.regionid= reg.REGIONID and vo.id=t.contractorid  "
				+ "and  vs.codevalue=t.specialty  and vs.columntype='BUSINESSTYPE'   ";

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
		if (StringUtils.isNotBlank(entity.getSpecialty())) {
			buf.append(" and  t.specialty ='").append(entity.getSpecialty())
					.append("'");

		}
		sql += buf.toString();

		logger.info("sql" + sql);
		return getSQLPageAll(page, sql);
	}

	/**
	 * 根据id 删除实体
	 * 
	 * @param id
	 *            String
	 */
	public void deleteEntityById(String id) {
		this.delete(id);
	}

	/**
	 * 保存实体
	 * 
	 * @param entity
	 *            MonthCheckCost
	 * @return
	 */
	public boolean saveEntity(MonthCheckCost entity) {
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
			orderStr = " order by reg.REGIONNAME, vo.orgname ,vs.lable";
		}
		if (Integer.parseInt(lab) == 2) {
			orderStr = " order by  vo.orgname ,reg.REGIONNAME,vs.lable";
		}
		if (Integer.parseInt(lab) == 3) {
			orderStr = " order by vs.lable, vo.orgname ,reg.REGIONNAME";
		}
		String sql = "select  reg.REGIONNAME,vs.lable specialty,"
				+ " vo.orgname CONTRACTORNAME, t.factMoney,"
				+ " t.unitprice,t.numbers "
				+ " from month_check_cost t, view_region reg,view_sysdictionary vs,"
				+ "view_org vo"
				+ " where  t.regionid= reg.REGIONID and vo.id=t.contractorid  "
				+ "and  vs.codevalue=t.specialty  and vs.columntype='BUSINESSTYPE' and t.months ='"
				+ year + "-" + StringUtil.getMonth(month) + "'" + orderStr + "";
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
		String sql = "select sum(t.numbers) sum1,sum(t.factmoney) sum2 from month_check_cost t  where t.months ='"
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
			orderStr = " order by reg.REGIONNAME, vo.orgname ,vs.lable";
		}
		if (Integer.parseInt(lab) == 2) {
			orderStr = " order by  vo.orgname ,reg.REGIONNAME,vs.lable";
		}
		if (Integer.parseInt(lab) == 3) {
			orderStr = " order by vs.lable, vo.orgname ,reg.REGIONNAME";
		}
		String sql = "select  reg.REGIONNAME,vs.lable specialty,"
				+ " vo.orgname CONTRACTORNAME, t.factMoney,"
				+ " t.unitprice,t.numbers "
				+ " from month_check_cost t, view_region reg,view_sysdictionary vs,"
				+ "view_org vo"
				+ " where  t.regionid= reg.REGIONID and vo.id=t.contractorid  "
				+ "and  vs.codevalue=t.specialty  and vs.columntype='BUSINESSTYPE' and t.months ='"
				+ year + "-" + StringUtil.getMonth(month) + "'" + orderStr + "";
		logger.info(sql);
		return super.getJdbcTemplate().queryForList(sql);
	}

	/**
	 * 获取小计的值
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
			orderStr = " order by reg.REGIONNAME, vo.orgname ,vs.lable";
		}
		if (Integer.parseInt(lab) == 2) {
			orderStr = " order by  vo.orgname ,reg.REGIONNAME,vs.lable";
		}
		if (Integer.parseInt(lab) == 3) {
			orderStr = " order by vs.lable, vo.orgname ,reg.REGIONNAME";
		}
		String sql = "select  reg.REGIONNAME,vs.lable specialty,"
				+ " vo.orgname CONTRACTORNAME, sum(t.factMoney)  factmoney,"
				+ " sum(t.numbers) numbers ,'合计' unitprice"
				+ " from month_check_cost t, view_region reg,view_sysdictionary vs,"
				+ "view_org vo"
				+ " where  t.regionid= reg.REGIONID and vo.id=t.contractorid  "
				+ "and  vs.codevalue=t.specialty  and vs.columntype='businesstype'and t.months ='"
				+ year + "-" + StringUtil.getMonth(month)
				+ "' group by reg.REGIONNAME,vs.lable,vo.orgname " + orderStr + "";
		logger.info(sql);
		return getSQLALL(sql);
	}

	/**
	 * 获取总计值
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
		String sql = "select sum(t.numbers) sum1,sum(t.factmoney) sum2 from month_check_cost t  where t.months ='"
				+ months + "'";
		logger.info(sql);
		return super.getJdbcTemplate().queryForList(sql);
	}

	/**
	 * 获取数量
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
		sql = " select count(distinct t." + strName
				+ ")  from month_check_cost t  where t.months ='" + months
				+ "'";
		logger.info(sql);
		return super.getJdbcTemplate().queryForInt(sql);
	}

	/**
	 * 检查专业类型名称是否合理
	 * 
	 * @param businessName
	 *            String
	 * @return
	 */
	public Integer checkBusinessName(String businessName) {
		String sql = "select count(*) from base_sysdictionary t where t.columntype='BUSINESSTYPE' and  t.lable='"
				+ businessName + "'";
		return super.getJdbcTemplate().queryForInt(sql);
	}

	/**
	 * 检查代维机构名称 是否合理
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
	 * 从excel表格里面取的数据 来寻找其对应的id
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
	 * 从参数表中查找专业id 根据excel表格的输入
	 * 
	 * @param specialty
	 *            String
	 * @return
	 */
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
