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
		<script type="text/javascript">
	In.add('jqui',{path:'${ctx}/js/jQuery/jqueryui/css/redmond/jquery-ui.custom.css'});
	In.add('jquijs',{path:'${ctx}/js/jQuery/jqueryui/jquery-ui.custom.min.js',type:'js',charset:'utf-8',rely:['jqui']});
	In.add('common', {path : '${ctx}/common/js/common.js',type : 'js',charset : 'utf-8'});
	In.add('pickjs', {path : '${ctx}/js/jQuery/galleria/galleria-1.2.8.min.js',type : 'js',charset : 'utf-8'});
	In.ready('jquijs', 'common', 'pickjs', function() {
		setContextPath("${ctx}");
		showAccidentPhotos("${accident.id}");
	});
</script>
	</head>
	<body>
		<div class="title_bg">
			<div id="title" class="title">
				当前位置-隐患详细信息
			</div>
		</div>
		<div class="tabcontent">
			<table border="0" align="center" cellpadding="3" cellspacing="0">
				<tr>
					<th>
						隐患名称：
					</th>
					<td colspan="3">
						${accident.name }
					</td>
				</tr>
				<tr>
					<th style="background-color:#B8DDFC;">
						<font color="#058CFE"><b>隐患图片&nbsp;&nbsp;</b></font>
					</th>
					<th colspan="3" style="background-color:#B8DDFC;"></th>
				</tr>
				<tr>
					<td colspan="4">
						<div id="photosTd"></div>
					</td>
				</tr>
				<tr>
					<th style="background-color:#B8DDFC;">
						<font color="#058CFE"><b>基本信息&nbsp;&nbsp;</b></font>
					</th>
					<th colspan="3" style="background-color:#B8DDFC;">
					</th>
				</tr>
				<tr>
					<th>
						专业类型：
					</th>
					<td>
						<c:if test="${accident.businessType=='C30' }">
						${accident.resourceType }
						</c:if>
						<c:if test="${accident.businessType!='C30' }">
						站点
						</c:if>
					</td>
					<th>
						资源名称：
					</th>
					<td>
						${accident.resourceName }
					</td>
				</tr>
				<tr>
					<th>
						经度：
					</th>
					<td>
						${accident.lon }
					</td>
					<th>
						纬度：
					</th>
					<td>
						${accident.lat }
					</td>
				</tr>
				<tr>
					<th>
						专业类型：
					</th>
					<td>
						<baseinfo:dicselector name="" columntype="BUSINESSTYPE" type="view" keyValue="${accident.businessType }"></baseinfo:dicselector>
					</td>
					<th>
						上报巡检组：
					</th>
					<td>
						${accident.patrolGroupName }
					</td>
				</tr>
				<tr>
					<th>
						上报人：
					</th>
					<td>
						${accident.createrName }
					</td>
					<th>
						上报时间：
					</th>
					<td>
						<fmt:formatDate value="${accident.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
					</td>
				</tr>
				<tr>
					<th>
						隐患类型：
					</th>
					<td>
						${accident.accidentTypeName }
					</td>
					<th>
						隐患级别：
					</th>
					<td>
						${accident.accidentLevel }
					</td>
				</tr>
				<tr>
					<th>
						位置描述：
					</th>
					<td colspan="3">
						${accident.positionRemark }
					</td>
				</tr>
				<tr>
					<th>
						隐患描述：
					</th>
					<td colspan="3">
						${accident.remark }
					</td>
				</tr>
			</table>
			<div style="text-align: center; margin-top: 10px">
				<input type="button" class="button" onclick="history.go(-1);" value="返回">
			</div>
		</div>
	</body>
	<script type="text/javascript">
function showAccidentPhotos(dispatchId) {
	var actionUrl = contextPath
			+ "/localeProcessAction!showProcessPhotos.action?localeProcess.taskId="
			+ dispatchId + "&localeProcess.taskType=MM_ACCIDENT_INFO&rnd="
			+ Math.random();
	jQuery("#photosTd").load(actionUrl);
};
	</script>
</html>
