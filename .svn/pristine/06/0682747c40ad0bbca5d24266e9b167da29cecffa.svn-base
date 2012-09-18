<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/header.jsp"%>
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
<%@ include file="bootstrap.jsp"%>
<script type="text/javascript" src="${ctx}/common/js/common.js"></script>
<script type="text/javascript">
function exportLs(type,n){ 
	var year ,month;
	if(n=='1'){
		  year = jQuery("#ryear").val();
		  month = jQuery("#rmonth").val();
	}	if(n=='2'){
		  year = jQuery("#cyear").val();
		  month = jQuery("#cmonth").val();
	}	 if(n=='3'){
		  year = jQuery("#syear").val();
		  month = jQuery("#smonth").val();
	}
	var url = "${ctx}/monthcost/monthstatistical!detailExport.action?type="+type+"&lab="+n+"&month="
		+ month + "&year=" + year;
	window.location.href =url;
} 
	jQuery(function() {
		jQuery("#lab1").click(function() {
			jQuery("#rmonth").val("");
			jQuery("#ryear").val("");
			jQuery("#tbody1").html("");
		});
		jQuery("#lab2").click(function() {
			jQuery("#cmonth").val("");
			jQuery("#cyear").val("");
			jQuery("#tbody2").html("");
		});
		jQuery("#lab3").click(function() {
			jQuery("#smonth").val("");
			jQuery("#syear").val("");
			jQuery("#tbody3").html("");
		});
		jQuery("#tongji1")
				.click(
						function() {
							var year = jQuery("#ryear").val();
							var month = jQuery("#rmonth").val();
							if (year == "") {
								alert("年份不可以不选择");
								return false;
							}
							if (month == "") {
								alert("月份不可以不选择");
								return false;
							}
							var url = "${ctx}/monthcost/monthstatistical!staticByType.action?type=Other&lab=1&month="
									+ month + "&year=" + year;
							window.location = url;
							jQuery("#ryear").val('${year}');
							jQuery("#rmonth").val('${month}');
						});
		for ( var i = 0; i <= 2; i++) {
			table_rowspan("#table4Search1", i);
		}
		jQuery("#tongji2")
				.click(
						function() {
							var year = jQuery("#cyear").val();
							var month = jQuery("#cmonth").val();
							if (year == "") {
								alert("年份不可以不选择");
								return false;
							}
							if (month == "") {
								alert("月份不可以不选择");
								return false;
							}
							var url = "${ctx}/monthcost/monthstatistical!staticByType.action?type=Other&lab=2&month="
									+ month + "&year=" + year;
							window.location = url;
							jQuery("#cyear").val('${year}');
							jQuery("#cmonth").val('${month}');
						});
		for ( var i = 0; i <= 2; i++) {
			table_rowspan("#table4Search2", i);
		}
		jQuery("#tongji3")
				.click(
						function() {
							var year = jQuery("#syear").val();
							var month = jQuery("#smonth").val();
							if (year == "") {
								alert("年份不可以不选择");
								return false;
							}
							if (month == "") {
								alert("月份不可以不选择");
								return false;
							}
							var url = "${ctx}/monthcost/monthstatistical!staticByType.action?type=Other&lab=3&month="
									+ month + "&year=" + year;
							window.location = url;
							jQuery("#syear").val('${year}');
							jQuery("#smonth").val('${month}');
						});
		for ( var i = 0; i <= 2; i++) {
			table_rowspan("#table4Search3", i);
		}
	});
