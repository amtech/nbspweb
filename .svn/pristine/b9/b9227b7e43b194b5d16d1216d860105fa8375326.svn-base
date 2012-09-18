<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<div class="list" align="center">
<table width="100%" cellpadding="0" cellspacing="0">
	<!-- 
	<tr>
		<td style="padding: 5px;background-color:#B8DDFC;" colspan="4"><font color="#058CFE"><b>回单历时&nbsp;&nbsp;</b></font></td>
	</tr>
	 -->
	<tr>
		<td class="tdl" style="width: 15%">总历时：</td>
		<td class="tdr" style="width: 35%">${total_result }</td>
		<td class="tdl" style="width: 15%">是否超时：</td>
		<td class="tdr" style="width: 35%">${is_over_time }</td>
	</tr>
	<tr>
		<td style="padding: 5px;" colspan="4">
			<div class="list">
				<table width="100%" cellpadding="0" cellspacing="0" class="list">
					<tr>
						<th style="width: 15%">序号</th>
						<th style="width: 15%">工单处理步骤</th>
						<th style="width: 70%">工单处理步骤历时</th>
					</tr>
					<c:forEach var="oneStepResult" items="${every_step_result }" varStatus="status">
						<tr>
							<td style="width: 15%">${status.index+1 }</td>
							<c:forEach var="result" items="${oneStepResult }" varStatus="status">
								<c:if test="${status.index==0 }">
									<td style="width: 15%">${result.key }</td>
									<td class="tdulleft" style="width: 70%">${result.value }</td>
								</c:if>
							</c:forEach>
						</tr>
					</c:forEach>
				</table>
			</div></td>
	</tr>
</table>
</div>
