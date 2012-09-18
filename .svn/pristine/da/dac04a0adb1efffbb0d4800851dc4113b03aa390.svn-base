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
		<script language="javascript" type="text/javascript">
In.add('jqui',{path:'${ctx}/js/jQuery/jqueryui/css/redmond/jquery-ui.custom.css'});
In.add('jquijs',{path:'${ctx}/js/jQuery/jqueryui/jquery-ui.custom.min.js',type:'js',charset:'utf-8',rely:['jqui']});
In.add('common', {path : '${ctx}/common/js/common.js',type : 'js',charset : 'utf-8'});
In.add('wdatejs', {path : '${ctx}/js/WdatePicker/WdatePicker.js',type : 'js',charset : 'utf-8'});
In.add('workorder_common', {path : '${ctx}/workflow/workorder/js/workorder_common.js',type : 'js',charset : 'utf-8'});
	In.ready('common', 'wdatejs','jquijs','workorder_common', function() {
		setContextPath('${ctx}');
		var initialized = [ false, false ]; //是否已初始化
		var tab = jQuery("#tab_process").tabs({
			cache : true,
			selected : 0
		});
		tab.bind('tabsshow', function(event, ui) {
			//选择二个tab时，生成未巡检rfid
			if (ui.index == 1 && !initialized[ui.index]) {
				showWorkOrderDispatch('${workOrder.id}');
				showSignForHistory('${pid }');
				showRefuseConfirmHistory('${pid }');
				showReplyCheckHistory('${pid }');
			}
			initialized[ui.index] = true;
		});
		showProcessHistory('${pid}');
		showProcessPhotos('${pid}');
		showReplyHistory('${pid }');
		showEveryStepTimeLength('${workOrder.id}', '${pid}');
		jQuery('#tab_process').tabs();
	})
</script>

	</head>
	<body>
		<div class="title_bg">
			<div id="title" class="title">
				当前位置-查看工单-<baseinfo:dicselector id="workOrder_taskType" name="workOrder.taskType" columntype="TASK_CODE" type="view" keyValue="${workOrder.taskType }"></baseinfo:dicselector>：${workOrder.taskCode }
			</div>
		</div>
		<div class="tabcontent" id="detailTd">
			<table border="0" align="center" cellpadding="3" cellspacing="0">
				<tr>
					<th style="width:20%">
						主题：
					</th>
					<td colspan="3" style="width:80%">
						${workOrder.taskName }
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div id="tab_process" style="width: 99%; height: 99%">
							<ul>
								<li>
									<a href="#workOrderTd">工单信息</a>
								</li>
								<li>
									<a href="#flowInfoTd">流程信息</a>
								</li>
							</ul>
							<div id="workOrderTd">
								<table border="0" align="center" cellpadding="3" cellspacing="0">
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
											<baseinfo:dicselector id="workOrder_taskType" name="workOrder.taskType" columntype="TASK_CODE" type="view" keyValue="${workOrder.taskType }"></baseinfo:dicselector>
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
										<td>
											${workOrder.acceptUserNames }
										</td>
									</tr>
									<tr>
										<th>
											受理时限：
										</th>
										<td>
											<fmt:formatDate value="${workOrder.acceptanceLimit }" pattern="yyyy-MM-dd HH:mm:ss" var="acceptanceLimit" />${acceptanceLimit }
										</td>
										<th>
											处理时限：
										</th>
										<td>
											<fmt:formatDate value="${workOrder.overtimeSet }" pattern="yyyy-MM-dd HH:mm:ss" var="overtimeSet" />${overtimeSet }
										</td>
									</tr>
									<tr>
										<th>
											任务要求：
										</th>
										<td colspan="3">
											${workOrder.taskRequest }
										</td>
									</tr>
									<tr>
										<th style="background-color:#B8DDFC;">
											<font color="#058CFE"><b>附件列表&nbsp;&nbsp;</b></font>
										</th>
										<th colspan="3" style="background-color:#B8DDFC;">
										</th>
									</tr>
									<tr>
										<th>
											附件：
										</th>
										<td colspan="3">
											<apptag:upload state="look" entityId="${workOrder.id }" entityType="WTASK_ORDER"></apptag:upload>
										</td>
									</tr>
									<c:if test="${empty type }">
									<tr>
										<th style="background-color:#B8DDFC;">
											<font color="#058CFE"><b>现场处理过程&nbsp;&nbsp;</b></font>
										</th>
										<th colspan="3" style="background-color:#B8DDFC;">
										</th>
									</tr>
									<tr>
										<td id="processHistoryTd" colspan="4"></td>
									</tr>
									<tr>
										<th style="background-color:#B8DDFC;">
											<font color="#058CFE"><b>现场图片&nbsp;&nbsp;</b></font>
										</th>
										<th colspan="3" style="background-color:#B8DDFC;">
										</th>
									</tr>
									<tr>
										<td id="processPhotosTd" colspan="4"></td>
									</tr>
									<tr>
										<th style="background-color:#B8DDFC;">
											<font color="#058CFE"><b>回单信息&nbsp;&nbsp;</b></font>
										</th>
										<th colspan="3" style="background-color:#B8DDFC;">
										</th>
									</tr>
									<tr>
										<td id="replyHistoryTd" colspan="4"></td>
									</tr>
									<tr>
										<th style="background-color:#B8DDFC;">
											<font color="#058CFE"><b>回单历时&nbsp;&nbsp;</b></font>
										</th>
										<th colspan="3" style="background-color:#B8DDFC;">
										</th>
									</tr>
									<tr>
										<td id="everyStepTimeLengthTd" colspan="4"></td>
									</tr>
									</c:if>
								</table>
							</div>
							<div id="flowInfoTd">
								<table border="0" align="center" cellpadding="3" cellspacing="0">
									<tr>
										<th style="background-color:#B8DDFC;width:20%;">
											<font color="#058CFE"><b>派单信息&nbsp;&nbsp;</b></font>
										</th>
										<th colspan="3" style="background-color:#B8DDFC;width:80%;">
										</th>
									</tr>
									<tr>
										<td id="workOrderDispatchTd" colspan="4"></td>
									</tr>
									<tr>
										<th style="background-color:#B8DDFC;;width:20%;">
											<font color="#058CFE"><b>签收信息&nbsp;&nbsp;</b></font>
										</th>
										<th colspan="3" style="background-color:#B8DDFC;width:80%;">
										</th>
									</tr>
									<tr>
										<td id="signForHistoryTd" colspan="4"></td>
									</tr>
									<tr>
										<th style="background-color:#B8DDFC;;width:20%;">
											<font color="#058CFE"><b>拒签确认信息&nbsp;&nbsp;</b></font>
										</th>
										<th colspan="3" style="background-color:#B8DDFC;width:80%;">
										</th>
									</tr>
									<tr>
										<td id="refuseConfirmHistoryTd" colspan="4"></td>
									</tr>
									<c:if test="${empty type }">
									<tr>
										<th style="background-color:#B8DDFC;;width:20%;">
											<font color="#058CFE"><b>回单验证信息&nbsp;&nbsp;</b></font>
										</th>
										<th colspan="3" style="background-color:#B8DDFC;width:80%;">
										</th>
									</tr>
									<tr>
										<td id="replyCheckHistoryTd" colspan="4"></td>
									</tr>
									</c:if>
								</table>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div style="text-align: center; margin-top: 10px">
			<input value="返回" type="button" class="button" onclick="history.go(-1);" />
		</div>
	</body>
</html>