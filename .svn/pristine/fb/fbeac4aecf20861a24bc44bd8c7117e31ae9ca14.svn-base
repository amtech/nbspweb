package com.cabletech.business.notice.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.util.CollectionUtils;

import com.cabletech.baseinfo.base.DateUtil;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.notice.model.Notice;
import com.cabletech.business.notice.service.NoticeService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 公告日至处理类
 * 
 * @author wangt
 * 
 */
@Namespace("/system")
@Results({
		@Result(name = "queryform", location = "/notice/query_notice_form.jsp"),
		@Result(name = "querynotice", location = "/notice/query_notice_list.jsp"),
		@Result(name = "querynoticemeet", location = "/notice/query_notice_meet.jsp"),
		@Result(name = "queryallissuenotice", location = "/notice/query_all_issue_notice.jsp"),
		@Result(name = "addform", location = "/notice/add_notice.jsp"),
		@Result(name = "editform", location = "/notice/edit_notice.jsp"),
		@Result(name = "shownotice", location = "/notice/show_notice.jsp") })
@Action("/notice")
@SuppressWarnings("rawtypes")
public class NoticeAction extends BaseAction<Notice, String> {
	private static final long serialVersionUID = 1L;
	private Notice notice = new Notice();
	@Resource(name = "noticeServiceImpl")
	private NoticeService noticeBo;

	/**
	 * 有编辑删除
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		String flag = getRequest().getParameter("flag");
		if (StringUtils.isBlank(flag)) {
			flag = "1";
		}
		this.getRequest().setAttribute("noticeflag", flag);
		return "querynotice";
	}

	/**
	 * 查询列表的数据
	 */
	public void listdate() {
		UserInfo userinfo = this.getUser();
		String flag = getRequest().getParameter("flag");
		notice.setType(super.getRequest().getParameter("type"));
		if (StringUtils.isNotBlank(flag) && "2".equals(flag)) {
			notice.setIsissue("y");
			notice.setAcceptUserIds(this.getUser().getPersonId());
		}
		notice.setRegionid(userinfo.getRegionId());
		Page page = super.initPage();
		page = noticeBo.queryPage(notice, page);
		convertObjToJson(page);
	}

	/**
	 * 查询会议列表notice
	 * 
	 * @return
	 * @throws Exception
	 */
	public String listMeet() throws Exception {
		UserInfo userinfo = this.getUser();
		if (notice == null) {
			notice = new Notice();
		}
		notice.setIsissue("y");
		notice.setRegionid(userinfo.getRegionId());
		notice.setType(Notice.MEET_TYPE);
		List list = noticeBo.queryList(notice);
		if (CollectionUtils.isEmpty(list)) {
			super.getRequest().setAttribute("list_size", "0");
		} else {
			super.getRequest().setAttribute("list_size", list.size() + "");
		}
		super.getRequest().setAttribute("noticelist", list);
		return "querynoticemeet";
	}

	/**
	 * 查询无编辑的notice
	 * 
	 * @return
	 * @throws Exception
	 */
	public String listIssueNotice() throws Exception {
		UserInfo userinfo = this.getUser();
		if (notice == null) {
			notice = new Notice();
		}
		notice.setRegionid(userinfo.getRegionId());
		notice.setIsissue("y");
		notice.setType(super.getRequest().getParameter("type"));
		Page page = super.initPage();
		page = noticeBo.queryPage(notice, page);
		super.getRequest().setAttribute("page", page);
		return "queryallissuenotice";
	}

