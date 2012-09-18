/**
 * fault_dispatch_input.js
 * 
 * @creator yangjun 2011-10-31
 * @used for fault_dispatch_input.jsp
 */
var resourceDefaultValue;
var resourceDefaultValueType;
var patrolgroupcombotree;

/**
 * selected patrolgroup event
 */
function search() {
	var val = window.showModalDialog(contextPath
			+ '/commonaccess!getpatrolgroup.action', '',
			'status:no;center:yes;dialogWidth:400px;dialogHeight:300px;');
	var userName = "";
	var userId = "";
	var orgId = "";
	if (val) {
		for (i = 0; i < val.length; i++) {
			userName += val[i].NAME + ",";
			userId += val[i].ID + ",";
			orgId += val[i].ORGID + ",";
		}
		userId = userId.substring(0, userId.length - 1);
		userName = userName.substring(0, userName.length - 1);
		orgId = orgId.substring(0, orgId.length - 1);
		jQuery("#patrolGroupName").val(userName);
		jQuery("#patrolGroupId").val(userId);
		jQuery("#maintenanceId").val(orgId);
	}
}
/**
 * 方法废弃于2012-07-12 杨隽
 */
function setStationInfo() {
	var station = jQuery("#faultAlert_stationId");
	var address = station.find("option:selected").attr("addr");
	var patrolmanId = station.find("option:selected").attr("pid");
	var patrolmanName = station.find("option:selected").attr("pname");
	var contractorId = station.find("option:selected").attr("cid");
	var rType = station.find("option:selected").attr("rtype");
	if (address == null || address == "null") {
		address = "";
	}
	if (patrolmanId == null || patrolmanId == "null") {
		patrolmanId = "";
		patrolmanName = "";
		contractorId = "";
	}
	jQuery("#address").val(address);
	jQuery('#patrolGroupName').val(patrolmanName);
	jQuery('#maintenanceId').val(contractorId);
	jQuery('#patrolGroupId').val(patrolmanId);
	jQuery('#stationType').val(rType);
}
