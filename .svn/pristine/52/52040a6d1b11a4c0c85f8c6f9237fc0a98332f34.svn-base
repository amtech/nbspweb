<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="now" class="java.util.Date" />
<%@ include file="/common/taglibs.jsp"%>
<c:forEach var="itemtype" items="${informationList}" end="8">
	<c:choose>
		<c:when test="${'B20'==itemtype.TYPE }">
			<div>
				<li class="gg" style="text-overflow:ellipsis;"><a href="#"
					title="${itemtype.TITLE}" onclick="viewNotice('${itemtype.ID}')">
						<c:choose>
							<c:when test="${fn:length(itemtype.TITLE)>8}">
								<c:out value="${fn:substring(itemtype.TITLE,0,8)}..." />
							</c:when>
							<c:otherwise>${itemtype.TITLE}</c:otherwise>
						</c:choose> </a><span style="float: right;font-size: 12px"> <img
						src="${ctx}/css/images/new.gif"> <fmt:formatDate
							value="${itemtype.ISSUEDATE}" pattern="MM/dd" /> </span></li>
			</div>
		</c:when>
		<c:when test="${'B22'==itemtype.TYPE }">
			<div>
				<li class="xw"><a href="#" title="${itemtype.TITLE}"
					onclick="viewNotice('${itemtype.ID}')"><c:choose>
							<c:when test="${fn:length(itemtype.TITLE)>8}">
								<c:out value="${fn:substring(itemtype.TITLE,0,8)}..." />
							</c:when>
							<c:otherwise>${itemtype.TITLE}</c:otherwise>
						</c:choose>
				</a><span style="float: right;font-size: 12px"> <img
						src="${ctx}/css/images/new.gif"> <fmt:formatDate
							value="${itemtype.ISSUEDATE}" pattern="MM/dd" /> </span>
				</li>
			</div>
		</c:when>
	</c:choose>
</c:forEach>
