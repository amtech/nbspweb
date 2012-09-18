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
	function searchChecker() {
		var val = window
				.showModalDialog(//&lv=2
						'${ctx}/commonaccess!getstaff.action?flag=radio&orgtype=1&regionid=${user.regionId}',
						'',
						'status:no;center:yes;dialogWidth:400px;dialogHeight:300px;');
		var userName = "";
		var userId = "";
		if (val) {
			for (i = 0; i < val.length; i++) {
				userName += val[i].NAME + ",";
				userId += val[i].ID + ",";
			}
			userId = userId.substring(0, userId.length - 1);
			userName = userName.substring(0, userName.length - 1);
			jQuery("#auditUserName").val(userName);
			jQuery("#auditUserId").val(userId);
		}
	}
	function searchIssuerArea() {
		var val = window
				.showModalDialog(
						'${ctx}/commonaccess!getstaff.action?regionid=${user.regionId}&orgType=2',
						'',
						'status:no;center:yes;dialogWidth:400px;dialogHeight:300px;');
		var userName = "";
		var userId = "";
		if (val) {
			for (i = 0; i < val.length; i++) {
				userName += val[i].NAME + ",";
				userId += val[i].ID + ",";
			}
			userId = userId.substring(0, userId.length - 1);
			userName = userName.substring(0, userName.length - 1);
			jQuery("#issuerAreaUserNames").val(userName);
			jQuery("#issuerAreaUserIds").val(userId);
		}
	}

	jQuery(function() {
		jQuery("#isneedCheck").click(function() {
			if (jQuery("#isneedCheck").attr("checked")) {
				jQuery("#checkout").show();
			} else {
				jQuery("#checkout").hide();
			}
		});
	});

	function save(i) {
		jQuery("#status").val(i);
		var type = jQuery("#documentType").val();
		var checkedman = jQuery("#auditUserName").val();
		var IssuerArea = jQuery("#issuerAreaUserNames").val();
		var isneedCheck = jQuery("#isneedCheck").val();
		var expTime = jQuery("#expirationTime").val();
		var title = jQuery("#title").val();
		var s = i;
		if(checkedman==""){
			alert("请选择审核人!");
			return false;
		}
		if (checkedman == "") {
			jQuery("#isneedCheck").val("0");
		} else {
			jQuery("#isneedCheck").val("1");
		}
		if (s == 1) {
			if (title == "") {
				alert("标题需要输入!");
				jQuery("#title").focus();
				return false;
			}
			if (IssuerArea == "") {
				alert("请选择发布范围！");
				return false;
			}
			if (expTime == "") {
				alert("请选择限制时间！");
				return false;
			}
		}
		if (s == 0) {
			if (!confirm("确定要保存当前的联系函吗？")) {
				return false;
			}
		} else {
			if (!confirm("确定要提交当前的联系函吗？")) {
				return false;
			}
		}
		jQuery("#BCletterForm").submit();
		return true;
	}
	/**
	 * 刷新页面
	 */
	function breakPage() {
		window.location.href = "${ctx }/contactletter/contactletter!input.action?t="
				+ Math.random();
	}
		/**
	 * 返回
	 */
	function breakPage() {
		window.location.href = "${ctx }/contactletter/contactletter!query.action?t="
				+ Math.random();
	}
