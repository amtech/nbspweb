package com.cabletech.business.notice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.Service.BaseInfoProvider;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.model.ModuleCatalog;
import com.cabletech.business.base.service.UploadFileService;
import com.cabletech.business.notice.dao.NoticeDao;
import com.cabletech.business.notice.model.Notice;
import com.cabletech.business.notice.model.NoticeSendEE;
import com.cabletech.business.notice.service.NoticeSendEEService;
import com.cabletech.business.notice.service.NoticeService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.DateUtil;
import com.cabletech.common.util.Page;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 公告日志业务处理实现
 * 
 * @author wangt
 * 
 */
@Service
@SuppressWarnings("rawtypes")
public class NoticeServiceImpl extends BaseServiceImpl implements NoticeService {
	@Resource(name = "noticeDao")
	private NoticeDao dao;
	@Resource(name = "baseInfoProvider")
	private BaseInfoProvider baseInfoProvider;
	// 上传附件业务处理
	@Resource(name = "uploadFileServiceImpl")
	private UploadFileService uploadFileService;

	@Resource(name = "noticeSendEEServiceImpl")
	private NoticeSendEEService noticesendeeservice;

	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	/**
	 * 保存
	 * 
	 * @param notice
	 *            Notice
	 * @param user
	 *            登录人
	 * @param files
	 *            附件
	 */
	@Transactional
	public Notice saveNotice(Notice notice, UserInfo user, List<FileItem> files)
			throws Exception {
		try {
			String[] acceptUserIds = null;
			notice.setIsCanceled("0");
			notice.setContent(notice.getContentString());
			if (StringUtils.isNotBlank(notice.getAcceptUserIds())) {
				acceptUserIds = notice.getAcceptUserIds().split(",");
				boolean flag = true;
				if (acceptUserIds != null) {
					for (int i = 0; i < acceptUserIds.length; i++) {
						if (user.getPersonId().equals(acceptUserIds[i])) {
							flag = false;
							break;
						}
					}
				}
				if (flag) {
					notice.setAcceptUserIds(notice.getAcceptUserIds() + ","
							+ user.getPersonId());
				}
				acceptUserIds = notice.getAcceptUserIds().split(",");
			}
			dao.saveNotice(notice);
			noticesendeeservice.delete(notice.getId());
			if (null != acceptUserIds) {
				for (int j = 0; j < acceptUserIds.length; j++) {
					NoticeSendEE sendentity = new NoticeSendEE();
					sendentity.setIsread("1");
					sendentity.setPersonid(acceptUserIds[j]);
					sendentity.setNoticeid(notice.getId());
					noticesendeeservice.save(sendentity);
				}
			}
			// 保存上传附件信息
			uploadFileService.saveFiles(files, ModuleCatalog.OTHER, "",
					notice.getId(), "NOTICE_CLOB", notice.getIssueperson());
			return notice;
		} catch (Exception ex) {
			logger.equals(ex);
		}
		return null;
	}

