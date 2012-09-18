<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/header.jsp"%>
<script language="javascript" type="text/javascript">
jQuery(function(){
	var actionUrl = "${ctx}/wplan/wplanTemplateAction!view.action?flag=view&id=${templateid}";
	 jQuery("#tabcontent").load(actionUrl);
	})
</script>
<body>
	<div class="title_bg">
		<div id="title" class="title">当前位置-查看模板</div>
	</div>
	<div id="tabcontent" class="tabcontent"></div>
	<div style="text-align: center; margin-top: 10px">
		<input value="返回" type="button" class="button"
			onclick="history.go(-1);" />
	</div>
</body>