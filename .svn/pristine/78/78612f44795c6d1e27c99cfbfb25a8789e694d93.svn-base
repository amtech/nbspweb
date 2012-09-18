var contextPath;
var pageNo;
var userType;
function setContextPath(path) {
	contextPath = path;
}
function setPageNo(pageNumber) {
	pageNo = pageNumber;
}
function setUserType(type){
	userType=type;
}
function del(id, businessType) {
	var con = confirm("确定要删除该条记录?");
	if (!!con) {
	window.location.href = contextPath
			+ '/wplan/patrolItemAction!deleteLogic.action?pageNo=' + pageNo
			+ '&parameter.businessType=' + businessType + '&parameter.id=' + id;
	}
}
function startUsing(id, businessType) {
	var con = confirm("确定要启用该条记录?");
	if (!!con) {
	window.location.href = contextPath
			+ '/wplan/patrolItemAction!startUsing.action?pageNo=' + pageNo
			+ '&parameter.businessType=' + businessType + '&parameter.id=' + id;
	}
}
function checkAll() {
	var allbox = document.getElementById("allbox").checked;
	var listbox = document.getElementsByName("listbox");
	for (var i = 0; i < listbox.length; i++) {
		listbox[i].checked = allbox;
	}
}
function checkList() {
	var allbox = document.getElementById("allbox");
	var listbox = document.getElementsByName("listbox");
	var checked = true;
	for (var i = 0; i < listbox.length; i++) {
		if (!listbox[i].checked) {
			checked = false;
			break;
		}
	}
	allbox.checked = checked;
}
//批量作废
function deleteLogicSum(businessType) {
	var s=""; 
	s = jQuery("#itemgrid").jqGrid('getGridParam','selarrrow');
	if (s.length==0) {
		alert('请至少选择一条记录！');
		return false;
	}
	var con = confirm("确定要批量作废这些记录吗?");
	if (!!con) {
	window.location.href = contextPath
			+ "/wplan/patrolItemAction!deleteLogic.action?pageNo=" + pageNo
			+ "&parameter.id=" + s + "&parameter.businessType="
			+ businessType;
	}
}
//批量启用
function startUsingSum(businessType) {
	var s=""; 
	s = jQuery("#itemgrid").jqGrid('getGridParam','selarrrow');
	if (s.length==0) {
		alert('请至少选择一条记录！');
		return false;
	}
	var con = confirm("确定要批量启用这些记录吗?");
	if (!!con) {
	window.location.href = contextPath
			+ "/wplan/patrolItemAction!startUsing.action?pageNo=" + pageNo
			+ "&parameter.id=" + s + "&parameter.businessType="
			+ businessType;
	}
}
//批量删除,暂时不用
function deletePhysicsSum(tablename, type) {
	var listbox = document.getElementsByName("listbox");
	var checkedNum = 0;
	var idValue = '';
	for (var i = 0; i < listbox.length; i++) {
		if (listbox[i].checked) {
			checkedNum = checkedNum + 1;
			idValue = idValue + listbox[i].value + ','
		}
	}
	if (checkedNum == 0) {
		alert('请至少选择一条记录！');
		return false;
	}
	var con;
	con = confirm("确定要删除选中项吗?物理删除可能会导致部分数据的统计无法查询到");
}
function toggleShowAll(obj) {
	if (obj.checked) {
		jQuery("#showAll").val("1");
	} else {
		jQuery("#showAll").val("0");
	}
}

//周期格式转换
function CycleFmatter(cellvalue, options, rowObject) {
	if (cellvalue === "year") {
		return '年';
	} else if (cellvalue === "quarter") {
		return '季';
	} else if (cellvalue === "month") {
		return '月';
	} else if (cellvalue === "week") {
		return '周';
	} else {
		return '自定义';
	}
}
//输入类型格式转换
function InputFmatter(cellvalue, options, rowObject) {
	if (cellvalue === "NUM") {
		return '数值';
	} else if (cellvalue === "TEXT") {
		return '文本';
	} else if (cellvalue === "CHOOSE") {
		return '单选';
	} else if (cellvalue === "GROUP") {
		return '多选';
	} else if (cellvalue === "BATTERY") {
		return '蓄电池测试';
	} else if (cellvalue === "STATION_ANTENNA") {
		return '天线测量';
	} else if (cellvalue === "INDOOR_COVERAGE") {
		return '天线信号测试';
	} else if (cellvalue === "WLAN") {
		return 'WLAN天线信号测试';
	} else {
		return '未定义';
	}
}
//巡检项操作列转换
function ItemActionFmatter(cellvalue, options, rowObject) {
	var view="";
	if(userType=="1"){
	if (rowObject.IS_FORBIDDEN_STATE === "N") {
		view =  "  <a style='color: blue;text-decoration: underline;' href=javascript:del('"
				+ cellvalue + "','" + rowObject.BUSINESS_TYPE + "')><img src='"+contextPath+"/css/image/forbidden.png' title='作废' /></a>";
	}
	else if(rowObject.IS_FORBIDDEN_STATE === "Y") {
		view = "  <a style='color: blue;text-decoration: underline;' href=javascript:startUsing('"
				+ cellvalue + "','" + rowObject.BUSINESS_TYPE + "')><img src='"+contextPath+"/css/image/startusing.png' title='启用' /></a>";
	}
	}
	return view;
}