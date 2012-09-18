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
<title>考核模板信息预览</title>
<script type="text/javascript">
In.add('common',{path:'${ctx}/common/js/common.js',type:'js',charset:'utf-8'});
In.add('vm', {path : '${ctx}/js/jQuery/jvalidation/jquery.validate.min.js',type : 'js',charset : 'utf-8'});
In.add('vcn', {path : '${ctx}/js/jQuery/jvalidation/messages_cn.js',type : 'js',charset : 'utf-8',rely : [ 'vm' ]});
In.add('vex', {path : '${ctx}/js/jQuery/jvalidation/jquery.validate.ex.js',type : 'js',charset : 'utf-8',rely : [ 'vcn' ]});
In.add('vam', {path : '${ctx}/js/jQuery/jvalidation/additional-methods.min.js',type : 'js',charset : 'utf-8',rely : [ 'vex' ]});
In.ready('vam', 'common',function(){
	jQuery("#importForm").validate({
		focusInvalid : false,
		debug : true,
		submitHandler : function(form) {
			if (check()) {
				form.submit();
			}
		}
	});
});
</script>
	</head>
	<body>
		<form id="importForm" method="post" action="${ctx }/assess/assessTemplateImportAction!importData.action" enctype="multipart/form-data">
			<div class="ui-layout-north">
				<div class="title_bg">
					<div id="title" class="title">
						当前位置-预览考核模板信息
					</div>
				</div>
				<div class="framecontentBottom">
					<table cellspacing="0" cellpadding="0" border="0" align="center">
						<tr>
							<th style="width:13%">模板名称：</th>
							<td style="width:20%">
								<input name="assessTemplate.tableName" id="tableName" value="${assessTemplate.tableName }" type="hidden" />${assessTemplate.tableName }
							</td>
							<th style="width:13%">类型：</th>
							<td style="width:20%">
								<baseinfo:dicselector name="" columntype="APPRAISE_TABLE_TYPE" keyValue="${assessTemplate.tableType }" type="view"></baseinfo:dicselector>
								<input name="assessTemplate.tableType" id="tableType" value="${assessTemplate.tableType }" type="hidden" />
							</td>
							<th style="width:13%"><c:if test="${assessTemplate.tableType!='02' }">专业：</c:if></th>
							<td style="width:20%"><c:if test="${assessTemplate.tableType!='02' }">
								<baseinfo:dicselector name="" columntype="businesstype" keyValue="${assessTemplate.businessType }" type="view"></baseinfo:dicselector>
								<input name="assessTemplate.businessType" id="businessType" value="${assessTemplate.businessType }" type="hidden" />
							</c:if></td>
						</tr>
						<tr>
							<th style="width:13%">备注：</th>
							<td style="width:87%" colspan="5">
								<input name="assessTemplate.remark" id="remark" value="${assessTemplate.remark }" type="hidden" />
								${assessTemplate.remark }
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center">
								<c:if test="${tableMap.validData=='1' }">
								<input name="btnSubmit" type="submit" class="button" onclick="return check();" value="确认导入">
								</c:if>
								<input name="btnReturn" type="button" class="button" onclick="history.go(-1);" value="返回">
							</td>
						</tr>
					</table>
					<table cellspacing="0" cellpadding="0" border="0" align="center">
						<c:forEach var="oneRow" items="${tableMap.dataList }" varStatus="status">
							<tr>
								<c:set var="parentCellValue" value=""></c:set>
								<c:forEach var="oneCell" items="${oneRow }">
									<td rowspan='${oneCell.rowSpan }' colspan='${oneCell.colSpan }' style='width: ${oneCell.width }px;'>
										<font color="${oneCell.cellColor }">${oneCell.cellValue }</font>&nbsp;
										<c:if test="${status.index>=1 }">
										<c:if test="${not empty oneCell.columnInputName }">
										<input name="assessTemplate.${oneCell.columnInputName }" id="${oneCell.columnInputName }" value="${oneCell.cellValue }" type="hidden" />
										</c:if>
										</c:if>
									</td>
									<c:set var="parentCellValue" value="${oneCell.parentCellValue }"></c:set>
								</c:forEach>
								<c:if test="${status.index>=1 }">
								<input id="itemName" name="assessTemplate.itemName" value="${parentCellValue }" type="hidden" />
								</c:if>
								<c:if test="${status.index==0 }">
									<td style="width:100px">错误提示信息</td>
								</c:if>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</form>
	</body>
	<script type="text/javascript">
	function check() {
		if("0"=="${tableMap.valid}"){
			alert("导入值不合法，请根据错误提示信息进行模板导入数据的修改！");
			return false;
		}
		return true;
	}
	</script>
</html>