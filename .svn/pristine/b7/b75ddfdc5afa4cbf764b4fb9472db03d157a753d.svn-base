<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script language="javascript" type="text/javascript">
</script>
<style type="text/css"> 
</style>
</head>
<body>
<c:forEach var="item" items="${mainMenulist}">
	<div class="menu_group">
		<div class="block-title">
			<h2>
				<strong>${item.text}</strong>
			</h2>
		</div>
		<ul class="m_menu">
			<c:set var="subWaitHandledNumberMap"
				value="${waitHandledNumMap[item.text] }"></c:set>
			<c:forEach var="subMap" items="${subMenuMap}">
				<c:if test="${item.id==subMap.key}">
					<c:forEach var="subMenu" items="${subMap.value}">
						<li class="m_module"><a href="${subMenu.HREFURL}"> <c:choose>
									<c:when test="${empty subMenu.ITEMCLS}">
										<img src="${ctx}/css/images/icon.png" />
									</c:when>
									<c:otherwise>
										<img src="${ctx}/css/images/icons/menu/${subMenu.ITEMCLS}" />
									</c:otherwise>
								</c:choose>
								<p class="mtitle" title="${subMenu.TEXT}">
									<span class="text_default">【${subMenu.TEXT}】</span></br>
									<c:set var="numberMap"
										value="${subWaitHandledNumberMap[subMenu.TEXT] }"></c:set>
									<c:if test="${numberMap.wait_handled_number>0 }">
								<span class="text_red">${numberMap.wait_handled_number }项待办</span>
								</c:if>
								</p> </a></li>
					</c:forEach>
				</c:if>
			</c:forEach>
		</ul>
	</div>
</c:forEach>
</body>
</html>