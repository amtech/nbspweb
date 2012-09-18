package com.cabletech.business.ah.familyband.service;

import java.util.List;
import java.util.Map;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.ah.familyband.model.AhFamilyBandRecode;
import com.cabletech.business.ah.familyband.model.AhFamilyBandTrouble;

/***
 * 存放每日家庭宽带巡检隐患情况接口
 * 
 * @author 陆道伟 2012-06-26 创建
 * 
 */
public interface AhFamilyBandTroubleService {
	
	
	/***
	 * 通过Map条件得到List集合Map的家庭宽带巡检隐患数据
	 * @param parameters  Map条件
	 * @return List<Map<String, Object>> List集合Map的家庭宽带巡检隐患数据
	 */
	public List<Map<String, Object>> searchTroublesByRecod(
			Map<String, Object> parameters);
	/***
	 * 添加家庭宽带巡检隐患数据
	 * @param entity 家庭宽带巡检隐患实体
	 * @return
	 */
	public Integer saveBandTrouble(AhFamilyBandTrouble entity);
	
	/***
	 * 删除家庭宽带巡检隐患数据
	 * @param entity 家庭宽带巡检隐患实体
	 * @return
	 */
	public Integer deleteBandTrouble(AhFamilyBandTrouble entity);

	
	/***
	 * 添加家庭宽带巡检隐患数据
	 * @param id 家庭宽带巡检隐患实体编号
	 * @return
	 */
	public AhFamilyBandTrouble getAhFamilyBandTroubleByid(String id);

	
	/***
	 * 添加家庭宽带巡检隐患数据
	 * @param userInfo 用户
	 * @return
	 */
	public String getOrderNumber(UserInfo userInfo);

	
	/***
	 * 通过家庭宽带隐患id删除家庭宽带隐患数据
	 * @param id 家庭宽带巡检隐患实体编号
	 */
	public void delete(String id);

	
	/***
	 * 保存巡检记录对应的宽带巡检隐患信息
	 * @param id 家庭宽带巡检隐患实体编号
	 * @param entity 
	 * @param userInfo 用户
	 */
	public void savebatch(String id, AhFamilyBandRecode entity,UserInfo userInfo);
	
	
	
	

}
