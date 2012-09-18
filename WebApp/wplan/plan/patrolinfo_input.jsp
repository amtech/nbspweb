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
	In.add('style',{path:'${ctx }/css/style.css'});
In.add('art',{path:'${ctx}/js/jQuery/artdialog/jquery.artDialog.js?skin=default',charset:'utf-8',type:'js'});
In.add('ztreecss',{path:'${config.cdnurl}/cabletech/ui/ztree/css/zTreeStyle/zTreeStyle.css'});
In.add('ztreejs',{path:'${config.cdnurl}/cabletech/ui/ztree/jquery.ztree.all.min.js',type:'js',charset:'utf-8',rely:['ztreecss']});
In.add('jqui',{path:'${ctx}/js/jQuery/jqueryui/css/redmond/jquery-ui.custom.css'});
In.add('jquijs',{path:'${ctx}/js/jQuery/jqueryui/jquery-ui.custom.min.js',type:'js',charset:'utf-8',rely:['jqui']});
In.add('vm', {path : '${ctx}/js/jQuery/jvalidation/jquery.validate.min.js',type : 'js',charset : 'utf-8'});
In.add('vcn', {path : '${ctx}/js/jQuery/jvalidation/messages_cn.js',type : 'js',charset : 'utf-8',rely : [ 'vm' ]});
In.add('vex', {path : '${ctx}/js/jQuery/jvalidation/jquery.validate.ex.js',type : 'js',charset : 'utf-8',rely : [ 'vcn' ]});
In.add('vam', {path : '${ctx}/js/jQuery/jvalidation/additional-methods.min.js',type : 'js',charset : 'utf-8',rely : [ 'vex' ]});
In.add('common',{path:'${ctx}/common/js/common.js',type:'js',charset:'utf-8'});
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
In.ready('vam', 'jquijs','wdatejs','art','common','patrolinfo_input','ztreejs', function() {
		setContextPath("${ctx}");
		jQuery("th").css("width","25%");
		jQuery("td").css("width","75%");
		//获取计划开始时间及结束时间
		var starttime='${patrolinfoMap.START_TIME }';
		var endtime='${patrolinfoMap.END_TIME }';
		if(starttime.length>0){
			starttime=starttime.substr(0,10);
			endtime=endtime.substr(0,10);
		}
		plantypechange(starttime,endtime);
		//启用Jquery验证
		jQuery("#patrolinfoForm").validate({
			focusInvalid : false,
			submitHandler : function(form) {
				var resourceIds=jQuery("#patrolinfo_resourceids").val();
				if (resourceIds) {
					jQuery("#patrolinfo_endtime").removeAttr("disabled");
					form.submit();
				}else{
					alert("请选择巡检资源！");
				}
			}
		});
		var  groupId='${patrolinfoMap.PATROL_GROUP_ID }';
		if(groupId){
			getResource(groupId,false);
		}
		getTemplate();
	});
