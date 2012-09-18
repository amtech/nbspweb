package com.cabletech.business.sysmanager.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.Service.BaseInfoDictionaryService;
import com.cabletech.business.sysmanager.model.RemindTimeConfigure;
import com.cabletech.business.sysmanager.service.RemindTimeConfigureService;
import com.cabletech.business.sysmanager.service.TaskSmsValidityService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * @author 周刚 短信提醒功能模块
 * 
 */
@Namespace("/sysmanager")
@Results({
		@Result(name = "timeSetting", location = "/sysmanager/timeSetting_list.jsp"),
		@Result(name = "add", location = "/sysmanager/timeSetAdd.jsp"),
		@Result(name = "edit", location = "/sysmanager/timeSetEdit.jsp"),
		@Result(name = "effectiveSetting", location = "/sysmanager/effectiveSetting_list.jsp") })
@Action("/warn")
public class WarnAction extends BaseAction<String, String> {

	/**
	 * 处理工单提醒的action
	 */

	@Resource(name = "taskSmsValidityServiceImpl")
	private TaskSmsValidityService taskSmsValidityServiceImpl;

	@Resource(name = "remindTimeConfigureServiceImpl")
	private RemindTimeConfigureService remindTimeConfigureServiceImpl;
	private static final long serialVersionUID = 1L;

	private RemindTimeConfigure remindTimeConfigure = new RemindTimeConfigure();

	@Resource(name = "baseInfoDictionaryServiceImpl")
	private BaseInfoDictionaryService baseInfoDictionaryServiceImpl;

	/**
	 * 
	 * @return
	 */
	public String timeSetting() {
		return "timeSetting";
	}

	/**
	 * 短信提醒有效性设置
	 * 
	 * @return
	 */
	public String effectiveSetting() {
		return "effectiveSetting";
	}

	/**
	 * 页面获取数据
	 */
	public void esGetData() {
		Map<String, Object> parameters = initCondition();
		@SuppressWarnings("unchecked")
		Page<Map<String, Object>> page = this.initPage();
		page = taskSmsValidityServiceImpl.getQueryList(page, parameters);
		convertObjToJson(page);
	}

	/**
	 * 页面获取数据
	 */
	public void timeGetData() {
		Map<String, Object> parameters = initCondition();
		@SuppressWarnings("unchecked")
		Page<RemindTimeConfigure> page = this.initPage();
		page = remindTimeConfigureServiceImpl.getQueryList(page, parameters);
		convertObjToJson(page);
	}

	/**
	 * 开启
	 */
	public String startup() {
		String id = super.getRequest().getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			taskSmsValidityServiceImpl.startup(id);
			super.addMessage("开启成功!",
					"/sysmanager/warn!effectiveSetting.action",
					SysConstant.SUCCESS);
		}
		return "effectiveSetting";
	}

	/**
	 * 关闭
	 */
	public String shutdown() {
		String id = super.getRequest().getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			taskSmsValidityServiceImpl.shutdown(id);//
			super.addMessage("关闭成功!",
					"/sysmanager/warn!effectiveSetting.action",
					SysConstant.SUCCESS);
		}
		return "effectiveSetting";
	}

	/**
	 * 转向 编辑页面
	 * 
	 * @return
	 */
	public String edit() {
		String professionType = "";
		String taskType = "";
		String smsType = "";
		String id = super.getRequest().getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			remindTimeConfigure = remindTimeConfigureServiceImpl
					.getEntityById(id);
		}

		if (StringUtils.isNotBlank(remindTimeConfigure.getProfessionType())) {

			if (baseInfoDictionaryServiceImpl.getDicMap(
					remindTimeConfigure.getProfessionType(), "businesstype") != null) {
				professionType = (String) baseInfoDictionaryServiceImpl
						.getDicMap("businesstype",
								remindTimeConfigure.getProfessionType()).get(
								"LABLE");
			}
			remindTimeConfigure.setProfessionTypeStr(professionType);
		}

		if (StringUtils.isNotBlank(remindTimeConfigure.getSmsType())) {

			if (baseInfoDictionaryServiceImpl.getDicMap(
					remindTimeConfigure.getSmsType(), "SMS_TYPE") != null) {
				smsType = (String) baseInfoDictionaryServiceImpl.getDicMap(
						"SMS_TYPE", remindTimeConfigure.getSmsType()).get(
						"LABLE");
			}
			remindTimeConfigure.setSmsTypeStr(smsType);
		}

		if (StringUtils.isNotBlank(remindTimeConfigure.getWorkorderType())) {

			if (baseInfoDictionaryServiceImpl.getDicMap(
					remindTimeConfigure.getWorkorderType(), "TASK_TYPE") != null) {
				taskType = (String) baseInfoDictionaryServiceImpl.getDicMap(
						"TASK_TYPE", remindTimeConfigure.getWorkorderType())
						.get("LABLE");
			}
			remindTimeConfigure.setWorkorderTypeStr(taskType);
		}

		this.getRequest().setAttribute("remindTimeConfigure",
				remindTimeConfigure);
		return "edit";

	}

	/**
	 * 转向 编辑页面
	 * 
	 * @return
	 */
	public String add() {
		return "add";

	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String delete() {
		String id = super.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			remindTimeConfigureServiceImpl.deleteEntityByid(id);
		}
		super.addMessage("删除信息成功!", "/monthcost/monthcheckcost!list.action",
				SysConstant.SUCCESS);
		return "timeSetting";
	}

	/**
	 * 初始化
	 * 
	 * @return
	 */
	private Map<String, Object> initCondition() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("smsType", super.getParameter("smsType"));
		map.put("taskType", super.getParameter("taskType"));
		map.put("businessType", super.getParameter("businessType"));
		return map;
	}

	/**
	 * 保存
	 */
	public String saveTimeSetting() {
		String taskType = super.getParameter("taskType");
		String smsType = super.getParameter("smsType");
		String businessType = super.getParameter("businessType");
		String timeoutNum = super.getParameter("timeoutNum");
		String id = super.getParameter("id");
		if (id != null) {
			remindTimeConfigure.setId(id);
			if (timeoutNum != null && !"".equals(timeoutNum)) {
				remindTimeConfigure.setTimeoutNum(Integer.parseInt(timeoutNum));
			}
			remindTimeConfigureServiceImpl.saveEntity(remindTimeConfigure);
		} else {
			remindTimeConfigure.setProfessionType(businessType);
			remindTimeConfigure.setSmsType(smsType);
			if (timeoutNum != null && !"".equals(timeoutNum)) {
				remindTimeConfigure.setTimeoutNum(Integer.parseInt(timeoutNum));
			}
			remindTimeConfigure.setWorkorderType(taskType);
			int count = remindTimeConfigureServiceImpl.checkNum(taskType,
					smsType, businessType);
			if (count != 0) {
				logger.info("已经存在了..");
				return "timeSetting";
			} else {
				remindTimeConfigureServiceImpl.saveEntity(remindTimeConfigure);
			}
		}
		return "timeSetting";
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

}
