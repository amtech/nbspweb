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
In.add('common',{path:'${ctx}/common/js/common.js',type:'js',charset:'utf-8'});
In.add('wdatejs',{path:'${ctx}/js/WdatePicker/WdatePicker.js',type:'js',charset:'utf-8'});
	In.ready('common', 'wdatejs', function() {
		setContextPath('${ctx}');
	});
</script>
</head>
	<body>
		<form id="searchForm" name="searchForm">
			<div>
				<div class="title_bg">
					<div class="title">
						当前位置-考核管理-汇总统计-年度排名
					</div>
				</div>
				<div class="tabcontent">
					<table cellspacing="0" cellpadding="0" border="0" align="center">
						<tr>
							<th>
								年份：
							</th>
							<td>
								<input id="yearmonth" name="yearmonth" type="text" class="Wdate inputtext" onfocus="WdatePicker({dateFmt:'yyyy',maxDate:'%y'})" style="width: 220px" />
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="button" onclick="queryData();" class="button" value="查询" />
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
		if(yearmonth==""){
			alert("没有选择年份！");
			return;
		}
		location="${ctx}/assess/assessYearSummaryAction!rankExport.action?yearmonth="+yearmonth;
	}
	/**
	 * 列表查询方法
	 */
	function queryData() {
		var yearmonth=jQuery("#yearmonth").val();
		if(yearmonth==""){
			alert("没有选择年份！");
			return;
		}
		yearmonth+="-01-01";
		var actionUrl="${ctx}/assess/assessYearSummaryAction!rankList.action?yearmonth="+yearmonth;
		jQuery("#content").load(actionUrl);
	}
	</script>
</html>
