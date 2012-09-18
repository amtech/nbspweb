<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/header.jsp"%>
<script language="javascript" type="text/javascript">
jQuery.fn.rowspan = function(colIdx) {
	return this.each(function(){
	var that;
	  jQuery('tr', this).each(function(row) {
	  jQuery('td:eq('+colIdx+')', this).filter(':visible').each(function(col) {
	        if (that!=null && jQuery(this).html() == jQuery(that).html()) {
	          rowspan = jQuery(that).attr("rowSpan");
	          if (rowspan == undefined) {
	   
	            jQuery(that).attr("rowSpan",1);
	            rowspan = jQuery(that).attr("rowSpan");
	          }
	          rowspan = Number(rowspan)+1;
	          jQuery(that).attr("rowSpan",rowspan); // do your action for the colspan cell here
	          jQuery(this).hide(); // .remove(); // do your action for the old cell here
	        } else {
	          that = this;
	        }
	  });
	 });
	});
	}
	jQuery.fn.colspan = function(rowIdx) {
	return this.each(function(){
	var that;
	  jQuery('tr', this).filter(":eq("+rowIdx+")").each(function(row) {
	  jQuery(this).find('td').filter(':visible').each(function(col) {
	  
	        if (that!=null && jQuery(this).html() == jQuery(that).html()) {
	          colspan = jQuery(that).attr("colSpan");
	          if (colspan == undefined) {
	            jQuery(that).attr("colSpan",1);
	            colspan = jQuery(that).attr("colSpan");
	          }
	          colspan = Number(colspan)+1;
	          jQuery(that).attr("colSpan",colspan);
	          jQuery(this).hide(); // .remove();
	        } else {
	          that = this;
	        }
	  });
	 });
	});
	}
	jQuery(function(){
	 jQuery("#_table2").rowspan(0);
	})
</script>
<table border="0" align="center">
	<tr>
		<th>模板名称：</th>
		<td>${vo.templateName}</td>
	</tr>
	<tr>
		<th>专业：</th>
		<td><baseinfo:dic displayProperty="lable"
				codevalue="${vo.businessType }" columntype="businesstype"></baseinfo:dic>
		</td>
	</tr>
	<tr>
		<th>备注：</th>
		<td>${vo.remark}</td>
	</tr>
	<tr>
		<th>巡检项：</th>
		<td style="border-collapse: collapse;">
			<table id="_table2">
				<c:set var="itemId" value=""></c:set>
				<c:forEach var="item" items="${items}" varStatus="stauts">
					<tr>
						<c:if test="${itemId!=item.ITEM_ID }">
							<c:set var="itemId" value="${item.ITEM_ID }"></c:set>
							<td style="text-align:left;width:25%" rowspan="${item.ROWSPAN }">${item.ITEM_NAME}</td>
						</c:if>
						<td style="text-align:left;width:75%">${stauts.index+1}、${item.SUBITEM_NAME}</td>
					</tr>
				</c:forEach>
			</table>
		</td>
	</tr>
</table>