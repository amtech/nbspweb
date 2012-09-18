<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div id="mytab" class="mytab" style="overflow: auto;">
	<ul>
		<li class="selected"><a href="#workorder">工单</a></li>
		<c:choose>
			<c:when test="${LOGIN_USER.orgType=='1'}">
				<li><a href="#wtask">巡检任务</a></li>
				<li><a href="#contactletter">业务联系函</a></li>
			</c:when>
		</c:choose>
	</ul>
	<div id="workorder" style="height: 200px;overflow: auto;">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="datatable">
			<tr>
				<th width="50px">类型</th>
				<th>标题</th>
				<th width="110px">派单时间</th>
				<th width="110px">处理时限</th>
				<th width="80px">派单人</th>
				<th width="50px">操作</th>
			</tr>
			<c:choose>
				<c:when test="${empty flag}">
					<c:forEach var="item" items="${workordercontent}">
						<tr>
							<td><span
								<c:if test="${item.IS_OVERTIME=='Y'}"> style="color: red"</c:if>
								<c:if test="${item.IS_DEADLINE=='Y'}"> style="color: orange"</c:if>>
									<c:if test="${'workorder'==item.module }">${item.task_type_dis }</c:if>
									<c:if test="${'workorder'!=item.module }">${item.module_name}</c:if>
							</span></td>
							<td><span
								<c:if test="${item.IS_OVERTIME=='Y'}"> style="color: red"</c:if>
								<c:if test="${item.IS_DEADLINE=='Y'}"> style="color: orange"</c:if>>${item.title}</span>
							</td>
							<td><span
								<c:if test="${item.IS_OVERTIME=='Y'}"> style="color: red"</c:if>
								<c:if test="${item.IS_DEADLINE=='Y'}"> style="color: orange"</c:if>>${item.CREATE_DATE_DIS}</span>
							</td>
							<td><span
								<c:if test="${item.IS_OVERTIME=='Y'}"> style="color: red"</c:if>
								<c:if test="${item.IS_DEADLINE=='Y'}"> style="color: orange"</c:if>>${item.PROCESS_TIME_DIS}</span>
							</td>
							<td><span
								<c:if test="${item.IS_OVERTIME=='Y'}"> style="color: red"</c:if>
								<c:if test="${item.IS_DEADLINE=='Y'}"> style="color: orange"</c:if>>${item.CREATER_NAME}</span>
							</td>
							<td><a
								href="javascript:processworkorder('${item.access_url}','工单处理');">处理
							</a></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach var="item" items="${workordercontent}">
						<tr>
							<td width="15%"><span
								<c:if test="${item.IS_OVERTIME=='Y'}"> style="color: red"</c:if>
								<c:if test="${item.IS_DEADLINE=='Y'}"> style="color: orange"</c:if>>
									<c:if test="${'workorder'==item.module }">${item.task_type_dis }</c:if>
									<c:if test="${'workorder'!=item.module }">${item.module_name}</c:if>
							</span></td>
							<td width="45%"><a
								<c:if test="${item.IS_OVERTIME=='Y'}"> style="color: red"</c:if>
								<c:if test="${item.IS_DEADLINE=='Y'}"> style="color: orange"</c:if>
								href="javascript:processworkorder('${item.access_url}','工单处理');">${item.title}</a>
							</td>
							<td width="25%"><span
								<c:if test="${item.IS_OVERTIME=='Y'}"> style="color: red"</c:if>
								<c:if test="${item.IS_DEADLINE=='Y'}"> style="color: orange"</c:if>>${item.PROCESS_TIME_DIS}</span>
							</td>
							<td width="15%"><span
								<c:if test="${item.IS_OVERTIME=='Y'}"> style="color: red"</c:if>
								<c:if test="${item.IS_DEADLINE=='Y'}"> style="color: orange"</c:if>>${item.CREATER_NAME}</span>
							</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
	</div>
	<c:choose>
		<c:when test="${LOGIN_USER.orgType=='1'}">
			<div id="wtask" style="height: 200px;overflow: auto;">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="datatable">
					<thead>
						<tr>
							<th>计划名称</th>
							<th>巡检专业</th>
							<th>代维公司</th>
							<th>区域</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${empty flag}">
								<c:forEach var="planitem" items="${plancontent}">
									<tr>
										<td>${planitem.title}</td>
										<td>${planitem.businesstype}</td>
										<td>${planitem.ORGNAME}</td>
										<td>${planitem.REGIONNAME}</td>
										<td><a
											href="javascript:processworkorder('${planitem.access_url}','无线巡检计划');">处理</a>
										</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<c:forEach var="planitem" items="${plancontent}">
									<tr>
										<td>${planitem.title}</td>
										<td>${planitem.businesstype}</td>
										<td>${planitem.ORGNAME}</td>
										<td>${planitem.REGIONNAME}</td>
										<td><a
											href="javascript:processworkorder('${planitem.access_url}','无线巡检计划');">处理</a>
										</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
			<div id="contactletter" style="height: 200px;overflow: auto;">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="datatable">
					<thead>
						<tr>
							<th width="100px">文号</th>
							<th nowrap="nowrap">标题</th>
							<th width="100px">派单人</th>
							<th width="100px">处理期限</th>
							<th width="70px">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${empty flag}">
								<c:forEach var="letteritem" items="${lettercontent}">
									<tr>
										<td>${letteritem.DOCUMENTNUMBER}</td>
										<td>${letteritem.TITLE}</td>
										<td>${letteritem.RELEASEUSERNAME}</td>
										<td><fmt:formatDate value="${letteritem.EXPIRATIONTIME}"
												pattern="yyyy-MM-dd hh:mm:ss" />
										</td>
										<td><a
											href="javascript:processworkorder('${letteritem.access_url}','业务联系函处理');">处理</a>
										</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<c:forEach var="letteritem" items="${lettercontent}">
									<tr>
										<td>${letteritem.DOCUMENTNUMBER}</td>
										<td>${letteritem.TITLE}</td>
										<td>${planitem.RELEASEUSERNAME}</td>
										<td><fmt:formatDate value="${letteritem.EXPIRATIONTIME}"
												pattern="yyyy-MM-dd hh:mm:ss" />
										</td>
										<td><a
											href="javascript:processworkorder('${letteritem.access_url}','业务联系函处理');">处理</a>
										</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
		</c:when>
	</c:choose>
</div>

<script type="text/javascript">
	In.add('jtabs',{path:'${ctx}/js/jQuery/jtabs/jquery.tabs.js'});
	In.ready('jtabs',function(){
		 $("#mytab ul").idTabs(); 
		 
		 // <c:if test="${empty flag}">
			//提醒待办任务
		//	art.dialog.notice({
	    //	title: '提醒',
	    //	width: 220,// 必须指定一个像素宽度值或者百分比，否则浏览器窗口改变可能导致artDialog收缩
	    //	content: '<div id="tasktj"><div class="row-fluid"><span class="span12"> 您有${fn:length(workordercontent)}条待办工单 </span></div>',
	    //	icon:'face-smile'
		//	});
		// </c:if> 
	})
	
</script>