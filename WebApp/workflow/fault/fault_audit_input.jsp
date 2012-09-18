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
In.add('pickjs', {path : '${ctx}/jQuery/galleria/galleria-1.2.8.min.js',type : 'js',charset : 'utf-8'});
In.add('fault-common', {path : '${ctx}/workflow/fault/js/fault_common.js',type : 'js',charset : 'utf-8',rely : [ 'pickjs' ]});
	In.ready('vam', 'common', 'wdatejs','jquijs', 'fault-common', function() {
		setContextPath("${ctx}");
		jQuery("#faultDispatchForm").validate({
			focusInvalid : false
		});
		if ('${fault_alert.findType}' == 'B17') {
			if ('${faultReply.faultClearTime}' == '') {
				jQuery("#submitBtn").hide();
			} else {
				jQuery("#submitBtn").show();
			}
		}
		var url='${ctx}/workflow/faultDispatchAction!view.action?parameter.id=${fault_dispatch.id}&businessType=${fault_alert.businessType}';
		jQuery("#detailInfoTd").load(url+" #detailTd",function(){
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
		});
	});
</script>
	</head>
	<body>
		<form action="${ctx }/workflow/faultAuditAction!audit.action"
			id="faultDispatchForm" name="faultDispatchForm" method="post">
			<div class="title_bg">
				<div id="title" class="title">
					当前位置-故障回单审核：${fault_dispatch.taskCode }
				</div>
			</div>
			<div class="tabcontent">
				<div id="detailInfoTd"></div>
				<table border="0" align="center" cellpadding="3" cellspacing="0">
					<input name="faultAudit.workflowTaskId" value="${faultAudit.workflowTaskId}" type="hidden" />
					<input name="businessType" value="${fault_alert.businessType }" type="hidden" />
					<input name="faultAudit.taskId" value="${fault_dispatch.id }" type="hidden" />
					<tr>
						<th style="background-color:#B8DDFC;width:20%;">
							<font color="#058CFE"><b>故障回单审核信息</b></font>
						</th>
						<th colspan="3" style="background-color:#B8DDFC;width:80%;">
						</th>
					</tr>
					<tr>
						<th style="width:20%;">
							审核结果：
						</th>
						<td colspan="3" style="width:80%;">
							<input type="radio" id="faultAudit_isAuditing" name="faultAudit.isAuditing" onclick="changeApproveResult(this.value);" value="pass" checked />
							通过
							<input type="radio" id="faultAudit_isAuditing" name="faultAudit.isAuditing" onclick="changeApproveResult(this.value);" value="reject" />
							不通过
							<input type="radio" id="faultAudit_isAuditing" name="faultAudit.isAuditing" onclick="changeApproveResult(this.value);" value="transfer" />
							转审
						</td>
					</tr>
					<tr id="transferApproverTr" style="display: none;">
						<th style="width:20%;">
							转审人：
						</th>
						<td colspan="3" style="width:80%;">
							<input id="faultAudit_transferApproverName" name="transferApproverName" class="inputtext required" style="width: 740px" readonly="readonly" />
							<a href="javascript:searchApprover('${LOGIN_USER.regionId }');"> <img border="0" src="${ctx}/css/image/personselect.png" /> </a>
							<span style="color: red">*</span>
							<input id="faultAudit_transferApprover" name="faultAudit.transferApprover" type="hidden" />
						</td>
					</tr>
					<tr>
						<th style="width:20%;">
							审核备注：
						</th>
						<td colspan="3" style="width:80%;">
							<textarea name="faultAudit.remark" class="inputtext" style="width: 740px; height: 80px;"></textarea>
						</td>
					</tr>
				</table>
				<div style="text-align: center; margin-top: 10px">
					<input type="submit" class="button" value="审核 ">
					<input type="button" class="button" onclick="history.back()" value="返回">
				</div>
			</div>
		</form>
	</body>
	<script type="text/javascript">
	function searchApprover(regionId) {
		var val = window.showModalDialog(
				'${ctx}/commonaccess!getstaff.action?orgtype=1&flag=radio&regionid='+regionId, '',
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
			jQuery("#faultAudit_transferApproverName").val(userName);
			jQuery("#faultAudit_transferApprover").val(userId);
		}
	}
	function changeApproveResult(value) {
		if (value == "transfer") {
			jQuery("#transferApproverTr").attr("style", "display:");
		} else {
			jQuery("#transferApproverTr").attr("style", "display:none");
		}
	}
	function checkData() {
		return true;
	}
	</script>
</html>
