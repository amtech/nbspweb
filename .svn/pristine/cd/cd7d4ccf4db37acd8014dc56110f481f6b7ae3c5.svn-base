package com.cabletech.business.workflow.fault.action;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Resource;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.resource.service.ResourceService;
import com.cabletech.business.workflow.fault.condition.parameter.FaultQueryParameter;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.util.Page;

/**
 * 故障Action基类
 * 
 * @param <T>
 *            实体类
 * @param <PK>
 *            主键类
 * @author 杨隽 2011-10-26 创建
 * @author 杨隽 2011-10-31 添加“查询条件生成器KEY”常量
 * @author 杨隽 2011-11-03 添加“故障专业”参数变量
 * @author 杨隽 2011-11-04 添加“获取资源信息列表”方法
 * @author 杨隽 2011-11-24 修改getResources()方法中的“获取资源信息列表”调用的方法
 * @author 杨隽 2012-02-08 添加“待办页面跳转”常量和“未派单页面跳转”常量
 * 
 */
public abstract class FaultBaseAction<T, PK extends Serializable> extends
		BaseAction<T, PK> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// “待办页面跳转”常量
	public static final String WAIT_HANDLED_PAGE_URL = "/workflow/faultDispatchAction!waitHandledList.action?parameter.isQuery=1&businessType=";
	// “未派单页面跳转”常量
	public static final String UNDISPATCHED_PAGE_URL = "/workflow/faultAlertAction!unDispatchedList.action?parameter.isQuery=1&businessType=";
	// “故障派单草稿箱页面跳转”常量
	public static final String DISPATCH_DRAFT_PAGE_URL = "/workflow/faultDispatchDraftAction!list.action?parameter.isQuery=1&businessType=";
	// “故障派单待取消页面跳转”常量
	public static final String WAIT_CANCELED_PAGE_URL = "/workflow/faultDispatchCancelAction!waitCanceledList.action?parameter.isQuery=1&businessType=";
	// “故障派单待删除页面跳转”常量
	public static final String WAIT_DELETED_PAGE_URL = "/workflow/faultDispatchWaitDeletedAction!list.action?parameter.isQuery=1&businessType=";
	// 故障专业查询条件
	private String businessType;
	// 故障查询条件参数
	protected FaultQueryParameter parameter = new FaultQueryParameter();
	// 资源业务处理服务
	@Resource(name = "resourceServiceImpl")
	protected ResourceService faultResourceManager;

	/**
	 * 获取资源Map<资源编号，资源名称>
	 * 
	 * @param businessType
	 *            String 专业类型
	 * @param userInfo
	 *            UserInfo 登录用户信息
	 * @return Map<String, Object> 资源Map
	 */
	public Map<String, Object> getResourcesMap(String businessType,
			UserInfo userInfo) {
		Map<String, Object> map = faultResourceManager.getResourcesMap(
				businessType, userInfo);
		return map;
	}

	/**
	 * 根据故障专业获取故障的资源列表
	 * 
	 * @return String 故障的资源列表字符串
	 * @throws Exception
	 *             异常
	 */
	public String getResources() throws Exception {
		UserInfo userInfo = super.getUser();
		String resources = faultResourceManager.getResources(parameter,
				userInfo);
		super.outPrint(resources, false);
		return null;
	}

	/**
	 * 根据资源编号和资源类型获取资源名称
	 * 
	 * @param stationId
	 *            String 资源编号
	 * @param stationType
	 *            String 资源类型
	 * @return String 资源名称
	 */
	public String getResourceName(String stationId, String stationType) {
		return faultResourceManager.getResourceName(getBusinessType(),
				stationId, stationType);
	}

	public FaultQueryParameter getParameter() {
		return parameter;
	}

	public void setParameter(FaultQueryParameter parameter) {
		this.parameter = parameter;
	}

	public String getBusinessType() {
		return businessType;
	}

	/**
	 * 
	 * @param businessType
	 *            String
	 */
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
		if (FaultQueryParameter.isNull(parameter)) {
			parameter = new FaultQueryParameter();
		}
		parameter.setBusinessType(businessType);
	}

	/**
	 * 预置列表查询的参数信息
	 * 
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void preSetListQuery(UserInfo userInfo) {
		Map<String, Object> map = faultResourceManager.getResourcesMap(
				this.getBusinessType(), userInfo);
		super.sessionManager.put("RESOURCES_MAP", map);
		Page page = super.initPage();
		parameter.setPage(page);
	}
}
