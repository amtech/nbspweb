package com.cabletech.common.base;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Administrator
 * @author 杨隽 2011-11-16 10:09 在getResourceTypeMap()方法中添加集客家客的Map键值对
 * @author 杨隽 2011-12-30 09:22 添加系统登录用户的会话保存KEY
 * @author 杨隽 2012-01-04 14:27 添加页面数据默认显示数量
 * @author 杨隽 2012-01-06 11:15 添加工作流通过与不通过常量
 * @author 杨隽 2012-01-31 10:26 添加数据字典中关键字和值字段名称常量、首页专业类型KEY常量
 * @author 杨隽 2012-01-31 10:31
 *         添加流程名称在首页待办数量map中的KEY常量、首页待办列表中列名字段KEY常量、首页待办列表中标题字段KEY常量
 * @author 杨隽 2012-02-13 10:05 添加“Excel下载模板的源包路径”常量
 * 
 */
public class SysConstant {
	/**
	 * 页面数据默认显示数量
	 */
	public static final int DEFAULT_PAGE_SIZE = 15;
	/**
	 * 系统登录用户的会话保存KEY
	 */
	public static final String SESSION_USERINFO_KEY = "LOGIN_USER";
	/**
	 * 代维
	 */
	public static final String DEPTTYPE_C = "2";
	/**
	 * 未提交状态
	 */
	public static final String WAIT_SUBMIT_STATE = "01";
	/**
	 * 待审核状态
	 */
	public static final String WAIT_AUDITING_STATE = "02";
	/**
	 * 审核通过状态（正在填写状态）
	 */
	public static final String PASSED_STATE = "03";
	/**
	 * 审核不通过状态
	 */
	public static final String NOT_PASSED_STATE = "04";
	/**
	 * 上半年
	 */
	public static final String PLAN_XJND_1 = "1";
	/**
	 * 下半年
	 */
	public static final String PLAN_XJND_2 = "2";
	/**
	 * 年计划
	 */
	public static final String WPLAN_YEAR = "1";
	/**
	 * 季度计划
	 */
	public static final String WPLAN_SEASON = "2";
	// 表名CONTRACTORINFO
	public static final String DB_TABLENAME_CONTRACTORINFO = "CONTRACTORINFO";
	// 表名CONTRACTORPERSON
	public static final String DB_TABLENAME_CONTRACTORPERSON = "CONTRACTORPERSON";
	// 基站
	public static final String DB_TABLENAME_RS_BASESTATION = "A24";
	// 铁塔
	public static final String DB_TABLENAME_RS_OURDOOR_FACILITIES = "A25";
	// 室内覆盖
	public static final String DB_TABLENAME_RS_OVERRIDEINFO = "A26";
	// 集客
	public static final String DB_TABLENAME_RS_GROUPCUSTOMER = "A30";
	// 家客
	public static final String DB_TABLENAME_RS_CUSTOMER = "A31";
	// 直放站
	public static final String DB_TABLENAME_RS_REPEATER = "A27";
	
	public static final String DICTIONARY_FORMITEM_POINTTYPE = "POINTTYPE";

	// 通用工单
	public static final String DICTIONARY_FORMITEM_BUSINESSTYPE_C20 = "C20";
	/**
	 * 线路巡检
	 */
	public static final String DICTIONARY_FORMITEM_BUSINESSTYPE_C30 = "C30";

	/**
	 * 基站巡检
	 */
	public static final String DICTIONARY_FORMITEM_BUSINESSTYPE_C31 = "C31";

	/**
	 * 综合覆盖巡检
	 */
	public static final String DICTIONARY_FORMITEM_BUSINESSTYPE_C32 = "C32";

	/**
	 * 铁塔巡检
	 */
	public static final String DICTIONARY_FORMITEM_BUSINESSTYPE_C33 = "C33";

	/**
	 * 集客家客
	 */
	public static final String DICTIONARY_FORMITEM_BUSINESSTYPE_C34 = "C34";

	public static final String IS_QUERY = "1";

	public static final String IS_CITY_QUERY = "2";
	/**
	 * 系统ID
	 */
	public static final String SYSTEM_ID = "ZHDW";
	/**
	 * 菜单等级
	 */
	public static final String MENU_LV = "3";

	/**
	 * 字典码表，信息
	 */
	public static final String SYSDICTIONARY_TYPE_INFORMATION = "INFORMATION";
	/**
	 * 最大信息条数
	 */
	public static final String MAX_INFORMATION_COUNT = "8";

	// 信息层次常量
	// 错误信息层次
	public static final String ERROR = "error";
	// 警告信息层次
	public static final String WARNING = "warning";
	// 提示信息层次
	public static final String INFO = "info";
	// 成功信息层次
	public static final String SUCCESS = "success";

	/**
	 * 工作流通过常量
	 */
	public static final String PASS_WORKFLOW_TRANSTION = "pass";
	/**
	 * 工作流审核通过名称
	 */
	public static final String PASS_WORKFLOW_TRANSTIONNAME = "审核通过";
	/**
	 * 工作流不通过常量
	 */
	public static final String REJECT_WORKFLOW_TRANSITION = "reject";
	/**
	 * 工作流不通过常量名称
	 */
	public static final String REJECT_WORKFLOW_TRANSITIONNAME = "审核不通过";
	
