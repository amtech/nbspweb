<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<table id="datatable" cellspacing="0">
	<thead>
		<tr>
			<th><fmt:formatDate value="${ordermonth }" pattern="M" />月份</th>
			<th>故障工单</th>
			<th>通用工单</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="itemtype" items="${workorder}" end="2">
			<tr>
				<th>${itemtype.value.GNAME}</th>
				<td>${itemtype.value.overtime_f_num}</td>
				<td>${itemtype.value.OVERTIME_W_NUM}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>