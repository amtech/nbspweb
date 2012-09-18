<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/header.jsp"%>
<div class="title_bg">
	<div id="title" class="title">
		当前位置-代维资源配备列表
	</div>
</div>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="list">
	<tr class="list_table_title">
		<th width="10%">
			${objectName}
		</th>
		<th width="10%" title="站点包括基站，铁塔，直放站，室分，集客等">
			站点
		</th>
		<th width="10%">
			人员
		</th>
		<th width="10%">
			车辆
		</th>
		<th width="10%">
			线路
		</th>
	</tr>
	<c:forEach items="${resequiplist}" var="item">
		<tr>
			<td>
				${item.NAME}
			</td>
			<td>
				${item.RESNUM}
			</td>
			<td>
				${item.PERSONNUM }
			</td>
			<td>
				${item.CARNUM }
			</td>
			<td>
				${item.LINENUM }
			</td>
		</tr>
	</c:forEach>
</table>
