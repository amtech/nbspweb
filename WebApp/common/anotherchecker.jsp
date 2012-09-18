<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<link href="${ctx}/js/jQuery/layout/jquery.layout.css" rel="stylesheet"
	type="text/css">
<link href="${ctx }/js/jQuery/ztree/css/zTreeStyle/zTreeStyle.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript"
	src="${ctx}/js/jQuery/layout/jquery.layout-latest.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/ztree/jquery.ztree.all.min.js"></script>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>请选择</title>
		<script type="text/javascript">
	//简单在线人员树样式	
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
	function save() {
		var userid = $("#workorder_accept_user_ids").val();
	}
</script>
	</head>
	<body>
		<div class="ui-layout-center">
			<div class="title_bg">
				<div id="title" class="title">
					请选择转审人员
				</div>
			</div>
			<div class="ztree" style="margin-top: 0;">
				<input id="workorder_accept_user_name"
					name="workOrder.acceptUsername" class="inputtext required"
					style="width: 55%" readonly="readonly" />
				<a href="javascript:search();"> <img border="0"
						src="${ctx}/css/images/selectcode.gif" /> </a>
				<span style="color: red">*</span>
				<input id="workorder_accept_user_ids" name="workOrder.acceptUserIds"
					type="hidden" />
			</div>
		</div>
		<br>
		<div class="ui-layout-south" style="text-align: center;">
			<input name="btnSave" value="提交" type="button" class="button"
				onclick="save();" />
		</div>
	</body>
</html>