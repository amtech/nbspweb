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
<title>工单处理及时率明细</title>
<script>
	jQuery(function(){
		setContextPath('${ctx }');
		var tab=jQuery("#tab_process").tabs({ cache: true,selected: 0 }); //缓存tab页
	//使用层布局
  var jqgrid=jQuery("#inworkordergrid").jqGrid({    
		url: "${ctx}/analysis/workOrderDetailAction!timelyprosseinlist.action",   
		datatype: "json",    
		mtype: 'GET',
		rownumbers: true,
		colNames:['ID','工单单号','工单标题','工单创建时间', '工单类型','工单时长（小时）'],
		colModel:[
		          {name:'ID',id:'ID',sortable:false,hidden:true},
		          {name:'TASK_CODE',id:'TASK_CODE',sortable:false},
		          {name:'TASK_NAME',id:'TASK_NAME',sortable:false},
		          {name:'CREATE_DATE_DIS',id:'CREATE_DATE_DIS',sortable:false},
		          {name:'TASK_TYPE_DIS',id:'TASK_TYPE_DIS',sortable:false},
		          {name:'LEN',id:'LEN',sortable:false}
		          ],     
		autowidth:true,
		shrinkToFit:true,
		viewrecords: true,
		hidegrid: false,
		postData:${paramMap},
		pager: '#inworkorderpager',
		prmNames: {page:"pageNo",rows:"rows", sort: "sidx",order: "sord"},   
		jsonReader: {
               root:"result" ,                // 数据行（默认为：rows） 
               page: "pageNo" ,            // 当前页 
               total: "totalPages" ,    // 总页数 
               records: "totalCount",     // 总记录数 
               repeatitems: false,
               id:"0"
               }
		  }).navGrid('#inworkorderpager',{edit:false ,add:false ,del:false,search:false,sortable:false  });
	var initialized = false; //是否已初始化
	tab.bind('tabsshow', function(event, ui) {
		//选择二个tab时，生成未巡检rfid
		if (ui.index == 1 && !initialized){ 
	jQuery("#overworkordergrid").jqGrid({    
		url: "${ctx}/analysis/workOrderDetailAction!timelyprosseoverlist.action", 
		datatype: "json",    
		mtype: 'GET',
		colNames:['ID','工单单号','工单标题','工单创建时间', '工单类型','工单时长'],
		colModel:[
		          {name:'ID',id:'ID',sortable:false,hidden:true},
		          {name:'TASK_CODE',id:'TASK_CODE',sortable:false},
		          {name:'TASK_NAME',id:'TASK_NAME',sortable:false},
		          {name:'CREATE_DATE_DIS',id:'CREATE_DATE_DIS',sortable:false},
		          {name:'TASK_TYPE_DIS',id:'TASK_TYPE_DIS',sortable:false},
		          {name:'LEN',id:'LEN',sortable:false}
		          ],         
		rowNum:10,
		autowidth:true,
		shrinkToFit:true,
		postData:${paramMap},
		viewrecords: true,
		rowList:[10,20,30],    
		pager: '#overworkorderpager',
		prmNames: {page:"pageNo",rows:"rows", sort: "sidx",order: "sord"},   
		jsonReader: {
               root:"result" ,                // 数据行（默认为：rows） 
               page: "pageNo" ,            // 当前页 
               total: "totalPages" ,    // 总页数 
               records: "totalCount",     // 总记录数 
               repeatitems: false,
               id:"0"
               }
		  }).navGrid('#overworkorderpager',{edit:false ,add:false ,del:false,search:false,sortable:false });
	     }
	     initialized = true; 
	});
	})
</script>
</head>
<body>
	<form id="workorderForm" name="workorderForm">
		<div id="header">
			<div class="title_bg">
				<div class="title">当前位置-工单处理明细</div>
			</div>
			<div class="tabcontent" style="text-align: center; margin-top: 10px">
				<input name="" value="返回" type="button" class="button"
					onclick="history.go(-1);" />
			</div>
		</div>
		<div id="content" align="center" style="padding-top: 2px">
			<div id="tab_process">
				<ul>
					<li><a href="#inworkorder">及时处理工单</a>
					</li>
					<li><a href="#overworkorder">超时处理工单</a>
					</li>

				</ul>
				<div id="inworkorder">
					<table id="inworkordergrid"></table>
					<div id="inworkorderpager"></div>
				</div>
				<div id="overworkorder">
					<table id="overworkordergrid"></table>
					<div id="overworkorderpager"></div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>