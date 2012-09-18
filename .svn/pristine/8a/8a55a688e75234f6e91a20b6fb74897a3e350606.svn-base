package com.cabletech.business.ah.familyband.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.ah.familyband.model.AhFamilyBandRecode;
import com.cabletech.business.ah.familyband.model.AhFamilyBandTrouble;
import com.cabletech.business.ah.familyband.service.AhFamilyBandRecodeService;
import com.cabletech.business.ah.familyband.service.AhFamilyBandTroubleService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 存放每日家庭宽带巡检隐患情况 ACTION
 * 
 * @author 陆道伟 2012-06-26 创建
 * 
 */
@Namespace("/ah")
@Results({
		@Result(name = "process", location = "/ah/familyband/familyfault_process_input.jsp"),
		@Result(name = "input", location = "/ah/familyband/familytrouble_waithandled_list.jsp"),
		@Result(name = "list", location = "/ah/familyband/familytrouble_list.jsp"),
		@Result(name = "detail", location = "/ah/familyband/familytrouble_viewdetail.jsp")
})
@Action("/ahFamilyBandTroubleAction")
public class AhFamilyBandTroubleAction extends
		BaseAction<AhFamilyBandTrouble, String> {
	private static final long serialVersionUID = 1L;
	@Resource(name = "ahFamilyBandTroubleServceImpl")
	private AhFamilyBandTroubleService ahFamilyBandTroubleService;
	@Resource(name = "ahFamilyBandRecodeServceImpl")
	private AhFamilyBandRecodeService ahFamilyBandRecodeService;

	private AhFamilyBandTrouble entity = new AhFamilyBandTrouble();

	/**
	 * 跳转到家庭宽带巡检隐患情况列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		return LIST;
	}

	/***
	 * 得到隐患情况列表数据的json
	 */
	@SuppressWarnings("rawtypes")
	public void listdata() {
		Page page = super.initPage();
		Map<String, Object> map = new HashMap<String, Object>();
		processParamValue(map, "orgid");
		if(null==map.get("regionid")||"".equals(map.get("regionid"))){
			map.put("regionid", super.getUser().getRegionId());	
		}
		if(null==map.get("orgid")){
			if (super.getUser().isContractor()) {
				map.put("orgid", super.getUser().getOrgId());	
			}
		}
		processParamValue(map, "creatername");
		processParamValue(map, "starttime");
		processParamValue(map, "endtime");
		page = ahFamilyBandRecodeService.searchRecods(map, page);
		super.setExcelParameter(page);
		convertObjToJson(page);
	}

	/***
	 * 转到未处理的隐患详细页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toDealCodeTrouble()throws Exception{
		String id=super.getRequest().getParameter("id");
		AhFamilyBandRecode code=ahFamilyBandRecodeService.getAhFamilyBandRecodeById(id);
		super.getRequest().setAttribute("code", code);
		return "input";
	}
	
	/***
	 * 隐患查看隐患详细页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewTroubleDetail()throws Exception{
		String id=super.getRequest().getParameter("id");
		AhFamilyBandTrouble code= ahFamilyBandTroubleService.getAhFamilyBandTroubleByid(id);
		super.getRequest().setAttribute("entity", code);
		return "detail";
	}
	
	/**
	 * 待处理隐患列表
	 */
	public void waithanledtroublelist(){
		String id=super.getRequest().getParameter("id");
		String status=super.getRequest().getParameter("status");
		Map<String ,Object> map=new HashMap<String, Object>();
		map.put("recodeId", id);
		map.put("status", status);
		List<Map<String,Object>> troubleMap=ahFamilyBandTroubleService.searchTroublesByRecod(map);
		map.clear();
		map.put("root", troubleMap);
		convertObjToJson(map);
	}

	/***
	 * 点击处理该隐患按钮进入处理家庭宽带隐患页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toUpdateTrouble() throws Exception {
		String id = super.getRequest().getParameter("id");
		entity = ahFamilyBandTroubleService.getAhFamilyBandTroubleByid(id);
		super.getRequest().setAttribute("entity", entity);
		return "process";
	}

	/****
	 * 处理家庭宽带隐患
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		UserInfo user = super.getUser();
		Date handletime = entity.getHandleTime();
		String handleremark = entity.getHandleRemark();
		String handleresult = entity.getHandleResult();
		String id = entity.getId();
		entity = ahFamilyBandTroubleService.getAhFamilyBandTroubleByid(id);
		entity.setHandleTime(handletime);
		entity.setHandleRemark(handleremark);
		entity.setHandleResult(handleresult);
		entity.setRegisteryserId(user.getId());
		entity.setRegisterTime(new Date());
		entity.setStatus("1");
		ahFamilyBandTroubleService.saveBandTrouble(entity);
		super.addMessage("处理家庭宽带隐患记录成功!",
				"/ah/ahFamilyBandTroubleAction!toDealCodeTrouble.action?id="+entity.getRecodeId(), SysConstant.SUCCESS);
		return SysConstant.SUCCESS;
	}

	@Override
	public AhFamilyBandTrouble getModel() {
		// TODO Auto-generated method stub
		return entity;
	}


	@Override
	protected void prepareViewModel() throws Exception {

	}

	@Override
	protected void prepareSaveModel() throws Exception {

	}

	/***
	 * 把request中的数据封装到Map中
	 * 
	 * @param map 
	 * @param key 
	 */
	private void processParamValue(Map<String, Object> map, String key) {
		String value = super.getParameter(key);
		map.put(key, value);
	}
}