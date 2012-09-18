package com.cabletech.business.workflow.fault.model;

import java.util.Date;

import com.cabletech.common.base.BaseEntity;

/**
 * 故障派单实体
 * 
 * @author 杨隽 2011-10-27 创建
 * @author 杨隽 2011-10-31 添加通用表格的类型常量“WTROUBLE_SENDTASK”
 * @author 杨隽 2011-12-12 添加“联系电话”属性
 * @author 杨隽 2012-02-22 添加“是否提交”属性和“提交标识”、“保存标识”常量
 * @author 杨隽 2012-05-04 去除表单提交标识常量
 * 
 */
public class FaultDispatch extends BaseEntity {
	// 故障派单处理中状态
	public static final String PROCESSING_STATE = "1";
	// 故障派单回单审核状态
	public static final String WAIT_APPROVED_STATE = "2";
	// 故障派单处理结束状态
	public static final String END_STATE = "3";
	// 故障派单已取消状态
	public static final String CANCELED_STATE = "4";
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 故障派单类型常量
	public static final String WTROUBLE_SENDTASK_TYPE = "WTROUBLE_SENDTASK";
	// 故障派单编号
	private String id;
	// 故障告警单编号
	private String alarmId;
	// 维护代维单位编号
	private String maintenanceId;
	// 驻点维护组编号
	private String patrolGroup;
	// 响应时限
	private Date response;
	// 处理期限
	private Date deadline;
	// 派单人
	private String creater;
	// 备注
	private String remark;
	// 状态
	private String state;
	// 工单编号
	private String taskCode;
	// 派单时间
	private Date sendTime;
	// 维护代维单位名称
	private String maintenanceName;
	// 驻点维护组名称
	private String patrolGroupName;
	// 派单人名称
	private String createrName;
	// 联系电话
	private String phone;
	// 是否提交表单
	private String isSubmited;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}

	public String getMaintenanceId() {
		return maintenanceId;
	}

	public void setMaintenanceId(String maintenanceId) {
		this.maintenanceId = maintenanceId;
	}

	public String getPatrolGroup() {
		return patrolGroup;
	}

	public void setPatrolGroup(String patrolGroup) {
		this.patrolGroup = patrolGroup;
	}

	public Date getResponse() {
		return response;
	}

	public void setResponse(Date response) {
		this.response = response;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getMaintenanceName() {
		return maintenanceName;
	}

	public void setMaintenanceName(String maintenanceName) {
		this.maintenanceName = maintenanceName;
	}

	public String getPatrolGroupName() {
		return patrolGroupName;
	}

	public void setPatrolGroupName(String patrolGroupName) {
		this.patrolGroupName = patrolGroupName;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIsSubmited() {
		return isSubmited;
	}

	public void setIsSubmited(String isSubmited) {
		this.isSubmited = isSubmited;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}
}
