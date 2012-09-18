package com.cabletech.business.sysmanager.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.sysmanager.dao.GprsMoDao;
import com.cabletech.business.sysmanager.model.GprsMo;
import com.cabletech.business.sysmanager.service.GprsMoService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * GPRS日志查询
 * @author wangt
 *
 */
@Service
@Transactional
public class GprsMoServiceImpl extends
BaseServiceImpl<GprsMo, String> implements GprsMoService {
	/**
	 * 巡检执行信息DAO
	 */
	@Resource(name = "gprsMoDao")
	private GprsMoDao dao;
	
	@Override
	protected BaseDao<GprsMo, String> getBaseDao() {
		return dao;
	}

	@Override
	public Page<Map<String, Object>> getQueryList(
			Page<Map<String, Object>> page, Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return dao.getQueryList(page,parameters);
	}

}
