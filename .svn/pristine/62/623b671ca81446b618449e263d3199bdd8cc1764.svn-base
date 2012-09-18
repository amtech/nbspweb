package com.cabletech.business.wplan.plan.action;

import java.util.Calendar;
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
import com.cabletech.business.wplan.plan.model.PatrolTemplate;
import com.cabletech.business.wplan.plan.model.Patrolinfo;
import com.cabletech.business.wplan.plan.service.PatrolResourceService;
import com.cabletech.business.wplan.plan.service.PatrolTemplateService;
import com.cabletech.business.wplan.plan.service.PatrolinfoService;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.DateUtil;
import com.cabletech.common.util.Page;
import com.cabletech.common.util.ServiceException;

/**
 * 巡检计划Action
 * 
 * @author zhaobi
 * 
 */
@Namespace("/wplan")
@Results({
		@Result(name = "input", location = "/wplan/plan/patrolinfo_input.jsp"),
		@Result(name = "view", location = "/wplan/plan/patrolinfo_view.jsp"),
		@Result(name = "list", location = "/wplan/plan/patrolinfo_list.jsp") })
@Action("/patrolinfoAction")
public class PatrolinfoAction extends PatrolinfoBaseAction<Patrolinfo, String> {
	/**
	 * 巡检计划信息
	 */
	private Patrolinfo patrolinfo = new Patrolinfo();
	/**
	 * 巡检计划信息服务
	 */
	@Resource(name = "patrolinfoServiceImpl")
	private PatrolinfoService patrolinfoService;

	/**
	 * 巡检资源服务
	 */
	@Resource(name = "patrolResourceServiceImpl")
	private PatrolResourceService patrolResourceService;

	/**
	 * 计划模板服务
	 */
	@Resource(name = "patrolTemplateServiceImpl")
	private PatrolTemplateService patrolTemplateService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cabletech.common.base.BaseAction#input()
	 */
	@Override
	public String input() {
		try {
			return INPUT;
		} catch (ServiceException e) {
			logger.error("执行添加计划出错:" + e.getMessage());
			return ERROR;
		}
	}

	@Override
	public Patrolinfo getModel() {
		return patrolinfo;
	}

