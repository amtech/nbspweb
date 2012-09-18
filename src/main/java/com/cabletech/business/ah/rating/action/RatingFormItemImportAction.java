package com.cabletech.business.ah.rating.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.util.CollectionUtils;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.ah.rating.model.RatingForm;
import com.cabletech.business.ah.rating.service.RatingFormItemImportService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.externalresources.ExternalResourcesAccessService;

/**
 * 
 * 考核表导入 ACTION
 * 
 * @author 杨隽 2012-06-26 创建
 */
@Namespace("/ah")
@Results({
		@Result(name = "input", location = "/ah/rating/rating_form_import.jsp"),
		@Result(name = "preview", location = "/ah/rating/rating_form_import_preview.jsp"),
		@Result(name = "success", location = "/ah/rating/rating_form_import_result.jsp") })
@Action("/ratingFormItemImportAction")
@InterceptorRefs({ @InterceptorRef("fileUploadStack"),
		@InterceptorRef("defaultStack") })
public class RatingFormItemImportAction extends BaseAction<RatingForm, String> {
	private static final long serialVersionUID = 1L;
	// 预览导入数据跳转页面
	private static final String PREVIEW = "preview";
	// 导入数据业务服务
	@Resource(name = "ratingFormItemImportServiceImpl")
	private RatingFormItemImportService ratingFormItemImportService;
	// 外部资源服务业务服务
	@Resource(name = "externalResourcesAccessService")
	private ExternalResourcesAccessService externalResourcesAccessService;
	// 上传的数据文件
	private File file;
	// 上传文件的内容类型
	private String fileContentType;
	// 上传文件名
	private String fileFileName;
	/**
	 * 表单数据信息
	 */
	private RatingForm ratingForm = new RatingForm();

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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String preview() {
		// 上传导入数据文件
		String[] uploadInfo = uploadFile();
		if (!"S001".equals(uploadInfo[0])) {
			super.addMessage("提示:" + uploadInfo[2],
					"/ah/ratingFormItemImportAction!input.action",
					SysConstant.WARNING);
			return SUCCESS;
		}
		String filePath = uploadInfo[1];
		// 生成预览数据
		List list = ratingFormItemImportService.createItemPreviewData(filePath);
		super.sessionManager.put("PATROLITEM_IMPORT_FILEPATH", filePath);
		super.sessionManager.put("PREVIEW_IMPORT_LIST", list);
		return PREVIEW;
	}

	/**
	 * 导入数据预览（直接从会话中获取导入数据列表进行分页）
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public void previewList() {
		List list = (List) super.sessionManager.get("PREVIEW_IMPORT_LIST");
		createMap(list);
	}

	/**
	 * 导入信息数据处理（保存导入数据后进行分页）
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String importData() {
		UserInfo user = super.getUser();
		// 执行导入分析数据文件
		String filePath = super.getParameter("filePath");
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("file_path", filePath);
		parameterMap.put("user", user);
		parameterMap.put("ratingForm", ratingForm);
		Map resultMap = ratingFormItemImportService
				.importItemData(parameterMap);
		List list = (List) resultMap.get("valid_cell_list");
		super.sessionManager.put("import_data_map", resultMap);
		super.sessionManager.put("ERROR_IMPORT_LIST",
				resultMap.get("invalid_cell_list"));
		super.sessionManager.put("CELL_IMPORTED_LIST", list);
		return SUCCESS;
	}

	/**
	 * 导入信息数据处理（直接从会话中获取导入后合法数据列表进行分页）
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public void importDataList() {
		List list = (List) super.sessionManager.get("CELL_IMPORTED_LIST");
		createMap(list);
	}

	/**
	 * preview预执行方法
	 */
	public void preparePreview() {
		if (ratingForm == null) {
			ratingForm = new RatingForm();
		}
	}

	/**
	 * importData预执行方法
	 */
	public void prepareImportData() {
		if (ratingForm == null) {
			ratingForm = new RatingForm();
		}
	}

	/**
	 * 根据数据列表创建列表页面的分页结果集合
	 * 
	 * @param list
	 *            List 数据列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void createMap(List list) {
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("root", list);
		super.convertObjToJson(map);
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
			if (!filename.substring(filename.length() - 3, filename.length())
					.equals("xls")) {
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

	public RatingForm getRatingForm() {
		return ratingForm;
	}

	public void setRatingForm(RatingForm ratingForm) {
		this.ratingForm = ratingForm;
	}

	@Override
	public RatingForm getModel() {
		return ratingForm;
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}
}
