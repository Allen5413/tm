<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <th style="width: 80%">学习中心</th>
  <th style="width: 20%">欠款金额</th>
  <c:if test="${empty list}">
    <tr>
      <td colspan="10" align="center" style="color: red;">没有找到相关数据</td>
    </tr>
  </c:if>
  <c:forEach var="json" items="${list}" varStatus="status">
    <tr>
      <td>(${json.code})${json.name}</td>
      <td>${json.own}</td>
    </tr>
  </c:forEach>
</table>
