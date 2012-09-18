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
	In.add('vm', {path : '${ctx}/js/jQuery/jvalidation/jquery.validate.min.js',type : 'js',charset : 'utf-8'});
	In.add('vcn', {path : '${ctx}/js/jQuery/jvalidation/messages_cn.js',type : 'js',charset : 'utf-8',rely : [ 'vm' ]});
	In.add('vex', {path : '${ctx}/js/jQuery/jvalidation/jquery.validate.ex.js',type : 'js',charset : 'utf-8',rely : [ 'vcn' ]});
	In.add('vam', {path : '${ctx}/js/jQuery/jvalidation/additional-methods.min.js',type : 'js',charset : 'utf-8',rely : [ 'vex' ]});
	In.add('common', {path : '${ctx}/common/js/common.js',type : 'js',charset : 'utf-8'});
	In.add('wdatejs', {path : '${ctx}/js/WdatePicker/WdatePicker.js',type : 'js',charset : 'utf-8'});
	In.add('pickjs', {path : '${ctx}/js/jQuery/galleria/galleria-1.2.8.min.js',type : 'js',charset : 'utf-8',rely:['jquijs']});
	In.add('fault-common', {path : '${ctx}/workflow/fault/js/fault_common.js',type : 'js',charset : 'utf-8',rely : [ 'pickjs' ]});
	In.add('fault-dispatch', {path : '${ctx}/workflow/fault/js/fault_dispatch_input.js',type : 'js',charset : 'utf-8',rely : [ 'fault-common' ]});
	In.ready('vam', 'common', 'wdatejs', 'fault-dispatch', function() {
		setContextPath("${ctx}");
		setPatrolGroupId("${faultDispatch.patrolGroup }");
		setPatrolGroupName("${faultDispatch.patrolGroupName }");
		setAddress("${faultAlert.address}");
		jQuery("#faultDispatchForm").validate({
			focusInvalid : false,
			submitHandler : function(form) {
				if (checkData()) {
					form.submit();
				}
			}
		});
		if (jQuery("#fault_alert_findtype")) {
			var findType = jQuery("#fault_alert_findtype");
			for (i = 0; i < findType.length; i++) {
				if (findType.options && findType.options[i].value == 'B16') {
					findType.options.remove(i);
				}
			}
		}
		if ('${faultAlert.findType}' == 'B16') {
			showFaultPhotos('${faultAlert.id }');
		}
		if ('${faultAlert.findType}' == 'B17') {
			showEoms('${faultAlert.eomsId}');
		}
	});
