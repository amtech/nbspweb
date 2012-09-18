<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xzhtml">
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
							url : "${ctx}/wplan/patrolinfoResultAction!statisticsData.action?queryType=byRegion",
							datatype : "json",
							mtype : 'GET',
							rownumbers : true,
							colNames : [ '地市', '计划站点数', '问题站点数', '拆迁站点数', '纠纷站点数', '其它不能巡检站点数' ],
							colModel : [{
								name : 'REGIONNAME',
								id : 'REGIONNAME',
								sortable : false,
								formatter:orgFmt
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
function search() {
	jQuery("#itemgrid").jqGrid().setGridParam({
    	postData: {
        	'parameter.showAll':jQuery("#showAll").val(),
            'parameter.isQuery':jQuery("#isQuery").val(),
            'parameter.businessType':jQuery("#businessType").val()
        } 
    }).trigger("reloadGrid");
}
//查询
function query() {
	jQuery("#itemgrid").jqGrid().setGridParam({
    	postData: {
         	businessType:jQuery("#business_type").val(),
            startTime:jQuery("#createtime").val(),
            endTime:jQuery("#endtime").val(),
            regionId:jQuery("#query_region_id").val(),
            orgId:'',
            patrolGroupId:''
        } 
     }).trigger("reloadGrid");
}
// 搜索区域
function searchRegion(url) {
	var val = showRegion(url);
	if (!!val) {
		jQuery("#query_region_id").val(val[0]);
		jQuery("#regionname").val(val[1]);
	}
}








function orgFmt(cellvalue, options, rowObjec){
        if(0==cellvalue){
        	return "0";
        }
		var view = "<a style='color: blue;text-decoration: underline;' href=javascript:orgShowDetailed('"
		+ rowObjec.REGIONID+"','"+rowObjec.BUSINESSTYPE+"','"+rowObjec.STARTTIME+"','"+rowObjec.ENDTIME+ "')>"+cellvalue+"</a>";
		return view;
}
function orgShowDetailed(id,businessType,startTime,endTime){
		if('undefined'==businessType){
		businessType="";
		}
		if('undefined'==startTime){
		startTime="";
		}
		if('undefined'==endTime){
		endTime="";
		}
		window.location.href = "${ctx}/wplan/patrolinfoResultAction!orgDetails.action?regionId="+id+"&businessType="+businessType+"&startTime="+startTime+"&endTime="+endTime;
}
function cqDetailedFmt(cellvalue, options, rowObjec){
        if(0==cellvalue){
        	return "0";
        }
		var view = "<a style='color: blue;text-decoration: underline;' href=javascript:cqShowDetailed('"
		+ rowObjec.REGIONID+"','"+rowObjec.BUSINESSTYPE+"','"+rowObjec.STARTTIME+"','"+rowObjec.ENDTIME+ "')>"+cellvalue+"</a>";
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
		window.location.href = "${ctx}/wplan/patrolinfoResultAction!noPatrolDetails.action?problemType=01&regionId="+id+"&businessType="+businessType+"&startTime="+startTime+"&endTime="+endTime;
}
function jfDetailedFmt(cellvalue, options, rowObjec){
        if(0==cellvalue){
        	return "0";
        }
		var view = "<a style='color: blue;text-decoration: underline;' href=javascript:jfShowDetailed('"
		+ rowObjec.REGIONID+"','"+rowObjec.BUSINESSTYPE+"','"+rowObjec.STARTTIME+"','"+rowObjec.ENDTIME+ "')>"+cellvalue+"</a>";
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
		window.location.href = "${ctx}/wplan/patrolinfoResultAction!noPatrolDetails.action?problemType=02&regionId="+id+"&businessType="+businessType+"&startTime="+startTime+"&endTime="+endTime;
}
function qtDetailedFmt(cellvalue, options, rowObjec){
        if(0==cellvalue){
        	return "0";
        }
		var view = "<a style='color: blue;text-decoration: underline;' href=javascript:qtShowDetailed('"
		+ rowObjec.REGIONID+"','"+rowObjec.BUSINESSTYPE+"','"+rowObjec.STARTTIME+"','"+rowObjec.ENDTIME+ "')>"+cellvalue+"</a>";
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
		window.location.href = "${ctx}/wplan/patrolinfoResultAction!noPatrolDetails.action?problemType=03&regionId="+id+"&businessType="+businessType+"&startTime="+startTime+"&endTime="+endTime;
}
function problemResFmt(cellvalue, options, rowObjec){
        if(0==cellvalue){
        	return "0";
        }
		var view = "<a style='color: blue;text-decoration: underline;' href=javascript:showProblemResFmt('"
		+ rowObjec.REGIONID+"','"+rowObjec.BUSINESSTYPE+"','"+rowObjec.STARTTIME+"','"+rowObjec.ENDTIME+ "')>"+cellvalue+"</a>";
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
		window.location.href = "${ctx}/wplan/patrolinfoResultAction!problemStationList.action?regionId="+id+"&businessType="+businessType+"&startTime="+startTime+"&endTime="+endTime;
}

function planFmt(cellvalue, options, rowObjec){
        if(0==cellvalue){
        	return "0";
        }
		var view = "<a style='color: blue;text-decoration: underline;' href=javascript:showPlanFmt('"
		+ rowObjec.REGIONID+"','"+rowObjec.BUSINESSTYPE+"','"+rowObjec.STARTTIME+"','"+rowObjec.ENDTIME+ "')>"+cellvalue+"</a>";
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
		window.location.href = "${ctx}/wplan/patrolinfoResultAction!planInfoList.action?regionId="+id+"&businessType="+businessType+"&startTime="+startTime+"&endTime="+endTime;
}
</script>
</head>
<body>
	<form id="optForm" method="post"
		action="${ctx }/wplan/patrolinfoScheduleAction!statisticsList.action" target="myframe">
		<input name="parameter.isQuery" id='isQuery' value="1" type="hidden" />
	
			<div class="title_bg">
				<div id="title" class="title">当前位置-巡检结果查询</div>
			</div>
			<div class="tabcontent">
				<table cellspacing="0" cellpadding="0" border="0" align="center">
					<tr>
						<th>
							开始时间：
						</th>
						<td> 
							&nbsp;&nbsp;&nbsp;<input id="createtime" name="startTime" type="text"
							value="" class="Wdate inputtext" style="width: 220px;"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endtime\')}'})" />
						</td>
						<th>
							结束时间:
						</th>
						<td>
							&nbsp;&nbsp;&nbsp;<input id="endtime" name="endTime"
							type="text" class="Wdate inputtext" value="" style="width: 220px;"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'createtime\')}'})" />
						</td>
					</tr>
					<tr>
						<th>
							区域：
						</th>
						<td>&nbsp;&nbsp;
							<input id="regionname" name="regionname" class="treetext"
							readonly="readonly" /><a
							href="javascript:searchRegion('${ctx}/commonaccess!getregion.action');">
							<img border="0"
								src="${ctx}/css/image/regionselect.png" />
							</a><input id="query_region_id" name="regionid" type="hidden"  />
						</td>
						<th>
							专业类型：
						</th>
						<td>&nbsp;&nbsp;
							<baseinfo:customselector name="businessType" data="${businessTypeMap}" isReversal="true" isQuery="query" cssClass="inputtext" id="businessType" style="width: 220px"></baseinfo:customselector>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<input type="button" class="button" value="查询" onclick="query();">
							<baseinfo:expexcel cls="exprotButton" businessId="regionpatrolresultstatistics" name="导出excel"></baseinfo:expexcel>
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