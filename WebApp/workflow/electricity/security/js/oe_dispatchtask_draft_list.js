/**
 * 进入修改发电派单页面
 * 
 * @param id
 */
function edit(id) {
	location = contextPath + "/workflow/oeDispatchTaskAction!input.action?id="
			+ id + "&businessType=" + businessType;
}
/**
 * 进行删除发电派单操作
 * 
 * @param id
 * @param state
 */
function del(id, state) {
	var url = contextPath + "/workflow/oeDispatchTaskAction!delete.action?id="
			+ id + '&businessType=' + businessType + "&state=" + state;
	var r = confirm("删除的数据不能够恢复   请确认是否删除");
	if (!!r) {
		location.href = url;
	}
}
/**
 * 发电派单草稿箱操作列转换
 * 
 * @param cellValue
 * @param options
 * @param rowObjec
 * @returns {String}
 */
function draftOperationFormatter(cellValue, options, rowObjec) {
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:view('"
			+ cellValue + "','draft')><img src='"+contextPath+"/css/image/view.png' title='查看' /></a>";
	view = view
			+ "  <a style='color: blue;text-decoration: underline;' href=javascript:edit('"
			+ cellValue + "')><img src='"+contextPath+"/css/image/edit.png' title='编辑' /></a>";
	view = view
			+ "  <a style='color: blue;text-decoration: underline;' href=javascript:del('"
			+ cellValue + "','1')><img src='"+contextPath+"/css/image/delete.png' title='删除' /></a>";
	return view;
}
