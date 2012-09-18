<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/header.jsp"%>
<html>
	<head>
		<link href="${ctx }/js/WdatePicker/skin/WdatePicker.css"
			type="text/css" rel="stylesheet" />
		<link href="${ctx }/js/jQuery/ztree/css/zTreeStyle/zTreeStyle.css"
			type="text/css" rel="stylesheet" />
		<script language="javascript" type="text/javascript">
	jQuery(function() {
		//初始化站点信息
		changeZdxx();
	});
	//改变油机类型改变站点信息
	function changeZdxx() {
		var table = document.getElementById("table1");
		//定点油机
		if ("${entity.oilengineType}" == 'R13'
				|| "${entity.oilengineType}" == 'R19') {
			var mytr1 = table.insertRow(8);
			var myth1 = document.createElement("th");
			myth1.innerHTML = "站点类型：";
			mytr1.appendChild(myth1);
			var mytd1 = document.createElement("td");
			mytd1.innerHTML = '<baseinfo:dicselector columntype="pointtype" type="view"
								keyValue="${entity.stationType }" id="oil_type" name="oil_type"
								cssClass="inputtext" style="width:220px" />';
			mytr1.appendChild(mytd1);
			var myth2 = document.createElement("th");
			myth2.innerHTML = "站点名称：";
			mytr1.appendChild(myth2);
			var mytd2 = document.createElement("td");
			mytd2.innerHTML = "${entity.stationName}";
			mytr1.appendChild(mytd2);
			var mytr2 = table.insertRow(9);
			var myth3 = document.createElement("th");
			myth3.innerHTML = "站点坐标：";
			mytr2.appendChild(myth3);
			var mytd3 = document.createElement("td");
			mytd3.colSpan = "3";
			if ("${entity.lon }" != null && "${entity.lon}" != '') {
				mytd3.innerHTML = "${entity.lon}" + "," + "${entity.lat}";
			}
			mytr2.appendChild(mytd3);
		}
	}
</script>
	</head>
	<body>
		<form action="${ctx }/oil/oilEngineManageAction!save.action"
			id="dForm" method="post">
			<div class="title_bg">
				<div id="title" class="title">
					当前位置-查看油机
				</div>
			</div>
			<div class="tabcontent">
				<table style="width: 75%" border="0" align="center" id="table1">
					<tr>
						<th>
							油机编号：
						</th>
						<td>
							${entity.oilengineCode }
						</td>
						<th>
							油机型号：
						</th>
						<td>
							${entity.oilengineModel }
						</td>
					</tr>
					<tr>
						<th>
							油料类型：
						</th>
						<td>
							<baseinfo:dicselector columntype="OIL_TYPE" type="view"
								keyValue="${entity.oilType }" id="oil_type" name="oil_type"
								cssClass="inputtext" style="width:220px"></baseinfo:dicselector>
						</td>
						<th>
							油料厂商：
						</th>
						<td>
							${entity.producer }
						</td>
					</tr>

					<tr>
						<th>
							额定功率KW：
						</th>
						<td>
							${entity.rationPower }
						</td>
						<th>
							标准油耗G/KW.H：
						</th>
						<td>
							${entity.standardOilwear }
						</td>
					</tr>
					<tr>
						<th>
							油机产权：
						</th>
						<td>
							<baseinfo:org displayProperty="organizename"
								id="${entity.propertyRight}"></baseinfo:org>
						</td>
						<th>
							油机重量KG：
						</th>
						<td>
							${entity.oilengineWeight }
						</td>
					</tr>

					<tr>
						<th>
							油机状态：
						</th>
						<td>
							<baseinfo:dicselector columntype="oliengine_state" type="view"
								keyValue="${entity.oilengineState }" id="oilengine_state"
								name="oilengine_state" cssClass="inputtext" style="width:220px"></baseinfo:dicselector>
						</td>
						<th>
							油机地址：
						</th>
						<td>
							${entity.address }
						</td>
					</tr>
					<tr>
						<th>
							所属区域：
						</th>
						<td>
							<baseinfo:region displayProperty="regionname"
								id="${entity.district}"></baseinfo:region>
						</td>
						<th>
							维护单位：
						</th>
						<td>
							<baseinfo:org displayProperty="organizename"
								id="${entity.maintenanceId }"></baseinfo:org>
						</td>
					</tr>
					<tr>
						<th>
							负责人：
						</th>
						<td>
							${entity.principal}
						</td>
						<th>
							联系电话：
						</th>
						<td>
							${entity.phone}
						</td>
					</tr>

					<tr>
						<th>
							油机类型：
						</th>
						<td>
							<baseinfo:dicselector columntype="OIL_ENGINE_TYPE" type="view"
								keyValue="${entity.oilengineType }" id="oilengine_type"
								name="oilengine_type" cssClass="inputtext" style="width:220px"
								onChange="changeZdxx();"></baseinfo:dicselector>
						</td>
						<th>
							油机使用状态：
						</th>
						<td>
							<baseinfo:dicselector columntype="oliengine_use" type="view"
								keyValue="${entity.state }" id="oliengine_use"
								name="oliengine_use" cssClass="inputtext" style="width:220px"></baseinfo:dicselector>
						</td>
					</tr>
					<tr>
						<th>
							备注：
						</th>
						<td colspan="3" id="td1">
							${entity.remark}
						</td>
					</tr>
				</table>
				<div style="text-align: center; margin-top: 10px">
					<input value="返回" type="button" class="button"
						onclick="history.go(-1);" />
				</div>
			</div>
		</form>
	</body>
</html>