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
	In.ready('jgcn', 'common', 'wdatejs', function() {
		//使用层布局
		var jqgrid = jQuery("#analysegrid")
				.jqGrid(
						{
							url : "${ctx}/analysis/stationPatrolResultAnalysisAction!listData.action?",
							datatype : "local",
							mtype : 'GET',
							rownumbers : true,
							colNames : [ '站点名称', '专业类型', '所属代维', '所属巡检组','计划名称','是否已巡检','巡检时间','巡检人','操作' ],
							colModel : [ 
							{name : 'STATION_NAME',id : 'STATION_NAME',sortable : false}, 
							{name : 'BUSINESS_TYPE',id : 'BUSINESS_TYPE',sortable : false}, 
							{name : 'ORG_NAME',id : 'ORG_NAME',sortable : false}, 
							{name : 'PATROL_NAME',id : 'PATROL_NAME',sortable : false}, 
							{name : 'PLAN_NAME',id : 'PLAN_NAME',sortable : false}, 
							{name : 'IS_PATROLED',id : 'IS_PATROLED',sortable : false}, 
							{name : 'PATROL_TIME_DIS',id : 'PATROL_TIME_DIS',sortable : false},
							{name : 'PATROL_MAN',id : 'PATROL_MAN',sortable : false},
							{name : 'RESULT_ID',id : 'RESULT_ID',sortable : false,formatter:viewFormatter} 
							],
							rowNum : 10,
							autowidth : true,
							rowList : [ 10, 20, 30 ],
							pager : '#analysepager',
							shrinkToFit : true,
							viewrecords : true,
							hidegrid : false,
							prmNames : {
								page : "pageNo",rows : "rows",sort : "sidx",order : "sord"
							},
							jsonReader : {
								root : "result",page : "pageNo",total : "totalPages", 
								records : "totalCount",repeatitems : false,id : "0"
							}
					}).navGrid('#analysepager', {
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
						当前位置-站点巡检结果统计列表
					</div>
				</div>
				<div class="tabcontent">
					<table cellspacing="0" cellpadding="0" border="0" align="center">
						<tr>
							<th>
								区域:
							</th>
							<td>
								<input id="regionName" name="regionName" class="inputtext" readonly value="" style="width: 220px;" />
								<a href="javascript:searchRegion();"><img border="0" src="${ctx}/css/image/regionselect.png" /> </a>
								<input id="regionId" name="regionId" type="hidden" value="" />
							</td>
							<th>
								专业类型：
							</th>
							<td>
								<baseinfo:customselector name="businessType" data="${businessTypeMap}" isReversal="true" isQuery="query" cssClass="inputtext" id="businessType" style="width: 220px"></baseinfo:customselector>
							</td>
						</tr>
						<tr>
							<th>
								巡检开始时间：
							</th>
							<td>
								<input id='startTime' type="text" class="Wdate inputtext" style="width: 220px" name="startTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',maxDate:'#F{$dp.$D(\'endTime\')}'})" />
							</td>
							<th>
								巡检结束时间：
							</th>
							<td>
								<input id='endTime' type="text" class="Wdate inputtext" style="width: 220px" name="endTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',minDate:'#F{$dp.$D(\'startTime\')}'})" />
							</td>
						</tr>
						<tr>
							<th>
								站点名称:
							</th>
							<td colspan="3">
								<input id='stationName' type="text" class="inputtext" style="width: 220px" name="stationName" class="inputtext" />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4">
								<input type="button" onclick="query();" class="button" value="查询" />
								<baseinfo:expexcel cls="exprotButton" businessId="stationPatrolResultList" name="导出excel"></baseinfo:expexcel>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<br>
			<div id="content" align="center">
				<table id="analysegrid"></table>
				<div id="analysepager"></div>
			</div>
		</form>
	</body>
	<script type="text/javascript">
function query() {
	jQuery("#analysegrid").jqGrid().setGridParam({
		datatype:'json',
		postData : {
			'regionId' : jQuery("#regionId").val(),
			'businessType' : jQuery("#businessType").val(),
			'stationName' : jQuery("#stationName").val(),
			'startTime' : jQuery("#startTime").val(),
			'endTime' : jQuery("#endTime").val()
	}}).trigger("reloadGrid");
}
function searchRegion() {
	var val = showRegion('${ctx}/commonaccess!getregion.action');
	if (!!val) {
		jQuery("#regionId").val(val[0]);
		jQuery("#regionName").val(val[1]);
	}
}
function searchOrg() {
	var val = showOrg('${ctx}/commonaccess!getorg.action?orgtype=2');
	if (!!val) {
		jQuery("#orgId").val(val[0]);
		jQuery("#orgName").val(val[1]);
	}
}
function searchPatrol() {
	var val = showPatrolGroup('${ctx}/commonaccess!getpatrolgroup.action');
	if (!!val) {
		jQuery("#patrolId").val(val[0]);
		jQuery("#patrolName").val(val[1]);
	}
}
function viewPatrolResult(id){
	window.location.href = "${ctx}/wplan/patrolinfoExecuteAction!patrolitemdetail.action?id="+id;
}
function viewFormatter(cellValue,options,rowObject){
	var view = "";
	if(rowObject.IS_PATROLED=='是'){
		view="<a style='color: blue;text-decoration: underline;' href=javascript:viewPatrolResult('"+cellValue+"')>查看巡检表</a>";
	}
	return view;
}
	</script>
</html>
