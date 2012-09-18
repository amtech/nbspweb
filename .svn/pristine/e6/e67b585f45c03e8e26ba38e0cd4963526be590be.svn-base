<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/header.jsp"%>
<html>
	<head>
		<link href="${ctx }/js/WdatePicker/skin/WdatePicker.css"
			type="text/css" rel="stylesheet" />
		<link href="${ctx }/js/jQuery/ztree/css/zTreeStyle/zTreeStyle.css"
			type="text/css" rel="stylesheet" />
		<script type="text/javascript"
			src="${ctx }/workflow/electricity/oilengine/js/oilenginemanage-input.js"></script>
		<script type="text/javascript" src="${ctx}/common/js/common.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jvalidation/jquery.validate.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jvalidation/jquery.validate.cn.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jvalidation/jquery.validate.cn.js"></script>	
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jvalidation/jquery.validate.ex.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jvalidation/messages_cn.js"></script>
		<script language="javascript" type="text/javascript">
	var n = 0; //标志是否初始化，是则初始化画出的组件值
	jQuery(function() {
		//初始化站点信息
		changeZdxx();
		user = "${user.userId}";
		jQuery("#dForm").validate({
			focusInvalid : false,
			submitHandler : function(form) {
				checkCodeNumber(false);
				if (jQuery("#oilengine_code_flag").val() == "0") {
					form.submit();
				} else {
					alert("存在相同油机编号的油机！");
				}
			}
		});
	});
	//改变油机类型改变站点信息
	function changeZdxx() {
		var table = document.getElementById("table1");
		var ntr = table.getElementsByTagName('tr');
		var ntd = ntr[8].getElementsByTagName('td');
		if (ntd[0].id != "td1") {
			table.deleteRow(8);
		}
		//定点油机
		if (jQuery("#oilengine_type").val() == 'R13') {
			var mytr = table.insertRow(8);
			var myth2 = document.createElement("th");
			myth2.innerHTML = "所在站点：";
			mytr.appendChild(myth2);
			var mytd2 = document.createElement("td");
			var zdmc = document.createElement("input");
			mytd2.colSpan = "3";
			zdmc.type = "text";
			zdmc.id = "station_name";
			zdmc.name = "entity.stationName";
			zdmc.style.width = "220px";
			zdmc.style.border = " solid 1px #999999";
			zdmc.style.background.color = "#ffffff";
			zdmc.style.align = "left";
			zdmc.style.height = "20px";
			zdmc.className = "inputtext required";
			mytd2.appendChild(zdmc);
			var zdic = document.createElement("input");
			zdic.type = "hidden";
			zdic.id = "station_id";
			zdic.name = "entity.stationId";
			mytd2.appendChild(zdic);
			var lon = document.createElement("input");
			lon.id = "lon";
			lon.name = "entity.lon";
			lon.type = "hidden";
			var lat = document.createElement("input");
			lat.id = "lat";
			lat.name = "entity.lat";
			lat.type = "hidden";
			var ct_x = document.createElement("input");
			ct_x.id = "ct_x";
			ct_x.name = "entity.ctX";
			ct_x.type = "hidden";
			var ct_y = document.createElement("input");
			ct_y.id = "ct_y";
			ct_y.name = "entity.ctY";
			ct_y.type = "hidden";
			var station_type = document.createElement("input");
			station_type.id = "station_type";
			station_type.name = "entity.stationType";
			station_type.type = "hidden";
			mytd2.appendChild(lon);
			mytd2.appendChild(lat);
			mytd2.appendChild(ct_x);
			mytd2.appendChild(ct_y);
			mytd2.appendChild(station_type);
			var img1 = document.createElement("img");
			img1.border = "0";
			img1.src = "${ctx}/css/images/selectcode.gif";
			img1.onclick = getZdxx;
			mytd2.appendChild(img1);
			var span2 = document.createElement("span");
			span2.innerText = " *";
			span2.style.color = "red";
			mytd2.appendChild(span2);
			mytr.appendChild(mytd2);
			if (n == 0) {
				zdmc.value = "${entity.stationName}";
				zdic.value = "${entity.stationId}";
				lon.value = "${entity.lon}";
				lat.value = "${entity.lat}";
				ct_x.value = "${entity.ctX}";
				ct_y.value = "${entity.ctY}";
			}
			n++;
		}
		if (jQuery("#oilengine_type").val() == 'R19') {
			var mytr = table.insertRow(8);
			var myth1 = document.createElement("th");
			myth1.innerHTML = "站点坐标：";
			mytr.appendChild(myth1);
			var mytd1 = document.createElement("td");
			mytd1.colSpan = "3";
			var xy = document.createElement("input");
			xy.type = "text";
			xy.id = "xy";
			xy.name = "entity.xy";
			xy.style.width = "220px";
			xy.style.border = " solid 1px #999999";
			xy.style.background.color = "#ffffff";
			xy.style.align = "left";
			xy.style.height = "20px";
			mytd1.appendChild(xy);
			var button1 = document.createElement("a");
			button1.border = "0";
			button1.innerHTML = "获取经纬度";
			button1.onclick = getLonLat;
			mytd1.appendChild(button1);
			mytr.appendChild(mytd1);
			if (n == 0) {
				if ("${entity.lon }" != null && "${entity.lon}" != '') {
					xy.value = "${entity.lon}" + "," + "${entity.lat}";
				}
			}
			n++;
		}
	}
