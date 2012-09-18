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
		<script language="javascript" type="text/javascript">
In.add('jqui',{path:'${ctx}/js/jQuery/jqueryui/css/redmond/jquery-ui.custom.css'});
In.add('jquijs',{path:'${ctx}/js/jQuery/jqueryui/jquery-ui.custom.min.js',type:'js',charset:'utf-8',rely:['jqui']});
In.add('layoutjs',{path:'${ctx}/js/jQuery/layout/jquery.layout-latest.min.js',type:'js',charset:'utf-8',rely:['jquijs']});
In.add('vm', {path : '${ctx}/js/jQuery/jvalidation/jquery.validate.min.js',type : 'js',charset : 'utf-8'});
In.add('vcn', {path : '${ctx}/js/jQuery/jvalidation/messages_cn.js',type : 'js',charset : 'utf-8',rely : [ 'vm' ]});
In.add('vex', {path : '${ctx}/js/jQuery/jvalidation/jquery.validate.ex.js',type : 'js',charset : 'utf-8',rely : [ 'vcn' ]});
In.add('vam', {path : '${ctx}/js/jQuery/jvalidation/additional-methods.min.js',type : 'js',charset : 'utf-8',rely : [ 'vex' ]});
In.add('common', {path : '${ctx}/common/js/common.js',type : 'js',charset : 'utf-8'});
In.add('wdatejs', {path : '${ctx}/js/WdatePicker/WdatePicker.js',type : 'js',charset : 'utf-8'});
In.add('workorder_common', {path : '${ctx}/workflow/workorder/js/workorder_common.js',type : 'js',charset : 'utf-8'});
	In.ready('vam', 'common', 'wdatejs','layoutjs','workorder_common', function() {
		jQuery("#workorderForm").validate({
			focusInvalid: false
		});
		setContextPath('${ctx}');
		var url='${ctx}/workflow/workorderDispatchAction!viewAll.action?id=${workOrder.id}&pId=${pid}&type=1';
		jQuery("#detailInfoTd").load(url+" #detailTd",function(){
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
				}
				initialized[ui.index] = true;
			});
			jQuery('#tab_process').tabs();
		});
	});
</script>
	</head>
	<body>
		<form
			action="${ctx }/workflow/workorderRefuseConfirmAction!save.action"
			id="workorderForm" name="form" method="post">
			<div class="title_bg">
				<div id="title" class="title">
					当前位置-拒签工单确认-<baseinfo:dicselector id="workOrder_taskType" name="workOrder.taskType" columntype="TASK_CODE" type="view" keyValue="${workOrder.taskType }"></baseinfo:dicselector>：${workOrder.taskCode }
				</div>
			</div>
			<div class="tabcontent">
				<div id="detailInfoTd"></div>
				<table border="0" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<th style="width:20%">
							确认结果：
						</th>
						<td colspan="3" style="width:80%">
							<input id="workOrderRefuseConfirm_workflowBzId" name="workOrderRefuseConfirm.workflowBzId" value="${pid }" type="hidden">
							<input id="pageNo" name="pageNo" value="${pageNo }" type="hidden" />
							<input id="workOrderRefuseConfirm_taskId" name="workOrderRefuseConfirm.taskId" value="${workOrder.id }" type="hidden">
							<input id="workOrderRefuseConfirm_workflowTaskId" name="workOrderRefuseConfirm.workflowTaskId" value="${task_id }" type="hidden">
							<input id="workOrderRefuseConfirm_refuseConfirmResult_yes" name="workOrderRefuseConfirm.refuseConfirmResult" onclick="changeApproveResult(this.value);" value="pass" type="radio" checked>
							通过
							<input id="workOrderRefuseConfirm_refuseConfirmResult_no" name="workOrderRefuseConfirm.refuseConfirmResult" onclick="changeApproveResult(this.value);" value="reject" type="radio">
							不通过
							<input type="radio" id="workOrderRefuseConfirm_refuseConfirmResult_transfer" name="workOrderRefuseConfirm.refuseConfirmResult" onclick="changeApproveResult(this.value);" value="transfer" />
							转审
						</td>
					</tr>
					<tr id="transferApproverTr" style="display: none;">
						<th style="width:20%">
							转审人：
						</th>
						<td colspan="3" style="width:80%">
							<input id="workOrderRefuseConfirm_transferApproverName" name="transferApproverName" class="inputtext required" style="width: 740px;" readonly="readonly" />
							<a href="javascript:searchApprover('${LOGIN_USER.regionId }');"> <img border="0" src="${ctx}/css/image/personselect.png" /> </a>
							<span style="color: red">*</span>
							<input id="workOrderRefuseConfirm_transferApprover" name="workOrderRefuseConfirm.transferApprover" type="hidden" />
						</td>
					</tr>
					<tr>
						<th style="width:20%">
							确认意见：
						</th>
						<td colspan="3" style="width:80%">
							<textarea id="workOrderRefuseConfirm_refuseConfirmRemark" name="workOrderRefuseConfirm.refuseConfirmRemark" class="inputtext" style="width: 740px; height: 80px;"></textarea>
						</td>
					</tr>
				</table>
				<div style="text-align: center; margin-top: 10px">
					<input name="btnSubmit" value="提交" type="submit" class="button" onclick="" />
					<input name="btnReset" value="重置" type="button" class="button" onclick="resetData();" />
				</div>
			</div>
		</form>
	</body>
	<script type="text/javascript">
	function check() {
		return true;
	}
	function searchApprover(regionId) {
		var val = window.showModalDialog(
				'${ctx}/commonaccess!getstaff.action?flag=radio&regionid='+regionId, '',
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
			jQuery("#workOrderRefuseConfirm_transferApproverName")
					.val(userName);
			jQuery("#workOrderRefuseConfirm_transferApprover").val(userId);
		}
	}
	function changeApproveResult(value) {
		if (value == "transfer") {
			jQuery("#transferApproverTr").attr("style", "display:");
		} else {
			jQuery("#transferApproverTr").attr("style", "display:none");
		}
	}
	function resetData(){
		jQuery("#workOrderRefuseConfirm_refuseConfirmResult_yes").attr("checked","true");
		jQuery("#workOrderRefuseConfirm_refuseConfirmResult_no").removeAttr("checked");
		jQuery("#workOrderRefuseConfirm_refuseConfirmResult_transfer").removeAttr("checked");
		jQuery("#transferApproverTr").attr("style", "display:none");
		jQuery("#workOrderRefuseConfirm_transferApproverName").val("");
		jQuery("#workOrderRefuseConfirm_transferApprover").val("");
		jQuery("#workOrderRefuseConfirm_refuseConfirmRemark").val("");
	}
	</script>
</html>