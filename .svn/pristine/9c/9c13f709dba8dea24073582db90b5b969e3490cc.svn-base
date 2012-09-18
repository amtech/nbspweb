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
In.add('common', {path : '${ctx}/common/js/common.js',type : 'js',charset : 'utf-8'});
In.add('wdatejs', {path : '${ctx}/js/WdatePicker/WdatePicker.js',type : 'js',charset : 'utf-8'});
In.add('patrolinfo_common', {path : '${ctx}/wplan/plan/js/patrolinfo_common.js',type : 'js',charset : 'utf-8'});
In.add('patrolinfo_input', {path : '${ctx}/wplan/plan/js/patrolinfo_input.js',type : 'js',charset : 'utf-8',rely : [ 'patrolinfo_common' ]});
In.ready('vam', 'common', 'wdatejs','jquijs', 'patrolinfo_input', function() {
	setContextPath("${ctx}");
	//获取计划开始时间及结束时间
	var starttime='${patrolinfoMap.START_TIME }';
	var endtime='${patrolinfoMap.END_TIME }';
	if(starttime.length>0){
		starttime=starttime.substr(0,10);
		endtime=endtime.substr(0,10);
	}
	plantypechange(starttime,endtime);
	change();
	jQuery("#patrolinfoForm").validate({
		focusInvalid : false
	});
});
</script>
</head>
<body>
	<form action="${ctx }/wplan/patrolinfoAction!confirm.action"
		id="patrolinfoForm" name="patrolinfoForm" method="post">
		<div class="title_bg">
			<div id="title" class="title">当前位置-制定巡检计划</div>
		</div>
		<div class="tabcontent">
			<table style="width: 60%" border="0" align="center">
				<tr>
					<th>计划年份：</th>
					<td><baseinfo:customselector name="year" data="${planyearMap}"
							isReversal="true" cssClass="inputtext" onChange="change();"
							keyValue="${patrolinfoMap.YEAR}"></baseinfo:customselector></td>
				</tr>
				<tr>
					<th>计划名称：</th>
					<td><input id="planname" name="planname" class="inputtext"
						value="${patrolinfoMap.PLAN_NAME}" /></td>
				</tr>
				<tr>
					<th>专业类型：</th>
					<td><baseinfo:customselector id="businesstype"
							name="businesstype" data="${businessTypeMap }"
							onChange="change();" cssClass="inputtext required"
							isReversal="true"></baseinfo:customselector><span
						style="color: red">*</span></td>
				</tr>
				<tr>
					<th>计划类型：</th>
					<td><baseinfo:customselector name="plantype" id="plantype"
							data="${plantypeMap}" isReversal="true" cssClass="inputtext"
							keyValue="${patrolinfoMap.PLAN_TYPE}" onChange="plantypechange();"></baseinfo:customselector>
					</td>
				</tr>
				<tr>
					<th>计划时间：</th>
					<td>
						<div id="yearDV">
							<baseinfo:customselector id="yeartype" name="yeartype"
								data="${yearTypeMap}" isReversal="true" cssClass="inputtext"
								onChange="change();" keyValue="${yeartype}"></baseinfo:customselector>
						</div>
						<div id="seasonDV">
							<baseinfo:customselector name="seasontype"
								data="${seasonTypeMap}" isReversal="true" cssClass="inputtext"
								onChange="change();" keyValue="${seasontype}"></baseinfo:customselector>
						</div>
						<div id="monthDV" style="white-space: nowrap;">
							<input id="patrolinfo_starttime" name="starttime" type="text"
								class="Wdate " width="20%" value="${patrolinfoMap.START_TIME }"
								onfocus="var fmt=setStartDateFmt();var maxDateS=maxDateFS();var minDateS=minDateFS(); WdatePicker({dateFmt:fmt,realDateFmt:fmt,onpicked:setEndDate,maxDate:maxDateS,minDate:minDateS})" />
							至 <input id="patrolinfo_endtime" name="endtime" type="text"
								width="20%" class="Wdate" value="${patrolinfoMap.END_TIME }"
								onfocus="var maxDateE=maxDateFE();var minDateE=minDateFE(); WdatePicker({dateFmt:'yyyy-MM-dd',realFullFmt:'yyyy-MM-dd HH:mm:ss',onpicked:setEndDate,maxDate:maxDateE,minDate:minDateE})" />
							<span style="color: red">*</span>
						</div></td>
				</tr>
				<tr>
					<th>巡检组：</th>
					<td><input id="patrolinfo_patrolgroupname"
						name="patrolgroupname" class="required inputtext"
						value="${patrolinfoMap.PATROLGROUPNAME }" /> <a
						href="javascript:getPatrolGroup();"> <img border="0"
							src="${ctx}/css/images/selectcode.gif" /> </a><input
						id="patrolinfo_patrolgroupid" name="patrolgroupid"
						value="${patrolinfoMap.PATROL_GROUP_ID }" type="hidden" /> <span
						style="color: red">*</span></td>
				</tr>
				<tr>
					<th>所属区域：</th>
					<td><input id="regionname" name="regionname"
						class="required inputtext" value="${patrolinfoMap.REGIONNAME }"
						readonly="readonly" /><a
						href="javascript:searchRegion('${ctx}/commonaccess!getregion.action');">
							<img border="0" src="${ctx}/css/images/selectcode.gif" /> </a><input
						id="regionid" name="regionid" type="hidden"
						value="${patrolinfoMap.REGIONID }" /><span style="color: red">*</span>
					</td>

				</tr>
			</table>
			<div style="text-align: center; margin-top: 10px">
				<input id="btnSave" value="下一步" type="submit" class="button" />
			</div>
		</div>
	</form>
</body>
</html>