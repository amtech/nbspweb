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
In.add('oe_dispatchtask_input',{path:'${ctx}/workflow/electricity/security/js/oe_dispatchtask_replyapprove.js',type:'js',charset:'utf-8',rely:['oe_dispatchtask_common']});
	In.ready('vam','jquijs','common','wdatejs','oe_dispatchtask_input',function(){
		setContextPath("${ctx}");
		jQuery("#oeReplyApproveTaskForm").validate({
			focusInvalid : false
		});
		var url = "${ctx}/workflow/oeDispatchTaskAction!view.action?id=${id}";
		jQuery("#view_plan_div").load(url+" #detailTd",function(){
			var initialized = [ false, false ]; //是否已初始化
			var tab = jQuery("#tab_process").tabs({
				cache : true,
				selected : 0
			});
			tab.bind('tabsshow', function(event, ui) {
				//选择二个tab时，生成未巡检rfid
				if (ui.index == 1 && !initialized[ui.index]) {
					showReplyHistory("${oeDispatchTask.id}");
					showAuditHistory("${oeDispatchTask.id}");
				}
				initialized[ui.index] = true;
			});
			showOilGenEleHistory("${oeDispatchTask.id}");
			showScheduleHistroy("${oeDispatchTask.id}");
			jQuery('#tab_process').tabs();
		});
	});
</script>
	</head>
	<body>
		<form action="${ctx }/workflow/oeReplyApproveTaskAction!save.action"
			id="oeReplyApproveTaskForm" method="post">
			<div class="title_bg">
				<div id="title" class="title">
					当前位置-供电保障-回复派单审核：${oeDispatchTask.taskCode }
				</div>
			</div>
			<div class="tabcontent">
				<div id="view_plan_div"></div>
				<table border="0" align="center">
					<tr>
						<th style="width:20%">
							是否断站：
						</th>
						<td colspan="3" style="width:80%">
							<input type="radio" id="oeReplyApproveTask_onstation" name="oeReplyApproveTask.offStation" value="0" />
							否
							<input type="radio" id="oeReplyApproveTask_offstation" name="oeReplyApproveTask.offStation" value="1" />
							是
						</td>
					</tr>
					<tr>
						<th style="width:20%">
							审核结果：
						</th>
						<td colspan="3" style="width:80%">
							<input type="radio" id="oeReplyApproveTask_pass" name="oeReplyApproveTask.approveResult" onclick="changeApproveResult(this.value);" value="pass" checked />
							通过
							<input type="radio" id="oeReplyApproveTask_reject" name="oeReplyApproveTask.approveResult" onclick="changeApproveResult(this.value);" value="reject" />
							不通过
							<input type="radio" id="oeReplyApproveTask_transfer" name="oeReplyApproveTask.approveResult" onclick="changeApproveResult(this.value);" value="transfer" />
							转审
						</td>
					</tr>
					<tr id="transferApproverTr" style="display: none;">
						<th style="width:20%">
							转审人：
						</th>
						<td colspan="3" style="width:80%">
							<input id="approverName" name="approvername" value="" class="inputtext required" style="width:740px" readonly="readonly" />
							<a href="javascript:searchAcceptUser('${LOGIN_USER.regionId }');"> <img border="0" src="${ctx}/css/image/personselect.png" /> </a>
							<span style="color: red">*</span>
							<input id="approverId" name="oeReplyApproveTask.transferApprover" value="" type="hidden" />
						</td>
					</tr>
					<tr>
						<th style="width:20%">
							审核意见：
						</th>
						<td colspan="3" style="width:80%">
							<textarea id="oeReplyApproveTask_approveRemark" name="oeReplyApproveTask.approveRemark" class="inputtext" style="height: 80px;width:740px;"></textarea>
						</td>
					</tr>
				</table>
			</div>
			<div style="text-align: center; margin-top: 10px">
				<input id="id" name="oeReplyApproveTask.dispatchId" value="${id }" type="hidden" />
				<input id="task_id" name="oeReplyApproveTask.workflowTaskId" value="${task_id }" type="hidden" />
				<input id="businessType" name="businessType" value="${oeDispatchTask.businessType }" type="hidden" />
				<input name="btnSubmit" value="提交" type="submit" class="button" onclick="submitData();" />
				<input name="btnReset" value="重置" type="button" class="button" onclick="resetData();" />
				<input type="button" class="button" onclick="history.go(-1);" value="返回">
			</div>
		</form>
	</body>
	<script type="text/javascript">
	function checkData() {
		return true;
	}
	</script>
</html>