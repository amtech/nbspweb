package com.cabletech.common.filter;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cabletech.baseinfo.business.Service.BaseInfoProvider;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.model.UserOnlineLog;
import com.cabletech.business.base.service.UserInfoService;
import com.cabletech.business.base.service.UserOnlineLogService;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.config.GlobeConfigParameter;
import com.cabletech.common.externalresources.ExternalResourcesAccessService;
import com.cabletech.common.servlet.SysServlet;

/**
 * 单点登录过滤
 * @author wangt
 *
 */
public class SingleSignInFilter implements Filter {


	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * Default constructor.
	 */
	public SingleSignInFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}


	/**
	 * 过滤逻辑：首先判断单点登录的账户是否已经存在本系统中， 如果不存在使用用户查询接口查询出用户对象并设置在Session中
	 * @param request 
	 * @param response 
	 * @param chain 
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		// _const_cas_assertion_是CAS中存放登录用户名的session标志
		Object object = httpRequest.getSession().getAttribute(
				"_const_cas_assertion_");

		if (object != null) {
			Assertion assertion = (Assertion) object;
			String loginName = assertion.getPrincipal().getName();			
			 UserInfo user =(UserInfo)httpRequest.getSession().getAttribute("LOGIN_USER");
			// 第一次登录系统
			if (user == null) {
				WebApplicationContext web = WebApplicationContextUtils.getWebApplicationContext(httpRequest.getSession().getServletContext());
				UserInfoService userservice = (UserInfoService) web.getBean("userInfoServiceImpl");
				//获取用户信息
				UserInfo userInfo = userservice.getUserInfoByUserId(loginName);
				if (userInfo != null) {
				    ExternalResourcesAccessService   externalservice = (ExternalResourcesAccessService) web.getBean("externalResourcesAccessService");
				    //cas单点重定向
				    httpRequest.getSession().setAttribute("caslogoutredirect", externalservice.getCaslogoutredirect());
				    //cas 单点登出
				    httpRequest.getSession().setAttribute("caslogout", externalservice.getCaslogout());
				    //保存menu信息
					BaseInfoProvider baseInfoProvider = (BaseInfoProvider) web.getBean("baseInfoProvider");
					try {
						List<Map<String, Object>> indexmenuList = baseInfoProvider.getMenuList(userInfo.getUserId(), SysConstant.SYSTEM_ID, "");
						httpRequest.getSession().setAttribute("indexmenu", indexmenuList); 
					} catch (Exception e) {
						// TODO Auto-generated catch block
						logger.error(e);
					}
					userInfo.setLoginIp(httpRequest.getRemoteAddr());
					httpRequest.getSession().setAttribute("LOGIN_USER", userInfo); 
				}
				//将用户专业类型存放到会话中
				List<Map<String,Object>> businessTypeList=userInfo.getBusinessTypes();
				Map<Object,Object> businessTypeMap=new LinkedHashMap<Object,Object>();
				if(!CollectionUtils.isEmpty(businessTypeList)){
					for(int i=0;i<businessTypeList.size();i++){
						Map<String,Object> map=businessTypeList.get(i);
						if(SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C31.equals(map.get("CODEVALUE"))){
							businessTypeMap.put(map.get("CODEVALUE"), map.get("LABLE"));
						}
						if(SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C32.equals(map.get("CODEVALUE"))){
							businessTypeMap.put(map.get("CODEVALUE"), map.get("LABLE"));
						}
						if(SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C33.equals(map.get("CODEVALUE"))){
							businessTypeMap.put(map.get("CODEVALUE"), map.get("LABLE"));
						}
						if(SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C34.equals(map.get("CODEVALUE"))){
							businessTypeMap.put(map.get("CODEVALUE"), map.get("LABLE"));
						}
					}
				}
				httpRequest.getSession().setAttribute("businessTypeMap", businessTypeMap);
				GlobeConfigParameter config=(GlobeConfigParameter)web.getBean("globeConfigParameter");
				httpRequest.getSession().setAttribute("WEBGIS_DEPLOYNAME", config.getWebGisDeployname());
				// 保存用户登陆信息
				UserOnlineLog uol = new UserOnlineLog();
				UserOnlineLogService uolservice = (UserOnlineLogService) web
						.getBean("userOnlineLogServiceImpl");
				uol.setIp(request.getRemoteAddr());
				Date date = new Date();
				uol.setLogin_time(date);
				uol.setPersonid(userInfo.getPersonId());
				uolservice.save(uol);
				SysServlet.setUserinfo(userInfo);
			}

		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
