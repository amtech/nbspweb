<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/meta.jsp"%>
<link href="${ctx}/css/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/js/in-min.js"
	autoload="true"
	core="${config.cdnurl}/cabletech/ui/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
In.add('jqui',{path:'${ctx}/js/jQuery/jqueryui/css/redmond/jquery-ui.custom.css'});
In.add('jquijs',{path:'${ctx}/js/jQuery/jqueryui/jquery-ui.custom.min.js',type:'js',charset:'utf-8',rely:['jqui']});
In.add('jgcss',{path:'${ctx}/js/jQuery/jqgrid/css/ui.jqgrid.css',charset:'utf-8',rely:['jquijs']});
In.add('jgjs',{path:'${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js',type:'js',charset:'utf-8',rely:['jgcss']});
In.add('jgcn',{path:'${ctx}/js/jQuery/jqgrid/grid.locale-cn.js',type:'js',charset:'utf-8',rely:['jgjs']});
In.add('jwresize',{path:'${ctx}/js/jQuery/jresize/jquery.wresize.js',type:'js',charset:'utf-8'});
In.add('common',{path:'${ctx}/common/js/common.js',type:'js',charset:'utf-8',rely:['jwresize']});
In.ready('jgcn','common',function() {
		//使用层布局
		var jqgrid = jQuery("#templategrid").jqGrid({
			url : "${ctx }/assess/assessTemplateAction!listdata.action",
			datatype : "json",
			mtype : 'GET',
			rownumbers : true,
			colNames : [ '模版名称', '专业', '类型','状态', '创建人', '创建时间', '操作'],
			colModel : [ {
				name : 'TABLE_NAME',
				id : 'TABLE_NAME',
				sortable : false
			}, {
				name : 'BUSINESS_TYPENAME',
				id : 'BUSINESS_TYPENAME',
				sortable : false
			}, {
				name : 'TABLE_TYPENAME',
				id : 'TABLE_TYPENAME',
				sortable : false
			}, {
				name : 'TABLE_STATENAME',
				id : 'TABLE_STATENAME',
				sortable : false,
				hidden:true
			}, {
				name : 'USERNAME',
				id : 'USERNAME',
				sortable : false
			}, {
				name : 'CREATE_DATE',
				id : 'CREATE_DATE',
				sortable : false

			}, {
				name : 'ID',
				id : 'ID',
				sortable : false,
				formatter : opFormatter
			}],
			autowidth : true,
			shrinkToFit : true,
			viewrecords : true,
			hidegrid : false,
			pager : '#templatepager',
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
		}).navGrid('#templatepager', {
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
	//添加模板
	function addTemplate() {
		location.href = "${ctx}/assess/assessTemplateImportAction!input.action";
	}
	function opFormatter(cellValue, options, rowObject) {
		var view = "";
		view += "<a style='color: blue;text-decoration: underline;' href=javascript:editItem('"
				+ rowObject.ID + "')>编辑</a>&nbsp;";
		view += "<a style='color: blue;text-decoration: underline;' href=javascript:delItem('"
				+ rowObject.ID + "')>删除</a>";
		return view;
	}
	//编辑
	function editItem(id) {
		var url = "${ctx}/assess/assessTemplateAction!input.action?id=" + id;
		location.href = url;
	}
	// 单条删除模板
	function delItem(id) {
		var con = confirm("确定要删除该条记录?");
		if (!!con) {
			location = "${ctx}/assess/assessTemplateAction!delete.action?id="
					+ id;
		}

	}
	//批量删除模板
	function deleteTemplate(){
		var s=""; 
		s = jQuery("#templategrid").jqGrid('getGridParam','selarrrow');
		if (s.length==0) {
			alert('请至少选择一条记录！');
			return false;
		}
		var con = confirm("确定要批量作废这些记录吗?");
		if (!!con) {
		location.href = contextPath
				+ "/assess/assessTemplateAction!delete.action!id=" + s
		}
	}

</script>
</head>
<body>
	<form id="optForm">
		<div>
			<div class="title_bg">
				<div id="title" class="title">当前位置-考核表模版管理</div>
			</div>
		</div>
		<br>
		<div align="left">
			<input name="button" type="button" class="button"
				onclick="addTemplate();" value="添加"> &nbsp; <input
				name="button" type="button" class="button"
				onclick="deleteTemplate();" value="删除"> &nbsp;
		</div>
		<div id="content" align="center" style="padding-top: 2px">
			<table id="templategrid"></table>
			<div id="templatepager"></div>
		</div>
	</form>
</body>
</html>