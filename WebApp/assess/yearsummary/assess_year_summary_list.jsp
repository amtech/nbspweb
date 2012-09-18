<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<c:if test="${not empty dataMap }">
	<table align="center">
		<tr>
			<th style="width:450px;text-align:center;">代维公司</th>
			<c:set var="itemList" value="${dataMap.tableItemList }"></c:set>
			<c:forEach var="oneItem" items="${itemList }">
				<th style="width:250px;text-align:center;">${oneItem.ITEM_NAME }</th>
			</c:forEach>
			<th style="width:250px;text-align:center;">申诉次数</th>
			<th style="width:350px;text-align:center;">年度考核成绩</th>
		</tr>
		<c:forEach var="oneData" items="${dataMap.resultList }">
			<tr>
				<td style="width:450px;text-align:center;">${oneData.NAME }</td>
				<c:forEach var="oneItem" items="${itemList }">
					<c:set var="key" value="ITEM_${oneItem.ITEM_ID }"></c:set>
					<td style="width:250px;text-align:center;">${oneData[key] }</td>
				</c:forEach>
				<td style="width:250px;text-align:center;">${oneData.APPERAL_NUM }</td>
				<td style="width:350px;text-align:center;">${oneData.SUM_ }</td>
			</tr>
		</c:forEach>
	</table>
</c:if>