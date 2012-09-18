<%@ page language="java" contentType="text/html; charset=UTF-8"%>
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
In.add('jquicn',{path:'${ctx}/js/jQuery/jqueryui/zh/jquery.ui.datepicker-zh-CN.js',type:'js',charset:'utf-8',rely:['jquijs']});
In.add('jresize',{path:'${ctx}/js/jQuery/jresize/jquery.wresize.js',type:'js',charset:'utf-8',rely:['jquicn']});
In.add('jgcss',{path:'${ctx}/js/jQuery/jqgrid/css/ui.jqgrid.css',charset:'utf-8',rely:['jresize']});
In.add('jgjs',{path:'${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js',type:'js',charset:'utf-8',rely:['jgcss']});
In.add('jgcn',{path:'${ctx}/js/jQuery/jqgrid/grid.locale-cn.js',type:'js',charset:'utf-8',rely:['jgjs']});
In.add('common',{path:'${ctx}/common/js/common.js',type:'js',charset:'utf-8'});
In.add('patrolinfo_common', {path : '${ctx}/wplan/plan/js/patrolinfo_common.js',type : 'js',charset : 'utf-8'});
</script>
<title>未巡检资源明细</title>
</head>
<body>
	<form id="patrolinfoForm" name="patrolinfoForm">
		<div class="title_bg">
			<div id="title" class="title">当前位置-未巡检资源明细</div>
		</div>
		<div style="text-align: center; margin-top: 10px" align="center">
			<input name="" value="返回" type="button" class="button"
				onclick="history.go(-1);" />
			<table id="lostgrid"></table>
			<div id="lostpager"></div>
		</div>

	</form>
</body>
<script type="text/javascript">
In.ready('jgcn', 'common', 'patrolinfo_common', function() {
	var jqgrid=jQuery("#lostgrid").jqGrid({    
			url: "${ctx}/wplan/patrolinfoExecuteAction!querylostdetail.action?id=${planid}",   
			datatype: "json",    
			mtype: 'POST',
			colNames:['巡检站点','专业类型', '未巡检RFID'],
			colModel:[
			          {name:'RS_NAME',id:'RS_NAME',sortable:false},
			          {name:'RESOURCE_TYPENAME',id:'RESOURCE_TYPENAME',sortable:false},
			          {name:'RITEMCOUNT',id:'RITEMCOUNT',sortable:false}
			          ],      
			rowNum:10,
			autowidth:true,
			rowList:[10,30,50,100],    
			pager: '#lostpager',
			prmNames: {page:"pageNo",rows:"rows", sort: "sidx",order: "sord"},   
			shrinkToFit:true,
			viewrecords: true, 
			jsonReader: {
	               root:"result" ,                // 数据行（默认为：rows） 
	               page: "pageNo" ,            // 当前页 
	               total: "totalPages" ,    // 总页数 
	               records: "totalCount",     // 总记录数 
	               repeatitems: false,
	               id:"0"
	               }
			  }).navGrid('#lostpager',{edit:false ,add:false ,del:false,search:false,sortable:false  });
		$(window).wresize(function(){
			grid_resize(jqgrid);
		});
		grid_resize(jqgrid);
	})
</script>
</html>