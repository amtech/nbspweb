/**
 * fault_common.js
 * 
 * @creator yangjun 2011-10-31
 * @used for fault's all jsp files
 */
var resourceItem;
var contextPath;
var resourceDefaultValue;
var patrolGroupId;
var patrolGroupName;
var address;
function setPatrolGroupId(id){
	patrolGroupId=id;
}
function setPatrolGroupName(name){
	patrolGroupName=name;
}
/**
 * 
 * @param addr
 */
function setAddress(addr){
	address=addr;
}
/**
 * set resource options form element
 */
setResourceItem = function(item) {
	resourceItem = jQuery(item);
}
/**
 * set context path
 */
setContextPath = function(path) {
	contextPath = path;
}
/**
 * show fault process history
 */
showProcessHistory = function(dispatchId) {
	var actionUrl = contextPath
			+ "/localeProcessAction!showProcessHistoryList.action?localeProcess.taskId="
			+ dispatchId + "&localeProcess.taskType=WTROUBLE_SENDTASK&rnd="
			+ Math.random();
	jQuery("#processHistoryTd").load(actionUrl);
};
/**
 * show fault process all photos
 */
showProcessPhotos = function(dispatchId) {
	var actionUrl = contextPath
			+ "/localeProcessAction!showProcessPhotos.action?localeProcess.taskId="
			+ dispatchId + "&localeProcess.taskType=WTROUBLE_SENDTASK&rnd="
			+ Math.random();
	jQuery("#processPhotosTd").load(actionUrl);
};
/**
 * show fault all photos
 */
showFaultPhotos = function(dispatchId) {
	var actionUrl = contextPath
			+ "/localeProcessAction!showProcessPhotos.action?localeProcess.taskId="
			+ dispatchId + "&localeProcess.taskType=WTROUBLE_ALARM&rnd="
			+ Math.random();
	jQuery("#photosTd").load(actionUrl);
};
/**
 * show fault eoms info
 */
showEoms = function(eomsId) {
	var actionUrl = contextPath
			+ "/workflow/wtroubleEomsAction!view.action?eomsId=" + eomsId + "&rnd="
			+ Math.random();
	jQuery("#eomsTd").load(actionUrl);
};
/**
 * show fault reply audit history
 */
showAuditHistory = function(dispatchId) {
	var actionUrl = contextPath
			+ "/workflow/faultAuditAction!showAuditHistoryList.action?parameter.id="
			+ dispatchId + "&&rnd=" + Math.random();
	jQuery("#auditHistoryTd").load(actionUrl);
};
/**
 * view fault dispatch
 */
function viewFaultAlert(id, businessType) {
	window.location.href = contextPath
			+ '/workflow/faultAlertAction!view.action?parameter.id=' + id
			+ '&businessType=' + businessType;
}
/**
 * view fault dispatch
 */
function viewFaultDispatch(id, businessType) {
	window.location.href = contextPath
			+ '/workflow/faultDispatchAction!view.action?parameter.id=' + id
			+ '&businessType=' + businessType;
}
/**
 * 获取站点资源
 */
function searchResource() {
	var businessType = jQuery("#businessType").val();
	if (businessType == "") {
		alert("没有选择专业类型！");
		return;
	}
	var url = contextPath + '/commonaccess!getresourceinfo.action';
	url += "?businessType=" + businessType;
	var val = showresource(url);
	if (val) {
		jQuery("#stationType").val(val[0].TYPE);
		jQuery("#stationId").val(val[0].ID);
		jQuery("#resourceName").val(val[0].NAME);
		jQuery("#address").val(val[0].ADDRESS);
		jQuery('#maintenanceId').val(val[0].ORGID);
		jQuery('#patrolGroupId').val(val[0].PATROLGROUPID);
		jQuery("#patrolGroupName").val(val[0].PATROLGROUPNAME);
	}
}
/**
 * 方法废弃于2012-07-12 杨隽
 * 
 * @param businessType
 * @param defaultValue
 */
function getResourceList(businessType, defaultValue) {
	var resourceName = $('#resourceName').val();
	var url = contextPath + "/workflow/faultDispatchAction!getResources.action";
	url += "?parameter.businessType=" + businessType;
	url += "&&parameter.resourceName=" + resourceName;
	resourceDefaultValue = defaultValue;
	jQuery.post(encodeURI(url, "UTF-8"), function(data, textStatus, jqXHR) {
				setDefaultResource(jqXHR);
			});
}
/**
 * 方法废弃于2012-07-12 杨隽
 */
function clearResourcesOptions() {
	resourceItem.empty();
}
/**
 * select resources list for businesstype 方法废弃于2012-07-12 杨隽
 */
