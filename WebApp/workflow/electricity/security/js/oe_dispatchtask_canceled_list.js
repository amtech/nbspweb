/**
 * 发电派单已取消工作操作列转换
 * 
 * @param cellValue
 * @param options
 * @param rowObjec
 * @returns {String}
 */
function canceledOperationFormatter(cellValue, options, rowObjec) {
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:view('"
			+ cellValue + "')><img src='"+contextPath+"/css/image/view.png' title='查看' /></a>";
	return view;
}
