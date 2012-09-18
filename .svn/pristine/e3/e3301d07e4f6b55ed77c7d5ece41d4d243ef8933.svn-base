package com.cabletech.business.workflow.electricity.oilengine.service;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.electricity.oilengine.model.OilRecord;
import com.cabletech.common.util.Page;

/**
 * 加油记录业务接口
 * 
 * @author wangt
 * @author 杨隽 2012-05-14 添加getList()方法
 * 
 */
public interface OilRecordService {
	/**
	 * 保存
	 * 
	 * @param entity
	 *            实体实体
	 * @param user
	 *            UserInfo
	 */
	void saveOilRecord(OilRecord entity, UserInfo user);

	/**
	 * 根据油机编号获取油机的加油记录信息列表
	 * 
	 * @param engineId
	 *            String 油机编号
	 * @param page
	 *            Page 分页信息
	 * @return Page 加油记录信息列表
	 */
	@SuppressWarnings("rawtypes")
	Page getList(String engineId, Page page);
}
