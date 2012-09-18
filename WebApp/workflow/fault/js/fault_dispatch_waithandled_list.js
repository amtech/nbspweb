/**
 * fault_dispatch_waithandled_list.js
 * 
 * @creator yangjun 2011-11-01
 * @used for fault_dispatch_waithandled_list.jsp
 */
/**
 * reply fault
 */
function reply(id, taskId, businessType) {
	window.location.href = contextPath
			+ '/workflow/faultReplyAction!input.action?parameter.id=' + id
			+ '&faultReply.workflowTaskId=' + taskId + '&businessType='
			+ businessType;
}
/**
 * audit fault reply
 */
function audit(id, taskId, businessType) {
	window.location.href = contextPath
			+ '/workflow/faultAuditAction!input.action?parameter.id=' + id
			+ '&faultAudit.workflowTaskId=' + taskId + '&businessType='
			+ businessType;
}
/**
 * goto doing task page
 */
function toDoTaskForm(url, businessType) {
	window.location.href = contextPath + "/" + url + '&businessType='
			+ businessType;
}
//待办列表操作转换
function waitHandledFmatter(cellvalue, options, rowObject){
	var view = "  <a style='color: blue;text-decoration: underline;' href=javascript:viewFaultDispatch('"
			+ rowObject.DISPATCH_ID + "','" + rowObject.BUSINESS_TYPE + "')><img src='"+contextPath+"/css/image/view.png' title='查看' /></a>";
	view += "  <a style='color: blue;text-decoration: underline;' href=javascript:toDoTaskForm('"
			+ rowObject.PROCESS_URL + "','" + rowObject.BUSINESS_TYPE + "')><img src='"+contextPath+"/css/image/process.png' title='处理' /></a>";
	return view;
}