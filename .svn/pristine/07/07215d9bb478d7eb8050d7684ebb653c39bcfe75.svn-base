<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/meta.jsp"%>
<link href="${ctx}/css/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<link href="${config.cdnurl }/cabletech/ui/css/jquery-ct-ui-custom.css" rel="stylesheet" type="text/css" />
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
	setUserType('${LOGIN_USER.orgType}');
	setContextPath('${ctx }');
//使用层布局
var jqgrid=jQuery("#patrolinfogrid").jqGrid({    
	url: "${ctx}/wplan/patrolinfoAction!query.action?type="+jQuery('select[name="businesstype"]').val(),   
	datatype: "json",    
	mtype: 'GET',
	rownumbers: true,
	colNames:['计划名称','所属区域', '所属代维','所属巡检组','专业类型','计划类型','制定人','审核人','制定时间','计划状态','操作','专业类型'],
	colModel:[
	          {name:'PLAN_NAME',id:'PLAN_NAME',sortable:false,width:200},
	          {name:'REGIONNAME',id:'REGIONNAME',sortable:false,width:60},
	          {name:'ORGNAME',id:'ORGNAME',sortable:false,width:140},
	          {name:'PATROLGROUPNAME',id:'PATROLGROUPNAME',sortable:false,width:100},
	          {name:'BUSINESS_TYPENAME',id:'BUSINESS_TYPENAME',sortable:false,width:100},
	          {name:'PLAN_TYPE',id:'PLAN_TYPE',sortable:false,formatter:PlanTypeFmatter,width:60},
	          {name:'CREATERNAME',id:'CREATERNAME',sortable:false,width:60},
	          {name:'APPROVERNAME',id:'APPROVERNAME',sortable:false,width:60},
	          {name:'CREATETIME',id:'CREATETIME',sortable:false,width:120},
	          {name:'PLAN_STATE',id:'PLAN_STATE',sortable:false,formatter:PlanStateFmatter,width:60},
	          {name:'ID',id:'ID',sortable:false,formatter:ActionFmatter,width:150},
	          {name:'BUSINESS_TYPE',id:'BUSINESS_TYPE',sortable:false,hidden:true}
	          ],
	shrinkToFit:false,fixed:true,autoScroll: true,rowNum:10,
	rowList:[10,30,50,100],pager: '#patrolinfopager',viewrecords: true,hidegrid: false, 
	prmNames: {page:"pageNo",rows:"rows", sort: "sidx",order: "sord"},   
	jsonReader: {root:"result" ,page: "pageNo" ,total: "totalPages" ,records: "totalCount",repeatitems: false,id:"ID"}
	  }).navGrid('#patrolinfopager',{edit:false ,add:false ,del:false,search:false,sortable:false  });
	jQuery(window).wresize(function(){
	grid_resize(jqgrid);
});
grid_resize(jqgrid);
});
	//查询
function query() {
	var jgrid=jQuery("#patrolinfogrid").jqGrid().setGridParam({
		url: "${ctx}/wplan/patrolinfoAction!query.action?type="+jQuery('select[name="businesstype"]').val(),  
		mtype: 'POST',
		page:1,
		postData: {
        	planname:jQuery("#planname").val(),
        	starttime:jQuery("#createtime").val(),
        	endtime:jQuery("#endtime").val(),
        	creatername:jQuery("#creatername").val(),
        	contractorid:jQuery("#orgid").val(),
        	patrolgroupid:jQuery("#patrolgroupid").val(),
        	regionid:jQuery("#regionid").val()
        	} 
        }).trigger("reloadGrid");
	grid_resize(jgrid);

}
function reset(){
	jQuery('#patrolinfoForm').resetForm();
	jQuery('#orgid').val("");
	jQuery('#regionid').val("");
	jQuery('#patrolgroupid').val("");
}
</script>
<title>巡检计划列表</title>
</head>
<body>
	<form action="${ctx }/wplan/patrolinfoAction!list.action"
		id="patrolinfoForm" name="patrolinfoForm" method="post">
		<div id="header">
			<div class="title_bg">
				<div class="title">当前位置-巡检计划列表</div>
			</div>
			<div class="tabcontent">
				<table cellspacing="0" cellpadding="0" border="0" >
					<tr>
						<th>计划名称：</th>
						<td><input id="planname" name="planname" class="inputtext"
							type="text" maxlength="60" /></td>
						<th>开始时间：</th>
						<td><input id="createtime" name="starttime" type="text"
							class="Wdate inputtext"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endtime\')}'})" />
							至 <input id="endtime" name="endtime" type="text"
							class="Wdate inputtext"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'createtime\')}'})" />
						</td>
					</tr>
					<tr>
						<th>制定人：</th>
						<td><input id="creatername" name="creatername" class="inputtext" type="text" maxlength="60" />
						</td>
						<th>所属区域：</th>
						<td><input id="regionname" name="regionname"
							class="inputtext" readonly="readonly" /><a
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
						<th>专业类型：</th>
						<td ><baseinfo:customselector id="businesstype"
								name="businesstype" data="${businessTypeMap }" cssClass="inputtext" isQuery="query"
								isReversal="true"></baseinfo:customselector>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center"><input type="button"
							onclick="query();" class="button" value="查询" /> <input
							type="button" onclick="reset();" class="button" value="清除" /> <baseinfo:expexcel
								cls="exprotButton" businessId="patrolinfolist" name="导出excel"></baseinfo:expexcel>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<br>
		<div id="content" align="center" style="padding-top: 2px">
			<table id="patrolinfogrid"></table>
			<div id="patrolinfopager"></div>
		</div>
	</form>
</body>
</html>