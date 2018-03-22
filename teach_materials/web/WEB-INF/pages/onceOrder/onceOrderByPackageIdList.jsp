
<%--<!DOCTYPE html>--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
  <%--<div class="main-content">--%>
    <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
      <%--<tr>--%>
        <%--<th style="width: 100px;">订单号</th>--%>
        <%--<th style="width: 100px;">学号</th>--%>
        <%--<th style="width: 80px;">姓名</th>--%>
        <%--<th style="width: 100px;">专业</th>--%>
        <%--<th style="width: 60px;">层次</th>--%>
        <%--<th style="width: 60px;">教材数量</th>--%>
        <%--<th style="width: 60px;">金额</th>--%>
        <%--<th style="width: 60px;">状态</th>--%>
        <%--<th style="width: 60px;">操作人</th>--%>
        <%--<th style="width: 80px;">操作时间</th>--%>
      <%--</tr>--%>
      <%--<c:if test="${empty pageInfo || empty pageInfo.pageResults}">--%>
        <%--<tr>--%>
          <%--<td colspan="20" align="center" style="color: red;">没有找到相关数据</td>--%>
        <%--</tr>--%>
      <%--</c:if>--%>
      <%--<c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">--%>
        <%--<tr>--%>
          <%--<td>${json.orderCode}</td>--%>
          <%--<td>${json.studentCode}</td>--%>
          <%--<td>${json.name}</td>--%>
          <%--<td>${json.specName}</td>--%>
          <%--<td>${json.levelName}</td>--%>
          <%--<td>${json.tmCount}</td>--%>
          <%--<td>${json.totalPrice}</td>--%>
          <%--<td>--%>
            <%--<c:choose>--%>
              <%--<c:when test="${json.state == 0}">未确认</c:when>--%>
              <%--<c:when test="${json.state == 1}">已确认</c:when>--%>
              <%--<c:when test="${json.state == 2}">处理中</c:when>--%>
              <%--<c:when test="${json.state == 3}">分拣中</c:when>--%>
              <%--<c:when test="${json.state == 4}">已打包</c:when>--%>
              <%--<c:when test="${json.state == 5}">已发出</c:when>--%>
              <%--<c:when test="${json.state == 6}">已签收</c:when>--%>
              <%--<c:otherwise>未知</c:otherwise>--%>
            <%--</c:choose>--%>
          <%--</td>--%>
          <%--<td>${json.operator}</td>--%>
          <%--<td>${json.operateTime}</td>--%>
        <%--</tr>--%>
      <%--</c:forEach>--%>
    <%--</table>--%>
  <%--</div>--%>
<%--</body>--%>
<%--</html>--%>


<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <tr class="am-primary">
    <th style="width: 100px;">订单号</th>
    <th style="width: 100px;">学号</th>
    <th style="width: 80px;">姓名</th>
    <th style="width: 100px;">专业</th>
    <th style="width: 60px;">层次</th>
    <th style="width: 60px;">教材数量</th>
    <th style="width: 60px;">金额</th>
    <th style="width: 60px;">状态</th>
    <th style="width: 60px;">操作人</th>
    <th style="width: 80px;">操作时间</th>
  </tr>
  <c:if test="${empty pageInfo || empty pageInfo.pageResults}">
    <tr>
      <td colspan="20" align="center" style="color: red;">没有找到相关数据</td>
    </tr>
  </c:if>
  <c:if test="${empty pageInfo || empty pageInfo.pageResults}">
    <tr>
      <td colspan="20" align="center" style="color: red;">没有找到相关数据</td>
    </tr>
  </c:if>
  <c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">
    <tr>
      <td>${json.orderCode}</td>
      <td>${json.studentCode}</td>
      <td>${json.name}</td>
      <td>${json.specName}</td>
      <td>${json.levelName}</td>
      <td>${json.tmCount}</td>
      <td>${json.totalPrice}</td>
      <td>
        <c:choose>
          <c:when test="${json.state == 0}">未确认</c:when>
          <c:when test="${json.state == 1}">已确认</c:when>
          <c:when test="${json.state == 2}">处理中</c:when>
          <c:when test="${json.state == 3}">分拣中</c:when>
          <c:when test="${json.state == 4}">已打包</c:when>
          <c:when test="${json.state == 5}">已发出</c:when>
          <c:when test="${json.state == 6}">已签收</c:when>
          <c:otherwise>未知</c:otherwise>
        </c:choose>
      </td>
      <td>${json.operator}</td>
      <td>${json.operateTime}</td>
    </tr>
  </c:forEach>
</table>