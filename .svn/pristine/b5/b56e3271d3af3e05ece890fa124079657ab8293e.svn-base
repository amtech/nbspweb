<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<%@ include file="/common/meta.jsp"%>
<head>
<title>首页</title>
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/js/in-min.js"
	autoload="true"
	core="${config.cdnurl}/cabletech/ui/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
In.add('layout',{path:'${ctx}/js/jQuery/layout/layout-default-latest.css'});
In.add('jqui',{path:'${ctx}/js/jQuery/jqueryui/css/redmond/jquery-ui.custom.css'});
In.add('ztreecss',{path:'${config.cdnurl}/cabletech/ui/ztree/css/zTreeStyle/zTreeStyle.css'});
In.add('ztreejs',{path:'${config.cdnurl}/cabletech/ui/ztree/jquery.ztree.all.min.js',type:'js',charset:'utf-8',rely:['ztreecss']});
In.add('jquijs',{path:'${ctx}/js/jQuery/jqueryui/jquery-ui.custom.min.js',type:'js',charset:'utf-8',rely:['jqui']});
In.add('layoutjs',{path:'${ctx}/js/jQuery/layout/jquery.layout-latest.min.js',type:'js',charset:'utf-8',rely:['layout']});
In.add('mainjs',{path:'${ctx}/frames/default/js/main.js',type:'js'});
In.ready('layoutjs','ztreejs','jquijs','mainjs',function(){
	jQuery(function(){
	 //左右布局
	jQuery('body').layout({ applyDefaultStyles: false});
	//使用折叠样式
 	accordionObj=$("#accordion").accordion({
 				autoHeight:false,
 				header: 'h3',
 				navigation:true,
 				collapsible: true,
 				activate:0,
 				change:function(event, ui){
 					//获取菜单折叠组件头ID
 					var currentHeaderID=ui.newHeader.find("a").attr("id");
 					if(currentHeaderID){
 						//获取菜单ID
 						var menuid=currentHeaderID.replace("accord","");
 						var treeid="ztree"+menuid;
 						//根据菜单ID获取是否有菜单
 						var treeobj= $.fn.zTree.getZTreeObj(treeid);
 						//如果没有从服务器上获取菜单
 						if(!treeobj){
 							getMenuTree(menuid,treeid);
 						}
 					}
 					}		 				  
 			});
    //获取活动层叠内容ID
 	var actiontreeid=accordionObj.find('.ui-accordion-content-active').attr('id');
    //如果不为空
 	if(!!actiontreeid){
 		var menuid=actiontreeid.replace("ztree","");
 		getMenuTree(menuid,actiontreeid);
 	}
	createframe('${jumpup}','auto','','mainFrame','content');

	})

});
var setting = {
		view: {
			showLine: false
		},
		data:{
			simpleData: {
				enable: true,
				idKey:"ID",
				pIdKey:"PARENTID",
				rootPId:"root"	
			},
			key: {
				title: "TEXT",
				name: "TEXT"
				
			}
		},
		callback: {
			onClick:pageSkip
		}
		
	};

//页面跳转
function pageSkip(e, treeId, node) {
  if(node.HREFURL)
	  jQuery('#mainFrame').attr('src',node.HREFURL);
}
//生成菜单数
function getMenuTree(menuid,treeid){
	 jQuery.ajax({
			url : '${ctx}/desktop/leftNavigate!getmenutree.action?menuid='+menuid,
			dataType : "json",
			type:'GET',
			cache:true,
			async:true,
			success : function(result) {
				if(!!result){
			 	jQuery.fn.zTree.init(jQuery("#"+treeid), setting,result);
				}
			},
			error : function() {
				//alert("获取菜单数据异常！");
			}
		});	
}
</script>
</head>
<body>
	<div class="ui-layout-west">
		<div id="accordion">
			<c:forEach var="item" items="${sessionScope.menuheadList}">
				<span style="overflow: auto">
					<h3>
						<a href="#" id="accord${item.id}">${item.text}</a>
					</h3>
					<div id="ztree${item.id}" class="ztree"></div>
				</span>
			</c:forEach>
		</div>
	</div>
	<div class="ui-layout-center" id="content"></div>
</body>
</html>
