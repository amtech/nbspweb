/**
 * 获取审核人
 */
function searchAcceptUser(regionId) {
	var val = showOrgPerson(contextPath
			+ '/commonaccess!getstaff.action?orgtype=1&flag=radio&regionid='+regionId);
	if (val) {
		jQuery("#patrolinfo_acceptUserid").val(val[0]);
		jQuery("#patrolinfo_acceptUsername").val(val[1]);
	}
}
/**
 * 获取巡检组
 */
function getPatrolGroup() {
	var val = showPatrolGroup(contextPath
			+ '/commonaccess!getpatrolgroup.action?orgtype=2');
	if (val) {
		jQuery("#patrolinfo_patrolgroupid").val(val[0]);
		jQuery("#patrolinfo_patrolgroupname").val(val[1]);
		change();
	}
}
/**
 * 保存数据
 */
function saveData() {
	jQuery("#is_submited").val("0");
	jQuery("#patrolinfo_endtime").removeAttr("disabled");
	return true;
}
/**
 * 提交数据
 */
function submitData() {
	jQuery("#is_submited").val("1");
	jQuery("#patrolinfo_endtime").removeAttr("disabled");
	return true;
}
/**
 * 重置数据
 */
function resetData() {
	jQuery("#planname").val("");
	jQuery("#patrolinfo_starttime").val("");
	jQuery("#patrolinfo_endtime").val("");
	jQuery("#patrolinfo_patrolgroupname").val("");
	jQuery("#patrolinfo_patrolgroupid").val("");
	jQuery("#patrolinfo_acceptUsername").val("");
	jQuery("#patrolinfo_acceptUserid").val("");
	jQuery("#plansitegrid").jqGrid("clearGridData");
	clearInputElement();
}
/**
 * 清空选择的问题站点数据
 */
function clearInputElement() {
	if (jQuery("input[name='plan.patrolRecordId']")) {
		jQuery("input[name='plan.patrolRecordId']").remove();
	}
	if (jQuery("input[name='plan_patrolItemId']")) {
		jQuery("input[name='plan_patrolItemId']").remove();
	}
	if (jQuery("input[name='plan_stationType']")) {
		jQuery("input[name='plan_stationType']").remove();
	}
	if (jQuery("input[name='plan_stationId']")) {
		jQuery("input[name='plan_stationId']").remove();
	}
}
/**
 * 根据选择的问题站点数据创建输入站点问题的隐藏域
 */
function createInputElement() {
	if (!!mySGdata) {
		for (i = 0; i < mySGdata.length; i++) {
			jQuery("#patrolinfoForm").append(
					"<input id='plan_patrolRecordId" + (i + 1)
							+ "' name='plan.patrolRecordId' value='"
							+ mySGdata[i].ID + "' type='hidden' />");
			jQuery("#patrolinfoForm").append(
					"<input id='plan_patrolItemId" + (i + 1)
							+ "' name='plan.patrolItemId' value='"
							+ mySGdata[i].SUBITEM_ID + "' type='hidden' />");
			jQuery("#patrolinfoForm").append(
					"<input id='plan_stationType" + (i + 1)
							+ "' name='plan.stationType' value='"
							+ mySGdata[i].RESOURCE_TYPE + "' type='hidden' />");
			jQuery("#patrolinfoForm").append(
					"<input id='plan_stationId" + (i + 1)
							+ "' name='plan.stationId' value='"
							+ mySGdata[i].RESOURCE_ID + "' type='hidden' />");
		}
	}
}
/**
 * 根据维护组和日期修改计划名称
 */
function change() {
	var str = jQuery("#patrolinfo_patrolgroupname").val();
	str += jQuery("#patrolinfo_starttime").val() + "至"
			+ jQuery("#patrolinfo_endtime").val();
	str += "维修作业计划";
	jQuery("#planname").val(str);
}
/**
 * 设置jqgrid的主表数据
 * 
 * @param val
 */
function setMydata(val) {
	mydata = val;
}
/**
 * 设置jqgrid的从表数据
 * 
 * @param val
 */
function setMySGdata(val) {
	mySGdata = val;
}
// 站点的删除格式化删除
function mainOperateFormatter(cellValue, options, rowObject) {
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:deleteMainRow('"
			+ options.gid
			+ "','"
			+ options.rowId
			+ "','"
			+ rowObject.ID
			+ "')>删除</a>";
	return view;
}
// 站点问题的删除格式化删除
function subOperateFormatter(cellValue, options, rowObject) {
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:deleteSubRow('"
			+ options.gid
			+ "','"
			+ options.rowId
			+ "','"
			+ rowObject.RES_
			+ "')>删除</a>";
	return view;
}
// 删除站点行
function deleteMainRow(mainTableId, rowId, resId) {
	for ( var i = 0, n = 0; i < mySGdata.length; i++) {
		if (mySGdata[i].RES_ == resId) {
			jQuery("#plan_patrolRecordId" + (i + 1)).remove();
			jQuery("#plan_patrolItemId" + (i + 1)).remove();
			jQuery("#plan_stationType" + (i + 1)).remove();
			jQuery("#plan_stationId" + (i + 1)).remove();
		}
	}
	jQuery("#" + mainTableId).jqGrid('collapseSubGridRow', rowId);
	jQuery("#" + mainTableId).jqGrid('delRowData', rowId);
}
// 删除站点问题行
function deleteSubRow(subTableId, rowId, resId) {
	jQuery("#" + subTableId).jqGrid('delRowData', rowId);
	jQuery("#plan_patrolRecordId" + rowId).remove();
	jQuery("#plan_patrolItemId" + rowId).remove();
	jQuery("#plan_stationType" + rowId).remove();
	jQuery("#plan_stationId" + rowId).remove();
	for ( var i = 0, n = 0; i < mySGdata.length; i++) {
		if (mySGdata[i].RES_ != resId) {
			mySGdata[n] = mySGdata[i];
		}
	}
	mySGdata.length--;
	var flag = false;
	for ( var i = 0, n = 0; i < mySGdata.length; i++) {
		if (mySGdata[i].RES_ == resId) {
			flag = true;
			break;
		}
	}
	if (!flag) {
		var parentRowId = subTableId.replace("mySubGridName", "");
		deleteMainRow('plansitegrid', parentRowId, resId);
	}
}