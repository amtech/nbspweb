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
		var url='${ctx}/workflow/workorderDispatchAction!viewAll.action?id=${workOrder.id}&pId=${pid}';
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
					showReplyCheckHistory('${pid }');
				}
				initialized[ui.index] = true;
			});
			showProcessHistory('${pid}');
			showProcessPhotos('${pid}');
			showReplyHistory('${pid }');
			showEveryStepTimeLength('${workOrder.id}', '${pid}');
			jQuery('#tab_process').tabs();
		});
	})
</script>
	</head>
	<body>
		<form action="${ctx }/workflow/workorderReplyCheckAction!save.action"
			id="workorderForm" name="form" method="post">
			<div class="title_bg">
				<div id="title" class="title">
					当前位置-回复工单验证-<baseinfo:dicselector id="workOrder_taskType" name="workOrder.taskType" columntype="TASK_CODE" type="view" keyValue="${workOrder.taskType }"></baseinfo:dicselector>：${workOrder.taskCode }
				</div>
			</div>
			<div class="tabcontent">
				<div id="detailInfoTd"></div>
				<table border="0" align="center" cellpadding="3" cellspacing="0">
					<tr class="trwhite">
						<th style="width:20%">
							验证结果：
						</th>
						<td colspan="3" style="width:80%">
							<input id="workOrderReplyCheck_taskId" name="workOrderReplyCheck.taskId" value="${workOrder.id }" type="hidden">
							<input id="workOrderReplyCheck_workflowTaskId" name="workOrderReplyCheck.workflowTaskId" value="${task_id }" type="hidden">
							<input id="pageNo" name="pageNo" value="${pageNo }" type="hidden" />
							<input id="workOrderReplyCheck_workflowBzId" name="workOrderReplyCheck.workflowBzId" value="${pid }" type="hidden">
							<input id="workOrderReplyCheck_checkResult_yes" name="workOrderReplyCheck.checkResult" onclick="changeApproveResult(this.value);" value="pass" type="radio" checked>
							通过
							<input id="workOrderReplyCheck_checkResult_no" name="workOrderReplyCheck.checkResult" onclick="changeApproveResult(this.value);" value="reject" type="radio">
							不通过
							<input type="radio" id="workOrderReplyCheck_checkResult_transfer" name="workOrderReplyCheck.checkResult" onclick="changeApproveResult(this.value);" value="transfer" />
							转审
						</td>
					</tr>
					<tr id="transferApproverTr" style="display: none;">
						<th style="width:20%">
							转审人：
						</th>
						<td colspan="3" style="width:80%">
							<input id="workOrderReplyCheck_transferApproverName" name="transferApproverName" class="inputtext required" style="width: 740px;" readonly="readonly" />
							<a href="javascript:searchApprover('${LOGIN_USER.regionId }');"> <img border="0" src="${ctx}/css/image/personselect.png" /> </a>
							<span style="color: red">*</span>
							<input id="workOrderReplyCheck_transferApprover" name="workOrderReplyCheck.transferApprover" type="hidden" />
						</td>
					</tr>
					<tr class="trwhite">
						<th style="width:20%">
							验证意见：
						</th>
						<td colspan="3" style="width:80%">
							<textarea id="workOrderReplyCheck_checkRemark" name="workOrderReplyCheck.checkRemark" class="inputtext" style="width: 740px; height: 80px;"></textarea>
						</td>
					</tr>
				</table>
				<div style="text-align: center; margin-top: 10px">
					<input name="btnSubmit" value="提交" type="submit" class="button" onclick="" />
					<input name="btnReset" value="重置" type="button" class="button" onclick="resetData();" />
					<input name="btnReturn" value="返回" type="button" class="button" onclick="history.go(-1);" />
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
			jQuery("#workOrderReplyCheck_transferApproverName").val(userName);
			jQuery("#workOrderReplyCheck_transferApprover").val(userId);
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
		jQuery("#workOrderReplyCheck_checkResult_yes").attr("checked","true");
		jQuery("#workOrderReplyCheck_checkResult_no").removeAttr("checked");
		jQuery("#workOrderReplyCheck_checkResult_transfer").removeAttr("checked");
		jQuery("#transferApproverTr").attr("style", "display:none");
		jQuery("#workOrderReplyCheck_transferApproverName").val("");
		jQuery("#workOrderReplyCheck_transferApprover").val("");
		jQuery("#workOrderReplyCheck_checkRemark").val("");
	}
	</script>
</html>