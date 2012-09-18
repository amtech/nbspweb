<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx }/js/jQuery/jqgrid/css/ui.jqgrid.css" type="text/css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${ctx}/js/jQuery/jqgrid/grid.locale-cn.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/WdatePicker/WdatePicker.js"></script>
<title>超时工单</title>
<script>
	jQuery(function(){
	//使用层布局
  var jqgrid=jQuery("#workordergrid").jqGrid({    
		url: "${ctx}/analysis/workOrderAnalysisAction!overtimeratelist.action",   
		datatype: "json",    
		mtype: 'GET',
		rownumbers: true,
		colNames:['ID','名称','超时工单总历时（小时）','超时工单平均历时（小时）','超时工单数','工单总数','超时工单比率'],
		colModel:[
		          {name:'ID',id:'ID',sortable:false,hidden:true},
		          {name:'NAME',id:'NAME',sortable:false},
		          {name:'OVERTIME_LEN',id:'OVERTIME_LEN',sortable:false},
		          {name:'OVERTIME_AVG_LEN',id:'OVERTIME_AVG_LEN',sortable:false},
		          {name:'OVERTIME_NUM',id:'OVERTIME_NUM',sortable:false},
		          {name:'ALL_NUM',id:'ALL_NUM',sortable:false},
		          {name:'OVERTIME_RATE',id:'OVERTIME_RATE',sortable:false}
		          ],      
		autowidth:true,
		shrinkToFit:true,
		viewrecords: true,
		hidegrid: false,
		jsonReader: {
            root:"root" , 
            repeatitems: false,
            id:"0"
            }
		  });
	
  jQuery(window).wresize(function(){
		grid_resize(jqgrid);
	});
	grid_resize(jqgrid);
	})

		//查询
	function query() {
		jQuery("#workordergrid").jqGrid().setGridParam({
            postData: {
             	startTime:jQuery("#starttime").val(),
            	endTime:jQuery("#endtime").val(),
            	orgId:jQuery("#orgid").val(),
            	taskType:jQuery('select[name="taskType"]').find("option:selected").val()
            	} 
            }).trigger("reloadGrid");
	}

</script>
</head>
<body>
	<form id="troubleForm" name="troubleForm">
		<div id="header">
			<div class="title_bg">
				<div id="title" class="title">当前位置-工单处理及时率</div>
			</div>
			<div class="tabcontent">
				<table cellspacing="0" cellpadding="0" border="0" align="center">
					<tr>
						<th>开始日期：</th>
						<td><input id="starttime" name="starttime" type="text"
							value="" class="Wdate inputtext"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
						</td>
						<th>结束日期：</th>
						<td><input id="endtime" name="endtime" type="text"
							class="Wdate inputtext" value=""
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'starttime\')}'})" />
						</td>
					</tr>
					<tr>
						<th>范围：</th>
						<td><input id="orgname" name="orgname" class="inputtext"
							readonly="readonly" /><a
							href="javascript:searchOrg('${ctx}/commonaccess!getorg.action');"><img
								<img border="0"
								src="${ctx}/css/images/selectcode.gif" />
						</a><input id="orgid" name="orgid" type="hidden" />
						</td>
						<th>工单类型：</th>
						<td><baseinfo:dicselector name="taskType"
								columntype="TASK_CODE" type="select" isQuery="query"></baseinfo:dicselector>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center"><input type="button"
							onclick="query();" class="button" value="查询"></td>
					</tr>
				</table>
			</div>
		</div>
		<div id="content" align="center" style="padding-top: 2px">
			<table id="workordergrid"></table>
		</div>
	</form>
</body>

</html>