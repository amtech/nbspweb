<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<%@ include file="/common/meta.jsp"%>
<head>
<title>综合代维管理平台</title>

<link href="${ctx }/css/login_style.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<div class="login_pic_bg">
		<div class="logo"></div>
		<div class="system_name">
			<span>中国移动${config.systemregionname}公司网络代维管理系统</span>
		</div>
		<div class="form_messages">
			<form action="${ctx }/login/loginAction!security.action"
				method="post">
				<table>
					<tr>
						<td>用户名：</td>
						<td><input name="userid" type="text" class="text_input" /></td>
						<td><input type="submit" class="button" value="登录" /></td>
					</tr>
					<tr>
						<td>密 码：</td>
						<td><input name="password" type="password" class="text_input"
							onpaste="return false" oncopy="return false"
							onselectstart="return false" /></td>
						<td><input type="reset" class="button" value="取消" /></td>
					</tr>
				</table>
				<div class="error_messages">
					<span>${msg}</span>
				</div>
			</form>

		</div>
		<div class="text_messages">
			中国移动${config.systemregionname}公司网络代维管理系统<br>系统热线电话：4006708558
			热线手机：1380000000
		</div>
	</div>
</body>
</html>
