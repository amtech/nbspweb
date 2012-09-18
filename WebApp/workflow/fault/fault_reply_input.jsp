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
In.add('pickjs', {path : '${ctx}/js/jQuery/galleria/galleria-1.2.8.min.js',type : 'js',charset : 'utf-8'});
In.add('fault-common', {path : '${ctx}/workflow/fault/js/fault_common.js',type : 'js',charset : 'utf-8',rely : [ 'pickjs' ]});
	In.ready('vam', 'common', 'wdatejs','jquijs', 'fault-common', function() {
		setContextPath("${ctx}");
		jQuery("#faultReplyForm").validate({
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
		<form action="${ctx }/workflow/faultReplyAction!reply.action"
			id="faultReplyForm" name="replyForm" method="post">
			<div class="title_bg">
				<div id="title" class="title">
					当前位置-故障回单：${fault_dispatch.taskCode }
				</div>
			</div>
			<div class="tabcontent">
				<div id="detailInfoTd"></div>
				<table border="0" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<th style="background-color:#B8DDFC;width:20%;">
							<font color="#058CFE"><b>故障报告信息</b></font>
						</th>
						<th colspan="3" style="background-color:#B8DDFC;width:80%;"></th>
					</tr>
					<tr>
						<th style="width:20%;">
							故障设备：
						</th>
						<td style="width:30%;">
							<input name="faultReply.id" value="${faultReply.id }" type="hidden" />
							<input id="is_submited" name="faultReply.isSubmited" value="1" type="hidden" />
							<input name="businessType" value="${fault_alert.businessType }" type="hidden" />
							<input name="faultReply.workflowTaskId" value="${faultReply.workflowTaskId}" type="hidden" />
							<input name="faultReply.taskId" value="${fault_dispatch.id }" type="hidden" />
							<input type="text" id="faultReply_equipment" name="faultReply.equipment" value="${faultReply.equipment }" class="inputtext required" style="width: 220px" maxlength="40" />
							<span style="color: red">*</span>
						</td>
						<th style="width:20%;">
							是否集团业务：
						</th>
						<td style="width:30%;">
							<c:set var="checked_isgroupbusiness_no" value=""></c:set>
							<c:set var="checked_isgroupbusiness_yes" value="checked"></c:set>
							<c:if test="${faultReply.isGroupbusiness==1 }">
								<c:set var="checked_isgroupbusiness_no" value=""></c:set>
								<c:set var="checked_isgroupbusiness_yes" value="checked"></c:set>
							</c:if>
							<c:if test="${faultReply.isGroupbusiness==2 }">
								<c:set var="checked_isgroupbusiness_no" value="checked"></c:set>
								<c:set var="checked_isgroupbusiness_yes" value=""></c:set>
							</c:if>
							<input type="radio" id="faultReply_isGroupbusiness_y" name="faultReply.isGroupbusiness" value="1" ${checked_isgroupbusiness_yes } />
							是
							<input type="radio" id="faultReply_isGroupbusiness_n" name="faultReply.isGroupbusiness" value="2" ${checked_isgroupbusiness_no }/>
							否
						</td>
					</tr>
					<tr>
						<th style="width:20%;">
							业务影响：
						</th>
						<td style="width:30%;">
							<input type="text" id="faultReply_influenceBusiness" name="faultReply.influenceBusiness" value="${faultReply.influenceBusiness }" class="inputtext required" style="width: 220px" maxlength="40" />
							<span style="color: red">*</span>
						</td>
						<th style="width:20%;">
							网络分类：
						</th>
						<td style="width:30%;">
							<input type="text" id="faultReply_networkType" name="faultReply.networkType" value="${faultReply.networkType }" class="inputtext required" style="width: 220px" maxlength="40" />
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th style="width:20%;">
							故障处理结果：
						</th>
						<td style="width:30%;">
							<input type="text" id="faultReply_faultResult" name="faultReply.faultResult" value="${faultReply.faultResult }" class="inputtext required" style="width: 220px" maxlength="40" />
							<span style="color: red">*</span>
						</td>
						<th style="width:20%;">
							故障原因类别：
						</th>
						<td style="width:30%;">
							<input type="text" id="faultReply_faultReasonType" name="faultReply.faultReasonType" value="${faultReply.faultReasonType }" class="inputtext required" style="width: 220px" maxlength="40" />
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th style="width:20%;">
							处理措施：
						</th>
						<td colspan="3" style="width:80%;">
							<textarea id="faultReply_processMeasure" name="faultReply.processMeasure" class="inputtext required" style="width:740px;height: 80px;">${faultReply.processMeasure }</textarea>
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th style="width:20%;">
							是否最终方案：
						</th>
						<td style="width:30%;">
							<baseinfo:customselector name="faultReply.isSolution" data="${whetherMap }" id="faultReply_isSolution" cssClass="inputtext required" style="width: 220px" keyValue="${faultReply.isSolution }" isReversal="true"></baseinfo:customselector>
							<span style="color: red">*</span>
						</td>
						<th style="width:20%;">
							是否入案例库：
						</th>
						<td style="width:30%;">
							<baseinfo:customselector name="faultReply.isCase" data="${whetherMap }" id="faultReply_isCase" cssClass="inputtext required" style="width: 220px" keyValue="${faultReply.isCase }" isReversal="true"></baseinfo:customselector>
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th style="width:20%;">
							是否实施变更：
						</th>
						<td style="width:30%;">
							<baseinfo:customselector name="faultReply.isChange" data="${whetherMap }" id="faultReply_isChange" cssClass="inputtext required" style="width: 220px" keyValue="${faultReply.isChange }" isReversal="true"></baseinfo:customselector>
							<span style="color: red">*</span>
						</td>
						<th style="width:20%;">
							故障消除时间：
						</th>
						<td style="width:30%;">
							<c:if test="${fault_alert.findType=='B17' }">
								<fmt:formatDate value="${faultReply.faultClearTime }" pattern="yyyy-MM-dd HH:mm:ss" var="faultClearTime" />
								${faultClearTime }
								<input id="faultReply_faultClearTime" name="faultReply.faultClearTime" type="hidden" value="${faultClearTime }" />
							</c:if>
							<c:if test="${fault_alert.findType!='B17' }">
								<fmt:formatDate value="${faultReply.faultClearTime }" pattern="yyyy-MM-dd HH:mm:ss" var="faultClearTime" />
								<input id="faultReply_faultClearTime" style="width: 220px" name="faultReply.faultClearTime" value="${faultClearTime }" type="input" class="Wdate inputtext required" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d'})" />
								<span style="color: red">*</span>
							</c:if>
						</td>
					</tr>
					<tr>
						<th style="width:20%;">
							业务恢复时间：
						</th>
						<td style="width:30%;">
							<fmt:formatDate value="${faultReply.businessRenewTime }" pattern="yyyy-MM-dd HH:mm:ss" var="faultRenewTime" />
							<input id="faultReply_businessRenewTime" style="width: 220px" name="faultReply.businessRenewTime" value="${faultRenewTime }" type="input" class="Wdate inputtext required" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d'})" />
							<span style="color: red">*</span>
						</td>
						<th style="width:20%;">
							最终网络分类：
						</th>
						<td style="width:30%;">
							<input type="text" id="faultReply_incEndNettype" name="faultReply.incEndNettype" value="${faultReply.incEndNettype }" class="inputtext required" style="width: 220px" maxlength="40" />
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th style="width:20%;">
							最终设备厂商：
						</th>
						<td style="width:30%;">
							<input type="text" id="faultReply_incEndEquipment" name="faultReply.incEndEquipment" value="${faultReply.incEndEquipment }" class="inputtext required" style="width: 220px" maxlength="40" />
							<span style="color: red">*</span>
						</td>
						<th style="width:20%;">
							是否重大故障：
						</th>
						<td style="width:30%;">
							<baseinfo:customselector name="faultReply.incIsImportantIncident" data="${whetherMap }" id="faultReply_incIsImportantIncident" cssClass="inputtext required" style="width: 220px" keyValue="${faultReply.incIsImportantIncident }" isReversal="true"></baseinfo:customselector>
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th style="width:20%;">
							故障现象：
						</th>
						<td colspan="3" style="width:80%;">
							<textarea id="faultReply_phenomena" name="faultReply.phenomena" class="inputtext required" style="width:740px;height: 80px;">${faultReply.phenomena }</textarea>
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th style="width:20%;">
							故障原因：
						</th>
						<td colspan="3" style="width:80%;">
							<textarea id="faultReply_reason" name="faultReply.reason" class="inputtext required" style="width:740px;height: 80px;">${faultReply.reason }</textarea>
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th style="width:20%;">
							故障解决方案：
						</th>
						<td colspan="3" style="width:80%;">
							<textarea id="faultReply_solution" name="faultReply.solution" class="inputtext required" style="height: 80px;width:740px">${faultReply.solution }</textarea>
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th style="width:20%;">
							遗留问题：
						</th>
						<td colspan="3" style="width:80%;">
							<textarea name="faultReply.leaveBehindProblem" class="inputtext" style="width:740px;height: 80px;">${faultReply.leaveBehindProblem }</textarea>
						</td>
					</tr>
					<tr>
						<th style="width:20%;">
							提交审核人：
						</th>
						<td colspan="3">
							<input id="faultReply_approver_name" name="faultReply.approverName" value="${fault_dispatch.createrName }" class="inputtext required" style="width: 740px" readonly="readonly" />
							<a href="javascript:search('${LOGIN_USER.regionId }');"> <img border="0" src="${ctx}/css/image/personselect.png" /> </a>
							<span style="color: red">*</span>
							<input id="faultReply_approver" name="faultReply.approver" value="${fault_dispatch.creater }" type="hidden" />
						</td>
					</tr>
				</table>
				<div style="text-align: center; margin-top: 10px">
					<input type="submit" class="button" value="阶段处理" onclick="saveData();" />
					<input type="submit" onclick="submitData();" class="button" value="回复" id="submitBtn" />
					<input type="button" class="button" onclick="history.back()" value="返回" />
				</div>
			</div>
		</form>
	</body>
	<script type="text/javascript">
	function search(regionId) {
		var val = window.showModalDialog(
				'${ctx}/commonaccess!getuser.action?&orgtype=1&regionid='+regionId, '',
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
			jQuery("#faultReply_approver_name").val(userName);
			jQuery("#faultReply_approver").val(userId);
		}
	}
	function saveData() {
		jQuery("#is_submited").val("0");
		return true;
	}
	function submitData() {
		jQuery("#is_submited").val("1");
		return true;
	}
	</script>
</html>
