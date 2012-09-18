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
							url : "${ctx}/analysis/problemStationAnalysisAction!problemListData.action?",
							datatype : "json",
							mtype : 'GET',
							rownumbers : true,
							postData : {
								'orgId' : '${map.orgId}',
								'regionId' : '${map.regionId}',
								'businessType' : '${map.businessType}',
								'patrolId' : '${map.patrolId}',
								'startTime' : '${map.startTime}',
								'endTime' : '${map.endTime}',
								'id':'${map.id}'
							},
							colNames : [ '计划名称', '维护对象', '维护检测项目', '上报时间','异常描述','是否已处理','处理时间','处理结果','处理巡检组' ],
							colModel : [ 
							{name : 'PLAN_NAME',id : 'PLAN_NAME',sortable : false}, 
							{name : 'ITEM_NAME',id : 'ITEM_NAME',sortable : false}, 
							{name : 'SUBITEM_NAME',id : 'SUBITEM_NAME',sortable : false}, 
							{name : 'REPORT_DATE_DIS',id : 'REPORT_DATE_DIS',sortable : false}, 
							{name : 'EXCEPTION_DESC',id : 'EXCEPTION_DESC',sortable : false}, 
							{name : 'IS_PROCESSED',id : 'IS_PROCESSED',sortable : false}, 
							{name : 'PROCESSED_DATE_DIS',id : 'PROCESSED_DATE_DIS',sortable : false},
							{name : 'MAINTAIN_RESULT',id : 'PROCESSED_RESULT',sortable : false},
							{name : 'PROCESSED_PATROLGROUP',id : 'PROCESSED_PATROLGROUP',sortable : false} 
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
						当前位置-问题站点隐患信息列表
					</div>
				</div>
				<div class="tabcontent">
					<table cellspacing="0" cellpadding="0" border="0" align="center">
						<tr>
							<td align="center" colspan="4">
								<input type="button" onclick="history.go(-1);" class="button" value="返回" />
								<baseinfo:expexcel cls="exprotButton" businessId="problemStationItemList" name="导出excel"></baseinfo:expexcel>
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
	</script>
</html>
