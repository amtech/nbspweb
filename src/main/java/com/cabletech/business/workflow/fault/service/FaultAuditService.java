package com.cabletech.business.workflow.fault.service;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.fault.model.FaultAudit;

/**
 * 故障回单审核业务操作接口
 * 
 * @author 杨隽 2012-02-07 创建
 * @author 杨隽 2012-02-15 删除getAuditHistoryList()方法
 * 
 */
public interface FaultAuditService {
	/**
	 * 故障回单审核
	 * 
	 * @param faultAudit
	 *            FaultAudit 故障回单审核输入信息
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 */
	public void audit(FaultAudit faultAudit, UserInfo userInfo);
}
