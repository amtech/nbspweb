// 巡检分析根据组织巡检组显示Grid
function getOrgGroupGrid(paramData) {
	var jqgrid = jQuery("#orginfogrid").jqGrid({
		url : contextPath
				+ "/wplan/patrolanalysisAction!getpatrolgrouppatrollist.action",
		datatype : "json",
		mtype : 'GET',
		postData : paramData,
		colNames : ['代维公司', '巡检组', '区域', '巡检计划数', '已巡检数', '未巡检数', '巡检资源数',
				'巡检率', '问题站点数'],
		colModel : [{
					name : 'ORGNAME',
					id : 'ORGNAME',
					width : 140,
					sortable : false
				}, {
					name : 'PATROLGROUPNAME',
					id : 'PATROLGROUPNAME',
					width : 100,
					sortable : false
				}, {
					name : 'REGIONNAME',
					id : 'REGIONNAME',
					width : 60,
					sortable : false
				}, {
					name : 'PLANCOUNT',
					id : 'PLANCOUNT',
					width : 80,
					sortable : false
				}, {
					name : 'PLANOVERRESCOUNT',
					id : 'PLANOVERRESCOUNT',
					width : 70,
					sortable : false
				}, {
					name : 'PLANLOSTRESCOUNT',
					id : 'PLANLOSTRESCOUNT',
					width : 70,
					sortable : false
				}, {
					name : 'PLANRESCOUNT',
					id : 'PLANRESCOUNT',
					width : 80,
					sortable : false
				}, {
					name : 'RATE',
					id : 'RATE',
					width : 60,
					sortable : false,
					formatter : OrgRateFmatter
				}, {
					name : 'EXCEPTIONCOUNT',
					width : 80,
					id : 'EXCEPTIONCOUNT',
					sortable : false
				}],
		rowNum : 10,
		shrinkToFit : false,fix:true,
		viewrecords : true,
		rowList : [10, 30, 50, 100],
		pager : '#orginfopager',
		rownumbers : true,autoScroll: true,
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
			repeatitems : false,
			id : "ROW_ID"
		}
	}).navGrid('#orginfopager', {
				edit : false,
				add : false,
				del : false,
				search : false,
				sortable : false
			});
	return jqgrid;
}

