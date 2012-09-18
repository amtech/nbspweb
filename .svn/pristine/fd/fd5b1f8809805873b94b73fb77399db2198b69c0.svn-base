<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/js/in-min.js"
	autoload="true"
	core="${config.cdnurl}/cabletech/ui/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	In.add('art',{path:'${ctx}/js/jQuery/artdialog/jquery.artDialog.js?skin=default',charset:'utf-8',type:'js'});
	In.add('artiframe',{path:'${ctx}/js/jQuery/artdialog/plugins/iframeTools.js',charset:'utf-8',type:'js',rely:['art']});
	In.add('artnotic',{path:'${ctx}/js/jQuery/artdialog/plugins/artNotice.js',charset:'utf-8',type:'js',rely:['artiframe']});
	In.add('welcomejs',{path:'${ctx}/frames/default/js/welcome.js',charset:'utf-8',type:'js'});
	In.add('jqui',{path:'${ctx}/js/jQuery/jqueryui/css/redmond/jquery-ui.custom.css'});
	In.add('jquijs',{path:'${ctx}/js/jQuery/jqueryui/jquery-ui.custom.min.js',type:'js',charset:'utf-8',rely:['jqui']});
	In.ready('artnotic','welcomejs',function(){
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
		art.dialog.open(url, {id:'viewDialog',title: '个人工作台设置',width: 800, height: 400,lock: true,close: function () {
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
function processworkorder(url,showTitle){
	art.dialog.open('${ctx}/'+url, {id:'viewDialog',title: showTitle,width: 800, height: 400,lock: true,close: function () {
		$('#work').empty();
		$('#work').load('${ctx}/desktop/mywork!gettask.action');
		return true;
	}});
}
</script>
</head>
<body style="background: white; ">
	<div id="container">
		<table cellspacing="0" id="columns" style="width: 100%">
			<tr>
				<td style="width: 248px">
					<div class="portlet" style="height: 180px">
						<div class="portlet_topper">
							<li class="li-zhxx"><span>个人信息<span>
							</li>
						</div>
						<div class="portlet_content">
							<div>
								<span class="imgWrap"><img
									src="${ctx}/css/images/userlogo.png"></img> </span>
								<ul
									style="list-style-type: none;list-style-position: inside;display: inline;">
									<li style="padding-bottom:5px;">用户：${LOGIN_USER.userName}</li>
									<li style="padding-bottom:5px;">单位：${LOGIN_USER.orgName}</li>

								</ul>
							</div>
							<ul style="list-style-type: none;display: inline;">
								<li style="padding-bottom:2px;margin-top:2px">上次登录时间：<fmt:formatDate
										value="${LOGIN_USER.lastlogin}" pattern="yyyy-MM-dd HH:mm:ss" />
								</li>
								<li style="padding-bottom:2px;">登录IP：${LOGIN_USER.loginIp}</li>
								<li style="padding-bottom:2px;">在线时长：${LOGIN_USER.spanHours}小时.</li>
							</ul>
						</div>
					</div>
					<div class="portlet">
						<div class="portlet_topper">
							<li class="li-grgzt"><span>个人工作台</span><a
								href="javascript:setWorkBench();" class="more">设置&gt;&gt;</a>
							</li>
						</div>
						<div class="portlet_content">
							<div id="workbench"></div>
						</div>
					</div></td>
				<td>
					<div class="portlet" style="height: 260px;overflow: hidden;">
						<div class="portlet_topper">
							<li class="li-db"><span>待办工单</span></li>
						</div>
						<div class="portlet_content">
							<div id="work"></div>
						</div>
					</div>
					<div class="portlet">
						<div class="portlet_topper">
							<li class="li-jrtj"><span>今日统计</span></li>
						</div>
						<div class="portlet_content">
							<div id="today"></div>
						</div>
					</div>
				</td>
				<td style="width: 248px">
					<div class="portlet" style="height: 180px;overflow: hidden;">
						<div class="portlet_topper">
							<li class="li-gg"><span>公告新闻</span><a
								href="javascript:viewNoticeMore();" class="more">更多&gt;&gt;</a>
							</li>
						</div>
						<div class="portlet_content">
							<div id="notice"></div>
						</div>
					</div>
					<div class="portlet">
						<div class="portlet_topper">
							<li class="li-zxry"><span>在线维护人数</span><a
								href="javascript:refreshOnlineman();" class="more">刷新&gt;&gt;</a>
							</li>
						</div>
						<div class="portlet_content" >
							<div id="onlineman" style="height: 300px; display: inline-block;"></div>
						</div>
					</div></td>
			</tr>
		</table>
		<!-- /#columns -->
	</div>
</body>
</html>
