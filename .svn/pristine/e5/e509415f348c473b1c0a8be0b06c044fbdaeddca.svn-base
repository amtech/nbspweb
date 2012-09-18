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
In.add('vm', {path : '${ctx}/js/jQuery/jvalidation/jquery.validate.min.js',type : 'js',charset : 'utf-8'});
In.add('vcn', {path : '${ctx}/js/jQuery/jvalidation/messages_cn.js',type : 'js',charset : 'utf-8',rely : [ 'vm' ]});
In.add('vex', {path : '${ctx}/js/jQuery/jvalidation/jquery.validate.ex.js',type : 'js',charset : 'utf-8',rely : [ 'vcn' ]});
In.add('vam', {path : '${ctx}/js/jQuery/jvalidation/additional-methods.min.js',type : 'js',charset : 'utf-8',rely : [ 'vex' ]});
In.add('common', {path : '${ctx}/common/js/common.js',type : 'js',charset : 'utf-8'});
In.add('wdatejs', {path : '${ctx}/js/WdatePicker/WdatePicker.js',type : 'js',charset : 'utf-8'});
In.ready('vam', 'common', 'wdatejs',function() {
	setContextPath("${ctx}");
	jQuery("#inputForm").validate({
		focusInvalid : false,
		submitHandler : function(form) {
			var ym = jQuery("#yearmonth").val();
			if (ym != "") {
				ym += "-01";
			}
			jQuery("#appraiseMonth").val(ym);
			if (checkData()) {
				form.submit();
			}
		}
	});
});
</script>
	</head>
	<body>
		<form action="${ctx }/assess/assessMonthAppraiseAction!inputSecond.action" id="inputForm" name="inputForm" method="post">
			<div class="title_bg">
				<div id="title" class="title">&nbsp; 
					当前位置-月度考核-考核评分
				</div>
			</div>
			<div class="tabcontent">
				<table border="0" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<th>考核月份</th>
						<td>
							<fmt:formatDate value="${assessExaminationResult.appraiseMonth }" pattern="yyyy-MM" var="yearmonth" />
							<input id="yearmonth" name="yearmonth" type="text" class="Wdate inputtext required" value="${yearmonth }" style="width: 220px;" onfocus="WdatePicker({dateFmt:'yyyy-MM',maxDate:'%y-%M'})" />
							<input id="taskId" name="assessExaminationResult.taskId" type="hidden" value="${taskId }" />
							<input id="id" name="assessExaminationResult.id" type="hidden" value="${assessExaminationResult.id }" />
							<input id="tableType" name="assessExaminationResult.tableType" type="hidden" value="${assessExaminationResult.tableType }" />
							<input id="regionId" name="assessExaminationResult.regionId" type="hidden" value="${LOGIN_USER.regionId }" />
							<input id="appraiseMonth" name="assessExaminationResult.appraiseMonth" type="hidden" value="${assessExaminationResult.appraiseMonth }" />
						</td>
						<th>代维公司</th>
						<td>
							<input id="maintenanceName" name="maintenanceName" readonly value="<baseinfo:org displayProperty="ORGANIZENAME" id="${assessExaminationResult.contractorId }"></baseinfo:org>" class="inputtext required" style="width: 220px" />
							<a href="javascript:getContractor();"> <img border="0" src="${ctx}/css/images/selectcode.gif" /> </a>
							<input type="hidden" name="assessExaminationResult.contractorId" id="contractorId" value="${assessExaminationResult.contractorId }" />
						</td>
					</tr>
					<tr>
						<th>考核模板</th>
						<td colspan="3">
							<select id="tableId" name="assessExaminationResult.tableId" style="width: 220px;" class="required">
								<c:forEach var="item" items="${templateList }">
									<option value="${item.id }">${item.table_name }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
				<div style="text-align: center; margin-top: 10px">
					<input type="submit" class="button" value="下一步" />
				</div>
			</div>
		</form>
	</body>
	<script type="text/javascript">
/**
 * 获取维护单位
 */
function getContractor() {
	var val = showOrg('${ctx}/commonaccess!getorg.action?orgtype=2');
	var orgId = "";
	var orgName = "";
	if (val) {
		orgId = val[0];
		orgName = val[1];
		jQuery("#contractorId").val(orgId);
		jQuery("#maintenanceName").val(orgName);
	}
}
function checkData(){
	var tId=jQuery("#id").val();
	if(tId==""){
		tId="-1";
	}
	var id="&id="+tId;
	var tableId="&tableId="+jQuery("#tableId").val();
	var contractorId="&contractorId="+jQuery("#contractorId").val();
	var appraiseMonth="&appraiseMonth="+jQuery("#appraiseMonth").val();
	var regionId="&regionId="+jQuery("#regionId").val();
	var queryString=id+tableId+contractorId+appraiseMonth+regionId;
	var actionUrl="${ctx}/assess/assessMonthAppraiseAction!isExist.action?"+queryString;
	var flag=true;
	jQuery.ajax({url: actionUrl,async:false,success: function(data) {
		if(data=="1"){
			alert("该月度考核评分已经存在！");
			flag=false;
		}
	}});
	return flag;
}
	</script>
</html>