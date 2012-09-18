<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link href="${ctx}/css/base.css" rel="stylesheet" type="text/css" />
		<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx }/js/in-min.js"
			autoload="true"
			core="${config.cdnurl}/cabletech/ui/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript">
In.add('vm',{path:'${ctx}/js/jQuery/jvalidation/jquery.validate.min.js',type:'js',charset:'utf-8'});
In.add('vcn',{path:'${ctx}/js/jQuery/jvalidation/messages_cn.js',type:'js',charset:'utf-8',rely:['vm']});
In.add('vex',{path:'${ctx}/js/jQuery/jvalidation/jquery.validate.ex.js',type:'js',charset:'utf-8',rely:['vcn']});
In.add('vam',{path:'${ctx}/js/jQuery/jvalidation/additional-methods.min.js',type:'js',charset:'utf-8',rely:['vex']});
In.add('jqui',{path:'${ctx}/js/jQuery/jqueryui/css/redmond/jquery-ui.custom.css'});
In.add('jquijs',{path:'${ctx}/js/jQuery/jqueryui/jquery-ui.custom.min.js',type:'js',charset:'utf-8',rely:['jqui']});
In.add('common',{path:'${ctx}/common/js/common.js',type:'js',charset:'utf-8'});
In.add('wdatejs',{path:'${ctx}/js/WdatePicker/WdatePicker.js',type:'js',charset:'utf-8'});
In.add('oe_dispatchtask_common',{path:'${ctx}/workflow/electricity/security/js/oe_dispatch_task_common.js',type:'js',charset:'utf-8'});
In.add('oe_dispatchtask_input',{path:'${ctx}/workflow/electricity/security/js/oe_dispatchtask_schedule.js',type:'js',charset:'utf-8',rely:['oe_dispatchtask_common']});
	In.ready('vam','jquijs','common','wdatejs','oe_dispatchtask_input',function(){
		setContextPath("${ctx}");
		jQuery("#oeDispatchTaskForm").validate({
			focusInvalid : false,
			submitHandler : function(form) {
				if (checkData()) {
					form.submit();
				}
			}
		});
		var url = "${ctx}/workflow/oeDispatchTaskAction!view.action?id=${id}";
		jQuery("#view_plan_div").load(url+" #detailTd",function(){
			var initialized = [ false, false ]; //是否已初始化
			var tab = jQuery("#tab_process").tabs({
				cache : true,
				selected : 0
			});
			tab.bind('tabsshow', function(event, ui) {
				//选择二个tab时，生成未巡检rfid
				if (ui.index == 1 && !initialized[ui.index]) {
				}
				initialized[ui.index] = true;
			});
			jQuery('#tab_process').tabs();
		});
	});
</script>
	</head>
	<body>
		<form action="${ctx }/workflow/oeScheduleTaskAction!save.action"
			id="oeDispatchTaskForm" method="post">
			<div class="title_bg">
				<div id="title" class="title">
					当前位置-供电保障-油机调度：${oeDispatchTask.taskCode }
				</div>
			</div>
			<div class="tabcontent">
				<div id="view_plan_div"></div>
				<table border="0" >
					<tr>
						<th>
							调度油机：
						</th>
						<td colspan="3">
							<a href="javascript:searchOilengine('${oeDispatchTask.stationId }','A24','${LOGIN_USER.userId }');">选择油机</a>
							<input id="oilengine_id" name="oilengine" value="" type="hidden" />
							<input id="oeScheduleTask_preStationId" name="oeScheduleTask.preStationId" value="" type="hidden" />
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<table id="oedispatchtaskgrid"></table>
							<div id="oedispatchtaskpager"></div>
						</td>
					</tr>
					<tr>
						<th>
							调度说明：
						</th>
						<td colspan="3">
							<textarea id="oeScheduleTask_dispatchRemark" name="oeScheduleTask.dispatchRemark" class="inputtext" style="width: 80%; height: 80px;"></textarea>
						</td>
					</tr>
				</table>
			</div>
			<div style="text-align: center; margin-top: 10px">
				<input id="id" name="oeScheduleTask.dispatchId" value="${id }" type="hidden" />
				<input id="task_id" name="oeScheduleTask.workflowTaskId" value="${task_id }" type="hidden" />
				<input id="businessType" name="businessType" value="${oeDispatchTask.businessType }" type="hidden" />
				<input name="btnSubmit" value="提交" type="submit" class="button" onclick="submitData();" />
				<input name="btnReset" value="重置" type="button" class="button" onclick="resetData();" />
				<input type="button" class="button" onclick="history.go(-1);" value="返回">
			</div>
		</form>
	</body>
	<script type="text/javascript">
	/**
	 * 搜索油机
	 */
	function searchOilengine(stationId, stationType, userId) {
		var url = "${WEBGIS_DEPLOYNAME}/gisextend!selectOeoilengines.action";
		url += "?id=" + stationId;
		url += "&type=" + stationType;
		url += "&eid=oilengine_id&userid=" + userId;
		window.showModalDialog(url, window,
				'status:no;center:yes;dialogWidth:800px;dialogHeight:600px;');
		listOilEngine(stationId, stationType);
	}
	/**
	 * 进行数据校验
	 */
	function checkData() {
		var obj = jQuery("input[name='oeScheduleTask.oilEngineId']:checked");
		if (jQuery.isEmptyObject(obj.val())) {
			alert("没有选择调度油机！");
			return false;
		}
		if(obj.attr("state")!='O11'){
			if(confirm("你选择了非空闲状态的油机，确定要进行调度吗？")){
				jQuery("#oeScheduleTask_preStationId").val(obj.attr("stationId"));
				return true;
			}
			return false;
		}
		return true;
	}
	</script>
</html>