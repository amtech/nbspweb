<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html>
<head>
</head>
<body>
	<div class="title_bg">
		<div class="title">当前位置-计次费用页面</div>
	</div>
	<div class="tabcontent">
		<table border="0" align="center" cellpadding="3" cellspacing="0">
			<tr>
				<th>区域:</th>
				<td>${entity.regionName }</td>

				<th>月份：</th>
				<td>${entity.months } 月</td>
			</tr>
			<tr>
				<th>代维专业：</th>
				<td>${entity.specialty }</td>
				<th>代维公司:</th>
				<td>${entity.contractorName }</td>
			</tr>
			<tr>
				<th>计次数量：</th>
				<td>${entity.numbers }个</td>
				<th>计次单价：</th>
				<td>${entity.unitPrice }元/个</td>
			</tr>
			<tr>
				<th>计次类型：</th>
				<td colspan="3"><baseinfo:dicselector name="" columntype="TIMESTYPE"
						type="view" keyValue="${entity.typet}"></baseinfo:dicselector></td>
			</tr>
			<tr>
				<th>应付款：</th>
				<td>${entity.shouldMoney } 元</td>
				<th>实付款：</th>
				<td>${entity.factMoney } 元</td>
			</tr>
		</table>
		<div style="text-align: center; margin-top: 10px">
			<input type="button" class="button"
				onclick="javascript:window.close();" value="关闭 ">
		</div>
		</form>
</body>
</html>
