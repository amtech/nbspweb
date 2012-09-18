<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>导入巡检项信息</title>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jvalidation/jquery.validate.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jvalidation/jquery.validate.cn.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jvalidation/jquery.validate.ex.js"></script>
<script>
	function check() {
		if (jQuery("#title_").val()=="") {
			alert("考核表标题不能为空！");
			return false;
		}
		if (jQuery("#file").val()=="") {
			alert("没有选择考核表附件！");
			return false;
		}
		return true;
	}
	function downloadFile() {
		//location.href = "${ctx}/wplan/patrolItemImportAction!downloadTemplate.action";
	}
</script>
</head>
<body>
	<form id="optForm" method="post"
		action="${ctx }/ah/ratingFormItemImportAction!preview.action"
		enctype="multipart/form-data">
		<div class="ui-layout-north">
			<div class="title_bg">
				<div id="title" class="title">当前位置-添加考核表信息</div>
			</div>
			<div class="framecontentBottom">
				<table cellspacing="0" cellpadding="0" border="0" align="center">
					<tr>
						<th>专业名称：</th>
						<td><baseinfo:dicselector name="businessType"
								columntype="businesstype" type="select"
								keyValue="${businessType }"></baseinfo:dicselector></td>
					</tr>
					<tr>
						<th>考核表标题：</th>
						<td><input name="title" id="title_" type="text"
							class="inputtext required" style="width: 350px;" />
							<font size="2" color="red">*</font>
						</td>
					</tr>
					<tr>
						<th>考核表：</th>
						<td><input type="file" name="file"  id="file"
							class="validate-file-xls required" />
							<font size="2" color="red">*</font>
							</td>
					</tr>
					<tr>
						<th>考核表备注：</th>
						<td><textarea id="remark" name="remark"
								style="width: 350px; heigth: 200px;" class="inputtextarea"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input name="Submit"
							type="submit" class="button" onclick="return check();" value="导入">
							<input name="button" type="button" class="button"
							onclick="history.go(-1);" value="返回"></td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</body>
</html>