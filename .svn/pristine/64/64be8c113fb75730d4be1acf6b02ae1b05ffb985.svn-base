/**
 * wmaintain_common.js
 * 
 * @creator 杨隽 2012-04-16 创建
 * @used for wmaintain's all jsp files
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
 * 设置维修作业计划的专业类型
 * 
 * @param type
 */
function setBusinessType(type) {
	businessType = type;
}
/**
 * 维修作业计划查看信息
 * 
 * @param id
 */
function view(id) {
	location = contextPath
			+ "/workflow/wmaintainCreatePlanAction!view.action?id=" + id;
}
/**
 * 进入修改维修作业计划页面
 * 
 * @param id
 */
function edit(id) {
	location = contextPath
			+ "/workflow/wmaintainCreatePlanAction!input.action?id=" + id
			+ "&businessType=" + businessType;
}
/**
 * 进行删除维修作业计划操作
 * 
 * @param id
 * @param state
 */
function del(id, state) {
	var url = contextPath
			+ "/workflow/wmaintainCreatePlanAction!delete.action?id=" + id
			+ '&businessType=' + businessType + "&state=" + state;
	var r = confirm("删除的数据不能够恢复   请确认是否删除");
	if (!!r) {
		location.href = url;
	}
}
/**
 * 进行取消维修作业计划操作
 * 
 * @param id
 */
function cancelobject(id) {
	var url = contextPath + '/workflow/wmaintainCancelAction!cancel.action?id='
			+ id + '&businessType=' + businessType;
	var r = confirm("取消后流程将被终止  请确认是否取消该隐患维修作业计划流程")
	if (r == true) {
		window.location.href = url;
	}
}
/**
 * 进行维修作业计划的待办处理操作
 * 
 * @param url
 */
function doTask(url) {
	location = contextPath + url + "&businessType=" + businessType;
}
/**
 * 维修作业计划状态值转换
 * 
 * @param cellvalue
 * @param options
 * @param rowObjec
 * @returns {String}
 */
function convertPlanStateFormatter(cellvalue, options, rowObjec) {
	var view = "";
	var stateValue = [ "暂存", "待审核", "计划退回", "处理问题", "报告审核", "报告退回", "归档", "已取消" ];
	view = stateValue[parseInt(cellvalue) - 1];
	return view;
}
/**
 * 维修作业计划草稿箱操作列转换
 * 
 * @param cellvalue
 * @param options
 * @param rowObjec
 * @returns {String}
 */
