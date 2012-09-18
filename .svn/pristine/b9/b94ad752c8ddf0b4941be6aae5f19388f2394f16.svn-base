<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/header.jsp"%>
<div class="title_bg">
	<div id="title" class="title">
		当前位置-超时故障与超时工单列表
	</div>
</div>
<table width="100%" border="0" cellpadding="0" cellspacing="0"
	class="list">
	<tr class="list_table_title">
		<th width="10%">
			<fmt:formatDate value="${ordermonth }" pattern="M" />
			月份
		</th>
		<th width="10%">
			超时故障工单数量
		</th>
		<th width="10%">
			超时通用工单数量
		</th>
	</tr>
	<c:forEach var="itemtype" items="${workorder}">
		<tr>
			<th>
				${itemtype.value.gname}
			</th>
			<td>
				${itemtype.value.overtime_f_num}
			</td>
			<td>
				${itemtype.value.overtime_w_num}
			</td>
		</tr>
	</c:forEach>
</table>
