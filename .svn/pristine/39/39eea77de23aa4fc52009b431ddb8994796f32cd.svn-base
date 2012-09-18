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
		
In.add('ztreeui',{path:'${ctx }/js/jQuery/ztree/css/zTreeStyle/zTreeStyle.css'});
In.add('jqui',{path:'${ctx}/js/jQuery/jqueryui/css/redmond/jquery-ui.custom.css'});
In.add('jquijs',{path:'${ctx}/js/jQuery/jqueryui/jquery-ui.custom.min.js',type:'js',charset:'utf-8',rely:['jqui']});
In.add('vm', {path : '${ctx}/js/jQuery/jvalidation/jquery.validate.min.js',type : 'js',charset : 'utf-8'});
In.add('vcn', {path : '${ctx}/js/jQuery/jvalidation/messages_cn.js',type : 'js',charset : 'utf-8',rely : [ 'vm' ]});
In.add('vex', {path : '${ctx}/js/jQuery/jvalidation/jquery.validate.ex.js',type : 'js',charset : 'utf-8',rely : [ 'vcn' ]});
In.add('vam', {path : '${ctx}/js/jQuery/jvalidation/additional-methods.min.js',type : 'js',charset : 'utf-8',rely : [ 'vex' ]});
In.add('common', {path : '${ctx}/common/js/common.js',type : 'js',charset : 'utf-8'});
In.add('wdatejs', {path : '${ctx}/js/WdatePicker/WdatePicker.js',type : 'js',charset : 'utf-8'});
In.add('ztreejs',{path:'${ctx}/js/jQuery/ztree/jquery.ztree.all.min.js',type:'js',charset:'utf-8'});
In.ready('vam', 'common', 'wdatejs','jquijs','ztreejs','ztreeui', function() {
var setting = {
	view: {
		dblClickExpand: false,
		showLine: false,
		showIcon: true
	},
	data: {
		simpleData: {
			enable: true,
			idKey:"ID",
			pIdKey:"PARENTID"
		},
		key: {
			title: "TEXT",
			name: "TEXT"	
		}
	},
	check: {
		enable: true
	},
	callback: {
		onCheck: callCheck
	}
};
zTree=jQuery.fn.zTree.init(jQuery("#menuTree"), setting, ${menuJson});

setDefaultValues();
});
//单击树节点展开
function onClick(e,treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("menuTree");
	zTree.expandNode(treeNode);
}
//单击树节点展开
function expandAll() {
	var zTree = $.fn.zTree.getZTreeObj("menuTree");
	zTree.expandAll(true); 
}
//清空
function clearAll() {
	var zTree = $.fn.zTree.getZTreeObj("menuTree");
	zTree.checkAllNodes(false);
	$("#menu_tree_selected").empty();
	$("#menu_tree_ids").val("");
}
//选择回调
function callCheck() {
	$("#menu_tree_ids").val("");
	$("#menu_tree_selected").empty();
	var zTree = $.fn.zTree.getZTreeObj("menuTree");
	var checknodes = zTree.getCheckedNodes(true);
	var effectnodes = new Array();
	var checkedIds="";
	var flag = false;
	for(var i=0;i<checknodes.length;i++){
		var temp = checknodes[i];
		var isLeaf = temp.isParent;
		if(!isLeaf){ 
			effectnodes.push(temp);
		}
	}
	var effectlength = effectnodes.length;
	if(effectlength>6){
		effectlength = 6;
		flag = true;
	}
	zTree.checkAllNodes(false);
	for(var j = 0;j<effectlength;j++){
		var temp = effectnodes[j];
		var tempText = "&nbsp;&nbsp;"+temp.TEXT;
		var tempId = temp.ID;
		checkedIds+=","+tempId;
		$("#menu_tree_selected").append(tempText);
		zTree.checkNode(temp,true,false);
	}
	$("#menu_tree_ids").val(checkedIds);
	if(flag){
		alert("最多只能选择6个连接,系统已自动为您截取前6个有效连接\n您可以根据需要进行调整");
	}
}
function save(){
	inputForm.submit();
}
function setDefaultValues(){
    var zTree = $.fn.zTree.getZTreeObj("menuTree");
	var vals = new Array();
	var names = new Array();
	var tempVal = "";
	var tempText = "";
	var node;
	//设置默认值
	<c:forEach items="${shortcuts}" var="item">
		vals.push("${item['ID']}");
		names.push("${item['TEXT']}");
	</c:forEach>
	if(vals.length>0){
		for(var i = 0;i<vals.length;i++){ 
			node = zTree.getNodeByParam("ID",vals[i]);
			zTree.checkNode(node, true, false);
			tempText += "&nbsp;&nbsp;"+names[i];
			tempVal+=","+vals[i];
		}
		$("#menu_tree_selected").append(tempText);
		$("#menu_tree_ids").val(tempVal);
	}
	
}

</script>
	</head>
	<body>
		<form action="${ctx }/desktop/deskTopWorkBenchAction!save.action" id="inputForm" name="inputForm" method="post">
			<div class="tabcontent">
			    <input type="hidden" id="menu_tree_ids" name="menuIds">
				<table border="0" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<td colspan="4" align="center">
							<b>${tableName}</b>
						</td>
					</tr>
					<tr>
						<th>
							快捷项
						</th>
						<td colspan="3" id="menu_tree_selected" height="50px;">&nbsp;&nbsp;
						</td>
					</tr>
					<tr>
						<th>
							系统菜单
						</th>
						<td colspan="3">&nbsp;&nbsp;
							<div id="menu_tree_content" style="height:275px;overflow:auto;">
								<div id="menuTree" class="ztree" style="margin-top:0;"></div>
							</div>
						</td>
					</tr>
				</table>
				<div style="text-align: center; margin-top: 10px">
					<input type="button" class="button" value="提交" onclick="save();" />
					<input type="button" class="button" value="重置" onclick="clearAll();" />
					<input type="button" class="button" value="展开" onclick="expandAll();" />
				</div>
			</div>
		</form>
	</body>
</html>