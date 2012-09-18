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
	In.ready('vam', 'common', function() {
		setContextPath("${ctx}");
		//合并行
		var rowCount = ${maxitemcount};
	    for(var i = 0; i <= rowCount; i++)
	    {
	     table_rowspan("#KH",i);
	    }
	    //合并列
	 	var colCount =${fn:length(templatecontent)};
	    for(var i = 0; i <= colCount; i++)
	    {
	     table_colspan("#KH",i,rowCount);
	    }

	});
	//编辑模板内容
	function editContent(id){
		var url = "${ctx}/assess/assessTemplateContentAction!input.action?tableType=${assesstemplate.tableType}&&id=" + id;
		location.href = url;
	}
	//删除模板内容
	function deleteContent(id){
		var con = confirm("确定要删除该条记录?");
		if (!!con) {
		var url = "${ctx}/assess/assessTemplateContentAction!delete.action?tableid=${assesstemplate.id }&id=" + id;
		location.href = url;
		}
	}
</script>
</head>
<body>
	<form action="${ctx }/assess/assessTemplateAction!save.action"
		id="assesstemplateForm" method="post">
		<div class="title_bg">
			<div id="title" class="title">当前位置-模版管理</div>
		</div>
		<div class="framecontentBottom">
			<table border="0" align="center" cellpadding="3" cellspacing="0">
				<tr>
					<th>模版名称：</th>
					<td><input id="tableName" name="tableName"
						value="${assesstemplate.tableName }" type="text"
						class="required inputtext" /><input id="id" name="id"
						value="${assesstemplate.id }" type="hidden"
						class="inputtext required" /> <span style="color: red">*</span></td>
					<th>专业类型：</th>
					<td><baseinfo:customselector name="businessType"
							data="${businessTypeMap}" isReversal="true"
							cssClass="required inputtext" id="businessType"
							keyValue="${assesstemplate.businessType }"></baseinfo:customselector>
						<font style="color: red;">&nbsp;*</font></td>
				</tr>
				<tr>
					<th>类型：</th>
					<td colspan="3"><baseinfo:dicselector id="tableType"
							name="tableType" columntype="APPRAISE_TABLE_TYPE" type="select"
							cssClass="inputtext" keyValue="${assesstemplate.tableType }"></baseinfo:dicselector>
					</td>
				</tr>
				<tr>
					<th>备注：</th>
					<td colspan="3"><textarea id="remark" name="remark"
							class="inputtext" style="width: 80%" rows="3">${assesstemplate.remark }</textarea>
					</td>
				</tr>
			</table>
			<div style="text-align: center; margin-top: 10px">
				<input type="submit" class="button" value="保存" onclick="saveData();" />
				<input type="button" class="button" onclick="history.back()"
					value="返回" />
			</div>
		</div>
		<div class="tabout">
			<table id="KH">
				<c:if test="${assesstemplate.tableType!='03' }">
				<thead>
					<tr>
						<th colspan="${maxitemcount}" style="width:80px;" nowrap>考核项目</th>
						<th style="width:60px;" nowrap>指标名称</th>
						<th style="width:40px;" nowrap>基准值</th>
						<th style="width:40px;" nowrap>挑战值</th>
						<th style="width:30px;" nowrap>权重</th>
						<th style="width:150px;" nowrap>计算公式和要求</th>
						<th nowrap>评价标准</th>
						<th tyle="width:80px;" nowrap>操作</th>
					</tr>
				</thead>
				</c:if>
				<c:if test="${assesstemplate.tableType=='03' }">
				<thead>
					<tr>
						<th colspan="${maxitemcount}" style="width:80px;" nowrap>考核项目</th>
						<th style="width:150px;" nowrap>计算公式和要求</th>
						<th nowrap>评价标准</th>
						<th tyle="width:80px;" nowrap>操作</th>
					</tr>
				</thead>
				</c:if>
				<tbody>
					<c:forEach var="item" items="${templatecontent}">
						<tr>
							<c:forEach var="itemname" items="${item.ITEMNAMELIST}">
								<c:set var="width" value="${80/maxitemcount }"></c:set>
								<td style="width:${width }px;">${itemname}</td>
							</c:forEach>
							<c:if test="${assesstemplate.tableType!='03' }">
							<td>${item.NAME}</td>
							<td>${item.BENCHMARK_VALUE}</td>
							<td>${item.CHALLENGE_VALUE}</td>
							<td>${item.WEIGHT}</td>
							</c:if>
							<td>${item.DEMAND_DESC}</td>
							<td>${item.EVALUATION_CRITERION}</td>
							<td><span><a
									style='color: blue;text-decoration: underline;'
									href=javascript:editContent('${item.CONTENTID}')>编辑</a> </span>&nbsp;&nbsp;<span><a
									style='color: blue;text-decoration: underline;'
									href=javascript:deleteContent('${item.CONTENTID}')>删除</a> </span>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</form>
</body>
</html>
