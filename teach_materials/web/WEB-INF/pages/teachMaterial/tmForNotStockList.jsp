<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<!DOCTYPE html>--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="main-content">--%>
  <%--<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findTeachMaterialNotStockPage/find.htm" method="post">--%>
    <%--<input type="hidden" id="page" name="page" value="${pageInfo.currentPage}"/>--%>
    <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
      <%--<tr>--%>
        <%--<th style="width: 5%;">序号</th>--%>
        <%--<th style="width: 10%;">渠道</th>--%>
        <%--<th style="width: 30%;">教材名称</th>--%>
        <%--<th style="width: 12%;">ISBN</th>--%>
        <%--<th style="width: 15%;">作者</th>--%>
        <%--<th style="width: 5%;">单价</th>--%>
        <%--<th style="width: 5%;">教材数量</th>--%>
        <%--<th style="width: 5%;">库存</th>--%>
        <%--<th style="width: 5%;">库存差</th>--%>
        <%--<th>操作</th>--%>
      <%--</tr>--%>
      <%--<c:if test="${empty pageInfo || empty pageInfo.pageResults}">--%>
        <%--<tr>--%>
          <%--<td colspan="20" align="center" style="color: red;">没有找到相关数据</td>--%>
        <%--</tr>--%>
      <%--</c:if>--%>
      <%--<c:forEach var="tm" items="${pageInfo.pageResults}" varStatus="status">--%>
        <%--<tr>--%>
          <%--<td align="center">${status.index+1}</td>--%>
          <%--<td>${tm.icName}</td>--%>
          <%--<td>${tm.name}</td>--%>
          <%--<td>${tm.isbn}</td>--%>
          <%--<td>${tm.author}</td>--%>
          <%--<td>${tm.price}</td>--%>
          <%--<td>${tm.orderCount}</td>--%>
          <%--<td>${tm.stock}</td>--%>
          <%--<td style="color: red">${tm.stockD}</td>--%>
          <%--<td>--%>
            <%--<c:if test="${!tm.isExistsOrder}">--%>
              <%--<a href="#" onclick="searchStock(${tm.id})" style="color: #0092DC">库存</a>&nbsp;&nbsp;--%>
            <%--</c:if>--%>
            <%--<a href="#" onclick="searchOrder(${tm.id})" style="color: #0092DC">查看订单</a>--%>
          <%--</td>--%>
        <%--</tr>--%>
      <%--</c:forEach>--%>
      <%--<%@ include file="/common/page.jsp"%>--%>
    <%--</table>--%>
  <%--</form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function searchStock(id){--%>
    <%--parent.openDialog('查看库存', 0.3, 0.6, '${pageContext.request.contextPath}/findtmStockBytmId/doFindtmStockBytmId.htm?tmId='+id);--%>
  <%--}--%>

  <%--function searchOrder(id){--%>
    <%--parent.openDialog('查看库存不足的订单', 0.8, 0.7, '${pageContext.request.contextPath}/findTeachMaterialNotStockPage/findOrder.htm?tmId='+id);--%>
  <%--}--%>
<%--</script>--%>

<p />
<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findTeachMaterialNotStockPage/find.htm" method="post">
  <input type="hidden" id="rows" name="rows" />
  <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
  <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
    <tr>
      <td colspan="999" style="background-color:#FFF">
        <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="down()"><span class="am-icon-download"></span> 下载</button>
      </td>
    </tr>
    <tr class="am-primary">
      <th style="width: 4%;">序号</th>
      <th style="width: 8%;">渠道</th>
      <th style="width: 22%;">教材名称</th>
      <th style="width: 15%;">出版社</th>
      <th style="width: 10%;">ISBN</th>
      <th style="width: 8%;">作者</th>
      <th style="width: 5%;">单价</th>
      <th style="width: 5%;">教材数量</th>
      <th style="width: 5%;">库存</th>
      <th style="width: 5%;">库存差</th>
      <th>操作</th>
    </tr>
    <c:if test="${empty pageInfo || empty pageInfo.pageResults}">
      <tr>
        <td colspan="20" align="center" style="color: red;">没有找到相关数据</td>
      </tr>
    </c:if>
    <c:forEach var="tm" items="${pageInfo.pageResults}" varStatus="status">
      <tr>
        <td align="center">${status.index+1}</td>
        <td>${tm.icName}</td>
        <td>${tm.name}</td>
        <td>${tm.pName}</td>
        <td>${tm.isbn}</td>
        <td>${tm.author}</td>
        <td>${tm.price}</td>
        <td>${tm.orderCount}</td>
        <td>${tm.stock}</td>
        <td style="color: red">${tm.stockD}</td>
        <td>
          <div class="am-btn-group">
            <c:if test="${!tm.isExistsOrder}">
              <button type="button" class="am-btn am-btn-primary am-round" onClick="searchStock(${tm.id})"><span class="am-icon-edit"></span> 库存</button>
            </c:if>
            <button type="button" id="searchBtn" class="am-btn am-btn-primary am-round btn-loading-example"
                    data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '<span class=am-icon-search></span> 查看订单'}"
                    onclick="searchOrder(${tm.id}, this)"><span class="am-icon-search"></span> 查看订单</button>
          </div>
        </td>
      </tr>
    </c:forEach>
  </table>
  <%@ include file="/common/page2.jsp"%>
</form>
<script>
  function searchStock(id){
    app.openOneBtnDialog('${pageContext.request.contextPath}/findtmStockBytmId/doFindtmStockBytmId.htm?tmId='+id, '查看库存', 600, 0.6);
  }

  function searchOrder(id, btnObj){
    $(btnObj).button('loading');
    setTimeout(function(){
      app.openOneBtnDialog('${pageContext.request.contextPath}/findTeachMaterialNotStockPage/findOrder.htm?tmId='+id, '查看库存不足的订单', 1400, 0.9);
      $(btnObj).button('reset');
    }, 100);
  }

  function down(){
    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/downTeachMaterialForNotStock/down.htm",
      data:$("#pageForm").serialize(),
      async: false,
      success: function(data) {
        open("${pageContext.request.contextPath}"+data);
      }
    });
  }
</script>
