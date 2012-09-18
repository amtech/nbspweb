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
<script  type="text/javascript">
In.add('jqui',{path:'${ctx}/js/jQuery/jqueryui/css/redmond/jquery-ui.custom.css'});
In.add('jquijs',{path:'${ctx}/js/jQuery/jqueryui/jquery-ui.custom.min.js',type:'js',charset:'utf-8',rely:['jqui']});
In.add('common',{path:'${ctx}/common/js/common.js',type:'js',charset:'utf-8'});
In.add('selectjs',{path:'${ctx}/js/selected_options.js',type:'js',charset:'utf-8'});
In.ready('jquijs', 'common', 'selectjs', function(){
	setContextPath('${ctx}');
});
</script>
</head>
<body id="bodydiv">
	<form action="${ctx }/resource/resourceAllotAction!confirm.action"
		name="resourceAllotForm" id="resourceAllotForm" method="post">
		<div class="title_bg">
			<div id="title" class="title">当前位置-分配回收资源</div>
		</div>
		<div class="tabcontent">
			<table border="0" align="center" cellpadding="3" cellspacing="0">
				<c:if test="${LOGIN_USER.orgType=='1' }">
					<tr>
						<th>专业类型：</th>
						<td>
							<baseinfo:customselector id="resourceAllot_resourceType" name="parameter.resourceType" data="${businessTypeMap }" cssClass="inputtext required" style="width:150px;" isReversal="true"></baseinfo:customselector>
						</td>
						<th>是否多家维护：</th>
						<td>
							<baseinfo:customselector name="parameter.isManyMaintenanced" id="resourceAllot_isManyMaintenanced" data="${whetherMap }" cssClass="inputtext required" style="width:150px;" isReversal="true"></baseinfo:customselector>
						</td>
					</tr>
					<tr>
						<th>区域：</th>
						<td>
							<input id="regionname" name="regionname" class="inputtext" readonly="readonly" style="width: 150px;" /> 
							<a href="javascript:searchRegion();"> <img border="0" src="${ctx}/css/images/selectcode.gif" /> </a> 
							<input id="resourceAllot_regionId" name="parameter.regionId" type="hidden" />
						</td>
						<th>代维公司：</th>
						<td>
							<input id="oldContractorName" name="oldContractorName" value="未分配代维公司" class="inputtext" style="width: 150px;" type="text" /> <a href="javascript:searchContractor();"> 
							<img border="0" src="${ctx}/css/images/selectcode.gif" /> </a> 
							<input type="hidden" name="parameter.oldMaintenceId" id="resourceAllot_oldMaintenceId" value="-1" /> 
							<input type="hidden" name="parameter.oldPatrolmanId" id="resourceAllot_oldPatrolmanId" value="" />
						</td>
					</tr>
					<tr>
						<th>资源名称：</th>
						<td colspan="3">
							<input name="parameter.resourceName" id="resourceName" type="text" class="inputtext" style="width: 150px;" /> （输入资源名称进行模糊查询）
						</td>
					</tr>
				</c:if>
				<c:if test="${LOGIN_USER.orgType=='2' }">
					<tr>
						<th>专业类型：</th>
						<td>
							<input name="parameter.isManyMaintenanced" value="" type="hidden" id="resourceAllot_isManyMaintenanced" /> 
							<baseinfo:customselector id="resourceAllot_resourceType" name="parameter.resourceType" data="${businessTypeMap }" cssClass="inputtext required" style="width:150px;" isReversal="true"></baseinfo:customselector>
						</td>
						<th>资源名称：</th>
						<td>
							<input name="parameter.resourceName" id="resourceName" type="text" class="inputtext" style="width: 150px;" /> （输入资源名称进行模糊查询）
						</td>
					</tr>
					<tr>
						<th>代维公司：</th>
						<td>
							<input id="resourceAllot_regionId" name="parameter.regionid" type="hidden" value="${LOGIN_USER.regionId }" /> 
							<input id="oldContractorName" name="oldContractorName" value="${LOGIN_USER.orgName }" class="inputtext" style="width: 150px;" type="text" /> 
							<a href="javascript:searchContractor();"> <img border="0" src="${ctx}/css/images/selectcode.gif" /> </a> 
							<input type="hidden" name="parameter.oldMaintenceId" id="resourceAllot_oldMaintenceId" value="${LOGIN_USER.orgId }" />
						</td>
						<th>维护组：</th>
						<td>
							<input id="oldPatrolManName" name="oldPatrolManName" value="未分配维护组" class="inputtext" style="width: 150px;" type="text" /> 
							<a href="javascript:searchPatrolgroup();"><img border="0" src="${ctx}/css/images/selectcode.gif" /> </a> 
							<input type="hidden" name="parameter.oldPatrolmanId" id="resourceAllot_oldPatrolmanId" value="-1" />
						</td>
					</tr>
				</c:if>
				<tr>
					<td colspan="4" style="text-align: center;">
						<input id="resourceAllot_actionType" name="parameter.actionType" value="" type="hidden" /> 
						<input id="btnQuery" name="btnQuery" value="查询" onclick="queryData();" class="button" type="button" /> 
						<input id="btnAllot" name="btnAllot" value="分配" onclick="allotData();" class="button" type="button" /> 
						<input id="btnRecycle" name="btnRecycle" value="回收" onclick="recycleData();" class="button" type="button" /> 
						<input id="btnReset" name="btnReset" value="重填" class="button" type="reset" />
					</td>
				</tr>
				<tr>
					<th>选择资源：</th>
					<th colspan="3"></th>
				</tr>
				<tr>
					<td colspan="4">
						<table width="95%">
							<tr>
								<td style="text-align: center; width: 40%;">
									<select name="oldResources" id="oldResources" style="width: 270px; height: 150px;" multiple="multiple" size="10">
									</select>
								</td>
								<td style="text-align: center; width: 20%;">
									<p>
										<input name="" value="选  择" type="button" class="button" onclick="moveSelected(document.getElementById('oldResources'),document.getElementById('newResources'))" />
									</p>
									<p>
										<input name="" value="删  除" type="button" class="button" onclick="moveSelected(document.getElementById('newResources'),document.getElementById('oldResources'))" />
									</p>
									<p>
										<input name="" value="全部选择" type="button" class="button" onclick="moveAll(document.getElementById('oldResources'),document.getElementById('newResources'));" />
									</p>
									<p>
										<input name="" value="全部删除" type="button" class="button" onclick="moveAll(document.getElementById('newResources'),document.getElementById('oldResources'))" />
									</p>
								</td>
								<td style="text-align: center; width: 40%;">
									<select name="parameter.newResources" id="newResources" style="width: 270px; height: 150px;" multiple="multiple" size="10">
									</select>
								</td>
							</tr>
						</table></td>
				</tr>
			</table>
		</div>
		<div id='loadingdiv' style='display:none;width: 100%; height:100%;margin-top:150px;text-align:center;'><img src='${ctx }/css/images/loading.gif'><br>&nbsp;&nbsp;&nbsp;&nbsp;加载中,请稍后  ......</div>
	</form>
