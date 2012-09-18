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
In.add('assess_appraise', {path : '${ctx}/assess/js/assess_appraise.js',type : 'js',charset : 'utf-8'});
In.ready('vam','jquijs', 'common','assess_appraise',function() {
	setContextPath("${ctx}");
	var url = "${ctx}/assess/assessMonthAppraiseAction!view.action?id=${id}";
	jQuery("#view_div").load(url+" #detailTb",function(){
	    var tab=jQuery('#tab_process').tabs({ cache: true });
		 tab.tabs( 'add' , '${ctx}/assess/assessMonthAppraiseApproveAction!list.action?id=${assessExaminationResult.id }' , '审核信息' , [0] );
	     tab.tabs( 'add' , '${ctx}/assess/assessMonthAppraiseConfirmAction!list.action?id=${assessExaminationResult.id }' , '确认信息' , [1] );
		var rowCount = ${assessExaminationResult.level};
	    for(var i = 0; i <= rowCount; i++){
	    	table_rowspan("#MonthKH",i);
	    }
	 	var colCount =${fn:length(assessExaminationResult.detailList)};
	    for(var i = 0; i <= colCount; i++){
	    	table_colspan("#MonthKH",i,rowCount);
	    }

	});
    jQuery("#inputForm").validate({
		submitHandler : function(form) {
			var reject=jQuery("#assessExaminationResult_reject").attr("checked");
			if(reject){
				if(confirm("请确认是否将反馈问题作为申诉理由反馈给移动公司进行复核。如果确认提出申诉请点击确定，否则点击取消。")){
					jQuery("#assessExaminationResult_apply").val("1");
				}
			}
			form.submit();
		}
	});
});
</script>
	</head>
	<body>
		<form action="${ctx }/assess/assessMonthAppraiseConfirmAction!save.action" id="inputForm" name="inputForm" method="post">
			<div class="title_bg">
				<div id="title" class="title">&nbsp; 
					当前位置-月度考核-确认考核评分
				</div>
			</div>
			<div class="tabcontent">
				<table border="0" align="center">
					<tr>
						<td colspan="4">
							<div id="view_div">
							</div>
						</td>
					</tr>
					<tr>
						<th>确认结果：</th>
						<td colspan="3">
							<input id="assessExaminationResult_apply" type='hidden' name="assessExaminationResult.apply" value="0" />
							<input id="tableType" name="assessExaminationResult.tableType" type="hidden" value="${tableType }" />
							<input name="assessExaminationResult.id" id="assessExaminationResult_id" type="hidden" value="${id }" />
							<input name="assessExaminationResult.taskId" id="assessExaminationResult_taskId" type="hidden" value="${taskId }" />
							<input type="radio" id="assessExaminationResult_pass" name="assessExaminationResult.approveResult" value="pass" checked />确认通过
							<input type="radio" id="assessExaminationResult_reject" name="assessExaminationResult.approveResult" value="reject" />问题反馈
						</td>
					</tr>
					<tr>
						<th>确认备注：</th>
						<td colspan="3">
							<textarea id="assessExaminationResult_approveRemark" name="assessExaminationResult.approveRemark" class="inputtext" style="height: 80px;"></textarea>
						</td>
					</tr>
				</table>
				<div style="text-align: center; margin-top: 10px">
					<input type="submit" class="button" value="提交" />
					<input type="button" onclick="history.go(-1);" class="button" value="返回" />
				</div>
			</div>
		</form>
	</body>
	<script type="text/javascript">
	</script>
</html>