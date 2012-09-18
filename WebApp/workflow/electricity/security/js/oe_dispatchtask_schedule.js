/**
 * 显示可以调度的油机列表
 */
function listOilEngine(stationId, stationType) {
	var oilEngineId = jQuery("#oilengine_id").val();
	if (oilEngineId != "") {
		var actionUrl = contextPath
				+ "/workflow/oeOilengineSearchAction!listData.action?";
		var jqgrid = jQuery("#oedispatchtaskgrid").jqGrid(
				{
					url : actionUrl,
					datatype : "json",
					mtype : 'GET',
					rownumbers : true,
					postData : {
						'oeDispatchTask.stationId' : stationId,
						'oeDispatchTask.stationType' : stationType,
						'oeDispatchTask.oilengineId' : oilEngineId
					},
					colNames : [ '', '油机编号', '油机型号', '油料类型', '距离(米)',
							'额定功率(KW)', '标准油耗(G/KW.H)', '所属区域', '维护单位', '负责人',
							'联系电话', '使用状态' ],
					colModel : [ {
						name : 'ID',
						id : 'ID',
						sortable : false,
						formatter : oilengineSelectFormatter
					}, {
						name : 'OILENGINE_CODE',
						id : 'OILENGINE_CODE',
						sortable : false
					}, {
						name : 'OILENGINE_MODEL',
						id : 'OILENGINE_MODEL',
						sortable : false
					}, {
						name : 'OIL_TYPE_DIS',
						id : 'OIL_TYPE_DIS',
						sortable : false
					}, {
						name : 'DISTANCE_DIS',
						id : 'DISTANCE_DIS',
						sortable : false
					}, {
						name : 'RATION_POWER',
						id : 'RATION_POWER',
						sortable : false
					}, {
						name : 'STANDARD_OILWEAR',
						id : 'STANDARD_OILWEAR',
						sortable : false
					}, {
						name : 'REGIONNAME',
						id : 'REGIONNAME',
						sortable : false
					}, {
						name : 'ORG_NAME',
						id : 'ORG_NAME',
						sortable : false
					}, {
						name : 'PRINCIPAL',
						id : 'PRINCIPAL',
						sortable : false
					}, {
						name : 'PHONE',
						id : 'PHONE',
						sortable : false
					}, {
						name : 'STATE_DIS',
						id : 'STATE_DIS',
						sortable : false
					} ],
					rowNum : 10,
					autoWidth : true,
					rowList : [ 10, 20, 30 ],
					pager : '#oedispatchtaskpager',
					shrinkToFit : true,
					viewrecords : true,
					hidegrid : false,
					prmNames : {
						page : "pageNo",
						rows : "rows",
						sort : "sidx",
						order : "sord"
					},
					jsonReader : {
						root : "result", // 数据行（默认为：rows）
						page : "pageNo", // 当前页
						total : "totalPages", // 总页数
						records : "totalCount", // 总记录数
						repeatitems : false
					}
				}).navGrid('#oedispatchtaskpager', {
			edit : false,
			add : false,
			del : false,
			search : false,
			sortable : false
		});
		jQuery("#oedispatchtaskgrid").jqGrid().setGridParam({
			postData : {
				'oeDispatchTask.stationId' : stationId,
				'oeDispatchTask.stationType' : stationType,
				'oeDispatchTask.oilengineId' : oilEngineId
			}
		}).trigger("reloadGrid");
		jQuery(window).wresize(function() {
			grid_resize(jqgrid);
		});
		grid_resize(jqgrid);
	}
}
/**
 * 油机单选按钮格式化输出
 * 
 * @param cellValue
 * @param options
 * @param rowObject
 */
function oilengineSelectFormatter(cellValue, options, rowObject) {
	var view = "<input name='oeScheduleTask.oilEngineId' id='oeScheduleTask_oilEngineId";
	view += options.rowId;
	view += "' type='radio' value='";
	view += cellValue;
	view += "' state='";
	view += rowObject.STATE;
	view += "' stationId='";
	view += rowObject.BASESTATION_ID;
	view += "' />"
	return view;
}
/**
 * 提交表单数据
 * 
 * @returns {Boolean}
 */
function submitData() {
	return true;
}
/**
 * 重置表单数据
 */
function resetData() {
	jQuery("#oeScheduleTask_dispatchRemark").val("");
	jQuery("#oeScheduleTask_preStationId").val("");
	jQuery("input[name='']:checked").removeAttr("checked");
}