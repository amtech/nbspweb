// 自适应浏览器高度
function autoHeight() {
	var section_middle = $(document).height()
			- $(".header").outerHeight() - $(".bottom").outerHeight();
	$("#bodyFrame").height(section_middle);
	$("#bodyFrame").width($(document).width());
	setTimeout(autoHeight, 500);
}
// 动态创建iframe
function createframe(_src, _scroll, _style, _frmid, _dvid) {
	var _s = 'width:100%;height:100%';
	if (_style) {
		_s = _style;
	}
	var i = $("<iframe id='" + _frmid + "' name='" + _frmid
			+ "' frameborder='0' src='" + _src + "' style='" + _s
			+ "' scrolling='" + _scroll + "'></iframe>");
	i.appendTo($('#' + _dvid));
	addLoadTip(_dvid,_frmid,"loadtipdiv");
}
function addLoadTip(_dvid, _frmid, _tipid) {
	$("#" + _frmid).css("display", "none");
	var loadtip = "<div id='"
			+ _tipid
			+ "' style='width: 100%; height:100%;margin-top:150px;text-align:center;'><img src='/nbspweb/css/images/loading.gif'><br>&nbsp;&nbsp;&nbsp;&nbsp;加载中,请稍后  ......</div>"
	$('#' + _dvid).append(loadtip);
	$("#" + _frmid).bind({
				"load" : function() {
					$("#" + _tipid).hide();
					$("#" + _frmid).css("display", "");
					$("#"+_tipid).remove();
				}
			});
}