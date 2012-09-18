package com.cabletech.business.resource.service;

import java.util.List;
import java.util.Map;

import com.cabletech.business.resource.model.ResSite;

/**
 * 坐标校正
 * 
 * @author zhaobi
 * 
 */
public interface LonLatReviseService {

	/**
	 * 获取所有资源点
	 * @param pointinfo 点信息
	 * @return
	 */
	public List<Map<String, Object>> getResourcePoint(ResSite pointinfo);

	/**
	 * 校正坐标
	 * @param pointinfo 点信息
	 */
	public void updateCoordinate(ResSite pointinfo);

	/**
	 * 获取SRID
	 * @param tablename 表名
	 * @return
	 */
	public String getSRID(String tablename);
	/**
	 * 修改资源坐标
	 * @param pointinfo 
	 */
	public void updateResoureCoordinate(ResSite pointinfo);
}
