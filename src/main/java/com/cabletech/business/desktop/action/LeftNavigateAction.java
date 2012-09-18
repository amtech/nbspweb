package com.cabletech.business.desktop.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.Service.BaseInfoProvider;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.common.base.BaseAction;

/**
 * @author 首页导航菜单
 * @author 杨隽 2012-05-17 去除无用的导入、局部变量和类成员
 * 
 */
@Namespace("/desktop")
@Results({ @Result(name = "index", location = "/frames/default/left.jsp") })
@Action("/leftNavigate")
public class LeftNavigateAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8860598228737034417L;
	/**
	 * 基础信息提供服务,用户获取菜单
	 */
	@Resource(name = "baseInfoProvider")
	private BaseInfoProvider baseInfoProvider;

	/**
	 * 获取左侧菜单信息
	 */
	public String getmenu() {
		UserInfo userInfo = this.getUser();
		// 获取菜单类型
		String type = this.getParameter("type");
		String jumpup=this.getParameter("jumpup");
		//String actionUrl = getActionUrl(text) + type;
		//super.getRequest().setAttribute("action_url", actionUrl);
		// 获取有权限模块下级菜单,用来生成折叠导航
		List<Map<String, Object>> accordionList = baseInfoProvider.getMenuList(
				userInfo.getUserId(), type, "");
		this.getRequest().getSession()
				.setAttribute("menuheadList", accordionList);
		if(StringUtils.isNotBlank(jumpup)){
			this.getRequest().getSession()
			.setAttribute("jumpup", jumpup);
		}
		return "index";
	}

	/**
	 * 根据输入参数获取actionurl
	 * 
	 * @param text
	 *            String 输入参数
	 * @return String 返回的ActionUrl
	 */
	private String getActionUrl(String text) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "/desktop/mywork!index.action?menuid=");
		map.put("2", "/desktop/basework!index.action?menuid=");
		return map.get(text.trim());
	}

	/**
	 * 获取树形菜单
	 */
	public void getmenutree() {
		UserInfo userInfo = this.getUser();
		String menuid = this.getParameter("menuid");
		// 生成PID形式菜单
		List<Map<String, Object>> menuList = new ArrayList<Map<String, Object>>();
		menuList = baseInfoProvider.getMenuList(userInfo.getUserId(), "",
				menuid);
		super.convertObjToJson(menuList);
	}

	@Override
	protected void prepareViewModel() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void prepareSaveModel() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}

}
