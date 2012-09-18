var contextPath;
/**
 * set context path
 */
setContextPath = function(path) {
	contextPath = path;
}
/**
 * 刷新页面
 */
function breakPage() {
	window.location.href = window.location.href;
	window.location.reload;
}
// 弹出区域选择窗口
function showRegion(url) {
	var val = window.showModalDialog(url, '',
			'status:no;center:yes;dialogWidth:400px;dialogHeight:300px;');
	var regionname = "";
	var regionid = "";
	var newval
	if (!!val) {
		for (var i = 0; i < val.length; i++) {
			regionname += val[i].REGIONNAME + ",";
			regionid += val[i].REGIONID + ",";
		}
		newval = new Array(2);
		regionid = regionid.substring(0, regionid.length - 1);
		regionname = regionname.substring(0, regionname.length - 1);
		newval[0] = regionid;
		newval[1] = regionname;
	}
	return newval;
}
// 弹出巡检组窗口
function showPatrolGroup(url) {
	var val = window.showModalDialog(url, '',
			'status:no;center:yes;dialogWidth:400px;dialogHeight:300px;');
	var groupName = "";
	var groupId = "";
	var newval;
	if (!!val) {
		for (i = 0; i < val.length; i++) {
			groupName += val[i].NAME + ",";
			groupId += val[i].ID + ",";
		}
		groupId = groupId.substring(0, groupId.length - 1);
		groupName = groupName.substring(0, groupName.length - 1);
		newval = new Array(2);
		newval[0] = groupId;
		newval[1] = groupName;
	}
	return newval;
}
// 弹出组织人员窗口
function showOrgPerson(url) {
	var val = window.showModalDialog(url, '',
			'status:no;center:yes;dialogWidth:400px;dialogHeight:300px;');
	var userName = "";
	var userId = "";
	var newval;
	if (!!val) {
		for (i = 0; i < val.length; i++) {
			userName += val[i].NAME + ",";
			userId += val[i].ID + ",";
		}
		userId = userId.substring(0, userId.length - 1);
		userName = userName.substring(0, userName.length - 1);
		newval = new Array(2);
		newval[0] = userId;
		newval[1] = userName;
	}
	return newval;
}
// 弹出组织窗口
function showOrg(url) {
	var val = window.showModalDialog(url, '',
			'status:no;center:yes;dialogWidth:400px;dialogHeight:300px;');
	var userName = "";
	var userId = "";
	var newval;
	if (!!val) {
		for (i = 0; i < val.length; i++) {
			userName += val[i].NAME + ",";
			userId += val[i].ID + ",";
		}
		userId = userId.substring(0, userId.length - 1);
		userName = userName.substring(0, userName.length - 1);
		newval = new Array(2);
		newval[0] = userId;
		newval[1] = userName;
	}
	return newval;
}
function showresource(url) {
	var val = window.showModalDialog(url, '',
			'status:no;center:yes;dialogWidth:600px;dialogHeight:400px;');
	return val;
}
// jqgrid resize
function grid_resize(grid) {
	// var grid = jQuery("#"+gridid);
	var h = jQuery(window).height() - grid.offset().top - 28;
	var w = jQuery(window).width() - 5;
	grid.setGridWidth(w).setGridHeight(h);
}
// 时间差值，返回天 小时 分 秒 传入参数格式为 yyyy-MM-dd HH:mm:ss
function timeDiff(starttime, endtime) {
	var end = Date.parse(starttime.replace(/-/g, "/"));
	var start = Date.parse(endtime.replace(/-/g, "/"));
	var leftsecond = parseInt((end - start) / 1000);
	var d = parseInt(leftsecond / 3600 / 24);
	var h = parseInt((leftsecond / 3600) % 24);
	var m = parseInt((leftsecond / 60) % 60);
	var s = parseInt(leftsecond % 60);
	return d + "天 " + h + "小时" + m + "分" + s + "秒";
}
// 比例转换
function rate(sum, over) {
	if (sum !== 0) {
		return (over / sum * 100).toFixed(2) + '%';
	}
	return "--";
}

