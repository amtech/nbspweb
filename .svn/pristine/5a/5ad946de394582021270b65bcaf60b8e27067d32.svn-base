package com.cabletech.business.wplan.template.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.base.BaseUtil;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.wplan.patrolitem.condition.parameter.ItemQueryParameter;
import com.cabletech.business.wplan.patrolitem.dao.PatrolItemDao;
import com.cabletech.business.wplan.patrolitem.dao.PatrolSubItemDao;
import com.cabletech.business.wplan.template.dao.WplanTemplateDao;
import com.cabletech.business.wplan.template.model.WplanTemplate;
import com.cabletech.business.wplan.template.service.WplanTemplateService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 计划模板 Service
 * 
 * @author 汪杰
 * @author 杨隽 2012-02-14 整理巡检项Dao的操作方式并重构代码
 */
@Service
public class WplanTemplateServiceImpl extends
		BaseServiceImpl<WplanTemplate, String> implements WplanTemplateService {
	@Resource(name = "wplanTemplateDao")
	private WplanTemplateDao wplanTemplateDao;
	@Resource(name = "patrolItemDao")
	private PatrolItemDao patrolItemDao;
	@Resource(name = "patrolSubItemDao")
	private PatrolSubItemDao patrolSubItemDao;
	@Autowired
	private Map<String, ConditionGenerate> conditionGenerateMap;

	/**
	 * 获取巡检项树形数据
	 * @param businessType 
	 * @param regionId 
	 * @param flag 
	 * @param templateId 
	 */
	@Transactional(readOnly = true)
	public String getPatrolItemTreddDate(String businessType, String regionId,
			String flag, String templateId) {
		ItemQueryParameter parameter = new ItemQueryParameter();
		parameter.setIsQuery(QueryParameter.IS_QUERY_PARAMETER);
		parameter.setBusinessType(businessType);
		parameter.setRegionId(regionId);
		ConditionGenerate itemConditionGenerate = conditionGenerateMap
				.get("itemConditionGenerate");
		itemConditionGenerate.setQuerySql(parameter);
		List<Map<String, Object>> items = patrolItemDao
				.queryListForSql(itemConditionGenerate);
		List<Map<String, Object>> subs = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();
		Map<String, Object> node = null;
		for (Map map : items) {
			node = new HashMap<String, Object>();
			String itemId = (String) map.get("id");
			String itemName = (String) map.get("item_name");
			node.put("pId", "root");
			node.put("id", itemId);
			node.put("name", itemName);
			if ("copy".equals(flag)) {
				ConditionGenerate itemIdAndTemplateIdConditionGenerate = conditionGenerateMap
						.get("itemIdAndTemplateIdConditionGenerate");
				parameter.setItemId(itemId);
				parameter.setTemplateId(templateId);
				itemIdAndTemplateIdConditionGenerate.setQuerySql(parameter);
				subs = patrolSubItemDao
						.queryListForSql(itemIdAndTemplateIdConditionGenerate);
			} else {
				ConditionGenerate itemIdConditionGenerate = conditionGenerateMap
						.get("itemIdConditionGenerate");
				parameter.setItemId(itemId);
				itemIdConditionGenerate.setQuerySql(parameter);
				subs = patrolSubItemDao
						.queryListForSql(itemIdConditionGenerate);
			}
			if(CollectionUtils.isNotEmpty(subs)){
				ret.add(node);
			}
			for (Map submap : subs) {
				node = new HashMap<String, Object>();
				String subItemId = (String) submap.get("id");
				String sub_name = (String) submap.get("subitem_name");
				node.put("pId", itemId);
				node.put("id", subItemId);
				node.put("name", sub_name);
				if (StringUtils.isNotBlank((String) submap.get("template_id"))) {
					node.put("checked", true);
				} else {
					node.put("checked", false);
				}
				ret.add(node);
			}
		}
		String json = BaseUtil.diversionJson(ret);
		return json;
	}

	/**
	 * 保存计划模板
	 * @param vo 
	 */
	@Transactional
	public void saveWplanTemplate(WplanTemplate vo) {
		String item = vo.getItems();
		String[] items = new String[] {};
		if (StringUtils.isNotBlank(item)) {
			items = item.split(",");
			vo.setState(SysConstant.TEMPLATE_START_USING_STATE);
			save(vo);
		}
		wplanTemplateDao.removeTemplateSubItem(vo.getId());
		for (String t : items) {
			wplanTemplateDao.saveTemplateSubItem(vo.getId(), t);
		}
	}

	/**
	 * 计划模板复制方法
	 * @param vo 
	 */
	@Transactional
	public void copyWplanTemplate(WplanTemplate vo) {
		String item = vo.getItems();
		String[] items = new String[] {};
		if (StringUtils.isNotBlank(item)) {
			items = item.split(",");
			vo.setState(SysConstant.TEMPLATE_START_USING_STATE);
			wplanTemplateDao.getSession().save(vo);
		}
		for (String t : items) {
			wplanTemplateDao.saveTemplateSubItem(vo.getId(), t);
		}
	}

	/**
	 * 启用计划模板方法
	 * @param id 
	 */
	@Transactional
	public void startUsingWplanTemplate(String id) {
		wplanTemplateDao.changeTemplateSubItemState(id,
				SysConstant.TEMPLATE_START_USING_STATE);
	}

	/**
	 * 删除计划模板方法
	 * @param id 
	 */
	@Transactional
	public void deleteWplanTemplate(String id) {
		wplanTemplateDao.changeTemplateSubItemState(id,
				SysConstant.TEMPLATE_STOP_USING_STATE);
	}

	/**
	 * 查询计划模板方法
	 * @param businessType  String
	 * @param templateName String
	 * @param user UserInfo
	 * @param page Page
	 */
	@Transactional(readOnly = true)
	public Page queryWplanTemplate(String businessType, String templateName,UserInfo user,
			Page page) {
		return wplanTemplateDao.queryWplanTemplate(businessType, templateName,user,
				page);
	}

	/**
	 * 查询计划模板方法
	 * @param businessType 
	 * @param regionid 
	 * @param state 
	 */	
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getWplanTemplate(String businessType,String regionid,
			String state) {
		return wplanTemplateDao.getWplanTemplate(businessType,regionid, state);
	}

	/**
	 * 查询指定计划模板方法
	 * @param id 
	 */	
	@Transactional(readOnly = true)
	public WplanTemplate getWplanTemplate(String id) {
		WplanTemplate vo = wplanTemplateDao.get(id);
		return vo;
	}

	/**
	 * 获取计划模板的子项
	 * @param templateId 
	 */
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getSubItemByTemplate(String templateId) {
		return wplanTemplateDao.getSubItemByTemplate(templateId);
	}

	@Override
	protected BaseDao<WplanTemplate, String> getBaseDao() {
		return wplanTemplateDao;
	}
}