package com.cabletech.business.workflow.workorder.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.common.condition.StatisticQueryParameter;
import com.cabletech.business.workflow.workorder.service.WorkOrderHandledOverTimeService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.util.Page;

/**
 * 工单处理及时率统计
 * 
 * @author zhaobi
 * @author 杨隽 2012-04-28 进行方法的参数个数重构
 * 
 */
@SuppressWarnings("rawtypes")
@Namespace("/workflow")
@Results({ @Result(name = "list", location = "/workflow/workorder/workorder_overtime_list.jsp") })
@Action("/workOrderHandledOverTimeAction")
public class WorkOrderHandledOverTimeAction extends BaseAction {
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 故障处理及时率统计服务
	 */
	@Resource(name = "workOrderHandledOverTimeServiceImpl")
	private WorkOrderHandledOverTimeService workOrderHandledOverTimeService;
	/**
	 * 查询条件参数
	 */
	private StatisticQueryParameter parameter = new StatisticQueryParameter();

	/**
	 * 列表
	 * 
	 * @return String
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 数据
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void listdata() {
		UserInfo user = this.getUser();
		Page<Map<String, Object>> page = this.initPage();
		parameter.setUser(user);
		page = workOrderHandledOverTimeService.list(page, parameter);
		convertObjToJson(page);
	}

	public StatisticQueryParameter getModel() {
		return parameter;
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}

	public StatisticQueryParameter getParameter() {
		return parameter;
	}

	public void setParameter(StatisticQueryParameter parameter) {
		this.parameter = parameter;
	}
}