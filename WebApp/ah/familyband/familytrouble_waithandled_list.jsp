<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${ctx}/ah/familyband/js/familyband_common.js"></script>
<title>待处理巡检表</title>
<script type="text/javascript">
	jQuery(function(){
	var jqgrid=jQuery("#faultgrid").jqGrid({    
			url: "${ctx}/ah/ahFamilyBandTroubleAction!waithanledtroublelist.action?id=${code.id}&status=0",   
			datatype: "json",    
			mtype: 'GET',
			colNames:[ '序号','隐患设备类型','隐患位置','隐患描述','预计完成时间','操作'],
			colModel:[
			          {name:'ORDERNUMBER',id:'ORDERNUMBER',sortable:false},
			          {name:'DEVICETYPE',id:'DEVICETYPE',sortable:false},
			          {name:'POSITION',id:'POSITION',sortable:false},
			          {name:'REMARK',id:'REMARK',sortable:false},
			          {name:'EXPECTENDTIME',id:'EXPECTENDTIME',sortable:false},
			          {name:'ID',id:'ID',sortable:false,width : 60,formatter:troubleprocessFmatter}		
			          ],      
			autowidth:true,
		    shrinkToFit:true,
			viewrecords: true, 
			hidegrid: false, 
			jsonReader: {
				 root:"root" ,      
          	   repeatitems: false,
	               id:"ID"
	               }   
			  });
		$(window).wresize(function(){
			grid_resize(jqgrid);
		});
		grid_resize(jqgrid);
    });
</script>
</head>
<body>
	<form id="patrolinfoForm" name="patrolinfoForm" method="post">
		<div class="title_bg">
			<div id="title" class="title">当前位置-待处理隐患列表</div>
		</div>
		<div class="tabcontent">
			<table border="0" align="center">
				<tr>
					<th>代维公司：</th>
					<td><baseinfo:org displayProperty="organizename" id="${code.contractorId }"></baseinfo:org>
					</td>
					<th>巡检人：</th>
					<td><baseinfo:user displayProperty="username" id="${code.patrolmanId }"></baseinfo:user>
					</td>
				</tr>
				<tr>
					<th>巡检开始时间：</th>
					<td><fmt:formatDate value="${code.startTime }"
							pattern="yyyy-MM-dd HH:mm:ss" />
					</td>
					<th>巡检结束时间：</th>
					<td><fmt:formatDate value="${code.endTime }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				<tr>
					<th>覆盖用户数：</th>
					<td>${code.userNum }</td>
					<th>巡检覆盖范围：</th>
					<td>${code.range }</td>
				</tr>
				<tr>
					<th>发现隐患数：</th>
					<td>${code.userNum }</td>
				</tr>
			</table>
		</div>
		<br>
		<div id="content" align="center" style="padding-top: 2px">
			<table id="faultgrid"></table>
		</div>
		<div style="text-align: center; margin-top: 10px">
			<input name="" value="返回" type="button" class="button"
				onclick="history.go(-1);" />
		</div>
	</form>
</body>

</html>