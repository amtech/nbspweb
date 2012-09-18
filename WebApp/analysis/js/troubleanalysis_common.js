var contextPath;
/**
 * set context path
 */
setContextPath = function(path) {
	contextPath = path;
}
// 获取参数
function getparam(id) {
	var s = id
			+ "&startTime="
			+ jQuery("#starttime").val()
			+ "&endTime="
			+ jQuery("#endtime").val()
			+ "&businessType="
			+ jQuery('select[name="businessType"]').find("option:selected")
					.val();
	return s;
}
// 处理及时率组织格式化
function timelyprosseorgFmatter(cellvalue, options, rowObjec) {
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:loadtimelyprosseorgDetail('"
			+ rowObjec.ID + "')>" + cellvalue + "</a>";
	return view;
}
// 处理及时率巡检组格式化
function timelyprossegroupFmatter(cellvalue, options, rowObjec) {
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:loadtimelyprossegroupDetail('"
			+ rowObjec.ID + "')>" + cellvalue + "</a>";
	return view;
}
// 处理及时率明细
function loadtimelyprosseorgDetail(id) {
	location = contextPath
			+ "/analysis/troubleDetailAction!prossetimelydetail.action?orgId="
			+ getparam(id);
}
// 处理及时率明细
function loadtimelyprossegroupDetail(id) {
	location = contextPath
			+ "/analysis/troubleDetailAction!prossetimelydetail.action?patrolGroupId="
			+ getparam(id);
}

// 响应及时率组织格式化
function timelyresponseorgFmatter(cellvalue, options, rowObjec) {
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:loadtimelyresponseorgDetail('"
			+ rowObjec.ID + "')>" + cellvalue + "</a>";
	return view;
}
// 处理及时率巡检组格式化
function timelyresponsegroupFmatter(cellvalue, options, rowObjec) {
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:loadtimelyresponsegroupDetail('"
			+ rowObjec.ID + "')>" + cellvalue + "</a>";
	return view;
}
// 响应及时率组织明细
function loadtimelyresponseorgDetail(id) {
	location = contextPath
			+ "/analysis/troubleDetailAction!responsetimelydetail.action?orgId="
			+ getparam(id);
}
// 响应及时率巡检组明细
function loadtimelyresponsegroupDetail(id) {
	location = contextPath
			+ "/analysis/troubleDetailAction!responsetimelydetail.action?patrolGroupId="
			+ getparam(id);
}

// 故障分级组织格式化
function levelsnumberorgFmatter(cellvalue, options, rowObjec) {
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:loadlevelsorgDetail('"
			+ rowObjec.ID + "')>" + cellvalue + "</a>";
	return view;
}
// 故障分级巡检组格式化
function levelsnumbergroupFmatter(cellvalue, options, rowObjec) {
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:loadlevelsgroupDetail('"
			+ rowObjec.ID + "')>" + cellvalue + "</a>";
	return view;
}
// 故障分级组织明细
function loadlevelsorgDetail(id) {
	location = contextPath
			+ "/analysis/troubleDetailAction!levelsnumberdetail.action?orgId="
			+ getparam(id);
}
//故障分级巡检组明细
function loadlevelsgroupDetail(id) {
	location = contextPath
			+ "/analysis/troubleDetailAction!levelsnumberdetail.action?patrolGroupId="
			+ getparam(id);
}
