<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx }/js/jQuery/jqgrid/css/ui.jqgrid.css" type="text/css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${ctx}/js/jQuery/jqgrid/grid.locale-cn.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/WdatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	jQuery(function() {

	});

	function submit() {
		var timeoutNum = jQuery("#timeoutNum").val();
		if (timeoutNum == "") {
			alert("请填写时间,单位是 分钟.");
			return false;
		} else if (!isNaN(timeoutNum)) {
			alert("请填写数字！");
			return false;
		}

		jQuery("#form1").submit();
		return true;
	}
</script>
</head>
<body>
	<div id="header">
		<div class="title_bg">
			<div class="title">当前位置-添加时限控制</div>
		</div>
		<div class="tabcontent">
			<form name="form1" method="post" id="form1"
				action="${ctx}/sysmanager/warn!saveTimeSetting.action">
				<input type="hidden" value="${remindTimeConfigure.id }" id="id"
					name="id"> <input type="hidden"
					value="${remindTimeConfigure.professionType }" id="businessType"
					name="businessType"> <input type="hidden"
					value="${remindTimeConfigure.smsType }" id="smsType" name="smsType">
				<input type="hidden" value="${remindTimeConfigure.workorderType }"
					id="taskType" name="taskType">
				<table cellspacing="0" cellpadding="0" border="0" align="center">
					<tr>
						<th>工单类型</th>
						<td>${remindTimeConfigure.workorderTypeStr }</td>
					</tr>
					<tr>
						<th>专业类型</th>
						<td>${remindTimeConfigure.professionTypeStr }</td>
					</tr>
					<tr>
						<th>提醒类型</th>
						<td>${remindTimeConfigure.smsTypeStr }</td>
					</tr>
					<tr>
						<th>设置时长</th>
						<td><input id="timeoutNum" name="timeoutNum"
							value="${ remindTimeConfigure.timeoutNum}" style="width: 198px"
							class="inputtext" /> (分钟)</td>
					</tr>
					<tr>
						<td colspan="4" align="center"><input type="button"
							onclick="submit();" class="button" value="提交"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>

