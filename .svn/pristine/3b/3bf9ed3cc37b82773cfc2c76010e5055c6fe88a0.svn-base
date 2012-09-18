<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>导入浏览</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${ctx }/js/jQuery/jqgrid/css/ui.jqgrid.css"
			type="text/css" rel="stylesheet" />
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jqgrid/grid.locale-cn.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js"></script>
		<script>
	function cancel() {
		location = "${ctx}/monthcost/monthothercost!input.action";
	}
	jQuery(function() {
		//使用层布局
		var jqgrid = jQuery("#itemgrid")
				.jqGrid(
						{
							url : "${ctx}/monthcost/monthstatistical!previewList.action?type=${type}",
							datatype : "json",
							mtype : 'GET',
							rownumbers : true,
							colNames : [ '月份', '代维公司', '费用类型', '应付款', '实付款','款项依据' ],
							colModel : [ {
								name : 'months',
								id : 'months',
								sortable : false
							}, {
								name : 'contractorId',
								id : 'contractorId',
								sortable : false
							}, {
								name : 'typet',
								id : 'typet',
								sortable : false
							}, {
								name : 'shouldMoney',
								id : 'shouldMoney',
								sortable : false
							}, {
								name : 'factMoney',
								id : 'factMoney',
								sortable : false
							} , {
								name : 'remark',
								id : 'remark',
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
					当前位置-浏览其他费用导入明细
				</div>
			</div>
		</div>
		<form action="${ctx }/monthcost/monthstatistical!importData.action">
			<input type="hidden" name="filePath" value="${other_IMPORT_FILEPATH}" />
			<input type="hidden" name="type" value="${type}" />
			<div style="height: 10px"> </div>
			<div style="padding-left: 20px">
				<input type="submit" class="button" value="确认导入"/>
				<input type="button" class="button" value="取消导入" onclick="cancel()"/>
			</div>
		</form>
		<br>
		<div id="content" align="center" style="padding-top: 2px">
			<table id="itemgrid"></table>
			<div id="itempager"></div>
		</div>

	</body>
</html>