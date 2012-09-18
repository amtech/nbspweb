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
	src="${ctx}/analysis/js/plananalysis_common.js"></script>
<title>计划资源覆盖率</title>
<script>
	jQuery(function(){
		setContextPath('${ctx }');
	//初始化grid
  var jqgrid=jQuery("#plangrid").jqGrid({    
		url: "${ctx}/analysis/planAnalysisAction!planrescoverlist.action",   
		datatype: "json",    
		mtype: 'GET',
		rownumbers: true,
		colNames:['ID','名称','维护资源总数', '计划巡检站点数','未计划站点数','计划站点覆盖率'],
		colModel:[
		          {name:'ID',id:'ID',sortable:false,hidden:true},
		          {name:'NAME',id:'NAME',sortable:false,formatter:rescoverorgFmatter},
		          {name:'RESNUM',id:'RESNUM',sortable:false},
		          {name:'PLANNUM',id:'PLANNUM',sortable:false},
		          {name:'UNPLANNUM',id:'UNPLANNUM',sortable:false},
		          {name:'COVRATE',id:'COVRATE',sortable:false}
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
        	                url: "${ctx}/analysis/planAnalysisAction!planrescoversublist.action",  //   
        	                datatype: "json",
        	                postData: {
        	                	startTime:jQuery("#starttime").val(),
        	                	endTime:jQuery("#endtime").val(),
        	                	orgId:row_id,
        	                	resourceType:jQuery('select[name="resourceType"]').find("option:selected").val()
        	                	}, 
        	            	colNames:['ID','名称','维护站点总数', '计划巡检站点数','未计划站点数','计划站点覆盖率'],
        	        		colModel:[
        	        		          {name:'ID',id:'ID',sortable:false,hidden:true},
        	        		          {name:'NAME',id:'NAME',sortable:false,formatter:rescovergroupFmatter},
        	        		          {name:'RESNUM',id:'RESNUM',sortable:false},
        	        		          {name:'PLANNUM',id:'PLANNUM',sortable:false},
        	        		          {name:'UNPLANNUM',id:'UNPLANNUM',sortable:false},
        	        		          {name:'COVRATE',id:'COVRATE',sortable:false}
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
		jQuery("#plangrid").jqGrid().setGridParam({
            postData: {
            	startTime:jQuery("#starttime").val(),
            	endTime:jQuery("#endtime").val(),
            	orgId:jQuery("#orgid").val(),
            	resourceType:jQuery('select[name="resourceType"]').find("option:selected").val()
            	} 
            }).trigger("reloadGrid");


	}


</script>
</head>
<body>
	<form id="troubleForm" name="troubleForm">
		<div id="header">
			<div class="title_bg">
				<div id="title" class="title">当前位置-计划资源覆盖率</div>
			</div>
			<div class="tabcontent">
				<table cellspacing="0" cellpadding="0" border="0" align="center">
					<tr>
						<th>开始日期：</th>
						<td><input id="starttime" name="startTime" type="text"
							value="" class="Wdate inputtext"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
						<th>结束日期：</th>
						<td><input id="endtime" name="endTime" type="text"
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
						</a><input id="orgid" name="orgid" type="hidden" /></td>
						<th>专业类型：</th>
						<td><baseinfo:customselector id="businesstype"
							name="resourceType" data="${businessTypeMap }" cssClass="inputtext" isReversal="true" keyValue="${businesstype}"></baseinfo:customselector>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center"><input type="button"
							onclick="query();" class="button" value="查询">
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div id="content" align="center" style="padding-top: 2px">
			<table id="plangrid"></table>
		</div>
	</form>
</body>

</html>