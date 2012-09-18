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
</script>
<title>计划执行进度</title>
</head>
<body>
	<form id="patrolinfoForm" name="patrolinfoForm" method="post">
		<div class="title_bg">
			<div id="title" class="title">当前位置-计划执行进度</div>
		</div>
		<div class="tabcontent">
			<table cellspacing="0" cellpadding="0" border="0" align="center">
				<tr>
					<th>计划名称：</th>
					<td><input id="planname" name="planname" class="inputtext"
						type="text" maxlength="60" />
					</td>
					<th>执行日期：</th>
					<td><input id="createtime" name="starttime" type="text"
						value="" class="Wdate " style="width: 125px"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endtime\')}'})" />
						至 <input id="endtime" name="endtime" type="text"
						class="Wdate inputtext" value="" style="width: 125px"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'createtime\')}'})" />
					</td>
				</tr>
				<tr>
					<th>所属代维：</th>
					<td><input id="orgname" name="orgname" class="inputtext"
						readonly="readonly" /><a href="javascript:getorg();"> <img
							border="0" src="${ctx}/css/image/orgselect.png" /> </a><input
						id="orgid" name="orgid" type="hidden" /></td>
					<th>所属巡检组：</th>
					<td><input id="patrolgroupname" name="patrolgroupname"
						class="inputtext" readonly="readonly" /><a
						href="javascript:getgroup();"> <img border="0"
							src="${ctx}/css/image/groupselect.png" /> </a><input
						id="patrolgroupid" name="patrolgroupid" type="hidden" /></td>
				</tr>
				<tr>
					<th>所属区域：</th>
					<td><input id="regionname" name="regionname" class="inputtext"
						readonly="readonly" /><a
						href="javascript:searchRegion('${ctx}/commonaccess!getregion.action');">
							<img border="0" src="${ctx}/css/image/regionselect.png" /> </a><input
						id="regionid" name="regionid" type="hidden" /></td>
					<th>专业类型：</th>
					<td><baseinfo:customselector id="businesstype"
							name="businesstype" data="${businessTypeMap }"
							cssClass="inputtext" isReversal="true" isQuery="query" ></baseinfo:customselector>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center"><input type="button"
						onclick="query();" class="button" value="查询" /> <input
						type="button" onclick="reset();" class="button" value="清除" /> <baseinfo:expexcel
							cls="exprotButton" businessId="patrolinfoexecuteschedulelist"
							name="导出excel"></baseinfo:expexcel></td>
				</tr>
			</table>
		</div>
		<br>
		<table id="patrolinfogrid"></table>
		<div id="patrolinfopager"></div>
	</form>
</body>
<script type="text/javascript">
In.ready('jgcn', 'common', 'wdatejs', 'patrolinfo_common', function() {
setContextPath('${ctx }');
var jqgrid=jQuery("#patrolinfogrid").jqGrid({    
	url: "${ctx}/wplan/patrolinfoExecuteAction!queryshowschedule.action?type="+jQuery('select[name="businesstype"]').val(),   
	datatype: "json",    
	mtype: 'GET',
	rownumbers: true,
	colNames:['计划ID','计划名称','专业类型','所属区域', '所属代维','所属巡检组','计划开始时间','计划结束时间','计划巡检数','异常巡检数','已巡检数','未巡检数','巡检完成率'],
	colModel:[
			  {name:'ID',id:'ID',hidden: true},
	          {name:'PLAN_NAME',id:'PLAN_NAME',sortable:false,formatter:PlanNameFmatter,width:200},
	          {name:'BUSINESS_TYPENAME',id:'BUSINESS_TYPENAME',sortable:false,width:100},
	          {name:'REGIONNAME',id:'REGIONNAME',sortable:false,width:60},
	          {name:'ORGNAME',id:'ORGNAME',sortable:false,width:140},
	          {name:'PATROLGROUPNAME',id:'PATROLGROUPNAME',sortable:false,width:100},
	          {name:'START_TIME',id:'START_TIME',sortable:false,formatter:'date',formatoptions: {newformat:'Y-m-d'},width:90 },
	          {name:'END_TIME',id:'END_TIME',sortable:false,formatter:'date',formatoptions: {newformat:'Y-m-d'},width:90,summaryType:'sum',summaryTpl:'<b>合计：</b>' },
	          {name:'PATROLCOUNT',id:'PATROLCOUNT',width:80,sortable:false,sorttype:'int', summaryType:'sum',summaryTpl:'<b>{0}</b>'},
	          {name:'EXCEPTIONCOUNT',id:'EXCEPTIONCOUNT',width:80,sortable:false,formatter:ExceptionPatrolFmatter,sorttype:'int', summaryType:'sum',summaryTpl:'<b>{0}</b>'},
	          {name:'ENDPATROLCOUNT',id:'ENDPATROLCOUNT',width:80,sortable:false,formatter:OverPatrolFmatter,sorttype:'int', summaryType:'sum',summaryTpl:'<b>{0}</b>'},
	          {name:'NOPATROLCOUNT',id:'NOPATROLCOUNT',width:80,sortable:false,formatter:LosePatrolFmatter,sorttype:'int', summaryType:'sum',summaryTpl:'<b>{0}</b>'},
	          {name:'PLANRATE',id:'PLANRATE',sortable:false,width:80}
	          ], 
	grouping: true,
	groupingView : {
	         		groupField : ['REGIONNAME'],
	         		groupColumnShow : [true],
	         		groupText : ['<b>{0} - {1} 条</b>'],
	         		groupCollapse : false,
	      		groupOrder: ['asc'],
	      		groupSummary : [true],
	      		groupDataSorted : true
	         	},          
	rowNum:10,
	shrinkToFit:false,  
	autoScroll: true,
	height: "auto",
	rowList:[10,30,50,100],    
	pager: '#patrolinfopager',
	viewrecords: true, 
	hidegrid: false, 
	prmNames: {page:"pageNo",rows:"rows", sort: "sidx",order: "sord"},   
	jsonReader: {
           root:"result" ,                // 数据行（默认为：rows） 
           page: "pageNo" ,            // 当前页 
           total: "totalPages" ,    // 总页数 
           records: "totalCount",     // 总记录数 
           repeatitems: false,
           id:"ID"
           }
	  }).navGrid('#patrolinfopager',{edit:false ,add:false ,del:false,search:false,sortable:false  });
jQuery(window).wresize(function(){
	grid_resize(jqgrid);
});
grid_resize(jqgrid);
});
    
		//查询
function query() {
	jQuery("#patrolinfogrid").jqGrid().setGridParam({
		url: "${ctx}/wplan/patrolinfoExecuteAction!queryshowschedule.action?type="+jQuery('select[name="businesstype"]').val(),
		mtype: 'POST',
		page:1,
		postData: {
        	planname:jQuery("#planname").val(),
        	starttime:jQuery("#createtime").val(),
        	endtime:jQuery("#endtime").val(),
        	contractorid:jQuery("#orgid").val(),
        	patrolgroupid:jQuery("#patrolgroupid").val(),
        	regionid:jQuery("#regionid").val()
        	} 
        }).trigger("reloadGrid");

}
function reset(){
	jQuery('#patrolinfoForm').resetForm();
	jQuery('#orgid').val("");
	jQuery('#regionid').val("");
	jQuery('#patrolgroupid').val("");
}
</script>
</html>