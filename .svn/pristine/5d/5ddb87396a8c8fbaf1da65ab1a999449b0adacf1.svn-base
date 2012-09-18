package com.cabletech.business.workflow.electricity.security.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.util.CollectionUtils;

import com.cabletech.business.workflow.electricity.security.model.OeDispatchTask;
import com.cabletech.business.workflow.electricity.security.service.OeOilengineRecordService;

/**
 * 油机发电记录信息Action
 * 
 * @author 杨隽 2012-05-04 创建
 * 
 */
@Namespace("/workflow")
@Results({ @Result(name = "list", location = "/workflow/electricity/security/oe_oilengine_record_list.jsp") })
@Action("/oeOilengineRecordAction")
public class OeOilengineRecordAction extends
		OeDispatchTaskBaseAction<OeDispatchTask, String> {
	// 油机发电记录信息业务处理
	@Resource(name = "oeOilengineRecordServiceImpl")
	private OeOilengineRecordService oeOilengineRecordService;
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 进入油机发电记录列表页面
	 * 
	 * @return String
	 */
	public String list() {
		String id = super.getParameter("id");
		List<Map<String, Object>> list = oeOilengineRecordService.getList(id);
		if (!CollectionUtils.isEmpty(list)) {
			super.getRequest().setAttribute("map", list.get(0));
		}
		return LIST;
	}

	@Override
	public OeDispatchTask getModel() {
		return null;
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}
}
