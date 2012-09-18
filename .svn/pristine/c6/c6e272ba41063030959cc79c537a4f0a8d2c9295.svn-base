<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx }/js/selected_options.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jvalidation/jquery.validate.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jvalidation/jquery.validate.cn.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jvalidation/jquery.validate.ex.js"></script>
<script type="text/javascript"
	src="${ctx}/js/WdatePicker/WdatePicker.js"></script>
<script type="text/javascript">

	//初始化壹面
jQuery(function() {
		jQuery("#Form2").validate({
			focusInvalid : false,
			ignore: "",
			submitHandler : function(form) {
				getGridData();
				if(checkGridData()){
					jQuery('input[name="isexam"]').attr('disabled',false);
					form.submit();
				}
			}
		});
		var  iseditable=true,ishidden=true;
		//如果是移动考核
		if(jQuery('#flag').val()=="1"||jQuery('#flag').val()=="2"){
			ishidden=false;
			iseditable=false;
			jQuery('input[name="isexam"]').attr('disabled',true);
			jQuery('#title').html("当前位置 -移动考核");
		}else if(jQuery('#flag').val()=="3"){
			//如果是代维查看
			ishidden=false;
			iseditable=true;
			jQuery('input[name="isexam"]').attr('disabled',true);
			jQuery('#btnSave').hide();
			jQuery('#title').html("当前位置 -考核评查看");
		}else{
			jQuery('#title').html("当前位置 -代维自评");
		}
		//考核表定义
		var jqgrid=jQuery("#selfrategrid").jqGrid({    
			url: "${ctx}/ah/ContractorSelfRatingAction!getItems.action?personId=${personId}&id=${map.ID}",   
			datatype: "json",    
			mtype: 'GET',
			rownumbers: true,
			rowNum:-1,
			colNames:['ID','考核ID','考核项目','权重','基本值','挑战值','指标说明和计算公式','评价标准','指标实际完成值','自评分','自评说明','考评分','考评说明'],
			colModel:[ {name:'ITEMRESULTID',id:'ITEMRESULTID',sortable:false,hidden:true},
			          {name:'RATINGITEMID',id:'RATINGITEMID',sortable:false,hidden:true},
			          {name:'ITEM',id:'ITEM',sortable:false},
			          {name:'WEIGHT',id:'WEIGHT',sortable:false},
			          {name:'BASE_VALUE',id:'BASE_VALUE',sortable:false},
			          {name:'CHALLENGE_VALUE',id:'CHALLENGE_VALUE',sortable:false},
			          {name:'NORM_REMARK',id:'NORM_REMARK',sortable:false},
			          {name:'EVALUATION_CRITERION',id:'EVALUATION_CRITERION',sortable:false},
			          {name:'TARGET_COMPLETEVALUE',id:'TARGET_COMPLETEVALUE',sortable:false, editable:iseditable},
			          {name:'SELF_SCORE',id:'SELF_SCORE',sortable:false, editable:iseditable},
			          {name:'SELF_REMARK',id:'SELF_REMARK',sortable:false, editable:iseditable},
			          {name:'SCORE',id:'SCORE',sortable:false, editable:!iseditable,hidden:ishidden},
			          {name:'EXAM_REMARK',id:'EXAM_REMARK',sortable:false, editable:!iseditable,hidden:ishidden}
			          ],
			editurl: 'clientArray',
			viewrecords: true,
			width:'100%',
			hidegrid: false, 
			onSelectRow: function(id){
				jQuery('#selfrategrid').jqGrid('editRow',id,true);
			},
			jsonReader: {
				   root:"root" ,      
            	   repeatitems: false,
                   id:"RATINGITEMID"
		           }
			  });
		jQuery(window).wresize(function() {
			grid_resize(jqgrid);
		});
		grid_resize(jqgrid);
		jQuery("#examtd :radio").change(function() {     //id 为season行内radio值变化函数  
		    var season = $("input[name='isexam']:checked").val();           // 获取当前选中radio的值  
		      if(season==1){
		    	  //获取修改后的值，存储到数组中
				  rows=jQuery("#selfrategrid").jqGrid('getRowData');
					  for(var i=0;i<rows.length;i++){
					      jQuery("#selfrategrid").setRowData(rows[i].RATINGITEMID,{SELF_SCORE:'0'});
					  }
		      }
		  
		})
	});
	/**
	 * 验证数据合法性
	 */
	function submitData() {
		jQuery("#Form2").submit();
	}
	/**
	 * 搜索代维
	 * @param url
	 */
	function searchPropertyRight(url) {
		var val = showOrg(url);
		if (!!val) {
			jQuery("#property_right").val(val[0]);
			jQuery("#property_right_name").val(val[1]);
		}
	}
	//获取GRID列表中数据
	function getGridData(){
		 //获取列数据
		  var rows= jQuery("#selfrategrid").jqGrid('getRowData');
		  var completearr=new Array();
		  var selfscorearr=new Array();
		  var selfremarkarr=new Array();
		  var scorearr=new Array();
		  var examremarkarr=new Array();
		  var itemresultarr=new Array();
		  var ratingitemidarr=new Array();
		  //将修改的值状态改变
		  for(var i=0;i<rows.length;i++){
		      var row=rows[i];
		      jQuery("#selfrategrid").jqGrid('saveRow',row.RATINGITEMID);
		  }
		  //获取修改后的值，存储到数组中
		  rows=jQuery("#selfrategrid").jqGrid('getRowData');
			  for(var i=0;i<rows.length;i++){
			      var row=rows[i];
			      completearr.push(row.TARGET_COMPLETEVALUE);
			      selfscorearr.push(row.SELF_SCORE);
			      selfremarkarr.push(row.SELF_REMARK);
			      scorearr.push(row.SCORE);
			      examremarkarr.push(row.EXAM_REMARK);
			      itemresultarr.push(row.ITEMRESULTID);
			      ratingitemidarr.push(row.RATINGITEMID);
			  }
			  //逗号隔开提交到后台
          jQuery('#completevalue').val(completearr.join(","));
          jQuery('#selfscore').val(selfscorearr.join(","));
          jQuery('#selfremark').val(selfremarkarr.join(","));
          jQuery('#score').val(scorearr.join(","));
          jQuery('#examremark').val(examremarkarr.join(","));
          jQuery('#itemresultid').val(itemresultarr.join(","));
          jQuery('#ratingitemid').val(ratingitemidarr.join(","));
	}
	function checkGridData(){
		var grid=jQuery("#selfrategrid");
		 //获取列数据
		  var rows= grid.jqGrid('getRowData');
		  //将修改的值状态改变
		  for(var i=0;i<rows.length;i++){
			  if(!jQuery.isNumeric(rows[i].SELF_SCORE)){
				  alert("第 "+(i+1)+"行输入的自评分不是数字！");
				  return false;
			  }
			  if(rows[i].SELF_SCORE.length>5){
				  alert("第 "+(i+1)+"行输入的自评分超过5位！");
				  return false;
			  }
			  //获取自评列属性
			  var colpro=grid.jqGrid('getColProp','SCORE');
			  if(!jQuery.isNumeric(rows[i].SCORE)&&!colpro.hidden){
				  alert("第 "+(i+1)+"行输入的考核分不是数字！");
				  return false;
			  }
			  if(rows[i].SCORE.length>3&&!colpro.hidden){
				  alert("第 "+(i+1)+"行输入的考核分超过3位！");
				  return false;
			  }
		  }
			  return true;
	}
	function changemonth() {
		var datevalue = jQuery('#yearmonthdate').val();
		jQuery('#yearmonth').val(datevalue +"-01");
	}