// 获取巡检信息Grid
function getPatrolinfoGrid(paramData) {
	var jqgrid = jQuery("#patrolinfogrid").jqGrid({
		url : contextPath
				+ "/wplan/patrolanalysisAction!getpatrolinfolist.action",
		datatype : "json",
		postData : paramData,
		mtype : 'GET',
		rownumbers : true,
		colNames : ['计划ID', '计划名称', '所属区域', '所属代维', '所属巡检组', '计划开始时间',
				'计划结束时间', '计划巡检数', '异常巡检数', '已巡检数', '未巡检数', '巡检完成率'],
		colModel : [{
					name : 'ID',
					id : 'ID',
					hidden : true
				}, {
					name : 'PLAN_NAME',
					id : 'PLAN_NAME',
					width : 100,
					sortable : false,
					align : 'left',
					formatter : PlanNameFmatter
				}, {
					name : 'REGIONNAME',
					id : 'REGIONNAME',
					width : 60,
					align : 'center',
					sortable : false
				}, {
					name : 'ORGNAME',
					id : 'ORGNAME',
					width : 140,
					align : 'center',
					sortable : false
				}, {
					name : 'PATROLGROUPNAME',
					id : 'PATROLGROUPNAME',
					width : 100,
					align : 'center',
					sortable : false
				}, {
					name : 'START_TIME',
					id : 'START_TIME',
					sortable : false,
					width : 90,
					formatter : 'date',
					align : 'center',
					formatoptions : {
						newformat : 'Y-m-d'
					}
				}, {
					name : 'END_TIME',
					id : 'END_TIME',
					sortable : false,
					width : 90,
					align : 'center',
					formatter : 'date',
					formatoptions : {
						newformat : 'Y-m-d'
					}
				}, {
					name : 'PATROLCOUNT',
					id : 'PATROLCOUNT',
					width : 80,
					align : 'center',
					sortable : false
				}, {
					name : 'EXCEPTIONCOUNT',
					id : 'EXCEPTIONCOUNT',
					sortable : false,
					width : 80,
					align : 'center',
					formatter : ExceptionPatrolFmatter
				}, {
					name : 'ENDPATROLCOUNT',
					id : 'ENDPATROLCOUNT',
					sortable : false,
					width : 70,
					align : 'center',
					formatter : OverPatrolFmatter
				}, {
					name : 'NOPATROLCOUNT',
					id : 'NOPATROLCOUNT',
					sortable : false,
					width : 70,
					align : 'center',
					formatter : LosePatrolFmatter
				}, {
					name : 'RATE',
					id : 'RATE',
					width : 80,
					align : 'left',
					sortable : false,
					formatter : PatrolRateFmatter
				}],
		rowNum : 10,
		shrinkToFit : false,fix:true,
		rowList : [10, 20, 30],
		pager : '#patrolinfopager',
		viewrecords : true,
		hidegrid : false,autoScroll: true,
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
			repeatitems : false,
			id : "ROW_ID"
		}
	}).navGrid('#patrolinfopager', {
				edit : false,
				add : false,
				del : false,
				search : false,
				sortable : false
			});
	return jqgrid;
}
// 获取区域巡检信息Grid
function getRegionPatrolGrid(paramData) {
	var jqgrid = jQuery("#regioninfogrid").jqGrid({
		url : contextPath
				+ "/wplan/patrolanalysisAction!getregionpatrollist.action",
		datatype : "json",
		mtype : 'get',
		postData : paramData,
		rownumbers : true,
		colNames : ['区域', '所属代维', '区域资源数', '计划巡检数', '已巡检数', '未巡检数', '巡检完成率'],
		colModel : [{
					name : 'REGIONNAME',
					id : 'REGIONNAME',
					width : 60,
					sortable : false
				}, {
					name : 'ORGNAME',
					id : 'ORGNAME',
					width : 140,
					sortable : false
				}, {
					name : 'RESCOUNT',
					id : 'RESCOUNT',
					width : 80,
					sortable : false
				}, {
					name : 'PLANRESCOUNT',
					id : 'PLANRESCOUNT',
					width : 80,
					sortable : false
				}, {
					name : 'PLANOVERRESCOUNT',
					id : 'PLANOVERRESCOUNT',
					width : 70,
					sortable : false
				}, {
					name : 'PLANLOSTRESCOUNT',
					id : 'PLANLOSTRESCOUNT',
					width : 70,
					sortable : false
				}, {
					name : 'PLANRATE',
					id : 'PLANRATE',
					width : 80,
					sortable : false,
					formatter : RegionRateFmatter
				}],
		rowNum : 10,
		shrinkToFit : false,fix:true,autoScroll: true,
		rowList : [10, 20, 30],
		pager : '#regioninfopager',
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
			repeatitems : false,
			id : "ROW_ID"
		}
	}).navGrid('#regioninfopager', {
				edit : false,
				add : false,
				del : false,
				search : false,
				sortable : false
			});
	return jqgrid;
}
// 区域巡检率转换
function RegionRateFmatter(cellvalue, options, rowObjec) {
	return rate(rowObjec.PLANRESCOUNT, rowObjec.PLANOVERRESCOUNT);
}
// ORG巡检率转换
function OrgRateFmatter(cellvalue, options, rowObjec) {
	return rate(rowObjec.PLANRESCOUNT, rowObjec.PLANOVERRESCOUNT);
}
// 巡检率转换
function PatrolRateFmatter(cellvalue, options, rowObjec) {
	return rate(rowObjec.PATROLCOUNT, rowObjec.ENDPATROLCOUNT);
}
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
// 获取巡检资源数
function patrolchart(nopatrolcount, alreadyrescount) {
	patrolchart = new Highcharts.Chart({
				chart : {
					renderTo : 'patrolreschart',
					plotBackgroundColor : null,
					plotBorderWidth : null,
					plotShadow : false
				},
				title : {
					text : ''
				},
				credits : {
					enabled : false
				},
				plotOptions : {
					pie : {
						allowPointSelect : true,
						cursor : 'pointer',
						dataLabels : {
							enabled : true,
							color : '#000000',
							connectorColor : '#000000',
							formatter : function() {
								return '<b>' + this.point.name + '</b>: '
										+ this.percentage + ' %';
							}
						}
					}
				},
				exporting : {
					enabled : false
				},
				series : [{
					type : 'pie',
					name : '巡检资源数统计',
					data : [['未巡检资源', nopatrolcount],
							['已巡检资源', alreadyrescount]],
					dataLabels : {
						formatter : function() {
							return this.point.name;
						},
						distance : 10
					}
				}]
			});
}

// 日常巡检chart
function patrolInfo(categories,rescounts,norescounts,alrescounts) {
	chartPatrolInfo = new Highcharts.Chart({
				chart : {
					renderTo : 'patrolinfochart',
					defaultSeriesType : 'column'
				},
				title : {
					text : ''
				},
				credits : {
					enabled : false
				},
				xAxis : {
					categories : categories,
					labels : {
						rotation : -30,
						align : 'right'

					}
				},
				yAxis : {
					min : 0.0,
					title : {
						text : '计划巡检数'
					}
				},
				exporting : {
					enabled : false
				},
				
				tooltip : {
					formatter : function() {
						return '' + this.x + ': ' + this.y + '';
					}
				},
				plotOptions : {
					column : {
						pointPadding : 0.2,
						borderWidth : 0
					}
				},
				    series: [{
						name: '计划巡检数',
						data: rescounts
					}, {
						name: '未巡检数',
						data: norescounts
					}, {
						name: '已巡检数',
						data: alrescounts
					}]
			});
}