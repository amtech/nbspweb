package com.cabletech.business.base.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.cabletech.business.base.service.UploadFileService;

/**
 * 图片显示Servlet
 * 
 * @author 杨隽 2012-01-10 创建
 * 
 */
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private XmlWebApplicationContext applicationContext;

	/**
	 * Servlet init
	 */
	public void init() {
		ServletConfig config = this.getServletConfig();
		ServletContext servletContext = config.getServletContext();
		applicationContext = (XmlWebApplicationContext) WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
	}

	/**
	 * Servlet doGet
	 * @param request 
	 * @param response 
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		image(request, response);
	}

	/**
	 * Servlet doPost
	 * @param request 
	 * @param response 
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		image(request, response);
	}

	/**
	 * 图片显示方法
	 * 
	 * @param request 
	 * @param response 
	 * @throws ServletException 
	 * @throws IOException 
	 */
	public void image(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String imageId = (String) request.getParameter("imageId");
			outputImage(response, imageId);
		} catch (Exception ex) {
			response.getWriter().write("");
		}
	}

	/**
	 * 输出图片
	 * 
	 * @param response 
	 * @param imageId 
	 * @throws Exception
	 * @throws IOException
	 */
	private void outputImage(HttpServletResponse response, String imageId)
			throws Exception, IOException {
		UploadFileService uploadFileService = (UploadFileService) applicationContext
				.getBean("uploadFileServiceImpl");
		InputStream input = uploadFileService.getFileInputStream(imageId);
		byte[] image = new byte[input.available()];
		ServletOutputStream out = response.getOutputStream();
		int len = 0;
		while ((len = input.read(image)) != -1) {
			out.write(image, 0, len);
		}
		out.flush();
		out.close();
	}
}
