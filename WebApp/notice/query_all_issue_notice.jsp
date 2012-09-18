<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<script type="text/javascript"
			src="${ctx}/js/WdatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
	function open_notify(NOTICE_ID, FORMAT) {
		URL = "${ctx}/system/notice!view.action?&id=" + NOTICE_ID
				+ "&preview=false";
		myleft = (screen.availWidth - 650) / 2;
		mytop = 100
		mywidth = 650;
		myheight = 500;
		if (FORMAT == "1") {
			myleft = 0;
			mytop = 0
			mywidth = screen.availWidth - 10;
			myheight = screen.availHeight - 40;
		}
		window
				.open(
						URL,
						"read_news",
						"height="
								+ myheight
								+ ",width="
								+ mywidth
								+ ",status=1,resizable=no,toolbar=no,menubar=no,location=no,scrollbars=yes,top="
								+ mytop + ",left=" + myleft + ",resizable=yes");
	}
</script>
	</head>
	<body>
		<form id="searchForm" name="searchForm"
			action="${ctx }/system/notice!list.action">
			<div class="ui-layout-north">
				<div class="title_bg">
					<div  class="title">
						当前位置-发布信息列表
					</div>
				</div>
				<div class="framecontentBottom">
					<table cellspacing="0" cellpadding="0" border="0" align="center">
						<tr>
							<th>
								关键字:
							</th>
							<td>
								<input type="text" name="notice.contentString" class="inputtext"
									style="width: 300px" maxlength="20" />
							</td>
							<th>
								类型:
							</th>
							<td>
								<baseinfo:dicselector name="notice.type" id="type"
									columntype="INFORMATION" type="select" isQuery="query"></baseinfo:dicselector>
							</td>
						</tr>
						<tr>
							<th>
								重要程度:
							</th>
							<td>
								<select property="notice.grade" styleClass="inputtext"
									style="width: 150px">
									<option value="">
										不限
									</option>
									重要
									<option value="重要">
									</option>
									一般
									<option value="一般">
									</option>
								</select>
							</td>
							<th>
								是否发布
							</th>
							<td>
								<select property="notice.isissue" styleClass="inputtext"
									style="width: 150px">
									<option value="">
										不限
									</option>
									<option value="y">
										已发布
									</option>
									<option value="n">
										未发布
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>
								发布日期：
							</th>
							<td>
								<input type="text" name="notice.beginDate" class="Wdate"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
								--
								<input type="text" name="notice.endDate" class="Wdate"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
							</td>
							<th>
							</th>
							<td>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4">
								<input class="button" type="submit" id="" name="" value="查询">
							</td>
						</tr>
					</table>
				</div>
				<div class="pagination_box">
					<baseinfo:pagenation records="${page.totalCount}"
						pageAlias="pageNo" url="${ctx }/system/notice!list.action"></baseinfo:pagenation>
				</div>
			</div>
			<div class="grid ui-layout-center">
				<div class="gridHeader">
					<table>
						<thead>
							<tr>
								<th width="350px">
									<div class="gridCol">
										标题
									</div>
								</th>
								<th width="120px">
									<div class="gridCol">
										发布人
									</div>
								</th>
								<th width="120px">
									<div class="gridCol">
										是否发布
									</div>
								</th>
								<th width="180px">
									<div class="gridCol">
										发布日期
									</div>
								</th>
								<th width="80px">
									<div class="gridCol">
										操作
									</div>
								</th>
							</tr>
						</thead>
					</table>
				</div>
				<div class="gridScroller" style="height: 95%">
					<div class="gridTbody">
						<table>
							<c:forEach items="${page.result}" var="item">
								<tr>
									<td width="350px">
										<div>
											${item.title}
										</div>
									</td>
									<td width="120px">
										<div>
											${item.issueperson}
										</div>
									</td>
									<td width="120px">
										<div>
											${item.isissue}
										</div>
									</td>
									<td width="180px">
										<div>
											${item.issuedate}
										</div>
									</td>
									<td width="80px">
										<div>
											<a href="javascript:open_notify('${item.id}')">查看</a>
										</div>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</form>
	</body>
</html>

