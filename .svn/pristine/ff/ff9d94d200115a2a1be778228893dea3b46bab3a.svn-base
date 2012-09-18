<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
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
In.add('common',{path:'${ctx}/common/js/common.js',type:'js',charset:'utf-8'});
In.add('oe_dispatchtask_common',{path:'${ctx}/workflow/electricity/security/js/oe_dispatch_task_common.js',type:'js',charset:'utf-8'});
	In.ready('jquijs','common','oe_dispatchtask_common',function(){
		setContextPath("${ctx}");
		var initialized = [ false, false ]; //是否已初始化
		var tab = jQuery("#tab_process").tabs({
			cache : true,
			selected : 0
		});
		tab.bind('tabsshow', function(event, ui) {
			//选择二个tab时，生成未巡检rfid
			if (ui.index == 1 && !initialized[ui.index]) {
				if("${oeDispatchTask.state}"!="6"&&"${oeDispatchTask.state}">"2"){
					showReplyHistory("${oeDispatchTask.id}");
					showAuditHistory("${oeDispatchTask.id}");
				}
			}
			initialized[ui.index] = true;
		});
		if("${oeDispatchTask.state}">"2"){
			showOilGenEleHistory("${oeDispatchTask.id}");
			showScheduleHistroy("${oeDispatchTask.id}");
		}
		jQuery('#tab_process').tabs();
	});
</script>
	</head>
	<body>
		<div class="title_bg">
			<div id="title" class="title">
				<c:if test="${showReturn=='1' }">
					当前位置-供电保障-查看发电派单：${oeDispatchTask.taskCode }
				</c:if>
				<c:if test="${showReturn!='1' }">
					派单信息
				</c:if>
			</div>
		</div>
		<div class="tabcontent" id="detailTd">
			<table border="0" align="center" cellpadding="3" cellspacing="0">
				<tr>
					<th style="width:20%">
						主题：
					</th>
					<td colspan="3" style="width:80%">
						${oeDispatchTask.title }
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div id="tab_process" style="width: 99%; height: 99%">
							<ul>
								<li>
									<a href="#detailBaseTd">工单信息</a>
								</li>
								<li>
									<a href="#flowInfoTd">流程信息</a>
								</li>
							</ul>
							<div id="detailBaseTd">
								<table border="0" align="center" cellpadding="3" cellspacing="0">
									<tr>
										<th style="background-color:#B8DDFC;">
											<font color="#058CFE"><b>基本信息&nbsp;&nbsp;</b></font>
										</th>
										<th colspan="3" style="background-color:#B8DDFC;"></th>
									</tr>
									<tr>
										<th>
											站点类型：
										</th>
										<td>
											<c:if test="${oeDispatchTask.stationType=='A24' }">
												基站
											</c:if>
										</td>
										<th>
											断电站点：
										</th>
										<td>
											${oeDispatchTask.stationName }
										</td>
									</tr>
									<tr>
										<th>
											站点地址：
										</th>
										<td colspan="3">
											${oeDispatchTask.stationAddress }
										</td>
									</tr>
									<tr>
										<th>
											断电时间：
										</th>
										<td>
											<fmt:formatDate value="${oeDispatchTask.blackoutTime }" pattern="yyyy-MM-dd HH:mm:ss" />
										</td>
										<th>
											维护单位：
										</th>
										<td>
											${oeDispatchTask.orgName }
										</td>
									</tr>
									<tr>
										<th>
											受理时限：
										</th>
										<td>
											<fmt:formatDate value="${oeDispatchTask.acceptLimit }" pattern="yyyy-MM-dd HH:mm:ss" />
										</td>
										<th>
											处理时限：
										</th>
										<td>
											<fmt:formatDate value="${oeDispatchTask.handleLimit }" pattern="yyyy-MM-dd HH:mm:ss" />
										</td>
									</tr>
									<tr>
										<th>
											断电原因：
										</th>
										<td>
											${oeDispatchTask.blackoutReason }
										</td>
										<th>
											<c:if test="${!empty oeDispatchTask.offStation }">
												是否断站：
											</c:if>
										</th>
										<td>
											<c:if test="${oeDispatchTask.offStation=='1' }">
												是
											</c:if>
											<c:if test="${oeDispatchTask.offStation=='0' }">
												否
											</c:if>
										</td>
									</tr>
									<tr>
										<th>
											服务区域：
										</th>
										<td colspan="3">
											${oeDispatchTask.serviceRegion }
										</td>
									</tr>
									<c:if test="${oeDispatchTask.state!='6' }">
									<tr>
										<th style="background-color:#B8DDFC;">
											<font color="#058CFE"><b>调度信息&nbsp;&nbsp;</b></font>
										</th>
										<th colspan="3" style="background-color:#B8DDFC;"></th>
									</tr>
									<tr>
										<td id="scheduleHistoryTd" colspan="4"></td>
									</tr>
									</c:if>
									<c:if test="${oeDispatchTask.state>'2' }">
									<tr>
										<th style="background-color:#B8DDFC;">
											<a href="javascript:showInfo();"><font color="#058CFE"><b>油机发电记录</b></font></a><font color="#058CFE"><b></b></font>
										</th>
										<th colspan="3" style="background-color:#B8DDFC;text-align:left;"><a href="javascript:showGis();"><font color="#058CFE"><b>油机轨迹图</b></font></a>&nbsp;&nbsp;</th>
									</tr>
									<tr>
										<td id="oilGenElecRecordTd" colspan="4"></td>
									</tr>
									</c:if>
								</table>
							</div>
							<div id="flowInfoTd">
								<c:if test="${oeDispatchTask.state!='6'&&oeDispatchTask.state>'2' }">
								<table border="0" align="center" cellpadding="3" cellspacing="0">
									<tr>
										<th style="background-color:#B8DDFC;width:20%;">
											<font color="#058CFE"><b>派单回复信息&nbsp;&nbsp;</b></font>
										</th>
										<th colspan="3" style="background-color:#B8DDFC;width:80%;"></th>
									</tr>
									<tr>
										<td id="replyHistoryTd" colspan="4"></td>
									</tr>
									<tr>
										<th style="background-color:#B8DDFC;width:20%;">
											<font color="#058CFE"><b>派单回复审核信息&nbsp;&nbsp;</b></font>
										</th>
										<th colspan="3" style="background-color:#B8DDFC;width:80%;"></th>
									</tr>
									<tr>
										<td id="auditHistoryTd" colspan="4"></td>
									</tr>
								</table>
								</c:if>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div style="text-align: center; margin-top: 10px">
			<c:if test="${showReturn=='1' }">
				<input type="button" class="button" onclick="history.go(-1);" value="返回" />
			</c:if>
		</div>
	</body>
</html>