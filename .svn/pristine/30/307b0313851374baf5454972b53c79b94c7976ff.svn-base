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
<title>巡检表</title>
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
In.add('fault-common', {path : '${ctx}/wplan/plan/js/patrolinfo_common.js',type : 'js',charset : 'utf-8'});
In.ready('jgcn', 'common', 'wdatejs', 'fault-common', function() {
	var jqgrid=jQuery("#itemgrid").jqGrid({    
			url: "${ctx}/wplan/patrolinfoExecuteAction!itemdetaillist.action?id=${patrolinfoMap.ID}",   
			datatype: "json",    
			mtype: 'POST',
			colNames:[ '巡检对象','巡检项目','巡检结果','巡检描述','是否异常'],
			colModel:[
			          {name:'ITEM_NAME',id:'ITEM_NAME',sortable:false},
			          {name:'SUBITEM_NAME',id:'SUBITEM_NAME',sortable:false},
			          {name:'SUBITEM_PATROL',id:'SUBITEM_PATROL',sortable:false},
			          {name:'EXCEPTION_DESC',id:'EXCEPTION_DESC',sortable:false},
			          {name:'CHECKED',id:'CHECKED',sortable:false,formatter:ItemExceptionFmatter}
			          ],      
			rowNum:10,
			autowidth:true,
			shrinkToFit:true,
			viewrecords: true,
			rowList:[10,30,50,100],    
			pager: '#itempager',
			rownumbers: true,
			prmNames: {page:"pageNo",rows:"rows", sort: "sidx",order: "sord"}, 
			jsonReader: {
	               root:"result" ,                // 数据行（默认为：rows） 
	               page: "pageNo" ,            // 当前页 
	               total: "totalPages" ,    // 总页数 
	               records: "totalCount",     // 总记录数 
	               repeatitems: false,
	               id:"ROW_ID"
	               }   
			  }).navGrid('#itempager',{edit:false ,add:false ,del:false,search:false,sortable:false });
	
    });
</script>
</head>
<body>
	<form id="patrolinfoForm" name="patrolinfoForm" method="post">
		<div class="title_bg">
			<div id="title" class="title">当前位置-巡检表</div>
		</div>
		<div class="tabcontent">
			<table border="0" align="center">
				<tr>
					<th>资源名称：</th>
					<td>${patrolinfoMap.RESOURCE_NAME }</td>
					<th>专业类型：</th>
					<td>${patrolinfoMap.RESOURCE_TYPENAME }</td>
				</tr>
				<tr>
					<th>代维公司：</th>
					<td>${patrolinfoMap.ORGNAME }</td>
					<th>巡检组：</th>
					<td>${patrolinfoMap.PATROLGROUPNAME }</td>
				</tr>
				<tr>
					<th>巡检人员：</th>
					<td>${patrolinfoMap.PATROLMANNAME }</td>
					<th>联系电话：</th>
					<td>${patrolinfoMap.PHONE }</td>
				</tr>
				<tr>
					<th>巡检时间：</th>
					<td><fmt:formatDate value="${patrolinfoMap.ARRIVE_TIME }"
							pattern="yyyy-MM-dd HH:mm:ss" />
					</td>
					<th>异常项数：</th>
					<td>${exceptionMap.EXCEPTIONCOUNT }</td>
				</tr>
				<tr>
					<td colspan="4">
						<div id="patrolinfo">
							<table id="itemgrid"></table>
							<div id="itempager"></div>
						</div></td>
				</tr>
			</table>
		</div>
		<div style="text-align: center; margin-top: 10px">
			<input name="" value="返回" type="button" class="button"
				onclick="history.go(-1);" />
				
		    <a class='exprotButton' href='${ctx}/wplan/patrolinfoExecuteAction!patrolitemdetailexport.action?id=${patrolinfoMap.ID }'>导出excel</a>

				
		</div>
	</form>
</body>

</html>