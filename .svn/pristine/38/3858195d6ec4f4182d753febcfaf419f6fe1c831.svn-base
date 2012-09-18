<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/meta.jsp"%>
<link href="${ctx}/css/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/js/in-min.js"
	autoload="true"
	core="${config.cdnurl}/cabletech/ui/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	In.add('jqui',{path:'${ctx}/js/jQuery/jqueryui/css/redmond/jquery-ui.custom.css'});
In.add('jquijs',{path:'${ctx}/js/jQuery/jqueryui/jquery-ui.custom.min.js',type:'js',charset:'utf-8',rely:['jqui']});
In.add('common',{path:'${ctx}/common/js/common.js',type:'js',charset:'utf-8'});
</script>
</head>
<body>
	<div class="title_bg">
		<div id="title" class="title">当前位置-查看计划</div>
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
			<input name="" value="返回" type="button" class="button"
				onclick="history.go(-1);" />
		</div>
	</div>
</body>
<script type="text/javascript">
	In.ready('jquijs', 'common', function() {
	//生成查看tab页
   var tab=jQuery("#tab_process").tabs({ cache: true }); //缓存tab页
   tab.tabs( 'add' , '${ctx}/wplan/wplanTemplateAction!view.action?flag=view&id=${patrolinfoMap.TEMPLATEID }' , '计划模板' , [0] );
   tab.tabs( 'add' , '${ctx}/wplan/patrolresourceAction!list.action?planid=${patrolinfoMap.id}' , '巡检站点' , [1] );
   tab.tabs( 'add' , '${ctx}/wplan/patrolinfoApproveAction!showAuditHistoryList.action?planid=${patrolinfoMap.id}' , '计划审核历史信息' , [2] );
});
</script>
</html>