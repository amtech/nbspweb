<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>资源分配确认</title>
<%@ include file="/common/meta.jsp"%>
<link href="${ctx}/css/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/js/in-min.js"
	autoload="true"
	core="${config.cdnurl}/cabletech/ui/js/jquery-1.7.2.min.js"></script>
<script>
In.add('vm', {path : '${ctx}/js/jQuery/jvalidation/jquery.validate.min.js',type : 'js',charset : 'utf-8'});
In.add('vcn', {path : '${ctx}/js/jQuery/jvalidation/messages_cn.js',type : 'js',charset : 'utf-8',rely : [ 'vm' ]});
In.add('vex', {path : '${ctx}/js/jQuery/jvalidation/jquery.validate.ex.js',type : 'js',charset : 'utf-8',rely : [ 'vcn' ]});
In.add('vam', {path : '${ctx}/js/jQuery/jvalidation/additional-methods.min.js',type : 'js',charset : 'utf-8',rely : [ 'vex' ]});
In.add('jqui',{path:'${ctx}/js/jQuery/jqueryui/css/redmond/jquery-ui.custom.css'});
In.add('jquijs',{path:'${ctx}/js/jQuery/jqueryui/jquery-ui.custom.min.js',type:'js',charset:'utf-8',rely:['jqui']});
In.add('jresize',{path:'${ctx}/js/jQuery/jresize/jquery.wresize.js',type:'js',charset:'utf-8',rely:['jquijs']});
In.add('jgcss',{path:'${ctx}/js/jQuery/jqgrid/css/ui.jqgrid.css',charset:'utf-8',rely:['jresize']});
In.add('jgjs',{path:'${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js',type:'js',charset:'utf-8',rely:['jgcss']});
In.add('jgcn',{path:'${ctx}/js/jQuery/jqgrid/grid.locale-cn.js',type:'js',charset:'utf-8',rely:['jgjs']});
In.add('common',{path:'${ctx}/common/js/common.js',type:'js',charset:'utf-8'});
	In.ready('common', 'jgcn', 'vam', function() {
		setContextPath('${ctx}');
		var jqgrid = jQuery("#resgrid")
				.jqGrid(
						{
							url : "${ctx}/resource/resourceAllotAction!confirmListData.action",
							datatype : "json",mtype : 'GET',rownumbers : true,
							colNames : [ '资源名称', '资源类型', '资源编号', '原维护单位','原维护组' ],
							colModel : [ 
							{name : 'ZYMC',id : 'ZYMC',sortable : false,formatter : nameFormatter}, 
							{name : 'TYPE',id : 'TYPE',sortable : false,hidden:true}, 
							{name : 'ZDBH',id : 'ZDBH',sortable : false}, 
							{name : 'ORG_NAME',id : 'ORG_NAME',sortable : false}, 
							{name : 'PATROLGROUP_NAME',id : 'PATROLGROUP_NAME',sortable : false}
							],
							jsonReader: {
            					root:"root" , 
            					repeatitems: false
            				},
            				rowNum:1000,autowidth : true,shrinkToFit : true,viewrecords : true,hidegrid : false
						}).navGrid('#resgrid',{edit:false ,add:false ,del:false,search:false,sortable:false  });
		jQuery(window).wresize(function() {
			grid_resize(jqgrid);
		});
		grid_resize(jqgrid);
		jQuery("#resourceAllotForm").validate({
			focusInvalid : false,
			submitHandler : function(form) {
				if (checkData()) {
					form.submit();
				}
			}
		});
	});
	function nameFormatter(cellValue, options, rowObjec){
		var view = "";
		view += "<input name='parameter.newResources' value='"+rowObjec.ID+"' type='hidden' />";
		view += cellValue;
		return view;
	}
