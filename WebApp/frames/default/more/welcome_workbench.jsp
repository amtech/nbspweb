<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<c:set var="count" value="${fn:length(mainmenulist)}"></c:set>
<c:forEach var="itemmenu" items="${mainmenulist}" varStatus="cou">
	<c:if test="${cou.count eq 1 || (cou.count-1) % 3 eq 0}">
		<div>
	</c:if>
	<li class="m_module"><c:choose>
				<c:when test="${empty itemmenu.ITEMCLS}">
					<div>
						<img src="${ctx}/css/images/icons/menu/ydkh.png" />
					</div>
				</c:when>
				<c:otherwise>
					<div>
						<img src="${ctx}/css/images/icons/menu/${itemmenu.ITEMCLS}" />
					</div>
				</c:otherwise>
			</c:choose><a href="#"
		onclick="viewDialog('${itemmenu.HREFURL}','${itemmenu.TEXT}')"><span>【${itemmenu.TEXT}】</span> </a></li>
	<c:if test="${cou.count % 3 eq 0 || cou.count eq count}">
		</div>
	</c:if>
</c:forEach>