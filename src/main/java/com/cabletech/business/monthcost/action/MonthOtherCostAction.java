package com.cabletech.business.monthcost.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import com.cabletech.baseinfo.business.Service.BaseInfoOrgService;
import com.cabletech.baseinfo.business.Service.BaseInfoRegionService;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.monthcost.model.MonthOtherCost;
import com.cabletech.business.monthcost.service.MonthOtherCostService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * @author 周剛
 * 
 */
@SuppressWarnings("serial")
@Namespace("/monthcost")
@Results({ @Result(name = "list", location = "/monthcost/other_list.jsp"),
		@Result(name = "input", location = "/monthcost/other_input.jsp"),
		@Result(name = "view", location = "/monthcost/other_view.jsp") })
@Action("/monthothercost")
public class MonthOtherCostAction extends BaseAction<MonthOtherCost, String> {
	private String id;
	/**
	 * 业务类
	 */
	@Resource(name = "monthOtherCostServiceImpl")
	private MonthOtherCostService monthOtherCostService;
	private MonthOtherCost entity = new MonthOtherCost();
	/**
	 * 关于组织机构的业务类
	 */
	@Resource(name = "baseInfoOrgServiceImpl")
	private BaseInfoOrgService baseInfoOrgServiceImpl;
	/**
	 * 数据字典的业务类
	 */
	@Resource(name = "baseInfoRegionServiceImpl")
	private BaseInfoRegionService baseInfoRegionServiceImpl;
	String contractorName;
	String specialtyName;
	String regionName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 列表转入
	 * 
	 * @return
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 列表展示數據
	 * 
	 */
	public void listDate() {
		@SuppressWarnings("rawtypes")
		Page page = super.initPage();
		UserInfo user = this.getUser();
		entity.setRegionId(this.getRequest().getParameter("regionId"));
		entity.setMonths(this.getRequest().getParameter("months"));
		entity.setTypet(this.getRequest().getParameter("typet"));
		entity.setContractorId(this.getRequest().getParameter("contractorId"));
		this.getRequest().setAttribute("user", user);
		page = monthOtherCostService.queryPage(entity, page, user);
		convertObjToJson(page);
	}

	/**
	 * 获取区域名称
	 * @param entity 实体
	 * @return
	 */
	public String getOrignName(MonthOtherCost entity) {
		if (StringUtils.isNotBlank(entity.getRegionId())) {
			if (baseInfoRegionServiceImpl.getRegionMap(entity.getRegionId()) != null) {
				regionName = (String) baseInfoRegionServiceImpl.getRegionMap(
						entity.getRegionId()).get("REGIONNAME");
			}
		}
		return regionName;
	}

	/**
	 * 获取代维组织名称
	 
	 * @param entity 实体
	 * @return
	 */
	public String getContractorName(MonthOtherCost entity) {
		if (StringUtils.isNotBlank(entity.getContractorId())) {
			if (baseInfoOrgServiceImpl.getOrgBaseInfo(entity.getContractorId()) != null) {
				contractorName = (String) baseInfoOrgServiceImpl
						.getOrgBaseInfo(entity.getContractorId()).get(
								"ORGANIZENAME");
			}
		}
		return contractorName;
	}

	@Override
	public String input() {
		id = this.getRequest().getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			entity = monthOtherCostService.getEntityById(id);
		}
		if (entity.getId() != null) {
			entity.setRegionName(getOrignName(entity));
			entity.setContractorName(getContractorName(entity));
		}
		this.getRequest().setAttribute("entity", entity);
		return INPUT;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String delete() {
		id = this.getRequest().getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			monthOtherCostService.deleteEntityById(id);
		}
		boolean s = true;
		String res = setDeleteReturn(s);
		return res;
	}
	

	/**
	 * 设置此方法的返回值
	 * 
	 * @param b
	 *            标示
	 * @return
	 */
	public String setDeleteReturn(boolean b) {
		String url = "";
		url = "/monthcost/monthothercost!list.action?t=" + Math.random();
		assertResult(b, "删除成功!", "删除成功!", url);
		return SUCCESS;
	}

	/**
	 * 保存实体
	 * 
	 * @return
	 */
	public MonthOtherCost saveEntity() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		entity.setWriteDate(sdf.format(new Date()));
		entity.setRemark(this.getRequest().getParameter("entity.remark"));
		entity.setRegionId(this.getRequest().getParameter("entity.regionId"));
		entity.setTypet(this.getRequest().getParameter("entity.typet"));
		entity.setMonths(this.getRequest().getParameter("entity.months"));
		entity.setContractorId(this.getRequest().getParameter(
				"entity.contractorId"));
		entity.setFactMoney(this.getRequest().getParameter("entity.factMoney"));
		entity.setShouldMoney(this.getRequest().getParameter(
				"entity.shouldMoney"));
		entity.setWritePersonId(this.getUser().getPersonId());
		entity = monthOtherCostService.saveEntity(entity);
		return entity;
	}

	/**
	 * 保存
	 * 
	 * @return
	 */
	public String save() {
		if (entity == null) {
			entity = new MonthOtherCost();
		}
		String idw = this.getRequest().getParameter("entity.id");
		if (StringUtils.isNotBlank(idw)) {
			entity.setId(idw);
		}
		entity = saveEntity();
		boolean b = true;
		String retu = "";
		if (entity != null) {
			b = true;
			retu = setSaveReturn(entity.getId(), b);
		} else {
			b = false;
			retu = INPUT;
		}
		return retu;
	}

	/**
	 * 设置此方法的返回值
	 * 
	 * @param id
	 *            主键
	 * @param b
	 *            标示
	 * @return
	 */
	private String setSaveReturn(String id, boolean b) {
		String url = "";
		url = "/monthcost/monthothercost!list.action?t=" + Math.random();
		assertResult(b, "提交成功!", "提交失败!", url);
		return SUCCESS;
	}

	/**
	 * 操作后返回页面信息
	 * 
	 * @param b
	 *            标示
	 * @param s_msg
	 *            成功信息
	 * @param f_msg
	 *            错误信息
	 * @param url
	 *            返回路径
	 */
	private void assertResult(boolean b, String s_msg, String f_msg, String url) {
		if (b) {
			super.addMessage(s_msg, url, SysConstant.SUCCESS);
		} else {
			super.addMessage(f_msg, url, SysConstant.ERROR);
		}
	}

	@Override
	public String view() {
		id = this.getRequest().getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			entity = monthOtherCostService.getEntityById(id);
		}
		if (entity.getId() != null) {
			entity.setRegionName(getOrignName(entity));
			entity.setContractorName(getContractorName(entity));
		}
		this.getRequest().setAttribute("entity", entity);
		return "view";
	}

	@Override
	protected void prepareViewModel() throws Exception {

	}

	@Override
	protected void prepareSaveModel() throws Exception {

	}

	@Override
	public MonthOtherCost getModel() {
		return null;
	}

}
