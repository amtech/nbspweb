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
			alert("计次数量不能为空！");
			return false;
		}
		if (isNaN(numbers)) {
			alert("计次数量必须是数字！");
			jQuery("#numbers").val("");
			jQuery("#numbers").focus();			
			return false;
		}
	    var factMoney=jQuery("#factMoney").val();
	      if(isNaN(factMoney)){
	        alert("实际付款必须是数字！");
	        jQuery("#factMoney").val("");
			jQuery("#factMoney").focus();		
			return false;
	}
		jQuery("#timescostForm").submit();
		return true;
	}
	/**
	 * 刷新页面
	 */
	function breakPage() {
		window.location.href = "${ctx }/monthcost/monthtimescost!input.action?t="
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
		window.location.href = "${ctx }/monthcost/monthstatistical!importFileByType.action?type=time";
	}
</script>
	</head>
	<body>
		<form action="${ctx }/monthcost/monthtimescost!save.action"
			id="timescostForm" name="timescostForm" method="post">
			<input id="id" name="entity.id" type="hidden" value="${entity.id }" />
			<div class="title_bg">
				<div class="title">
					当前位置-计次费用上报
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
								class="inputtext required" value="${ entity.regionName}"
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
							代维专业：
						</th>
						<td>
							<baseinfo:customselector name="entity.specialty"
								data="${businessTypeMap }" isReversal="true"
								cssClass="inputtext required" id="specialty"
								style="width: 180px" keyValue="${entity.specialty }"></baseinfo:customselector>
							<span style="color: red">*</span>
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
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th>
							计次数量：
						</th>
						<td>
							<input name="entity.numbers" type="text" id="numbers"
								cssClass="inputtext required" value="${entity.numbers }">
							<span style="color: red">*</span>
						</td>
						<th>
							计次单价：
						</th>
						<td>
							<input name="entity.unitPrice" type="text" id="unitPrice"
								cssClass="inputtext required" value="${entity.unitPrice }"
								onblur="computed();">
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th>
							计次类型：
						</th>
						<td colspan="3">
							<baseinfo:dicselector name="entity.typet" id="typet"
								columntype="TIMESTYPE" type="select"></baseinfo:dicselector>
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th>
							应付款：
						</th>
						<td>
							<input name="entity.shouldMoney" type="text" required="required"
								id="shouldMoney" cssClass="inputtext required"
								readonly="readonly" value="${entity.shouldMoney }">
							<span style="color: red">*</span>
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
					<input type="button" class="button" value="提交 " 	onclick="return save()">
					<input type="button" class="button" value="重置"
						onclick="breakPage()">
					<input type="button" class="button" id="import" value="导入数据"
						onclick="gotoImport()" />
				</div>
		</form>
	</body>
	<script type="text/javascript">
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
	//给Number类型增加一个mul方法，调用起来更加方便。 
	Number.prototype.mul = function(arg) {
		return accMul(arg, this);
	}
	function computed() {
		var numbers = jQuery("#numbers").val();
		var unitPrice = jQuery("#unitPrice").val();
		if (numbers == "") {
			alert("请输入代维数量！");
			jQuery("#numbers").val("");
			jQuery("#numbers").focus();
			return false;
		} else {
			if (unitPrice == "") {
				alert("请输入单价！");
				jQuery("#unitPrice").focus();
				return false;
			} else {
				if (isNaN(unitPrice)) {
					alert("请输入数字！");
					jQuery("#unitPrice").focus();
					return false;
				}
			}
		}
		var shouldMoney = accMul(numbers , unitPrice);
		jQuery("#shouldMoney").val(shouldMoney);
	}
</script>
</html>
