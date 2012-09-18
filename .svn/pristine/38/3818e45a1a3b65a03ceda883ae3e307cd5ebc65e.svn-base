// 人员离职率
function personnel(data) {
	chartPersonnel = new Highcharts.Chart({
				chart : {
					renderTo : 'personnel',
					zoomType : 'line'
				},
				title : {
					text : ''
				},
				xAxis : [{
					categories : ['1月', '2月', '3月', '4月', '5月', '6月', '7月',
							'8月', '9月', '10月', '11月', '12月']
				}],
				yAxis : {

					title : {
						text : '离职率',
						style : {
							color : '#4572A7'
						}
					},
					labels : {
						formatter : function() {
							return this.value + ' %';
						},
						style : {
							color : '#4572A7'
						}
					},
					min:0
				},
				tooltip : {
					enabled : false,
					formatter : function() {
						return '<b>' + this.series.name + '</b><br/>' + this.x
								+ ': ' + this.y + '%';
					}
				},
				exporting : {
					enabled : false
				},
				credits : {
					enabled : false
				},
				series : data
			});
}
// 设备统计
function terminal(data) {
	chartterminal = new Highcharts.Chart({
				chart : {
					renderTo : 'terminal',
					plotBackgroundColor : null,
					plotBorderWidth : null,
					plotShadow : false
				},
				title : {
					text : ''
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
				credits : {
					enabled : false
				},
				series : [{
							type : 'pie',
							name : '代维终端统计',
							data : data,
							dataLabels : {
								formatter : function() {
									return this.point.name;
								},
								distance : 10
							}
						}]
			});
}
// 维护资源
function resource(data) {
	chartResource = new Highcharts.Chart({
				chart : {
					renderTo : 'resource',
					defaultSeriesType : 'column'
				},
				title : {
					text : ''
				},
				xAxis : {
					categories :data.xcategories,
					labels : {
						rotation : -10,
						align : 'right'

					}
				},
				yAxis : {
					min : 0,
					title : {
						text : '维护资源'
					}
				},
				exporting : {
					enabled : false
				},
				credits : {
					enabled : false
				},
				tooltip : {
					formatter : function() {
						if (this.series.name == '线路巡检') {
							return '' + this.x + ': ' + this.y + '千米 ';
						} else {
							return '' + this.x + ': ' + this.y + '个 ';
						}
					}
				},
				plotOptions : {
					column : {
						pointPadding : 0.2,
						borderWidth : 0
					}
				},
				series : data.series
			});
}