package com.cabletech.business.monthcost.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.util.CollectionUtils;

import com.cabletech.baseinfo.base.BaseUtil;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.baseinfo.excel.ExportUtil;
import com.cabletech.baseinfo.excel.WorkBookData;
import com.cabletech.business.monthcost.service.MonthCheckCostService;
import com.cabletech.business.monthcost.service.MonthCostStaticService;
import com.cabletech.business.monthcost.service.MonthOtherCostService;
import com.cabletech.business.monthcost.service.MonthTimesCostService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.externalresources.ExternalResourcesAccessService;
import com.cabletech.common.util.Page;

/**
 * @author 周刚
 * 
 */
@Namespace("/monthcost")
@Results({
		@Result(name = "preview_check", location = "/monthcost/preview/check_import_item_preview.jsp"),
		@Result(name = "preview_time", location = "/monthcost/preview/times_import_item_preview.jsp"),
		@Result(name = "preview_other", location = "/monthcost/preview/other_import_item_preview.jsp"),
		@Result(name = "import", location = "/monthcost/import_File.jsp"),
		@Result(name = "Check", location = "/monthcost/statistical/statics4Check.jsp"),
		@Result(name = "Times", location = "/monthcost/statistical/statics4Times.jsp"),
		@Result(name = "Other", location = "/monthcost/statistical/statics4Other.jsp") })
@Action("/monthstatistical")
public class MonthCostStatisticalAction extends BaseAction<String, String> {
	/**
	 * 统计类
	 */
	// 预览导入数据跳转页面
	private static final String PREVIEW = "preview";
	String fileSptr = File.separator;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/ddHHmmss");
	/**
	 * 业务类
	 */
	@Resource(name = "monthCostStaticServiceImpl")
	private MonthCostStaticService staticService;

	/**
	 * 月度考核业务类
	 */
	@Resource(name = "monthCheckCostServiceImpl")
	private MonthCheckCostService monthCheckCostServiceImpl;

	/**
	 * 计次费用业务类
	 */
	@Resource(name = "monthTimesCostServiceImpl")
	private MonthTimesCostService monthTimesCostServiceImpl;

	/**
	 * 其他费用业务类
	 */
	@Resource(name = "monthOtherCostServiceImpl")
	private MonthOtherCostService monthOtherCostServiceImpl;
	private static final long serialVersionUID = 1L;
	// 上传的数据文件
	private File file = null;
	// 上传文件的内容类型
	private String fileContentType = "";
	// 上传文件名
	private String fileFileName = "";

	/**
	 * 上传文件错误代码
	 */
	private String uploadCode = "S001";

	/**
	 * 上传验证时候的信息
	 */
	private String errorMsg = "";

	/**
	 * 上传文件名
	 */
	private String filePathNameStr = "";
	// 类型
	private String type;
	// 外部资源服务业务服务
	@Resource(name = "externalResourcesAccessService")
	private ExternalResourcesAccessService externalResourcesAccessService;

	/**
	 * 月度考核统计
	 */
	public String statics() {
		String type = this.getRequest().getParameter("type");
		String lab = this.getRequest().getParameter("lab");
		this.getRequest().setAttribute("labq", lab);
		return type;
	}

	/**
	 * 转向导入页面
	 * 
	 * @return str
	 */
	public String importFileByType() {
		String type = this.getRequest().getParameter("type");
		String NameStr = "";
		String nameFileStr = "";
		if (type.equals("check")) {
			NameStr = "月考核信息导入模板.xls";
			nameFileStr = "check_template.xls";
		}
		if (type.equals("time")) {
			NameStr = "计次费用报表信息导入模板.xls";
			nameFileStr = "times_template.xls";
		}
		if (type.equals("other")) {
			NameStr = "其他费用报表信息导入模板.xls";
			nameFileStr = "other_template.xls";
		}
		this.getRequest().setAttribute("NameStr", NameStr);
		this.getRequest().setAttribute("type", type);
		this.getRequest().setAttribute("nameFileStr", nameFileStr);
		return "import";
	}

