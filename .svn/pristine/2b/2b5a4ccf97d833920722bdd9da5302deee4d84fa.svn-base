<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html>
<head>
<link href="${ctx }/js/WdatePicker/skin/WdatePicker.css" type="text/css"
	rel="stylesheet" />
<!--jvalidation  -->
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
	jQuery(function() {
		jQuery("#noticeForm").validate({
			focusInvalid : false,
			debug : true,
			submitHandler : function(form) {
				if (isValidForm()) {
					form.submit();
				}
			}
		});
		changeType("${notice.type}");
	});
</script>
</head>
<body>
	<form action="${ctx }/system/notice!save.action" id="noticeForm"
		name="noticeForm" method="post" enctype="multipart/form-data">
		<div class="title_bg">
			<div class="title">当前位置-信息发布</div>
		</div>
		<div class="tabcontent">
			<table border="0" align="center" cellpadding="3" cellspacing="0">
				<tr>
					<th>标题：</th>
					<td colspan="3"><input type="hidden" name="id"
						value="${notice.id }" /> <input type="hidden" name="isCanceled"
						value="${notice.isCanceled }" /> <input type="hidden"
						name="oldMeetTime" value="${notice.meetTime }" /> <input
						type="hidden" name="oldMeetEndTime" value="${notice.meetEndTime }" />
						<input type="hidden" name="oldMeetAddress"
						value="${notice.meetAddress }" /> <input type="hidden"
						name="oldMeetPerson" value="${notice.meetPerson }" /> <input
						name="isissue" id="isissue" type="hidden" value="y" /> <input
						type="text" name="title" id="title" value="${notice.title }"
						class="inputtext required" style="width: 680px" maxlength="25" />
						<span style="color: red">*</span></td>
				</tr>
				<tr>
					<th>类型：</th>
					<td><baseinfo:dicselector name="type" columntype="INFORMATION" style="width:220px;"
							type="select" id="notice_type" onChange="changeType(this.value);"
							keyValue="${notice.type }"></baseinfo:dicselector></td>
					<th>重要程度：</th>
					<td>
					<c:set var="oneGradeSelected" value=""></c:set>
					<c:set var="twoGradeSelected" value=""></c:set>
					<c:if test="${notice.grade=='一般' }"><c:set var="oneGradeSelected" value="selected"></c:set></c:if>
					<c:if test="${notice.grade=='重要' }"><c:set var="twoGradeSelected" value="selected"></c:set></c:if>
					<select name="grade" class="inputtext"
						style="width: 220px">
							<option value="一般" ${oneGradeSelected }>一般</option>
							<option value="重要" ${twoGradeSelected }>重要</option>
					</select></td>
				</tr>
				<tr id="meetTimeTr" style="display: none;">
					<th>会议开始时间：</th>
					<td><input type="text" id="meetTime" name="meetTime"
						value="${notice.meetTime }" readonly="readonly"
						class="Wdate required" style="width: 220px"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'2000/1/1'})" />
					</td>
					<th>会议结束时间：</th>
					<td><input type="text" id="meetEndTime" name="meetEndTime"
						value="${notice.meetEndTime }" readonly="readonly"
						class="Wdate required" style="width: 220px"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:$('meetTime')})" />
					</td>
				</tr>
				<tr id="meetAddressTr" style="display: none;">
					<th>会议地点：</th>
					<td colspan="3"><input type="text" name="meetAddress"
						class="inputtext required" style="width: 680px"
						value="${notice.meetAddress }" /></td>
				</tr>
				<tr id="acceptUserTr">
					<th>发布对象：</th>
					<td colspan="3"><input id="acceptUserNames"
						name="acceptUserNames" value="${notice.meetPersonName }"
						class="inputtext" style="width: 680px" readonly="readonly" /> <a
						href="javascript:search();"> <img border="0"
							src="${ctx}/css/images/selectcode.gif" /> </a> <span
						style="color: red">*</span> <input id="acceptUserIds"
						name="acceptUserIds" value="${notice.acceptUserIds }"
						type="hidden"></td>
				</tr>
				<tbody id="sendSmTbody" style="display: ;">
					<tr class=trcolor>
						<th>短信发送方式：</th>
						<td colspan="3"><input name="sendMethod" value="0"
							type="radio" checked
							onclick="hideTimeInput();" />
							即时发送 <input name="sendMethod" value="1" type="radio"
							onclick="showTimeInput();" />
							定时发送</td>
					</tr>
					<tr class=trcolor id="timeInputSpan" style="display: none;">
						<th>短信发送时间：</th>
						<td colspan="3"><input type="text" id="beginDate" name="beginDate"
							value="${notice.beginDate}" readonly="readonly" class="Wdate"
							style="width: 100px"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'2000-1-1'})" />
							到 <input type="text" id="endDate" name="endDate"
							value="${notice.endDate}" readonly="readonly" class="Wdate"
							style="width: 100px"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:$('beginDate')})" />
						</td>
					</tr>
					<tr class=trcolor id="timeSpaceTr" style="display: none;">
						<th>短信发送间隔：</th>
						<td colspan="3"><input name="sendTimeSpace"
							value="${notice.sendTimeSpace}" type="text" style="width: 100px" />
							<input name="sendIntervalType" value="-1" type="radio" checked />
							单次 <input name="sendIntervalType" value="0" type="radio" /> 秒 <input
							name="sendIntervalType" value="1" type="radio" /> 分 <input
							name="sendIntervalType" value="2" type="radio" /> 时 <input
							name="sendIntervalType" value="3" type="radio" /> 天</td>
					</tr>
				</tbody>
				<tr>
					<th>附件：</th>
					<td colspan="3"><apptag:upload state="edit" entityId="${id}"
							entityType="NOTICE_CLOB"></apptag:upload></td>
				</tr>
				<tr>
					<th>内容：</th>
					<td colspan="3"><textarea cols="200px" id="editor_v2"
							name="contentString" rows="10">${notice.contentString}</textarea>
						<script type="text/javascript">
	CKEDITOR.replace('editor_v2', {
		skin : 'v2',
		toolbar : 'MyToolbar',
		filebrowserUploadUrl : '${ctx}/ckeditor/uploader?Type=File',
		filebrowserImageUploadUrl : '${ctx}/ckeditor/uploader?Type=Image',
		filebrowserFlashUploadUrl : '${ctx}/ckeditor/uploader?Type=Flash'
	});
