package com.cabletech.business.workflow.electricity.security.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.electricity.security.model.OeOutageAlarm;
import com.cabletech.business.workflow.electricity.security.service.OeOutageAlarmService;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 断电告警单处理Action
 * 
 * @author 杨隽 2012-05-04 创建
 * @author 杨隽 2012-05-08 删除view()方法
 * @author 杨隽 2012-05-17 去除无用的导入、局部变量和类成员
 * 
 */
@Namespace("/workflow")
@Results({ @Result(name = "list", location = "/workflow/electricity/security/oe_outagealarm_list.jsp") })
@Action("/oeOutageAlarmAction")
public class OeOutageAlarmAction extends
		OeDispatchTaskBaseAction<OeOutageAlarm, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 断电告警单查询表单数据
	private OeOutageAlarm oeOutageAlarm = new OeOutageAlarm();
	// 断电告警单业务处理服务
	@Resource(name = "oeOutageAlarmServiceImpl")
	private OeOutageAlarmService oeOutageAlarmService;

	/**
	 * 进入断电告警单列表页面
	 * 
	 * @return String
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 断电告警查询
	 */
	@SuppressWarnings("rawtypes")
	public void listData() {
		UserInfo userInfo = super.getUser();
		Page page = super.initPage();
		oeOutageAlarm.setPage(page);
		page = oeOutageAlarmService.getList(oeOutageAlarm, userInfo);
		super.setExcelParameter(page);
		convertObjToJson(page);
	}

	/**
	 * 故障忽略操作
	 * 
	 * @return String
	 */
	public String ignore() {
		UserInfo userInfo = super.getUser();
		String id = super.getParameter("id");
		oeOutageAlarmService.ignore(id, userInfo);
		super.addMessage("提示：断电告警单忽略成功!", ALARMLIST_PAGE_URL,
				SysConstant.SUCCESS);
		return SUCCESS;
	}

	@Override
	public OeOutageAlarm getModel() {
		return oeOutageAlarm;
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}
}
