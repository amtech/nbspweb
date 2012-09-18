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
In.add('fault-common', {path : '${ctx }/wplan/patrolitem/js/list_patrol_item.js',type : 'js',charset : 'utf-8'});
In.ready('jgcn', 'common', 'wdatejs', 'fault-common','ztreejs','ztreeui', function() {
//使用层布局
		var jqgrid = jQuery("#itemgrid")
				.jqGrid(
						{
							url : "${ctx}/wplan/patrolinfoScheduleAction!statisticsData.action?queryType=byPatrolGroup",
							datatype : "json",
							mtype : 'GET',
							rownumbers : true,
							colNames : [ '区域','代维公司','巡检组','巡检站点数', '隐患站点数', '异常项' ],
							colModel : [{
								name : 'REGIONNAME',
								id : 'REGIONNAME',
								sortable : false
							},{
								name : 'ORGNAME',
								id : 'ORGNAME',
								sortable : false
							},{
								name : 'NAME',
								id : 'NAME',
								sortable : false
							}, {
								name : 'RESNUB',
								id : 'RESNUB',
								sortable : false,
								formatter:detailedFmt
							}, {
								name : 'EXCNUB',
								id : 'EXCNUB',
								sortable : false
							}, {
								name : 'EXCITEM',
								id : 'EXCITEM',
								sortable : false
							}
							],
							autowidth : true,
							shrinkToFit : true,
							viewrecords : true,
							hidegrid : false,
							pager : '#itempager',
							rowNum:15,
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
function detailedFmt(cellvalue, options, rowObjec){
		var view = "<a style='color: blue;text-decoration: underline;' href=javascript:showDetailed('"
		+ rowObjec.ID + "')>"+cellvalue+"</a>";
		return view;
}
function showDetailed(id){
		window.location.href = "${ctx}/wplan/patrolinfoScheduleAction!detailedList.action?queryType=&patrolGroupId="+id+"&startTime=${parameters['startTime']}&orgId=${parameters['orgId']}&endTime=${parameters['endTime']}&regionId=${parameters['regionId']}";
}
function weaveCondition(type){
        if("byPatrolGroup"!=type){ 
        	window.location.href = "${ctx}/wplan/patrolinfoScheduleAction!statisticsList.action?queryType="+type;
        }
}
//查询
function query() {
	jQuery("#itemgrid").jqGrid().setGridParam({
    	postData: {
         	queryType:'byPatrolGroup',
            startTime:jQuery("#createtime").val(),
            endTime:jQuery("#endtime").val(),
            regionId:jQuery("#query_region_id").val(),
            orgId:jQuery("#query_org_id").val(),
            patrolGroupId:jQuery("#query_patrolgroup_id").val()
        } 
     }).trigger("reloadGrid");
}
// 搜索区域
function searchQueryRegion(url) {
	var val = showRegion(url);
	if (!!val) {
		jQuery("#query_region_id").val(val[0]);
		jQuery("#regionname").val(val[1]);
	}
}
// 搜索代维
function searchQueryOrg(url) {
	var val = showOrg(url);
	if (!!val) {
		jQuery("#query_org_id").val(val[0]);
		jQuery("#orgname").val(val[1]);
	}
}
// 搜索巡检组
function searchQueryPatrolGroup(url) {
	var val = showPatrolGroup(url);
	if (!!val) {
		jQuery("#query_patrolgroup_id").val(val[0]);
		jQuery("#patrolgroupname").val(val[1]);
	}
}
</script>
</head>
<body>
	<form id="optForm" method="post"
		action="${ctx }/wplan/patrolinfoScheduleAction!statisticsList.action" target="myframe">
		<input name="parameter.isQuery" id='isQuery' value="1" type="hidden" />
	
			<div class="title_bg">
				<div id="title" class="title">当前位置-巡检进度查询</div>
			</div>
			<div class="tabcontent">
				<table cellspacing="0" cellpadding="0" border="0" align="center">
					<tr>
						<th>
						         查询类型：
						</th>
						<td colspan="3"> 
						    <c:if test="${user.orgType=='1'}">
						    &nbsp;&nbsp;<input type="radio" name="queryType" value="byRegion" onclick="weaveCondition('byRegion')" >按地市
						    &nbsp;&nbsp;<input type="radio" name="queryType" value="byOrg" onclick="weaveCondition('byOrg')">按代维公司
						    </c:if>
							&nbsp;&nbsp;<input type="radio" name="queryType" value="byPatrolGroup" onclick="weaveCondition('byPatrolGroup')" checked>按巡检组
							&nbsp;&nbsp;<input type="radio" name="queryType" value="byPatrolMan" onclick="weaveCondition('byPatrolMan')">按巡检员
						</td>
					</tr>
					<tr>
						<th>
							开始时间：
						</th>
						<td> 
							&nbsp;&nbsp;&nbsp;<input id="createtime" name="startTime" type="text"
							value="" class="Wdate inputtext" style="width: 220px;"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endtime\')}'})" />
						</td>
						<th>
							结束时间:
						</th>
						<td>
							&nbsp;&nbsp;&nbsp;<input id="endtime" name="endTime"
							type="text" class="Wdate inputtext" value="" style="width: 220px;"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'createtime\')}'})" />
						</td>
					</tr>
					<tr>
						<th>
							区域：
						</th>
						<td>
						    &nbsp;&nbsp;
						    <input id="regionname" name="regionname" class="treetext"
							readonly="readonly" /><a
							href="javascript:searchQueryRegion('${ctx}/commonaccess!getregion.action');">
							<img border="0"
								src="${ctx}/css/image/regionselect.png" />
							</a><input id="query_region_id" name="regionid" type="hidden"  />
						</td>
						<th>
							组织:
						</th>
						<td>&nbsp;&nbsp;
						<input id="orgname" name="orgname" class="treetext"
						readonly="readonly" /><a
						href="javascript:searchQueryOrg('${ctx}/commonaccess!getorg.action?orgtype=2');">
						<img border="0" src="${ctx}/css/image/orgselect.png" />
						<input type="hidden" id="query_org_id" name="orgId">
						</td>
					</tr>
					<tr>
						<th>
							巡检组：
						</th>
						<td colspan="3">&nbsp;&nbsp;
						    <input id="patrolgroupname" name="patrolgroupname"
						    class="treetext" readonly="readonly" /><a
						    href="javascript:searchQueryPatrolGroup('${ctx}/commonaccess!getpatrolgroup.action');">
							<img border="0"
								src="${ctx}/css/image/groupselect.png" />
					        </a>
						   	<input type="hidden" id="query_patrolgroup_id" name="patrolGroupId">
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<input type="button" class="button" value="查询" onclick="query();">
							<baseinfo:expexcel cls="exprotButton"  businessId="schedule_patrolgroup" name="导出excel"></baseinfo:expexcel>
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