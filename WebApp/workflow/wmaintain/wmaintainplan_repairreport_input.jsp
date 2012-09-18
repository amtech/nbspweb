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
In.ready('vam','jgcn','common','wdatejs',function(){
	jQuery(function() {
		setContextPath("${ctx}");
		jQuery("#patrolRecordForm").validate({
			focusInvalid : false,
			submitHandler : function(form) {
				getGridData();
				if (checkGridData()) {
					form.submit();
				}
			}
		});
		var jqgrid = jQuery("#wmainplangrid").jqGrid({
					url : "${ctx}/workflow/wmaintainSiteAction!subList.action?id=${plan.id}&type=1",
					datatype : "json",
					mtype : 'GET',
					rownumbers : true,
					height : 300,
					rowNum: -1,
					editurl: 'clientArray',
					colNames : ['ID', '巡检项', '问题', '整改状态', '维护时间', '处理说明','站点名称' ],
					colModel : [{
						name : 'ID',
						id : 'ID',
						sortable : false,
						hidden : true
					}, {
						name : 'SUBITEM_NAME',
						id : 'SUBITEM_NAME',
						sortable : false
					}, {
						name : 'SUBITEM_PATROL',
						id : 'SUBITEM_PATROL',
						sortable : false
					}, {
						name : 'MAINTAIN_RESULT',
						id : 'MAINTAIN_RESULT',
						sortable : false,
						 editable: true,
						 edittype:"select",
						 editoptions:{value:"无法处理:无法处理;已处理:已处理"}
					}, {
						name : 'MAINTAIN_DATE',
						id : 'MAINTAIN_DATE',
						sortable : false,
						 editable:true, 
						 editoptions:{dataInit: function(element) { 
							 $(element).addClass('Wdate');
							 $(element).focus(function() {
								 WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});
								});
					      } 
							}
					}, {
						name : 'MAINTAIN_RECORD',
						id : 'MAINTAIN_RECORD',
						sortable : false,
						editable: true,
						edittype:"textarea"
					},{
						name : 'RS_NAME',
						id : 'RS_NAME',
						sortable : false
					} ],
					shrinkToFit : true,
					viewrecords : true,
					jsonReader : {
						root : "root",
						repeatitems : false,
						id : "ID"
					},
					onSelectRow: function(id){
						jQuery('#wmainplangrid').jqGrid('editRow',id,true);
					},
					grouping:true,
				   	groupingView : {
				   		groupField : ['RS_NAME'],
				   		groupColumnShow : [false]
				   	}
		});
	});
});
</script>
	</head>
	<body>
		<form
			action="${ctx }/workflow/wmaintainCreateReportAction!save.action"
			id="patrolRecordForm" name="patrolRecordForm" method="post">
			<div class="title_bg">
				<div class="title">
					当前位置-填写维修作业报告
				</div>
			</div>
			<div class="tabcontent">
				<table border="0" align="center">
					<tr>
						<th>
							计划名称：
						</th>
						<td colspan="3">
							${plan.planName }
						</td>
					</tr>
					<tr>
						<th>
							计划开始时间：
						</th>
						<td>
							<fmt:formatDate value="${plan.startDate }" pattern="yyyy-MM-dd" />
						</td>
						<th>
							计划结束时间：
						</th>
						<td>
							<fmt:formatDate value="${plan.endDate }" pattern="yyyy-MM-dd" />
						</td>
					</tr>
					<tr>
						<th>
							代维公司：
						</th>
						<td>
							${plan.orgName }
						</td>
						<th>
							巡检组：
						</th>
						<td>
							${plan.patrolGroupName }
						</td>
					</tr>
					<tr>
						<th>
							计划制定人员：
						</th>
						<td>
							${plan.createrName }
						</td>
						<th>
							计划制定时间：
						</th>
						<td>
							<fmt:formatDate value="${plan.createDate }"
								pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<table id="wmainplangrid" style="width:100%"></table>
						</td>
					</tr>
					<tr>
						<th>
							维护总结
						</th>
						<td colspan="3">
							<textarea id="plan_report" name="plan.report" class="inputtext required" style="height: 80px;width:450px">${plan.report }</textarea>
							<span style="color: red">*</span>
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
				<table border="0" align="center" style="display: none;">
					<tr>
						<th>
							维护资源数量：
						</th>
						<td>
							${number_map.total_num }
						</td>
						<th>
							修复资源数量：
						</th>
						<td>
							${number_map.maintain_num }
						</td>
					</tr>
					<tr>
						<th>
							问题未解决资源数量：
						</th>
						<td>
							${number_map.remain_num }
						</td>
						<th>
							未维护资源数量：
						</th>
						<td>
							${number_map.nonmaintain_num }
						</td>
					</tr>
				</table>
				<div style="text-align: center; margin-top: 10px">
					<input id="is_submited" name="plan.isSubmited" value="1" type="hidden" />
					<input id="id" name="plan.id" value="${id }" type="hidden" />
					<input id="task_id" name="plan.workflowTaskId" value="${task_id }" type="hidden" />
					<input id="businesstype" name="plan.businessType" value="${plan.businessType }" type="hidden" />
					<input name="btnSave" value="保存" type="submit" class="button" onclick="saveData();" />
					<input name="btnSubmit" value="提交" type="submit" class="button" onclick="submitData();" />
					<input name="btnReset" value="重置" type="button" class="button" onclick="resetData();" />
					<input type="button" class="button" onclick="history.back()" value="返回" />
					<input name="plan.resultId" id="resultid" type="hidden" />
					<input name="plan.resultMaintainResult" id="maintainresult" type="hidden" />
					<input name="plan.resultMaintainRecord" id="maintainrecord" type="hidden" />
					<input name="plan.resultMaintainDate" id="maintaindate" type="hidden" />
				</div>
			</div>
		</form>
	</body>
	<script type="text/javascript">
	/**
 * 获取审核人
 */
