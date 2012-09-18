<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link href="${ctx}/css/base.css" rel="stylesheet" type="text/css" />
		<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx }/js/in-min.js"
			autoload="true"
			core="${config.cdnurl}/cabletech/ui/js/jquery-1.7.2.min.js"></script>
		<script language="javascript" type="text/javascript">
In.add('vm',{path:'${ctx}/js/jQuery/jvalidation/jquery.validate.min.js',type:'js',charset:'utf-8'});
In.add('vcn',{path:'${ctx}/js/jQuery/jvalidation/messages_cn.js',type:'js',charset:'utf-8',rely:['vm']});
In.add('vex',{path:'${ctx}/js/jQuery/jvalidation/jquery.validate.ex.js',type:'js',charset:'utf-8',rely:['vcn']});
In.add('vam',{path:'${ctx}/js/jQuery/jvalidation/additional-methods.min.js',type:'js',charset:'utf-8',rely:['vex']});
In.add('common',{path:'${ctx}/common/js/common.js',type:'js',charset:'utf-8'});
	function initPage() {
		jQuery("#inputForm").validate({
			focusInvalid: false
		});
	}
	jQuery(function() {
		initPage();
	});
</script>
	</head>
	<body>
		<form action="${ctx }/wplan/noPatrolStationAction!confirm.action" id="inputForm" name="inputForm" method="post">
			<div class="title_bg">
				<div id="title" class="title">
					当前位置-拆分纠纷站点登记确认
				</div>
			</div>
			<div class="tabcontent">
				<table border="0" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<th><b>站点情况：</b></th>
						<th colspan="3"></th>
					</tr>
					<tr>
						<th>专业：</th>
						<td>
							<baseinfo:dicselector name="" columntype="businesstype" type="view" keyValue="${noPatrolStation.resourceType }"></baseinfo:dicselector>
						</td>
						<th>问题类型：</th>
						<td>
							<baseinfo:dicselector name="" columntype="NOPATROLSTATION_PROBLEM_TYPE" type="view" keyValue="${noPatrolStation.problemType }"></baseinfo:dicselector>
						</td>
					</tr>
					<tr>
						<th>计划名称：</th>
						<td>${noPatrolStation.planName }</td>
						<th>站点名称：</th>
						<td>${noPatrolStation.stationName }</td>
					</tr>
					<tr>
						<th>上报时间：</th>
						<td><fmt:formatDate value="${noPatrolStation.recordDate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<th>上报人：</th>
						<td>${noPatrolStation.recorderName }</td>
					</tr>
					<tr>
						<th>问题描述：</th>
						<td colspan="3">${noPatrolStation.cause }</td>
					</tr>
					<tr>
						<th>
							确认结果：
						</th>
						<td colspan="3">
							<input id="noPatrolStation_id" name="noPatrolStation.id" value="${noPatrolStation.id }" type="hidden">
							<input id="result_yes" name="noPatrolStation.result" value="0" type="radio" checked>
							通过
							<input id="result_no" name="noPatrolStation.result" value="1" type="radio">
							不通过
						</td>
					</tr>
					<tr>
						<th>
							确认意见：
						</th>
						<td colspan="3">
							<textarea id="remark" name="noPatrolStation.remark" class="inputtext" style="width: 660px; height: 80px;"></textarea>
						</td>
					</tr>
				</table>
				<div style="text-align: center; margin-top: 10px">
					<input name="btnSubmit" value="提交" type="submit" class="button" />
					<input name="btnReset" value="重置" type="reset" class="button" />
					<input name="btnReturn" value="返回" type="button" onclick="history.go(-1);" class="button" />
				</div>
			</div>
		</form>
	</body>
	<script type="text/javascript">
	function check() {
		return true;
	}
	</script>
</html>