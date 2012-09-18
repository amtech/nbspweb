<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${ctx }/js/jQuery/jqgrid/css/ui.jqgrid.css"
			type="text/css" rel="stylesheet" />
		<link href="${ctx }/js/jQuery/ztree/css/zTreeStyle/zTreeStyle.css"
			type="text/css" rel="stylesheet">
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jqgrid/grid.locale-cn.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/WdatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/jQuery/ztree/jquery.ztree.all.min.js"></script>
		<script type="text/javascript"
			src="${ctx}/workflow/wmaintain/js/wmaintain_common.js"></script>
		<title>问题站点选择</title>
		<script type="text/javascript">
	//简单树样式	
	var setting = {
		view : {
			dblClickExpand : false,
			showLine : false,
			showIcon : true,
			chkDisabled : true
		},
		data : {
			simpleData : {
				enable : true,
				idKey : "ID",
				pIdKey : "RES_",
				rootPId : "root"
			},
			key : {
				title : "SUBITEM_NAME",
				name : "SUBITEM_NAME"

			}
		},
		check : {
			enable : true
		},
		callback : {
			onClick : onClick
		}
	};
	//单击树节点展开
	function onClick(e, treeId, treeNode) {
		var zTree = jQuery.fn.zTree.getZTreeObj("plansitetree");
		zTree.expandNode(treeNode);
	}
	/**
	 * 查询问题站点信息
	 */
	function query() {
		jQuery("#allCheckDiv").attr("style", "display:");
		//获取问题站点树
		getplansitetree(null);
	}
	/**
	 * 选择问题站点
	 */
	function selectsite() {
		var zTree = $.fn.zTree.getZTreeObj("plansitetree");
		if(zTree==null){
			alert("请选择问题站点！");
			return;
		}
		//获取选中节点
		var checknodes = zTree.getCheckedNodes(true);
		var allnodes = new Array();
		//放置所有父节点
		var mainnodes = new Array();
		//放置所有子节点
		var subnodes = new Array();
		if (checknodes) {
			for ( var i = 0; i < checknodes.length; i++) {
				if (checknodes[i].isParent) {
					mainnodes.push(checknodes[i]);
				} else {
					subnodes.push(checknodes[i]);
				}

			}
			if (subnodes.length === 0) {
				alert("请选择问题站点！");
			} else {
				allnodes[0] = mainnodes;
				allnodes[1] = subnodes
				window.returnValue = allnodes;
				window.close();
			}
		} else {
			alert("请选择问题站点！");
		}
	}

	/**
	 * 根据日期选择变更计划选择下拉列表
	 */
	function changePlanSelect() {
		var url = contextPath
				+ "/workflow/wmaintainPlanResourceAction!planList.action?rnd="
				+ Math.random();
		url += "&plan.businessType=" + jQuery("#wmaintain_businessType").val();
		url += "&plan.patrolGroup=" + jQuery("#wmaintain_patrolGroup").val();
		url += "&plan.createDateMin="
				+ jQuery("#wmaintain_createDateMin").val();
		url += "&plan.createDateMax="
				+ jQuery("#wmaintain_createDateMax").val();
		jQuery.post(encodeURI(url, "UTF-8"), function(data, textStatus, jqXHR) {
			setResourceCode(jqXHR);
		});
	}
	/**
	 * 根据计划选择返回的列表字串组织下拉选项
	 */
	function setResourceCode(response) {
		var str = response.responseText;
		jQuery("#wmaintain_wplanId").empty();
		jQuery("#wmaintain_wplanId").append("<option value=''>不限</option>");
		if (str == "") {
			return true;
		}
		var optionlist = str.split(";");
		if (typeof (optionlist.length) == "undefined") {
			var t = optionlist.split("=")[0];
			var v = optionlist.split("=")[1];
			jQuery("#wmaintain_wplanId").append(
					"<option value='" + t + "'>" + v + "</option>");
		} else {
			for ( var i = 0; i < optionlist.length; i++) {
				var t = optionlist[i].split("=")[0];
				var v = optionlist[i].split("=")[1];
				jQuery("#wmaintain_wplanId").append(
						"<option value='" + t + "'>" + v + "</option>");
			}
		}
	}
	/**
	 * 全选/全不选处理方法
	 */
	function allCheckEvent() {
		var zTree = $.fn.zTree.getZTreeObj("plansitetree");
		if (zTree) {
			if (jQuery("#allCheck").attr("checked") == "checked") {
				zTree.checkAllNodes(true);
			} else {
				zTree.checkAllNodes(false);
			}
		}
	}
</script>
	</head>
	<body>
		<form id="planSiteForm" name="planSiteForm">
			<div id="header">
				<div class="tabcontent">
					<table cellspacing="0" cellpadding="0" border="0" align="center">
						<tr>
							<th>
								搜索时间段：
							</th>
							<td>
								<input id="wmaintain_businessType" name="businessType" value="${businessType }" type="hidden" />
								<input id="wmaintain_id" name="id" value="${planId }" type="hidden" />
								<input id="wmaintain_patrolGroup" name="patrolGroup" value="${patrolGroup }" type="hidden" />
								<input id="wmaintain_createDateMin" name="createDateMin" type="text" class="Wdate inputtext" style="width: 30%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:function(){changePlanSelect();}})" />
								至
								<input id="wmaintain_createDateMax" name="createDateMax" type="text" class="Wdate inputtext" style="width: 30%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:function(){changePlanSelect();}})" />
							</td>
						</tr>
						<tr>
							<th>
								巡检计划：
							</th>
							<td>
								<select id="wmaintain_wplanId" name="wplanId" class="inputtext" style="width:70%">
									<option value=''>不限</option>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="button" onclick="query();" class="button" value="查询" />
								<input type="button" onclick="selectsite();" class="button" value="完成选择" />
							</td>
						</tr>
						<tr>
							<th>
								问题站点:
							</th>
							<td>
								<div style="white-space: nowrap;">
									<div id="allCheckDiv" style="display: none;">
										&nbsp;&nbsp;&nbsp;&nbsp;
										<input id="allCheck" name="allCheck" type="checkbox" value="0" onclick="allCheckEvent();" />
										全选/全不选
									</div>
									<div id="plansitetree" class="ztree"></div>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</form>
	</body>
</html>