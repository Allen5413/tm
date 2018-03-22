<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>

<%--<body>--%>
	<%--<div class="main-content">--%>
		<%--<form id="pageForm" name="pageForm" class="polPageForm" action="${pageContext.request.contextPath}/centerPay/verifySpotPay.htm" method="post">--%>
			<%--<input type="hidden" id="page" name="page" value="${pageInfo.currentPage}"/>--%>
			<%--<ul class="create_info_list">--%>
				 <%--<li>--%>
			        <%--<label class="lab_80">学习中心：</label>--%>
			        <%--<select id="spotCode" name="spotCode">--%>
			          <%--<option value="">请选择</option>--%>
			          <%--<c:forEach var="spot" items="${spotList}">--%>
			            <%--<option value="${spot.code}" <c:if test="${param.spotCode eq spot.code}">selected="selected" </c:if> >[${spot.code}]${spot.name}</option>--%>
			          <%--</c:forEach>--%>
			        <%--</select>--%>
        			 <%--&nbsp;&nbsp;&nbsp;&nbsp;--%>
			        <%--<a class="com_btn_b" href="#" id="subButPol" onclick="pageForm.submit();"><span>查&nbsp;询</span></a>&nbsp;&nbsp;--%>
			        <%--<a class="com_btn_b" href="#" id="resetBut" onclick="resetForm();"><span>重&nbsp;置</span></a>--%>
				 <%--</li>--%>
			<%--</ul>--%>
		<%--</form>--%>
		<%----%>
		<%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
			<%--<tr>--%>
		      	<%--<th style="width: 30px">序号</th>--%>
				<%--<th>学习中心编号</th>--%>
		      	<%--<th>学习中心名称</th>--%>
		      	<%--<th>交费金额</th>--%>
		      	<%--<th>支付方式</th>--%>
		      	<%--<th>交费人</th>--%>
		      	<%--<th>交费日期</th>--%>
		      	<%--<th>审核人</th>--%>
		      	<%--<th>审核日期</th>--%>
		      	<%--<th>状态</th>--%>
				<%--<th>备注</th>--%>
		      	<%--<th>操作</th>--%>
    		<%--</tr>--%>
    		<%--<c:if test="${empty pageInfo || empty pageInfo.pageResults}">--%>
     			<%--<tr>--%>
        			<%--<td colspan="8" align="center" style="color: red;">没有找到相关数据</td>--%>
      			<%--</tr>--%>
    		<%--</c:if>--%>
    		<%--<c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">--%>
      		<%--<tr>--%>
		        <%--<td align="center">${status.index+1}</td>--%>
		        <%--<td>${json.spotCode}</td>--%>
		        <%--<td>${json.spotName}</td>--%>
		        <%--<td>${json.spotMoney}</td>--%>
		        <%--<c:if test="${json.payWay == 1}">--%>
		        	<%--<td>其他</td>--%>
		        <%--</c:if>--%>
		        <%--<c:if test="${json.payWay == 2}">--%>
		        	<%--<td>工商银行</td>--%>
		        <%--</c:if>--%>
		        <%--<c:if test="${json.payWay == 3}">--%>
		        	<%--<td>支付宝</td>--%>
		        <%--</c:if>--%>
		        <%--<c:if test="${json.payWay == null || json.payWay == '' }">--%>
		        	<%--<td></td>--%>
		        <%--</c:if>--%>
		        <%--<td>${json.payer}</td>--%>
		        <%--<td>${json.payTime}</td>--%>
		        <%--<td>${json.verifyer}</td>--%>
		        <%--<td>${json.verifyTime}</td>--%>
		        <%--<td>${json.status}</td>--%>
				<%--<td>${json.remark}</td>--%>
		        <%--<td>--%>
					<%--<button onclick="alterDetailPage('${json.polId}');">查看</button>--%>
					<%--<c:if test="${'审核通过' eq json.status}">--%>
						<%--&nbsp;&nbsp;&nbsp;&nbsp;<button onclick="downDetail('${json.polId}');">下载</button>--%>
					<%--</c:if>--%>
				<%--</td>--%>
      		<%--</tr>--%>
    		<%--</c:forEach>--%>
    		<%--<%@ include file="/common/page.jsp"%>--%>
		<%--</table>--%>
	<%--</div>--%>
	<%--<script type="text/javascript">--%>
		<%--function resetForm(){--%>
			<%--$("#spotCode").val("");--%>
		<%--}--%>

		<%--function alterDetailPage(polId){--%>
			<%--var dialog = new Dialog();--%>
			<%--dialog.Width = 800;--%>
			<%--dialog.Height = 900;--%>
			<%--dialog.Title = "明细";--%>
			<%--dialog.URL = "${pageContext.request.contextPath}/centerPay/querySpotDetail.htm?polId=" + polId;--%>
			<%--dialog.show();--%>
		<%--}--%>

		<%--function downDetail(polId){--%>
			<%--$.ajax({--%>
				<%--cache: true,--%>
				<%--type: "POST",--%>
				<%--url:"${pageContext.request.contextPath}/centerPay/downSpotDetail.htm?polId=" + polId,--%>
				<%--data:{},--%>
				<%--async: false,--%>
				<%--success: function(data) {--%>
					<%--open("${pageContext.request.contextPath}"+data);--%>
				<%--}--%>
			<%--});--%>
		<%--}--%>
	<%--</script>--%>
