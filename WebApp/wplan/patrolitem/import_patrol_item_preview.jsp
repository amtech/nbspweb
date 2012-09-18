<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>导入巡检项浏览</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${ctx }/js/jQuery/jqgrid/css/ui.jqgrid.css"
			type="text/css" rel="stylesheet" />
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jqgrid/grid.locale-cn.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js"></script>
		<script>
	function cancel() {
		location = "${ctx}/wplan/patrolItemImportAction!input.action?businessType=${businessType}";
	}
	jQuery(function() {
		//使用层布局
		var jqgrid = jQuery("#itemgrid")
				.jqGrid(
						{
							url : "${ctx}/wplan/patrolItemImportAction!previewList.action?businessType=${businessType}",
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
		<div>
			<div class="title_bg">
				<div id="title" class="title">
					当前位置-导入巡检项浏览
				</div>
			</div>
		</div>
		<form action="${ctx }/wplan/patrolItemImportAction!importData.action">
			<input type="hidden" name="filePath"
				value="${PATROLITEM_IMPORT_FILEPATH}" />
			<input type="hidden" name="businessType" value="${businessType}" />
			<div align="center">
				<input type="submit" class="button" value="确认导入">
				<input type="button" class="button" value="取消导入" onclick="cancel()">
			</div>
		</form>
		<br>
		<div id="content" align="center" style="padding-top: 2px">
			<table id="itemgrid"></table>
			<div id="itempager"></div>
		</div>
	</body>
</html>