</script>
</head>
<body>
	<form
		action="${ctx}/resource/resourceAllotAction!${parameter.actionType }.action"
		name="resourceAllotForm" method="post" id="resourceAllotForm">
		<input name="parameter.oldMaintenceId" id="oldMaintenceId"
			value="${parameter.oldMaintenceId }" type="hidden" /> <input
			name="parameter.oldPatrolmanId" id="oldPatrolmanId"
			value="${parameter.oldPatrolmanId }" type="hidden" /> <input
			name="parameter.resourceType" id="resourceType"
			value="${parameter.resourceType }" type="hidden" /> <input
			name="parameter.regionId" id="regionId"
			value="${parameter.regionId }" type="hidden" /> <input
			name="parameter.isManyMaintenanced" type="hidden"
			id="isManyMaintenanced" value="${parameter.isManyMaintenanced }" />
		<div class="ui-layout-north">
			<div class="title_bg">
				<div id="title" class="title">当前位置-资源分配确认</div>
			</div>
			<div class="framecontentBottom">
				<table cellspacing="0" cellpadding="0" border="0" align="center">
					<c:if test="${LOGIN_USER.orgType=='1' }">
						<tr>
							<th>专业类型：</th>
							<td>
								<baseinfo:dicselector name="" columntype="businesstype" type="view" keyValue="${parameter.resourceType }"></baseinfo:dicselector>
							</td>
							<th>是否多家维护：</th>
							<td>
								<c:set var="isManyMaintenanced" value="${parameter.isManyMaintenanced }"></c:set>
								${whetherMap[isManyMaintenanced] }
							</td>
						</tr>
						<tr>
							<th>区域：</th>
							<td>
								<baseinfo:region displayProperty="regionname" id="${parameter.regionId }"></baseinfo:region>
							</td>
							<th>原代维公司：</th>
							<td>
								<baseinfo:org displayProperty="organizename" id="${parameter.oldMaintenceId}"></baseinfo:org>
							</td>
						</tr>
					</c:if>
					<c:if test="${LOGIN_USER.orgType=='2' }">
						<tr>
							<th>专业类型：</th>
							<td>
								<baseinfo:dicselector name="" columntype="businesstype" type="view" keyValue="${parameter.resourceType }"></baseinfo:dicselector>
							</td>
							<th>维护组：</th>
							<td>
								<baseinfo:patrolman displayProperty="patrolname" id="${parameter.oldPatrolmanId }"></baseinfo:patrolman>
							</td>
						</tr>
					</c:if>
					<c:if test="${parameter.actionType=='allot' }">
						<tr>
							<th>分配到：</th>
							<td colspan="3">
								<c:if test="${LOGIN_USER.orgType=='1' }">
									<input id="newContractorName" name="newContractorName" value="" class="inputtext required" style="width: 200px;" type="text" />
									<a href="javascript:searchContractor();"> <img border="0" src="${ctx}/css/images/selectcode.gif" /> </a>
									<input type="hidden" name="parameter.newMaintenceId" id="newMaintenceId" value="" />
									<input type="hidden" name="parameter.newPatrolmanId" id="newPatrolmanId" value="" />
									<input id="newPatrolManName" name="newPatrolManName" value="" type="hidden" />
								</c:if> 
								<c:if test="${LOGIN_USER.orgType=='2' }">
									<input id="newContractorName" name="newContractorName" value="${LOGIN_USER.orgName }" type="hidden" />
									<input type="hidden" name="parameter.newMaintenceId" id="newMaintenceId" value="${LOGIN_USER.orgId }" />
									<input id="newPatrolManName" name="newPatrolManName" value="" class="inputtext required" style="width: 200px;" type="text" />
									<a href="javascript:searchPatrolgroup();"> <img border="0" src="${ctx}/css/images/selectcode.gif" /> </a>
									<input type="hidden" name="parameter.newPatrolmanId" id="newPatrolmanId" value="" />
								</c:if>
							</td>
						</tr>
					</c:if>
					<c:if test="${parameter.actionType=='recycle' }">
						<c:if test="${LOGIN_USER.orgType=='1' }">
							<input type="hidden" name="parameter.newMaintenceId" id="newMaintenceId" value="" />
							<input type="hidden" name="parameter.newPatrolmanId" id="newPatrolmanId" value="" />
						</c:if>
						<c:if test="${LOGIN_USER.orgType=='2' }">
							<input type="hidden" name="parameter.newMaintenceId" id="newMaintenceId" value="${LOGIN_USER.orgId }" />
							<input type="hidden" name="parameter.newPatrolmanId" id="newPatrolmanId" value="" />
						</c:if>
					</c:if>
					<tr>
						<td colspan="4" style="text-align: center; border: 0px;">
							<c:set var="actionTypeName" value=""></c:set> 
							<c:if test="${parameter.actionType=='allot' }">
								<c:set var="actionTypeName" value="分 配"></c:set>
							</c:if> 
							<c:if test="${parameter.actionType=='recycle' }">
								<c:set var="actionTypeName" value="回 收"></c:set>
							</c:if> 
							<input type="submit" class="button" value="${actionTypeName }">
							<input type="button" class="button" value="重新选择" onclick="goBack();"></td>
					</tr>
				</table>
			</div>
		</div>
		<div id="content" align="center">
			<table id="resgrid"></table>
		</div>
	</form>