	/**
	 * 联系函使用 驳回
	 */
	public static final String REJECT_WORKFLOW_CONTACTNAME = "驳回";
	/**
	 * 工作流转审常量
	 */
	public static final String TRANSFER_WORKFLOW_TRANSTION = "transfer";
	/**
	 * 工作流拒签确认常量
	 */
	public static final String REFUSE_WORKFLOW_TRANSITION = "untread";

	// 数据字典中关键字和值字段名称常量
	// 数据字典中关键字字段名称常量
	public static final String DICTIONARY_VALUE_COLUMN = "LABLE";
	// 数据字典中值字段名称常量
	public static final String DICTIONARY_KEY_COLUMN = "CODEVALUE";

	// 首页专业类型KEY常量
	public static final String BUSINESSTYPE_KEY = "BUSINESSTYPE";

	// 流程名称在首页待办数量map中的KEY
	public static final String PROCESS_NAME_KEY = "module";
	// 流程名称在首页待办数量map中的KEY
	public static final String PROCESS_COMMENT_KEY = "module_name";
	// 列名字段KEY常量
	public static final String ID_COLUMN_KEY = "id";
	// 标题字段KEY常量
	public static final String TITLE_COLUMN_KEY = "title";
	// 服务处理访问地址KEY
	public static final String ACCESS_URL_KEY = "access_url";
	/**
	 * 开始时间
	 */
	public static final String DAY_STARTTIME = " 00:00:00";
	/**
	 * 结束时间
	 */
	public static final String DAY_ENDTIME = " 23:59:59";

	/**
	 * 获取资源类型列表
	 * 
	 * @return
	 */
	public static Map<String, String> getResourceTypeMap() {
		// TODO Auto-generated method stub
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(SysConstant.DB_TABLENAME_RS_BASESTATION, "基站");
		map.put(SysConstant.DB_TABLENAME_RS_REPEATER, "直放站");
		map.put(SysConstant.DB_TABLENAME_RS_OURDOOR_FACILITIES, "铁塔");
		map.put(SysConstant.DB_TABLENAME_RS_OVERRIDEINFO, "室内分布");
		map.put(SysConstant.DB_TABLENAME_RS_GROUPCUSTOMER, "集客");
		map.put(SysConstant.DB_TABLENAME_RS_CUSTOMER, "家客");
		return map;
	}

	/**
	 * 获取是否列表，供customselect标签使用
	 * @return
	 */
	public static Map<String,String> getWhetherMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("0", "是");
		map.put("1", "否");
		return map;
	}
	/**
	 * 可用的专业类型函数
	 * 
	 * @return
	 */
	public static List usebtypeList() {
		List list = new ArrayList();
		list.add(SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C30);
		list.add(SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C31);
		list.add(SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C32);
		list.add(SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C33);
		list.add(SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C34);
		return list;
	}

	/**
	 * 获取资源专业对应列表
	 * 
	 * @return
	 */
	public static Map<String, String> getResourceBusinessTypeMap() {
		// TODO Auto-generated method stub
		Map<String, String> map = new LinkedHashMap<String, String>();
		// 铁塔
		map.put(DICTIONARY_FORMITEM_BUSINESSTYPE_C31,
				DB_TABLENAME_RS_BASESTATION);
		// 综合覆盖
		map.put(DICTIONARY_FORMITEM_BUSINESSTYPE_C32,
				DB_TABLENAME_RS_OVERRIDEINFO + "," + DB_TABLENAME_RS_REPEATER);
		// 铁塔
		map.put(DICTIONARY_FORMITEM_BUSINESSTYPE_C33,
				DB_TABLENAME_RS_OURDOOR_FACILITIES);
		// 集客家客
		map.put(DICTIONARY_FORMITEM_BUSINESSTYPE_C34,
				DB_TABLENAME_RS_GROUPCUSTOMER + "," + DB_TABLENAME_RS_CUSTOMER);
		return map;
	}

	// 计划模板启用状态
	public static final String TEMPLATE_START_USING_STATE = "1";

	// 计划模板停用状态
	public static final String TEMPLATE_STOP_USING_STATE = "2";

	// 表单提交标识
	public static final String FORM_IS_SUBMITED = "1";

	// Excel下载模板的源包路径
	public static final String EXCEL_DOWNLOAD_TEMPLATES_PACKAGE_PATH = "/excel_templates";

	// 巡检计划执行完 状态
	public static final String WPLAN_PATROLINFO_STATE_END = "03";
	public static final String OILENGINEUSE_SPACE="O11"; //油机使用状态空闲
	public static final String OILENGINEUSE_DISPATCHE="O12";//油机调度途
	public static final String OILENGINEUSE_ING="O13";//油机发电中
	public static final String OILENGINEUSED = "O14"; //发电已结束
	public static final String OILENGINEUSE_STATE="O24"; //油机状态删除
	
	//代维工单流程类型
	public static final String COMMON_ORDER_FLOW_TYPE="001";
	public static final String FAULT_FLOW_TYPE="002";
	public static final String OE_DISPATCHTASK_FLOW_TYPE="500";
}