</script>
</head>
<body>
	<div class="title_bg">
		<div class="title">当前位置-其它费用统计</div>
	</div>
	<div class="tabbable" style="background: #ffffff; padding: 10px;">
		<ul class="nav nav-tabs">
			<li <c:if test="${labq=='1'}">class='active'</c:if>><a
				href="#tab1-1" data-toggle="tab" id="lab1">按地市统计</a></li>
			<li <c:if test="${labq=='2'}">class='active'</c:if>><a
				href="#tab1-2" data-toggle="tab" id="lab2">按代维公司统计</a></li>
			<li <c:if test="${labq=='3'}">class='active'</c:if>><a
				href="#tab1-3" data-toggle="tab" id="lab3">按费用类型统计</a></li>
		</ul>
		<div class="tab-content"
			style="padding-bottom: 9px; border-bottom: 1px solid #ddd;">
			<div class="tab-pane  <c:if test="${labq=='1'}"> active  </c:if>"
				id="tab1-1">
				<p>
					年份:<select id="ryear" name="ryear">
						<option value="">--请选择--</option>
						<option value="2012"
							<c:if test="${year=='2012'}">selected='selected'</c:if>>2012</option>
						<option value="2013"
							<c:if test="${year=='2013' }">selected='selected'</c:if>>2013</option>
						<option value="2014"
							<c:if test="${year =='2014'}">selected='selected'</c:if>>2014</option>
					</select> 月度:<select id="rmonth" name="rmonth"><option value="">--请选择--</option>
						<option value="1"
							<c:if test="${month =='1'}">selected='selected'</c:if>>1</option>
						<option value="2"
							<c:if test="${month =='2'}">selected='selected'</c:if>>2</option>
						<option value="3"
							<c:if test="${month =='3'}">selected='selected'</c:if>>3</option>
						<option value="4"
							<c:if test="${month=='4' }">selected='selected'</c:if>>4</option>
						<option value="5"
							<c:if test="${month=='5' }">selected='selected'</c:if>>5</option>
						<option value="6"
							<c:if test="${month =='6'}">selected='selected'</c:if>>6</option>
						<option value="7"
							<c:if test="${month=='7' }">selected='selected'</c:if>>7</option>
						<option value="8"
							<c:if test="${month =='8'}">selected='selected'</c:if>>8</option>
						<option value="9"
							<c:if test="${month =='9'}">selected='selected'</c:if>>9</option>
						<option value="10"
							<c:if test="${month=='10' }">selected='selected'</c:if>>10</option>
						<option value="11"
							<c:if test="${month =='11'}">selected='selected'</c:if>>11</option>
						<option value="12"
							<c:if test="${month =='12'}">selected='selected'</c:if>>12</option>
					</select> <input type="button" value="查询" id="tongji1" /> <input
						type="button" value="导出"  onclick="exportLs('Other','1')"/>
				</p>
				<p>
				<table width="100%" height="98%" border="1" cellpadding="0"
					id="table4Search1" cellspacing="0" bordercolor="#000000">
					<thead>
						<tr>
							<td align="center"><strong>地市</strong></td>
							<td align="center"><strong>代维公司</strong></td>
							<td align="center"><strong>费用类型</strong></td>
							<td align="center"><strong>应付金额（元）</strong></td>
							<td align="center"><strong>实付费用（元）</strong></td>
							<td align="center"><strong>款项依据</strong></td>
						</tr>
					</thead>
					<tbody id="tbody1">
						<c:forEach var="map" items="${DataMap}" varStatus="status">
							<tr>
								<td align="center">${map.REGIONNAME }</td>
								<td align="center">${map.CONTRACTORNAME }</td>
								<td align="center">${map.TYPET }</td>
								<td align="center">${map.SHOULDMONEY }</td>
								<td align="center">${map.FACTMONEY }</td>
								<td align="center">${map.REMARK }</td>
							</tr>
						</c:forEach>
						<c:forEach var="map" items="${totalData}" varStatus="status">
							<tr>
								<td colspan="3">总计</td>
								<td align="center">${map.SUM1 }</td>
								<td align="center">${map.SUM2 }</td>
								<td align="center"> </td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</p>
			</div>
			<div class="tab-pane  <c:if test="${labq=='2'}"> active  </c:if>"
				id="tab1-2">
				<p>
					年份:<select id="cyear" name="cyear"><option value="">--请选择--</option>
						<option value="2012"
							<c:if test="${year==\'2012\'}">selected='selected'</c:if>>2012</option>
						<option value="2013"
							<c:if test="${year=='2013' }">selected='selected'</c:if>>2013</option>
						<option value="2014"
							<c:if test="${year =='2014'}">selected='selected'</c:if>>2014</option>
					</select> 月度:<select id="cmonth" name="cmonth"><option value="">--请选择--</option>
						<option value="1"
							<c:if test="${month =='1'}">selected='selected'</c:if>>1</option>
						<option value="2"
							<c:if test="${month =='2'}">selected='selected'</c:if>>2</option>
						<option value="3"
							<c:if test="${month =='3'}">selected='selected'</c:if>>3</option>
						<option value="4"
							<c:if test="${month=='4' }">selected='selected'</c:if>>4</option>
						<option value="5"
							<c:if test="${month=='5' }">selected='selected'</c:if>>5</option>
						<option value="6"
							<c:if test="${month =='6'}">selected='selected'</c:if>>6</option>
						<option value="7"
							<c:if test="${month=='7' }">selected='selected'</c:if>>7</option>
						<option value="8"
							<c:if test="${month =='8'}">selected='selected'</c:if>>8</option>
						<option value="9"
							<c:if test="${month =='9'}">selected='selected'</c:if>>9</option>
						<option value="10"
							<c:if test="${month=='10' }">selected='selected'</c:if>>10</option>
						<option value="11"
							<c:if test="${month =='11'}">selected='selected'</c:if>>11</option>
						<option value="12"
							<c:if test="${month =='12'}">selected='selected'</c:if>>12</option>
					</select> <input type="button" value="查询" id="tongji2" /> <input
						type="button" value="导出"  onclick="exportLs('Other','2')"/>
				</p>
				<p>
				<table width="100%" height="98%" border="1" cellpadding="0"
					id="table4Search2" cellspacing="0" bordercolor="#000000">
					<thead>
						<tr>
							<td align="center"><strong>代维公司</strong></td>
							<td align="center"><strong>地市</strong></td>
							<td align="center"><strong>费用类型</strong></td>
							<td align="center"><strong>应付金额（元）</strong></td>
							<td align="center"><strong>实付费用（元）</strong></td>
							<td align="center"><strong>款项依据</strong></td>
						</tr>
					</thead>
					<tbody id="tbody2">
						<c:forEach var="map" items="${DataMap}" varStatus="status">
							<tr>
								<td align="center">${map.CONTRACTORNAME }</td>
								<td align="center">${map.REGIONNAME }</td>
								<td align="center">${map.TYPET }</td>
								<td align="center">${map.SHOULDMONEY }</td>
								<td align="center">${map.FACTMONEY }</td>
								<td align="center">${map.REMARK }</td>
							</tr>
						</c:forEach>
						<c:forEach var="map" items="${totalData}" varStatus="status">
							<tr>
								<td colspan="3">总计</td>
								<td align="center">${map.SUM1 }</td>
								<td align="center">${map.SUM2 }</td>
								<td align="center"> </td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</p>
			</div>
			<div class="tab-pane  <c:if test="${labq=='3'}"> active  </c:if>"
				id="tab1-3">
				<p>
					年份:<select id="syear" name="syear"><option value="">--请选择--</option>
						<option value="2012"
							<c:if test="${year==\'2012\'}">selected='selected'</c:if>>2012</option>
						<option value="2013"
							<c:if test="${year=='2013' }">selected='selected'</c:if>>2013</option>
						<option value="2014"
							<c:if test="${year =='2014'}">selected='selected'</c:if>>2014</option>
					</select> 月度:<select id="smonth" name="smonth"><option value="">--请选择--</option>
						<option value="1"
							<c:if test="${month =='1'}">selected='selected'</c:if>>1</option>
						<option value="2"
							<c:if test="${month =='2'}">selected='selected'</c:if>>2</option>
						<option value="3"
							<c:if test="${month =='3'}">selected='selected'</c:if>>3</option>
						<option value="4"
							<c:if test="${month=='4' }">selected='selected'</c:if>>4</option>
						<option value="5"
							<c:if test="${month=='5' }">selected='selected'</c:if>>5</option>
						<option value="6"
							<c:if test="${month =='6'}">selected='selected'</c:if>>6</option>
						<option value="7"
							<c:if test="${month=='7' }">selected='selected'</c:if>>7</option>
						<option value="8"
							<c:if test="${month =='8'}">selected='selected'</c:if>>8</option>
						<option value="9"
							<c:if test="${month =='9'}">selected='selected'</c:if>>9</option>
						<option value="10"
							<c:if test="${month=='10' }">selected='selected'</c:if>>10</option>
						<option value="11"
							<c:if test="${month =='11'}">selected='selected'</c:if>>11</option>
						<option value="12"
							<c:if test="${month =='12'}">selected='selected'</c:if>>12</option>
					</select> <input type="button" value="查询" id="tongji3" /> <input
						type="button" value="导出"  onclick="exportLs('Other','3')"/>
				</p>
				<p>
				<table width="100%" height="98%" border="1" cellpadding="0"
					id="table4Search3" cellspacing="0" bordercolor="#000000">
					<thead>
						<tr>
							<td align="center"><strong>费用类型</strong></td>
							<td align="center"><strong>地市</strong></td>
							<td align="center"><strong>代维公司</strong></td>
							<td align="center"><strong>应付金额（元）</strong></td>
							<td align="center"><strong>实付费用（元）</strong></td>
							<td align="center"><strong>款项依据</strong></td>
						</tr>
					</thead>
					<tbody id="tbody3">
						<c:forEach var="map" items="${DataMap}" varStatus="status">
							<tr>
								<td align="center">${map.TYPET }</td>
								<td align="center">${map.REGIONNAME }</td>
								<td align="center">${map.CONTRACTORNAME }</td>
								<td align="center">${map.SHOULDMONEY }</td>
								<td align="center">${map.FACTMONEY }</td>
								<td align="center">${map.REMARK }</td>
							</tr>
						</c:forEach>
						<c:forEach var="map" items="${totalData}" varStatus="status">
							<tr>
								<td colspan="3">总计</td>
								<td align="center">${map.SUM1 }</td>
								<td align="center">${map.SUM2 }</td>
								<td align="center"> </td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</p>
			</div>
		</div>
	</div>
</body>
</html>
