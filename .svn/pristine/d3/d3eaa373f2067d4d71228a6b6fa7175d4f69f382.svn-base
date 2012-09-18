package com.cabletech.business.ah.familyband.service;

import java.util.Map;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.ah.familyband.model.AhFamilyBandRecode;
import com.cabletech.common.util.Page;

/***
 * 存放每日家庭宽带的巡检具体实现接口
 * 
 * @author 陆道伟 2012-06-26 创建
 * 
 */
public interface AhFamilyBandRecodeService {
	
	
	/***
	 * 通过巡检记录条件得到Page类
	 * @param parameters  巡检记录条件
	 * @param page  Page类
	 * @return
	 */
	public Page searchRecods(Map<String, Object> parameters, Page page);

	
	/***
	 *   添加宽带巡检记录
	 * @param entity  宽带巡检记录实体
	 * @param userInfo  人员实体
	 */
	public void saveAhFamilyBandRecode(AhFamilyBandRecode entity,UserInfo userInfo);

	
	/***
	 * 删除宽带巡检记录
	 * @param entity  宽带巡检记录实体
	 */
	public void deleteAnFamilyBandRecode(AhFamilyBandRecode entity);
	
	
	/***
	 * 通过id得到宽带巡检记录Map
	 * @param params 宽带巡检记录Id
	 * @return Map<String,Object>   宽带巡检记录Map
	 */
	public Map<String,Object> getAhFamilyBandRecodeById(Map<String,Object>params);
	
	
	/***
	 * 通过id得到宽带巡检记录实体类
	 * @param id  宽带巡检记录id
	 * @return  宽带巡检记录实体类
	 */
	public AhFamilyBandRecode getAhFamilyBandRecodeById(String id);

}
