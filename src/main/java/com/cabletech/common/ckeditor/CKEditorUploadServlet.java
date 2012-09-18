package com.cabletech.common.ckeditor;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
/**
 * 提供CKEditer在线编辑器上传附件
 * @author 张会军
 *
 */
public class CKEditorUploadServlet extends HttpServlet {
	private static String baseDir;// CKEditor的根目录
	private static boolean debug = false;// 是否debug模式
	private static boolean enabled = false;// 是否开启CKEditor上传
	private static Hashtable allowedExtensions;// 允许的上传文件扩展名
	private static Hashtable deniedExtensions;// 阻止的上传文件扩展名
	private static SimpleDateFormat dirFormatter;// 目录命名格式:yyyyMM
	private static SimpleDateFormat fileFormatter;// 文件命名格式:yyyyMMddHHmmssSSS
	private Logger logger = Logger.getLogger(this.getClass());
	/**
	 * Servlet初始化方法
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void init() throws ServletException {
		// 从web.xml中读取debug模式
		debug = (new Boolean(getInitParameter("debug"))).booleanValue();
		if (debug)
			logger.info("\r\n---- SimpleUploaderServlet initialization started ----");
		// 格式化目录和文件命名方式
		dirFormatter = new SimpleDateFormat("yyyyMM");
		fileFormatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		// 从web.xml中获取根目录名称
		baseDir = getInitParameter("baseDir");
		// 从web.xml中获取是否可以进行文件上传
		enabled = (new Boolean(getInitParameter("enabled"))).booleanValue();
		if (baseDir == null) baseDir = "/UserFiles/";
		String realBaseDir = getServletContext().getRealPath(baseDir);
		File baseFile = new File(realBaseDir);
		if (!baseFile.exists()) {
			baseFile.mkdirs();
		}
		// 实例化允许的扩展名和阻止的扩展名
		allowedExtensions = new Hashtable(3);
		deniedExtensions = new Hashtable(3);
		// 从web.xml中读取配置信息
		allowedExtensions.put("File",stringToArrayList(getInitParameter("AllowedExtensionsFile")));
		deniedExtensions.put("File",stringToArrayList(getInitParameter("DeniedExtensionsFile")));
		allowedExtensions.put("Image",stringToArrayList(getInitParameter("AllowedExtensionsImage")));
		deniedExtensions.put("Image",stringToArrayList(getInitParameter("DeniedExtensionsImage")));
		allowedExtensions.put("Flash",stringToArrayList(getInitParameter("AllowedExtensionsFlash")));
		deniedExtensions.put("Flash",stringToArrayList(getInitParameter("DeniedExtensionsFlash")));
		if (debug)
			logger.info("---- SimpleUploaderServlet initialization completed ----\r\n");
	}
	/**
	 * servlet doget
	 * @param request 
	 * @param response 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	/**
	 * servlet doPost
	 * @param request 
	 * @param response 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (debug)
			logger.info("--- BEGIN DOPOST ---");
		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		// 从请求参数中获取上传文件的类型：File/Image/Flash
		String typeStr = request.getParameter("Type");
		if (typeStr == null) {
			typeStr = "File";
		}
		if (debug)
			logger.info(typeStr);
		// 实例化dNow对象，获取当前时间
		Date dNow = new Date();
		// 设定上传文件路径
		String currentPath = baseDir + typeStr + "/"
				+ dirFormatter.format(dNow);
		// 获得web应用的上传路径
		String currentDirPath = getServletContext().getRealPath(currentPath);
		// 判断文件夹是否存在，不存在则创建
		File dirTest = new File(currentDirPath);
		if (!dirTest.exists()) {
			dirTest.mkdirs();
		}
		// 将路径前加上web应用名
		currentPath = request.getContextPath() + currentPath;
		if (debug)
			logger.info(currentDirPath);
		// 文件名和文件真实路径
		String newName = "";
		String fileUrl = "";
		if (enabled) {
			// 使用Apache Common组件中的fileupload进行文件上传
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				List items = upload.parseRequest(request);
				Map fields = new HashMap();
				Iterator iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();
					if (item.isFormField())
						fields.put(item.getFieldName(), item.getString());
					else
						fields.put(item.getFieldName(), item);
				}
				// CEKditor中file域的name值是upload
				FileItem uplFile = (FileItem) fields.get("upload");
				// 获取文件名并做处理
				String fileNameLong = uplFile.getName();
				fileNameLong = fileNameLong.replace('\\', '/');
				String[] pathParts = fileNameLong.split("/");
				String fileName = pathParts[pathParts.length - 1];
				// 获取文件扩展名
				String ext = getExtension(fileName);
				// 设置上传文件名
				fileName = fileFormatter.format(dNow) + "." + ext;
				// 获取文件名(无扩展名)
				String nameWithoutExt = getNameWithoutExtension(fileName);
				File pathToSave = new File(currentDirPath, fileName);
				fileUrl = currentPath + "/" + fileName;
				if (extIsAllowed(typeStr, ext)) {
					int counter = 1;
					while (pathToSave.exists()) {
						newName = nameWithoutExt + "_" + counter + "." + ext;
						fileUrl = currentPath + "/" + newName;
						pathToSave = new File(currentDirPath, newName);
						counter++;
					}
					uplFile.write(pathToSave);
				} else {
					if (debug)
						logger.info("无效的文件类型： " + ext);
				}
			} catch (Exception ex) {
				if (debug)
					logger.error(ex);
			}
		} else {
			if (debug)
				logger.info("未开启CKEditor上传功能");
		}
		// CKEditorFuncNum是回调时显示的位置，这个参数必须有
		String callback = request.getParameter("CKEditorFuncNum");
		out.println("<script type=\"text/javascript\">");
		out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
				+ ",'" + fileUrl + "',''" + ")");
		out.println("</script>");
		out.flush();
		out.close();
		if (debug)
			logger.info("--- END DOPOST ---");
	}
	/**
	 * 获取文件名的方法
	 * @param fileName 
	 */
	private static String getNameWithoutExtension(String fileName) {
		return fileName.substring(0, fileName.lastIndexOf("."));
	}
	/**
	 * 获取扩展名的方法
	 * @param fileName 
	 */
	private String getExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}
	/**
	 * 字符串像ArrayList转化的方法
	 * @param str 
	 */
	private ArrayList stringToArrayList(String str) {
		if (debug)
			logger.info(str);
		String[] strArr = str.split("\\|");
		ArrayList tmp = new ArrayList();
		if (str.length() > 0) {
			for (int i = 0; i < strArr.length; ++i) {
				if (debug)
					logger.info(i + " - " + strArr[i]);
				tmp.add(strArr[i].toLowerCase());
			}
		}
		return tmp;
	}
	/**
	 * 判断扩展名是否允许的方法
	 * @param fileType 
	 * @param ext 
	 */
	private boolean extIsAllowed(String fileType, String ext) {
		ext = ext.toLowerCase();
		ArrayList allowList = (ArrayList) allowedExtensions.get(fileType);
		ArrayList denyList = (ArrayList) deniedExtensions.get(fileType);
		if (allowList.size() == 0) {
			if (denyList.contains(ext)) {
				return false;
			} else {
				return true;
			}
		}
		if (denyList.size() == 0) {
			if (allowList.contains(ext)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
}
