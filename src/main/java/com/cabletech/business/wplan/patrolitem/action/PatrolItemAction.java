package com.cabletech.business.wplan.patrolitem.action;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.wplan.patrolitem.condition.parameter.ItemQueryParameter;
import com.cabletech.business.wplan.patrolitem.model.PatrolItem;
import com.cabletech.business.wplan.patrolitem.service.PatrolSubItemService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 巡检项 ACTION
 * 
 * @author wnagjie
 * @author 杨隽 2011-10-25 添加启用巡检项目、修改逻辑删除为作废，废弃物理删除
 * @author 杨隽 2012-02-14 重构query方法
 * @author 杨隽 2012-02-15 添加export方法
 * @author 杨隽 2012-05-17 去除无用的导入、局部变量和类成员
 * 
 */
@Namespace("/wplan")
@Results({
		@Result(name = "input", location = "/wplan/patrolitem/list_patrol_items.jsp"),
		@Result(name = "list", location = "/wplan/patrolitem/list_patrol_items.jsp") })
@Action("/patrolItemAction")
@Scope("prototype")
public class PatrolItemAction extends BaseAction<PatrolItem, String> {
	private static final long serialVersionUID = 1L;
	// 巡检项列表页面跳转路径
	private static final String PATROL_ITEM_LIST_URL = "/wplan/patrolItemAction!list.action?parameter.businessType=";
	// 巡检项查询条件参数
	private ItemQueryParameter parameter = new ItemQueryParameter();
	// 巡检子项业务处理服务
	@Resource(name = "patrolSubItemServiceImpl")
	private PatrolSubItemService patrolSubItemService;

	/**
	 * 批量作废
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteLogic() throws Exception {
		String[] itemId = getItemIdArray();
		patrolSubItemService.deleteLogic(itemId);
		StringBuffer url = getUrl();
		super.addMessage("提示：巡检项信息作废成功 ", url.toString(), SysConstant.SUCCESS);
		return SUCCESS;
	}

	/**
	 * 批量启用
	 * 
	 * @return
	 * @throws Exception
	 */
	public String startUsing() throws Exception {
		String[] itemId = getItemIdArray();
		patrolSubItemService.startUsing(itemId);
		StringBuffer url = getUrl();
		super.addMessage("提示：巡检项信息启用成功 ", url.toString(), SysConstant.SUCCESS);
		return SUCCESS;
	}

	/**
	 * 转到巡检列表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String list() throws Exception {
		return LIST;
	}

	/**
	 * 巡检项列表数据
	 */
	public void listdata() {
		UserInfo user = super.getUser();
		Page page = super.initPage();
		parameter.setPage(page);
		parameter.setUser(user);
		if (!QueryParameter.IS_QUERY_PARAMETER.equals(parameter.getIsQuery())) {
			parameter.setShowAll(ItemQueryParameter.NO_SHOW_ALL);
		}
		if (StringUtils.isBlank(parameter.getShowAll())) {
			parameter.setShowAll(ItemQueryParameter.NO_SHOW_ALL);
		}
		if (user.isProvinceMobile()) { // 省公司
			parameter.setIsProvince("true");
		} else { // 市公司
			parameter.setIsProvince("false");
			parameter.setRegionId(user.getRegionId());
		}
		page = patrolSubItemService.queryPage(parameter);
		super.setExcelParameter(page);
		convertObjToJson(page);
	}

	@Override
	public PatrolItem getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	public ItemQueryParameter getParameter() {
		return parameter;
	}

	public void setParameter(ItemQueryParameter parameter) {
		this.parameter = parameter;
	}

	/**
	 * 获取巡检项的编号数组
	 * 
	 * @return String[] 巡检项的编号数组
	 */
	private String[] getItemIdArray() {
		String[] itemId = new String[] {};
		if (StringUtils.isNotBlank(parameter.getId())) {
			itemId = parameter.getId().split(",");
		}
		return itemId;
	}

	/**
	 * 获取成功返回页面中“返回”按钮的跳转路径（从待办列表中跳入处理功能页面）
	 * 
	 * @return String “返回”按钮的跳转路径
	 */
	private StringBuffer getUrl() {
		StringBuffer url = new StringBuffer("");
		String pageNo = super.getRequest().getParameter("pageNo");
		url.append(PATROL_ITEM_LIST_URL);
		url.append(parameter.getBusinessType());
		url.append("&pageNo=");
		url.append(pageNo);
		return url;
	}

	@Override
	protected void prepareViewModel() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	protected void prepareSaveModel() throws Exception {
		// TODO Auto-generated method stub
	}
}