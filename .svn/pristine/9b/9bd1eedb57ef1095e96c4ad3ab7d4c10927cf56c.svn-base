package com.cabletech.business.contactletter.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cabletech.business.contactletter.model.BusinessContactLetterSendee;
import com.cabletech.common.base.BaseDao;

/**
 * 业务联系函 发送对象表 操作对象
 * 
 * @author zg
 * 
 */
@Repository
public class BusinessContactLetterSendeeDao extends
		BaseDao<BusinessContactLetterSendee, String> {

	private static final String SQL_DELETEBYCONTACTLETTER = "delete from BusinessContactLetterSendee bcls WHERE bcls.letterId =?";

	/**
	 * 删除对象 批量
	 * 
	 * @param contactletterid
	 *            String
	 */
	public void deleteBycontactletter(String contactletterid) {
		this.batchHQLExecute(SQL_DELETEBYCONTACTLETTER, contactletterid);

	}

	/**
	 * 检查是否已读
	 * 
	 * @param objectid
	 *            String
	 * @param letterid
	 *            String
	 * @return
	 */
	public String checkReadById(String objectid,
			String letterid) { 
		String isread="";
		String sql = "select isread from  BUSINESS_CONTACT_LETTER_SENDEE bcls WHERE bcls.OBJECTID='"
				+ letterid + "' and  bcls.LETTERID ='" +objectid  + "' ";
		logger.info(sql); 
		List<Map<String,Object>> list = this.jdbcTemplate.queryForList(sql);
		if (list != null && list.size()!=0) {
			isread = list.get(0).get("isread").toString();
		}
		return isread;
	}

	/**
	 * 
	 * @param entityEE
	 *            BusinessContactLetterSendee
	 */
	public void updateByEntity(BusinessContactLetterSendee entityEE) {
		String sql = "update BUSINESS_CONTACT_LETTER_SENDEE  bcls set bcls.READTIME= sysdate ,"
				+ " bcls.ISREAD=1 where bcls.objectId='"
				+ entityEE.getObjectId()
				+ "' and bcls.letterId='"
				+ entityEE.getLetterId() + "'";
		logger.info(sql);
		this.jdbcTemplate.execute(sql);

	}

	/**
	 * 
	 * @param entityEE
	 *            BusinessContactLetterSendee
	 */
	public void updateIsread(BusinessContactLetterSendee entityEE) {
		String sql = "update BUSINESS_CONTACT_LETTER_SENDEE  bcls set bcls.READTIME= sysdate ,"
				+ " bcls.ISREAD=1 where bcls.objectId='"
				+ entityEE.getObjectId()
				+ "' and bcls.letterId='"
				+ entityEE.getLetterId() + "'";
		logger.info(sql);
		this.jdbcTemplate.execute(sql);
	}

	public long getCountByLetterId(String id) {
		String sql = "select count(*)  from BUSINESS_CONTACT_LETTER_SENDEE   where  letterId='"
				+ id + "'";
		logger.info(sql);
		return this.getJdbcTemplate().queryForLong(sql);
	}

}