function draftOperateionActionFormatter(cellvalue, options, rowObjec) {
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:view('"
			+ cellvalue + "')><img src='"+contextPath+"/css/image/view.png' title='查看' /></a>";
	view = view
			+ "  <a style='color: blue;text-decoration: underline;' href=javascript:edit('"
			+ cellvalue + "')><img src='"+contextPath+"/css/image/edit.png' title='编辑' /></a>";
	view = view
			+ "  <a style='color: blue;text-decoration: underline;' href=javascript:del('"
			+ cellvalue + "','1')><img src='"+contextPath+"/css/image/delete.png' title='删除' /></a>";
	return view;
}
/**
 * 待办工作操作列转换
 * 
 * @param cellvalue
 * @param options
 * @param rowObjec
 * @returns {String}
 */
function waitHandledActionFormatter(cellvalue, options, rowObjec) {
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:view('"
			+ cellvalue + "')><img src='"+contextPath+"/css/image/view.png' title='查看' /></a>";
	view = view
			+ "  <a style='color: blue;text-decoration: underline;' href=javascript:doTask('"
			+ rowObjec.PROCESS_URL + "')><img src='"+contextPath+"/css/image/process.png' title='处理' /></a>";
	return view;
}
/**
 * 已办工作操作列转换
 * 
 * @param cellvalue
 * @param options
 * @param rowObjec
 * @returns {String}
 */
function handledActionFormatter(cellvalue, options, rowObjec) {
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:view('"
			+ cellvalue + "')><img src='"+contextPath+"/css/image/view.png' title='查看' /></a>";
	return view;
}
/**
 * 待取消操作列转换
 * 
 * @param cellvalue
 * @param options
 * @param rowObjec
 * @returns {String}
 */
function waitCanceledActionFormatter(cellvalue, options, rowObjec) {
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:view('"
			+ cellvalue + "')><img src='"+contextPath+"/css/image/view.png' title='查看' /></a>";
	view = view
			+ "  <a style='color: blue;text-decoration: underline;' href=javascript:cancelobject('"
			+ cellvalue + "')><img src='"+contextPath+"/css/image/forbidden.png' title='取消' /></a>";
	return view;
}
/**
 * 待删除操作列转换
 * 
 * @param cellvalue
 * @param options
 * @param rowObjec
 * @returns {String}
 */
function waitDeletedOperateionActionFormatter(cellvalue, options, rowObjec) {
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:view('"
			+ cellvalue + "')><img src='"+contextPath+"/css/image/view.png' title='查看' /></a>";
	view = view
			+ "  <a style='color: blue;text-decoration: underline;' href=javascript:del('"
			+ cellvalue + "','8')><img src='"+contextPath+"/css/image/delete.png' title='删除' /></a>";
	return view;
}
/**
 * 获取站点选择树
 * 
 * @returns {String}
 */
function getplansitetree(jsonObj) {
	jQuery
			.ajax({
				url : contextPath
						+ '/workflow/wmaintainPlanResourceAction!resourceProblemesList.action',
				dataType : "json",
				data : {
					'plan.businessType' : jQuery("#wmaintain_businessType")
							.val(),
					'plan.patrolGroup' : jQuery("#wmaintain_patrolGroup").val(),
					'plan.id' : jQuery("#wmaintain_id").val(),
					'plan.wplanId' : jQuery("#wmaintain_wplanId").val(),
					'plan.createDateMin' : jQuery("#wmaintain_createDateMin")
							.val(),
					'plan.createDateMax' : jQuery("#wmaintain_createDateMax")
							.val()
				},
				type : 'GET',
				cache : true,
				async : false,
				success : function(result) {
					if (!!result) {
						var ztree = jQuery.fn.zTree.init(
								jQuery("#plansitetree"), setting, result);
						ztree.checkAllNodes(false);
						var patrolResourceJSONStr = jsonObj;
						if (patrolResourceJSONStr) {
							var patrolResourceJSON = jQuery
									.parseJSON(patrolResourceJSONStr);
							for ( var i = 0; i < patrolResourceJSON.length; i++) {
								var node = ztree.getNodeByParam("RS_ID",
										patrolResourceJSON[i].RESOURCE_ID);
								ztree.checkNode(node, true, true, false);
							}
						}
					} else {
						alert("没有查询到问题资源！");
						var ztree = jQuery.fn.zTree.getZTreeObj("plansitetree");
						if(ztree){
							ztree.destroy();
						}
					}

				},
				error : function() {
					alert("获取问题资源失败！");
				}
			});
}
/**
 * 列表查询方法
 */
function query() {
	jQuery("#wmaintaingrid").jqGrid().setGridParam({
		postData : {
			planName : jQuery("#wmaintain_planName").val(),
			businessType : jQuery("#businessType").val(),
			creater : jQuery("#wmaintain_creater").val(),
			regionId : jQuery("#wmaintain_regionid").val(),
			createDateMin : jQuery("#wmaintain_createDateMin").val(),
			createDateMax : jQuery("#wmaintain_createDateMax").val()
		}
	}).trigger("reloadGrid");
}
/**
 * 搜索人员数
 */
function search() {
	var val = window.showModalDialog(contextPath
			+ '/commonaccess!getstaff.action', '',
			'status:no;center:yes;dialogWidth:400px;dialogHeight:300px;');
	var userName = "";
	var userId = "";
	if (val) {
		for (i = 0; i < val.length; i++) {
			userName += val[i].NAME + ",";
			userId += val[i].ID + ",";
		}
		userId = userId.substring(0, userId.length - 1);
		userName = userName.substring(0, userName.length - 1);
		jQuery("#wmaintain_creater_name").val(userName);
		jQuery("#wmaintain_creater").val(userId);
	}
}
/**
 * 搜索区域树
 * 
 * @param url
 */
function searchRegion(url) {
	var val = showRegion(url);
	if (!!val) {
		jQuery("#regionid").val(val[0]);
		jQuery("#wmaintain_regionid").val(val[0]);
		jQuery("#regionname").val(val[1]);
	}
}
