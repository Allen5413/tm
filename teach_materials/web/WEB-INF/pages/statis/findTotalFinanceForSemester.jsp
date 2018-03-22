<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <tr class="am-primary">
    <th style="width:5%">序号</th>
    <th style="width:10%">学期</th>
    <th style="width:10%">订书总码洋</th>
    <th style="width:10%">交费总额</th>
    <th style="width:10%">欠费金额</th>
  </tr>
  <c:forEach var="json" items="${json.list}" varStatus="status">
    <tr>
        <td align="center">${status.index+1}</td>
        <td>${json.semester}</td>
        <td>${json.buy}</td>
        <td>${json.pay}</td>
        <td>
          <a href="javascript:void(0)" onclick="detail(${json.year}, ${json.quarter}, '${json.semester}')" style="color: #0092DC">${json.own}</a>
        </td>
    </tr>
  </c:forEach>
  <tr>
    <td></td>
    <td align="right">合计:</td>
    <td>${json.totalBuy}</td>
    <td>${json.totalPay}</td>
    <td style="color: #ff0000">${json.totalOwn}</td>
  </tr>
</table>
<script>
  function detail(year, quarter, semester){
    app.openOneBtnDialog('${pageContext.request.contextPath}/findTotalFinanceForSemester/findBySemesterForSpot.htm?year='+year+'&quarter='+quarter, semester+"中心欠款金额", 1000, 0.9);
  }
</script>