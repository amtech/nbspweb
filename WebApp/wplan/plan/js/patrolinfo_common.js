/**
 * patrolinfo_common.js
 * 
 * @creator zhaobi 2011-02-13
 * @used 计划信息共用JS
 */
var contextPath;
var userType;
/**
 * set context path
 */
setContextPath = function(path) {
	contextPath = path;
}
function setUserType(type){
	userType=type;
}

// 查看计划明细
function view(id) {
	location = contextPath + "/wplan/patrolinfoAction!view.action?id=" + id;
}
// 编辑计划明细
function edit(id) {
	location = contextPath + "/wplan/patrolinfoAction!input.action?id=" + id;
}
//复制计划明细
function copy(id) {
	location = contextPath + "/wplan/patrolinfoAction!input.action?id=" + id
			+ "&opt=copy";
}
// 删除计划明细
function del(id, businessType) {
	var con = confirm("确定要删除该条记录?");
	if (!!con) {
		location = contextPath + "/wplan/patrolinfoAction!delete.action?id="
				+ id + "&type=" + businessType;
	}

}
/**
 * 审批
 */
function audit(id, businessType, taskid) {
	location = contextPath
			+ "/wplan/patrolinfoApproveAction!input.action?planid=" + id
			+ "&type=" + businessType + "&taskid=" + taskid;
}
// 未巡检数明细
function loadLostPatrolDetail(id) {
	location = contextPath
			+ "/wplan/patrolinfoExecuteAction!lostdetail.action?id=" + id;
}
// 已巡检数明细
function loadOverPatrolDetail(id) {
	location = contextPath
			+ "/wplan/patrolinfoExecuteAction!overdetail.action?id=" + id;
}
// 异常巡检明细
function loadExceptionPatrolDetail(id) {
	location = contextPath
			+ "/wplan/patrolinfoExecuteAction!losedetail.action?id=" + id;
}
// 已巡检数RFID明细
function loadRFIDDetail(id) {
	location = contextPath
			+ "/wplan/patrolinfoExecuteAction!rfiddetail.action?id=" + id;
}
// 异常项巡检明细
function loadPatrolItemDetail(id) {
	location = contextPath
			+ "/wplan/patrolinfoExecuteAction!patrolitemdetail.action?id=" + id;
}
// 计划类型转换
function PlanTypeFmatter(cellvalue, options, rowObject) {
	if (cellvalue === "1") {
		return '年度';
	} else if (cellvalue === "2") {
		return '季度';
	} else if (cellvalue === "3") {
		return '月度';
	} else {
		return '自定义';
	}
}
// 计划状态转换
function PlanStateFmatter(cellvalue, options, rowObject) {
	if (cellvalue === "02") {
		return '待审核';
	} else if (cellvalue === "03") {
		return '已审核';
	} else if (cellvalue === "04") {
		return '未通过';
	} else {
		return '未提交';
	}
}
// 计划操作列转换
function ActionFmatter(cellvalue, options, rowObjec) {
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:view('"
			+ cellvalue + "')><img src='"+contextPath+"/css/image/view.png' title='查看' /></a>";
	if(userType=='2'){
	view = view
			+ " <a style='color: blue;text-decoration: underline;' href=javascript:copy('"
			+ cellvalue + "')><img src='"+contextPath+"/css/image/copy.png' title='复制' /></a>";
	if (rowObjec.PLAN_STATE === "01" || rowObjec.PLAN_STATE === "04") {
		view = view
				+ "  <a style='color: blue;text-decoration: underline;' href=javascript:edit('"
				+ cellvalue + "')><img src='"+contextPath+"/css/image/edit.png' title='编辑' /></a>";
		view = view
				+ "  <a style='color: blue;text-decoration: underline;' href=javascript:del('"
				+ cellvalue + "','" + rowObjec.BUSINESS_TYPE + "')><img src='"+contextPath+"/css/image/delete.png' title='删除' /></a>";
	} else if (rowObjec.PLAN_STATE === "04") {
		view = view
				+ "  <a style='color: blue;text-decoration: underline;' href=javascript:del('"
				+ cellvalue + "','" + rowObjec.BUSINESS_TYPE + "')><img src='"+contextPath+"/css/image/delete.png' title='删除' /></a>";
	}
	}
	return view;
}
// 审批操作列转换
function AuditActionFmatter(cellvalue, options, rowObjec) {
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:view('"
			+ cellvalue + "')><img src='"+contextPath+"/css/image/view.png' title='查看' /></a>";
	if (rowObjec.PLAN_STATE === "02" || rowObjec.PLAN_STATE === "04") {
		view = view
				+ "  <a style='color: blue;text-decoration: underline;' href=javascript:audit('"
				+ cellvalue + "','" + rowObjec.BUSINESS_TYPE + "','"
				+ rowObjec.TASKID + "')><img src='"+contextPath+"/css/image/process.png' title='处理' /></a>";
	}
	return view;
}
// 计划名称列转换
function PlanNameFmatter(cellvalue, options, rowObjec) {
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:view('"
			+ rowObjec.ID + "')>" + cellvalue + "</a>";
	return view;
}
// 异常巡检数列转换
function ExceptionPatrolFmatter(cellvalue, options, rowObjec) {
	return cellvalue;
}
// 已巡检数列转换
function OverPatrolFmatter(cellvalue, options, rowObjec) {
	if (cellvalue === "0" || options.rowId === "") {
		return cellvalue;
	} else {
		var view = "<a style='color: blue;text-decoration: underline;' href=javascript:loadOverPatrolDetail('"
				+ rowObjec.ID + "')>" + cellvalue + "</a>";
		return view;
	}
}
// 未巡检数列转换
function LosePatrolFmatter(cellvalue, options, rowObjec) {
	if (cellvalue === "0" || options.rowId === "") {
		return cellvalue;
	} else {
		var view = "<a style='color: blue;text-decoration: underline;' href=javascript:loadLostPatrolDetail('"
				+ rowObjec.ID + "')>" + cellvalue + "</a>";
		return view;
	}
}
// RFID列转换
function RFIDFmatter(cellvalue, options, rowObjec) {
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:loadRFIDDetail('"
			+ cellvalue + "')><img src='"+contextPath+"/css/image/view.png' title='查看RFID' /></a>";
	return view;
}
// 巡检表异常项格式转换
function ItemExceptionFmatter(cellvalue, options, rowObjec) {
	if (cellvalue == "0") {
		return "正常";
	} else {
		return "<div style='background-color:red'>异常</div>";
	}
}
// 巡检项列转换
function PatrolItemFmatter(cellvalue, options, rowObjec) {
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:loadPatrolItemDetail('"
			+ cellvalue + "')><img src='"+contextPath+"/css/image/view.png' title='查看巡检表' /></a>";
	return view;
}
// 巡检时长格式化
function TimeDiffFmatter(cellvalue, options, rowObjec) {
	// 如果结束时间不为空
	if (!!rowObjec.END_TIME) {
		return timeDiff(rowObjec.END_TIME, rowObjec.START_TIME);
	}
	return "";
}
// 搜索区域
function searchRegion(url) {
	var val = showRegion(url);
	if (!!val) {
		jQuery("#regionid").val(val[0]);
		jQuery("#regionname").val(val[1]);
	}
}
// 搜索代维
function searchPatrolGroup(url) {
	var val = showPatrolGroup(url);
	if (!!val) {
		jQuery("#patrolgroupid").val(val[0]);
		jQuery("#patrolgroupname").val(val[1]);
	}
}
// 搜索巡检组
function searchOrg(url) {
	var val = showOrg(url);
	if (!!val) {
		jQuery("#orgid").val(val[0]);
		jQuery("#orgname").val(val[1]);
	}
}

// 获取月份最后一天
function getLastDate(y, m) {
	var startdate = y + "-" + m + "-1";
	// 计算下个月的年份(y)、月份值(m)
	if (m == 12) {
		y++;
		m = 1;
	} else {
		m++;
	}
	// 生成下个月1日的Date值
	var dt = new Date(y, m - 1, 1); // 月份值0--11
	// 一天差值=86400000，将下月1日转换成数值，再相减，得上月最后一天Date值
	var n = Date.parse(dt);
	n -= 86400000;
	var dt1 = new Date(n);
	// 输出当月最后一天日期字符串
	return dt1.getDate();
}