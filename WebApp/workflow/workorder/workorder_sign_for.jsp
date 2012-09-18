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
			debug : true,
			focusInvalid: false,
			submitHandler : function(form) {
				if (check()) {
					form.submit();
				}
			}
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
		<form action="${ctx }/workflow/workorderSignForAction!save.action"
			id="workorderForm" name="form" method="post">
			<div class="title_bg">
				<div id="title" class="title">
					当前位置-签收工单-<baseinfo:dicselector id="workOrder_taskType" name="workOrder.taskType" columntype="TASK_CODE" type="view" keyValue="${workOrder.taskType }"></baseinfo:dicselector>：${workOrder.taskCode }
				</div>
			</div>
			<div class="tabcontent">
				<div id="detailInfoTd"></div>
				<table border="0" align="center" cellpadding="3" cellspacing="0"
					style="border-collapse: collapse;">
					<tr>
						<th style="width:20%">
							签收结果：
						</th>
						<td colspan="3" style="width:80%">
							<input id="workorderSignFor_workflowBzId" name="workorderSignFor.workflowBzId" value="${pid }" type="hidden">
							<input id="workorderSignFor_workflowTaskId" name="workorderSignFor.workflowTaskId" value="${task_id }" type="hidden">
							<input id="pageNo" name="pageNo" value="${pageNo }" type="hidden" />
							<input id="workorderSignFor_taskId" name="workorderSignFor.taskId" value="${workOrder.id }" type="hidden">
							<input id="workorderSignFor_signForResult_yes" name="workorderSignFor.signForResult" value="pass" type="radio" checked onclick="changeRemarkLabel(this.value);">
							签收
							<input id="workorderSignFor_signForResult_no" name="workorderSignFor.signForResult" value="untread" type="radio" onclick="changeRemarkLabel(this.value);">
							拒签
						</td>
					</tr>
					<tr id="patrolGrouptr">
						<th style="width:20%">
							巡检组：
						</th>
						<td colspan="3" style="width:80%">
							<input id="patrolGroupName" name="patrolGroupName" />
							<a href="javascript:search();"> <img border="0" src="${ctx}/css/image/groupselect.png" /> </a>
							<input id="patrolGroupId" name="workorderSignFor.patrolGroupId" value="" type="hidden">
						</td>
					</tr>
					<tr>
						<th style="width:20%">
							<span id="signfor_label_id" style="display: ;"> 签收说明： </span>
							<span id="refuse_label_id" style="display: none;"> 拒签说明： </span>
						</th>
						<td colspan="3" style="width:80%">
							<textarea id="workorderSignFor_signForRemark" name="workorderSignFor.signForRemark" class="inputtext" style="width: 740px; height: 80px;"></textarea>
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
	function changeRemarkLabel(value) {
		if (value == "pass") {
			jQuery("#signfor_label_id").attr("style", "display:");
			jQuery("#refuse_label_id").attr("style", "display:none");
			jQuery("#patrolGrouptr").show();
		} else {
			jQuery("#signfor_label_id").attr("style", "display:none");
			jQuery("#refuse_label_id").attr("style", "display:");
			jQuery("#patrolGrouptr").hide();
		}
	}
	function search() {
		var val = window.showModalDialog(
				'${ctx}/commonaccess!getpatrolgroup.action', '',
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
			jQuery("#patrolGroupName").val(userName);
			jQuery("#patrolGroupId").val(userId);
		}
	}
	function resetData(){
		jQuery("#workorderSignFor_signForResult_yes").attr("checked","true");
		jQuery("#workorderSignFor_signForResult_no").removeAttr("checked");
		jQuery("#patrolGroupName").val("");
		jQuery("#patrolGroupId").val("");
		jQuery("#workorderSignFor_signForRemark").val("");
		jQuery("#patrolGrouptr").show();
	}
	</script>
</html>