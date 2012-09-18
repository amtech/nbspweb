/**
 * 供电保障油机列表
 */
// 供电保障油机操作列转换
function OptFmatter(cellvalue, options, rowObject) {
	var view = "  <a style='color: blue;text-decoration: underline;' href=javascript:view('"
			+ cellvalue + "')><img src='"+contextPath+"/css/image/view.png' title='查看' /></a>";
	view += "  <a style='color: blue;text-decoration: underline;' href=javascript:edit('"
			+ cellvalue + "')><img src='"+contextPath+"/css/image/edit.png' title='编辑' /></a>";
	if (rowObject.STATE != null && rowObject.STATE == '空闲') {
		view += "  <a style='color: blue;text-decoration: underline;' href=javascript:del('"
				+ cellvalue + "')><img src='"+contextPath+"/css/image/delete.png' title='删除' /></a>";
	}
	view += "  <a style='color: blue; text-decoration: underline;' href=javascript:recordAddOil('"
			+ cellvalue + "')><img src='"+contextPath+"/css/image/addoil.png' title='加油' /></a>";
	view += "  <a style='color: blue; text-decoration: underline;' href=javascript:viewOilRecord('"
			+ cellvalue + "')><img src='"+contextPath+"/css/image/addoilrecord.png' title='加油记录' /></a>";
	return view;
}

/**
 * 加油
 * 
 * @param id
 */
function recordAddOil(id) {
	window.location.href = contextPath
			+ "/oil/oilEngineManageAction!recordAddOil.action?id=" + id;
}
/**
 * 查看加油记录
 * 
 * @param id
 */
function viewOilRecord(id) {
	window.location.href = contextPath
			+ "/oil/oilEngineManageAction!viewOilRecord.action?id=" + id;
}
/**
 * 查看
 * 
 * @param id
 */
function view(id) {
	window.location.href = contextPath
			+ "/oil/oilEngineManageAction!view.action?id=" + id;
}

/**
 * 编辑
 * 
 * @param id
 */
function edit(id) {
	window.location.href = contextPath
			+ "/oil/oilEngineManageAction!input.action?id=" + id;
}

/**
 * 删除
 * 
 * @param id
 */
function del(id) {
	if (confirm("你确定要删除这条信息吗")) {
		window.location.href = contextPath
				+ "/oil/oilEngineManageAction!del.action?id=" + id;
	}
}