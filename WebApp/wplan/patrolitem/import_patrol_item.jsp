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
<title>巡检项信息导入</title>
<script type="text/javascript">
In.add('common',{path:'${ctx}/common/js/common.js',type:'js',charset:'utf-8'});
In.add('fault-common', {path : '${ctx }/wplan/patrolitem/js/list_patrol_item.js',type : 'js',charset : 'utf-8'});
	function check() {
		if (document.forms[0].businessType.value == '') {
			alert("专业名称不能为空");
			return false;
		}
		if (document.forms[0].file.value == '') {
			alert("导入的附件不能为空");
			return false;
		}
		return true;
	}
	function downloadFile() {
		location.href = "${ctx}/wplan/patrolItemImportAction!downloadTemplate.action";
	}
</script>
	</head>
	<body>
		<form id="optForm" method="post"
			action="${ctx }/wplan/patrolItemImportAction!preview.action"
			enctype="multipart/form-data">
			<div class="ui-layout-north">
				<div class="title_bg">
					<div id="title" class="title">
						当前位置-导入巡检项信息
					</div>
				</div>
				<div class="framecontentBottom">
					<table cellspacing="0" cellpadding="0" border="0" align="center">
						<tr>
							<th>
								专业名称：
							</th>
							<td>
								<baseinfo:customselector name="businessType" id="businessType" data="${businessTypeMap}" isReversal="true" cssClass="inputtext" isQuery="select"></baseinfo:customselector>
								<span style="color: red">*</span>
							</td>
						</tr>
						<tr>
							<th>
								数据文件：
							</th>
							<td>
								<input type="file" name="file" value="" id="file"
									class="validate-file-xls" />
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input name="Submit" type="submit" class="button" onclick="return check();" value="导入">
								<input type="button" class="button" value="模板下载"
									onclick="downloadFile()">
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
					<b>1、必须保证模版的风格不变，否则模版不能导入； <br />
						2、维护检测项目的描述尽可能的简短，在8-20个汉字内描述清楚，若不能描述清楚可以在质量标准中进行详细的描述； <br />
						3、周期是改巡检项巡检周期，目前包括：年，季，月； <br /> 4、输入类型用与描述维护检测项目是选择，数值，文本； <br />
						5、值域范围与数据类型对应，如：选择单选那么在值域范围中可以根据维护检测项目的具体描述输入“是,否”、“有,无”、“正常,不正常”等，具体详见上面的示例；
						<br /> 6、在输入类型为数值或文本方式时，值域范围为可选项； <br />
						7、异常状态用于判断巡检人员上传的巡检项数据是否存在异常。通过它才能判断出该检测项目是否异常。注意：值域范围中必须包含异常状态中的值；
						<br /> 8、默认值 可选项，用于设置巡检终端上巡检项目的默认选项； <br /> </b>
				</td>
			</tr>
		</table>
	</body>
</html>