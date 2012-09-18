<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
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
In.add('oe_dispatchtask_common',{path:'${ctx}/workflow/electricity/security/js/oe_dispatch_task_common.js',type:'js',charset:'utf-8'});
In.add('oe_dispatchtask_list',{path:'${ctx}/workflow/electricity/security/js/oe_outagealarm_list.js',type:'js',charset:'utf-8',rely:['oe_dispatchtask_common']});
In.add('layout',{path:'${ctx}/js/jQuery/layout/layout-default-latest.css'});
In.add('layoutjs',{path:'${ctx}/js/jQuery/layout/jquery.layout-latest.min.js',type:'js',charset:'utf-8',rely:['layout']});
	In.ready('jgcn','layoutjs', 'common', 'wdatejs', 'oe_dispatchtask_list', function() {
		setContextPath('${ctx}');
		setBusinessType('${businessType}');
	accordionObj=jQuery("#accordion").accordion({
 		autoHeight:false,header: 'h3',navigation:true,
 		collapsible: true,active:1
	});
		//使用层布局
		var jqgrid = jQuery("#oedispatchtaskgrid").jqGrid({
							url : "${ctx}/workflow/oeOutageAlarmAction!listData.action?businessType=${businessType}",
							datatype : "json",mtype : 'GET',rownumbers : true,
							colNames : [ '站点名称', '站点类型', '断电时间', '断电原因',
									'接收时间', '状态', '操作' ],
							colModel : [ 
								{name : 'RS_NAME',id : 'RS_NAME',sortable : false,width:150}, 
								{name : 'RS_TYPE',id : 'RS_TYPE',sortable : false,width:80}, 
								{name : 'BLACKOUT_TIME_DIS',id : 'BLACKOUT_TIME_DIS',sortable : false,width:120}, 
								{name : 'BLACKOUT_REASON',id : 'BLACKOUT_REASON',sortable : false,width:300}, 
								{name : 'CREATE_TIME_DIS',id : 'CREATE_TIME_DIS',sortable : false,width:120}, 
								{name : 'STATE',id : 'STATE',sortable : false,formatter : alarmStateFormatter,width:80}, 
								{name : 'ID',id : 'ID',sortable : false,formatter : alarmListOperationFormatter,width:80} 
							],
							rowNum : 10,autowidth : true,rowList : [ 10, 20, 30 ],pager : '#oedispatchtaskpager',shrinkToFit : false,viewrecords : true,hidegrid : false,fix:true,autoScroll:true,
							prmNames : {
								page : "pageNo",rows : "rows",sort : "sidx",order : "sord"
							},
							jsonReader : {
								root : "result",page : "pageNo",total : "totalPages", 
								records : "totalCount",repeatitems : false
							}
						}).navGrid('#oedispatchtaskpager', {
					edit : false,add : false,del : false,search : false,sortable : false
				});
		jQuery(window).wresize(function() {
			grid_resize(jqgrid);
		});
		grid_resize(jqgrid);
	});
</script>
	</head>
	<body>
		<form id="searchForm" name="searchForm">
			<div>
				<div class="title_bg">
					<div id="title" class="title"> 
						当前位置-断电告警列表 
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
									断电站点：
								</th>
								<td>
									<input id="stationName" name="stationName" value="${oeDispatchTask.stationName }" type="text" class="inputtext" style="width: 220px" />
								</td>
								<th>
									断电时间：
								</th>
								<td>
									<input type="text" class="inputtext" style="width: 95px" id='createDateMin' name="createDateMin" class="Wdate inputtext" value="${oeDispatchTask.createDateMin}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'createDateMax\')}'})" />
									至
									<input id='createDateMax' type="text" class="inputtext" style="width: 95px" name="createDateMax" class="Wdate inputtext" value="${oeDispatchTask.createDateMax}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'createDateMin\')}'})" />
								</td>
							</tr>
							<tr>
								<td align="center" colspan="4">
									<input type="button" onclick="query();" class="button" value="查询" />
									<baseinfo:expexcel cls="exprotButton"  businessId="oeoutagealarmlist" name="导出excel"></baseinfo:expexcel>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div id="content" align="center">
				<table id="oedispatchtaskgrid"></table>
				<div id="oedispatchtaskpager"></div>
				
			</div>
		</form>
	</body>
	<script type="text/javascript">
	function query() {
		jQuery("#oedispatchtaskgrid").jqGrid().setGridParam({
			postData : {
				'stationName' : jQuery("#stationName").val(),
				'createDateMin' : jQuery("#createDateMin").val(),
				'createDateMax' : jQuery("#createDateMax").val()
			}
		}).trigger("reloadGrid");
	}
	</script>
</html>