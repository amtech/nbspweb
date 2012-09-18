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
		<script language="javascript" type="text/javascript">
In.add('vm',{path:'${ctx}/js/jQuery/jvalidation/jquery.validate.min.js',type:'js',charset:'utf-8'});
In.add('vcn',{path:'${ctx}/js/jQuery/jvalidation/messages_cn.js',type:'js',charset:'utf-8',rely:['vm']});
In.add('vex',{path:'${ctx}/js/jQuery/jvalidation/jquery.validate.ex.js',type:'js',charset:'utf-8',rely:['vcn']});
In.add('vam',{path:'${ctx}/js/jQuery/jvalidation/additional-methods.min.js',type:'js',charset:'utf-8',rely:['vex']});
In.add('jqui',{path:'${ctx}/js/jQuery/jqueryui/css/redmond/jquery-ui.custom.css'});
In.add('jquijs',{path:'${ctx}/js/jQuery/jqueryui/jquery-ui.custom.min.js',type:'js',charset:'utf-8',rely:['jqui']});
In.add('common',{path:'${ctx}/common/js/common.js',type:'js',charset:'utf-8'});
In.add('jgcss',{path:'${ctx}/js/jQuery/jqgrid/css/ui.jqgrid.css',charset:'utf-8',rely:['jquijs']});
In.add('jgjs',{path:'${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js',type:'js',charset:'utf-8',rely:['jgcss']});
In.add('jgcn',{path:'${ctx}/js/jQuery/jqgrid/grid.locale-cn.js',type:'js',charset:'utf-8',rely:['jgjs']});
In.add('wdatejs',{path:'${ctx}/js/WdatePicker/WdatePicker.js',type:'js',charset:'utf-8'});
In.add('wmaintain_common',{path:'${ctx}/workflow/wmaintain/js/wmaintain_common.js',type:'js',charset:'utf-8'});
In.add('wmaintainplan_input',{path:'${ctx}/workflow/wmaintain/js/wmaintainplan_input.js',type:'js',charset:'utf-8',rely:['wmaintain_common']});
	var jqgrid, mySGdata, mydata;//全局jqgrid,子表数据,主表数据
	In.ready('vam','jquijs','jgcn','common','wdatejs','wmaintainplan_input',function(){
		setContextPath("${ctx}");
		var planId="${plan.id}";
		//启用Jquery验证
		jQuery("#patrolinfoForm").validate({
			focusInvalid : false,
			submitHandler : function(form) {
				if (checkData()) {
					form.submit();
				}
			}
		});
		jqgrid = jQuery("#plansitegrid")
				.jqGrid(
						{
							datatype : "local",
							rownumbers : true,
							colNames : [ 'ID', '站点名称', '站点地址', '操作' ],
							colModel : [ 
							{name : 'ID',index : 'ID',sortable : false,hidden : true}, 
							{name : 'RS_NAME',index : 'RS_NAME',sortable : false}, 
							{name : 'ADDRESS',index : 'ADDRESS',sortable : false}, 
							{name : 'RES_',index : 'RES_',sortable : false,width : 45,formatter : mainOperateFormatter} 
							],
							autowidth : true,
							height : "250",
							gridview : true,
							shrinkToFit : true,
							caption : '问题站点',
							autoencode : true,
							subGrid : true,
							subGridOptions : {
								reloadOnExpand : false
							},
							subGridRowExpanded : function(subgrid_id, row_id) {
								var subgrid_table_id;
								subgrid_table_id = "mySubGridName" + row_id;
								$("#" + subgrid_id)
										.html(
												"<table id='" + subgrid_table_id + "'></table>");
								jQuery("#" + subgrid_table_id)
										.jqGrid(
												{
													datatype : "local",
													shrinkToFit : true,
													colNames : [ 'ID','SUBITEM_ID','巡检项', '问题','资源ID', '专业类型','操作' ],
													colModel : [
													{name : "ID",index : "ID",width : 20,key : true,hidden : true},
													{name : "SUBITEM_ID",index : "SUBITEM_ID",hidden : true},
													{name : "SUBITEM_NAME",index : "SUBITEM_NAME",width : 100,align : "left"},
													{name : "SUBITEM_PATROL",index : "SUBITEM_PATROL",width : 100,align : "left"},
													{name : "RESOURCE_ID",index : "RESOURCE_ID",hidden : true},
													{name : "RESOURCE_TYPE",index : "RESOURCE_TYPE",hidden : true},
													{name : "RES_",index : "RES_",width : 45,align : "left",formatter : subOperateFormatter} 
													],
													autowidth : true,
													loadonce : true,
													loadComplete : function() {
														var dataIds = $('#plansitegrid').jqGrid('getDataIDs');
														var data = $('#plansitegrid').jqGrid('getRowData',dataIds[row_id - 1]);
														loadDetail(data.ID,subgrid_table_id);
													}
												});
							}
						});
		if(planId!=""){
			setMydata(${siteDataJson});
			setMySGdata(${resultDataJson});
			createInputElement();
			jqgrid.clearGridData().setGridParam({
				data : mydata
			}).trigger("reloadGrid");
		}
	});
