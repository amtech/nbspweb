<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx }/js/jQuery/jqgrid/css/ui.jqgrid.css" type="text/css"
	rel="stylesheet" />
<link href="${ctx }/js/jQuery/ztree/css/zTreeStyle/zTreeStyle.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript"
	src="${ctx}/js/jQuery/jqgrid/grid.locale-cn.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/WdatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/ztree/jquery.ztree.all.min.js"></script>
<!-- workorder-common -->
<script type="text/javascript"
	src="${ctx}/ah/workorder/js/workorder_common.js"></script>
<title>考核表列表</title>
<script type="text/javascript">
	jQuery(function(){
	setContextPath('${ctx }');
//使用层布局
var jqgrid=jQuery("#workordergrid").jqGrid({    
	url: "${ctx}/ah/PersonRatingFormAction!listData.action",   
	datatype: "json",    
	mtype: 'POST',
	rownumbers: true,
	colNames:['考核表名称','专业','操作'],
	colModel:[
	          {name:'TITLE',id:'TITLE',sortable:false},
	          {name:'BUSINESS_TYPE',id:'BUSINESS_TYPE',sortable:false},
	          {name:'ID',id:'ID',sortable:false,formatter:HanledActionFmatter}
	          ],      
	rowNum:10,
	autowidth:true,
	rowList:[10,20,30],    
	pager: '#workorderpager',
	shrinkToFit:true,
	viewrecords: true, 
	hidegrid: false, 
	prmNames: {page:"pageNo",rows:"rows", sort: "sidx",order: "sord"},   
	jsonReader: {
           root:"result" ,                // 数据行（默认为：rows） 
           page: "pageNo" ,            // 当前页 
           total: "totalPages" ,    // 总页数 
           records: "totalCount",     // 总记录数 
           repeatitems: false
           }
	  }).navGrid('#workorderpager',{edit:false ,add:false ,del:false,search:false,sortable:false  });

	jQuery(window).wresize(function(){
	grid_resize(jqgrid);
});
grid_resize(jqgrid);
});
// 考核
function HanledActionFmatter(cellvalue, options, rowObjec) {
	var edit = '<a href="#" style="color: blue;text-decoration: underline;" onclick="assignPerson(\''
			+ cellvalue + '\',\''+ rowObjec.TITLE + '\')">设置参考人员</a>';
	return edit;
}
//设置参考
function assignPerson(id,tablename){
	window.location.href="${ctx}/ah/PersonRatingFormAction!toassignPersonPage.action?id="+id
	+"&table_name=" + tablename;
}
</script>
</head>
<body>
	<form id="workorderForm" name="form">
		<div id="header">
			<div class="title_bg">
				<div class="title">当前位置-定义人员考核表</div>
			</div>
		</div>
		<div id="content" align="center" style="padding-top: 2px">
			<table id="workordergrid"></table>
			<div id="workorderpager"></div>
		</div>
	</form>
</body>
</html>