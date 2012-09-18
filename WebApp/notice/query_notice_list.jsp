<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx }/js/jQuery/jqgrid/css/ui.jqgrid.css" type="text/css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${ctx}/js/jQuery/jqgrid/grid.locale-cn.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jqgrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/WdatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${ctx}/notice/js/notice_common.js"></script>
<script type="text/javascript">
	jQuery(function(){
		setContextPath('${ctx }');
	//使用层布局
  var jqgrid=jQuery("#noticegrid").jqGrid({    
		url: "${ctx}/system/notice!listdate.action?flag=${noticeflag}",   
		datatype: "json",    
		mtype: 'POST',
		rownumbers: true,
		colNames:['标题','发布人', '是否发布','重要程度','发布日期','类型','操作'],
		colModel:[
		          {name:'TITLE',id:'TITLE',sortable:false},
		          {name:'ISSUEPERSON',id:'ISSUEPERSON',sortable:false},
		          {name:'ISISSUE',id:'ISISSUE',sortable:false},
		          {name:'GRADE',id:'GRADE',sortable:false},
		          {name:'ISSUEDATE',id:'ISSUEDATE',sortable:false},
		          {name:'TYPENAME',id:'TYPENAME',sortable:false},
		          {name:'ID',id:'ID',sortable:false,formatter:ActionFmatter}
		          ],      
		rowNum:10,
		autowidth:true,
		rowList:[10,20,30],    
		pager: '#noticepager',
		shrinkToFit:true,
		viewrecords: true, 
		hidegrid: false, 
		prmNames: {page:"pageNo",rows:"rows", sort: "sidx",order: "sord"},   
		jsonReader: {
               root:"result" ,                // 数据行（默认为：rows） 
               page: "pageNo" ,            // 当前页 
               total: "totalPages" ,    // 总页数 
               records: "totalCount",     // 总记录数 
               repeatitems: false,
               id:"0"
               }
		  }).navGrid('#noticepager',{edit:false ,add:false ,del:false,search:false,sortable:false  });
  jQuery(window).wresize(function(){
		grid_resize(jqgrid);
	});
	grid_resize(jqgrid);
	})
// 公告操作列
function ActionFmatter(cellvalue, options, rowObjec) {
	var view = "<a style='color: blue;text-decoration: underline;' href=javascript:open_notify('"
			+ cellvalue + "')>查看</a>";
			if('${noticeflag}'!=='2'){
	view = view
			+ "  <a style='color: blue;text-decoration: underline;' href=javascript:toEdit('"
			+ cellvalue + "')>编辑</a>";
	view = view
			+ "  <a style='color: blue;text-decoration: underline;' href=javascript:toDelete('"
			+ cellvalue + "')>删除</a>";
			}
	return view;
}
		//查询
	function query() {
		jQuery("#noticegrid").jqGrid().setGridParam({
            postData: {
            	title:jQuery("#title").val(),
            	type:jQuery("#type").val(),
            	grade:jQuery("#grade").val(),
            	isissue:jQuery("#isissue").val(),
                beginDate:jQuery("#begindate").val(),
            	endDate:jQuery("#enddate").val()
            	} 
            }).trigger("reloadGrid");


	}
</script>
</head>
<body>
	<form id="searchForm" name="searchForm">
		<div id="header">
			<div class="title_bg">
				<div class="title">当前位置-信息列表</div>
			</div>
			<div class="tabcontent">
				<table cellspacing="0" cellpadding="0" border="0" align="center">
					<tr>
						<th>标题:</th>
						<td><input type="text" name="title"
							id="title" class="inputtext" style="width: 300px"
							maxlength="20" />
						</td>
						<th>类型:</th>
						<td><baseinfo:dicselector name="type" id="type"
								columntype="INFORMATION" type="select" isQuery="query"></baseinfo:dicselector>
						</td>
					</tr>
					<tr>
						<th>发布开始日期：</th>
						<td><input type="text" name="beginDate" class="Wdate"
							id="begindate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
						</td>
						<th>发布结束日期：</th>
						<td><input type="text" name="endDate" class="Wdate"
							id="enddate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
					</tr>
					<tr>
						<th>重要程度：</th>
						<td><select property="grade" styleClass="inputtext"
							id="grade" style="width: 150px">
								<option value="">不限</option> 
								<option value="重要">重要</option> 
								<option value="一般">一般</option>
						</select>
						</td>
						<th>
						<c:if test="${noticeflag!=2 }">是否发布：</c:if>
						</th>
						<td><c:if test="${noticeflag!=2 }"><select property="isissue" styleClass="inputtext"
							id="isissue" style="width: 150px">
								<option value="">不限</option>
								<option value="y">已发布</option>
								<option value="n">未发布</option>
						</select></c:if>
						</td>
					</tr>

					<tr>
						<td colspan="4" align="center"><input type="button"
							onclick="query();" class="button" value="查询"></td>
					</tr>
				</table>
			</div>
		</div>
		<div id="content" align="center" style="padding-top: 2px">
			<table id="noticegrid"></table>
			<div id="noticepager"></div>
		</div>
	</form>
</body>
</html>

