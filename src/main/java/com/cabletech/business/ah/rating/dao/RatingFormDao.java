package com.cabletech.business.ah.rating.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cabletech.business.ah.rating.model.RatingForm;

/**
 * 考核表DAO
 * 
 * @author 杨隽 2012-06-26 创建
 */
@Repository
public class RatingFormDao extends RatingFormBaseDao<RatingForm, String> {
	/**
	 * 获取表单数据信息的sql语句
	 * 
	 * @return String 获取表单数据信息的sql语句
	 */
	@Override
	public String getBusinessTableSql() {
		StringBuffer sqlBuf = new StringBuffer("");
		sqlBuf.append(" SELECT t.*,dic.LABLE AS BUSINESS_TYPE_NAME, ");
		sqlBuf.append(" to_char(t.CREATE_TIME,'yyyy-mm-dd hh24:mi:ss') ");
		sqlBuf.append(" AS CREATE_TIME_DIS,vu.USERNAME ");
		sqlBuf.append(" FROM AH_RATINGFORM t ");
		sqlBuf.append(" JOIN VIEW_USERINFO vu ON t.CREATER=vu.SID ");
		sqlBuf.append(" JOIN BASE_SYSDICTIONARY dic ");
		sqlBuf.append(" ON dic.CODEVALUE=t.BUSINESS_TYPE ");
		sqlBuf.append(" AND dic.COLUMNTYPE='BUSINESSTYPE' ");
		sqlBuf.append(" WHERE 1=1 ");
		return sqlBuf.toString();
	}

	/**
	 * 根据导入巡检项数据（项目名称、专业类型和区域编号）获取巡检项目列表
	 * 
	 * @param oneCell
	 *            RatingFormItemTemp 导入巡检项数据
	 * @return List<RatingForm> 巡检项目列表
	 */
	@SuppressWarnings("unchecked")
	public List<RatingForm> getRatingFormList(RatingForm oneCell) {
		Criteria criteria = getSession().createCriteria(RatingForm.class);
		criteria.add(Restrictions.eq("title", oneCell.getTitle()));
		criteria.add(Restrictions.eq("businessType", oneCell.getBusinessType()));
		List<RatingForm> list = criteria.list();
		return list;
	}
}