<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<div class="list" align="center">
<c:if test="${!empty PATROLINFO_AUDIT_HISTORY_MAP}">
	<table cellpadding="0" cellspacing="0" width="100%" style="border-collapse:collapse;">
		<c:forEach var="oneProcessMap" items="${PATROLINFO_AUDIT_HISTORY_MAP}">
			<c:set var="processKey" value="${oneProcessMap.key}"></c:set>
			<c:set var="processList" value="${oneProcessMap.value}"></c:set>
			<c:if test="${processKey=='审核'}">
				<c:forEach var="oneProcess" items="${oneProcessMap.value}">
					<c:if test="${oneProcess.processResult!=null }">
						<tr>
							<td class="tdl" style="width: 20%; padding: 5px;">
								${processKey }结果：</td>
							<td class="tdr" style="width: 80%; padding: 5px;text-align:left;">
								${oneProcess.processResult }</td>
						</tr>
					</c:if>
					<tr>
						<td class="tdl" style="width: 20%; padding: 5px;">
							${processKey }备注：</td>
						<td class="tdr" style="width: 80%; padding: 5px;text-align:left;">
							${oneProcess.processComment }</td>
					</tr>
					<tr>
						<td class="tdl" colspan="2" 
							style="width: 100%; padding: 5px;text-align:right;">${processKey }人：
							${oneProcess.processUser } ${processKey }时间：
							&nbsp;&nbsp;&nbsp;&nbsp; ${oneProcess.processDate }</td>
					</tr>
				</c:forEach>
			</c:if>
		</c:forEach>
	</table>
</c:if>
<c:if test="${empty PATROLINFO_AUDIT_HISTORY_MAP}">
		没有可显示的审批内容。
</c:if>
</div>