package com.cabletech.business.wplan.plan.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.business.wplan.plan.model.Patrolinfo;
import com.cabletech.business.wplan.plan.service.PatrolinfoService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;
/**
 * 计划待办工作Action
 * 
 * @author zhaobi
 * 
 */
@Namespace("/wplan")
@Results({ @Result(name = "list", location = "/wplan/plan/patrolinfo_waithandled_list.jsp") })
@Action("/patrolinfoWaitHandledAction")
public class PatrolinfoWaitHandledAction extends BaseAction<Patrolinfo, String> {
	/**
	 * 巡检计划信息
	 */
	private Patrolinfo patrolinfo = new Patrolinfo();
	/**
	 * 巡检计划信息服务
	 */
	@Resource(name = "patrolinfoServiceImpl")
	private PatrolinfoService patrolinfoService;

	@Override
	public Patrolinfo getModel() {
		return patrolinfo;
	}

	/**
	 * 待办工作列表页面
	 * 
	 * @return
	 */
	public String query() {
		try {
			String businesstype = this.getRequest().getParameter("type");
			super.getRequest().setAttribute("businesstype", businesstype);
			return LIST;
		} catch (Exception e) {
			logger.error("执行待审批页面出错:" + e.getMessage());
			return ERROR;
		}
	}
	/**
	 * 待办工作列表
	 * 
	 * @throws Exception
	 */
	public void list() throws Exception {
		Page<Map<String, Object>> page = this.initPage();
		String businesstype = this.getRequest().getParameter("type");
		patrolinfo.setBusinesstype(businesstype);
		patrolinfo.setPlanstate(SysConstant.WAIT_AUDITING_STATE);
		page = patrolinfoService.queryWaithHandledList(patrolinfo, page,
				this.getUser());
		super.setExcelParameter(page);
		convertObjToJson(page);
	}
	@Override
	protected void prepareViewModel() throws Exception {
		if (null == patrolinfo) {
			patrolinfo = new Patrolinfo();
		}
	}
	@Override
	protected void prepareSaveModel() throws Exception {
		// TODO Auto-generated method stub

	}
	/**
	 * @return the patrolinfo
	 */
	public Patrolinfo getPatrolinfo() {
		return patrolinfo;
	}

	/**
	 * @param patrolinfo
	 *            the patrolinfo to set
	 */
	public void setPatrolinfo(Patrolinfo patrolinfo) {
		this.patrolinfo = patrolinfo;
	}

}
