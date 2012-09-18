package com.cabletech.common.externalresources;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.cabletech.baseinfo.base.BaseConfig;
import com.cabletech.baseinfo.business.Service.BaseInfoProvider;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.util.ServiceException;

/**
 * 外部资源访问接口
 * 
 * (通用权限： CommonContractor 基础信息： BaseinfoModel 流程服务： flowService)
 * 
 * 
 * @author wangjie
 * @since 2011-10-09
 * @author 杨隽 2012-05-17 去除无用的导入、局部变量和类成员
 * 
 * 
 * **/
@Service
@Scope("prototype")
public class ExternalResourcesAccessAction extends BaseAction {

	private static final long serialVersionUID = 5767952220675870501L;
	private Logger logger = Logger.getLogger("ExternalResourcesAccessService");

	@Resource(name = "baseInfoProvider")
	private BaseInfoProvider baseInfoProvider;

	/**
	 * 区域
	 * 
	 * @throws ServiceException
	 * 
	 */
	public void getRegionJson() throws Exception {
		UserInfo userInfo = this.getUser();
		String userRegionId = userInfo.getRegionId();
		String parentid = StringUtils.isBlank(getParameter("node")) ? "000000"
				: getParameter("node");
		String nodeid = "";
		if ("000000".equals(parentid)) {
			parentid = "";
			nodeid = userRegionId;
		} else {
			nodeid = "";
		}
		String content = baseInfoProvider.getRegionJson(parentid, nodeid,
				BaseConfig.EXTJS_JSON_TYPE);
		logger.debug(content);
		convertObjToJson(content);
	}

	/**
	 * 获取所在机构及下属机构 objtype='ORG' 只显示组织 orgtype 1、2,1为移动，2为代维，不传为所有 objtype='ORG'
	 * orgtype
	 * 
	 * @throws ServiceException
	 */
	public void getOrgnizeJson() throws Exception {
		String regionid = this.getParameter("regionid");
		String objtype = this.getParameter("objtype");
		String orgtype = this.getParameter("orgtype");
		String node = this.getParameter("node");
		String content = baseInfoProvider.getOrgJson(node, objtype, orgtype,
				regionid, BaseConfig.EXTJS_JSON_TYPE);
		logger.debug(content);
		convertObjToJson(content);
	}

	/**
	 * 获取代维公司
	 * 
	 * @throws ServiceException
	 */
	public void getContractorJson() throws Exception {
		UserInfo userInfo = (UserInfo) this.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		String regionid = userInfo.getRegionId();
		String content = baseInfoProvider.getPatrolmanJson(regionid, "2", "1",
				BaseConfig.EXTJS_JSON_TYPE);
		logger.debug(content);
		convertObjToJson(content);
	}

	/**
	 * 获取所在机构及下属机构 objtype='ORG' 只显示组织 orgtype 1、2,1为移动，2为代维，不传为所有 objtype='ORG'
	 * orgtype
	 * 
	 * @throws ServiceException
	 */
	public void getOrgDeptUserJson() throws Exception {
		String regionid = super.getRequest().getParameter("regionid");// 区域ID
		if (!StringUtils.isNotBlank(regionid)) {
			regionid = "";
		}
		String objtype = super.getRequest().getParameter("objtype");
		if (!StringUtils.isNotBlank(objtype)) {
			objtype = "";
		}
		String orgtype = super.getRequest().getParameter("orgtype");
		if (!StringUtils.isNotBlank(orgtype)) {
			orgtype = "";
		}
		String node = super.getRequest().getParameter("node");// 组织ID
		String lv = super.getRequest().getParameter("lv");// 组织ID
		if (!StringUtils.isNotBlank(lv)) {
			node = "";
		}
		String content = baseInfoProvider.getOrgDepUserJson(node, regionid,
				orgtype, lv, "1");
		convertObjToJson(content);
	}

	/**
	 * 字典
	 * 
	 * @throws ServiceException
	 */
	public void getDictionaryJson() throws Exception {
		String type = this.getParameter("type");
		String content = baseInfoProvider.getDicJson(type);
		logger.debug(content);
		convertObjToJson(content);
	}

	/**
	 * 字典 带空选项
	 * 
	 * @throws ServiceException
	 */
	public void getDictionaryBlankJson() throws Exception {
		String type = this.getParameter("type");
		String content = baseInfoProvider.getDicWithblankJson("不限", type);
		logger.debug(content);
		convertObjToJson(content);
	}

	/**
	 * 维护组
	 * 
	 * @throws ServiceException
	 */
	public void getPatrolmanJson() throws Exception {
		UserInfo userInfo = this.getUser();
		String orgid = "";
		if (!"1".equals(userInfo.getOrgtType())) {
			orgid = userInfo.getOrgId();
		}
		String regionid = userInfo.getRegionId();
		String content = baseInfoProvider.getPatrolmanJson(orgid, regionid,
				"2", "2", BaseConfig.EXTJS_JSON_TYPE);
		logger.debug(content);
		convertObjToJson(content);
	}

	/**
	 * 获取所在机构及下属机构 objtype='ORG' 只显示组织 orgtype 1、2,1为移动，2为代维，不传为所有 objtype='ORG'
	 * orgtype
	 * 
	 * @throws ServiceException
	 */
	public void getPatrolmanUserJson() throws Exception {
		String regionid = super.getParameter("regionid");// 区域ID
		if (!StringUtils.isNotBlank(regionid)) {
			regionid = "";
		}
		String objtype = super.getParameter("objtype");
		if (!StringUtils.isNotBlank(objtype)) {
			objtype = "";
		}
		String orgtype = super.getParameter("orgtype");
		if (!StringUtils.isNotBlank(orgtype)) {
			orgtype = "";
		}
		String node = super.getParameter("node");// 组织ID
		if (!StringUtils.isNotBlank(node)) {
			node = "";
		}
		String lv = super.getParameter("lv");// 级数
		if (!StringUtils.isNotBlank(lv)) {
			node = "";
		}
		String content = baseInfoProvider.getPatrolmanUserJson(node, regionid,
				objtype, orgtype, "3", "1");
		convertObjToJson(content);
	}

	@Override
	protected void prepareViewModel() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void prepareSaveModel() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}

}