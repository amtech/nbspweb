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
});
function save(){
  $("#inputForm").submit();
}
function takeResult(val){
	var inputExamresultId = "";
    var inputTableName = "";
    var inputAppraiseMonth = "";
    var inputRegionName = "";
    var inputContractorName = "";
    if(""!=val){
         inputExamresultId = val.split(",")[0];
   		 inputTableName = val.split(",")[1];
    	 inputAppraiseMonth = val.split(",")[2];
    	 inputRegionName = val.split(",")[3];
    	 inputContractorName = val.split(",")[4];
    }
    $("#input_table_name").val(inputTableName);
    $("#input_appraise_month").val(inputAppraiseMonth);
    $("#input_region_name").val(inputRegionName);
    $("#input_contractor_name").val(inputContractorName);
    $("#input_examresult_id").val(inputExamresultId);
    
}
function iRest(){
$("#input_appealform_result").val("");
	takeResult("");
	$("#input_cause").val("");
}
</script>
	</head>
	<body>
		<form action="${ctx }/assess/assessAppealFormAction!save.action" id="inputForm" name="inputForm" method="post">
			<div class="title_bg">
				<div id="title" class="title">
					当前位置-考核管理-填写申诉
				</div>
			</div>
			<div class="tabcontent">
			    <input type="hidden" name="examination.tableId" value="${tableId}">
				<table border="0" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<td colspan="4" align="center">
							<b>${tableName}</b>
						</td>
					</tr>
					<tr>
						<th>
							考核结果
						</th>
						<td colspan="3">&nbsp;&nbsp;
							<select class="treetext required" style="width: 765px;" id="input_appealform_result" onchange="takeResult(this.value);">
								<option value=""></option>
								<c:forEach items="${resultList}" var="item">
									<option value="${item['ID']},${item['TABLE_NAME']},${item['APPRAISEMONTH']},${item['REGIONNAME']},${item['ORGNAME']}">
										${item['APPRAISEMONTH']}&nbsp;&nbsp;${item['ORGNAME']}&nbsp;&nbsp;${item['REGIONNAME']}&nbsp;&nbsp;${item['TABLE_NAME']}
									</option>
								</c:forEach>
							</select>
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th>
							考核表
						</th>
						<td>&nbsp;&nbsp;
							<input type="text" id="input_table_name"  class="treetext" readonly />
						</td>
						<th>
							考核月份
						</th>
						<td>&nbsp;&nbsp;
							<input type="text" id="input_appraise_month" class="treetext" readonly />
						</td>
					</tr>
					<tr>
						<th>
							区域
						</th>
						<td>&nbsp;&nbsp;
							<input type="text" id="input_region_name" class="treetext" readonly />
						</td>
						<th>
							代维公司
						</th>
						<td>&nbsp;&nbsp;
							<input type="text" id="input_contractor_name" class="treetext" readonly />
						</td>
					</tr>
					<tr>
						<th>
							申诉原因
						</th>
						<td colspan="3">&nbsp;&nbsp;
						    <input type="hidden" id="input_examresult_id" name="appealForm.examResultId" />
							<textarea class="required" rows="5" cols="142" id="input_cause" name="appealForm.cause"></textarea>
							<span style="color: red">*</span>
						</td>
					</tr>
				</table>
				<div style="text-align: center; margin-top: 10px">
					<input type="button" class="button" value="提交" onclick="save();" />
					<input type="button" class="button" value="重置" onclick="iRest();" />
				</div>
			</div>
		</form>
	</body>
</html>