package com.cabletech.business.webservice.action;

import java.io.PrintWriter;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.business.notice.model.Notice;
import com.cabletech.business.notice.service.NoticeService;
import com.cabletech.common.base.BaseAction;

/**
 * 公告webservice调用类
 * 
 * @author 杨隽 2012-09-17 创建
 * 
 */
@Namespace("/webservice")
@Results({ @Result(name = "shownotice", location = "/notice/show_notice.jsp") })
@Action("/notice")
public class NoticeWebserviceAction extends BaseAction<Notice, String> {
	private static final long serialVersionUID = 1L;
	@Resource(name = "noticeServiceImpl")
	private NoticeService noticeBo;

	/**
	 * 执行webservice的调用方法
	 */
	@SuppressWarnings("unchecked")
	public void doWebservice() {
		Map<String, Object> parameterMap = super.getRequest().getParameterMap();
		String json = noticeBo.doWebService(parameterMap);
		try {
			super.getResponse().setContentType("text/html; charset=utf-8");
			PrintWriter out = super.getResponse().getWriter();
			out.print(json);
			out.flush();
		} catch (Exception ex) {
			logger.error("", ex);
		}
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}

	@Override
	public Notice getModel() {
		return null;
	}
}
