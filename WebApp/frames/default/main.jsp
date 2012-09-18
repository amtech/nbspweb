<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<%@ include file="/common/meta.jsp"%>
<head>
<title>网络代维管理系统</title>
<%@include file="/common/cssjs.jsp" %>

<script type="text/javascript">

In.add('mainjs',{path:'${ctx}/frames/default/js/main.js',type:'js'});
In.add('iepng',{path:'${ctx}/js/unitpngfix/iepng.js',type:'js'});
//退出
function exitSystem() {
	if (confirm("您确实要关闭系统吗?")) {
		var _window = top.location != self.location? window.parent.window : window;
		_window.location="${ctx}/desktop/desktop!logout.action";
	}
}
</script>
</head>
<body>
	<div class="header">
		<div class="top_frame">
			<div class="top_left"></div>
			<div class="logo"></div>
			<div class="System_Name">中国移动${config.systemregionname}公司网络代维管理系统</div>
			<div class="top_textbg"></div>
		</div>
		<div class="Login_Information">
			<a href="#">当前用户：${LOGIN_USER.userName}</a> &nbsp;&nbsp; <a href="javascript:updatepws();">修改密码</a><a
				href="javascript:exitSystem();">[退出]</a>
		</div>
		<div class="banner">
			<ul id="nav">
				<c:forEach var="item" items="${sessionScope.indexmenu}">
					<li class="top"><a id='${item.ID }' href="#" class="top_link" url="${item.HREFURL }" text="${item.TEXT}"
						onclick="gotoMainUrl('${item.ID }','${item.HREFURL }','${item.DESCRIPTION}','${item.TEXT}')"><span>${item.TEXT}</span>
					</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<div id="content" class="maincontent">
	</div>
	<div class="bottom">
		<span class="version">版本：V2.1.0.5 2012-09-12 </span> <span
			class="copyright">版权所有：北京鑫干线网络科技发展有限公司
			&nbsp;&nbsp;技术支持电话：4006708558</span>
	</div>
</body>
<!--[if lt IE 7]>
<script src="${ctx}/js/unitpngfix/iepng.js" type="text/javascript"></script>
<script type="text/javascript">
EvPNG.fix('div, ul, img, li, input'); 
</script>
<![endif]-->
<script type="text/javascript">
	In.ready('mainjs',function(){
		
		$("#nav a").each(function(n)
				{
			var index=$(this);
			if(index.attr('text')==='首页'){
				//动态创建iframe
				createframe(index.attr('url')+'?r='+Math.random(),'auto','zoom:1;margin:0','bodyFrame','content');
				}
			}
		); 
		
		autoHeight();
		$(".top_link span").click(function(){
			$(".top_link span").each(function(){
				$(this).removeClass('selected');
			})
			$(this).addClass('selected');
	  });
	});
	var setting = {
			view: {
				showLine: false
			},
			data:{
				simpleData: {
					enable: true,
					idKey:"ID",
					pIdKey:"PARENTID",
					rootPId:"root"	
				},
				key: {
					title: "TEXT",
					name: "TEXT"
					
				}
			},
			callback: {
				onClick:pageSkip
			}
		}; 
	// 页面跳转
	function pageSkip(e, treeId, node) {
		if (node.HREFURL)
			$('#bodyFrame').attr('src', node.HREFURL);
	}
	function gotoMainUrl(index,hrefurl,flag,text){
		if(text!=="首页"){
			if(flag=="1"){
			$("#bodyFrame").attr('src','${ctx}/desktop/leftNavigate!getmenu.action?type='+index+'&jumpup='+hrefurl);
			}else if(flag=="2"){
				$("#bodyFrame").attr('src',hrefurl);
			}else{
				$("#bodyFrame").attr('src','${ctx}/desktop/leftNavigate!getmenu.action?type='+index);
			}
		addLoadTip('content', 'bodyFrame', 'loadtipdiv');
		}else{
			$("#bodyFrame").attr('src',hrefurl+'?r='+Math.random());
		}
	}
	function updatepws(){
	    $("#bodyFrame").attr('src','/bspweb/SystemManage/updatePsw.jsp');
	}
</script>
</html>
