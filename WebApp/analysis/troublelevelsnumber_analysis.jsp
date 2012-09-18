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
<script type="text/javascript"
	src="${ctx}/analysis/js/troubleanalysis_common.js"></script>	
<title>故障分级统计</title>
<script>
	jQuery(function(){
		setContextPath('${ctx }');
	//使用层布局
  var jqgrid=jQuery("#troublegrid").jqGrid({    
		url: "${ctx}/analysis/troubleAnalysisAction!levelnumberlist.action",   
		datatype: "json",    
		mtype: 'GET',
		rownumbers: true,
		colNames:['ID','名称','重大故障', '严重故障','一般故障','故障工单总数'],
		colModel:[
		          {name:'ID',id:'ID',sortable:false,hidden:true},
		          {name:'NAME',id:'NAME',sortable:false,formatter:levelsnumberorgFmatter},
		          {name:'LEVEL_NUM0',id:'LEVEL_NUM0',sortable:false},
		          {name:'LEVEL_NUM1',id:'LEVEL_NUM1',sortable:false},
		          {name:'LEVEL_NUM2',id:'LEVEL_NUM2',sortable:false},
		          {name:'ALL_NUM',id:'ALL_NUM',sortable:false}
		          ],      
		autowidth:true,
		shrinkToFit:true,
		viewrecords: true,
		hidegrid: false,
		jsonReader: {
            root:"root" , 
            repeatitems: false,
            id:"0"
            },
        subGrid: true,
        subGridRowExpanded: function(subgrid_id, row_id) {
        	            var subgrid_table_id;  //子表格ID
        	            subgrid_table_id = subgrid_id + "_t";
        	              
        	            // 动态添加子报表的table和pager  
        	            jQuery("#" + subgrid_id).html("<table id='"+subgrid_table_id+"'class='scroll'></table>");  
        	              
        	            // 创建jqGrid对象  
        	            jQuery("#" + subgrid_table_id).jqGrid({  
        	                url: "${ctx}/analysis/troubleAnalysisAction!levelnumbersublist.action",  //   
        	                datatype: "json",
        	                postData: {
        	                 	startTime:jQuery("#starttime").val(),
        	                	endTime:jQuery("#endtime").val(),
        	                	orgId:row_id,
        	                	businessType:jQuery('select[name="businessType"]').find("option:selected").val()
        	                	}, 
        	            	colNames:['ID','名称','重大故障', '严重故障','一般故障','故障工单总数'],
        	        		colModel:[
        	        		          {name:'ID',id:'ID',sortable:false,hidden:true},
        	        		          {name:'NAME',id:'NAME',sortable:false,formatter:levelsnumbergroupFmatter},
        	        		          {name:'LEVEL_NUM0',id:'LEVEL_NUM0',sortable:false},
        	        		          {name:'LEVEL_NUM1',id:'LEVEL_NUM1',sortable:false},
        	        		          {name:'LEVEL_NUM2',id:'LEVEL_NUM2',sortable:false},
        	        		          {name:'ALL_NUM',id:'ALL_NUM',sortable:false}
        	        		          ],  
        	                jsonReader: {   
        	                	   root:"root" ,      
        	                	   repeatitems: false,
        	                       id:"0"
        	                },  
        	                prmNames: {search: "search"},  
        	                viewrecords: true,  
        	                height: "100%"
        	           });  
        	       }  
		  });
	
  jQuery(window).wresize(function(){
		grid_resize(jqgrid);
	});
	grid_resize(jqgrid);
	})

		//查询
	function query() {
		jQuery("#troublegrid").jqGrid().setGridParam({
            postData: {
             	startTime:jQuery("#starttime").val(),
            	endTime:jQuery("#endtime").val(),
            	orgId:jQuery("#orgid").val(),
            	businessType:jQuery('select[name="businessType"]').find("option:selected").val()
            	} 
            }).trigger("reloadGrid");
	}


</script>
</head>
<body>
	<form id="troubleForm" name="troubleForm">
		<div id="header">
			<div class="title_bg">
				<div id="title" class="title">当前位置-故障分级统计</div>
			</div>
			<div class="tabcontent">
				<table cellspacing="0" cellpadding="0" border="0" align="center">
					<tr>
						<th>开始日期：</th>
						<td><input id="starttime" name="starttime" type="text"
							value="" class="Wdate inputtext"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
						</td>
						<th>结束日期：</th>
						<td><input id="endtime" name="endtime" type="text"
							class="Wdate inputtext" value=""
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'starttime\')}'})" />
						</td>
					</tr>
					<tr>
						<th>范围：</th>
						<td><input id="orgname" name="orgname" class="inputtext"
							readonly="readonly" /><a
							href="javascript:searchOrg('${ctx}/commonaccess!getorg.action');"><img
								<img border="0"
								src="${ctx}/css/images/selectcode.gif" />
						</a><input id="orgid" name="orgid" type="hidden" />
						</td>
						<th>专业类型：</th>
						<td><baseinfo:dicselector name="businessType"
								columntype="businesstype" type="select" isQuery="query"></baseinfo:dicselector>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center"><input type="button"
							onclick="query();" class="button" value="查询"></td>
					</tr>
				</table>
			</div>
		</div>
		<div id="content" align="center" style="padding-top: 2px">
			<table id="troublegrid"></table>
		</div>
	</form>
</body>

</html>