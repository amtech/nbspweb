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
			当前位置-查看工单：${workOrder.taskCode }
		</div>
	</div>
	<div class="tabcontent" id="detailTd">
		<table border="0" align="center" cellpadding="3" cellspacing="0">
			<tr>
				<th>
					主题：
				</th>
				<td colspan="3">
					${workOrder.taskName }
				</td>
			</tr>
			<tr>
				<th style="background-color:#B8DDFC;">
					<font color="#058CFE"><b>工单信息&nbsp;&nbsp;</b></font>
				</th>
				<th colspan="3" style="background-color:#B8DDFC">
				</th>
			</tr>
			<tr>
				<th>
					工单类型：
				</th>
				<td>
					<baseinfo:dicselector id="workOrder_taskType"
						name="workOrder.taskType" columntype="TASK_CODE" type="view"
						keyValue="${workOrder.taskType }"></baseinfo:dicselector>
				</td>
				<th>
					专业类型：
				</th>
				<td>
					<baseinfo:dicselector id="workOrder_businessType" name="workOrder.businessType" columntype="businesstype" type="view" keyValue="${workOrder.businessType }"></baseinfo:dicselector>
				</td>
			</tr>
			<tr>
				<th>
					紧急程度：
				</th>
				<td>
					<baseinfo:dicselector id="workOrder_emergencyLevel" name="workOrder.emergencyLevel" columntype="EMERGENCY_LEVEL" type="view" keyValue="${workOrder.emergencyLevel }"></baseinfo:dicselector>
				</td>
				<th>
					受理人员：
				</th>
				<td style="width: 85%">
					${workOrder.acceptUserNames }
				</td>
			</tr>
			<tr>
				<th>
					受理时限：
				</th>
				<td>
					<fmt:formatDate value="${workOrder.acceptanceLimit }" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
				<th>
					处理时限：
				</th>
				<td>
					<fmt:formatDate value="${workOrder.overtimeSet }" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
			</tr>
			<tr>
				<th>
					任务要求：
				</th>
				<td colspan="3" style="width: 85%">
					${workOrder.taskRequest }
				</td>
			</tr>
			<tr>
				<th style="background-color:#B8DDFC;">
					<font color="#058CFE"><b>附件列表&nbsp;&nbsp;</b></font>
				</th>
				<th colspan="3" style="background-color:#B8DDFC">
				</th>
			</tr>
			<tr>
				<th>
					附件：
				</th>
				<td colspan="3" style="width: 85%">
					<apptag:upload state="look" entityId="${workOrder.id }"
						entityType="WTASK_ORDER"></apptag:upload>
				</td>
			</tr>
		</table>
		<div style="text-align: center; margin-top: 10px">
			<input value="返回" type="button" class="button"
				onclick="history.go(-1);" />
		</div>
	</div>
</body>
</html>