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
		<title>未巡检站点原因登记列表</title>
		<script>
In.add('jqui',{path:'${ctx}/js/jQuery/jqueryui/css/redmond/jquery-ui.custom.css'});
In.add('jquijs',{path:'${ctx}/js/jQuery/jqueryui/jquery-ui.custom.min.js',type:'js',charset:'utf-8',rely:['jqui']});
In.add('jquicn',{path:'${ctx}/js/jQuery/jqueryui/zh/jquery.ui.datepicker-zh-CN.js',type:'js',charset:'utf-8',rely:['jquijs']});
In.add('jresize',{path:'${ctx}/js/jQuery/jresize/jquery.wresize.js',type:'js',charset:'utf-8',rely:['jquicn']});
In.add('jgcss',{path:'${ctx}/js/jQuery/jqgrid/css/ui.jqgrid.css',charset:'utf-8',rely:['jresize']});
In.add('jgjs',{path:'${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js',type:'js',charset:'utf-8',rely:['jgcss']});
In.add('jgcn',{path:'${ctx}/js/jQuery/jqgrid/grid.locale-cn.js',type:'js',charset:'utf-8',rely:['jgjs']});
In.add('common',{path:'${ctx}/common/js/common.js',type:'js',charset:'utf-8'});
In.add('wdatejs',{path:'${ctx}/js/WdatePicker/WdatePicker.js',type:'js',charset:'utf-8'});
	In.ready('jgcn', 'common', 'wdatejs', function() {
		//使用层布局
		var jqgrid=jQuery("#nopatrolstationgrid").jqGrid({    
			url: "${ctx}/wplan/noPatrolStationAction!listData.action?noPatrolStation.ifConfirm=${ifConfirm}",   
			datatype: "json",    
			mtype: 'POST',
			rownumbers: true,
			colNames:['计划名称','站点名称', '问题类型','上报人','上报时间','问题描述','处理状态','操作','专业类型'],
			colModel:[
		          {name:'PLAN_NAME',id:'PLAN_NAME',sortable:false,width:140},
		          {name:'ZYMC',id:'ZYMC',sortable:false,width:140},
		          {name:'PROBLEM_TYPE_DIS',id:'PROBLEM_TYPE_DIS',sortable:false,width:60},
		          {name:'RECORD_USERNAME',id:'RECORD_USERNAME',sortable:false,width:60},
		          {name:'RECORD_DATE_DIS',id:'RECORD_DATE_DIS',sortable:false,width:90},
		          {name:'CAUSE',id:'CAUSE',formatter:causeFormatter,sortable:false,width:150},
		          {name:'PROCESS_STATE',id:'PROCESS_STATE',formatter:stateFormatter,sortable:false,width:50},
		          {name:'ID',id:'ID',sortable:false,formatter:actionFormatter,width:50},
		          {name:'RESOURCE_TYPE',id:'RESOURCE_TYPE',sortable:false,hidden:true}
		          ],      
			rowNum:10,
			rowList:[10,30,50,100],    
			pager: '#nopatrolstationpager',
			shrinkToFit:true,
			viewrecords: true, 
			hidegrid: false, 
			prmNames: {page:"pageNo",rows:"rows", sort: "sidx",order: "sord"},   
			jsonReader: {
               root:"result" ,page: "pageNo" ,total: "totalPages" , records: "totalCount",repeatitems: false,id:"0"
			}
		}).navGrid('#nopatrolstationpager',{edit:false ,add:false ,del:false,search:false,sortable:false  });
		jQuery(window).wresize(function(){
			grid_resize(jqgrid);
		});
		grid_resize(jqgrid);
	});