</script></td>
				</tr>
			</table>
		</div>
		<div style="text-align: center; margin-top: 10px">
			<input type="button" onclick="issue();" class="button" value="立即发布 ">
			<input type="button" onclick="save();" class="button" value="保存 ">
			<input type="button" class="button" value="重置" onclick="breakPage()">
			<input type="button" class="button" value="返回"
				onclick="history.back()">
		</div>
	</form>
<script type="text/javascript">
	function search() {
		var val = window.showModalDialog('${ctx}/commonaccess!getstaff.action',
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
			jQuery("#acceptUserNames").val(userName);
			jQuery("#acceptUserIds").val(userId);
		}
	}
	function isValidForm() {
		var patrolmanSon = jQuery("#acceptUserIds").val();
		if (jQuery("#notice_type").val() != "B22") {
			if (patrolmanSon == "") {
				alert("发布对象不能为空！");
				return false;
			}
		}
		return true;
	}
	function changeType(value) {
		if (value == "B21") {
			jQuery("#meetTimeTr").show();
			jQuery("#meetAddressTr").show();
			jQuery("#sendSmTbody").show();
			jQuery("#acceptUserTr").show();
		} else if (value == "B20") {
			jQuery("#meetTimeTr").hide();
			jQuery("#meetAddressTr").hide();
			jQuery("#sendSmTbody").show();
			jQuery("#acceptUserTr").show();
		} else {
			jQuery("#meetTimeTr").hide();
			jQuery("#meetAddressTr").hide();
			jQuery("#sendSmTbody").hide();
			jQuery("#acceptUserTr").hide();
		}
	}
	function issue() {
		jQuery("#isissue").val('y');
		jQuery("#noticeForm").submit();
	}
	function save() {
		jQuery("#isissue").val('n');
		jQuery("#noticeForm").submit();
	}
	/**
	 * 刷新页面
	 */
	function breakPage() {
		window.location.href = "${ctx }/system/notice!input.action?id=${notice.id}&t="
				+ Math.random();
	}
	function showTimeInput(){
		jQuery("#timeInputSpan").show();
		jQuery("#timeSpaceTr").show();
	}
	function hideTimeInput(){
		jQuery("#timeInputSpan").hide();
		jQuery("#timeSpaceTr").hide();
	}
</script>
	<c:if test="${notice.sendIntervalType=='-1'}">
		<script type="text/javascript">
	document.noticeForm.sendIntervalType[0].checked = true;
</script>
	</c:if>
	<c:if test="${sendIntervalType=='0'}">
		<script type="text/javascript">
	document.noticeForm.sendIntervalType[1].checked = true;
</script>
	</c:if>
	<c:if test="${sendIntervalType=='1'}">
		<script type="text/javascript">
	document.noticeForm.sendIntervalType[2].checked = true;
</script>
	</c:if>
	<c:if test="${sendIntervalType=='2'}">
		<script type="text/javascript">
	document.noticeForm.sendIntervalType[3].checked = true;
</script>
	</c:if>
	<c:if test="${sendIntervalType=='3'}">
		<script type="text/javascript">
	document.noticeForm.sendIntervalType[4].checked = true;
</script>
	</c:if>
	<c:if test="${sendMethod=='0'}">
		<script type="text/javascript">
	document.noticeForm.sendMethod[0].checked = true;
	hideTimeInput();
</script>
	</c:if>
	<c:if test="${sendMethod=='1'}">
		<script type="text/javascript">
	document.noticeForm.sendMethod[1].checked = true;
	showTimeInput();
</script>
	</c:if>
</body>
</html>
