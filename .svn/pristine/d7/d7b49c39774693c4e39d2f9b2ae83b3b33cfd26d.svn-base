<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link href="${ctx}/css/base.css" rel="stylesheet" type="text/css" />
		<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx }/js/in-min.js"
			autoload="true"
			core="${config.cdnurl}/cabletech/ui/js/jquery-1.7.2.min.js"></script>
<title>巡检项信息查询</title>
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
In.add('fault-common', {path : '${ctx }/wplan/patrolitem/js/list_patrol_item.js',type : 'js',charset : 'utf-8'});
In.ready('jgcn', 'common', 'wdatejs', 'fault-common', function() {
		setUserType('${LOGIN_USER.orgType}');
		setContextPath("${ctx}");
		//使用层布局
		var jqgrid = jQuery("#itemgrid")
				.jqGrid(
						{
							url : "${ctx}/wplan/patrolItemAction!listdata.action?",
							datatype : "json",
							mtype : 'GET',
							rownumbers : true,
							colNames : [ '维护对象', '维护检测项目', '周期', '质量标准',
									'输入类型', '值域范围', '异常状态', '默认值','操作' ],
							colModel : [
								{name : 'ITEMNAME',id : 'ITEMNAME',sortable : false,width:200}, 
								{name : 'SUBITEM_NAME',id : 'SUBITEM_NAME',sortable : false,width:200}, 
								{name : 'CYCLE',id : 'CYCLE',sortable : false,formatter:CycleFmatter,width:50}, 
								{name : 'QUALITY_STANDARD',id : 'QUALITY_STANDARD',sortable : false,width:150}, 
								{name : 'INPUT_TYPE',id : 'INPUT_TYPE',sortable : false,formatter:InputFmatter,width:60}, 
								{name : 'VALUE_SCOPE',id : 'VALUE_SCOPE',sortable : false,width:100}, 
								{name : 'EXCEPTION_VALUE',id : 'EXCEPTION_VALUE',sortable : false,width:70}, 
								{name : 'DEFAULT_VALUE',id : 'DEFAULT_VALUE',sortable : false,width:70}, 
								{name : 'ID',id : 'ID',sortable : false,formatter:ItemActionFmatter,width:50} 
							],
							shrinkToFit:false,fixed:true,autoScroll: true,viewrecords : true,hidegrid : false,
							pager : '#itempager',rowNum:15,
							rowList:[15,20,30],multiselect: true,
							prmNames : {page : "pageNo",rows : "rows",sort : "sidx",order : "sord"},
							jsonReader : {root : "result",page : "pageNo",total : "totalPages",records : "totalCount",repeatitems : false,id : "ID"}
						}).navGrid('#itempager', {
					edit : false,
					add : false,
					del : false,
					search : false,
					sortable : false
				});
		jQuery(window).wresize(function(){
			grid_resize(jqgrid);
		});
		grid_resize(jqgrid);
	});
	//查询
	function search() {
		var showAll="";
		if(jQuery("input[name=parameter.showAll]:checked").val()=="on"){
			showAll="1";
		}
		jQuery("#itemgrid").jqGrid().setGridParam({
            postData: {
            	'parameter.showAll':showAll,
            	'parameter.isQuery':jQuery("#isQuery").val(),
            	'parameter.businessType':jQuery("#businessType").val()
            	} 
            }).trigger("reloadGrid");
	}
</script>
</head>
<body>
	<form id="optForm" method="post"
		action="${ctx }/wplan/patrolItemAction!list.action">
		<input name="parameter.isQuery" id='isQuery' value="1" type="hidden" />
		<div>
			<div class="title_bg">
				<div id="title" class="title">当前位置-巡检项信息查询</div>
			</div>
			<div class="tabcontent">
				<table cellspacing="0" cellpadding="0" border="0" align="center">
					<tr>
						<th>专业：</th>
						<td> 
						<baseinfo:customselector name="parameter.businessType" id="businessType" isQuery="query" data="${businessTypeMap}" isReversal="true" cssClass="inputtext"></baseinfo:customselector>
						</td>
						<th><c:if test="${parameter.isProvince=='true'}">区域：</c:if></th>
						<td><c:if test="${parameter.isProvince=='true'}"></c:if></td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<input type="hidden" id="showAll" name="parameter.showAll" value="${parameter.showAll}"> 显示所有数据（包括作废数据） 
							<input onclick="toggleShowAll(this);" type="checkbox"> 
							<input type="button" class="button" value="查询" onclick="search();">
						</td>
					</tr>
				</table>
			</div>
		</div>
		<br>
		<div align="left">
			<a href="javascript:void(0);"
				onclick="deleteLogicSum('${parameter.businessType}')">批量作废</a>&nbsp;
			<a href="javascript:void(0);"
				onclick="startUsingSum('${parameter.businessType}')">批量启用</a>&nbsp;
			<baseinfo:expexcel  businessId="listpatrolitems" name="导出excel"></baseinfo:expexcel>
		</div>
		<div id="content" align="center" style="padding-top: 2px">
			<table id="itemgrid"></table>
			<div id="itempager"></div>
		</div>
	</form>
</body>
</html>