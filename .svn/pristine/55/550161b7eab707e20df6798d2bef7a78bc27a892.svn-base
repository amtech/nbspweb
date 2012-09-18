package com.cabletech.business.ah.familyband.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.business.ah.familyband.model.AhFamilyBandRecode;
import com.cabletech.business.ah.familyband.service.AhFamilyBandRecodeService;
import com.cabletech.business.ah.familyband.service.AhFamilyBandTroubleService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 存放每日家庭宽带的巡检记录 ACTION
 * 
 * @author 陆道伟 2012-06-26 创建
 * 
 */
@Namespace("/ah")
@Results({
		@Result(name = "list", location = "/ah/familyband/familypatrolinfo_list.jsp"), 
		@Result(name = "input", location = "/ah/familyband/familytrouble_input.jsp"),
		@Result(name = "tosee", location = "/ah/familyband/familytrouble_view.jsp")
})
@Action("/ahFamilyBandRecodeAction")
public class AhFamilyBandRecodeAction extends
		BaseAction<AhFamilyBandRecode, String> {
	private static final long serialVersionUID = 1L;
	/***
	 * 宽带巡检记录事务类
	 */
	@Resource(name = "ahFamilyBandRecodeServceImpl")
	private AhFamilyBandRecodeService ahFamilyBandRecodeService;
	/***
	 * 宽带隐患事务类
	 */
	@Resource(name = "ahFamilyBandTroubleServceImpl")
	private AhFamilyBandTroubleService ahFamilyBandTroubleService;
	/***
	 * 存放每日家庭宽带的巡检记录实体
	 */
	private AhFamilyBandRecode entity = new AhFamilyBandRecode();

	@Override
	public String input(){
		super.getRequest().setAttribute("rowNub",0);//用于动态构建表格
		return INPUT;
	}
	
	/**
	 * 转到家庭宽带巡检列表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		if(super.getUser().isProvinceMobile()){
		super.getRequest().setAttribute("show_region", "show");
		}
		return LIST;
	}

	/**
	 * 跳转到存放每日家庭宽带的巡检记录列表数据
	 */
	@SuppressWarnings("rawtypes")
	public void listdata() {
		Page page = super.initPage();
		Map<String, Object> map = new HashMap<String, Object>();
		processParamValue(map, "regionid");
		processParamValue(map, "orgid");
		processParamValue(map, "creatername");
		processParamValue(map, "starttime");
		processParamValue(map, "endtime");
		if(null==map.get("regionid")||"".equals(map.get("regionid"))){
			map.put("regionid", super.getUser().getRegionId());	
		}
		if(null==map.get("orgid")){
			if (super.getUser().isContractor()) {
				map.put("orgid", super.getUser().getOrgId());	
			}
		}
		page = ahFamilyBandRecodeService.searchRecods(map, page);
		convertObjToJson(page);
	}

	/***
	 * 跳转宽带巡检记录修改页面
	 * @return
	 * @throws Exception
	 */
	public String toUpdate()throws Exception{
		String id=super.getRequest().getParameter("id");
		Map<String,Object>paramRecode=new HashMap<String, Object>();
		paramRecode.put("id", id);
		Map<String,Object> familyRecode=ahFamilyBandRecodeService.getAhFamilyBandRecodeById(paramRecode);
		super.getRequest().setAttribute("familyRecode", familyRecode);
		Map<String,Object>paramTrouble=new HashMap<String, Object>();
		paramTrouble.put("recodeId", id);
		List<Map<String,Object>> troubleList=ahFamilyBandTroubleService.searchTroublesByRecod(paramTrouble);
		super.getRequest().setAttribute("troubleList", troubleList);
		super.getRequest().setAttribute("rowNub", troubleList.size());//用于动态构建表格
		return "input";
	}
	
	/**
	 * 添加修改家庭宽带巡检记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		boolean isupdate= true;
		if (StringUtils.isBlank(entity.getId())) {
			entity.setId(null);
			isupdate=false;
		}
		ahFamilyBandRecodeService.saveAhFamilyBandRecode(entity,super.getUser());
		if(isupdate){
			super.addMessage("修改宽带巡检记录成功!",
					"/ah/familyband/familypatrolinfo_list.jsp", SysConstant.SUCCESS);
		}else{
			super.addMessage("添加宽带巡检记录成功!",
					"/ah/familyband/familytrouble_input.jsp", SysConstant.SUCCESS);
		}
		return SysConstant.SUCCESS;
	}

	/**
	 * 跳转到家庭宽带巡检记录详细页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toSee()throws Exception{
		String id=super.getRequest().getParameter("id");
		Map<String,Object>perameter=new HashMap<String, Object>();
		perameter.put("id", id);
		Map<String,Object> familyRecode=ahFamilyBandRecodeService.getAhFamilyBandRecodeById(perameter);
		super.getRequest().setAttribute("familyRecode", familyRecode);
		perameter.clear();
		perameter.put("recodeId", id);
		this.getRequest().setAttribute("recodeId", id);
		List<Map<String,Object>> troubleList=ahFamilyBandTroubleService.searchTroublesByRecod(perameter);
		super.getRequest().setAttribute("troubleList", troubleList);
		return "tosee";
	}
	
	/**
	 * 获取家庭宽带记录详细
	 * @return
	 * @throws Exception
	 */
	/**
	 * 查看隐患记录详细
	 * @return
	 * @throws Exception
	 */
	public String toAhFamilyBandRecodeDetails() throws Exception {
		String id = super.getRequest().getParameter("id");
		entity = ahFamilyBandRecodeService.getAhFamilyBandRecodeById(id);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("recodeId", id);
		List<Map<String, Object>> troubleMap = ahFamilyBandTroubleService
				.searchTroublesByRecod(parameters);
		super.getRequest().setAttribute("entity", entity);
		super.getRequest().setAttribute("troubleMap", troubleMap);
		return "details";
	}

	@Override
	public AhFamilyBandRecode getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	@Override
	protected void prepareViewModel() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	protected void prepareSaveModel() throws Exception {
		if (entity == null) {
			entity = new AhFamilyBandRecode();
		}
	}

	/***
	 * 把request中的数据封装到Map中
	 * 
	 * @param map 
	 * @param key 
	 */
	private void processParamValue(Map<String, Object> map, String key) {
		String value = super.getRequest().getParameter(key);
		map.put(key, value);
	}
}