</script>
	</head>
	<body>
		<form action="${ctx }/oil/oilEngineManageAction!save.action"
			id="dForm" method="post">
			<div class="title_bg">
				<div id="title" class="title">
					<c:if test="${id==null or id=='' }">
					当前位置-添加油机
				</c:if>
					<c:if test="${id!=null and id!='' }">
					当前位置-编辑油机
				</c:if>
				</div>
			</div>
			<div class="tabcontent">
				<table style="width: 75%" border="0" align="center" id="table1">
					<tr>
						<th>
							油机编号：
						</th>
						<td>
							<input id="id" name="entity.id" type="hidden" value="${entity.id }" />
							<input id="state" name="entity.state" type="hidden" value="${entity.state }" />
							<input id="oilengine_code" name="entity.oilengineCode" class="inputtext required byteRangeLength:[0,10]" value="${entity.oilengineCode }" style="width: 220px;" value="0" onblur="checkCodeNumber(true)" />
							<input type="hidden" id="oilengine_code_flag">
							<span style="color: red">*</span>
						</td>
						<th>
							油机型号：
						</th>
						<td>
							<input id="oilengine_model" name="entity.oilengineModel" class="inputtext required" value="${entity.oilengineModel }" style="width: 220px;" maxlength="12" />
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th>
							油料类型：
						</th>
						<td>
							<baseinfo:dicselector columntype="OIL_TYPE" type="select" keyValue="${entity.oilType }" id="oil_type" name="entity.oilType" cssClass="inputtext" style="width:220px"></baseinfo:dicselector>
							<span style="color: red">*</span>
						</td>
						<th>
							油料厂商：
						</th>
						<td>
							<input id="producer" name="entity.producer" class="inputtext" value="${entity.producer }" style="width: 220px;" maxlength="50"/>
						</td>
					</tr>

					<tr>
						<th>
							额定功率KW：
						</th>
						<td>
							<input id="ration_power" name="entity.rationPower" class="inputtext required number" value="${entity.rationPower }" style="width: 220px;" maxlength="5"/>
							<span style="color: red">*</span>
						</td>
						<th>
							标准油耗G/KW.H：
						</th>
						<td>
							<input id="standard_oilwear" name="entity.standardOilwear" class="inputtext required number" value="${entity.standardOilwear }" style="width: 220px;"/>
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th>
							油机产权：
						</th>
						<td>
							<input id="property_right_name" name="entity.propertyRightName" value='<baseinfo:org displayProperty="organizename" id="${entity.propertyRight }"/>' maxlength="60" class="inputtext required" type="text" style="width: 220px;" readonly />
							<input id="property_right" name="entity.propertyRight" value="${entity.propertyRight }" type="hidden">
							<a href="javascript:searchPropertyRight('${ctx}/commonaccess!getorg.action');">
								<img border="0" src="${ctx}/css/images/selectcode.gif" /> </a>
							<span style="color: red">*</span>
						</td>
						<th>
							油机重量KG：
						</th>
						<td>
							<input id="oilengine_weight" name="entity.oilengineWeight" class="inputtext number" value="${entity.oilengineWeight }" style="width: 220px;" />
						</td>
					</tr>
					<tr>
						<th>
							油机状态：
						</th>
						<td>
							<baseinfo:dicselector columntype="oliengine_state" type="select" keyValue="${entity.oilengineState }" id="oilengine_state" name="entity.oilengineState" cssClass="inputtext" style="width:220px"></baseinfo:dicselector>
							<span style="color: red">*</span>
						</td>
						<th>
							油机地址：
						</th>
						<td>
							<input id="address" name="entity.address" class="inputtext required" value="${entity.address }" style="width: 220px;" maxlength="100"/>
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th>
							所属区域：
						</th>
						<td>
							<input id="regionname" name="entity.regionname" value='<baseinfo:region displayProperty="REGIONNAME" id="${entity.district }"/>' maxlength="60" class="inputtext required" type="text" style="width: 220px;" readonly />
							<a href="javascript:searchRegion('${ctx}/commonaccess!getregion.action');">
								<img border="0" src="${ctx}/css/images/selectcode.gif" /> </a>
							<input id="district" name="entity.district" value="${entity.district }" type="hidden" />
							<span style="color: red">*</span>
						</td>
						<th>
							维护单位：
						</th>
						<td>
							<input id="maintenance_name" name="entity.maintenance_name" class="inputtext required" value='<baseinfo:org displayProperty="organizename" id="${entity.maintenanceId }"/>' style="width: 220px;" readonly />
							<input id="maintenance_id" name="entity.maintenanceId" type="hidden" value="${entity.maintenanceId }" />
							<a href="javascript:searchWhdw('${ctx}/commonaccess!getorg.action');">
								<img border="0" src="${ctx}/css/images/selectcode.gif" /> </a>
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th>
							负责人：
						</th>
						<td>
							<input id="principal" name="entity.principal" class="inputtext required" value="${entity.principal}" style="width: 220px;" maxlength="30"/>
							<span style="color: red">*</span>
						</td>
						<th>
							联系电话：
						</th>
						<td>
							<input id="phone" name="entity.phone" class="inputtext required isMobile" value="${entity.phone}" style="width: 220px;" />
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th>
							油机类型：
						</th>
						<td colspan="3">
							<baseinfo:dicselector columntype="OIL_ENGINE_TYPE" type="select" keyValue="${entity.oilengineType }" id="oilengine_type" name="entity.oilengineType" cssClass="inputtext" style="width:220px" onChange="changeZdxx();"></baseinfo:dicselector>
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th>
							备注：
						</th>
						<td colspan="3" id="td1">
							<textarea rows="5" name="entity.remark" id="remark" style="width: 220px;">${entity.remark}</textarea>
						</td>
					</tr>
				</table>
				<div style="text-align: center; margin-top: 10px">
					<input type="submit" class="button" value="提交" />
					<input value="重置" type="button" class="button" onclick="breakPage()"/>
					<c:if test="${id!=null and id!='' }">
						<input value="返回" type="button" class="button" onclick="history.go(-1);" />
					</c:if>
				</div>
			</div>
		</form>
	</body>
	<script type="text/javascript">
	// 获取地图上经纬度值
	function getLonLat() {
		url = "${WEBGIS_DEPLOYNAME}/gisextend!drawReturn.action?graphictype=1&eid=eid&userid="
				+ user;
		var val = showGis(url);
		if (!!val) {
			jQuery("#xy").val(val[0] + val[1]);
		}
	}
	</script>
</html>