/**
 * 获取站点资源
 */
function searchResource() {
	var businessType = jQuery("#businessType").val();
	if (businessType == "") {
		alert("没有选择专业类型！");
		return;
	}
	var val = showresource(contextPath + '/commonaccess!getresourceinfo.action?businessType='+businessType);
	if (val) {
		jQuery("#oeDispatchTask_stationType").val(val[0].TYPE);
		jQuery("#oeDispatchTask_stationId").val(val[0].ID);
		jQuery("#oeDispatchTask_stationName").val(val[0].NAME);
		jQuery("#oeDispatchTask_stationAddress").val(val[0].ADDRESS);
		getTitle();
	}
}
/**
 * 获取发电派单标题
 */
function getTitle() {
	var title = jQuery("#oeDispatchTask_stationName").val();
	if (title) {
		var date = jQuery("#oeDispatchTask_blackoutTime").val();
		if (date) {
			title += date;
		}
		title += "发电派单";
		jQuery("#oeDispatchTask_title").val(title);
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
	jQuery("#oeDispatchTask_stationType").val("");
	jQuery("#oeDispatchTask_stationId").val("");
	jQuery("#oeDispatchTask_stationName").val("");
	jQuery("#oeDispatchTask_stationAddress").val("");
	jQuery("#oeDispatchTask_blackoutTime").val("");
	jQuery("#maintenanceName").val("");
	jQuery("#maintenanceId").val("");
	jQuery("#oeDispatchTask_title").val("");
	jQuery("#oeDispatchTask_blackoutReason").val("");
}