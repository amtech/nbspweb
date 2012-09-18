package com.cabletech.business.notice.service;

import java.util.List;
import java.util.Map;

import com.cabletech.business.notice.model.NoticeSendEE;


/**
 * 操作短信接收人与参会人员的表
 * @author wangt
 *
 */
public interface NoticeSendEEService {

	/**
	 * 根据公告编号查询与会人员
	 * @param noticeid 公告滴编号
	 * @return
	 */
	public List<NoticeSendEE> getNoticeSendEEByNoticeId(String noticeid);
	
	/**
	 * 获取参与人员列表
	 * @param noticeid noticeid
	 * @return
	 */
	public List<Map<String,Object>> getNoticeSendList(String noticeid);

	/**
	 * 删除
	 * @param noticeid 
	 */
	void delete(String noticeid);
	
	/**
	 * 保存
	 * @param noticesendee 
	 */
	void save(NoticeSendEE noticesendee);

	/**
	 * 获取实体
	 * @param noticesendee 
	 * @return
	 */
	public NoticeSendEE getOneNoticeSendEE(NoticeSendEE noticesendee);
	

}
