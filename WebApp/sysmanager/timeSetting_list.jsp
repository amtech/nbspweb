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
		var jqgrid = jQuery("#grid").jqGrid({
			url : "${ctx}/sysmanager/warn!timeGetData.action",
			datatype : "json",
			mtype : 'POST',
			rownumbers : true,
			colNames : [ '工单类型', '专业类型', '提醒类型', '设置时长（分钟）', '操作' ],
			colModel : [ {
				name : 'WORKORDER_TYPE',
				id : 'WORKORDER_TYPE',
				sortable : false
			}, {
				name : 'PROFESSION_TYPE',
				id : 'PROFESSION_TYPE',
				sortable : false
			}, {
				name : 'SMS_TYPE',
				id : 'SMS_TYPE',
				sortable : false
			}, {
				name : 'TIMEOUT_NUM',
				id : 'TIMEOUT_NUM',
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
			pager : '#pager',
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
		}).navGrid('#pager', {
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
		view = view
				+ " <a style='color: blue;text-decoration: underline;' href=javascript:toEdit('"
				+ cellvalue + "')>修改</a>";
		view = view
				+ " <a style='color: blue;text-decoration: underline;' href=javascript:toDelete('"
				+ cellvalue + "')>删除</a>";
		return view;
	}
	function toEdit(idValue) {
		var url = "${ctx}/sysmanager/warn!edit.action?id=" + idValue;
		location.href = url;
	}
	function toDelete(idValue) {
		if (confirm("确定删除该纪录？")) {
			var url = "${ctx}/sysmanager/warn!delete.action?id=" + idValue;
			self.location.replace(url);
		}
	}
	function query() {
		jQuery("#grid").jqGrid().setGridParam({
			postData : {
				smsType : jQuery("#smsType").val(),
				taskType : jQuery("#taskType").val(),
				businessType : jQuery("#businessType").val()
			}
		}).trigger("reloadGrid");

	}
	function add() {
		var url = "${ctx}/sysmanager/warn!add.action?" +<%=Math.random()%>;
		location.href = url;
	}
</script>
</head>
<body>
	<form id="searchForm" name="searchForm">
		<div id="header">
			<div class="title_bg">
				<div class="title">当前位置-提醒时间查询列表</div>
			</div>
			<div class="tabcontent">
				<table cellspacing="0" cellpadding="0" border="0" align="center">
					<tr>
						<th>工单类型</th>
						<td><baseinfo:dicselector name="taskType" id="taskType"
								columntype="TASK_TYPE" type="select"></baseinfo:dicselector></td>

						<th>提醒类型</th>
						<td><baseinfo:dicselector name="smsType" id="smsType"
								columntype="SMS_TYPE" type="select"></baseinfo:dicselector></td>
					</tr>
					<tr>
						<th>专业类型</th>
						<td><baseinfo:dicselector name="businessType"
								id="businessType" columntype="businesstype" type="select"></baseinfo:dicselector></td>
						<td colspan="2" align="center"><input type="button"
							onclick="query();" class="button" value="查询"> <input
							type="button" onclick="add();" class="button" value="新增">
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div id="content" align="center" style="padding-top: 2px">
			<table id="grid"></table>
			<div id="pager"></div>
		</div>
	</form>
</body>
</html>

