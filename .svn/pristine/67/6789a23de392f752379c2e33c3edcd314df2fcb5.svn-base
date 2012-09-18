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
	In.add('wdatejs',{path:'${ctx}/js/WdatePicker/WdatePicker.js',type:'js',charset:'utf-8'});
	In.add('common', {path : '${ctx}/common/js/common.js',type : 'js',charset : 'utf-8'});
	In.ready('wdatejs', 'common', function() {
		setContextPath("${ctx}");
		//合并行
		var rowCount = 1;
	    for(var i = 0; i <= rowCount; i++)
	    {
	     table_rowspan("#KH",i);
	    }
	});
	// 搜索区域
	function searchRegion(url) {
		var val = showRegion(url);
		if (!!val) {
			jQuery("#regionid").val(val[0]);
			jQuery("#regionname").val(val[1]);
		}
	}
</script>
</head>
<body>
	<form
		action="${ctx }/assess/assessMonthSummaryAction!getmonthrank.action"
		id="assesstemplateForm" method="post">
		<div class="title_bg">
			<div id="title" class="title">当前位置-月度考核排名</div>
		</div>
		<div class="tabcontent">
			<table cellspacing="0" cellpadding="0" border="0" align="center">
				<tr>
					<th>区域:</th>
					<td><input id="regionname" name="regionname" class="inputtext"
						value="${regionname}" readonly="readonly" /><a
						href="javascript:searchRegion('${ctx}/commonaccess!getregion.action');">
							<img border="0" src="${ctx}/css/images/selectcode.gif" /> </a><input
						id="regionid" name="regionid" type="hidden" value="${regionid}" />
					</td>
					<th>考核月份：</th>
					<td><input id="month" name="month" type="text"
						value="${month}" "
						class="Wdate inputtext"
						onfocus="WdatePicker({dateFmt:'yyyy-MM'})" /></td>
				</tr>
				<tr>
					<th>专业类型:</th>
					<td><baseinfo:customselector id="businesstype"
							name="businesstype" data="${businessTypeMap }" cssClass="inputtext" isReversal="true" keyValue="${businesstype}"></baseinfo:customselector>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center"><input type="submit"
						class="button" value="查询" />
						<baseinfo:expexcel cls="exprotButton"  businessId="assess_monthrank_list" name="导出excel"></baseinfo:expexcel>
						</td>
				</tr>
			</table>
		</div>
		<br>
		<div class="tabout">
		<table  id="KH">
			<thead>
				<tr>
					<th style="width:120px;" nowrap="nowrap">区域</th>
					<th style="width:120px;" nowrap="nowrap">排名</th>
					<th nowrap="nowrap">代维公司</th>
					<th style="width:120px;" nowrap="nowrap">月度考核成绩</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${scorecontent}">
					<tr>
						<td>${item.REGIONNAME}</td>
						<td>${item.PM}</td>
						<td>${item.ORGNAME}</td>
						<td>${item.SCORE}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
	</form>
</body>
</html>
