package com.cabletech.business.resource.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.resource.dao.ResourceAllotDao;
import com.cabletech.business.resource.model.ResourceAllotForm;
import com.cabletech.business.resource.service.ResourceAllotService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 资源分配
 * 
 * @author wj
 * @author 杨隽 2012-07-20
 *         删除queryRelatingContractor()、queryRelatingPatrolman()和saveRsMaintenance
 *         ()方法
 * @author 杨隽 2012-07-20 改名queryResourcesString()和queryResourceList()方法
 * @author 杨隽 2012-07-20 添加recycleResources()和allotResources()方法
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Service
@Transactional
public class ResourceAllotServiceImpl extends BaseServiceImpl implements
		ResourceAllotService {
	/**
	 * 资源分配Dao
	 */
	@Resource(name = "resourceAllotDao")
	private ResourceAllotDao resourceAllotDao;

	/**
	 * 根据查询条件获取所有未分配的资源
	 * 
	 * @param form
	 *            ResourceAllotForm 检索条件
	 * @return string
	 */
	@Transactional(readOnly = true)
	public List<Map<String, Object>> queryResourcesString(ResourceAllotForm form) {
		List<Map<String, Object>> ls = resourceAllotDao.queryResourceList(form);
		return ls;
	}

	/**
	 * 根据查询条件获取所有待确认的资源
	 * 
	 * @param form
	 *            ResourceAllotForm 检索条件
	 * @return list
	 */
	@Transactional(readOnly = true)
	public List<Map<String, Object>> queryResourceList(ResourceAllotForm form) {
		return resourceAllotDao.queryConfirmResourceList(form);
	}

	@Override
	public void allotResources(ResourceAllotForm form, UserInfo user) {
		if (user.isContractor()) {
			resourceAllotDao.updateRsMaintenance(form);
		} else {
			if (!form.isManyMaintenanced()) {
				resourceAllotDao.deleteRsMaintenance(form,
						form.isManyMaintenanced());
			}
			resourceAllotDao.insertRsMaintenance(form);
		}
	}

	@Override
	public void recycleResources(ResourceAllotForm form, UserInfo user) {
		if (user.isContractor()) {
			resourceAllotDao.updateRsMaintenance(form);
		} else {
			resourceAllotDao.deleteRsMaintenance(form,
					form.isManyMaintenanced());
		}
	}

	@Override
	public String isAllotedToSelf(ResourceAllotForm parameter) {
		List<Map<String, Object>> list = resourceAllotDao
				.getAllotedSelfResources(parameter);
		if (CollectionUtils.isEmpty(list)) {
			return "";
		}
		String resourceName = "";
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			resourceName += map.get("ZYMC");
			if (i < list.size() - 1) {
				resourceName += ",";
			}
		}
		return resourceName;
	}

	protected BaseDao getBaseDao() {
		return resourceAllotDao;
	}
}