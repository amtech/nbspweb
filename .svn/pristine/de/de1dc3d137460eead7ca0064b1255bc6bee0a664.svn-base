package com.cabletech.business.workflow.accident.service.impl;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cabletech.baseinfo.business.Service.BaseInfoProvider;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.base.service.UserInfoService;
import com.cabletech.business.resource.model.ResourceInfo;
import com.cabletech.business.resource.service.ResourceService;
import com.cabletech.business.workflow.accident.dao.MmAccidentBaseDao;
import com.cabletech.business.workflow.accident.model.MmAccident;
import com.cabletech.business.workflow.accident.model.MmAccidentType;
import com.cabletech.business.workflow.accident.service.MmAccidentTypeService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 隐患业务接口实现基类
 * 
 * @author 杨隽 2012-08-27 创建
 * 
 * @param <T>
 * @param <PK>
 */
@SuppressWarnings("all")
public abstract class MmAccidentBaseServiceImpl<T, PK extends Serializable>
		extends BaseServiceImpl<T, PK> {
	// “查询条件生成器KEY”常量
	// “获取待处理隐患列表查询条件生成器KEY”
	protected static final String WAIT_PROCESS_ACCIDENT_CONDITION_GENERATE_KEY = "waitProcessAccidentConditionGenerateImpl";
	// 用户信息业务处理
	@Resource(name = "userInfoServiceImpl")
	private UserInfoService userInfoService;
	// 基础信息业务处理服务
	@Resource(name = "baseInfoProvider")
	private BaseInfoProvider baseInfoProvider;
	// 隐患类型业务处理
	@Resource(name = "mmAccidentTypeServiceImpl")
	private MmAccidentTypeService mmAccidentTypeService;
	// 资源业务处理
	@Resource(name = "resourceServiceImpl")
	private ResourceService resourceService;
	// 查询条件生成器业务处理Map
	@Autowired
	private Map<String, ConditionGenerate> conditionGenerateMap;

	/**
	 * 获取查询条件生成器并将查询条件参数传递到其中
	 * 
	 * @param key
	 *            String 查询条件生成器Key
	 * @param parameter
	 *            QueryParameter 查询参数
	 * @return ConditionGenerate 查询条件生成器
	 */
	protected ConditionGenerate getConditionGenerate(String key,
			QueryParameter parameter) {
		ConditionGenerate conditionGenerate = conditionGenerateMap.get(key);
		conditionGenerate.setQuerySql(parameter);
		return conditionGenerate;
	}

	/**
	 * 根据用户的输入信息和登录信息设置查询条件参数
	 * 
	 * @param accident
	 *            MmAccident 用户的输入信息的隐患实体
	 * @param userInfo
	 *            UserInfo 当前用户信息
	 * @return QueryParameter 查询条件参数
	 */
	protected QueryParameter setQueryParameter(MmAccident accident,
			UserInfo userInfo) {
		QueryParameter parameter = new QueryParameter();
		parameter.setUser(userInfo);
		parameter.setEntity(accident);
		return parameter;
	}

	/**
	 * @return BaseDao<T, PK> 工单处理操作Dao
	 */
	@Override
	protected BaseDao<T, PK> getBaseDao() {
		return getAccidentBaseDao();
	}

	/**
	 * 根据用户编号获取用户联系电话
	 * 
	 * @param userId
	 *            String 用户编号
	 * @return String 用户联系电话
	 */
	protected String getUserPhone(String userId) {
		String phone = "";
		UserInfo user = userInfoService.getUserInfoByPersonId(userId);
		if (user != null) {
			phone = user.getPhone();
			if (StringUtils.isBlank(phone)) {
				phone = user.getMobile();
			}
		}
		return phone;
	}

	/**
	 * 根据用户编号获取用户姓名
	 * 
	 * @param userId
	 *            String 用户编号
	 * @return String 用户姓名
	 */
	protected String getUserName(String userId) {
		String userName = "";
		UserInfo user = userInfoService.getUserInfoByPersonId(userId);
		if (user != null) {
			userName = user.getUserName();
		}
		return userName;
	}

	/**
	 * 根据资源编号获取资源名称
	 * 
	 * @param resourceId
	 *            String 资源编号
	 * @return String 用户姓名
	 */
	protected String getResourceName(String resourceId) {
		String resourceName = "";
		ResourceInfo resource = resourceService.viewResourceInfo(resourceId);
		if (resource != null) {
			resourceName = resource.getResourceName();
		}
		return resourceName;
	}

	/**
	 * 根据隐患类型编号获取隐患类型名称
	 * 
	 * @param accidentTypeId
	 *            String 隐患类型编号
	 * @return String 隐患类型名称
	 */
	protected String getAccidentTypeName(String accidentTypeId) {
		String accidentTypeName = "";
		MmAccidentType accidentType = mmAccidentTypeService
				.viewAccidentType(accidentTypeId);
		if (accidentType != null) {
			accidentTypeName = accidentType.getTypeName();
		}
		return accidentTypeName;
	}

	/**
	 * 根据巡检组编号获取巡检组名称
	 * 
	 * @param patrolGroupId
	 *            String 巡检组编号
	 * @return String 巡检组名称
	 */
	@SuppressWarnings("deprecation")
	public String getPatrolGroupName(String patrolGroupId) {
		String patrolGroupName = "";
		Map<String, Object> patrolGroupInfo = baseInfoProvider
				.getPatrolmanMap(patrolGroupId);
		if (MapUtils.isNotEmpty(patrolGroupInfo)) {
			patrolGroupName = (String) patrolGroupInfo.get("patrolname");
		}
		return patrolGroupName;
	}

	/**
	 * 获取隐患处理操作Dao
	 * 
	 * @return AccidentBaseDao<T, PK> 隐患处理操作Dao
	 */
	protected abstract MmAccidentBaseDao<T, PK> getAccidentBaseDao();
}
