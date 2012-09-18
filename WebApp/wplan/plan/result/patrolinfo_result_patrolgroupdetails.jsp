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
<title>巡检项信息查询</title>
<script type="text/javascript">
In.add('jqui',{path:'${ctx}/js/jQuery/jqueryui/css/redmond/jquery-ui.custom.css'});
In.add('ztreeui',{path:'${ctx }/js/jQuery/ztree/css/zTreeStyle/zTreeStyle.css'});
In.add('jquijs',{path:'${ctx}/js/jQuery/jqueryui/jquery-ui.custom.min.js',type:'js',charset:'utf-8',rely:['jqui']});
In.add('jquicn',{path:'${ctx}/js/jQuery/jqueryui/zh/jquery.ui.datepicker-zh-CN.js',type:'js',charset:'utf-8',rely:['jquijs']});
In.add('jresize',{path:'${ctx}/js/jQuery/jresize/jquery.wresize.js',type:'js',charset:'utf-8',rely:['jquicn']});
In.add('jgcss',{path:'${ctx}/js/jQuery/jqgrid/css/ui.jqgrid.css',charset:'utf-8',rely:['jresize']});
In.add('jgjs',{path:'${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js',type:'js',charset:'utf-8',rely:['jgcss']});
In.add('ztreejs',{path:'${ctx}/js/jQuery/ztree/jquery.ztree.all.min.js',type:'js',charset:'utf-8'});
In.add('jgcn',{path:'${ctx}/js/jQuery/jqgrid/grid.locale-cn.js',type:'js',charset:'utf-8',rely:['jgjs']});
In.add('common',{path:'${ctx}/common/js/common.js',type:'js',charset:'utf-8'});
In.add('wdatejs',{path:'${ctx}/js/WdatePicker/WdatePicker.js',type:'js',charset:'utf-8'});
In.ready('jgcn', 'common', 'wdatejs','ztreejs','ztreeui', function() {
		//使用层布局
		var jqgrid = jQuery("#itemgrid")
				.jqGrid(
						{
							url : "${ctx}/wplan/patrolinfoResultAction!patrolgroupDetailsData.action?regionId=${parameters['regionId']}&startTime=${parameters['startTime']}&orgId=${parameters['orgId']}&patrolGroupId=${parameters['patrolGroupId']}&endTime=${parameters['endTime']}&businessType=${parameters['businessType']}",
							datatype : "json",
							mtype : 'GET',
							rownumbers : true,
							colNames : [ '名称', '计划站点数', '问题站点数', '拆迁站点数', '纠纷站点数', '其它不能巡检站点数' ],
							colModel : [{
								name : 'NAME',
								id : 'NAME',
								sortable : false
							}, {
								name : 'PLANNUB',
								id : 'PLANNUB',
								sortable : false,
								formatter:planFmt
							}, {
								name : 'EXCEPTIONNUB',
								id : 'EXCEPTIONNUB',
								sortable : false,
								formatter:problemResFmt
							}, {
								name : 'CQNUB',
								id : 'CQNUB',
								sortable : false,
								formatter:cqDetailedFmt
							}, {
								name : 'JFNUB',
								id : 'JFNUB',
								sortable : false,
								formatter:jfDetailedFmt
							}, {
								name : 'QTNUB',
								id : 'QTNUB',
								sortable : false,
								formatter:qtDetailedFmt
							}
							],
							autowidth : true,
							shrinkToFit : true,
							viewrecords : true,
							hidegrid : false,
							pager : '#itempager',
							rowNum:20,
							rowList:[15,20,30], 
							prmNames : {
								page : "pageNo",
								rows : "rows",
								sort : "sidx",
								order : "sord"
							},
							jsonReader : {
								root : "result",
								page : "pageNo",
								total : "totalPages",
								records : "totalCount",
								repeatitems : false
							}
						}).navGrid('#itempager', {
					edit : false,
					add : false,
					del : false,
					search : false,
					sortable : false
				});
		jQuery(window).wresize(function(){
			grid_resize(jqgrid);
		});
		grid_resize(jqgrid);
});
//查询
function query() {
	jQuery("#itemgrid").jqGrid().setGridParam({
    	postData: {
         	businessType:jQuery("#business_type").val(),
            startTime:jQuery("#createtime").val(),
            endTime:jQuery("#endtime").val(),
            regionId:'',
            orgId:jQuery("#query_org_id").val(),
            patrolGroupId:jQuery("#query_patrolgroup_id").val()
        } 
     }).trigger("reloadGrid");
}
// 搜索代维
function searchQueryOrg(url) {
	var val = showOrg(url);
	if (!!val) {
		jQuery("#query_org_id").val(val[0]);
		jQuery("#orgname").val(val[1]);
	}
}
// 搜索巡检组
function searchQueryPatrolGroup(url) {
	var val = showPatrolGroup(url);
	if (!!val) {
		jQuery("#query_patrolgroup_id").val(val[0]);
		jQuery("#patrolgroupname").val(val[1]);
	}
}
function cqDetailedFmt(cellvalue, options, rowObjec){
        if(0==cellvalue){
        	return "0";
        }
		var view = "<a style='color: blue;text-decoration: underline;' href=javascript:cqShowDetailed('"
		+ rowObjec.ID+"','"+rowObjec.BUSINESSTYPE+"','"+rowObjec.STARTTIME+"','"+rowObjec.ENDTIME+ "')>"+cellvalue+"</a>";
		return view;
}
function cqShowDetailed(id,businessType,startTime,endTime){
		if('undefined'==businessType){
		businessType="";
		}
		if('undefined'==startTime){
		startTime="";
		}
		if('undefined'==endTime){
		endTime="";
		}
		window.location.href = "${ctx}/wplan/patrolinfoResultAction!noPatrolDetails.action?problemType=01&patrolGroupId="+id+"&businessType="+businessType+"&startTime="+startTime+"&endTime="+endTime;
}
function jfDetailedFmt(cellvalue, options, rowObjec){
        if(0==cellvalue){
        	return "0";
        }
		var view = "<a style='color: blue;text-decoration: underline;' href=javascript:jfShowDetailed('"
		+ rowObjec.ID+"','"+rowObjec.BUSINESSTYPE+"','"+rowObjec.STARTTIME+"','"+rowObjec.ENDTIME+ "')>"+cellvalue+"</a>";
		return view;
}
function jfShowDetailed(id,businessType,startTime,endTime){
		if('undefined'==businessType){
		businessType="";
		}
		if('undefined'==startTime){
		startTime="";
		}
		if('undefined'==endTime){
		endTime="";
		}
		window.location.href = "${ctx}/wplan/patrolinfoResultAction!noPatrolDetails.action?problemType=02&patrolGroupId="+id+"&businessType="+businessType+"&startTime="+startTime+"&endTime="+endTime;
}
function qtDetailedFmt(cellvalue, options, rowObjec){
        if(0==cellvalue){
        	return "0";
        }
		var view = "<a style='color: blue;text-decoration: underline;' href=javascript:qtShowDetailed('"
		+ rowObjec.ID+"','"+rowObjec.BUSINESSTYPE+"','"+rowObjec.STARTTIME+"','"+rowObjec.ENDTIME+ "')>"+cellvalue+"</a>";
		return view;
}
function qtShowDetailed(id,businessType,startTime,endTime){
		if('undefined'==businessType){
		businessType="";
		}
		if('undefined'==startTime){
		startTime="";
		}
		if('undefined'==endTime){
		endTime="";
		}
		window.location.href = "${ctx}/wplan/patrolinfoResultAction!noPatrolDetails.action?problemType=03&patrolGroupId="+id+"&businessType="+businessType+"&startTime="+startTime+"&endTime="+endTime;
}
function problemResFmt(cellvalue, options, rowObjec){
        if(0==cellvalue){
        	return "0";
        }
		var view = "<a style='color: blue;text-decoration: underline;' href=javascript:showProblemResFmt('"
		+ rowObjec.ID+"','"+rowObjec.BUSINESSTYPE+"','"+rowObjec.STARTTIME+"','"+rowObjec.ENDTIME+ "')>"+cellvalue+"</a>";
		return view;
}
function showProblemResFmt(id,businessType,startTime,endTime){
		if('undefined'==businessType){
		businessType="";
		}
		if('undefined'==startTime){
		startTime="";
		}
		if('undefined'==endTime){
		endTime="";
		}
		window.location.href = "${ctx}/wplan/patrolinfoResultAction!problemStationList.action?patrolGroupId="+id+"&businessType="+businessType+"&startTime="+startTime+"&endTime="+endTime;
}

