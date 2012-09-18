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
	In.add('jqui',{path:'${ctx}/js/jQuery/jqueryui/css/redmond/jquery-ui.custom.css'});
In.add('jquijs',{path:'${ctx}/js/jQuery/jqueryui/jquery-ui.custom.min.js',type:'js',charset:'utf-8',rely:['jqui']});
In.add('vm', {path : '${ctx}/js/jQuery/jvalidation/jquery.validate.min.js',type : 'js',charset : 'utf-8'});
In.add('vcn', {path : '${ctx}/js/jQuery/jvalidation/messages_cn.js',type : 'js',charset : 'utf-8',rely : [ 'vm' ]});
In.add('vex', {path : '${ctx}/js/jQuery/jvalidation/jquery.validate.ex.js',type : 'js',charset : 'utf-8',rely : [ 'vcn' ]});
In.add('vam', {path : '${ctx}/js/jQuery/jvalidation/additional-methods.min.js',type : 'js',charset : 'utf-8',rely : [ 'vex' ]});
In.add('common', {path : '${ctx}/common/js/common.js',type : 'js',charset : 'utf-8'});
In.add('wdatejs', {path : '${ctx}/js/WdatePicker/WdatePicker.js',type : 'js',charset : 'utf-8'});

In.ready('vam', 'common', 'wdatejs','jquijs', function() {
	setContextPath("${ctx}");
	
	jQuery("#inputForm").validate({
		    debug: true, 
			submitHandler: function(form){
				if(checkItem()){
					inputForm.submit();
				}
	        }
	});
	
	//合并行
	var rowCount = ${maxitemcount};
    for(var i = 0; i <= rowCount; i++)
    {
     table_rowspan("#MonthKH",i);
    }
    //合并列
 	var colCount =${fn:length(templatecontent)};
    for(var i = 0; i <= colCount; i++)
    {
     table_colspan("#MonthKH",i,rowCount);
    }
	
});
// 搜索代维
function searchQueryOrg(url) {
	var val = window.showModalDialog(url, '','status:no;center:yes;dialogWidth:400px;dialogHeight:300px;');
	var orgId = "";
	var orgName = "";
	var regionId = "";
	if (!!val) {
		for (i = 0; i < val.length; i++) {
		    orgId += val[i].ID + ",";
			orgName += val[i].NAME + ",";
			regionId += val[i].REGIONID + ",";
		}
		orgId = orgId.substring(0, orgId.length - 1);
		orgName = orgName.substring(0, orgName.length - 1);
		regionId = regionId.substring(0, regionId.length - 1);
		
		jQuery("#input_contractor_id").val(orgId);
		jQuery("#input_contractor_name").val(orgName);
		jQuery("#input_region_id").val(regionId);
	}
}
// 搜索站点
function searchResource() {
	var url = '${ctx}/commonaccess!getresourceinfo.action';
	if(""!=jQuery("#input_contractor_id").val()){
		url+="?orgid="+jQuery("#input_contractor_id").val();
	}
	var val = showresource(url);
	if (val) {
		jQuery("#input_site_id").val(val[0].ID);
		jQuery("#input_site_name").val(val[0].NAME);
	}
}
// 搜索人员
function searchPrincipal(url) {
    if(""!=jQuery("#input_contractor_id").val()){
		url+="&orgid="+jQuery("#input_contractor_id").val();
	}
	var val = showOrgPerson(url);
	if (!!val) {
		jQuery("#input_principal_id").val(val[0]);
		jQuery("#input_principal_name").val(val[1]);
	}
}
// 搜索人员
function searchInspector(url) {
	var val = showOrgPerson(url);
	if (!!val) {
		jQuery("#input_principal_id").val(val[0]);
		jQuery("#input_principal_name").val(val[1]);
	}
}

