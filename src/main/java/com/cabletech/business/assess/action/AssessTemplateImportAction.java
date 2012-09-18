package com.cabletech.business.assess.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.assess.model.AssessTemplate;
import com.cabletech.business.assess.service.AssessTemplateImportService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.externalresources.ExternalResourcesAccessService;

/**
 * 
 * 考核模板导入 ACTION
 * 
 * @author 杨隽 2012-07-31 创建
 */
@Namespace("/assess")
@Results({
		@Result(name = "input", location = "/assess/assess_template_import.jsp"),
		@Result(name = "preview", location = "/assess/assess_template_preview.jsp") })
@Action("/assessTemplateImportAction")
@InterceptorRefs({ @InterceptorRef("fileUploadStack"),
		@InterceptorRef("defaultStack") })
public class AssessTemplateImportAction extends
		BaseAction<AssessTemplate, String> {
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 预览导入数据跳转页面
	 */
	private static final String PREVIEW = "preview";
	/**
	 * 导入页面URL
	 */
	private static final String INPUT_URL = "/assess/assessTemplateImportAction!input.action";
	/**
	 * 模板导入数据业务服务
	 */
	@Resource(name = "assessTemplateImportServiceImpl")
	private AssessTemplateImportService assessTemplateImportService;
	/**
	 * 外部资源服务业务服务
	 */
	@Resource(name = "externalResourcesAccessService")
	private ExternalResourcesAccessService externalResourcesAccessService;
	/**
	 * 上传的数据文件
	 */
	private File file;
	/**
	 * 上传文件的内容类型
	 */
	private String fileContentType;
	/**
	 * 上传文件名
	 */
	private String fileFileName;
	/**
	 * 模板表单数据
	 */
	private AssessTemplate assessTemplate = new AssessTemplate();

	/**
	 * 转到表单输入
	 */
	public String input() throws Exception {
		return INPUT;
	}

	/**
	 * 导入数据预览（从导入文件中解析上传数据进行分页）
	 * 
	 * @return
	 */
	public String previewData() {
		// 上传导入数据文件
		String[] uploadInfo = uploadFile();
		if (!"S001".equals(uploadInfo[0])) {
			super.addMessage("提示:" + uploadInfo[2], INPUT_URL,
					SysConstant.WARNING);
			return SUCCESS;
		}
		String filePath = uploadInfo[1];
		try {
			Map<String, Object> resultMap = assessTemplateImportService
					.createItemPreviewData(assessTemplate, filePath);
			super.getRequest().setAttribute("tableMap", resultMap);
			return PREVIEW;
		} catch (Exception ex) {
			super.addMessage("导入数据不符合模板规范！", INPUT_URL, SysConstant.ERROR);
			return SUCCESS;
		}
	}

	/**
	 * 导入信息数据处理
	 * 
	 * @return
	 */
	public String importData() {
		UserInfo user = super.getUser();
		try {
			assessTemplateImportService.importItemData(assessTemplate, user);
			super.addMessage("导入数据成功！", INPUT_URL, SysConstant.SUCCESS);
		} catch (Exception ex) {
			super.addMessage("导入数据不符合模板规范！", INPUT_URL, SysConstant.ERROR);
		}
		return SUCCESS;
	}

	/**
	 * 导入单模板下载
	 * 
	 * @return
	 */
	public String downloadTemplate() {
		HttpServletResponse response = getResponse();
		response.reset();
		response.setContentType("application/octet-stream");
		try {
			String downloadFilePath = SysConstant.EXCEL_DOWNLOAD_TEMPLATES_PACKAGE_PATH
					+ "/assess_table_template_xls.zip";
			String templateFile = getClass().getResource(downloadFilePath)
					.getPath();
			InputStream in = new BufferedInputStream(new FileInputStream(
					templateFile));
			byte[] buffer = new byte[in.available()];
			in.read(buffer);
			in.close();
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String("考核表导入模板.zip".getBytes(), "iso-8859-1"));
			OutputStream out = response.getOutputStream();
			out.write(buffer);
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error("下载考核表导入模板出错：", e);
		}
		return null;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	@Override
	public AssessTemplate getModel() {
		return assessTemplate;
	}

	public AssessTemplate getAssessTemplate() {
		return assessTemplate;
	}

	public void setAssessTemplate(AssessTemplate assessTemplate) {
		this.assessTemplate = assessTemplate;
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}

	/**
	 * 执行上传导入数据的文件
	 * 
	 * @return String[] 上传文件信息：数组第一个元素为上传是否成功信息，第二个元素为上传后的数据文件路径
	 */
	private String[] uploadFile() {
		// FormFile file = getFile();
		String uploadCode = "S001";
		String filePathNameStr = "";
		String errorMsg = "";
		if (file != null) {
			String filename = fileFileName;
			if (StringUtils.isBlank(filename)) {
				uploadCode = "fileerror";
				errorMsg = "文件名为空！";
			}
			// 文件类型必须是xls的
			String fileExtName = filename
					.substring(filename.lastIndexOf(".") + 1);
			if (!"xls".equals(fileExtName) && !"xlsx".equals(fileExtName)) {
				uploadCode = "structerror";
				errorMsg = "文件类型不是xls格式!";
			}
			if (file.length() > 2000000) {
				uploadCode = "fileoutsize";
				errorMsg = "文件超过规定长度!";
			}
		} else {
			uploadCode = "fileerror";
			errorMsg = "文件上传错误!";
		}
		// 保存文件
		if ("S001".equals(uploadCode)) {
			filePathNameStr = saveFile(file);
			if (filePathNameStr == null) {
				uploadCode = "fileerror";
				errorMsg = "文件上传错误!";
			}
		}
		return new String[] { uploadCode, filePathNameStr, errorMsg };
	}

	/**
	 * 将上传的文件保存为临时文件
	 * 
	 * @param formFile
	 *            File
	 * @return String
	 */
	private String saveFile(File formFile) {
		if (formFile == null) {
			return null;
		}
		String uploadRoot = externalResourcesAccessService.getUploadroot();
		uploadRoot = uploadRoot + File.separator + "temp";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/ddHHmmss");
		String strTemp = formatter.format(new Date());
		String fileSptr = File.separator;
		String saveAsName = strTemp.substring(8, 16) + fileFileName;
		String relativePath = strTemp.substring(0, 4) + fileSptr
				+ strTemp.subSequence(5, 7);
		String absolutePath = uploadRoot + fileSptr + relativePath;
		String absolutePathFile = absolutePath + fileSptr + saveAsName;
		(new File(absolutePath)).mkdirs();
		// 保存文件
		try {
			InputStream streamIn = new FileInputStream(formFile);
			OutputStream streamOut = new FileOutputStream(absolutePathFile);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = streamIn.read(buffer, 0, 8192)) != -1) {
				streamOut.write(buffer, 0, bytesRead);
			}
			streamOut.close();
			streamIn.close();
			return absolutePathFile;
		} catch (Exception e) {
			logger.error("导入文件时出错:", e);
			return null;
		}
	}
}