	@Override
	protected void prepareViewModel() throws Exception {
		String id = this.getRequest().getParameter("id");
		String businessType = this.getRequest().getParameter("type");
		UserInfo user = super.getUser();
		Map<String, Object> patrolinfoMAP = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(id)) {
			patrolinfoMAP = patrolinfoService.view(id);
			if (StringUtils.isNotBlank(super.getRequest().getParameter("opt"))) {
				patrolinfoMAP.remove("ID");
				patrolinfoMAP.put("OPT", "copy");
			}
			PatrolTemplate template = patrolTemplateService.get(id);
			List<Map<String, Object>> reslist = patrolResourceService
					.getPatrolResourceByPlanid(id);
			// 查看编辑时计划对应的巡检资源
			this.getRequest().setAttribute("patrolResourceListJSON",
					this.convertObjToJsonStr(reslist));
			businessType = patrolinfoMAP.get("BUSINESS_TYPE").toString();
			if (template != null) {
				patrolinfoMAP.put("TEMPLATEID", template.getTemplateid());
			}
		} else {
			// 设置查看时巡检年默认值
			patrolinfoMAP
					.put("YEAR", Calendar.getInstance().get(Calendar.YEAR));
			patrolinfoMAP.put("REGIONNAME", this.getUser().getRegionName());
			patrolinfoMAP.put("REGIONID", this.getUser().getRegionId());
		}
		this.getRequest().setAttribute("patrolinfoMap", patrolinfoMAP);
		this.getRequest().setAttribute("btypeString", businessType);
		this.setViewTag(businessType, user.getRegionId());
	}

	/**
	 * 查看计划信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		return VIEW;
	}

	@Override
	protected void prepareSaveModel() throws Exception {
		if (null == patrolinfo) {
			patrolinfo = new Patrolinfo();
		}
	}

	/**
	 * 根据专业类型动态获取模版
	 */
	public void gettemplate() {
		String businessType = this.getParameter("type");
		String regionId = super.getParameter("regionId");
		if (StringUtils.isNotBlank(businessType)) {
			convertObjToJson(getPlanTemplate(businessType, regionId));
		}
	}

	/**
	 * 计划信息保存
	 * 
	 * @return
	 */
	public String save() {
		UserInfo user = this.getUser();
		try {
			// 半年
			String id = patrolinfo.getId();
			if (SysConstant.WPLAN_YEAR.equals(patrolinfo.getPlantype())) {
				if (SysConstant.PLAN_XJND_1.equals(patrolinfo.getYeartype())) {
					patrolinfo.setStartdate(DateUtil.StringToUtilDate(
							patrolinfo.getYear() + "-01-01"
									+ SysConstant.DAY_STARTTIME,
							"yyyy-MM-dd HH:mm:ss"));
					patrolinfo.setEnddate(DateUtil.StringToUtilDate(
							patrolinfo.getYear() + "-06-30"
									+ SysConstant.DAY_ENDTIME,
							"yyyy-MM-dd HH:mm:ss"));
				} else {
					patrolinfo.setStartdate(DateUtil.StringToUtilDate(
							patrolinfo.getYear() + "-07-01"
									+ SysConstant.DAY_STARTTIME,
							"yyyy-MM-dd HH:mm:ss"));
					patrolinfo.setEnddate(DateUtil.StringToUtilDate(
							patrolinfo.getYear() + "-12-31"
									+ SysConstant.DAY_ENDTIME,
							"yyyy-MM-dd HH:mm:ss"));
				}
			}
			// 季度
			else if (SysConstant.WPLAN_SEASON.equals(patrolinfo.getPlantype())) {
				patrolinfo.setStartdate(DateUtil.StringToUtilDate(
						DateUtil.getSeasonTime(patrolinfo.getYear(),
								Integer.parseInt(patrolinfo.getSeasontype()),
								true) + SysConstant.DAY_STARTTIME,
						"yyyy-MM-dd HH:mm:ss"));
				patrolinfo.setEnddate(DateUtil.StringToUtilDate(
						DateUtil.getSeasonTime(patrolinfo.getYear(),
								Integer.parseInt(patrolinfo.getSeasontype()),
								false) + SysConstant.DAY_ENDTIME,
						"yyyy-MM-dd HH:mm:ss"));
			} else {
				// 设置结束时间
				patrolinfo.setStartdate(DateUtil.parseDate(
						patrolinfo.getStarttime() + SysConstant.DAY_ENDTIME,
						"yyyy-MM-dd HH:mm:ss"));
				patrolinfo.setEnddate(DateUtil.parseDate(
						patrolinfo.getEndtime() + SysConstant.DAY_ENDTIME,
						"yyyy-MM-dd HH:mm:ss"));
			}
			patrolinfo.setCreater(user.getPersonId());
			patrolinfo.setCreatername(user.getUserName());
			patrolinfo.setCreatetime(new Date());
			if (StringUtils.isEmpty(patrolinfo.getId())) {
				patrolinfo.setId(null);
			}
			patrolinfoService.save(patrolinfo);
			returnOfSave(id);
			return SUCCESS;

		} catch (ServiceException e) {
			logger.error("执行添加计划出错:" + e.getMessage());
			return ERROR;
		}

	}

	/**
	 * 给save方法设定返回
	 * 
	 * @param id
	 *            patrolinfo.id
	 */
	private void returnOfSave(String id) {
		String url = "";
		if (StringUtils.isNotBlank(id)
				|| StringUtils.isNotBlank(super.getRequest()
						.getParameter("opt"))) {
			url = "/wplan/patrolinfoAction!list.action?type="
					+ patrolinfo.getBusinesstype();
		} else {
			url = "/wplan/patrolinfoAction!input.action?type="
					+ patrolinfo.getBusinesstype();
		}
		if (SysConstant.FORM_IS_SUBMITED.equals(patrolinfo.getIssubmited())) {
			this.addMessage("提交计划信息成功", url, SysConstant.SUCCESS);
		} else {
			this.addMessage("保存计划信息成功", url, SysConstant.SUCCESS);
		}
	}

	/**
	 * 巡检信息列表页面
	 * 
	 * @return
	 */
	public String list() {
		try {
			String business_type = this.getRequest().getParameter("type");
			this.getRequest().setAttribute("businesstype", business_type);
			return LIST;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.logger.error(e);
			return ERROR;
		}
	}

	/**
	 * 查询计划列表信息
	 * 
	 * @return
	 */
	public void query() {
		String businesstype = this.getRequest().getParameter("type");
		patrolinfo.setBusinesstype(businesstype);
		UserInfo userinfo = this.getUser();
		if (!StringUtils.isNotBlank(patrolinfo.getRegionid())) {
			patrolinfo.setRegionid(userinfo.getRegionId());
		}
		if (!StringUtils.isNotBlank(patrolinfo.getContractorid())) {
			if (userinfo.isContractor()) {
				patrolinfo.setContractorid(userinfo.getOrgId());
			}
		}
		Page<Map<String, Object>> page = this.initPage();
		page = patrolinfoService.listByPage(patrolinfo, page);
		super.setExcelParameter(page);
		convertObjToJson(page);

	}

	/**
	 * 删除计划信息
	 */
	public String delete() {
		String id = this.getRequest().getParameter("id");
		String business_type = this.getRequest().getParameter("type");
		patrolinfoService.delete(id);
		this.addMessage("删除计划信息成功", "/wplan/patrolinfoAction!list.action?type="
				+ business_type, SysConstant.SUCCESS);
		return SUCCESS;
	}
}
