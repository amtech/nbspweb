package com.cabletech.business.wplan.template.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.wplan.template.model.WplanTemplate;
import com.cabletech.business.wplan.template.service.WplanTemplateService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.util.Page;

/**
 * 巡检模板 ACTION
 * 
 * @author wnagjie
 * **/
@Namespace("/wplan")
@Results({
		@Result(name = "list", location = "/wplan/template/listWplanTemplate.jsp"),
		@Result(name = "input", location = "/wplan/template/addWplanTemplate.jsp"),
		@Result(name = "copy", location = "/wplan/template/copyWplanTemplate.jsp"),
		@Result(name = "view", location = "/wplan/template/viewWplanTemplate.jsp"),
		@Result(name = "viewall", location = "/wplan/template/viewAllWplanTemplate.jsp"),
		@Result(name = "reload", location = "wplanTemplateAction!query.action", params = {
				"pageNo", "%{pageNo}" }, type = "redirect") })
@Action("/wplanTemplateAction")
public class WplanTemplateAction extends BaseAction<WplanTemplate, String> {

	@Resource(name = "wplanTemplateServiceImpl")
	private WplanTemplateService wplanTemplateService;

	private WplanTemplate vo;

	/**
	 * 转到表单输入
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String input() throws Exception {
		String businessType = this.getRequest().getParameter("businessType");
		String templateId = this.getRequest().getParameter("templateId");
		String flag = this.getRequest().getParameter("flag");
		super.getRequest().setAttribute("businessType", businessType);
		super.getRequest().setAttribute("templateId", templateId);
		super.getRequest().setAttribute("flag", flag);
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
		String regionId = user.getRegionId();
		vo.setRegionid(regionId);
		wplanTemplateService.saveWplanTemplate(vo);
		addMessage(
				"提示:保存" + " 计划模板信息成功 ",
				"/wplan/wplanTemplateAction!input.action?businessType="
						+ vo.getBusinessType(), "success");
		return SUCCESS;
	}

	/**
	 * 复制计划模板信息
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String copy() throws Exception {
		UserInfo user = this.getUser();
		String regionId = user.getRegionId();
		vo.setRegionid(regionId);
		wplanTemplateService.copyWplanTemplate(vo);
		addMessage(
				"提示：复制计划模板信息成功 ",
				"/wplan/wplanTemplateAction!query.action?businessType="
						+ vo.getBusinessType(), "success");
		return SUCCESS;
	}

	/**
	 * 启用计划模板
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String startUsing() throws Exception {
		String id = this.getRequest().getParameter("id");
		wplanTemplateService.startUsingWplanTemplate(id);
		WplanTemplate temp = wplanTemplateService.getWplanTemplate(id);
		String businesstype = "";
		if (null != temp) {
			businesstype = temp.getBusinessType();
		}
		addMessage("提示：启用计划模板信息成功 ",
				"/wplan/wplanTemplateAction!query.action?businessType="
						+ businesstype, "success");
		return SUCCESS;
	}

	/**
	 * 停用计划模板
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.getRequest().getParameter("id");
		wplanTemplateService.deleteWplanTemplate(id);
		WplanTemplate temp = wplanTemplateService.getWplanTemplate(id);
		String businesstype = "";
		if (null != temp) {
			businesstype = temp.getBusinessType();
		}
		addMessage("提示：停用计划模板信息成功 ",
				"/wplan/wplanTemplateAction!query.action?businessType="
						+ businesstype, "success");
		return SUCCESS;
	}

	/**
	 * 查询计划模板信息
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String query() throws Exception {
		String type = this.getRequest().getParameter("businessType");
		this.getRequest().setAttribute("businessType", type);
		return LIST;
	}

	/**
	 * 查看计划模板信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.getRequest().getParameter("id");
		String flag = this.getRequest().getParameter("flag");
		vo = wplanTemplateService.getWplanTemplate(id);
		if ("view".equals(flag)) {
			getRequest().setAttribute("items",
					wplanTemplateService.getSubItemByTemplate(id));
			return VIEW;
		}
		if ("copy".equals(flag)) {
			super.getRequest().setAttribute("businessType",
					vo.getBusinessType());
			super.getRequest().setAttribute("templateId", vo.getId());
			super.getRequest().setAttribute("flag", flag);
			return "copy";
		}
		return VIEW;
	}

	/**
	 * 计划模板列表数据
	 */
	@SuppressWarnings("unchecked")
	public void list() {
		UserInfo user = this.getUser();
		Page<Map<String, Object>> page = super.initPage();
		wplanTemplateService.queryWplanTemplate(
				this.getRequest().getParameter("businessType"), this
						.getRequest().getParameter("templateName"), user, page);
		super.setExcelParameter(page);
		convertObjToJson(page);
	}

	/**
	 * 查看计划模板所有信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewALL() throws Exception {
		String id = this.getRequest().getParameter("id");
		getRequest().setAttribute("templateid", id);
		return "viewall";
	}

	/**
	 * 查看计划模板的巡检项json字串
	 */
	public String getPatrolItemJson() throws Exception {
		UserInfo user = super.getUser();
		String id = super.getParameter("id");
		String businessType = super.getParameter("businessType");
		String flag = super.getParameter("flag");
		String json = this.wplanTemplateService.getPatrolItemTreddDate(
				businessType, user.getRegionId(), flag, id);
		super.outPrint(json, false);
		return null;
	}

	/**
	 * 导出信息报表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() throws Exception {
		try {
			logger.info("输出excel成功");
			return null;
		} catch (Exception e) {
			logger.error("导出信息报表出现异常:" + e.getMessage());
		}
		return null;
	}

	/**
	 * 导出
	 */
	public String exportForm() throws Exception {
		return null;
	}

	public WplanTemplate getVo() {
		return vo;
	}

	public void setVo(WplanTemplate vo) {
		this.vo = vo;
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	public WplanTemplate getModel() {
		if (null == vo) {
			vo = new WplanTemplate();
		}
		return vo;
	}
}