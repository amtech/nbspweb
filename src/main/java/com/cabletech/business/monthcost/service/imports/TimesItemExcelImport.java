package com.cabletech.business.monthcost.service.imports;

import java.util.List;
import org.springframework.stereotype.Service;
import com.cabletech.business.monthcost.model.MonthTimesCostTemp; 
import com.cabletech.common.excel.imports.ExcelImport;
/**
 *  Excel导入功能类
 * 
 * @author  周刚 2012-08 创建
 * 
 */
@Service
public class TimesItemExcelImport extends ExcelImport {
	//  导入模板的Xml配置文件的根节点id
	private static final String TIMES_ITEM_IMPORT_ID = "timesItemImport";
	//  导入数据的开始行行索引
	private static final int IMPORT_DATA_ROW = 2;
	@SuppressWarnings("rawtypes")
	private List errorList;
	@SuppressWarnings("rawtypes")
	private List list;

	@SuppressWarnings("unchecked")
	@Override
	public void processData(Object oneCellTemp) {
		MonthTimesCostTemp monthTimesCostTemp = (MonthTimesCostTemp) oneCellTemp;
		if (!monthTimesCostTemp.isFlag()) {
			errorList.add(monthTimesCostTemp);
		}
		list.add(monthTimesCostTemp);
	}

	@Override
	public int getImportDataRow() {
		return IMPORT_DATA_ROW;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setLists(List... lists) {
		this.list = lists[0];
		this.errorList = lists[1];
	}

	@Override
	public String getImportXmlId() {
		return TIMES_ITEM_IMPORT_ID;
	}
}
