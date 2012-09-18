package com.cabletech.business.workflow.accident.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.workflow.accident.dao.MmAccidentBaseDao;
import com.cabletech.business.workflow.accident.model.MmAccidentType;
import com.cabletech.business.workflow.accident.service.MmAccidentTypeService;

/**
 * 隐患类型业务处理接口实现
 * 
 * @author 杨隽 2012-08-27 创建
 * 
 */
@SuppressWarnings("unchecked")
@Service
@Transactional
public class MmAccidentTypeServiceImpl extends
		MmAccidentBaseServiceImpl<MmAccidentType, String> implements
		MmAccidentTypeService {
	@Resource(name = "mmAccidentTypeDao")
	private MmAccidentBaseDao<MmAccidentType, String> accidentTypeDao;

	@Override
	@Transactional(readOnly = true)
	public MmAccidentType viewAccidentType(String id) {
		MmAccidentType accident = accidentTypeDao.get(id);
		return accident;
	}

	@Override
	protected MmAccidentBaseDao<MmAccidentType, String> getAccidentBaseDao() {
		return accidentTypeDao;
	}
}
