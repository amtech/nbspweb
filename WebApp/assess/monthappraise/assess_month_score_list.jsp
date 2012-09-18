<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<c:if test="${not empty tableMap }">
	<table style='width:${tableMap.tableWidth}px;' align="center">
		<c:forEach var="oneRow" items="${tableMap.dataList }">
			<tr>
				<c:forEach var="oneCell" items="${oneRow }">
					<td rowspan='${oneCell.rowSpan }' colspan='${oneCell.colSpan }' style='width: ${oneCell.width }px;'>
						${oneCell.cellValue }&nbsp;
					</td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
</c:if>
<c:if test="${empty tableMap }">
没有代维确认后的月度考核评分！
</c:if>
