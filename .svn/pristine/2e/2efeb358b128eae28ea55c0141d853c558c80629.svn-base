package com.cabletech.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * NoSession过滤器
 * 
 * @author wangt
 * 
 */
public class NoSessionFilter implements Filter {
	private static Logger log = Logger.getLogger(NoSessionFilter.class);

	/**
	 * 初始化
	 * 
	 * @param filterConfig
	 *            过滤器配置
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/**
	 * destroy 方法
	 */
	public void destroy() {
	}

	/**
	 * 过滤器实现
	 * 
	 * @param request
	 *            ServletRequest
	 * @param response
	 *            ServletResponse
	 * @param chain
	 *            FilterChain
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest rq = (HttpServletRequest) request;
			HttpServletResponse rp = (HttpServletResponse) response;
			String url = "";
			String page = "/bspweb/SystemManage/nosession.jsp";
			url = rq.getRequestURI();
			if (!url.equals(page)
					&& !url.equals("/bspweb/manager/sortUser.jsp")
					&& !url.equals("/bspweb/login_index.jspx")
					&& !url.equals("/bspweb/SortAction.do")
					&& !url.equals("/bspweb/login.do")
					&& !url.equals("/bspweb/SSOLogin.do")
					&& !url.equals("/bspweb/frames/head.jsp")
					&& !url.equals("/bspweb/imageUpload_upload.jspx")
					&& !url.equals("/bspweb/upload/send.jsp")
					&& !url.equals("/bspweb/frames/menu.jsp")
					&& !url.equals("/bspweb/frames/controlframe.jsp")
					&& !url.equals("/bspweb/frames/main.jsp")
					&& !url.equals("/bspweb/frames/foot.jsp")
					&& !url.equals("/bspweb/login/relogin.jsp")
					&& !url.equals("/bspweb/baseStationAction_view.jspx")
					&& !url.equals("/bspweb/sendTaskAction_exportXml.jspx")) {

				if (rq.getSession(false) == null) {
					rp.sendRedirect(page);
				} else {
					chain.doFilter(request, response);
				}
			} else {
				chain.doFilter(request, response);
			}
		} catch (Exception e) {
			log.error(e);
			log.warn("session过期处理异常:" + e.getMessage());
		}
	}
}