</script>
	</head>
	<body>
		<form action="${ctx }/workflow/faultDispatchAction!dispatch.action"
			id="faultDispatchForm" method="post" onsubmit="">
			<div class="title_bg">
				<div id="title" class="title">
					当前位置-故障派单：${faultDispatch.taskCode }
				</div>
			</div>
			<div class="tabcontent">
				<table border="0" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<th>
							主题：
						</th>
						<td colspan="3">
							<input id="is_submited" name="faultDispatch.isSubmited" value="1" type="hidden" />
							<input id="parameter_origin" name="parameter.origin" value="${parameter.origin }" type="hidden" />
							<input id="faultAlert_id" name="faultAlert.id" value="${faultAlert.id }" type="hidden" />
							<input id="faultDispatch_id" name="faultDispatch.id" value="${faultDispatch.id }" type="hidden" />
							<input id="faultDispatch_alarmId" name="faultDispatch.alarmId" value="${faultAlert.id }" type="hidden" />
							<input id="faultAlert_troubleTitle" name="faultAlert.troubleTitle" value="${faultAlert.troubleTitle }" type="input" class="inputtext required" style="width: 740px" />
							<span style="color: red">*</span>
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
					<c:if test="${faultAlert.findType=='B17' }">
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
						<th colspan="3" style="background-color:#B8DDFC;"></th>
					</tr>
					<tr>
						<th>
							发现方式：
						</th>
						<td>
							<input name="faultAlert.findType" id="fault_alert_findtype" value="${faultAlert.findType }" type=hidden />
							<baseinfo:dicselector id="" name="faultAlert.findType" columntype="FIND_TYPE" type="view" keyValue="${faultAlert.findType }"></baseinfo:dicselector>
						</td>
						<th>
							<c:if test="${faultAlert.findType=='B17' }">
							</c:if>
							<div id="emos_title_div" style="display: none;">
								EMOS单号：
							</div>
						</th>
						<td>
							<c:if test="${faultAlert.findType=='B17' }">
								${faultAlert.eomsId }
								<input id="faultAlert_eomsId" name="faultAlert.eomsId" value="${faultAlert.eomsId }" type="hidden" />
							</c:if>
						</td>
					</tr>
					<tr>
						<th>
							故障发生时间：
						</th>
						<td>
							<fmt:formatDate value="${faultAlert.troubleTime }" pattern="yyyy-MM-dd HH:mm:ss" var="troubleTime" />
							<input id="faultAlert_troubleTime" name="faultAlert.troubleTime" value="${troubleTime }" type="input" class="Wdate inputtext required" style="width: 220px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d'})" />
							<span style="color: red">*</span>
						</td>
						<th>
							是否紧急故障：
						</th>
						<td>
							<c:set var="checkedNoRadio" value="checked"></c:set>
							<c:set var="checkedYesRadio" value=""></c:set>
							<c:if test="${faultAlert.isInstancy==1 }">
								<c:set var="checkedYesRadio" value="checked"></c:set>
								<c:set var="checkedNoRadio" value=""></c:set>
							</c:if>
							<c:if test="${faultAlert.isInstancy==2 }">
								<c:set var="checkedYesRadio" value=""></c:set>
								<c:set var="checkedNoRadio" value="checked"></c:set>
							</c:if>
							<input name="faultAlert.isInstancy" value="2" type="radio" ${checkedNoRadio } />
							否
							<input name="faultAlert.isInstancy" value="1" type="radio" ${checkedYesRadio } />
							是
						</td>
					</tr>
					<tr>
						<th>
							报告时间：
						</th>
						<td>
							<fmt:formatDate value="${faultAlert.reportTime }" pattern="yyyy-MM-dd HH:mm:ss" var="reportTime" />
							<input type="text" id="faultAlert_reportTime" style="width: 220px" name="faultAlert.reportTime" value="${reportTime }" class="Wdate inputtext required" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'faultAlert_troubleTime\')}'})" />
							<span style="color: red">*</span>
						</td>
						<th>
							报告人：
						</th>
						<td>
							<input id="faultAlert_reporter" name="faultAlert.reporter" value="${faultAlert.reporter }" type="input" class="inputtext required" style="width: 220px" />
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th>
							故障级别：
						</th>
						<td>
							<baseinfo:dicselector id="fault_alert_troublelevel" name="faultAlert.troubleLevel" columntype="TROUBLE_LEVEL" type="select" keyValue="${faultAlert.troubleLevel }" cssClass="inputtext required" style="width: 220px;"></baseinfo:dicselector>
							<font style="color: red;">&nbsp;*</font>
						</td>
						<th>
							专业类型：
						</th>
						<td>
							<baseinfo:customselector name="faultAlert.businessType" data="${businessTypeMap}" isReversal="true" cssClass="inputtext required" id="businessType" style="width: 220px" keyValue="${faultAlert.businessType }"></baseinfo:customselector>
							<font style="color: red;">&nbsp;*</font>
						</td>
					</tr>
					<tr>
						<th>
							响应期限：
						</th>
						<td>
							<fmt:formatDate value="${faultDispatch.response }" pattern="yyyy-MM-dd HH:mm:ss" var="responseDate" />
							<input type="text" id="faultDispatch_response" style="width: 220px" name="faultDispatch.response" value="${responseDate }" class="Wdate inputtext required" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'faultAlert_reportTime\')}'})" />
							<span style="color: red">*</span>
						</td>
						<th>
							处理期限：
						</th>
						<td>
							<fmt:formatDate value="${faultDispatch.deadline }" pattern="yyyy-MM-dd HH:mm:ss" var="deadlineDate" />
							<input type="text" id="faultDispatch_deadline" style="width: 220px" name="faultDispatch.deadline" value="${deadlineDate }" class="Wdate inputtext required" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'faultAlert_reportTime\')}'})" />
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th>
							故障描述：
						</th>
						<td colspan="3">
							<textarea id="troubleDesc" name="faultAlert.troubleDesc" class="inputtext" style="width: 740px; height: 80px;">${faultAlert.troubleDesc }</textarea>
						</td>
					</tr>
					<tr>
						<th>
							故障站点：
						</th>
						<td>
							<input type="hidden" name="faultAlert.stationType" id="stationType" value="${faultAlert.stationType }" />
							<input type="hidden" name="faultAlert.stationId" id="stationId" value="${faultAlert.stationId }" />
							<input type="text" name="resourceName" class="inputtext" style="width: 220px" id="resourceName" value="${faultAlert.stationName }" />
							<a href="javascript:searchResource();"> <img border="0" src="${ctx}/css/images/selectcode.gif" /> </a>
							<span style="color: red">*</span>
						</td>
						<th>
							故障地点：
						</th>
						<td>
							<input id="address" name="faultAlert.address" value="${faultAlert.address }" type="input" class="inputtext required" style="width: 220px" />
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th>
							维护组：
						</th>
						<td colspan="3">
							<input id="patrolGroupName" name="patrolGroupName" type="text" class="inputtext required" style="width: 220px" value="${faultDispatch.patrolGroupName }" />
							<a href="javascript:search();"> <img border="0" src="${ctx}/css/image/groupselect.png" /> </a>
							<span style="color: red;">&nbsp;*</span>
							<input type="hidden" name="faultDispatch.maintenanceId" id="maintenanceId" value="${faultDispatch.maintenanceId }" />
							<input type="hidden" name="faultDispatch.patrolGroup" id="patrolGroupId" value="${faultDispatch.patrolGroup }" />
						</td>
					</tr>
					<tr>
						<th>
							备注：
						</th>
						<td colspan="3">
							<textarea id="remark" name="faultDispatch.remark" class="inputtext" style="width: 740px; height: 80px;">${faultDispatch.remark }</textarea>
						</td>
					</tr>
				</table>
				<div style="text-align: center; margin-top: 10px">
					<input type="submit" class="button" value="保存" onclick="saveData();" />
					<input type="submit" onclick="submitData();" class="button" value="派单" />
					<input type="button" class="button" onclick="history.back()" value="返回" />
				</div>
			</div>
		</form>
	</body>
	<script type="text/javascript">
	function checkData() {
		if (jQuery("#fault_alert_findtype").val() == "") {
			alert("没有选择发现方式！");
			return false;
		}
		if (jQuery("#fault_alert_troublelevel").val() == "") {
			alert("没有选择故障级别！");
			return false;
		}
		if (jQuery("#patrolGroupId").val() == "") {
			alert("没有选择维护组！");
			return false;
		}
		return true;
	}
	function saveData() {
		jQuery("#is_submited").val("0");
		return true;
	}
	function submitData() {
		jQuery("#is_submited").val("1");
		return true;
	}
	function changeFindType(value) {
		if (value == "B17") {
			document.getElementById("emos_title_div").style.display = "";
			document.getElementById("emos_value_div").style.display = "";
		} else {
			document.getElementById("emos_title_div").style.display = "none";
			document.getElementById("emos_value_div").style.display = "none";
		}
	}
</script>
</html>
