<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link href="${ctx}/css/base.css" rel="stylesheet" type="text/css" />
		<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx }/js/in-min.js" autoload="true" core="${config.cdnurl}/cabletech/ui/js/jquery-1.7.2.min.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src=""></script>
		<script type="text/javascript">
		In.add('jqui',{path:'${ctx}/js/jQuery/jqueryui/css/redmond/jquery-ui.custom.css'});
		In.add('jquijs',{path:'${ctx}/js/jQuery/jqueryui/jquery-ui.custom.min.js',type:'js',charset:'utf-8',rely:['jqui']});
		In.add('jquicn',{path:'${ctx}/js/jQuery/jqueryui/zh/jquery.ui.datepicker-zh-CN.js',type:'js',charset:'utf-8',rely:['jquijs']});
		In.add('jresize',{path:'${ctx}/js/jQuery/jresize/jquery.wresize.js',type:'js',charset:'utf-8',rely:['jquicn']});
		In.add('jgcss',{path:'${ctx}/js/jQuery/jqgrid/css/ui.jqgrid.css',charset:'utf-8',rely:['jresize']});
		In.add('jgjs',{path:'${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js',type:'js',charset:'utf-8',rely:['jgcss']});
		In.add('jgcn',{path:'${ctx}/js/jQuery/jqgrid/grid.locale-cn.js',type:'js',charset:'utf-8',rely:['jgjs']});
		In.add('common',{path:'${ctx}/common/js/common.js',type:'js',charset:'utf-8'});
		In.add('wdatejs',{path:'${ctx}/js/WdatePicker/WdatePicker.js',type:'js',charset:'utf-8'});
		In.add('fault-common', {path : '${ctx}/notice/js/notice_common.js',type : 'js',charset : 'utf-8'});
</script>
	</head>
	<body>
		<form id="searchForm" name="searchForm">
			<div id="header">
				<div class="title_bg">
					<div class="title">
						当前位置-用户操作信息查询
					</div>
				</div>
				<div class="tabcontent">
					<table cellspacing="0" cellpadding="0" border="0" align="center">
						<tr>
							<th>
								操作人员：
							</th>
							<td>
								<input id="userName" name="userName" value="" type="text" style="width: 220px;">
							</td>
							<th>
								操作时间：
							</th>
							<td>
								<input id="startTime" name="startTime" value=""  class="Wdate " onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" style="width: 95px;">
								至
								<input id="endTime" name="endTime" value="" class="Wdate " onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" style="width: 95px;">
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="button" onclick="query();" class="button" value="查询">
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div align="left">
				<a href="javascript:void(0);" onclick="batchDel()">批量删除</a>&nbsp;
				<baseinfo:expexcel  businessId="actionlogresult" name="导出excel"></baseinfo:expexcel>
			</div>
			<div id="content" align="center" style="padding-top: 2px">
				<table id="noticegrid"></table>
				<div id="noticepager"></div>
			</div>
		</form>
	</body>
	<script type="text/javascript">
	function query() {
		if (jQuery("#startTime").val() == '' || jQuery("#endTime").val() == '') {
			alert("日志开始日期和结束日期都必填!");
			return false;
		} else {
			var enddate = getDate(jQuery("#endTime").val()).getTime();
			var begindate = getDate(jQuery("#startTime").val()).getTime();
			if ((enddate - begindate) > 3 * 24 * 60 * 60 * 1000) {
				alert("日志发布日期跨度不能超过三天!");
				return false;
			}
		}
		jQuery("#noticegrid").jqGrid().setGridParam({
			postData : {
				begindate : jQuery("#startTime").val(),
				enddate : jQuery("#endTime").val(),
				userName:jQuery("#userName").val()
			}
		}).trigger("reloadGrid");
	}
	function getDate(strDate) {
		if (strDate == null || strDate === undefined)
			return null;
		var date = new Date();
		try {
			if (strDate == undefined) {
				date = null;
			} else if (typeof strDate == 'string') {
				strDate = strDate.replace(/:/g, '-');
				strDate = strDate.replace(/ /g, '-');
				var dtArr = strDate.split("-");
				if (dtArr.length >= 3 && dtArr.length < 6) {
					date = new Date(dtArr[0], dtArr[1], dtArr[2]);
				} else if (dtArr.length >= 6) {
					date = new Date(Date.UTC(dtArr[0], dtArr[1] - 1, dtArr[2],
							dtArr[3] - 8, dtArr[4], dtArr[5]));
				}
			} else {
				date = null;
			}
			return date;
		} catch (e) {
			alert('格式化日期出现异常：' + e.message);
		}
	}
	function actionFormatter(cellValue,options,rowObject){
		var view="";
		view = "<a style='color: blue;text-decoration: underline;' href=javascript:del('"
			+ cellValue + "')><img src='"+contextPath+"/css/image/delete.png' title='删除' /></a>";
		return view;
	}
	function del(id) {
		if (confirm("删除将不能恢复，请确认是否要删除该用户操作信息查询，按‘确定’删除？")) {
			window.location.href = '${ctx}/system/actionlog!del.action?ids=' + id;
		}
	}
	function batchDel(){
		var idValue=""; 
		idValue = jQuery("#noticegrid").jqGrid('getGridParam','selarrrow');
		if (idValue.length==0) {
			alert('请至少选择一条记录！');
			return false;
		}
	    if (confirm("删除将不能恢复，请确认是否要删除选中的用户操作信息查询，按‘确定’删除？")) {
			window.location.href = '${ctx}/system/actionlog!del.action?ids=' + idValue;
		}
	}
	In.ready('jgcn', 'common', 'wdatejs', 'fault-common', function() {
		setContextPath('${ctx }');
		//使用层布局
		var jqgrid = jQuery("#noticegrid").jqGrid({
			url : "${ctx}/system/actionlog!listData.action",
			datatype : "json",
			mtype : 'POST',
			rownumbers : true,
			colNames : [ '人员名称', '操作时间', '登录IP', '操作名称', '操作描述','操作数据','操作' ],
			colModel : [ {
				name : 'PERSONID',id : 'PERSONID',sortable : false
			}, {
				name : 'OPERATE_TIME',id : 'OPERATE_TIME',sortable : false
			}, {
				name : 'LOGINIP',id : 'LOGINIP',sortable : false
			}, {
				name : 'RECORDID',id : 'RECORDID',sortable : false
			}, {
				name : 'METHOD_DESC',id : 'METHOD_DESC',sortable : false
			}, {
				name : 'RECORD',id : 'RECORD',sortable : false
			}, {
				name : 'ID',id : 'ID',sortable : false,formatter:actionFormatter
			} ],
			rowNum : 10,autowidth : true,rowList : [ 10, 20, 30 ],pager : '#noticepager',shrinkToFit : true,viewrecords : true,hidegrid : false,multiselect: true,
			prmNames : {page : "pageNo",rows : "rows",sort : "sidx",order : "sord"},
			jsonReader : {root : "result",page : "pageNo",total : "totalPages",records : "totalCount",repeatitems : false,id : "ID"}
		}).navGrid('#noticepager', {
			edit : false,add : false,del : false,search : false,sortable : false
		});
		jQuery(window).wresize(function() {
			grid_resize(jqgrid);
		});
		grid_resize(jqgrid);
	});
	</script>
</html>