	/**
	 * 载入编辑表单
	 */
	public String input() throws Exception {
		String id = super.getRequest().getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			Notice notice = noticeBo.loadNotice(id);
			super.getRequest().setAttribute("notice", notice);
			return "editform";
		}
		return "addform";
	}

	/**
	 * 添加公告
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String save() throws Exception {
		UserInfo userinfo = this.getUser();
		if (null == notice) {
			notice = new Notice();
		}
		String id = notice.getId();
		List<FileItem> files = (List<FileItem>) super.sessionManager
				.get("FILES");
		notice.setRegionid(userinfo.getRegionId());
		notice.setIssuedate(new Date());
		notice.setIssueperson(userinfo.getPersonId());
		notice.setIsread("n");
		notice = noticeBo.saveNotice(notice, userinfo, files);
		boolean b = true;
		if (notice.getId() != null) {
			b = true;
		} else {
			b = false;
		}
		if (notice.getIsissue().equals("y")) {
			// smSendProxy = (RmiSmProxyService)
			// ctx.getBean("rmiSmProxyService");
			// String sim = noticebean.getMobileIds();
			String content = "";
			String sendObjectName = "";
			if ("会议".equals(notice.getType())) {
				content += "邀您于"
						+ DateUtil.UtilDate2Str(notice.getMeetTime(),
								"yyyy-MM-dd HH:mm:ss");
				content += "参加" + notice.getTitle() + "会议";
				sendObjectName = notice.getTitle() + "会议定时发送短信";
			} else {
				content = "公告：" + notice.getTitle();
				sendObjectName = notice.getTitle() + "公告定时发送短信";
			}
			// logger.info(sim + ":" + content);
			// if (sim != null && !"".equals(sim)) {
			String sendMethod = notice.getSendMethod();
			String beginDate = DateUtil.UtilDate2Str(notice.getBeginDate(),
					"yyyy-MM-dd HH:mm:ss");
			String endDate = DateUtil.UtilDate2Str(notice.getEndDate(),
					"yyyy-MM-dd HH:mm:ss");
			String intervalType = notice.getSendIntervalType();
			String interval = notice.getSendTimeSpace();
			String sendCycleRule = super.getRequest().getParameter(
					"sendCycleRule");
			String[] inputDate = super.getRequest().getParameterValues(
					"inputDate");
			// SendMessageBO sendMessageBo = (SendMessageBO)
			// ctx.getBean("sendMessageBO");
			// sendMessageBo.sendMessage("notice." + notice.getId(),
			// content, sim, sendMethod, beginDate, endDate,
			// intervalType, interval, userinfo.getUserID(), inputDate,
			// sendObjectName, sendCycleRule);
			// }
		}
		return setSaveReturn(id, b);
	}

	/**
	 * 设置此方法的返回值
	 * 
	 * @param id
	 * @param b
	 * @return
	 */
	private String setSaveReturn(String id, boolean b) {
		if ("y".equals(notice.getIsissue())) {
			String url = "";
			if (StringUtils.isBlank(id)) {
				url = "/system/notice!input.action";
			} else {
				url = "/system/notice!list.action";
			}
			assertResult(b, "发布信息成功!", "发布信息失败!", url);
		} else {
			if (StringUtils.isBlank(id)) {
				String url = "/system/notice!input.action";
				assertResult(b, "添加信息成功!", "添加信息失败!", url);
			} else {
				String url = "/system/notice!list.action";
				assertResult(b, "更新信息成功!", "更新信息失败!", url);
			}
		}
		return SUCCESS;
	}

	/**
	 * 删除notice
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = super.getRequest().getParameter("id");
		boolean b = noticeBo.deleteNotice(id);
		String url = "/system/notice!list.action";
		assertResult(b, "删除信息成功!", "删除信息失败!", url);
		return SUCCESS;
	}

	/**
	 * 发布信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String cancel() throws Exception {
		String id = super.getRequest().getParameter("id");
		noticeBo.cancelNotice(id);
		String url = "/system/notice!list.action";
		super.addMessage("会议取消成功!", url, SysConstant.SUCCESS);
		return SUCCESS;
	}

	/**
	 * 查看公告详细信息。
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = super.getRequest().getParameter("id");
		boolean preview = new Boolean(super.getRequest()
				.getParameter("preview")).booleanValue();
		String model = super.getRequest().getParameter("model");
		if (StringUtils.isBlank(model)) {
			model = "brower";
		}
		UserInfo user = super.getUser();
		Notice notice = noticeBo.readNotice(id, user.getPersonId(), preview);
		super.getRequest().setAttribute("model", model);
		super.getRequest().setAttribute("notice", notice);
		return "shownotice";

	}

	/**
	 * 断言操作成功失败。
	 * 
	 * @param b
	 *            boolean 操作成功true ，否则 false
	 * @param s_msg
	 *            成功信息，可能是成功 信息字符串，可能是msgid
	 * @param f_msg
	 *            失败信息，可能是失败 信息字符串，可能是msgid
	 * @param url
	 *            地址
	 */
	private void assertResult(boolean b, String s_msg, String f_msg, String url) {
		if (b) {
			super.addMessage(s_msg, url, SysConstant.SUCCESS);
		} else {
			super.addMessage(f_msg, url, SysConstant.ERROR);
		}
	}

	@Override
	protected void prepareViewModel() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	protected void prepareSaveModel() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public Notice getModel() {
		// TODO Auto-generated method stub
		return notice;
	}
}
