<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/header.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link href="${ctx }/js/WdatePicker/skin/WdatePicker.css"
			type="text/css" rel="stylesheet" />
		<link href="${ctx }/js/jQuery/ztree/css/zTreeStyle/zTreeStyle.css"
			type="text/css" rel="stylesheet" />
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
		<script type="text/javascript"
			src="${ctx}/js/jQuery/ztree/jquery.ztree.all.min.js"></script>
		<script language="javascript" type="text/javascript">
	jQuery(function() {
		//启用Jquery验证
		jQuery("#patrolinfoForm").validate({
			focusInvalid : false
		});
	});
	// 获取巡检组
	function getPatrolGroup() {
		var val = showPatrolGroup('${ctx}/commonaccess!getpatrolgroup.action?orgtype=2');
		if (val) {
			jQuery("#patrolinfo_patrolgroupid").val(val[0]);
			jQuery("#patrolinfo_patrolgroupname").val(val[1]);
			change();
			getResource(val[0]);
		}
	}
	//提交数据
	function submitData() {
		return true;
	}

	//动态添加行---------------------------------------------------------------------------------------------------------------
	//添加处理人
	var processerIndex = 0;
	function addTrouble() {
		processerIndex++;
		var content = '<tr>';
		content += '<td style="width:10%">' + processerIndex + '</td>';
		content += '<td style="width:25%">';
		content += '<input name="devicetype" class="inputtext" /><span style="color: red">*</span>';
		content += '</td>';
		content += '<td style="width:20%">';
		content += '<input name="position" class="inputtext" /><span style="color: red">*</span>';
		content += '</td>';
		content += '<td style="width:20%">';
		content += '<input name="remark" class="inputtext" /><span style="color: red">*</span>';
		content += '</td>';
		content += '<td style="width:25%">';
		content += '<input name="expectendtime" class="inputtext" /><span style="color: red">*</span>';
		content += '</td>';
		content += '</tr>';
		$("#tableContainer").append(content);
	}
	//减少处理人
	function delProcesser() {
		if (1 == processerIndex)
			return;
		var row_index = document.getElementById("processer_" + processerIndex).rowIndex;
		document.getElementById("tableContainer").deleteRow(row_index);
		processerIndex--;
	}
</script>
	</head>
	<body>
			<div class="title_bg">
				<div id="title" class="title">
					当前位置-登记家庭宽带巡检记录查看
				</div>
			</div>
			<div class="tabcontent">
				<table style="width: 70%" border="0" align="center">
					<tr>
						<th>
							代维单位：
						</th>
						<td>
							<baseinfo:org displayProperty="ORGANIZENAME"
								id="${familyRecode['orgid'] }"></baseinfo:org>
						</td>
						<th>
							巡检人：
						</th>
						<td>
							<c:out value="${familyRecode['username'] }" />
						</td>
					</tr>
					<tr>
						<th>
							巡检开始时间：
						</th>
						<td>
						<c:out value="${familyRecode['starttime'] }" />
						</td>
						<th>
							巡检结束时间
						</th>
						<td>
							<c:out value="${familyRecode['endtime'] }" />
						</td>
					</tr>
					<tr>
						<th>
							覆盖用户数：
						</th>
						<td>
						<c:out value="${familyRecode['usernum'] }" />
						</td>
						<th>
							巡检覆盖范围：
						</th>
						<td>
						<c:out value="${familyRecode['range'] }" />
						</td>
					</tr>



					<tr>
						<td colspan="4">
							<b>发现隐患列表</b>
						</td>
					</tr>
				</table>
				<table id="tableContainer" style="width: 70%" border="0"
					align="center">
					<tr>
						<td style="width: 10%">
							序号
						</td>
						<td style="width: 25%">
							隐患设备类型
						</td>
						<td style="width: 20%">
							隐患位置
						</td>
						<td style="width: 20%">
							隐患描述
						</td>
						<td style="width: 25%">
							预计完成时间
						</td>
					</tr>
					<c:if test="${troubleList!=null}">
						<c:forEach items="${troubleList}" var="trouble" varStatus="tag">
							<tr>
						<td style="width: 10%">${tag.count }</td>
						<td style="width: 25%">
							<c:out value="${trouble['devicetype'] }" />
						</td>
						<td style="width: 20%">
							<c:out value="${trouble['position'] }" />
						</td>
						<td style="width: 20%">
							<c:out value="${trouble['remark'] }" />
						</td>
						<td style="width: 25%">
						<fmt:formatDate value="${trouble['expectendtime'] }"
							pattern="yyyy-MM-dd" />
						</td>
					</tr>
						</c:forEach>
					</c:if>
				</table>

				<div style="text-align: center; margin-top: 10px">
					<input id="btnSubmit" value="返回" type="button" class="button"
						onclick="javascript:history.go(-1)" />
				</div>
			</div>
	</body>
</html>