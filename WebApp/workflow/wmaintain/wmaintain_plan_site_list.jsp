<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${ctx }/js/jQuery/jqgrid/css/ui.jqgrid.css"
			type="text/css" rel="stylesheet" />
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jqgrid/grid.locale-cn.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js"></script>
		<title></title>
		<script>
	jQuery(function() {
		loadLayout();
		jQuery(window).wresize(function() {
			grid_resize(jqgrid);
		});
		grid_resize(jqgrid);
	})
	/**
	 * 获取维修作业计划中维护站点的标题栏
	 * @return 维修作业计划中维护站点的标题栏
	 */
	function getColumnNames() {
		return [ 'ID', '站点名称', '站点地点' ];
	}
	/**
	 * 获取维修作业计划中维护站点异常项及处理结果的标题栏
	 * @return 维修作业计划中维护站点异常项及处理结果的标题栏
	 */
	function getSubColumnNames() {
		return [ 'ID', '巡检项', '问题' ];
	}
	/**
	 * 获取维修作业计划中维护站点的数据值
	 * @return 维修作业计划中维护站点的数据值
	 */
	function getColumnModels() {
		return [ {
			name : 'ID',
			id : 'ID',
			sortable : false,
			hidden : true
		}, {
			name : 'RS_NAME',
			id : 'RS_NAME',
			width:100,
			sortable : false
		}, {
			name : 'ADDRESS',
			id : 'ADDRESS',
			width:150,
			sortable : false
		} ]
	}
	/**
	 * 获取维修作业计划中维护站点异常项及处理结果的数据值
	 * @return 维修作业计划中维护站点异常项及处理结果的数据值
	 */
	function getSubColumnModels() {
		return [ {
			name : 'ID',
			id : 'ID',
			sortable : false,
			hidden : true
		}, {
			name : 'SUBITEM_NAME',
			id : 'SUBITEM_NAME',
			width:100,
			sortable : false
		}, {
			name : 'SUBITEM_PATROL',
			id : 'SUBITEM_PATROL',
			width:70,
			sortable : false
		} ];
	}
	/**
	 * 载入层布局
	 */
	function loadLayout() {
		var jqgrid = jQuery("#plansitegrid")
				.jqGrid(
						{
							url : "${ctx}/workflow/wmaintainSiteAction!mainList.action?id=${plan.id}",
							datatype : "json",
							mtype : 'GET',
							rownumbers : true,
							colNames : getColumnNames(),
							colModel : getColumnModels(),
							autowidth : true,
							shrinkToFit : true,
							viewrecords : true,
							hidegrid : false,
							jsonReader : {
								root : "root",
								repeatitems : false,
								id : "0"
							},
							subGrid : true,
							subGridRowExpanded : function(subgrid_id, row_id) {
								var subgrid_table_id; //子表格ID
								subgrid_table_id = subgrid_id + "_t";

								// 动态添加子报表的table和pager  
								jQuery("#" + subgrid_id)
										.html(
												"<table id='"+subgrid_table_id+"'class='scroll'></table>");

								// 创建jqGrid对象  
								jQuery("#" + subgrid_table_id)
										.jqGrid(
												{
													url : "${ctx}/workflow/wmaintainSiteAction!subList.action",
													datatype : "json",
													postData : {
														id : row_id
													},
													colNames : getSubColumnNames(),
													colModel : getSubColumnModels(),
													jsonReader : {
														root : "root",
														repeatitems : false,
														id : "0"
													},
													prmNames : {
														search : "search"
													},
													viewrecords : true,
													height : "100%"
												});
							}
						});
	}
</script>
	</head>
	<body>
		<form id="planSiteForm" name="planSiteForm">
			<div id="content" align="center" style="padding-top: 2px">
				<table id="plansitegrid"></table>
			</div>
		</form>
	</body>

</html>