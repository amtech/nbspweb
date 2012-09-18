<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${ctx}/js/highchart/highcharts.js"></script>
<script type="text/javascript"
	src="${ctx}/frames/default/js/basework.js"></script>
<script>
	jQuery(function(){
		terminal(${terminalchart});
		jQuery.ajax({
			url : '${ctx}/desktop/basework!getreslinechart.action',
			dataType : "json",
			type:'GET',
			cache:true,
			async:true,
			success : function(result) {
				if(!!result)
					resource(result);
			},
			error : function() {
				//alert("获取数据异常！");
			}
		});
		 jQuery.ajax({
				url : '${ctx}/desktop/basework!getleavechart.action',
				dataType : "json",
				type:'GET',
				cache:true,
				async:true,
				success : function(result) {
					if(!!result)
						personnel(result);
				},
				error : function() {
					//alert("获取数据异常！");
				}
			});	
	});
</script>
</head>

<body>
	<div id="main">
		<div class="parts bg3">
			<div class="maintitle">
				<span class="bspan">代维人员流动情况</span>
			</div>
			<div class="content" id="personnel"
				style="width: 580px; height: 180px; margin: 5px"></div>
		</div>
		<div class="parts bg1" style="margin:0">
			<div class="maintitle">
				<span class="bspan">代维维护终端情况</span>
			</div>
			<div class="content" id="terminal"
				></div>
		</div>
		<br class="all">

		<div class="parts bg3">
			<div class="maintitle">
				<span class="bspan">维护的资源</span>
			</div>
			<div class="content" id="resource"
				style="width: 580px; height: 180px; margin: 5px">
				<span class="brief">呈现各地市，以及代维公司的维护资源数量。</span>
			</div>
		</div>
		<div class="parts bg1" style="margin:0">
			<div class="maintitle">
				<span class="bspan">资质，证书到期提醒</span> <a href="#" class="more" onclick="openDialog('${ctx}/desktop/basework!validperiodedCertificatesMoreList.action');">更多</a>
			</div>
			<div class="content">
				<table id="datatable" cellspacing="0">
					<thead>
						<tr>
							<th>证书持有人</th>
							<th>证书名称</th>
							<th>到期时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${validperiodedCertificatesList }"
							end="3">
							<tr>
								<td>${item.ORGNAME }</td>
								<td>${item.CERTIFICATENAME }</td>
								<td>${item.VALIDPERIOD }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<br clear="all">
	</div>
</body>
</html>