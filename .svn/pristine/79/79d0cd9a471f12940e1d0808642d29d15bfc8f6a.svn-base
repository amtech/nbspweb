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
	In.add('fault-common', {path : '${ctx}/workflow/fault/js/fault_common.js',type : 'js',charset : 'utf-8',rely : [ 'pickjs' ]});
	In.ready('jquijs', 'common', 'pickjs', 'fault-common', function() {
		setContextPath("${ctx}");
		if ('${fault_alert.findType}' == 'B16') {
			showFaultPhotos('${fault_alert.id }');
		}
		if ('${fault_alert.findType}' == 'B17') {
			showEoms('${fault_alert.eomsId}');
		}
	});
</script>
	</head>
	<body>
		<div class="title_bg">
			<div id="title" class="title">
				当前位置-故障告警单详细信息
			</div>
		</div>
		<div class="tabcontent">
			<table border="0" align="center" cellpadding="3" cellspacing="0">
				<tr>
					<th>
						故障标题：
					</th>
					<td colspan="3">
						${fault_alert.troubleTitle }
					</td>
				</tr>
				<c:if test="${faultAlert.findType=='B16' }">
					<tr>
						<th style="background-color:#B8DDFC;">
							<font color="#058CFE"><b>故障图片&nbsp;&nbsp;</b></font>
						</th>
						<th colspan="3" style="background-color:#B8DDFC;"></th>
					</tr>
					<tr>
						<td colspan="4">
							<div id="photosTd"></div>
						</td>
					</tr>
				</c:if>
				<c:if test="${fault_alert.findType=='B17' }">
					<tr>
						<th style="background-color:#B8DDFC;">
							<font color="#058CFE"><b>EOMS工单信息&nbsp;&nbsp;</b></font>
						</th>
						<th colspan="3" style="background-color:#B8DDFC;"></th>
					</tr>
					<tr>
						<td colspan="4" id="eomsTd"></td>
					</tr>
				</c:if>
				<tr>
					<th style="background-color:#B8DDFC;">
						<font color="#058CFE"><b>基本信息&nbsp;&nbsp;</b></font>
					</th>
					<th colspan="3" style="background-color:#B8DDFC;">
					</th>
				</tr>
				<tr>
					<th>
						发现方式：
					</th>
					<td>
						<baseinfo:dicselector columntype="FIND_TYPE" type="view" keyValue="${fault_alert.findType }" id="" name=""></baseinfo:dicselector>
					</td>
					<th>
						EMOS单号：
					</th>
					<td>
						${fault_alert.eomsId }
					</td>
				</tr>
				<tr>
					<th>
						故障发生时间：
					</th>
					<td>
						<fmt:formatDate value="${fault_alert.troubleTime }" pattern="yyyy-MM-dd HH:mm:ss" var="troubleTime" />
						${troubleTime }
					</td>
					<th>
						是否紧急故障：
					</th>
					<td>
						<c:if test="${fault_alert.isInstancy==1 }">是</c:if>
						<c:if test="${fault_alert.isInstancy==2 }">否</c:if>
					</td>
				</tr>
				<tr>
					<th>
						报告时间：
					</th>
					<td>
						<fmt:formatDate value="${fault_alert.reportTime }" pattern="yyyy-MM-dd HH:mm:ss" var="reportTime" />
						${reportTime }
					</td>
					<th>
						报告人：
					</th>
					<td>
						${fault_alert.reporter }
					</td>
				</tr>
				<tr>
					<th>
						故障级别：
					</th>
					<td>
						<baseinfo:dicselector columntype="TROUBLE_LEVEL" type="view" keyValue="${fault_alert.troubleLevel }" id="" name=""></baseinfo:dicselector>
					</td>
					<th>
					</th>
					<td>
					</td>
				</tr>
				<tr>
					<th>
						故障描述：
					</th>
					<td colspan="3">
						${fault_alert.troubleDesc }
					</td>
				</tr>
				<tr>
					<th>
						故障站点：
					</th>
					<td>
						${fault_alert.stationName }
					</td>
					<th>
						故障地点：
					</th>
					<td>
						${fault_alert.address }
					</td>
				</tr>
			</table>
			<div style="text-align: center; margin-top: 10px">
				<input type="button" class="button" onclick="history.go(-1);" value="返回">
			</div>
		</div>
	</body>
</html>
