package com.cabletech.business.sysmanager.job;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date; 
import org.quartz.JobExecutionContext; 

/**
 * @author zg 公用类 工具类
 */
public class Common implements  Serializable {
	private static final long serialVersionUID = 1L;
	private long limit_pre;// 超时前时间间隔
	private long limit_later;// 超时后 时间间隔
	private JobExecutionContext context; // job 环境对象
	private Integer limitTime;// 限制时间分钟数 

	public long getLimit_pre() {
		return limit_pre;
	}
	public void setLimit_pre(long limit_pre) {
		this.limit_pre = limit_pre;
	}

	public long getLimit_later() {
		return limit_later;
	}

	public void setLimit_later(long limit_later) {
		this.limit_later = limit_later;
	}

	public JobExecutionContext getContext() {
		return context;
	}

	public void setContext(JobExecutionContext context) {
		this.context = context;
	}

	public Integer getLimitTime() {
		return limitTime;
	}

	public void setLimitTime(Integer limitTime) {
		this.limitTime = limitTime;
	}

	/**
	 * 获取trigger 的结束时间
	 * 
	 * @param dt
	 *            分鐘 頁面指定的
	 * @param date
	 *            創建工單的時候 創建的
	 * @param a
	 *            发送类型
	 * @return date
	 */
	public static Date getDateByType(long dt, Date date, int a)
			throws ParseException {
		long d = 0;
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		if (a == 1) {
			d = c.getTimeInMillis() + dt * 60 * 1000;
		}
		if (a == 2) {
			d = c.getTimeInMillis() - dt * 60 * 1000;
		}
		Date da = new Date(d);
		return da;
	}
}
