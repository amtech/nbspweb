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
		// 加载可分配的油机
		var url = "${ctx }/oil/oilEngineManageAction!getOilEngine.action?property_right="
				+ jQuery("#property_right").val()
				+ "&oilengine_code="
				+ jQuery("#oilengine_code").val() + "&t=" + Math.random();
		loadUrl(url, "oilEngine");
	}
	//初始化壹面
	jQuery(function() {
		query();
		jQuery("#Form2").validate({
			focusInvalid : false,
			submitHandler : function(form) {
				form.submit();
			}
		});
	});
	/**
	 * 刷新页面
	 */
	function breakPage() {
		window.location.href = "${ctx }/oil/oilEngineManageAction!assign.action?t="
				+ Math.random();
	}
	/**
	 * 验证数据合法性
	 */
	function submitData() {
		var ids = getChecked("ids");
		jQuery("#id").val(ids);
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
	/**
	 * 搜索代维
	 * @param url
	 */
	function searchWhdw(url) {
		var val = showOrg(url);
		if (!!val) {
			jQuery("#maintenance_id").val(val[0]);
			jQuery("#maintenance_name").val(val[1]);
		}
	}
</script>
	</head>
	<body>
		<div class="title_bg">
			<div id="title" class="title">
				当前位置-油机分配
			</div>
		</div>
		<div class="tabcontent">
			<table cellspacing="0" class="Detailed_list" cellpadding="0"
				border="0" align="center" style="width: 75%">
				<tr>
					<th>
						产权：
					</th>
					<td>
						<input id="property_right_name" name="entity.propertyRightName"
							maxlength="60" class="inputtext" type="text"
							style="width: 220px;" readonly />
						<input id="property_right" name="entity.propertyRight"
							type="hidden">
						<a
							href="javascript:searchPropertyRight('${ctx}/commonaccess!getorg.action');">
							<img border="0" src="${ctx}/css/images/selectcode.gif" /> </a>
					</td>
					<th>
						油机编号：
					</th>
					<td>
						<input id="oilengine_code" class="inputtext" style="width: 220px;" />
					</td>
				</tr>
				<tr>
					<td colspan="4" style="text-align: center; padding: 5px;">
						<input class="button" type="button" value="查询 " onclick="query();">
					</td>
				</tr>
				<form id="Form2" method="post"
					action="${ctx }/oil/oilEngineManageAction!assEngine.action">
					<tr>
						<th>
							维护单位：
						</th>
						<td colspan="3">
							<input id="maintenance_name" name="entity.maintenanceName"
								class="inputtext required" style="width: 220px;" readonly />
							<input id="maintenance_id" name="entity.maintenanceId"
								type="hidden" />
							<a
								href="javascript:searchWhdw('${ctx}/commonaccess!getorg.action');">
								<img border="0" src="${ctx}/css/images/selectcode.gif" /> </a>
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<table width="80%" border="0" align="center">
								<tr>
									<td style="text-align: center; width: 40%;">
										<select id="oilEngine" style="width: 200px; height: 200px;"
											multiple="multiple" size="10">
										</select>
									</td>
									<td style="text-align: center; width: 20%;">
										<p>
											<input name="" value="选  择" type="button" class="button"
												onclick="moveSelected(document.getElementById('oilEngine'),document.getElementById('ids'))" />
										</p>
										<p>
											<input name="" value="删  除" type="button" class="button"
												onclick="moveSelected(document.getElementById('ids'),document.getElementById('oilEngine'))" />
										</p>
										<p>
											<input name="" value="全部选择" type="button" class="button"
												onclick="moveAll(document.getElementById('oilEngine'),document.getElementById('ids'));" />
										</p>
										<p>
											<input name="" value="全部删除" type="button" class="button"
												onclick="moveAll(document.getElementById('ids'),document.getElementById('oilEngine'))" />
										</p>
									</td>
									<td style="text-align: center; width: 40%;">
										<select id="ids" style="width: 200px; height: 200px;"
											multiple="multiple" size="10">
										</select>
										<input type="hidden" id="id" name="entity.id"
											class="inputtext required" />
										<font color="red">*</font>
									</td>
								</tr>
							</table>

						</td>
					</tr>
					<tr>
						<td height="26" colspan="4" align="center">
							<input class="button" type="button" value="提交"
								onclick="submitData()">
							<input class="button" type="button" value="重置"
								onclick="breakPage()">
						</td>
					</tr>
			</table>
			</form>
		</div>
	</body>
</html>