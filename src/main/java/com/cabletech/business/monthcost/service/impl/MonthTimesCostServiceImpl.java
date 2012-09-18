package com.cabletech.business.monthcost.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.monthcost.dao.MonthTimesCostDao;
import com.cabletech.business.monthcost.model.MonthTimesCost;
import com.cabletech.business.monthcost.model.MonthTimesCostTemp;
import com.cabletech.business.monthcost.service.MonthTimesCostService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.excel.imports.ExcelImport;
import com.cabletech.common.util.Page;

/**
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@Service
public class MonthTimesCostServiceImpl extends BaseServiceImpl implements
		MonthTimesCostService {
	@Resource(name = "monthTimesCostDao")
	private MonthTimesCostDao monthTimesCostDao;
	@Resource(name = "timesItemExcelImport")
	private ExcelImport excelImport;
	// 导入错误提示信息列表
	private List<MonthTimesCostTemp> errorList = new ArrayList<MonthTimesCostTemp>();

	// 预览数据信息列表
	private List<MonthTimesCostTemp> list;

	@Transactional
	@Override
	public Page queryPage(MonthTimesCost entity, Page page, UserInfo user) {
		return monthTimesCostDao.queryPage(entity, page, user);
	}

	@Transactional
	@Override
	public MonthTimesCost getEntityById(String id) {
		return monthTimesCostDao.getEntityById(id);
	}

	@Transactional
	@Override
	public void deleteEntityById(String id) {
		monthTimesCostDao.deleteEntityById(id);
	}

	@Transactional
	@Override
	protected BaseDao getBaseDao() {
		return monthTimesCostDao;
	}

	@Transactional
	@Override
	public MonthTimesCost saveEntity(MonthTimesCost entity) {
		monthTimesCostDao.saveEntity(entity);
		return entity;
	}

	@Transactional
	@Override
	public List createItemPreviewData(String filePath, UserInfo user) {
		list = new ArrayList<MonthTimesCostTemp>();
		excelImport.setLists(list, errorList);
		excelImport.importExcelData(filePath);
		return list;
	}

	@Transactional
	@Override
	public Map importItemData(Map<String, String> parameterMap) {
		list = new ArrayList<MonthTimesCostTemp>();
		errorList = new ArrayList<MonthTimesCostTemp>();
		excelImport.setLists(list, errorList);
		excelImport.importExcelData(parameterMap.get("file_path"));
		saveBatch(list, parameterMap);
		Map resultMap = new HashMap();
		List importedList = calculateValidRatio(resultMap);
		resultMap.put("invalid_cell_list", errorList);
		resultMap.put("valid_cell_list", importedList);
		return resultMap;
	}

	/**
	 * 批量保存导入的数据
	 * 
	 * @param list
	 *            List<PatrolItemTemp> 导入的数据列表
	 * @param parameterMap
	 *            Map<String, String> 传入的参数Map
	 */

	@Transactional
	private void saveBatch(List<MonthTimesCostTemp> list,
			Map<String, String> parameterMap) {
		MonthTimesCostTemp oneCellTemp;
		for (int i = 0; list != null && i < list.size(); i++) {
			oneCellTemp = list.get(i);
			if (!oneCellTemp.isFlag()) {
				continue;
			}
			try {
				this.save(parameterMap, oneCellTemp);
			} catch (Exception e) {
				logger.info("数据信息保存出错：", e);
				oneCellTemp.setFlag(false);
				oneCellTemp.setErrorMsg("数据信息保存出错！");
				errorList.add(oneCellTemp);
			}
		}
	}

	/**
	 * 计算导入 合法数据所占比例并返回导入的合法数据列表
	 * 
	 * @param resultMap
	 *            Map 存放导入后数据的Map
	 * @return List 导入的合法数据列表
	 */
	@Transactional
	private List calculateValidRatio(Map resultMap) {
		double validRatio = 0;
		int totalCellNumber = 0;
		int validCellNumber = 0;
		int invalidCellNumber = 0;
		if (!CollectionUtils.isEmpty(list)) {
			totalCellNumber = list.size();
		}
		if (!CollectionUtils.isEmpty(errorList)) {
			validCellNumber = list.size() - errorList.size();
			invalidCellNumber = errorList.size();
		} else {
			validCellNumber = list.size();
		}
		if (totalCellNumber != 0) {
			validRatio = 100 * validCellNumber / totalCellNumber;
		}
		List importedList = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			MonthTimesCostTemp oneCellTemp = (MonthTimesCostTemp) list.get(i);
			boolean flag = oneCellTemp.isFlag();
			if (oneCellTemp != null) {
				if (StringUtils.isNotBlank(oneCellTemp.getTypet())) {
					if (checkTypet(oneCellTemp.getTypet())) {
						flag = true;
					}
				}
				if (StringUtils.isNotBlank(oneCellTemp.getContractorId())) {
					if (checkContractorId(oneCellTemp.getContractorId())) {
						flag = true;
					}
				}

			}
			if (flag) {
				importedList.add(oneCellTemp);
			}
		}
		DecimalFormat df = new DecimalFormat("#0.00");
		resultMap.put("total_cell_number", Integer.toString(totalCellNumber));
		resultMap.put("valid_cell_number", Integer.toString(validCellNumber));
		resultMap.put("invalid_cell_number",
				Integer.toString(invalidCellNumber));
		resultMap.put("valid_ratio", df.format(validRatio));
		return importedList;
	}

	/**
	 * 
	 * @param contractorId
	 *            String
	 * @return
	 */
	@Transactional
	private boolean checkContractorId(String contractorId) {
		Integer i = monthTimesCostDao.checkContractorId(contractorId);
		boolean b = false;
		if (i == 1) {
			b = true;
		}
		return b;
	}

	/**
	 * 
	 * @param typet
	 *            String
	 * @return
	 */
	@Transactional
	private boolean checkTypet(String typet) {
		Integer i = monthTimesCostDao.checkTypetName(typet);
		boolean b = false;
		if (i == 1) {
			b = true;
		}
		return b;
	}

	@Transactional
	@Override
	public void save(Map<String, String> parameterMap,
			MonthTimesCostTemp oneCellTemp) throws Exception {
		MonthTimesCostTemp oneCell = new MonthTimesCostTemp();
		String realId4Contractor = "";
		String realId4Type = "";
		String realId4Specialty = "";
		boolean flag1 = false;
		boolean flag2 = false;
		boolean flag3 = false;
		boolean flag = false;
		if (oneCellTemp != null) {
			if (StringUtils.isNotBlank(oneCellTemp.getContractorId())) {
				// 取得 代维机构的名字 来获取其对应的id
				realId4Contractor = monthTimesCostDao
						.getContractorIdByInput(oneCellTemp.getContractorId());
				if (StringUtils.isNotBlank(realId4Contractor)) {
					flag1 = true;
				}

			}
			if (StringUtils.isNotBlank(oneCellTemp.getTypet())) {
				realId4Type = monthTimesCostDao.getTypetByInput(oneCellTemp
						.getTypet());
				if (StringUtils.isNotBlank(realId4Type)) {
					flag2 = true;
				}
			}
			if (oneCellTemp.getSpecialty() != null) {
				realId4Specialty = monthTimesCostDao
						.getSpecialtyByInput(oneCellTemp.getSpecialty());
				if (StringUtils.isNotBlank(realId4Specialty)) {
					flag3 = true;
				}
			}
		}
		if (flag1 && flag2 && flag3) {
			flag = true;
		}
		String unitPrice = oneCellTemp.getUnitPrice();
		String numbers = oneCellTemp.getNumbers();
		String months = oneCellTemp.getMonths();
		String months_ = "";
		if (StringUtils.isNotBlank(months)) {
			months_ = months.substring(0, 7);
		}
		BigDecimal unitPrice_ = new BigDecimal(0);
		if (StringUtils.isNotBlank(unitPrice)) {
			unitPrice_ = new BigDecimal(unitPrice);
		}
		BigDecimal numbers_ = new BigDecimal(0);
		if (StringUtils.isNotBlank(numbers)) {
			numbers_ = new BigDecimal(numbers);
		}
		BigDecimal FactMoney = new BigDecimal(0);
		FactMoney = unitPrice_.multiply(numbers_);
		BeanUtils.copyProperties(oneCell, oneCellTemp);
		oneCell.setContractorId(realId4Contractor);
		oneCell.setShouldMoney(FactMoney.toString());
		oneCell.setSpecialty(realId4Specialty);
		oneCell.setTypet(realId4Type);
		oneCell.setMonths(months_);// 截取月份
		oneCell.setRegionId(parameterMap.get("region_id"));// 设定当前填表人所在地区
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		oneCell.setWriteDate(sdf.format(new Date()));
		oneCell.setWritePersonId(parameterMap.get("user_sid"));
		logger.info(oneCell);
		if (flag) {
			saveOneItem(oneCell);
		}
	}

	/**
	 * 
	 * @param oneCell
	 *            MonthTimesCostTemp
	 */
	@Transactional
	private void saveOneItem(MonthTimesCostTemp oneCell) {
		MonthTimesCost item = new MonthTimesCost();
		try {
			BeanUtils.copyProperties(item, oneCell);
			monthTimesCostDao.saveEntity(item);
		} catch (Exception e) {
			logger.error("", e);
		}

	}

}
