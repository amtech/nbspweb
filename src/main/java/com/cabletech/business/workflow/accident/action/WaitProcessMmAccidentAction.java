package com.cabletech.business.workflow.accident.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.accident.model.MmAccident;
import com.cabletech.business.workflow.accident.service.WaitProcessMmAccidentService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.util.Page;

/**
 * 
 * 待处理隐患列表ACTION
 * 
 * @author 杨隽 2012-08-27 创建
 * 
 */
@Namespace("/workflow")
@Results({ @Result(name = "list", location = "/workflow/accident/mm_accident_wait_process_list.jsp") })
@Action("/waitProcessMmAccidentAction")
public class WaitProcessMmAccidentAction extends BaseAction<String, String> {
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 隐患查询参数
	 */
	private MmAccident accident = new MmAccident();
	/**
	 * 待处理隐患业务处理服务
	 */
	@Resource(name = "waitProcessMmAccidentServiceImpl")
	private WaitProcessMmAccidentService waitProcessAccidentService;

	/**
	 * 
	 * 待处理隐患列表页面
	 * 
	 * @return String
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 待处理隐患列表数据
	 */
	@SuppressWarnings("rawtypes")
	public void listData() {
		UserInfo userInfo = super.getUser();
		Page page = super.initPage();
		waitProcessAccidentService.getWaitProcessAccidentPage(accident,
				userInfo, page);
		super.setExcelParameter(page);
		convertObjToJson(page);
	}

	public String getModel() {
		return null;
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}
}