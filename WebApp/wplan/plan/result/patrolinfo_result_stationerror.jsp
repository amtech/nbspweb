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
							url : "${ctx}/wplan/patrolinfoResultAction!stationErrorListData.action?",
							datatype : "json",
							postData : {
									'orgId' : '${parameters.orgId}',
									'regionId' : '${parameters.regionId}',
									'businessType' : '${parameters.businessType}',
									'patrolGroupId' : '${parameters.patrolGroupId}',
									'startTime' : '${parameters.startTime}',
									'endTime' : '${parameters.endTime}',
									'planId' : '${parameters.planId}',
									'rsId' : '${parameters.rsId}'
							},
							mtype : 'GET',
							rownumbers : true,
							colNames : [ '站点名称','维护对象', '维护检测项目', '巡检结果','异常描述' ],
							colModel : [ 
							{name : 'STATION_NAME',id : 'STATION_NAME',sortable : false}, 
							{name : 'ITEM_NAME',id : 'ITEM_NAME',sortable : false}, 
							{name : 'SUBITEM_NAME',id : 'SUBITEM_NAME',sortable : false}, 
							{name : 'SUBITEM_PATROL',id : 'SUBITEM_PATROL',sortable : false}, 
							{name : 'EXCEPTION_DESC',id : 'EXCEPTION_DESC',sortable : false}
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
						当前位置-站点异常项列表
					</div>
				</div>
				<div class="tabcontent">
					<table cellspacing="0" cellpadding="0" border="0" align="center">
						<tr>
							<td align="center" colspan="4">
								<input type="button" onclick="history.go(-1);" class="button" value="返回" />
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
</html>
