<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/header.jsp"%>
<link href="${ctx }/js/jQuery/ztree/css/zTreeStyle/zTreeStyle.css"
	type="text/css" rel="stylesheet">
<!--jvalidation  -->
<script type="text/javascript"
	src="${ctx}/js/jQuery/jvalidation/jquery.validate.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jvalidation/messages_cn.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jvalidation/additional-methods.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jvalidation/jquery.validate.ex.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/ztree/jquery.ztree.all.min.js"></script>
<body>
	<form action="${ctx }/wplan/wplanTemplateAction!copy.action"
		id="wplantemplateForm" name="wplantemplateForm" method="post">
		<div class="title_bg">
			<div id="title" class="title">
				当前位置-复制计划模板
			</div>
		</div>
		<div class="tabcontent">
			<table border="0" align="center" cellpadding="3" cellspacing="0">
				<tr>
					<th>
						专业：
					</th>
					<td>
					   &nbsp;<baseinfo:dic displayProperty="lable" codevalue="${vo.businessType }" columntype="businesstype"></baseinfo:dic>
					   <input type="hidden" name="businessType" value="${vo.businessType }"/>
					</td>
				</tr>
				<tr>
					<th>
						模板名称：
					</th>
					<td>
						&nbsp;<input id="template_name" name="templateName" value="${vo.templateName }" style="width: 220px;"/><span style="color: red">*</span>
					</td>
				</tr>
				<tr>
					<th>
						备注：
					</th>
					<td>
						&nbsp;<textarea rows="3" name="remark" style="width: 220px;">${vo.remark }</textarea>
					</td>
				</tr>
				<tr>
					<th>
						计划模板项：
					</th>
					<td>
						<div style="margin-top:2;margin-bottom:2;margin-left:6;border-color:#5EA2D2; border-style:solid; border-width:1px;height:300px;width:85%;overflow-y:auto;">
						  	<div id="stafftree" class="ztree" style="margin-top:0;"></div>
						</div>
						<input type="hidden" name="items" id="voitems">
					</td>
				</tr>
			</table>
			<div style="text-align: center; margin-top: 10px">
				<input name="btnSave" value="创建" type="button" class="button"
					onclick="save();" />
				<input name="btnReset" value="返回" type="button" class="button"
					onclick="history.back()" />
			</div>
		</div>
	</form>
	<script language="javascript" type="text/javascript">
	function save() {  
		if(jQuery("#template_name").val()==''){
			alert("计划模板名称不能为空！");
			return false;
		}
		var rds = "";//资源字符ID
		var checkedNodes = treeObj.getCheckedNodes(true);
		
		for (var i = 0; i < checkedNodes.length; i++) {
			if ("root"!=checkedNodes[i].pId) {
				rds +=","+checkedNodes[i].id;
			}
		}
		if (rds != "") {
			rds = rds.substring(1, rds.length);
		}
		if(rds.length==0){
			alert("计划模板项不能为空！");
			return false;
		}
		jQuery("#voitems").val(rds);
		jQuery("#wplantemplateForm").submit();
	}
	function search() {
		var val = window.showModalDialog('${ctx}/commonaccess!getstaff.action',
				'',
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
			jQuery("#workorder_accept_user_name").val(userName);
			jQuery("#workorder_accept_user_ids").val(userId);
		}
	}
	function reset() {
	}
function loadTree(){
	treeObj.destroy();
	var newSetting = {
		view: {dblClickExpand: false,showLine: false,showIcon: true},
		data: {
			simpleData: {enable: true,rootPId:"root"}
		},
		async:{
			url:"${ctx}/wplan/wplanTemplateAction!getPatrolItemJson.action?businessType="+jQuery("#businessType").val()+"&id=${templateId}&flag=${flag}",
			enable:true
		},
		check: {enable: true},
		callback: {}
	};
	treeObj = jQuery.fn.zTree.init($("#stafftree"), newSetting);
}
var treeObj;
var setting = {
	view: {dblClickExpand: false,showLine: false,showIcon: true},
	data: {
		simpleData: {enable: true,rootPId:"root"}
	},
	async:{
		url:"${ctx}/wplan/wplanTemplateAction!getPatrolItemJson.action?businessType=${businessType}&id=${templateId}&flag=${flag}",
		enable:true
	},
	check: {enable: true},
	callback: {}
};
	jQuery(function() {
		jQuery("#wplantemplateForm").validate();
		treeObj = jQuery.fn.zTree.init($("#stafftree"), setting);
	})
</script>
</body>