</script>
	</head>
	<body>
		<form action="${ctx }/contactletter/contactletter!save.action"
			id="BCletterForm" name="BCletterForm" method="post"
			enctype="multipart/form-data">
			<input type="hidden" id="status" name="entity.status"
				value="${entity.status}" />
			<input type="hidden" id="id" name="idff" value="${entity.id}" />
			<div class="title_bg">
				<div class="title">
					当前位置-新建业务联系函
				</div>
			</div>
			<div class="tabcontent">
				<table border="0" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<td colspan="4" align="right">
							<input type="hidden" name="isneedCheck" id="isneedCheck"
								alt="是否审核"
								<c:if test="${entity.isAudit==1}">checked="checked"</c:if>>
							<input type="checkbox" name="entity.isemegency" id="isemegency"
								<c:if test="${entity.isEmergency==1}">checked="checked"</c:if>
								value="1">
							是否紧急
							<input type="checkbox" id="isSend" value="1" name="entity.isSend"
								<c:if test="${entity.isSend==1}">checked="checked"</c:if>>
							短信提醒
						</td>
					</tr>
					<table border="0" align="center" cellpadding="3" cellspacing="0">
						<c:if test="${entity.documentNumber!='' }">
							<tr>
								<th>
									公文文号：
								</th>
								<td colspan="3">
									<input type="hidden" name="entity.documentNumber"
										id="documentNumber" class="inputtext" readonly="readonly"
										style="width: 500px" maxlength="25"
										value="${entity.documentNumber }" />
									${entity.documentNumber}
								</td>
							</tr>
						</c:if>
						<tr>
							<th>
								标题：
							</th>
							<td colspan="3">
								<input type="text" name="entity.title" id="title"
									value="${entity.title }" class="inputtext required"
									style="width: 400px" />
								<span style="color: red">*</span>
							</td>
						</tr>
						<tr>
							<th>
								发布人：
							</th>
							<td>
								${entity.releaseUserName }
								<input name="entity.releaseUserId" type="hidden"
									value="${entity.releaseUserId }">
								<input name="entity.releaseUserName" type="hidden"
									value="${entity.releaseUserName }">
							</td>
							<th>
								发布人部门：
							</th>
							<td>
								${entity.releaseUserDept }
								<input name="entity.releaseUserDept" type="hidden"
									value="${entity.releaseUserDept }">
							</td>
						</tr>
						<tr>
							<th>
								类型：
							</th>
							<td>
								<select name="entity.documentType" id=“documentType”
									style="width: 150px">
									<option value="1"
										<c:if test="${entity.documentType==1}">selected="selected"</c:if>>
										通告
									</option>
									<option value="2"
										<c:if test="${entity.documentType==2}">selected="selected"</c:if>>
										通知
									</option>
								</select>
							</td>
							<th>
								过期时限：
							</th>
							<td>
								<input type="text" id="expirationTime"
									name="entity.expirationTime" value="<fmt:formatDate value="${entity.expirationTime}"
								pattern="yyyy-MM-dd HH:mm:ss" />"
									readonly="readonly" class="Wdate required" style="width: 150px"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d'})" />
										<span style="color: red">*</span>
							</td>
						</tr>
						<tr>
							<th>
								附件：
							</th>
							<td colspan="3">
								<c:if test="${entity!=null }">
									<apptag:upload state="edit" entityId="${entity.id}"
										entityType="CONTACTLETTER_CLOB"></apptag:upload>
								</c:if>
								<c:if test="${entity==null }">
									<apptag:upload state="add"></apptag:upload>
								</c:if>
							</td>
						</tr>
						<tr id="checkout" style="display: ">
							<th>
								审核人：
							</th>
							<td colspan="3">
								<input id="auditUserName" name="entity.auditUserName"
									value="${entity.auditUserName }" class="inputtext"
									style="width: 55%" readonly="readonly" />
								<a href="javascript:searchChecker();"> <img border="0"
										src="${ctx}/css/images/selectcode.gif" /> </a>
								<input id="auditUserId" name="entity.auditUserId"
									value="${entity.auditUserId }" type="hidden">
								<span style="color: red">*</span>
							</td>
						</tr>
						<tr>
							<th>
								发布范围：
							</th>
							<td colspan="3">
								<input id="issuerAreaUserNames"
									name="entity.issuerAreaUserNames"
									value="${entity.issuerAreaUserNames }" class="inputtext"
									style="width: 55%" readonly="readonly" />
								<a href="javascript:searchIssuerArea();"> <img border="0"
										src="${ctx}/css/images/selectcode.gif" /> </a>
								<span style="color: red">*</span>
								<input id="issuerAreaUserIds" name="entity.issuerAreaUserIds"
									value="${entity.issuerAreaUserIds }" type="hidden">
							</td>
						</tr>

						<tr>
							<th>
								发布内容：
							</th>
							<td colspan="3">
								<textarea cols="200px" id="editor_v2"
									name="entity.releaseContent" rows="10">${entity.releaseContent}</textarea>
								<script type="text/javascript">
								CKEDITOR
										.replace(
												'editor_v2',
												{
													skin : 'v2',
													toolbar : 'MyToolbar',
													filebrowserUploadUrl : '${ctx}/ckeditor/uploader?Type=File',
													filebrowserImageUploadUrl : '${ctx}/ckeditor/uploader?Type=Image',
													filebrowserFlashUploadUrl : '${ctx}/ckeditor/uploader?Type=Flash'
												});
							</script>
							</td>
						</tr>

					</table>

					<div style="text-align: center; margin-top: 10px">
						<input type="button" onclick="save(1);" class="button" value="提交 ">
						<input type="button" onclick="save(0);" class="button" value="保存 ">
						<input type="button" class="button" value="重置"
							onclick="breakPage()"> 
					</div>
					</form>
	</body>
</html>
