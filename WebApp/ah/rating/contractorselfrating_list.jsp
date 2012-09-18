<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${ctx}/js/WdatePicker/WdatePicker.js"></script>
<title>已取消任务</title>
<script type="text/javascript">
	jQuery(function(){
	setContextPath('${ctx }');
//使用层布局
var jqgrid=jQuery("#workordergrid").jqGrid({    
	url: "${ctx}/ah/ContractorSelfRatingAction!listData.action",   
	datatype: "json",    
	mtype: 'GET',
	rownumbers: true,
	colNames:['人员姓名','员工编号','职位','区域','是否参与考核','考核时间','组织机构','自评得分','操作','月考核表'],
	colModel:[
	          {name:'USERNAME',id:'USERNAME',sortable:false},
	          {name:'EMPLOYEE_NUM',id:'EMPLOYEE_NUM',sortable:false},
	          {name:'JOBINFO',id:'JOBINFO',sortable:false},
	          {name:'REGIONNAME',id:'REGIONNAME',sortable:false},
	          {name:'IS_EXAM',id:'IS_EXAM',sortable:false},
	          {name:'YEAR_MONTH',id:'YEAR_MONTH',sortable:false,formatter:'date',formatoptions: {newformat:'Y-m'} },
	          {name:'ORGNAME',id:'ORGNAME',sortable:false},
	          {name:'SELF_ASSE_NUM',id:'SELF_ASSE_NUM',sortable:false},
	          {name:'ID',id:'ID',sortable:false,formatter:HanledActionFmatter},
	          {name:'ID',id:'ID',sortable:false,hidden:true}
	          ],      
	rowNum:15,
	autowidth:true,
	rowList:[15,30,50],    
	pager: '#workorderpager',
	shrinkToFit:true,
	viewrecords: true, 
	multiselect: true,
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
	if(!cellvalue){
		cellvalue = "";
	}
	var edit = '<a href="#" style="color: blue;text-decoration: underline;" onclick="edit(\''
			+ cellvalue + '\',\'' + rowObjec.SID + '\',\'' + rowObjec.TABLE_ID + '\')">编辑</a>';
	return edit;
}
//设置参考
function edit(id,personId,tableid){
	window.location.href="${ctx}/ah/ContractorSelfRatingAction!input.action?id="+ id +"&personId=" +personId +"&tableid=" + tableid;
}
//提交
function save(){
	var s,grid;
	grid=jQuery("#workordergrid");
	ids = grid.jqGrid('getGridParam','selarrrow');
	   if (ids.length>0) {
           var names = [];
           var row = grid.jqGrid('getRowData',ids[0]);
           if(!row.YEAR_MONTH){
           		alert("选择列的考核时间不能为空并且要都相同！");
           		return false;
           }
           var yearmonth = row.YEAR_MONTH;
           for(var j=1; j< ids.length; j++){
           		row = grid.jqGrid('getRowData',ids[j]);
           		if(!row.YEAR_MONTH || row.YEAR_MONTH!=yearmonth){
           			alert("选择列的考核时间不能为空并且要都相同！");
           			return false;
           		}
           }
           for (var i=0, il=ids.length; i < il; i++) {
               row = grid.jqGrid('getRowData',ids[i]);
               names.push(row.ID);
               jQuery('#ids').val(names.join(","));
           }
           document.getElementById('initdiv').style.display="none";
           document.getElementById('hiddendiv').style.display="block";
	   }else{
		alert("请选择要提交的行！");   
	   }
}
function Undo(){
	document.getElementById('initdiv').style.display="block";
    document.getElementById('hiddendiv').style.display="none";
    jQuery('#ids').val("");
}
function SubmitData(){
	document.getElementById("Form5").submit();
}
</script>
</head>
<body>
		<div id="header">
			<div class="title_bg">
				<div class="title">当前位置-代维人员自评</div>
			</div>
		</div>
			<div id="hiddendiv" align="center" style="display:none" class="tabcontent" >
			<form id="Form5" method="post" action="${ctx }/ah/ContractorSelfRatingAction!submitData.action">
			<table cellspacing="0" cellpadding="0" class="Detailed_list"
				border="0" align="center">
					<tr>
						<td colspan="4">
						<font color="red">请确认是否将选中的代维人员考核结果提交上一级审核</font>
						</td>
					</tr>
					<tr>
						<th>
							备注：
						</th>
						<td colspan="3">
							<textarea rows="3" cols="100" id = "remark" name="remark"></textarea>
						</td>
					</tr>
				<tr>
					<td colspan="4" align="center"><input class="button"
						type="button" value="提交" onclick="SubmitData()"/>
						<input class="button"
						type="button" value="返回" onclick="Undo()">
						<input type="hidden" id="ids" name="ids" />
						<input type="hidden" name="result"  value="1" />
					</td>
				</tr>
			</table>
			</form>
		</div>
		<form id="workorderForm" name="form">
		<div class="tabcontent" id="initdiv">
				<table cellspacing="0" cellpadding="0" border="0" align="center">
					<tr>
						<td colspan="4" align="center">
						   
							<input type="button" onclick="save();" class="button" value="提交" />
							
						</td>
					</tr>
				</table>
			</div>
		<div id="content" align="center" style="padding-top: 2px">
			<table id="workordergrid"></table>
			<div id="workorderpager"></div>
		</div>
	</form>
</body>
</html>