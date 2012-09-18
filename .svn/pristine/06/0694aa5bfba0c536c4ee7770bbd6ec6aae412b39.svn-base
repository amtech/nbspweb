<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/header.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link href="${ctx }/js/WdatePicker/skin/WdatePicker.css"
			type="text/css" rel="stylesheet" />
		<link href="${ctx }/js/jQuery/ztree/css/zTreeStyle/zTreeStyle.css"
			type="text/css" rel="stylesheet" />
		<!--jvalidation  -->
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jvalidation/jquery.validate.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jvalidation/jquery.validate.cn.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jvalidation/messages_cn.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jvalidation/additional-methods.min.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jvalidation/jquery.validate.ex.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/WdatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/jQuery/ztree/jquery.ztree.all.min.js"></script>
		<script language="javascript" type="text/javascript">
	jQuery(function() {
		var strVoucherGroupSelect ="";
		//启用Jquery验证
		jQuery("#patrolinfoForm").validate({
			focusInvalid : false
		});
		getVoucherGroupData();
	});
	// 获取巡检组
	function getConPerson() {
		var val = showOrgPerson('${ctx}/commonaccess!getstaff.action?orgtype=2&flag=radio');
		if (val) {
			jQuery("#patrolinfo_patrolgroupid").val(val[0]);
			jQuery("#patrolinfo_patrolgroupname").val(val[1]);
		}
	}
	//提交数据
	function submitData() {
		return true;
	}

	//动态添加行---------------------------------------------------------------------------------------------------------------
	//添加处理人
	var processerIndex = ${rowNub};
	var stopIndex = 0;
	function addTrouble() {
		processerIndex++;
		var content = "";
		content +='<tr id="processer_'+processerIndex+'">'
		content += '<td style="width:10%">' + processerIndex + '</td>';
		content += '<td style="width:25%">';
		content += strVoucherGroupSelect;
		content += '</td>';
		content += '<td style="width:20%">';
		content += '<input name="position" id="position_'+processerIndex+'" class="inputtext required" /><span style="color: red">*</span>';
		content += '</td>';
		content += '<td style="width:20%">';
		content += '<input name="remark" id="remark_'+processerIndex+'" class="inputtext required" /><span style="color: red">*</span>';
		content += '</td>';
		content += '<td style="width:25%">';
		content += '<input name="expectendtime" id="expectendtime_'+processerIndex+'" class="inputtext Wdate required" onfocus="WdatePicker()" /><span style="color: red">*</span>';
		content += '</td>';
		content += '</tr>';
		$("#tableContainer").append(content);
	}
	
	//减少处理人
    function delTrouble(){
		if(stopIndex==processerIndex)return;
     	var row_index = document.getElementById("processer_"+processerIndex).rowIndex;
     	document.getElementById("tableContainer").deleteRow(row_index); 
     	processerIndex--;
    }
	

	function getVoucherGroupData(){
	    jQuery.ajax({
	        type: "get",
	        url: "${ctx}/commonaccess!getDic.action?columntype=device_type",
	        dataType: "json",
	        data: "",
	        cache: true,
	        success: function(res) {
	                var str = "<select name='devicetype' class='inputtext'>";
	                var option = "";
	                for(var j =0;j < res.length; j++)
	                {
	                    option += "<option value=\"" + res[j].CODEVALUE + "\">" + res[j].LABLE + "</option>";
	                }
	                strVoucherGroupSelect = str+option+"</select>";
	                
	        }
	    });
	}
