package com.cabletech.business.ah.rating.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.cabletech.business.ah.rating.model.RatingForm;
import com.cabletech.business.ah.rating.model.RatingFormItemTemp;
import com.cabletech.business.ah.rating.service.RatingFormItemImportService;
import com.cabletech.business.ah.rating.service.RatingFormService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.excel.imports.ExcelImport;

/**
 * 考核表导入数据业务服务接口实现
 * 
 * @author 杨隽 2012-06-26 创建
 * 
 */
@Service
public class RatingFormItemImportServiceImpl extends
		BaseServiceImpl<RatingForm, String> implements
		RatingFormItemImportService {
	@Resource(name = "ratingFormServiceImpl")
	private RatingFormService ratingFormService;
	@Resource(name = "ratingFormItemExcelImport")
	private ExcelImport excelImport;
	// 预览数据信息列表
	private List<RatingFormItemTemp> list;
	// 导入错误提示信息列表
	private List<RatingFormItemTemp> errorList = new ArrayList<RatingFormItemTemp>();

	/**
	 * 根据要导入的Excel文件生成预览数据
	 * 
	 * @param filePath
	 *            String 导入的巡检报表文件路径信息
	 * @throws Exception
	 */
	public List<RatingFormItemTemp> createItemPreviewData(String filePath) {
		list = new ArrayList<RatingFormItemTemp>();
		excelImport.setLists(list, errorList);
		excelImport.importExcelData(filePath);
		return list;
	}

	/**
	 * 导入Excel数据
	 * 
	 * @param parameterMap
	 *            Map<String,Object>
	 *            导入Excel的参数：file_path->导入的巡检报表文件路径信息，business_type
	 *            ->专业类型，region_id->区域编号
	 * @return Map 导入的结果Map
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	public Map importItemData(Map<String, Object> parameterMap) {
		list = new ArrayList<RatingFormItemTemp>();
		errorList = new ArrayList<RatingFormItemTemp>();
		excelImport.setLists(list, errorList);
		excelImport.importExcelData((String) parameterMap.get("file_path"));
		saveBatch(list, parameterMap);
		Map resultMap = new HashMap();
		List importedList = calculateValidRatio(resultMap);
		resultMap.put("invalid_cell_list", errorList);
		resultMap.put("valid_cell_list", importedList);
		return resultMap;
	}

	/**
	 * 批量保存导入的巡检项数据
	 * 
	 * @param list
	 *            List<PatrolItemTemp> 导入的巡检项数据列表
	 * @param parameterMap
	 *            Map<String, Object> 传入的参数Map
	 */
	private void saveBatch(List<RatingFormItemTemp> list,
			Map<String, Object> parameterMap) {
		RatingFormItemTemp oneCellTemp;
		for (int i = 0; list != null && i < list.size(); i++) {
			oneCellTemp = list.get(i);
			if (!oneCellTemp.isImportSuccessFlag()) {
				continue;
			}
			try {
				ratingFormService.save(parameterMap, oneCellTemp);
			} catch (Exception e) {
				logger.info("考核表数据信息保存出错：", e);
				oneCellTemp.setImportSuccessFlag(false);
				oneCellTemp.setErrorMsg("考核表数据信息保存出错！");
				errorList.add(oneCellTemp);
			}
		}
	}

	/**
	 * 计算导入巡检项的合法数据所占比例并返回导入的合法数据列表
	 * 
	 * @param resultMap
	 *            Map 存放导入后数据的Map
	 * @return List 导入的合法数据列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
			RatingFormItemTemp oneCellTemp = (RatingFormItemTemp) list.get(i);
			if (oneCellTemp.isImportSuccessFlag()) {
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

	@Override
	protected BaseDao<RatingForm, String> getBaseDao() {
		return null;
	}
}