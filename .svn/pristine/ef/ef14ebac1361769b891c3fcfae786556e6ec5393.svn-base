<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx }/js/jQuery/jqgrid/css/ui.jqgrid.css" type="text/css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${ctx}/js/jQuery/jqgrid/grid.locale-cn.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/WdatePicker/WdatePicker.js"></script>
<link href="${ctx }/js/jQuery/ztree/css/zTreeStyle/zTreeStyle.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript"
	src="${ctx}/js/jQuery/ztree/jquery.ztree.all.min.js"></script>
<script type="text/javascript"
	src="${ctx}/ah/workorder/js/workorder_common.js"></script>
<title>移动考核查询统计列表</title>
<script type="text/javascript">
	jQuery(function() {
		setContextPath('${ctx }');
		var jqgrid = jQuery("#mobileexamgrid")
				.jqGrid(
						{
							url : "${ctx}/ah/MobileExamFormAction!queryAnalysisData.action",
							datatype : "json",
							mtype : 'POST',
							rownumbers : true,
							colNames : [ '姓名', '职位', '代维公司', '专业', '考核月份',
									'区域','自评分', '考评分','操 作' ],
							colModel : [ {
								name : 'NAME',
								id : 'NAME',
								sortable : false
							}, {
								name : 'POSITION',
								id : 'POSITION',
								sortable : false
							}, {
								name : 'CONTRACTOR',
								id : 'CONTRACTOR',
								sortable : false
							}, {
								name : 'BUSINESS',
								id : 'BUSINESS',
								sortable : true
							}, {
								name : 'MONTH',
								id : 'MONTH',
								sortable : true
								,formatter:'date',formatoptions: {newformat:'Y-m'} 
							}, {
								name : 'REGION',
								id : 'region',
								sortable : false
							}, {
								name : 'SELF_ASSE_NUM',
								id : 'SELF_ASSE_NUM',
								sortable : false
							}, {
								name : 'EXAM_ASSE_NUM',
								id : 'EXAMASSENUM',
								sortable : false
							}, {
                                name:'ID',
                                id:'ID',
                                sortable:false,
                                formatter:detailFmatter
                            } ],
							rowNum : 10,
							autowidth : true,
							rowList : [ 10, 20, 30 ],
							pager : '#mobileexampager',
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
								root: function(obj) { return obj.listJson.result; }, // 数据行（默认为：rows） 
     							page: function(obj) { return obj.listJson.pageNo; }, // 当前页 
     							total: function(obj) { return obj.listJson.totalPages; }, // 总页数 
     							records: function(obj) { 
     							if(obj.mapJson.status==null|| obj.mapJson.status==''){
     								jQuery("#hasedCheckUserCount").html(obj.mapJson.hasedCheckUserCount);
     								jQuery("#noneCheckUserCount").html(obj.mapJson.noneCheckUserCount);
     								jQuery("#daiweiOKCount").html(obj.mapJson.daiweiOKCount);
     							}
     							return obj.listJson.totalCount; }, // 总记录数 
							
								repeatitems : false
							}
						}).navGrid('#mobileexampager', {
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
	 // 操作
        function detailFmatter(cellvalue, options, rowObjec) {
        	  var view="";
        	  var str="";
        	  view= "<a style='color: blue;text-decoration: underline;' href=javascript:detailPerson('"
                    + cellvalue
                    + "','"
                    + rowObjec.SID
                    + "','"
                    + rowObjec.TABLE_ID
                    + "','"
                    + rowObjec.MAXFLOWNUM
                    + "')>"; 
        	str="查看</a>";
        	view =view+str;
            return view;
        }
        //设置参考
        function detailPerson(id, userid, tableid, maxflownum) {
            window.location.href = "${ctx}/ah/ContractorSelfRatingAction!input.action?personId="
                    + userid + "&id=" + id + "&tableid=" + tableid + "&maxflownum=" + maxflownum+"&flag=3";
        }
	//查询
	function query(status) {
		if(jQuery("#query_yearmonth").val()==''){
			alert("考核月份不能为空! ");
			return false;
		}
		jQuery("#mobileexamgrid").jqGrid().setGridParam(
				{
					postData : {
						regionId : jQuery("#regionid").val(),
						orgId : jQuery("#orgid").val(),
						businessType : jQuery(
								'select[name="query_businesstype"]').find(
								"option:selected").val(),
						postOffice : jQuery('select[name="query_personjob"]')
								.find("option:selected").val(),
						yearMonth : jQuery("#query_yearmonth").val(),
						Status : status
					}
				}).trigger("reloadGrid");
	}
	function exportALL() {
		alert("开始导出当前表格!");
	}
	
	//查找区域
	function searchQueryRegion(url){
		var val = showRegion(url);
		if (!!val) {
			jQuery("#regionid").val(val[0]);
			jQuery("#regionname").val(val[1]);
			jQuery("#orgid").val("");
			jQuery("#orgname").val("");
		}
	}
</script>
</head>
<body>
	<form id="mobileexamForm" name="form">
		<div id="header">
			<div class="title_bg">
				<div class="title">当前位置>汇总统计</div>
			</div>
		</div>
		<div class="tabcontent">
			<table id="tableContainer" border="0" align="center" cellpadding="3"
				cellspacing="0">
				<tr>
					<th>所属区域</th>
					<td><input id="regionname" class="inputtext"
						readonly="readonly" /> <a
						href="javascript:searchQueryRegion('${ctx}/commonaccess!getregion.action');">
							<img border="0" src="${ctx}/css/images/selectcode.gif" /> </a> <input
						type="hidden" id="regionid" /></td>
					<th>代维公司</th>
					<td><input id="orgname" name="orgname" style="width: 198px"
						class="inputtext" readonly="readonly" /><a
						href="javascript:getorg();"> <img border="0"
							src="${ctx}/css/images/selectcode.gif" /> </a><input id="orgid"
						name="orgid" type="hidden" /></td>
				</tr>
				<tr>
					<th>专业</th>
					<td><baseinfo:dicselector name="query_businesstype"
							cssClass="treetext" columntype="businesstype" type="select"
							isQuery="query"></baseinfo:dicselector></td>
					<th>职位</th>
					<td><baseinfo:dicselector name="query_personjob"
							cssClass="treetext" columntype="job_type" type="select"
							isQuery="query"></baseinfo:dicselector></td>
				</tr>
				<tr>
					<th>考核月份</th>
					<td>
						<%
							Date date = new Date();
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
							String dateString = formatter.format(date);
						%> <input id="query_yearmonth" name="yearmonth" type="text"
						class="Wdate inputtext required" style="width: 125px;"
						onfocus="WdatePicker({dateFmt:'yyyy-MM'})"
						value='<%=dateString%>' /> <font size="2" color="red">*</font>
					</td>
					<th>操作</th>
					<td align="right"><input type="button" class="button"
						value="查询" onclick="query('');" /> <input type="button"
						class="button" value="重置" onclick="Reset();" />
					</td>
				</tr>
			</table>

		</div>
		<div id="content" align="center" style="padding-top: 2px;">
			<table id="tableContainer" border="0" align="center" cellpadding="8"
				cellspacing="0">
				<tr>
					<td>&nbsp; &nbsp; 参加考核人数：<a href="javascript:query('0')"
						id="hasedCheckUserCount"></a> ${mapJson.hasedCheckUserCount } 人</td>
					<td>&nbsp; &nbsp; 未参加考核人数：<a href="javascript:query('1')"
						id="noneCheckUserCount"></a>${mapJson.noneCheckUserCount} 人</td>
					<td>&nbsp; &nbsp; 代维确认数：<a href="javascript:query('-2')"
						id="daiweiOKCount"></a>${mapJson.daiweiOKCount} 人 &nbsp; &nbsp;
						&nbsp;</td>
					<td><baseinfo:expexcel cls="exprotButton"
							businessId="mobilequeryanalysislist" name="导出excel"></baseinfo:expexcel>
					</td>
				</tr>
			</table>

			<table id="mobileexamgrid"></table>
			<div id="mobileexampager"></div>
		</div>
	</form>
</body>
</html>