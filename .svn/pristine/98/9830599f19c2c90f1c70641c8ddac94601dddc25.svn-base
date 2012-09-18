<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>网络代维综合管理系统</title>

<script language="javascript" type="text/javascript">
	function gotoMainUrl(index,hrefurl,text){
		$('li a').removeClass('hovertad');
        $('#'+index).addClass('hovertad');
		if(hrefurl){
			if(hrefurl.indexOf("userId=")!=-1){
				document.getElementById("bodyFrame").src=hrefurl+'${LOGIN_USER.userId}';
			}else{
				document.getElementById("bodyFrame").src=hrefurl;
			}
			
		}else{
			if(index!="index"){
				if(text==="我的工作")
				{
					document.getElementById("bodyFrame").src='${ctx}/desktop/leftNavigate!getmenu.action?type='+index+'&text=1';
				}else if(text==="基础模块"){
					document.getElementById("bodyFrame").src='${ctx}/desktop/leftNavigate!getmenu.action?type='+index+'&text=2';
				}else{
					document.getElementById("bodyFrame").src='${ctx}/desktop/leftNavigate!getmenu.action?type='+index;
				}
			}else{
				document.getElementById("bodyFrame").src='${ctx}/desktop/desktop!index.jspx';
			}
		}
}

	//主目录URL
	jQuery(function(){
		$('#index').addClass('hovertad');
		autoHeight();				
	})
	
		//自适应浏览器高度
	function autoHeight()
	{
		var section_middle = jQuery(document).height()-jQuery("#framecontentTop").outerHeight()-jQuery("#framecontentBottom").outerHeight();
		jQuery("#bodyFrame").height(section_middle);
		jQuery("#bodyFrame").width(jQuery(document).width());
		setTimeout(autoHeight, 500);
	}
</script>
</head>
<html>
<body>
	<div id="framecontentTop">
		<div class="head_top">
			<div class="head_topleft_bg">
				<div class="head_logo"></div>
			</div>
			<div class="head_System_Name">网络代维综合管理系统</div>
			<div id="topBanner_right">
				<div class="top_right_content">
					<span id="set"><a href="#"
						onclick='javascript:exitSystem();' title="设置个人信息">设置</a> </span> <span
						id="logout"><a href="#" onclick='javascript:exitSystem();'
						title="退出">退出</a> </span>
				</div>
			</div>
		</div>
		<div class="head_banner">
			<div class="menu">
				<ul>
					<c:forEach var="item" items="${sessionScope.indexmenu}">
						<c:choose>
							<c:when test="${item.TEXT=='知识论坛'}">
								<li><a id='${item.ID }' href="${item.HREFURL }" target="_blank">知识论坛</a>
								</li>
							</c:when>
							<c:otherwise>
								<li><a id='${item.ID }' href="#"
									onclick="gotoMainUrl('${item.ID }','${item.HREFURL }','${item.TEXT}')">${item.TEXT}</a>
								</li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<div id="maincontent">
		<iframe id="bodyFrame" name="bodyFrame" scrolling="auto"
			style="overflow-x:hidden; " width="100%" frameborder="0"
			src='${ctx}/desktop/desktop!index.action'></iframe>
	</div>
	<div id="framecontentBottom">
		<div class="bottom_left_Information">版本：1.2.0 2012.4.12</div>
		<div class="bottom_right_cabletech">北京鑫干线网络科技发展有限公司</div>
	</div>
</body>
</html>