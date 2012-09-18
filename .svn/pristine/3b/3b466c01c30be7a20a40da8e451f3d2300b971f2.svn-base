package com.cabletech.business.wplan.plan.service;

import java.util.List;
import java.util.Map;

import com.cabletech.business.wplan.plan.model.Patrolinfo;
import com.cabletech.common.util.Page;

/**
 * 巡检执行信息
 * 
 * @author zhaobi
 * 
 */
public interface PatrolinfoExecuteService {

	/**
	 * 按指定条件获得巡检执行信息
	 * 
	 * @param patrolinfo
	 *            Patrolinfo
	 * @param page
	 *            Page
	 * @return
	 */
	public Page getAllPatrolScheduleInfo(Patrolinfo patrolinfo, Page page);

	/**
	 * 按指定条件获得巡检结果信息
	 * 
	 * @param patrolinfo
	 *            Patrolinfo
	 * @param page
	 *            Page
	 * @return
	 */
	public Page getAllPatrolResultInfo(Patrolinfo patrolinfo, Page page);

	/**
	 * 获取未巡检明细
	 * 
	 * @param planid
	 *            String
	 * @param page
	 *            Page
	 * @return
	 */
	public Page getAllLostDetail(String planid, Page page);

	/**
	 * 获取已巡检明细
	 * 
	 * @param planid
	 *            String
	 * @param page
	 *            Page
	 * @return
	 */
	public Page getAllOverDetail(String planid, Page page);

	/**
	 * 获取巡检资源的巡检信息
	 * 
	 * @param rid
	 *            String
	 * @return
	 */
	public Map<String, Object> getPatrolResourceDetail(String rid);

	/**
	 * 获取已巡检RFID
	 * 
	 * @param rid
	 *            巡检执行结果
	 * @param page
	 *            Page
	 * @return
	 */
	public Page getOverRFIDDetail(String rid, Page page);

	/**
	 * 获取未巡检RFID
	 * 
	 * @param rid
	 *            String
	 * @param resourceid
	 *            资源ID
	 * @param resourcetype
	 *            资源类型
	 * @param page
	 *            Page
	 * @return
	 */
	public Page getLostRFIDDetail(String rid, String resourceid,
			String resourcetype, Page page);

	/**
	 * 获取异常项总数根据RID
	 * 
	 * @param rid
	 *            巡检结果ID
	 * @return
	 */
	public Map<String, Object> getExceptionItemCount(String rid);

	/**
	 * 获取巡检项明细
	 * 
	 * @param rid
	 *            执行结果
	 * @param page
	 *            Page
	 * @return
	 */
	public Page getItemDetail(String rid, Page page);
	
	/**
	 * 获取巡检项巡检结果明细 EXCEL导出使用 无分页
	 * 
	 * 
	 * 后期优化
	 * 
	 * @param rid
	 *            String
	 * @return
	 */
	public List getItemDetailForExport(String rid);
}
