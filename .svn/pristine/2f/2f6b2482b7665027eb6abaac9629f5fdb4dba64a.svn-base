package com.cabletech.business.notice.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cabletech.business.notice.model.NoticeSendEE;
import com.cabletech.common.base.BaseDao;

/**
 * 会议人员Dao
 * @author wangt
 *
 */
@Repository
public class NoticeSendEEDao extends BaseDao<NoticeSendEE, String> {

	/**
	 * 通过noticeid 查询与会人员信息
	 * @param id noticeid
	 * @return
	 */
	public List<NoticeSendEE> getNoticeSendEEByNoticeId(String id) {
		// TODO Auto-generated method stub
		String hql = " from NoticeSendEE where notice_id='"+id+"'";
		return this.getHQLAll(hql);
	}
	
	/**查询所有参与人员信息
	 * @param id noticeid
	 * @return
	 */
	public List<Map<String,Object>>getNoticeSendList(String id){
		String sql="select s.*,u.username from notice_sendee s join view_userinfo u on s.person_id=u.sid where notice_id='"+id+"'";
		return this.getSQLALL(sql);
	}
	
	/**
	 * 删除noticeid相关与会人员信息
	 * @param noticeid 
	 */
	public void deletebynoticeid(String noticeid){
		String hql= "delete from NoticeSendEE where notice_id=?";
		this.batchHQLExecute(hql, noticeid);
	}

	/**
	 * 通过联合主键获取实体
	 * @param noticesendee 
	 * @return
	 */
	public NoticeSendEE getOneNoticeSendEE(NoticeSendEE noticesendee) {
		String hql= " from NoticeSendEE where notice_id='"
			+noticesendee.getNoticeid()+"' and person_id='"
			+noticesendee.getPersonid()+"'";
		 List<NoticeSendEE> entitylist = this.getHQLAll(hql);
		 if(entitylist!=null && entitylist.size()!=0){
			 noticesendee=entitylist.get(0);
		 }
		return noticesendee;
	}
}
