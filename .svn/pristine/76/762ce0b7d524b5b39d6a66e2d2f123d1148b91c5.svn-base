<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/meta.jsp"%>
<link href="${ctx}/css/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/js/in-min.js"
	autoload="true"
	core="${config.cdnurl}/cabletech/ui/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	In.add('jqui',{path:'${ctx}/js/jQuery/jqueryui/css/redmond/jquery-ui.custom.css'});
In.add('jquijs',{path:'${ctx}/js/jQuery/jqueryui/jquery-ui.custom.min.js',type:'js',charset:'utf-8',rely:['jqui']});
In.add('vm', {path : '${ctx}/js/jQuery/jvalidation/jquery.validate.min.js',type : 'js',charset : 'utf-8'});
In.add('vcn', {path : '${ctx}/js/jQuery/jvalidation/messages_cn.js',type : 'js',charset : 'utf-8',rely : [ 'vm' ]});
In.add('vex', {path : '${ctx}/js/jQuery/jvalidation/jquery.validate.ex.js',type : 'js',charset : 'utf-8',rely : [ 'vcn' ]});
In.add('vam', {path : '${ctx}/js/jQuery/jvalidation/additional-methods.min.js',type : 'js',charset : 'utf-8',rely : [ 'vex' ]});
In.add('common',{path:'${ctx}/common/js/common.js',type:'js',charset:'utf-8'});
In.add('wdatejs', {path : '${ctx}/js/WdatePicker/WdatePicker.js',type : 'js',charset : 'utf-8'});
In.add('patrolinfo_common', {path : '${ctx}/wplan/plan/js/patrolinfo_common.js',type : 'js',charset : 'utf-8'});
In.add('patrolinfo_analysis', {path : '${ctx}/wplan/plan/js/patrolinfo_analysis.js',type : 'js',charset : 'utf-8',rely : ['patrolinfo_common' ]});
</script>
<title>区域巡检分析</title>
</head>
<body>
	<form
		action="${ctx }/wplan/patrolanalysisAction!showregion.action"
		id="patrolinfoForm" name="patrolinfoForm" method="post">
		<div id="header">
			<div class="title_bg">
				<div id="title" class="title">当前位置-按区域分析</div>
			</div>
			<div class="tabcontent">
				<table cellspacing="0" cellpadding="0" border="0" align="center">
					<tr>
						<th>区域：</th>
						<td><input id="regionname" name="regionname"
							class="inputtext required" readonly="readonly"
							value="${regionname}" /><a
							href="javascript:searchRegion('${ctx}/commonaccess!getregion.action');"><img
								<img border="0"
								src="${ctx}/css/image/regionselect.png" />
						</a><input id="regionid" name="regionid" type="hidden" /></td>
					</tr>
					<tr>
						<th>统计方式：</th>
						<td><baseinfo:customselector name="plantype" id="plantype"
								data="${plantypeMap}" isReversal="true" cssClass="inputtext"
								keyValue="${plantype}" onChange="plantypechange();"></baseinfo:customselector>
						</td>
					</tr>
					<tr>
						<th>统计时间：</th>
						<td>
							<div id="yearDV">
								<baseinfo:customselector name="year" data="${planyearMap}"
									isReversal="true" cssClass="inputtext" keyValue="${year}"></baseinfo:customselector>
							</div>
							<div id="seasonDV">
								<baseinfo:customselector name="seasontype"
									data="${seasonTypeMap}" isReversal="true" cssClass="inputtext"></baseinfo:customselector>
							</div>
							<div id="monthDV">
								<input id="starttime" name="starttime" type="text"
									value="${starttime}" class="Wdate " style="width: 125px"
									onfocus="var fmt=setStartTimeFmt();WdatePicker({dateFmt:fmt,realDateFmt:fmt,opposite:true,onpicked:setEndTime,maxDate:'#F{$dp.$D(\'endtime\')}'})" />至
								<input id="endtime" name="endtime" type="text"
									value="${endtime}" class="Wdate " style="width: 125px"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',realFullFmt:'yyyy-MM-dd 23:59:59',onpicked:setEndTime,minDate:'#F{$dp.$D(\'starttime\')}'})" />
							</div></td>
					</tr>
					<tr>
						<th>专业类型：</th>
						<td><baseinfo:customselector id="businesstype"
								name="businesstype" data="${businessTypeMap }"
								keyValue="${patrolinfoMap.BUSINESS_TYPE}"
								cssClass="inputtext required" isReversal="true"></baseinfo:customselector>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							class="button" value="查询" onclick="search();" /><input
							type="reset" class="button" value="清除" /></td>

					</tr>
				</table>
			</div>
		</div>
	</form>
</body>
<script type="text/javascript">
In.ready('vam', 'jquijs','wdatejs','common','patrolinfo_analysis', function() {
	setContextPath('${ctx }');
	//获取计划开始时间及结束时间
	var starttime='${starttime }';
	var endtime='${endtime }';
	if(starttime.length>0){
		starttime=starttime.substr(0,10);
		endtime=endtime.substr(0,10);
	}
	plantypechange(starttime,endtime);
	jQuery("#patrolinfoForm").validate({
		focusInvalid : false,
		submitHandler : function(form) {
			jQuery("#patrolinfo_endtime").removeAttr("disabled");
				form.submit();
		}
	});
})
function search(){
		 var plantype = jQuery('select[name="plantype"]').val();
		if(plantype==4){
			jQuery("#patrolinfo_starttime").addClass('required');
			jQuery("#patrolinfo_endtime").addClass('required');
		}else if(plantype==3){
			jQuery("#patrolinfo_starttime").addClass('required');
			jQuery("#patrolinfo_endtime").addClass('required');
		}else{
			jQuery("#patrolinfo_starttime").removeClass('required');
			jQuery("#patrolinfo_endtime").removeClass('required');
		}
		return true;
	}
</script>
</html>