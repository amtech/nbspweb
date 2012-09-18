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
In.add('common',{path:'${ctx}/common/js/common.js',type:'js',charset:'utf-8',rely:['jgcn']});
In.ready('common', function() {
		//使用层布局
		var jqgrid = jQuery("#itemgrid")
				.jqGrid(
						{
							url : "${ctx}/wplan/patrolinfoResultAction!planInfoListData.action?regionId=${parameters['regionId']}&startTime=${parameters['startTime']}&orgId=${parameters['orgId']}&patrolGroupId=${parameters['patrolGroupId']}&endTime=${parameters['endTime']}&businessType=${parameters['businessType']}",
							datatype : "json",
							mtype : 'GET',
							rownumbers : true,
							colNames : [ '计划名称', '制定时间', '维护资源数', '计划资源数', '已巡检资源数', '未巡检资源数','问题站点数','计划巡检率','计划资源覆盖率','实际巡检率','拆迁站点数','纠纷站点数','其他不能巡检数' ],
							colModel : [{
								name : 'PLAN_NAME',
								id : 'PLAN_NAME',
								sortable : false,width:250
							}, {
								name : 'CREATETIME',
								id : 'CREATETIME',
								sortable : false,width:120
							}, {
								name : 'RESCOUNT',
								id : 'RESCOUNT',
								sortable : false,width:80
							}, {
								name : 'PATROLCOUNT',
								id : 'PATROLCOUNT',
								sortable : false,width:80
							}, {
								name : 'ENDPATROLCOUNT',
								id : 'ENDPATROLCOUNT',
								sortable : false,width:90
							}, {
								name : 'NOPATROLCOUNT',
								id : 'NOPATROLCOUNT',
								sortable : false,width:90
							}, {
								name : 'EXCEPTIONCOUNT',
								id : 'EXCEPTIONCOUNT',
								sortable : false,
								formatter:problemResFmt,width:80
							}, {
								name : 'PLANRATE',
								id : 'PLANRATE',
								sortable : false,width:80
							}, {
								name : 'PLANOVERRATE',
								id : 'PLANOVERRATE',
								sortable : false,width:100
							}, {
								name : 'REALRATE',
								id : 'REALRATE',
								sortable : false,width:80
							}, {
								name : 'CQNUM',
								id : 'CQNUM',
								sortable : false,
								formatter:cqDetailedFmt,width:80
							}, {
								name : 'JFNUM',
								id : 'JFNUM',
								sortable : false,
								formatter:jfDetailedFmt,width:80
							}, {
								name : 'QTNUM',
								id : 'QTNUM',
								sortable : false,
								formatter:qtDetailedFmt,width:100
							}
							],
							autowidth : true,
							shrinkToFit : false,fix:true,autoScroll:true,
							viewrecords : true,
							hidegrid : false,
							pager : '#itempager',
							rowNum:20,
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
function cqDetailedFmt(cellvalue, options, rowObjec){
        if(0==cellvalue){
        	return "0";
        }
		var view = "<a style='color: blue;text-decoration: underline;' href=javascript:cqShowDetailed('"
		+ rowObjec.ID+"','"+rowObjec.BUSINESSTYPE+"','"+rowObjec.STARTTIME+"','"+rowObjec.ENDTIME+ "')>"+cellvalue+"</a>";
		return view;
}
function cqShowDetailed(id,businessType,startTime,endTime){
		if('undefined'==businessType){
		businessType="";
		}
		if('undefined'==startTime){
		startTime="";
		}
		if('undefined'==endTime){
		endTime="";
		}
		window.location.href = "${ctx}/wplan/patrolinfoResultAction!noPatrolDetails.action?problemType=01&planId="+id+"&businessType="+businessType+"&startTime="+startTime+"&endTime="+endTime;
}
function jfDetailedFmt(cellvalue, options, rowObjec){
        if(0==cellvalue){
        	return "0";
        }
		var view = "<a style='color: blue;text-decoration: underline;' href=javascript:jfShowDetailed('"
		+ rowObjec.ID+"','"+rowObjec.BUSINESSTYPE+"','"+rowObjec.STARTTIME+"','"+rowObjec.ENDTIME+ "')>"+cellvalue+"</a>";
		return view;
}
function jfShowDetailed(id,businessType,startTime,endTime){
		if('undefined'==businessType){
		businessType="";
		}
		if('undefined'==startTime){
		startTime="";
		}
		if('undefined'==endTime){
		endTime="";
		}
		window.location.href = "${ctx}/wplan/patrolinfoResultAction!noPatrolDetails.action?problemType=02&planId="+id+"&businessType="+businessType+"&startTime="+startTime+"&endTime="+endTime;
}
function qtDetailedFmt(cellvalue, options, rowObjec){
        if(0==cellvalue){
        	return "0";
        }
		var view = "<a style='color: blue;text-decoration: underline;' href=javascript:qtShowDetailed('"
		+ rowObjec.ID+"','"+rowObjec.BUSINESSTYPE+"','"+rowObjec.STARTTIME+"','"+rowObjec.ENDTIME+ "')>"+cellvalue+"</a>";
		return view;
}
function qtShowDetailed(id,businessType,startTime,endTime){
		if('undefined'==businessType){
		businessType="";
		}
		if('undefined'==startTime){
		startTime="";
		}
		if('undefined'==endTime){
		endTime="";
		}
		window.location.href = "${ctx}/wplan/patrolinfoResultAction!noPatrolDetails.action?problemType=03&planId="+id+"&businessType="+businessType+"&startTime="+startTime+"&endTime="+endTime;
}
function problemResFmt(cellvalue, options, rowObjec){
        if(0==cellvalue){
        	return "0";
        }
		var view = "<a style='color: blue;text-decoration: underline;' href=javascript:showProblemResFmt('"
		+ rowObjec.ID+"','"+rowObjec.BUSINESSTYPE+"','"+rowObjec.STARTTIME+"','"+rowObjec.ENDTIME+ "')>"+cellvalue+"</a>";
		return view;
}
function showProblemResFmt(id,businessType,startTime,endTime){
		if('undefined'==businessType){
		businessType="";
		}
		if('undefined'==startTime){
		startTime="";
		}
		if('undefined'==endTime){
		endTime="";
		}
		window.location.href = "${ctx}/wplan/patrolinfoResultAction!problemStationList.action?planId="+id+"&businessType="+businessType+"&startTime="+startTime+"&endTime="+endTime;
}
</script>
</head>
<body>
	<form id="optForm" method="post"
		action="${ctx }/wplan/patrolinfoScheduleAction!statisticsList.action" target="myframe">
		<input name="parameter.isQuery" id='isQuery' value="1" type="hidden" />
	
			<div class="title_bg">
				<div id="title" class="title">当前位置-计划列表明细</div>
			</div>
		<br>
		<div class="tabcontent">
					<table cellspacing="0" cellpadding="0" border="0" align="center">
						<tr>
							<td align="center" colspan="4">
								<input type="button" onclick="history.go(-1);" class="button" value="返回" />
								<baseinfo:expexcel cls="exprotButton" businessId="planpatrolresultdetailstatistics" name="导出excel"></baseinfo:expexcel>
							</td>
						</tr>
					</table>
		</div>
		<br>
		<div id="content" align="center" style="padding-top: 2px">
			<table id="itemgrid"></table>
			<div id="itempager"></div>
		</div>
	</form>
</body>
</html>