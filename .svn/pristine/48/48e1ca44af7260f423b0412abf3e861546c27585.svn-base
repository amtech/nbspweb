package com.cabletech.business.webservice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import com.cabletech.baseinfo.business.Service.BaseInfoProvider;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.service.PatrolGroupService;
import com.cabletech.business.base.service.UserInfoService;
import com.cabletech.business.webservice.interfaces.ExternalWebService;
import com.cabletech.business.webservice.service.BusinessService;
import com.cabletech.business.workflow.workorder.service.WorkOrderWorkflowWaitHandledService;
import com.cabletech.common.util.Page;
import com.cabletech.common.util.ReflectionUtils;
import com.cabletech.common.xmlparse.ParseXmlTools;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 业务处理方法接口（用户登录服务）
 * 
 * @author 杨隽 2012-08-16 创建
 * 
 */
@Component
public class UserWebServiceImpl implements BusinessService {
	/**
	 * 日志输出
	 */
	private Logger logger = Logger.getLogger("UserWebServiceImpl");
	/**
	 * 工单待办服务
	 */
	@Resource(name = "workOrderWorkflowWaitHandledService")
	private WorkOrderWorkflowWaitHandledService workflowService;
	/**
	 * 用户信息服务
	 */
	@Resource(name = "userInfoServiceImpl")
	private UserInfoService userInfoService;
	/**
	 * 巡检组信息服务
	 */
	@Resource(name = "patrolGroupServiceImpl")
	private PatrolGroupService patrolGroupService;
	/**
	 * 基础业务服务
	 */
	@Resource(name = "baseInfoProvider")
	private BaseInfoProvider baseInfoProvider;

