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
		<script type="text/javascript" src="${ctx}/js/timefmt.js"></script>
		<script src="${ctx}/css/jquery-ct-ui-custom.css" type="text/css"
			rel="stylesheet"></script>
		<script type="text/javascript">
	jQuery(function() {
		setContextPath('${ctx }');
		var view = "";
		//使用层布局
		var jqgrid = jQuery("#bcontactlettergrid")
				.jqGrid(
						{
							url : "${ctx}/contactletter/contactletter!listdate4handList.action",
							datatype : "json",
							mtype : 'POST',
							rownumbers : true,
							colNames : [ '系统时间', '文号', '主题', '发布人', '类型',
									'派发时间', '有期时限', '是否紧急', 'taskid',
									'statusName', '状态', '操作' ],
							colModel : [
									{
										name : 'SYSDATE',
										id : 'SYSDATE',
										sortable : false,
										hidden : true
									},
									{
										name : 'DOCUMENTNUMBER',
										id : 'DOCUMENTNUMBER',
										sortable : false,
										width : 250,
										formatter : function(value, options,
												rData) {
											if (rData.EXPIRATIONTIME < rData.SYSDATE) {

												return "<img src='${ctx}/css/images/contactletter/4.png'/> "
														+ rData['DOCUMENTNUMBER'];
											} else {
												return rData['DOCUMENTNUMBER'];
											}
										}
									},
									{
										name : 'TITLE',
										id : 'TITLE',
										sortable : false,
										width : 180,
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
										width : 60,
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
										name : 'TYPE',
										id : 'TYPE',
										sortable : false,
										width : 60,
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
										formatter : function(value, options,
												rData) {
											if (rData.ISEMERGENCY == '紧急') {
												return "<font color=red> "
														+ rData['EXPIRATIONTIME']
														+ "</font>";
											} else {
												return "<font color=black> "
														+ rData['EXPIRATIONTIME']
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
										name : 'TASKID',
										id : 'TASKID',
										hidden : true,
										sortable : false
									},
									{
										name : 'STATUS',
										id : 'STATUS',
										hidden : true,
										sortable : false
									},
									{
										name : 'STATUSNAME',
										id : 'STATUSNAME',
										sortable : false,
										width : 50,
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
										name : 'ID',
										id : 'ID',
										sortable : false,
										formatter : ActionThisFmatter,
										width : 50
									} ],
							rowNum : 10,
							autowidth : true,
							rowList : [ 10, 20, 30 ],
							pager : '#bcontactletterpager',
							shrinkToFit : false,
							viewrecords : true,
							hidegrid : false,
							afterInsertRow : function(rowid, aData) {
								switch (aData.ISEMERGENCY) {
								case '普通':
									jQuery("#bcontactlettergrid").jqGrid(
											'setCell', rowid, 'DOCUMENTNUMBER',
											'', {
												color : 'black'
											});
									break;
								case '紧急':
									jQuery("#bcontactlettergrid").jqGrid(
											'setCell', rowid, 'DOCUMENTNUMBER',
											'', {
												color : 'red'
											});
									break;
								}
							},
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

	// 公告操作列
	function ActionThisFmatter(cellvalue, options, rowObjec) {
		var view = "";
		if (rowObjec.STATUS == '0' || rowObjec.STATUS == '3') {
			view = view
					+ " <a href=javascript:toEdit('"
					+ cellvalue
					+ "')><img src='${ctx}/css/image/edit.png' title='修改'/></a>";
		} else {
			view = view
					+ " <a href=javascript:toCheck('"
					+ cellvalue
					+ "','"
					+ rowObjec.TASKID
					+ "')><img src='${ctx}/css/image/process.png' title='审核'/></a>";
		}
		return view;
	}
	function toCheck(idValue, taskid) { // 审核信息
		self.location = "${ctx}/contactletter/contactletter!toCheck.action?taskId="
				+ taskid + "&id=" + idValue;
	}
	// 编辑
	function toEdit(idValue) {
		var url = "${ctx}/contactletter/contactletter!input.action?id="
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
		<form id="Form" name="Form">
			<div id="header">
				<div class="title_bg">
					<div class="title">
						当前位置-待办业务联系函
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
							<font>发布开始日期：</font>
							<input type="text" name="beginDate"
								class="Wdate ui-div-list-search" id="begindate"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
							<font>发布结束日期：<font> <input type="text"
										name="endDate" class="Wdate  ui-div-list-search" id="enddate"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /> <input
										type="button" onclick="query();" class="button" value="查询">
						</li>
				</div>
				<div id="content" align="center" style="padding-top: 2px">
					<div align="left" style="padding-left: 20px">
						<font color="red">注意:紅色表示紧急</font>
						<img src='${ctx}/css/images/contactletter/4.png' />
						已过期
					</div>
					<table id="bcontactlettergrid"></table>
					<div id="bcontactletterpager"></div>
				</div>
		</form>
	</body>
</html>