	/**
	 * 导入数据预览（直接从会话中获取导入数据列表进行分页）
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public void previewList() {
		String type = this.getRequest().getParameter("type");
		List list = (List) super.sessionManager.get("PREVIEW_IMPORT_LIST");
		createPage(list);
		super.getRequest().setAttribute("type", type);
	}

	/**
	 * 导入信息数据处理至数据库中（保存导入数据后进行分页）
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String importData() throws UnsupportedEncodingException {
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("file_path", getRequest().getParameter("filePath"));
		parameterMap.put("type", getRequest().getParameter("type"));
		parameterMap.put("region_id", super.getUser().getRegionId());
		parameterMap.put("user_sid", super.getUser().getPersonId());
		Map resultMap = getMapbyTypeAndParameMap(type, parameterMap);
		boolean resultMapflag = false;
		if (resultMap != null) {
			resultMapflag = true;
		}
		List list = (List) resultMap.get("valid_cell_list");
		super.sessionManager.put("import_data_map", resultMap);
		super.sessionManager.put("ERROR_IMPORT_LIST",
				resultMap.get("invalid_cell_list"));
		super.sessionManager.put("CELL_IMPORTED_LIST", list);
		super.sessionManager.put("type", type);
		return setSaveReturn(resultMapflag, type);
	}

	/**
	 * 根据类型和参数获取结果map
	 * 
	 * @param type
	 *            类型
	 * @param parameterMap
	 *            参数map
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getMapbyTypeAndParameMap(String type, Map parameterMap) {
		Map resultMap = null;
		if (type.equals("check")) {
			resultMap = monthCheckCostServiceImpl.importItemData(parameterMap);
		}
		if (type.equals("time")) {
			resultMap = monthTimesCostServiceImpl.importItemData(parameterMap);
		}
		if (type.equals("other")) {
			resultMap = monthOtherCostServiceImpl.importItemData(parameterMap);
		}
		return resultMap;
	}

	/**
	 * 设置此方法的返回值
	 * 
	 * @param type
	 *            类型
	 * @param b
	 *            标示
	 * @return
	 */
	private String setSaveReturn(boolean b, String type) {
		String url = "";
		if (type.equals("check"))
			url = "/monthcost/monthcheckcost!input.action?t=" + Math.random();
		if (type.equals("time"))
			url = "/monthcost/monthtimescost!input.action?t=" + Math.random();
		if (type.equals("other"))
			url = "/monthcost/monthothercost!input.action?t=" + Math.random();
		assertResult(b, "提交成功!", "提交成功!", url);
		return SUCCESS;
	}

	/**
	 * 返回显示信息结果
	 * 
	 * @param b
	 *            标示
	 * @param s_msg
	 *            成功信息
	 * @param f_msg
	 *            失败信息
	 * @param url
	 *            返回时路径
	 */
	private void assertResult(boolean b, String s_msg, String f_msg, String url) {
		if (b) {
			super.addMessage(s_msg, url, SysConstant.SUCCESS);
		} else {
			super.addMessage(f_msg, url, SysConstant.ERROR);
		}
	}

