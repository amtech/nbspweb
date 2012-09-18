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

	jQuery("#inputForm").validate({
		focusInvalid : false
	});

	setContextPath("${ctx}");
});
function save(){
  $("#inputForm").submit();
}
function addItem(){
	var div2=document.createElement("div");
	var divbase=document.getElementById("aaa");
	div2.innerHTML= "<div style='width:100%;'>"
					+"<span>"
					+"&nbsp;&nbsp;加分说明:"
					+"</span>"
					+"<span>"
					+"<input type='text' class='treetext' name='appealForm.adjusstmentCauses'/>"
					+"</span>"
					+"<span>"
					+"&nbsp;&nbsp;分值:"
					+"</span>"
					+"<span>"
					+"<input type='text' class='treetext' name='appealForm.adjusstmentScores'/>"
					+"</span>"
					+"&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' onclick='javascript:delItem(this)'>删除</a>"
					+"</div>";			
	divbase.appendChild(div2);
}
function delItem(obj){
	var divbase = obj.parentNode;
	var divs = divbase.parentNode;
	divs.removeChild(divbase);
}
</script>
	</head>
	<body>
		<form action="${ctx }/assess/assessAppealFormWaitHandledAction!saveApprove.action" id="inputForm" name="inputForm" method="post">
			<div class="title_bg">
				<div id="title" class="title">
					当前位置-考核管理-申诉审核
				</div>
			</div>
			<div class="tabcontent">
			    <input type="hidden" name="examination.tableId" value="${tableId}">
				<table border="0" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<td colspan="4">
							考核情况
						</td>
					</tr>
					<tr>
						<th>
							考核表
						</th>
						<td colspan="3">&nbsp;&nbsp;
						  ${appealMap['TABLE_NAME']}
						</td>
					</tr>
					<tr>
						<th>
							考核月份
						</th>
						<td>&nbsp;&nbsp;
						  ${appealMap['APPRAISEMONTH']}
						</td>
						<th width="10%">
							考核成绩
						</th>
						<td>&nbsp;&nbsp;
						    ${appealMap['SCORE']}
						</td>
					</tr>
					<tr>
						<th>
							区域
						</th>
						<td>&nbsp;&nbsp;
							${appealMap['REGIONNAME']}
						</td>
						<th>
							代维公司
						</th>
						<td>&nbsp;&nbsp;
						 	${appealMap['ORGNAME']}
						</td>
					</tr>
					<tr>
						<td colspan="4">
							申诉情况
						</td>
					</tr>
					<tr>
						<th>
							申诉人
						</th>
						<td>&nbsp;&nbsp;
						   ${appealMap['USERNAME']}
						</td>
						<th>
							申诉时间
						</th>
						<td>&nbsp;&nbsp;
						  <fmt:formatDate value="${appealMap['APPEAL_TIME']}"/>
						</td>
					</tr>
					<tr>
						<th>
							申诉理由
						</th>
						<td colspan="3">&nbsp;&nbsp;
						  ${appealMap['CAUSE']}
						</td>
					</tr>
					<tr>
						<td colspan="4">
							复核情况
						</td>
					</tr>
					<tr>
						<th>
							复核结果
						</th>
						<td colspan="3">&nbsp;&nbsp;
						  <c:if test="${appealMap['CHECK_RESULT']=='pass'}">通过</c:if>
						</td>
					</tr>
					<tr>
						<th>
							复核批注
						</th>
						<td colspan="3">&nbsp;&nbsp;
						    ${appealMap['CHECK_OPINION']}
						</td>
					</tr>
					
					<tr>
						<th>
							加分项 
						</th>
						<td colspan="3"><br>
						    <table border="0" align="center" cellpadding="3" cellspacing="0" style="width:90%;">
						    <c:forEach items="${adjusstmentList}" var="item">
 								<tr>
						    		<td style="width:10%;">
						    			加分说明
						    		</td>
						    		<td style="width:40%;">
						    			${item['ADJUSSTMENT_CAUSE']}
						    		</td>
						    		<td style="width:10%;">
						    			分值
						    		</td>
						    		<td style="width:40%;">
						   				${item['ADJUSSTMENT_SCORE']}
						    		</td>
						    	</tr>
						    </c:forEach>
						    		<td style="width:10%;">
						    			
						    		</td>
						    		<td style="width:40%;">
						    			
						    		</td>
						    		<td style="width:10%;">
						    			总计
						    		</td>
						    		<td style="width:40%;">
						   				${appealMap['APPEALSCORE']}
						    		</td>
						    </table><br>
						</td>
					</tr>

					<c:if test="${isHasHis }">
					<tr>
						<td colspan="4">
							审核情况
						</td>
					</tr>
					<c:forEach items="${hisList}" var="item">
					<tr>
						<th>
							${item.taskName}
						</th>
						<td colspan="3">
						    <br>
							<table border="0" align="center" cellpadding="3" cellspacing="0" style="width:90%;">
						    		<tr>
						    		<td style="width:20%;">
						    			 审核结果
						    		</td>
						    		<td style="width:80%;">
						    			${item.transition}
						    		</td>
						    		</tr>
						    		<tr>
						    		<td style="width:20%;">
						    			 审核人
						    		</td>
						    		<td style="width:80%;">
						    			${item.userName}
						    		</td>
						    		</tr>
						    		<tr>
						    		<td style="width:20%;">
						    			 审核时间
						    		</td>
						    		<td style="width:80%;">
						    			${item.endTime}
						    		</td>
						    		</tr>
						    		<tr>
						    		<td style="width:20%;">
						    			 审核意见
						    		</td>
						    		<td style="width:80%;">
						    			${item.description}
						    		</td>
						    		</tr>
						    </table>
						    <br>
						</td>
					</tr>
					</c:forEach>
				</c:if>

				<tr> 
						<td colspan="4">
							审核
						</td>
					</tr>
					<tr>
						<th>
							审核结果
						</th>
						<td colspan="3">&nbsp;&nbsp;
							<input type="radio" name="appealForm.transition" value="pass" checked>通过
						    &nbsp;&nbsp;
						    <input type="radio" name="appealForm.transition" value="reject" >不通过
						</td>
					</tr>
					<tr>
						<th>
							批注
						</th>
						<td colspan="3">&nbsp;&nbsp;
							<textarea rows="5" cols="120" name="appealForm.comment" class="required"></textarea>
							<span style="color: red">*</span>
						</td>
					</tr>
				</table>
				<div style="text-align: center; margin-top: 10px">
				    <input type="button" class="button" value="返回" onclick="history.go(-1);" />
					<input type="button" class="button" value="提交" onclick="save();" />
				</div>
			</div>
			<input type="hidden" name="appealForm.id" value="${appealMap['ID']}" >
			<input type="hidden" name="appealForm.taskId" value="${appealMap['TASKID']}" >
			<input type="hidden" name="appealForm.step" value="${appealMap['STEP']}" >
		</form>
	</body>
</html>