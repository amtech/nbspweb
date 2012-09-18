package com.cabletech.business.wplan.nopatrolstation.action;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.wplan.nopatrolstation.model.NoPatrolStation;
import com.cabletech.business.wplan.nopatrolstation.service.NoPatrolStationService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 未巡检站点原因登记Action
 * 
 * @author 杨隽 2012-07-23 创建
 * 
 */
@Namespace("/wplan")
@Results({
		@Result(name = "select", location = "/wplan/nopatrolstation/nopatrolstation_select_station.jsp"),
		@Result(name = "input", location = "/wplan/nopatrolstation/nopatrolstation_input.jsp"),
		@Result(name = "list", location = "/wplan/nopatrolstation/nopatrolstation_list.jsp"),
		@Result(name = "view", location = "/wplan/nopatrolstation/nopatrolstation_view.jsp"),
		@Result(name = "confirm_input", location = "/wplan/nopatrolstation/nopatrolstation_confirm.jsp") })
@Action("/noPatrolStationAction")
public class NoPatrolStationAction extends BaseAction<NoPatrolStation, String> {
	/**
	 * 未巡检站点原因登记列表URL
	 */
	public static final String NO_PATROL_STATION_LIST_URL = "/wplan/noPatrolStationAction!list.action";
	/**
	 * 选择站点计划页面路径
	 */
	public static final String SELECT = "select";
	/**
	 * 未巡检站点确认输入路径
	 */
	public static final String CONFIRM_INPUT = "confirm_input";
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 输入页面参数
	 */
	private NoPatrolStation noPatrolStation = new NoPatrolStation();
	/**
	 * 未巡检站点原因登记业务
	 */
	@Resource(name = "noPatrolStationServiceImpl")
	private NoPatrolStationService noPatrolStationService;

	/**
	 * 进入未巡检站点原因列表页面
	 * 
	 * @return String
	 */
	public String list() {
		String ifConfirm = super.getParameter("ifConfirm");
		super.getRequest().setAttribute("ifConfirm", ifConfirm);
		return LIST;
	}

	/**
	 * 未巡检站点原因列表读取JSON
	 */
	@SuppressWarnings("rawtypes")
	public void listData() {
		Page page = super.initPage();
		UserInfo user = super.getUser();
		noPatrolStation.setUser(user);
		noPatrolStationService.queryNoPatrolStationPage(noPatrolStation, page);
		super.convertObjToJson(page);
	}

	/**
	 * 进入选择站点计划页面
	 * 
	 * @return String
	 */
	public String selectSite() {
		String resourceType = super.getRequest().getParameter("resourceType");
		super.getRequest().setAttribute("resourceType", resourceType);
		return SELECT;
	}

	/**
	 * 选择站点计划列表读取JSON
	 */
	@SuppressWarnings("rawtypes")
	public void selectSiteData() {
		Page page = super.initPage();
		UserInfo user = super.getUser();
		noPatrolStation.setUser(user);
		noPatrolStationService.queryStationPage(noPatrolStation, page);
		super.convertObjToJson(page);
	}

	/**
	 * 未巡检站点登记输入页面
	 * 
	 * @return String
	 */
	public String input() {
		return INPUT;
	}

	/**
	 * 未巡检站点登记查看页面
	 * 
	 * @return String
	 */
	public String view() {
		return VIEW;
	}

	/**
	 * 未巡检站点确认输入页面
	 * 
	 * @return String
	 */
	public String confirmInput() {
		try {
			prepareViewModel();
		} catch (Exception e) {
		}
		return CONFIRM_INPUT;
	}

	/**
	 * 未巡检站点登记
	 * 
	 * @return String
	 */
	public String save() {
		UserInfo user = super.getUser();
		noPatrolStationService.saveNoPatrolStation(noPatrolStation, user);
		super.addMessage("未巡检站点登记成功！", NO_PATROL_STATION_LIST_URL,
				SysConstant.SUCCESS);
		return SUCCESS;
	}

	/**
	 * 未巡检站点登记确认
	 * 
	 * @return String
	 */
	public String confirm() {
		UserInfo user = super.getUser();
		noPatrolStationService.confirmNoPatrolStation(noPatrolStation, user);
		super.addMessage("未巡检站点登记确认成功！", NO_PATROL_STATION_LIST_URL,
				SysConstant.SUCCESS);
		return SUCCESS;
	}

	@Override
	public NoPatrolStation getModel() {
		return noPatrolStation;
	}

	public NoPatrolStation getNoPatrolStation() {
		return noPatrolStation;
	}

	public void setNoPatrolStation(NoPatrolStation noPatrolStation) {
		this.noPatrolStation = noPatrolStation;
	}

	@Override
	protected void prepareViewModel() throws Exception {
		String id = super.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			noPatrolStation = noPatrolStationService.viewNoPatrolStation(id);
		}
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}
}
