<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript"
	src="${ctx}/wplan/plan/js/patrolinfo_common.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jvalidation/jquery.validate.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jvalidation/jquery.validate.cn.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jvalidation/messages_cn.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jvalidation/additional-methods.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jvalidation/jquery.validate.ex.js"></script>
<script type="text/javascript">
	jQuery(function() {
		setContextPath("${ctx}");
		var tab=jQuery('#tab_process').tabs({ cache: true });
		jQuery("#patrolApproveForm").validate();
		 tab.tabs( 'add' , '${ctx}/wplan/wplanTemplateAction!view.action?flag=view&id=${patrolinfoMap.TEMPLATEID }' , '计划模板' , [0] );
	     tab.tabs( 'add' , '${ctx}/wplan/patrolresourceAction!list.action?planid=${patrolinfoMap.ID}' , '巡检资源' , [1] );
	     tab.tabs( 'add' , '${ctx}/wplan/patrolinfoApproveAction!showAuditHistoryList.action?planid=${patrolinfoMap.ID}' , '计划审核历史信息' , [2] );
	});
</script>
</head>
<body>
	<form
		action="${ctx }/wplan/patrolinfoApproveAction!audit.action?type=${patrolinfoMap.BUSINESS_TYPE}"
		id="patrolApproveForm" name="patrolApproveForm" method="post">
		<div class="title_bg">
			<div id="title" class="title">当前位置-计划审批</div>
		</div>
		<div class="tabcontent">
			<table border="0" align="center">
				<tr>
					<th>计划名称：</th>
					<td>${patrolinfoMap.PLAN_NAME }</td>

					<th>计划类型：</th>
					<td>${plantypeMap[patrolinfoMap.PLAN_TYPE] }</td>
				</tr>
				<tr>
					<th>计划开始时间：</th>
					<td><fmt:formatDate value="${patrolinfoMap.START_TIME }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<th>计划结束时间：</th>
					<td><fmt:formatDate value="${patrolinfoMap.END_TIME }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				<tr>
					<th>代维公司：</th>
					<td>${patrolinfoMap.ORGNAME }</td>
					<th>巡检组：</th>
					<td>${patrolinfoMap.PATROLGROUPNAME }</td>
				</tr>
				<tr>
					<th>计划制定人员：</th>
					<td>${patrolinfoMap.CREATERNAME }</td>
					<th>计划制定时间：</th>
					<td><fmt:formatDate value="${patrolinfoMap.CREATETIME }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				<tr>
					<td colspan="4">
						<div id="tab_process" style="width: 99%; height: 99%">
							<ul>
							</ul>
						</div>
					</td>
				</tr>
				<tr>
					<th><b>计划审核信息</b>
					</th>
					<th colspan="3"></th>
				</tr>
				<tr>
					<th>审核结果：</th>
					<td colspan="3"><input type="radio" name="result" value="pass"
						checked /> 通过 <input type="radio" name="result" value="reject" />
						不通过</td>
				</tr>
				<tr>
					<th>审核备注：</th>
					<td colspan="3"><textarea name="remark"
							class="inputtext required" style="height: 80px;width:85%;"></textarea> <span
						style="color: red;">*</span></td>
				</tr>
			</table>
			<div style="text-align: center; margin-top: 10px">
				<input name="id" value="${patrolinfoMap.TASKID }" type="hidden" />
				<input name="planid" value="${patrolinfoMap.ID }" type="hidden" />
				<input type="submit" class="button" value="审核 "> <input
					type="button" class="button" onclick="history.back()" value="返回">
			</div>
		</div>
	</form>
</body>
</html>

