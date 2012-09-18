package com.cabletech.business.ah.rating.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.ah.rating.dao.ItemResultDao;
import com.cabletech.business.ah.rating.model.ItemResult;
import com.cabletech.business.ah.rating.service.ItemResultService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 项目结果
 * @author wangt
 *
 */
@Service
@Transactional
public class ItemResultServiceImpl extends
BaseServiceImpl<ItemResult, String> implements ItemResultService  {

	// 代维单位人员自评Dao
	@Resource(name = "itemResultDao")
	private ItemResultDao dao;
	@Override
	protected BaseDao<ItemResult, String> getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	public String getSelfScoreSum(String id) {
		// TODO Auto-generated method stub
		return dao.getSelfScoreSum(id);
	}
	@Override
	public String getExamScoreSum(String id) {
		// TODO Auto-generated method stub
		return dao.getExamScoreSum(id);
	}

}