</script>
</head>
<body>
	<form action="${ctx }/wplan/noPatrolStationAction!list.action?"
		id="queryForm" name="queryForm" method="post">
		<div id="header">
			<div class="title_bg">
				<div class="title">当前位置-拆分纠纷站点登记<c:if test="${ifConfirm=='1' }">待确认</c:if>列表</div>
			</div>
			<div class="tabcontent">
				<table cellspacing="0" cellpadding="0" border="0" align="center">
					<tr>
						<th>代维公司：</th>
						<td>
							<input id="orgname" name="orgname" class="inputtext" style="width:220px;" readonly="readonly" />
							<a href="javascript:getOrg();"><img border="0" src="${ctx}/css/image/orgselect.png" /> </a>
							<input id="orgid" name="orgid" type="hidden" />
						</td>
						<th>问题类型：</th>
						<td>
							<baseinfo:dicselector name="problemtype" columntype="NOPATROLSTATION_PROBLEM_TYPE" type="select" cssClass="inputtext" id="problemtype" style="width:220px;" isQuery="query"></baseinfo:dicselector>
						</td>
					</tr>
					<tr>
						<th>上报时间：</th>
						<td>
							<input id="starttime" name="starttime" type="text" value="" class="Wdate inputtext" style="width: 100px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endtime\')}'})" />
							至<input id="endtime" name="endtime" type="text" class="Wdate inputtext" value="" style="width: 100px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'starttime\')}'})" />
						</td>
						<th>上报人：</th>
						<td>
							<input id="creatername" name="creatername" class="inputtext" style="width:220px;" type="text" maxlength="60" />
						</td>
					</tr>
					<tr>
						<th>计划名称：</th>
						<td>
							<input id="planname" name="planname" class="inputtext" style="width:220px;" type="text" maxlength="60" />
						</td>
						<th>站点名称：</th>
						<td>
							<input id="stationname" name="stationname" class="inputtext" style="width:220px;" type="text" maxlength="60" />
						</td>
					</tr>
					<tr>
						<th>专业：</th>
						<td>
							<baseinfo:customselector name="businesstype" data="${businessTypeMap }" cssClass="inputtext" isQuery="query" id="businesstype" isReversal="true" style="width:220px;"></baseinfo:customselector>
						</td>
						<th>处理状态：</th>
						<td>
							<select id="state" name="state" class="inputtext" style="width:220px;">
								<option value="">不限</option>
								<option value="0">未处理</option>
								<c:if test="${ifConfirm!='1' }">
								<option value="1">已处理</option>
								</c:if>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<input type="button" onclick="query();" class="button" value="查询" /> 
							<input type="button" onclick="reset();" class="button" value="重置" />
						</td>
					</tr>
				</table>
			</div>
		</div>
		<br>
		<div id="content" align="center" style="padding-top: 2px">
			<table id="nopatrolstationgrid"></table>
			<div id="nopatrolstationpager"></div>
		</div>
	</form>
</body>
<script type="text/javascript">
	function getOrg(){
		var val = showOrg('${ctx}/commonaccess!getorg.action?orgtype=2');
		var orgId = "";
		var orgName = "";
		if (val) {
			orgId = val[0];
			orgName = val[1];
			jQuery("#orgid").val(orgId);
			jQuery("#orgname").val(orgName);
		}
	}
	//查询
	function query() {
		jQuery("#nopatrolstationgrid").jqGrid().setGridParam({
            postData: {
            	'noPatrolStation.planName':jQuery("#planname").val(),
            	'noPatrolStation.stationName':jQuery("#stationname").val(),
            	'noPatrolStation.startDate':jQuery("#starttime").val(),
            	'noPatrolStation.endDate':jQuery("#endtime").val(),
            	'noPatrolStation.contractorId':jQuery("#orgid").val(),
            	'noPatrolStation.resourceType':jQuery("#businesstype").val(),
            	'noPatrolStation.problemType':jQuery("#problemtype").val(),
            	'noPatrolStation.recorder':jQuery("#creatername").val(),
            	'noPatrolStation.processState':jQuery("#state").val()
            	} 
            }).trigger("reloadGrid");
	}
	function view(id){
		location.href="${ctx}/wplan/noPatrolStationAction!view.action?id="+id;
	}
	function confirm(id){
		location.href="${ctx}/wplan/noPatrolStationAction!confirmInput.action?id="+id;
	}
/**
 * 状态值转换
 * 
 * @param cellValue
 * @param options
 * @param rowObjec
 * @returns {String}
 */
function stateFormatter(cellValue, options, rowObjec) {
	var view = "";
	var stateValue = [ "未处理", "已处理" ];
	view = stateValue[parseInt(cellValue)];
	return view;
}
/**
 * 问题描述操作列转换
 * 
 * @param cellValue
 * @param options
 * @param rowObjec
 * @returns {String}
 */
function causeFormatter(cellValue, options, rowObjec) {
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:view('"
			+ rowObjec.ID + "')>"+cellValue+"</a>";
	return view;
}
/**
 * 确认操作列转换
 * 
 * @param cellValue
 * @param options
 * @param rowObjec
 * @returns {String}
 */
function actionFormatter(cellValue, options, rowObjec) {
	var view = "";
	if(rowObjec.PROCESS_STATE=='0'&&'${ifConfirm}'=='1'){
		view+="<a style='color: blue;text-decoration: underline;' href=javascript:confirm('"
			+ rowObjec.ID + "')><img src='"+contextPath+"/css/image/confirm.png' title='确认' /></a>";
	}
	return view;
}
</script>
</html>