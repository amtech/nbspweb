package com.cabletech.business.base.service;

/**
 * 
 * 通用户生成(根据 中国移动网络代维管理平台技术规范 编码规范分册.doc) 包括： 工单编号 公告编号 公文编号 代维资料编号
 * 
 * @author wj
 * 
 */
public interface CommonOrderCodeService {
	public static final String ORDER_CODE_TASK_TYPE = "TASK_TYPE";// 类别模块编码
	public static final String ORDER_CODE_SYS_DATE = "SYS_DATE";// 系统日期
	public static final String ORDER_CODE_SERIAL_NUMBER = "SERIAL_NUMBER";// 序号
	public static final String ORDER_CODE_REGION_ID = "regionId";// 区域关键字
	public static final int ORDER_CODE_SERIAL_NUMBER_INITIAL = 1;// 序号 初始值
	public static final int ORDER_CODE_LENGTH = 5;// 序号 长度
	public static final String ORDER_CODE_SEPARATOR = "-";// 编码分隔符
	public static final String DIC_BUSINESS_TYPE = "businesstype";// "代维工作类型"===“维护专业”
	public static final String DIC_TASK_TYPE = "TASK_TYPE";// 代维工单流程类型
	public static final String ORDER_CODE_LETTER_MARKING = "网通-函";// 公文编码 公文标识

	public static final String MODULE_TYPE_WORK_ORDER = "work_order";// 模块编码 --
																		// 工单
	public static final String MODULE_TYPE_LETTER = "letter";// 模块编码 -- 联系函
	public static final String[] newBusinessTypes = new String[] { "C31-001",
			"C32-002", "C30-003", "C33-004", "C34-005" };// 专业编码

	/**
	 * 生成工单编号
	 * 
	 * @param regionId
	 *            区域ID
	 * @param businessType
	 *            专业编码
	 * @param taskType
	 *            工单流程类型
	 * @return
	 */
	public String generatorWorkOrderCode(String regionId, String businessType,
			String taskType);

	/**
	 * 生成公文编码
	 * 
	 * @param regionId
	 *            区域Id
	 * @return String
	 */
	public String generatorLetterCode(String regionId);
}