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

		var specialty = jQuery("#specialty").val();
		if (specialty == "") {
			alert("代维专业不能为空！");
			return false;
		}

		var contractorId = jQuery("#contractorId").val();
		if (contractorId == "") {
			alert("代维公司不能为空！");
			return false;
		}

		var numbers = jQuery("#numbers").val();
		if (numbers == "") {
			alert("代维数量不能为空！");
			return false;
		}

		var unitPrice = jQuery("#unitPrice").val();
		if (isNaN(unitPrice)) {
			alert("单价必须是数字！");
			jQuery("#unitPrice").val("");
			jQuery("#unitPrice").focus();
			return false;
		}

		var checkFraction = jQuery("#checkFraction").val();
		if (isNaN(checkFraction)) {
			alert("考核分数必须是数字！");
			jQuery("#checkFraction").val("");
			jQuery("#checkFraction").focus();
			return false;
		}

		var subtractMoney = jQuery("#subtractMoney").val();
		if (isNaN(subtractMoney)) {
			alert("扣款必须是数字！");
			jQuery("#subtractMoney").val("");
			jQuery("#subtractMoney").focus();
			return false;
		}

		var factMoney = jQuery("#factMoney").val();
		if (isNaN(factMoney)) {
			alert("实付款必须是数字！");
			jQuery("#factMoney").val("");
			jQuery("#factMoney").focus();
			return false;
		}
		jQuery("#checkcostForm").submit();
		return true;
	}
	/**
	 * 刷新页面
	 */
	function breakPage() {
		window.location.href = "${ctx }/monthcost/monthcheckcost!input.action?t="
				+ Math.random();
	}/**
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
		var url = contextPath + '/commonaccess!getorg.action?orgtype=2';
		if (regionId != '') {
			url += "&regionid=" + regionId;
		}
		var val = showOrg(url);
		if (!!val) {
			jQuery("#contractorId").val(val[0]);
			jQuery("#contractorName").val(val[1]);
		}
	}
</script>
	</head>
	<body>
		<form action="${ctx }/monthcost/monthcheckcost!save.action"
			id="checkcostForm" name="checkcostForm" method="post">
			<div class="title_bg">
				<div class="title">
					当前位置-月度考核费用上报
				</div>
			</div>
			<div class="tabcontent">
				<table border="0" align="center" cellpadding="3" cellspacing="0">
					<input type="hidden" id="id" name="entity.id" value="${entity.id }" />
					<tr>
						<th>
							区域:
						</th>
						<td>
							<input id="regionname" name="regionname"
								class="ui-form-input required" value="${entity.regionName }"
								readonly="readonly" style="width: 150px;" />
							<a href="javascript:searchRegion();"> <img border="0"
									src="${ctx}/css/images/selectcode.gif" /> </a>
							<input id="regionId" name="entity.regionId" type="hidden"
								value="${entity.regionId }" />
							<span style="color: red">*</span>
						</td>
						<th>
							月份：
						</th>
						<td>
							<input type="text" id="months" name="entity.months"
								value="${entity.months }" readonly="true" class="Wdate required"
								style="width: 150px" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" />
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th>
							代维专业：
						</th>
						<td>
							<baseinfo:customselector name="entity.specialty"
								data="${businessTypeMap }" isReversal="true"
								cssClass="inputtext required" id="specialty"
								style="width: 180px" keyValue="${entity.specialty }"></baseinfo:customselector>
						</td>
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
						</td>
					</tr>
					<tr>
						<th>
							代维数量：
						</th>
						<td>
							<input name="entity.numbers" type="text" id="numbers"
								cssClass="inputtext required" value="${entity.numbers }"
								required="required">
							<span style="color: red">*</span>
						</td>
						<th>
							单价：
						</th>
						<td>
							<input name="entity.unitPrice" type="text" id="unitPrice"
								cssClass="inputtext required" value="${entity.unitPrice }"
								required="required">
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th>
							考核分数：
						</th>
						<td>
							<input name="entity.checkFraction" type="text" id="checkFraction"
								cssClass="inputtext required" value="${entity.checkFraction }"
								required="required">
							<span style="color: red">*</span>
						</td>
						<th>
							扣款：
						</th>
						<td>
							<input name="entity.subtractMoney" type="text" id="subtractMoney"
								cssClass="inputtext required" value="${entity.subtractMoney }"
								onblur="computed();">
						</td>
					</tr>
					<tr>
						<th>
							应付款：
						</th>
						<td>
							<input name="entity.shouldMoney" type="text" id="shouldMoney"
								cssClass="inputtext required" value="${entity.shouldMoney }"
								readonly="readonly">
						</td>
						<th>
							实付款：
						</th>
						<td>
							<input name="entity.factMoney" type="text" id="factMoney"
								value="${entity.factMoney }" cssClass="inputtext required">
							<span style="color: red">*</span>
						</td>
					</tr>
				</table>
				<div style="text-align: center; margin-top: 10px">
					<input type="button" class="button" value="提交 "
						onclick=" return save()" />
					<input type="button" class="button" value="重置"
						onclick="breakPage()" />
					<input type="button" class="button" id="import" value="导入数据"
						onclick="gotoImport()" />
				</div>
		</form>
	</body>
	<script type="text/javascript">
	function gotoImport() {
		window.location.href = "${ctx }/monthcost/monthstatistical!importFileByType.action?type=check";
	}
	function accMul(arg1, arg2) {
		var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
		try {
			m += s1.split(".")[1].length
		} catch (e) {
		}
		try {
			m += s2.split(".")[1].length
		} catch (e) {
		}
		return Number(s1.replace(".", "")) * Number(s2.replace(".", ""))
				/ Math.pow(10, m)
	} 
	Number.prototype.mul = function(arg) {
		return accMul(arg, this);
	}
	function Subtr(arg1, arg2) {
		var r1, r2, m, n;
		try {
			r1 = arg1.toString().split(".")[1].length
		} catch (e) {
			r1 = 0
		}
		try {
			r2 = arg2.toString().split(".")[1].length
		} catch (e) {
			r2 = 0
		}
		m = Math.pow(10, Math.max(r1, r2)); 
		n = (r1 >= r2) ? r1 : r2;
		return ((arg1 * m - arg2 * m) / m).toFixed(n);
	}
	function computed() {
		var numbers = jQuery("#numbers").val();
		var unitPrice = jQuery("#unitPrice").val();
		var subtractMoney = jQuery("#subtractMoney").val();
		if (numbers == "") {
			alert("请输入代维数量！");
			return false;
		}
		if (isNaN(numbers)) {
			alert("代维数量必须是数字！");
			jQuery("#numbers").val("");
			jQuery("#numbers").focus();
			return false;
		}
		if (unitPrice == "") {
			alert("请输入单价！");
			return false;
		}
		if (isNaN(unitPrice)) {
			alert("单价必须是数字！");
			jQuery("#unitPrice").val("");
			jQuery("#unitPrice").focus();
			return false;
		}
		if (subtractMoney == "") {
			subtractMoney = 0;
		}
		var data = accMul(numbers, unitPrice);
		var shouldMoney = Subtr(data, subtractMoney);
		jQuery("#shouldMoney").val(shouldMoney);
	}
</script>
</html>
