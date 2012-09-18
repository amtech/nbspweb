<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link href="${ctx}/css/base.css" rel="stylesheet" type="text/css" />
		<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx }/js/in-min.js"
			autoload="true"
			core="${config.cdnurl}/cabletech/ui/js/jquery-1.7.2.min.js"></script>
		<title>选择站点</title>
		<script>
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
  		var jqgrid=jQuery("#resourcegrid").jqGrid({    
		url: '${ctx }/wplan/noPatrolStationAction!selectSiteData.action?noPatrolStation.resourceType=${resourceType}',datatype: "json", mtype: 'GET', rownumbers: true,
		colNames:['计划名称','站点名称','站点编号','资源类型','PLAN_ID','RESOURCE_ID'],
		colModel:[
		          {name:'PLAN_NAME',id:'PLAN_NAME',sortable:false},
		          {name:'ZYMC',id:'ZYMC',sortable:false},
		          {name:'ZDBH',id:'ZDBH',sortable:false},
		          {name:'PLAN_ID',id:'PLAN_ID',sortable:false,hidden:true},
		          {name:'RESOURCE_ID',id:'RESOURCE_ID',sortable:false,hidden:true},
		          {name:'RESOURCE_TYPE',id:'RESOURCE_TYPE',sortable:false,hidden:true}
		          ],      
		rowNum:10, autowidth:true, rowList:[10,20,30],    
		pager: '#resourcepager', shrinkToFit:true,
		viewrecords: true, hidegrid: false, multiselect: false,
		prmNames: {page:"pageNo",rows:"rows", sort: "sidx",order: "sord"},
		jsonReader: {
               root:"result" , page: "pageNo" , total: "totalPages" , records: "totalCount", repeatitems: false
		}
	}).navGrid('#resourcepager',{edit:false ,add:false ,del:false,search:false,sortable:false  });
	jQuery(window).wresize(function(){
		grid_resize(jqgrid);
	});
	grid_resize(jqgrid);
	})
</script>
	</head>
	<body>
		<form action="${ctx }/wplan/noPatrolStationAction!selectSite.action?resourceType=${resourceType}" id="queryForm" name="queryForm" method="post">
			<div id="header">
				<div class="title_bg">
					<div class="title">
						选择站点
					</div>
				</div>
				<div class="tabcontent">
					<table cellspacing="0" cellpadding="0" border="0" align="center">
						<tr>
							<th>
								开始时间：
							</th>
							<td>
								<input id="starttime" name="starttime" class="Wdate inputtext" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endtime\')}'})" />
							</td>
							<th>
								结束时间：
							</th>
							<td>
								<input id="endtime" name="endtime" class="Wdate inputtext" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'starttime\')}'})" />
							</td>
						</tr>
						<tr>
							<th>
								计划名称：
							</th>
							<td>
								<input id="planname" name="planname" class="inputtext" type="text" maxlength="60" />
							</td>
							<th>
								站点名称：
							</th>
							<td>
								<input id="resourcename" name="resourcename" class="inputtext" type="text" maxlength="60" />
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="button" onclick="query();" class="button" value="查询" />
								<input type="button" onclick="selectOK();" class="button" value="选择确定" />
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div id="content" align="center" style="padding-top: 2px">
				<table id="resourcegrid"></table>
				<div id="resourcepager"></div>
			</div>
		</form>
	</body>
	<script type="text/javascript">
    //查询
	function query() {
		jQuery("#resourcegrid").jqGrid().setGridParam({
            postData: {
            	'noPatrolStation.planName':jQuery("#planname").val(),
            	'noPatrolStation.stationName':jQuery("#resourcename").val(),
            	'noPatrolStation.startDate':jQuery("#starttime").val(),
            	'noPatrolStation.endDate':jQuery("#endtime").val()
            } 
		}).trigger("reloadGrid");
	}
	//选择确定
	function selectOK(){
		var jqgrid=jQuery("#resourcegrid");
		var ids;
		var arr=new Array();
		ids = jqgrid.jqGrid('getGridParam','selrow'); 
		var rowData = jqgrid.getRowData(ids);
		if(!!ids){
			arr.push(rowData);
		}
		if(arr.length>0){
			window.returnValue = arr;
			window.close();
		}else{
			alert('请选择站点!');
		}
	}
	</script>
</html>