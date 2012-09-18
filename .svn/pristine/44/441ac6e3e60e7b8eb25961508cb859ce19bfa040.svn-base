<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/meta.jsp"%>
<link href="${ctx}/css/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/js/in-min.js"
	autoload="true"
	core="${config.cdnurl}/cabletech/ui/js/jquery-1.7.2.min.js"></script>
<title>巡检分析</title>
<script type="text/javascript">
In.add('jqui',{path:'${ctx}/js/jQuery/jqueryui/css/redmond/jquery-ui.custom.css'});
In.add('jquijs',{path:'${ctx}/js/jQuery/jqueryui/jquery-ui.custom.min.js',type:'js',charset:'utf-8',rely:['jqui']});
In.add('jgcss',{path:'${ctx}/js/jQuery/jqgrid/css/ui.jqgrid.css',charset:'utf-8',rely:['jquijs']});
In.add('jgjs',{path:'${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js',type:'js',charset:'utf-8',rely:['jgcss']});
In.add('jgcn',{path:'${ctx}/js/jQuery/jqgrid/grid.locale-cn.js',type:'js',charset:'utf-8',rely:['jgjs']});
In.add('common',{path:'${ctx}/common/js/common.js',type:'js',charset:'utf-8'});
In.add('patrolinfo_common', {path :'${ctx}/wplan/plan/js/patrolinfo_common.js',type : 'js',charset :'utf-8'}); 
In.add('patrolinfo_analysis', {path :'${ctx}/wplan/plan/js/patrolinfo_analysis.js',type : 'js',charset :'utf-8',rely : ['patrolinfo_common' ]}); 
In.add('highcharts', {path :'${ctx}/js/highchart/highcharts.js',type : 'js',charset : 'utf-8'});
</script>
</head>
<body>
	<form id="patrolinfoForm" name="patrolinfoForm" method="post">
		<div class="title_bg">
			<div id="title" class="title">当前位置-按代维分析结果</div>
		</div>
		<div class="tabcontent">
			<table cellspacing="0" cellpadding="0" border="0" align="center">
				<tr>
					<th>巡检计划数：</th>
					<td>${patrolanalyMap.PLANCOUNT}</td>
					<th>计划巡检站点数：</th>
					<td>${patrolanalyMap.PLANRESCOUNT}</td>
				</tr>
				<tr>
					<th>已巡检站点数：</th>
					<td>${patrolanalyMap.PLANOVERRESCOUNT}</td>
					<th>巡检率：</th>
					<td>${patrolanalyMap.PLANRATE}</td>
				</tr>
				<tr>
					<td colspan="4">
						<div id="tab_process" style="width:100%">
							<ul>
								<li><a href="#orginfo">代维公司情况</a>
								</li>
								<li><a href="#patrolinfo">计划完成情况</a>
								</li>
								<li><a href="#chartinfo">图表</a>
								</li>
							</ul>
							<div id="orginfo" style="width:98%">
								<table id="orginfogrid"></table>
								<div id="orginfopager"></div>
							</div>
							<div id="patrolinfo" style="width:98%">
								<table id="patrolinfogrid"></table>
								<div id="patrolinfopager"></div>
							</div>
							<div id="chartinfo" style="height: 180px">
								<div id="patrolreschart"
									style="width: 250px; height: 180px; float: left"></div>
								<div id="patrolinfochart"
									style="width: 250px; height: 180px; float: right;"></div>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div align="center">
							<input name="button" type="button" class=button value="返回"
								onclick="history.go(-1);">
						</div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
<script type="text/javascript">
	In.ready('jgcn', 'highcharts', 'common', 'patrolinfo_analysis', function() {
		setContextPath('${ctx }');
		//获取计划开始时间及结束时间
		var starttime='${starttime }';
		var endtime='${endtime }';
		var param={
		    	orgid:'${contractorid}',
		    	starttime:starttime,
		    	businesstype:'${businesstype}',
		    	endtime:endtime,
		    	year:'${year}',
		    	plantype:'${plantype}',
		    	seasontype:'${seasontype}'
		    	};
		var tab=jQuery('#tab_process').tabs({ cache: true ,selected: 0});
		var orgGrid=getOrgGroupGrid(param);
		var initialized = false; //是否已初始化
		tab.bind('tabsshow', function(event, ui) {
			if (ui.index == 1 && !initialized){ 
				getPatrolinfoGrid(param);
				initialized = true; 
			}
		});
		var nocount=${patrolanalyMap.PLANRESCOUNT}-${patrolanalyMap.PLANOVERRESCOUNT};
		patrolchart(nocount,${patrolanalyMap.PLANOVERRESCOUNT});
		patrolInfo([${PatrolGroupChartMap.PATROLNAMES}],[${PatrolGroupChartMap.RESCOUNTS}],[${PatrolGroupChartMap.NORESCOUNTS}],[${PatrolGroupChartMap.ALRESCOUNTS}]);
	})
</script>
</html>