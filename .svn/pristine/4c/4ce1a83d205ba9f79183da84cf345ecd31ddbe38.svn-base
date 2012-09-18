/**
 * 
 */
package com.cabletech.business.sysmanager.job;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.cabletech.baseinfo.jms.JmsSmMessageSender;
import com.cabletech.baseinfo.jms.JmsSmParameter;
import com.cabletech.business.sysmanager.model.WorkorderControlInfo;
import com.cabletech.business.sysmanager.service.WorkorderControlInfoService;

/**
 * @author 周刚 2012 -8-16 超时处理类
 */
public class OnceOvertime implements Serializable, Job {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 日志类对象
	 */
	public Logger logger = Logger.getLogger(this.getClass());

	@Resource(name = "smMessageSender")
	private JmsSmMessageSender smSender;

	/**
	 * 构造方法
	 */
	public OnceOvertime() {
	}

	@SuppressWarnings("null")
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		SchedulerFactory scheFact = new StdSchedulerFactory();
		Scheduler sche = null;
		boolean tag = false;
		try {
			sche = scheFact.getScheduler();
			JobDataMap jobDataMap = context.getTrigger().getJobDataMap();
			WorkorderControlInfo object = (WorkorderControlInfo) jobDataMap
					.get("object");
			Common common = (Common) jobDataMap.get("common");
			WorkorderControlInfoService workorderControlInfoService = (WorkorderControlInfoService) jobDataMap
					.get("workorderControlInfoService");
			long limitTime = common.getLimitTime();
			if (object != null) {
				return;
			}
			Date now = new Date();
			if (object.getHandleLimit() != null) {
				if (now.equals(object.getHandleLimit())) {
					this.sendMesge(object, 2, limitTime,
							workorderControlInfoService);
				}
				tag = sche.deleteJob(context.getJobDetail().getName(),
						"WarnJob_Next_Group");
				if (tag) {
					logger.info("delete job sucess");
				} else {
					logger.info("delete job failed");
				}
			}
		} catch (SchedulerException e) {

		}
	}

	/**
	 * 发送短信
	 * 
	 * @param info
	 *            控制对象实体
	 * @param flag
	 *            标示
	 * @param limitTime
	 *            时限时间
	 * @param workorderControlInfoService
	 *            业务类对象
	 */
	public void sendMesge(WorkorderControlInfo info, int flag, long limitTime,
			WorkorderControlInfoService workorderControlInfoService) {
		StringBuffer sb = new StringBuffer();
		sb.append("您").append(info.getWorkorderTitle()).append("，在超时时限为");
		sb.append(info.getHandleLimit()).append("内");
		if (flag == 1) // 超時前提醒
			sb.append("，注意：还有" + limitTime + "分鐘超時");
		if (flag == 2)
			sb.append("已经超时了");
		if (flag == 3)
			sb.append("已经超时" + limitTime + "分鐘");

		String phoneNumber = "";
		StringBuffer contents = new StringBuffer();
		if (info != null) {
			phoneNumber = workorderControlInfoService.getPhoneBYWorkId(info
					.getHandlePersonId());
		}
		contents.append(sb);
		JmsSmParameter parameter1 = JmsSmParameter
				.getInstanceForNotNeedResponse(phoneNumber, contents.toString());
		smSender.sendMessage(parameter1);

	}

}
