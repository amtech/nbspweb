package com.cabletech.business.wplan.patrolitem.service;

import java.util.List;
import java.util.Map;

import com.cabletech.business.wplan.patrolitem.condition.parameter.ItemQueryParameter;
import com.cabletech.business.wplan.patrolitem.model.PatrolItemTemp;
import com.cabletech.common.util.Page;

/**
 * 巡检子项业务服务接口
 * 
 * @author 杨隽 2012-02-14 创建
 * @author 杨隽 2012-02-15 添加queryList方法和exportData方法
 * @author 杨隽 2012-05-17 去除无用的导入、局部变量和类成员
 * 
 */
public interface PatrolSubItemService {
	/**
	 * 执行巡检子项的查询操作并进行后台分页
	 * 
	 * @param parameter
	 *            ItemQueryParameter 查询条件参数
	 * @return Page<Map<String, Object>> 查询后的巡检项列表
	 */
	public Page<Map<String, Object>> queryPage(ItemQueryParameter parameter);

	/**
	 * 执行巡检子项的查询操作
	 * 
	 * @param parameter
	 *            ItemQueryParameter 查询条件参数
	 * @return Page<Map<String, Object>> 查询后的巡检项列表
	 */
	public List<Map<String, Object>> queryList(ItemQueryParameter parameter);

	/**
	 * 保存巡检子项信息
	 * 
	 * @param itemId
	 *            String 巡检项编号
	 * @param oneCell
	 *            PatrolItemTemp 巡检项导入数据信息
	 * @throws Exception
	 */
	public void save(PatrolItemTemp oneCell, String itemId) throws Exception;

	/**
	 * 作废巡检项
	 * 
	 * @param ids
	 *            String[] 要作废的巡检项id数组
	 */
	public void deleteLogic(String[] ids);

	/**
	 * 启用巡检项
	 * 
	 * @param ids
	 *            String[] 要启用的巡检项id数组
	 */
	public void startUsing(String[] ids);

}
