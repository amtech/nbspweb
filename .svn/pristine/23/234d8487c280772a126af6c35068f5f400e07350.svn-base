<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div id="stafftree" class="ztree" style="overflow: auto;height: 300px"></div>

<script type="text/javascript">
	//简单在线人员树样式	
	var setting = {
				view: {
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
						title: "NAME",
						name: "NAME"
					}
					}
			};
	In.add('ztreecss',{path:'${ctx }/js/jQuery/ztree/css/zTreeStyle/zTreeStyle.css'});
	In.add('ztreejs',{path:'${ctx}/js/jQuery/ztree/jquery.ztree.all.min.js',charset:'utf-8',rely:['ztreecss']});
	In.ready('ztreejs',function(){
		 //转换成js对象
		var jsonobj=$.parseJSON('${onlinemanJson}');
		var zTree=$.fn.zTree.init($("#stafftree"), setting, ${onlinemanJson});
		var tn=zTree.getNodesByParam('OBJTYPE','REGION');
		if(""==tn){
			tn=zTree.getNodesByParam('OBJTYPE','ORG');
		}
		//如果能找到代维公司
		if(tn){
			for(var i=0;i<tn.length;i++){
			    var reg_allxjzcount=0;//代维人员数量
				var reg_zxrycount=0;//在线人员数量
				var tnz=tn[i].children;
				if(tnz){ 
					for(var j=0;j<tnz.length;j++){
						var org_allxjzcount=tnz[j].ALLMANCOUNT//代维人员数量
						var org_zxrycount=tnz[j].OLMANCOUNT//在线人员数量
						reg_allxjzcount += org_allxjzcount;
						reg_zxrycount += org_zxrycount;
						tnz[j].NAME = tnz[j].NAME+'('+org_zxrycount+'/'+org_allxjzcount+')';
						tnz[j].flag = true;
						zTree.updateNode(tnz[j]);
					}
				}
				if(!tn[i].flag){
					tn[i].NAME = tn[i].NAME+'('+reg_zxrycount+'/'+reg_allxjzcount+')';
					zTree.updateNode(tn[i]);
				}
			}
		}
		zTree.expandNode(tn[0], true, false, true);
	})
</script>