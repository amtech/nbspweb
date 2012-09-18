/**
 * 油机管理保存javascript
 */
var user;
/**
 * 检查编码是否重复
 */
function checkCodeNumber(flag) {
	jQuery.ajaxSetup({
				async : flag
			});
	jQuery.get(contextPath + "/oil/oilEngineManageAction!checkCode.action?id="
					+ jQuery("#id").val() + "&code="
					+ jQuery("#oilengine_code").val(), function(data) {
				if (data == '0') {
					jQuery("#oilengine_code_flag").val("0");
				} else {
					jQuery("#oilengine_code_flag").val("1");
				}
			}, 'text');
}
// 获取基站相关信息

function getZdxx() {
	var a = showresource(contextPath + '/commonaccess!getresourceinfo.action?businessType=C31');
	if (!!a) {
		jQuery("#station_name").val(a[0].NAME);
		jQuery("#station_type").val(a[0].TYPE);
		jQuery("#station_id").val(a[0].ID);
		jQuery("#address").val(a[0].ADDRESS);
		jQuery("#lon").val(a[0].LON);
		jQuery("#lat").val(a[0].LAT);
		jQuery("#ct_x").val(a[0].CT_X);
		jQuery("#ct_y").val(a[0].CT_Y);
	}
}

/**
 * 搜索油机的产权
 * 
 * @param url
 */
function searchPropertyRight(url) {
	var val = showOrg(url);
	if (!!val) {
		jQuery("#property_right").val(val[0]);
		jQuery("#property_right_name").val(val[1]);
	}
}
/**
 * 搜索代维单位
 * 
 * @param url
 */
function searchWhdw(url) {
	var val = showOrg(url);
	if (!!val) {
		jQuery("#maintenance_id").val(val[0]);
		jQuery("#maintenance_name").val(val[1]);
	}
}
// 搜索区域
function searchRegion(url) {
	var val = showRegion(url);
	if (!!val) {
		jQuery("#district").val(val[0]);
		jQuery("#regionname").val(val[1]);
	}
}

