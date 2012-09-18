package com.cabletech.business.ah.excelreport.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cabletech.baseinfo.base.BaseLog;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.ah.excelreport.dao.AhExcelReportRecodeDao;
import com.cabletech.business.ah.excelreport.model.AhExcelReportRecode;
import com.cabletech.business.ah.excelreport.service.AhExcelReportRecodeService;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.externalresources.ExternalResourcesAccessService;
import com.cabletech.common.util.Page;
/**
 * 存放每日家庭宽带的巡检具体实现
 * 
 * @author 陆道伟 2012-06-26 创建
 * 
 */
@Service
@Transactional
public class AhExcelReportRecodeServiceImpl extends BaseServiceImpl<AhExcelReportRecode, String> implements AhExcelReportRecodeService {

	@Resource(name = "ahExcelReportRecodeDao")
	private AhExcelReportRecodeDao ahExcelReportRecodeDao;
	
	// 外部资源服务业务服务
	@Resource(name = "externalResourcesAccessService")
	private ExternalResourcesAccessService externalResourcesAccessService;

	@Override
	protected AhExcelReportRecodeDao getBaseDao() {
		return ahExcelReportRecodeDao;
	}
	
	
	/* (non-Javadoc)
	 * @see com.cabletech.business.ah.excelreport.service.AhExcelReportRecodeService#checkUploadFile(com.cabletech.baseinfo.business.entity.UserInfo, java.io.File, java.lang.String, java.lang.String)
	 */
	@Override
	public String[] checkUploadFile(UserInfo userInfo,File reportFile,String reportFileFileName,String reportDate) {
		// FormFile file = getFile();
		String uploadCode = "S001";
		String filePathNameStr = "";
		String errorMsg = "";
		if (reportFile != null) {
			String filename = reportFileFileName;
			if (StringUtils.isBlank(filename)) {
				uploadCode = "fileerror";
				errorMsg = "文件名为空!";
			}
			// 文件类型必须是xls的
			if (!filename.substring(filename.length() - 3, filename.length()).equals("xls")) {
				uploadCode = "structerror";
				errorMsg = "文件类型不是xls格式!";
			}
			if (reportFile.length() > 2000000) {
				uploadCode = "fileoutsize";
				errorMsg = "文件超过规定长度!";
			}
			if (reportFile.length() ==0) {
				uploadCode = "emptyfile";
				errorMsg = "文件为空文档!";
			}
		} else {
			uploadCode = "fileerror";
			errorMsg = "文件上传错误!";
		}
		// 保存文件
		if ("S001".equals(uploadCode)) {
			filePathNameStr = transfersFile(userInfo,reportFile,reportDate);
			if (filePathNameStr == null) {
				uploadCode = "fileerror";
				errorMsg = "文件上传错误!";
			}
		}
		return new String[] { uploadCode, filePathNameStr, errorMsg };
	}
	
	/**
	 *  将文件转移上传文件目录中，并返回文件的绝对路径
	 * @param userInfo 用户实体
	 * @param reportFile 报表文件
	 * @param reportDate 报告日期
	 * @return
	 */
	private String transfersFile(UserInfo userInfo,File reportFile,String reportDate) {
		String saveFileDir =  externalResourcesAccessService.getUploadroot()+File.separator+"excelreport";
		String saveFileName = reportDate+userInfo.getRegionId()+".xls";
		if(!new File(saveFileDir).isDirectory())new File(saveFileDir).mkdir();
		String absolutePathFile = saveFileDir+File.separator+saveFileName;
		try {
		File saveFile = new File(absolutePathFile);
		if(saveFile.exists())saveFile.delete();
		boolean success = saveFile.createNewFile();
		OutputStream bos = null;
		InputStream stream = new FileInputStream(reportFile);
		if (success) {
			bos = new FileOutputStream(absolutePathFile);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
			bos.close();
			stream.close();
		} else {
			BaseLog.log("不能够创建文件!");
			throw new Exception("该目录不能够创建文件!");
		}
		} catch (Exception e) {
			absolutePathFile = "";
			logger.error(e);
		}
		return absolutePathFile;
	}
	
	
	/**
	 * 保存报表记录
	 * @param entity 实体
	 */
	public void saveReportRecode(AhExcelReportRecode entity){ 
		AhExcelReportRecode old = this.viewahExcelReportRecode(entity);
		if(null != old){ 
			old.setUploadUserId(entity.getUploadUserId());
			old.setUploadTime(entity.getUploadTime());
			ahExcelReportRecodeDao.save(old);
		}else{
			ahExcelReportRecodeDao.save(entity);
		}
	}
	
	/**
	 * 检索报表记录列表
	 * @param parameters 查询参数
	 * @param page 分页
	 */
	public Page searchReportRecodes(Map<String, Object> parameters,Page page){ 
		return ahExcelReportRecodeDao.searchReportRecodes( parameters, page);
	}
	
	/**
	 * 检索报表记录
	 * @param parameters 查询参数
	 */
	public Map<String, Object> searchReportRecode(Map<String, Object> parameters){
		return ahExcelReportRecodeDao.searchReportRecode(parameters);
	}
	
	/**
	 * 根据查询条件获取上传的月报表数据信息
	 * 
	 * @param report
	 *            AhExcelReportRecode 查询条件
	 * @return AhExcelReportRecode 上传的月报表数据信息
	 */
	public AhExcelReportRecode viewahExcelReportRecode(AhExcelReportRecode report){
		return ahExcelReportRecodeDao.viewahExcelReportRecode(report);
	}

}