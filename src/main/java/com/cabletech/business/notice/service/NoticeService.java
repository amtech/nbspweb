package com.cabletech.business.notice.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.notice.model.Notice;
import com.cabletech.common.util.Page;

/**
 * 公告日志业务处理接口
 * 
 * @author wangt
 * 
 */
public interface NoticeService {
	public static final String ACCESS_URL = "/system/notice!showNotice.action?&preview=true&id=";

	/**
	 * 保存通知
	 * 
	 * @param notice
	 *            notice
	 * @param user
	 *            user
	 * @param files
	 *            files
	 * @return
	 * @throws Exception
	 */
	Notice saveNotice(Notice notice, UserInfo user, List<FileItem> files)
			throws Exception;

	/**
	 * 删除通知
	 * 
	 * @param id
	 *            id
	 * @return
	 */
	boolean deleteNotice(String id);

	/**
	 * 取消通知
	 * 
	 * @param id
	 *            id
	 */
	void cancelNotice(String id);

	/**
	 * loadNotice
	 * 
	 * @param id
	 *            id
	 * @return
	 */
	Notice loadNotice(String id);

	/**
	 * 读通知
	 * 
	 * @param id
	 *            id
	 * @param userid
	 *            userid
	 * @param preview
	 *            preview
	 * @return
	 */
	Notice readNotice(String id, String userid, boolean preview);

	/**
	 * 查询通知
	 * 
	 * @param notice
	 *            notice
	 * @param page
	 *            page
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Page queryPage(Notice notice, Page page);

	/**
	 * 返回通知列表
	 * 
	 * @param notice
	 *            notice
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List queryList(Notice notice);

	/**
	 * 根据查询条件获取最新的信息列表
	 * 
	 * @param condition
	 *            condition
	 * @return
	 */
	List<Map<String, Object>> getLatestNoticeList(String condition);

	/**
	 * 执行webservice的调用方法
	 * 
	 * @param parameterMap
	 *            Map<String, Object>
	 * @return
	 */
	String doWebService(Map<String, Object> parameterMap);
}
