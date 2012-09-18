package com.cabletech.business.base.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.cabletech.business.base.model.UploadFile;
import com.cabletech.business.base.service.UploadFileService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.externalresources.ExternalResourcesAccessService;


/**
 * 下载文件处理Action
 * @author wangt
 * @author 杨隽 2012-05-17 去除无用的导入、局部变量和类成员
 *
 */
@Namespace("/")
@Action("download")
public class DownloadAction extends BaseAction<UploadFile, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 上传文件服务
	@Resource(name = "uploadFileServiceImpl")
	private UploadFileService uploadFileService;
	@Resource(name = "externalResourcesAccessService")
	private ExternalResourcesAccessService externalResourcesService;

	@Override
	public UploadFile getModel() {
		return null;
	}

	@Override
	protected void prepareViewModel() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void prepareSaveModel() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 下载文件Action
	 * @throws Exception
	 */
	public void download() throws Exception {
		String fileId = getRequest().getParameter("fileid");
		UploadFile fileInfo = null;
		String relativePathFile = "";
		String saveAsT = "";
		// String fileSptr = File.separator;
		String fileSptr = "/";

		String absolutePathFile = "";

		fileInfo = uploadFileService.getFileId(fileId);
		relativePathFile = fileInfo.getSavePath();
		saveAsT = fileInfo.getOriginalName();
		//saveAs = new String(saveAsT.getBytes("utf-8"));
		absolutePathFile = externalResourcesService.getUploadroot() + fileSptr
				+ relativePathFile;
		logger.info("filePath : " + absolutePathFile);
		File file = new java.io.File(absolutePathFile);
		if (file.exists()) {
			FileInputStream fInputStream = null;
			OutputStream output = null;
			try {
				fInputStream = new FileInputStream(absolutePathFile);
				// 获得文件的长度
				String fileSize =Long.toString(file.length()) ;
				// 设置输出格式
				getResponse().addHeader("content-type",
						"application/x-msdownload;charset=utf-8");
				getResponse().addHeader(
						"Content-Disposition",
						"attachment; filename="
								+ URLEncoder.encode(saveAsT,"utf-8"));
				getResponse().addHeader("content-length",
						fileSize);
				output = getResponse().getOutputStream();
				byte[] b =new byte[Integer.valueOf(fileSize)];
				while ((fInputStream.read(b)) > 0) {
					output.write(b);
				}
				output.close();
				fInputStream.close();
			} catch (Exception e) {
				logger.error("SocketException: " + e.getMessage());
				output.close();
				fInputStream.close();
			}
		} else {
			logger.error("文件不存在无法下载！");
		}
	}
}
