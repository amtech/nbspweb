<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div id="mystatistictab" class="mytab">
	<ul>
		<li class="selected"><a href="#patrol">今日巡检结果</a></li>
		<li><a href="#wtrouble">今日处理故障</a></li>
	</ul>
	<div id="patrol">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="datatable">
			<thead>
				<tr>
					<th>名称</th>
					<th>基站</th>
					<th>铁塔</th>
					<th>综合覆盖</th>
					<th>集客</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${empty flag}">
						<c:forEach var="patrolitem" items="${patrolstatistic}">
							<tr>
								<td>${patrolitem.NAME}</td>
								<td><c:choose>
										<c:when test="${empty patrolitem.TJC31}">
									--
								</c:when>
										<c:otherwise>
								${patrolitem.TJC31}
								</c:otherwise>
									</c:choose>
								</td>
								<td><c:choose>
										<c:when test="${empty patrolitem.TJC33}">
									--
								</c:when>
										<c:otherwise>
								${patrolitem.TJC33}
								</c:otherwise>
									</c:choose></td>
								<td><c:choose>
										<c:when test="${empty patrolitem.TJC32}">
									--
								</c:when>
										<c:otherwise>
								${patrolitem.TJC32}
								</c:otherwise>
									</c:choose></td>
								<td><c:choose>
										<c:when test="${empty patrolitem.TJC34}">
									--
								</c:when>
										<c:otherwise>
								${patrolitem.TJC34}
								</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach var="patrolitem" items="${patrolstatistic}">
							<tr>
								<td>${patrolitem.NAME}</td>
								<td><c:choose>
										<c:when test="${empty patrolitem.TJC31}">
									--
								</c:when>
										<c:otherwise>
								${patrolitem.TJC31}
								</c:otherwise>
									</c:choose>
								</td>
								<td><c:choose>
										<c:when test="${empty patrolitem.TJC33}">
									--
								</c:when>
										<c:otherwise>
								${patrolitem.TJC33}
								</c:otherwise>
									</c:choose></td>
								<td><c:choose>
										<c:when test="${empty patrolitem.TJC32}">
									--
								</c:when>
										<c:otherwise>
								${patrolitem.TJC32}
								</c:otherwise>
									</c:choose></td>
								<td><c:choose>
										<c:when test="${empty patrolitem.TJC34}">
									--
								</c:when>
										<c:otherwise>
								${patrolitem.TJC34}
								</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
	<div id="wtrouble">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="datatable">
			<thead>
				<tr>
					<th>名称</th>
					<th>基站</th>
					<th>铁塔</th>
					<th>综合覆盖</th>
					<th>集客</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${empty flag}">
						<c:forEach var="wtroubleitem" items="${wtroublestatistic}">
							<tr>
								<td>${wtroubleitem.NAME}</td>
								<td><c:choose>
										<c:when test="${empty wtroubleitem.TJC31}">
									--
								</c:when>
										<c:otherwise>
								${wtroubleitem.TJC31}
								</c:otherwise>
									</c:choose>
								</td>
								<td><c:choose>
										<c:when test="${empty wtroubleitem.TJC33}">
									--
								</c:when>
										<c:otherwise>
								${wtroubleitem.TJC33}
								</c:otherwise>
									</c:choose></td>
								<td><c:choose>
										<c:when test="${empty wtroubleitem.TJC32}">
									--
								</c:when>
										<c:otherwise>
								${wtroubleitem.TJC32}
								</c:otherwise>
									</c:choose></td>
								<td><c:choose>
										<c:when test="${empty wtroubleitem.TJC34}">
									--
								</c:when>
										<c:otherwise>
								${wtroubleitem.TJC34}
								</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach var="wtroubleitem" items="${wtroublestatistic}">
							<tr>
								<td>${wtroubleitem.NAME}</td>
								<td><c:choose>
										<c:when test="${empty wtroubleitem.TJC31}">
									--
								</c:when>
										<c:otherwise>
								${wtroubleitem.TJC31}
								</c:otherwise>
									</c:choose>
								</td>
								<td><c:choose>
										<c:when test="${empty wtroubleitem.TJC33}">
									--
								</c:when>
										<c:otherwise>
								${wtroubleitem.TJC33}
								</c:otherwise>
									</c:choose></td>
								<td><c:choose>
										<c:when test="${empty wtroubleitem.TJC32}">
									--
								</c:when>
										<c:otherwise>
								${wtroubleitem.TJC32}
								</c:otherwise>
									</c:choose></td>
								<td><c:choose>
										<c:when test="${empty wtroubleitem.TJC34}">
									--
								</c:when>
										<c:otherwise>
								${wtroubleitem.TJC34}
								</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>

</div>
<script type="text/javascript">

In.add('jtabs',{path:'${ctx}/js/jQuery/jtabs/jquery.tabs.js'});
In.ready('jtabs',function(){
	 $("#mystatistictab ul").idTabs(); 
})

</script>