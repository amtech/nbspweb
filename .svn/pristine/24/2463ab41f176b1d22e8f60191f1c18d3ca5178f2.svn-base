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
</head>
<body>
	<form id="searchForm" name="patrolinfo">
		<div class="title_bg">
			<div id="title" class="title">当前位置-待办工作</div>
		</div>
		<div class="tabcontent">
			<table cellspacing="0" cellpadding="0" border="0" align="center">
				<tr>
					<th>计划名称：</th>
					<td><input id="planname" name="planname" class="inputtext"
						type="text" maxlength="60" /></td>
					<th>制定日期：</th>
					<td><input id="createtime" name="createtime" type="text"
						value="" class="Wdate " style="width: 125px"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endtime\')}'})" />
						至 <input id="endtime" name="endtime" type="text"
						class="Wdate inputtext" value="" style="width: 125px"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'createtime\')}'})" />
					</td>
				</tr>
				<tr>
					<th>制定人：</th>
					<td><input id="creatername" name="creatername"
						class="inputtext" type="text" maxlength="60" /></td>
					<th>所属区域：</th>
					<td><input id="regionname" name="regionname" class="inputtext"
						readonly="readonly" /><a
						href="javascript:searchRegion('${ctx}/commonaccess!getregion.action');">
							<img border="0" src="${ctx}/css/image/regionselect.png" /> </a><input
						id="regionid" name="regionid" type="hidden" />
					</td>
				</tr>
				<tr>
					<th>所属代维：</th>
					<td><input id="orgname" name="orgname" class="inputtext"
						readonly="readonly" /><a href="javascript:getorg();"> <img
							border="0" src="${ctx}/css/image/orgselect.png" /> </a><input
						id="orgid" name="orgid" type="hidden" />
					</td>
					<th>所属巡检组：</th>
					<td><input id="patrolgroupname" name="patrolgroupname"
						class="inputtext" readonly="readonly" /><a
						href="javascript:getgroup();"> <img border="0"
							src="${ctx}/css/image/groupselect.png" /> </a><input
						id="patrolgroupid" name="patrolgroupid" type="hidden" />
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center"><input type="button"
						onclick="query();" class="button" value="查询" /> <input
						type="button" onclick="reset();" class="button" value="清除" /> <baseinfo:expexcel
							cls="exprotButton" businessId="patrolinfowaithandledlist"
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
		url: "${ctx}/wplan/patrolinfoWaitHandledAction!list.action",   
		datatype: "json",    
		mtype: 'GET',
		rownumbers: true,
		colNames:['计划名称','专业类型','所属区域', '所属代维','所属巡检组','计划类型','计划制定人','计划制定时间','计划状态','操作','任务ID','专业类型'],
		colModel:[
		          {name:'PLAN_NAME',id:'PLAN_NAME',sortable:false,width:200},
		          {name:'BUSINESS_TYPENAME',id:'BUSINESS_TYPENAME',sortable:false,width:100},
		          {name:'REGIONNAME',id:'REGIONNAME',sortable:false,width:60},
		          {name:'ORGNAME',id:'ORGNAME',sortable:false,width:140},
		          {name:'PATROLGROUPNAME',id:'PATROLGROUPNAME',sortable:false,width:100},
		          {name:'PLAN_TYPE',id:'PLAN_TYPE',sortable:false,formatter:PlanTypeFmatter,width:60},
		          {name:'CREATERNAME',id:'CREATERNAME',sortable:false,width:60},
		          {name:'CREATETIME',id:'CREATETIME',sortable:false,width:120},
		          {name:'PLAN_STATE',id:'PLAN_STATE',sortable:false,formatter:PlanStateFmatter,width:60},
		          {name:'ID',id:'ID',sortable:false,formatter:AuditActionFmatter,width:90},
		          {name:'TASKID',id:'TASKID',hidden:true},
		          {name:'BUSINESS_TYPE',id:'BUSINESS_TYPE',hidden:true}
		          
		          ],      
		rowNum:10,
		height: "100%",
		rowList:[10,30,50,100],    
		pager: '#patrolinfopager',
		shrinkToFit:false,
		autoScroll: true,  
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
		  }).navGrid('#patrolinfopager',{edit:false ,add:false ,del:false,search:false,sortable:false  });
	$(window).wresize(function(){
		grid_resize(jqgrid);
	});
	grid_resize(jqgrid);
})
//查询
function query() {
	jQuery("#patrolinfogrid").jqGrid().setGridParam({
		mtype: 'POST',
		page:1,
        postData: {
        	planname:jQuery("#planname").val(),
        	starttime:jQuery("#createtime").val(),
        	endtime:jQuery("#endtime").val(),
        	creatername:jQuery("#creatername").val(),
        	contractorid:jQuery("#orgid").val(),
        	patrolgroupid:jQuery("#patrolgroupid").val()
        	} 
        }).trigger("reloadGrid");

}
function reset(){
	jQuery('#searchForm').resetForm();
	jQuery('#orgid').val("");
	jQuery('#regionid').val("");
	jQuery('#patrolgroupid').val("");
}
</script>
</html>