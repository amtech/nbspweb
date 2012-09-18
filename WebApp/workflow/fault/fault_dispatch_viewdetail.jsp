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
		var initialized = [ false, false ]; //是否已初始化
		var tab = jQuery("#tab_process").tabs({
			cache : true,
			selected : 0
		});
		tab.bind('tabsshow', function(event, ui) {
			//选择二个tab时，生成未巡检rfid
			if (ui.index == 1 && !initialized[ui.index]) {
				showAuditHistory('${fault_dispatch.id }');
			}
			initialized[ui.index] = true;
		});
		showFaultPhotos('${fault_alert.id }');
		showProcessHistory('${fault_dispatch.id }');
		showProcessPhotos('${fault_dispatch.id }');
		if ('${fault_alert.findType}' == 'B17') {
			showEoms('${fault_alert.eomsId}');
		}
		jQuery('#tab_process').tabs();
	})
</script>
	</head>
	<body>
		<div class="title_bg">
			<div id="title" class="title">
				当前位置-故障派单详细信息：${fault_dispatch.taskCode }
			</div>
		</div>
		<div class="tabcontent" id="detailTd">
			<table border="0" align="center" cellpadding="3" cellspacing="0">
				<tr>
					<th style="width:20%">
						主题：
					</th>
					<td colspan="3" style="width:80%">
						${fault_alert.troubleTitle }
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div id="tab_process" style="width: 99%; height: 99%">
							<ul>
								<li>
									<a href="#detailBaseTd">故障工单信息</a>
								</li>
								<li>
									<a href="#flowInfoTd">流程信息</a>
								</li>
							</ul>
							<div id="detailBaseTd">
								<table border="0" align="center" cellpadding="3" cellspacing="0">
									<c:if test="${fault_alert.findType=='B16' }">
									<tr>
										<th style="background-color:#B8DDFC;">
											<font color="#058CFE"><b>故障图片&nbsp;&nbsp;</b></font>
										</th>
										<th colspan="3" style="background-color:#B8DDFC;">
										</th>
									</tr>
									<tr>
										<td colspan="4" id="photosTd"></td>
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
											响应期限：
										</th>
										<td>
											<fmt:formatDate value="${fault_dispatch.response }" pattern="yyyy-MM-dd HH:mm:ss" var="responseDate" />
											${responseDate }
										</td>
										<th>
											处理期限：
										</th>
										<td>
											<fmt:formatDate value="${fault_dispatch.deadline }" pattern="yyyy-MM-dd HH:mm:ss" var="deadlineDate" />
											${deadlineDate }
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
											专业类型
										</th>
										<td>
											<baseinfo:dicselector columntype="businesstype" type="view" keyValue="${fault_alert.businessType }" id="" name=""></baseinfo:dicselector>
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
									<tr>
										<th>
											代维公司：
										</th>
										<td>
											${fault_dispatch.maintenanceName }
										</td>
										<th>
											维护组：
										</th>
										<td>
											${fault_dispatch.patrolGroupName }
										</td>
									</tr>
									<tr>
										<th>
											派单人：
										</th>
										<td>
											${fault_dispatch.createrName }
										</td>
										<th>
											派单时间：
										</th>
										<td>
											<fmt:formatDate value="${fault_dispatch.sendTime }" pattern="yyyy-MM-dd HH:mm:ss" var="sendTime" />
											${sendTime }
										</td>
									</tr>
									<tr>
										<th>
											联系电话：
										</th>
										<td colspan="3">
											${fault_dispatch.phone }
										</td>
									</tr>
									<tr>
										<th>
											备注：
										</th>
										<td>
											${fault_dispatch.remark }
										</td>
									</tr>
									<tr>
										<th style="background-color:#B8DDFC;">
											<font color="#058CFE"><b>现场处理过程&nbsp;&nbsp;</b></font>
										</th>
										<th colspan="3" style="background-color:#B8DDFC;">
										</th>
									</tr>
									<tr>
										<td colspan="4" id="processHistoryTd"></td>
									</tr>
									<tr>
										<th style="background-color:#B8DDFC;">
											<font color="#058CFE"><b>现场图片&nbsp;&nbsp;</b></font>
										</th>
										<th colspan="3" style="background-color:#B8DDFC;">
										</th>
									</tr>
									<tr>
										<td colspan="4" id="processPhotosTd"></td>
									</tr>
									<tr>
										<th style="background-color:#B8DDFC;">
											<font color="#058CFE"><b>故障报告信息&nbsp;&nbsp;</b></font>
										</th>
										<th colspan="3" style="background-color:#B8DDFC;">
										</th>
									</tr>
									<tr>
										<td colspan="4" id="detailReportTd">
											<table border="0" align="center" cellpadding="3" cellspacing="0" style="width:95%">
												<c:if test="${empty fault_reply }">
													<tr class="trwhite">
														<td class="tdulright" colspan="4"
															style="width: 100%; padding: 5px; text-align: center;">
															没有可显示的内容。
														</td>
													</tr>
												</c:if>
												<c:if test="${not empty fault_reply }">
													<tr>
														<th>
															故障设备：
														</th>
														<td>
															${fault_reply.equipment }
														</td>
														<th>
															是否集团业务：
														</th>
														<td>
															<c:if test="${fault_reply.isGroupbusiness==1 }">是</c:if>
															<c:if test="${fault_reply.isGroupbusiness==2 }">否</c:if>
														</td>
													</tr>
													<tr>
														<th>
															业务影响：
														</th>
														<td>
															${fault_reply.influenceBusiness }
														</td>
														<th>
															网络分类：
														</th>
														<td>
															${fault_reply.networkType }
														</td>
													</tr>
													<tr>
														<th>
															故障处理结果：
														</th>
														<td>
															${fault_reply.faultResult }
														</td>
														<th>
															故障原因类别：
														</th>
														<td>
															${fault_reply.faultReasonType }
														</td>
													</tr>
													<tr>
														<th>
															处理措施：
														</th>
														<td colspan="3">
															${fault_reply.processMeasure }
														</td>
													</tr>
													<tr>
														<th>
															是否最终方案：
														</th>
														<td>
															<c:set var="isSolution" value="${fault_reply.isSolution }"></c:set>
															${whetherMap[isSolution] }
														</td>
														<th>
															是否入案例库：
														</th>
														<td>
															<c:set var="isCase" value="${fault_reply.isCase }"></c:set>
															${whetherMap[isCase] }
														</td>
													</tr>
													<tr>
														<th>
															是否实施变更：
														</th>
														<td>
															<c:set var="isChange" value="${fault_reply.isChange }"></c:set>
															${whetherMap[isChange] }
														</td>
														<th>
															故障消除时间：
														</th>
														<td>
															<fmt:formatDate value="${fault_reply.faultClearTime }" pattern="yyyy-MM-dd HH:mm:ss" var="faultClearTime" />
															${faultClearTime }
														</td>
													</tr>
													<tr>
														<th>
															业务恢复时间：
														</th>
														<td>
															<fmt:formatDate value="${fault_reply.businessRenewTime }" pattern="yyyy-MM-dd HH:mm:ss" var="faultRenewTime" />
															${faultRenewTime }
														</td>
														<th>
															最终网络分类：
														</th>
														<td>
															${fault_reply.incEndNettype }
														</td>
													</tr>
													<tr>
														<th>
															最终设备厂商：
														</th>
														<td>
															${fault_reply.incEndEquipment }
														</td>
														<th>
															是否重大故障：
														</th>
														<td>
															<c:set var="incIsImportantIncident" value="${fault_reply.incIsImportantIncident }"></c:set>
															${whetherMap[incIsImportantIncident] }
														</td>
													</tr>
													<tr>
														<th>
															故障现象：
														</th>
														<td colspan="3">
															${fault_reply.phenomena }
														</td>
													</tr>
													<tr>
														<th>
															故障原因：
														</th>
														<td colspan="3">
															${fault_reply.reason }
														</td>
													</tr>
													<tr>
														<th>
															故障解决方案：
														</th>
														<td colspan="3">
															${fault_reply.solution }
														</td>
													</tr>
													<tr>
														<th>
															遗留问题：
														</th>
														<td colspan="3">
															${fault_reply.leaveBehindProblem }
														</td>
													</tr>
												</c:if>
											</table>										
										</td>
									</tr>
								</table>
							</div>
							<div id="flowInfoTd">
								<table border="0" align="center" cellpadding="3" cellspacing="0">
									<tr>
										<th style="background-color:#B8DDFC;width:25%">
											<font color="#058CFE"><b>故障回单审核信息&nbsp;&nbsp;</b></font>
										</th>
										<th colspan="3" style="background-color:#B8DDFC;;width:75%">
										</th>
									</tr>
									<tr>
										<td colspan="4" id="auditHistoryTd"></td>
									</tr>
								</table>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div style="text-align: center; margin-top: 10px">
			<input type="button" class="button" onclick="history.back()" value="返回">
		</div>
	</body>
</html>

