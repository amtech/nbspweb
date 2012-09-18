<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html>
<head>
<title>家庭宽带巡检记录</title>
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
			url: "${ctx}/ah/ahFamilyBandRecodeAction!listdata.action",   
			datatype: "json",    
			mtype: 'POST',
			rownumbers: true,
			colNames:['代维公司','巡检人', '覆盖用户数','发现隐患数','巡检开始时间','巡检结束时间','操作'],
			colModel:[
			          {name:'ORGNAME',id:'ORGNAME',sortable:false},
			          {name:'USERNAME',id:'USERNAME',sortable:false},
			          {name:'USERNUM',id:'USERNUM',sortable:false},
			          {name:'TROUBLENUM',id:'TROUBLENUM',sortable:false},
			          {name:'STARTTIME',id:'STARTTIME',sortable:false},
			          {name:'ENDTIME',id:'ENDTIME',sortable:false},
			          {name:'ID',id:'ID',sortable:false,formatter:operateFmatter}	          
			          ],      
			rowNum:15,
			rowList:[15,20,30],  
			shrinkToFit:true,
			autowidth:true,
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
	               id:"ID"
	               }
			  }).navGrid('#patrolpager',{edit:false ,add:false ,del:false,search:false,sortable:false  });
		$(window).wresize(function(){
			grid_resize(jqgrid);
		});
		grid_resize(jqgrid);
})


//操作按钮
function operateFmatter(cellvalue, options, rowObjec) {
	var view = '<a href="#" style="color: blue;text-decoration: underline;" onclick="see(\''+ rowObjec.ID + '\')">详细</a>';
	    view+='&nbsp;&nbsp;<a href="#" style="color: blue;text-decoration: underline;" onclick="edit(\''+ rowObjec.ID + '\')">修改</a>';
	return view;
}

// 搜索区域
function searchRegion(url) {
	var val = showRegion(url);
	if (!!val) {
		jQuery("#regionid").val(val[0]);
		jQuery("#regionname").val(val[1]);
	}
}

		//查询
	function query() {
		jQuery("#patrolgrid").jqGrid().setGridParam({
            postData: {
            	regionid:jQuery("#regionid").val(),
            	orgid:jQuery("#orgid").val(),
            	creatername:jQuery("#creatername").val(),
            	starttime:jQuery("#starttime").val(),
            	endtime:jQuery("#endtime").val()
            	} 
            }).trigger("reloadGrid");


	}
</script>
</head>
<body>
	<form id="optForm" method="post">
		<div class="title_bg">
			<div id="title" class="title">当前位置-家庭宽带巡检记录</div>
		</div>
		<div class="tabcontent">
			<table cellspacing="0" cellpadding="0" border="0" align="center">
				<c:if test="${show_region=='show' }">
				<tr>
					<th>区域：</th>
					<td colspan="3"><input id="regionname" name="regionname"
						style="width: 198px" class="inputtext" readonly="readonly" /><a
						href="javascript:searchRegion('${ctx}/commonaccess!getregion.action');">
							<img border="0" src="${ctx}/css/images/selectcode.gif" /> </a><input
						id="regionid" name="regionid" type="hidden" /></td>
				</tr>
				</c:if>
				<c:if test="${show_region!='show' }">
					<input id="regionid" name="regionid" value="${LOGIN_USER.regionId }" type="hidden" />
				</c:if>
				<tr>
					<th>巡检开始时间：</th>
					<td><input id="starttime" name="starttime" type="text"
						value="" class="Wdate " style="width: 198px"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endtime\')}'})" />
					</td>
					<th>巡检结束时间：</th>
					<td><input id="endtime" name="endtime" type="text"
						class="Wdate inputtext" value="" style="width: 198px"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'starttime\')}'})" />
					</td>
				</tr>
				<tr>
					<th>代维公司：</th>
					<td><input id="orgname" name="orgname" style="width: 198px"
						class="inputtext" readonly="readonly" /><a
						href="javascript:getorg();"> <img border="0"
							src="${ctx}/css/images/selectcode.gif" /> </a><input id="orgid"
						name="orgid" type="hidden" /></td>
					<th>巡检人：</th>
					<td><input id="creatername" name="creatername"
						style="width:198px" class="inputtext" type="text" maxlength="60" />
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center"><input type="button"
						class="button" onclick="query();" value="查询"></td>
				</tr>
			</table>
		</div>
		<div id="content" align="center" style="padding-top: 2px">
			<table id="patrolgrid"></table>
			<div id="patrolpager"></div>
		</div>
	</form>
</body>

</html>