function checkItem(){
   var regx=/^([1-9]\d*|0)(\.\d*[0-9])?$/;
   var scoreInputs=jQuery("input[name='examination.itemScore']");
   for(var i=0;i<scoreInputs.length;i++){
      var message = "";
   	  var scoreId = "assessExaminationResult_itemScore_"+i;
   	  var descId  = "assessExaminationResult_indicatorsValue_"+i;
   	  var weightId = "assessExaminationResult_weight_"+i;
   	  var scoreValue = jQuery.trim($("#"+scoreId).val());
   	  var descValue = jQuery.trim($("#"+descId).val());
   	  var weightValue = jQuery.trim($("#"+weightId).val());
   	  if(""==scoreValue){
   	  	continue;
   	  }
   	  if(""!=scoreValue&&regx.test(scoreValue)&&(scoreValue<=weightValue)){
   	  	continue;
   	  }
   	  if(!regx.test(scoreValue)){
   	  		message = "第  "+(i+1)+" 行的'扣分'必须为(正)数字！";
   	  }else{
   	  		message = "第  "+(i+1)+" 行的'扣分'不能大于'权重'！";
   	  }
   	  alert(message);
   	  return false;
   }   
   return true;
}
</script>
</head>
<body>
	<form action="${ctx }/assess/assessExaminationAction!save.action"
		id="inputForm" name="inputForm" method="post">
		<div class="title_bg">
			<div id="title" class="title">当前位置-现场检查-考核评分</div>
		</div>
		<div class="tabout">
			<input type="hidden" name="examination.tableId" value="${tableId}">
			<input id="input_region_id" name="examination.regionId" type="hidden" />
			<table border="0"  cellpadding="3" cellspacing="0">
				<tr>
					<td colspan="4" align="center"><b>${tableName}</b></td>
				</tr>
				<tr>
					<th>代维公司</th>
					<td colspan="3" ><input id="input_contractor_name"
						class="treetext required" readonly="readonly" /> <a
						href="javascript:searchQueryOrg('${ctx}/commonaccess!getorg.action?orgtype=2');">
							<img border="0" src="${ctx}/css/images/selectcode.gif" /> </a> <span
						style="color: red">*</span> <input type="hidden"
						id="input_contractor_id" name="examination.contractorId">
					</td>
				</tr>
				<tr>
					<th>站点名</th>
					<td><input id="input_site_name" name="input_site_name"
						class="treetext required" readonly="readonly" /> <a
						href="javascript:searchResource();"> <img border="0"
							src="${ctx}/css/images/selectcode.gif" /> </a> <span
						style="color: red">*</span> <input id="input_site_id"
						name="examination.siteId" type="hidden" /></td>
					<th>维护责任人</th>
					<td><input id="input_principal_name"
						name="input_principal_name" class="treetext required"
						readonly="readonly" /><a
						href="javascript:searchPrincipal('${ctx}/commonaccess!getstaff.action?orgtype=2&flag=radio&regionid=${user.regionId }');">
							<img border="0" src="${ctx}/css/images/selectcode.gif" /> </a> <span
						style="color: red">*</span> <input type="hidden"
						id="input_principal_id" name="examination.principal"
						class="treetext" /></td>
				</tr>
				<tr>
					<th>检查日期</th>
					<td><input id="inspectionDate"
						name="examination.inspectionDate" type="text" value=""
						class="Wdate treetext required" style="width: 220px"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /> <span
						style="color: red">*</span></td>
					<th>检查人员</th>
					<td><input id="input_inspector_name"
						name="input_inspector_name" class="treetext required"
						readonly="readonly" value="${user.userName }" /><a
						href="javascript:searchInspector('${ctx}/commonaccess!getstaff.action?orgtype=1&flag=radio&regionid=${user.regionId }');">
							<img border="0" src="${ctx}/css/images/selectcode.gif" /> </a> <span
						style="color: red">*</span> <input type="hidden"
						id="input_inspector_id" name="examination.inspector"
						value="${user.personId }" /></td>
				</tr>
				<tr>
					<td colspan="4">
						<table id="MonthKH">
							<thead>
								<tr>
									<th colspan="${maxitemcount}" style="width:80px;" nowrap>考核项目</th>
									<th style="width:20px;" nowrap>序号</th>
									<th style="width:40px;" nowrap>基准值</th>
									<th style="width:40px;" nowrap>挑战值</th>
									<th style="width:30px;" nowrap>权重</th>
									<th style="width:150px;" nowrap>计算公式和要求</th>
									<th nowrap>评价标准</th>
									<th style="width:80px;" nowrap>扣分</th>
									<th style="width:80px;" nowrap>扣分说明</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${templatecontent}"
									varStatus="status">
									<tr>
										<c:forEach var="itemname" items="${item.ITEMNAMELIST}">
											<td>${itemname}</td>
										</c:forEach>
										<td>${status.index+1 }</td>
										<td>${item.BENCHMARK_VALUE}</td>
										<td>${item.CHALLENGE_VALUE}</td>
										<td>${item.WEIGHT}</td>
										<td>${item.DEMAND_DESC}</td>
										<td>${item.EVALUATION_CRITERION}</td>
										<td style="text-align: center;"><input
											id="assessExaminationResult_itemScore_${status.index }"
											name="examination.itemScore" value="${item.SCORE }"
											type="text" style="width: 80px" type="text"/></td>
										<td style="text-align: center;">
											<input
											id="assessExaminationResult_contentId${item.CONTENTID}"
											name="examination.contentId" type="hidden"
											style="width: 80px" value="${item.CONTENTID }" /> 

											<input
											id="assessExaminationResult_indicatorsValue_${status.index }"
											name="examination.ratingDesc" value="无" style="width: 80px" type="text" />
											
											<input
											id="assessExaminationResult_weight_${status.index}"
											type="hidden"  value="${item.WEIGHT }" /> 
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table></td>
				</tr>
			</table>
			<div style="text-align: center; margin-top: 10px">
				<input type="button" class="button" value="返回"
					onclick="history.go(-1);" /> <input type="submit" class="button"
					value="提交" />
			</div>
		</div>
	</form>
</body>
</html>