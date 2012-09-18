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
<title>巡检项信息查询</title>
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
In.add('fault-common', {path : '${ctx }/wplan/patrolitem/js/list_patrol_item.js',type : 'js',charset : 'utf-8'});
In.ready('jgcn', 'common', 'wdatejs', 'fault-common', function() {
		//使用层布局
		var jqgrid = jQuery("#itemgrid")
				.jqGrid(
						{
							url : "${ctx}/wplan/patrolinfoScheduleAction!detailedData.action?queryType=&startTime=${parameters['startTime']}&orgId=${parameters['orgId']}&patrolGroupId=${parameters['patrolGroupId']}&endTime=${parameters['endTime']}&regionId=${parameters['regionId']}&patrolManId=${parameters['patrolManId']}",
							datatype : "json",
							mtype : 'GET',
							rownumbers : true,
							colNames : [ '专业类型', '站点名称', '维护单位', '巡检组', '巡检人', '到达时间', '巡检开始时间', '巡检结束时间', '巡检时长'],
							colModel : [{
								name : 'RESTYPE',
								id : 'RESTYPE',
								sortable : false
							}, {
								name : 'RESNAME',
								id : 'RESNAME',
								sortable : false
							}, {
								name : 'ORGNAME',
								id : 'ORGNAME',
								sortable : false
							}, {
								name : 'PATROLGROUPNAME',
								id : 'PATROLGROUPNAME',
								sortable : false
							}, {
								name : 'PATROLMANNAME',
								id : 'PATROLMANNAME',
								sortable : false
							}, {
								name : 'ARRIVE_TIME',
								id : 'ARRIVE_TIME',
								sortable : false
							}, {
								name : 'START_TIME',
								id : 'START_TIME',
								sortable : false
							}, {
								name : 'END_TIME',
								id : 'END_TIME',
								sortable : false
							}, {
								name : 'PRESSURE',
								id : 'PRESSURE',
								sortable : false
							}
							],
							autowidth : true,
							shrinkToFit : true,
							viewrecords : true,
							hidegrid : false,
							pager : '#itempager',
							rowNum:17,
							rowList:[15,20,30], 
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
								repeatitems : false
							}
						}).navGrid('#itempager', {
					edit : false,
					add : false,
					del : false,
					search : false,
					sortable : false
				});
		jQuery(window).wresize(function(){
			grid_resize(jqgrid);
		});
		grid_resize(jqgrid);
	});
	//查询
	function search() {
		jQuery("#itemgrid").jqGrid().setGridParam({
            postData: {
            	'parameter.showAll':jQuery("#showAll").val(),
            	'parameter.isQuery':jQuery("#isQuery").val(),
            	'parameter.businessType':jQuery("#businessType").val()
            	} 
            }).trigger("reloadGrid");
	}
	function detailedFmt(cellvalue, options, rowObjec){
			var view = "<a style='color: blue;text-decoration: underline;' href=javascript:showDetailed('"
			+ rowObjec.REGIONID + "')>"+cellvalue+"</a>";
			return view;
	}
	function pressureFmt(cellvalue, options, rowObjec){
		var view = getTimeDifference(rowObjec.START_TIME,rowObjec.END_TIME);
		return view;
	}
	function showDetailed(id){
			window.location.href = "${ctx}/wplan/patrolinfoScheduleAction!detailedList.action?regionId="+id+"&startTime=${parameters['startTime']}&orgId=${parameters['orgId']}&patrolGroupId=${parameters['patrolGroupId']}&endTime=${parameters['endTime']}";
	}

</script>
</head>
<body>
	<form id="optForm" method="post"
		action="${ctx }/wplan/patrolItemAction!list.action">
		<input name="parameter.isQuery" id='isQuery' value="1" type="hidden" />
		<div>
			<div class="title_bg">
				<div id="title" class="title">当前位置-巡检明细 </div>
			</div>
		</div>
		<div class="tabcontent">
					<table cellspacing="0" cellpadding="0" border="0" align="center">
						<tr>
							<td align="center" colspan="4">
											<input name="" value="返回" type="button" class="button" onclick="history.go(-1);" />
											<baseinfo:expexcel cls='exprotButton' businessId="schedule_detailed" name="导出excel"></baseinfo:expexcel>
							</td>
						</tr>
					</table>
		</div>
		<br>
		<div id="content" align="center" style="padding-top: 2px;padding-bottom:40px;">
			<table id="itemgrid"></table>
			<div id="itempager"></div>
		</div>
	</form>
</body>
</html>