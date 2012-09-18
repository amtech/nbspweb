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
<title>现场检查管理</title>
<script type="text/javascript">
In.add('jqui',{path:'${ctx}/js/jQuery/jqueryui/css/redmond/jquery-ui.custom.css'});
In.add('ztreeui',{path:'${ctx }/js/jQuery/ztree/css/zTreeStyle/zTreeStyle.css'});
In.add('jquijs',{path:'${ctx}/js/jQuery/jqueryui/jquery-ui.custom.min.js',type:'js',charset:'utf-8',rely:['jqui']});
In.add('jquicn',{path:'${ctx}/js/jQuery/jqueryui/zh/jquery.ui.datepicker-zh-CN.js',type:'js',charset:'utf-8',rely:['jquijs']});
In.add('jresize',{path:'${ctx}/js/jQuery/jresize/jquery.wresize.js',type:'js',charset:'utf-8',rely:['jquicn']});
In.add('jgcss',{path:'${ctx}/js/jQuery/jqgrid/css/ui.jqgrid.css',charset:'utf-8',rely:['jresize']});
In.add('jgjs',{path:'${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js',type:'js',charset:'utf-8',rely:['jgcss']});
In.add('ztreejs',{path:'${ctx}/js/jQuery/ztree/jquery.ztree.all.min.js',type:'js',charset:'utf-8'});
In.add('jgcn',{path:'${ctx}/js/jQuery/jqgrid/grid.locale-cn.js',type:'js',charset:'utf-8',rely:['jgjs']});
In.add('common',{path:'${ctx}/common/js/common.js',type:'js',charset:'utf-8'});
In.add('wdatejs',{path:'${ctx}/js/WdatePicker/WdatePicker.js',type:'js',charset:'utf-8'});
In.ready('jgcn', 'common', 'wdatejs','ztreejs','ztreeui', function() {
	//合并行
	var rowCount = 2;
    for(var i = 0; i <= rowCount; i++)
    {
     table_rowspan("#MonthKH",i);
    }
});
//查询
function query() {
	window.location.href = "${ctx}/assess/assessExaminationSummaryAction!list.action?regionId="+jQuery("#query_region_id").val()+
							"&regionName="+jQuery("#regionname").val()+"&startTime="+jQuery("#createtime").val()+"&endTime="+jQuery("#endtime").val();
}
// 搜索区域
function searchRegion(url) {
	var val = showRegion(url);
	if (!!val) {
		jQuery("#query_region_id").val(val[0]);
		jQuery("#regionname").val(val[1]);
	}
}
</script>
</head>
<body>
	<form id="optForm" method="post"
		action="${ctx }/wplan/patrolinfoScheduleAction!statisticsList.action" target="myframe">
		<input name="parameter.isQuery" id='isQuery' value="1" type="hidden" />
	
			<div class="title_bg">
				<div id="title" class="title">当前位置-考核管理-现场检查汇总</div>
			</div>
			<div class="tabcontent">
				<table cellspacing="0" cellpadding="0" border="0" align="center">
					<tr>
						<th>
							检查区域:
						</th>
						<td colspan="3">&nbsp;&nbsp;
							<input id="regionname" name="regionname" class="treetext"
							readonly="readonly" value="${parameters['regionName']}"/><a
							href="javascript:searchRegion('${ctx}/commonaccess!getregion.action');">
							<img border="0"
								src="${ctx}/css/images/selectcode.gif" />
							</a><input id="query_region_id" name="regionid" type="hidden"  value="${parameters['regionId']}"/>
						</td>
					</tr>
					<tr>
						<th>
							开始时间:
						</th>
						<td> 
							&nbsp;&nbsp;&nbsp;<input id="createtime" name="startTime" type="text"
							value="${parameters['startTime']}" class="Wdate inputtext" style="width: 220px;"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endtime\')}'})" />
						</td>
						<th>
							结束时间:
						</th>
						<td>
							&nbsp;&nbsp;&nbsp;<input id="endtime" name="endTime"
							type="text" class="Wdate inputtext" value="${parameters['endTime']}" style="width: 220px;"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'createtime\')}'})" />
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<input type="button" class="button" value="查询" onclick="query();">
							<input type="button" class="button" value="打印" onclick="window.print();">
							<baseinfo:expexcel cls="exprotButton"  businessId="assess_examination_summary" name="导出excel"></baseinfo:expexcel>
						</td>
					</tr>  
				</table>
			</div>
		<br>
	<div class="tabout">
				<table id="MonthKH" border="0" align="center" cellpadding="3" cellspacing="0">
						<thead>
							<tr >
								<th style="width:20%;"><strong>地市</strong></th>
								<th style="width:20%;"><strong>代维公司</strong></th>
								<th style="width:20%;"><strong>县区</strong></th>
								<th style="width:20%;"><strong>站点数</strong></th>
								<th style="width:20%;"><strong>扣分</strong></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${list}">
								<tr>
									<td style="width:20%;">
									${item['CITY']}
									</td>
									<td style="width:20%;">
									${item['CONTRACTOR']}
									</td>
									<td style="width:20%;">
									${item['COUNTY']}
									</td>
									<td style="width:20%;">${item['NUB']}</td>
									<td style="width:20%;">${item['SCORE']}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
		</div>
	</form>
</body>
</html>