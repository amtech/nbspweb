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
 * 保存表单数据
 * 
 * @returns {Boolean}
 */
function saveData() {
	jQuery("#is_submited").val("0");
	return true;
}
/**
 * 提交表单数据
 * 
 * @returns {Boolean}
 */
function submitData() {
	jQuery("#is_submited").val("1");
	return true;
}
/**
 * 重置表单数据
 */
function resetData() {
	jQuery("#replyContent").val("");
	jQuery("#approverName").val("");
	jQuery("#approverId").val("");
}