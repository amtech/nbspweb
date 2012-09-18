<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<div class="list" align="center">
	<c:if test="${!empty FAULT_AUDIT_HISTORY_MAP}">
		<table cellpadding="0" cellspacing="0" align="center">
			<c:forEach var="oneProcessMap" items="${FAULT_AUDIT_HISTORY_MAP}">
				<c:set var="processKey" value="${oneProcessMap.key}"></c:set>
				<c:set var="processList" value="${oneProcessMap.value}"></c:set>
				<c:if test="${processKey=='审核故障回单'}">
					<!-- 
					<tr>
						<th style="width: 20%; padding: 5px; background-color:#B8DDFC;"><font color="#058CFE"><b>${processKey }&nbsp;&nbsp;</b></font></th>
						<th style="background-color:#B8DDFC;"></th>
					</tr>
					 -->
					<c:forEach var="oneProcess" items="${oneProcessMap.value}">
						<c:if test="${oneProcess.processResult!=null }">
							<tr>
								<th style="width: 20%; padding: 5px;">${processKey }结果：</th>
								<td style="width: 80%; padding: 5px;">
									${oneProcess.processResult }</td>
							</tr>
						</c:if>
						<tr>
							<th style="width: 20%; padding: 5px;">${processKey }意见：</th>
							<td style="width: 80%; padding: 5px;">
								${oneProcess.processComment }</td>
						</tr>
						<tr>
							<td colspan="2" align="right" style="width: 100%; padding: 5px;">${processKey
								}人： ${oneProcess.processUser } ${processKey }时间：
								&nbsp;&nbsp;&nbsp;&nbsp; ${oneProcess.processDate }</td>
						</tr>
					</c:forEach>
				</c:if>
			</c:forEach>
		</table>
	</c:if>
<c:if test="${empty FAULT_AUDIT_HISTORY_MAP}">
		没有可显示的内容。
</c:if>
</div>

