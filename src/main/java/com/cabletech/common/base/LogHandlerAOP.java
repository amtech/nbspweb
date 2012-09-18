package com.cabletech.common.base;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.model.UserActionLog;
import com.cabletech.business.base.service.UserActionLogService;
import com.cabletech.common.servlet.SysServlet;

/**
 * 日志Aop
 * @author zhaobi
 * @date 2012-9-14 
 */
public class LogHandlerAOP {

	@Resource(name = "userActionLogService")
	protected UserActionLogService userActionLogService;

	/**
	 * 日志记录
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	public Object RecordLog(ProceedingJoinPoint pjp) throws Throwable {
		String className = pjp.getTarget().getClass().getSimpleName();
		String methodName = pjp.getSignature().getName();
		Object obj = pjp.proceed();
		HttpServletRequest request = SysServlet.getRequest();
		if (request != null) {
			UserInfo userInfo = SysServlet.getUserinfo();
			UserActionLog entity = new UserActionLog();
			entity.setClass_method(className);

			entity.setPersonid(userInfo.getId());
			entity.setLoginip(request.getRemoteAddr());
			entity.setMenuid(request.getRequestURL().toString());
			if (methodName.toLowerCase().contains("save")) {
				entity.setMethod_desc("保存操作");
				entity.setRecordid(methodName);
				if (pjp.getArgs().length > 0) {
					entity.setRecord(pjp.getArgs()[0].toString());
				}
				userActionLogService.savelog(entity);
			} else if (methodName.toLowerCase().contains("delete")) {
				entity.setMethod_desc("删除操作");
				entity.setRecordid(methodName);
				if (pjp.getArgs().length > 0) {
					entity.setRecord(pjp.getArgs()[0].toString());
				}
				userActionLogService.savelog(entity);
			} else if (methodName.toLowerCase().contains("add")) {
				entity.setMethod_desc("添加操作");
				entity.setRecordid(methodName);
				if (pjp.getArgs().length > 0) {
					entity.setRecord(pjp.getArgs()[0].toString());
				}
				userActionLogService.savelog(entity);
			} else if (methodName.toLowerCase().contains("update")) {
				entity.setMethod_desc("更新操作");
				entity.setRecordid(methodName);
				if (pjp.getArgs().length > 0 && pjp.getArgs().length <3000) {
					entity.setRecord(pjp.getArgs()[0].toString());
				}
				userActionLogService.savelog(entity);
			}

		}
		return obj;
	}
}
