<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="main-content">--%>
  <%--<span style="font-weight: bold; font-size: 18px;">${title}</span>--%>
  <%--<a class="com_btn_b" href="#" onclick="down();"><span>下载</span></a>--%>
  <%--<br /><br />--%>
  <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
    <%--<tr>--%>
      <%--<th style="width: 5%">序号</th>--%>
      <%--<th style="width: 23%">教材名称</th>--%>
      <%--<th style="width: 20%">isbn</th>--%>
      <%--<th style="width: 13%">作者</th>--%>
      <%--<th style="width: 13%">单价</th>--%>
      <%--<th style="width: 13%">数量</th>--%>
      <%--<th style="width: 13%">总价</th>--%>
    <%--</tr>--%>
    <%--<c:if test="${empty result}">--%>
      <%--<tr>--%>
        <%--<td colspan="10" align="center" style="color: red;">没有找到相关数据</td>--%>
      <%--</tr>--%>
    <%--</c:if>--%>
    <%--<c:forEach var="json" items="${result.data}" varStatus="status">--%>
      <%--<tr>--%>
        <%--<td align="center">${status.index+1}</td>--%>
        <%--<td>${json.name}</td>--%>
        <%--<td>${json.isbn}</td>--%>
        <%--<td>${json.author}</td>--%>
        <%--<td>${json.price}</td>--%>
        <%--<td>${json.count}</td>--%>
        <%--<td>${json.totalPrice}</td>--%>
      <%--</tr>--%>
    <%--</c:forEach>--%>
    <%--<tr>--%>
      <%--<td align="center"></td>--%>
      <%--<td></td>--%>
      <%--<td></td>--%>
      <%--<td></td>--%>
      <%--<td></td>--%>
      <%--<td align="right">合计：</td>--%>
      <%--<td>${result.sumPrice}</td>--%>
    <%--</tr>--%>
  <%--</table>--%>
  <%--<br />--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function down(){--%>
    <%--$.ajax({--%>
      <%--cache: true,--%>
      <%--type: "POST",--%>
      <%--url:"${pageContext.request.contextPath}/findIssueChannelPayDetail/downDetail.htm?semesterId=${param.semesterId}&icId=${param.icId}&type=${param.type}",--%>
      <%--data:{},--%>
      <%--async: false,--%>
      <%--success: function(data) {--%>
        <%--open("${pageContext.request.contextPath}"+data);--%>
      <%--}--%>
    <%--});--%>
  <%--}--%>
<%--</script>--%>

<!doctype html>
<html class="no-js">
<head>
  <%@ include file="/common/meta2.jsp"%>
  <%@ include file="/common/taglibs2.jsp"%>
</head>
<body>
<span style="font-weight: bold; font-size: 18px;">${title}</span><br /><br />
<div class="admin-content">
  <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
    <tr>
      <td colspan="999" style="background-color:#FFF">
        <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="add()"><span class="am-icon-download"></span> 下载</button>
      </td>
    </tr>
    <th style="width: 5%">序号</th>
    <th style="width: 23%">教材名称</th>
    <th style="width: 20%">isbn</th>
    <th style="width: 13%">作者</th>
    <th style="width: 13%">单价</th>
    <th style="width: 13%">数量</th>
    <th style="width: 13%">总价</th>
    <c:if test="${empty result}">
      <tr>
        <td colspan="10" align="center" style="color: red;">没有找到相关数据</td>
      </tr>
    </c:if>
    <c:forEach var="json" items="${result.data}" varStatus="status">
      <tr>
        <td align="center">${status.index+1}</td>
        <td>${json.name}</td>
        <td>${json.isbn}</td>
        <td>${json.author}</td>
        <td>${json.price}</td>
        <td>${json.count}</td>
        <td>${json.totalPrice}</td>
      </tr>
    </c:forEach>
    <tr>
      <td align="center"></td>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
      <td align="right">合计：</td>
      <td>${result.sumPrice}</td>
    </tr>
  </table>
</div>
</body>
</html>
<script>
  function down(){
    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/findIssueChannelPayDetail/downDetail.htm?semesterId=${param.semesterId}&icId=${param.icId}&type=${param.type}",
      data:{},
      async: false,
      success: function(data) {
        open("${pageContext.request.contextPath}"+data);
      }
    });
  }
</script>