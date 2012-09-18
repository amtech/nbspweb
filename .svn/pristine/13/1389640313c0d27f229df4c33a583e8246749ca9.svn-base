<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link href="${ctx}/css/base.css" rel="stylesheet" type="text/css" />
		<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx }/js/in-min.js"
			autoload="true"
			core="${config.cdnurl}/cabletech/ui/js/jquery-1.7.2.min.js"></script>
	</head>	
<body>
	<div class="title_bg">
		<div id="title" class="title">
			当前位置-查看拆分纠纷站点
		</div>
	</div>
	<div class="tabcontent">
		<table border="0" align="center" cellpadding="3" cellspacing="0">
			<tr>
				<th><b>站点情况：</b></th>
				<th colspan="3"></th>
			</tr>
			<tr>
				<th>专业：</th>
				<td>
					<baseinfo:dicselector name="" columntype="businesstype" type="view" keyValue="${noPatrolStation.resourceType }"></baseinfo:dicselector>
				</td>
				<th>问题类型：</th>
				<td>
					<baseinfo:dicselector name="" columntype="NOPATROLSTATION_PROBLEM_TYPE" type="view" keyValue="${noPatrolStation.problemType }"></baseinfo:dicselector>
				</td>
			</tr>
			<tr>
				<th>计划名称：</th>
				<td>${noPatrolStation.planName }</td>
				<th>站点名称：</th>
				<td>${noPatrolStation.stationName }</td>
			</tr>
			<tr>
				<th>上报时间：</th>
				<td><fmt:formatDate value="${noPatrolStation.recordDate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<th>上报人：</th>
				<td>${noPatrolStation.recorderName }</td>
			</tr>
			<tr>
				<th>问题描述：</th>
				<td colspan="3">${noPatrolStation.cause }</td>
			</tr>
			<c:if test="${not empty noPatrolStation.result }">
			<tr>
				<th><b>确认情况：</b></th>
				<th colspan="3"></th>
			</tr>
			<tr>
				<th>确认时间：</th>
				<td><fmt:formatDate value="${noPatrolStation.processTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<th>确认人：</th>
				<td>${noPatrolStation.managerName }</td>
			</tr>
			<tr>
				<th>确认结果：</th>
				<td colspan="3">
					<c:if test="${noPatrolStation.result=='0' }">通过</c:if>
					<c:if test="${noPatrolStation.result=='1' }">不通过</c:if>
				</td>
			</tr>
			<tr>
				<th>确认意见：</th>
				<td colspan="3">${noPatrolStation.remark }</td>
			</tr>
			</c:if>
		</table>
		<div style="text-align: center; margin-top: 10px">
			<input value="返回" type="button" class="button"
				onclick="history.go(-1);" />
		</div>
	</div>
</body>
</html>