<%--</body>--%>

<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/centerPay/verifySpotPay.htm" method="post">
	<input type="hidden" id="rows" name="rows" />
	<input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
	<label >学习中心：</label>
	<select id="spotCode" name="spotCode" data-am-selected="{maxHeight: 500, searchBox: 1}" onchange="app.changeSelect(this)">
		<option value=""></option>
		<option value="null">全部</option>
		<c:forEach var="spot" items="${spotList}">
			<option value="${spot.code}" <c:if test="${param.spotCode eq spot.code}">selected="selected" </c:if> >[${spot.code}]${spot.name}</option>
		</c:forEach>
	</select>
	&nbsp;&nbsp;&nbsp;&nbsp;
	<label >状态：</label>
	<select id="state" name="state" onchange="app.changeSelect(this)">
		<option value=""></option>
		<option value="null">全部</option>
		<option value="0" <c:if test="${param.state eq '0'}">selected="selected" </c:if> >未审核</option>
		<option value="1" <c:if test="${param.state eq '1'}">selected="selected" </c:if> >审核通过</option>
		<option value="2" <c:if test="${param.state eq '2'}">selected="selected" </c:if> >审核不通过</option>
	</select>
	&nbsp;&nbsp;&nbsp;&nbsp;

	<button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example"
			data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '查询超时'}"
			onclick="app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'), this)"><span class="am-icon-search"></span> 查询</button>
</form>
<p /><p />
<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
	<tr class="am-primary">
		<th style="width: 30px">序号</th>
		<th>中心编号</th>
		<th>中心名称</th>
		<th>交费金额</th>
		<th>支付方式</th>
		<th>交费人</th>
		<th>交费日期</th>
		<th>审核人</th>
		<th>审核日期</th>
		<th>状态</th>
		<th>备注</th>
		<th>操作</th>
	</tr>
	<c:if test="${empty pageInfo || empty pageInfo.pageResults}">
		<tr>
			<td colspan="8" align="center" style="color: red;">没有找到相关数据</td>
		</tr>
	</c:if>
	<c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">
		<tr>
			<td align="center">${status.index+1}</td>
			<td>${json.spotCode}</td>
			<td>${json.spotName}</td>
			<td>${json.spotMoney}</td>
			<c:if test="${json.payWay == 1}">
				<td>其他</td>
			</c:if>
			<c:if test="${json.payWay == 2}">
				<td>工商银行</td>
			</c:if>
			<c:if test="${json.payWay == 3}">
				<td>支付宝</td>
			</c:if>
			<c:if test="${json.payWay == null || json.payWay == '' }">
				<td></td>
			</c:if>
			<td>${json.payer}</td>
			<td>${json.payTime}</td>
			<td>${json.verifyer}</td>
			<td>${json.verifyTime}</td>
			<td>${json.status}</td>
			<td>${json.remark}</td>
			<td>
				<div class="am-btn-group">
					<button type="button" class="am-btn am-btn-primary am-round" onClick="alterDetailPage('${json.polId}', '${json.status}')"><span class="am-icon-th-list"></span> 查看</button>
					<c:if test="${'审核通过' eq json.status}">
						<button type="button" class="am-btn am-btn-primary am-round" onClick="downDetail('${json.polId}')"><span class="am-icon-download"></span> 下载</button>
					</c:if>
				</div>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="/common/page2.jsp"%>
<script>
	$("select").selected();
	function alterDetailPage(polId, status){
		if("未审核" == status){
			app.openAuditBtnDialog("${pageContext.request.contextPath}/centerPay/querySpotDetail.htm?polId=" + polId, "查看交费明细", 900, 0.8, function(index, flag){
				audit(index, flag);
			});
		}else{
			app.openOneBtnDialog("${pageContext.request.contextPath}/centerPay/querySpotDetail.htm?polId=" + polId, "查看交费明细", 900, 0.8);
		}
	}

	function downDetail(polId){
		$.ajax({
			cache: true,
			type: "POST",
			url:"${pageContext.request.contextPath}/centerPay/downSpotDetail.htm?polId=" + polId,
			data:{},
			async: false,
			success: function(data) {
				open("${pageContext.request.contextPath}"+data);
			}
		});
	}

	function audit(index, ope){
		var url = "";
		if(0 == ope){
			if($("#wdate").val().trim() == ""){
				app.msg("请选择到账时间", 1);
				return false;
			}else {
				$("#arrivalTime").val($("#wdate").val() + " 00:00:00");
				url = "${pageContext.request.contextPath}/centerPay/verifySure.htm";
			}
		}else{
			url = "${pageContext.request.contextPath}/centerPay/verifyNotSure.htm";
		}

		$.ajax({
			type: "POST",
			url:url,
			data:$('#hidForm').serialize(),
			async: false,
			success: function(data) {
				if(data.verifyState == 1){
					app.msg("操作成功！", 0);
					$("#searchBtn").click();
					layer.close(index);
				}else{
					app.msg("操作失败！", 1);
				}
			}
		});
	}
</script>