</script>
	</head>
	<body>
		<form action="${ctx }/ah/ahFamilyBandRecodeAction!save.action"
			id="patrolinfoForm" name="patrolinfoForm" method="post">
			<div class="title_bg">
				<div id="title" class="title">
					当前位置-登记家庭宽带巡检记录修改
				</div>
			</div>
			<div class="tabcontent">
				<table style="width: 70%" border="0" align="center">
					<tr>
						<th>
							代维单位：
						</th>
						<td>
							<input id="patrolinfo_id" name="id" type="hidden"
								value="${familyRecode['id'] }" />
							<c:if test="${familyRecode['orgid']!=null&&familyRecode['orgid']!='' }">
								<input id="patrolinfo_contractorid" name="contractorId"
									type="hidden" value="${familyRecode['orgid'] }" />
								<baseinfo:org displayProperty="ORGANIZENAME"
									id="${familyRecode['orgid'] }"></baseinfo:org>
							</c:if>
							<c:if test="${familyRecode['orgid']==null||familyRecode['orgid']=='' }">
								<input id="patrolinfo_contractorid" name="contractorId"
									type="hidden" value="${LOGIN_USER.orgId }" />
								<baseinfo:org displayProperty="ORGANIZENAME"
									id="${LOGIN_USER.orgId }"></baseinfo:org>
							</c:if>
						</td>
						<th>
							巡检人：
						</th>
						<td>
							<input id="patrolinfo_patrolgroupname" name="patrolgroupname"
								class="inputtext required" value="${familyRecode['username'] }"
								readonly="readonly" />
							<a href="javascript:getConPerson();"> <img border="0"
									src="${ctx}/css/images/selectcode.gif" /> </a>
							<input id="patrolinfo_patrolgroupid" name="patrolmanId"
								value="${familyRecode['userid'] }" type="hidden">
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th>
							巡检开始时间：
						</th>
						<td>
							<input id="patrolinfo_starttime" name="startTime" type="text"
								class="Wdate inputtext" value="${familyRecode['starttime'] }"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d',maxDate:'#F{$dp.$D(\'patrolinfo_endtime\')}'})" />
							<span style="color: red">*</span>
						</td>
						<th>
							巡检结束时间
						</th>
						<td>
							<input id="patrolinfo_endtime" name="endTime" type="text"
								class="Wdate inputtext" value="${familyRecode['endtime'] }"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'patrolinfo_starttime\')}'})" />
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th>
							覆盖用户数：
						</th>
						<td>
							<input id="patrolinfo_usernum" name="userNum"
								class="inputtext required number"
								value="${familyRecode['usernum'] }" />
							<span style="color: red">*</span>
						</td>
						<th>
							巡检覆盖范围：
						</th>
						<td>
							<input id="patrolinfo_range" name="range"
								class="inputtext required" value="${familyRecode['range'] }" />
							<span style="color: red">*</span>
						</td>
					</tr>



					<tr>
						<td colspan="4">
							<b>发现隐患列表</b>
						</td>
					</tr>
				</table>
				<table id="tableContainer" style="width: 70%" border="0"
					align="center">
					<tr>
						<td style="width: 10%">
							序号
						</td>
						<td style="width: 25%">
							隐患设备类型
						</td>
						<td style="width: 20%">
							隐患位置
						</td>
						<td style="width: 20%">
							隐患描述
						</td>
						<td style="width: 25%">
							预计完成时间
						</td>
					</tr>
					<c:if test="${troubleList!=null}">
						<c:forEach items="${troubleList}" var="trouble" varStatus="tag">
						<tr id="processer_${tag.index+1}">
						<td style="width: 10%">${trouble['ordernumber'] }</td> 
						<td style="width: 25%">
							<baseinfo:dicselector columntype="device_type" cssClass="inputtext required" name="devicetype" keyValue="${trouble['devicetype'] }" type="select" />
							<span style="color: red">*</span>
						</td>
						<td style="width: 20%">
							<input name="position" class="inputtext required" value="${trouble['position'] }"/>
							<span style="color: red">*</span>
						</td>
						<td style="width: 20%">
							<input name="remark" class="inputtext required" value="${trouble['remark'] }"/>
							<span style="color: red">*</span>
						</td>
						<td style="width: 25%">
							<input name="expectendtime" class="inputtext required" value='<fmt:formatDate value="${trouble['expectendtime'] }" pattern="yyyy-MM-dd HH:mm:ss" />'
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d'})"/>
							<span style="color: red">*</span>
						</td>
					</tr>
						</c:forEach>
					</c:if>
				</table>

				<div style="text-align: center; margin-top: 10px">
					 <input id="btnSubmit" value="添加隐患" type="button" class="button"
						onclick="addTrouble();" />
					 <input id="btnSubmit" value="减少隐患" type="button" class="button"
						onclick="delTrouble();" />
					<input id="btnSubmit" value="提交" type="submit" class="button"
						onclick="submitData();" />
					<input name="btnReset" value="重置" type="reset" class="button"
						onclick="reset();" />
				</div>
			</div>
		</form>


	</body>
</html>