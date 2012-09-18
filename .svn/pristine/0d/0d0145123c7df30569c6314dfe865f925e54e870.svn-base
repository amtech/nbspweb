<%@ page language="java" pageEncoding="UTF-8"%>
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
<title>移动考核列表</title>
 
<script type="text/javascript">
        jQuery(function () {
            setContextPath('${ctx }');
            var flag = jQuery('#flag').val();
             
            //移动审核
            var url="";
            if (flag=="1") {
            	//移动考核
                jQuery('#checkdiv').hide(); 
               jQuery('#title').html("当前位置 -移动考核");
            } else if(flag=="2"){
                //移动审核
            	jQuery('#checkdiv').show();
                jQuery('#submitdata').val("审核"); 
                  jQuery('#title').html("当前位置 -考核结果审核");
               
            } else if(flag=="3"){
            	   //代维确认
            	jQuery('#checkdiv').hide();
           	 	jQuery('#submitdata').val("确认"); 
           	 	jQuery('#okdiv').hide();
           	 	  jQuery('#title').html("当前位置 -代维确认");
           	 	
            }
             url="${ctx}/ah/MobileExamFormAction!listWaitCheckData.action?flag="+flag;
            // alert(url);
            //使用层布局
            var jqgrid = jQuery("#mobileexamgrid")
                    .jqGrid(
                    {
                        url:url,
                        datatype:"json",
                        mtype:'GET',
                        rownumbers:true,
                        colNames:[ '人员名称', 'TABLEID', '人员ID', '员工编号',
                            '区域', '专业','是否参与考评','考核期间', 'MAXFLOWNUM', '组织机构', '考核得分', '操  作' ],
                        colModel:[
                            {
                                name:'USERNAME',
                                id:'USERNAME',
                                sortable:false
                            },
                            {
                                name:'TABLE_ID',
                                id:'TABLE_ID',
                                sortable:false,
                                hidden:true
                            },
                            {
                                name:'SID',
                                id:'SID',
                                sortable:false,
                                hidden:true
                            },
                            {
                                name:'EMPLOYEE_NUM',
                                id:'EMPLOYEE_NUM',
                                sortable:false
                            },
                            {
                                name:'REGIONNAME',
                                id:'REGIONNAME',
                                sortable:false
                            },
                            {
                                name:'BUSINESS_TYPE',
                                id:'BUSINESSTYPE',
                                sortable:false
                            },{
                            	name:'IS_EXAM',
                                id:'IS_EXAM',
                                sortable:false,
                                formatter:examFmatter
                            },
                            {
                                name:'YEAR_MONTH',
                                id:'YEARMONTH',
                                sortable:true
                                ,formatter:'date',formatoptions: {newformat:'Y-m'} 
                            },
                            {
                                name:'MAXFLOWNUM',
                                id:'MAXFLOWNUM',
                                hidden:true
                            },
                            {
                                name:'ORGNAME',
                                id:'ORGNAME',
                                sortable:false
                            },
                            {
                                name:'EXAM_ASSE_NUM',
                                id:'EXAMASSENUM',
                                sortable:false
                            },
                            {
                                name:'ID',
                                id:'ID',
                                sortable:false,
                                formatter:detailFmatter
                            }
                        ],
                        rowNum:10,
                        autowidth:true,
                        rowList:[10, 20, 30 ],
                        pager:'#mobileexampager',
                        shrinkToFit:true,
                        multiselect:true,
                        viewrecords:true,
                        hidegrid:false,
                        prmNames:{
                            page:"pageNo",
                            rows:"rows",
                            sort:"sidx",
                            order:"sord"
                        },
                        jsonReader:{
                            root:"result", // 数据行（默认为：rows）
                            page:"pageNo", // 当前页
                            total:"totalPages", // 总页数
                            records:"totalCount", // 总记录数
                            repeatitems:false,
                            id:'ID'
                        }
                    }).navGrid('#mobileexampager', {
                        edit:false,
                        add:false,
                        del:false,
                        search:false,
                        sortable:false
                    });

            jQuery(window).wresize(function () {
                grid_resize(jqgrid);
            });
            grid_resize(jqgrid);
        });
        function examFmatter(cellvalue, options, rowObjec){
        	if(cellvalue=="0"){
        		return "参与";
        	}else if(cellvalue==="1"){
        		return "不参与";
        	}else{
        		return "";
        	}
        }
        // 考核
        function detailFmatter(cellvalue, options, rowObjec) {
        	  var view="";
        	  var str="";
        	  view= "<a style='color: blue;text-decoration: underline;' href=javascript:detailPerson('"
                    + cellvalue
                    + "','"
                    + rowObjec.SID
                    + "','"
                    + rowObjec.TABLE_ID
                    + "','"
                    + rowObjec.MAXFLOWNUM
                    + "')>"; 
        	if(jQuery('#flag').val()=="3"){
        		 str="查看</a>";
        	}else if(jQuery('#flag').val()=="2"){ 
                 str="审核</a>";
        	}else if(jQuery('#flag').val()=="1"){ 
                 str="考核</a>";
        	}
        	view =view+str;
            return view;
        }
        //设置参考
        function detailPerson(id, userid, tableid, maxflownum) {
            window.location.href = "${ctx}/ah/ContractorSelfRatingAction!input.action?personId="
                    + userid + "&id=" + id + "&tableid=" + tableid + "&maxflownum=" + maxflownum+"&flag="+jQuery('#flag').val();
        }
        function SubmitData() {
            var rowdata = "";
            var maxflownum = "";
            rowdata = jQuery("#mobileexamgrid").jqGrid('getGridParam', 'selarrrow'); 
            var newds =[];
            if (rowdata.length == 0) {    
                alert('请至少选择一条记录！');
                return false;
            } else {
                for (var i = 0; i < rowdata.length; i++) {
                    var row = jQuery("#mobileexamgrid").jqGrid('getRowData', rowdata[i]);
                    if(row.IS_EXAM=="参与"&&!row.EXAM_ASSE_NUM){
                    	alert("第"+(i+1)+"行没有考核评分,不能提交！");
                    	return false;
                    }
                    newds.push(row.MAXFLOWNUM);
                    maxflownum = newds.join(",");
                }
            }
            var con = confirm("确定要批量提交这些记录吗?");
            if (!!con) {
                jQuery("#ids").val(rowdata);
                jQuery("#maxflownum").val(maxflownum);
                return true;
            } else {
                return false;
            }
        }
    </script>
