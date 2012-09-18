package com.cabletech.business.monthcost.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.monthcost.dao.MonthCheckCostDao;
import com.cabletech.business.monthcost.dao.MonthOtherCostDao;
import com.cabletech.business.monthcost.dao.MonthTimesCostDao;
import com.cabletech.business.monthcost.service.MonthCostStaticService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 
 * @author Administrator
 *
 */
@Service
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MonthCostStaticServiceImpl extends BaseServiceImpl implements
		MonthCostStaticService {

	

	@Resource(name = "monthTimesCostDao")
	private MonthTimesCostDao monthTimesDao;
	@Resource(name = "monthOtherCostDao")
	private MonthOtherCostDao monthOtherDao;
	@Resource(name = "monthCheckCostDao")
	private MonthCheckCostDao monthCheckDao;

	private List<Map<String, Object>> dataListMap = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> totalDatalist =new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> litteltotalDatalist =new ArrayList<Map<String, Object>>();
	private List dataList =new ArrayList();
	private List dataList4export =new ArrayList();
	

	@Transactional
	@Override
	public List<Map<String, Object>> getDataMap(String type, String month,
			String year, String lab) {
		if (type.equals("Check")) {
			dataListMap = monthCheckDao.getData(month, year, lab);
		}
		if (type.equals("Times")) {
			dataListMap = monthTimesDao.getData(month, year, lab);
		}
		if (type.equals("Other")) {
			dataListMap = monthOtherDao.getData(month, year, lab);
		}
		return dataListMap;
	}
	
	
	
	/*  按照类型取到统计类型的 数量
	 * @see com.cabletech.business.monthcost.service.MonthCostStaticService#getCount4Total(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Integer getCount4Total(String type, String month, String year, String lab) {
		Integer count=0;
		if (type.equals("Check")) {
			count = monthCheckDao.getCount4Total(month, year, lab);
		}
		if (type.equals("Times")) {
			count = monthTimesDao.getCount4Total(month, year, lab);
		}
		if (type.equals("Other")) {
			count = monthOtherDao.getCount4Total(month, year, lab);
		}
		return count;
	}



	@Transactional
	@Override
	public List getDetailForExport(String type, String month, String year,String lab) {
		if (type.equals("Check")) {
			dataList = monthCheckDao.getDataList(month, year, lab);
		}
		if (type.equals("Times")) {
			dataList = monthTimesDao.getDataList(month, year, lab);
		}
		if (type.equals("Other")) {
			dataList = monthOtherDao.getDataList(month, year, lab);
		}
		return dataList;
	}
	 

	@Override
	protected BaseDao getBaseDao() {

		return null;
	}

	/* (non-Javadoc) 页面展示的时候 使用
	 * @see com.cabletech.business.monthcost.service.MonthCostStaticService#getTotalData(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Transactional
	@Override
	public List<Map<String, Object>> getTotalData(String type, String month, String year) {
		if (type.equals("Check")) {
			  totalDatalist = monthCheckDao.getTotalData(month, year);
		}
		if (type.equals("Times")) {
			  totalDatalist = monthTimesDao.getTotalData(month, year);
		}
		if (type.equals("Other")) {
			  totalDatalist = monthOtherDao.getTotalData(month, year);
		}
		 
		return totalDatalist;
	}
	
	
	
	 
	
	/* (non-Javadoc)  导出的时候使用
	 * @see com.cabletech.business.monthcost.service.MonthCostStaticService#getTotalDataList(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Transactional
	@Override
	public List getTotalDataList(String type, String month, String year) {
		if (type.equals("Check")) {
			dataList4export = monthCheckDao.getTotalDataList(month, year);
		}
		if (type.equals("Times")) {
			dataList4export = monthTimesDao.getTotalDataList(month, year);
		}
		if (type.equals("Other")) {
			dataList4export = monthOtherDao.getTotalDataList(month, year);
		}
		 
		return dataList4export;
	}
	
	
	
	
	/* 获取小计的列表
	 */
	@Transactional
	@Override
	public List<Map<String, Object>> getLittleTotalMap(String type,
			String month, String year, String lab) {
		if (type.equals("Check")) {
			litteltotalDatalist = monthCheckDao.getlitteltotalData(month, year, lab);
		}
		if (type.equals("Times")) {
			litteltotalDatalist = monthTimesDao.getlitteltotalData(month, year, lab);
		}
		if (type.equals("Other")) {
			litteltotalDatalist = monthOtherDao.getlitteltotalData(month, year, lab);
		}
		return litteltotalDatalist;
	}

}
