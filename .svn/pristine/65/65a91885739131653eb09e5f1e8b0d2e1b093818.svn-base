<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/header.jsp"%>
<div class="list">
	<table width="100%" cellpadding="0" cellspacing="0" align="center">
		<tr>
			<th>派单部门</th>
			<th>派单人</th>
			<th>受理部门</th>
			<th>受理人</th>
			<th>派单时间</th>
		</tr>
		<c:forEach items="${list}" var="item">
			<tr>
				<td><baseinfo:user displayProperty="orgname"
						id="${item.CREATER }" />
				</td>
				<td><baseinfo:user displayProperty="username"
						id="${item.CREATER }" />
				</td>
				<td><baseinfo:user displayProperty="orgname"
						id="${item.TARGET_PRINCIPAL }" />
				</td>
				<td class="nowrap"><baseinfo:user displayProperty="username"
						id="${item.TARGET_PRINCIPAL }" />
				</td>
				<td class="nowrap">${item.CREATE_DATE_DIS}</td>
			</tr>
		</c:forEach>
	</table>
</div>