// 日常巡检chart
function patrolInfo(data) {
	chartPatrolInfo = new Highcharts.Chart({
				chart : {
					renderTo : 'patrolinfo',
					defaultSeriesType : 'column'
				},
				title : {
					text : ''
				},
				credits : {
					enabled : false
				},
				xAxis : {
					categories : data.xcategories,
					labels : {
						rotation : -30,
						align : 'right'

					}
				},
				yAxis : {
					min : 0.0,
					title : {
						text : jQuery('select[name="timetype"]').find('option:selected').text()+'巡检率 %'
					}
				},
				exporting : {
					enabled : false
				},
				
				tooltip : {
					formatter : function() {
						return '' + this.x + ': ' + this.y + ' %';
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
// 获取代维人员图
function chartcontractorman(data) {
	chartcontractorman = new Highcharts.Chart({
				chart : {
					renderTo : 'contractorman',
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
							name : '代维人员统计',
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
// 通用工单图表
function workSheet(total_hour, total_num, overtime_num) {
	chartWorkSheet = new Highcharts.Chart({
				chart : {
					renderTo : 'worksheet',
					zoomType : 'xy'
				},
				credits : {
					enabled : false
				},
				title : {
					text : ''
				},
				xAxis : [{
					categories : ['1月', '2月', '3月', '4月', '5月', '6月', '7月',
							'8月', '9月', '10月', '11月', '12月']
				}],
				yAxis : [{ // Primary yAxis
					labels : {
						formatter : function() {
							return this.value + '个';
						},
						style : {
							color : '#89A54E'
						}
					},
					title : {
						text : '数量',
						style : {
							color : '#89A54E'
						}
					},
					min:0
				}, {	// Secondary yAxis
							gridLineWidth : 0,
							title : {
								text : '平均历时',
								style : {
									color : '#4572A7'
								}
							},
							min:0,
							labels : {
								formatter : function() {
									return this.value + ' h';
								},
								style : {
									color : '#4572A7'
								}
							},
							opposite : true
						}],
				tooltip : {
					formatter : function() {
						return '' + this.x + ': ' + this.y
								+ (this.series.name == '平均历时' ? ' h' : '个');
					}
				},
				exporting : {
					enabled : false
				},
				
				series : [{
							name : '平均历时',
							color : '#4572A7',
							type : 'column',
							yAxis : 1,
							data : total_hour

						}, {
							name : '工单总数',
							color : '#89A54E',
							type : 'spline',
							yAxis : 0,
							data : total_num
						}, {
							name : '超时工单',
							color : '#69004E',
							type : 'spline',

							data : overtime_num
						}]
			});
}
// 在线人员图表
function sitePerson(data) {
	var colors = Highcharts.getOptions().colors, categories = data.categories, name = '现场在线人员', data = data.data;

	// Build the data arrays
	var browserData = [];
	var versionsData = [];
	for (var i = 0; i < data.length; i++) {
		// add browser data
		browserData.push({
					name : categories[i],
					y : data[i].y,
					color : data[i].color
				});
		// add version data
		for (var j = 0; j < data[i].drilldown.data.length; j++) {
			var brightness = 0.2 - (j / data[i].drilldown.data.length) / 5;
			versionsData.push({
						name : data[i].drilldown.categories[j],
						y : data[i].drilldown.data[j],
						color : Highcharts.Color(data[i].color)
								.brighten(brightness).get()
					});
		}
	}

	// Create the chart
	chartSitePerson = new Highcharts.Chart({
				chart : {
					renderTo : 'siteperson',
					type : 'pie'
				},
				title : {
					text : ''
				},
				credits : {
					enabled : false
				},
				plotOptions : {
					pie : {
						allowPointSelect : false,
						cursor : 'pointer',
						events : {
							click : function(e) {
								//alert(e.point.name);// 直接定位到地图上
							}
						}
					}
				},

				tooltip : {
					formatter : function() {
						return '<b>' + this.point.name + '</b>: ' + this.y
								+ ' ';
					}
				},
				exporting : {
					enabled : false
				},
				series : [{
							name : 'Browsers',
							data : browserData,
							size : '60%',
							dataLabels : {
								formatter : function() {
									return this.y > 5 ? this.point.name : null;
								},
								color : 'green'
							}
						}, {
							name : 'Versions',
							data : versionsData,
							innerSize : '50%',
							dataLabels : {
								formatter : function() {
									// display only if larger than 1
									return null;
								}
							}
						}]
			});
}
