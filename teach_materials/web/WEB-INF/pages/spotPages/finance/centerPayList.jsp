<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
  <%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>--%>
<%--</head>--%>

<%--<body>--%>
	<%--<div class="main-content">--%>
		<%--<form id="pageForm" name="pageForm" class="payPageForm" action="${pageContext.request.contextPath}/centerPay/querySpotPay.htm" method="post">--%>
			<%--<input type="hidden" name="method" value="search"/>--%>
			<%--<input type="hidden" id="page" name="page" value="${pageInfo.currentPage}"/>--%>
			<%--<ul class="create_info_list">--%>
				 <%--<li>--%>
			        <%--<label class="lab_80">学习中心：</label>${spot.name}--%>
			       <%--<label class="lab_80">学期：</label>--%>
			        <%--<select id="semesterId" name="semesterId">--%>
			        	<%--<option value="">请选择</option>--%>
			          <%--<c:forEach var="semester" items="${semesterList}">--%>
			            <%--<option value="${semester.id}" <c:if test="${param.semesterId == semester.id}">selected="selected" </c:if> >${semester.semester}</option>--%>
			          <%--</c:forEach>--%>
       				 <%--</select>--%>
        			<%----%>
        			 <%--&nbsp;&nbsp;&nbsp;&nbsp;--%>
			        <%--<a class="com_btn_b" href="#" id="subPayBut" onclick="pageForm.submit();"><span>查&nbsp;询</span></a>&nbsp;&nbsp;--%>
			        <%--<a class="com_btn_b" href="#" id="resetBut" onclick="resetForm();"><span>重&nbsp;置</span></a>--%>
				 <%--</li>--%>
			<%--</ul>--%>
			<%--<c:if test="${param.method eq 'search'}">--%>
				<%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
					<%--<tr>--%>
					  <%--<th style="width: 30px">序号</th>--%>
					  <%--<th>学期</th>--%>
					  <%--<th>省中心</th>--%>
					  <%--<th>学习中心编号</th>--%>
					  <%--<th>学习中心名称</th>--%>
					  <%--<th>交费总额</th>--%>
					  <%--<th>学生购书总额</th>--%>
					  <%--<th>学生账户余额</th>--%>
					  <%--<th>学生账户欠款</th>--%>
					  <%--<th>操作</th>--%>
					  <%--<th>费用结清日期</th>--%>
					<%--</tr>--%>
					<%--<c:if test="${empty pageInfo || empty pageInfo.pageResults}">--%>
						<%--<tr>--%>
							<%--<td colspan="88" align="center" style="color: red;">没有找到相关数据</td>--%>
						<%--</tr>--%>
					<%--</c:if>--%>
					<%--<c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">--%>
					<%--<tr>--%>
						<%--<td align="center">${status.index+1}</td>--%>
						<%--<td>${json.semesterName}</td>--%>
						<%--<td>${json.pName}</td>--%>
						<%--<td>${json.spotCode}</td>--%>
						<%--<td>${json.spotName}</td>--%>
						<%--<td>${json.totalPay}</td>--%>
						<%--<td>${json.totalBuy}</td>--%>
						<%--<td>${json.acc}</td>--%>
						<%--<td>--%>
							<%--<c:if test="${json.ownIsRed == 1}">--%>
								<%--<font color="red">${json.own}</font>--%>
							<%--</c:if>--%>
							<%--<c:if test="${json.ownIsRed == 0}">--%>
								<%--${json.own}--%>
							<%--</c:if>--%>
						<%--</td>--%>
						<%--<c:if test="${json.showPay == 1 || json.showSur == 1|| json.showSur == 2}">--%>
							<%--<td>--%>
								<%--<c:if test="${isShowBtn == 1}">--%>
									<%--<button onclick="alterAddstu(0,'${json.spotCode}','${json.spotOwnId}');">交预付款</button>&nbsp;&nbsp;&nbsp;--%>
									<%--<button onclick="alterAddstu(1,'${json.spotCode}','${json.spotOwnId}','${json.own}',${json.semesterId});">交老生欠款</button>--%>
								<%--</c:if>--%>
							<%--</td>--%>
						<%--</c:if>--%>
						<%--<c:if test="${json.showSur == 0}">--%>
							<%--<td>已交费未审核</td>--%>
						<%--</c:if>--%>
						<%--<td>${json.clearTime}</td>--%>
					<%--</tr>--%>
					<%--</c:forEach>--%>
					<%--<tr>--%>
						<%--<td></td>--%>
						<%--<td></td>--%>
						<%--<td></td>--%>
						<%--<td></td>--%>
						<%--<td align="right">合计:</td>--%>
						<%--<td>${sumTotalPay}</td>--%>
						<%--<td>${sumTotalBuy}</td>--%>
						<%--<td>${sumAcc}</td>--%>
						<%--<td>${sumOwn}</td>--%>
						<%--<td></td>--%>
						<%--<td></td>--%>
					<%--</tr>--%>
					<%--<%@ include file="/common/page.jsp"%>--%>
				<%--</table>--%>
			<%--</c:if>--%>
		<%--</form>--%>
	<%--</div>--%>
	<%--<div id="dialogDiv_center"></div>--%>
	<%--<script type="text/javascript">--%>
		<%--function selectProv(){--%>
    <%--var provCode = $("#provCode").val();--%>
    <%--$.ajax({--%>
      <%--cache: true,--%>
      <%--type: "POST",--%>
      <%--url:"${pageContext.request.contextPath}/findSpotByProvCode/doFindSpotByProvCode.htm",--%>
      <%--data:{"provCode":provCode},--%>
      <%--async: false,--%>
      <%--success: function(result) {--%>
        <%--$("#spotCode option").remove();--%>
        <%--$("#spotCode").append("<option value=''>请选择</option>");--%>
        <%--if(typeof(result.spotArray) != "undefined"){--%>
          <%--for(var i=0; i<result.spotArray.length; i++){--%>
            <%--var spot = result.spotArray[i];--%>
            <%--$("#spotCode").append("<option value='"+spot.code+"'>["+spot.code+"]"+spot.name+"</option>");--%>
          <%--}--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>
  <%--}--%>
  <%----%>
  <%--function refreshPayForm(){--%>
	  <%--$("#pageForm").submit();--%>
  <%--}--%>
  <%----%>
  <%--function resetForm(){--%>
    <%--$("#provCode").val('');--%>
    <%--$("#spotCode").val('');--%>
    <%--$('#semesterId').val('');--%>
  <%--}--%>
  <%----%>
  <%--function alterAddstu(type,spotCode,spotOwnId,ownFee,semesterId){--%>
	  <%--if(0 ==  type){--%>
	  	<%--var url = "${pageContext.request.contextPath}/centerPay/toAddStu.htm?spotCode=" + spotCode + "&spotOwnId=" + spotOwnId;--%>
	    <%--parent.openPayDialog(url);--%>
	  <%--}else{--%>
		<%--if(ownFee == 0){--%>
			<%--alert("未欠费");--%>
			<%--return;--%>
		<%--}--%>
		<%--var url = "${pageContext.request.contextPath}/centerPay/toPayStu.htm?spotCode=" + spotCode + "&spotOwnId=" + spotOwnId + "&semesterId=" + semesterId;--%>
		<%--parent.openOwnDialog('交费', 0.6, 0.9, url);--%>
	  <%--}--%>
  <%--}--%>
	<%--</script>--%>
<%--</body>--%>



<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/centerPay/querySpotPay.htm" method="post">
	<input type="hidden" name="method" value="search"/>
	<input type="hidden" id="rows" name="rows" />
	<input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
	<label >学习中心：</label>${spot.name}&nbsp;&nbsp;&nbsp;&nbsp;

	<label >学期：</label>
	<select id="semesterId" name="semesterId" onchange="app.changeSelect(this)">
		<option value=""></option>
		<option value="null">全部</option>
		<c:forEach var="semester" items="${semesterList}">
			<option value="${semester.id}" <c:if test="${param.semesterId == semester.id}">selected="selected" </c:if> >${semester.semester}</option>
		</c:forEach>
	</select>
	&nbsp;&nbsp;&nbsp;&nbsp;

	<button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example"
			data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '查询超时'}"
			onclick="app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'), this)"><span class="am-icon-search"></span> 查询</button>
</form>
<p /><p />
<c:if test="${param.method eq 'search'}">
	<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
		<tr class="am-primary">
			<th style="width:4%">序号</th>
			<th style="width:6%">学期</th>
			<th style="width:6%">中心编号</th>
			<th style="width:20%">中心名称</th>
			<th style="width:7%">交费总额</th>
			<th style="width:7%">购书总额</th>
			<th style="width:6%">账户余额</th>
			<th style="width:7%">账户欠款</th>
			<th>操作</th>
			<th style="width:12%">费用结清日期</th>
		</tr>
		<c:if test="${empty pageInfo || empty pageInfo.pageResults}">
			<tr>
				<td colspan="88" align="center" style="color: red;">没有找到相关数据</td>
			</tr>
		</c:if>
		<c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">
			<tr>
				<td align="center">${status.index+1}</td>
				<td>${json.semesterName}</td>
				<td>${json.spotCode}</td>
				<td>${json.spotName}</td>
				<td>${json.totalPay}</td>
				<td>${json.totalBuy}</td>
				<td>${json.acc}</td>
				<td>
					<c:if test="${json.ownIsRed == 1}">
						<font color="red">${json.own}</font>
					</c:if>
					<c:if test="${json.ownIsRed == 0}">
						${json.own}
					</c:if>
				</td>
				<c:if test="${json.showPay == 1 || json.showSur == 1|| json.showSur == 2}">
					<td>
						<c:if test="${isShowBtn == 1}">
							<div class="am-btn-group">
								<button type="button" class="am-btn am-btn-primary am-round" onClick="alterAddstu(0,'${json.spotCode}','${json.spotOwnId}')"><span class="am-icon-plus"></span> 交预付款</button>
								<button type="button" class="am-btn am-btn-primary am-round" onClick="alterAddstu(1,'${json.spotCode}','${json.spotOwnId}','${json.own}',${json.semesterId})"><span class="am-icon-plus"></span> 交老生欠款</button>
							</div>
						</c:if>
					</td>
				</c:if>
				<c:if test="${json.showSur == 0}">
					<td>已交费未审核</td>
				</c:if>
				<td>${json.clearTime}</td>
			</tr>
		</c:forEach>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td align="right">合计:</td>
			<td>${sumTotalPay}</td>
			<td>${sumTotalBuy}</td>
			<td>${sumAcc}</td>
			<td>${sumOwn}</td>
			<td></td>
			<td></td>
		</tr>
	</table>
	<%@ include file="/common/page2.jsp"%>
</c:if>
<script>
	$("select").selected();
	function alterAddstu(type,spotCode,spotOwnId,ownFee,semesterId){
		if(0 ==  type){
			var url = "${pageContext.request.contextPath}/centerPay/toAddStu.htm?spotCode=" + spotCode + "&spotOwnId=" + spotOwnId;
			app.openNextBtnDialog(url, "交预付款", 1100, 0.9, function(index){
				var trArr = $(".cpAddStuTr");
				if(trArr.length == 0){
					app.msg("无记录", 1);
					return;
				}

				var trArr = $(".cpAddStuTr");
				for(var i = 0;i < trArr.length;i ++){
					if((trArr[i].id) != "title"){
						if(trArr[i].id != "sumTr"){
							var stuCode = $(trArr[i]).find("td").eq(1).text();
							var money = $(trArr[i]).find("td").eq(3).find("input").val();
							if(!vaild.vaildMoney(money)){
								app.msg("学号："+stuCode+", 金额错误，或者金额小于等于0", 1);
								return;
							}
						}
					}
				}
				var url = '${pageContext.request.contextPath}/centerPay/toNextPage.htm?'+ "spotCode=" + spotCode + "&spotOwnId=" + spotOwnId;
				app.openDialog(url, "中心确认交费", 700, 0.45, function(index2){
					var arrId=new Array();
					var i=0;
					$("[name = payImg]").each(function(){
						arrId[i] = $(this).attr("id");
						i++;
					});

					app.addForFile('${pageContext.request.contextPath}/centerPay/sureTemPay.htm', "doNextForm", index2);
					layer.close(index);
				});
			});
		}else{
			if(ownFee == 0){
				app.msg("未欠费", 0);
				return;
			}
			var url = "${pageContext.request.contextPath}/centerPay/toPayStu.htm?spotCode=" + spotCode + "&spotOwnId=" + spotOwnId + "&semesterId=" + semesterId;
			//parent.openOwnDialog('交费', 0.6, 0.9, url);

			app.openNextBtnDialog(url, "交老生欠款", 1100, 0.9, function(index){
				var trArr = $(".cpAddStuTr");
				if(trArr.length == 0){
					app.msg("无记录", 1);
					return;
				}

				var trArr = $(".cpAddStuTr");
				for(var i = 0;i < trArr.length;i ++){
					if((trArr[i].id) != "title"){
						if(trArr[i].id != "sumTr"){
							var stuCode = $(trArr[i]).find("td").eq(1).text();
							var money = $(trArr[i]).find("td").eq(3).find("input").val();
							if(!vaild.vaildMoney(money)){
								app.msg("学号："+stuCode+", 金额错误，或者金额小于等于0", 1);
								return;
							}
						}
					}
				}
				var url = '${pageContext.request.contextPath}/centerPay/toNextPage.htm?'+ "spotCode=" + spotCode + "&spotOwnId=" + spotOwnId;
				app.openDialog(url, "中心确认交费", 700, 0.45, function(index2){
					var arrId=new Array();
					var i=0;
					$("[name = payImg]").each(function(){
						arrId[i] = $(this).attr("id");
						i++;
					});

					app.addForFile('${pageContext.request.contextPath}/centerPay/sureTemPay.htm', "doNextForm", index2);
					layer.close(index);
				});
			});
		}
	}
</script>

