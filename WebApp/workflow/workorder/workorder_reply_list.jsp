<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<div class="list" align="center">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<th style="width:15%">回单时间</th>
			<th style="width:15%">回单部门</th>
			<th style="width:10%">回单人</th>
			<th style="width:45%">回单内容</th>
			<th style="width:15%">附件</th>
		</tr>
		<c:forEach items="${list}" var="item">
			<tr>
				<td style="width:15%">${item.CREATE_DATE_DIS}</td>
				<td style="width:15%"><baseinfo:user displayProperty="orgname" id="${item.CREATER }" /></td>
				<td style="width:10%"><baseinfo:user displayProperty="username" id="${item.CREATER }" /></td>
				<td style="width:45%">${item.REMARK}</td>
				<td style="width:15%"><apptag:upload state="look" entityId="${item.ID }" entityType="WTASK_RECEIPT"></apptag:upload></td>
			</tr>
		</c:forEach>
	</table>
</div>