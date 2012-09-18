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
  getlevelGrid('Atrouble','${ctx}/analysis/troubleDetailAction!onelevelnumberlist.action');
  var initialized = [false,false,false]; //是否已初始化
	tab.bind('tabsshow', function(event, ui) {
		//选择二个tab时，生成未巡检rfid
		if (ui.index == 1 && !initialized[ui.index]){
			  getlevelGrid('Btrouble','${ctx}/analysis/troubleDetailAction!twolevelnumberlist.action');
	     }else if(ui.index == 2 && !initialized[ui.index]){
	    	 getlevelGrid('Ctrouble','${ctx}/analysis/troubleDetailAction!threenumberlist.action'); 
	     }
		initialized[ui.index] = true; 
	});
	})
	//生成等级Grid
	function getlevelGrid(id,url){
		var gridid="#"+id+"grid";
		var pageid="#"+id+"pager";
	return	jQuery(gridid).jqGrid({    
			url: url, 
			datatype: "json",    
			mtype: 'GET',
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
			rowNum:10,
			autowidth:true,
			shrinkToFit:true,
			postData:${paramMap},
			viewrecords: true,
			rowList:[10,20,30],    
			pager: pageid,
			prmNames: {page:"pageNo",rows:"rows", sort: "sidx",order: "sord"},   
			jsonReader: {
	               root:"result" ,                // 数据行（默认为：rows） 
	               page: "pageNo" ,            // 当前页 
	               total: "totalPages" ,    // 总页数 
	               records: "totalCount",     // 总记录数 
	               repeatitems: false
	               }
			  }).navGrid(pageid,{edit:false ,add:false ,del:false,search:false,sortable:false });
	}
</script>
</head>
<body>
	<form id="troubleForm" name="troubleForm">
		<div id="header">
			<div class="title_bg">
				<div class="title">当前位置-故障处理分级明细</div>
			</div>
			<div class="tabcontent" style="text-align: center; margin-top: 10px">
				<input name="" value="返回" type="button" class="button"
					onclick="history.go(-1);" />
			</div>
		</div>
		<div id="content" align="center" style="padding-top: 2px">
			<div id="tab_process">
				<ul>
					<li><a href="#Atrouble">重大故障工单</a>
					</li>
					<li><a href="#Btrouble">严重故障工单</a>
					</li>
					<li><a href="#Ctrouble">一般故障工单</a>
					</li>
				</ul>
				<div id="Atrouble">
					<table id="Atroublegrid"></table>
					<div id="Atroublepager"></div>
				</div>
				<div id="Btrouble">
					<table id="Btroublegrid"></table>
					<div id="Btroublepager"></div>
				</div>
				<div id="Ctrouble">
					<table id="Ctroublegrid"></table>
					<div id="Ctroublepager"></div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>