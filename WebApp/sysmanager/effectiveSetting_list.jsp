<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx }/js/jQuery/jqgrid/css/ui.jqgrid.css" type="text/css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${ctx}/js/jQuery/jqgrid/grid.locale-cn.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/WdatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	jQuery(function() {
		setContextPath('${ctx }');
		//使用层布局
		var jqgrid = jQuery("#smsviliditygrid").jqGrid({
			url : "${ctx}/sysmanager/warn!esGetData.action",
			datatype : "json",
			mtype : 'POST',
			rownumbers : true,
			colNames : [ '工单类型', '提醒类型', '状态', '操作' ],
			colModel : [ {
				name : 'WORKORDER_TYPE',
				id : 'WORKORDER_TYPE',
				sortable : false
			}, {
				name : 'SMS_TYPE',
				id : 'SMS_TYPE',
				sortable : false
			}, {
				name : 'VALIDITY',
				id : 'VALIDITY',
				sortable : false
			}, {
				name : 'ID',
				id : 'ID',
				sortable : false,
				formatter : ActionFmatter
			} ],
			rowNum : 10,
			autowidth : true,
			rowList : [ 10, 20, 30 ],
			pager : '#smsviliditypager',
			shrinkToFit : true,
			viewrecords : true,
			hidegrid : false,
			prmNames : {
				page : "pageNo",
				rows : "rows",
				sort : "sidx",
				order : "sord"
			},
			jsonReader : {
				root : "result", // 数据行（默认为：rows） 
				page : "pageNo", // 当前页 
				total : "totalPages", // 总页数 
				records : "totalCount", // 总记录数 
				repeatitems : false,
				id : "ID"
			}
		}).navGrid('#smsviliditypager', {
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

	function ActionFmatter(cellvalue, options, rowObjec) {
		var view = "";
		if (rowObjec.VALIDITY == '关闭') {
			view = view
					+ " <a style='color: blue;text-decoration: underline;' href=javascript:toStartup('"
					+ cellvalue + "')>开启</a>";
		} else {
			view = view
					+ " <a style='color: blue;text-decoration: underline;' href=javascript:toShutdown('"
					+ cellvalue + "')>关闭</a>";
		}
		
		
		return view;
	}

	function toStartup(idValue) {
		if (confirm("确定开启该纪录？")) {
			var url = "${ctx}/sysmanager/warn!startup.action?id=" + idValue;
			self.location.replace(url);
		}
	}

	function toShutdown(idValue) { 
		if (confirm("确定关闭该纪录？")) {
			var url = "${ctx}/sysmanager/warn!shutdown.action?id=" + idValue;
			self.location.replace(url);
		}
	}
</script>
</head>
<body>
	<div id="header">
		<div class="title_bg">
			<div class="title">当前位置-有效性设置列表</div>
		</div>
	</div>
	<div id="content" align="center" style="padding-top: 2px">
		<table id="smsviliditygrid"></table>
		<div id="smsviliditypager"></div>
	</div>
</body>
</html>

