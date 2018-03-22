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
      <%--<th style="width: 60px;">序号</th>--%>
      <%--<th style="width: 150px;">学院</th>--%>
      <%--<th style="width: 150px;">教材名称</th>--%>
      <%--<th style="width: 100px;">作者</th>--%>
      <%--<th style="width: 150px;">出版社</th>--%>
      <%--<th style="width: 150px;">ISBN</th>--%>
      <%--<th style="width: 70px;">价格</th>--%>
      <%--<th style="width: 70px;">数量</th>--%>
      <%--<th style="width: 80px;">入库数量</th>--%>
    <%--</tr>--%>
    <%--<c:if test="${empty resultJson}">--%>
      <%--<tr>--%>
        <%--<td colspan="20" align="center" style="color: #ff000c;">没有找到相关数据</td>--%>
      <%--</tr>--%>
    <%--</c:if>--%>
    <%--<c:forEach var="result" items="${resultJson}" varStatus="status">--%>
      <%--<tr>--%>
        <%--<td>${status.index+1}</td>--%>
        <%--<td>${result.deptName}</td>--%>
        <%--<td>${result.tmName}</td>--%>
        <%--<td>${result.author}</td>--%>
        <%--<td>${result.pressName}</td>--%>
        <%--<td>${result.isbn}</td>--%>
        <%--<td>${result.price}</td>--%>
        <%--<td>${result.tmCount}</td>--%>
        <%--<td>${result.storageCount}</td>--%>
      <%--</tr>--%>
    <%--</c:forEach>--%>
  <%--</table>--%>
  <%--<br />--%>
  <%--<a class="com_btn_b" href="#" onclick="print()"><span>打&nbsp;印</span></a>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--$(function(){--%>
    <%--var height = getWindowHeightSize();--%>
    <%--$(".main-content").height(height * 0.9);--%>
  <%--});--%>
<%--</script>--%>


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
        <th style="width: 60px;">序号</th>
        <th style="width: 150px;">学院</th>
        <th style="width: 150px;">教材名称</th>
        <th style="width: 100px;">作者</th>
        <th style="width: 150px;">出版社</th>
        <th style="width: 150px;">ISBN</th>
        <th style="width: 70px;">价格</th>
        <th style="width: 70px;">数量</th>
        <th style="width: 80px;">入库数量</th>
      </tr>
      <c:if test="${empty resultJson}">
        <tr>
          <td colspan="20" align="center" style="color: #ff000c;">没有找到相关数据</td>
        </tr>
      </c:if>
      <c:forEach var="result" items="${resultJson}" varStatus="status">
        <tr>
          <td>${status.index+1}</td>
          <td>${result.deptName}</td>
          <td>${result.tmName}</td>
          <td>${result.author}</td>
          <td>${result.pressName}</td>
          <td>${result.isbn}</td>
          <td>${result.price}</td>
          <td>${result.tmCount}</td>
          <td>${result.storageCount}</td>
        </tr>
      </c:forEach>
    </table>
  </div>
</div>
</body>
</html>