</script>
</head>
<body>
	<form action="${ctx }/wplan/patrolinfoAction!save.action"
		id="patrolinfoForm" name="patrolinfoForm" method="post">
		<div class="title_bg">
			<div id="title" class="title">当前位置-制定巡检计划</div>
		</div>
		<div class="tabcontent">
			<table border="0">
				<tr>
					<th>计划年份：</th>
					<td><baseinfo:customselector name="year" data="${planyearMap}"
							isReversal="true" cssClass="inputtext"
							keyValue="${patrolinfoMap.YEAR}"></baseinfo:customselector></td>
				</tr>
				<tr>
					<th>计划名称：</th>
					<td><input id="planname" name="planname" class="inputtext" style="width: 280px"
						value="${patrolinfoMap.PLAN_NAME}" /></td>
				</tr>
				<tr>
					<th>计划类型：</th>
					<td><baseinfo:customselector name="plantype" id="plantype"
							data="${plantypeMap}" isReversal="true" cssClass="inputtext"
							keyValue="${patrolinfoMap.PLAN_TYPE}"
							onChange="plantypechange();"></baseinfo:customselector></td>
				</tr>
				<tr>
					<th>专业类型：</th>
					<td><baseinfo:customselector id="businesstype"
							name="businesstype" data="${businessTypeMap }"
							keyValue="${patrolinfoMap.BUSINESS_TYPE}"
							onChange="change();clearResource();"
							cssClass="inputtext required" isReversal="true"></baseinfo:customselector><span
						style="color: red">*</span></td>
				</tr>
				<tr>
					<th>计划时间：</th>
					<td>
						<div id="yearDV">
							<baseinfo:customselector id="yeartype" name="yeartype"
								data="${yearTypeMap}" isReversal="true" cssClass="inputtext"
								onChange="" keyValue="${yeartype}"></baseinfo:customselector>
						</div>
						<div id="seasonDV">
							<baseinfo:customselector name="seasontype"
								data="${seasonTypeMap}" isReversal="true" cssClass="inputtext"
								onChange="" keyValue="${seasontype}"></baseinfo:customselector>
						</div>
						<div id="monthDV" style="white-space: nowrap;">
							<input id="patrolinfo_starttime" name="starttime" type="text"
								class="Wdate" style="width:35%"
								value="${patrolinfoMap.START_TIME }"
								onfocus="var fmt=setStartDateFmt();var maxDateS=maxDateFS();var minDateS=minDateFS(); WdatePicker({dateFmt:fmt,realDateFmt:fmt,onpicked:setEndDate,maxDate:maxDateS,minDate:minDateS})" />
							至 <input id="patrolinfo_endtime" name="endtime" type="text"
								class="Wdate" style="width:35%"
								value="${patrolinfoMap.END_TIME }"
								onfocus="var maxDateE=maxDateFE();var minDateE=minDateFE(); WdatePicker({dateFmt:'yyyy-MM-dd',realFullFmt:'yyyy-MM-dd HH:mm:ss',onpicked:setEndDate,maxDate:maxDateE,minDate:minDateE})" />
							<span style="color: red">*</span>
						</div>
				</tr>
				<tr>
					<th>所属区域：</th>
					<td><input id="regionname" name="regionname"
						class="required inputtext" value="${patrolinfoMap.REGIONNAME }"
						readonly="readonly" /> <a href="javascript:getRegion();"><img
							border="0" src="${ctx}/css/image/regionselect.png" /> </a> <input
						id="regionid" name="regionid" type="hidden"
						value="${patrolinfoMap.REGIONID }" /> <span style="color: red">*</span>
					</td>
				</tr>
				<tr>
					<th>巡检组：</th>
					<td><input id="patrolinfo_patrolgroupname"
						name="patrolgroupname" class="inputtext required"
						value="${patrolinfoMap.PATROLGROUPNAME }" /> <a
						href="javascript:getPatrolGroup();"> <img border="0"
							src="${ctx}/css/image/groupselect.png" /> </a> <input
						id="patrolinfo_patrolgroupid" name="patrolgroupid"
						value="${patrolinfoMap.PATROL_GROUP_ID }" type="hidden" /> <span
						style="color: red">*</span></td>
				</tr>
				<tr>
					<th>巡检站点：</th>
					<td><input id="patrolinfo_resourceids" name="resourceids"
						value="${resourceids }" type="hidden" /> <input
						id="patrolinfo_resourcetypes" name="resourcetypes"
						value="${resourcetypes }" type="hidden" /> <a
						href="javascript:showResourceDialog();">选择站点</a><br /> <span
						id="patrolinfo_resourcenames"></span> <span
						style="color: red;display: inherit;">*</span>
						<div id="resourceDiv" style="display:none">
							快速搜索站点： <input id="search_resources" onblur="searchNode();" />
							<div style="white-space: nowrap;">
								<div id="patrolresourctree" class="ztree"></div>
								<div id="ztreeControler" style="display:none">
									<input type="button" class="button" value="全选/取消"
										onclick="CheckAll()"> <input type="button"
										class="button" value="反选" onclick="Opselect()"> <input
										type="button" class="button" value="完成"
										onclick="setResource()">
								</div>
							</div>
						</div></td>
				</tr>
				<tr>
					<th>巡检模板：</th>
					<td><select id="plantemplate" name="plantemplate" 
						class="inputtext required"></select> <font style="color: red;">&nbsp;*</font>
					</td>
				</tr>
				<tr>
					<th>审核人：</th>
					<td><input id="patrolinfo_acceptUsername" name="approvername"
						value="${patrolinfoMap.APPROVERNAME }" class="inputtext required"
						readonly="readonly" /> <a href="javascript:searchAcceptUser();"><img
							border="0" src="${ctx}/css/image/personselect.png" /> </a> <input
						id="patrolinfo_acceptUserid" name="approver"
						value="${patrolinfoMap.APPROVER }" type="hidden" /> <span
						style="color: red">*</span></td>
				</tr>
			</table>
			<div style="text-align: center; margin-top: 10px">
				<input id="is_submited" name="issubmited" value="1" type="hidden" />
				<input id="id" name="id" value="${patrolinfoMap.ID }" type="hidden" />
				<input id="opt" name="opt" value="${patrolinfoMap.OPT }"
					type="hidden" /> <input id="btnSave" value="保存" type="submit"
					class="button" onclick="saveData();" /> <input id="btnSubmit"
					value="提交" type="submit" class="button" onclick="submitData();" />
				<input name="btnReset" value="重置" type="reset" class="button"
					onclick="reset();" />
			</div>
		</div>
	</form>
	<script type="text/javascript">
		// 获取审核人
	function searchAcceptUser() {
		var val = showOrgPerson('${ctx}/commonaccess!getstaff.action?orgtype=1&flag=radio&regionid=${LOGIN_USER.regionId}');
		if (!!val) {
			jQuery("#patrolinfo_acceptUserid").val(val[0]);
			jQuery("#patrolinfo_acceptUsername").val(val[1]);
			
		}
	}
	// 获取巡检组
	function getPatrolGroup() {
		var url='${ctx}/commonaccess!getpatrolgroup.action?orgtype=2';
		var regionId=jQuery("#regionid").val();
		if(regionId!=""){
			url+='&regionid='+regionId;
		}
		var val = showPatrolGroup(url);
		if (!!val) {
			jQuery("#patrolinfo_patrolgroupid").val(val[0]);
			jQuery("#patrolinfo_patrolgroupname").val(val[1]);
			getResource(val[0],false);
		}
	}
	//保存数据
	function saveData() {
		jQuery("#is_submited").val("0");
		 var plantype = jQuery('select[name="plantype"]').val();
		if(plantype==4){
				jQuery("#patrolinfo_starttime").addClass('required');
				jQuery("#patrolinfo_endtime").addClass('required');
			}else if(plantype==3){
				jQuery("#patrolinfo_starttime").addClass('required');
				jQuery("#patrolinfo_endtime").addClass('required');
			}else{
				jQuery("#patrolinfo_starttime").removeClass('required');
				jQuery("#patrolinfo_endtime").removeClass('required');
			}

		return true;
	}
	//提交数据
	function submitData() {
		jQuery("#is_submited").val("1");
		return true;
	}
	// 根据巡检组设置巡检资源
	function getResource(groupId,flag){
		//获取巡检资源
		jQuery.ajax({
				url : '${ctx}/wplan/patrolresourceAction!getpatrolresourcetree.action',
				dataType : "json",
				data:{
						patrolgroup_id:groupId,
						businesstype:jQuery('#businesstype').val()
					},
				type:'GET',
				cache:true,
				async:false,
				success : function(result) {
				  if(result==""){
					  alert("该巡检组下没有该专业类型下的维护站点资源！");
					  jQuery("#patrolinfo_resourceids").val("");
					  jQuery("#patrolinfo_resourcetypes").val("");
					  jQuery("#patrolinfo_resourcenames").html("");
					  var ztree=jQuery.fn.zTree.getZTreeObj("patrolresourctree");
					  if(ztree){
						  ztree.destroy();
					  }
					  return;
				  }
				  if(flag){
					  var ztree=jQuery.fn.zTree.init(jQuery("#patrolresourctree"),setting,result);
					  ztree.checkAllNodes(false);
				  }
				  var patrolResourceJSONStr='${patrolResourceListJSON}';
					if(patrolResourceJSONStr){
						var patrolResourceJSON=jQuery.parseJSON(patrolResourceJSONStr);
						var rnames="";
						var rds = "";//资源字符ID
					    var rtype="";//资源类型
						for(var i=0;i<patrolResourceJSON.length;i++)
							{
							 if(flag){
								 var node=ztree.getNodeByParam("RS_ID",patrolResourceJSON[i].RESOURCE_ID);
								 ztree.checkNode(node,true,true,false);
							 }
							 rds+=patrolResourceJSON[i].RESOURCE_ID;
							 rtype+=patrolResourceJSON[i].RESOURCE_TYPE;
							 rnames+=patrolResourceJSON[i].NAME;
							 if(i<patrolResourceJSON.length-1){
								 rds+=",";
								 rtype+=",";
								 rnames+="<br>"
							 }
							}
						jQuery("#patrolinfo_resourceids").val(rds);
						jQuery("#patrolinfo_resourcetypes").val(rtype);
					    jQuery("#patrolinfo_resourcenames").html(rnames);
					}
					if(flag){
						document.getElementById("ztreeControler").style.display="block";
					}
				},
				error : function() {
					alert("获取巡检组所属资源失败！");
				}
			});
	}
	function getTemplate(){
		btype=jQuery('#businesstype').val();
		var regionId=jQuery("#regionid").val();
		var templateId="${patrolinfoMap.TEMPLATEID}";
		//获取模板数据
		jQuery.ajax({
			url : '${ctx}/wplan/patrolinfoAction!gettemplate.action?type='+btype+'&regionId='+regionId,
			success : function(result) {
				var str = result;
				document.getElementById("plantemplate").options.length = 0;
				if (!!str) {
					for ( var i = 0; i < str.length; i++) {
						var isSelected="";
						if(templateId==str[i].ID){
							isSelected="selected";
						}
							var option ="<option value='"+str[i].ID+"'"+isSelected+">"+str[i].TEMPLATE_NAME+"</option>"; 
							jQuery("#plantemplate").append(option);
					}
				}
			},
			error : function() {
				alert("获取资源未知异常！");
			}
		});
	}
	//设置资源
	function setResource(){
		//获取选中节点
		var rds = "";//资源字符ID
	    var rtype="";//资源类型
	    var rname="";
		var zTree = jQuery.fn.zTree.getZTreeObj("patrolresourctree");
		var flag;
	    if(zTree){
			var checknodes = zTree.getCheckedNodes(true);
			if(checknodes){
				for(var i=0;i<checknodes.length;i++){
					if(checknodes[i].RS_TYPE!=="root"){
						rds +=checknodes[i].RS_ID;
						rtype+=checknodes[i].RS_TYPE;
						rname+=checknodes[i].RS_NAME;
						if(i<checknodes.length-1){
							rds+=",";
							rtype+=",";
							rname+="<br>";
						}
					}
				}
				if(rds.length===0){
					return false;
				}
			}else{
				return false;
			}
			jQuery("#patrolinfo_resourceids").val(rds);
			jQuery("#patrolinfo_resourcetypes").val(rtype);
			jQuery("#patrolinfo_resourcenames").html(rname);
			art.dialog.list['siteDialog'].close();
			return true;
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
	function getRegion(){
		searchRegion('${ctx}/commonaccess!getregion.action');
		getTemplate();
	}
	function showResourceDialog(){
		var patrolId=jQuery("#patrolinfo_patrolgroupid").val();
		if(patrolId){
		art.dialog({
			id:"siteDialog",
			title:"选择巡检资源",
			width:500,heigth:400,lock: true,
			content:document.getElementById("resourceDiv")
		});
		jQuery("#resourceDiv").show();
		getResource(patrolId,true);
		}else{
			alert("请先选择巡检组！");
		}
	}
	function clearResource(){
		jQuery("#patrolinfo_resourceids").val("");
		jQuery("#patrolinfo_resourcetypes").val("");
		jQuery("#patrolinfo_resourcenames").html("");
	}
	</script>
</body>
</html>