<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="${ctx}/js/selected_options.js" type="text/javascript"></script>
<style type="text/css">
.treetext {
	border: solid 1px #999999;
	background-color: #ffffff;
	text-align: left;
	height: 20px;
	width: 60%;
}
</style>
<script type="text/javascript">
	jQuery(function() {});
	//查询考核人
	function perosnQueryFetch(){
		var queryRegionId = $("#query_region_id").val();
		var queryOrgId = $("#query_org_id").val();
		var queryBusinessType = $("#query_businesstype").val();
		var queryPersonJob = $("#query_personjob").val();
		var queryStr = "&regionId="+queryRegionId+"&orgId="+queryOrgId+"&businessType="+queryBusinessType+"&postOffice="+queryPersonJob;
		var actionUrl = "${ctx}/ah/personFlowAction!searchRatingPersons.action?rnd="+Math.random()+queryStr;
		$('#oldResources').empty();
		jQuery.ajax({
			url:actionUrl,
			success:function(data,textStatus,jqXHR){
				var str = jqXHR.responseText;
				if(str != ''){
					var strings = str.split(";");
					for(var i=0; i<strings.length; i++){
						if(strings[i] != ""){
							var xstrings = strings[i].split(",");
							jQuery('#oldResources').append("<option value='"+xstrings[1]+"'>"+xstrings[0]+"</option>");
						}
					}
				}else{
					jQuery('#oldResources').append("<option value=''>查询不到数据</option>");
				}
			},
			error:function(){
				alert("获取考核人员异常！");
			}
		});
	}
	//重置
    function perosnQueryReset(){
    	clearItmes_query_region();
    	clearItmes_query_org();
    	$("#query_businesstype").val("");
		$("#query_personjob").val("");
    }
    //保存提交
    function savaData(){
    	var checkProcesserRet = checkProcesser();
		if(checkProcesserRet!=0){
			 alert("第"+getThTitle(checkProcesserRet)+"环节处理人不能为空,请选择！");
			return false;
		}
     	$("#submitForm").submit();
    }
    //查找区域
    function searchQueryRegion(url){
    	var val = showRegion(url);
		if (!!val) {
			jQuery("#query_region_id").val(val[0]);
			jQuery("#query_region").val(val[1]);
		}
    }
    //查找组织
    function searchQueryOrg(url){
    	var val = showOrg(url);
		if (!!val) {
			jQuery("#query_org_id").val(val[0]);
			jQuery("#query_org").val(val[1]);
		}
    }
    //动态处理人---------------------------------------------------------------------------------------------------------------
    
    //添加处理人
    var processerIndex = ${processerIndex};
    function addProcesser(){
       processerIndex++;
      if(processerIndex>5){
      	processerIndex = 5;
      	return;
      }
      var content = '';
          content +='<tr id="processer_'+processerIndex+'">'
          content +='<th>第'+getThTitle(processerIndex)+'环节处理人</th>';
          content +='<td colspan="3">';
          content +='<input id="processer_'+processerIndex+'_name" type="text" class="treetext" readonly/>';
          content +='<a href=\'javascript:searchProcesser("processer_'+processerIndex+'_name","processer_'+processerIndex+'_id");\'>';
          content +='<img border="0" src="${ctx}/css/images/selectcode.gif" /></a>';
          content +='<input id="processer_'+processerIndex+'_id" name="processer" type="hidden"/>';
          content +='</td>';
          content +='</tr>';
     $("#tableContainer").append(content);
    }
    //减少处理人
    function delProcesser(){
		if(1==processerIndex)return;
     	var row_index = document.getElementById("processer_"+processerIndex).rowIndex;
     	document.getElementById("tableContainer").deleteRow(row_index); 
     	processerIndex--;
    }
   //获取TH表头
   function getThTitle(trIndex){
	  var indexStr = ['零','一','二','三','四','五','六','七','八','九','十'];
	  return indexStr[trIndex];
   }
    //判断处理人选择-依次选择
    function checkProcesser(){
    	var rear = 0;
    	for(var i = 1;i<=processerIndex;i++){
    		var processerValue = $("#processer_"+i+"_id").val();
    		if(''==processerValue){
    		   rear = i;
    		   break;
    		}
    	}
    	return rear;
    }
    //查找人员
    function searchProcesser(nameId,valueId){
        var url = "${ctx}/commonaccess!getstaff.action?orgtype=1&regionid=${user.regionId }";
		var val = showOrgPerson(url);
		if (!!val) {
			jQuery("#"+valueId).val(val[0]);
			jQuery("#"+nameId).val(val[1]);
		}
    }
</script>
</head>
<body>
		<div class="title_bg">
			<div id="title" class="title">当前位置-人员流程节点定义</div>
		</div>
		<div class="tabcontent">
		<form id="submitForm" action="${ctx }/ah/personFlowAction!save.action" method="post">
			<table id="tableContainer" border="0" align="center" cellpadding="3" cellspacing="0">
				<tr>
					<th ><b>人员信息</b></th>
					<th colspan="3">
					</th>
				</tr>
				<tr>
					<th>人员姓名</th>
					<td colspan="3">
						${personInfo['USERNAME']}
						<input type="hidden" name="newResources" value="${personInfo['SID']}"/>
					</td>
				</tr>
				<tr>
					<th>职位</th>
					<td colspan="3">
						<baseinfo:dic displayProperty="lable" codevalue="${personInfo['JOBINFO']}" columntype="job_type"></baseinfo:dic>
					</td>
				</tr>
				<tr>
					<th><b>处理人</b>
					</th>
					<th colspan="3"></th>
				</tr>
				
				<c:forEach var="processer" items="${processers}" varStatus="status" >
					<tr id="processer_${status.index+1}">
						<th>第<c:out value="${status.index+1}"/>环节处理人</th>
						<td colspan="3">
							<input id="processer_<c:out value="${status.index+1}"/>_name" type="text" class="treetext" readonly  value="${processer['PROCESSERNAME']}"/>
							<a href="javascript:searchProcesser('processer_<c:out value="${status.index+1}"/>_name','processer_<c:out value="${status.index+1}"/>_id');">
							<img border="0" src="${ctx}/css/images/selectcode.gif" /></a>
							<input type="hidden" id="processer_<c:out value="${status.index+1}"/>_id" name="processer" value="${processer['PROCESSER']}"/>
							<c:if test="${status.index==0}">
								&nbsp;<input type="button" class="button" onclick="addProcesser();" value="添加处理人"/>
								&nbsp;<input type="button" class="button" onclick="delProcesser();" value="减少处理人"/>
							</c:if>
						</td>
					</tr>
				</c:forEach>
				
			</table>
			</form>
			<div style="text-align: center; margin-top: 10px">
				<input type="button" class="button" onclick="savaData();" value="保存"/>
				<input type="button" class="button" onclick="history.back()" value="返回" />
			</div>
		</div>
</body>
</html>