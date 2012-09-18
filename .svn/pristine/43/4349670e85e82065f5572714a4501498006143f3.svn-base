package com.cabletech.business.assess.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.assess.model.AssessTemplate;
import com.cabletech.business.assess.service.AssessTemplateContentService;
import com.cabletech.business.assess.service.AssessTemplateService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.util.Page;

/**
 * 考核模板管理
 * 
 * @author zhaobi 2012-7-31 创建
 */
@Namespace("/assess")
@Results({
		@Result(name = "input", location = "/assess/assesstemplate_input.jsp"),
		@Result(name = "view", location = "/assess/assesstemplate_view.jsp"),
		@Result(name = "list", location = "/assess/assesstemplate_list.jsp") })
@Action("/assessTemplateAction")
public class AssessTemplateAction extends BaseAction<AssessTemplate, String> {
	/**
	 * 考核模版服务
	 */
	@Resource(name = "assessTemplateServiceImpl")
	private AssessTemplateService assessTemplateService;
	/**
	 * 考核模版服务
	 */
	@Resource(name = "assessTemplateContentServiceImpl")
	private AssessTemplateContentService assessTemplateContentService;
	/**
	 * 考核模版
	 */
	private AssessTemplate assesstemplate = new AssessTemplate();

	@Override
	public AssessTemplate getModel() {
		if (null == assesstemplate) {
			assesstemplate = new AssessTemplate();
		}
		return assesstemplate;
	}

	@Override
	protected void prepareViewModel() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void prepareSaveModel() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 列表页面
	 * 
	 * @return String
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 模版列表书记
	 */
	public void listdata() {
		UserInfo user = this.getUser();
		Page<Map<String, Object>> page = super.initPage();
		assessTemplateService.queryAssessTemplate(assesstemplate, page);
		convertObjToJson(page);
	}

	/**
	 * 转到表单输入
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String input() throws Exception {
		UserInfo userInfo = this.getUser();
		if (StringUtils.isNotBlank(assesstemplate.getId())) {
			assesstemplate = assessTemplateService.getTemplate(assesstemplate
					.getId());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tableId", assesstemplate.getId());
			// 获取模板内容
			List<Map<String, Object>> newlist = assessTemplateContentService
					.getTableItemList(map);
			// 获取模板最大列数
			int maxItemCount = assessTemplateContentService
					.getMaxTableItem(map);
			this.getRequest().setAttribute("maxitemcount", maxItemCount);
			this.getRequest().setAttribute("templatecontent", newlist);
		}
		this.getRequest().setAttribute("assesstemplate", assesstemplate);
		return INPUT;
	}

	/**
	 * 保存
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
		UserInfo user = this.getUser();
		assesstemplate.setCreater(user.getPersonId());
		assesstemplate.setCreateDate(new Date());
		assessTemplateService.save(assesstemplate);
		addMessage("提示:保存" + " 考核模板信息成功 ",
				"/assess/assessTemplateAction!list.action", "success");
		return SUCCESS;
	}

	/**
	 * 删除
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String[] itemId = new String[] {};
		if (StringUtils.isNotBlank(assesstemplate.getId())) {
			itemId = assesstemplate.getId().split(",");
		}
		assessTemplateService.del(itemId);
		addMessage("提示:删除考核模板信息成功 ",
				"/assess/assessTemplateAction!list.action", "success");
		return SUCCESS;
	}
}
