package com.cabletech.business.wplan.patrolitem.service;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.cabletech.business.wplan.patrolitem.model.PatrolItemTemp;

/**
 * 巡检项导入数据业务服务接口
 * 
 * @author wangjie
 * @author 杨隽 2011-10-25 添加启用巡检项目方法
 * @author 杨隽 2012-02-13 分离巡检项导入业务和巡检项基本操作业务
 * 
 */
public interface PatrolItemImportService {
	/**
	 * 根据要导入的Excel文件生成预览数据
	 * 
	 * @param filePath
	 *            String 导入的巡检报表文件路径信息
	 * @throws Exception
	 */
	public List<PatrolItemTemp> createItemPreviewData(String filePath);

	/**
	 * 导入Excel数据
	 * 
	 * @param parameterMap
	 *            Map<String,String>
	 *            导入Excel的参数：file_path->导入的巡检报表文件路径信息，business_type
	 *            ->专业类型，region_id->区域编号
	 * @return Map 导入的结果Map
	 */
	@SuppressWarnings("rawtypes")
	public Map importItemData(Map<String, String> parameterMap);

	/**
	 * 导出出错的导入巡检项数据
	 * 
	 * @param out
	 *            OutputStream 输出流
	 * @param list
	 *            List 出错的导入巡检项数据列表
	 */
	@SuppressWarnings("rawtypes")
	public void exportInvalidItemData(OutputStream out, List list);
}
