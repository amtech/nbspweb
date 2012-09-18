<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/header.jsp"%>
<html>
	<head>
		<script type="text/javascript"
			src="${ctx }/workflow/fault/js/fault_common.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/WdatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="${ctx }/workflow/electricity/oilengine/js/oilenginemanage-list.js"></script>
		<script type="text/javascript">
	jQuery(function() {
		var jqgrid = jQuery("#faultgrid").jqGrid(
				{
					url : "${ctx}/oil/oilEngineManageAction!listData.action",
					datatype : "json",
					mtype : 'GET',
					rownumbers : true,
					colNames : [ '油机编号', '油机型号', '油料类型', '额定功率(KW)', '所属区域',
							'维护单位', '负责人', '联系电话', '使用状态', '操作' ],
					colModel : [ 
						{name : 'OILENGINE_CODE',id : 'OILENGINE_CODE',sortable : false,width:80}, 
						{name : 'OILENGINE_MODEL',id : 'OILENGINE_MODEL',sortable : false,width:80}, 
						{name : 'OIL_TYPE',id : 'OIL_TYPE',sortable : false,width:80}, 
						{name : 'RATION_POWER',id : 'RATION_POWER',sortable : false,width:100}, 
						{name : 'DISTRICT',id : 'DISTRICT',sortable : false,width:80}, 
						{name : 'MAINTENANCE_ID',id : 'MAINTENANCE_ID',sortable : false,width:140}, 
						{name : 'PRINCIPAL',id : 'PRINCIPAL',sortable : false,width:80}, 
						{name : 'PHONE',id : 'PHONE',sortable : false,width:70}, 
						{name : 'STATE',id : 'STATE',sortable : false,width:80}, 
						{name : 'ID',id : 'ID',sortable : false,formatter : OptFmatter,width:150} 
					],
					rowNum : 15,
					rowList : [ 15, 30, 50 ],
					pager : '#faultpager',
					viewrecords : true,
					hidegrid : false,
					prmNames : {page : "pageNo",rows : "rows",sort : "sidx",order : "sord"},
					jsonReader : {root : "result",page : "pageNo",total : "totalPages",records : "totalCount",repeatitems : false}
				}).navGrid('#faultpager', {
			edit : false,
			add : false,
			del : false,
			search : false,
			sortable : false
		});

		jQuery(window).wresize(function() {
			grid_resize(jqgrid);
		});
		grid_resize(jqgrid);
	});
	/**
	 * 列表查询方法
	 */
	function query() {
		jQuery("#faultgrid").jqGrid().setGridParam({
			postData : {
				'entity.oilengineCode' : jQuery("#oilengine_code").val(),
				'entity.oilengineModel' : jQuery("#oilengine_model").val(),
				'entity.oilType' : jQuery("#oil_type").val(),
				'entity.rationPowerMin' : jQuery("#ration_power_min").val(),
				'entity.rationPowerMax' : jQuery("#ration_power_max").val(),
				'entity.district' : jQuery("#district").val(),
				'entity.maintenanceId' : jQuery("#maintenance_id").val(),
				'entity.principal' : jQuery("#principal").val()
			}
		}).trigger("reloadGrid");
	}
	//搜索区域
	function searchRegion(url) {
		var val = showRegion(url);
		if (!!val) {
			jQuery("#district").val(val[0]);
			jQuery("#regionname").val(val[1]);
		}
	}
	/**
	 * 搜索代维单位
	 * @param url
	 */
	function searchWhdw(url) {
		var val = showOrg(url);
		if (!!val) {
			jQuery("#maintenance_id").val(val[0]);
			jQuery("#maintenance_name").val(val[1]);
		}
	}
</script>
	</head>
	<body>
		<form id="searchForm" name="searchForm">
			<div id="header">
				<div class="title_bg">
					<div id="title" class="title">
						当前位置-油机信息列表
					</div>
				</div>
				<div class="tabcontent">
					<table cellspacing="0" cellpadding="0" border="0" align="center">
						<tr>
							<th>
								油机编号：
							</th>
							<td>
								<input type="text" class="inputtext" style="width: 220px"
									name="entity.oilengine_code" id="oilengine_code" />
							</td>
							<th>
								油机型号：
							</th>
							<td>
								<input type="text" class="inputtext" style="width: 220px"
									name="entity.oilengine_model" id="oilengine_model" />
							</td>
						</tr>
						<tr>
							<th>
								油料类型：
							</th>
							<td>
								<baseinfo:dicselector columntype="OIL_TYPE" type="select"
									id="oil_type" name="entity.oil_type" cssClass="inputtext"
									style="width:220px" isQuery="query"></baseinfo:dicselector>
							</td>
							<th>
								额定功率(KW)：
							</th>
							<td>
								<input id="ration_power_min" name="entity.ration_power_min"
									class="inputtext" style="width: 99px;"
									onkeyup="this.value=this.value.replace(/\D/g,'')"
									onafterpaste="this.value=this.value.replace(/\D/g,'')" />
								至
								<input id="ration_power_max" name="entity.ration_power_max"
									class="inputtext" style="width: 99px;"
									onkeyup="this.value=this.value.replace(/\D/g,'')"
									onafterpaste="this.value=this.value.replace(/\D/g,'')" />
							</td>
						</tr>
						<tr>
							<th>
								所属区域：
							</th>
							<td>
								<input id="regionname" name="entity.regionname" maxlength="60"
									class="inputtext" type="text" style="width: 220px;" readonly />
								<a
									href="javascript:searchRegion('${ctx}/commonaccess!getregion.action');">
									<img border="0" src="${ctx}/css/images/selectcode.gif" /> </a>
								<input id="district" name="entity.district" type="hidden" />
							</td>
							<th>
								维护单位：
							</th>
							<td>
								<input id="maintenance_name" name="entity.maintenance_name"
									class="inputtext" style="width: 220px;" readonly />
								<input id="maintenance_id" name="entity.maintenance_id"
									type="hidden" />
								<a
									href="javascript:searchWhdw('${ctx}/commonaccess!getorg.action');">
									<img border="0" src="${ctx}/css/images/selectcode.gif" /> </a>
							</td>
						</tr>
						<tr>
							<th>
								负责人：
							</th>
							<td colspan="3">
								<input id="principal" name="entity.principal" class="inputtext"
									style="width: 220px;" />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4">
								<input type="button" onclick="query();" class="button"
									value="查询" />
								<baseinfo:expexcel cls="exprotButton"  businessId="oilenginelist" name="导出excel"></baseinfo:expexcel>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<br>
			<div id="content" align="center">
				<table id="faultgrid"></table>
				<div id="faultpager"></div>
			</div>
		</form>
	</body>
</html>
