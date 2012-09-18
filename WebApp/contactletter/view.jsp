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
	function breakPage() {
		window.location.href = "${ctx }/contactletter/contactletter!query.action?t="
				+ Math.random();
	}
</script>
	</head>
	<body>
		<div class="title_bg">
			<div class="title">
				当前位置-查看业务联系函
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
						<td>
							${entity.title }
						</td>
						<th>
							是否过期：
						</th>
						<td>
							${timeOutstr }
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
						<td colspan="3">
							<table width="100%" cellspacing="0" cellpadding="0"
								style="table-layout: fixed; word-break: break-all;">
								<tr>
									<td>
										${entity.issuerAreaUserNames }
									</td>
								</tr>
							</table>
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
											${processKey }结果：
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
											${processKey }人： ${oneProcess.processUser } ${processKey }时间：
											&nbsp;&nbsp;&nbsp;&nbsp; ${oneProcess.processDate }
										</th>
									</tr>
								</c:if>
							</c:forEach>
						</c:forEach>
					</c:if>
				</table>
				<div style="text-align: center; margin-top: 10px" class="Close">
					<input type="button" class="button" value="返回"
						onclick="breakPage()" />
				</div>
	</body>
</html>
