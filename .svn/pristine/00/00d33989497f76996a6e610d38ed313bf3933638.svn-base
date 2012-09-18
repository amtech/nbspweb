package com.cabletech.business.ah.familyband.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.ah.familyband.dao.AhFamilyBandRecodeDao;
import com.cabletech.business.ah.familyband.model.AhFamilyBandRecode;
import com.cabletech.business.ah.familyband.service.AhFamilyBandRecodeService;
import com.cabletech.business.ah.familyband.service.AhFamilyBandTroubleService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 存放每日家庭宽带的巡检具体实现
 * 
 * @author 陆道伟 2012-06-26 创建
 * 
 */
@Service
@Transactional
public class AhFamilyBandRecodeServceImpl extends
		BaseServiceImpl<AhFamilyBandRecode, String> implements
		AhFamilyBandRecodeService {
	/**
	 * 存放每日家庭宽带的巡检DAO
	 */
	@Resource(name = "ahFamilyBandRecodeDao")
	private AhFamilyBandRecodeDao ahFamilyBandRecodeDao;
	@Resource(name = "ahFamilyBandTroubleServceImpl")
	private AhFamilyBandTroubleService ahFamilyBandTroubleService;

	/***
	 * 得到ahFamilyBandRecodeDao事物dao
	 */
	@Override
	protected BaseDao<AhFamilyBandRecode, String> getBaseDao() {
		// TODO Auto-generated method stub
		return ahFamilyBandRecodeDao;
	}
	
	
	/***
	 * 通过巡检记录条件得到Page类
	 * @param parameters  巡检记录条件
	 * @param page  Page类
	 * @return
	 */
	@Override
	public Page searchRecods(Map<String, Object> parameters, Page page) {
		// TODO Auto-generated method stub
		return ahFamilyBandRecodeDao.searchRecods(parameters, page);
	}

	
	/***
	 * 删除宽带巡检记录
	 * @param entity  宽带巡检记录实体
	 */
	@Override
	public void deleteAnFamilyBandRecode(AhFamilyBandRecode entity) {
		// TODO Auto-generated method stub
		try {
			ahFamilyBandRecodeDao.delete(entity);
		} catch (Exception e) {
			logger.error("删除家庭宽带巡检记录出现错误");
		}
	}

	/***
	 * 通过id得到宽带巡检记录Map
	 * @param params 宽带巡检记录Id
	 * @return Map<String,Object>   宽带巡检记录Map
	 */
	@Override
	public Map<String,Object> getAhFamilyBandRecodeById(Map<String,Object>params) {
		// TODO Auto-generated method stub
		return ahFamilyBandRecodeDao.getRecode(params);
	}

	
	/***
	 * 保存巡检记录对应的宽带巡检隐患信息
	 * @param entity 
	 * @param userInfo 用户
	 */
	@Override
	@Transactional
	public void saveAhFamilyBandRecode(AhFamilyBandRecode entity,UserInfo userInfo) {
		// TODO Auto-generated method stub
		try {
			ahFamilyBandRecodeDao.save(entity);
			String id = entity.getId();
			ahFamilyBandTroubleService.delete(id);
			ahFamilyBandTroubleService.savebatch(id, entity,userInfo);
		} catch (Exception e) {
			logger.error("保存家庭宽带巡检记录出现错误", e);
		}
	}
	

	/* (non-Javadoc)
	 * @see com.cabletech.business.wplan.familyband.service.AhFamilyBandRecodeService#getAhFamilyBandRecodeById(java.lang.String)
	 */
	@Override
	public AhFamilyBandRecode getAhFamilyBandRecodeById(String id){
		return ahFamilyBandRecodeDao.get(id);
	}

}
