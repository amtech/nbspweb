<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${ctx}/js/jQuery/galleria/galleria-1.2.8.min.js"></script>
<style>

/* Demo styles */

.content {
	color: #777;
	font: 12px/1.4 "helvetica neue", arial, sans-serif;
	width: 820px;
	margin: 20px auto;
}

h1 {
	font-size: 12px;
	font-weight: normal;
	color: #ddd;
	margin: 0;
}

p {
	margin: 0 0 15px
}

a {
	color: #22BCB9;
	text-decoration: none;
}

.cred {
	margin-top: 20px;
	font-size: 11px;
}

/* This rule is read by Galleria to define the gallery height: */
#_processphotos {
	height: 520px
}
</style>
<div class="list" align="center">
	<c:if test="${!empty LOCALE_PROCESS_PHOTOS_LIST }">
		<div id="_processphotos">
			<c:forEach var="oneImage"
				items="${sessionScope.LOCALE_PROCESS_PHOTOS_LIST}">
				<c:set var="fileId" value="${oneImage.fileid}"></c:set>
				<c:set var="savePath" value="${oneImage.savepath}"></c:set>
				<a href="${ctx }/imageServlet?imageId=${fileId }"><img
					data-title="现场照片" data-description="${oneImage.description}"
					src="${ctx }/imageServlet?imageId=${fileId }" /> </a>
			</c:forEach>
		</div>
		<script type="text/javascript">
			var contextPath="${ctx}";
				jQuery(document).ready(function (){
					  Galleria.loadTheme('${ctx}/js/jQuery/galleria/galleria.classic.min.js');
					  Galleria.run('#_processphotos');
				});
		</script>
	</c:if>
	<c:if test="${empty LOCALE_PROCESS_PHOTOS_LIST }">
没有可显示的照片。
</c:if>
</div>
