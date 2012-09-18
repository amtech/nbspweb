<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<%@ include file="/common/meta.jsp"%>
		<link href="${ctx}/css/base.css" rel="stylesheet" type="text/css" />
		<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx }/js/in-min.js"
			autoload="true"
			core="${config.cdnurl}/cabletech/ui/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
In.add('jqui',{path:'${ctx}/js/jQuery/jqueryui/css/redmond/jquery-ui.custom.css'});
In.add('jquijs',{path:'${ctx}/js/jQuery/jqueryui/jquery-ui.custom.min.js',type:'js',charset:'utf-8',rely:['jqui']});
In.add('jquicn',{path:'${ctx}/js/jQuery/jqueryui/zh/jquery.ui.datepicker-zh-CN.js',type:'js',charset:'utf-8',rely:['jquijs']});
In.add('jresize',{path:'${ctx}/js/jQuery/jresize/jquery.wresize.js',type:'js',charset:'utf-8',rely:['jquicn']});
In.add('jgcss',{path:'${ctx}/js/jQuery/jqgrid/css/ui.jqgrid.css',charset:'utf-8',rely:['jresize']});
In.add('jgjs',{path:'${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js',type:'js',charset:'utf-8',rely:['jgcss']});
In.add('jgcn',{path:'${ctx}/js/jQuery/jqgrid/grid.locale-cn.js',type:'js',charset:'utf-8',rely:['jgjs']});
In.add('common',{path:'${ctx}/common/js/common.js',type:'js',charset:'utf-8'});
In.add('wdatejs',{path:'${ctx}/js/WdatePicker/WdatePicker.js',type:'js',charset:'utf-8'});
	In.ready('jgcn', 'common', 'wdatejs', function() {
		setContextPath('${ctx}');
		var jqgrid=jQuery("#assessgrid").jqGrid({    
			url: "${ctx}/assess/assessMonthAppraiseListAction!listData.action?",   
			datatype: "json",    
			mtype: 'GET',
			rownumbers: true,
			colNames:['考核表名称','考核月份','专业类型', '代维公司','考核成绩','考核人','考核时间','操作'],
			colModel:[
			          {name:'TABLE_NAME',id:'TABLE_NAME',sortable:false},
			          {name:'YEAR_MONTH',id:'YEAR_MONTH',sortable:false},
			          {name:'BUSINESS_TYPE',id:'BUSINESS_TYPE',sortable:false},
			          {name:'ORGNAME',id:'ORGNAME',sortable:false},
			          {name:'SCORE_DIS',id:'SCORE_DIS',sortable:false},
			          {name:'CREATER',id:'CREATER',sortable:false},
			          {name:'CREATE_DATE_DIS',id:'CREATE_DATE_DIS',sortable:false},
			          {name:'ID',id:'ID',sortable:false,formatter:viewFormatter}
			          ],      
			rowNum:15,
			autowidth:true,
			rowList:[15,30,50],    
			pager: '#assesspager',
			shrinkToFit:true,
			viewrecords: true, 
			hidegrid: false, 
			prmNames: {page:"pageNo",rows:"rows", sort: "sidx",order: "sord"},   
			jsonReader: {
		           root:"result" ,page: "pageNo" ,total: "totalPages" , 
		           records: "totalCount",repeatitems: false
		    }
		}).navGrid('#assesspager',{edit:false ,add:false ,del:false,search:false,sortable:false  });
		jQuery(window).wresize(function(){
			grid_resize(jqgrid);
		});
		grid_resize(jqgrid);
		});
</script>
</head>
	<body>
		<form id="searchForm" name="searchForm">
			<div>
				<div class="title_bg">
					<div class="title">
						当前位置-月度考核-成绩查询列表
					</div>
				</div>
				<div class="tabcontent">
					<table cellspacing="0" cellpadding="0" border="0" align="center">
						<tr>
							<th>
								代维公司：
							</th>
							<td>
								<input id="orgname" name="orgname" class="inputtext" readonly="readonly" value="" style="width: 220px;" />
								<a href="javascript:searchOrg('${ctx}/commonaccess!getorg.action?orgtype=2');"><img border="0" src="${ctx}/css/images/selectcode.gif" /> </a>
								<input id="orgid" name="orgid" type="hidden" value="" />
							</td>
							<th>
								区域：
							</th>
							<td>
								<input id="regionname" name="regionname" class="inputtext" readonly="readonly" value="" style="width: 220px;" />
								<a href="javascript:searchRegion('${ctx}/commonaccess!getregion.action');"><img border="0" src="${ctx}/css/images/selectcode.gif" /> </a>
								<input id="regionid" name="regionid" type="hidden" value="" />
							</td>
						</tr>
						<tr>
							<th>
								专业类型：
							</th>
							<td>
								<baseinfo:customselector name="businessType" data="${businessTypeMap}" isReversal="true" isQuery="query" cssClass="inputtext" id="businessType" style="width: 220px"></baseinfo:customselector>
							</td>
							<th>
								考核月份：
							</th>
							<td>
								<input id="yearmonth" name="yearmonth" type="text" class="Wdate inputtext" onfocus="WdatePicker({dateFmt:'yyyy-MM',maxDate:'%y-%M'})" style="width: 220px" />
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="button" onclick="query();" class="button" value="查询" />
								<!-- <baseinfo:expexcel cls="exprotButton"  businessId="wmaintainhandledlist" name="导出excel"></baseinfo:expexcel> -->
							</td>
						</tr>
					</table>
				</div>
			</div>
			<br>
			<div id="content" align="center">
				<table id="assessgrid"></table>
				<div id="assesspager"></div>
			</div>
		</form>
	</body>
	<script type="text/javascript">
	/**
	 * 列表查询方法
	 */
	function query() {
		var yearmonth=jQuery("#yearmonth").val();
		if(yearmonth!=""){
			yearmonth+="-01";
		}
		jQuery("#assessgrid").jqGrid().setGridParam({
			postData : {
				businessType : jQuery("#businessType").val(),
				regionId : jQuery("#regionid").val(),
				orgId : jQuery("#orgid").val(),
				yearmonth : yearmonth
			}
		}).trigger("reloadGrid");
	}
	/**
	 * 搜索区域树
	 * 
	 * @param url
	 */
	function searchRegion(url) {
		var val = showRegion(url);
		if (!!val) {
			jQuery("#regionid").val(val[0]);
			jQuery("#regionname").val(val[1]);
		}
	}
	/**
	 * 搜索组织树
	 * 
	 * @param url
	 */
	function searchOrg(url) {
		var val = showOrg(url);
		if (!!val) {
			jQuery("#orgid").val(val[0]);
			jQuery("#orgname").val(val[1]);
		}
	}
	function view(id){
		window.location.href = "${ctx}/assess/assessMonthAppraiseAction!view.action?id="+id+"&tableType=01";
	}
	function viewFormatter(cellValue,options,rowObject){
		var view="";
		view+="<a style='color: blue;text-decoration: underline;' href=javascript:view('" + cellValue + "')>查看</a>";
		return view;
	}
	</script>
</html>
