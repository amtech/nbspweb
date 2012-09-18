package com.cabletech.business.ah.familyband.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.ah.familyband.dao.AhFamilyBandTroubleDao;
import com.cabletech.business.ah.familyband.model.AhFamilyBandRecode;
import com.cabletech.business.ah.familyband.model.AhFamilyBandTrouble;
import com.cabletech.business.ah.familyband.service.AhFamilyBandTroubleService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 存放每日家庭宽带巡检隐患情况具体实现
 * 
 * @author 陆道伟 2012-06-26 创建
 * 
 */
@Service
@Transactional
public class AhFamilyBandTroubleServceImpl extends
		BaseServiceImpl<AhFamilyBandTrouble, String> implements
		AhFamilyBandTroubleService {
	/**
	 * 存放每日家庭宽带巡检隐患情况DAO
	 */
	@Resource(name = "ahFamilyBandTroubleDao")
	private AhFamilyBandTroubleDao ahFamilyBandTroubleDao;

	/***
	 * 得到宽带巡检隐患Dao
	 */
	@Override
	protected BaseDao<AhFamilyBandTrouble, String> getBaseDao() {
		// TODO Auto-generated method stub
		return ahFamilyBandTroubleDao;
	}


	/* (non-Javadoc)
	 * @see com.cabletech.business.wplan.familyband.service.AhFamilyBandTroubleService#searchTroublesByRecod(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> searchTroublesByRecod(
			Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return ahFamilyBandTroubleDao.searchTroublesByRecod(parameters);
	}

	/***
	 * 删除家庭宽带隐患数据
	 * @param entity  家庭宽带巡检隐患实体
	 * @return 
	 */
	@Override
	public Integer deleteBandTrouble(AhFamilyBandTrouble entity) {
		// TODO Auto-generated method stub
		Integer flag = 0;
		try {
			ahFamilyBandTroubleDao.delete(entity);
			flag = 1;
		} catch (Exception e) {
			logger.error("删除家庭宽带巡检记录出现错误");
		}
		return flag;
	}

	
	/***
	 * 删除家庭宽带隐患数据
	 * @param id  家庭宽带巡检隐患实体 编号
	 * @return 
	 */
	@Override
	public AhFamilyBandTrouble getAhFamilyBandTroubleByid(String id) {
		// TODO Auto-generated method stub
		return ahFamilyBandTroubleDao.get(id);
	}

	
	/***
	 * 添加家庭宽带巡检隐患数据
	 * @param entity 家庭宽带巡检隐患实体
	 * @return
	 */
	@Override
	public Integer saveBandTrouble(AhFamilyBandTrouble entity) {
		Integer flag = 0;
		try {
			ahFamilyBandTroubleDao.save(entity);
			flag = 1;
		} catch (Exception e) {
			logger.error("删除家庭宽带巡检记录出现错误");
		}
		return flag;
	}


	/* (non-Javadoc)
	 * @see com.cabletech.business.wplan.familyband.service.AhFamilyBandTroubleService#getOrderNumber(com.cabletech.baseinfo.business.entity.UserInfo)
	 */
	@Override
	public String getOrderNumber(UserInfo userInfo) {
		return ahFamilyBandTroubleDao.getOrderNumber(userInfo);
	}



	/* (non-Javadoc)
	 * @see com.cabletech.common.base.BaseServiceImpl#delete(java.io.Serializable)
	 */
	@Override
	public void delete(String id) {
		List<AhFamilyBandTrouble> list = ahFamilyBandTroubleDao.findBy(
				"recodeId", id);
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		for (int i = 0; i < list.size(); i++) {
			ahFamilyBandTroubleDao.delete(list.get(i));
		}
	}


	/* (non-Javadoc)
	 * @see com.cabletech.business.wplan.familyband.service.AhFamilyBandTroubleService#savebatch(java.lang.String, com.cabletech.business.wplan.familyband.model.AhFamilyBandRecode, com.cabletech.baseinfo.business.entity.UserInfo)
	 */
	@Override
	public void savebatch(String id, AhFamilyBandRecode entity,UserInfo userInfo) {
		// TODO Auto-generated method stub
		if (ArrayUtils.isEmpty(entity.getDevicetype())) {
			return;
		}
		String[] devicetype = entity.getDevicetype();
		String[] position = entity.getPosition();
		String[] remark = entity.getRemark();
		Date[] expectendtime = entity.getExpectendtime();
		String orderNumber=ahFamilyBandTroubleDao.getOrderNumber(userInfo);
		for (int i = 0; i < devicetype.length; i++) {
			AhFamilyBandTrouble trouble = new AhFamilyBandTrouble();
			trouble.setOrdernumber(orderNumber);
			trouble.setDeviceType(devicetype[i]);
			trouble.setPosition(position[i]);
			trouble.setRemark(remark[i]);
			trouble.setExpectEndTime(expectendtime[i]);
			trouble.setRecodeId(id);
			trouble.setStatus("0");
			ahFamilyBandTroubleDao.save(trouble);
		}
	}

}
