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
In.add('vm',{path:'${ctx}/js/jQuery/jvalidation/jquery.validate.min.js',type:'js',charset:'utf-8'});
In.add('vcn',{path:'${ctx}/js/jQuery/jvalidation/messages_cn.js',type:'js',charset:'utf-8',rely:['vm']});
In.add('vex',{path:'${ctx}/js/jQuery/jvalidation/jquery.validate.ex.js',type:'js',charset:'utf-8',rely:['vcn']});
In.add('vam',{path:'${ctx}/js/jQuery/jvalidation/additional-methods.min.js',type:'js',charset:'utf-8',rely:['vex']});
In.add('jqui',{path:'${ctx}/js/jQuery/jqueryui/css/redmond/jquery-ui.custom.css'});
In.add('jquijs',{path:'${ctx}/js/jQuery/jqueryui/jquery-ui.custom.min.js',type:'js',charset:'utf-8',rely:['jqui']});
In.add('common',{path:'${ctx}/common/js/common.js',type:'js',charset:'utf-8'});
In.add('wdatejs',{path:'${ctx}/js/WdatePicker/WdatePicker.js',type:'js',charset:'utf-8'});
In.add('oe_dispatchtask_common',{path:'${ctx}/workflow/electricity/security/js/oe_dispatch_task_common.js',type:'js',charset:'utf-8'});
In.add('oe_dispatchtask_input',{path:'${ctx}/workflow/electricity/security/js/oe_dispatch_task_input.js',type:'js',charset:'utf-8',rely:['oe_dispatchtask_common']});
	In.ready('vam','jquijs','common','wdatejs','oe_dispatchtask_input',function(){
		setContextPath("${ctx}");
		jQuery("#oeDispatchTaskDispatchForm").validate({
			focusInvalid : false
		});
		getTitle();
	});
</script>
	</head>
	<body>
		<form action="${ctx }/workflow/oeDispatchTaskAction!save.action"
			id="oeDispatchTaskDispatchForm" method="post">
			<div class="title_bg">
				<div id="title" class="title">
					当前位置-供电保障-发电派单：${oeDispatchTask.taskCode }
				</div>
			</div>
			<div class="tabcontent">
				<table border="0" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<th>
							主题：
						</th>
						<td colspan="3">
							<input id="oeDispatchTask_title" name="oeDispatchTask.title" value="${oeDispatchTask.title }" type="input required" class="inputtext required" readonly style="width: 740px" />
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th style="background-color:#B8DDFC;">
							<font color="#058CFE"><b>基本信息&nbsp;&nbsp;</b></font>
						</th>
						<th colspan="3" style="background-color:#B8DDFC;"></th>
					</tr>
					<c:if test="${origin=='alarm' }">
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
					</c:if>
					<tr>
						<th>
							<c:if test="${origin=='alarm' }">
								确认断电站点：
							</c:if>
							<c:if test="${origin!='alarm' }">
								断电站点：
							</c:if>
						</th>
						<td>
							<input id="oeDispatchTask_id" name="oeDispatchTask.id" value="${oeDispatchTask.id }" type="hidden" />
							<input id="businessType" name="businessType" value="C31" type="hidden" />
							<input id="oeDispatchTask_alarmId" name="oeDispatchTask.alarmId" value="${oeDispatchTask.alarmId }" type="hidden" />
							<input id="is_submited" name="oeDispatchTask.isSubmited" value="1" type="hidden" />
							<input id="oeDispatchTask_stationType" name="oeDispatchTask.stationType" value="${oeDispatchTask.stationType }" type="hidden" />
							<input id="oeDispatchTask_stationId" name="oeDispatchTask.stationId" value="${oeDispatchTask.stationId }" type="hidden" />
							<input id="oeDispatchTask_stationName" name="oeDispatchTask.stationName" readonly value="${oeDispatchTask.stationName }" type="text" class="inputtext required" style="width: 220px" />
							<a href="javascript:searchResource();"> <img border="0" src="${ctx}/css/images/selectcode.gif" /> </a>
							<span style="color: red">*</span>
						</td>
						<th>
							站点地址：
						</th>
						<td>
							<input id="oeDispatchTask_stationAddress" name="oeDispatchTask.stationAddress" value="${oeDispatchTask.stationAddress }" type="text" class="inputtext required" style="width: 220px" />
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th>
							断电时间：
						</th>
						<td>
							<fmt:formatDate value="${oeDispatchTask.blackoutTime }" pattern="yyyy-MM-dd HH:mm:ss" var="blackoutTime" />
							<input id="oeDispatchTask_blackoutTime" readonly name="oeDispatchTask.blackoutTime" value="${blackoutTime }" type="input" class="Wdate inputtext required" style="width: 220px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){getTitle();},minDate:'%y-%M-%d'})" />
							<span style="color: red">*</span>
						</td>
						<th>
							维护单位：
						</th>
						<td>
							<input id="maintenanceName" name="maintenanceName" readonly value="${oeDispatchTask.orgName }" class="inputtext required" style="width: 220px" />
							<a href="javascript:searchOrg();"> <img border="0" src="${ctx}/css/image/orgselect.png" /> </a>
							<input type="hidden" name="oeDispatchTask.maintenanceId" id="maintenanceId" value="${oeDispatchTask.maintenanceId }" />
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th>
							受理时限：
						</th>
						<td>
							<fmt:formatDate value="${oeDispatchTask.acceptLimit }" pattern="yyyy-MM-dd HH:mm:ss" var="acceptLimit" />
							<input id="oeDispatchTask_acceptLimit" readonly name="oeDispatchTask.acceptLimit" value="${acceptLimit }" type="input" class="Wdate inputtext required" style="width: 220px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d'})" />
							<span style="color: red">*</span>
						</td>
						<th>
							处理时限：
						</th>
						<td>
							<fmt:formatDate value="${oeDispatchTask.handleLimit }" pattern="yyyy-MM-dd HH:mm:ss" var="handleLimit" />
							<input id="oeDispatchTask_handleLimit" readonly name="oeDispatchTask.handleLimit" value="${handleLimit }" type="input" class="Wdate inputtext required" style="width: 220px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d'})" />
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th>
							断电原因：
						</th>
						<td colspan="3">
							<textarea id="oeDispatchTask_blackoutReason" name="oeDispatchTask.blackoutReason" class="inputtext required" style="width: 740px; height: 80px;">${oeDispatchTask.blackoutReason }</textarea>
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th>
							服务区域：
						</th>
						<td colspan="3">
							<textarea id="oeDispatchTask_serviceRegion" name="oeDispatchTask.serviceRegion" class="inputtext required" style="width: 740px; height: 80px;">${oeDispatchTask.serviceRegion }</textarea>
							<span style="color: red">*</span>
						</td>
					</tr>
				</table>
				<div style="text-align: center; margin-top: 10px">
					<input type="submit" class="button" value="保存" onclick="saveData();" />
					<input type="submit" onclick="submitData();" class="button" value="提交" />
					<input type="button" onclick="resetData();" class="button" value="重置" />
					<c:if test="${showReturn=='y' }">
						<input type="button" class="button" onclick="history.go(-1);" value="返回" />
					</c:if>
				</div>
			</div>
		</form>
	</body>
	<script type="text/javascript">
	function checkData() {
		return true;
	}
	</script>
</html>