	@SuppressWarnings("unchecked")
	@Override
	public String doBusiness(String cmd, Map<String, Object> parameter,
			List<Element> props) {
		String methodName = getMethodName(cmd);
		Map<String, Object> valueMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(methodName)) {
			try {
				valueMap = (Map<String, Object>) ReflectionUtils.invokeMethod(
						this, methodName,
						new Class[] { Map.class, List.class }, new Object[] {
								parameter, props });
			} catch (Exception ex) {
				logger.error("接口异常：", ex);
				valueMap.put("code", ExternalWebService.EXCEPTION_CODE);
			}
		} else {
			valueMap.put("code", ExternalWebService.NO_CMD_ERROR_CODE);
		}
		valueMap.put("cmd", cmd);
		String datafomat = "yyyy-MM-dd HH:mm:ss";
		Gson gson = new GsonBuilder().setDateFormat(datafomat).create();
		String value = gson.toJson(valueMap);
		logger.info(cmd + "::" + value);
		return value;
	}

	/**
	 * 获取登录用户信息
	 * 
	 * @param map
	 *            Map<String,Object>
	 * @param props
	 *            List<Element>
	 * @return Map<String,Object>
	 */
	public Map<String, Object> getLoginUserInfo(Map<String, Object> map,
			List<Element> props) {
		if (CollectionUtils.isEmpty(props)) {
			return null;
		}
		Map<String, Object> valueMap = new HashMap<String, Object>();
		String[] parameters = getInputParameters(map, props);
		UserInfo user = baseInfoProvider.getLoginUserService()
				.getUserInfoByUserId(parameters[0]);
		if (user == null) {
			valueMap.put("code", ExternalWebService.NO_USER_ERROR_CODE);
			return valueMap;
		}
		if (!user.getPassword().equals(parameters[1])) {
			valueMap.put("code", ExternalWebService.PASSWORD_ERROR_CODE);
			return valueMap;
		}
		if (CollectionUtils.isEmpty(user.getRoles())) {
			valueMap.put("code", ExternalWebService.NO_POWER_ERROR_CODE);
			return valueMap;
		}
		List<Map<String, Object>> businessTypeList = user.getBusinessTypes();
		workflowService.setBusinessTypeList(businessTypeList);
		List<Map<String, Object>> list = workflowService
				.getOneWorkflowHandledTaskList(businessTypeList, user);
		valueMap.put("code", ExternalWebService.SUCCESS_CODE);
		valueMap.put("userName", user.getUserName());
		valueMap.put("deptName", user.getOrgName());
		valueMap.put("headshipName", user.getStation());
		valueMap.put("value", processPage(user.getRoles()));
		if (CollectionUtils.isEmpty(list)) {
			valueMap.put("taskNum", "0");
		} else {
			valueMap.put("taskNum", Integer.toString(list.size()));
		}
		valueMap.put("total", user.getRoles().size());
		return valueMap;
	}

	/**
	 * 获取用户信息列表
	 * 
	 * @param map
	 *            Map<String, Object>
	 * @param props
	 *            List<Element>
	 * @return Map<String, Object>
	 */
	public Map<String, Object> getUserInfoList(Map<String, Object> map,
			List<Element> props) {
		if (CollectionUtils.isEmpty(props)) {
			return null;
		}
		Map<String, Object> valueMap = getValueMap(map, props, "user");
		return valueMap;
	}

	/**
	 * 获取巡检组信息列表
	 * 
	 * @param map
	 *            Map<String, Object>
	 * @param props
	 *            List<Element>
	 * @return Map<String, Object>
	 */
	public Map<String, Object> getPatrolGroupInfoList(Map<String, Object> map,
			List<Element> props) {
		if (CollectionUtils.isEmpty(props)) {
			return null;
		}
		Map<String, Object> valueMap = getValueMap(map, props, "patrolgroup");
		return valueMap;
	}

	/**
	 * 根据不同命令获取不同的调用方法名称
	 * 
	 * @param cmd
	 *            String
	 * @return String
	 */
	private String getMethodName(String cmd) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("wlblogin", "getLoginUserInfo");
		map.put("wlbuserinfo", "getUserInfoList");
		map.put("wlbpatrolGroupList", "getPatrolGroupInfoList");
		return map.get(cmd);
	}

	/**
	 * 获取列表的返回Map
	 * 
	 * @param map
	 *            Map<String, Object>
	 * @param props
	 *            List<Element>
	 * @param type
	 *            String
	 * @return Map<String, Object>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map<String, Object> getValueMap(Map<String, Object> map,
			List<Element> props, String type) {
		String[] parameters = getInputParameters(map, props);
		Map<String, Object> valueMap = new HashMap<String, Object>();
		if (parameters.length < 4) {
			valueMap.put("code", ExternalWebService.PARAMETER_ERROR_CODE);
			return valueMap;
		}
		UserInfo user = userInfoService.getUserInfoByUserId(parameters[0]);
		Page page = new Page();
		page.setPageNo(Integer.parseInt(parameters[1]));
		page.setPageSize(Integer.parseInt(parameters[2]));
		if ("user".equals(type)) {
			userInfoService.getUserInfoList(parameters[3], user, page);
		}
		if ("patrolgroup".equals(type)) {
			patrolGroupService.getPatrolGroupList(parameters[3], user, page);
		}
		List<Map<String, Object>> result = processPage(page.getResult());
		if (CollectionUtils.isNotEmpty(result)) {
			page.setResult(result);
		}
		if (page.getTotalCount() == 0) {
			valueMap.put("code", ExternalWebService.NO_DATA_LIST_CODE);
		} else {
			valueMap.put("code", ExternalWebService.SUCCESS_CODE);
			valueMap.put("value", page.getResult());
		}
		valueMap.put("total", page.getTotalCount());
		valueMap.put("totalpage", page.getTotalPages());
		return valueMap;
	}

	/**
	 * 获取输入的参数信息
	 * 
	 * @param map
	 *            Map<String, Object>
	 * @param props
	 *            List<Element>
	 * @return String[]
	 */
	private String[] getInputParameters(Map<String, Object> map,
			List<Element> props) {
		String[] parameters = new String[props.size()];
		for (int i = 0; i < props.size(); i++) {
			Element e = props.get(i);
			String key = e.attributeValue(ParseXmlTools.ID_ATTRBUTE_KEY);
			parameters[i] = (String) map.get(key);
		}
		return parameters;
	}

	/**
	 * 处理页面列表数据
	 * 
	 * @param list
	 *            List<Map<String, Object>>
	 * @return List<Map<String, Object>>
	 */
	private List<Map<String, Object>> processPage(List<Map<String, Object>> list) {
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> valueMap = new HashMap<String, Object>();
			Map<String, Object> map = list.get(i);
			Set<String> keySet = map.keySet();
			Iterator<String> it = keySet.iterator();
			if (it == null) {
				continue;
			}
			while (it.hasNext()) {
				String key = it.next();
				Object value = map.get(key);
				key = key.toLowerCase();
				char firstChar = key.charAt(0);
				key = WordUtils.capitalize(key, new char[] { '_' });
				key = firstChar + key.replaceAll("_", "").substring(1);
				valueMap.put(key, value);
			}
			result.add(valueMap);
		}
		return result;
	}
}
