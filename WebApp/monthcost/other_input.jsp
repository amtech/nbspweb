<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html>
	<head>
		<link href="${ctx }/js/WdatePicker/skin/WdatePicker.css"
			type="text/css" rel="stylesheet" />
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
			src="${ctx}/js/WdatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
	function save() {
		var regionname = jQuery("#regionname").val();
		if (regionname == "") {
			alert("地区不能为空！");
			return false;
		}

		var months = jQuery("#months").val();
		if (months == "") {
			alert("请选择月份！");
			return false;
		}

		var contractorId = jQuery("#contractorId").val();
		if (contractorId == "") {
			alert("代维公司不能为空！");
			return false;
		}
		var shouldMoney = jQuery("#shouldMoney").val();
		if (shouldMoney == "") {
			alert("应付款不能为空！");
			return false;
		}
		if (isNaN(shouldMoney)) {
			alert("应付款必须是数字！");
			jQuery("#shouldMoney").val("");
			jQuery("#shouldMoney").focus();
			return false;
		}
		var factMoney = jQuery("#factMoney").val();
		if (factMoney == "") {
			alert("实付款不能为空！");
			return false;
		}
		if (isNaN(factMoney)) {
			alert("实付款必须是数字！");
			jQuery("#factMoney").val("");
			jQuery("#factMoney").focus();
			return false;
		}
		jQuery("#othercostForm").submit();
		return true;
	}
	/**
	 * 刷新页面
	 */
	function breakPage() {
		window.location.href = "${ctx }/monthcost/monthothercost!input.action?t="
				+ Math.random();
	}
	/**
	 * 搜索区域树
	 */
	function searchRegion() {
		var val = showRegion('${ctx }/commonaccess!getregion.action?');
		if (!!val) {
			jQuery("#regionId").val(val[0]);
			jQuery("#regionname").val(val[1]);
		}
	}

	/**
	 * 搜索代维公司树
	 */
	function searchContractor() {
		var regionId = jQuery("#regionId").val();
		var url = '${ctx }/commonaccess!getorg.action?orgtype=2';
		if (regionId != '') {
			url += "&regionid=" + regionId;
		}
		var val = showOrg(url);
		if (!!val) {
			jQuery("#contractorId").val(val[0]);
			jQuery("#contractorName").val(val[1]);
		}
	}
	function gotoImport() {
		window.location.href = "${ctx }/monthcost/monthstatistical!importFileByType.action?type=other";
	}
</script>
	</head>
	<body>
		<form action="${ctx }/monthcost/monthothercost!save.action"
			id="othercostForm" name="othercostForm" method="post">
			<input type="hidden" name="entity.id" value="${entity.id }" id="id" />
			<div class="title_bg">
				<div class="title">
					当前位置-其他费用上报
				</div>
			</div>
			<div class="tabcontent">
				<table border="0" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<th>
							区域:
						</th>
						<td>
							<input id="regionname" name="regionname"
								class="inputtext required" value="${entity.regionName }"
								readonly="readonly" style="width: 150px;" />
							<a href="javascript:searchRegion();"> <img border="0"
									src="${ctx}/css/images/selectcode.gif" /> </a>
							<input id="regionId" name="entity.regionId" type="hidden"
								value="${entity.regionId }" />
						</td>

						<th>
							月份：
						</th>
						<td>
							<input type="text" id="months" name="entity.months"
								value="${entity.months }" readonly="readonly"
								class="Wdate required" style="width: 150px"
								onfocus="WdatePicker({dateFmt:'yyyy-MM'})" />
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th>
							代维公司:
						</th>
						<td>
							<input id="contractorName" name="contractorName"
								value="${entity.contractorName }" class="inputtext required"
								style="width: 150px;" type="text" />
							<a href="javascript:searchContractor();"> <img border="0"
									src="${ctx}/css/images/selectcode.gif" /> </a>
							<input type="hidden" name="entity.contractorId" id="contractorId"
								value="${entity.contractorId }" />
							<span style="color: red">*</span>
						</td>
						<th>
							类型：
						</th>
						<td>
							<baseinfo:dicselector name="entity.typet" id="typet"
								columntype="OTHERTYPE" type="select"></baseinfo:dicselector>
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th>
							应付款：
						</th>
						<td>
							<input name="entity.shouldMoney" type="text" id="shouldMoney"
								cssClass="inputtext required" value="${entity.shouldMoney }" />
							<span style="color: red">*</span>
						</td>
						<th>
							实付款：
						</th>
						<td>
							<input name="entity.factMoney" type="text" id="factMoney"
								value="${entity.factMoney }" cssClass="inputtext required" />
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th>
							款项依据：
						</th>
						<td colspan="3">
							<textarea id="remark" cols="120" rows="9" name="entity.remark">${entity.remark }</textarea>
						</td>
					</tr>
				</table>
				<div style="text-align: center; margin-top: 10px">
					<input type="button" class="button" value="提交 "
						onclick="return save()">
					<input type="button" class="button" value="重置"
						onclick="breakPage()" />
					<input type="button" class="button" id="import" value="导入数据"
						onclick="gotoImport()" />
				</div>
		</form>
	</body>
</html>
