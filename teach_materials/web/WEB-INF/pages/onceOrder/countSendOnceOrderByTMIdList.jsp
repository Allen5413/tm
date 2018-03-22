<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<div class="main-content" style="overflow-y:scroll; height: 95%;">--%>
  <%--<ul class="create_info_list">--%>
    <%--<li>--%>
      <%--<label class="lab_80">教材名称：</label>${teachMaterial.name}--%>
      <%--<label class="lab_80">ISBN：</label>${teachMaterial.isbn}--%>
      <%--<label class="lab_80">作者：</label>${teachMaterial.author}--%>
    <%--</li>--%>
  <%--</ul>--%>
  <%--<form id="addForm" name="addForm" method="post">--%>
    <%--<input type="hidden" id="orderCode" name="orderCode" value="${param.orderCode}" />--%>
    <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
      <%--<tr>--%>
        <%--<th style="width: 5%;">序号</th>--%>
        <%--<th style="width: 12%;">订单号</th>--%>
        <%--<th style="width: 12%;">学习中心编号</th>--%>
        <%--<th style="width: 40%;">学习中心名称</th>--%>
        <%--<th style="width: 10%;">学号</th>--%>
        <%--<th style="width: 9%;">姓名</th>--%>
        <%--<th style="width: 7%;">现价</th>--%>
        <%--<th style="width: 5%;">数量</th>--%>
      <%--</tr>--%>
      <%--<c:if test="${empty jsonArray}">--%>
        <%--<tr>--%>
          <%--<td colspan="20" align="center" style="color: #ff000c;">没有找到相关数据</td>--%>
        <%--</tr>--%>
      <%--</c:if>--%>
      <%--<c:forEach var="json" items="${jsonArray}" varStatus="status">--%>
        <%--<tr>--%>
          <%--<td align="center">${status.index+1}</td>--%>
          <%--<td>${json.orderCode}</td>--%>
          <%--<td>${json.spotCode}</td>--%>
          <%--<td>${json.spotName}</td>--%>
          <%--<td>${json.code}</td>--%>
          <%--<td>${json.name}</td>--%>
          <%--<td>${json.price}</td>--%>
          <%--<td>${json.count}</td>--%>
        <%--</tr>--%>
      <%--</c:forEach>--%>
    <%--</table>--%>
  <%--</form>--%>
<%--</div>--%>




<div class="admin-content">
  <div class="am-tabs" data-am-tabs>
    <div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
      <div class="am-panel-hd am-cf" data-am-collapse="{target: '#tm'}">教材信息<span class="am-icon-chevron-down am-fr"></span></div>
      <div id="tm" class="am-in">
        <div class="am-g am-margin-top">
          <div class="am-u-sm-2 am-text-right"><label >教材名称：</label></div>
          <div class="am-u-sm-8" style="float:left">${teachMaterial.name}</div>
        </div>

        <div class="am-g am-margin-top">
          <div class="am-u-sm-2 am-text-right"><label >ISBN：</label></div>
          <div class="am-u-sm-8" style="float:left">${teachMaterial.isbn}</div>
        </div>

        <div class="am-g am-margin-top">
          <div class="am-u-sm-2 am-text-right"><label >作者：</label></div>
          <div class="am-u-sm-8" style="float:left">${teachMaterial.author}</div>
        </div>
      </div>
    </div>

    <div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
      <div class="am-panel-hd am-cf" data-am-collapse="{target: '#orderTM'}">订单明细<span class="am-icon-chevron-down am-fr"></span></div>
      <div id="orderTM" class="am-in">
        <form id="addForm" name="addForm" method="post">
          <input type="hidden" id="orderCode" name="orderCode" value="${param.orderCode}" />
          <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
            <tr class="am-primary">
              <th style="width: 5%;">序号</th>
              <th style="width: 12%;">订单号</th>
              <th style="width: 12%;">学习中心编号</th>
              <th style="width: 40%;">学习中心名称</th>
              <th style="width: 10%;">学号</th>
              <th style="width: 9%;">姓名</th>
              <th style="width: 7%;">现价</th>
              <th style="width: 5%;">数量</th>
            </tr>
            <c:if test="${empty jsonArray}">
              <tr>
                <td colspan="20" align="center" style="color: #ff000c;">没有找到相关数据</td>
              </tr>
            </c:if>
            <c:forEach var="json" items="${jsonArray}" varStatus="status">
              <tr>
                <td align="center">${status.index+1}</td>
                <td>${json.orderCode}</td>
                <td>${json.spotCode}</td>
                <td>${json.spotName}</td>
                <td>${json.code}</td>
                <td>${json.name}</td>
                <td>${json.price}</td>
                <td>${json.count}</td>
              </tr>
            </c:forEach>
          </table>
        </form>
      </div>
    </div>
  </div>
</div>
