package com.cabletech.business.ah.rating.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cabletech.business.ah.rating.model.ContractorSelfRating;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.Page;

/**
 * 代维单位人员自评Dao
 * 
 * @author wangt
 * 
 */
@Repository
public class ContractorSelfRatingDao extends
		BaseDao<ContractorSelfRating, String> {
	/**
	 * 获取代维人员自评列表
	 * 
	 * @param page
	 *            Page
	 * @param orgId
	 *            String
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page queryPage(Page page, String orgId) {
		StringBuffer buffer = new StringBuffer("");
		buffer.append("select to_char(m.year_month,'YYYY-MM') year_month,decode(m.is_exam,'0','参与','1','不参与',null) is_exam,m.id,m.self_asse_num,u.sid,u.username,u.employee_num,u.orgname,u.REGIONID,g.REGIONNAME,r.table_id,FN_GETNAMEBYCODE(u.JOBINFO,'job_type') JOBINFO ");
		buffer.append("from AH_PERSONRATINGFORM r left join VIEW_USERINFO u on r.person_id = u.sid left join AH_MONTHRESULT m on (u.sid = m.person_id  and r.table_id= m.table_id ) ");
		buffer.append("left join region g on u.REGIONID = g.REGIONID ");
		buffer.append("where u.orgid in (select o.ID from view_org o connect by prior o.ID=o.PARENTID start with o.ID='");
		buffer.append(orgId);
		buffer.append("') ");
		buffer.append(" and r.person_id in (select person_id from AH_PERSONFLOW )");
		buffer.append(" and (m.year_month is null or not exists(select am.id from AH_MONTHRESULT am where am.id=m.id and am.flow_state is not null and am.SELF_ASSE_NUM is not null and ((am.YEAR_MONTH<trunc(sysdate,'MM')  and am.YEAR_MONTH>=add_months(trunc(sysdate,'MM'),-1)))))   ");
		buffer.append(" order by m.year_month,u.username ");
		return this.getSQLPageAll(page, buffer.toString());
	}

	/**
	 * 获取评估项
	 * 
	 * @param personId
	 *            String
	 * @param id
	 *            String
	 * @return
	 */
	public List<Map<String, Object>> getItems(String personId, String id) {
		StringBuffer buffer = new StringBuffer("");
		buffer.append("select m.*,h.exam_remark,h.target_completevalue,h.self_score,h.self_remark,h.id itemresultid,h.score from ");
		buffer.append("(select f.item,f.weight,f.base_value,f.challenge_value,f.norm_remark,f.evaluation_criterion,f.id ratingitemid ");
		buffer.append("from AH_PERSONRATINGFORM p,AH_RATINGFORM r,AH_RATINGFORMITEM f ");
		buffer.append("where  p.table_id = r.id and f.table_id = r.id and p.person_id='");
		buffer.append(personId);
		buffer.append("') m left join AH_ITEMRESULT h on h.item_id = m.ratingitemid and h.exam_id='");
		buffer.append(id);
		buffer.append("'");
		return this.getSQLALL(buffer.toString());
	}

	/**
	 * 获取代维人员信息
	 * 
	 * @param personId
	 *            String
	 * @param id
	 *            String
	 * @return
	 */
	public Map<String, Object> getPersonImfor(String personId, String id) {
		StringBuffer buffer = new StringBuffer("");
		buffer.append("select h.*,nvl(r.year_month,Add_months(sysdate,-1)) year_month,decode(r.flow_state,null,'0',r.flow_state) flowstate,r.is_exam from (select f.sid,f.orgid,f.JOBINFO,q.title table_name from view_userinfo f,ah_personratingform p,ah_ratingform q ");
		buffer.append("where f.sid=p.person_id and p.table_id=q.id ) h left join ah_monthresult r on h.sid = r.person_id and r.id='");
		buffer.append(id);
		buffer.append("' ");
		buffer.append("where h.sid='");
		buffer.append(personId);
		buffer.append("' ");
		List<Map<String, Object>> list = this.getSQLALL(buffer.toString());
		Map<String, Object> map = new HashMap<String, Object>();
		if (list != null && list.size() > 0) {
			map = list.get(0);
		}
		return map;
	}

}
