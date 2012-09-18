<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
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
In.add('common',{path:'${ctx}/common/js/common.js',type:'js',charset:'utf-8'});
In.add('jgcss',{path:'${ctx}/js/jQuery/jqgrid/css/ui.jqgrid.css',charset:'utf-8',rely:['jquijs']});
In.add('jgjs',{path:'${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js',type:'js',charset:'utf-8',rely:['jgcss']});
In.add('jgcn',{path:'${ctx}/js/jQuery/jqgrid/grid.locale-cn.js',type:'js',charset:'utf-8',rely:['jgjs']});
</script>
<title>RFID巡检信息</title>
</head>
<body>
	<form id="patrolinfoForm" name="patrolinfoForm" method="post">
		<div class="title_bg">
			<div id="title" class="title">当前位置-RFID巡检明细</div>
		</div>
		<div class="tabcontent">
			<table border="0" align="center">
				<tr>
					<th>站点名称：</th>
					<td>${rfidMap.RESOURCE_NAME }</td>
					<th>专业类型：</th>
					<td>${rfidMap.RESOURCE_TYPENAME }</td>
				</tr>
				<tr>
					<th>代维公司：</th>
					<td>${rfidMap.ORGNAME }</td>
					<th>巡检组：</th>
					<td>${rfidMap.PATROLGROUPNAME }</td>
				</tr>
				<tr>
					<th>巡检人员：</th>
					<td>${rfidMap.PATROLMANNAME }</td>
					<th>联系电话：</th>
					<td>${rfidMap.PHONE }</td>
				</tr>
				<tr>
					<th>进站时间：</th>
					<td><fmt:formatDate value="${rfidMap.ARRIVE_TIME}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<th>离站时间：</th>

					<td><fmt:formatDate value="${rfidMap.END_TIME}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				<tr>
					<td colspan="4">
						<div id="tab_process">
							<ul>
								<li><a href="#overrfid">已巡检RFID</a>
								</li>
								<li><a href="#lostrfid">未巡检RFID</a>
								</li>
							</ul>
							<div id="overrfid">
								<table id="overrfidgrid"></table>
								<div id="overrfidpager"></div>
							</div>
							<div id="lostrfid">
								<table id="lostrfidgrid"></table>
								<div id="lostrfidpager"></div>
							</div>
						</div></td>
				</tr>
			</table>
		</div>
		<div style="text-align: center; margin-top: 10px">
			<input name="" value="返回" type="button" class="button"
				onclick="history.go(-1);" />
		</div>
	</form>
</body>
<script type="text/javascript">
In.ready('jgcn', function() {
var tab=jQuery("#tab_process").tabs({ cache: true,selected: 0 }); //缓存tab页
jQuery("#overrfidgrid").jqGrid({    
	url: "${ctx}/wplan/patrolinfoExecuteAction!rfidover.action?id=${rfidMap.ID}",   
	datatype: "json",    
	mtype: 'GET',
	colNames:['巡检时间','巡检地址', 'RFID','LACCI'],
	colModel:[
	          {name:'PATROLD_TIME',id:'PATROLD_TIME',sortable:false},
	          {name:'ADDRESS',id:'ADDRESS',sortable:false},
	          {name:'RFID',id:'RFID',sortable:false},
	          {name:'LACCI',id:'LACCI',sortable:false}
	          ],      
	rowNum:10,
	autowidth:true,
	rowList:[10,30,50,100],    
	pager: '#overrfidpager',
	prmNames: {page:"pageNo",rows:"rows", sort: "sidx",order: "sord"},   
	shrinkToFit:true,
	viewrecords: true, 
	jsonReader: {
           root:"result" ,                // 数据行（默认为：rows） 
           page: "pageNo" ,            // 当前页 
           total: "totalPages" ,    // 总页数 
           records: "totalCount",     // 总记录数 
           repeatitems: false,
           id:"0"
           }
	  }).navGrid('#overrfidpager',{edit:false ,add:false ,del:false,search:false,sortable:false  });
var initialized = false; //是否已初始化
tab.bind('tabsshow', function(event, ui) {
	//选择二个tab时，生成未巡检rfid
	if (ui.index == 1 && !initialized){ 
jQuery("#lostrfidgrid").jqGrid({    
	url: "${ctx}/wplan/patrolinfoExecuteAction!rfidlost.action?id=${rfidMap.ID}&rtype=${rfidMap.RESOURCE_TYPE}&rid=${rfidMap.RESOURCE_ID}",   
	datatype: "json",    
	mtype: 'POST',
	colNames:[ 'RFID','巡检地址'],
	colModel:[
	          {name:'RFID',id:'RFID',sortable:false,width:'250'},
	          {name:'ADDRESS',id:'ADDRESS',sortable:false,width:'250'}
	          ],      
	rowNum:10,
	autowidth:true,
	shrinkToFit:true,
	viewrecords: true,
	rowList:[10,20,30],    
	pager: '#lostrfidpager',
	prmNames: {page:"pageNo",rows:"rows", sort: "sidx",order: "sord"},   
	jsonReader: {
           root:"result" ,                // 数据行（默认为：rows） 
           page: "pageNo" ,            // 当前页 
           total: "totalPages" ,    // 总页数 
           records: "totalCount",     // 总记录数 
           repeatitems: false,
           id:"0"
           }
	  }).navGrid('#lostrfidpager',{edit:false ,add:false ,del:false,search:false,sortable:false });
     }
     initialized = true; 
});
});
</script>
</html>