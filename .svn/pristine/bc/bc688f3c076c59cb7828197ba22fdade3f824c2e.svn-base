/**
 * fault_alert_undispatched_list.js
 * 
 * @creator yangjun 2011-11-01
 * @used for fault_alert_undispatched_list.jsp
 */
/**
 * dispatch fault
 */
function dispatch(id, businessType) {
	window.location.href = contextPath
			+ '/workflow/faultDispatchAction!input.action?parameter.id=' + id
			+ '&businessType=' + businessType + "&findType=B16";
}
/**
 * ignore fault
 */
function ignore(id, businessType) {
	var con = confirm("确定要忽略这条告警记录吗?");
	if (!!con) {
		window.location.href = contextPath
				+ '/workflow/faultAlertAction!ignore.action?parameter.id=' + id
				+ '&businessType=' + businessType;
	}
}
// 未派单故障列表操作
function unDispatchedfaultFmatter(cellvalue, options, rowObject) {
	var view = "  <a style='color: blue;text-decoration: underline;' href=javascript:viewFaultAlert('"
			+ cellvalue + "','" + rowObject.BUSINESS_TYPE + "')><img src='"+contextPath+"/css/image/view.png' title='查看' /></a>";
	view += "  <a style='color: blue;text-decoration: underline;' href=javascript:dispatch('"
			+ cellvalue + "','" + rowObject.BUSINESS_TYPE + "')><img src='"+contextPath+"/css/image/dispatch.png' title='派单' /></a>";
	if (rowObject.FIND_TYPE != 'B17') {
		view += "  <a style='color: blue;text-decoration: underline;' href=javascript:ignore('"
				+ cellvalue + "','" + rowObject.BUSINESS_TYPE + "')><img src='"+contextPath+"/css/image/forbidden.png' title='忽略' /></a>";
	}
	return view;
}