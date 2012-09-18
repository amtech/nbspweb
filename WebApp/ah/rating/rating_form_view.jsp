<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>查看巡检项信息</title>
		<script type="text/javascript">
	jQuery(function() {
		//使用层布局
		var jqgrid = jQuery("#itemgrid").jqGrid({
			url : "${ctx}/ah/ratingFormAction!listItemData.action?id=${tableid}",
			datatype : "json",
			rowNum:-1,
			mtype : 'GET',
			rownumbers : true,
			jsonReader : {
				root : "root",
				id : "item",
				repeatitems : false
			},
			colNames : [ '考核项目', '权重', '基本值', '挑战值', '指标说明和计算公式', '评价标准' ],
			colModel : [ {
				name : 'ITEM',
				id : 'ITEM',
				sortable : false
			}, {
				name : 'WEIGHT',
				id : 'WEIGHT',
				sortable : false,
				width : 100
			}, {
				name : 'BASE_VALUE',
				id : 'BASE_VALUE',
				sortable : false,
				width : 100
			}, {
				name : 'CHALLENGE_VALUE',
				id : 'CHALLENGE_VALUE',
				sortable : false,
				width : 100
			}, {
				name : 'NORM_REMARK',
				id : 'NORM_REMARK',
				sortable : false,
				width : 200
			}, {
				name : 'EVALUATION_CRITERION',
				id : 'EVALUATION_CRITERION',
				sortable : false,
				width : 200
			} ],
			autowidth : true,
			height : 300
		});
	});
</script>
	</head>
	<body>
		<form id="optForm" method="post"
			action="${ctx }/ah/ratingFormItemImportAction!preview.action"
			enctype="multipart/form-data">
			<div class="ui-layout-north">
				<div class="title_bg">
					<div id="title" class="title">
						当前位置-查看考核表信息
					</div>
				</div>
				<div class="framecontentBottom">
					<table cellspacing="0" cellpadding="0" border="0" align="center">
						<tr>
							<th>
								专业名称：
							</th>
							<td colspan="3">
								<baseinfo:dicselector name="businessType"
									columntype="businesstype" type="view"
									keyValue="${ratingForm.businessType }"></baseinfo:dicselector>
							</td>
						</tr>
						<tr>
							<th>
								考核表标题：
							</th>
							<td colspan="3">
								${ratingForm.title }
							</td>
						</tr>
						<tr>
							<th>
								创建人：
							</th>
							<td>
								<baseinfo:user displayProperty="USERNAME"
									id="${ratingForm.creater }"></baseinfo:user>
							</td>
							<th>
								创建时间：
							</th>
							<td>
								<fmt:formatDate value="${ratingForm.createTime }"
									pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
						</tr>
						<tr>
							<th>
								考核表备注：
							</th>
							<td colspan="3">
								${ratingForm.remark }
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<div id="content" align="center" style="padding-top: 2px">
									<table id="itemgrid"></table>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input name="button" type="button" class="button"
									onclick="history.go(-1);" value="返回">
							</td>
						</tr>
					</table>
				</div>
			</div>
		</form>
	</body>
</html>