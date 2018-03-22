<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<div class="main-content" style="overflow-y:scroll; height: 650px;">--%>
  <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
    <%--<tr>--%>
      <%--<th style="width: 50px;">序号</th>--%>
      <%--<th style="width: 150px;">调拨前的渠道</th>--%>
      <%--<th style="width: 150px;">调拨后的渠道</th>--%>
      <%--<th style="width: 80px;">调拨库存量</th>--%>
      <%--<th style="width: 80px;">操作人</th>--%>
      <%--<th>操作时间</th>--%>
    <%--</tr>--%>
    <%--<c:if test="${empty jsonArray}">--%>
      <%--<tr>--%>
        <%--<td colspan="20" align="center" style="color: #ff000c;">没有找到相关数据</td>--%>
      <%--</tr>--%>
    <%--</c:if>--%>
    <%--<c:forEach var="json" items="${jsonArray}" varStatus="status">--%>
      <%--<tr>--%>
        <%--<td align="center">${status.index+1}</td>--%>
        <%--<td>${json.afterChannelName}</td>--%>
        <%--<td>${json.beforeChannelName}</td>--%>
        <%--<td>${json.stock}</td>--%>
        <%--<td>${json.operator}</td>--%>
        <%--<td>${json.operateTime}</td>--%>
      <%--</tr>--%>
    <%--</c:forEach>--%>
  <%--</table>--%>
<%--</div>--%>
<%--<script>--%>
  <%--$(function(){--%>
    <%--var height = getWindowHeightSize();--%>
    <%--$(".main-content").height(height * 0.65);--%>
  <%--});--%>
<%--</script>--%>

<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <tr class="am-primary">
    <th style="width: 10%;">序号</th>
    <th style="width: 20%;">调拨前的渠道</th>
    <th style="width: 20%;">调拨后的渠道</th>
    <th style="width: 10%;">调拨库存量</th>
    <th style="width: 10%;">操作人</th>
    <th>操作时间</th>
  </tr>
  <c:if test="${empty jsonArray}">
    <tr>
      <td colspan="20" align="center" style="color: #ff000c;">没有找到相关数据</td>
    </tr>
  </c:if>
  <c:forEach var="json" items="${jsonArray}" varStatus="status">
    <tr>
      <td align="center">${status.index+1}</td>
      <td>${json.afterChannelName}</td>
      <td>${json.beforeChannelName}</td>
      <td>${json.stock}</td>
      <td>${json.operator}</td>
      <td>${json.operateTime}</td>
    </tr>
  </c:forEach>
</table>