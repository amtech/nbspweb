package com.cabletech.business.ah.rating.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.ah.rating.dao.MobileExamDao;
import com.cabletech.business.ah.rating.service.MobileExamService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 移动考核审核确认service 实现类。
 * 
 * @author 周刚
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Service
public class MobileExamServiceImpl extends BaseServiceImpl<String, String>
		implements MobileExamService {
	/*
	 * 注入mobileExamDao
	 */
	@Resource(name = "mobileExamDao")
	private MobileExamDao mobileExamDao;

	/*
	 * 实现根据不同的值 flag来取数据库列表
	 * 
	 * @see
	 * com.cabletech.business.workflow.rating.service.MobileExamService#getPageList
	 * (com.cabletech.common.util.Page,
	 * com.cabletech.baseinfo.business.entity.UserInfo, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Page getPageList(Page<Map<String, Object>> page, UserInfo userInfo,
			String flag) {
		return mobileExamDao.getUnExamList(page, userInfo, flag);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cabletech.common.base.BaseServiceImpl#getBaseDao()
	 */
	@Override
	protected BaseDao getBaseDao() {
		return null;
	}

	/*
	 * 查询统计页面
	 * 
	 * @see com.cabletech.business.workflow.rating.service.MobileExamService#
	 * getQueryAnalysisList(com.cabletech.common.util.Page, java.util.Map)
	 */
	@Override
	public Page<Map<String, Object>> getQueryAnalysisList(
			Page<Map<String, Object>> page, Map<String, Object> parameters) {
		return mobileExamDao.getQueryAnalysisList(page, parameters);

	}

	/*
	 * 取已审核的人数
	 * 
	 * @see com.cabletech.business.workflow.rating.service.MobileExamService#
	 * getHasedCheckUserCount(java.util.Map)
	 */
	@Override
	public String getHasedCheckUserCount(Map<String, Object> parameters) {

		return mobileExamDao.getHasedCheckUserCount(parameters);
	}

	/*
	 * 取为审核的人数
	 * 
	 * @see com.cabletech.business.workflow.rating.service.MobileExamService#
	 * getNoneCheckUserCount(java.util.Map)
	 */
	@Override
	public String getNoneCheckUserCount(Map<String, Object> parameters) {
		return mobileExamDao.getNoneCheckUserCount(parameters);
	}

	/*
	 * 取代维确认的人数
	 * 
	 * @see com.cabletech.business.workflow.rating.service.MobileExamService#
	 * getDaiweiOKCount(java.util.Map)
	 */
	@Override
	public String getDaiweiOKCount(Map<String, Object> parameters) {
		return mobileExamDao.getDaiweiOKCount(parameters);
	}
}
