package com.cabletech.business.wplan.patrolitem.service;

import java.util.Map;

import com.cabletech.business.wplan.patrolitem.model.PatrolItemTemp;

/**
 * 巡检项业务服务接口
 * 
 * @author wangjie
 * @author 杨隽 2011-10-25 添加启用巡检项目方法
 * @author 杨隽 2012-02-13 分离巡检项导入业务和巡检项基本操作业务
 * @author 杨隽 2012-02-14 修改query方法为queryForList方法并修改输入参数和返回参数
 * @author 杨隽 2012-02-14 添加save方法并去除queryForList、deleteLogic、startUsing方法
 * 
 */
public interface PatrolItemService {
	/**
	 * 保存巡检项和巡检子项信息
	 * 
	 * @param parameterMap
	 *            Map<String,String> 传入的参数
	 * @param oneCellTemp
	 *            PatrolItemTemp 巡检项导入数据信息
	 * @throws Exception
	 */
	public void save(Map<String, String> parameterMap,
			PatrolItemTemp oneCellTemp) throws Exception;
}