</script>
</head>
<body>
	<div class="title_bg">
		<div id="title" class="title"></div>
	</div>
	<div class="tabcontent">
		<form id="Form2" method="post"
			action="${ctx }/ah/ContractorSelfRatingAction!save.action">
			<table cellspacing="0" class="Detailed_list" cellpadding="0"
				border="0" align="center">
				<tr>
					<th>代维公司名称：</th>
					<td><input type="hidden" value="${map.ID }" name="id" id="id">
						<input type="hidden" value="${map.TABLEID }" name="tableid"
						id="tableid"> <baseinfo:org displayProperty="organizename"
							id="${map.ORGID }"></baseinfo:org></td>
					<th>考核日期：</th>
					<td><fmt:formatDate value="${map.YEAR_MONTH }"
							pattern="yyyy-MM" /> <input type="hidden" id="yearmonth"
						name="yearmonth"
						value='<fmt:formatDate value="${map.YEAR_MONTH }" pattern="yyyy-MM-1" />'>
						</input></td>
				</tr>
				<tr>
					<th>是否参与自评：</th>
					<td id="examtd"><c:if test="${map.IS_EXAM=='1' }">
							<input type="radio" name="isexam" value="0"> 参与
							  <input type="radio" name="isexam" value="1" checked> 不参与
							 </c:if> <c:if test="${map.IS_EXAM!='1' }">
							<input type="radio" name="isexam" value="0" checked> 参与
							  <input type="radio" name="isexam" value="1"> 不参与
							 </c:if></td>
					<th>被考核者姓名：</th>
					<td><input type="hidden" id="personid" name="personid"
						value="${map.SID }" /> <baseinfo:user displayProperty="username"
							id="${map.SID }"></baseinfo:user></td>
				</tr>
				<tr>
					<th>被考核者职位：</th>
					<td><input type="hidden" id="position" name="position"
						value="${map.JOBINFO }" /> <baseinfo:dic displayProperty="lable"
							codevalue="${map.JOBINFO }" columntype="job_type"></baseinfo:dic>
					</td>
					<th>所用考核表：</th>
					<td>${map.TABLE_NAME }</td>
				</tr>
				<tr>
					<td colspan="4" align="center"><input class="button"
						id="btnSave" type="button" value="保存" onclick="submitData()">
						<input class="button" type="button" value="重置"
						onclick="breakPage()"> <input class="button" type="button"
						value="返回" onclick="history.go(-1)"> <input type="hidden"
						id="completevalue" name="completevalue"></input> <input
						type="hidden" id="selfscore" name="selfscore"></input> <input
						type="hidden" id="selfremark" name="selfremark"></input> <input
						type="hidden" id="itemresultid" name="itemresultid"></input> <input
						type="hidden" type="hidden" id="score" name="score"></input> <input
						type="hidden" id="examremark" name="examremark"></input> <input
						type="hidden" id="ratingitemid" name="ratingitemid"></input> <input
						type="hidden" id="flag" name="flag" value="${map.flag}" /></td>
				</tr>
			</table>
		</form>
	</div>
	<br />
	<div id="content" align="center" style="padding-top: 2px">
		<table id="selfrategrid"></table>
	</div>
</body>
</html>