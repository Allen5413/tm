<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html class="no-js">
<head>
  <%@ include file="/common/meta2.jsp"%>
  <%@ include file="/common/taglibs2.jsp"%>
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->

<div class="admin-content">
  <div class="am-tabs" data-am-tabs>
    <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
      <tr class="am-primary">
        <th style="width: 10%">序号</th>
        <th style="width: 10%">学号</th>
        <th style="width: 10%">姓名</th>
        <th style="width: 10%">层次</th>
        <th style="width: 30%">专业</th>
        <th style="width: 10%">订单总码洋</th>
        <c:if test="${param.flag > 0}">
          <th style="width: 10%">确认总码洋</th>
          <th style="width: 10%">码洋确认百分比</th>
        </c:if>
      </tr>
      <c:if test="${empty resultData}">
        <tr>
          <td colspan="10" align="center" style="color: red;">没有找到相关数据</td>
        </tr>
      </c:if>
      <c:forEach var="json" items="${resultData}" varStatus="status">
        <tr>
          <td align="center">${status.index+1}</td>
          <td>${json.code}</td>
          <td>${json.name}</td>
          <td>${json.level}</td>
          <td>${json.spec}</td>
          <td>${json.totalPrice}</td>
          <c:if test="${param.flag > 0}">
            <td>${json.price}</td>
            <td>${json.pricePercent}</td>
          </c:if>
        </tr>
      </c:forEach>
      <tr style="color:red;">
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td align="right">合计：</td>
        <td>${sumTotalPrice}</td>
        <c:if test="${param.flag > 0}">
          <td>${sumPrice}</td>
          <td>${sumTotalPricePercent}</td>
        </c:if>
      </tr>
    </table>
  </div>
</div>
</body>
</html>