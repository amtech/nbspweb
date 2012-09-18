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
In.add('vm',{path:'${ctx}/js/jQuery/jvalidation/jquery.validate.min.js',type:'js',charset:'utf-8'});
In.add('vcn',{path:'${ctx}/js/jQuery/jvalidation/messages_cn.js',type:'js',charset:'utf-8',rely:['vm']});
In.add('vex',{path:'${ctx}/js/jQuery/jvalidation/jquery.validate.ex.js',type:'js',charset:'utf-8',rely:['vcn']});
In.add('vam',{path:'${ctx}/js/jQuery/jvalidation/additional-methods.min.js',type:'js',charset:'utf-8',rely:['vex']});
In.add('jqui',{path:'${ctx}/js/jQuery/jqueryui/css/redmond/jquery-ui.custom.css'});
In.add('jquijs',{path:'${ctx}/js/jQuery/jqueryui/jquery-ui.custom.min.js',type:'js',charset:'utf-8',rely:['jqui']});
In.add('common',{path:'${ctx}/common/js/common.js',type:'js',charset:'utf-8'});
	In.ready('vam','jquijs','common',function(){
		setContextPath("${ctx}");
		var url = "${ctx}/workflow/wmaintainCreatePlanAction!view.action?id=${id}&showReturn=0";
		jQuery("#view_plan_div").load(url);
		jQuery("#patrolApproveForm").validate({
			focusInvalid : false
		});
		jQuery("th").css("width","25%");
		jQuery("td").css("width","75%");
	});
</script>
	</head>
	<body>
		<form
			action="${ctx }/workflow/wmaintainApproveReportAction!save.action"
			id="patrolApproveForm" name="patrolApproveForm" method="post">
			<div class="title_bg">
				<div id="title" class="title">
					当前位置-审核维修作业报告
				</div>
			</div>
			<div class="tabcontent">
				<table border="0" align="center">
					<tr>
						<td colspan="4">
							<div id="view_plan_div">
							</div>
						</td>
					</tr>
					<tr>
						<th>
							维护资源数量：
						</th>
						<td>
							${number_map.total_num }
						</td>
						<th>
							修复资源数量：
						</th>
						<td>
							${number_map.maintain_num }
						</td>
					</tr>
					<tr>
						<th>
							问题未解决资源数量：
						</th>
						<td>
							${number_map.remain_num }
						</td>
						<th>
							未维护资源数量：
						</th>
						<td>
							${number_map.nonmaintain_num }
						</td>
					</tr>
					<tr>
						<th>
							维护总结:
						</th>
						<td colspan="3">
							${plan.report }
						</td>
					</tr>
					<tr>
						<th>
							审核结果：
						</th>
						<td colspan="3">
							<input type="radio" id="plan_passApproveResult" name="plan.approveResult" onclick="changeApproveResult(this.value);" value="pass" checked />
							通过
							<input type="radio" id="plan_rejectApproveResult" name="plan.approveResult" onclick="changeApproveResult(this.value);" value="reject" />
							不通过
							<input type="radio" id="plan_transferApproveResult" name="plan.approveResult" onclick="changeApproveResult(this.value);" value="transfer" />
							转审
						</td>
					</tr>
					<tr id="transferApproverTr" style="display: none;">
						<th>
							转审人：
						</th>
						<td colspan="3">
							<input id="audit_transferApproverName" name="transferApproverName" class="inputtext required" style="width: 55%" readonly="readonly" />
							<a href="javascript:searchApprover('${LOGIN_USER.regionId }');"> <img border="0" src="${ctx}/css/image/personselect.png" /> </a>
							<span style="color: red">*</span>
							<input id="audit_transferApprover" name="plan.transferApproverId" type="hidden" />
						</td>
					</tr>
					<tr>
						<th>
							审核意见：
						</th>
						<td colspan="3">
							<textarea id="plan_approveRemark" name="plan.approveRemark" class="inputtext" style="height: 80px;width:450px"></textarea>
						</td>
					</tr>
				</table>
				<div style="text-align: center; margin-top: 10px">
					<input name="plan.id" value="${id }" type="hidden" />
					<input name="plan.workflowTaskId" value="${task_id }" type="hidden" />
					<input id="businesstype" name="plan.businessType" value="${plan.businessType }" type="hidden" />
					<input type="submit" class="button" value="审核 ">
					<input name="btnReset" value="重置" type="button" class="button" onclick="resetData();" />
					<input type="button" class="button" onclick="history.back()" value="返回">
				</div>
			</div>
		</form>
	</body>
	<script type="text/javascript">
	function checkData() {
		return true;
	}
	/**
	 * 获取审核人
	 */
	function searchApprover() {
		var val = showOrgPerson('${ctx}/commonaccess!getstaff.action?orgtype=1&flag=radio');
		if (val) {
			jQuery("#audit_transferApprover").val(val[0]);
			jQuery("#audit_transferApproverName").val(val[1]);
		}
	}
	/**
	 * 重置数据
	 */
	function resetData() {
		jQuery("#transferApproverTr").attr("style", "display:none");
		jQuery("#plan_passApproveResult").attr("checked", "checked");
		jQuery("#plan_rejectApproveResult").removeAttr("checked");
		jQuery("#plan_transferApproveResult").removeAttr("checked");
		jQuery("#audit_transferApproverName").val("");
		jQuery("#audit_transferApprover").val("");
		jQuery("#plan_approveRemark").val("");
	}
	/**
	 * 根据审核结果决定是否显示转审人
	 * @param value 审核结果
	 */
	function changeApproveResult(value) {
		if (value == "transfer") {
			jQuery("#transferApproverTr").attr("style", "display:");
		} else {
			jQuery("#transferApproverTr").attr("style", "display:none");
		}
	}
	</script>
</html>

