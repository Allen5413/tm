<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <%@ include file="/common/meta.jsp"%>
  <%@ include file="/common/taglibs.jsp"%>
</head>
<body>
<div class="main-content">
  <table class="table_slist" cellpadding="0" cellspacing="0" width="100%">
    <tr>
      <th style="width: 60px;">序号</th>
      <th style="width: 150px;">学院</th>
      <th style="width: 150px;">教材名称</th>
      <th style="width: 100px;">作者</th>
      <th style="width: 70px;">数量</th>
      <th style="width: 70px;">单价</th>
      <th style="width: 80px;">总价</th>
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
        <td>${result.count}</td>
        <td>${result.price}</td>
        <td>${result.totalPrice}</td>
      </tr>
    </c:forEach>
  </table>
</div>
</body>
</html>