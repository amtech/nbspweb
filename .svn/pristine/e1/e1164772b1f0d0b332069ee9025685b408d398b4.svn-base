package com.cabletech.business.base.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义每个模块上传附件的目录结构
 * 
 * @author zhaobi
 * 
 */
public class ModuleCatalog {
	private static Map<String, String> MODULECATALOG = new HashMap<String, String>();
	public static final String TROUBLE = "trouble";
	public static final String MAINTENANCE = "maintenance";// 技术维护模块
	public static final String SAFEGUARD = "safeguard";
	public static final String DRILL = "drill";
	public static final String OPTICALCABLE = "opticalcable";
	public static final String PIPELINE = "pipeline";
	public static final String DATUM = "datum";
	public static final String HIDDTROUBLEWATCH = "hiddtroubleWatch";
	public static final String INSPECTION = "inspection";
	public static final String LINECUT = "lineCut";
	public static final String FIBRECORETEST = "fibreCoreTest";
	public static final String EARTHINGTEST = "earthingTest";
	public static final String SENDTASK = "WTASK_ORDER";
	public static final String OTHER = "other";
	public static final String MATERIAL = "material";
	public static final String PROJECT = "project";
	public static final String QUEST = "quest";
	public static final String OVERHAUL = "overhaul";
	public static final String MAINTAINAGENTMANAGEMENT = "mam";

	static {
		MODULECATALOG.put("trouble", "线路维护/线路故障");
		MODULECATALOG.put("safeguard", "线路维护/线路保障");
		MODULECATALOG.put("drill", "线路维护/演练");
		MODULECATALOG.put("opticalcable", "线路维护/维护资源/中继段");
		MODULECATALOG.put("pipeline", "线路维护/维护资源/管道");
		MODULECATALOG.put("datum", "线路维护/维护资源/其他");
		MODULECATALOG.put("hiddtroubleWatch", "线路维护/隐患盯防");
		MODULECATALOG.put("inspection", "线路维护/验收交维");
		MODULECATALOG.put("lineCut", "线路维护/线路割接");
		MODULECATALOG.put("fibreCoreTest", "线路维护/技术维护/备纤测试");
		MODULECATALOG.put("earthingTest", "线路维护/技术维护/接地电阻测试");
		MODULECATALOG.put("WTASK_ORDER", "任务派单");
		MODULECATALOG.put("other", "线路维护/其他维护工作");
		MODULECATALOG.put("project", "线路维护/工程管理");
		MODULECATALOG.put("quest", "问卷调查");
		MODULECATALOG.put("overhaul", "线路维护/大修项目");
		MODULECATALOG.put("BASESTATION", "基站维护/基站图片");
		MODULECATALOG.put("PATROL", "基站维护/巡检图片");
		MODULECATALOG.put("HIDDTROUBLE", "基站维护/隐患图片");
		MODULECATALOG.put("EQUIPMENT", "基站维护/设备图片");
		MODULECATALOG.put("mam", "代维管理");
	}
	/**
	 * 获取单个实体
	 * @param key 
	 * @return
	 */
	public static String get(String key) {
		return (String) MODULECATALOG.get(key);
	}
}
