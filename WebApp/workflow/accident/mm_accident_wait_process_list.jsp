<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
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
In.add('jquicn',{path:'${ctx}/js/jQuery/jqueryui/zh/jquery.ui.datepicker-zh-CN.js',type:'js',charset:'utf-8',rely:['jquijs']});
In.add('jresize',{path:'${ctx}/js/jQuery/jresize/jquery.wresize.js',type:'js',charset:'utf-8',rely:['jquicn']});
In.add('jgcss',{path:'${ctx}/js/jQuery/jqgrid/css/ui.jqgrid.css',charset:'utf-8',rely:['jresize']});
In.add('jgjs',{path:'${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js',type:'js',charset:'utf-8',rely:['jgcss']});
In.add('jgcn',{path:'${ctx}/js/jQuery/jqgrid/grid.locale-cn.js',type:'js',charset:'utf-8',rely:['jgjs']});
In.add('common',{path:'${ctx}/common/js/common.js',type:'js',charset:'utf-8'});
In.add('wdatejs',{path:'${ctx}/js/WdatePicker/WdatePicker.js',type:'js',charset:'utf-8'});
In.add('layout',{path:'${ctx}/js/jQuery/layout/layout-default-latest.css'});
In.add('layoutjs',{path:'${ctx}/js/jQuery/layout/jquery.layout-latest.min.js',type:'js',charset:'utf-8',rely:['layout']});
	In.ready('jgcn','layoutjs', 'common', 'wdatejs', function() {
		setContextPath('${ctx }');
	accordionObj=jQuery("#accordion").accordion({
 		autoHeight:false,header: 'h3',navigation:true,
 		collapsible: true,active:1
	});
		//使用层布局
		var jqgrid = jQuery("#accidentgrid").jqGrid({
							url : "${ctx}/workflow/waitProcessMmAccidentAction!listData.action?",
							datatype : "json",mtype : 'GET',rownumbers : true,
							colNames : [ '资源名称', '隐患名称', '专业类型', '隐患描述','隐患分类', '隐患级别', '上报人', '上报时间', '操作' ],
							colModel : [ 
							{name : 'RES_NAME',id : 'RES_NAME',sortable : false}, 
							{name : 'NAME',id : 'NAME',sortable : false}, 
							{name : 'BUSINESS_TYPE_NAME',id : 'BUSINESS_TYPE_NAME',sortable : false}, 
							{name : 'REMARK',id : 'REMARK',sortable : false}, 
							{name : 'ACCIDENT_TYPE_DIS',id : 'ACCIDENT_TYPE_DIS',sortable : false}, 
							{name : 'ACCIDENT_LEVEL_NAME',id : 'ACCIDENT_LEVEL_NAME',sortable : false}, 
							{name : 'CREATER_NAME',id : 'CREATER_NAME',sortable : false}, 
							{name : 'CREATE_TIME_DIS',id : 'CREATE_TIME_DIS',sortable : false}, 
							{name : 'ID',id : 'ID',sortable : false,formatter : viewFormatter} 
							],
							rowNum : 10,autowidth : true,rowList : [ 10, 20, 30 ],pager : '#accidentpager',shrinkToFit : true,viewrecords : true,hidegrid : false,
							prmNames : {
								page : "pageNo",rows : "rows",sort : "sidx",order : "sord"
							},
							jsonReader : {
								root : "result",page : "pageNo",total : "totalPages", 
								records : "totalCount",repeatitems : false,id : "0"
							}
					}).navGrid('#accidentpager', {
					edit : false,add : false,del : false,search : false,sortable : false
				});
		jQuery(window).wresize(function() {grid_resize(jqgrid);});
		grid_resize(jqgrid);
	});
</script>
	</head>
	<body>
		<form id="searchForm" name="searchForm">
			<div id="header">
				<div class="title_bg">
					<div id="title" class="title">
						当前位置-待处理隐患信息列表
					</div>
				</div>
				<div id="accordion">
					<h3>
						<a href="#" id="accord">搜索条件</a>
					</h3>
					<div class="tabcontent">
						<table cellspacing="0" cellpadding="0" border="0" align="center">
							<tr>
								<th>
									隐患名称:
								</th>
								<td>
									<input type="text" class="inputtext" style="width: 220px" name="accident.name" id='accident_name' />
								</td>
								<th>
									上报时间:
								</th>
								<td>
									<input type="text" class="inputtext" style="width: 95px" id='accident_createTimeStart' name="accident.createTimeStart" class="Wdate inputtext" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'troubleTimeEnd\')}'})" />
									至
									<input id='accident_createTimeEnd' type="text" class="inputtext" style="width: 95px" name="accident.createTimeEnd" class="Wdate inputtext" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'troubleTimeStart\')}'})" />
								</td>
							</tr>
							<tr>
								<th>
									巡检组:
								</th>
								<td>
									<input id="patrolgroupname" name="patrolgroupname" class="inputtext" readonly="readonly" style="width: 220px" />
									<a href="javascript:getPatrolGroup();"> <img border="0" src="${ctx}/css/images/selectcode.gif" /> </a>
									<input id="accident_patrolGroupId" name="accident.patrolGroupId" type="hidden">
								</td>
								<th>
									上报人：
								</th>
								<td>
									<input id="accident_creater" name="accident.creater" class="inputtext" type="text" style="width: 220px;" />
								</td>
							</tr>
							<tr>
								<th>
									专业类型：
								</th>
								<td>
									<baseinfo:customselector name="accident.businessType" data="${businessTypeMap}" isReversal="true" isQuery="query" cssClass="inputtext" id="accident_businessType" style="width: 220px"></baseinfo:customselector>
								</td>
								<th>
									隐患类型：
								</th>
								<td>
									<input id="accident_accidentType" name="accident.accidentType" class="inputtext" type="text" style="width: 220px;" />
								</td>
							</tr>
							<tr>
								<td align="center" colspan="4">
									<input type="button" onclick="query();" class="button" value="查询" />
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div id="content" align="center">
				<table id="accidentgrid"></table>
				<div id="accidentpager"></div>
			</div>
		</form>
	</body>
	<script type="text/javascript">
/**
 * 获取巡检组
 */
function getPatrolGroup() {
	var val = showPatrolGroup(contextPath+'/commonaccess!getpatrolgroup.action?orgtype=2');
	if (val) {
		jQuery("#accident_patrolGroupId").val(val[0]);
		jQuery("#patrolgroupname").val(val[1]);
	}
}
function query() {
	jQuery("#accidentgrid").jqGrid().setGridParam({
		postData : {
			'accident.name' : jQuery("#accident_name").val(),
			'accident.businessType' : jQuery("#accident_businessType").val(),
			'accident.createTimeStart' : jQuery("#accident_createTimeStart").val(),
			'accident.createTimeEnd' : jQuery("#accident_createTimeEnd").val(),
			'accident.patrolGroupId' : jQuery("#accident_patrolGroupId").val(),
			'accident.creater' : jQuery("#accident_creater").val(),
			'accident.accidentType' : jQuery("#accident_accidentType").val()
	}}).trigger("reloadGrid");
}
function viewMmAccident(id){
	window.location.href = contextPath + '/workflow/mmAccidentAction!view.action?id=' + id;
}
function viewFormatter(cellValue,options,rowObject){
	var view = "  <a style='color: blue;text-decoration: underline;' href=javascript:viewMmAccident('"
			+ cellValue + "')>查看</a>";
	return view;
}
	</script>
</html>
