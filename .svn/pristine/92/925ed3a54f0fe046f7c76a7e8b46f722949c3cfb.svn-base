<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/header.jsp"%>
<div class="title_bg">
	<div id="title" class="title">当前位置-资质，证书到期提醒列表</div>
</div>
<table width="100%" border="0" cellpadding="0" cellspacing="0"
	class="list">
	<tr class="list_table_title">
		<th width="10%">证书持有人</th>
		<th width="10%">证书名称</th>
		<th width="10%">到期时间</th>
	</tr>
	<c:forEach items="${validperiodedCertificatesList}" var="item">
		<tr>
			<td>${item.ORGNAME}</td>
			<td>${item.CERTIFICATENAME }</td>
			<td>${item.VALIDPERIOD }</td>
		</tr>
	</c:forEach>
</table>