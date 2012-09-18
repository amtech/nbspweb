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
In.ready('vam', 'common', 'wdatejs','jquijs', 'fault-common', function() {
	setContextPath("${ctx}");
});
function next(){
    var selector = document.getElementById("appraise_table_id");
    var checkval = selector.options[selector.selectedIndex].value;
	var checkText = selector.options[selector.selectedIndex].text;
	if('' == checkval){ 
		alert("请选择一个模板");
		return false;
	}
	$("#appraise_table_name").val(checkText);
	$("#inputForm").submit();
}
</script>
</head>
<body>
	<form
		action="${ctx }/assess/assessExaminationAction!inputSecond.action"
		id="inputForm" name="inputForm" method="post">
		<div class="title_bg">
			<div id="title" class="title">&nbsp; 当前位置-考核管理-现场检查</div>
		</div>
		<div class="tabcontent">
			<table border="0" align="center" cellpadding="3" cellspacing="0">
				<tr>
					<th>检查模板</th>
					<td colspan="3"><select class="treetext"
						id="appraise_table_id" name="appraiseTableId">
							<option value=""></option>
							<c:forEach items="${appraiseTables}" var="item">
								<option value="${item['ID'] }">${item['TABLE_NAME'] }</option>
							</c:forEach>
					</select></td>
				</tr>
			</table>
			<input type="hidden" id="appraise_table_name"
				name="appraiseTableName">
			<div style="text-align: center; margin-top: 10px">
				<input type="button" class="button" value="下一步" onclick="next();" />
			</div>
		</div>
	</form>
</body>
</html>