<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html>
<head>
<title>家庭宽带隐患查询</title>
<link href="${ctx }/js/WdatePicker/skin/WdatePicker.css" type="text/css"
	rel="stylesheet" />

<script type="text/javascript"
	src="${ctx}/js/WdatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${ctx}/ah/familyband/js/familyband_common.js"></script>	
<script type="text/javascript">
	jQuery(function(){
		//家庭宽带列表
	  var jqgrid=jQuery("#patrolgrid").jqGrid({    
			url: "${ctx}/ah/ahFamilyBandTroubleAction!listdata.action",   
			datatype: "json",    
			mtype: 'POST',
			rownumbers: true,
			colNames:['代维公司','巡检人', '发现隐患数','未处理隐患数','巡检开始时间','巡检结束时间','操作'],
			colModel:[
			          {name:'ORGNAME',id:'ORGNAME',sortable:false,width : 50},
			          {name:'USERNAME',id:'USERNAME',sortable:false,width : 50},
			          {name:'TROUBLENUM',id:'TROUBLENUM',sortable:false,width : 80},
			          {name:'UNHANDLETROUBLENUM',id:'UNHANDLETROUBLENUM',sortable:false,width : 80},
			          {name:'STARTTIME',id:'STARTTIME',sortable:false,width : 80},
			          {name:'ENDTIME',id:'ENDTIME',sortable:false,width : 80},
			          {name:'ID',id:'ID',sortable:false,width : 60,formatter:familytroubleFmatter}			          
			          ],      
			rowNum:15,
			rowList:[15,20,30],    
			pager: '#patrolpager',
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
			  }).navGrid('#patrolpager',{edit:false ,add:false ,del:false,search:false,sortable:false  });
		$(window).wresize(function(){
			grid_resize(jqgrid);
		});
		grid_resize(jqgrid);
});

		//查询
	function query() {
		jQuery("#patrolgrid").jqGrid().setGridParam({
            postData: {
            	orgid:jQuery("#orgid").val(),
            	creatername:jQuery("#creatername").val(),
            	starttime:jQuery("#starttime").val(),
            	endtime:jQuery("#endtime").val()
            	} 
            }).trigger("reloadGrid");


	}
		/**
	 * 搜索代维单位
	 * @param url
	 */
	function getorg(url) {
		var val = showOrg(url);
		if (!!val) {
			jQuery("#orgid").val(val[0]);
			jQuery("#orgname").val(val[1]);
		}
	}
</script>
</head>
<body>
	<form id="optForm" method="post">
		<div class="title_bg">
			<div id="title" class="title">当前位置-家庭宽带隐患查询</div>
		</div>
		<div class="tabcontent">
			<table cellspacing="0" cellpadding="0" border="0" align="center">
				<tr>
					<th>代维公司：</th>
					<td>
						<baseinfo:org displayProperty="ORGANIZENAME" id="${LOGIN_USER.orgId }"></baseinfo:org>
						<input id="orgid" name="orgid" value="${LOGIN_USER.orgId }" type="hidden"/>
					</td>
					<th>巡检人：</th>
					<td><input id="creatername" name="creatername" style="width:198px"
						class="inputtext" type="text" maxlength="60" />
					</td>
				</tr>
				<tr>
					<th>巡检开始时间：</th>
						<td><input id="starttime" name="starttime" type="text"
							value="" class="Wdate " style="width: 198px"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'starttime\')}'})" />
						</td>
					<th>巡检结束时间：</th>
						<td><input id="endtime" name="endtime" type="text"
							class="Wdate inputtext" value="" style="width: 198px"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'endtime\')}'})" />
						</td>
				</tr>
				<tr>
					<td colspan="4" align="center"><input type="button"
						class="button" onclick="query();" value="查询"> 
					</td>
				</tr>
			</table>
		</div>
		<br/>
		<div id="content" align="center" style="padding-top: 2px">
			<table id="patrolgrid"></table>
			<div id="patrolpager"></div>
		</div>
	</form>
</body>

</html>