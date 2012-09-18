<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${ctx }/js/jQuery/jqgrid/css/ui.jqgrid.css"
			type="text/css" rel="stylesheet" />
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jqgrid/grid.locale-cn.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/WdatePicker/WdatePicker.js"></script>
		<script src="${ctx}/css/jquery-ct-ui-custom.css" type="text/css"
			rel="stylesheet"></script>
		<script type="text/javascript">
	jQuery(function() {
		setContextPath('${ctx }');
		//使用层布局
		var jqgrid = jQuery("#bcontactlettergrid")
				.jqGrid(
						{
							url : "${ctx}/contactletter/contactletter!listdate4query.action",
							datatype : "json",
							mtype : 'POST',
							rownumbers : true,
							colNames : [ '文号', '标题', '发布人', '重要程度', '类型',
									'发布日期', '过期时限', 'status', '状态',
									'releaseuserid', '操作' ],
							colModel : [
									{
										name : 'DOCUMENTNUMBER',
										id : 'DOCUMENTNUMBER',
										sortable : false,
										width : 200,
										formatter : function(value, options,
												rData) {
											if (rData.ISEMERGENCY == '紧急') {
												return "<font color=red> "
														+ rData['DOCUMENTNUMBER']
														+ "</font>";
											} else {
												return "<font color=black> "
														+ rData['DOCUMENTNUMBER']
														+ "</font>";
											}
										}
									},
									{
										name : 'TITLE',
										id : 'TITLE',
										sortable : false,
										width : 220,
										formatter : function(value, options,
												rData) {
											if (rData.ISEMERGENCY == '紧急') {
												return "<font color=red> "
														+ rData['TITLE']
														+ "</font>";
											} else {
												return "<font color=black> "
														+ rData['TITLE']
														+ "</font>";
											}
										}
									},
									{
										name : 'RELEASEUSERNAME',
										id : 'RELEASEUSERNAME',
										sortable : false,
										width : 90,
										formatter : function(value, options,
												rData) {
											if (rData.ISEMERGENCY == '紧急') {
												return "<font color=red> "
														+ rData['RELEASEUSERNAME']
														+ "</font>";
											} else {
												return "<font color=black> "
														+ rData['RELEASEUSERNAME']
														+ "</font>";
											}
										}

									},
									{
										name : 'ISEMERGENCY',
										id : 'ISEMERGENCY',
										sortable : false,
										hidden : true
									},
									{
										name : 'TYPE',
										id : 'TYPE',
										sortable : false,
										width : 90,
										formatter : function(value, options,
												rData) {
											if (rData.ISEMERGENCY == '紧急') {
												return "<font color=red> "
														+ rData['TYPE']
														+ "</font>";
											} else {
												return "<font color=black> "
														+ rData['TYPE']
														+ "</font>";
											}
										}
									},
									{
										name : 'RELEASETIME',
										id : 'RELEASETIME',
										sortable : false,
										width : 180,
										formatter : function(value, options,
												rData) {
											if (rData.ISEMERGENCY == '紧急') {
												return "<font color=red> "
														+ rData['RELEASETIME']
														+ "</font>";
											} else {
												return "<font color=black> "
														+ rData['RELEASETIME']
														+ "</font>";
											}
										}
									},
									{
										name : 'EXPIRATIONTIME',
										id : 'EXPIRATIONTIME',
										sortable : false,
										hidden : true
									},
									{
										name : 'STATUS',
										id : 'STATUS',
										sortable : false,
										hidden : true
									},
									{
										name : 'STATUSNAME',
										id : 'STATUSNAME',
										sortable : false,
										width : 80,
										formatter : function(value, options,
												rData) {
											if (rData.ISEMERGENCY == '紧急') {
												return "<font color=red> "
														+ rData['STATUSNAME']
														+ "</font>";
											} else {
												return "<font color=black> "
														+ rData['STATUSNAME']
														+ "</font>";
											}
										}
									}, {
										name : 'RELEASEUSERID',
										id : 'RELEASEUSERID',
										sortable : false,
										hidden : true
									}, {
										name : 'ID',
										id : 'ID',
										sortable : false,
										formatter : ActionFmatter,
										width : 100
									} ],
							rowNum : 10,
							autowidth : true,
							rowList : [ 10, 20, 30 ],
							pager : '#bcontactletterpager',
							shrinkToFit : false,
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
						}).navGrid('#bcontactletterpager', {
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
	})
	// 操作列
	function ActionFmatter(cellvalue, options, rowObjec) {
		var view = "";
		view = view
				+ " <a href=javascript:toView('"
				+ cellvalue
				+ "')><img src='${ctx}/css/image/view.png' title='查看'/></a>";
		if (rowObjec.RELEASEUSERID == '${user.personId}') {
			if (rowObjec.STATUS == '0' || rowObjec.STATUS == '3') {
				view = view
						+ "  <a href=javascript:toEdit('"
						+ cellvalue
						+ "')><img src='${ctx}/css/image/edit.png' title='修改'/></a>";
				view = view
						+ "  <a href=javascript:toDelete('"
						+ cellvalue
						+ "')><img src='${ctx}/css/image/delete.png' title='删除'/></a>";
			}
		}
		return view;
	}
	function toDelete(idValue) {
		if (confirm("确定删除该记录?")) {
			var url = "${ctx}/contactletter/contactletter!delete.action?id="
					+ idValue;
			self.location.replace(url);
		}
	}
	// 编辑
	function toEdit(idValue) {
		var url = "${ctx}/contactletter/contactletter!input.action?id="
				+ idValue;
		self.location = url;
	}
	function toView(idValue) {
		var url = "${ctx}/contactletter/contactletter!view.action?id="
				+ idValue;
		self.location = url;

	}
	function query() {
		jQuery("#bcontactlettergrid").jqGrid().setGridParam({
			mtype : 'POST',
			page : 1,
			postData : {
				documentNumber : jQuery("#documentNumber").val(),
				title : jQuery("#title").val(),
				beginDate : jQuery("#begindate").val(),
				endDate : jQuery("#enddate").val()
			}
		}).trigger("reloadGrid");

	}
</script>
	</head>
	<body>
		<form id="searchForm" name="searchForm">
			<div id="header">
				<div class="title_bg">
					<div class="title">
						当前位置-查询联系函
					</div>
				</div>
			</div>
			<div class="ui-div-list-search" style="width: 100%;">
				<ul class="searchul">
					<li class="searchli" style="height: 5px">
					</li>
				</ul>
				<ul class="searchul">
					<li class="searchli">
						<font>文号:</font>
						<input type="text" name="documentNumber" id="documentNumber"
							maxlength="20" />
						<font>标题:</font>
						<input type="text" name="title" id="title"
							class="ui-div-list-search" maxlength="20" />
						<font>开始日期：</font>
						<input type="text" name="beginDate"
							class="Wdate ui-div-list-search" id="begindate"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
						<font>结束日期：<font> <input type="text" name="endDate"
									class="Wdate  ui-div-list-search" id="enddate"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /> <input
									type="button" onclick="query();" class="button" value="查询">
					</li>
			</div>
			<div id="content" align="center" style="padding-top: 2px">
				<div align="left" style="padding-left: 20px">
					<font color="red">注意:紅色表示紧急</font>
				</div>
				<table id="bcontactlettergrid"></table>
				<div id="bcontactletterpager"></div>
			</div>
		</form>
	</body>
</html>

