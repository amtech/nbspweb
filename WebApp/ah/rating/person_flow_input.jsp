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
	width: 55%;
}
</style>
<script type="text/javascript">
	jQuery(function() {//初始化化树

	});
	//查询考核人
	function perosnQueryFetch(){
		var queryRegionId = $("#query_region_id").val();
		var queryOrgId = $("#query_org_id").val();
		var queryBusinessType = $("#query_businesstype").val();
		var queryPersonJob = $("#query_personjob").val();
		var queryPersonName=jQuery("#query_name").val();
		var queryStr = "&regionId="+queryRegionId+"&orgId="+queryOrgId+"&businessType="+queryBusinessType+"&personName="+queryPersonName+"&postOffice="+queryPersonJob;
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
    	var target = document.getElementById("newResources");
    	var checkProcesserRet = checkProcesser();
    	if(target.options.length==0){
			alert("请选择考核人！");
			return false;
		}
		if(checkProcesserRet!=0){
			 alert("第"+getThTitle(checkProcesserRet)+"环节处理人不能为空,请选择！");
			return false;
		}
		
    	for(var i=0; i<target.options.length; i++){
             target.options[i].selected=true;
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
    var processerIndex = 1;
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
					<th><b>过滤筛选条件</b>
					</th>
					<th colspan="3"></th>
				</tr>
				<tr>
					<th>
						所属区域
					</th>
					<td>
					  	<input id="query_region" class="inputtext" readonly="readonly" style="width:220px;" />
						<a href="javascript:searchQueryRegion('${ctx}/commonaccess!getregion.action');">
						<img border="0" src="${ctx}/css/images/selectcode.gif" /></a>
					    <input type="hidden" id="query_region_id" />
					</td>
					<th>
						代维公司
					</th>
					<td>
						<input id="query_org" class="inputtext" readonly="readonly" style="width:220px;" />
						<a href="javascript:searchQueryOrg('${ctx}/commonaccess!getorg.action?orgtype=2');">
						<img border="0" src="${ctx}/css/images/selectcode.gif" /></a>
						<input type="hidden" id="query_org_id" />
					</td>
				</tr>
				<tr>
					<th>
						专业
					</th>
					<td>
					  <baseinfo:dicselector name="query_businesstype" cssClass="treetext" columntype="businesstype" type="select" style="width:220px;" isQuery="query"></baseinfo:dicselector>
					</td>
					<th>
						职位
					</th>
					<td>
						<baseinfo:dicselector name="query_personjob" cssClass="treetext" columntype="job_type" type="select" style="width:220px;" isQuery="query"></baseinfo:dicselector>
					</td>
				</tr>
				<tr>
					<th>
						考核人员姓名
					</th>
					<td>
					  <input id="query_name" class="inputtext" style="width:220px;" />
					</td>
					<th>
					</th>
					<td>
					  <input type="button" class="button" value="查询" onclick="perosnQueryFetch();" />
					  <input type="button" class="button" value="重置" onclick="perosnQueryReset();" />
					</td>
				</tr>
				<tr>
					<th><b>考核人员选择</b></th>
					<th colspan="3"></th>
				</tr>
				<tr>
					<th>
						考核人员
					</th>
					<td>
					  	<select name="oldResources" id="oldResources" style="width: 200px; height: 200px;" multiple="multiple" size="10"></select>
					</td>
					<th style="text-align:center;">
						<p>
						<input name="" value="选  择" type="button" class="button" onclick="moveSelected(document.getElementById('oldResources'),document.getElementById('newResources'))" />
						</p>
						<p>
						<input name="" value="删  除" type="button" class="button" onclick="moveSelected(document.getElementById('newResources'),document.getElementById('oldResources'))" />
						</p>
						<p>
						<input name="" value="全部选择" type="button" class="button" onclick="moveAll(document.getElementById('oldResources'),document.getElementById('newResources'));" />
						</p>
						<p>
						<input name="" value="全部删除" type="button" class="button" onclick="moveAll(document.getElementById('newResources'),document.getElementById('oldResources'))" />
						</p>
					</th>
					<td>
						<select name="newResources" id="newResources" style="width: 200px; height: 200px;" multiple="multiple" size="10"></select>
					</td>
				</tr>
				<tr>
					<th><b>处理人员选择</b></th>
					<th colspan="3"></th>
				</tr>
				

				<tr>
					<th>第一环节处理人</th>
					<td colspan="3">
						<input id="processer_1_name" type="text" class="treetext" readonly/>
						<a href="javascript:searchProcesser('processer_1_name','processer_1_id');">
						<img border="0" src="${ctx}/css/images/selectcode.gif" /></a>
						<input type="hidden" id="processer_1_id" name="processer" />
						&nbsp;
						<input type="button" class="button" onclick="addProcesser();" value="添加处理人"/>
						&nbsp;
						<input type="button" class="button" onclick="delProcesser();" value="减少处理人"/>
					</td>
				</tr>

				
			</table>
			</form>
			<div style="text-align: center; margin-top: 10px">
				<input type="button" class="button" onclick="savaData();" value="保存"/>
				<input type="button" class="button" onclick="history.back()" value="返回" />
			</div>
		</div>
</body>
</html>