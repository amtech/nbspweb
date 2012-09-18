<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>月表格信息查看</title>
		<script type="text/javascript">
		function exportXls(){
			location="${ctx}/ah/ahExcelReportViewAction!export.action";
		}
</script>
	</head>
	<body>
		<div>
			<div class="title_bg">
				<div id="title" class="title">
					当前位置-您查看的是：
					<baseinfo:region displayProperty="REGIONNAME"
						id="${report.regionId }"></baseinfo:region>
					<fmt:formatDate value="${report.reportDate }" pattern="yyyy年MM月" />
					${report.sheetName }
				</div>
			</div>
		</div>
		<br>
		<div style="padding: 15px;">
			<c:if test="${empty tableMap }">
				没有月报表数据。
			</c:if>
			<c:if test="${not empty tableMap }">
			<table border='1' style='width:${tableMap.tableWidth}px;'>
				<c:forEach var="oneRow" items="${tableMap.dataList }">
					<tr>
						<c:forEach var="oneCell" items="${oneRow }">
							<td rowspan='${oneCell.rowSpan }' colspan='${oneCell.colSpan }'
								style='width: ${oneCell.width }px;'>
								${oneCell.cellValue }&nbsp;
							</td>
						</c:forEach>
					</tr>
				</c:forEach>
			</table>
			</c:if>
		</div>
		<div class="framecontentBottom" align="center">
			<input name="button" type="button" class="button"
				onclick="exportXls();" value="导出Excel">
			<input name="button" type="button" class="button"
				onclick="history.go(-1);" value="返回">
		</div>
	</body>
</html>