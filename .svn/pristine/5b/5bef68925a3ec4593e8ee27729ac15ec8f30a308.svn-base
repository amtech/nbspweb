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
In.add('assess_appraise', {path : '${ctx}/assess/js/assess_appraise.js',type : 'js',charset : 'utf-8'});
In.add('common', {path : '${ctx}/common/js/common.js',type : 'js',charset : 'utf-8'});
In.ready('jquijs','common','assess_appraise',function() {
	setContextPath("${ctx}");
	//合并行
	var rowCount = ${assessExaminationResult.level};
    for(var i = 0; i <= rowCount; i++){
     table_rowspan("#MonthKH",i);
    }
    //合并列
 	var colCount =${fn:length(assessExaminationResult.detailList)};
    for(var i = 0; i <= colCount; i++){
     table_colspan("#MonthKH",i,rowCount); 
    }
	var tab=jQuery('#tab_process').tabs({ cache: true });
	tab.tabs( 'add' , '${ctx}/assess/assessYearAppraiseApproveAction!list.action?id=${assessExaminationResult.id }' , '审核信息' , [0] );
    tab.tabs( 'add' , '${ctx}/assess/assessYearAppraiseConfirmAction!list.action?id=${assessExaminationResult.id }' , '确认信息' , [1] );
});
</script>
</head>
<body>
	<div class="title_bg">
		<div id="title" class="title">&nbsp; 当前位置-年度考核-查看考核评分</div>
	</div>
	<div class="tabout" id="detailTb">
		<table border="0" align="center" cellpadding="3" cellspacing="0">
			<tr>
				<td colspan="4" align="center"><b>中国移动通信集团${regionName}有限公司<baseinfo:region
							displayProperty="REGIONNAME"
							id="${assessExaminationResult.regionId }"></baseinfo:region>分公司${tableName}</b>
				</td>
			</tr>
			<tr>
				<th>市县分公司名称</th>
				<td>中国移动通信集团${regionName }有限公司<baseinfo:region
						displayProperty="REGIONNAME"
						id="${assessExaminationResult.regionId }"></baseinfo:region>分公司</td>
				<th>代维单位名称</th>
				<td><baseinfo:org displayProperty="ORGANIZENAME"
						id="${assessExaminationResult.contractorId }"></baseinfo:org></td>
			</tr>
			<tr>
				<th>评估考核时间</th>
				<td><fmt:formatDate
						value="${assessExaminationResult.appraiseMonth }"
						pattern="yyyy年MM月" /></td>
				<th>考核成绩</th>
				<td><fmt:formatNumber value="${assessExaminationResult.score }"
						pattern="#0.00" /></td>
			</tr>
			<tr>
				<td colspan="4">
					<table id="MonthKH"">
						<thead>
							<tr>
								<th colspan="${assessExaminationResult.level}"
									style="width:80px;" nowrap>考核项目</th>
								<th style="width:60px;" nowrap>指标名称</th>
								<th style="width:40px;" nowrap>基准值</th>
								<th style="width:40px;" nowrap>挑战值</th>
								<th style="width:30px;" nowrap>权重</th>
								<th style="width:150px;" nowrap>计算公式和要求</th>
								<th nowrap>评价标准</th>
								<th style="width:80px;" nowrap>指标实际完成值</th>
								<th style="width:90px;" nowrap>实际执行结果评分</th>
								<th tyle="width:80px;" nowrap>扣分说明</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item"
								items="${assessExaminationResult.detailList}">
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
									<td>${item.INDICATORS_VALUE}</td>
									<td>${item.SCORE}</td>
									<td>${item.RATING_DESC}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
			</td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="tab_process" style="width: 99%; height: 99%">
						<ul>
						</ul>
					</div></td>
			</tr>
		</table>
	</div>
	<div style="text-align: center; margin-top: 10px">
		<input type="button" onclick="history.go(-1);" class="button"
			value="返回" />
	</div>
</body>
</html>