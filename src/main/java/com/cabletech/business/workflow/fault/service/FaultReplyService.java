package com.cabletech.business.workflow.fault.service;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.fault.model.FaultAlert;
import com.cabletech.business.workflow.fault.model.FaultReply;

/**
 * 故障回单业务操作接口
 * 
 * @author 杨隽 2012-02-07 创建
 * @author 杨隽 2012-07-18 添加doEomsReply()方法
 * 
 */
public interface FaultReplyService {
	/**
	 * 故障回单
	 * 
	 * @param faultReply
	 *            FaultReply 输入的故障回单信息
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 */
	public void reply(FaultReply faultReply, UserInfo userInfo);

	/**
	 * 根据故障派单编号获取故障回单信息
	 * 
	 * @param dispatchId
	 *            String 故障派单编号
	 * @return FaultReply 故障回单信息
	 */
	public FaultReply getFaultReply(String dispatchId);

	/**
	 * 调用EOMS的回复工单内容
	 * 
	 * @param faultReply
	 *            FaultReply
	 * @param faultAlert
	 *            FaultAlert
	 */
	public void doEomsReply(FaultReply faultReply, FaultAlert faultAlert);
}