</body>
<script type="text/javascript">
/**
 * 搜索区域树
 */
function searchRegion() {
	var val = showRegion(contextPath + '/commonaccess!getregion.action?');
	if (!!val) {
		jQuery("#resourceAllot_regionId").val(val[0]);
		jQuery("#regionname").val(val[1]);
	}
}
/**
 * 搜索代维公司树
 */
function searchContractor() {
	var businessType = jQuery("#resourceAllot_resourceType").val();
	var regionId = jQuery("#resourceAllot_regionId").val();
	var url = contextPath + '/commonaccess!getorg.action?orgtype=2';
	if (businessType == '') {
		alert("请选择专业类型！");
		return;
	}
	url += "&businesstype=" + businessType;
	if (regionId != '') {
		url += "&regionid=" + regionId;
	}
	var val = showOrg(url);
	if (!!val) {
		jQuery("#resourceAllot_oldMaintenceId").val(val[0]);
		jQuery("#oldContractorName").val(val[1]);
	}
}
/**
 * 搜索巡检组树
 */
function searchPatrolgroup() {
	var orgId = jQuery("#resourceAllot_oldMaintenceId").val();
	var url = contextPath + '/commonaccess!getpatrolgroup.action?';
	if (orgId != '') {
		url += "&orgid=" + orgId;
	}
	var val = showPatrolGroup(url);
	if (!!val) {
		jQuery("#resourceAllot_oldPatrolmanId").val(val[0]);
		jQuery("#oldPatrolManName").val(val[1]);
	}
}
	/**
	 * 查询站点数据
	 */
	function queryData() {
		var regionId = jQuery('#resourceAllot_regionId').val();
		var contractorId = jQuery('#resourceAllot_oldMaintenceId').val();
		var patrolmanId = jQuery('#resourceAllot_oldPatrolmanId').val();
		var resourceType = jQuery('#resourceAllot_resourceType').val();
		var resourceName = jQuery('#resourceName').val();
		if (resourceType == "") {
			alert("请选择专业类型！");
			return;
		}
		var actionUrl = "${ctx}/resource/resourceAllotAction!getResForSelect.action?";
		actionUrl += "parameter.oldMaintenceId=" + contractorId;
		actionUrl += "&&parameter.oldPatrolmanId=" + patrolmanId;
		actionUrl += "&&parameter.resourceName=" + encodeURI(resourceName);
		actionUrl += "&&parameter.resourceType=" + resourceType;
		actionUrl += "&&parameter.regionId=" + regionId;
		actionUrl += "&&rnd=" + Math.random();
		jQuery.ajax({
			url : encodeURI(actionUrl),
			beforeSend:function(result){
				jQuery("#loadingdiv").show();
			},
			success : function(result) {
				var str = result;
				document.getElementById("oldResources").options.length = 0;
				if (!!str) {
					for ( var i = 0; i < str.length; i++) {
							var option ="<option value='"+str[i].ID+"'>"+str[i].NAME+"</option>"; 
							jQuery("#oldResources").append(option);
					}
				}else{
					alert("没有查询到任何资源！");
				}
				jQuery("#loadingdiv").hide();
			},
			error : function() {
				alert("获取资源未知异常！");
			}
		});
	}
	/**
	 * 进行资源分配
	 */
	function allotData() {
		if (checkData()) {
			jQuery("#resourceAllot_actionType").val("allot");
			jQuery("#resourceAllotForm").submit();
		}
	}
	/**
	 * 进行资源回收
	 */
	function recycleData() {
		if (checkData()) {
			jQuery("#resourceAllot_actionType").val("recycle");
			jQuery("#resourceAllotForm").submit();
		}
	}
	/**
	 * 进行表单验证
	 */
	function checkData() {
		var target = document.getElementById("newResources");
		if (target.options.length == 0) {
			alert("请选择要分配或者回收的资源！");
			return false;
		}
		if (target.options.length > 1000) {
			alert("用户不能一次分配或者回收超过1000个资源！");
			return false;
		}
		for ( var i = 0; i < target.options.length; i++) {
			target.options[i].selected = true;
		}
		return true;
	}
</script>
</html>