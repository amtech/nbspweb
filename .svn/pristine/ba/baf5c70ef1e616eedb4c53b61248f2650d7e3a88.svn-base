<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>导入信息</title>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jvalidation/jquery.validate.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jvalidation/jquery.validate.cn.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jvalidation/jquery.validate.ex.js"></script>
<script>
	function check() {
		if (jQuery("#file").val() == "") {
			alert("没有导入附件！");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<form id="optForm" method="post"
		action="${ctx }/monthcost/monthstatistical!preview.action"
		enctype="multipart/form-data">
		<input type="hidden" value="${type }" name="type" />
		<div class="ui-layout-north">
			<div class="title_bg">
				<div id="title" class="title">当前位置-导入 ${NameStr }</div>
			</div>
			<div class="framecontentBottom">
				<table cellspacing="0" cellpadding="0" border="0" align="center">
					<tr>
						<th>考核表：</th>
						<td><input type="file" name="file" id="file"
							class="validate-file-xls required" /> <font size="2" color="red">*</font>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input name="Submit"
							type="submit" class="button" onclick="return check();" value="导入">
							<input name="button" type="button" class="button"
							onclick="history.go(-1);" value="返回">下载模板-->> <a
							href="${ctx }/monthcost/monthstatistical!downloadTemplate.action?type=${type}"
							target="_self">[${NameStr }]</a></td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</body>
</html>