</head>
<body>
	<form id="mobileexamForm" name="form"
		action="${ctx }/ah/ContractorSelfRatingAction!submitData.action">
		<div id="header">
			<div class="title_bg">
				<div class="title" id="title">
			    </div>
			</div>
		</div>
		<div align="center" class="tabcontent" style="padding-top: 6px">
			<table cellspacing="0" cellpadding="0" class="Detailed_list"
				border="0" align="center">
				<tr id="okdiv">
					<td colspan="4"><font color="red">请确认是否将选中的代维人员考核结果提交上一级审核</font>
					</td>
				</tr>
				<tr id="checkdiv">
					<th>审批结果：</th>
					<td><input type="radio" value="1" name="result"
						checked="checked" />通过<input type="radio" value="0" name="result" />不通过
					</td>
				</tr>
				<tr>
					<th>备注：</th>
					<td colspan="3"><textarea rows="3" cols="100" id="remark"
							name="remark"></textarea></td>
				</tr>
				<tr>
					<td colspan="4" align="center"><input class="button"
						type="submit" value="提交" onclick=" return SubmitData();"
						id="submitdata" /> <input class="button" type="button" value="返回"
						onclick="Undo()"> <input type="hidden" id="ids" name="ids" />
						<input type="hidden" id="maxflownum" name="maxflownum" /> <input
						type="hidden" id="flag" name="flag" value="${flag}" />
					</td>
				</tr>
			</table>
		</div>

		<div id="content" align="center" style="padding-top: 2px">
			<table id="mobileexamgrid"></table>
			<div id="mobileexampager"></div>
		</div>
	</form>
</body>
</html>