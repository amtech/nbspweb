package com.cabletech.business.desktop.action;

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

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.desktop.service.MyWorkService;
import com.cabletech.business.desktop.service.PatrolStatisticService;
import com.cabletech.common.base.BaseAction;

/**
 * 我的工作
 * 
 * @author zhaobi
 * 
 */
@Namespace("/desktop")
@Results({
		@Result(name = "index", location = "/frames/default/mywork.jsp"),
		@Result(name = "shortcut", location = "/frames/default/more/welcome_workbench.jsp"),
		@Result(name = "gettask", location = "/frames/default/more/welcome_task.jsp"),
		@Result(name="today",location = "/frames/default/more/welcome_tj.jsp")
		})
@Action("/mywork")
public class MyWorkAction extends BaseAction<String, String> {

	@Resource(name = "myWorkServiceImpl")
	private MyWorkService myWorkService;
	@Resource(name = "patrolStatisticServiceImpl")
	private PatrolStatisticService patrolStatisticService;
	/**
	 * 我的工作首页信息
	 * 
	 * @return
	 */
	public String index() {
		// 获取菜单ID
		String menuid = this.getParameter("menuid");
		UserInfo user = this.getUser();
		if (StringUtils.isNotBlank(menuid)) {
			List<Map<String, Object>> maplist = myWorkService.getMenuInfo(user,
					menuid);

			if (maplist != null && maplist.size() > 0) {
				this.getRequest().setAttribute("mainMenulist", maplist);
				Map<String, Object> subMap = new HashMap<String, Object>();
				for (int i = 0; i < maplist.size(); i++) {
					List<Map<String, Object>> sublist = myWorkService
							.getMenuInfo(user, maplist.get(i).get("ID")
									.toString());
					subMap.put(maplist.get(i).get("ID").toString(), sublist);
				}
				this.getRequest().setAttribute("subMenuMap", subMap);
			}
			Map<String, Object> waitHandledNumMap = myWorkService
					.getWaitHandledTasksNumber(user);
			this.getRequest().setAttribute("waitHandledNumMap",
					waitHandledNumMap);
		}
		return "index";
	}

	/**
	 * 获取快捷方式
	 * 
	 * @return
	 */
	public String getshortcut() {
		UserInfo user = this.getUser();
		List<Map<String,Object>> map=myWorkService.getShortCuts(user);
		this.getRequest().setAttribute("mainmenulist",map
				);
		return "shortcut";
	}

	/**
	 * 获取当前用户待办任务
	 * @return
	 */
	public String gettask(){
		UserInfo user = this.getUser();
		String flag=this.getParameter("flag");
		List<Map<String,Object>> workordermap=myWorkService.getWaitHandledTasksList(user,"task");
		List<Map<String,Object>> planmap=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> lettermap=new ArrayList<Map<String,Object>>(); 
		if(user.isMobile()){
			planmap=myWorkService.getWaitHandledTasksList(user,"plan");
			lettermap=myWorkService.getWaitHandledTasksList(user,"letter");
		}
		this.getRequest().setAttribute("workordercontent", workordermap);
		this.getRequest().setAttribute("plancontent", planmap);
		this.getRequest().setAttribute("flag", flag);
		this.getRequest().setAttribute("lettercontent", lettermap);
		return "gettask";
	}
	
	/**
	 * 获取当前待办工单
	 */
	public void getworkorder(){
		UserInfo user = this.getUser();
		List<Map<String,Object>> workordermap=myWorkService.getWaitHandledTasksList(user,"task");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("root", workordermap);
	    convertObjToJson(map);
	}
	/**
	 * 获取今日统计
	 * @return
	 */
	public String gettoday(){
		UserInfo user = this.getUser();
		String flag=this.getParameter("flag");
		List<Map<String,Object>> patrolStatistic=patrolStatisticService.getPatrolStatistic(user);
		List<Map<String,Object>> wtroubleStatistic=patrolStatisticService.getWtrouble(user);
		this.getRequest().setAttribute("patrolstatistic", patrolStatistic);
		this.getRequest().setAttribute("wtroublestatistic", wtroubleStatistic);
		this.getRequest().setAttribute("flag", flag);
		return "today";
	}
	
	@Override
	public String getModel() {
		// TODO Auto-generated method stub
		return null;
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
