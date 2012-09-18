package com.cabletech.business.base.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.fileupload.FileItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.base.dao.AnnexAddOneDao;
import com.cabletech.business.base.dao.UploadFileDao;
import com.cabletech.business.base.model.AnnexAddOne;
import com.cabletech.business.base.model.ModuleCatalog;
import com.cabletech.business.base.model.UploadFile;
import com.cabletech.business.base.service.UploadFileService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.externalresources.ExternalResourcesAccessService;
import com.cabletech.common.util.DateUtil;

/**
 * 上传文件服务实现
 * 
 * @author zhaobi
 * @author 杨隽 2012-01-10 添加getFileInputStream方法
 * 
 */
@Service
public class UploadFileServiceImpl extends BaseServiceImpl implements
		UploadFileService {

	@Resource(name = "uploadFileDao")
	private UploadFileDao uploadDao;

	@Resource(name = "annexAddOneDao")
	private AnnexAddOneDao annexAddOneDao;

	@Resource(name = "externalResourcesAccessService")
	private ExternalResourcesAccessService externalService;

	@Override
	@Transactional
	public void saveFiles(List<FileItem> files, String module,
			String regionName, String entityId, String entityType,
			String uploader, String isUsable) throws Exception {
		if ("BS".equals(entityType)) {
			entityType = "RS_BASESTATION";
		}
		if ("RS".equals(entityType)) {
			entityType = "RS_REPEATER";
		}

		if ("HT".equals(entityType)) {
			entityType = "BS_HIDD_TROUBLE";
		}
		if ("EQU".equals(entityType)) {
			entityType = "RS_EQUIPMENT";
		}

		UploadFile uploadFile = null;
		AnnexAddOne addOne = null;
		// 目录结构设置 区域+业务名称+年份+月份+文件（文件格式 年月日+xx公司xx文件+文档编号（fileseq）.doc）
		String fileSptr = "/";

		String relativePath = regionName + fileSptr + ModuleCatalog.get(module)
				+ fileSptr + DateUtil.getNowDateString("yyyy年") + fileSptr
				+ DateUtil.getNowDateString("MM月");
		String absolutePath = externalService.getUploadroot() + fileSptr
				+ relativePath;
		createCatalog(absolutePath);
		if (files != null) {
			// 单个文件上传
			for (int i = 0; i < files.size(); i++) {
				FileItem item = (FileItem) files.get(i);
				String fileName = item.getName();
				fileName = fileName.replaceAll("\\\\", fileSptr);
				if (fileName.indexOf(fileSptr) != -1) {
					fileName = fileName.substring(fileName
							.lastIndexOf(fileSptr) + 1);
				}
				// 对文件名称进行分割，从名称中分割出名称与文件类型 例如：xx年**公司接地电阻测试工作计划.doc
				// 分割为 xx年**公司接地电阻测试工作计划 与 .doc
				Integer separatorsIndex = fileName.lastIndexOf(".");
				String name = fileName.substring(0, separatorsIndex);
				String fileType = fileName.substring(separatorsIndex);
				String saveAsName = DateUtil
						.getNowDateString("yyyy年MM月dd日HHmmssSSS") + "_" + name;// 在文件名称前增加时间戳+文件名称+随机码；防止文件重复
				uploadFile = new UploadFile();
				uploadFile.setCatlog("");
				uploadFile.setDescription(module);
				uploadFile.setFileType(fileType);
				uploadFile.setOriginalName(name + fileType);
				uploadFile.setSavePath(relativePath + fileSptr + saveAsName);// 相对路径

				transfersFile(item, uploadFile);
				uploadDao.save(uploadFile);
				addOne = new AnnexAddOne();
				addOne.setFileId(uploadFile.getFileId());
				addOne.setEntityId(entityId);// 参数传入
				addOne.setEntityType(entityType);
				addOne.setModule(module);
				addOne.setModuleCatalog(ModuleCatalog.get(module));
				addOne.setUploader(uploader);
				addOne.setTimeStamp(new Date());
				addOne.setUploadDate(new Date());
				addOne.setIsUsable(isUsable);
				// 设置正常状态
				addOne.setState("0");
				annexAddOneDao.save(addOne);
			}
		} else {
			logger.info("不包含任何附件");
		}

	}

	/**
	 * 转移上传文件
	 * 
	 * @param item
	 *            文件
	 * @param uploadFile
	 *            上传文件信息
	 * @throws Exception
	 *             异常
	 */
	private void transfersFile(FileItem item, UploadFile uploadFile)
			throws Exception {
		String fileSptr = "/";
		Long fileSize = item.getSize();
		uploadFile.setFileSize(fileSize);
		String absolutePathFile = externalService.getUploadroot() + fileSptr
				+ uploadFile.getSavePath() + uploadFile.getFileType();
		logger.info("文件绝对路径：" + absolutePathFile);
		try {
			// 获取输入流
			InputStream stream = item.getInputStream();
			// 将上传文件写指定路径
			File file = new File(absolutePathFile);
			if (!file.exists()) {
				boolean success = file.createNewFile();
				OutputStream bos = null;
				if (success) {
					bos = new FileOutputStream(absolutePathFile);
					int bytesRead = 0;
					byte[] buffer = new byte[8192];
					while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
						bos.write(buffer, 0, bytesRead);
					}
					bos.close();
					// close the stream
					stream.close();
					logger.info("The file has been written to \""
							+ absolutePathFile + "\"");
				} else {
					logger.info("不能够创建文件！");
					throw new Exception("该目录不能够创建文件！");
				}
			} else {// 如果文件重复
				uploadFile.setSavePath(uploadFile.getSavePath() + "as_");
				absolutePathFile = externalService.getUploadroot()
						+ uploadFile.getSavePath() + uploadFile.getFileType();
				file = new File(absolutePathFile);
				if (!file.exists()) {
					boolean success = file.createNewFile();
					OutputStream bos = null;
					if (success) {
						bos = new FileOutputStream(absolutePathFile);
						int bytesRead = 0;
						byte[] buffer = new byte[8192];
						while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
							bos.write(buffer, 0, bytesRead);
						}
						bos.close();
						// close the stream
						stream.close();
					} else {
						logger.info("不能够创建文件！");
						throw new Exception("该目录不能够创建文件！");
					}
				} else {
					logger.info("目录中存在相同文件，不能够创建！");
					throw new Exception("目录中存在相同文件，不能够创建！");
				}
			}
			uploadFile.setSavePath(uploadFile.getSavePath()
					+ uploadFile.getFileType());
			// 将文件路径及相关属性写入数据库表
		} catch (Exception ex) {
			throw new Exception(ex);
		}
	}

	/**
	 * 创建目录
	 * 
	 * @param absolutePath
	 *            absolutePath
	 */
	private void createCatalog(String absolutePath) {
		File file = new File(absolutePath);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	@Override
	@Transactional
	public void saveFiles(List<FileItem> files, String module,
			String regionName, String entityId, String entityType,
			String uploader) throws Exception {
		saveFiles(files, module, regionName, entityId, entityType, uploader,
				"1");

	}

	@Override
	@Transactional
	public void saveFileInfos(List<UploadFile> files, String regionName,
			String entityId, String entityType, String uploader)
			throws Exception {
		String module = "BASESTATION";
		if ("BS".equals(entityType)) {
			entityType = "RS_BASESTATION";
		} else if ("RS".equals(entityType)) {
			entityType = "RS_REPEATER";
		} else if ("HT".equals(entityType)) {
			entityType = "BS_HIDD_TROUBLE";
			module = "HIDDTROUBLE";
		} else if ("EQU".equals(entityType)) {
			entityType = "RS_EQUIPMENT";
			module = "EQUIPMENT";
		}
		if (files != null) {
			for (int i = 0; i < files.size(); i++) {
				AnnexAddOne addOne = new AnnexAddOne();
				addOne.setFileId(files.get(i).getFileId());
				addOne.setEntityId(entityId);// 参数传入
				addOne.setEntityType(entityType);
				addOne.setModule(module);
				addOne.setModuleCatalog(ModuleCatalog.get(module));
				addOne.setUploader(uploader);
				addOne.setTimeStamp(new Date());
				addOne.setUploadDate(new Date());
				addOne.setIsUsable("1");// 1: 表示资源有效
				addOne.setState("0");
				annexAddOneDao.save(addOne);
			}
		}
	}

	/*
	 * 删除文件
	 * 
	 * @see
	 * com.cabletech.business.base.service.UploadFileService#delById(java.lang
	 * .String)
	 */
	@Override
	@Transactional
	public void delById(String id) throws Exception {
		AnnexAddOne addOne = annexAddOneDao.findUniqueBy("fileId", id);
		UploadFile uploadFile = uploadDao.get(addOne.getFileId());
		String absolutePathFile = externalService.getUploadroot()
				+ File.separator + uploadFile.getSavePath();
		File file = new File(absolutePathFile);
		file.delete();
		uploadDao.delete(uploadFile);
		annexAddOneDao.delete(addOne);
		logger.info("删除文件:" + absolutePathFile);

	}

	/*
	 * 通过ID获取文件信息
	 * 
	 * @see
	 * com.cabletech.business.base.service.UploadFileService#getFileId(java.
	 * lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public UploadFile getFileId(String fileId) throws Exception {
		// TODO Auto-generated method stub
		UploadFile uploadFile = uploadDao.get(fileId);
		return uploadFile;
	}

	/*
	 * 获取多个文件
	 * 
	 * @see
	 * com.cabletech.business.base.service.UploadFileService#getFiles(java.lang
	 * .String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public List<FileItem> getFiles(String entityId, String entityType,
			String useable) throws Exception {
		List<FileItem> files = new ArrayList<FileItem>();

		List<UploadFile> fileInfos = uploadDao.getFilesByIds(entityId,
				entityType, useable);
		for (UploadFile fileInfo : fileInfos) {
			FileItem fileItem = null;

			File repository = new File(externalService.getUploadroot()
					+ File.separator + fileInfo.getSavePath());
			fileItem.write(repository);
			files.add(fileItem);
		}
		return files;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UploadFile> getFilesByIds(String entityId, String entityType,
			String useable) throws Exception {
		List<UploadFile> newfileList = new ArrayList<UploadFile>();

		List<Map<String, Object>> filelist = uploadDao.getFilesById(entityId,
				entityType, useable);
		if (null != filelist) {
			for (int i = 0; i < filelist.size(); i++) {
				UploadFile uploadFile = new UploadFile();
				uploadFile.setId(filelist.get(i).get("ID").toString());
				uploadFile.setFileId(filelist.get(i).get("FILEID").toString());
				uploadFile.setSavePath(filelist.get(i).get("SAVEPATH")
						.toString());
				uploadFile.setOriginalName(filelist.get(i).get("ORIGINALNAME")
						.toString());
				uploadFile.setFileType(filelist.get(i).get("FILETYPE")
						.toString());
				uploadFile.setFileSize(Long.parseLong(filelist.get(i)
						.get("FILESIZE").toString()));
				uploadFile.setDescription(filelist.get(i).get("DESCRIPTION")
						.toString());
				newfileList.add(uploadFile);
			}
		}
		return newfileList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UploadFile> getImageFile(String entityId, String entityType)
			throws Exception {
		return uploadDao.getImageFiles(entityId, entityType);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UploadFile> getImageFile(String entityId, String entityType,
			String startTime, String endTime) throws Exception {
		return uploadDao
				.getImageFiles(entityId, entityType, startTime, endTime);
	}

	@Override
	@Transactional
	public boolean delAnnexFile(String id) throws Exception {
		String path = uploadDao.getSavePathByAnnexId(id);
		boolean success = false;
		success = this.moveFile4Backup(path);
		if (success) {
			uploadDao.updateState(id);
		}
		return success;
	}

	/**
	 * 获取上传文件的输入流
	 * 
	 * @param fileId
	 *            String
	 */
	@Override
	@Transactional(readOnly = true)
	public InputStream getFileInputStream(String fileId) throws Exception {
		// TODO Auto-generated method stub
		UploadFile file = getFileId(fileId);
		// 显示图片
		String absolutePathFile = externalService.getUploadroot()
				+ File.separator + file.getSavePath();
		InputStream input = new FileInputStream(absolutePathFile);
		return input;
	}

	/**
	 * 文件移动，将文件从原路径移动到相应路径下的upload/recycle里
	 * 
	 * @param srcFile
	 *            String
	 * @return
	 */
	private boolean moveFile4Backup(String srcFile) {
		String RECYCLE = externalService.getUploadroot() + File.separator
				+ "recycle";
		try {
			if (!(new File(RECYCLE).isDirectory())) {
				new File(RECYCLE).mkdir();
			}
		} catch (SecurityException e) {
			logger.error("不能创建recyle目录，请检查是否有目录权限");
		}
		File file = new File(externalService.getUploadroot() + File.separator
				+ srcFile);
		File dir = new File(RECYCLE);
		boolean success = file.renameTo(new File(dir, file.getName()));
		return success;
	}

	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return uploadDao;
	}

}
