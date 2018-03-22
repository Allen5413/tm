<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <%@ include file="/common/meta.jsp"%>
  <%@ include file="/common/taglibs.jsp"%>
</head>
<body>
<c:if test="${empty list}">
  全部成功
</c:if>
<table width="100%" border="1">
  <tr>
    <td width="5%">序号</td>
    <td width="25%">教材名称</td>
    <td width="20%">ISBN</td>
    <td width="20%">作者</td>
    <td width="10%">价格</td>
    <td width="10%">数量</td>
    <td width="10%">说明</td>
  </tr>
  <c:forEach var="json" items="${list}" varStatus="status">
    <tr>
      <td width="5%">${status.index+1}</td>
      <td width="25%">${json.tmName}</td>
      <td width="20%">${json.isbn}</td>
      <td width="20%">${json.author}</td>
      <td width="10%">${json.price}</td>
      <td width="10%">${json.count}</td>
      <td width="10%">${json.detail}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
