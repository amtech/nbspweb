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
		<script type="text/javascript">
	function query() {
				// 加载可分配的人员列表
		var url2 = "${ctx }/ah/PersonRatingFormAction!getPersons.action?"
				+"jobtype=" + jQuery("#job_type").val()
				+ "&t=" + Math.random();
		loadUrl(url2, "personList");
	}
	//初始化壹面
	jQuery(function() {
		//加载已分配的人员列表
		var url1 = "${ctx }/ah/PersonRatingFormAction!getPersonsAssigned.action?"
				+"jobtype=" + jQuery("#job_type").val()
				+"&tableid=" +jQuery("#id").val();
				+ "&t=" + Math.random();
		loadUrl(url1, "ids");
		query();
		jQuery("#Form2").validate({
			focusInvalid : false,
			ignore: "",
			submitHandler : function(form) {
				form.submit();
			}
		});
	});
	/**
	 * 验证数据合法性
	 */
	function submitData() {
		var ids = getChecked("ids");
		jQuery("#personId").val(ids);
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
</script>
	</head>
	<body>
		<div class="title_bg">
			<div id="title" class="title">
				当前位置-定义人员考核表
			</div>
		</div>
		<div class="tabcontent">
			<table cellspacing="0" class="Detailed_list" cellpadding="0"
				border="0" align="center" style="width: 75%">
				<form id="Form2" method="post"
					action="${ctx }/ah/PersonRatingFormAction!setPersonsForm.action">
					<tr>
						<th>
							考核表：
						</th>
						<td colspan="3">
							${tablename }
						</td>
					</tr>
					<tr>
						<th>
							人员职位：
						</th>
						<td colspan="3">
							<baseinfo:dicselector columntype="job_type" type="select"
								id="job_type" name="job_type" cssClass="inputtext" style="width:220px" isQuery="query" onChange="query()"></baseinfo:dicselector>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<table width="80%" border="0" align="center">
								<tr>
									<td style="text-align: center; width: 40%;">
										<select id="personList" style="width: 200px; height: 200px;"
											multiple="multiple" size="10">
										</select>
									</td>
									<td style="text-align: center; width: 20%;">
										<p>
											<input name="" value="选  择" type="button" class="button"
												onclick="moveSelected(document.getElementById('personList'),document.getElementById('ids'))" />
										</p>
										<p>
											<input name="" value="删  除" type="button" class="button"
												onclick="moveSelected(document.getElementById('ids'),document.getElementById('personList'))" />
										</p>
										<p>
											<input name="" value="全部选择" type="button" class="button"
												onclick="moveAll(document.getElementById('personList'),document.getElementById('ids'));" />
										</p>
										<p>
											<input name="" value="全部删除" type="button" class="button"
												onclick="moveAll(document.getElementById('ids'),document.getElementById('personList'))" />
										</p>
									</td>
									<td style="text-align: center; width: 40%;">
										<input type="hidden" id="id" name="entity.tableId"
											value="${id }"/>
										<select id="ids" style="width: 200px; height: 200px;"
											multiple="multiple" size="10">
										</select>
										<input type="hidden" id="personId" name="entity.personId"
											class="inputtext" />
									</td>
								</tr>
							</table>

						</td>
					</tr>
					<tr>
						<td height="26" colspan="4" align="center">
							<input class="button" type="button" value="保存"
								onclick="submitData()">
							<input class="button" type="button" value="重置"
								onclick="breakPage()">
							<input class="button" type="button" value="返回"
								onclick="history.go(-1)">
						</td>
					</tr>
			</table>
			</form>
		</div>
	</body>
</html>