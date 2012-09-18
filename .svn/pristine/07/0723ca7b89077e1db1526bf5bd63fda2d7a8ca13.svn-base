<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<script type="text/javascript" src="${ctx }/js/in-min.js"
	autoload="true"
	core="${config.cdnurl}/cabletech/ui/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	In.add('style',{path:'${ctx }/css/style.css'});
	In.add('art',{path:'${ctx}/js/jQuery/artdialog/jquery.artDialog.js?skin=default',charset:'utf-8',type:'js'});
	In.add('artiframe',{path:'${ctx}/js/jQuery/artdialog/plugins/iframeTools.js',charset:'utf-8',type:'js',rely:['art']});
	In.add('artnotic',{path:'${ctx}/js/jQuery/artdialog/plugins/artNotice.js',charset:'utf-8',type:'js',rely:['artiframe']});
	In.add('welcomejs',{path:'${ctx}/frames/default/js/welcome.js',charset:'utf-8',type:'js'});
</script>
</head>
<body style="background: white; ">
	<div id="main">
		<div class="parts bg1">
			<div class="maintitle">
				<span class="bspan"><img
					src="${ctx}/css/images/myaccount.gif"></img> 个人信息</span>
			</div>
			<div class="content">
				<span class="imgWrap"><img
					src="${ctx}/css/images/userlogo.png"></img> </span>
				<p>
					<span class="brief">用户：${LOGIN_USER.userName}</span><span
						class="brief">单位：${LOGIN_USER.orgName}</span><span class="brief">在线时长：${LOGIN_USER.spanHours}小时.</span>
				</p>
			</div>
			<div class="content">
				<span class="brief">上次登录时间：<fmt:formatDate
						value="${LOGIN_USER.lastlogin}" pattern="yy-MM-dd HH:mm:ss" /> </span> <span
					class="brief">登录IP：10.139.17.89</span>
			</div>
		</div>
		<div class="parts bg3">
			<div class="maintitle">
				<span class="bspan"><img src="${ctx}/css/images/task.gif"></img>
					待办工作</span> <a
					href="javascript:viewTaskMore('${ctx}/desktop/mywork!gettask.action?flag=2','查看待办工作');"
					class="more">更多&gt;&gt;</a>
			</div>
			<div class="content" id="work"></div>
		</div>
		<div class="parts bg1">
			<div class="maintitle">
				<span class="bspan"><img src="${ctx}/css/images/outbox.gif"></img>
					公告新闻 </span> <a href="javascript:viewNoticeMore();" class="more">更多&gt;&gt;</a>
			</div>
			<div class="content" id="notice"></div>
		</div>
		<br style="clear:both;" />
		<div class="parts bg1">
			<div class="maintitle">
				<span class="bspan"><img src="${ctx}/css/images/memeber.gif"></img>
					个人工作台</span> <a href="javascript:setWorkBench();" class="more">设置&gt;&gt;</a>
			</div>
			<div class="content">
				<div id="workbench" align="center"></div>
			</div>
		</div>
		<div class="parts bg3">
			<div class="maintitle">
				<span class="bspan"><img src="${ctx}/css/images/process.gif"></img>
					今日统计</span><a
					href="javascript:viewTaskMore('${ctx}/desktop/mywork!gettoday.action?flag=2','查看今日统计');"
					class="more">更多&gt;&gt;</a>
			</div>
			<div class="content" id="today"></div>
		</div>
		<div class="parts bg1">
			<div class="maintitle">
				<span class="bspan"><img
					src="${ctx}/css/images/customers.gif"></img> 在线维护人数</span><a
					href="javascript:refreshOnlineman();" class="more">刷新&gt;&gt;</a>
			</div>
			<div class="content" id="onlineman"></div>
			<br style="clear:both;" />
		</div>
		<br style="clear:both;" />
</body>

<script type="text/javascript">
	In.ready('style','artnotic','welcomejs',function(){
		$('#notice').load('${ctx}/desktop/desktop!getinformation.action');
		$('#workbench').load('${ctx}/desktop/mywork!getshortcut.action');
		$('#onlineman').load('${ctx}/desktop/desktop!getonlinemantree.action');
		$('#work').load('${ctx}/desktop/mywork!gettask.action');
		$('#today').load('${ctx}/desktop/mywork!gettoday.action');
})
//查看对话框
function viewDialog(url,text){
		if(text!=='发布信息'){
			art.dialog.open(url, {id:'viewDialog',title: text,width: 800, height: 400,lock: true});
		}else{
			art.dialog.open(url, {id:'viewDialog',title: text,width: 800, height: 400,lock: true,close: function () {
				$('#notice').load('${ctx}/desktop/desktop!getinformation.action');
				return true;
			}
			});	
		}
}
//查看公告
function viewNotice(NOTICE_ID){
		url="${ctx}/system/notice!view.action?id="+NOTICE_ID+"&preview=false";
		art.dialog.open(url, {id:'viewDialog',title: '查看公告新闻',width: 800, height: 400,lock: true});
}
//设置个人工作台
function setWorkBench(){
		url="${ctx}/desktop/deskTopWorkBenchAction!input.action";
		art.dialog.open(url, {id:'viewDialog',title: '个人控制台设置',width: 800, height: 400,lock: true,close: function () {
			$('#workbench').empty();
			$('#workbench').load('${ctx}/desktop/mywork!getshortcut.action');
			return true;
		}
		});
}
//查看公告更多
function viewNoticeMore(){
		url="${ctx}/system/notice!list.action?flag=2";
		art.dialog.open(url, {id:'viewDialog',title: '查看公告新闻',width: 800, height: 400,lock: true});
}
//刷新在线人员
function refreshOnlineman(){
	$('#onlineman').empty();
	$('#onlineman').load('${ctx}/desktop/desktop!getonlinemantree.action');
}
//查看待办任务更多
function viewTaskMore(url,title){
	var myDialog = art.dialog({title:title});// 初始化一个带有loading图标的空对话框
	jQuery.ajax({
	    url: url,
	   	success: function (data) {
	      myDialog.content(data);// 填充对话框内容
	    }
	});
}
</script>
</html>
