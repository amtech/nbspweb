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
							url : "${ctx}/assess/assessExaminationAction!listData.action",
							datatype : "json",
							mtype : 'GET',
							rownumbers : true,
							colNames : [ '现场检查表', '代维公司', '检查站点', '站点类型','检查人','检查时间','扣分','操作' ],
							colModel : [{
								name : 'TABLE_NAME',
								id : 'TABLE_NAME',
								sortable : false
							}, {
								name : 'ORGNAME',
								id : 'ORGNAME',
								sortable : false
							}, {
								name : 'ZYMC',
								id : 'ZYMC',
								sortable : false
							}, {
								name : 'LABLE',
								id : 'LABLE',
								sortable : false
							}, {
								name : 'USERNAME',
								id : 'USERNAME',
								sortable : false
							}, {
								name : 'INSPECTION_DATE',
								id : 'INSPECTION_DATE',
								sortable : false,
								formatter:dateFmt
							}, {
								name : 'SCORE',
								id : 'SCORE',
								sortable : false,
								formatter:scoreFmt
							}, {
								name : 'ID',
								id : 'ID',
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
//查询
function query() {
	jQuery("#itemgrid").jqGrid().setGridParam({
    	postData: {
            orgId:jQuery("#query_org_id").val(),
            inspector:jQuery("#query_person_id").val(),
            stationType:jQuery("#station_type").val(),
            stationId:jQuery("#query_station_id").val(),
            startTime:jQuery("#createtime").val(),
            endTime:jQuery("#endtime").val() 
        } 
     }).trigger("reloadGrid");
}
// 搜索代维
function searchQueryOrg(url) {
	var val = showOrg(url);
	if (!!val) {
		jQuery("#query_org_id").val(val[0]);
		jQuery("#orgname").val(val[1]);
	}
}
// 搜索人员
function searchStaff(url) {
	var val = showOrgPerson(url);
	if (!!val) {
		jQuery("#query_person_id").val(val[0]);
		jQuery("#personname").val(val[1]);
	}
}
// 搜索站点
function searchResource() {
	var businessType = '';
	var url = '${ctx}/commonaccess!getresourceinfo.action';
	url += "?businessType=" + businessType;
	var val = showresource(url);
	if (val) {
		jQuery("#query_station_id").val(val[0].ID);
		jQuery("#stationname").val(val[0].NAME);
	}
}
function input(){
	window.location.href = "${ctx}/assess/assessExaminationAction!inputFirst.action";
}
function optFmt(cellvalue, options, rowObjec){
		var view = "<a style='color: blue;text-decoration: underline;' href=javascript:optAchieve('"
		+ rowObjec.ID+"')>查看</a>";
		return view;
}
function scoreFmt(cellvalue, options, rowObjec){
		return Math.abs(cellvalue);
}
function optAchieve(id){
		window.location.href = "${ctx}/assess/assessExaminationAction!view.action?id="+id;
}
function dateFmt(cellvalue, options, rowObjec){
		return cellvalue.split(" ")[0];
}
</script>
</head>
<body>
	<form id="optForm" method="post"
		action="${ctx }/wplan/patrolinfoScheduleAction!statisticsList.action" target="myframe">
		<input name="parameter.isQuery" id='isQuery' value="1" type="hidden" />
	
			<div class="title_bg">
				<div id="title" class="title">当前位置-现场检查管理-查询检查列表</div>
			</div>
			<div class="tabcontent">
				<table cellspacing="0" cellpadding="0" border="0" align="center">
					<tr>
						<th>
							代维公司:
						</th>
						<td>&nbsp;&nbsp;
						<input id="orgname" name="orgname" class="treetext"
						readonly="readonly" /><a
						href="javascript:searchQueryOrg('${ctx}/commonaccess!getorg.action?orgtype=2');">
						<img border="0" src="${ctx}/css/images/selectcode.gif" />
						<input type="hidden" id="query_org_id" name="orgId">
						</td>
						<th>
							检查人：
						</th>
						<td>
						    &nbsp;&nbsp;
						    <input id="personname" name="personname" class="treetext"
							readonly="readonly" /><a
							href="javascript:searchStaff('${ctx}/commonaccess!getstaff.action?orgtype=1&flag=radio&regionid=${user.regionId }');">
							<img border="0"
								src="${ctx}/css/images/selectcode.gif" />
							</a><input id="query_person_id" name=inspector type="hidden"  />
						</td>
					</tr>
					<tr>
						<th>
							站点类型:
						</th>
						<td>&nbsp;&nbsp;
						    <baseinfo:dicselector cssClass="treetext" name="station_type" columntype="ZDLX" type="select" isQuery="query"></baseinfo:dicselector>
						</td>
						<th>
							检查站点：
						</th>
						<td>&nbsp;&nbsp;
							<input id="stationname" name="stationname" class="treetext"
							readonly="readonly" /><a
							href="javascript:searchResource();">
							<img border="0"
								src="${ctx}/css/images/selectcode.gif" />
							</a><input id="query_station_id" name="stationId" type="hidden"  />
						</td>
					</tr>
					<tr>
						<th>
							开始时间：
						</th>
						<td> 
							&nbsp;&nbsp;&nbsp;<input id="createtime" name="startTime" type="text"
							value="${monthSlot['startTime'] }" class="Wdate inputtext" style="width: 220px;"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endtime\')}'})" />
						</td>
						<th>
							结束时间:
						</th>
						<td>
							&nbsp;&nbsp;&nbsp;<input id="endtime" name="endTime"
							type="text" class="Wdate inputtext" value="${monthSlot['endTime'] }" style="width: 220px;"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'createtime\')}'})" />
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<input type="button" class="button" value="查询" onclick="query();">
							<baseinfo:expexcel cls="exprotButton"  businessId="assess_examination_list" name="导出excel"></baseinfo:expexcel>
							<input type="button" style="width:100px;" class="button_length" value="登记查询记录" onclick="input();">
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