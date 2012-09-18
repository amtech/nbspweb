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
In.add('wdatejs',{path:'${ctx}/js/WdatePicker/WdatePicker.js',type:'js',charset:'utf-8'});
In.add('patrolinfo_common', {path : '${ctx}/wplan/plan/js/patrolinfo_common.js',type : 'js',charset : 'utf-8'});

	In.ready('jgcn', 'common', 'wdatejs', 'patrolinfo_common', function() {
		setContextPath('${ctx }');
	var jqgrid=jQuery("#patrolinfogrid").jqGrid({    
			url: "${ctx}/wplan/patrolinfoExecuteAction!queryoverdetail.action?id=${planid}",   
			datatype: "json",    
			mtype: 'GET',
			rownumbers: true,
			colNames:['站点名称','专业类型', '巡检人','到达时间','开始巡检时间','结束巡检时间','巡检时长','异常项数','RFID巡检信息','巡检表'],
			colModel:[
			          {name:'RS_NAME',id:'RS_NAME',sortable:false,width:100},
			          {name:'RESOURCE_TYPENAME',id:'RESOURCE_TYPENAME',sortable:false,width:100},
			          {name:'PATROLMANNAME',id:'PATROLMANNAME',sortable:false,width:70},
			          {name:'ARRIVE_TIME',id:'ARRIVE_TIME',sortable:false,width:120},
			          {name:'START_TIME',id:'START_TIME',sortable:false,width:120},
			          {name:'END_TIME',id:'END_TIME',sortable:false,width:120},
			          {name:'TIMEDIFF',id:'TIMEDIFF',sortable:false,formatter:TimeDiffFmatter,width:100},
			          {name:'EXCEPTIONCOUNT',id:'EXCEPTIONCOUNT',sortable:false,formatter:'integer',width:70},
			          {name:'ID',id:'ID',sortable:false,formatter:RFIDFmatter,width:90},
			          {name:'ID',id:'ID',sortable:false,formatter:PatrolItemFmatter,width:60}
			          ],      
			      	shrinkToFit:false,fixed:true,autoScroll: true,rowNum:10,multiselect: true,
			    	rowList:[10,30,50,100],pager: '#patrolinfopager',viewrecords: true,hidegrid: false, 
			    	prmNames: {page:"pageNo",rows:"rows", sort: "sidx",order: "sord"},   
			    	jsonReader: {root:"result" ,page: "pageNo" ,total: "totalPages" ,records: "totalCount",repeatitems: false,id:"ID"}
			  }).navGrid('#patrolinfopager',{edit:false ,add:false ,del:false,search:false,sortable:false  });
	jQuery(window).wresize(function(){
			grid_resize(jqgrid);
		});
		grid_resize(jqgrid);
	});
	function exportLs(){ 
		s = jQuery("#patrolinfogrid").jqGrid('getGridParam','selarrrow');
		if (s.length==0) {
			alert('请至少选择一条记录！');
			return false;
		}
		window.location.href = "${ctx}/wplan/patrolinfoExecuteAction!patrolitemdetailexport.action?id="+s;
	} 
</script>
</head>
<body>
	<form id="patrolinfoForm" name="patrolinfoForm" method="post">
		<div class="title_bg">
			<div id="title" class="title">当前位置-已巡检计划明细</div>
		</div>
		<div style="text-align: center; margin-top: 10px" align="center">
			<input name="" value="返回" type="button" class="button"
				onclick="history.go(-1);" />
			<input name="" value="批量导出" type="button" class="button" onclick="exportLs();" />	
			<table id="patrolinfogrid"></table>
			<div id="patrolinfopager"></div>
		</div>
	</form>
</body>
</html>