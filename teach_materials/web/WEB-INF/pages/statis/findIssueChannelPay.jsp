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
  <%--<span style="font-weight: bold; font-size: 18px;">发行商应付款</span><br /><br />--%>
  <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="70%">--%>
    <%--<tr>--%>
      <%--<th style="width: 5%">序号</th>--%>
      <%--<th style="width: 10%">学期</th>--%>
      <%--<th style="width: 20%">发行商</th>--%>
      <%--<th style="width: 13%">发书总码洋</th>--%>
      <%--<th style="width: 13%">统订</th>--%>
      <%--<th style="width: 13%">自编</th>--%>
      <%--<th style="width: 13%">自考</th>--%>
      <%--<th style="width: 13%">应付款</th>--%>
    <%--</tr>--%>
    <%--<c:if test="${empty result}">--%>
      <%--<tr>--%>
        <%--<td colspan="10" align="center" style="color: red;">没有找到相关数据</td>--%>
      <%--</tr>--%>
    <%--</c:if>--%>
    <%--<c:forEach var="json" items="${result}" varStatus="status">--%>
      <%--<tr>--%>
        <%--<td align="center">${status.index+1}</td>--%>
        <%--<td>${json.semester}</td>--%>
        <%--<td>${json.name}</td>--%>
        <%--<td>--%>
          <%--<a href="#" onclick="searchDetail(${json.semesterId}, ${json.icId}, '')" style="color: #0092DC">${json.totalPrice}</a>--%>
        <%--</td>--%>
        <%--<td>--%>
          <%--<a href="#" onclick="searchDetail(${json.semesterId}, ${json.icId}, '4')" style="color: #0092DC">${json.td}</a>--%>
        <%--</td>--%>
        <%--<td>--%>
          <%--<a href="#" onclick="searchDetail(${json.semesterId}, ${json.icId}, '3')" style="color: #0092DC">${json.zb}</a>--%>
        <%--</td>--%>
        <%--<td>--%>
          <%--<a href="#" onclick="searchDetail(${json.semesterId}, ${json.icId}, '1')" style="color: #0092DC">${json.zk}</a>--%>
        <%--</td>--%>
        <%--<td>${json.pay}</td>--%>
      <%--</tr>--%>
    <%--</c:forEach>--%>
  <%--</table>--%>
  <%--<br />--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function searchDetail(semesterId, icId, type){--%>
    <%--open('${pageContext.request.contextPath}/findIssueChannelPayDetail/find.htm?semesterId='+semesterId+'&icId='+icId+'&type='+type);--%>
  <%--}--%>
<%--</script>--%>

<span style="font-weight: bold; font-size: 18px;">发行商应付款</span><br /><br />
<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <th style="width: 5%">序号</th>
  <th style="width: 10%">学期</th>
  <th style="width: 20%">发行商</th>
  <th style="width: 13%">发书总码洋</th>
  <th style="width: 13%">统订</th>
  <th style="width: 13%">自编</th>
  <th style="width: 13%">自考</th>
  <th style="width: 13%">应付款</th>
  <c:if test="${empty result}">
    <tr>
      <td colspan="10" align="center" style="color: red;">没有找到相关数据</td>
    </tr>
  </c:if>
  <c:forEach var="json" items="${result}" varStatus="status">
  <tr>
    <td align="center">${status.index+1}</td>
    <td>${json.semester}</td>
    <td>${json.name}</td>
    <td>
      <a href="#" onclick="searchDetail(${json.semesterId}, ${json.icId}, '')" style="color: #0092DC">${json.totalPrice}</a>
    </td>
    <td>
      <a href="#" onclick="searchDetail(${json.semesterId}, ${json.icId}, '4')" style="color: #0092DC">${json.td}</a>
    </td>
    <td>
      <a href="#" onclick="searchDetail(${json.semesterId}, ${json.icId}, '3')" style="color: #0092DC">${json.zb}</a>
    </td>
    <td>
      <a href="#" onclick="searchDetail(${json.semesterId}, ${json.icId}, '1')" style="color: #0092DC">${json.zk}</a>
    </td>
    <td>${json.pay}</td>
  </tr>
  </c:forEach>
</table>
<script>
  function searchDetail(semesterId, icId, type){
    open('${pageContext.request.contextPath}/findIssueChannelPayDetail/find.htm?semesterId='+semesterId+'&icId='+icId+'&type='+type);
  }
</script>