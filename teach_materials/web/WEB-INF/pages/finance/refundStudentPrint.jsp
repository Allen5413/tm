<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html class="no-js">
<head>
  <%@ include file="/common/meta2.jsp"%>
  <%@ include file="/common/taglibs2.jsp"%>
</head>
<body style="background-color: #ffffff">
    <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
      <tr class="am-primary">
        <th style="width: 7%;">序号</th>
        <th style="width: 10%;">学号</th>
        <th style="width: 10%;">姓名</th>
        <th style="width: 30%;">专业</th>
        <th style="width: 13%;">层次</th>
        <th style="width: 13%;">退款金额</th>
        <th style="width: 18%;">退款说明</th>
      </tr>
      <c:if test="${empty list}">
        <tr>
          <td colspan="99" align="center" style="color: red;">没有找到相关数据</td>
        </tr>
      </c:if>
      <c:forEach var="map" items="${list}" varStatus="status">
        <tr>
          <td align="center">${status.index+1}</td>
          <td>${map.studentCode}</td>
          <td>${map.name}</td>
          <td>${map.specName}</td>
          <td>${map.levelName}</td>
          <td>${map.money}</td>
          <td>${map.refundDetail}</td>
        </tr>
      </c:forEach>
      <tr>
        <td colspan="5" style="text-align: right">合计：</td>
        <td colspan="2">${totalPrice}</td>
      </tr>
    </table>
</body>
</html>
