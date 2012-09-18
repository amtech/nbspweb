package com.cabletech.business.login.action;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.util.CollectionUtils;

import com.cabletech.baseinfo.base.BaseConfig;
import com.cabletech.baseinfo.business.Service.BaseInfoProvider;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.model.UserOnlineLog;
import com.cabletech.business.base.service.UserInfoService;
import com.cabletech.business.base.service.UserOnlineLogService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.config.GlobeConfigParameter;
import com.cabletech.common.servlet.SysServlet;

/**
 * 登录Action
 * 
 * @author Administrator
 * 
 */
@Namespace("/login")
@Results({ @Result(name = "index", location = "/frames/default/main.jsp"),
		@Result(name = "check", location = "/frames/default/login.jsp") })
@Action("/loginAction")
public class LoginAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String userid;
	private String password;
	@Resource(name = "userInfoServiceImpl")
	private UserInfoService userInfoService;
	@Resource(name = "globeConfigParameter")
	private GlobeConfigParameter config;
	@Resource(name = "baseInfoProvider")
	private BaseInfoProvider baseInfoProvider;
	@Resource(name = "userOnlineLogServiceImpl")
	private UserOnlineLogService onlineLogService; 

	/**
	 * 转向登录页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String index() throws Exception {
		userid = null;
		password = null;
		return "check";
	}

	/**
	 * 对登录用户进行认证
	 * 
	 * @return
	 * @throws Exception
	 */
	public String security() throws Exception {
		if ("".equals(userid)) {
			getRequest().setAttribute("msg", "用户名不能为空");
			return "check";
		}
		UserInfo userInfo = userInfoService.getUserInfoByUserId(userid);
		if (userInfo == null) {
			getRequest().setAttribute("msg", "用户或密码不正确");
			userid = "";
			return "check";
		} else {
			if (!userInfo.getPassword().equals(password)) {
				getRequest().setAttribute("msg", "用户或密码不正确");
				return "check";
			}
		}
		List<Map<String, Object>> indexmenuList = baseInfoProvider.getMenuList(
				userInfo.getUserId(), SysConstant.SYSTEM_ID, "");
		userInfo.setLoginIp(getRequest().getRemoteAddr());
		sessionManager.put("indexmenu", indexmenuList);
		sessionManager.put(SysConstant.SESSION_USERINFO_KEY, userInfo);
		Map<String, Object> menumap = baseInfoProvider.getMenuList(
				userInfo.getUserId(), SysConstant.SYSTEM_ID,
				SysConstant.MENU_LV, BaseConfig.EXTJS_JSON_TYPE);
		sessionManager.put("menuMap", menumap);
		// 加载用户设置
		// 将用户专业类型存放到会话中
		List<Map<String, Object>> businessTypeList = userInfo
				.getBusinessTypes();
		Map<Object, Object> businessTypeMap = new LinkedHashMap<Object, Object>();
		if (!CollectionUtils.isEmpty(businessTypeList)) {
			for (int i = 0; i < businessTypeList.size(); i++) {
				Map<String, Object> map = businessTypeList.get(i);

				if (SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C31.equals(map
						.get("CODEVALUE"))) {
					businessTypeMap.put(map.get("CODEVALUE"), map.get("LABLE"));
				}
				if (SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C32.equals(map
						.get("CODEVALUE"))) {
					businessTypeMap.put(map.get("CODEVALUE"), map.get("LABLE"));
				}
				if (SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C33.equals(map
						.get("CODEVALUE"))) {
					businessTypeMap.put(map.get("CODEVALUE"), map.get("LABLE"));
				}
				if (SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C34.equals(map
						.get("CODEVALUE"))) {
					businessTypeMap.put(map.get("CODEVALUE"), map.get("LABLE"));
				}
			}
		}
		// 保存用户登陆信息
		UserOnlineLog uol = new UserOnlineLog();
		uol.setIp(getRequest().getRemoteAddr());
		Date date = new Date();
		uol.setLogin_time(date);
		uol.setPersonid(userInfo.getPersonId());
		onlineLogService.save(uol);
		SysServlet.setUserinfo(userInfo);
		sessionManager.put("businessTypeMap", businessTypeMap);
		sessionManager.put("WEBGIS_DEPLOYNAME", config.getWebGisDeployname());
		return "index";
	}

	/**
	 * 登出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String logout() throws Exception {
		sessionManager.clear();
		return null;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