getSelectResourceList = function(businessType, defaultValue, defaultValueType) {
	var resourceName = $('#resourceName').val();
	var url = contextPath + "/workflow/faultDispatchAction!getResources.action";
	url += "?parameter.businessType=" + businessType;
	url += "&&parameter.resourceName=" + resourceName;
	resourceDefaultValue = defaultValue;
	resourceDefaultValueType = defaultValueType;
	jQuery.post(encodeURI(url, "UTF-8"), function(data, textStatus, jqXHR) {
		setResourceCode(jqXHR);
	});
}
/**
 * set resource options for ajax response 方法废弃于2012-07-12 杨隽
 */
function setResourceCode(response) {
	var str = response.responseText;
	if (str == "")
		return true;
	var optionlist = str.split(";");
	if (typeof (optionlist.length) == "undefined") {
		var t = optionlist.split("=")[0];
		var v = optionlist.split("=")[1];
		var addr = optionlist[i].split("=")[2];
		var pid = optionlist[i].split("=")[3];
		var pname = optionlist[i].split("=")[4];
		var cid = optionlist[i].split("=")[5];
		var rType = optionlist[i].split("=")[6];
		if(addr==""||addr==null||addr=="null"){
			addr="";
		}
		if (t == resourceDefaultValue && rType == resourceDefaultValueType) {
			resourceItem.append("<option value='" + t + "' addr='" + addr
					+ "' pid='" + pid + "' cid='" + cid + "' pname='" + pname
					+ "' rtype='"+rType+"' selected>" + v + "</option>");
			if (pid != "" && pid != null && pid != "null") {
				jQuery('#maintenanceId').val(cid);
				if(patrolGroupId!=null&&patrolGroupId!=""){
					jQuery('#patrolGroupId').val(patrolGroupId);
				}else{
					jQuery('#patrolGroupId').val(pid);
				}
				if(patrolGroupName!=null&&patrolGroupName!=""){
					jQuery("#patrolGroupName").val(patrolGroupName);
				}else{
					jQuery("#patrolGroupName").val(pname);
				}
				if(address==null||address==""){
					jQuery("#address").val(addr);
				}
				jQuery('#stationType').val(rType);
			}
		} else {
			resourceItem.append("<option value='" + t + "' addr='" + addr
					+ "' pid='" + pid + "' cid='" + cid + "' pname='" + pname
					+ "' rtype='"+rType+"'>" + v + "</option>");
		}
	} else {
		for ( var i = 0; i < optionlist.length; i++) {
			var t = optionlist[i].split("=")[0];
			var v = optionlist[i].split("=")[1];
			var addr = optionlist[i].split("=")[2];
			var pid = optionlist[i].split("=")[3];
			var pname = optionlist[i].split("=")[4];
			var cid = optionlist[i].split("=")[5];
			var rType = optionlist[i].split("=")[6];
			if(addr==""||addr==null||addr=="null"){
				addr="";
			}
			if (t == resourceDefaultValue && rType == resourceDefaultValueType) {
				resourceItem.append("<option value='" + t + "' addr='" + addr
						+ "' pid='" + pid + "' cid='" + cid + "' pname='"
						+ pname + "' rtype='"+rType+"' selected>" + v + "</option>");
				if (pid != "" && pid != null && pid != "null") {
					jQuery('#maintenanceId').val(cid);
					if(patrolGroupId!=null&&patrolGroupId!=""){
						jQuery('#patrolGroupId').val(patrolGroupId);
					}else{
						jQuery('#patrolGroupId').val(pid);
					}
					if(patrolGroupName!=null&&patrolGroupName!=""){
						jQuery("#patrolGroupName").val(patrolGroupName);
					}else{
						jQuery("#patrolGroupName").val(pname);
					}
					if(address==null||address==""){
						jQuery("#address").val(addr);
					}
					jQuery('#stationType').val(rType);
				}
			} else {
				resourceItem.append("<option value='" + t + "' addr='" + addr
						+ "' pid='" + pid + "' cid='" + cid + "' pname='"
						+ pname + "' rtype='"+rType+"'>" + v + "</option>");
			}
		}
	}
}
/**
 * 方法废弃于2012-07-12 杨隽
 * 
 * @param response
 * @returns {Boolean}
 */
function setDefaultResource(response) {
	var str = response.responseText;
	if (str == "")
		return true;
	var optionlist = str.split(";");
	if (typeof(optionlist.length) == "undefined") {
		var t = optionlist.split("=")[0];
		var v = optionlist.split("=")[1];
		var addr = optionlist[i].split("=")[2];
		var pid = optionlist[i].split("=")[3];
		var pname = optionlist[i].split("=")[4];
		var cid = optionlist[i].split("=")[5];
		var rType = optionlist[i].split("=")[6];
		if(addr==""||addr==null||addr=="null"){
			addr="";
		}
		if (t == resourceDefaultValue) {
			resourceItem.append("<option value='" + t + "' selected>" + v
					+ "</option>");
		} else {
			resourceItem.append("<option value='" + t + "'>" + v + "</option>");
		}
	} else {
		for (var i = 0; i < optionlist.length; i++) {
			var t = optionlist[i].split("=")[0];
			var v = optionlist[i].split("=")[1];
			var addr = optionlist[i].split("=")[2];
			var pid = optionlist[i].split("=")[3];
			var pname = optionlist[i].split("=")[4];
			var cid = optionlist[i].split("=")[5];
			var rType = optionlist[i].split("=")[6];
			if (t == resourceDefaultValue) {
				resourceItem.append("<option value='" + t + "' selected>" + v
						+ "</option>");
			} else {
				resourceItem.append("<option value='" + t + "'>" + v
						+ "</option>");
			}
		}
	}
}

