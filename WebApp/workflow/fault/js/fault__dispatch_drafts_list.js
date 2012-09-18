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
			+ '/workflow/faultDispatchAction!input.action?parameter.dispatchId='
			+ id + '&businessType=' + businessType
			+ "&parameter.origin=draft&findType=B16";
}
/**
 * ignore fault
 */
function ignore(id, businessType) {
	window.location.href = contextPath
			+ '/workflow/faultAlertAction!ignore.action?parameter.id=' + id
			+ '&businessType=' + businessType;
}
/**
 * 
 * @param id
 */
function del(id, businessType) {
	var url = contextPath
			+ "/workflow/faultDispatchAction!delete.action?parameter.origin=draft&parameter.id=" + id
			+ '&businessType=' + businessType;
	var r = confirm("删除的数据不能够恢复   请确认是否删除");
	if (r) {
		window.location.href = url;
	}
}
//草稿箱操作列转换
function dispatchFmatter(cellvalue, options, rowObject) {
	var view = "  <a style='color: blue;text-decoration: underline;' href=javascript:viewFaultDispatch('"
			+ cellvalue + "','" + rowObject.BUSINESS_TYPE + "')><img src='"+contextPath+"/css/image/view.png' title='查看' /></a>";
	view += "  <a style='color: blue;text-decoration: underline;' href=javascript:dispatch('"
			+ cellvalue + "','" + rowObject.BUSINESS_TYPE + "')><img src='"+contextPath+"/css/image/edit.png' title='编辑' /></a>";
	view += "  <a style='color: blue;text-decoration: underline;' href=javascript:del('"
			+ cellvalue + "','" + rowObject.BUSINESS_TYPE + "')><img src='"+contextPath+"/css/image/delete.png' title='删除' /></a>";
	return view;
}