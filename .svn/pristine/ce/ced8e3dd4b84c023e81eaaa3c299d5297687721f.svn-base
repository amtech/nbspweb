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
		    debug: true, 
			submitHandler: function(form){
				inputForm.submit();
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
var index = 0;
function addItem(){
    index = index+1;
	var div2=document.createElement("div");
	var divbase=document.getElementById("baseDiv");
	div2.innerHTML= "<div style='width:100%;'>"
					+"<span>"
					+"&nbsp;&nbsp;加分说明:"
					+"</span>"
					+"<span>"
					+"<input type='text'  class='treetext required' name='appealForm.adjusstmentCauses'/>"
					+"</span>"
					+"<span>"
					+"&nbsp;&nbsp;分值:"
					+"</span>"
					+"<span>"
					+"<input type='text' class='treetext required number' name='appealForm.adjusstmentScores'/>"
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
function changeTransition(type){
	if("reject"==type){
		$("#baseDiv").empty();
		$("#add_item").attr({"disabled":"disabled"});
		$("#radio_flag").val("0");
		
	}else{
	    if("0"==$("#radio_flag").val()){
			addItem();
			$("#add_item").removeAttr("disabled");
			$("#radio_flag").val("1");
		}
	}
}
</script>
	</head>
	<body>
		<form action="${ctx }/assess/assessAppealFormWaitHandledAction!saveApprove.action" id="inputForm" name="inputForm" method="post">
			<div class="title_bg">
				<div id="title" class="title">
					当前位置-考核管理-申诉复核
				</div>
			</div>
			<div class="tabcontent">
			    <input type="hidden" name="examination.tableId" value="${tableId}">
				<table border="0" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<td colspan="4" align="center">
							<b>${appealMap['TABLE_NAME']}</b>
						</td>
					</tr>
					<tr>
						<th>
							市分公司名称
						</th>
						<td colspan="3">&nbsp;&nbsp;
						  中国移动通信集团安徽有限公司  ${appealMap['REGIONNAME']} 分公司
						</td>
					</tr>
					<tr>
						<th>
							代维单位名称
						</th>
						<td colspan="3">&nbsp;&nbsp;
						 ${appealMap['ORGNAME']}
						</td>
					</tr>
					<tr>
						<th>
							考核评估期间
						</th>
						<td colspan="3">&nbsp;&nbsp;
						 ${appealMap['APPRAISEMONTH']}
						</td>
					</tr>
					<tr>
						<td colspan="4">
						&nbsp;&nbsp;
						</td>
					</tr>
					
					<tr><td colspan="4">
					<table class="table table-bordered" id="MonthKH" style="width:100%">
						<thead>
							<tr>
								<th colspan="${maxitemcount}">考核项目</th>
								<th>指标名称</th>
								<th>基准值</th>
								<th>挑战值</th>
								<th>权重</th>
								<th>计算公式和要求</th>
								<th>评价标准</th>
								<th>扣分</th>
								<th>扣分说明</th>
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
									<td>
										${item.SCORE }
									</td>
									<td>
										${item.RATING_DESC}
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</td></tr>
				
				<c:if test="${isHasHis}">
					<tr>
						<td colspan="4">
						&nbsp;&nbsp;
						</td>
					</tr>
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
						&nbsp;&nbsp;
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
						<th>
							复核结果
						</th>
						<td colspan="3">&nbsp;&nbsp;
							<input <c:if test="${isHasHis}">checked</c:if>  class="required" type="radio" name="appealForm.transition" value="pass" onclick="changeTransition('pass')">通过
						    &nbsp;&nbsp;
						    <input class="required" type="radio" name="appealForm.transition" value="reject" onclick="changeTransition('reject')">不通过
						</td>
					</tr>
					<tr>
						<th>
							复核批注
						</th>
						<td colspan="3">&nbsp;&nbsp;
							<textarea class="required" rows="5" cols="142" id="appeal_form_comment" name="appealForm.comment">${appealMap.CHECK_OPINION }</textarea>
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
					  	<th>
							加分项 
						</th>
						<td colspan="3" id="baseDiv">
							<c:forEach var="item" items="${adjusstmentList}">
								<div style='width:100%;'>
									<span>&nbsp;&nbsp;加分说明:</span><span><input type='text'  class='treetext required' name='appealForm.adjusstmentCauses' value="${item['ADJUSSTMENT_CAUSE']}"/></span>
									<span>&nbsp;&nbsp;分值:</span><span><input type='text' class='treetext required number' 
									name='appealForm.adjusstmentScores' value="${item['ADJUSSTMENT_SCORE']}"/></span>&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' onclick='javascript:delItem(this)'>删除</a>
								</div>
							</c:forEach>
						</td>
					</tr>
				</table>
				<div style="text-align: center; margin-top: 10px">
				    <input type="button" class="button" value="添加加分项" id="add_item" onclick="addItem();" />
				    <input type="button" class="button" value="返回" onclick="history.go(-1);" />
					<input type="submit" class="button" value="提交" />
				</div>
			</div>
			<input type="hidden" name="appealForm.id" value="${appealMap['ID']}" >
			<input type="hidden" name="appealForm.taskId" value="${appealMap['TASKID']}" >
			<input type="hidden" name="appealForm.step" value="${appealMap['STEP']}" >
			<input type="hidden" id="radio_flag" value="0">
		</form>
	</body>
</html>