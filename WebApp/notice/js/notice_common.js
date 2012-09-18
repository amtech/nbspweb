
// 删除
function toDelete(idValue) {
	if (confirm("确定删除该纪录？")) {
		var url = contextPath + "/system/notice!delete.action?&id=" + idValue;
		self.location.replace(url);
	}
}
// 编辑
function toEdit(idValue) {
	var url = contextPath + "/system/notice!input.action?&id=" + idValue;
	self.location=url;

}
// 查看
function open_notify(NOTICE_ID) {
	URL = contextPath + "/system/notice!view.action?&id=" + NOTICE_ID
			+ "&preview=true";
	myleft = (screen.availWidth - 650) / 2;
	mytop = 100
	mywidth = 650;
	myheight = 500;
	// if (FORMAT == "1") {
	// myleft = 0;
	// mytop = 0
	// mywidth = screen.availWidth - 10;
	// myheight = screen.availHeight - 40;
	// }
	window
			.open(
					URL,
					"read_news",
					"height="
							+ myheight
							+ ",width="
							+ mywidth
							+ ",status=1,resizable=no,toolbar=no,menubar=no,location=no,scrollbars=yes,top="
							+ mytop + ",left=" + myleft + ",resizable=yes");
}
