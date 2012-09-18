<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/header.jsp"%>
<head xmlns="http://www.w3.org/1999/xhtml">
	<link
		href="${ctx }/js/jQuery/jqueryui/css/redmond/jquery-ui.custom.css"
		type="text/css" rel="stylesheet" />
	<script type="text/javascript"
		src="${ctx}/js/jQuery/jqueryui/jquery-ui.custom.min.js"></script>
	<script type="text/javascript">
	var planState = "${plan.planState}";
	jQuery(function() {
		//生成查看tab页
		var tab = jQuery("#tab_process").tabs({
			cache : true
		});
		//缓存tab页
		if (planState >= 4 && planState <= 7) {
			tab
					.tabs(
							'add',
							'${ctx}/workflow/wmaintainSiteAction!recordList.action?id=${plan.id}',
							'巡检问题站点', [ 0 ]);
		} else {
			tab
					.tabs(
							'add',
							'${ctx}/workflow/wmaintainSiteAction!list.action?id=${plan.id}',
							'巡检问题站点', [ 0 ]);
		}
		if (planState != '8') {
			tab
					.tabs(
							'add',
							'${ctx}/workflow/wmaintainApprovePlanAction!list.action?id=${plan.id}',
							'审核作业计划信息', [ 1 ]);
			tab
					.tabs(
							'add',
							'${ctx}/workflow/wmaintainCreateReportAction!list.action?id=${plan.id}',
							'提交作业报告信息', [ 2 ]);
			tab
					.tabs(
							'add',
							'${ctx}/workflow/wmaintainApproveReportAction!list.action?id=${plan.id}',
							'审核作业报告信息', [ 3 ]);
		}
	});
</script>
</head>
<body>
	<div class="title_bg">
		<div id="title" class="title">
			<c:if test="${showReturn==1 }">
				当前位置-查看维修计划
			</c:if>
			<c:if test="${showReturn!=1 }">
				维修计划信息
			</c:if>
		</div>
	</div>
	<div id="tabcontent" class="tabcontent">
		<table border="0" align="center">
			<tr>
				<th>
					计划名称：
				</th>
				<td colspan="3">
					${plan.planName }
				</td>
			</tr>
			<tr>
				<th>
					计划开始时间：
				</th>
				<td>
					<fmt:formatDate value="${plan.startDate }" pattern="yyyy-MM-dd" />
				</td>
				<th>
					计划结束时间：
				</th>
				<td>
					<fmt:formatDate value="${plan.endDate }" pattern="yyyy-MM-dd" />
				</td>
			</tr>
			<tr>
				<th>
					代维公司：
				</th>
				<td>
					${plan.orgName }
				</td>
				<th>
					巡检组：
				</th>
				<td>
					${plan.patrolGroupName }
				</td>
			</tr>
			<tr>
				<th>
					计划制定人员：
				</th>
				<td>
					${plan.createrName }
				</td>
				<th>
					计划制定时间：
				</th>
				<td>
					<fmt:formatDate value="${plan.createDate }"
						pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="tab_process" style="width: 99%; height: 99%">
						<ul>
						</ul>
					</div>
				</td>
			</tr>
		</table>
		<div style="text-align: center; margin-top: 10px">
			<c:if test="${showReturn==1 }">
				<input name="" value="返回" type="button" class="button"
					onclick="history.go(-1);" />
			</c:if>
		</div>
	</div>
</body>