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
	In.ready('jgcn', 'common', 'wdatejs', function() {
		setContextPath('${ctx}');
	});
</script>
</head>
	<body>
		<form id="searchForm" name="searchForm">
			<div>
				<div class="title_bg">
					<div class="title">
						当前位置-月度考核-生成成绩
					</div>
				</div>
				<div class="tabcontent">
					<table cellspacing="0" cellpadding="0" border="0" align="center">
						<tr>
							<th>
								类型：
							</th>
							<td>
								<input id="querytype_0" name="querytype" type="radio" value="0" checked/>专业月度代维考核成绩
								<input id="querytype_1" name="querytype" type="radio" value="1" />月度综合考核成绩
							</td>
						</tr>
						<tr>
							<th>
								月份：
							</th>
							<td>
								<input id="yearmonth" name="yearmonth" type="text" class="Wdate inputtext" onfocus="WdatePicker({dateFmt:'yyyy-MM',maxDate:'%y-%M'})" style="width: 220px" />
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="button" onclick="queryData();" class="button" value="生成" />
								<input name="button" type="button" class="button" onclick="exportXls();" value="导出Excel">
							</td>
						</tr>
					</table>
				</div>
			</div>
			<br>
			<div id="content" style="text-align: center;" class="tabcontent">
			</div>
		</form>
	</body>
	<script type="text/javascript">
	function exportXls(){
		var yearmonth=jQuery("#yearmonth").val();
		var querytype=jQuery("input[name='querytype']:checked").val();
		location="${ctx}/assess/assessMonthScoreQueryAction!export.action?yearmonth="+yearmonth+"&querytype="+querytype;
	}
	/**
	 * 列表查询方法
	 */
	function queryData() {
		var yearmonth=jQuery("#yearmonth").val();
		var querytype=jQuery("input[name='querytype']:checked").val();
		if(yearmonth==""){
			alert("没有选择月份！");
			return;
		}
		yearmonth+="-01";
		var actionUrl="${ctx}/assess/assessMonthScoreQueryAction!list.action?yearmonth="+yearmonth+"&querytype="+querytype;
		jQuery("#content").load(actionUrl);
	}
	</script>
</html>