// 是否紧急列转换
function InstancyFmatter(cellvalue, options, rowObject) {
	if (cellvalue === "1") {
		return '是';
	} else if (cellvalue === "2") {
		return '否';
	}
	return "";
}
// 状态操作列
function stateViewFormatter(cellValue,options,rowObject){
	var state=["处理中","回单审核","处理结束","已取消"];
	if(cellValue){
		var view=state[parseInt(cellValue)-1];
		return view;
	}
	return "";
}
// 故障查看操作列
function faultViewFmatter(cellvalue, options, rowObject) {
	var view = "  <a style='color: blue;text-decoration: underline;' href=javascript:viewFaultAlert('"
			+ cellvalue + "','" + rowObject.BUSINESS_TYPE + "')><img src='"+contextPath+"/css/image/view.png' title='查看' /></a>";
	return view;
}

// 故障查看操作列
function faultDispatchViewFmatter(cellvalue, options, rowObject) {
	var view = "  <a style='color: blue;text-decoration: underline;' href=javascript:viewFaultDispatch('"
			+ cellvalue + "','" + rowObject.BUSINESS_TYPE + "')><img src='"+contextPath+"/css/image/view.png' title='查看' /></a>";
	return view;
}
// 待取消工单操作列
function waitCanceledFmatter(cellvalue, options, rowObject) {
	var view = "  <a style='color: blue;text-decoration: underline;' href=javascript:viewFaultDispatch('"
			+ cellvalue + "','" + rowObject.BUSINESS_TYPE + "')><img src='"+contextPath+"/css/image/view.png' title='查看' /></a>";
	view += "  <a style='color: blue;text-decoration: underline;' href=javascript:cancelfault('"
			+ cellvalue + "','" + rowObject.BUSINESS_TYPE + "')><img src='"+contextPath+"/css/image/forbidden.png' title='取消' /></a>";
	return view;
}
// 待删除操作列转换
function waitdelFmatter(cellvalue, options, rowObject) {
	var view = "  <a style='color: blue;text-decoration: underline;' href=javascript:viewFaultDispatch('"
			+ cellvalue + "','" + rowObject.BUSINESS_TYPE + "')><img src='"+contextPath+"/css/image/view.png' title='查看' /></a>";
	view += "  <a style='color: blue;text-decoration: underline;' href=javascript:delfault('"
			+ cellvalue + "','" + rowObject.BUSINESS_TYPE + "')><img src='"+contextPath+"/css/image/delete.png' title='删除' /></a>";
	return view;
}
/**
 * 取消工单
 */
function cancelfault(id, businessType) {
	var url = contextPath
			+ '/workflow/faultDispatchCancelAction!cancel.action?parameter.id='
			+ id + '&businessType=' + businessType;
	var r = confirm("取消后流程将被终止  请确认是否取消该工单流程")
	if (r == true) {
		window.location.href = url;
	}
}
/**
 * 删除故障工单
 */
function delfault(id, businessType) {
	var url = contextPath
		+ "/workflow/faultDispatchAction!delete.action?parameter.origin=waitdeleted&parameter.id="
		+ id + '&businessType=' + businessType;
	var r = confirm("删除的数据不能够恢复   请确认是否删除")
	if (r) {
		window.location.href = url;
	}
}
// 查询
function query() {
	jQuery("#faultgrid").jqGrid().setGridParam({
		postData : {
			'parameter.isQuery' : jQuery("#isQuery").val(),
			'parameter.resourceName' : jQuery("#resourceName").val(),
			'parameter.troubleTitle' : jQuery("#troubleTitle").val(),
			'parameter.businessType' : jQuery("#businessType").val(),
			'parameter.troubleTimeStart' : jQuery("#troubleTimeStart").val(),
			'parameter.troubleTimeEnd' : jQuery("#troubleTimeEnd").val(),
			'parameter.findType' : jQuery('select[name="parameter.findType"]').find("option:selected").val(),
			'parameter.eomsId' : jQuery("#eomsid").val(),
			'parameter.stationId' : jQuery('#stationId').val(),
			'parameter.isInstancy' : jQuery('input[name="parameter.isInstancy"]:checked').val()
	}}).trigger("reloadGrid");
}