	/**
	 * 导入信息数据处理（直接从会话中获取导入后合法数据列表进行分页）
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public void importDataList() {
		List list = (List) super.sessionManager.get("CELL_IMPORTED_LIST");
		createPage(list);
	}

	/**
	 * 根据数据列表创建列表页面的分页结果集合
	 * 
	 * @param list
	 *            List 数据列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void createPage(List list) {
		Page page = super.initPage();
		if (CollectionUtils.isEmpty(list)) {
			page.setTotalCount(0);
			return;
		}
		List result = new ArrayList();
		for (int i = 0; i < page.getPageSize(); i++) {
			if (page.getFirst() + i + 1 > list.size()) {
				break;
			}
			result.add(list.get(page.getFirst() + i));
		}
		page.setResult(result);
		page.setTotalCount(list.size());
		super.convertObjToJson(page);
	}

	/**
	 * 根据类型 用户 文件路径获取导入后
	 * 
	 * @param Type
	 *            类型
	 * @param filePath
	 *            文件路径
	 * @param userinfo
	 *            用户
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList getUrlAndList(String Type, String filePath,
			UserInfo userinfo) {
		String inputUrl = "";
		List list = new ArrayList();
		if (type.equals("check")) {
			inputUrl = "/monthcost/monthcheckcost!input.action";
			list = monthCheckCostServiceImpl.createItemPreviewData(filePath,
					userinfo);
		}
		if (type.equals("time")) {
			inputUrl = "/monthcost/monthtimescost!input.action";
			list = monthTimesCostServiceImpl.createItemPreviewData(filePath,
					userinfo);
		}
		if (type.equals("other")) {
			inputUrl = "/monthcost/monthothercost!input.action";
			list = monthOtherCostServiceImpl.createItemPreviewData(filePath,
					userinfo);
		}
		ArrayList alist = new ArrayList();
		alist.add(inputUrl);
		alist.add(list);
		return alist;
	}

	/**
	 * 导入数据预览（从导入文件中解析上传数据进行分页）
	 * 
	 * @return
	 * @throws Exception
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String preview() throws Exception {
		// 上传导入数据文件
		String type = this.getRequest().getParameter("type");
		String[] uploadInfo = uploadFile(type);
		ArrayList list = getUrlAndList(type, uploadInfo[1], this.getUser());
		if (!"S001".equals(uploadInfo[0])) {
			super.addMessage("提示:" + uploadInfo[2], list.get(0).toString(),
					SysConstant.WARNING);
			return SUCCESS;
		}
		super.sessionManager.put(type + "_IMPORT_FILEPATH", uploadInfo[1]);
		super.sessionManager.put("PREVIEW_IMPORT_LIST", list.get(1));
		super.getRequest().setAttribute(type + "_IMPORT_FILEPATH",
				uploadInfo[1]);
		super.getRequest().setAttribute("type", type);
		return PREVIEW + "_" + type;
	}

	public String getFileSptr() {
		return fileSptr;
	}

	public void setFileSptr(String fileSptr) {
		this.fileSptr = fileSptr;
	}

	public SimpleDateFormat getFormatter() {
		return formatter;
	}

	public void setFormatter(SimpleDateFormat formatter) {
		this.formatter = formatter;
	}

	public String getUploadCode() {
		return uploadCode;
	}

	public void setUploadCode(String uploadCode) {
		this.uploadCode = uploadCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getFilePathNameStr() {
		return filePathNameStr;
	}

	public void setFilePathNameStr(String filePathNameStr) {
		this.filePathNameStr = filePathNameStr;
	}

	/**
	 * 根据类型获取错误信息
	 * 
	 * @param type
	 *            類型
	 * @return
	 */
	public String getMessageBytype(String type) {
		String errorMsg = "";
		if (type.equals("check")) {
			if (!fileFileName.equals("月考核信息导入模板.xls")) {
				errorMsg = "您所选择的文件名不对！请重新选择!";
			}
		}
		if (type.equals("time")) {
			if (!fileFileName.equals("计次费用报表信息导入模板.xls")) {
				errorMsg = "您所选择的文件名不对！请重新选择!";
			}
		}
		if (type.equals("other")) {
			if (!fileFileName.equals("其他费用报表信息导入模板.xls")) {
				errorMsg = "您所选择的文件名不对！请重新选择!";
			}
		}
		return errorMsg;
	}