</body>
<script type="text/javascript">
/**
 * 搜索代维公司树
 */
function searchContractor() {
	var businessType = jQuery("#resourceType").val();
	var regionId = jQuery("#regionId").val();
	var url = contextPath + '/commonaccess!getorg.action?orgtype=2';
	if (businessType == '') {
		alert("请选择专业类型！");
		return;
	}
	url += "&businesstype=" + businessType;
	var val = showOrg(url);
	if (!!val) {
		jQuery("#newMaintenceId").val(val[0]);
		jQuery("#newContractorName").val(val[1]);
	}
}
/**
 * 搜索巡检组树
 */
function searchPatrolgroup() {
	var orgId = jQuery("#newMaintenceId").val();
	var url = contextPath + '/commonaccess!getpatrolgroup.action?';
	if (orgId != '') {
		url += "&orgid=" + orgId;
	}
	var val = showPatrolGroup(url);
	if (!!val) {
		jQuery("#newPatrolmanId").val(val[0]);
		jQuery("#newPatrolManName").val(val[1]);
	}
}
	function checkData() {
		var oldId = "";
		var newId = "";
		var orgName = "<baseinfo:org displayProperty='organizename' id='${parameter.oldMaintenceId }'></baseinfo:org>";
		var patrolgroupName = "<baseinfo:patrolman displayProperty='patrolname' id='${parameter.oldPatrolmanId }'></baseinfo:patrolman>";
		var newOrgName = jQuery("#newContractorName").val();
		var newPatrolgroupName = jQuery("#newPatrolManName").val();
		if ("${LOGIN_USER.orgType}" == "1") {
			oldId = jQuery("#oldMaintenceId").val();
			newId = jQuery("#newMaintenceId").val();
		}
		if ("${LOGIN_USER.orgType}" == "2") {
			oldId = jQuery("#oldPatrolmanId").val();
			newId = jQuery("#newPatrolmanId").val();
		}
		if (isAllotedToSelf(oldId,newId)) {
			return false;
		}
		var message = "";
		if ("${parameter.actionType}" == "allot") {
			message="你确定将" + orgName + patrolgroupName + "维护的资源分配给" + newOrgName + newPatrolgroupName + "吗？";
		}
		if ("${parameter.actionType}" == "recycle") {
			message="你确定将" + orgName + patrolgroupName + "维护的资源回收吗？";
		}
		if (confirm(message)) {
			return true;
		}
		return false;
	}
	function isAllotedToSelf(oldId,newId){
		var flag=false;
		if(oldId!=newId){
			return flag;
		}
		var actionUrl="${ctx}/resource/resourceAllotAction!isAllotedToSelf.action?parameter.resourceType=${parameter.resourceType}";
		actionUrl+="&parameter.newMaintenceId="+jQuery("#newMaintenceId").val()+"&parameter.newPatrolmanId="+jQuery("#newPatrolmanId").val()+"&";
		actionUrl+=jQuery("input[name='parameter.newResources']").serialize();
		jQuery.ajax({url:actionUrl,async:false,success:function(data){
			var newOrgName = jQuery("#newContractorName").val();
			var newPatrolgroupName = jQuery("#newPatrolManName").val();
			var businessTypeName='<baseinfo:dicselector name="" columntype="businesstype" type="view" keyValue="${parameter.resourceType }"></baseinfo:dicselector>';
			if(data!=""){
				alert(data+"资源已经被分配到" + newOrgName + newPatrolgroupName + "的"+businessTypeName+"专业中！");
				flag=true;
			}
		}});
		return flag;
	}
	function goBack() {
		location = "${ctx}/resource/resourceAllotAction!input.action";
	}
</script>
</html>