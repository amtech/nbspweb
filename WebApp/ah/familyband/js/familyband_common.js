/**
 * familyband_common.js
 * 
 * @creator zhaobi 2011-02-13
 * @used 计划信息共用JS
 */
var contextPath;
/**
 * set context path
 */
setContextPath = function(path) {
	contextPath = path;
}
//家庭隐患转换
function familytroubleFmatter(cellvalue, options, rowObjec){
	var view=""
	if(rowObjec.UNHANDLETROUBLENUM!==0){
	view = "<a style='color: blue;text-decoration: underline;' href=javascript:troubleprocesspage('"
			+ cellvalue + "')>处理</a>";
	}
	return view;
	
}
//转到隐患处理页面
function troubleprocesspage(id){
	location = contextPath + "/ah/ahFamilyBandTroubleAction!toDealCodeTrouble.action?id=" + id;
}
//隐患处理格式化
function troubleprocessFmatter(cellvalue, options, rowObjec){
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:processtrouble('"
			+ cellvalue + "')>处理</a>";
	
	return view;
}
//隐患处理
function processtrouble(id){
		location = contextPath + "/ah/ahFamilyBandTroubleAction!toUpdateTrouble.action?id=" + id;
}
//隐患查看
function troubleviewFmatter(cellvalue, options, rowObjec){
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:viewtrouble('"
			+ cellvalue + "')>查看</a>";
	
	return view;
}
//隐患状态格式化
function troubleStatusFmatter(cellvalue, options, rowObjec){
	var view = "已处理";
	if('0'==cellvalue){
		view = "未处理";
	}
	return view;
}
function viewtrouble(id){
	location = contextPath + "/ah/ahFamilyBandTroubleAction!viewTroubleDetail.action?id=" + id;
}

//跳转到宽带巡检记录修改页面
function edit(obj){
		window.location.href=contextPath+"/ah/ahFamilyBandRecodeAction!toUpdate.action?id="+obj;
}

//跳转到巡检宽带查看页面
function see(obj){
		window.location.href=contextPath+"/ah/ahFamilyBandRecodeAction!toSee.action?id="+obj;
}
