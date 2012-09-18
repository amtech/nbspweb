package com.cabletech.business.base.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.cabletech.baseinfo.base.BaseDao;
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
@Repository
public class CommonOrderCodeDao extends BaseDao {

	/**
	 * 添加编码
	 * @param parameter 参数
	 * @return
	 */
	public void addOrderCode(Map<String,Object> parameter){
		StringBuffer sb = new StringBuffer();
		sb.append(" INSERT INTO BASE_SEQUENCE(TASK_TYPE,SYS_DATE,SERIAL_NUMBER) ");
		sb.append(" VALUES ('"+parameter.get(CommonOrderCodeService.ORDER_CODE_TASK_TYPE)+"', ");
		sb.append(" TO_DATE('"+parameter.get(CommonOrderCodeService.ORDER_CODE_SYS_DATE)+"','yyyy-MM-dd'), ");
		sb.append(" "+parameter.get(CommonOrderCodeService.ORDER_CODE_SERIAL_NUMBER)+") ");
		this.insetOrUpdate(sb.toString());
	}
	
	/**
	 * 删除编码
	 * @param parameter 参数
	 * @return
	 */
	public void deleteOrderCode(Map<String,Object> parameter){
		StringBuffer sb = new StringBuffer();
		sb.append(" DELETE BASE_SEQUENCE BS WHERE 1=1 ");
		sb.append(" AND BS.TASK_TYPE = '"+parameter.get(CommonOrderCodeService.ORDER_CODE_TASK_TYPE)+"'  ");
//		sb.append(" AND BS.SYS_DATE = TO_DATE('"+parameter.get(CommonOrderCodeService.ORDER_CODE_TASK_TYPE)+"','yyyy-MM-dd') ");
		this.insetOrUpdate(sb.toString());
	}
	
	/**
	 * 修改编码
	 * @param parameter 参数
	 * @return
	 */
	public void updateOrderCode(Map<String,Object> parameter){
		StringBuffer sb = new StringBuffer();
		sb.append(" UPDATE BASE_SEQUENCE SET ");
		sb.append(" SERIAL_NUMBER = "+parameter.get(CommonOrderCodeService.ORDER_CODE_SERIAL_NUMBER)+" ");
		sb.append(" WHERE 1=1 ");
		sb.append(" AND TASK_TYPE='"+parameter.get(CommonOrderCodeService.ORDER_CODE_TASK_TYPE)+"' ");
		sb.append(" AND SYS_DATE=TO_DATE('"+parameter.get(CommonOrderCodeService.ORDER_CODE_SYS_DATE)+"','yyyy-MM-dd') ");
		logger.info("updateOrderCode : "+sb.toString());
		this.insetOrUpdate(sb.toString());
	}

	/**
	 * 查询编码
	 * @param parameter 参数
	 * @return
	 */
	public Map<String,Object> queryOrderCode(Map<String,Object> parameter){
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT BS.TASK_TYPE,TO_CHAR(BS.SYS_DATE,'YYYY-MM-DD') AS SYS_DATE,(BS.SERIAL_NUMBER+1) AS SERIAL_NUMBER FROM BASE_SEQUENCE BS WHERE 1=1 ");
		sb.append(" AND BS.TASK_TYPE = '"+parameter.get(CommonOrderCodeService.ORDER_CODE_TASK_TYPE)+"'  ");
		sb.append(" AND BS.SYS_DATE = TO_DATE('"+parameter.get(CommonOrderCodeService.ORDER_CODE_SYS_DATE)+"','yyyy-MM-dd') ");
		List<Map<String,Object>> ls = this.queryForList(sb.toString());
		if(ls.size()>0)return ls.get(0);
		return null;
	}
	
	
	/**
	 * 查询编码 --前缀--省份缩写
	 * @param parameter 参数
	 * @return
	 */
	public String queryProvinceShort(Map<String,Object> parameter){
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT BR.REGIONCODE FROM BASE_REGION BR WHERE 1=1 ");
		sb.append(" AND BR.REGIONID = SUBSTR('"+parameter.get(CommonOrderCodeService.ORDER_CODE_REGION_ID)+"',0,2)||'0000' ");
		logger.info(sb);
		return this.queryForString(sb.toString());
	}
}