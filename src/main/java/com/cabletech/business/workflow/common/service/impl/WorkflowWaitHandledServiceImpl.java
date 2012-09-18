package com.cabletech.business.workflow.common.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.desktop.service.MyWorkService;
import com.cabletech.business.desktop.service.impl.DesktopServiceImpl;
import com.cabletech.business.workflow.common.dao.WaitHandledWorkDao;
import com.cabletech.business.workflow.common.service.AbstractWorkflowWaitHandledService;
import com.cabletech.business.workflow.common.service.WorkflowWaitHandledService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.base.SysConstant;

/**
 * 工作流待办列表数量业务处理类接口实现
 * 
 * @author 杨隽 2012-03-13 创建
 * 
 */
@Service
@SuppressWarnings("rawtypes")
public class WorkflowWaitHandledServiceImpl extends BaseServiceImpl implements
		WorkflowWaitHandledService {
	/**
	 * 首页待办数量获取dao
	 */
	@Resource(name = "waitHandledWorkDao")
	private WaitHandledWorkDao waitHandledWorkDao;
	/**
	 * “我的工作”业务处理服务
	 */
	@Resource(name = "myWorkServiceImpl")
	private MyWorkService myWorkService;

	/**
	 * 根据当前登录用户获取待办工作数量
	 * 
	 * @param userInfo
	 *            UserInfo 当前登录用户
	 * @return int 待办工作数量
	 */
	@Override
	public int getWorkflowWaitHandledNumber(UserInfo userInfo) {
		// TODO Auto-generated method stub
		// return waitHandledWorkDao.getWaitHandledWorkNumber(userInfo);
		Map<String, Object> map = myWorkService
				.getWaitHandledTasksNumber(userInfo);
		if (MapUtils.isEmpty(map)) {
			return 0;
		}
		Set<String> keySet = map.keySet();
		if (CollectionUtils.isEmpty(keySet)) {
			return 0;
		}
		int num = getWaitHandledNumberFromMap(map);
		return num;
	}

	/**
	 * 根据当前登录用户获取待办工作map
	 * 
	 * @param userInfo
	 *            UserInfo 当前登录用户
	 * @return Map<String,Object> 待办工作map
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getWorkflowWaitHandledNumberMap(UserInfo userInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = waitHandledWorkDao.getWaitHandledWorkNumberList(userInfo);
		String key = AbstractWorkflowWaitHandledService.getMapKey(SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C30);
		Map<String, Object> valueMap = new HashMap<String, Object>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> resultMap = list.get(i);
			Map<String, Object> numberMap = new HashMap<String, Object>();
			String moduleKey = getProcessDefineName(resultMap.get("deploymentkey"));
			if (StringUtils.isNotBlank(moduleKey)) {
				if (valueMap.containsKey(moduleKey)) {
					numberMap = (Map<String, Object>) valueMap.get(moduleKey);
				}
			}
			int waitHandleNumber = 0;
			if (MapUtils.isNotEmpty(numberMap)) {
				waitHandleNumber = Integer.parseInt((String) numberMap.get(DesktopServiceImpl.WAIT_HANDLED_NUMBER));
			}
			waitHandleNumber += Integer.parseInt((String) resultMap.get("wait_handled_num"));
			numberMap.put(DesktopServiceImpl.WAIT_HANDLED_NUMBER,Integer.toString(waitHandleNumber));
			if (StringUtils.isNotBlank(moduleKey)) {
				valueMap.put(moduleKey, numberMap);
			}
		}
		map.put(key, valueMap);
		return map;
	}

	/**
	 * 累加获取的待办数量
	 * 
	 * @param map
	 *            Map<String,Object> 待办数量存放Map
	 * @return int 待办数量
	 */
	@SuppressWarnings("unchecked")
	private int getWaitHandledNumberFromMap(Map<String, Object> map) {
		int num = 0;
		Set<String> keySet = map.keySet();
		Iterator<String> keyIt = keySet.iterator();
		while (keyIt.hasNext()) {
			Map<String, Object> valueMap = (Map<String, Object>) map.get(keyIt
					.next());
			Set<String> valueKeySet = valueMap.keySet();
			if (CollectionUtils.isEmpty(valueKeySet)) {
				continue;
			}
			Iterator<String> valueKeyIt = valueKeySet.iterator();
			while (valueKeyIt.hasNext()) {
				Object obj = valueMap.get(valueKeyIt.next());
				if (obj instanceof Map && obj != null) {
					String number = (String) ((Map) obj)
							.get(DesktopServiceImpl.WAIT_HANDLED_NUMBER);
					num += Integer.parseInt(number);
				}
			}
		}
		return num;
	}

	/**
	 * 根据工作流定义id获取工作流名称
	 * 
	 * @param key
	 *            Object 工作流定义id
	 * @return String 工作流名称
	 */
	private String getProcessDefineName(Object key) {
		// TODO Auto-generated method stub
		Map<String, String> processDefineMap = new HashMap<String, String>();
		processDefineMap.put("acceptance", "验收交维");
		processDefineMap.put("acceptancesubflow", "验收交维");
		processDefineMap.put("dispatchtask", "任务派单");
		processDefineMap.put("drill", "演练管理");
		processDefineMap.put("drill-sub-workflow", "演练管理");
		processDefineMap.put("hiddanger", "隐患盯防");
		processDefineMap.put("linechange", "割接管理");
		processDefineMap.put("maintence", "技术维护");
		processDefineMap.put("material", "材料管理");
		processDefineMap.put("overhaul", "大修项目");
		processDefineMap.put("overhaulsubflow", "大修项目");
		processDefineMap.put("project", "工程管理");
		processDefineMap.put("safeguard", "保障管理");
		processDefineMap.put("safeguard-sub", "保障管理");
		processDefineMap.put("safeguard-replan-sub", "保障管理");
		processDefineMap.put("trouble", "故障管理");
		return processDefineMap.get(key);
	}

	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return waitHandledWorkDao;
	}
}
