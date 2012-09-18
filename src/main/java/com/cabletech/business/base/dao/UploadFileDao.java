package com.cabletech.business.base.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cabletech.business.base.model.UploadFile;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.DateUtil;

/**
 * 上传文件
 * 
 * @author Administrator
 * 
 */
@Repository
public class UploadFileDao extends BaseDao<UploadFile, String> {

	/**
	 * 通过实体ID获取文件
	 * 
	 * @param entityId
	 *            实体ID
	 * @param entityType
	 *            实体类型
	 * @param useable
	 *            是否可用
	 * @return
	 */
	public List<UploadFile> getFilesByIds(String entityId, String entityType,
			String useable) {
		String hql = "select f  from UploadFile as f,AnnexAddOne as ao "
				+ "where f.fileId = ao.fileId and ao.entityId=? and ao.entityType=? and ao.state=?";
		if (useable != null && !"".equals(useable)) {
			hql += " and ao.isUsable =?";
			return super.find(hql, entityId, entityType, "0", useable);
		} else {
			return super.find(hql, entityId, "0", entityType);
		}
	}

	/**
	 * 通过实体ID获取文件
	 * 
	 * @param entityId
	 *            String
	 * @param entityType
	 *            String
	 * @param useable
	 *            String
	 * @return
	 */
	public List<Map<String, Object>> getFilesById(String entityId,
			String entityType, String useable) {
		String sql = "select f.fileid,f.savepath,f.filetype,f.filesize,f.originalname,f.description,ao.id from filepathinfo f ,annex_add_one  ao where f.fileid=ao.fileid and ao.entity_id=? and ao.entity_type=? and ao.state=?";
		if (useable != null && !"".equals(useable)) {
			sql += " and ao.is_usable =?";
			return super.getJdbcTemplate().queryForList(sql, entityId,
					entityType, "0", useable);
		} else {
			return super.getJdbcTemplate().queryForList(sql, entityId,
					entityType, useable, "0");
		}
	}

	/**
	 * 获取图片文件
	 * 
	 * @param entityId
	 *            String
	 * @param entityType
	 *            String
	 * @return
	 */
	public List<UploadFile> getImageFiles(String entityId, String entityType) {
		String hql = "select f from UploadFile as f,AnnexAddOne as ao where f.fileId = ao.fileId and f.fileType='.jpg' and ao.entityId=? and ao.entityType=? and state=? order by ao.timeStamp desc";
		return super.find(hql, entityId, entityType, "0");
	}

	/**
	 * 获取图片文件，根据时间
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
	 */
	public List<UploadFile> getImageFiles(String entityId, String entityType,
			String startTime, String endTime) {
		String hql = "select f from UploadFile as f,AnnexAddOne as ao where f.fileId = ao.fileId and f.fileType='.jpg' and  ao.entityType=? and ao.uploadDate >=? and ao.uploadDate<=? and state=? order by ao.timeStamp desc";
		return super.find(hql, entityType,
				DateUtil.StringToDate(startTime, "yyyy-MM-dd HH:mm:ss"),
				DateUtil.StringToDate(endTime, "yyyy-MM-dd HH:mm:ss"), "0");
	}

	/**
	 * 通过附件在表annex_add_one 的id得到附件的上传路径
	 * 
	 * @param id
	 *            String
	 * @return
	 */
	public String getSavePathByAnnexId(String id) {
		String path = "";
		List list = null;
		String hql = "select u.savePath from UploadFile u,AnnexAddOne a where u.fileId=a.fileId and a.id="
				+ id;
		list = this.find(hql);
		path = (String) list.get(0);
		return path;
	}

	/**
	 * 更新附件的使用状态 “1”是已经移除的，“0”为正常的
	 * 
	 * @param id
	 *            String
	 */
	public void updateState(String id) {
		String sql = "update annex_add_one set state='1' where id=" + id;
		super.getJdbcTemplate().update(sql);
	}

}
