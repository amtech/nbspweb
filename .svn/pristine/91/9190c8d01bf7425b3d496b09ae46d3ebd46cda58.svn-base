<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="${ctx }/js/jQuery/jqueryui/css/redmond/jquery-ui.custom.css"
	type="text/css" rel="stylesheet" />
<link href="${ctx }/js/jQuery/jqgrid/css/ui.jqgrid.css" type="text/css"
	rel="stylesheet" />
<link href="${ctx }/js/WdatePicker/skin/WdatePicker.css" type="text/css"
	rel="stylesheet" />
<link href="${ctx}/js/jQuery/layout/jquery.layout.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="${ctx}/js/jQuery/layout/jquery.layout-latest.min.js"></script>

<script type="text/javascript"
	src="${ctx}/js/jQuery/jqueryui/jquery-ui.custom.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jqgrid/grid.locale-cn.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/WdatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${ctx}/wplan/plan/js/patrolinfo_common.js"></script>
<title>工单统计及时率列表</title>
<script>
	jQuery(function(){
		setContextPath('${ctx }');
	//使用层布局
	jQuery("#overtimegrid").jqGrid({    
		url: "${ctx}/workflow/workOrderHandledOverTimeAction!listdata.action",   
		datatype: "json",    
		mtype: 'POST',
		rownumbers: true,
		colNames:['工单标题','派单时间','结束时间','处理期限','派单人','工单类型','工单受理单位','超时时长(单位:分钟)'],
		colModel:[
		          {name:'TASK_NAME',id:'TASK_NAME',sortable:false},
		          {name:'CREATE_DATE',id:'CREATE_DATE',sortable:false},
		          {name:'REPLY_TIME',id:'REPLY_TIME',sortable:false},
		          {name:'DEADLINE',id:'DEADLINE',sortable:false},
		          {name:'USERNAME',id:'USERNAME',sortable:false},
		          {name:'TASK_TYPE',id:'TASK_TYPE',sortable:false},
		          {name:'CONTRACTORNAME',id:'CONTRACTORNAME',sortable:false},
		          {name:'OVERTIME_LENGTH',id:'OVERTIME_LENGTH',sortable:false}
		          ],      
		rowNum:10,
		autowidth:true,
		height: "100%",
		rowList:[10,20,30],    
		pager: '#overtimepager',
		shrinkToFit:true,
		viewrecords: true, 
		hidegrid: false, 
		prmNames: {page:"pageNo",rows:"rows", sort: "sidx",order: "sord"},   
		jsonReader: {
               root:"result" ,                // 数据行（默认为：rows） 
               page: "pageNo" ,            // 当前页 
               total: "totalPages" ,    // 总页数 
               records: "totalCount",     // 总记录数 
               repeatitems: false,
               id:"0"
               }
		  }).navGrid('#overtimepager',{edit:false ,add:false ,del:false,search:false,sortable:false  });
	})
	
		//查询
	function query() {
		jQuery("#overtimegrid").jqGrid().setGridParam({
            postData: {
            	'parameter.startTime':jQuery("#createtime").val(),
            	'parameter.endTime':jQuery("#endtime").val(),
            	'parameter.contractorId':jQuery("#orgid").val(),
            	'parameter.oderType':jQuery("#taskType").val()
            	} 
            }).trigger("reloadGrid");

	}
</script>
</head>
<body>
	<form id="patrolinfoForm" name="patrolinfoForm" method="post">
		<div class="title_bg">
			<div id="title" class="title">当前位置-工单统计超时列表</div>
		</div>
		<div class="tabcontent">
			<table cellspacing="0" cellpadding="0" border="0" align="center">
				<tr>
					<th>代维公司：</th>
					<td><input id="orgname" name="orgname" class="inputtext"
						readonly="readonly" /><a
						href="javascript:searchOrg('${ctx}/commonaccess!getorg.action');"><img
							<img border="0"
								src="${ctx}/css/images/selectcode.gif" />
					</a><input id="orgid" name="orgid" type="hidden" /></td>
					<th>工单类型:</th>
					<td><baseinfo:dicselector id="taskType" name="taskType"
							keyValue="" columntype="TASK_CODE" type="select"
							cssClass="inputtext" isQuery="query"></baseinfo:dicselector>
					</td>
				</tr>
				<tr>
					<th>开始日期：</th>
					<td><input id="createtime" name="createtime" type="text"
						value="" class="Wdate " style="width: 125px"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endtime\')}'})" />
					</td>
					<th>结束日期:</th>
					<td><input id="endtime" name="endtime" type="text"
						class="Wdate" value="" style="width: 125px"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'createtime\')}'})" />
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center"><input type="button"
						onclick="query();" class="button" value="查询"></td>
				</tr>
			</table>
		</div>
		<table id="overtimegrid"></table>
		<div id="overtimepager"></div>
	</form>
</body>

</html>