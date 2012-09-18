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
<script type="text/javascript">
In.add('ztreecss',{path:'${config.cdnurl}/cabletech/ui/ztree/css/zTreeStyle/zTreeStyle.css'});
In.add('ztreejs',{path:'${config.cdnurl}/cabletech/ui/ztree/jquery.ztree.all.min.js',type:'js',charset:'utf-8',rely:['ztreecss']});
In.add('jqui',{path:'${ctx}/js/jQuery/jqueryui/css/redmond/jquery-ui.custom.css'});
In.add('jquijs',{path:'${ctx}/js/jQuery/jqueryui/jquery-ui.custom.min.js',type:'js',charset:'utf-8',rely:['jqui']});
In.add('vm', {path : '${ctx}/js/jQuery/jvalidation/jquery.validate.min.js',type : 'js',charset : 'utf-8'});
In.add('vcn', {path : '${ctx}/js/jQuery/jvalidation/messages_cn.js',type : 'js',charset : 'utf-8',rely : [ 'vm' ]});
In.add('vex', {path : '${ctx}/js/jQuery/jvalidation/jquery.validate.ex.js',type : 'js',charset : 'utf-8',rely : [ 'vcn' ]});
In.add('vam', {path : '${ctx}/js/jQuery/jvalidation/additional-methods.min.js',type : 'js',charset : 'utf-8',rely : [ 'vex' ]});
In.add('common', {path : '${ctx}/common/js/common.js',type : 'js',charset : 'utf-8'});
In.add('wdatejs', {path : '${ctx}/js/WdatePicker/WdatePicker.js',type : 'js',charset : 'utf-8'});
In.add('patrolinfo_common', {path : '${ctx}/wplan/plan/js/patrolinfo_common.js',type : 'js',charset : 'utf-8'});
In.add('patrolinfo_input', {path : '${ctx}/wplan/plan/js/patrolinfo_input.js',type : 'js',charset : 'utf-8',rely : [ 'patrolinfo_common' ]});

//简单树样式	
var setting = {
				view: {
						dblClickExpand: false,
						showLine: false,
						fontCss: getFontCss,
						showIcon: true,
						chkDisabled:true
						},
						data: {
								simpleData: {
								enable: true,
								idKey:"RS_ID",
								pIdKey:"RS_TYPE",
								rootPId:"root"	
							},
							key: {
								title: "RS_NAME",
								name: "RS_NAME"
								
							}
						},
						check: {
							enable: true
						}
					};

In.ready('vam', 'common', 'wdatejs','jquijs', 'patrolinfo_input','ztreejs', function() {
	setContextPath("${ctx}");
	jQuery("#patrolinfoForm").validate({
		focusInvalid : false,
		submitHandler : function(form) {
			if (setResource()) {
				form.submit();
			}
		}
	});
	key = jQuery("#search_resources");
	key.bind("focus", focusKey)
	.bind("blur", blurKey);
	var  groupId='${patrolinfo.patrolgroupid }';
	if(groupId){
		getResource(groupId);
	}
});
//设置查询到的资源字体颜色
function getFontCss(treeId, treeNode) {
	return (!!treeNode.highlight) ? {
		color : "#A60000",
		"font-weight" : "bold"
	} : {
		color : "#333",
		"font-weight" : "normal"
	};
}

// 获取审核人
function searchAcceptUser() {
	var val = showOrgPerson('${ctx}/commonaccess!getstaff.action?orgtype=1&flag=radio');
	if (val) {
		jQuery("#patrolinfo_acceptUserid").val(val[0]);
		jQuery("#patrolinfo_acceptUsername").val(val[1]);
		
	}
}
//根据巡检组设置巡检资源
function getResource(groupId){
	//获取巡检资源
	jQuery.ajax({
			url : '${ctx}/wplan/patrolresourceAction!getpatrolresourcetree.action',
			dataType : "json",
			data:{
					patrolgroup_id:groupId,
					businesstype:'${patrolinfo.businesstype}'
				},
			type:'GET',
			cache:true,
			async:false,
			success : function(result) {
			  var ztree=jQuery.fn.zTree.init(jQuery("#patrolresourctree"),setting,result);
			  ztree.checkAllNodes(false);
			  var patrolResourceJSONStr='${patrolResourceListJSON}';
				if(patrolResourceJSONStr){
					var patrolResourceJSON=jQuery.parseJSON(patrolResourceJSONStr);
					for(var i=0;i<patrolResourceJSON.length;i++)
						{
						 var node=ztree.getNodeByParam("RS_ID",patrolResourceJSON[i].RESOURCE_ID);
						 ztree.checkNode(node,true,true,false);
						}
				}
			  document.getElementById("ztreeControler").style.display="block";
			},
			error : function() {
				alert("获取巡检组所属资源失败！");
			}
		});
}
//提交数据
function submitData() {
	jQuery("#is_submited").val("1");
	return true;
}
//设置资源
function setResource(){
	//获取选中节点
	var rds = "";//资源字符ID
    var rtype="";//资源类型
	var zTree = jQuery.fn.zTree.getZTreeObj("patrolresourctree");
    if(zTree){
	var checknodes = zTree.getCheckedNodes(true);
	if(checknodes){
		for(var i=0;i<checknodes.length;i++){
			if(checknodes[i].RS_TYPE!=="root"){
				rds +=","+checknodes[i].RS_ID;
				rtype+=","+checknodes[i].RS_TYPE;
			}
		}
		if (rds != "") {
			rds = rds.substring(1, rds.length);
			rtype=rtype.substring(1, rtype.length);
		}
		if(rds.length===0){
			alert("请选择巡检站点！");
			return false;
			}
		else{
			jQuery("#patrolinfo_resourceids").val(rds);
			jQuery("#patrolinfo_resourcetypes").val(rtype);
			return true;
		}
		
	}else{
		alert("请选择巡检站点！");
		return false;
	}
    }
}
//全选全不选
function CheckAll(){
	var treeobj = jQuery.fn.zTree.getZTreeObj("patrolresourctree");
	var selectedobj = treeobj.getCheckedNodes(true);
	var allnodes = treeobj.transformToArray(treeobj.getNodes());
   if(!!selectedobj&&!!allnodes&&selectedobj.length===allnodes.length){
		treeobj.checkAllNodes(false);
	}else {
		treeobj.checkAllNodes(true);
	}
}

