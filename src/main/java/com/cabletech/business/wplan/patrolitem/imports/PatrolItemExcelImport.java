package com.cabletech.business.wplan.patrolitem.imports;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cabletech.business.wplan.patrolitem.model.PatrolItemTemp;
import com.cabletech.common.excel.imports.ExcelImport;

/**
 * 巡检项Excel导入功能类
 * 
 * @author 杨隽 2012-02-13 创建
 * 
 */
@Service
public class PatrolItemExcelImport extends ExcelImport {
	// 巡检项导入模板的Xml配置文件的根节点id
	private static final String PATROL_ITEM_IMPORT_ID = "patrolItemImport";
	// 巡检项导入数据的开始行行索引
	private static final int IMPORT_DATA_ROW = 2;
	@SuppressWarnings("rawtypes")
	private List errorList;
	@SuppressWarnings("rawtypes")
	private List list;

	@SuppressWarnings("unchecked")
	@Override
	public void processData(Object oneCellTemp) {
		// TODO Auto-generated method stub
		PatrolItemTemp patrolItemTemp = (PatrolItemTemp) oneCellTemp;
		if (!patrolItemTemp.isFlag()) {
			errorList.add(patrolItemTemp);
		}
		list.add(patrolItemTemp);
	}

	@Override
	public int getImportDataRow() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return PATROL_ITEM_IMPORT_ID;
	}
}
