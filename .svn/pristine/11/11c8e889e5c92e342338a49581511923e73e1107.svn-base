package com.cabletech.business.ah.excelreport.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.ah.excelreport.model.AhExcelReportRecode;
import com.cabletech.business.ah.excelreport.service.AhExcelReportRecodeService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.DateUtil;
import com.cabletech.common.util.Page;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * 地市每个月上传的excel报表action
 * @author wj
 *
 */
@Namespace("/ah")
@Results({
		@Result(name = "list",  location = "/ah/excelreport/excel_report_recode_list.jsp"),
		@Result(name = "input", location = "/ah/excelreport/excel_report_recode_upload.jsp")
})
@Action("/ahExcelReportRecodeAction")
public class AhExcelReportRecodeAction extends BaseAction<AhExcelReportRecode, String> {
	
	// 提示页面跳转路径
	public static final String LIST_PAGE_URL = "/workflow/rating/person_flow_list.jsp";
	public static final String INPUT_PAGE_URL = "/ah/ahExcelReportRecodeAction!input.action";
	public static final String FIX_DAY = "-01";

	private File reportFile;//与jsp表单中的名称对应
	private String reportFileFileName;
	private String reportFileContentType;//这两个是固定格式

	@Resource(name = "ahExcelReportRecodeServiceImpl")
	private AhExcelReportRecodeService ahExcelReportRecodeService;

	/**
	 * 列表界面
	 * 
	 * @return String
	 */
	public String list() {
		getRequest().setAttribute("isProvinceMobile", getUser().isProvinceMobile());
		return LIST;
	}
	
	/**
	 * 添加界面
	 * 
	 * @return String
	 */
	public String input() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		DateFormat d = new SimpleDateFormat("yyyy-MM");
		super.getRequest().setAttribute("yearmonth", d.format(c.getTime()));
		return INPUT;
	}
	
	/**
	 * 列表数据
	 */
	public void listData() {
		Map<String, Object> parameters = initCondition();
		Page<Map<String, Object>> page = this.initPage();
		ahExcelReportRecodeService.searchReportRecodes(parameters, page);
		try {
			getResponse().setContentType("application/json;charset=UTF-8");
			getResponse().setHeader("Cache-Control", "no-cache");
			Gson gson = new GsonBuilder().setDateFormat("yyyy年MM月").create();
			PrintWriter out = getResponse().getWriter();
			out.print(gson.toJson(page));
			out.flush();
		} catch (IOException e) {
			logger.error("处理JSON对象出错", e);
		}
	}

	/**
	 * 
	 * 保存报表
	 * @return
	 */
	public String saveReport() {
		UserInfo userInfo = super.getUser();
		String reportDate  = getRequest().getParameter("reportDate");
		String[] uploadInfo = ahExcelReportRecodeService.checkUploadFile(userInfo,reportFile,reportFileFileName,reportDate);
		if (!"S001".equals(uploadInfo[0])) {
			super.addMessage("提示：" + uploadInfo[2],INPUT_PAGE_URL,SysConstant.WARNING);
			return SUCCESS;
		}
		String filePath = uploadInfo[1];
		AhExcelReportRecode recode = new AhExcelReportRecode();
		recode.setFileUrl(filePath);
		recode.setRegionId(userInfo.getRegionId());
		recode.setReportDate(DateUtil.Str2UtilDate(reportDate+FIX_DAY, "yyyy-MM-dd"));
		recode.setUploadTime(new Date());
		recode.setUploadUserId(userInfo.getPersonId());
		ahExcelReportRecodeService.saveReportRecode(recode);
		addMessage("月报表上传成功！", INPUT_PAGE_URL, SysConstant.SUCCESS);
		return SUCCESS;  
	}
	
	/**
	 * 
	 * 下载报表
	 * @return
	 */
	public String downloadReport() {
		Map<String, Object> parameters = initCondition();
		Map<String, Object> recode = ahExcelReportRecodeService.searchReportRecode(parameters);
		String regionName = (String) recode.get("REGIONNAME");
		String reportDate = DateUtil.DateToString((Date) recode.get("REPORTDATE"), "yyyy年MM月");
		String reportPath = (String) recode.get("FILEURL");
		download(regionName+reportDate+"月报.xls",reportPath);
		return null;
	}
	
	/**
	 * 下载模板
	 * 
	 * @return
	 */
	public String downloadTemplate() {
		String downloadFilePath = SysConstant.EXCEL_DOWNLOAD_TEMPLATES_PACKAGE_PATH+ "/excel_report_template.xls";
		String templateFile = getClass().getResource(downloadFilePath).getPath();
		download("中国移动通信集团安徽有限公司代维管理月报模板.xls",templateFile);
		return null;
	}
	
	/**
	 * 下载
	 * @param name 名称
	 * @param path 路径
	 * @return
	 */
	private String download(String name,String path) {
		HttpServletResponse response = getResponse();
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		try {
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String(name.getBytes(), "iso-8859-1"));
			OutputStream out = response.getOutputStream();
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(path));
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			workbook.write(out);
		} catch (Exception e) {
			logger.error("下载文件出错：", e);
		}
		return null;
	}

	/**
	 * 封装查询条件
	 * @return Map
	 */
	private Map<String, Object> initCondition() {
		HttpServletRequest request = this.getRequest();
		Map<String, Object> map = new HashMap<String, Object>();
		UserInfo userInfo = super.getUser();
		if(userInfo.isProvinceMobile()){
			map.put("regionId", request.getParameter("regionId"));
		}else{
			map.put("regionId", userInfo.getRegionId());
		}
		String reportDate = request.getParameter("reportDate");
		if(StringUtils.isNotBlank(reportDate))reportDate = reportDate+FIX_DAY;
		map.put("reportDate",reportDate);
		map.put("id",request.getParameter("id"));
		return map;
	}

	@Override
	protected void prepareSaveModel() throws Exception {}

	@Override
	protected void prepareViewModel() throws Exception {}

	@Override
	public AhExcelReportRecode getModel() {
		return null;
	}
	
	/**
	 * @param reportFile the reportFile to set
	 */
	public void setReportFile(File reportFile) {
		this.reportFile = reportFile;
	}
	/**
	 * @param reportFileFileName the reportFileFileName to set
	 */
	public void setReportFileFileName(String reportFileFileName) {
		this.reportFileFileName = reportFileFileName;
	}
	/**
	 * @param reportFileContentType the reportFileContentType to set
	 */
	public void setReportFileContentType(String reportFileContentType) {
		this.reportFileContentType = reportFileContentType;
	}
}