function Opselect(){
	var treeobj = jQuery.fn.zTree.getZTreeObj("patrolresourctree");
	var allnodes = treeobj.getNodes();
	for(var i = 0; i < allnodes.length; i++){
		var childrennodes = allnodes[i].children;
		for(m=0;m<childrennodes.length;m++){
			if(!childrennodes[m].isParent){
			    if(childrennodes[m].checked){
			    	isselected = false;
			    }else{
			        isselected = true;
			    }
				treeobj.checkNode(childrennodes[m],isselected,true,false);
			}
		}
	}
}
</script>
</head>
<body>
	<form action="${ctx }/wplan/patrolinfoAction!save.action"
		id="patrolinfoForm" name="patrolinfoForm" method="post">

		<div class="title_bg">
			<div id="title" class="title">当前位置-确定巡检计划</div>
		</div>
		<div class="tabcontent">
			<table style="width: 60%" border="0" align="center">
				<tr>
					<th>计划名称：</th>
					<td>${patrolinfo.planname}<input id="planname" name="planname"
						class="inputtext" value="${patrolinfoMap.PLAN_NAME}" type="hidden" />
						<input name="year" id="year" value="${patrolinfo.year }"
						type="hidden" /> <input name="businesstype" id="businesstype"
						value="${patrolinfo.businesstype }" type="hidden" /> <input
						name="plantype" id="plantype" value="${patrolinfo.plantype }"
						type="hidden" /> <input id="patrolinfo_starttime"
						name="starttime" value="${patrolinfo.starttime }" type="hidden" />
						<input id="patrolinfo_endtime" name="endtime" type="hidden"
						value="${patrolinfo.endtime }" />
				</tr>
				<tr>
					<th>计划时间：</th>
					<td>${patrolinfo.starttime }至${patrolinfo.endtime }</td>
				</tr>
				<tr>
					<th>巡检组：</th>
					<td>${patrolinfo.patrolgroupname } <input
						id="patrolinfo_patrolgroupid" name="patrolgroupid"
						value="${patrolinfo.patrolgroupid }" type="hidden" />
				</tr>
				<tr>
					<th>所属区域：</th>
					<td>${patrolinfo.regionname} <input id="regionid"
						name="regionid" type="hidden" value="${patrolinfo.regionid }" />
					</td>

				</tr>
					<tr>
					<th>巡检站点：</th>
					<td>快速搜索站点：<input id="search_resources" /><a
						href="javascript:searchNode();"> <img border="0"
							src="${ctx}/css/images/selectcode.gif" /> </a>
						<div style="white-space: nowrap;">
							<div id="patrolresourctree" class="ztree"></div>
							<div id="ztreeControler" style="display:none">
							<input type="button" class="button" value="全选/取消" onclick="CheckAll()">
							<input type="button" class="button" value="反选" onclick="Opselect()">
							</div>
							<input id="patrolinfo_resourceids" name="resourceids"
								value="${resourceids }" type="hidden" /> <input
								id="patrolinfo_resourcetypes" name="resourcetypes"
								value="${resourcetypes }" type="hidden" /> <span
								style="color: red;display: inherit;">*</span>
						</div>
					</td>
				</tr>
				<tr>
					<th>巡检模板：</th>
					<td><baseinfo:customselector name="plantemplate"
							data="${plantemplateMap}" isReversal="true"
							cssClass="inputtext required"
							keyValue="${patrolinfoMap.TEMPLATEID}"></baseinfo:customselector>
						<font style="color: red;">&nbsp;*</font></td>
				</tr>
				<tr>
					<th>审核人：</th>
					<td><input id="patrolinfo_acceptUsername" name="approvername"
						value="${patrolinfoMap.APPROVERNAME }" class="inputtext required"
						readonly="readonly" /> <a href="javascript:searchAcceptUser();">
							<img border="0" src="${ctx}/css/images/selectcode.gif" /> </a> <input
						id="patrolinfo_acceptUserid" name="approver"
						value="${patrolinfoMap.APPROVER }" type="hidden" /><span
						style="color: red">*</span>
					</td>
				</tr>
			</table>
			<div style="text-align: center; margin-top: 10px">
				<input id="btnSubmit" value="提交"
					type="submit" class="button" onclick="submitData();" />
			</div>
		</div>
	</form>
</body>
</html>