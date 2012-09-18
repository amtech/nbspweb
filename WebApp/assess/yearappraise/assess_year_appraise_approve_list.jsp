<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<body>
	<c:if test="${!empty PROCESS_HISTORY_MAP}">
		<table border="1" cellpadding="0" cellspacing="0" width="100%"
			class="tabout" style="border-collapse:collapse;">
			<c:forEach var="oneProcessMap"
				items="${PROCESS_HISTORY_MAP}">
				<c:set var="processKey" value="${oneProcessMap.key}"></c:set>
				<c:set var="processList" value="${oneProcessMap.value}"></c:set>
				<c:if test="${processKey=='省运维主任审核'||processKey=='省公司分管领导审核'}">
					<thead>
						<tr>
							<th class="tdl" style="width: 20%; padding: 5px;">
								审核历史
							</th>
							<th class="tdc" style="width: 80%; padding: 5px;">
							</th>
						</tr>
					</thead>
					<c:forEach var="oneProcess" items="${oneProcessMap.value}">
						<c:if test="${oneProcess.processResult!=null }">
							<tr>
								<th style="width: 20%; padding: 5px;">
									${processKey }结果：
								</th>
								<td style="width: 80%; padding: 5px;">
									${oneProcess.processResult }
								</td>
							</tr>
						</c:if>
						<tr>
							<th style="width: 20%; padding: 5px;">
								${processKey }意见：
							</th>
							<td style="width: 80%; padding: 5px;">
								${oneProcess.processComment }
							</td>
						</tr>
						<tr>
							<th colspan="2" align="right"
								style="width: 100%; padding: 5px;">
								${processKey }人： ${oneProcess.processUser } ${processKey }时间：
								&nbsp;&nbsp;&nbsp;&nbsp; ${oneProcess.processDate }
							</th>
						</tr>
					</c:forEach>
				</c:if>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${empty PROCESS_HISTORY_MAP}">
		没有可显示的内容。
	</c:if>
</body>	
</html>
