/**
 * 进行告警单派单
 * 
 * @param id
 */
function dispatch(id) {
	location = contextPath
			+ "/workflow/oeDispatchTaskAction!dispatchInput.action?id=" + id;
}
/**
 * 进行告警单忽略
 * 
 * @param id
 */
function ignore(id) {
	var url = contextPath + "/workflow/oeOutageAlarmAction!ignore.action?id="
			+ id;
	var r = confirm("请确认是否忽略该告警单");
	if (!!r) {
		location.href = url;
	}
}
/**
 * 状态值转换
 * 
 * @param cellValue
 * @param options
 * @param rowObjec
 */
function alarmStateFormatter(cellValue, options, rowObjec) {
	var view = "";
	var stateValue = [ "未派单", "已派单", "已忽略", "结束" ];
	view = stateValue[parseInt(cellValue) - 1];
	return view;
}
/**
 * 断电告警单列表操作列转换
 * 
 * @param cellValue
 * @param options
 * @param rowObjec
 * @returns {String}
 */
function alarmListOperationFormatter(cellValue, options, rowObjec) {
	var view = "";
	if (rowObjec.STATE == '1') {
		view += "<a style='color: blue;text-decoration: underline;' href=javascript:dispatch('"
				+ cellValue + "')><img src='"+contextPath+"/css/image/dispatch.png' title='派单' /></a>";
		view += " <a style='color: blue;text-decoration: underline;' href=javascript:ignore('"
				+ cellValue + "')><img src='"+contextPath+"/css/image/forbidden.png' title='忽略' /></a>";
	}
	return view;
}