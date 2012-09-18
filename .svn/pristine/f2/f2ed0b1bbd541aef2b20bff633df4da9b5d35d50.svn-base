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
<script type="text/javascript">
	In.add('jqui',{path:'${ctx}/js/jQuery/jqueryui/css/redmond/jquery-ui.custom.css'});
In.add('jquijs',{path:'${ctx}/js/jQuery/jqueryui/jquery-ui.custom.min.js',type:'js',charset:'utf-8',rely:['jqui']});
In.add('common',{path:'${ctx}/common/js/common.js',type:'js',charset:'utf-8'});
In.add('jgcss',{path:'${ctx}/js/jQuery/jqgrid/css/ui.jqgrid.css',charset:'utf-8',rely:['jquijs']});
In.add('jgjs',{path:'${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js',type:'js',charset:'utf-8',rely:['jgcss']});
In.add('jgcn',{path:'${ctx}/js/jQuery/jqgrid/grid.locale-cn.js',type:'js',charset:'utf-8',rely:['jgjs']});
</script>
</head>
<body>
	<table id="list"></table>
	<div id="pager"></div>
</body>
<script type="text/javascript">
	In.ready('jgcn', function() {
 var jqgrid=jQuery("#list").jqGrid({    
		url: "${ctx}/wplan/patrolresourceAction!query.action?planid=${planid}",   
		datatype: "json",    
		mtype: 'GET',
		colNames:['站点名称','专业类型', '站点编号','站点地址'],
		colModel:[
		          {name:'NAME',id:'NAME', width:150,sortable:false},
		          {name:'RESOURCE_TYPENAME',id:'RESOURCE_TYPENAME',width:150,sortable:false},
		          {name:'STATIONCODE',id:'STATIONCODE',width:150,sortable:false},
		          {name:'ADDRESS',id:'ADDRESS',width:150,sortable:false}
		          ],      
		rowNum:10,
		autowidth:true,
		rowList:[10,30,50,100],
		prmNames: {page:"pageNo",rows:"rows", sort: "sidx",order: "sord"},   
		pager: '#pager',    
		viewrecords: true, 
		jsonReader: {
               root:"result" ,                // 数据行（默认为：rows） 
               page: "pageNo" ,            // 当前页 
               total: "totalPages" ,    // 总页数 
               records: "totalCount",     // 总记录数 
               repeatitems: false,
               id:"0"
               }
		  }).navGrid('#pager',{edit:false ,add:false ,del:false,search:false });
})
</script>
</html>