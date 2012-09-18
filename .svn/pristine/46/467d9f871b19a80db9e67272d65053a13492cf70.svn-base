package com.cabletech.business.base.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cabletech.baseinfo.base.BaseUtil;
import com.cabletech.baseinfo.base.DateUtil;
import com.cabletech.business.base.dao.CommonOrderCodeDao;
import com.cabletech.business.base.service.CommonOrderCodeService;


/**
 * 
 * 通用户生成(根据   中国移动网络代维管理平台技术规范 编码规范分册.doc)
 *     包括：
 *     	工单编号
 *     	公告编号
 *     	公文编号
 *     	代维资料编号
 * @author wj
 *
 */
@Service
@Transactional
public class CommonOrderCodeServiceImpl  implements CommonOrderCodeService{
	@Resource(name = "commonOrderCodeDao")
	private CommonOrderCodeDao commonOrderCodeDao;

	/**
	 * 私有方法--添加工单编号记录
	 * @param parameter 参数
	 */
	private void addOrderCode(Map<String, Object> parameter) {
		commonOrderCodeDao.deleteOrderCode(parameter);
		commonOrderCodeDao.addOrderCode(parameter);
	}
	
	
	/**
	 * 私有方法--查询工单编号记录
	 * @param parameter 参数
	 */
    private int queryOrderCode(Map<String, Object> parameter) {
		int index = ORDER_CODE_SERIAL_NUMBER_INITIAL;
		Map<String, Object> ret = commonOrderCodeDao.queryOrderCode(parameter);
		if(null==ret){
			parameter.put(ORDER_CODE_SERIAL_NUMBER, new BigDecimal(index));
			addOrderCode(parameter);
			ret = parameter;
		}else{
			commonOrderCodeDao.updateOrderCode(ret);
		}
		index = BaseUtil.intValue(ret.get(ORDER_CODE_SERIAL_NUMBER));
		return index;
	}

    @Override
    public String generatorWorkOrderCode(String regionId, String businessType,String taskType) {
		//初始化查询参数
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put(ORDER_CODE_TASK_TYPE, MODULE_TYPE_WORK_ORDER);
		parameter.put(ORDER_CODE_SYS_DATE, DateUtil.getNowDateString("yyyy-MM-dd"));
		parameter.put(ORDER_CODE_REGION_ID, regionId);
        String index = String.valueOf(queryOrderCode(parameter));
        String prefix  = "";
        for(int i=0;i<(ORDER_CODE_LENGTH-index.length());i++)prefix=prefix+"0";
        index = prefix+index;
		StringBuffer orderCode = new StringBuffer();
		orderCode.append(commonOrderCodeDao.queryProvinceShort(parameter)); //省份编码
		orderCode.append(ORDER_CODE_SEPARATOR);
		orderCode.append(getnNewBusinessType(businessType));//代维工作类型(专业类型)
		orderCode.append(ORDER_CODE_SEPARATOR);
		orderCode.append(taskType);//代维工单流程类型
		orderCode.append(ORDER_CODE_SEPARATOR);
		orderCode.append(DateUtil.getNowDateString("yyMMdd"));//日期
		orderCode.append(ORDER_CODE_SEPARATOR);
		orderCode.append(index);//每日流水号
		return orderCode.toString();
	}

    @Override
	public String generatorLetterCode(String regionId) {
		//初始化查询参数
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put(ORDER_CODE_TASK_TYPE,MODULE_TYPE_LETTER);
		parameter.put(ORDER_CODE_SYS_DATE, DateUtil.getNowDateString("yyyy-MM-dd"));
		parameter.put(ORDER_CODE_REGION_ID, regionId);
        String index = String.valueOf(queryOrderCode(parameter));
        String prefix  = "";
        for(int i=0;i<(ORDER_CODE_LENGTH-index.length());i++)prefix=prefix+"0";
        index = prefix+index;
		StringBuffer orderCode = new StringBuffer();
		orderCode.append(commonOrderCodeDao.queryProvinceShort(parameter)); //省份编码
		orderCode.append(ORDER_CODE_SEPARATOR);
		orderCode.append(ORDER_CODE_LETTER_MARKING); //公文标识
		orderCode.append(ORDER_CODE_SEPARATOR);
		orderCode.append(DateUtil.getNowDateString("yyMMdd"));//日期
		orderCode.append(ORDER_CODE_SEPARATOR);
		orderCode.append(index);//每日流水号
		return orderCode.toString();
	}	
	
	/**
	 * 私有方法--获取专业类型与新规范的对应关系
	 * @param oldType 专业类型
	 */
	public String getnNewBusinessType(String oldType){
		for(String temp:newBusinessTypes){
			if(temp.startsWith(oldType)){
				return temp.split("-")[1];
			}
		}
		return "";
	}
	
	
}