<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/header.jsp"%>
<html>
	<head>
		<link href="${ctx }/js/WdatePicker/skin/WdatePicker.css"
			type="text/css" rel="stylesheet" />
		<link href="${ctx }/js/jQuery/ztree/css/zTreeStyle/zTreeStyle.css"
			type="text/css" rel="stylesheet" />
		<script type="text/javascript">
	jQuery(function() {
		//使用层布局
		var jqgrid = jQuery("#oilrecordgrid")
				.jqGrid(
						{
							url : "${ctx}/oil/oilRecordAction!listData.action?engineId=${entity.id}",
							datatype : "json",
							mtype : 'GET',
							rownumbers : true,
							colNames : [ '加油人', '加油时间', '加油量（升）' ],
							colModel : [ {
								name : 'RECORDER_NAME',
								id : 'RECORDER_NAME',
								sortable : false
							}, {
								name : 'RECORD_DATE_DIS',
								id : 'RECORD_DATE_DIS',
								sortable : false
							}, {
								name : 'QUANTITY',
								id : 'QUANTITY',
								sortable : false
							} ],
							rowNum : 10,
							autowidth : true,
							rowList : [ 10, 20, 30 ],
							pager : '#oilrecordpager',
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
						}).navGrid('#oilrecordpager', {
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
</script>
	</head>
	<body>
		<div class="title_bg">
			<div id="title" class="title">
				当前位置-查看油机加油记录信息
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
			</table>
			<div style="text-align: center; margin-top: 10px">
				<input value="返回" type="button" class="button"
					onclick="history.go(-1);" />
			</div>
			<div id="content" align="center">
				<table id="oilrecordgrid"></table>
				<div id="oilrecordpager"></div>
			</div>
		</div>
	</body>
</html>