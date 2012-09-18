var contextPath;
/**
 * set context path
 */
setContextPath = function(path) {
	contextPath = path;
}
// 获取参数
function getworkorderparam(id) {
	var s = id + "&startTime=" + jQuery("#starttime").val() + "&endTime="
			+ jQuery("#endtime").val() + "&taskType="
			+ jQuery('select[name="taskType"]').find("option:selected").val();
	return s;
}
// 处理及时率格式化
function workordertimelyFmatter(cellvalue, options, rowObjec) {
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:loadworkordertimelyDetail('"
			+ rowObjec.ID + "')>" + cellvalue + "</a>";
	return view;
}
function loadworkordertimelyDetail(id) {
	location = contextPath
			+ "/analysis/workOrderDetailAction!prossetimelydetail.action?orgId="
			+ getworkorderparam(id);
}

// 审批通过明细格式化
function workorderpassFmatter(cellvalue, options, rowObjec) {
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:loadworkorderpassDetail('"
			+ rowObjec.ID + "')>" + cellvalue + "</a>";
	return view;
}
function loadworkorderpassDetail(id) {
	location = contextPath
			+ "/analysis/workOrderDetailAction!approvepassdetail.action?orgId="
			+ getworkorderparam(id);
}