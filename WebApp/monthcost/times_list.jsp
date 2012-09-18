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
		var jqgrid = jQuery("#grid").jqGrid(
				{
					url : "${ctx}/monthcost/monthtimescost!listDate.action",
					datatype : "json",
					mtype : 'POST',
					rownumbers : true,
					colNames : [ '区域', '月份', '代维专业', '代维公司', '计次数量', '计次单价',
							'计次类型',/*  '应付款', */'实付款', '操作' ],
					colModel : [ {
						name : 'REGIONNAME',
						id : 'REGIONNAME',
						sortable : true
					}, {
						name : 'MONTHS',
						id : 'MONTHS',
						sortable : false
					}, {
						name : 'SPECIALTY',
						id : 'SPECIALTY',
						sortable : false
					}, {
						name : 'CONTRACTORNAME',
						id : 'CONTRACTORNAME',
						sortable : false,
						width:150
					}, {
						name : 'NUMBERS',
						id : 'NUMBERS',
						sortable : false
					}, {
						name : 'UNITPRICE',
						id : 'UNITPRICE',
						sortable : false
					}, {
						name : 'TYPET',
						id : 'TYPET',
						sortable : false
					}, {
						name : 'FACTMONEY',
						id : 'FACTMONEY',
						sortable : false
					}, {
						name : 'ID',
						id : 'ID',
						sortable : false,
						formatter : ActionFmatter,
						width : 120
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
	})

	/**
	 * 搜索区域树
	 */
	function searchRegion() {
		var val = showRegion(contextPath + '/commonaccess!getregion.action?');
		if (!!val) {
			jQuery("#regionId").val(val[0]);
			jQuery("#regionname").val(val[1]);
		}
	}

	/**
	 * 搜索代维公司树
	 */
	function searchContractor() {
		var businessType = jQuery("#specialty").val();
		var regionId = jQuery("#regionId").val();
		var url = contextPath + '/commonaccess!getorg.action?orgtype=2';
		if (businessType == '') {
			alert("请选择专业类型！");
			return;
		}
		url += "&businesstype=" + businessType;
		if (regionId != '') {
			url += "&regionid=" + regionId;
		}
		var val = showOrg(url);
		if (!!val) {
			jQuery("#contractorId").val(val[0]);
			jQuery("#contractorName").val(val[1]);
		}
	}
	// 操作列
	function ActionFmatter(cellvalue, options, rowObjec) {
		var view = "";
		view = view + " <a  href=javascript:toView('" + cellvalue
				+ "')><img src='${ctx}/css/image/view.png' title='查看'/></a>";
		view = view + "  <a  href=javascript:toEdit('" + cellvalue
				+ "')><img src='${ctx}/css/image/edit.png' title='修改'/></a>";
		view = view + "  <a  href=javascript:toDelete('" + cellvalue
				+ "')><img src='${ctx}/css/image/delete.png' title='删除'/></a> ";
		return view;
	}
	function toView(idValue) {
		var URL = "${ctx}/monthcost/monthtimescost!view.action?id=" + idValue;
		var myleft = (screen.availWidth - 650) / 2;
		var mytop = 100;
		var mywidth = 650;
		var myheight = 500;
		window
				.open(
						URL,
						"read_monthtimescost",
						"height="
								+ myheight
								+ ",width="
								+ mywidth
								+ ",status=1,resizable=no,toolbar=no,menubar=no,location=no,scrollbars=yes,top="
								+ mytop + ",left=" + myleft + ",resizable=yes");
	}
	function toDelete(idValue) {
		if (confirm("确定删除该纪录？")) {
			var url = "${ctx}/monthcost/monthtimescost!delete.action?id="
					+ idValue;
			self.location.replace(url);
		}
	}
	// 编辑
	function toEdit(idValue) {
		var url = "${ctx}/monthcost/monthtimescost!input.action?id=" + idValue;
		self.location = url;

	}
	function query() {
		jQuery("#grid").jqGrid().setGridParam({
			postData : {
				regionId : jQuery("#regionId").val(),
				contractorId : jQuery("#contractorId").val(),
				typet : jQuery("#typet").val(),
				months : jQuery("#months").val(),
				specialty : jQuery("#specialty").val()
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
						当前位置-计次费用查询列表
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
						<font>区域:</font>
						<input id="regionname" name="regionname" class="inputtext"
							readonly="readonly" style="width: 150px;" />
						<a href="javascript:searchRegion();"> <img border="0"
								src="${ctx}/css/images/selectcode.gif" /> </a>
						<input id="regionId" name="entity.regionId" type="hidden" />

						<font>代维公司:</font>
						<input id="contractorName" name="contractorName" class="inputtext"
							style="width: 150px;" type="text" />
						<a href="javascript:searchContractor();"> <img border="0"
								src="${ctx}/css/images/selectcode.gif" /> </a>
						<input type="hidden" name="entity.contractorId" id="contractorId" />
					</li>
					<li>
						<font>计次类型：</font>
						<baseinfo:dicselector name="entity.typet" id="typet"
							columntype="TIMESTYPE" type="select"></baseinfo:dicselector>
						<font>代维专业：</font>
						<baseinfo:customselector name="entity.specialty"
							data="${businessTypeMap }" isReversal="true"
							cssClass="inputtext required" id="specialty" style="width: 180px"
							keyValue=""></baseinfo:customselector>
						<font>月份：</font>
						<input type="text" id="months" name="entity.months"
							readonly="readonly" class="Wdate required" style="width: 150px"
							onfocus="WdatePicker({dateFmt:'yyyy-MM'})" />
						<input type="button" onclick="query();" class="button" value="查询">
					</li>
				</ul>
			</div>
		</form>
		<div id="content" align="center" style="padding-top: 2px">
			<table id="grid"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>

