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
In.add('vm', {path : '${ctx}/js/jQuery/jvalidation/jquery.validate.min.js',type : 'js',charset : 'utf-8'});
In.add('vcn', {path : '${ctx}/js/jQuery/jvalidation/messages_cn.js',type : 'js',charset : 'utf-8',rely : [ 'vm' ]});
In.add('vex', {path : '${ctx}/js/jQuery/jvalidation/jquery.validate.ex.js',type : 'js',charset : 'utf-8',rely : [ 'vcn' ]});
In.add('vam', {path : '${ctx}/js/jQuery/jvalidation/additional-methods.min.js',type : 'js',charset : 'utf-8',rely : [ 'vex' ]});
In.add('common', {path : '${ctx}/common/js/common.js',type : 'js',charset : 'utf-8'});
In.add('wdatejs', {path : '${ctx}/js/WdatePicker/WdatePicker.js',type : 'js',charset : 'utf-8'});
In.add('ajaxupload', {path : '${ctx}/js/jQuery/jupload/ajaxupload.js',type : 'js',charset : 'utf-8'});
In.add('upload', {path : '${ctx}/js/jQuery/jupload/upload.js',type : 'js',charset : 'utf-8',rely : [ 'ajaxupload' ]});
	In.ready('vam', 'common', 'wdatejs','jquijs','upload', function() {
		jQuery("#workorderForm").validate({
			focusInvalid : false
		});
		uploadFile();
	});
