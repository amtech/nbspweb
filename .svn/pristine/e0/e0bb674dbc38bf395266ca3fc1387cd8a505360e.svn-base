package com.cabletech.common.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.baseinfo.excel.ExportUtil;
import com.cabletech.common.util.Page;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * struts2 Action基类，所有的业务的action必须继续base包下的action
 * 
 * @param <T>
 *            T
 * @param <PK>
 *            PK
 * @author 赵璧
 * @author 杨隽 2012-02-10 添加initResponse()方法
 * @author 杨隽 2012-05-15 提取setExcelParameter()方法并放入基类
 * 
 */
public abstract class BaseAction<T, PK extends Serializable> extends
		ActionSupport implements ModelDriven<T>, Preparable,
		ServletRequestAware, ServletResponseAware, SessionAware {
	public static String CONTENT_TYPE = "application/vnd.ms-excel";
	public Logger logger = Logger.getLogger(this.getClass());
	/**
* 
*/
	private static final long serialVersionUID = 1L;
	// 载入list页面
	public static final String LIST = "list";
	public static final String VIEW = "view";
	public static final String RELOAD = "reload";

	// 默认每页显示条数 添加： 汪杰 2011-12-31
	public static final int DEFAULT_PAGE_SIZE = 15;
	private String pageNo; // 当前页码

	private HttpServletResponse response;
	private HttpServletRequest request;
	private ServletContext context;
	@SuppressWarnings("rawtypes")
	public Map sessionManager;
	// 处理日期JSON类型格式
	private String datafomat = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 获取参数
	 * 
	 * @param name
	 *            String
	 * @return String
	 */
	public String getParameter(String name) {
		return request.getParameter(name);
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public ServletContext getContext() {
		return context;
	}

	/**
	 * 获取当前登录用户信息
	 * 
	 * @return
	 */
	public UserInfo getUser() {
		return (UserInfo) sessionManager.get(SysConstant.SESSION_USERINFO_KEY);
	}

	/**
	 * Action函数，显示新增或修改Entity界面。建议return INPUT
	 */
	@Override
	public String input() throws Exception {
		return INPUT;
	}

	/**
	 * Action函数，显示Entity详细信息界面,建议return VIEW
	 */
	public String view() throws Exception {
		return VIEW;
	}

	/**
	 * 在input()前执行二次绑定
	 */
	public void prepareInput() throws Exception {
		prepareViewModel();
	}

	/**
	 * 在view()前执行二次绑定
	 */
	public void prepareView() throws Exception {
		prepareViewModel();
	}

	/**
	 * 在view()前执行二次绑定
	 */
	public void prepareSave() throws Exception {
		prepareSaveModel();
	}

	/**
	 * 等同于prepare()的内部函数，供prepardView()函数调用
	 */
	protected abstract void prepareViewModel() throws Exception;

	/**
	 * 等同于prepare()的内部函数，供prepardSave()函数调用
	 */
	protected abstract void prepareSaveModel() throws Exception;

	/**
	 * prepare
	 */
	public void prepare() throws Exception {
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * 页面跳转
	 * 
	 * @param info
	 *            提示信息
	 * @param url
	 *            跳转url
	 * @param errorLevel
	 *            错误级别
	 * @param values
	 *            暂时没用
	 */
	public void addMessage(String info, String url, String errorLevel,
			Object... values) {
		if (!"".equals(url)) {
			if (url.indexOf("-") != -1) {
				url = "javascript:history.go(" + url + ")";
			} else {
				url = "javascript:location.href='" + request.getContextPath()
						+ "" + url + "'";
			}
		} else {
			url = "javascript:history.go(-1)";
		}
		request.setAttribute("info", info);
		request.setAttribute("url", url);
		request.setAttribute("errorLevel", errorLevel);
	}

	/**
	 * 将单个对象转换成JSON对象
	 * 
	 * @param obj
	 *            Object
	 */
	public void convertObjToJson(Object obj) {
		try {
			response.setContentType("application/json;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			Gson gson = new GsonBuilder().setDateFormat(this.datafomat)
					.create();
			PrintWriter out = response.getWriter();
			out.print(gson.toJson(obj));
			out.flush();
		} catch (IOException e) {
			logger.error("处理JSON对象出错", e);
		}
	}

	/**
	 * 将单个对象转换成JSON对象
	 * 
	 * @param obj
	 *            Object
	 */
	public String convertObjToJsonStr(Object obj) {
		try {

			Gson gson = new GsonBuilder().setDateFormat(this.datafomat)
					.create();

			return gson.toJson(obj);
		} catch (Exception e) {
			logger.error("处理JSON对象出错", e);
			return "";
		}
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.sessionManager = session;
	}

	/**
	 * 获取分页工具
	 * 
	 * 汪杰 2011-12-31 添加
	 */
	@SuppressWarnings("rawtypes")
	public Page initPage() {
		int limit = 0;
		int pageNo = 1;
		String parameter_limit = request.getParameter("rows");
		String parameter_pageNo = this.getPageNo();
		if (StringUtils.isBlank(parameter_limit)) {
			limit = DEFAULT_PAGE_SIZE;
		} else {
			limit = Integer.valueOf(parameter_limit);
		}
		if (StringUtils.isBlank(parameter_pageNo)
				|| parameter_pageNo.equals("0")) {
			pageNo = 1;
		} else {
			pageNo = Integer.valueOf(parameter_pageNo);
		}
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("rows", limit);
		Page page = new Page();
		page.setPageSize(limit);
		page.setPageNo(pageNo);
		return page;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * 向界面输出html
	 * 
	 * @param html
	 *            String
	 * @param isCache
	 *            boolean
	 * @throws IOException
	 */
	public void outPrint(String html, boolean isCache) throws IOException {
		if (isCache) {
			nocache();
		}
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(html);
		out.flush();
	}

	/**
	 * 去除缓存
	 */
	public void nocache() {
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
	}

	/**
	 * 设置导出Excel报表下载的文件头
	 * 
	 * @param response
	 *            HttpServletResponse 响应头
	 * @param outfilename
	 *            String 导出的文件名
	 * @throws Exception
	 */
	protected void initResponse(HttpServletResponse response, String outfilename)
			throws Exception {
		response.reset();
		response.setContentType(CONTENT_TYPE);
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(outfilename.getBytes(), "iso-8859-1"));

	}

	/**
	 * 设置excel导出参数
	 * 
	 * @param page
	 *            Page 分页数据信息
	 */
	@SuppressWarnings("rawtypes")
	protected void setExcelParameter(Page page) {
		Map<String, Object> excelParemerts = new HashMap<String, Object>();
		excelParemerts.put("sql", page.getSql());
		ExportUtil.intExportParameters(getRequest(), excelParemerts);
	}
}