function searchAcceptUser(regionId) {
	var val = showOrgPerson('${ctx}/commonaccess!getstaff.action?orgtype=1&flag=radio&regionid='+regionId);
	if (val) {
		jQuery("#patrolinfo_acceptUserid").val(val[0]);
		jQuery("#patrolinfo_acceptUsername").val(val[1]);
	}
}
/**
 * 保存数据
 */
function saveData() {
	jQuery("#is_submited").val("0");
	return true;
}
/**
 * 提交数据
 */
function submitData() {
	jQuery("#is_submited").val("1");
	return true;
}
/**
 * 重置数据
 */
function resetData() {
	jQuery("#plan_report").val("");
	jQuery("#patrolinfo_acceptUsername").val("");
	jQuery("#patrolinfo_acceptUserid").val("");
}

//获取GRID列表中数据
function getGridData(){
	 //获取列数据
	  var rows= jQuery("#wmainplangrid").jqGrid('getRowData');
	  var resultidarr=new Array();
	  var maintainresultarr=new Array();
	  var maintainrecordarr=new Array();
	  var maintaindatearr=new Array();
	  //将修改的值状态改变
	  for(var i=0;i<rows.length;i++){
	      var row=rows[i];
	      jQuery("#wmainplangrid").jqGrid('saveRow',row.ID);
	  }
	  //获取修改后的值，存储到数组中
	  rows=jQuery("#wmainplangrid").jqGrid('getRowData');
		  for(var i=0;i<rows.length;i++){
		      var row=rows[i];
		      resultidarr.push(row.ID);
		      maintainresultarr.push(row.MAINTAIN_RESULT);
		      maintainrecordarr.push(row.MAINTAIN_RECORD);
		      maintaindatearr.push(row.MAINTAIN_DATE);
		  }
		  //逗号隔开提交到后台
      jQuery('#resultid').val(resultidarr.join(","));
      jQuery('#maintainresult').val(maintainresultarr.join(","));
      jQuery('#maintainrecord').val(maintainrecordarr.join(","));
      jQuery('#maintaindate').val(maintaindatearr.join(","));
}
//检查Gird数据
function checkGridData(){
	var grid=jQuery("#wmainplangrid");
	 //获取列数据
	  var rows= grid.jqGrid('getRowData');
	  //将修改的值状态改变
	  for(var i=0;i<rows.length;i++){
		  if(rows[i].MAINTAIN_RESULT==""){
			  alert("第 "+(i+1)+"行输入的整改状态为空！");
			  return false;
		  }
		  if(rows[i].MAINTAIN_DATE==""){
			  alert("第 "+(i+1)+"行输入的整改时间为空！");
			  return false;
		  }
	  }
		  return true;
}
</script>
</html>

