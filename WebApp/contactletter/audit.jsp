<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html>
	<head>
		<link href="${ctx }/js/WdatePicker/skin/WdatePicker.css"
			type="text/css" rel="stylesheet" />
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jvalidation/jquery.validate.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jvalidation/jquery.validate.cn.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jvalidation/messages_cn.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jvalidation/additional-methods.min.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jvalidation/jquery.validate.ex.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/WdatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/js/ckeditor/ckeditor.js"></script>
		<script type="text/javascript">
	function issubmit() {
		if (!confirm("确定要提交当前的联系函吗？")) {
			return false;
		}
		jQuery("#auditForm").submit();
		return true;
	}
	/**
	 * 刷新页面
	 */
	function breakPage() {
		window.location.href = "${ctx }/contactletter/contactletter!toCheck.action?id=${entity.id}&t="
				+ Math.random();
	}
		function breakPage2() {
		window.location.href = "${ctx }/contactletter/contactletter!waithandledlist.action?t="
				+ Math.random();
	}
</script>
	</head>
	<body>
		<form action="${ctx }/contactletter/contactletter!audit.action"
			id="auditForm" name="auditForm" method="post">
			<input id='workflowTaskId' name="contactLetterAudit.taskId"
				value="${ contactLetterAudit.taskId}" type="hidden" />
			<input id="id" name="id" type="hidden" value="${entity.id }" />
			<div class="title_bg">
				<div class="title">
					当前位置-审批业务联系函
				</div>
			</div>
			<div class="tabcontent">
				<table border="0" align="center" cellpadding="3" cellspacing="0">
					<table border="0" align="center" cellpadding="3" cellspacing="0">
						<tr>
							<th>
								公文文号：
							</th>
							<td>
								${entity.documentNumber}
							</td>
							<th>
								发布时间：
							</th>
							<td>
								<fmt:formatDate value="${entity.releaseTime}"
									pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
						</tr>
						<tr>
							<th>
								标题：
							</th>
							<td colspan="3">
								${entity.title }
							</td>
						</tr>
						<tr>
							<th>
								发布人：
							</th>
							<td>
								${entity.releaseUserName }
							</td>
							<th>
								发布人部门：
							</th>
							<td>
								${entity.releaseUserDept }
							</td>
						</tr>
						<tr>
							<th>
								类型：
							</th>
							<td>
								${entity.documentType }
							</td>
							<th>
								过期时限：
							</th>
							<td>
								<fmt:formatDate value="${entity.expirationTime}"
									pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
						</tr>
						<tr>
							<th>
								发布范围：
							</th>
							<td colspan="3" width="600px">
								${entity.issuerAreaUserNames }
							</td>
						</tr>
						<tr>
							<th>
								附件：
							</th>
							<td colspan="3">
								<apptag:upload state="look" entityId="${entity.id}"
									entityType="CONTACTLETTER_CLOB"></apptag:upload>
							</td>
						</tr>
						<tr>
							<th>
								发布内容：
							</th>
							<td colspan="3">
								<c:out value="${entity.releaseContent}" escapeXml="false"></c:out>
							</td>
						</tr>
						<c:if test="${!empty PROCESS_HISTORY_MAP}">
							<c:forEach var="oneProcessMap" items="${PROCESS_HISTORY_MAP}">
								<c:set var="processKey" value="${oneProcessMap.key}"></c:set>
								<c:set var="processList" value="${oneProcessMap.value}"></c:set>
								<c:forEach var="oneProcess" items="${oneProcessMap.value}">
									<c:if
										test="${oneProcess.processResult!=null && processKey=='审核'}">
										<tr>
											<th>
												(历史)${processKey }结果：
											</th>
											<td colspan="3">
												${oneProcess.processResult }
											</td>
										</tr>
										<tr>
											<th>
												${processKey }说明：
											</th>
											<td colspan="3">
												${oneProcess.processComment }
											</td>
										</tr>
										<tr>
											<th colspan="4" align="left"
												style="width: 100%; padding: 5px;">
												${processKey }人： ${oneProcess.processUser } ${processKey
												}时间： &nbsp;&nbsp;&nbsp;&nbsp; ${oneProcess.processDate }
											</th>
										</tr>
									</c:if>
								</c:forEach>
							</c:forEach>
						</c:if>
						<tr>
							<th>
								审批结果：
							</th>
							<td colspan="3">
								<select id="isAuditing" name="contactLetterAudit.isAuditing">
									<option value="pass">
										审批通过
									</option>
									<option value="reject">
										驳回
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>
								审批意见：
							</th>
							<td colspan="3">
								<textarea rows="5" cols="50" id="auditMsg"
									name="contactLetterAudit.auditMsg"></textarea>
							</td>
						</tr>
					</table>
					<div style="text-align: center; margin-top: 10px">
						<input type="button" onclick="issubmit();" class="button"
							value="提交 " />
						<input type="button" class="button" value="重置"
							onclick="breakPage()" />
						<input type="button" class="button" value="返回"
							onclick="breakPage2()" />
					</div>
					</form>
	</body>
</html>
