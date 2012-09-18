<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx }/js/jQuery/jqgrid/css/ui.jqgrid.css" type="text/css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${ctx}/js/jQuery/jqgrid/grid.locale-cn.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js"></script>
<title>已取消任务</title>
<script type="text/javascript">
	jQuery(function(){
	setContextPath('${ctx }');
	


	
//使用层布局
var jqgrid=jQuery("#workordergrid").jqGrid({    
	url: "${ctx}/ah/personFlowAction!listData.action",   
	datatype: "json",    
	mtype: 'POST',
	rownumbers: true,
	colNames:['人员姓名','第一环节处理人', '第二环节处理人','第三环节处理人','第四环节处理人','第五环节处理人','操作','PID'],
	colModel:[
	          {name:'PERSON_NAME',id:'PERSON_NAME',sortable:false},
	          {name:'index1',id:'INDEX1',sortable:false},
	          {name:'index2',id:'INDEX2',sortable:false},
	          {name:'index3',id:'INDEX3',sortable:false},
	          {name:'index4',id:'INDEX4',sortable:false},
			  {name:'index5',id:'INDEX5',sortable:false},

	          {name:'ID',id:'ID',sortable:false,formatter:editFmatter},
	          {name:'PID',id:'PID',sortable:false,hidden:true}
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
//查询
function query() {
	jQuery("#workordergrid").jqGrid().setGridParam({
        postData: {
        	regionId:jQuery("#query_region_id").val(),
        	orgId:jQuery("#query_org_id").val(),
        	businessType:jQuery('select[name="query_businesstype"]').find("option:selected").val(),
        	postOffice:jQuery('select[name="query_personjob"]').find("option:selected").val(),
        	} 
        }).trigger("reloadGrid");
}
//添加
function add(){
	window.location.href = "${ctx}/ah/personFlowAction!input.action";
}
//编辑
function edit(personId) {
	window.location.href = "${ctx}/ah/personFlowAction!edit.action?personId="+personId;
}
//删除
function del(personId) {
	window.location.href = "${ctx}/ah/personFlowAction!delete.action?personId="+personId;
}
//编辑按钮
function editFmatter(cellvalue, options, rowObjec) {
	var view = '<a href="#" style="color: blue;text-decoration: underline;" onclick="edit(\''+ rowObjec.PERSON_ID + '\')">编辑</a>';
	    view+='&nbsp;&nbsp;<a href="#" style="color: blue;text-decoration: underline;" onclick="del(\''+ rowObjec.PERSON_ID + '\')">删除</a>';
	return view;
}
//查找区域
function searchQueryRegion(url){
	var val = showRegion(url);
	if (!!val) {
		jQuery("#query_region_id").val(val[0]);
		jQuery("#query_region").val(val[1]);
	}
}
//查找组织
function searchQueryOrg(url){
    var val = showOrg(url);
	if (!!val) {
		jQuery("#query_org_id").val(val[0]);
		jQuery("#query_org").val(val[1]);
	}
}
</script>
</head>
<body>
	<form id="workorderForm" name="form">
		<div id="header">
			<div class="title_bg">
				<div class="title">当前位置-人员流程定义</div>
			</div>
			<div class="tabcontent">
				<table cellspacing="0" cellpadding="0" border="0" align="center">
					<tr>
						<th>
							所属区域
						</th>
						<td>
					  		<input id="query_region" class="inputtext" readonly="readonly" />
							<a href="javascript:searchQueryRegion('${ctx}/commonaccess!getregion.action');">
							<img border="0" src="${ctx}/css/images/selectcode.gif" /></a>
					    	<input type="hidden" id="query_region_id" />
						</td>
						<th>
							代维公司
						</th>
						<td>
							<input id="query_org" class="inputtext" readonly="readonly" />
							<a href="javascript:searchQueryOrg('${ctx}/commonaccess!getorg.action');">
							<img border="0" src="${ctx}/css/images/selectcode.gif" /></a>
							<input type="hidden" id="query_org_id" />
						</td>
					</tr>
					<tr>
						<th>
							专业
						</th>
						<td>
					  		<baseinfo:dicselector name="query_businesstype" cssClass="treetext" columntype="businesstype" type="select" isQuery="query"></baseinfo:dicselector>
						</td>
						<th>
							职位
						</th>
						<td>
							<baseinfo:dicselector name="query_personjob" cssClass="treetext" columntype="job_type" type="select" isQuery="query"></baseinfo:dicselector>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<input type="button" onclick="query();" class="button" value="查询" />
							<input type="button" onclick="add();" class="button" value="添加" />
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div id="content" align="center" style="padding-top: 2px">
			<table id="workordergrid"></table>
			<div id="workorderpager"></div>
		</div>
	</form>
</body>
</html>