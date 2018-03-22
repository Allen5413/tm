<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="main-content">--%>
  <%--<span style="font-weight: bold; font-size: 18px;">${result.semesterStr}订单发出情况</span><br /><br />--%>
  <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="70%">--%>
    <%--<tr>--%>
      <%--<th style="width: 10%">序号</th>--%>
      <%--<th style="width: 20%">日期</th>--%>
      <%--<th style="width: 20%">小包数量</th>--%>
      <%--<th style="width: 20%">大包数量</th>--%>
      <%--<th style="width: 15%">订单金额</th>--%>
      <%--<th style="width: 15%">发出百分比</th>--%>
    <%--</tr>--%>
    <%--<c:if test="${empty result}">--%>
      <%--<tr>--%>
        <%--<td colspan="7" align="center" style="color: red;">没有找到相关数据</td>--%>
      <%--</tr>--%>
    <%--</c:if>--%>
    <%--<c:forEach var="json" items="${result.sendOrderList}" varStatus="status">--%>
      <%--<tr>--%>
        <%--<td align="center">${status.index+1}</td>--%>
        <%--<td>${json.date}</td>--%>
        <%--<td>${json.orderCount}</td>--%>
        <%--<td>${json.packageCount}</td>--%>
        <%--<td>${json.price}</td>--%>
        <%--<td>${json.percent}</td>--%>
      <%--</tr>--%>
    <%--</c:forEach>--%>
    <%--<tr>--%>
      <%--<td></td>--%>
      <%--<td>未发出</td>--%>
      <%--<td>${result.unSendOrderCount}</td>--%>
      <%--<td>${result.unSendOrderPackageCount}</td>--%>
      <%--<td>${result.unSendOrderPrice}</td>--%>
      <%--<td>${result.unSendOrderPercent}</td>--%>
    <%--</tr>--%>
  <%--</table>--%>
  <%--<br />--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>

<span style="font-weight: bold; font-size: 18px; color: #0b76ac;">${result.semesterStr}订单发出情况</span><br /><br />
<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <tr class="am-primary">
    <th style="width: 10%">序号</th>
    <th style="width: 20%">日期</th>
    <th style="width: 20%">小包数量</th>
    <th style="width: 20%">大包数量</th>
    <th style="width: 15%">订单金额</th>
    <th style="width: 15%">发出百分比</th>
  </tr>
  <c:if test="${empty result}">
    <tr>
      <td colspan="7" align="center" style="color: red;">没有找到相关数据</td>
    </tr>
  </c:if>
  <c:forEach var="json" items="${result.sendOrderList}" varStatus="status">
    <tr>
      <td align="center">${status.index+1}</td>
      <td>${json.date}</td>
      <td>${json.orderCount}</td>
      <td>${json.packageCount}</td>
      <td>${json.price}</td>
      <td>${json.percent}</td>
    </tr>
  </c:forEach>
  <tr>
    <td></td>
    <td>未发出</td>
    <td>${result.unSendOrderCount}</td>
    <td>${result.unSendOrderPackageCount}</td>
    <td>${result.unSendOrderPrice}</td>
    <td>${result.unSendOrderPercent}</td>
  </tr>
</table>