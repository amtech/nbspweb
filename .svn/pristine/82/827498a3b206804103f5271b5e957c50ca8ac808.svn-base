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
In.add('vm', {path : '${ctx}/js/jQuery/jvalidation/jquery.validate.min.js',type : 'js',charset : 'utf-8'});
In.add('vcn', {path : '${ctx}/js/jQuery/jvalidation/messages_cn.js',type : 'js',charset : 'utf-8',rely : [ 'vm' ]});
In.add('vex', {path : '${ctx}/js/jQuery/jvalidation/jquery.validate.ex.js',type : 'js',charset : 'utf-8',rely : [ 'vcn' ]});
In.add('vam', {path : '${ctx}/js/jQuery/jvalidation/additional-methods.min.js',type : 'js',charset : 'utf-8',rely : [ 'vex' ]});
In.add('common', {path : '${ctx}/common/js/common.js',type : 'js',charset : 'utf-8'});
In.add('wdatejs', {path : '${ctx}/js/WdatePicker/WdatePicker.js',type : 'js',charset : 'utf-8'});

In.ready('vam', 'common', 'wdatejs','jquijs', function() {
	setContextPath("${ctx}");
	
	jQuery("#inputForm").validate({
		focusInvalid : false
	});
	
	//合并行
	var rowCount = ${maxitemcount};
    for(var i = 0; i <= rowCount; i++)
    {
     table_rowspan("#MonthKH",i);
    }
    //合并列
 	var colCount =${fn:length(templatecontent)};
    for(var i = 0; i <= colCount; i++)
    {
     table_colspan("#MonthKH",i,rowCount);
    }
	
});
</script>
</head>
<body>
	<form action="${ctx }/assess/assessExaminationAction!save.action"
		id="inputForm" name="inputForm" method="post">
		<div class="title_bg">
			<div id="title" class="title">当前位置-考核管理-现场检查明细</div>
		</div>
		<div class="tabout">
			<table border="0" align="center" cellpadding="3" cellspacing="0">
				<tr>
					<td colspan="4" align="center"><b>${appraiseTableName }</b></td>
				</tr>

				<tr>
					<th>代维公司名称</th>
					<td colspan="3">
						<baseinfo:org displayProperty="ORGANIZENAME" id="${assessExaminationResult.contractorId }"></baseinfo:org>
					</td>
				</tr>
				<tr>
					<th>站点</th>
					<td>
						${siteName }
					</td>
					<th>负责人</th>
					<td>
						<baseinfo:user displayProperty="username" id="${assessExaminationResult.principal }"></baseinfo:user>
					</td>
				</tr>
				<tr>
					<th>检查人</th>
					<td>
						<baseinfo:user displayProperty="username" id="${assessExaminationResult.inspector }"></baseinfo:user>
					</td>
					<th>检查时间</th>
					<td>
						<fmt:formatDate value="${assessExaminationResult.inspectionDate }" pattern="yyyy年MM月dd日" />
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<table id="MonthKH">
							<thead>
								<tr>
									<th colspan="${maxitemcount}" style="width:80px;" nowrap>考核项目</th>
									<th style="width:60px;" nowrap>指标名称</th>
									<th style="width:40px;" nowrap>基准值</th>
									<th style="width:40px;" nowrap>挑战值</th>
									<th style="width:30px;" nowrap>权重</th>
									<th style="width:150px;" nowrap>计算公式和要求</th>
									<th nowrap>评价标准</th>
									<th style="width:80px;" nowrap>扣分</th>
									<th style="width:80px;" nowrap>扣分说明</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${templatecontent}">
									<tr>
										<c:forEach var="itemname" items="${item.ITEMNAMELIST}">
											<td>${itemname}</td>
										</c:forEach>
										<td>${item.NAME}</td>
										<td>${item.BENCHMARK_VALUE}</td>
										<td>${item.CHALLENGE_VALUE}</td>
										<td>${item.WEIGHT}</td>
										<td>${item.DEMAND_DESC}</td>
										<td>${item.EVALUATION_CRITERION}</td>
										<td>${item.SCORE*-1 }</td>
										<td>${item.RATING_DESC}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table></td>
				</tr>
			</table>
			<div style="text-align: center; margin-top: 10px">
				<input type="button" class="button" value="返回"
					onclick="history.go(-1);" />
			</div>
		</div>
	</form>
</body>
</html>