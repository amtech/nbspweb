package com.cabletech.business.wplan.patrolitem.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.wplan.patrolitem.condition.parameter.ItemQueryParameter;
import com.cabletech.business.wplan.patrolitem.dao.PatrolSubItemDao;
import com.cabletech.business.wplan.patrolitem.model.PatrolItem;
import com.cabletech.business.wplan.patrolitem.model.PatrolItemTemp;
import com.cabletech.business.wplan.patrolitem.model.PatrolSubItem;
import com.cabletech.business.wplan.patrolitem.service.PatrolSubItemService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 巡检子项业务服务接口实现
 * 
 * @author 杨隽 2012-02-14 创建
 * @author 杨隽 2012-02-15 添加queryList方法和exportData方法
 * @author 杨隽 2012-02-24 修改exportData()方法
 * @author 杨隽 2012-05-17 去除无用的导入、局部变量和类成员
 * 
 */
@Service
public class PatrolSubItemServiceImpl extends
		BaseServiceImpl<PatrolSubItem, String> implements PatrolSubItemService {
	@Resource(name = "patrolSubItemDao")
	private PatrolSubItemDao patrolSubItemDao;
	@Resource(name = "itemConditionGenerate")
	private ConditionGenerate conditionGenerate;

	/**
	 * 执行巡检项的查询操作并进行后台分页
	 * 
	 * @param parameter
	 *            ItemQueryParameter 查询条件参数
	 * @return List<Map<String, Object>> 查询后的巡检项列表
	 */
	@Transactional(readOnly = true)
	public Page<Map<String, Object>> queryPage(ItemQueryParameter parameter) {
		setQueryParameter(parameter);
		return patrolSubItemDao.queryPageForSql(conditionGenerate);
	}

	/**
	 * 执行巡检子项的查询操作
	 * 
	 * @param parameter
	 *            ItemQueryParameter 查询条件参数
	 * @return Page<Map<String, Object>> 查询后的巡检项列表
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> queryList(ItemQueryParameter parameter) {
		// TODO Auto-generated method stub
		setQueryParameter(parameter);
		return patrolSubItemDao.queryListForSql(conditionGenerate);
	}

	/**
	 * 保存巡检子项信息
	 * 
	 * @param itemId
	 *            String 巡检项编号
	 * @param oneCell
	 *            PatrolItemTemp 巡检项导入数据信息
	 * @throws Exception
	 */
	@Transactional
	public void save(PatrolItemTemp oneCell, String itemId) throws Exception {
		PatrolSubItem sub = new PatrolSubItem();
		BeanUtils.copyProperties(sub, oneCell);
		sub.setItemId(itemId);
		sub.setState(PatrolItem.ITEM_START_USING_STATE);
		patrolSubItemDao.save(sub);
	}

	/**
	 * 作废巡检项
	 * 
	 * @param ids
	 *            String[] 要作废的巡检项id数组
	 */
	@Transactional
	public void deleteLogic(String[] ids) {
		String itemIds = getItemIds(ids);
		patrolSubItemDao.deleteLogic(itemIds);
	}

	/**
	 * 启用巡检项
	 * 
	 * @param ids
	 *            String[] 要启用的巡检项id数组
	 */
	@Transactional
	public void startUsing(String[] ids) {
		String itemIds = getItemIds(ids);
		patrolSubItemDao.startUsing(itemIds);
	}

	/**
	 * 将巡检项数组编号拼接成巡检项编号字串
	 * 
	 * @param ids
	 *            String[] 巡检项数组编号
	 * @return String 巡检项编号字串
	 */
	private String getItemIds(String[] ids) {
		String itemIds = "";
		for (int i = 0; i < ids.length; i++) {
			if (i > 0) {
				itemIds = itemIds + ",";
			}
			itemIds = itemIds + "'" + ids[i] + "'";
		}
		return itemIds;
	}

	/**
	 * 设置条件生成器的查询条件参数和分页参数
	 * 
	 * @param parameter
	 *            ItemQueryParameter 查询条件参数
	 */
	private void setQueryParameter(ItemQueryParameter parameter) {
		ItemQueryParameter queryParameter = new ItemQueryParameter();
		try {
			BeanUtils.copyProperties(queryParameter, parameter);
		} catch (Exception ex) {
		}
		conditionGenerate.setQuerySql(queryParameter);
		conditionGenerate.setPage(parameter.getPage());
	}

	@Override
	protected BaseDao<PatrolSubItem, String> getBaseDao() {
		// TODO Auto-generated method stub
		return patrolSubItemDao;
	}
}