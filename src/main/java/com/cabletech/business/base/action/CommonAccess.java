package com.cabletech.business.base.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.Service.BaseInfoProvider;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.resource.model.ResourceInfo;
import com.cabletech.business.resource.service.ResourceService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;
import com.cabletech.common.util.ServiceException;

/**
 * 通用访问模块
 * 
 * @author Administrator
 * 
 */
@Namespace("/")
@Results({
		@Result(name = "stafftree", location = "/common/orgperson_select.jsp"),
		@Result(name = "regiontree", location = "/common/region_select.jsp"),
		@Result(name = "orgtree", location = "/common/org_select.jsp"),
		@Result(name = "patrolgrouptree", location = "/common/orgpatrolgroup_select.jsp"),
		@Result(name = "getresource", location = "/common/resource_select.jsp") })
@Action("commonaccess")
public class CommonAccess extends BaseAction<String, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name = "baseInfoProvider")
	private BaseInfoProvider baseInfoProvider;
	/*
	 * 资源信息服务
	 */
	@Resource(name = "resourceServiceImpl")
	private ResourceService resourceService;
	/**
	 * 资源信息实体
	 */
	private ResourceInfo rsparameter = new ResourceInfo();

	/**
	 * 获取字典数据
	 */
	public void getDic() {
		String columntype = this.getRequest().getParameter("columntype");// 字典类型
		convertObjToJson(baseInfoProvider.getDicList(columntype));
	}

	/**
	 * 获取人员树Action
	 * 
	 * @return
	 */
	public String getstaff() {
		List<Map<String, Object>> list = getOrgDeptUserList();
		String jsonstr = super.convertObjToJsonStr(list);
		checkstyle();
		logger.info("人员树数据:" + jsonstr);
		super.getRequest().getSession().setAttribute("stafftree", jsonstr);
		return "stafftree";
	}

	/**
	 * 获取用户树
	 * 
	 * @return
	 */
	public String getuser() {
		List<Map<String, Object>> list = getOrgDeptUserList();
		if (null != list && list.size() > 0) {
			// 有用户人员
			List<Map<String, Object>> newlist = new ArrayList<Map<String, Object>>();
			for (int i = 0, len = list.size(); i < len; i++) {
				if ("STAFF".equals(list.get(i).get("OBJTYPE"))) {
					if (null != list.get(i).get("USERID")) {
						newlist.add(list.get(i));
					}
				} else {
					newlist.add(list.get(i));
				}
			}
			String jsonstr = super.convertObjToJsonStr(newlist);
			checkstyle();
			logger.info("用户树数据:" + jsonstr);
			super.getRequest().getSession().setAttribute("stafftree", jsonstr);
		}
		return "stafftree";
	}

	/**
	 * 获取巡检组Action
	 * 
	 * @return
	 */
	public String getpatrolgroup() {
		List<Map<String, Object>> list = getOrgPatrolgroupList();
		String jsonstr = super.convertObjToJsonStr(list);
		logger.info("巡检组树数据:" + jsonstr);
		super.getRequest().getSession()
				.setAttribute("patrolgrouptree", jsonstr);
		return "patrolgrouptree";
	}

	/**
	 * 获取区域Action
	 * 
	 * @return
	 */
	public String getregion() {
		List<Map<String, Object>> list = getRegionList();
		String jsonstr = super.convertObjToJsonStr(list);
		logger.info("区域树数据:" + jsonstr);
		super.getRequest().getSession().setAttribute("regiontree", jsonstr);
		return "regiontree";
	}

	/**
	 * 获取组织选择页面Action
	 * 
	 * @return
	 */
	public String getorg() {
		List<Map<String, Object>> list = getOrgList();
		String jsonstr = super.convertObjToJsonStr(list);
		logger.info("组织树数据:" + jsonstr);
		super.getRequest().getSession().setAttribute("orgtree", jsonstr);
		return "orgtree";
	}

	/**
	 * 获取所在机构及下属机构 objtype='ORG' 只显示组织 orgtype 1、2,1为移动，2为代维，不传为所有 objtype='ORG'
	 * orgtype
	 * 
	 * @throws ServiceException
	 */
	private List<Map<String, Object>> getOrgDeptUserList() {
		String lv = this.getRequest().getParameter("lv");// 级别
		UserInfo user = this.getUser();
		String orgtype = this.getRequest().getParameter("orgtype");
		String orgid = this.getRequest().getParameter("orgid");
		String regionid = this.getRequest().getParameter("regionid");
		if (StringUtils.isBlank(orgtype)) {
			regionid = user.getRegionId();
		}
		if (StringUtils.isBlank(orgtype)) {
			if (user.isContractor()) {
				orgtype = user.getOrgType();
				orgid = user.getOrgId();
			}
		}
		if (!StringUtils.isNotBlank(lv)) {
			lv = "3";
		}
		List<Map<String, Object>> orgdeptlist = baseInfoProvider.getUserList(
				orgid, regionid, "", orgtype, lv);
		return orgdeptlist;
	}

	/**
	 * 获取巡检组List
	 * 
	 * @return
	 */
	private List<Map<String, Object>> getOrgPatrolgroupList() {
		UserInfo user = this.getUser();
		String orgid = this.getRequest().getParameter("orgid");
		String regionid = this.getRequest().getParameter("regionid");
		if (user.isContractor()) {
			orgid = user.getOrgId();
		}
		if (StringUtils.isBlank(regionid)) {
			regionid = user.getRegionId();
		}
		String lv = super.getRequest().getParameter("lv");// 级别
		if (!StringUtils.isNotBlank(lv)) {
			lv = "3";
		}
		List<Map<String, Object>> patrolgrouplist = baseInfoProvider
				.getPatrolmanList(orgid, regionid, "2", lv);
		return patrolgrouplist;

	}

	/**
	 * 获取区域List
	 * 
	 * @return
	 */
	private List<Map<String, Object>> getRegionList() {
		UserInfo user = this.getUser();
		String lv = this.getRequest().getParameter("lv");// 级别
		if (!StringUtils.isNotBlank(lv)) {
			lv = "";
		}
		List<Map<String, Object>> regiongrouplist = baseInfoProvider
				.regionIteration(user.getRegionId(), lv);
		return regiongrouplist;

	}

	/**
	 * 获取组织LIST
	 * 
	 * @return
	 */
	private List<Map<String, Object>> getOrgList() {
		UserInfo user = this.getUser();
		List<Map<String, Object>> orglist = null;
		String orgtype = this.getRequest().getParameter("orgtype");// 组织类型
		String orgid = this.getRequest().getParameter("orgid");
		String regionid = this.getRequest().getParameter("regionid");
		if (StringUtils.isBlank(orgtype)) {
			if (user.isContractor()) {
				orgtype = user.getOrgType();
				orgid = user.getOrgId();
			}
		} else {
			if (user.isContractor()) {
				orgtype = user.getOrgType();
				orgid = user.getOrgId();
			}
		}
		if (StringUtils.isBlank(regionid)) {
			regionid = user.getRegionId();
		}
		orglist = baseInfoProvider.getUserList(orgid, regionid, "ORG", orgtype,
				"1");
		return orglist;

	}

	/**
	 * 选择树模式
	 */
	private void checkstyle() {
		String flag = this.getRequest().getParameter("flag");// 级别
		Map<String, Object> flagMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(flag)) {
			if ("radio".equals(flag)) {
				flagMap.put("enable", true);
				flagMap.put("chkStyle", "radio");
				flagMap.put("radioType", "all");
			}
		} else {
			flagMap.put("enable", true);
		}
		this.getRequest().getSession()
				.setAttribute("checkstyle", convertObjToJsonStr(flagMap));
	}

	/**
	 * 转到资源选择界面
	 * 
	 * @return
	 */
	public String getresourceinfo() {
		// 是否允许多选
		String multi = this.getRequest().getParameter("multi");
		if (StringUtils.isNotBlank(multi)) {
			if ("true".equals(multi)) {
				multi = "true";
			} else {
				multi = "false";
			}
		} else {
			multi = "false";
		}
		String businessType = super.getRequest().getParameter("businessType");
		Map<String, Object> resourceTypeMap = getResourceTypeMap(businessType);
		super.getRequest().setAttribute("businessType", businessType);
		super.getRequest().setAttribute("resourceTypeMap", resourceTypeMap);
		this.getRequest().setAttribute("orgid",
				this.getRequest().getParameter("orgid"));
		this.getRequest().getSession().setAttribute("multi", multi);
		return "getresource";
	}

	/**
	 * 根据专业类型获取资源类型map
	 * 
	 * @param businessType
	 *            String
	 * @return Map<String, Object>
	 */
	private Map<String, Object> getResourceTypeMap(String businessType) {
		Map<String, Object> resourceTypeMap = new HashMap<String, Object>();
		Map<String, String> map = SysConstant.getResourceTypeMap();
		if (SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C31
				.equals(businessType)) {
			resourceTypeMap.put(SysConstant.DB_TABLENAME_RS_BASESTATION,
					map.get(SysConstant.DB_TABLENAME_RS_BASESTATION));
		}
		if (SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C32
				.equals(businessType)) {
			resourceTypeMap.put(SysConstant.DB_TABLENAME_RS_REPEATER,
					map.get(SysConstant.DB_TABLENAME_RS_REPEATER));
			resourceTypeMap.put(SysConstant.DB_TABLENAME_RS_OVERRIDEINFO,
					map.get(SysConstant.DB_TABLENAME_RS_OVERRIDEINFO));
		}
		if (SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C33
				.equals(businessType)) {
			resourceTypeMap.put(SysConstant.DB_TABLENAME_RS_OURDOOR_FACILITIES,
					map.get(SysConstant.DB_TABLENAME_RS_OURDOOR_FACILITIES));
		}
		if (SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C34
				.equals(businessType)) {
			resourceTypeMap.put(SysConstant.DB_TABLENAME_RS_GROUPCUSTOMER,
					map.get(SysConstant.DB_TABLENAME_RS_GROUPCUSTOMER));
			resourceTypeMap.put(SysConstant.DB_TABLENAME_RS_CUSTOMER,
					map.get(SysConstant.DB_TABLENAME_RS_CUSTOMER));
		}
		return resourceTypeMap;
	}

	/**
	 * 获取资源信息
	 */
	public void getresourceinfolist() {
		String businessType = super.getRequest().getParameter("businessType");
		String orgid = super.getRequest().getParameter("orgid");
		Map<String, Object> map = (Map<String, Object>) super.getRequest()
				.getSession().getAttribute("businessTypeMap");
		@SuppressWarnings("unchecked")
		Page<Map<String, Object>> page = this.initPage();
		this.rsparameter.setRegionid(this.getUser().getRegionId());
		this.rsparameter.setOrgid(orgid);
		this.rsparameter.setBusinessType(businessType);
		this.rsparameter.setBusinessTypeMap(map);
		convertObjToJson(resourceService
				.getResourceInfo(this.rsparameter, page));
	}

	@Override
	public String getModel() {
		return null;
	}

	@Override
	protected void prepareViewModel() throws Exception {

	}

	@Override
	protected void prepareSaveModel() throws Exception {

	}

	/**
	 * @return the rsparameter
	 */
	public ResourceInfo getRsparameter() {
		return rsparameter;
	}

	/**
	 * @param rsparameter
	 *            the rsparameter to set
	 */
	public void setRsparameter(ResourceInfo rsparameter) {
		this.rsparameter = rsparameter;
	}

}
