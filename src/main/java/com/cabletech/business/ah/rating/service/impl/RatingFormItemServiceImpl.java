package com.cabletech.business.ah.rating.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.cabletech.business.ah.rating.dao.RatingFormItemDao;
import com.cabletech.business.ah.rating.model.RatingFormItem;
import com.cabletech.business.ah.rating.model.RatingFormItemTemp;
import com.cabletech.business.ah.rating.service.RatingFormItemService;
import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 考核表子项业务服务接口实现
 * 
 * @author 杨隽 2012-06-26 创建
 * 
 */
@Service
public class RatingFormItemServiceImpl extends
		BaseServiceImpl<RatingFormItem, String> implements
		RatingFormItemService {
	@Resource(name = "ratingFormItemDao")
	private RatingFormItemDao ratingFormItemDao;
	@Resource(name = "ratingFormIdConditionGenerate")
	private ConditionGenerate conditionGenerate;

	/**
	 * 保存考核表子项信息
	 * 
	 * @param itemId
	 *            String 考核表编号
	 * @param oneCell
	 *            RatingFormItemTemp 考核表导入数据信息
	 * @throws Exception
	 */
	@Transactional
	public void save(RatingFormItemTemp oneCell, String itemId)
			throws Exception {
		RatingFormItem sub = new RatingFormItem();
		BeanUtils.copyProperties(sub, oneCell);
		sub.setItemId(itemId);
		ratingFormItemDao.save(sub);
	}

	/**
	 * 删除考核表子项
	 * 
	 * @param tableId
	 *            String 要删除的考核表子项的考核表项编号
	 */
	@Transactional
	public void delete(String tableId) {
		List<RatingFormItem> list = ratingFormItemDao.findBy("itemId", tableId);
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		for (int i = 0; i < list.size(); i++) {
			RatingFormItem ratingFormItem = list.get(i);
			ratingFormItemDao.delete(ratingFormItem);
		}
	}

	@Override
	public List<Map<String, Object>> queryListByTableId(String tableId) {
		QueryParameter parameter = new QueryParameter();
		parameter.setId(tableId);
		conditionGenerate.setQuerySql(parameter);
		return ratingFormItemDao.queryListForSql(conditionGenerate);
	}

	@Override
	protected BaseDao<RatingFormItem, String> getBaseDao() {
		return ratingFormItemDao;
	}
}