	/**
	 * 取消
	 * 
	 * @param id
	 *            String 信息编号
	 */
	@Transactional
	public void cancelNotice(String id) {
		Notice notice = dao.get(id);
		notice.setIsCanceled(Notice.CANCEL_STATE);
		dao.save(notice);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 *            String 信息编号
	 */
	@Transactional
	public boolean deleteNotice(String id) {
		Notice notice = dao.findById(id);
		if (notice != null) {
			noticesendeeservice.delete(id);
			return dao.removeNotice(notice);

		} else
			return false;

	}

	/**
	 * 获取Notice实体
	 * 
	 * @param id
	 *            Notice系统编号
	 */
	@Transactional(readOnly = true)
	public Notice loadNotice(String id) {
		Notice notice = dao.findById(id);
		getNoticeSendEE(notice);
		Map map = this.baseInfoProvider.getPersonService().getPersonInfo(
				notice.getIssueperson());
		if (!MapUtils.isEmpty(map)) {
			notice.setIssueperson((String) map.get("USERNAME"));
		}
		getAcceptUserNames(notice);
		return notice;
	}

	/**
	 * 获取Notice相关的与会人员信息
	 * 
	 * @param notice
	 *            Notice 信息实体
	 */
	private void getNoticeSendEE(Notice notice) {
		List<NoticeSendEE> noticeSend = noticesendeeservice
				.getNoticeSendEEByNoticeId(notice.getId());
		String receiveIds = "";
		if (noticeSend != null && noticeSend.size() > 0) {
			for (int j = 0; j < noticeSend.size(); j++) {
				if (StringUtils.isNotBlank(noticeSend.get(j).getPersonid())) {
					receiveIds += noticeSend.get(j).getPersonid();
					if (j < noticeSend.size() - 1) {
						receiveIds += ",";
					}
				}
			}
		}
		notice.setAcceptUserIds(receiveIds);
	}

	/**
	 * 读取
	 * 
	 * @param id
	 *            String 信息编号
	 * @param personid
	 *            String 人员id
	 * @param preview
	 *            boolean 是否为预览模式
	 */
	@Transactional
	public Notice readNotice(String id, String personid, boolean preview) {
		if (!preview) {
			NoticeSendEE noticesendee = new NoticeSendEE();
			noticesendee.setNoticeid(id);
			noticesendee.setPersonid(personid);
			noticesendee = noticesendeeservice.getOneNoticeSendEE(noticesendee);
			noticesendee.setIsread("0");
			noticesendeeservice.save(noticesendee);
		}
		Notice notice = dao.findById(id);
		getNoticeSendEE(notice);
		Map map = this.baseInfoProvider.getPersonService().getPersonInfo(
				notice.getIssueperson());
		if (!MapUtils.isEmpty(map)) {
			notice.setIssueperson((String) map.get("USERNAME"));
		}
		getAcceptUserNames(notice);
		return notice;
	}

	@Override
	@Transactional(readOnly = true)
	public Page queryPage(Notice notice, Page page) {
		page = dao.queryForPage(notice, page);
		return page;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cabletech.business.notice.service.NoticeService#queryList(com.cabletech
	 * .business.notice.model.Notice)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List queryList(Notice notice) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = dao.queryForList(notice);
		for (int i = 0; list != null && i < list.size(); i++) {
			Map<String, Object> oneMap = (Map<String, Object>) list.get(i);
			String noticeid = (String) oneMap.get("id");
			List<Map<String, Object>> meetpersonlist = noticesendeeservice
					.getNoticeSendList(noticeid);
			String acceptUserNames = "";
			for (int j = 0; meetpersonlist != null && j < meetpersonlist.size(); j++) {
				Map<String, Object> map = (Map<String, Object>) meetpersonlist
						.get(j);
				acceptUserNames += (String) map.get("USERNAME") + ",";
			}
			if (acceptUserNames.length() > 0) {
				acceptUserNames = acceptUserNames.substring(0,
						acceptUserNames.length() - 1);
			}
			oneMap.put("meet_person_name", acceptUserNames);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cabletech.business.notice.service.NoticeService#getLatestNoticeList
	 * (java.lang.String)
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Map<String, Object>> getLatestNoticeList(String condition) {
		return dao.getLatestNoticeList(condition);
	}

	/**
	 * 获取接受用户
	 * 
	 * @param notice
	 *            notice
	 */
	private void getAcceptUserNames(Notice notice) {
		String acceptUserIds = notice.getAcceptUserIds();
		if (StringUtils.isNotBlank(acceptUserIds)) {
			String[] acceptUserId = acceptUserIds.split(",");
			String acceptUserNames = "";
			for (int i = 0; acceptUserId != null && i < acceptUserId.length; i++) {
				Map map = this.baseInfoProvider.getPersonService()
						.getPersonInfo(acceptUserId[i]);
				acceptUserNames += (String) map.get("USERNAME");
				if (i < acceptUserId.length - 1) {
					acceptUserNames += ",";
				}
			}
			notice.setMeetPersonName(acceptUserNames);
		}
	}

	@Override
	@Transactional
	public String doWebService(Map<String, Object> parameterMap) {
		String[] userId = (String[]) parameterMap.get("usercode");
		UserInfo user = baseInfoProvider.getUserInfoByUserId(userId[0]);
		String[] cmd = (String[]) parameterMap.get("cmd");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("cmd", cmd[0]);
		if (user == null) {
			resultMap.put("code", "10");
		} else {
			getWebserviceResult(parameterMap, resultMap, user);
		}
		String datafomat = "yyyy-MM-dd HH:mm:ss";
		Gson gson = new GsonBuilder().disableHtmlEscaping()
				.setDateFormat(datafomat).create();
		return gson.toJson(resultMap);
	}

	/**
	 * 获取webservice的结果
	 * 
	 * @param parameterMap
	 *            Map<String, Object>
	 * @param resultMap
	 *            Map<String, Object>
	 * @param user
	 *            UserInfo
	 */
	@Transactional
	private void getWebserviceResult(Map<String, Object> parameterMap,
			Map<String, Object> resultMap, UserInfo user) {
		String[] cmd = (String[]) parameterMap.get("cmd");
		if ("wlbnewslist".equals(cmd[0])) {
			getNoticeListMap(parameterMap, resultMap, user);
		}
		if ("wlbnewsdetail".equals(cmd[0])) {
			getNoticeDetailMap(parameterMap, resultMap, user);
		}
	}

	/**
	 * 查看公告详细信息结果
	 * 
	 * @param parameterMap
	 *            Map<String, Object>
	 * @param resultMap
	 *            Map<String, Object>
	 * @param user
	 *            UserInfo
	 */
	@Transactional
	private void getNoticeDetailMap(Map<String, Object> parameterMap,
			Map<String, Object> resultMap, UserInfo user) {
		String[] id = (String[]) parameterMap.get("id");
		Notice notice = readNotice(id[0], user.getPersonId(), false);
		if (notice == null) {
			resultMap.put("code", "50");
		} else {
			resultMap.put("code", "0");
			resultMap.put("title", notice.getTitle());
			resultMap.put("issueperson", notice.getIssueperson());
			resultMap.put("issuedate", DateUtil.UtilDate2Str(
					notice.getIssuedate(), "yyyy-MM-dd HH:mm:ss"));
			resultMap.put("contentString", notice.getContentString());
			resultMap.put("typename", notice.getTypename());
			resultMap.put("newflag", "0");
		}
	}

	/**
	 * 查看公告列表信息结果
	 * 
	 * @param parameterMap
	 *            Map<String, Object>
	 * @param resultMap
	 *            Map<String, Object>
	 * @param user
	 *            UserInfo
	 */
	@Transactional(readOnly = true)
	private void getNoticeListMap(Map<String, Object> parameterMap,
			Map<String, Object> resultMap, UserInfo user) {
		String[] pageNo = (String[]) parameterMap.get("page");
		String[] pageSize = (String[]) parameterMap.get("pagesize");
		String[] title = (String[]) parameterMap.get("title");
		Page page = new Page();
		page.setPageNo(Integer.parseInt(pageNo[0]));
		page.setPageSize(Integer.parseInt(pageSize[0]));
		Notice notice = new Notice();
		notice.setTitle(title[0]);
		notice.setAcceptUserIds(user.getPersonId());
		queryPage(notice, page);
		if (CollectionUtils.isEmpty(page.getResult())) {
			resultMap.put("value", "");
			resultMap.put("code", "50");
			resultMap.put("total", "0");
			resultMap.put("totalpage", "0");
		} else {
			resultMap.put("value", page.getResult());
			resultMap.put("code", "0");
			resultMap.put("total", page.getTotalCount());
			resultMap.put("totalpage", page.getTotalPages());
		}
	}
}
