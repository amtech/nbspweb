<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${ctx }/js/jQuery/jqgrid/css/ui.jqgrid.css"
			type="text/css" rel="stylesheet" />
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jqgrid/grid.locale-cn.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/WdatePicker/WdatePicker.js"></script>
		<title>客户满意度评价查询列表</title>
		<script type="text/javascript">
	jQuery(function() {
		setContextPath('${ctx }');
		//使用层布局
		var jqgrid = jQuery("#satisfactionid").jqGrid({
			url : "${ctx}/satisfactionAction!listData.action?rnd=",
			datatype : "json",
			mtype : 'POST',
			rownumbers : true,
			colNames : [ '集客', '地区', '代维公司', '维护组', '维护人员', '工作类型', '满意度' ],
			colModel : [ {
				name : 'GROUP_NAME',
				id : 'GROUP_NAME',
				sortable : false
			}, {
				name : 'REGIONNAME',
				id : 'REGIONNAME',
				sortable : false
			}, {
				name : 'ORGNAME',
				id : 'ORGNAME',
				sortable : false
			}, {
				name : 'PATROLGROUP_NAME',
				id : 'PATROLGROUP_NAME',
				sortable : false
			}, {
				name : 'PERSON_NAME',
				id : 'PERSON_NAME',
				sortable : false
			}, {
				name : 'TASK_TYPE',
				id : 'TASK_TYPE',
				sortable : false,
				formatter : taskTypeFormatter
			}, {
				name : 'SATISFACTION',
				id : 'SATISFACTION',
				sortable : false,
				formatter : satisfactionFormatter
			} ],
			rowNum : 10,
			autowidth : true,
			rowList : [ 10, 20, 30 ],
			pager : '#satisfactionpager',
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
				repeatitems : false,
				id : "0"
			}
		}).navGrid('#satisfactionpager', {
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
	function taskTypeFormatter(cellValue, options, rowObjec){
		var view = "";
		if(cellValue=="PLAN"){
			view="日常维护";
		}
		if(cellValue=="TROUBLE"){
			view="故障处理";
		}
		if(cellValue=="TASK"){
			view="通用任务";
		}
		return view;
	}
	function satisfactionFormatter(cellValue, options, rowObjec){
		var view = "";
		if(cellValue=="10"){
			view="非常满意";
		}
		if(cellValue=="9"){
			view="满意";
		}
		if(cellValue=="8"){
			view="一般(较差)";
		}
		if(cellValue=="7"){
			view="不满意";
		}
		if(cellValue=="6"){
			view="非常不满意";
		}
		if(cellValue=="no"){
			view="未回复";
		}
		return view;
	}
	//查询
	function query() {
		jQuery("#satisfactionid").jqGrid().setGridParam({
			postData : {
				taskType : jQuery("#satisfy_tasktype").val(),
				satisfaction : jQuery("#satisfy_satisfaction").val(),
				regionId : jQuery("#satisfy_regionid").val(),
				orgId : jQuery("#satisfy_orgid").val(),
				replyTimeStart : jQuery("#satisfy_createDateMin").val(),
				replyTimeEnd : jQuery("#satisfy_createDateMax").val()
			}
		}).trigger("reloadGrid");
	}
	function searchRegion(url) {
		var val = showRegion(url);
		if (!!val) {
			jQuery("#satisfy_regionid").val(val[0]);
			jQuery("#regionname").val(val[1]);
		}
	}
	function searchOrg(url) {
		var val = showOrg(url);
		if (!!val) {
			jQuery("#satisfy_orgid").val(val[0]);
			jQuery("#orgname").val(val[1]);
		}
	}
</script>
	</head>
	<body>
		<form id="workorderForm" name="form">
			<div id="header">
				<div class="title_bg">
					<div class="title">
						当前位置-客户满意度评价查询列表
					</div>
				</div>
				<div class="tabcontent">
					<table cellspacing="0" cellpadding="0" border="0" align="center">
						<tr>
							<th>
								工作类型：
							</th>
							<td>
								<select class="inputtext" id="satisfy_tasktype" name="taskType">
									<option value="">
										不限
									</option>
									<option value="PLAN">
										日常维护
									</option>
									<option value="TROUBLE">
										故障处理
									</option>
									<option value="TASK">
										通用任务
									</option>
								</select>
							</td>
							<th>
								评价级别：
							</th>
							<td>
								<select class="inputtext" id="satisfy_satisfaction"
									name="satisfaction">
									<option value="">
										不限
									</option>
									<option value="10">
										非常满意
									</option>
									<option value="9">
										满意
									</option>
									<option value="8">
										一般(较差)
									</option>
									<option value="7">
										不满意
									</option>
									<option value="6">
										非常不满意
									</option>
									<option value="no">
										未回复
									</option>
								</select>
							</td>

						</tr>
						<tr>
							<th>
								日期：
							</th>
							<td>
								<input id="satisfy_createDateMin" name="createDateMin"
									type="text" class="Wdate inputtext" style="width: 125px;"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
								至
								<input id="satisfy_createDateMax" name="createDateMax"
									type="text" class="Wdate inputtext" style="width: 125px;"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'satisfy_createDateMin\')}'})" />
							</td>
							<th>
								地市：
							</th>
							<td>
								<input id="regionname" name="regionname" class="inputtext"
									readonly="readonly" value="" />
								<a
									href="javascript:searchRegion('${ctx}/commonaccess!getregion.action');">
									<img border="0" src="${ctx}/css/images/selectcode.gif" /> </a>
								<input id="satisfy_regionid" name="regionId" type="hidden"
									value="" />
							</td>
						</tr>
						<tr>
							<th>
								代维公司：
							</th>
							<td>
								<input id="orgname" name="" orgname"" class="inputtext"
									readonly="readonly" value="" />
								<a
									href="javascript:searchOrg('${ctx}/commonaccess!getorg.action?orgtype=2');">
									<img border="0" src="${ctx}/css/images/selectcode.gif" /> </a>
								<input id="satisfy_orgid" name="orgId" type="hidden" value="" />
							</td>
							<th>
							</th>
							<td>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="button" onclick="query();" class="button"
									value="查询" />
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div id="content" align="center" style="padding-top: 2px">
				<table id="satisfactionid"></table>
				<div id="satisfactionpager"></div>
			</div>
		</form>
	</body>
</html>