	/**
	 * 执行上传导入数据的文件
	 * 
	 * @param type
	 *            類型
	 * @return String[] 上传文件信息：数组第一个元素为上传是否成功信息，第二个元素为上传后的数据文件路径
	 */
	private String[] uploadFile(String type) throws Exception {
		if (file != null) {
			errorMsg = getMessageBytype(type);
			if (StringUtils.isBlank(fileFileName)) {
				uploadCode = "fileerror";
				errorMsg = "文件名为空！";
			}
			if (!fileFileName.substring(fileFileName.length() - 3,
					fileFileName.length()).equals("xls")) {
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
		String relativePath = formatter.format(new Date()).substring(0, 4)
				+ fileSptr + formatter.format(new Date()).subSequence(5, 7);
		String absolutePath = externalResourcesAccessService.getUploadroot()
				+ File.separator + "temp" + fileSptr + relativePath;
		String absolutePathFile = absolutePath + fileSptr
				+ formatter.format(new Date()).substring(8, 16) + fileFileName;
		(new File(absolutePath)).mkdirs();
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

	/**
	 * 根据type 获取下载文件名称和文件
	 * 
	 * @param type
	 *            類型
	 * @return
	 */
	public List getListBytype(String type) {
		List list = new ArrayList();
		String NameStr = "";
		String nameFileStr = "";
		if (type.equals("check")) {
			NameStr = "月考核信息导入模板.xls";
			nameFileStr = "check_template.xls";
		}
		if (type.equals("time")) {
			NameStr = "计次费用报表信息导入模板.xls";
			nameFileStr = "time_template.xls";
		}
		if (type.equals("other")) {
			NameStr = "其他费用报表信息导入模板.xls";
			nameFileStr = "other_template.xls";
		}
		list.add(NameStr);
		list.add(nameFileStr);
		return list;
	}

	/**
	 * 导入单模板下载
	 * 
	 * @return
	 */
	public String downloadTemplate() {
		HttpServletResponse response = getResponse();
		String type = this.getRequest().getParameter("type");
		List list = getListBytype(type);
		if (list != null) {
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			try {
				response.setHeader("Content-Disposition",
						"attachment;filename="
								+ new String(list.get(0).toString().getBytes(),
										"iso-8859-1"));
				OutputStream out = response.getOutputStream();
				String downloadFilePath = SysConstant.EXCEL_DOWNLOAD_TEMPLATES_PACKAGE_PATH
						+ "/costexcel_templetes/" + list.get(1).toString();
				String templateFile = getClass().getResource(downloadFilePath)
						.getPath();
				POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(
						templateFile));
				HSSFWorkbook workbook = new HSSFWorkbook(fs);
				workbook.write(out);
			} catch (Exception e) {
				logger.error("下载" + list.get(0).toString() + "模板出错：", e);
			}
		}
		return null;

	}

	/**
	 * 获取 模板名字
	 * 
	 * @param type
	 *            String
	 * @param lab
	 *            String
	 * @return
	 */
	public String getTempleteName(String type, String lab) {
		String tempFileName = "";
		if (type.equals("Check") && lab.equals("1")) {
			tempFileName = "regionForCheck";
		}
		if (type.equals("Check") && lab.equals("2")) {
			tempFileName = "contractorForCheck";
		}
		if (type.equals("Check") && lab.equals("3")) {
			tempFileName = "specialtyForCheck";
		}
		if (type.equals("Times") && lab.equals("1")) {
			tempFileName = "regionForTimes";
		}
		if (type.equals("Times") && lab.equals("2")) {
			tempFileName = "contractorForTimes";
		}
		if (type.equals("Times") && lab.equals("3")) {
			tempFileName = "specialtyForTimes";
		}
		if (type.equals("Times") && lab.equals("4")) {
			tempFileName = "typeForTimes";
		}
		if (type.equals("Other") && lab.equals("1")) {
			tempFileName = "regionForOther";
		}
		if (type.equals("Other") && lab.equals("2")) {
			tempFileName = "contractorForOther";
		}
		if (type.equals("Other") && lab.equals("3")) {
			tempFileName = "typeForOther";
		}

		return tempFileName;
	}

	/**
	 * 
	 * @param type
	 *            String
	 * @param lab
	 *            String
	 * @param month
	 *            String
	 * @param year
	 *            String
	 * @return
	 */
	public String getExcelName(String type, String lab, String month,
			String year) {
		StringBuffer yearw = new StringBuffer();
		StringBuffer tempFileName = new StringBuffer();
		if (type.equals("Check") && lab.equals("1")) {
			tempFileName.append("按地区统计月考核费用表格");
		}
		if (type.equals("Check") && lab.equals("2")) {
			tempFileName.append("按组织机构统计月考核费用表格");
		}
		if (type.equals("Check") && lab.equals("3")) {
			tempFileName.append("按专业统计月考核费用表格");
		}
		if (type.equals("Times") && lab.equals("1")) {
			tempFileName.append("按地区统计计次费用表格");
		}
		if (type.equals("Times") && lab.equals("2")) {
			tempFileName.append("按代维组织机构统计计次费用表格");
		}
		if (type.equals("Times") && lab.equals("3")) {
			tempFileName.append("按专业统计费用表格");
		}
		if (type.equals("Times") && lab.equals("4")) {
			tempFileName.append("按计次类型统计费用表格");
		}
		if (type.equals("Other") && lab.equals("1")) {
			tempFileName.append("按区域统计其他费用表格");
		}
		if (type.equals("Other") && lab.equals("2")) {
			tempFileName.append("按代维机构统计其他费用表格");
		}
		if (type.equals("Other") && lab.equals("3")) {
			tempFileName.append("按费用类型统计其他费用表格");
		}

		return yearw.append("year").append("-").append(month).append("-")
				.append(tempFileName).append(".xls").toString();
	}

	/**
	 * 导出excel表格
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void detailExport() {
		String month = this.getRequest().getParameter("month");
		String year = this.getRequest().getParameter("year");
		String type = this.getRequest().getParameter("type");
		String lab = this.getRequest().getParameter("lab");
		try {
			WorkBookData workBookData = new WorkBookData();
			workBookData.setCommonData(new HashMap());
			List itemDetails = staticService.getDetailForExport(type, month,
					year, lab);
			List BigTotalList = staticService.getTotalDataList(type, month,
					year);
			Map recode = new HashMap();
			recode.put("ls", itemDetails);
			recode.put("total", BigTotalList);
			workBookData.addSheet(getExcelName(type, lab, month, year), recode);
			BaseUtil.setResponseHeader(this.getRequest(), this.getResponse(),
					getExcelName(type, lab, month, year));
			OutputStream output = getResponse().getOutputStream();
			String templateName = SysConstant.EXCEL_DOWNLOAD_TEMPLATES_PACKAGE_PATH
					+ "/costexcel_templetes/"
					+ getTempleteName(type, lab)
					+ ".xls";
			String templateFile = getClass().getResource(templateName)
					.getPath();
			InputStream is = new BufferedInputStream(new FileInputStream(
					templateFile));
			workBookData.setRes(is);
			workBookData.setDest(output);
			ExportUtil.multiSheet(workBookData);
			this.getRequest().setAttribute("year", year);
			this.getRequest().setAttribute("month", month);
			if (StringUtils.isBlank(lab)) {
				lab = "1";
			}
			this.getRequest().setAttribute("labq", lab);
		} catch (Exception e) {
			logger.error("导出异常:" + e.getMessage());
		}
	}

	/**
	 * 统计
	 */
	public String staticByType() {
		String month = this.getRequest().getParameter("month");
		String year = this.getRequest().getParameter("year");
		String type = this.getRequest().getParameter("type");
		String lab = this.getRequest().getParameter("lab");
		List<Map<String, Object>> dataListMap = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> littleTotalMap = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> BigTotalMap = new ArrayList<Map<String, Object>>();
		dataListMap = staticService.getDataMap(type, month, year, lab);// 根据当前时间月份和年份获取所有记录按照lab排序结果
		littleTotalMap = staticService
				.getLittleTotalMap(type, month, year, lab);// 小计
		BigTotalMap = staticService.getTotalData(type, month, year);// 总计
		this.getRequest().setAttribute("littleTotalMap", littleTotalMap);
		this.getRequest().setAttribute("DataMap", dataListMap);
		this.getRequest().setAttribute("totalData", BigTotalMap);
		this.getRequest().setAttribute("year", year);
		this.getRequest().setAttribute("month", month);
		if (StringUtils.isBlank(lab)) {
			lab = "1";
		}
		this.getRequest().setAttribute("labq", lab);
		return type;
	}

	@Override
	public String getModel() {
		return null;
	}

	@Override
	protected void prepareViewModel() throws Exception {

	}

	@Override
	protected void prepareSaveModel() throws Exception {

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

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
