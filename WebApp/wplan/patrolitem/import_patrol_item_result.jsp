<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>导入巡检项结果</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${ctx }/js/jQuery/jqgrid/css/ui.jqgrid.css"
			type="text/css" rel="stylesheet" />
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jqgrid/grid.locale-cn.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js"></script>
		<script>
	function exportFile() {
		location.href = "${ctx}/wplan/patrolItemImportAction!exportInvalidItemData.action";
	}
	function goBack() {
		location.href = "${ctx}/wplan/patrolItemImportAction!input.action?businessType=${businessType}";
	}
	jQuery(function() {
		//使用层布局
		var jqgrid = jQuery("#itemgrid")
				.jqGrid(
						{
							url : "${ctx}/wplan/patrolItemImportAction!importDataList.action?",
							datatype : "json",
							mtype : 'GET',
							rownumbers : true,
							colNames : [ '维护对象', '维护检测项目', '周期', '质量标准',
									'输入类型', '值域范围', '异常状态', '默认值' ],
							colModel : [ {
								name : 'itemName',
								id : 'itemName',
								sortable : false
							}, {
								name : 'subitemName',
								id : 'subitemName',
								sortable : false
							}, {
								name : 'cycleText',
								id : 'cycleText',
								sortable : false
							}, {
								name : 'qualityStandard',
								id : 'qualityStandard',
								sortable : false
							}, {
								name : 'inputTypeText',
								id : 'inputTypeText',
								sortable : false
							}, {
								name : 'valueScope',
								id : 'valueScope',
								sortable : false
							}, {
								name : 'exceptionValue',
								id : 'exceptionValue',
								sortable : false
							}, {
								name : 'defaultValue',
								id : 'defaultValue',
								sortable : false
							} ],
							rowNum:15,
							autowidth : true,
							shrinkToFit : true,
							viewrecords : true,
							hidegrid : false,
							pager : '#itempager',
							prmNames : {
								page : "pageNo",
								rows : "rows",
								sort : "sidx",
								order : "sord"
							},
							jsonReader : {
								root : "result",
								page : "pageNo",
								total : "totalPages",
								records : "totalCount",
								repeatitems : false,
								id : "0"
							}
						}).navGrid('#itempager', {
					edit : false,
					add : false,
					del : false,
					search : false,
					sortable : false
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
						当前位置-导入巡检项结果
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
									<input name="button" type="button" class="button_length"
										style="width: 120px" value="导出不合法巡检项" onclick="exportFile();">
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
				<div id="itempager"></div>
			</div>
		</c:if>
	</body>
</html>