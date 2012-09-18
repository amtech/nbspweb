package com.cabletech.common.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cabletech.baseinfo.business.entity.UserInfo;

/**
 * 保存系统servlert
 * 
 * @author zhaobi
 * @date 2012-8-13
 */
public class SysServlet {
	/**
	 * Request
	 */
	private static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();
	/**
	 * Response
	 */
	private static ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();
	/**
	 * 用户信息
	 */
	private static UserInfo userinfo = new UserInfo();

	/**
	 * 获取请求
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) requestLocal.get();
	}

	/**
	 * 设置请求
	 * @param request
	 */
	public static void setRequest(HttpServletRequest request) {
		requestLocal.set(request);
	}

	/**
	 * 获取响应
	 * @return
	 */
	public static HttpServletResponse getResponse() {
		return (HttpServletResponse) responseLocal.get();
	}

	/**
	 * 设置响应
	 * @param response
	 */
	public static void setResponse(HttpServletResponse response) {
		responseLocal.set(response);
	}

	/**
	 * 获取session
	 * @return
	 */
	public static HttpSession getSession() {
		return (HttpSession) ((HttpServletRequest) requestLocal.get())
				.getSession();
	}

	/**
	 * 获取用户信息
	 * @return
	 */
	public static UserInfo getUserinfo() {
		return userinfo;
	}

	/**
	 * 设置用户信息
	 * @param userinfo
	 */
	public static void setUserinfo(UserInfo userinfo) {
		SysServlet.userinfo = userinfo;
	}

}
