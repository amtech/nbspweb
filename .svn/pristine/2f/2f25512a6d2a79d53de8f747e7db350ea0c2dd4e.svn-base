package com.cabletech.business.notice.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.notice.dao.NoticeSendEEDao;
import com.cabletech.business.notice.model.NoticeSendEE;
import com.cabletech.business.notice.service.NoticeSendEEService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;


/**
 * 操作短信接收人与参会人员的表
 * @author wangt
 *
 */
@Service
@SuppressWarnings("rawtypes")
public class NoticeSendEEServiceImpl extends BaseServiceImpl<NoticeSendEE,String> implements NoticeSendEEService {

	@Resource(name = "noticeSendEEDao")
	private NoticeSendEEDao dao;
	@SuppressWarnings("unchecked")
	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	@Transactional(readOnly=true)
	public List<NoticeSendEE> getNoticeSendEEByNoticeId(String id) {
		// TODO Auto-generated method stub
		return dao.getNoticeSendEEByNoticeId(id);
	}
	@Override
	@Transactional
	public void delete(String noticeid) {
		dao.deletebynoticeid(noticeid);
		
	}
	@Override
	@Transactional(readOnly=true)
	public NoticeSendEE getOneNoticeSendEE(NoticeSendEE noticesendee) {
		// TODO Auto-generated method stub
		return dao.getOneNoticeSendEE(noticesendee);
	}
	@Override
	@Transactional(readOnly=true)
	public List<Map<String, Object>> getNoticeSendList(String noticeid) {
		// TODO Auto-generated method stub
		return dao.getNoticeSendList(noticeid);
	}

}
