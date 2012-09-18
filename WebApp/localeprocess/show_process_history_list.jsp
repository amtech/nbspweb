<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/header.jsp"%>
<script type="text/javascript">
	jQuery(function() {
		var url = "${WEBGIS_DEPLOYNAME}/gisextend!universalProcessLocus.action?";
		url += "id=${localeProcess.taskId}";
		url += "&type=${localeProcess.taskType}";
		url += "&userid=${LOGIN_USER.userId}";
		jQuery("#local_process_gis").attr("src", url);
	});
</script>
<div class="list" align="center">
<c:if test="${!empty LOCALE_PROCESS_LIST }">
	<table width="100%" cellpadding="0" cellspacing="0">
		<!-- 
		<tr>
			<td style="padding: 5px;">
				现场处理过程列表
			</td>
		</tr>
		 -->
		<tr>
			<td>
				<iframe id="local_process_gis" frameborder="0" scrolling="auto"
					height="480" width="600"></iframe>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="list">
					<tr class="list_table_title">
						<th width="30%">
							现场处理状态
						</th>
						<th width="40%">
							现场处理时间
						</th>
						<th width="30%">
							现场处理人
						</th>
					</tr>
					<c:forEach items="${LOCALE_PROCESS_LIST}" var="item">
						<tr>
							<td>
								${item.process_state_dis}
							</td>
							<td>
								${item.process_time_dis}
							</td>
							<td>
								${item.person_name}
							</td>
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
	</table>
</c:if>
<c:if test="${empty LOCALE_PROCESS_LIST }">
没有可显示的内容。
</c:if>
</div>
