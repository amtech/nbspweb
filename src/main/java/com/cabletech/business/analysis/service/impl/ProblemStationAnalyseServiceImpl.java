package com.cabletech.business.analysis.service.impl;

import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.analysis.dao.ProblemStationAnalyseDao;
import com.cabletech.business.analysis.service.PatrolAnalyseService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 问题站点统计分析接口实现
 * 
 * @author 杨隽 2012-07-27 创建
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Service
@Transactional(readOnly = true)
public class ProblemStationAnalyseServiceImpl extends BaseServiceImpl implements
		PatrolAnalyseService {
	/**
	 * 问题站点统计分析Dao
	 */
	@Resource(name = "problemStationAnalyseDao")
	private ProblemStationAnalyseDao problemStationAnalyseDao;

	@Override
	public void analyse(Map<String, Object> map, Page page) {
		problemStationAnalyseDao.getProblemStations(map, page);
	}

	@Override
	public void analyseDetail(Map<String, Object> map, Page page) {
		problemStationAnalyseDao.getProblemStationItems(map, page);
	}

	@Override
	protected BaseDao getBaseDao() {
		return problemStationAnalyseDao;
	}
}
