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
		url: "${ctx}/sysmanager/gprsmoaction!listData.action",   
		datatype: "json",    
		mtype: 'POST',
		rownumbers: true,
		colNames:['日志内容','记录时间', '巡检组','代维单位','地市'],
		colModel:[
		          {name:'CONTENT',id:'TITLE',sortable:false},
		          {name:'RECEIVE_TIME',id:'ISSUEPERSON',sortable:false},
		          {name:'PATROLNAME',id:'ISISSUE',sortable:false},
		          {name:'ORGNAME',id:'GRADE',sortable:false},
		          {name:'REGIONNAME',id:'ISSUEDATE',sortable:false}
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
	});
		//查询
	function query() {
		if(jQuery("#begindate").val()==''||jQuery("#enddate").val()==''){
			alert("日志开始日期和结束日期都必填!");
			return false;
		}else{
			var enddate = getDate(jQuery("#enddate").val()).getTime();
			var begindate = getDate(jQuery("#begindate").val()).getTime();
			if((enddate-begindate)>3*24*60*60*1000){
				alert("日志发布日期跨度不能超过三天!");
				return false;
			}
		}
		jQuery("#noticegrid").jqGrid().setGridParam({
            postData: {
            	regionid:jQuery("#regionid").val(),
            	orgid:jQuery("#orgid").val(),
            	patrolid:jQuery("#patrolid").val(),
                begindate:jQuery("#begindate").val(),
            	enddate:jQuery("#enddate").val()
            	} 
            }).trigger("reloadGrid");

	}
	//查找区域
	function searchQueryRegion(url){
		var val = showRegion(url);
		if (!!val) {
			jQuery("#regionid").val(val[0]);
			jQuery("#regionname").val(val[1]);
			jQuery("#orgid").val("");
			jQuery("#orgname").val("");
		}
	}
	function searchPatrolGroup() {
		var orgid = jQuery("#orgid").val();
		if(orgid==''){
			alert("请先选择代维公司!");
		}else{
			var patrols = showPatrolGroup('${ctx}/commonaccess!getpatrolgroup.action?orgtype=2&orgid='+jQuery("#orgid").val());
			if (!!patrols) {
				jQuery("#patrolid").val(patrols[0]);
				jQuery("#patrolname").val(patrols[1]);
			}
		}
		
	}
	//获取日期
	function getDate(strDate){  

         if(strDate==null||strDate===undefined) return null;  

         var date = new Date();  

         try{  

             if(strDate == undefined){    

                 date= null;  

            }else if(typeof strDate == 'string'){  

                 strDate = strDate.replace(/:/g,'-');  

                 strDate = strDate.replace(/ /g,'-');  

                 var dtArr = strDate.split("-");  
                 if(dtArr.length>=3&&dtArr.length<6){  
                     date=new Date(dtArr[0], dtArr[1], dtArr[2]);  

                }else if(dtArr.length>=6){  
                     date=new Date(Date.UTC(dtArr[0],dtArr[1]-1,dtArr[2],dtArr[3]-8,dtArr[4],dtArr[5]));  

                  }  

             }else{  

                 date = null;  

             }  
             return date;  

         }catch(e){    

             alert('格式化日期出现异常：' + e.message);    

          }   

      }  
      
      function getcont(){
      	getorg();
      	jQuery("#patrolid").val("");
		jQuery("#patrolname").val("");
      }
	
</script>
</head>
<body>
	<form id="searchForm" name="searchForm">
		<div id="header">
			<div class="title_bg">
				<div class="title">当前位置-GPRS日志查询列表</div>
			</div>
			<div class="tabcontent">
				<table cellspacing="0" cellpadding="0" border="0" align="center">
				<tr>
					<th>所属区域</th>
					<td><input id="regionname" class="inputtext" style="width: 198px"
						readonly="readonly"/> <a
						href="javascript:searchQueryRegion('${ctx}/commonaccess!getregion.action');">
							<img border="0" src="${ctx}/css/images/selectcode.gif" /> </a> <input
						type="hidden" id="regionid" />
					</td>
					<th>代维公司</th>
					<td><input id="orgname" name="orgname" style="width: 198px"
						class="inputtext" readonly="readonly" /><a
						href="javascript:getcont();"> <img border="0"
							src="${ctx}/css/images/selectcode.gif" /> </a><input id="orgid"
						name="orgid" type="hidden" />
					</td>
				</tr>
				<tr>
					<th>巡检组</th>
					<td colspan="3"><input id="patrolname" class="inputtext" style="width: 198px"
						readonly="readonly"/> <a
						href="javascript:searchPatrolGroup();">
							<img border="0" src="${ctx}/css/images/selectcode.gif" /> </a> <input
						type="hidden" id="patrolid" />
					</td>
				</tr>
					<tr>
						<th>日志开始日期：</th>
						<td><input type="text" name="begindate" class="Wdate" style="width: 198px"
							id="begindate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
							<font color="red" size="2">*</font>
						</td>
						<th>日志结束日期：</th>
						<td><input type="text" name="enddate" class="Wdate" style="width: 198px"
							id="enddate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
							<font color="red" size="2">*</font>
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

