<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="main-content" style="overflow-y:scroll; height: 95%;">
  <label>教材名称：</label>${teachMaterial.name}&nbsp;&nbsp;&nbsp;&nbsp;
  <label>ISBN：</label>${teachMaterial.isbn}&nbsp;&nbsp;&nbsp;&nbsp;
  <label>作者：</label>${teachMaterial.author}
  <form id="addForm" name="addForm" method="post">
    <input type="hidden" id="orderCode" name="orderCode" value="${param.orderCode}" />
    <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
      <tr class="am-primary">
        <th style="width: 5%;">序号</th>
        <th style="width: 12%;">订单号</th>
        <th style="width: 12%;">学习中心编号</th>
        <th style="width: 40%;">学习中心名称</th>
        <th style="width: 7%;">现价</th>
        <th style="width: 5%;">数量</th>
      </tr>
      <c:if test="${empty jsonArray}">
        <tr>
          <td colspan="20" align="center" style="color: #ff000c;">没有找到相关数据</td>
        </tr>
      </c:if>
      <c:forEach var="json" items="${jsonArray}" varStatus="status">
        <tr>
          <td align="center">${status.index+1}</td>
          <td>${json.orderCode}</td>
          <td>${json.spotCode}</td>
          <td>${json.spotName}</td>
          <td>${json.price}</td>
          <td>${json.count}</td>
        </tr>
      </c:forEach>
    </table>
  </form>
</div>
