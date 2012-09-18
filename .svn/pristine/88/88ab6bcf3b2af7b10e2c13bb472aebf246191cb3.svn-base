<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>导入巡检项结果</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script>
	function goBack() {
		location.href = "${ctx}/ah/ratingFormItemImportAction!input.action?";
	}
	jQuery(function() {
		//使用层布局
		var jqgrid = jQuery("#itemgrid")
				.jqGrid(
						{
							url : "${ctx}/ah/ratingFormItemImportAction!importDataList.action?",
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
							autowidth : true,
							shrinkToFit : true,
							viewrecords : true,
							hidegrid : false,
							prmNames : {
								page : "pageNo",
								rows : "rows",
								sort : "sidx",
								order : "sord"
							},
						});
		jQuery(window).wresize(function(){
			grid_resize(jqgrid);
		});
		grid_resize(jqgrid);
	});
</script>
	</head>
	<body>
		<c:if test="${sessionScope.import_data_map!=null}">
			<div class="ui-layout-north">
				<div class="title_bg">
					<div id="title" class="title">
						当前位置-导入考核表结果
					</div>
				</div>
				<div class="framecontentBottom">
					<table cellspacing="0" cellpadding="0" border="0" align="center">
						<tr>
							<th>
								导入巡检项数据总数：
							</th>
							<td>
								<c:out
									value="${sessionScope.import_data_map.total_cell_number }" />
							</td>
						</tr>
						<tr>
							<th>
								合法巡检项数据总数：
							</th>
							<td>
								<c:out
									value="${sessionScope.import_data_map.valid_cell_number }" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">不合法巡检项数据总数：</font>
							</th>
							<td>
								<font color="red"><c:out
										value="${sessionScope.import_data_map.invalid_cell_number }" />
								</font>
							</td>
						</tr>
						<tr>
							<th>
								合格率：
							</th>
							<td>
								<c:out value="${sessionScope.import_data_map.valid_ratio }" />
								%
							</td>
						</tr>
						<c:if test="${sessionScope.import_data_map.valid_ratio!='100.00'}">
							<tr>
								<td colspan="2" align="center">
									<input name="button" type="button" class=button value="返回"
										onclick="goBack();">
								</td>
							</tr>
						</c:if>
					</table>
				</div>
			</div>
			<div id="content" align="center" style="padding-top: 2px">
				<table id="itemgrid"></table>
			</div>
		</c:if>
	</body>
</html>