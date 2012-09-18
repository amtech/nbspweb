<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/header.jsp"%>
<link href="${ctx }/js/jQuery/ztree/css/zTreeStyle/zTreeStyle.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript"
	src="${ctx}/js/jQuery/jvalidation/jquery.validate.js"></script>
<script type="text/javascript"
			src="${ctx}/js/jQuery/jvalidation/jquery.validate.cn.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jvalidation/messages_cn.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jvalidation/additional-methods.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jvalidation/jquery.validate.ex.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/ztree/jquery.ztree.all.min.js"></script>
<body>
	<form action="${ctx }/wplan/wplanTemplateAction!save.action"
		id="wplantemplateForm" name="wplantemplateForm" method="post">
		<div class="title_bg">
			<div id="title" class="title">当前位置-添加计划模板</div>
		</div>
		<div class="tabcontent">
			<table border="0" align="center" cellpadding="3" cellspacing="0">
				<tr>
					<th>专业：</th>
					<td>
						<baseinfo:customselector name="businessType" onChange="loadTree();" data="${businessTypeMap}" isReversal="true" cssClass="inputtext required" id="businessType" style="width: 220px" keyValue="${businessType }"></baseinfo:customselector>
						<font style="color: red;">*</font>
					</td>
				</tr>
				<tr>
					<th>模板名称：</th>
					<td>
						<input id="template_name" name="templateName" class="required" style="width: 220px;" /><span style="color: red">*</span>
					</td>
				</tr>
				<tr>
					<th>备注：</th>
					<td>
						<textarea rows="3" name="remark" style="width: 220px;"></textarea>
					</td>
				</tr>
				<tr>
					<th>计划模板项：</th>
					<td>
						<div style="margin-top:2;margin-bottom:2;margin-left:6;border-color:#5EA2D2; border-style:solid; border-width:1px;height:300px;width:85%;overflow-y:auto;">
							<div id="stafftree" class="ztree" style="margin-top:0;"></div>
						</div>
						<input type="hidden" name="items" id="voitems"></td>
				</tr>
			</table>
			<div style="text-align: center; margin-top: 10px">
				<input name="btnSave" value="保存" type="button" class="button"
					onclick="save();" /> <input
					name="btnReset" value="重置" type="reset" class="button"
					onclick="resetData();" /> <input
					name="btnReset" value="选择全部" type="reset" class="button"
					onclick="spellAll();" />
			</div>
		</div>
	</form>
	<script type="text/javascript">
	function save() {  
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
		var val = window.showModalDialog('${ctx}/commonaccess!getstaff.action','','status:no;center:yes;dialogWidth:400px;dialogHeight:300px;');
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
	function resetData() {
		document.forms["wplantemplateForm"].reset();
		treeObj.checkAllNodes(false);
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
			url:"${ctx}/wplan/wplanTemplateAction!getPatrolItemJson.action?businessType="+jQuery("#businessType").val()+"&id=${templateId}&flag=${flag}",
			enable:true
		},
		check: {enable: true},
		callback: {}
	};
	var checkAll = true;
	function spellAll(){
		var treeObj = $.fn.zTree.getZTreeObj("stafftree");
		if(checkAll){
			treeObj.checkAllNodes(checkAll);
			checkAll = false;
		}else{
			treeObj.checkAllNodes(checkAll);
			checkAll = true;
		}
	}
		jQuery(function() {
			jQuery("#wplantemplateForm").validate();
			treeObj = jQuery.fn.zTree.init($("#stafftree"), setting);
		})
	</script>
</body>
