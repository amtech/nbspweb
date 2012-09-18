package com.cabletech.business.monthcost.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.Service.BaseInfoDictionaryService;
import com.cabletech.baseinfo.business.Service.BaseInfoOrgService;
import com.cabletech.baseinfo.business.Service.BaseInfoRegionService;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.monthcost.model.MonthTimesCost;
import com.cabletech.business.monthcost.service.MonthTimesCostService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * @author zg
 * 
 */
@Namespace("/monthcost")
@Results({ @Result(name = "list", location = "/monthcost/times_list.jsp"),
		@Result(name = "view", location = "/monthcost/times_view.jsp"),
		@Result(name = "input", location = "/monthcost/times_input.jsp") })
@Action("/monthtimescost")
public class MonthTimesCostAction extends BaseAction<MonthTimesCost, String> {
	private static final long serialVersionUID = 1L;
	@Resource(name = "monthTimesCostServiceImpl")
	private MonthTimesCostService monthTimesCostService;
	private MonthTimesCost entity = new MonthTimesCost();
	@Resource(name = "baseInfoOrgServiceImpl")
	private BaseInfoOrgService baseInfoOrgServiceImpl;

	@Resource(name = "baseInfoDictionaryServiceImpl")
	private BaseInfoDictionaryService baseInfoDictionaryServiceImpl;

	@Resource(name = "baseInfoRegionServiceImpl")
	private BaseInfoRegionService baseInfoRegionServiceImpl;
	String contractorName;
	String regionName;
	private String id;
	private String specialtyName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 转向列表页面
	 * 
	 * @return
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 列表内部查询方法
	 * 
	 */
	public void listDate() {
		@SuppressWarnings("rawtypes")
		Page page = super.initPage();
		UserInfo user = this.getUser();
		String regionId = this.getRequest().getParameter("regionId");
		String months = this.getRequest().getParameter("months");
		String typet = this.getRequest().getParameter("typet");
		String contractorId = this.getRequest().getParameter("contractorId");
		String specialty = this.getRequest().getParameter("specialty");
		entity.setRegionId(regionId);
		entity.setMonths(months);
		entity.setTypet(typet);
		entity.setContractorId(contractorId);
		entity.setSpecialty(specialty);
		this.getRequest().setAttribute("user", user);
		page = monthTimesCostService.queryPage(entity, page, user);
		convertObjToJson(page);
	}

	/**
	 * 获取区域名称
	 * 
	 * @param entity
	 *            实体
	 */
	public String getOrignName(MonthTimesCost entity) {
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
	 * 
	 * @param entity
	 *            实体
	 * @return
	 */
	public String getContractor(MonthTimesCost entity) {
		if (StringUtils.isNotBlank(entity.getContractorId())) {
			if (baseInfoOrgServiceImpl.getOrgBaseInfo(entity.getContractorId()) != null) {
				contractorName = (String) baseInfoOrgServiceImpl
						.getOrgBaseInfo(entity.getContractorId()).get(
								"ORGANIZENAME");
			}
		}
		return contractorName;
	}

	/**
	 * 获取专业类型名称
	 * 
	 * @param entity
	 *            实体
	 * @return
	 */
	public String getSaplity(MonthTimesCost entity) {
		if (StringUtils.isNotBlank(entity.getSpecialty())) {
			if (baseInfoDictionaryServiceImpl.getDicMap(entity.getSpecialty(),
					"businesstype") != null) {
				specialtyName = (String) baseInfoDictionaryServiceImpl
						.getDicMap("businesstype", entity.getSpecialty()).get(
								"LABLE");
			}
		}
		return specialtyName;
	}

	@Override
	public String input() {
		id = this.getRequest().getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			entity = monthTimesCostService.getEntityById(id);
		}
		if (entity.getId() != null) {
			entity.setRegionName(getOrignName(entity));
			entity.setContractorName(getContractor(entity));
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
			monthTimesCostService.deleteEntityById(id);
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
		url = "/monthcost/monthtimescost!list.action?t=" + Math.random();
		assertResult(b, "删除成功!", "删除成功!", url);
		return SUCCESS;
	}

	/**
	 * 保存
	 * 
	 * @return
	 */
	public String save() {

		if (entity == null) {
			entity = new MonthTimesCost();
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
	 * 保存实体
	 * 
	 * @return
	 */
	public MonthTimesCost saveEntity() {
		String idw = this.getRequest().getParameter("entity.id");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		entity.setWriteDate(sdf.format(new Date()));
		if (StringUtils.isNotBlank(idw)) {
			entity.setId(idw);
		}
		entity.setRegionId(this.getRequest().getParameter("entity.regionId"));
		entity.setMonths(this.getRequest().getParameter("entity.months"));
		entity.setSpecialty(this.getRequest().getParameter("entity.specialty"));
		entity.setContractorId(this.getRequest().getParameter(
				"entity.contractorId"));
		entity.setNumbers(this.getRequest().getParameter("entity.numbers"));
		entity.setUnitPrice(this.getRequest().getParameter("entity.unitPrice"));
		entity.setTypet(this.getRequest().getParameter("entity.typet"));
		entity.setFactMoney(this.getRequest().getParameter("entity.factMoney"));
		entity.setShouldMoney(this.getRequest().getParameter(
				"entity.shouldMoney"));
		String writePersonId = this.getUser().getPersonId();
		entity.setWritePersonId(writePersonId);
		entity = monthTimesCostService.saveEntity(entity);
		return entity;
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
		url = "/monthcost/monthtimescost!list.action?t=" + Math.random();
		assertResult(b, "提交成功!", "提交失败!", url);

		return SUCCESS;
	}

	/**
	 * 返回值
	 * 
	 * @param b
	 *            标示
	 * @param s_msg
	 *            成功信息
	 * @param f_msg
	 *            失败信息
	 * @param url
	 *            路径
	 */
	private void assertResult(boolean b, String s_msg, String f_msg, String url) {
		if (b) {
			super.addMessage(s_msg, url, SysConstant.SUCCESS);
		} else {
			super.addMessage(f_msg, url, SysConstant.ERROR);
		}
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}

	@Override
	public MonthTimesCost getModel() {
		return entity;
	}

	@Override
	public String view() {
		id = this.getRequest().getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			entity = monthTimesCostService.getEntityById(id);
		}
		if (entity.getId() != null) {
			entity.setRegionName(getOrignName(entity));
			entity.setContractorName(getContractor(entity));
			entity.setSpecialty(getSaplity(entity));
		}
		this.getRequest().setAttribute("entity", entity);
		return VIEW;
	}
}
