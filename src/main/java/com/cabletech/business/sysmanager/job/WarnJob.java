package com.cabletech.business.sysmanager.job;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.cabletech.business.sysmanager.model.WorkorderControlInfo;
import com.cabletech.business.sysmanager.service.RemindTimeConfigureService;
import com.cabletech.business.sysmanager.service.WorkorderControlInfoService;

/**
 * 提醒任务类
 * 
 * @author zg
 * 
 */
public class WarnJob extends QuartzJobBean implements Job {
	/**
	 * 日志类对象
	 */
	public Logger logger = Logger.getLogger(this.getClass());

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private WorkorderControlInfoService workorderControlInfoService = null;
	private RemindTimeConfigureService remindTimeConfigureService = null;
	// 公用类
	private Common common = new Common();
	private WorkorderControlInfo object = null;
	private String id = "";
	private String workorderId = "";
	private String workorderType = "";
	private String handleLimit = "";
	private String workordertitle = "";
	private String handlepersonid = "";
	private String smsflag = "";
	private String professionFlag = "";
	private String smstype = "";
	private String limitNum = "";
	private List<Map<String, Object>> alllist = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> remindTimeConfigureList = new ArrayList<Map<String, Object>>();

	/**
	 * 初始化参数
	 */
	public void init() {
		ApplicationContext springContext = new ClassPathXmlApplicationContext(
				new String[] { "classpath:applicationContext.xml" });
		workorderControlInfoService = (WorkorderControlInfoService) springContext
				.getBean("workorderControlInfoService");
		remindTimeConfigureService = (RemindTimeConfigureService) springContext
				.getBean("remindTimeConfigureService");
		object = (WorkorderControlInfo) springContext
				.getBean("workorderControlInfo");
		common = (Common) springContext.getBean("common");

		logger.info("--------------初始化完毕----------------");
	}

