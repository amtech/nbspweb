/**
 * workorder_common.js
 * 
 * @creator yangjun 2012-01-09
 * @used for workorder's all jsp files
 */
var contextPath;
/**
 * set context path
 */
function setContextPath(path) {
	contextPath = path;
}
function viewAll(id, pId) {
	location = contextPath
			+ "/workflow/workorderDispatchAction!viewAll.action?id=" + id
			+ "&pId=" + pId;
}
function showProcessHistory(processInstId) {
	var actionUrl = contextPath
			+ "/localeProcessAction!showProcessHistoryList.action?localeProcess.taskId="
			+ processInstId + "&localeProcess.taskType=WTASK_TRANSFER&rnd="
			+ Math.random();
	jQuery("#processHistoryTd").load(actionUrl);
};
function showProcessPhotos(processInstId) {
	var actionUrl = contextPath
			+ "/localeProcessAction!showProcessPhotos.action?localeProcess.taskId="
			+ processInstId + "&localeProcess.taskType=WTASK_TRANSFER&rnd="
			+ Math.random();
	jQuery("#processPhotosTd").load(actionUrl);
};
function showTotalTimeLength(id, pId) {
	var actionUrl = contextPath
			+ "/workflow/workOrderCalculateTimeAction!totalCalucate.action?id="
			+ id + "&pId=" + pId + "&rnd=" + Math.random();
	jQuery("#totalTimeLengthTd").load(actionUrl);
};
function showEveryStepTimeLength(id, pId) {
	var actionUrl = contextPath
			+ "/workflow/workOrderCalculateTimeAction!everyStepCalculate.action?id="
			+ id + "&pId=" + pId + "&rnd=" + Math.random();
	jQuery("#everyStepTimeLengthTd").load(actionUrl);
}
function showWorkOrderDispatch(workOrderId) {
	var actionUrl = contextPath
			+ "/workflow/workorderDispatchAction!dispatchList.action?id="
			+ workOrderId + "&&rnd=" + Math.random();
	var showTd = "#workOrderDispatchTd";
	jQuery(showTd).load(actionUrl);
}
function showSignForHistory(processInstId) {
	var actionUrl = contextPath
			+ "/workflow/workorderSignForAction!list.action?id="
			+ processInstId + "&&rnd=" + Math.random();
	var showTd = "#signForHistoryTd";
	jQuery(showTd).load(actionUrl);
}
function showRefuseConfirmHistory(processInstId) {
	var actionUrl = contextPath
			+ "/workflow/workorderRefuseConfirmAction!list.action?id="
			+ processInstId + "&&rnd=" + Math.random();
	var showTd = "#refuseConfirmHistoryTd";
	jQuery(showTd).load(actionUrl);
}
function showReplyHistory(processInstId) {
	var actionUrl = contextPath
			+ "/workflow/workorderReplyAction!list.action?id=" + processInstId
			+ "&&rnd=" + Math.random();
	var showTd = "#replyHistoryTd";
	jQuery(showTd).load(actionUrl);
}
function showReplyCheckHistory(processInstId) {
	var actionUrl = contextPath
			+ "/workflow/workorderReplyCheckAction!list.action?id="
			+ processInstId + "&&rnd=" + Math.random();
	var showTd = "#replyCheckHistoryTd";
	jQuery(showTd).load(actionUrl);
}
// 查看
function view(id) {
	location = contextPath
			+ "/workflow/workorderDispatchAction!view.action?id=" + id;
}
// 编辑
function edit(id) {
	location = contextPath
			+ "/workflow/workorderDispatchAction!input.action?id=" + id;
}
// 删除
function del(id) {
	var url = contextPath
			+ "/workflow/workorderDispatchAction!delete.action?id=" + id;
	var r = confirm("删除的数据不能够恢复   请确认是否删除");
	if (!!r) {
		location.href = url;
	}
}
/**
 * 删除
 */
function deleteobject(workOrderId, transferId) {
	var url = contextPath
			+ '/workflow/workOrderWaitDeletedAction!delete.action?workOrderId='
			+ workOrderId + '&transferId=' + transferId;
	var r = confirm("删除的数据不能够恢复   请确认是否删除");
	if (r == true) {
		window.location.href = url;
	}
}
/**
 * 取消
 */
