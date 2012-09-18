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
In.add('vm',{path:'${ctx}/js/jQuery/jvalidation/jquery.validate.min.js',type:'js',charset:'utf-8'});
In.add('vcn',{path:'${ctx}/js/jQuery/jvalidation/messages_cn.js',type:'js',charset:'utf-8',rely:['vm']});
In.add('vex',{path:'${ctx}/js/jQuery/jvalidation/jquery.validate.ex.js',type:'js',charset:'utf-8',rely:['vcn']});
In.add('vam',{path:'${ctx}/js/jQuery/jvalidation/additional-methods.min.js',type:'js',charset:'utf-8',rely:['vex']});
In.add('common',{path:'${ctx}/common/js/common.js',type:'js',charset:'utf-8'});
	In.ready('vam','common',function(){
		jQuery("#inputForm").validate({
			focusInvalid : false,
			submitHandler : function(form) {
				if (checkData()) {
					form.submit();
				}
			}
		});
	});
</script>
	</head>
	<body>
		<form action="${ctx }/wplan/noPatrolStationAction!save.action"
			id="inputForm" name="inputForm" method="post">
			<div class="title_bg">
				<div id="title" class="title">
					当前位置-拆分纠纷站点登记
				</div>
			</div>
			<div class="tabcontent">
				<table border="0" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<th>专业：</th>
						<td>
							<baseinfo:customselector name="noPatrolStation.resourceType" data="${businessTypeMap }" isReversal="true" cssClass="inputtext required" style="width:220px;" id="resourceType"></baseinfo:customselector>
							<span style="color: red">*</span>
						</td>
						<th>问题类型：</th>
						<td>
							<baseinfo:dicselector name="noPatrolStation.problemType" columntype="NOPATROLSTATION_PROBLEM_TYPE" type="select" cssClass="inputtext required" style="width:220px;" id="problemType"></baseinfo:dicselector>
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th></th>
						<td colspan="3" style="width: 85%">
							<a href="javascript:selectPlanStation();">选择计划和站点</a>
						</td>
					</tr>
					<tr>
						<th>计划名称：</th>
						<td>
							<input id="planId" name="noPatrolStation.planId" value="" type="hidden" />
							<span id="planName"></span>
						</td>
						<th>站点名称：</th>
						<td>
							<input id="resourceId" name="noPatrolStation.resourceId" value="" type="hidden" />
							<span id="resourceName"></span>
						</td>
					</tr>
					<tr>
						<th>问题描述：</th>
						<td colspan="3">
							<textarea id="cause" name="noPatrolStation.cause"
								class="inputtext required" style="height: 240px;width: 80%"></textarea>
							<span style="color: red">*</span>
						</td>
					</tr>
				</table>
				<div style="text-align: center; margin-top: 10px">
					<input name="btnSave" value="保存" type="button" class="button"
						onclick="submitData();" />
					<input name="btnReset" value="重置" type="button" class="button"
						onclick="resetData();" />
					<!-- 
					<input name="btnReturn" value="返回" type="button" class="button"
						onclick="history.go(-1);" />
					 -->
				</div>
			</div>
		</form>
	</body>
	<script type="text/javascript">
	function submitData() {
		jQuery("#inputForm").submit();
	}
	function resetData(){
		document.forms["inputForm"].reset();
		jQuery("#planName").html("");
		jQuery("#resourceName").html("");
	}
	function checkData(){
		var planId=jQuery("#planId").val();
		var resId=jQuery("#resourceId").val();
		if(planId==""||resId==""){
			alert("没有选择计划和站点信息！");
			return false;
		}
		return true;
	}
	function selectPlanStation() {
		var businessType=jQuery("#resourceType").val();
		var url="${ctx}/wplan/noPatrolStationAction!selectSite.action?resourceType="+businessType;
		var val = window.showModalDialog(url, '',
			'status:no;center:yes;dialogWidth:800px;dialogHeight:600px;');
		if (val) {
			jQuery("#planId").val(val[0].PLAN_ID);
			jQuery("#resourceId").val(val[0].RESOURCE_ID);
			jQuery("#planName").html(val[0].PLAN_NAME);
			jQuery("#resourceName").html(val[0].ZYMC);
		}
	}
	</script>
</html>