<%@ page language="java" pageEncoding="UTF-8"%>
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
<title>考核模板信息导入</title>
<script type="text/javascript">
In.add('common',{path:'${ctx}/common/js/common.js',type:'js',charset:'utf-8'});
In.add('vm', {path : '${ctx}/js/jQuery/jvalidation/jquery.validate.min.js',type : 'js',charset : 'utf-8'});
In.add('vcn', {path : '${ctx}/js/jQuery/jvalidation/messages_cn.js',type : 'js',charset : 'utf-8',rely : [ 'vm' ]});
In.add('vex', {path : '${ctx}/js/jQuery/jvalidation/jquery.validate.ex.js',type : 'js',charset : 'utf-8',rely : [ 'vcn' ]});
In.add('vam', {path : '${ctx}/js/jQuery/jvalidation/additional-methods.min.js',type : 'js',charset : 'utf-8',rely : [ 'vex' ]});
In.ready('vam', 'common',function(){
	jQuery("#importForm").validate({focusInvalid : false});
	changeTableType(jQuery("#tableType").val());
});
</script>
	</head>
	<body>
		<form id="importForm" method="post" action="${ctx }/assess/assessTemplateImportAction!previewData.action" enctype="multipart/form-data">
			<div class="ui-layout-north">
				<div class="title_bg">
					<div id="title" class="title">
						当前位置-导入考核模板信息
					</div>
				</div>
				<div class="framecontentBottom">
					<table cellspacing="0" cellpadding="0" border="0" align="center">
						<tr id="businessTypeTr">
							<th>专业：</th>
							<td>
								<baseinfo:customselector name="assessTemplate.businessType" id="businessType" data="${businessTypeMap}" isReversal="true" cssClass="inputtext required" style="width:220px;"></baseinfo:customselector>
								<span style="color: red">*</span>
							</td>
						</tr>
						<tr>
							<th>类型：</th>
							<td>
								<baseinfo:dicselector name="assessTemplate.tableType" id="tableType" columntype="APPRAISE_TABLE_TYPE" type="select" cssClass="inputtext required" style="width:220px;" onChange="changeTableType(this.value);"></baseinfo:dicselector>
								<span style="color: red">*</span>
							</td>
						</tr>
						<tr>
							<th>模板名称：</th>
							<td>
								<input name="assessTemplate.tableName" id="tableName" class="inputtext required" style="width:220px;" />
								<span style="color: red">*</span>
							</td>
						</tr>
						<tr>
							<th>考核/现场检查表：</th>
							<td>
								<input type="file" name="file" value="" id="file" class="inputtext required" style="width:220px;" />
								<span style="color: red">*</span>
							</td>
						</tr>
						<tr>
							<th>备注：</th>
							<td>
								<textarea name="assessTemplate.remark" id="remark" class="inputtext" style="width:220px;height:100px;"></textarea>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input name="btnSubmit" type="submit" class="button" onclick="return check();" value="导入">
								<input type="button" class="button" value="模板下载" onclick="downloadFile()">
								<input name="btnReturn" type="button" class="button" onclick="history.go(-1);" value="返回">
							</td>
						</tr>
					</table>
				</div>
			</div>
		</form>
		<table width="520" border="0" align="center" cellpadding="3"
			cellspacing="0">
			<tr>
				<td width="15%" valign="top" style="color: red">
					<b>说明：</b>
				</td>
				<td width="85%" valign="top" style="line-height: 20px; color: red;">
					<b>1、必须保证模版的风格不变，否则模版不能导入； <br /></b>
				</td>
			</tr>
		</table>
	</body>
	<script type="text/javascript">
	function check() {
		return true;
	}
	function changeTableType(value){
		if(value=="02"){
			jQuery("#businessTypeTr").hide();
		}else{
			jQuery("#businessTypeTr").show();
		}
	}
	function downloadFile() {
		location.href = "${ctx}/assess/assessTemplateImportAction!downloadTemplate.action";
	}
	</script>
</html>