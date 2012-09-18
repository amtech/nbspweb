package com.cabletech.business.workflow.electricity.oilengine.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.electricity.oilengine.dao.OilRecordDao;
import com.cabletech.business.workflow.electricity.oilengine.model.OilRecord;
import com.cabletech.business.workflow.electricity.oilengine.service.OilRecordService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 加油记录业务实现
 * 
 * @author wangt
 * @author 杨隽 2012-05-14 添加getList()方法
 * 
 */
@Service
public class OilRecordServiceImpl extends BaseServiceImpl<OilRecord, String>
		implements OilRecordService {
	// 油机管理Dao
	@Resource(name = "oilRecordDao")
	private OilRecordDao dao;

	@Override
	@Transactional
	public void saveOilRecord(OilRecord entity, UserInfo user) {
		Date date = new Date();
		entity.setRecordDate(date);
		entity.setRecorder(user.getPersonId());
		super.save(entity);
	}

	@SuppressWarnings("rawtypes")
	@Override
	@Transactional(readOnly = true)
	public Page getList(String engineId, Page page) {
		return dao.getList(engineId, page);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected BaseDao getBaseDao() {
		return dao;
	}
}
