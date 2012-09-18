<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<table id="datatable" cellspacing="0">
	<thead>
		<tr>
			<th></th>
			<th title="站点包括基站，铁塔，直放站，室分，集客等">站点</th>
			<th>人员</th>
			<th>车辆</th>
			<th>线路</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="itemtype" items="${resequiplist}" end="2">
			<tr>
				<th>${itemtype.NAME}</th>
				<td>${itemtype.RESNUM}</td>
				<td>${itemtype.PERSONNUM}</td>
				<td>${itemtype.CARNUM}</td>
				<td><fmt:formatNumber value="${itemtype.LINENUM}"
						pattern="##.##" minFractionDigits="2"></fmt:formatNumber></td>
			</tr>
		</c:forEach>
	</tbody>
</table>