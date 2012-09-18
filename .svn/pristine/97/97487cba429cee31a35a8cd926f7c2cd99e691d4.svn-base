package com.cabletech.business.wplan.template.service;

import java.util.List;
import java.util.Map;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.wplan.template.model.WplanTemplate;
import com.cabletech.common.util.Page;

/**
 * 计划模板 Service
 * @author wangjie
 */
public interface WplanTemplateService {
	/**
	 * 获取巡检项树形数据
	 * @param businessType businessType
	 * @param regionId regionId
	 * @param flag flag
	 * @param templateId templateId
	 * @return
	 */
	public String getPatrolItemTreddDate(String businessType,String regionId,String flag,String templateId);
	/**
	 * 保存无线模板
	 * @param vo vo
	 */
	public void saveWplanTemplate(WplanTemplate vo);
	/**
	 * 查询无线模板
	 * @param businessType businessType
	 * @param templateName templateName
	 * @param user UserInfo
	 * @param page page
	 * @return
	 */
	public Page queryWplanTemplate(String businessType,String templateName,UserInfo user, Page page);
	/**
	 * 获取模板对象
	 * @param id id
	 * @return
	 */
	public WplanTemplate getWplanTemplate(String id );
	/**
	 * 获取模板子项
	 * @param templateId 模板ID
	 * @return
	 */
	public List<Map<String, Object>> getSubItemByTemplate(String templateId);
	/**
	 * 删除无线模板信息
	 * @param id 主键
	 */
	public void deleteWplanTemplate(String id);
	/**
	 * 启用模板
	 * @param id 主键
	 */
	public void startUsingWplanTemplate(String id);
	/**
	 * 复制模板
	 * @param vo 对象
	 */
	public void copyWplanTemplate(WplanTemplate vo);
	/**
	 * 根据条件获取所有模板
	 * @param businessType 专业类型
	 * @param regionid 区域ID
	 * @param state 使用状态
	 * @return
	 */
	public List<Map<String,Object>> getWplanTemplate(String businessType,String regionid,String state);
}
