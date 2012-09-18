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
import com.cabletech.business.monthcost.model.MonthCheckCost;
import com.cabletech.business.monthcost.service.MonthCheckCostService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * @author Administrator 月度考核费用
 */
@Namespace("/monthcost")
@Results({ @Result(name = "list", location = "/monthcost/check_list.jsp"),
		@Result(name = "input", location = "/monthcost/check_input.jsp"),
		@Result(name = "view", location = "/monthcost/check_view.jsp") })
@Action("/monthcheckcost")
public class MonthCheckCostAction extends BaseAction<MonthCheckCost, String> {
	private static final long serialVersionUID = 1L;
	/**
	 * 业务类
	 */
	@Resource(name = "monthCheckCostServiceImpl")
	private MonthCheckCostService monthCheckCostService;
	private String id;
	private MonthCheckCost entity = new MonthCheckCost();
	/**
	 * 关于组织机构的业务类
	 */
	@Resource(name = "baseInfoOrgServiceImpl")
	private BaseInfoOrgService baseInfoOrgServiceImpl;

	/**
	 * 数据字典的业务类
	 */
	@Resource(name = "baseInfoDictionaryServiceImpl")
	private BaseInfoDictionaryService baseInfoDictionaryServiceImpl;
	/**
	 * 地区的业务类
	 */
	@Resource(name = "baseInfoRegionServiceImpl")
	private BaseInfoRegionService baseInfoRegionServiceImpl;
	String contractorName;
	String specialtyName;
	String regionName;

	/**
	 * 跳转到列表页面
	 * 
	 * @return
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 查询列表数据
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public void listDate() {
		Page page = super.initPage();
		UserInfo user = this.getUser();
		entity.setRegionId(this.getRequest().getParameter("regionId"));
		entity.setMonths(this.getRequest().getParameter("months"));
		entity.setSpecialty(this.getRequest().getParameter("specialty"));
		entity.setContractorId(this.getRequest().getParameter("contractorId"));
		this.getRequest().setAttribute("user", user);
		page = monthCheckCostService.queryPage(entity, page, user);
		convertObjToJson(page);
	}

	/**
	 * 获取区域名称
	 * 
	 * @param entity
	 *            实体
	 */
	public String getOrignName(MonthCheckCost entity) {
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
	public String getContractor(MonthCheckCost entity) {
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
	public String getSaplity(MonthCheckCost entity) {
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
			entity = monthCheckCostService.getEntityById(id);
		}
		if (entity.getId() != null) {
			entity.setRegionName(getOrignName(entity));
			entity.setContractorName(getContractor(entity));
		}
		this.getRequest().setAttribute("entity", entity);
		return INPUT;
	}

	@Override
	public String view() {
		id = this.getRequest().getParameter("id");
		if (StringUtils.isNotBlank(id))
			entity = monthCheckCostService.getEntityById(id);
		if (entity.getId() != null) {
			entity.setRegionName(getOrignName(entity));
			entity.setContractorName(getContractor(entity));
			entity.setSpecialty(getSaplity(entity));
		}
		this.getRequest().setAttribute("entity", entity);
		return VIEW;
	}

	public String getContractorName() {
		return contractorName;
	}

	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}

	public String getSpecialtyName() {
		return specialtyName;
	}

	public void setSpecialtyName(String specialtyName) {
		this.specialtyName = specialtyName;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	/**
	 * 删除数据
	 * 
	 * @return
	 */
	public String delete() {
		id = this.getRequest().getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			monthCheckCostService.deleteEntityById(id);
		}
		boolean s = true;
		String res = setDeleteReturn(s);
		return res;
	}

	/**
	 * 获取Sdf
	 * 
	 * @return
	 */
	public String getsdf() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String writedate = sdf.format(new Date());
		return writedate;
	}

	/**
	 * 保存实体
	 * 
	 * @return
	 */
	public MonthCheckCost saveEntity() {
		entity.setWriteDate(getsdf());
		entity.setRegionId(this.getRequest().getParameter("entity.regionId"));
		entity.setMonths(this.getRequest().getParameter("entity.months"));
		entity.setSpecialty(this.getRequest().getParameter("entity.specialty"));
		entity.setContractorId(this.getRequest().getParameter(
				"entity.contractorId"));
		entity.setNumbers(this.getRequest().getParameter("entity.numbers"));
		entity.setUnitPrice(this.getRequest().getParameter("entity.unitPrice"));
		entity.setCheckFraction(this.getRequest().getParameter(
				"entity.checkFraction"));
		entity.setSubtractMoney(this.getRequest().getParameter(
				"entity.subtractMoney"));
		entity.setFactMoney(this.getRequest()
				.getParameter("entity.shouldMoney"));
		entity.setShouldMoney(this.getRequest().getParameter(
				"entity.shouldMoney"));
		entity.setWritePersonId(this.getUser().getPersonId());
		entity = monthCheckCostService.saveEntity(entity);
		return entity;
	}

	/**
	 * 保存数据
	 * 
	 * @return
	 */
	public String save() {
		if (entity == null) {
			entity = new MonthCheckCost();
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
		this.getRequest().setAttribute("entity", entity);
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
		url = "/monthcost/monthcheckcost!list.action?t=" + Math.random();
		assertResult(b, "提交成功!", "提交成功!", url);

		return SUCCESS;
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
		url = "/monthcost/monthcheckcost!list.action?t=" + Math.random();
		assertResult(b, "删除成功!", "删除成功!", url);
		return SUCCESS;
	}

	/**
	 * 返回页面信息展示
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
	public MonthCheckCost getModel() {
		return entity;
	}

	@Override
	protected void prepareViewModel() throws Exception {

	}

	@Override
	protected void prepareSaveModel() throws Exception {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
