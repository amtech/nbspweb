/**
 * 获取审核人
 */
function searchAcceptUser(regionId) {
	var val = showOrgPerson(contextPath
			+ '/commonaccess!getstaff.action?orgtype=1&flag=radio&regionid='+regionId);
	if (val) {
		jQuery("#approverId").val(val[0]);
		jQuery("#approverName").val(val[1]);
	}
}
/**
 * 根据审核结果决定是否显示转审人
 * 
 * @param value
 *            审核结果
 */
function changeApproveResult(value) {
	if (value == "transfer") {
		jQuery("#transferApproverTr").attr("style", "display:");
	} else {
		jQuery("#transferApproverTr").attr("style", "display:none");
	}
}
/**
 * 提交表单数据
 * 
 * @returns {Boolean}
 */
function submitData() {
	return true;
}
/**
 * 重置表单数据
 */
function resetData() {
	jQuery("#transferApproverTr").attr("style", "display:none");
	jQuery("#oeReplyApproveTask_onstation").removeAttr("checked");
	jQuery("#oeReplyApproveTask_offstation").removeAttr("checked");
	jQuery("#oeReplyApproveTask_pass").attr("checked", "checked");
	jQuery("#oeReplyApproveTask_reject").removeAttr("checked");
	jQuery("#oeReplyApproveTask_transfer").removeAttr("checked");
	jQuery("#approverName").val("");
	jQuery("#approverId").val("");
	jQuery("#oeReplyApproveTask_approveRemark").val("");
}