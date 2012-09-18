<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/js/jQuery/jautocomplete/jquery.autocomplete.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${ctx}/js/jQuery/jvalidation/jquery.validate.js"></script>
<script type="text/javascript"
			src="${ctx}/js/jQuery/jvalidation/jquery.validate.cn.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jvalidation/messages_cn.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jQuery/jautocomplete/jquery.autocomplete.js"></script>
<title>坐标校正</title>
<script type="text/javascript">
function check() {
	if (jQuery("#id").val() == "") {
		alert("没有选择正确的资源点！");
		return false;
	}
	return true;
}

	jQuery(function(){
		jQuery("#lonlatReviseForm").validate({
			debug : true,
			submitHandler : function(form) {
				if (check()) {
					form.submit();
				}
			}});
	resAutocomplete();
});
	function resAutocomplete() {
    jQuery("#resname").autocomplete("${ctx}/resource/lonlatReviseAction!getResPoint.action", {
        selectFirst: false,
        max: 50,      //列表里的条目数
        minChars: 0,    //自动完成激活之前填入的最小字符
        autoFill: false,
        matchContains: 'word',
        highlightItem: true,
        cacheLength: 1,
        parse: function(data) {   
            var rows = [];   
            for(var i=0; i<data.length; i++){ 
            if(data[i].TYPE==jQuery('select[name="pointtype"]').val()){
              rows[rows.length] = {   
                data:data[i],   
                value:data[i].NAME,   
                result:data[i].NAME   
                };
            	}
              }   
         	 return rows;   
             },   
        formatItem: function(row, i, max, term) {
        	//只返回类型匹配的数据
        	if(row.TYPE==jQuery('select[name="pointtype"]').val()){
            	return row.NAME;
        	}else{
        		return null;
        	}
        },
        formatResult: function(row, i, max) {
        	//只返回类型匹配的数据
        	if(row.TYPE==jQuery('select[name="pointtype"]').val()){
        		return row.NAME;
        		}else{
        			return null;
        		}
      
        }
    }).result(function(event, item) {
    	//筛选到值后，处理赋值
        jQuery('#olon').val(item.LON);
        jQuery('#olat').val(item.LAT);
        jQuery('#oct_x').val(item.CT_X);
        jQuery('#oct_y').val(item.CT_Y);
        jQuery('#pointid').val(item.POINTID);
        jQuery('#id').val(item.ID);
    });
}
	 //点类型改变时清空数据
	function changetype(){
		 jQuery('#resname').val("");
		  jQuery('#olon').val("");
	        jQuery('#olat').val("");
	        jQuery('#oct_x').val("");
	        jQuery('#oct_y').val("");
	        jQuery('#pointid').val("");
	        jQuery('#id').val("");
	}
</script>
</head>
<body>
	<form action="${ctx}/resource/lonlatReviseAction!save.action"
		id="lonlatReviseForm" name="lonlatReviseForm" method="post">
		<div class="title_bg">
			<div id="title" class="title">当前位置-资源坐标校正</div>
		</div>
		<div class="tabcontent">
			<table cellspacing="0" cellpadding="0" border="0" align="center"
				style="width: 60%">
				<tr>
					<th>资源类型：</th>
					<td><baseinfo:customselector name="pointtype" id="pointtype"
							data="${resource_type_map}" isReversal="true"
							cssClass="inputtext" keyValue="${oneResourceType.key}"
							onChange="changetype();"></baseinfo:customselector></td>
				</tr>
				<tr>
					<th>请输入资源名称：</th>
					<td><input type="text" id="resname" class="inputtext  " /> <input
						id="pointid" type="hidden" name="pointid" class="inputtext" /> <input
						id="id" type="hidden" name="id" class="inputtext" /></td>
				</tr>
				<tr>
					<th>原经度X：</th>
					<td><input id="olon" type="text" class="inputtext"
						readonly="readonly" />
					</td>
				</tr>
				<tr>
					<th>原纬度Y：</th>
					<td><input id="olat" type="text" class="inputtext"
						readonly="readonly" />
					</td>
				</tr>
				<tr>
					<th>原X坐标：</th>
					<td><input id="oct_x" type="text" class="inputtext"
						readonly="readonly" />
					</td>
				</tr>
				<tr>
					<th>原Y坐标：</th>
					<td><input id="oct_y" type="text" class="inputtext"
						readonly="readonly" />
					</td>
				</tr>
				<tr>
					<th>输入新经度X：</th>
					<td><input id="lon" name="lon" type="text"
						class="inputtext required" />
					</td>
				</tr>
				<tr>
					<th>输入新纬度Y：</th>
					<td><input id="lat" name="lat" type="text"
						class="inputtext required" />
					</td>
				</tr>
			</table>
			<div style="text-align: center; margin-top: 10px">
			<input
						type="submit" class="button" value="确 认"> <input
						type="reset" class="button" value="重置">
			</div>
		</div>
	</form>
</body>
</html>