function planFmt(cellvalue, options, rowObjec){
        if(0==cellvalue){
        	return "0";
        }
		var view = "<a style='color: blue;text-decoration: underline;' href=javascript:showPlanFmt('"
		+ rowObjec.ID+"','"+rowObjec.BUSINESSTYPE+"','"+rowObjec.STARTTIME+"','"+rowObjec.ENDTIME+ "')>"+cellvalue+"</a>";
		return view;
}
function showPlanFmt(id,businessType,startTime,endTime){
		if('undefined'==businessType){
		businessType="";
		}
		if('undefined'==startTime){
		startTime="";
		}
		if('undefined'==endTime){
		endTime="";
		}
		window.location.href = "${ctx}/wplan/patrolinfoResultAction!planInfoList.action?patrolGroupId="+id+"&businessType="+businessType+"&startTime="+startTime+"&endTime="+endTime;
}
</script>
</head>
<body>
	<form id="optForm" method="post"
		action="${ctx }/wplan/patrolinfoScheduleAction!statisticsList.action" target="myframe">
		<input name="parameter.isQuery" id='isQuery' value="1" type="hidden" />
			<div class="title_bg">
				<div id="title" class="title">当前位置-巡检组巡检结果明细</div>
			</div>
		<br>
		<div class="tabcontent">
					<table cellspacing="0" cellpadding="0" border="0" align="center">
						<tr>
							<td align="center" colspan="4">
								<input type="button" onclick="history.go(-1);" class="button" value="返回" />
								<baseinfo:expexcel cls="exprotButton" businessId="grouppatrolresultstatistics" name="导出excel"></baseinfo:expexcel>
							</td>
						</tr>
					</table>
		</div>
		<br>
		<div id="content" align="center" style="padding-top: 2px">
			<table id="itemgrid"></table>
			<div id="itempager"></div>
		</div>
	</form>
</body>
</html>