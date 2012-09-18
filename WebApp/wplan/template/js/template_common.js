var userType;
function setUserType(type){
	userType=type;
}
//查看
function view(id) {
	window.location.href = contextPath+'/wplan/wplanTemplateAction!viewALL.action?id='
			+ id;
}
//编辑
function edit(id) {
	window.location.href = contextPath+'/wplan/wplanTemplateAction!view.action?flag=copy&id='
			+ id;
}
//删除
function del(id) {
	//if (confirm("删除将不能恢复，请确认是否要删除该信息，按‘确定’删除？")) {
	window.location.href = contextPath+'/wplan/wplanTemplateAction!delete.action?id='
			+ id;
	//}
}
//启用
function startUsing(id) {
	//if (confirm("删除将不能恢复，请确认是否要删除该信息，按‘确定’删除？")) {
	window.location.href = contextPath+'/wplan/wplanTemplateAction!startUsing.action?id='
			+ id;
	//}
}
// 计划模板操作列转换
function TemplateActionFmatter(cellvalue, options, rowObjec) {
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:view('"
			+ cellvalue + "')><img src='"+contextPath+"/css/image/view.png' title='查看' /></a>";
	if(userType=="1"){
	view = view
				+ "  <a style='color: blue;text-decoration: underline;' href=javascript:edit('"
				+ cellvalue + "')><img src='"+contextPath+"/css/image/copy.png' title='复制' /></a>";		
	if (rowObjec.IS_FORBIDDEN_STATE === "N"){
		view = view
				+ "  <a style='color: blue;text-decoration: underline;' href=javascript:del('"
				+ cellvalue + "')><img src='"+contextPath+"/css/image/forbidden.png' title='停用' /></a>";
	}else if(rowObjec.IS_FORBIDDEN_STATE === "Y"){
			view = view
				+ "  <a style='color: blue;text-decoration: underline;' href=javascript:startUsing('"
				+ cellvalue + "')><img src='"+contextPath+"/css/image/startusing.png' title='启用' /></a>";
	}
	}
	return view;
}