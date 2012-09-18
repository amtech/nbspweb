<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>导入巡检项浏览</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script>
	function cancel() {
		location = "${ctx}/ah/ratingFormItemImportAction!input.action?";
	}
	jQuery(function() {
		//使用层布局
		var jqgrid = jQuery("#itemgrid")
				.jqGrid(
						{
							url : "${ctx}/ah/ratingFormItemImportAction!previewList.action?",
							datatype : "json",
							mtype : 'GET',
							rownumbers : true,
							jsonReader : {
								root : "root",
								id : "item",
								repeatitems : false
							},
							colNames : [ '考核项目', '权重', '基本值', '挑战值',
									'指标说明和计算公式', '评价标准' ],
							colModel : [ {
								name : 'item',
								id : 'item',
								sortable : false
							}, {
								name : 'weight',
								id : 'weight',
								sortable : false,
								width : 100
							}, {
								name : 'baseValue',
								id : 'baseValue',
								sortable : false,
								width : 100
							}, {
								name : 'challengeValue',
								id : 'challengeValue',
								sortable : false,
								width : 100
							}, {
								name : 'normRemark',
								id : 'normRemark',
								sortable : false,
								width : 200
							}, {
								name : 'evaluationCriterion',
								id : 'evaluationCriterion',
								sortable : false,
								width : 200
							} ],
							autowidth : true
						});
		jQuery(window).wresize(function() {
			grid_resize(jqgrid);
		});
		grid_resize(jqgrid);
	});
</script>
	</head>
	<body>
		<div>
			<div class="title_bg">
				<div id="title" class="title">
					当前位置-导入考核表浏览
				</div>
			</div>
		</div>
		<form
			action="${ctx }/ah/ratingFormItemImportAction!importData.action">
			<input type="hidden" name="filePath"
				value="${PATROLITEM_IMPORT_FILEPATH}" />
			<input type="hidden" name="title" value="${ratingForm.title}" />
			<input type="hidden" name="businessType"
				value="${ratingForm.businessType}" />
			<input type="hidden" name="remark" value="${ratingForm.remark}" />
			<div align="center">
				<input type="submit" class="button" value="确认导入">
				<input type="button" class="button" value="取消导入" onclick="cancel()">
			</div>
			<div id="content" align="center" style="padding-top: 2px">
				<table id="itemgrid"></table>
			</div>
		</form>
	</body>
</html>