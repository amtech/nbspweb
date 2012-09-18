package com.cabletech.business.monthcost.service.impl;

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
import com.cabletech.business.monthcost.dao.MonthOtherCostDao;
import com.cabletech.business.monthcost.model.MonthOtherCost;
import com.cabletech.business.monthcost.model.MonthOtherCostTemp;
import com.cabletech.business.monthcost.service.MonthOtherCostService;
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
public class MonthOtherCostServiceImpl extends BaseServiceImpl implements
		MonthOtherCostService {

	@Resource(name = "monthOtherCostDao")
	private MonthOtherCostDao monthDao;
	@Resource(name = "otherItemExcelImport")
	private ExcelImport excelImport;
	// 导入错误提示信息列表
	private List<MonthOtherCostTemp> errorList = new ArrayList<MonthOtherCostTemp>();

	// 预览数据信息列表
	private List<MonthOtherCostTemp> list;

	@Transactional
	@Override
	public Page queryPage(MonthOtherCost entity, Page page, UserInfo user) {
		return monthDao.queryPage(entity, page, user);
	}

	@Transactional
	@Override
	public MonthOtherCost getEntityById(String id) {
		return monthDao.getEntityById(id);
	}

	@Transactional
	@Override
	public void deleteEntityById(String id) {
		monthDao.deleteEntityById(id);
	}

	@Transactional
	@Override
	public MonthOtherCost saveEntity(MonthOtherCost entity) {
		monthDao.saveEntity(entity);
		return entity;
	}

	@Override
	protected BaseDao getBaseDao() {
		return monthDao;
	}

	@Override
	@Transactional
	public List createItemPreviewData(String filePath, UserInfo user) {
		list = new ArrayList<MonthOtherCostTemp>();
		excelImport.setLists(list, errorList);
		excelImport.importExcelData(filePath);
		return list;
	}

	@Override
	@Transactional
	public Map importItemData(Map<String, String> parameterMap) {
		list = new ArrayList<MonthOtherCostTemp>();
		errorList = new ArrayList<MonthOtherCostTemp>();
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
			MonthOtherCostTemp oneCellTemp = (MonthOtherCostTemp) list.get(i);
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
		Integer i = monthDao.checkContractorId(contractorId);
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
		Integer i = monthDao.checkTypetName(typet);
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
	private void saveBatch(List<MonthOtherCostTemp> list,
			Map<String, String> parameterMap) {
		MonthOtherCostTemp oneCellTemp;
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

	@Transactional
	@Override
	public void save(Map<String, String> parameterMap,
			MonthOtherCostTemp oneCellTemp) throws Exception {
		MonthOtherCostTemp oneCell = new MonthOtherCostTemp();
		String realId4Contractor = "";
		String realId4Type = "";
		boolean flag1 = false;
		boolean flag2 = false;
		boolean flag = false;
		if (oneCellTemp != null) {
			if (StringUtils.isNotBlank(oneCellTemp.getContractorId())) {
				// 取得 代维机构的名字 来获取其对应的id
				realId4Contractor = monthDao.getContractorIdByInput(oneCellTemp
						.getContractorId());
				if (StringUtils.isNotBlank(realId4Contractor)) {
					flag1 = true;
				}
			}
			if (StringUtils.isNotBlank(oneCellTemp.getTypet())) {
				realId4Type = monthDao.getTypetByInput(oneCellTemp.getTypet());
				if (StringUtils.isNotBlank(realId4Type)) {
					flag2 = true;
				}
			}
		}
		if (flag1 && flag2) {
			flag = true;
		}
		String months = oneCellTemp.getMonths();
		String months_ = "";
		if (StringUtils.isNotBlank(months)) {
			months_ = months.substring(0, 7);
		}
		BeanUtils.copyProperties(oneCell, oneCellTemp);
		oneCell.setContractorId(realId4Contractor);
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
	 *            MonthOtherCostTemp
	 */
	@Transactional
	private void saveOneItem(MonthOtherCostTemp oneCell) {
		MonthOtherCost item = new MonthOtherCost();
		try {
			BeanUtils.copyProperties(item, oneCell);
			monthDao.saveEntity(item);
		} catch (Exception e) {
			logger.error("", e);
		}
	}
}
