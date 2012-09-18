package com.cabletech.business.assess.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.assess.model.AssessTemplateContent;
import com.cabletech.business.assess.service.AssessTemplateContentService;
import com.cabletech.common.base.BaseAction;

/**
 * 模板内容Action
 * 
 * @author Administrator 2012-08-07
 * 
 */
@Namespace("/assess")
@Results({ @Result(name = "input", location = "/assess/assesstemplateitem_input.jsp") })
@Action("/assessTemplateContentAction")
public class AssessTemplateContentAction extends
		BaseAction<AssessTemplateContent, String> {
	/**
	 * 考核模版服务
	 */
	@Resource(name = "assessTemplateContentServiceImpl")
	private AssessTemplateContentService assessTemplateContentService;
	/**
	 * 模版内容
	 */
	private AssessTemplateContent content = new AssessTemplateContent();

	@Override
	public AssessTemplateContent getModel() {
		// TODO Auto-generated method stub
		if (null == content) {
			content = new AssessTemplateContent();
		}
		return content;
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
	 * 转到表单输入
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String input() throws Exception {
		UserInfo userInfo = this.getUser();
		super.getRequest().setAttribute("tableType",
				super.getParameter("tableType"));
		if (StringUtils.isNotBlank(content.getId())) {
			Map<String, Object> contentMap = assessTemplateContentService
					.getTemplateContent(content);
			this.getRequest().setAttribute("templatecontent", contentMap);
		}
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
		assessTemplateContentService.save(content);
		addMessage(
				"提示:保存考核内容信息成功 ",
				"/assess/assessTemplateAction!input.action?id="
						+ content.getTableid(), "success");
		return SUCCESS;
	}

	/**
	 * 删除
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		UserInfo user = this.getUser();
		assessTemplateContentService.delete(content.getId());
		addMessage(
				"提示:删除考核内容信息成功 ",
				"/assess/assessTemplateAction!input.action?id="
						+ content.getTableid(), "success");
		return SUCCESS;
	}
}
