package com.cabletech.business.satisfy.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.satisfy.dao.SatisfyDao;
import com.cabletech.business.satisfy.model.Satisfaction;
import com.cabletech.business.satisfy.service.SatisfactionListService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 客户满意度评价查询业务处理接口实现
 * 
 * @author 杨隽 2012-04-21 创建
 * 
 */
@SuppressWarnings("rawtypes")
@Service
public class SatisfactionListServiceImpl extends BaseServiceImpl implements
		SatisfactionListService {
	// 客户满意度评价Dao
	@Resource(name = "satisfyDao")
	private SatisfyDao satisfyDao;
	// 客户满意度评价查询条件生成器
	@Resource(name = "satisfactionConditionGenerateImpl")
	private ConditionGenerate conditionGenerate;

	@Override
	protected BaseDao getBaseDao() {
		return satisfyDao;
	}

	/**
	 * 获取客户满意度评价查询列表
	 * 
	 * @param satisfaction
	 *            Satisfaction 查询条件的客户满意度评价实体
	 * @param userInfo
	 *            UserInfo 当前登录用户
	 * @return Page 客户满意度评价查询列表
	 */
	@Transactional(readOnly = true)
	public Page getSatisfactionList(Satisfaction satisfaction, UserInfo userInfo) {
		QueryParameter parameter = new QueryParameter();
		parameter.setUser(userInfo);
		parameter.setEntity(satisfaction);
		conditionGenerate.setQuerySql(parameter);
		Page page = satisfyDao.queryPageForSql(conditionGenerate);
		return page;
	}
}
