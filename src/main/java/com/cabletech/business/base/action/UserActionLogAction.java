package com.cabletech.business.base.action;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.business.base.service.UserActionLogService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 用户操作日志查询
 * 
 * @author Administrator
 * 
 */
@Namespace("/system")
@Results({ @Result(name = "list", location = "/sysmanager/user_action_log_list.jsp") })
@Action("/actionlog")
public class UserActionLogAction extends BaseAction {
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 用户操作日志业务服务
	 */
	@Resource(name = "userActionLogServiceImpl")
	private UserActionLogService userActionLogService;

	/**
	 * 信息查询页面加载
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() {
		logger.info("list--------------------");
		return LIST;
	}

	/**
	 * 查询列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public void listData() {
		Page page = super.initPage();
		userActionLogService.searchlog(getParameter("userName"),
				getParameter("startTime"), getParameter("endTime"), page);
		super.setExcelParameter(page);
		super.convertObjToJson(page);
	}

	/**
	 * 删除信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String del() {
		String[] temp = new String[] {};
		if (StringUtils.isNotBlank(getParameter("ids"))) {
			temp = getParameter("ids").split(",");
		}
		userActionLogService.dellogs(temp);
		addMessage("提示：删除用户操作信息成功", "/system/actionlog!list.action",
				SysConstant.SUCCESS);
		return SUCCESS;
	}

	@Override
	public Object getModel() {
		return null;
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}
}
