<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<div class="main-content" style="overflow-y:scroll; height: 95%;">--%>
  <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
    <%--<tr>--%>
      <%--<th style="width: 50%">学习中心</th>--%>
      <%--<th style="width: 15%">小包数量</th>--%>
      <%--<th style="width: 15%">大包数量</th>--%>
      <%--<th style="width: 20%">订单金额</th>--%>
    <%--</tr>--%>
    <%--<c:if test="${empty result}">--%>
      <%--<tr>--%>
        <%--<td colspan="7" align="center" style="color: red;">没有找到相关数据</td>--%>
      <%--</tr>--%>
    <%--</c:if>--%>
    <%--<c:forEach var="json" items="${result}" varStatus="status">--%>
      <%--<tr>--%>
        <%--<td>(${json.spotCode})${json.spotName}</td>--%>
        <%--<td>${json.orderCount}</td>--%>
        <%--<td>${json.packageCount}</td>--%>
        <%--<td>${json.price}</td>--%>
      <%--</tr>--%>
    <%--</c:forEach>--%>
  <%--</table>--%>
<%--</div>--%>

<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <th style="width: 50%">学习中心</th>
  <th style="width: 15%">小包数量</th>
  <th style="width: 15%">大包数量</th>
  <th style="width: 20%">订单金额</th>
  <c:if test="${empty result}">
    <tr>
      <td colspan="10" align="center" style="color: red;">没有找到相关数据</td>
    </tr>
  </c:if>
  <c:forEach var="json" items="${result}" varStatus="status">
    <tr>
      <td>(${json.spotCode})${json.spotName}</td>
      <td>${json.orderCount}</td>
      <td>${json.packageCount}</td>
      <td>${json.price}</td>
    </tr>
  </c:forEach>
</table>
