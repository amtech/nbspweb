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
							url : "${ctx}/wplan/patrolinfoResultAction!problemStationListData.action?",
							datatype : "json",
							postData : {
									'planId' : '${parameters.planId}',
									'orgId' : '${parameters.orgId}',
									'regionId' : '${parameters.regionId}',
									'businessType' : '${parameters.businessType}',
									'patrolGroupId' : '${parameters.patrolGroupId}',
									'startTime' : '${parameters.startTime}',
									'endTime' : '${parameters.endTime}'
							},
							mtype : 'GET',
							rownumbers : true,
							colNames : [ '站点名称', '专业类型', '所属区域','所属代维公司','所属计划','问题项数量','操作' ],
							colModel : [ 
							{name : 'STATION_NAME',id : 'STATION_NAME',sortable : false,width:100}, 
							{name : 'BUSINESS_TYPE',id : 'BUSINESS_TYPE',sortable : false,width:100}, 
							{name : 'REGIONNAME',id : 'REGIONNAME',sortable : false,width:90}, 
							{name : 'ORG_NAME',id : 'ORG_NAME',sortable : false,width:140}, 
							{name : 'PLAN_NAME',id : 'PLAN_NAME',sortable : false,width:300}, 
							{name : 'ERROR_NUM',id : 'ERROR_NUM',sortable : false,width:100}, 
							{name : 'ID',id : 'ID',sortable : false,formatter:viewFormatter,width:100} 
							],
							rowNum : 10,
							autowidth : true,
							rowList : [ 10, 20, 30 ],
							pager : '#analysepager',
							shrinkToFit : false,fix:true,autoScroll:true,
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
						当前位置-问题站点统计列表
					</div>
				</div>
				<div class="tabcontent">
					<table cellspacing="0" cellpadding="0" border="0" align="center">
						<tr>
							<td align="center" colspan="4">
								<input type="button" onclick="history.go(-1);" class="button" value="返回" />
								<baseinfo:expexcel cls="exprotButton" businessId="problemstationresultdetailstatistics" name="导出excel"></baseinfo:expexcel>
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
function viewPatrolProblem(stationId,planId){
	var orgId="&org=${parameters.orgId}";
	var regionId="&regionId=${parameters.regionId}";
	var businessType="&businessType=${parameters.businessType}";
	var patrolId="&patrolId=${parameters.patrolId}";
	var startTime="&startTime=${parameters.startTime}";
	var endTime="&endTime=${parameters.endTime}";
	var queryString=orgId+regionId+businessType+patrolId+startTime+endTime;
	window.location.href = "${ctx}/wplan/patrolinfoResultAction!stationErrorList.action?rsId="+stationId+"&planId="+planId+queryString;
}
function viewFormatter(cellValue,options,rowObject){
	var view="<a style='color: blue;text-decoration: underline;' href=javascript:viewPatrolProblem('"+rowObject.STATION_ID+"','"+rowObject.PLAN_ID+"')>查看详细</a>";;
	return view;
}
	</script>
</html>