</script>
	</head>
	<body>
		<form action="${ctx }/workflow/wmaintainCreatePlanAction!save.action"
			id="patrolinfoForm" name="patrolinfoForm" method="post">
			<div class="title_bg">
				<div id="title" class="title">
					当前位置-制定维修计划
				</div>
			</div>
			<div class="tabcontent">
				<table style="width: 75%" border="0" align="center">
					<tr>
						<th>
							计划名称：
						</th>
						<td colspan="3">
							<input id="p_id" name="plan.id" type="hidden" value="${plan.id }" />
							<input id="p_taskId" name="plan.workflowTaskId" type="hidden" value="${task_id }" />
							<input id="planname" name="plan.planName" class="inputtext" style="width:560px" readonly="readonly" value="${plan.planName }" />
						</td>
					</tr>
					<tr>
						<th>
							计划时间：
						</th>
						<td colspan="3">
							<fmt:formatDate value="${plan.startDate }" pattern="yyyy-MM-dd" var="startDate" />
							<fmt:formatDate value="${plan.endDate }" pattern="yyyy-MM-dd" var="endDate" />
							<input id="patrolinfo_starttime" name="plan.startDate" type="text" class="Wdate  required" width="20%" value="${startDate }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:function(dp){change();},minDate:'%y-%M-%d',maxDate:'#F{$dp.$D(\'patrolinfo_endtime\')}'})" />
							至
							<input id="patrolinfo_endtime" name="plan.endDate" type="text" width="20%" class="Wdate required" value="${endDate }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:function(dp){change();},minDate:'#F{$dp.$D(\'patrolinfo_starttime\')}'})" />
							<span style="color: red">*</span>
					</tr>
					<tr>
						<th>
							巡检组：
						</th>
						<td>
							<input id="patrolinfo_patrolgroupname" name="patrolgroupname" class="inputtext required" value="${plan.patrolGroupName }" readonly="readonly" />
							<a href="javascript:getPatrolGroup();"> <img border="0" src="${ctx}/css/image/groupselect.png" /> </a>
							<input id="patrolinfo_patrolgroupid" name="plan.patrolGroup" value="${plan.patrolGroup }" type="hidden">
							<span style="color: red">*</span>
						</td>
						<th>
							专业类型
						</th>
						<td>
							<baseinfo:customselector name="plan.businessType" data="${businessTypeMap}" isReversal="true" cssClass="inputtext required" id="businessType" style="width: 180px" keyValue="${plan.businessType }"></baseinfo:customselector>
							<font style="color: red;">&nbsp;*</font>
						</td>
					</tr>
					<tr>
						<th>
							巡检问题站点：
						</th>
						<td colspan="3">
							<a href="javascript:selectResources();"> 选择站点</a>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<table id="plansitegrid"></table>
						</td>
					</tr>
					<tr>
						<th>
							审核人：
						</th>
						<td colspan="3">
							<input id="patrolinfo_acceptUsername" name="approvername" value="${plan.auditorName }" class="inputtext required" readonly="readonly" />
							<a href="javascript:searchAcceptUser('${LOGIN_USER.regionId }');"> <img border="0" src="${ctx}/css/image/personselect.png" /> </a>
							<input id="patrolinfo_acceptUserid" name="plan.auditor" value="${plan.auditor }" type="hidden" />
							<span style="color: red">*</span>
						</td>
					</tr>
				</table>
				<div style="text-align: center; margin-top: 10px">
					<input id="is_submited" name="plan.isSubmited" value="1" type="hidden" />
					<input name="btnSave" value="保存" type="submit" class="button" onclick="saveData();" />
					<input name="btnSubmit" value="提交" type="submit" class="button" onclick="submitData();" />
					<input name="btnReset" value="重置" type="button" class="button" onclick="resetData();" />
					<c:if test="${not empty plan.id }">
						<input name="btn" value="返回" type="button" class="button" onclick="history.go(-1);" />
					</c:if>
				</div>
			</div>
		</form>
	</body>
	<script type="text/javascript">
	function checkData() {
		if(jQuery("input[name='plan.patrolRecordId']").length==0){
			alert("没有选择问题站点！");
			return false;
		}
		return true;
	}
	//load子表明细数据
	function loadDetail(id, subgrid_table_id) {
		if(!!mySGdata){
			for ( var i = 0; i < mySGdata.length; i++) {
				if (mySGdata[i].RES_ == id) {
					jQuery("#" + subgrid_table_id).jqGrid('addRowData', i + 1,
						mySGdata[i]);
				}
			}
		}
	}
	/**
	 * 打开选择资源站点页面
	 */
	function selectResources() {
		var businessType = jQuery("#businessType").val();
		var url = contextPath
				+ "/workflow/wmaintainPlanResourceAction!list.action?businessType="+businessType+"&planId=${plan.id}";
		var patrolgroupid = jQuery("#patrolinfo_patrolgroupid").val();
		if (businessType == "") {
			alert("没有选择专业类型！");
			return;
		}
		if (patrolgroupid) {
			url += "&patrolGroup=" + patrolgroupid;
			var val = window
					.showModalDialog(url, '',
							'status:no;center:yes;dialogWidth:500px;dialogHeight:400px;');
			mydata = val[0];
			mySGdata = val[1];
			clearInputElement();
			createInputElement();
			jqgrid.clearGridData().setGridParam({
				data : mydata
			}).trigger("reloadGrid");
		} else {
			alert("请选择巡检组！");
		}
	}
	</script>
</html>