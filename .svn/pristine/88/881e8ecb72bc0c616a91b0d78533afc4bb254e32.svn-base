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
In.ready('vam', 'common', function() {
	jQuery("#inputForm").validate({
		focusInvalid : false,
		submitHandler : function(form) {
			if (checkData()) {
				form.submit();
			}
		}
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
	<form action="${ctx }/assess/assessMonthAppraiseAction!save.action"
		id="inputForm" name="inputForm" method="post">
		<div class="title_bg">
			<div id="title" class="title">当前位置-月度考核-考核评分</div>
		</div>
		<div class="tabout">
			<input id="taskId" name="assessExaminationResult.taskId" type="hidden" value="${assessExaminationResult.taskId }" />
			<input type="hidden" name="assessExaminationResult.appraiseMonth" id="appraiseMonth" value="<fmt:formatDate value="${assessExaminationResult.appraiseMonth }" pattern="yyyy-MM-dd"/>" />
			<input type="hidden" name="assessExaminationResult.id" id="id" value="${assessExaminationResult.id }"> <input type="hidden" name="assessExaminationResult.regionId" id="regionId" value="${assessExaminationResult.regionId }" /> 
			<input type="hidden" name="assessExaminationResult.contractorId" id="contractorId" value="${assessExaminationResult.contractorId }" />
			<input type="hidden" name="assessExaminationResult.tableId" id="tableId" value="${assessExaminationResult.tableId }" /> 
			<input type="hidden" name="assessExaminationResult.tableType" id="tableType" value="${assessExaminationResult.tableType }" /> 
			<input type="hidden" name="assessExaminationResult.isSubmited" id="isSubmited" value="1" />
			<table border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4" align="center">
						<b>中国移动通信集团${regionName}有限公司<baseinfo:region displayProperty="REGIONNAME" id="${assessExaminationResult.regionId }"></baseinfo:region>分公司${tableName}</b>
					</td>
				</tr>
				<tr>
					<th>市县分公司名称</th>
					<td colspan="3">
						中国移动通信集团${regionName }有限公司<baseinfo:region displayProperty="REGIONNAME" id="${assessExaminationResult.regionId }"></baseinfo:region>分公司</td>
				</tr>
				<tr>
					<th>代维单位名称</th>
					<td>
						<baseinfo:org displayProperty="ORGANIZENAME" id="${assessExaminationResult.contractorId }"></baseinfo:org>
					</td>
					<th>评估考核时间</th>
					<td>
						<fmt:formatDate value="${assessExaminationResult.appraiseMonth }" pattern="yyyy年MM月" />
					</td>
				</tr>
				<tr><td colspan="4">
					<table  id="MonthKH">
						<thead>
							<tr>
								<th colspan="${maxitemcount}" style="width:80px;" nowrap>考核项目</th>
								<th style="width:20px;" nowrap>序号</th>
								<th style="width:60px;" nowrap>指标名称</th>
								<th style="width:40px;" nowrap>基准值</th>
								<th style="width:40px;" nowrap>挑战值</th>
								<th style="width:30px;" nowrap>权重</th>
								<th style="width:150px;" nowrap>计算公式和要求</th>
								<th nowrap >评价标准</th>
								<th style="width:80px;" nowrap>指标实际完成值</th>
								<th style="width:90px;" nowrap>实际执行结果评分</th>
								<th style="width:80px;" nowrap>扣分说明</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${templatecontent}" varStatus="status">
								<tr>
									<c:set var="width" value="${80/maxitemcount }"></c:set>
									<c:forEach var="itemname" items="${item.ITEMNAMELIST}">
										<td >${itemname}</td>
									</c:forEach>
									<td >${status.index+1 }</td>
									<td>${item.NAME}</td>
									<td>${item.BENCHMARK_VALUE}</td>
									<td>${item.CHALLENGE_VALUE}</td>
									<td> ${item.WEIGHT}</td>
									<td>${item.DEMAND_DESC}</td>
									<td>${item.EVALUATION_CRITERION}</td>
									<td style="text-align: center;">
										<input id="assessExaminationResult_contentId${item.CONTENTID}" name="assessExaminationResult.contentId" value="${item.CONTENTID }" type="hidden" />
										<input id="assessExaminationResult_indicatorsValue${item.CONTENTID}" name="assessExaminationResult.indicatorsValue" value="${item.INDICATORS_VALUE }" style="width: 80px" type="text" class="required" />
									</td>
									<td style="text-align: center;">
										<input id="assessExaminationResult_itemScore${item.CONTENTID}"  name="assessExaminationResult.itemScore"  value="${item.SCORE }" style="width: 80px" type="text" class="required" />
									</td>
									<td style="text-align: center;">
										<c:set var="desc" value="无"></c:set>
										<c:if test="${not empty item.RATING_DESC }"><c:set var="desc" value="${item.RATING_DESC }"></c:set></c:if>
										<input id="assessExaminationResult_ratingDesc${item.CONTENTID}"  name="assessExaminationResult.ratingDesc"  value="${desc }" style="width: 80px" type="text" class="required" />
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</td></tr>
			</table>
			<div style="text-align: center; margin-top: 10px">
				<!-- <input type="button" class="button" value="保存" onclick="saveData();" /> --> 
				<input type="button" class="button" value="提交" onclick="submitData();" />
				<input type="button" class="button" value="返回" onclick="history.go(-1);" /> 
			</div>
		</div>
	</form>
</body>
<script type="text/javascript">
function saveData(){
	jQuery("#isSubmited").val("0");
	jQuery("#inputForm").submit();
}
function submitData(){
	jQuery("#isSubmited").val("1");
	jQuery("#inputForm").submit();
}
function checkData(){
	var regx=/^([1-9]\d*|0)(\.\d*[0-9])?$/;
	var fregx=/^-([1-9]\d*|0)(\.\d*[0-9])?$/;
	var valueArray=jQuery("input[name='assessExaminationResult.indicatorsValue']");
	var scoreArray=jQuery("input[name='assessExaminationResult.itemScore']");
	var descArray=jQuery("input[name='assessExaminationResult.ratingDesc']");
	for(i=0;i<valueArray.length;i++){
		if(valueArray[i].value==""){
			alert("第"+(i+1)+"行的指标实际完成值不能为空！");
			return false;
		}
		if(!regx.test(valueArray[i].value)){
			alert("第"+(i+1)+"行的指标实际完成值必须为非负数字格式！");
			return false;
		}
	}
	for(i=0;i<scoreArray.length;i++){
		if(scoreArray[i].value==""){
			alert("第"+(i+1)+"行的实际执行结果评分不能为空！");
			return false;
		}
		if(!regx.test(scoreArray[i].value)&&!fregx.test(scoreArray[i].value)){
			alert("第"+(i+1)+"行的指标实际完成值必须为数字格式！");
			return false;
		}
	}
	for(i=0;i<descArray.length;i++){
		if(descArray[i].value==""){
			alert("第"+(i+1)+"行的扣分说明不能为空！");
			return false;
		}
	}
	return true;
}
</script>
</html>