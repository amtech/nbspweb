/**
 * 进行取消发电派单
 * 
 * @param id
 */
function cancelObject(id) {
	var url = contextPath
			+ "/workflow/oeDispatchTaskCancelAction!cancel.action?id=" + id
			+ "&businessType=" + businessType;
	var r = confirm("取消后流程将被终止  请确认是否取消该供电保障派单流程")
	if (r == true) {
		window.location.href = url;
	}
}
/**
 * 发电派单待取消操作列转换
 * 
 * @param cellValue
 * @param options
 * @param rowObjec
 * @returns {String}
 */
function waitCanceledOperationFormatter(cellValue, options, rowObjec) {
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:view('"
			+ cellValue + "')><img src='"+contextPath+"/css/image/view.png' title='查看' /></a>";
	view = view
			+ "  <a style='color: blue;text-decoration: underline;' href=javascript:cancelObject('"
			+ cellValue + "')><img src='"+contextPath+"/css/image/forbidden.png' title='取消' /></a>";
	return view;
}