</script>
	</head>
	<body>
		<form action="${ctx }/workflow/workorderDispatchAction!save.action"
			id="workorderForm" name="workorderForm" method="post">
			<div class="title_bg">
				<div id="title" class="title">
					当前位置-新建工单：${workOrder.taskCode }
				</div>
			</div>
			<div class="tabcontent">
				<table border="0" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<th>
							主题：
						</th>
						<td colspan="3">
							<input id="workorder_task_code" name="workOrder.taskCode" value="${workOrder.taskCode }" type="hidden" />
							<input id="workOrder_id" name="workOrder.id" value="${workOrder.id }" type="hidden" />
							<input id="pageNo" name="pageNo" value="${pageNo }" type="hidden" />
							<input id="workOrder_infoid" name="workOrder.infoId" value="${workOrder.infoId }" type="hidden" />
							<input id="is_submited" name="workOrder.isSubmited" value="1" type="hidden" />
							<input id="workorder_task_name" name="workOrder.taskName" value="${workOrder.taskName }" style="width:740px;" type="input" class="inputtext required" />
							<span style="color: red">*</span>
						</td>
					</tr>
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
							<baseinfo:dicselector id="workorder_task_type" name="workOrder.taskType" style="width:220px;" columntype="TASK_CODE" type="select" keyValue="${workOrder.taskType }" cssClass="inputtext required"></baseinfo:dicselector>
							<font style="color: red;">&nbsp;*</font>
						</td>
						<th>
							专业类型：
						</th>
						<td>
							<baseinfo:dicselector id="workOrder_businessType" name="workOrder.businessType" style="width:220px;" columntype="businesstype" type="select" keyValue="${workOrder.businessType }" cssClass="inputtext required"></baseinfo:dicselector>
							<font style="color: red;">&nbsp;*</font>
						</td>
					</tr>
					<tr>
						<th>
							紧急程度：
						</th>
						<td>
							<baseinfo:dicselector id="workOrder_emergencyLevel" name="workOrder.emergencyLevel" style="width:220px;" columntype="EMERGENCY_LEVEL" type="select" keyValue="${workOrder.emergencyLevel }" cssClass="inputtext required"></baseinfo:dicselector>
							<font style="color: red;">&nbsp;*</font>
						</td>
						<th>
							受理人员：
						</th>
						<td>
							<input id="workorder_accept_user_name" name="workOrder.acceptUserNames" value="${workOrder.acceptUserNames }" style="width:220px;" class="inputtext required" style="width: 73%;" readonly="readonly" />
							<a href="javascript:search();"> <img border="0" src="${ctx}/css/image/personselect.png" /> </a>
							<span style="color: red">*</span>
							<input id="workorder_accept_user_ids" name="workOrder.acceptUserIds" value="${workOrder.acceptUserIds }" type="hidden" />
						</td>
					</tr>
					<tr>
						<th>
							受理时限：
						</th>
						<td>
							<c:if test="${origin=='transfer' }">
								<fmt:formatDate value="${workOrder.acceptanceLimit }" pattern="yyyy-MM-dd HH:mm:ss" var="acceptanceLimit" />
								${acceptanceLimit }
								<input name="workOrder.acceptanceLimit" value="${acceptanceLimit }" type="hidden" />
							</c:if>
							<c:if test="${origin=='input' }">
								<fmt:formatDate value="${workOrder.acceptanceLimit }" pattern="yyyy-MM-dd HH:mm:ss" var="acceptanceLimit" />
								<input id="workOrder_acceptanceLimit" name="workOrder.acceptanceLimit" type="text" class="Wdate inputtext required" style="width:220px;" value="${acceptanceLimit }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d'})" />
								<span style="color: red">*</span>
							</c:if>
						</td>
						<th>
							处理时限：
						</th>
						<td>
							<c:if test="${origin=='transfer' }">
								<fmt:formatDate value="${workOrder.overtimeSet }" pattern="yyyy-MM-dd HH:mm:ss" var="overtimeSet" />
								${overtimeSet }
								<input name="workOrder.overtimeSet" value="${overtimeSet }" type="hidden" />
							</c:if>
							<c:if test="${origin=='input' }">
								<fmt:formatDate value="${workOrder.overtimeSet }" pattern="yyyy-MM-dd HH:mm:ss" var="overtimeSet" />
								<input id="workorder_overtime_set" name="workOrder.overtimeSet" style="width:220px;" type="text" class="Wdate inputtext required" value="${overtimeSet }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d'})" />
								<span style="color: red">*</span>
							</c:if>
						</td>
					</tr>
					<tr>
						<th>
							任务要求：
						</th>
						<td colspan="3">
							<textarea id="workorder_task_request" name="taskRequest" class="inputtext required" style="height: 80px;width:740px;">${workOrder.taskRequest }</textarea>
							<span style="color: red">*</span>
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
						<td colspan="3" style="width: 85%">
							<c:if test="${workOrder.id!=null&&workOrder.id!='' }">
								<apptag:upload state="edit" entityId="${workOrder.id }" entityType="WTASK_ORDER"></apptag:upload>
							</c:if>
							<c:if test="${workOrder.id==null||workOrder.id=='' }">
								<apptag:upload state="add"></apptag:upload>
							</c:if>
						</td>
					</tr>
				</table>
				<div style="text-align: center; margin-top: 10px">
					<input name="btnSave" value="保存" type="button" class="button" onclick="saveData();" />
					<input name="btnSubmit" value="提交" type="button" class="button" onclick="submitData();" />
					<input name="btnReset" value="重置" type="button" class="button" onclick="resetData();" />
				</div>
			</div>
		</form>
	</body>
	<script type="text/javascript">
	function saveData() {
		jQuery("#is_submited").val("0");
		jQuery("#workorderForm").submit();
	}
	function submitData() {
		jQuery("#is_submited").val("1");
		jQuery("#workorderForm").submit();
	}
	function search() {
		var val = window.showModalDialog('${ctx}/commonaccess!getuser.action',
				'',
				'status:no;center:yes;dialogWidth:400px;dialogHeight:300px;');
		var userName = "";
		var userId = "";
		if (val) {
			for (i = 0; i < val.length; i++) {
				userName += val[i].NAME + ",";
				userId += val[i].ID + ",";
			}
			userId = userId.substring(0, userId.length - 1);
			userName = userName.substring(0, userName.length - 1);
			jQuery("#workorder_accept_user_name").val(userName);
			jQuery("#workorder_accept_user_ids").val(userId);
		}
	}
	function resetData() {
		jQuery("#workorder_task_name").val("");
		jQuery("#workorder_task_type").val("");
		jQuery("#workorder_overtime_set").val("");
		jQuery("#workorder_task_request").val("");
		jQuery("#workorder_accept_user_name").val("");
		jQuery("#workorder_accept_user_ids").val("");
	}
	</script>
</html>