function cancelobject(id) {
	var url = contextPath
			+ '/workflow/workOrderCancelAction!cancel.action?workOrderId=' + id;
	var r = confirm("取消后流程将被终止  请确认是否取消该工单流程")
	if (r == true) {
		window.location.href = url;
	}
}
// 处理
function doTask(id, url) {
	location = contextPath + url + id;
}
// 派发
function transferDispatch(id) {
	location = contextPath
			+ "/workflow/workorderDispatchAction!transferInput.action?id=" + id;
}
// 工单操作列转换
function OrderActionFmatter(cellvalue, options, rowObjec) {
	var view = '<a href="#" style="color: blue;text-decoration: underline;" onclick="view(\''
			+ cellvalue + '\')"><img src="'+contextPath+'/css/image/view.png" title="查看" /></a>';
	view += ' <a href="#" style="color: blue;text-decoration: underline;" onclick="edit(\''
			+ cellvalue + '\')"><img src="'+contextPath+'/css/image/edit.png" title="编辑" /></a>';
	view += ' <a href="#" style="color: blue;text-decoration: underline;" onclick="del(\''
			+ cellvalue + '\')"><img src="'+contextPath+'/css/image/delete.png" title="删除" /></a>';
	return view;
}
// 已办工作操作列转换
function HanledActionFmatter(cellvalue, options, rowObjec) {
	var view = '<a href="#" style="color: blue;text-decoration: underline;" onclick="viewAll(\''
			+ cellvalue + '\',\'' + rowObjec.PID + '\')"><img src="'+contextPath+'/css/image/view.png" title="查看" /></a>';
	return view;
}
// 待办工作操作列转换
function WaitHandworkorderFmatter(cellvalue, options, rowObjec) {
	var view = '<a href="#" style="color: blue;text-decoration: underline;" onclick="viewAll(\''
			+ cellvalue + '\',\'' + rowObjec.PID + '\')"><img src="'+contextPath+'/css/image/view.png" title="查看" /></a>';
	view += ' <a href="#" style="color: blue;text-decoration: underline;" onclick="doTask(\''
			+ cellvalue + '\',\'' + rowObjec.PROCESS_URL + '\')"><img src="'+contextPath+'/css/image/process.png" title="处理" /></a>';
	view += ' <a href="#" style="color: blue;text-decoration: underline;" onclick="transferDispatch(\''
			+ cellvalue + '\')"><img src="'+contextPath+'/css/image/dispatch.png" title="转派" /></a>';
	return view;
}
// 待取消操作列转换
function WaitCanceledActionFmatter(cellvalue, options, rowObjec) {
	var view = '<a href="#" style="color: blue;text-decoration: underline;" onclick="viewAll(\''
			+ cellvalue + '\',\'' + rowObjec.PID + '\')"><img src="'+contextPath+'/css/image/view.png" title="查看" /></a>';
	view += ' <a href="#" style="color: blue;text-decoration: underline;" onclick="cancelobject(\''
			+ rowObjec.PID + '\')"><img src="'+contextPath+'/css/image/forbidden.png" title="取消" /></a>';
	return view;

}
// 待删除操作列转换
function WaitDeleteOrderFmatter(cellvalue, options, rowObjec) {
	var view = '<a href="#" style="color: blue;text-decoration: underline;" onclick="view(\''
			+ cellvalue + '\',\'' + rowObjec.PID + '\')"><img src="'+contextPath+'/css/image/view.png" title="查看" /></a>';
	view += ' <a href="#" style="color: blue;text-decoration: underline;" onclick="deleteobject(\''
			+ cellvalue + '\',\'' + rowObjec.PID + '\')"><img src="'+contextPath+'/css/image/delete.png" title="删除" /></a>';
	return view;

}
// 工单状态列转换
function orderTaskStateFmatter(cellvalue, options, rowObjec) {
	if (cellvalue === "0") {
		return '草稿';
	} else if (cellvalue === "2") {
		return '签收';
	} else if (cellvalue === "3") {
		return '回单';
	} else if (cellvalue === "4") {
		return '验证审核';
	} else if (cellvalue === "5") {
		return '结束';
	} else if (cellvalue === "6") {
		return '已取消 ';
	} else if (cellvalue === "a") {
		return '退单';
	} else if (cellvalue === "b") {
		return '退单审核';
	}
	return '';
}