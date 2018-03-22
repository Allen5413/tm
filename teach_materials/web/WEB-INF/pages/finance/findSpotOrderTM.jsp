<%--<%@ page language="java" contentType="text/html; charset=utf-8"--%>
         <%--pageEncoding="utf-8"%>--%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="main-content">--%>
  <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
    <%--<tr>--%>
      <%--<th style="width: 3%">序号</th>--%>
      <%--<th style="width: 35%">邮寄地址</th>--%>
      <%--<th style="width: 5%">收件人</th>--%>
      <%--<th style="width: 17%">教材名称</th>--%>
      <%--<th style="width: 8%">isbn</th>--%>
      <%--<th style="width: 17%">作者</th>--%>
      <%--<th style="width: 5%">单价</th>--%>
      <%--<th style="width: 5%">数量</th>--%>
      <%--<th style="width: 7%">总价</th>--%>
    <%--</tr>--%>
    <%--<c:if test="${empty data}">--%>
      <%--<tr>--%>
        <%--<td colspan="10" align="center" style="color: red;">没有找到相关数据</td>--%>
      <%--</tr>--%>
    <%--</c:if>--%>
    <%--<c:forEach var="json" items="${data}" varStatus="status">--%>
      <%--<tr>--%>
        <%--<td align="center">${status.index+1}</td>--%>
        <%--<td>${json.address}</td>--%>
        <%--<td>${json.adminName}</td>--%>
        <%--<td>${json.name}</td>--%>
        <%--<td>${json.isbn}</td>--%>
        <%--<td>${json.author}</td>--%>
        <%--<td>${json.price}</td>--%>
        <%--<td>${json.count}</td>--%>
        <%--<td>${json.totalPrice}</td>--%>
      <%--</tr>--%>
    <%--</c:forEach>--%>
    <%--<tr>--%>
      <%--<td></td>--%>
      <%--<td></td>--%>
      <%--<td></td>--%>
      <%--<td></td>--%>
      <%--<td></td>--%>
      <%--<td></td>--%>
      <%--<td></td>--%>
      <%--<td align="right">合计：</td>--%>
      <%--<td>${sumTotalPrice}</td>--%>
    <%--</tr>--%>
  <%--</table>--%>
  <%--<br />--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>


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
        <th style="width: 3%">序号</th>
        <th style="width: 35%">邮寄地址</th>
        <th style="width: 5%">收件人</th>
        <th style="width: 17%">教材名称</th>
        <th style="width: 8%">isbn</th>
        <th style="width: 17%">作者</th>
        <th style="width: 5%">单价</th>
        <th style="width: 5%">数量</th>
        <th style="width: 7%">总价</th>
      </tr>
      <c:if test="${empty data}">
        <tr>
          <td colspan="10" align="center" style="color: red;">没有找到相关数据</td>
        </tr>
      </c:if>
      <c:forEach var="json" items="${data}" varStatus="status">
        <tr>
          <td align="center">${status.index+1}</td>
          <td>${json.address}</td>
          <td>${json.adminName}</td>
          <td>${json.name}</td>
          <td>${json.isbn}</td>
          <td>${json.author}</td>
          <td>${json.price}</td>
          <td>${json.count}</td>
          <td>${json.totalPrice}</td>
        </tr>
      </c:forEach>
      <tr>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td align="right">合计：</td>
        <td>${sumTotalPrice}</td>
      </tr>
    </table>
  </div>
</div>
</body>
</html>