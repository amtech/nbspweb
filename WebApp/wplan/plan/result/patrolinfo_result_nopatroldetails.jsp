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
							url : "${ctx}/wplan/patrolinfoResultAction!noPatrolDetailsData.action?problemType=${parameters['problemType']}&regionId=${parameters['regionId']}&startTime=${parameters['startTime']}&orgId=${parameters['orgId']}&patrolGroupId=${parameters['patrolGroupId']}&endTime=${parameters['endTime']}&businessType=${parameters['businessType']}&planId=${parameters['planId']}",
							datatype : "json",
							mtype : 'GET',
							rownumbers : true,
							colNames : [ '拆迁站点名称', '专业类型', '所属区域', '所属代维公司', '所属计划', '拆迁原因' ],
							colModel : [{
								name : 'NAME',
								id : 'NAME',
								sortable : false,width:100
							}, {
								name : 'LABLE',
								id : 'LABLE',
								sortable : false,width:100
							}, {
								name : 'REGIONNAME',
								id : 'REGIONNAME',
								sortable : false,width:90
							}, {
								name : 'ORGNAME',
								id : 'ORGNAME',
								sortable : false,width:140
							}, {
								name : 'PLAN_NAME',
								id : 'PLAN_NAME',
								sortable : false,width:250
							}, {
								name : 'CAUSE',
								id : 'CAUSE',
								sortable : false,width:330
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
function cqDetailedFmt(cellvalue, options, rowObjec){
		var view = "<a style='color: blue;text-decoration: underline;' href=javascript:cqShowDetailed('"
		+ rowObjec.REGIONID + "')>"+cellvalue+"</a>";
		return view;
}
function cqShowDetailed(id){
		window.location.href = "${ctx}/wplan/patrolinfoResultAction!noPatrolDetails.action?problemType=${parameters['problemType']}&regionId="+id+"&startTime=${parameters['startTime']}&orgId=${parameters['orgId']}&patrolGroupId=${parameters['patrolGroupId']}&endTime=${parameters['endTime']}";
}
function weaveCondition(type){
        if("byRegion"!=type){ 
        	window.location.href = "${ctx}/wplan/patrolinfoScheduleAction!statisticsList.action?queryType="+type;
        }
}
//查询
function query() {
	jQuery("#itemgrid").jqGrid().setGridParam({
    	postData: {
         	businessType:jQuery("#business_type").val(),
            startTime:jQuery("#createtime").val(),
            endTime:jQuery("#endtime").val(),
            regionId:jQuery("#query_region_id").val(),
            orgId:'',
            patrolGroupId:''
        } 
     }).trigger("reloadGrid");
}
// 搜索区域
function searchRegion(url) {
	var val = showRegion(url);
	if (!!val) {
		jQuery("#query_region_id").val(val[0]);
		jQuery("#regionname").val(val[1]);
	}
}
</script>
</head>
<body>
	<form id="optForm" method="post"
		action="${ctx }/wplan/patrolinfoScheduleAction!statisticsList.action" target="myframe">
		<input name="parameter.isQuery" id='isQuery' value="1" type="hidden" />
			<div class="title_bg">
				<div id="title" class="title">当前位置-
				<c:if test="${parameters['problemType']=='01'}">
				拆迁站点
				</c:if>
				<c:if test="${parameters['problemType']=='02'}">
				纠纷站点
				</c:if>
				<c:if test="'${parameters['problemType']=='03'}">
				其它未巡检站点
				</c:if>
				明细</div>
			</div>
		<br>
		<div class="tabcontent">
					<table cellspacing="0" cellpadding="0" border="0" align="center">
						<tr>
							<td align="center" colspan="4">
								<input type="button" onclick="history.go(-1);" class="button" value="返回" />
								<baseinfo:expexcel cls="exprotButton" businessId="nopatrolstationresultdetailstatistics" name="导出excel"></baseinfo:expexcel>
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