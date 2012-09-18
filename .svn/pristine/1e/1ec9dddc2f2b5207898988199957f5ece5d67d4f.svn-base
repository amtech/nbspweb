/**
 * 进入派单待办处理页面
 * 
 * @param url
 */
function doTask(url) {
	location = contextPath + url + "&businessType=" + businessType;
}
/**
 * 发电派单待办工作操作列转换
 * 
 * @param cellValue
 * @param options
 * @param rowObjec
 * @returns {String}
 */
function waitHandledOperationFormatter(cellValue, options, rowObjec) {
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:view('"
			+ cellValue + "')><img src='"+contextPath+"/css/image/view.png' title='查看' /></a>";
	view = view
			+ "  <a style='color: blue;text-decoration: underline;' href=javascript:doTask('"
			+ rowObjec.PROCESS_URL + "')><img src='"+contextPath+"/css/image/process.png' title='处理' /></a>";
	return view;
}
