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
<title>现场检查管理</title>
<script type="text/javascript">
In.add('jqui',{path:'${ctx}/js/jQuery/jqueryui/css/redmond/jquery-ui.custom.css'});
In.add('ztreeui',{path:'${ctx }/js/jQuery/ztree/css/zTreeStyle/zTreeStyle.css'});
In.add('jquijs',{path:'${ctx}/js/jQuery/jqueryui/jquery-ui.custom.min.js',type:'js',charset:'utf-8',rely:['jqui']});
In.add('jquicn',{path:'${ctx}/js/jQuery/jqueryui/zh/jquery.ui.datepicker-zh-CN.js',type:'js',charset:'utf-8',rely:['jquijs']});
In.add('jresize',{path:'${ctx}/js/jQuery/jresize/jquery.wresize.js',type:'js',charset:'utf-8',rely:['jquicn']});
In.add('jgcss',{path:'${ctx}/js/jQuery/jqgrid/css/ui.jqgrid.css',charset:'utf-8',rely:['jresize']});
In.add('jgjs',{path:'${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js',type:'js',charset:'utf-8',rely:['jgcss']});
In.add('ztreejs',{path:'${ctx}/js/jQuery/ztree/jquery.ztree.all.min.js',type:'js',charset:'utf-8'});
In.add('jgcn',{path:'${ctx}/js/jQuery/jqgrid/grid.locale-cn.js',type:'js',charset:'utf-8',rely:['jgjs']});
In.add('common',{path:'${ctx}/common/js/common.js',type:'js',charset:'utf-8'});
In.add('wdatejs',{path:'${ctx}/js/WdatePicker/WdatePicker.js',type:'js',charset:'utf-8'});
In.ready('jgcn', 'common', 'wdatejs','ztreejs','ztreeui', function() {
		//使用层布局
		var jqgrid = jQuery("#itemgrid")
				.jqGrid(
						{
							url : "${ctx}/assess/assessAppealFormWaitHandledAction!listData.action",
							datatype : "json",
							mtype : 'GET',
							rownumbers : true,
							colNames : [ '考核表名称', '考核月份', '考核成绩', '区域','代维公司','申诉原因','申诉人','申诉时间','办理' ],
							colModel : [{
								name : 'TABLE_NAME',
								id : 'TABLE_NAME',
								sortable : false
							}, {
								name : 'APPRAISE_MONTH',
								id : 'APPRAISE_MONTH',
								sortable : false
							}, {
								name : 'SCORE',
								id : 'SCORE',
								sortable : false
							}, {
								name : 'REGIONNAME',
								id : 'REGIONNAME',
								sortable : false
							}, {
								name : 'ORGNAME',
								id : 'ORGNAME',
								sortable : false
							}, {
								name : 'CAUSE',
								id : 'CAUSE',
								sortable : false
							}, {
								name : 'USERNAME',
								id : 'USERNAME',
								sortable : false
							}, {
								name : 'APPEAL_TIME',
								id : 'APPEAL_TIME',
								sortable : false
							}, {
								name : 'BZID',
								id : 'BZID',
								sortable : false,
								formatter:optFmt
							}
							],
							autowidth : true,
							shrinkToFit : true,
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
function optFmt(cellvalue, options, rowObjec){
        var taskName = rowObjec.TASKNAME;
        var len = taskName.length;
        taskName = taskName.substring(len-2,len);
		var view = "<a style='color: blue;text-decoration: underline;' href=javascript:optAchieve('"
		+ rowObjec.RES+"')>"+taskName+"</a>";
		return view;
}
function optAchieve(dealUrl){
		window.location.href = "${ctx}/"+dealUrl;
}
</script>
</head>
<body>
	<form id="optForm" method="post"
		action="${ctx }/wplan/patrolinfoScheduleAction!statisticsList.action" target="myframe">
		<div class="title_bg">
				<div id="title" class="title">当前位置-申诉管理-申诉待办工作</div>
		</div>
		<div id="content" align="center" style="padding-top: 2px">
			<table id="itemgrid"></table>
			<div id="itempager"></div>
		</div>
	</form>
</body>
</html>