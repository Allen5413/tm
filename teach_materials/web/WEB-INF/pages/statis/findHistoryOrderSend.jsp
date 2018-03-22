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
  <%--<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findHistoryOrderSend/find.htm">--%>
    <%--<input type="hidden" name="doSearch" value="1"/>--%>
    <%--<span style="font-weight: bold; font-size: 18px;">历史学期订单发出情况</span><br /><br />--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<label class="lab_80" style="width: 100px; text-align: left"><input type="checkbox" id="isSpot" name="isSpot" value="1" <c:if test="${!empty param.isSpot}">checked="true"</c:if> />&nbsp;按学习中心</label>--%>
        <%--&nbsp;&nbsp;--%>
        <%--<a class="com_btn_b" href="#" id="subBut" onclick="pageForm.submit();"><span>查&nbsp;询</span></a>--%>
      <%--</li>--%>
    <%--</ul>--%>
    <%--<c:if test="${param.doSearch eq '1'}">--%>
      <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="60%">--%>
        <%--<tr>--%>
          <%--<c:if test="${empty param.isSpot}">--%>
            <%--<th style="width: 30%">学期</th>--%>
            <%--<th style="width: 20%">小包数量</th>--%>
            <%--<th style="width: 20%">大包数量</th>--%>
            <%--<th style="width: 30%">订单金额</th>--%>
          <%--</c:if>--%>
          <%--<c:if test="${!empty param.isSpot}">--%>
            <%--<th style="width: 10%">学期</th>--%>
            <%--<th style="width: 60%">学习中心</th>--%>
            <%--<th style="width: 10%">小包数量</th>--%>
            <%--<th style="width: 10%">大包数量</th>--%>
            <%--<th style="width: 10%">订单金额</th>--%>
          <%--</c:if>--%>
        <%--</tr>--%>
        <%--<c:if test="${empty result}">--%>
          <%--<tr>--%>
            <%--<td colspan="7" align="center" style="color: red;">没有找到相关数据</td>--%>
          <%--</tr>--%>
        <%--</c:if>--%>
        <%--<c:forEach var="json" items="${result}" varStatus="status">--%>
          <%--<tr>--%>
            <%--<td>--%>
              <%--<c:if test="${!empty param.isSpot}">--%>
                <%--${json.semester}--%>
              <%--</c:if>--%>
              <%--<c:if test="${empty param.isSpot}">--%>
                <%--<a href="javascript:void(0)" onclick="detail(${json.semesterId}, '${json.semester}')" style="color: #0092DC">${json.semester}</a>--%>
              <%--</c:if>--%>
            <%--</td>--%>
            <%--<c:if test="${!empty param.isSpot}">--%>
              <%--<td>(${json.spotCode})${json.spotName}</td>--%>
            <%--</c:if>--%>
            <%--<td>${json.orderCount}</td>--%>
            <%--<td>${json.packageCount}</td>--%>
            <%--<td>${json.price}</td>--%>
          <%--</tr>--%>
        <%--</c:forEach>--%>
      <%--</table>--%>
    <%--</c:if>--%>
  <%--</form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function detail(id, semester){--%>
    <%--parent.openDialog(semester+"订单发出情况", 0.6, 0.8, '${pageContext.request.contextPath}/findHistoryOrderSend/findBySemesterId.htm?semesterId='+id);--%>
  <%--}--%>
<%--</script>--%>


<p />
<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findHistoryOrderSend/find.htm" method="post">
  <input type="hidden" name="doSearch" value="1"/>
  <span style="font-weight: bold; font-size: 18px;">历史学期订单发出情况</span><br /><br />

  <label class="am-checkbox am-secondary" style="margin-top:5px; margin-left:24px;">
    <input type="checkbox" id="isSpot" name="isSpot" value="1" <c:if test="${!empty param.isSpot}">checked="true"</c:if> data-am-ucheck>按学习中心 &nbsp;&nbsp;&nbsp;&nbsp;
    <button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example"
            data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '查询超时'}"
            onclick="app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'), this)"><span class="am-icon-search"></span> 查询</button>
  </label>


</form>
<p /><p />

<c:if test="${param.doSearch eq '1'}">
  <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
    <tr>
      <c:if test="${empty param.isSpot}">
        <th style="width: 30%">学期</th>
        <th style="width: 20%">小包数量</th>
        <th style="width: 20%">大包数量</th>
        <th style="width: 30%">订单金额</th>
      </c:if>
      <c:if test="${!empty param.isSpot}">
        <th style="width: 10%">学期</th>
        <th style="width: 60%">学习中心</th>
        <th style="width: 10%">小包数量</th>
        <th style="width: 10%">大包数量</th>
        <th style="width: 10%">订单金额</th>
      </c:if>
    </tr>
    <c:if test="${empty result}">
      <tr>
        <td colspan="7" align="center" style="color: red;">没有找到相关数据</td>
      </tr>
    </c:if>
    <c:forEach var="json" items="${result}" varStatus="status">
      <tr>
        <td>
          <c:if test="${!empty param.isSpot}">
            ${json.semester}
          </c:if>
          <c:if test="${empty param.isSpot}">
            <a href="javascript:void(0)" onclick="detail(${json.semesterId}, '${json.semester}')" style="color: #0092DC">${json.semester}</a>
          </c:if>
        </td>
        <c:if test="${!empty param.isSpot}">
          <td>(${json.spotCode})${json.spotName}</td>
        </c:if>
        <td>${json.orderCount}</td>
        <td>${json.packageCount}</td>
        <td>${json.price}</td>
      </tr>
    </c:forEach>
    </table>
</c:if>
<script>
  function detail(id, semester){
    app.openOneBtnDialog('${pageContext.request.contextPath}/findHistoryOrderSend/findBySemesterId.htm?semesterId='+id, semester+"订单发出情况", 1000, 0.9);
  }
</script>