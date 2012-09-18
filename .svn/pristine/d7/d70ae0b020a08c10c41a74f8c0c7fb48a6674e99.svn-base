package com.cabletech.business.base.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;

import com.cabletech.business.base.model.LocaleProcess;
import com.cabletech.business.base.service.LocaleProcessService;
import com.cabletech.common.base.BaseAction;

/**
 * 现场处理过程处理Action
 * 
 * @author 杨隽 2012-01-09 创建
 * @author 杨隽 2012-05-10 将现场处理过程的参数信息放到request的属性中
 * 
 */
@Results({
		@Result(name = "show_process_history_list", location = "/localeprocess/show_process_history_list.jsp"),
		@Result(name = "show_process_photos", location = "/localeprocess/show_process_photos.jsp"), })
@Action("/localeProcessAction")
@Scope("prototype")
public class LocaleProcessAction extends BaseAction<LocaleProcess, String> {
	private static final long serialVersionUID = 1L;
	// 现场处理过程列表页面跳转标识
	private static final String SHOW_PROCESS_HISTORY_LIST = "show_process_history_list";
	// 现场处理过程图片页面跳转标识
	private static final String SHOW_PROCESS_PHOTOS = "show_process_photos";
	// 现场处理过程表单数据
	private LocaleProcess localeProcess;
	@Resource(name = "localeProcessServiceImpl")
	private LocaleProcessService localeProcessService;

	/**
	 * 显示现场处理过程信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked" })
	public String showProcessHistoryList() throws Exception {
		List<Map<String, Object>> list = localeProcessService
				.showLocaleProcessList(localeProcess);
		super.getRequest().setAttribute("localeProcess", localeProcess);
		super.sessionManager.put("LOCALE_PROCESS_LIST", list);
		return SHOW_PROCESS_HISTORY_LIST;
	}

	/**
	 * 显示现场处理过程图片信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked" })
	public String showProcessPhotos() throws Exception {
		List<Map<String, Object>> list = localeProcessService
				.showLocaleProcessPhotosList(localeProcess);
		super.sessionManager.put("LOCALE_PROCESS_PHOTOS_LIST", list);
		super.getRequest().setAttribute("suffix", "_processphotos");
		return SHOW_PROCESS_PHOTOS;
	}

	@Override
	public LocaleProcess getModel() {
		// TODO Auto-generated method stub
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

	public LocaleProcess getLocaleProcess() {
		return localeProcess;
	}

	public void setLocaleProcess(LocaleProcess localeProcess) {
		this.localeProcess = localeProcess;
	}
}
