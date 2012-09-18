<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>月表格信息查看</title>
		<script type="text/javascript"
			src="${ctx}/js/WdatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jvalidation/jquery.validate.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jvalidation/jquery.validate.cn.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jvalidation/messages_cn.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jvalidation/jquery.validate.ex.js"></script>
		<script type="text/javascript">
	function query() {
		var ym = jQuery("#yearmonth").val();
		if (ym != "") {
			ym += "-01";
		}
		jQuery("#reportDate").val(ym);
		jQuery("#optForm").submit();
	}
	jQuery(function() {
		jQuery("#optForm").validate({
			focusInvalid : false
		});
	});
</script>
	</head>
	<body>
		<form id="optForm" method="post"
			action="${ctx }/ah/ahExcelReportSumAction!list.action">
			<div>
				<div class="title_bg">
					<div id="title" class="title">
						当前位置-查看汇总表
					</div>
				</div>
			</div>
			<div class="tabcontent">
				<table id="tableContainer" border="0" align="center" cellpadding="3"
					cellspacing="0">
					<tr>
						<th>
							月表格时间
						</th>
						<td>
							<input id="yearmonth" name="yearmonth" type="text"
								class="Wdate inputtext required" style="width: 220px;"
								onfocus="WdatePicker({dateFmt:'yyyy-MM'})" />
							<input id="reportDate" name="reportDate" type="hidden" value="" />
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th>
							表格类型
						</th>
						<td>
							<select id="reportSheetType" name="reportSheetType"
								style="width: 220px;" class="required">
								<c:forEach var="item" items="${sheetTypeList }">
									<option value="${item.id }">
										${item.sheetname }
									</option>
								</c:forEach>
							</select>
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th>
							汇总类型
						</th>
						<td>
							<select id="sumType" name="sumType" class="required"
								style="width: 220px;">
								<option value="0">
									按地市汇总
								</option>
								<option value="1">
									按全部汇总
								</option>
							</select>
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="button" class="button" value="查询" onclick="query();" />
							<input type="button" class="button" value="重置" onclick="reset();" />
						</td>
					</tr>
				</table>
			</div>
		</form>
	</body>
</html>