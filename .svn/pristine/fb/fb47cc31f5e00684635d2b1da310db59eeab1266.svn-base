package com.cabletech.business.workflow.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cabletech.business.flowservice.util.ProMockPo;
import com.cabletech.common.base.SysConstant;

/**
 * 工作流业务操作抽象基类
 * 
 * @author 杨隽 2011-11-07 创建
 * @author 杨隽 2011-11-29 添加getBusinessTitleColumn()方法和getAccessUrl()方法
 * @author 杨隽 2011-11-29 修改mergeList()方法
 * @author 杨隽 2012-01-09 修改包名
 * @author 杨隽 2012-01-31 常量移动到SyscConstant中
 * 
 */
public abstract class WorkflowEntityManager {
	// 专业类型Map
	private Map<String, Object> businessTypeMap;
	// 待办任务列表
	private List<ProMockPo> waitHandledTaskList;

	public Map<String, Object> getBusinessTypeMap() {
		return businessTypeMap;
	}

	public void setBusinessTypeMap(Map<String, Object> businessTypeMap) {
		this.businessTypeMap = businessTypeMap;
	}

	public List<ProMockPo> getWaitHandledTaskList() {
		return waitHandledTaskList;
	}

	public void setWaitHandledTaskList(List<ProMockPo> waitHandledTaskList) {
		this.waitHandledTaskList = waitHandledTaskList;
	}

	/**
	 * 合并业务操作数据列表和待办任务列表
	 * 
	 * @param waitHandledTaskList
	 *            List<ProMockPo> 工作流待办任务列表
	 * @param businessDataList
	 *            List<Map<String, Object>> 业务数据信息列表
	 * @param processDefName
	 *            String 流程定义名称
	 * @param processCommentName
	 *            String 流程定义名称注释
	 * @return List<Map<String, Object>> 合并后的业务操作数据列表
	 */
	public List<Map<String, Object>> mergeList(
			List<ProMockPo> waitHandledTaskList,
			List<Map<String, Object>> businessDataList, String processDefName,
			String processCommentName) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		if (businessDataList == null || businessDataList.isEmpty()) {
			return resultList;
		}
		if (waitHandledTaskList == null || waitHandledTaskList.isEmpty()) {
			return resultList;
		}
		ProMockPo pro;
		Map<String, Object> map;
		String dispatchId = "";
		for (int i = 0; i < businessDataList.size(); i++) {
			map = businessDataList.get(i);
			dispatchId = (String) map.get(getBusinessIdColumn());
			if (StringUtils.isBlank(dispatchId)) {
				continue;
			}
			for (int j = 0; j < waitHandledTaskList.size(); j++) {
				pro = waitHandledTaskList.get(j);
				if (processDefName.equals(pro.getProcessName())
						&& dispatchId.equals(pro.getBzid())) {
					String url = "";
					if (StringUtils.isNotBlank(pro.getResource())) {
						url = pro.getResource().replaceAll(";", "&")
								.replaceAll("\\[piId\\]", pro.getBzid())
								.replaceAll("\\[taskId\\]", pro.getTaskId());
						url += getUrlSuffix(map);
					}
					map.put(SysConstant.PROCESS_NAME_KEY, processDefName);
					map.put(SysConstant.PROCESS_COMMENT_KEY, processCommentName);
					map.put(SysConstant.ID_COLUMN_KEY,
							map.get(getBusinessIdColumn()));
					map.put(SysConstant.TITLE_COLUMN_KEY,
							map.get(getBusinessTitleColumn()));
					map.put(SysConstant.ACCESS_URL_KEY, url);
					resultList.add(map);
					break;
				}
			}
		}
		return resultList;
	}

	/**
	 * 获取待办处理的url地址后缀
	 * 
	 * @param map
	 *            Map<String,Object>
	 * @return String
	 */
	public abstract String getUrlSuffix(Map<String, Object> map);

	/**
	 * 获取工作流待办列表访问地址
	 * 
	 * @return String 工作流待办列表访问地址
	 */
	public abstract String getAccessUrl();

	/**
	 * 获取业务数据的主键列名
	 * 
	 * @return String 业务数据的主键列名
	 */
	public abstract String getBusinessIdColumn();

	/**
	 * 获取业务数据的标题列名
	 * 
	 * @return String 业务数据的标题列名
	 */
	public abstract String getBusinessTitleColumn();

	/**
	 * 根据专业获取业务操作数据列表
	 * 
	 * @param businessType
	 *            String 专业编号
	 * @return List<Map<String, Object>> 业务操作数据列表
	 */
	public abstract List<Map<String, Object>> getBusinessDataList(
			String businessType);
}
