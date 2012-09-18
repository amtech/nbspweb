<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<script type="text/javascript" src="${ctx }/js/in-min.js" autoload="true" core="${config.cdnurl}/cabletech/ui/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
In.add('css',{path:'${ctx }/css/css.css'});
In.add('style',{path:'${ctx }/css/style.css'});
In.add('highchartjs',{path:'${ctx}/js/highchart/highcharts.js',type:'js',charset:'utf-8',rely:['style']});
In.add('wdatejs',{path:'${ctx}/js/WdatePicker/WdatePicker.js',type:'js',charset:'utf-8'});
In.add('welcomejs',{path:'${ctx}/frames/default/js/welcome.js',type:'js'});
</script>
</head>
<body style="background: white; font-size: 12px;">
	<div id="main">
		<div class="parts bg1">
			<div class="maintitle">
				<span class="bspan">个人信息</span>
			</div>
			<div class="content">
				<span class="imgWrap"><img
					src="${ctx}/css/images/userlogo.png"> </span>
				<p>
					<span class="brief">用户：${LOGIN_USER.userName}</span> <span
						class="brief">单位：${LOGIN_USER.orgName} </span> <span class="brief">上次登录时间：<fmt:formatDate
							value="${LOGIN_USER.lastlogin}" pattern="yy-MM-dd HH:mm:ss" /> </span>
				</p>
			</div>
			<div class="content">
				<c:forEach var="item" items="${sessionScope.indexmenu}">
					<c:if test="${item.TEXT=='我的工作'}">
						<c:set var="menuid" value="${item.ID }"></c:set>
					</c:if>
				</c:forEach>
				<span class="brief">在线时长：${LOGIN_USER.spanHours}小时.</span>
				<c:choose>
					<c:when test="${today_meet_num!=0 }">
						<fmt:formatDate value="${ordermonth }" pattern="yyyy-MM-dd"
							var="curDate" />
						<span class="brief"><a href="#"
							onclick="openDialog('${ctx}/system/notice!listMeet.action?meetTime=${curDate }');">你今天有${today_meet_num
								}个会议需要参加。</a> </span>
					</c:when>
					<c:otherwise>
						<span class="brief"> 您今天没有需要参加的会议.</span>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="parts bg3">
			<div class="maintitle">
				<span class="bspan">公告新闻</span> <a href="javascript:void(0);"
					onclick="openDialog('${ctx}/system/notice!list.action?flag=2');"
					class="more">更多&gt;&gt;</a>
			</div>
			<div class="content" id="notice">
			</div>
		</div>
		<div class="parts bg1" style="margin:0">
			<div class="maintitle">
				<span class="bspan">超时工单 </span> <a href="#"
					onclick="openDialog('${ctx}/desktop/desktop!overtimeTaskMoreList.action');"
					class="more">更多&gt;&gt;</a>
			</div>
			<div class="content" id="workorder">
			</div>
		</div>
		<br clear="all">
		<div class="parts bg1">
			<div class="maintitle">
				<span class="bspan">会议日程</span>
			</div>
			<div class="content">
				<div id="div1" align="center"></div>
			</div>
		</div>
		<div class="parts bg3">
			<div class="maintitle">
				<span class="bspan">日常巡检</span> <span class="more "
					style="float: right;margin-top: 7px"> <select
					class="more inputtext" style="width: 60px" name="timetype" onchange="timetypechange();">
						<option value="0">本月</option>
						<option value="1">本季</option>
						<option value="2">本年</option>
				</select> </span>
			</div>
			<div class="content" id="patrolinfo"
				style="width: 580px; height: 180px; margin: 5px"></div>
		</div>
		<div class="parts bg1" style="margin:0">
			<div class="maintitle">
				<span class="bspan">在线维护人员</span>
			</div>
			<div class="content" id="siteperson"
				style="width: 285px; height: 180px; margin: 5px"></div>
		</div>
		<br clear="all">
		<div class="parts bg1">
			<div class="maintitle">
				<span class="bspan">代维人员统计</span>
			</div>
			<div class="content" id="contractorman"
				style="width: 285px; height: 180px; margin: 5px"></div>
		</div>
		<div class="parts bg3">
			<div class="maintitle">
				<span class="bspan">工单统计</span>
			</div>
			<div class="content" id="worksheet"
				style="width: 580px; height: 180px; margin: 5px"></div>
		</div>
		<div class="parts bg1" style="margin:0">
			<div class="maintitle">
				<span class="bspan">代维资源配备 </span> <a href="#"
					onclick="openDialog('${ctx}/desktop/desktop!contractorResEquipMoreList.action');"
					class="more">更多&gt;&gt;</a>
			</div>
			<div class="content" id="resequip">
			</div>
		</div>
		<br clear="all">
	</div>
</body>
<script type="text/javascript">
In.ready('highchartjs','wdatejs','welcomejs',function(){
		 $.ajax({
				url : '${ctx}/desktop/desktop!getonlineman.action',
				dataType : "json",
				type:'GET',
				cache:true,
				async:true,
				success : function(result) {
					if(!!result)
						sitePerson(result);
				},
				error : function() {
					//alert("获取数据异常！");
				}
			});	
		 $.ajax({
				url : '${ctx}/desktop/desktop!getContractorPersonChart.action',
				type:'GET',
				cache:true,
				async:true,
				success : function(result) {
					if(!!result)
						chartcontractorman(eval(result));
				},
				error : function() {
					//alert("获取数据异常！");
				}
			});	
		//chartcontractorman(${personchart});
		//workSheet(${workchart.total_hour},${workchart.total_num},${workchart.overtime_num});
		var meetingdate="${meet_date_list}";
		var specialDates=[${meet_date_list}];
		if(!!meetingdate){
		WdatePicker({eCont:'div1',specialDates:specialDates,onpicked:function(dp){
			if(meetingdate.indexOf(dp.cal.getDateStr()) != -1){
			openDialog("${ctx}/system/notice!listMeet.action?meetTime="+dp.cal.getDateStr());
			}
			}
		});
		}else{
			WdatePicker({eCont:'div1'});
		}
		getPatrolPlan();
		$('#notice').load('${ctx}/desktop/desktop!getinformation.action');
		$('#resequip').load('${ctx}/desktop/desktop!getContractorResEquipList.action');
		$('#workorder').load('${ctx}/desktop/desktop!getWorkOrder.action');
}
)
	//获取巡检信息
function getPatrolPlan(){
	 $.ajax({
			url : '${ctx}/desktop/desktop!getPatrolPlan.action',
			dataType : "json",
			type:'GET',
			data: ({timetype : $('select[name="timetype"]').val()}),
			async:true,
			success : function(result) {
				if(!!result)
				patrolInfo(result);
			},
			error : function() {
				//alert("获取数据异常！");
			}
		});
}	

function timetypechange(){
	getPatrolPlan();
}
	//查看公告
function viewNotice(NOTICE_ID){
	URL="${ctx}/system/notice!view.action?id="+NOTICE_ID+"&preview=false";
	myleft=(screen.availWidth-650)/2;
	mytop=100
	mywidth=650;
	myheight=500;
	window.open(URL,"read_news","height="+myheight+",width="+mywidth+",status=1,resizable=no,toolbar=no,menubar=no,location=no,scrollbars=yes,top="+mytop+",left="+myleft+",resizable=yes");
}
</script>
</html>
