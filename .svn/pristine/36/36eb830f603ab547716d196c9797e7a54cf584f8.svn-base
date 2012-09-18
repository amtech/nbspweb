<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jvalidation/jquery.validate.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jvalidation/jquery.validate.cn.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jvalidation/jquery.validate.ex.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jvalidation/messages_cn.js"></script>
		<script type="text/javascript">
	jQuery(function() {
		jQuery("#staffForm").validate({
			focusInvalid : false,
			submitHandler : function(form) {
				form.submit();
			}
		});
	});
</script>
	</head>
	<body>
		<div class="title_bg">
			<div id="title" class="title">
				当前位置- 油机加油记录
			</div>
		</div>
		<div id="msg" class='pushmsg'></div>
		<form id="staffForm" method="post"
			action="${ctx }/oil/oilRecordAction!save.action">
			<div class="tabcontent">
				<table style="width: 75%" border="0" align="center" id="table1">
					<tr>
						<th>
							${LOGIN_USER.userName }为${entity.oilengineCode }编号的油机加油
							<baseinfo:dicselector columntype="OIL_TYPE" type="view"
								keyValue="${entity.oilType }" id="oil_type" name="oil_type"
								cssClass="inputtext" style="width:220px"></baseinfo:dicselector>
							：
						</th>
						<td>
							<input id="oe_id" name="oeId" type="hidden" value="${entity.id }" />
							<input id="quantity" name="quantity" class="inputtext required number"
								style="width: 220px;" />
							升
							<span style="color: red">*</span>
						</td>
					</tr>
				</table>
				<div style="text-align: center; margin-top: 10px">
					<input value="提交" type="submit" class="button" />
					<input value="重置" type="reset" class="button" />
					<input value="返回" type="button" class="button"
						onclick="history.go(-1);" />
				</div>
			</div>
		</form>
	</body>
</html>