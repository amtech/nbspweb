<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="${ctx }/js/jQuery/jqueryui/css/redmond/jquery-ui.custom.css"
	type="text/css" rel="stylesheet" />
<link href="${ctx }/js/jQuery/jqgrid/css/ui.jqgrid.css" type="text/css"
	rel="stylesheet" />
<link href="${ctx }/js/WdatePicker/skin/WdatePicker.css" type="text/css"
	rel="stylesheet" />
<link href="${ctx}/js/jQuery/layout/jquery.layout.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="${ctx}/js/jQuery/layout/jquery.layout-latest.min.js"></script>

<script type="text/javascript"
	src="${ctx}/js/jQuery/jqueryui/jquery-ui.custom.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jqgrid/grid.locale-cn.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/WdatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${ctx}/wplan/plan/js/patrolinfo_common.js"></script>
<title>工单统计及时率列表</title>
<script>
	jQuery(function(){
		setContextPath('${ctx }');
	//使用层布局
	jQuery("#overtimegrid").jqGrid({    
		url: "${ctx}/workflow/faultHandledOverTimeAction!listdata.action?parameter.businessType=${businessType}",   
		datatype: "json",    
		mtype: 'POST',
		rownumbers: true,
		colNames:['故障标题','故障开始时间', '故障结束时间','处理期限','故障处理单位','超时时长(单位分钟)'],
		colModel:[
		          {name:'TROUBLE_TITLE',id:'TROUBLE_TITLE',sortable:false},
		          {name:'TROUBLE_TIME',id:'TROUBLE_TIME',sortable:false},
		          {name:'REPLY_TIME',id:'REPLY_TIME',sortable:false},
		          {name:'DEADLINE',id:'DEADLINE',sortable:false},
		          {name:'CONTRACTORNAME',id:'CONTRACTORNAME',sortable:false},
		          {name:'OVERTIME_LENGTH',id:'OVERTIME_LENGTH',sortable:false}
		          ],      
		rowNum:10,
		autowidth:true,
		height: "100%",
		rowList:[10,20,30],    
		pager: '#timelyratepager',
		shrinkToFit:true,
		viewrecords: true, 
		hidegrid: false, 
		prmNames: {page:"pageNo",rows:"rows", sort: "sidx",order: "sord"},   
		jsonReader: {
               root:"result" ,                // 数据行（默认为：rows） 
               page: "pageNo" ,            // 当前页 
               total: "totalPages" ,    // 总页数 
               records: "totalCount",     // 总记录数 
               repeatitems: false,
               id:"0"
               }
		  }).navGrid('#timelyratepager',{edit:false ,add:false ,del:false,search:false,sortable:false  });
	});
	
		//查询
	function query() {
		jQuery("#overtimegrid").jqGrid().setGridParam({
            postData: {
            	'parameter.startTime':jQuery("#createtime").val(),
            	'parameter.endTime':jQuery("#endtime").val(),
            	'parameter.contractorId':jQuery("#orgid").val()
            	} 
            }).trigger("reloadGrid");

	}
</script>
</head>
<body>
	<form id="patrolinfoForm" name="patrolinfoForm" method="post">
		<div class="title_bg">
			<div id="title" class="title">当前位置-故障统计超时列表</div>
		</div>
		<div class="framecontentBottom">
			<table cellspacing="0" cellpadding="0" border="0" align="center">
				<tr>
					<th>代维公司：</th>
					<td><input id="orgname" name="orgname" class="inputtext"
						readonly="readonly" /><a
						href="javascript:searchOrg('${ctx}/commonaccess!getorg.action');">
							<img border="0"
								src="${ctx}/css/images/selectcode.gif" />
					</a> <input id="orgid" name="orgid" type="hidden" /></td>
					<th>开始日期：</th>
					<td><input id="createtime" name="createtime" type="text"
						value="" class="Wdate " style="width: 100px"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endtime\')}'})" />
						结束日期 ：<input id="endtime" name="endtime" type="text" class="Wdate"
						value="" style="width: 100px"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'createtime\')}'})" />
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center"><input type="button"
						onclick="query();" class="button" value="查询">
						<baseinfo:expexcel cls="exprotButton"  businessId="faultovertimelist" name="导出excel"></baseinfo:expexcel>
					</td>
				</tr>
			</table>
		</div>
		<table id="overtimegrid"></table>
		<div id="overtimepager"></div>
	</form>
</body>

</html>