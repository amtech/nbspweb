<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx }/js/jQuery/jqgrid/css/ui.jqgrid.css" type="text/css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${ctx}/js/jQuery/jqgrid/grid.locale-cn.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/artdialog/jquery.artDialog.js?skin=default"></script>
		
<title>选择资源</title>
<script>
	jQuery(function(){
		setContextPath('${ctx }');
		var multiselect=false;
		var actionUrl='${ctx}/commonaccess!getresourceinfolist.action?orgid=${orgid}';
		if("${businessType}"!=""){
			actionUrl+='&businessType=${businessType}';
		}
	//使用层布局
  var jqgrid=jQuery("#resourcegrid").jqGrid({    
		url: actionUrl,   
		datatype: "json",    
		mtype: 'GET',
		rownumbers: true,
		colNames:['资源ID','站点编号','站点名称','站点地址','所属区域', '所属代维','所属巡检组','经度','纬度','LON','LAT','POINTID','资源类型','ORGID','PATROLGROUPID'],
		colModel:[
		          {name:'ID',id:'ID',sortable:false,hidden:true},
		          {name:'STATIONCODE',id:'STATIONCODE',sortable:false},
		          {name:'NAME',id:'NAME',sortable:false},
		          {name:'ADDRESS',id:'ADDRESS',sortable:false},
		          {name:'REGIONNAME',id:'REGIONNAME',sortable:false},
		          {name:'ORGNAME',id:'ORGNAME',sortable:false},
		          {name:'PATROLGROUPNAME',id:'PATROLGROUPNAME',sortable:false},
		          {name:'CT_X',id:'CT_X',sortable:false,hidden:true},
		          {name:'CT_Y',id:'CT_Y',sortable:false,hidden:true},
		          {name:'LON',id:'LON',sortable:false},
		          {name:'LAT',id:'LAT',sortable:false},
		          {name:'POINTID',id:'POINTID',sortable:false,hidden:true},
		          {name:'TYPE',id:'TYPE',sortable:false,hidden:true},
		          {name:'ORGID',id:'ORGID',sortable:false,hidden:true},
		          {name:'PATROLGROUPID',id:'PATROLGROUPID',sortable:false,hidden:true}
		          ],      
		rowNum:10,
		autowidth:true,
		rowList:[10,20,30],    
		pager: '#resourcepager',
		shrinkToFit:true,
		viewrecords: true, 
		hidegrid: false, 
		multiselect: ${multi},
		prmNames: {page:"pageNo",rows:"rows", sort: "sidx",order: "sord"},
		jsonReader: {root:"result" ,page: "pageNo" ,total: "totalPages" ,records: "totalCount",repeatitems: false,id:"POINTID"}
		  }).navGrid('#resourcepager',{edit:false ,add:false ,del:false,search:false,sortable:false  });
  jQuery(window).wresize(function(){
		grid_resize(jqgrid);
	});
	grid_resize(jqgrid);
	})
    //查询
	function query() {
		if("${businessType}"!=""){
		jQuery("#resourcegrid").jqGrid().setGridParam({
            postData: {
            	'rsparameter.rsname':jQuery("#rsname").val(),
            	'rsparameter.address':jQuery("#address").val(),
            	'rsparameter.stationcode':jQuery("#stationcode").val()
            	} 
            }).trigger("reloadGrid");
		}else{
		jQuery("#resourcegrid").jqGrid().setGridParam({
            postData: {
            	'businessType':jQuery("#businessType").val(),
            	'rsparameter.rsname':jQuery("#rsname").val(),
            	'rsparameter.address':jQuery("#address").val(),
            	'rsparameter.stationcode':jQuery("#stationcode").val()
            	} 
            }).trigger("reloadGrid");
		}
	}
	//选择确定
	function selectOK(){
		var jqgrid=jQuery("#resourcegrid");
		var ids;
		var arr=new Array();
		if(${multi}){
			ids = jqgrid.jqGrid('getGridParam','selarrrow'); 
			 for (var i = 0; i < ids.length; i++) {
				 var rowData = jqgrid.getRowData(ids[i]);
				 arr.push(rowData);
		       }
		}else{
			ids = jqgrid.jqGrid('getGridParam','selrow'); 
			var rowData = jqgrid.getRowData(ids);
			if(!!ids){
			arr.push(rowData);
			}
		}
		if(arr.length>0){
			window.returnValue = arr;
			window.close();
		}else{
			alert('请选择资源!');
		}
	}
</script>
</head>
<body>
	<form
		action="${ctx }/wplan/patrolinfoAction!list.action?type=${businesstype}"
		id="patrolinfoForm" name="patrolinfoForm" method="post">
		<div id="header">
			<div class="title_bg">
				<div class="title">选择站点</div>
			</div>
			<div class="tabcontent">
				<table cellspacing="0" cellpadding="0" border="0" align="center">
					<tr>
						<th>站点名称：</th>
						<td><input id="rsname" name="rsname" class="inputtext"
							type="text" maxlength="60" />
						</td>
						<th>专业类型：</th>
						<td>
							<c:if test="${not empty businessType }">
								<input id="businessType" name="businessType" type="hidden" value="${businessType }" />
								<baseinfo:dicselector name="" columntype="BUSINESSTYPE" type="view" keyValue="${businessType }"></baseinfo:dicselector>
							</c:if>
							<c:if test="${empty businessType }">
								<baseinfo:customselector name="businessType" data="${businessTypeMap}" isReversal="true" isQuery="query" cssClass="inputtext" id="businessType"></baseinfo:customselector>
							</c:if>
						</td>
					</tr>
					<tr>
						<th>站点编号：</th>
						<td><input id="stationcode" name="stationcode" class="inputtext"
							type="text" maxlength="60" /></td>
						<th>站点地址：</th>
						<td><input id="address" name="address" class="inputtext"
							type="text" maxlength="60" />
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center"><input type="button"
							onclick="query();" class="button" value="查询" /> <input
							type="button" onclick="selectOK();" class="button" value="选择确定" />
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div id="content" align="center" style="padding-top: 2px">
			<table id="resourcegrid"></table>
			<div id="resourcepager"></div>
		</div>
	</form>
</body>
</html>