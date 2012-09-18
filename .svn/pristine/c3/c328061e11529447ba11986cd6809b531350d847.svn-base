<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx }/js/WdatePicker/skin/WdatePicker.css" type="text/css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${ctx}/js/WdatePicker/WdatePicker.js"></script>
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
    //保存提交
    function savaData(){
    	if($("#report_date").val()==''){
    		alert("请选择报表时间");
    		return false;
    	}
    	if($("#report_file").val()==''){
    		alert("请选择报表文件");
    		return false;
    	}
     	$("#submitForm").submit();
    }
    //下载模板
    function downloadTemplate(){
    	window.location.href = "${ctx}/ah/ahExcelReportRecodeAction!downloadTemplate.action"
    }
    
    function reset(){
        $("#report_date").val('');
    	$("#report_file").val('');
    }
</script>
</head>
<body>
		<div class="title_bg">
			<div id="title" class="title">当前位置-上传Excel月报表</div>
		</div>
		<div class="tabcontent">
		<form id="submitForm" action="${ctx }/ah/ahExcelReportRecodeAction!saveReport.action" method="post" enctype="multipart/form-data">
			<table id="tableContainer" border="0" align="center" cellpadding="3" cellspacing="0">
				<tr>
					<th>
						报表时间
					</th>
					<td>
					  	<input type="text" id="report_date" name="reportDate" class="Wdate" value="${yearmonth }" readonly onfocus="WdatePicker({dateFmt:'yyyy-MM'})" />
					</td>
				</tr>
				<tr>
					<th>
						EXCEL月报表
					</th>
					<td>
					  <input type="file" id="report_file" name="reportFile" class="inputtext"/>
					</td>
				</tr>
			</table>
			</form>

			<div style="text-align: center; margin-top: 10px">
				<table border="0" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<td style="width:30%">
							说明：
						</td>
						<td style="width:70%;text-align:left;">
						  	1、一个月只对应一个报表，多次上传，将覆盖以前上传的报表。<br>
							2、所有报表必须按照指定的模板进行数据填报；<br>
							3、报表模板的格式不允许被变更，只允许进行数据内容的填写；<br>
							4、必须删除空行数据内容；<br>
							5、数值内容不允许填写汉字；<br>
						</td>
					</tr>
				</table>
			</div>
			
			
			<div style="text-align: center; margin-top: 10px">
				<input type="button" class="button" onclick="savaData();" value="上传" />
				<input type="button" class="button" onclick="reset();" value="重置"/>
				<input type="button" class="button" onclick="downloadTemplate()" value="模板下载" />
			</div>
		</div>
</body>
</html>