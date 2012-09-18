package com.cabletech.business.workflow.electricity.security.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.baseinfo.excel.ExportSupport;
import com.cabletech.business.workflow.electricity.security.dao.ElectricitySecurityBaseDao;
import com.cabletech.business.workflow.electricity.security.model.OeDispatchTask;
import com.cabletech.business.workflow.electricity.security.model.OeOutageAlarm;
import com.cabletech.business.workflow.electricity.security.service.OeOutageAlarmService;
import com.cabletech.common.util.Page;

/**
 * 断电告警业务操作接口实现
 * 
 * @author 杨隽 2012-05-03 创建
 * @author 杨隽 2012-05-17 去除无用的导入、局部变量和类成员
 * 
 */
@Service
@Transactional
public class OeOutageAlarmServiceImpl extends
		ElectricitySecurityBaseServiceImpl<OeOutageAlarm, String> implements
		OeOutageAlarmService, ExportSupport {
	/**
	 * 断电告警信息DAO
	 */
	@Resource(name = "oeOutageAlarmDao")
	private ElectricitySecurityBaseDao<OeOutageAlarm, String> oeOutageAlarmDao;

	/**
	 * 根据断电告警单编号查看断电告警单信息
	 * 
	 * @param id
	 *            String 断电告警单编号
	 * @return OeOutageAlarm 断电告警单信息
	 */
	@Transactional(readOnly = true)
	@Override
	public OeOutageAlarm viewOeOutageAlarm(String id) {
		OeOutageAlarm oeOutageAlarm = oeOutageAlarmDao.get(id);
		String userName = super.getUserName(oeOutageAlarm.getHandler());
		oeOutageAlarm.setHandlerName(userName);
		return oeOutageAlarm;
	}

	/**
	 * 复制断电告警单信息到断电告警发电信息中
	 * 
	 * @param oeOutageAlarm
	 *            OeOutageAlarm 断电告警单信息
	 * @return OeDispatchTask 断电告警发电信息
	 */
	public OeDispatchTask cloneAlarmToDispatchTask(OeOutageAlarm oeOutageAlarm) {
		OeDispatchTask oeDispatchTask = new OeDispatchTask();
		try {
			BeanUtils.copyProperties(oeDispatchTask, oeOutageAlarm);
			oeDispatchTask.setId("");
			oeDispatchTask.setAlarmId(oeOutageAlarm.getId());
			String resourceName = super.getResourceName(oeDispatchTask);
			oeDispatchTask.setStationName(resourceName);
			String resourceAddress = super.getResourceAddress(oeDispatchTask);
			oeDispatchTask.setStationAddress(resourceAddress);
		} catch (Exception ex) {
			logger.error("", ex);
		}
		return oeDispatchTask;
	}

	/**
	 * 忽略断电告警单
	 * 
	 * @param id
	 *            String 断电告警单编号
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 */
	@Override
	public void ignore(String id, UserInfo userInfo) {
		OeOutageAlarm oeOutageAlarm = oeOutageAlarmDao.get(id);
		oeOutageAlarm.setState(OeOutageAlarm.IGNORED_STATE);
		oeOutageAlarm.setHandler(userInfo.getPersonId());
		oeOutageAlarm.setIgnoreTime(new Date());
		oeOutageAlarmDao.save(oeOutageAlarm);
	}

	/**
	 * 根据查询条件获取断电告警单分页列表
	 * 
	 * @param oeOutageAlarm
	 *            OeOutageAlarm 查询条件参数
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 * @return Page 断电告警单分页列表
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("rawtypes")
	@Override
	public Page getList(OeOutageAlarm oeOutageAlarm, UserInfo userInfo) {
		String key = LIST_CONDITION_GENERATE_KEY;
		OeDispatchTask oeDispatchTask = new OeDispatchTask();
		try {
			BeanUtils.copyProperties(oeDispatchTask, oeOutageAlarm);
		} catch (Exception e) {
			logger.error("", e);
		}
		return super.getOeDispatchTaskList(oeDispatchTask, key);
	}

	/**
	 * 根据查询条件获取未派单断电告警单分页列表
	 * 
	 * @param oeOutageAlarm
	 *            OeOutageAlarm 查询条件参数
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 * @return Page 未派单断电告警单分页列表
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("rawtypes")
	@Override
	public Page getUnDispatchedList(OeOutageAlarm oeOutageAlarm,
			UserInfo userInfo) {
		String key = UNDISPATCHED_LIST_CONDITION_GENERATE_KEY;
		OeDispatchTask oeDispatchTask = new OeDispatchTask();
		try {
			BeanUtils.copyProperties(oeDispatchTask, oeOutageAlarm);
		} catch (Exception e) {
			logger.error("", e);
		}
		return super.getOeDispatchTaskList(oeDispatchTask, key);
	}

	/**
	 * 获取业务处理操作Dao
	 * 
	 * @return ElectricitySafeBaseDao<T, PK> 业务操作Dao
	 */
	@Override
	protected ElectricitySecurityBaseDao<OeOutageAlarm, String> getElectricitySecurityBaseDao() {
		return oeOutageAlarmDao;
	}
}
