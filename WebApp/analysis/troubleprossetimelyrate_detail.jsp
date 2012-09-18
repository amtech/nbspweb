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
<title>故障处理及时率明细</title>
<script>
	jQuery(function(){
		setContextPath('${ctx }');
		var tab=jQuery("#tab_process").tabs({ cache: true,selected: 0 }); //缓存tab页
	//使用层布局
  var jqgrid=jQuery("#introublegrid").jqGrid({    
		url: "${ctx}/analysis/troubleDetailAction!timelyprosseinlist.action",   
		datatype: "json",    
		mtype: 'GET',
		rownumbers: true,
		colNames:['ID','故障单标题','EMOS单号','故障发生时间', '故障发生地点','故障级别','发现方式','处理时长（小时）'],
		colModel:[
		          {name:'ID',id:'ID',sortable:false,hidden:true},
		          {name:'TITLE',id:'TITLE',sortable:false},
		          {name:'EOMS_ID',id:'EOMS_ID',sortable:false},
		          {name:'TROUBLE_TIME',id:'TROUBLE_TIME',sortable:false},
		          {name:'ADDRESS',id:'ADDRESS',sortable:false},
		          {name:'IS_INSTANCY',id:'IS_INSTANCY',sortable:false},
		          {name:'FIND_TYPE',id:'FIND_TYPE',sortable:false},
		          {name:'LEN',id:'LEN',sortable:false}
		          ],      
		autowidth:true,
		shrinkToFit:true,
		viewrecords: true,
		hidegrid: false,
		postData:${paramMap},
		pager: '#introublepager',
		prmNames: {page:"pageNo",rows:"rows", sort: "sidx",order: "sord"},   
		jsonReader: {
               root:"result" ,                // 数据行（默认为：rows） 
               page: "pageNo" ,            // 当前页 
               total: "totalPages" ,    // 总页数 
               records: "totalCount",     // 总记录数 
               repeatitems: false,
               id:"0"
               }
		  }).navGrid('#introublepager',{edit:false ,add:false ,del:false,search:false,sortable:false  });
	var initialized = false; //是否已初始化
	tab.bind('tabsshow', function(event, ui) {
		//选择二个tab时，生成未巡检rfid
		if (ui.index == 1 && !initialized){ 
	jQuery("#overtroublegrid").jqGrid({    
		url: "${ctx}/analysis/troubleDetailAction!timelyprosseoverlist.action", 
		datatype: "json",    
		mtype: 'POST',
		colNames:['ID','故障单标题','EMOS单号','故障发生时间', '故障发生地点','故障级别','发现方式','处理时长'],
		colModel:[
		          {name:'ID',id:'ID',sortable:false,hidden:true},
		          {name:'TITLE',id:'TITLE',sortable:false},
		          {name:'EOMS_ID',id:'EOMS_ID',sortable:false},
		          {name:'TROUBLE_TIME',id:'TROUBLE_TIME',sortable:false},
		          {name:'ADDRESS',id:'ADDRESS',sortable:false},
		          {name:'IS_INSTANCY',id:'IS_INSTANCY',sortable:false},
		          {name:'FIND_TYPE',id:'FIND_TYPE',sortable:false},
		          {name:'LEN',id:'LEN',sortable:false}
		          ],         
		rowNum:10,
		autowidth:true,
		shrinkToFit:true,
		postData:${paramMap},
		viewrecords: true,
		rowList:[10,20,30],    
		pager: '#overtroublepager',
		prmNames: {page:"pageNo",rows:"rows", sort: "sidx",order: "sord"},   
		jsonReader: {
               root:"result" ,                // 数据行（默认为：rows） 
               page: "pageNo" ,            // 当前页 
               total: "totalPages" ,    // 总页数 
               records: "totalCount",     // 总记录数 
               repeatitems: false,
               id:"0"
               }
		  }).navGrid('#overtroublepager',{edit:false ,add:false ,del:false,search:false,sortable:false });
	     }
	     initialized = true; 
	});
	})
</script>
</head>
<body>
	<form id="troubleForm" name="troubleForm">
		<div id="header">
			<div class="title_bg">
				<div class="title">当前位置-故障处理及时率明细</div>
			</div>
			<div class="tabcontent" style="text-align: center; margin-top: 10px">
				<input name="" value="返回" type="button" class="button"
					onclick="history.go(-1);" />
			</div>
		</div>
		<div id="content" align="center" style="padding-top: 2px">
			<div id="tab_process">
				<ul>
					<li><a href="#introuble">及时处理工单</a></li>
					<li><a href="#overtrouble">超时处理工单</a></li>

				</ul>
				<div id="introuble">
					<table id="introublegrid"></table>
					<div id="introublepager"></div>
				</div>
				<div id="overtrouble">
					<table id="overtroublegrid"></table>
					<div id="overtroublepager"></div>
				</div>
			</div>
		</div>
	</form>
</body>

</html>