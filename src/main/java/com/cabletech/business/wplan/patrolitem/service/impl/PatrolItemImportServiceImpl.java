package com.cabletech.business.wplan.patrolitem.service.impl;

import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.cabletech.business.wplan.patrolitem.model.PatrolItem;
import com.cabletech.business.wplan.patrolitem.model.PatrolItemTemp;
import com.cabletech.business.wplan.patrolitem.service.PatrolItemImportService;
import com.cabletech.business.wplan.patrolitem.service.PatrolItemService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.excel.exports.ExcelExport;
import com.cabletech.common.excel.imports.ExcelImport;

/**
 * 巡检项导入数据业务服务接口实现
 * 
 * @author wangjie
 * @author 杨隽 2011-10-25 添加导入巡检项目的“值域范围”和“默认值”字段
 * @author 杨隽 2011-10-25 添加启用巡检项目、修改逻辑删除为作废，废弃物理删除
 * @author 杨隽 2012-02-13 重构导入方法
 * @author 杨隽 2012-02-13 分离巡检项导入业务和巡检项基本操作业务
 * @author 杨隽 2012-02-14 重构saveBatch方法
 * @author 杨隽 2012-02-15 实现exportInvalidItemData()方法
 * @author 杨隽 2012-02-24 修改exportInvalidItemData()方法
 * 
 */
@Service
public class PatrolItemImportServiceImpl extends
		BaseServiceImpl<PatrolItem, String> implements PatrolItemImportService {
	@Resource(name = "patrolItemServiceImpl")
	private PatrolItemService patrolItemService;
	@Resource(name = "patrolItemExcelImport")
	private ExcelImport excelImport;
	@Resource(name = "invalidPatrolItemExcelExport")
	private ExcelExport excelExport;
	// 预览数据信息列表
	private List<PatrolItemTemp> list;
	// 导入错误提示信息列表
	private List<PatrolItemTemp> errorList = new ArrayList<PatrolItemTemp>();

	/**
	 * 根据要导入的Excel文件生成预览数据
	 * 
	 * @param filePath
	 *            String 导入的巡检报表文件路径信息
	 * @throws Exception
	 */
	public List<PatrolItemTemp> createItemPreviewData(String filePath) {
		list = new ArrayList<PatrolItemTemp>();
		excelImport.setLists(list, errorList);
		excelImport.importExcelData(filePath);
		return list;
	}

	/**
	 * 导入Excel数据
	 * 
	 * @param parameterMap
	 *            Map<String,String>
	 *            导入Excel的参数：file_path->导入的巡检报表文件路径信息，business_type
	 *            ->专业类型，region_id->区域编号
	 * @return Map 导入的结果Map
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	public Map importItemData(Map<String, String> parameterMap) {
		list = new ArrayList<PatrolItemTemp>();
		errorList = new ArrayList<PatrolItemTemp>();
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
	 * 导出出错的导入巡检项数据
	 * 
	 * @param out
	 *            OutputStream 输出流
	 * @param list
	 *            List 出错的导入巡检项数据列表
	 */
	@SuppressWarnings("rawtypes")
	public void exportInvalidItemData(OutputStream out, List list) {
		try {
			excelExport.exportExcelData(list);
			excelExport.outExcelFile(out);
		} catch (Exception e) {
			logger.error("导出出错的导入巡检项数据出错:", e);
		}
	}

	/**
	 * 批量保存导入的巡检项数据
	 * 
	 * @param list
	 *            List<PatrolItemTemp> 导入的巡检项数据列表
	 * @param parameterMap
	 *            Map<String, String> 传入的参数Map
	 */
	private void saveBatch(List<PatrolItemTemp> list,
			Map<String, String> parameterMap) {
		PatrolItemTemp oneCellTemp;
		for (int i = 0; list != null && i < list.size(); i++) {
			oneCellTemp = list.get(i);
			if (!oneCellTemp.isFlag()) {
				continue;
			}
			try {
				patrolItemService.save(parameterMap, oneCellTemp);
			} catch (Exception e) {
				logger.info("巡检项数据信息保存出错：", e);
				oneCellTemp.setFlag(false);
				oneCellTemp.setErrorMsg("巡检项数据信息保存出错！");
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
			PatrolItemTemp oneCellTemp = (PatrolItemTemp) list.get(i);
			if (oneCellTemp.isFlag()) {
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
	protected BaseDao<PatrolItem, String> getBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}
}