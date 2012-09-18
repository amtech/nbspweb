<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/header.jsp"%>
<html>
	<head>
		<title>查看公告通知</title>
		<style>
body,html {
	margin: 5px;
	width: 99%;
}

.gg_Framework {
	margin: 5px 2%;
	width: 98%;
	background-color: #F6FCFF;
	border: 1px #016C96 solid;
	font-size: 12px;
}

.title_bg {
	height: 30px;
	background-color: #016C96;
	margin: 1px;
	text-align: center;
	font-size: 14px;
	font-weight: bold;
}

.title_left {
	width: 60%;
	float: left;
	font-size: 12px;
	line-height: 30px;
	color: #FFFFFF;
	text-align: left;
	padding-left: 10px;
	font-weight: normal;
	letter-spacing: 2px;
}

.title_text {
	font-weight: bold;
	height: 40px;
	text-align: center;
	font-size: 20px;
	margin-left: 5%;
	margin-right: 5%;
	border-bottom: 1px #3088AA solid;
	color: #BFAF2B;
}

.content_time {
	margin-left: 5%;
	margin-right: 5%;
	height: 10px;;
	line-height: 15px;
	text-align: center;
	color: #016C96;
}

.meet {
	margin-left: 5%;
	margin-right: 5%;
	text-align: left;
	font-weight: bold;
	color: #016C96;
}

.content {
	line-height: 20px;
	color: #016C96;
}

.Accessories {
	height: 20px;
	text-align: left;
	color: #016C96;
}

.Close {
	margin-left: 5%;
	margin-right: 5%;
	line-height: 20px;
	text-align: center;
	color: #016C96;
	font-size: 12px;
	font-weight: bold;
}
</style>
	</head>
	<body class="" topmargin="5">
		<table class="gg_Framework">
			<tr>
				<td colspan="3" class="title_text">
					${notice.title }
				</td>
			</tr>
			<tr class="content_time">
				<td width="44%">
					发布人： ${notice.issueperson }
				</td>
				<td width="55%">
					&nbsp;发布时间：
					<fmt:formatDate value="${notice.issuedate}"
						pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
				<td width="1%">
					<!-- 
					发布对象：
					 -->
				</td>
			</tr>
			<c:if test="${notice.type=='B21' }">
				<tr class="meet">
					<td colspan="3">
						会议时间：
						<fmt:formatDate value="${notice.meetTime}"
							pattern="yyyy-MM-dd HH:mm:ss" />
						到
						<fmt:formatDate value="${notice.meetEndTime}"
							pattern="yyyy-MM-dd HH:mm:ss" />
					</td>
				</tr>
				<tr class="meet">
					<td colspan="3" class="meet_time">
						会议地点：${notice.meetAddress}
					</td>
				</tr>
			</c:if>
			<tr height="10px">
				<td colspan="3"></td>
			</tr>
			<tr class="content">
				<td colspan="3" style="height: 400px;" valign="top">
					<c:out value="${notice.contentString}" escapeXml="false"></c:out>
				</td>
			</tr>
			<tr height="10px">
				<td colspan="3"></td>
			</tr>
			<tr>
				<td colspan="3" class="Accessories">
					<apptag:upload state="look" entityId="${notice.id}"
						entityType="NOTICE_CLOB"></apptag:upload>
				</td>
			</tr>
			<c:if test="${model=='wap'}">
				<tr class="Close">
					<td colspan="3">
						【
						<a href="javascript:history.back();">返回</a>】
					</td>
				</tr>
			</c:if>
		</table>
	</body>
</html>
