<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<link href="${ctx}/js/jQuery/layout/layout-default-latest.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx }/js/jQuery/ztree/css/zTreeStyle/zTreeStyle.css"
	type="text/css" rel="stylesheet"/>
<script type="text/javascript"
	src="${ctx}/js/jQuery/layout/jquery.layout-latest.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/ztree/jquery.ztree.all.min.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请选择</title>
<script type="text/javascript">
	//简单区域树样式	
var setting = {
				view: {
						dblClickExpand: false,
						showLine: false,
						showIcon: true
						},
						data: {
								simpleData: {
								enable: true,
								idKey:"REGIONID",
								pIdKey:"PARENTID"
							},
							key: {
								title: "REGIONNAME",
								name: "REGIONNAME"
								
							}
						},
						check: {
							enable: true,
							chkStyle: "radio",
							radioType: "all"
						},
						callback: {
							onClick: onClick
						}
					};
		jQuery(function(){
			 //区域树布局
			bodyLayout=jQuery('body').layout({ applyDefaultStyles: true ,south__resizable:false,south__spacing_open:0});
			 //获取区域树
			 zTree=jQuery.fn.zTree.init(jQuery("#regiontree"), setting, ${regiontree});
			 zTree.expandAll(true); 
		});
		//单击树节点展开
		function onClick(e,treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("regiontree");
			zTree.expandNode(treeNode);
		}
		//选择节点
		function save() {
			var zTree = $.fn.zTree.getZTreeObj("regiontree");
			//获取选中节点
			var checknodes = zTree.getCheckedNodes(true);
			var regionnodes= new Array();
			if(checknodes){
				for(var i=0;i<checknodes.length;i++){
					regionnodes.push(checknodes[i]);
					
				}
				if(regionnodes.length===0){
					alert("请选择区域！");
					}
				else{
					window.returnValue = regionnodes;
					window.close();
				}
				
			}else{
				alert("请选择区域！");
			}
			
		}
		//关闭窗体
		function quit(){
			window.close();
		}
</script>
</head>
<body>
	<div class="ui-layout-center">
		<div class="title_bg">
			<div id="title" class="title">选择区域</div>
		</div>
		<div id="regiontree" class="ztree" style="margin-top:0;"></div>
	</div>
	<div class="ui-layout-south" style="text-align: center;">
		<input name="btnSave" value="选择" type="button" class="button"
			onclick="save();" /> <input name="btnReset" value="关闭" type="button"  
			class="button" onclick="quit();" />
	</div>
</body>
</html>