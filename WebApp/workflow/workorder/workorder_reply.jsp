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
			focusInvalid: false,
			debug : true,
			submitHandler : function(form) {
				if (check()) {
					form.submit();
				}
			}
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
	});
</script>
	</head>

	<body>
		<form action="${ctx }/workflow/workorderReplyAction!save.action"
			id="workorderForm" name="form" method="post">
			<div class="title_bg">
				<div id="title" class="title">
					当前位置-回复工单-<baseinfo:dicselector id="workOrder_taskType" name="workOrder.taskType" columntype="TASK_CODE" type="view" keyValue="${workOrder.taskType }"></baseinfo:dicselector>：${workOrder.taskCode }
				</div>
			</div>
			<div class="tabcontent">
				<div id="detailInfoTd"></div>
				<table border="0" align="center" cellpadding="3" cellspacing="0"
					style="border-collapse: collapse;">
					<tr>
						<th style="background-color:#B8DDFC;width:20%">
							<font color="#058CFE"><b>回复信息&nbsp;&nbsp;</b></font>
						</th>
						<th colspan="3" style="background-color:#B8DDFC;width:80%">
						</th>
					</tr>
					<tr>
						<th style="width:20%">
							任务回复：
						</th>
						<td colspan="3" style="width:80%">
							<input id="workOrderReply_replyCheckUserId" name="workOrderReply.replyCheckUserId" value="${workOrder.creater }" type="hidden">
							<input id="pageNo" name="pageNo" value="${pageNo }" type="hidden" />
							<input id="workOrderReply_taskId" name="workOrderReply.taskId" value="${workOrder.id }" type="hidden">
							<input id="workOrderReply_transferId" name="workOrderReply.transferId" value="${pid }" type="hidden">
							<input id="workOrderReply_workflowTaskId" name="workOrderReply.workflowTaskId" value="${task_id }" type="hidden">
							<textarea id="workOrderReply_remark" name="workOrderReply.remark" class="inputtext required" style="height: 80px;width:740px;"></textarea>
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th style="width:20%">
							多次回复：
						</th>
						<td colspan="3" style="width:80%">
							<input id="workOrderReply_is_submited_yes" name="workOrderReply.isSubmited" value="0" type="radio" checked>
							是
							<input id="workOrderReply_is_submited_no" name="workOrderReply.isSubmited" value="1" type="radio">
							否
						</td>
					</tr>
					<tr>
						<th style="background-color:#B8DDFC;width:20%">
							<font color="#058CFE"><b>附件列表&nbsp;&nbsp;</b></font>
						</th>
						<th colspan="3" style="background-color:#B8DDFC;width:80%">
						</th>
					</tr>
					<tr>
						<th style="width:20%">
							回复附件：
						</th>
						<td colspan="3" style="width:80%">
							<apptag:upload state="add"></apptag:upload>
						</td>
					</tr>
				</table>
				<div style="text-align: center; margin-top: 10px">
					<input name="btnSubmit" value="提交" type="submit" class="button" />
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
	function resetData(){
		jQuery("#workOrderReply_remark").val("");
		jQuery("#workOrderReply_is_submited_yes").attr("checked","true");
		jQuery("#workOrderReply_is_submited_no").removeAttr("checked");
	}
	</script>
</html>