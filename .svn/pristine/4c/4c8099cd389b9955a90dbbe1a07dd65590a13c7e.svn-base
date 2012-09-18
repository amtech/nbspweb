<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link href="${ctx}/css/base.css" rel="stylesheet" type="text/css" />
		<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx }/js/in-min.js"
			autoload="true"
			core="${config.cdnurl}/cabletech/ui/js/jquery-1.7.2.min.js"></script>
		<title>维修作业计划待办工作</title>
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
In.add('wmaintain_common',{path:'${ctx}/workflow/wmaintain/js/wmaintain_common.js',type:'js',charset:'utf-8'});
	In.ready('jgcn', 'common', 'wdatejs', 'wmaintain_common', function() {
		setContextPath('${ctx }');
		//使用层布局
		var jqgrid = jQuery("#wmaintaingrid")
				.jqGrid(
						{
							url : "${ctx}/workflow/wmaintainHandleAction!handledListData.action?",
							datatype : "json",
							mtype : 'POST',
							rownumbers : true,
							colNames : [ '维修计划名称', '地市', '计划时间', '创建人', '创建时间',
									'代维公司', '维护组', '状态', '操作' ],
							colModel : [ 
							{name : 'PLAN_NAME',id : 'PLAN_NAME',sortable : false,width:150}, 
							{name : 'REGIONNAME',id : 'REGIONNAME',sortable : false,width:50}, 
							{name : 'PLAN_DATE',id : 'PLAN_DATE',sortable : false,width:120}, 
							{name : 'CREATER_NAME',id : 'CREATER_NAME',sortable : false,width:60}, 
							{name : 'CREATE_DATE_DIS',id : 'CREATE_DATE_DIS',sortable : false,width:100}, 
							{name : 'ORGNAME',id : 'ORGNAME',sortable : false,width:140}, 
							{name : 'PATROLGROUP_NAME',id : 'PATROLGROUP_NAME',sortable : false,width:100}, 
							{name : 'PLAN_STATE',id : 'PLAN_STATE',sortable : false,formatter : convertPlanStateFormatter,width:50}, 
							{name : 'ID',id : 'ID',sortable : false,formatter : handledActionFormatter,width:50} 
							],
							rowNum : 10,
							autowidth : true,
							rowList : [ 10, 20, 30 ],
							pager : '#wmaintainpager',
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
					}).navGrid('#wmaintainpager', {
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
		<form id="workorderForm" name="form">
			<div id="header">
				<div class="title_bg">
					<div class="title">
						当前位置-维修作业计划已办工作
					</div>
				</div>
				<div class="tabcontent">
					<table cellspacing="0" cellpadding="0" border="0" align="center">
						<tr>
							<th>
								名称：
							</th>
							<td>
								<input class="inputtext" type="text" id="wmaintain_planName" name="planName" maxlength="60" style="width: 220px;" />
							</td>
							<th>
								地市：
							</th>
							<td>
								<input id="regionname" name="regionname" class="inputtext" readonly="readonly" value="${regionname}" style="width: 220px;" />
								<a href="javascript:searchRegion('${ctx}/commonaccess!getregion.action');"><img border="0" src="${ctx}/css/image/regionselect.png" /> </a>
								<input id="wmaintain_regionid" name="regionid" type="hidden" value="${regionid}" />
							</td>
						</tr>
						<tr>
							<th>
								创建人：
							</th>
							<td>
								<input id="wmaintain_creater" name="creater" value="${creater }" class="inputtext" type="text" style="width: 220px;" />
							</td>
							<th>
								创建日期：
							</th>
							<td>
								<input id="wmaintain_createDateMin" name="createDateMin" type="text" class="Wdate inputtext" style="width: 95px;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'wmaintain_createDateMax\')}'})" />
								至
								<input id="wmaintain_createDateMax" name="createDateMax" type="text" class="Wdate inputtext" style="width: 95px;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'wmaintain_createDateMin\')}'})" />
							</td>
						</tr>
						<tr>
							<th>
								专业类型：
							</th>
							<td colspan="3">
								<baseinfo:customselector name="businessType" data="${businessTypeMap}" isReversal="true" isQuery="query" cssClass="inputtext" id="businessType" style="width: 220px"></baseinfo:customselector>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="button" onclick="query();" class="button" value="查询" />
								<baseinfo:expexcel cls="exprotButton"  businessId="wmaintainhandledlist" name="导出excel"></baseinfo:expexcel>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div id="content" align="center" style="padding-top: 2px">
				<table id="wmaintaingrid"></table>
				<div id="wmaintainpager"></div>
			</div>
		</form>
	</body>
</html>