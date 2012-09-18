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
import com.cabletech.business.monthcost.dao.MonthCheckCostDao;
import com.cabletech.business.monthcost.model.MonthCheckCost;
import com.cabletech.business.monthcost.model.MonthCheckCostTemp;
import com.cabletech.business.monthcost.service.MonthCheckCostService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.excel.imports.ExcelImport;
import com.cabletech.common.util.Page;

/**
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Service
public class MonthCheckCostServiceImpl extends BaseServiceImpl implements
		MonthCheckCostService {
	@Resource(name = "monthCheckCostDao")
	private MonthCheckCostDao monthDao;
	@Resource(name = "checkItemExcelImport")
	private ExcelImport excelImport;
	// 导入错误提示信息列表
	private List<MonthCheckCostTemp> errorList = new ArrayList<MonthCheckCostTemp>();

	// 预览数据信息列表
	private List<MonthCheckCostTemp> list;

	@Override
	protected BaseDao getBaseDao() {
		return monthDao;
	}

	@Transactional
	@Override
	public MonthCheckCost getEntityById(String id) {
		return monthDao.getEntityById(id);
	}

	@Override
	@Transactional
	public void deleteEntityById(String id) {
		monthDao.deleteEntityById(id);
	}

	@Override
	@Transactional
	public MonthCheckCost saveEntity(MonthCheckCost entity) {
		try {
			monthDao.saveEntity(entity);
			logger.info(entity.getId());
			return entity;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	@Transactional
	@Override
	public Page queryPage(MonthCheckCost entity, Page page, UserInfo user) {
		return monthDao.queryPage(entity, page, user);
	}

	@Transactional
	@Override
	public List createItemPreviewData(String filePath, UserInfo user) {
		list = new ArrayList<MonthCheckCostTemp>();
		excelImport.setLists(list, errorList);
		excelImport.importExcelData(filePath);
		return list;
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
			MonthCheckCostTemp oneCellTemp = (MonthCheckCostTemp) list.get(i);
			// 做数据校验
			boolean flag = oneCellTemp.isFlag();
			if (oneCellTemp != null) {
				String specialty = oneCellTemp.getSpecialty();
				logger.info(specialty);
				if (checkBusinessName(specialty)) {
					flag = true;
				}
				String contractorId = oneCellTemp.getContractorId();
				logger.info(contractorId);
				if (checkContractorId(contractorId)) {
					flag = true;
				}
				flag = false;
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
	 * 校验代维单位名字的输入是否正确
	 * 
	 * @param contractorId
	 *            String
	 * @return
	 */
	private boolean checkContractorId(String contractorId) {
		Integer i = monthDao.checkContractorId(contractorId);
		boolean b = false;
		if (i == 1) {
			b = true;
		}
		return b;
	}

	/**
	 * 根据专业 和名称查询常量表里面 是否存在这条数据来验证用户输入是否正确
	 * 
	 * @param businessName
	 *            String
	 * @return
	 */
	private boolean checkBusinessName(String businessName) {
		Integer i = monthDao.checkBusinessName(businessName);
		boolean b = false;
		if (i == 1) {
			b = true;
		}
		return b;
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
	private void saveBatch(List<MonthCheckCostTemp> list,
			Map<String, String> parameterMap) {
		MonthCheckCostTemp oneCellTemp;
		for (int i = 0; list != null && i < list.size(); i++) {
			oneCellTemp = list.get(i);
			if (!oneCellTemp.isFlag()) {
				continue;
			}
			try {
				this.save(parameterMap, oneCellTemp);
			} catch (Exception e) {
				logger.info(" 数据信息保存出错：", e);
				oneCellTemp.setFlag(false);
				oneCellTemp.setErrorMsg(" 数据信息保存出错！");
				errorList.add(oneCellTemp);
			}
		}
	}

	@Transactional
	@Override
	public Map importItemData(Map<String, String> parameterMap) {
		list = new ArrayList<MonthCheckCostTemp>();
		errorList = new ArrayList<MonthCheckCostTemp>();
		excelImport.setLists(list, errorList);
		excelImport.importExcelData(parameterMap.get("file_path"));
		saveBatch(list, parameterMap);
		Map resultMap = new HashMap();
		List importedList = calculateValidRatio(resultMap);
		resultMap.put("invalid_cell_list", errorList);
		resultMap.put("valid_cell_list", importedList);
		return resultMap;
	}

	@Transactional
	@Override
	public void save(Map<String, String> parameterMap,
			MonthCheckCostTemp oneCellTemp) throws Exception {
		String realId4Contractor = "";
		String realId4Specialty = "";
		boolean flag1 = false;
		boolean flag2 = false;
		boolean flag = false;
		MonthCheckCostTemp oneCell = new MonthCheckCostTemp();
		if (oneCellTemp != null) {
			if (oneCellTemp.getContractorId() != null) {
				// 取得 代维机构的名字 来获取其对应的id
				realId4Contractor = monthDao.getContractorIdByInput(oneCellTemp
						.getContractorId());
				if (StringUtils.isNotBlank(realId4Contractor)) {
					flag1 = true;
				}
			}
			if (oneCellTemp.getSpecialty() != null) {
				realId4Specialty = monthDao.getSpecialtyByInput(oneCellTemp
						.getSpecialty());
				if (StringUtils.isNotBlank(realId4Specialty)) {
					flag2 = true;
				}
			}
		}
		if (flag1 && flag2) {
			flag = true;
		}
		String unitPrice = oneCellTemp.getUnitPrice();
		String numbers = oneCellTemp.getNumbers();
		String subtractMoney = oneCellTemp.getSubtractMoney();
		String months = oneCellTemp.getMonths();
		String months_ = "";
		if (StringUtils.isNotBlank(months)) {
			months_ = months.substring(0, 7);
		}
		BigDecimal unitPrice_ = new BigDecimal(0);
		if (StringUtils.isNotBlank(unitPrice)) {
			unitPrice_ = new BigDecimal(unitPrice);
		}
		BigDecimal subtractMoney_ = new BigDecimal(0);
		if (StringUtils.isNotBlank(unitPrice)) {
			subtractMoney_ = new BigDecimal(subtractMoney);
		}
		BigDecimal numbers_ = new BigDecimal(0);
		if (StringUtils.isNotBlank(numbers)) {
			numbers_ = new BigDecimal(numbers);
		}
		BigDecimal FactMoney = new BigDecimal(0);
		FactMoney = unitPrice_.multiply(numbers_).subtract(subtractMoney_);
		BeanUtils.copyProperties(oneCell, oneCellTemp);
		oneCell.setShouldMoney(FactMoney.toString());
		oneCell.setMonths(months_);// 截取月份
		oneCell.setContractorId(realId4Contractor);
		oneCell.setSpecialty(realId4Specialty);
		oneCell.setRegionId(parameterMap.get("region_id"));// 设定当前填表人所在地区
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		oneCell.setWriteDate(sdf.format(new Date()));
		oneCell.setWritePersonId(parameterMap.get("user_sid"));
		if (flag) {
			saveOneItem(oneCell);
		}
	}

	/**
	 * 
	 * @param oneCell
	 *            MonthCheckCostTemp
	 */
	@Transactional
	private void saveOneItem(MonthCheckCostTemp oneCell) {
		MonthCheckCost item = new MonthCheckCost();
		try {
			BeanUtils.copyProperties(item, oneCell);
			monthDao.saveEntity(item);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

}