	/**
	 * 组装bean 为下面的类使用
	 * 
	 * @param map
	 *            Map<String,Object>
	 * @return
	 */
	public WorkorderControlInfo getObject(Map<String, Object> map) {
		if (map.get("ID") != null) {
			id = map.get("ID").toString();
		}
		if (map.get("WORKORDER_ID") != null) {
			workorderId = map.get("WORKORDER_ID").toString();
		}
		if (map.get("WORKORDER_TYPE") != null) {
			workorderType = map.get("WORKORDER_TYPE").toString();
		}
		if (map.get("HANDLE_LIMIT") != null) {
			handleLimit = map.get("HANDLE_LIMIT").toString();
		}
		if (map.get("WORKORDER_TITLE") != null) {
			workordertitle = map.get("WORKORDER_TITLE").toString();
		}
		if (map.get("HANDLE_PERSONID") != null) {
			handlepersonid = map.get("HANDLE_PERSONID").toString();
		}
		if (map.get("SMS_SEND_FLG") != null) {
			smsflag = map.get("SMS_SEND_FLG").toString();
		}
		if (map.get("PROFESSION_TYPE") != null) {
			professionFlag = map.get("PROFESSION_TYPE").toString();
		}
		object.setWorkorderId(workorderId);
		object.setHandlePersonId(handlepersonid);
		object.setId(id);
		object.setWorkorderType(workorderType);
		object.setSmsSendFlg(smsflag);
		object.setProfessionType(professionFlag);
		try {
			object.setHandleLimit(sdf.parse(handleLimit));
		} catch (ParseException e) {
			logger.error("", e);
		}
		object.setWorkorderTitle(workordertitle);
		logger.info("-----------组装完毕---------------");
		return object;
	}

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		try {
			logger.info("开始-------------job----------------");
			init();
			// 获取当前所有的没有执行的控制信息列表
			alllist = workorderControlInfoService.getAllList();
			for (Map<String, Object> map : alllist) {
				object = getObject(map);

				if (StringUtils.isNotBlank(object.getWorkorderType())
						&& StringUtils.isNotBlank(object.getProfessionType())) {
					remindTimeConfigureList = remindTimeConfigureService
							.getObjectListByType(object.getWorkorderType(),
									object.getProfessionType());
					for (Map<String, Object> map2 : remindTimeConfigureList) {
						if (map2.get("SMS_TYPE") != null) {
							smstype = String.valueOf(map2.get("SMS_TYPE"));
						}
						if (map2.get("TIMEOUT_NUM") != null) {
							limitNum = String.valueOf(map2.get("TIMEOUT_NUM"));
						}
						// 开始判断 时限 和 超时时间设置的关系
						Date now = new Date();
						Integer limitTime = Integer.parseInt(limitNum);// 分钟
																		// 超时时间
						long ins_later = getMillisOfDate(now)
								- getMillisOfDate(object.getHandleLimit());// 当前时间减去时限获得毫秒数
						long ins_front = getMillisOfDate(object
								.getHandleLimit()) - getMillisOfDate(now);// 时限减去当前时间获得毫秒数
						long limit_pre = ins_front % (60 * 1000);// 获取分钟数
						long limit_later = ins_later % (60 * 1000);// 获取分钟数
						common.setContext(context);
						common.setLimit_later(limit_later);
						common.setLimit_pre(limit_pre);
						common.setLimitTime(limitTime);
						updateJob(object, smstype, common);
						logger.info("---------------------------结束job-----------------------------------------------------");
					}
				}
			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * 开始job的时间 推迟 30秒。。。
	 * 
	 * @return
	 * @throws ParseException
	 */
	public Date getDateAdd() throws ParseException {
		long d = 0;
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(new Date());
		d = c.getTimeInMillis() + 30 * 1000;
		Date da = new Date(d);
		return da;
	}

	/**
	 * 创建job
	 * 
	 * @param object
	 *            WorkorderControlInfo
	 * @param common
	 *            Common
	 * @param smstype
	 *            String
	 * @throws ParseException
	 */
	public void updateJob(WorkorderControlInfo object, String smstype,
			Common common) throws ParseException {
		Scheduler sched = null;
		JobDetail job = null;
		Trigger trigger = null;
		try {
			SchedulerFactory sf = new StdSchedulerFactory();
			sched = sf.getScheduler();
			JobDataMap triggerDataMap = common.getContext().getTrigger()
					.getJobDataMap();
			if (smstype.equals("001")) {// 超时前
				job = new JobDetail(object.getWorkorderId(),
						"WarnJob_Pre_Group", PreOvertime.class);
				trigger = new SimpleTrigger(object.getWorkorderTitle(),
						"WarnTrigger_Pre_Group", getDateAdd());
			}
			if (smstype.equals("002")) {// 超时
				job = new JobDetail(object.getWorkorderId(),
						"WarnJob_Next_Group", OnceOvertime.class);
				trigger = new SimpleTrigger(object.getWorkorderTitle(),
						"WarnTrigger_Next_Group", getDateAdd());
			}

			if (smstype.equals("003")) {// 超时后
				job = new JobDetail(object.getWorkorderId(),
						"WarnJob_After_Group", AfterOvertime.class);
				trigger = new SimpleTrigger(object.getWorkorderTitle(),
						"WarnTrigger_After_Group", getDateAdd());
			}
			triggerDataMap.put("object", object);
			triggerDataMap.put("common", common);
			job.setJobDataMap(triggerDataMap);
			sched.scheduleJob(job, trigger);
			sched.start();// 开始job
			// 修改工单job类型
			workorderControlInfoService.updateSchedulerState(object
					.getWorkorderId());
		} catch (SchedulerException e) {
			logger.error("", e);
		}
	}

	/**
	 * 获取时间毫分秒
	 * 
	 * @param date
	 *            Date
	 * @return
	 */
	public static long getMillisOfDate(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	public WorkorderControlInfoService getWorkorderControlInfoService() {
		return workorderControlInfoService;
	}

	public void setWorkorderControlInfoService(
			WorkorderControlInfoService workorderControlInfoService) {
		this.workorderControlInfoService = workorderControlInfoService;
	}

	public RemindTimeConfigureService getRemindTimeConfigureService() {
		return remindTimeConfigureService;
	}

	public void setRemindTimeConfigureService(
			RemindTimeConfigureService remindTimeConfigureService) {
		this.remindTimeConfigureService = remindTimeConfigureService;
	}

	public WorkorderControlInfo getObject() {
		return object;
	}

	public void setObject(WorkorderControlInfo object) {
		this.object = object;
	}

}