function loadingtext(id, gv) {
	jQuery("<div class='loading ui-state-default ui-state-active' style='margin:0' id='load_"
			+ id + "'>读取中...</div>").appendTo(gv);
}
// 弹出窗口
function openDialog(new_url) {
	window.open(new_url, "", "width:600, height: 400");
}
// 搜索巡检组
function searchOrg(url) {
	var val = showOrg(url);
	if (!!val) {
		jQuery("#orgid").val(val[0]);
		jQuery("#orgname").val(val[1]);
	}
}
// 弹出信息
function info(msg) {
	jQuery.dialog({
				lock : true,
				content : msg,
				cancelVal : '关闭',
				cancel : true,
				time : 3
			});
}
// 确认信息
function confirminfo(msg, fun) {
	art.dialog({
		content : msg,
		ok : fun,
		cancelVal : '关闭',
		cancel : true
			// 为true等价于function(){}
		});
}
// 弹出GIS选择窗口
function showGis(url) {
	var val = window.showModalDialog(url, window,
			'status:no;center:yes;dialogWidth:400px;dialogHeight:300px;');
	var userName = "";
	var userId = "";
	var newval;
	if (!!val) {
		for (i = 0; i < val.length; i++) {
			userName += val[i].NAME + ",";
			userId += val[i].ID + ",";
		}
		userId = userId.substring(0, userId.length - 1);
		userName = userName.substring(0, userName.length - 1);
		newval = new Array(2);
		newval[0] = userId;
		newval[1] = userName;
	}
	return newval;
}
// 获取组织
function getorg() {
	var url = contextPath + '/commonaccess!getorg.action?orgtype=2&regionid='
			+ jQuery('#regionid').val();
	searchOrg(url);
}
// 获取巡检组
function getgroup() {
	var url = contextPath + '/commonaccess!getpatrolgroup.action?orgid='
			+ jQuery('#orgid').val();
	searchPatrolGroup(url);
}
// 刷新页面
function breakPage() {
	window.location.href = window.location.href;
	window.location.reload;
}
// 函数说明：合并指定表格（表格id为_w_table_id）指定列（列数为_w_table_colnum）的相同文本的相邻单元格
// 参数说明：_w_table_id 为需要进行合并单元格的表格的id。如在HTMl中指定表格 id="data" ，此参数应为 #data
// 参数说明：_w_table_colnum 为需要合并单元格的所在列。为数字，从最左边第一列为1开始算起。
function table_rowspan(_w_table_id, _w_table_colnum) {
	_w_table_firsttd = "";
	_w_table_currenttd = "";
	_w_table_SpanNum = 0;
	_w_table_Obj = $(_w_table_id + " tr td:nth-child(" + _w_table_colnum + ")");
	_w_table_Obj.each(function(i) {
				if (i == 0) {
					_w_table_firsttd = $(this);
					_w_table_SpanNum = 1;
				} else {
					_w_table_currenttd = $(this);
					if (_w_table_firsttd.text() == _w_table_currenttd.text()) {
						_w_table_SpanNum++;
						_w_table_currenttd.hide(); // remove();
						_w_table_firsttd.attr("rowSpan", _w_table_SpanNum);
					} else {
						_w_table_firsttd = $(this);
						_w_table_SpanNum = 1;
					}
				}
			});
}
// 函数说明：合并指定表格（表格id为_w_table_id）指定行（行数为_w_table_rownum）的相同文本的相邻单元格
// 参数说明：_w_table_id 为需要进行合并单元格的表格id。如在HTMl中指定表格 id="data" ，此参数应为 #data
// 参数说明：_w_table_rownum 为需要合并单元格的所在行。其参数形式请参考jQuery中nth-child的参数。
// 如果为数字，则从最左边第一行为1开始算起。
// "even" 表示偶数行
// "odd" 表示奇数行
// "3n+1" 表示的行数为1、4、7、10.......
// 参数说明：_w_table_maxcolnum 为指定行中单元格对应的最大列数，列数大于这个数值的单元格将不进行比较合并。
// 此参数可以为空，为空则指定行的所有单元格要进行比较合并。
function table_colspan(_w_table_id, _w_table_rownum, _w_table_maxcolnum) {
	if (_w_table_maxcolnum == void 0) {
		_w_table_maxcolnum = 0;
	}
	_w_table_firsttd = "";
	_w_table_currenttd = "";
	_w_table_SpanNum = 0;
	$(_w_table_id + " tr:nth-child(" + _w_table_rownum + ")").each(function(i) {
		_w_table_Obj = $(this).children();
		_w_table_Obj.each(function(i) {
					if (i == 0) {
						_w_table_firsttd = $(this);
						_w_table_SpanNum = 1;
					} else if ((_w_table_maxcolnum > 0)
							&& (i > _w_table_maxcolnum)) {
						return "";
					} else {
						_w_table_currenttd = $(this);
						if (_w_table_firsttd.text() == _w_table_currenttd
								.text()) {
							_w_table_SpanNum++;
							_w_table_currenttd.hide(); // remove();
							_w_table_firsttd.attr("colSpan", _w_table_SpanNum);
						} else {
							_w_table_firsttd = $(this);
							_w_table_SpanNum = 1;
						}
					}
				});
	});
}