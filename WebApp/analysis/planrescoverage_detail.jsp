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
<title>计划资源覆盖率明细</title>
<script>
	jQuery(function(){
		setContextPath('${ctx }');
		var tab=jQuery("#tab_process").tabs({ cache: true,selected: 0 }); //缓存tab页
	//使用层布局
  var jqgrid=jQuery("#inplangrid").jqGrid({    
		url: "${ctx}/analysis/planDetailAction!planresincoverlist.action",   
		datatype: "json",    
		mtype: 'GET',
		rownumbers: true,
		colNames:['ID','站点编号','站点名称','站点类型', '站点维护单位','站点维护组'],
		colModel:[
		          {name:'ID',id:'ID',sortable:false,hidden:true},
		          {name:'STATIONCODE',id:'STATIONCODE',sortable:false},
		          {name:'RSNAME',id:'RSNAME',sortable:false},
		          {name:'RSTYPE',id:'RSTYPE',sortable:false},
		          {name:'ORGNAME',id:'ORGNAME',sortable:false},
		          {name:'PATROLNAME',id:'PATROLNAME',sortable:false}
		          ],      
		autowidth:true,
		shrinkToFit:true,
		viewrecords: true,
		hidegrid: false,
		postData:${paramMap},
		pager: '#inplanpager',
		prmNames: {page:"pageNo",rows:"rows", sort: "sidx",order: "sord"},   
		jsonReader: {
               root:"result" ,                // 数据行（默认为：rows） 
               page: "pageNo" ,            // 当前页 
               total: "totalPages" ,    // 总页数 
               records: "totalCount",     // 总记录数 
               repeatitems: false,
               id:"0"
               }
		  }).navGrid('#inplanpager',{edit:false ,add:false ,del:false,search:false,sortable:false  });
	var initialized = false; //是否已初始化
	tab.bind('tabsshow', function(event, ui) {
		//选择二个tab时，生成未巡检rfid
		if (ui.index == 1 && !initialized){ 
	jQuery("#overplangrid").jqGrid({    
		url: "${ctx}/analysis/planDetailAction!planresovercoverlist.action",
		datatype: "json",    
		mtype: 'POST',
		colNames:['ID','站点编号','站点名称','站点类型', '站点维护单位','站点维护组'],
		colModel:[
		          {name:'ID',id:'ID',sortable:false,hidden:true},
		          {name:'STATIONCODE',id:'STATIONCODE',sortable:false},
		          {name:'RSNAME',id:'RSNAME',sortable:false},
		          {name:'RSTYPE',id:'RSTYPE',sortable:false},
		          {name:'ORGNAME',id:'ORGNAME',sortable:false},
		          {name:'PATROLNAME',id:'PATROLNAME',sortable:false}
		          ],      
		rowNum:10,
		autowidth:true,
		shrinkToFit:true,
		postData:${paramMap},
		viewrecords: true,
		rowList:[10,20,30],    
		pager: '#overplanpager',
		prmNames: {page:"pageNo",rows:"rows", sort: "sidx",order: "sord"},   
		jsonReader: {
               root:"result" ,                // 数据行（默认为：rows） 
               page: "pageNo" ,            // 当前页 
               total: "totalPages" ,    // 总页数 
               records: "totalCount",     // 总记录数 
               repeatitems: false,
               id:"0"
               }
		  }).navGrid('#overplanpager',{edit:false ,add:false ,del:false,search:false,sortable:false });
	     }
	     initialized = true; 
	});
	})
</script>
</head>
<body>
	<form id="planForm" name="planForm">
		<div id="header">
			<div class="title_bg">
				<div class="title">当前位置-计划资源覆盖率明细</div>
			</div>
			<div class="tabcontent" style="text-align: center; margin-top: 10px">
				<input name="" value="返回" type="button" class="button"
					onclick="history.go(-1);" />
			</div>
		</div>
		<div id="content" align="center" style="padding-top: 2px">
			<div id="tab_process">
				<ul>
					<li><a href="#inplan">已计划巡检站点</a></li>
					<li><a href="#overplan">未计划巡站点</a></li>
				</ul>
				<div id="inplan">
					<table id="inplangrid"></table>
					<div id="inplanpager"></div>
				</div>
				<div id="overplan">
					<table id="overplangrid"></table>
					<div id="overplanpager"></div>
				</div>
			</div>
		</div>
	</form>
</body>

</html>