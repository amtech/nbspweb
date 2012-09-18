<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>巡检项信息查询</title>
		<script type="text/javascript">
	jQuery(function() {
		//使用层布局
		var jqgrid = jQuery("#itemgrid").jqGrid({
			url : "${ctx}/ah/ratingFormAction!listData.action?",
			datatype : "json",
			mtype : 'GET',
			rownumbers : true,
			colNames : [ '考核表名称', '专业', '状态', '创建人', '创建时间', '操作', '编号' ],
			colModel : [ {
				name : 'TITLE',
				id : 'TITLE',
				sortable : false
			}, {
				name : 'BUSINESS_TYPE_NAME',
				id : 'BUSINESS_TYPE_NAME',
				sortable : false
			}, {
				name : 'USE_STATE',
				id : 'USE_STATE',
				sortable : false,
				formatter : stateFormatter,
				hidden:true
			}, {
				name : 'USERNAME',
				id : 'USERNAME',
				sortable : false
			}, {
				name : 'CREATE_TIME_DIS',
				id : 'CREATE_TIME_DIS',
				sortable : false

			}, {
				name : 'ID_',
				id : 'ID_',
				sortable : false,
				formatter : opFormatter
			}, {
				name : 'ID',
				id : 'ID',
				sortable : false,
				hidden : true
			} ],
			autowidth : true,
			shrinkToFit : true,
			viewrecords : true,
			hidegrid : false,
			pager : '#itempager',
			rowNum : 15,
			rowList : [ 15, 20, 30 ],
			multiselect : true,
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
				id : "ID"
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
	function addOneForm() {
		location.href = "${ctx}/ah/ratingFormItemImportAction!input.action";
	}
	function stateFormatter(cellValue, options, rowObject) {
		var view;
		if (cellValue == "1") {
			view = "启用";
		} else {
			view = "作废";
		}
		return view;
	}
	function opFormatter(cellValue, options, rowObject) {
		var view = "";
		view += "<a style='color: blue;text-decoration: underline;' href=javascript:viewItem('"
				+ rowObject.ID + "')>查看</a>&nbsp;";
		view += "<a style='color: blue;text-decoration: underline;' href=javascript:deleteItem('"
				+ rowObject.ID + "')>删除</a>";
		return view;
	}
	function viewItem(id) {
		var url = "${ctx}/ah/ratingFormAction!view.action?&id=" + id;
		location.href = url;
	}
	function deleteItem(id) {
		var url = "${ctx}/ah/ratingFormAction!delete.action?";
		if (typeof (id) == "undefined") {
			var grid = jQuery("#itemgrid");
			ids = grid.jqGrid('getGridParam', 'selarrrow');
			if (ids.length > 0) {
				var names = [];
				for ( var i = 0; i < ids.length; i++) {
					var row = grid.jqGrid('getRowData', ids[i]);
					url += "&id=" + row.ID;
				}
			}
		} else {
			url += "&id=" + id;
		}
		if (confirm("确定要删除这些考核表信息吗？")) {
			location.href = url;
		}
	}
</script>
	</head>
	<body>
		<form id="optForm" method="post"
			action="${ctx }/ah/ratingFormAction!list.action">
			<div>
				<div class="title_bg">
					<div id="title" class="title">
						当前位置-考核表信息查询
					</div>
				</div>
			</div>
			<br>
			<div align="left">
				<input name="button" type="button" class="button"
					onclick="addOneForm();" value="添加">
				&nbsp;
				<input name="button" type="button" class="button"
					onclick="deleteItem();" value="删除">
				&nbsp;
			</div>
			<div id="content" align="center" style="padding-top: 2px">
				<table id="itemgrid"></table>
				<div id="itempager"></div>
			</div>
		</form>
	</body>
</html>