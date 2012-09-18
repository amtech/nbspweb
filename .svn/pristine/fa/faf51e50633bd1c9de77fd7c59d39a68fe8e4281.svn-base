/**
 * oe_dispatchtask_common.js
 * 
 * @creator 杨隽 2012-05-04 创建
 * @update 杨隽 2012-05-07 添加view() edit() del() typeFormatter()
 *         draftOperationFormatter()方法
 * @used for oe_dispatchtask's all jsp files
 */
var contextPath;
var businessType;
/**
 * 设置访问上下文路径
 * 
 * @param path
 */
function setContextPath(path) {
	contextPath = path;
}
/**
 * 设置专业类型
 * 
 * @param type
 */
function setBusinessType(type) {
	businessType = type;
}
/**
 * 获取维护单位
 */
function searchOrg() {
	var val = showOrg(contextPath + '/commonaccess!getorg.action?orgtype=2');
	var orgId = "";
	var orgName = "";
	if (val) {
		orgId = val[0];
		orgName = val[1];
		jQuery("#maintenanceId").val(orgId);
		jQuery("#maintenanceName").val(orgName);
	}
}
/**
 * 发电派单查看信息
 * 
 * @param id
 */
function view(id, origin) {
	location = contextPath + "/workflow/oeDispatchTaskAction!view.action?id="
			+ id + "&origin=" + origin;
}
/**
 * 站点类型值转换
 * 
 * @param cellValue
 * @param options
 * @param rowObjec
 * @returns {String}
 */
function typeFormatter(cellValue, options, rowObjec) {
	var view = "";
	if (cellValue == "A24") {
		view = "基站";
	}
	return view;
}
/**
 * 状态值转换
 * @param cellValue
 * @param options
 * @param rowObjec
 * @returns
 */
function stateViewFormatter(cellValue,options,rowObjec){
	var state=["待派单","待调度","处理中","回单审核","处理结束","已取消"];
	if(cellValue){
		var view=state[parseInt(cellValue)-1];
		return view;
	}
	return "";
}
/**
 * 查询
 */
function query() {
	jQuery("#oedispatchtaskgrid").jqGrid().setGridParam({
		postData : {
			'title' : jQuery("#taskName").val(),
			'businessType' : jQuery("#businessType").val(),
			'maintenanceId' : jQuery("#maintenanceId").val(),
			'stationName' : jQuery("#stationName").val(),
			'createDateMin' : jQuery("#createDateMin").val(),
			'createDateMax' : jQuery("#createDateMax").val(),
			'state' : jQuery("#taskState").val()
		}
	}).trigger("reloadGrid");
}
/**
 * 生成查看tab页
 */
function showTab(id) {
	//生成查看tab页
	var tab = jQuery("#tab_process").tabs({
		cache : true
	});
	//缓存tab页
	tab.tabs('add',contextPath+'/workflow/oeOilengineRecordAction!list.action?id='+id,'油机发电记录信息', [ 1 ]);
	if ("${oeDispatchTask.state}" != '6') {
		tab.tabs('add',contextPath+'/workflow/oeScheduleTaskAction!list.action?id='+id,'调度信息', [ 2 ]);
		tab.tabs('add',contextPath+'/workflow/oeReplyTaskAction!list.action?id='+id,'派单回复信息', [ 3 ]);
		tab.tabs('add',contextPath+'/workflow/oeReplyApproveTaskAction!list.action?id='+id,'派单回复审核信息', [ 4 ]);
	}
}
/**
 * 显示油机发电记录
 * @param id
 */
function showOilGenEleHistory(id){
	var actionUrl=contextPath+'/workflow/oeOilengineRecordAction!list.action?id='+id;
	actionUrl+="&rnd="+Math.random();
	jQuery("#oilGenElecRecordTd").load(actionUrl);
}
/**
 * 显示油机调度信息
 * @param id
 */
function showScheduleHistroy(id){
	var actionUrl=contextPath+'/workflow/oeScheduleTaskAction!list.action?id='+id;
	actionUrl+="&rnd="+Math.random();
	jQuery("#scheduleHistoryTd").load(actionUrl);
}
/**
 * 显示油机派单回复信息
 * @param id
 */
function showReplyHistory(id){
	var actionUrl=contextPath+'/workflow/oeReplyTaskAction!list.action?id='+id;
	actionUrl+="&rnd="+Math.random();
	jQuery("#replyHistoryTd").load(actionUrl);
}
/**
 * 显示油机派单回复验证信息
 * @param id
 */
function showAuditHistory(id){
	var actionUrl=contextPath+'/workflow/oeReplyApproveTaskAction!list.action?id='+id;
	actionUrl+="&rnd="+Math.random();
	jQuery("#auditHistoryTd").load(actionUrl);
}