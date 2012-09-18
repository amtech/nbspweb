<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/meta.jsp"%>
<link href="${ctx}/css/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/js/in-min.js"
	autoload="true"
	core="${config.cdnurl}/cabletech/ui/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	var jQuery;
In.add('vm', {path : '${ctx}/js/jQuery/jvalidation/jquery.validate.min.js',type : 'js',charset : 'utf-8'});
In.add('vcn', {path : '${ctx}/js/jQuery/jvalidation/messages_cn.js',type : 'js',charset : 'utf-8',rely : [ 'vm' ]});
In.add('vex', {path : '${ctx}/js/jQuery/jvalidation/jquery.validate.ex.js',type : 'js',charset : 'utf-8',rely : [ 'vcn' ]});
In.add('vam', {path : '${ctx}/js/jQuery/jvalidation/additional-methods.min.js',type : 'js',charset : 'utf-8',rely : [ 'vex' ]});
In.add('common', {path : '${ctx}/common/js/common.js',type : 'js',charset : 'utf-8'});
In.ready('vam','common', function() {
		setContextPath("${ctx}");
	});
</script>
</head>
<body>
	<form action="${ctx }/assess/assessTemplateContentAction!save.action"
		id="templateContent" name="templateContent" method="post">
		<div class="title_bg">
			<div id="title" class="title">当前位置-编辑考核项</div>
		</div>
		<div class="tabcontent">
			<table border="0" align="center">
				<tr>
					<th>项目：</th>
					<td><input id="itemName" name="itemName" class="inputtext"
						value="${templatecontent.ITEM_NAME}" /> <input id="id" name="id"
						type="hidden" value="${templatecontent.ID}" /> <input
						id="tableid" name="tableid" type="hidden"
						value="${templatecontent.TABLE_ID}" /></td>
						<input id="itemId" name="itemId" class="inputtext"
						value="${templatecontent.ITEM_ID}"  type="hidden"/>
				</tr>
				<c:if test="${tableType!='03' }">
				<tr>
					<th>指标名称：</th>
					<td><input id="name" name="name" class="inputtext"
						value="${templatecontent.NAME}" /></td>
				</tr>
				<tr>
					<th>权重：</th>
					<td><input id="weight" name="weight" class="inputtext"
						value="${templatecontent.WEIGHT}" />
					</td>
				</tr>
				<tr>
					<th>基准值：</th>
					<td><input id="benchmarkValue" name="benchmarkValue"
						class="inputtext" value="${templatecontent.BENCHMARK_VALUE}" /></td>
				</tr>
				<tr>
					<th>挑战值：</th>
					<td><input id="challengeValue" name="challengeValue"
						class="inputtext" value="${templatecontent.CHALLENGE_VALUE}" />
				</tr>
				</c:if>
				<tr>
					<th>计算公式和要求：</th>
					<td><textarea id="demandDesc" name="demandDesc" rows="3"
							style="width: 80%">${templatecontent.DEMAND_DESC }</textarea></td>

				</tr>
				<tr>
					<th>评价标准：</th>
					<td><textarea id="evaluationCriterion"
							name="evaluationCriterion" rows="3" style="width: 80%">${templatecontent.EVALUATION_CRITERION }</textarea>
					</td>
				</tr>
			</table>
			<div style="text-align: center; margin-top: 10px">
				<input id="btnSave" value="保存" type="submit" class="button" /> <input
					name="btnReset" value="重置" type="reset" class="button"
					onclick="reset();" />
			</div>
		</div>
	</form>
</body>
</html>