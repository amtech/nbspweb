<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="org.springframework.web.context.WebApplicationContext,com.cabletech.common.externalresources.ExternalResourcesAccessService" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://www.cabletech.com.cn/baseinfo" prefix="baseinfo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="cabletechtag" prefix="apptag"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	if(request.getAttribute("config") == null){
		WebApplicationContext wac = (WebApplicationContext) this
				.getServletConfig().getServletContext()
				.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		ExternalResourcesAccessService c = (ExternalResourcesAccessService)wac.getBean("externalResourcesAccessService");	
		request.setAttribute("config",c);
	}
%>