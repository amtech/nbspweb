<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html>
<head>
<title>模板列表</title>
<link href="${ctx }/js/WdatePicker/skin/WdatePicker.css" type="text/css"
	rel="stylesheet" />

<script type="text/javascript"
	src="${ctx}/js/WdatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${ctx}/wplan/template/js/template_common.js"></script>	
<script type="text/javascript">
jQuery(function(){
		setUserType('${LOGIN_USER.orgType}');
	//使用层布局
	  var jqgrid=jQuery("#templategrid").jqGrid({    
			url: "${ctx}/wplan/wplanTemplateAction!list.action?businessType="+jQuery('select[name="businessType"]').val(),   
			datatype: "json",    
			mtype: 'POST',
			rownumbers: true,
			colNames:['专业','所属区域', '模板名称','备注','操作'],
			colModel:[
			          {name:'BUSINESSTYPENAME',id:'BUSINESSTYPENAME',sortable:false,width : 100},
			          {name:'REGIONNAME',id:'REGIONNAME',sortable:false,width : 50},
			          {name:'TEMPLATE_NAME',id:'TEMPLATE_NAME',sortable:false,width : 150},
			          {name:'REMARK',id:'REMARK',sortable:false,width : 80},
			          {name:'ID',id:'ID',sortable:false,formatter:TemplateActionFmatter,width : 50}			          
			          ],      
			rowNum:15,
			rowList:[15,20,30],    
			pager: '#templatepager',
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
			  }).navGrid('#templatepager',{edit:false ,add:false ,del:false,search:false,sortable:false  });
		$(window).wresize(function(){
			grid_resize(jqgrid);
		});
		grid_resize(jqgrid);
})

		//查询
	function query() {
		jQuery("#templategrid").jqGrid().setGridParam({
            url: "${ctx}/wplan/wplanTemplateAction!list.action?businessType="+jQuery('select[name="businessType"]').val(),  
			postData: {
            	businessType:jQuery("#businessType").val(),
            	templateName:jQuery("#templateName").val()
            	} 
            }).trigger("reloadGrid");


	}
</script>
</head>
<body>
	<form id="optForm" method="post">
			<div class="title_bg">
				<div id="title" class="title">当前位置-计划模板管理</div>
			</div>
			<div class="tabcontent">
				<table cellspacing="0" cellpadding="0" border="0"  align="center" >
					<tr>
						<th>模板名称：</th>
						<td>
							<input type="text" style="width: 220px;" id="templateName" name="templateName" isQuery="query" value="${templateName }"/>
						</td>
						<th>所属专业：</th>
						<td>
						    <baseinfo:customselector name="businessType" data="${businessTypeMap}" isQuery="query" isReversal="true" cssClass="inputtext" id="businessType" style="width: 220px"></baseinfo:customselector>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center"><input type="button" class="button" onclick="query();"
							value="查询">
							<baseinfo:expexcel cls="exprotButton"  businessId="listWplanTemplate" name="导出excel"></baseinfo:expexcel>
						</td>
					</tr>
				</table>
			</div>
	      <div id="content" align="center" style="padding-top: 2px">
			<table id="templategrid" ></table>
			<div id="templatepager"></div>
		</div>
	</form>
</body>

</html>