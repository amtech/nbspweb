package com.cabletech.business.desktop.action;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import com.cabletech.baseinfo.base.BaseUtil;
import com.cabletech.baseinfo.business.Service.BaseInfoProvider;
import com.cabletech.business.desktop.service.DeskTopWorkBenchService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;

/**
 * 
 * 个人工作台--用户快捷方式
 * @author wj
 * 
 */
@Namespace("/desktop")
@Results({ 
	        @Result(name = "list", location = "/frames/default/basework.jsp"),
		    @Result(name = "input", location = "/frames/default/more/workbench_set.jsp"),
		    @Result(name = "reload", location="deskTopWorkBenchAction!input.action",type="redirect")
})
@Action("/deskTopWorkBenchAction")
public class DeskTopWorkBenchAction extends BaseAction<String, String>{
	
	@Resource(name = "deskTopWorkBenchServiceImpl")
	private DeskTopWorkBenchService deskTopWorkBenchService;

	@Resource(name = "baseInfoProvider")
	private BaseInfoProvider baseInfoProvider;
	
	/**
	 * 
	 * @return
	 */
	public String list(){ 
		return LIST;
    }
	
	@Override
	public String input(){ 
		List<Map<String, Object>> shortcuts = deskTopWorkBenchService.queryUserShortcuts(this.getUser().getUserId());
		List<Map<String, Object>> menuList  = baseInfoProvider.getMenuService().getUserMenuList(getUser().getUserId(),SysConstant.SYSTEM_ID,"");
		String menuJson = BaseUtil.diversionJson(menuList);
		getRequest().setAttribute("menuJson", menuJson);
		getRequest().setAttribute("shortcuts", shortcuts);
		return INPUT;
    }
	
	/**
	 * 
	 * @return
	 */
	public String save(){ 
		deskTopWorkBenchService.saveUserShortcuts(this.getUser().getUserId(), getRequest().getParameter("menuIds"));
		return "reload";
    }

	@Override
	protected void prepareSaveModel() throws Exception {
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	public String getModel() {
		return null;
	}
}