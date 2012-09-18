package com.cabletech.business.resource.action;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.baseinfo.business.util.CoordinateTransformer;
import com.cabletech.business.resource.model.ResSite;
import com.cabletech.business.resource.service.LonLatReviseService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.externalresources.ExternalResourcesAccessService;

/**
 * 坐标校正
 * 
 * @author zhaobi
 * 
 */
@Namespace("/resource")
@Results({ @Result(name = "input", location = "/resource/resource_lonlat_revise.jsp") })
@Action("/lonlatReviseAction")
public class LonLatReviseAction extends BaseAction<ResSite, String> {

	/**
	 * 经度纬度转换服务
	 */
	@Resource(name = "lonLatReviseServiceImpl")
	private LonLatReviseService lonLatReviseService;
	// 外部资源服务业务服务
	@Resource(name = "externalResourcesAccessService")
	private ExternalResourcesAccessService externalResourcesAccessService;
	/**
	 * 点信息
	 */
	private ResSite pointinfo = new ResSite();

	@Override
	public ResSite getModel() {
		// TODO Auto-generated method stub
		return pointinfo;
	}

	@Override
	protected void prepareViewModel() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void prepareSaveModel() throws Exception {
		// TODO Auto-generated method stub

	}

	/*
	 * input
	 * @see com.cabletech.common.base.BaseAction#input()
	 */
	@Override
	public String input() {
		Map<String, String> map = SysConstant.getResourceTypeMap();
		getRequest().setAttribute("resource_type_map", map);
		return INPUT;
	}

	/**
	 * 保存
	 * @return
	 */
	public String save() {
//		Map<String, Double> map = CoordinateTransformer.transformForMap(
//				pointinfo.getLon().doubleValue(), pointinfo.getLat().doubleValue(),
//				externalResourcesAccessService.getCoordinatesource(),
//				externalResourcesAccessService.getCoordinatetarget());
//		pointinfo.setCt_x(BigDecimal.valueOf((double)map.get("x")));
//		pointinfo.setCt_y(BigDecimal.valueOf((double)map.get("y")));
//		lonLatReviseService.updateCoordinate(pointinfo);
		this.addMessage("坐标转换成功", "/resource/lonlatReviseAction!input.action",
				SysConstant.SUCCESS);
		return SUCCESS;
	}

	/**
	 * 获取资源信息
	 */
	public void getResPoint() {
		UserInfo user = this.getUser();
		String rname = this.getRequest().getParameter("q");
//		if (user.isContractor()) {
//			pointinfo.setOrgid(user.getOrgId());
//		}
//		if (StringUtils.isNotBlank(rname)) {
//			pointinfo.setPointname(rname);
//		}
//		pointinfo.setRegionid(user.getRegionId());
		List<Map<String, Object>> list = lonLatReviseService
				.getResourcePoint(pointinfo);
		convertObjToJson(list);
	}

}
