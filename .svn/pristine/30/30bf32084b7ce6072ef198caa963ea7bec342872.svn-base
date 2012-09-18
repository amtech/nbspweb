package com.cabletech.business.base.service;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import com.cabletech.business.base.model.UploadFile;

/**
 * 上传文件服务接口
 * 
 * @author 杨隽 2012-01-10 添加getFileInputStream方法
 * 
 */
public interface UploadFileService {

	/***************************************************************************
	 * 上传多个附件，并记录附件的相关信息到ANNEX_ADD_ONE，FILEPATHINFO表中。
	 * <l>uploadFile.saveFiles(files, ModuleCatalog.HIDDTROUBLEWATCH,
	 * userInfo.getRegionName(), entityId, "LP_HIDDANGER_REPORT",
	 * userInfo.getUserID(),"0");</l>
	 * @param files
	 *            上传文件列表，存储在session的FILES变量中。
	 * @param module
	 *            模块常量
	 * @param regionName
	 *            区域名称
	 * @param entityId
	 *            附件关联的实体id
	 * @param entityType
	 *            附件关联的表名
	 * @param uploader
	 *            附件上传人
	 * @param isUsable
	 *            资源是否有效 1：有效；0：表示无效
	 * @throws Exception
	 *             抛出的运行时异常，进行事务回滚
	 */
	public void saveFiles(List<FileItem> files, String module,
			String regionName, String entityId, String entityType,
			String uploader, String isUsable) throws Exception;

	/***************************************************************************
	 * 上传多个附件，并记录附件的相关信息到ANNEX_ADD_ONE，FILEPATHINFO表中。
	 * <l>uploadFile.saveFiles(files, ModuleCatalog.HIDDTROUBLEWATCH,
	 * userInfo.getRegionName(), entityId, "LP_HIDDANGER_REPORT",
	 * userInfo.getUserID());</l>
	 * 
	 * @param files
	 *            上传文件列表，存储在session的FILES变量中。
	 * @param module
	 *            模块常量
	 * @param regionName
	 *            区域名称
	 * @param entityId
	 *            附件关联的实体id
	 * @param entityType
	 *            附件关联的表名
	 * @param uploader
	 *            附件上传人
	 * @throws Exception
	 *             抛出的运行时异常，进行事务回滚
	 */
	public void saveFiles(List<FileItem> files, String module,
			String regionName, String entityId, String entityType,
			String uploader) throws Exception;

	/**
	 * 转派单 编辑后保存上传文件信息
	 * 
	 * @param files
	 *            文件名称
	 * @param regionName
	 *            区域名称
	 * @param entityId
	 *            附件关联的实体id
	 * @param entityType
	 *            附件关联的表名
	 * @param uploader
	 *            附件上传人
	 * @throws Exception
	 *             抛出的运行时异常，进行事务回滚
	 */
	public void saveFileInfos(List<UploadFile> files, String regionName,
			String entityId, String entityType, String uploader)
			throws Exception;

	/**
	 * 删除文件信息
	 * 
	 * @param id
	 *            String
	 * @throws Exception
	 */
	public void delById(String id) throws Exception;

	/**
	 * 获取上传文件
	 * 
	 * @param fileId
	 *            String
	 * @return
	 * @throws Exception
	 */
	public UploadFile getFileId(String fileId) throws Exception;

	/**
	 * 通过实体id，实体类型，获得相关附件信息。
	 * 
	 * @param entityId
	 *            String
	 * @param entityType
	 *            String
	 * @param useable
	 *            String
	 * @return
	 */
	public List<FileItem> getFiles(String entityId, String entityType,
			String useable) throws Exception;

	/**
	 * 通过实体id，实体类型获得相关的附件。
	 * 
	 * @param entityId
	 *            String
	 * @param entityType
	 *            String
	 * @param useable
	 *            String
	 */
	public List<UploadFile> getFilesByIds(String entityId, String entityType,
			String useable) throws Exception;

	/**
	 * 获取图片
	 * 
	 * @param entityId
	 *            String
	 * @param entityType
	 *            String
	 * @return
	 * @throws Exception
	 */
	public List<UploadFile> getImageFile(String entityId, String entityType)
			throws Exception;

	/**
	 * 获取图片
	 * 
	 * @param entityId
	 *            String
	 * @param entityType
	 *            String
	 * @param startTime
	 *            String
	 * @param endTime
	 *            String
	 * @return
	 * @throws Exception
	 */
	public List<UploadFile> getImageFile(String entityId, String entityType,
			String startTime, String endTime) throws Exception;

	/**
	 * 删除附件数据库信息
	 * 
	 * @param id
	 *            String
	 * @return
	 * @throws Exception
	 */
	public boolean delAnnexFile(String id) throws Exception;

	/**
	 * 获取上传文件的输入流
	 * 
	 * @param fileId
	 *            String
	 * @return
	 * @throws Exception
	 */
	public InputStream getFileInputStream(String fileId) throws Exception;
}
