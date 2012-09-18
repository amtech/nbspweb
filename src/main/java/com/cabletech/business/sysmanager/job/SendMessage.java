package com.cabletech.business.sysmanager.job;

import com.cabletech.business.sysmanager.model.WorkorderControlInfo;
import com.cabletech.business.sysmanager.service.WorkorderControlInfoService;

/**
 * @author zg 发送短信
 */
public interface SendMessage {
	/**
	 * 
	 */
	public void sendMesge();

	/**
	 * 
	 * @param info
	 *            WorkorderControlInfo
	 */
	public void sendMesge(WorkorderControlInfo info);

	/**
	 * 
	 * @param info
	 *            WorkorderControlInfo
	 * @param flag
	 *            int
	 * @param limitTime
	 *            long
	 * @param workorderControlInfoService
	 *            WorkorderControlInfoService
	 */
	public void sendMesge(WorkorderControlInfo info, int flag, long limitTime,
			WorkorderControlInfoService workorderControlInfoService);

}
