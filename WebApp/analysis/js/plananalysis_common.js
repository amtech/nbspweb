var contextPath;
/**
 * set context path
 */
setContextPath = function(path) {
	contextPath = path;
}
// 获取参数
function getplanparam(id) {
	var s = id
			+ "&startTime="
			+ jQuery("#starttime").val()
			+ "&endTime="
			+ jQuery("#endtime").val()
			+ "&resourceType="
			+ jQuery('select[name="resourceType"]').find("option:selected")
					.val()
	return s;
}

// 计划完成率组织格式化
function planfinishorgFmatter(cellvalue, options, rowObjec) {
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:loadfinishorgDetail('"
			+ rowObjec.ID + "')>" + cellvalue + "</a>";
	return view;
}
// 计划完成率巡检组格式化
function planfinishgroupFmatter(cellvalue, options, rowObjec) {
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:loadfinishgroupDetail('"
			+ rowObjec.ID + "')>" + cellvalue + "</a>";
	return view;
}
// 处理组织明细
function loadfinishorgDetail(id) {
	location = contextPath
			+ "/analysis/planDetailAction!planfinishdetail.action?orgId="
			+ getplanparam(id);
}
// 处理及时率巡检组明细
function loadfinishgroupDetail(id) {
	location = contextPath
			+ "/analysis/planDetailAction!planfinishdetail.action?patrolId="
			+ getplanparam(id);
}
// 计划覆盖率组织格式化
function rescoverorgFmatter(cellvalue, options, rowObjec) {
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:loadrescoverorgDetail('"
			+ rowObjec.ID + "')>" + cellvalue + "</a>";
	return view;
}
// 计划覆盖率巡检组格式化
function rescovergroupFmatter(cellvalue, options, rowObjec) {
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:loadrescovergroupDetail('"
			+ rowObjec.ID + "')>" + cellvalue + "</a>";
	return view;
}
// 处理组织明细
function loadrescoverorgDetail(id) {
	location = contextPath
			+ "/analysis/planDetailAction!planrescoverdetail.action?orgId="
			+ getplanparam(id);
}
// 处理及时率巡检组明细
function loadrescovergroupDetail(id) {
	location = contextPath
			+ "/analysis/planDetailAction!planrescoverdetail.action?patrolId="
			+ getplanparam(id);
}