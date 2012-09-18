package com.cabletech.business.base.dao;

import org.springframework.stereotype.Repository;

import com.cabletech.business.base.model.LocaleProcessPhotos;

/**
 * 现场处理过程图片DAO
 * 
 * @author 杨隽 2012-01-09 创建
 * 
 */
@Repository
public class LocaleProcessPhotosDao extends
		CommonBaseDao<LocaleProcessPhotos, String> {
	/**
	 * 获取现场处理过程图片的sql
	 */
	public String getSql() {
		// TODO Auto-generated method stub
		StringBuffer sqlBuf = new StringBuffer("");
		sqlBuf.append(" SELECT DISTINCT fpi.FILEID,fpi.SAVEPATH,fpi.description,aao.* ");
		sqlBuf.append(" FROM ANNEX_ADD_ONE aao ");
		sqlBuf.append(" JOIN FILEPATHINFO fpi ");
		sqlBuf.append(" ON aao.FILEID=fpi.FILEID ");
		sqlBuf.append(" LEFT JOIN WLOCALE_PROCESS p ");
		sqlBuf.append(" ON p.TASK_ID=aao.ENTITY_ID ");
		sqlBuf.append(" AND p.TASK_TYPE=aao.ENTITY_TYPE ");
		sqlBuf.append(" WHERE aao.IS_USABLE='1' ");
		sqlBuf.append(" AND aao.STATE='0' ");
		return sqlBuf.toString();
	}
}
