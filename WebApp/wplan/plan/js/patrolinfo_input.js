var contextPath;
/**
 * set context path
 */
setContextPath = function(path) {
	contextPath = path;
}

// 设置开始日期格式
function setStartDateFmt() {
	var plantype = jQuery('select[name="plantype"]').val();
	if (plantype == 3) {
		return "yyyy-MM-01";
	} else {
		return "yyyy-MM-dd";
	}
}
// 设置结束日期值
function setEndDate() {
	var plantype = jQuery('select[name="plantype"]').val();
	var year = $dp.cal.getP('y');
	var month = $dp.cal.getP('M');
	// 如果是月计划
	if (plantype == 3) {
		jQuery('#patrolinfo_endtime').val(year + "-" + month + "-"
				+ getLastDate(year, month));
	}
	// 如果是月计划或者自定义
	if (plantype == 3 || plantype == 4) {
		change();
	}

}

// 计划类型改变时触发
function plantypechange(starttime, endtime) {
	var plantype = jQuery('select[name="plantype"]').val();
	jQuery("#patrolinfo_starttime").val(starttime);
	jQuery("#patrolinfo_endtime").val(endtime);
	if (plantype == 1) {
		jQuery("#yearDV").show();
		jQuery("#seasonDV").hide();
		jQuery("#monthDV").hide();
	} else if (plantype == 2) {
		jQuery("#yearDV").hide();
		jQuery("#seasonDV").show();
		jQuery("#monthDV").hide();
	} else if (plantype == 3) {
		jQuery("#yearDV").hide();
		jQuery("#seasonDV").hide();
		jQuery("#monthDV").show();
		jQuery("#patrolinfo_endtime").attr("disabled", "disabled");
	} else {
		jQuery("#yearDV").hide();
		jQuery("#seasonDV").hide();
		jQuery("#monthDV").show();
		jQuery("#patrolinfo_endtime").removeAttr("disabled");
	}
}
// 计划类型改变时触发
function change() {
	var plantype = jQuery('select[name="plantype"]').val();
	var str = jQuery("#patrolinfo_patrolgroupname").val();
	var businesstype=jQuery('select[name="businesstype"]').find("option:selected").text();
	// 清空时间
	if (plantype == 1) {
		str += jQuery('select[name="year"]').find("option:selected").text()
				+ jQuery('select[name="yeartype"]').find("option:selected")
						.text();
	} else if (plantype == 2) {
		str += jQuery('select[name="year"]').find("option:selected").text()
				+ jQuery('select[name="seasontype"]').find("option:selected")
						.text();
	} else if (plantype == 3) {
		str += jQuery("#patrolinfo_starttime").val() + "至"
				+ jQuery("#patrolinfo_endtime").val();
	} else {
		str += jQuery("#patrolinfo_starttime").val() + "至"
				+ jQuery("#patrolinfo_endtime").val();
	}
	str += businesstype+"计划";
	jQuery("#planname").val(str);
	getTemplate();
	var groupId=jQuery("#patrolinfo_patrolgroupid").val();
	if(groupId){
	getResource(groupId);
	}
}
var lastValue = "", nodeList = [], fontCss = {};
var key;
function focusKey(e) {
	if (key.hasClass("empty")) {
		key.removeClass("empty");
	}
}
function blurKey(e) {
	if (key.get(0).value === "") {
		key.addClass("empty");
	}
}

// 搜索节点
function searchNode() {
	var zTree = jQuery.fn.zTree.getZTreeObj("patrolresourctree");
	var key = jQuery("#search_resources");
	if (zTree) {
		var value = jQuery.trim(key.val());
		var keyType = "RS_NAME";
		if (key.hasClass("empty")) {
			value = "";
		}
		if (lastValue === value)
			return;
		updateNodes(false);
		lastValue = value;
		if (value === "") {
			key.addClass("empty");
			return;
		}
		nodeList = zTree.getNodesByParamFuzzy(keyType, value);
		updateNodes(true);
	}

}
// 更新节点
function updateNodes(highlight) {

	var zTree = jQuery.fn.zTree.getZTreeObj("patrolresourctree");
	for (var i = 0, l = nodeList.length; i < l; i++) {
		nodeList[i].highlight = highlight;
		zTree.updateNode(nodeList[i]);
	}
}

// 开始日期最大值限制
function maxDateFS() {
	var plantype = jQuery('select[name="plantype"]').val();
	if (plantype == 4) {
		return '#F{$dp.$D(\'patrolinfo_endtime\')}';
	} else {
		return jQuery('select[name="year"]').find("option:selected").val()
				+ '-12-31';
	}
}
// 开始日期最小值限制
function minDateFS() {
	var plantype = jQuery('select[name="plantype"]').val();
	if (plantype == 4) {
		return jQuery('select[name="year"]').find("option:selected").val()
				+ '-01-01';
	}
}
// 结束日期最大值限制
function maxDateFE() {
	var plantype = jQuery('select[name="plantype"]').val();
	if (plantype == 4) {
		return jQuery('select[name="year"]').find("option:selected").val()
				+ '-12-31';
	}
}
// 结束日期最小值限制
function minDateFE() {
	var plantype = jQuery('select[name="plantype"]').val();
	if (plantype == 4) {
		return '#F{$dp.$D(\'patrolinfo_starttime\')}';
	} else {
		return jQuery('select[name="year"]').find("option:selected").val()
				+ '-01-01';
	}
}
