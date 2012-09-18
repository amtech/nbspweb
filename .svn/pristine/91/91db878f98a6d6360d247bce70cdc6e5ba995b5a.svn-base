<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx }/js/jQuery/jqgrid/css/ui.jqgrid.css" type="text/css"
	rel="stylesheet" />
<link href="${ctx }/js/WdatePicker/skin/WdatePicker.css" type="text/css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${ctx}/js/WdatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jqgrid/grid.locale-cn.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js"></script>
<title>月报表列表</title>
<script type="text/javascript">
	jQuery(function(){
	setContextPath('${ctx }');
//使用层布局
var jqgrid=jQuery("#workordergrid").jqGrid({    
	url: "${ctx}/ah/ahExcelReportRecodeAction!listData.action",   
	datatype: "json",    
	mtype: 'POST',
	rownumbers: true,
	colNames:['地市','报表月份','上传时间','上传人' ,'操作'],
	colModel:[
	          {name:'REGIONNAME',id:'REGIONNAME',sortable:false},
	          {name:'REPORTDATE',id:'REPORTDATE',sortable:false},
	          {name:'UPLOADTIME',id:'UPLOADTIME',sortable:false},
	          {name:'USERNAME',id:'USERNAME',sortable:false},
	          {name:'ID',id:'ID',sortable:false,formatter:downloadFmatter}
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
        	reportDate:jQuery("#report_date").val()
        	} 
        }).trigger("reloadGrid");
}
//下载按钮
function downloadFmatter(cellvalue, options, rowObjec) {
	var view = '<a href="#" style="color: blue;text-decoration: underline;" onclick="downloadReport(\''+ rowObjec.ID+ '\')">下载</a>';
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
function downloadReport(id){
	window.location.href = "${ctx}/ah/ahExcelReportRecodeAction!downloadReport.action?id="+id;
}
</script>
</head>
<body>
	<form id="workorderForm" name="form">
		<div id="header">
			<div class="title_bg">
				<div class="title">当前位置-Excel月报表查询</div>
			</div>
			<div class="tabcontent">
				<table cellspacing="0" cellpadding="0" border="0" align="center">
					<tr>
						<th>
							所属区域
						</th>
						<td>
						    <c:if test="${isProvinceMobile}">
					  			<input id="query_region" class="inputtext" readonly="readonly" />
								<a href="javascript:searchQueryRegion('${ctx}/commonaccess!getregion.action');">
								<img border="0" src="${ctx}/css/images/selectcode.gif" /></a>
					    		<input type="hidden" id="query_region_id" />
					    	</c:if>
					    	<c:if test="${!isProvinceMobile}">
								<baseinfo:region displayProperty="regionname" id="${LOGIN_USER.regionId}"></baseinfo:region>
					    	</c:if>
						</td>
						<th>
							月报表时间
						</th>
						<td>
							<input type="text" id="report_date" name="reportDate" class="Wdate" readonly onfocus="WdatePicker({dateFmt:'yyyy-MM'})" />
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<input type="button" onclick="query();" class="button" value="查询" />
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