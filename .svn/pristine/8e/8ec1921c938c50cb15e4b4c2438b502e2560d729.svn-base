<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
		<c:if test="${not empty map }">
			<div class="tabcontent" align="center">
				<table border="0" align="center" style="width:100%" cellpadding="3" cellspacing="0">
					<!-- 
					<tr>
						<th colspan="4" style="text-align: left;background-color:#B8DDFC;">
							<a href="javascript:showInfo();"><font color="#058CFE"><b>油机发电记录</b></font></a><font color="#058CFE"><b>|</b></font>
							<a href="javascript:showGis();"><font color="#058CFE"><b>油机轨迹图</b></font></a>
						</th>
					</tr>
					 -->
					<tbody id="gisTb" style="display: none;">
						<tr>
							<td colspan="4">
								<iframe id="oil_engine_pos_gis" frameborder="0" scrolling="auto" height="480" width="600"></iframe>
							</td>
						</tr>
					</tbody>
					<tbody id="infoTb">
						<tr>
							<th>
								油机编号：
							</th>
							<td>
								${map.OILENGINE_CODE }
							</td>
							<th>
								油机型号：
							</th>
							<td>
								${map.OILENGINE_MODEL }
							</td>
						</tr>
						<tr>
							<th>
								标准油耗(G/KW.H)：
							</th>
							<td>
								${map.STANDARD_OILWEAR }
							</td>
							<th>
								额定功率(KW)：
							</th>
							<td>
								${map.RATION_POWER }
							</td>
						</tr>
						<tr>
							<th>
								发电前电表读数：
							</th>
							<td>
								${map.START_DEGREE }
							</td>
							<th>
								发电后电表读数：
							</th>
							<td>
								${map.END_DEGREE }
							</td>
						</tr>
						<tr>
							<th>
								发电量：
							</th>
							<td>
								${map.GEN_POWER }度
							</td>
							<th>
								油耗量：
							</th>
							<td>
								${map.CONSUME_QUANTITY }升
							</td>
						</tr>
						<tr>
							<th>
								处理人员：
							</th>
							<td>
								${map.PROCESSOR_NAME }
							</td>
							<th>
								联系电话：
							</th>
							<td>
								${map.PHONE }
							</td>
						</tr>
						<tr>
							<th>
								出发时间：
							</th>
							<td>
								${map.SETOUT_TIME_DIS }
							</td>
							<th>
								发电时间：
							</th>
							<td>
								${map.GEN_ELE_TIME_DIS }
							</td>
						</tr>
						<tr>
							<th>
								结束时间：
							</th>
							<td colspan="3">
								${map.END_TIME_DIS }
							</td>
						</tr>
						<tr>
							<th>
								备注信息：
							</th>
							<td colspan="3">
								${map.REMARK }
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</c:if>
	<script type="text/javascript">
	function showGis() {
		var url = "${WEBGIS_DEPLOYNAME}/gisextend!oilenginePosition.action?";
		url += "id=${map.TASK_ID}";
		url += "&userid=${LOGIN_USER.userId}";
		jQuery("#oil_engine_pos_gis").attr("src", url);
		jQuery("#gisTb").show();
		jQuery("#infoTb").hide();
	}
	function showInfo() {
		jQuery("#infoTb").show();
		jQuery("#gisTb").hide();
	}
	</script>
