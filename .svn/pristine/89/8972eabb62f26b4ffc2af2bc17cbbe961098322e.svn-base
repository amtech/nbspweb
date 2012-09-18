package com.cabletech.business.ah.rating.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cabletech.business.ah.rating.model.ItemResult;
import com.cabletech.common.base.BaseDao;

/**
 * 考核项结果
 * 
 * @author wangt
 * 
 */
@Repository
public class ItemResultDao extends BaseDao<ItemResult, String> {

	/**
	 * 查询自评总分
	 * 
	 * @param id
	 *            String
	 * @return
	 */
	public String getSelfScoreSum(String id) {
		StringBuffer sql = new StringBuffer("");
		sql.append("select decode(sum( m.self_score),null,0,sum(m.self_score)) sumscore from AH_ITEMRESULT m where m.exam_id='");
		sql.append(id);
		sql.append("'");
		List<Map<String, Object>> list = this.getSQLALL(sql.toString());
		String sum = "0";
		if (list != null && list.size() > 0) {
			sum = list.get(0).get("SUMSCORE").toString();
		}
		return sum;
	}

	/**
	 * 查询考核总分
	 * 
	 * @param id
	 *            String
	 * @return
	 */
	public String getExamScoreSum(String id) {
		StringBuffer sql = new StringBuffer("");
		sql.append("select decode(sum( m.score),null,'null',sum(m.score)) sumscore from AH_ITEMRESULT m where m.exam_id='");
		sql.append(id);
		sql.append("'");
		List<Map<String, Object>> list = this.getSQLALL(sql.toString());
		String sum = "0";
		if (list != null && list.size() > 0) {
			sum = list.get(0).get("SUMSCORE").toString();
		}
		return sum;
	}

}
