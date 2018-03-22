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
<%--<div id="searchStudent" class="main-content">--%>
  <%--<li>申请退款上传凭证：</li>--%>
  <%--<c:forTokens items="${refund.refundApplyImg}" delims="," var="imagUrl">--%>
    <%--<img onclick="openImg('${pageContext.request.contextPath}${imagUrl}')" src="${pageContext.request.contextPath}${imagUrl}" style="width: 395px; height: 200px;" />--%>
  <%--</c:forTokens>--%>
  <%--<li>审核上传凭证：</li>--%>
  <%--<c:forTokens items="${refund.refundConfirmImg}" delims="," var="imagUrl">--%>
    <%--<img onclick="openImg('${pageContext.request.contextPath}${imagUrl}')" src="${pageContext.request.contextPath}${imagUrl}" style="width: 395px; height: 200px;" />--%>
  <%--</c:forTokens>--%>
  <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
    <%--<tr>--%>
      <%--<th style="width: 3%;">序号</th>--%>
      <%--<th style="width: 8%;">学号</th>--%>
      <%--<th style="width: 6%;">姓名</th>--%>
      <%--<th style="width: 15%;">专业</th>--%>
      <%--<th style="width: 5%;">层次</th>--%>
      <%--<th style="width: 5%;">状态</th>--%>
      <%--<th style="width: 5%;">退款金额</th>--%>
      <%--<th style="width: 19%;">退款说明</th>--%>
      <%--<th style="width: 19%;">审核说明</th>--%>
      <%--<th style="width: 6%;">操作人</th>--%>
      <%--<th style="width: 7%;">操作时间</th>--%>
    <%--</tr>--%>
    <%--<c:if test="${empty list}">--%>
      <%--<tr>--%>
        <%--<td colspan="99" align="center" style="color: red;">没有找到相关数据</td>--%>
      <%--</tr>--%>
    <%--</c:if>--%>
    <%--<c:forEach var="map" items="${list}" varStatus="status">--%>
      <%--<tr>--%>
        <%--<td align="center">${status.index+1}</td>--%>
        <%--<td>${map.studentCode}</td>--%>
        <%--<td>${map.name}</td>--%>
        <%--<td>${map.specName}</td>--%>
        <%--<td>${map.levelName}</td>--%>
        <%--<td>--%>
          <%--<c:choose>--%>
            <%--<c:when test="${map.state == 0}">待审核</c:when>--%>
            <%--<c:when test="${map.state == 1}">通过</c:when>--%>
            <%--<c:when test="${map.state == 2}"><span style="color: #ff0000;">未通过</span></c:when>--%>
            <%--<c:otherwise>未知</c:otherwise>--%>
          <%--</c:choose>--%>
        <%--</td>--%>
        <%--<td>${map.money}</td>--%>
        <%--<td>${map.refundDetail}</td>--%>
        <%--<td>${map.auditDetail}</td>--%>
        <%--<td>${map.operator}</td>--%>
        <%--<td>${map.operateTime}</td>--%>
      <%--</tr>--%>
    <%--</c:forEach>--%>
      <%--<tr>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--<td align="right">合计：</td>--%>
        <%--<td>${totalMoney}</td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>
      <%--</tr>--%>
      <%--<tr>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--<td align="right">审核通过合计：</td>--%>
        <%--<td>${passTotalMoney}</td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>
      <%--</tr>--%>
      <%--<tr>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--<td align="right">审核未通过合计：</td>--%>
        <%--<td>${notPassTotalMoney}</td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>
      <%--</tr>--%>
  <%--</table>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function openImg(url){--%>
    <%--open("http://xiwang.attop.com"+url);--%>
  <%--}--%>
<%--</script>--%>


<div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
  <div class="am-panel-hd am-cf" data-am-collapse="{target: '#apply'}">申请退款上传凭证：<span class="am-icon-chevron-down am-fr"></span></div>
  <div id="apply" class="am-in">
    <ul data-am-widget="gallery" class="am-gallery am-avg-sm-2 am-avg-md-3 am-avg-lg-4 am-gallery-imgbordered" data-am-gallery="{ pureview: true }" >
      <c:forTokens items="${refund.refundApplyImg}" delims="," var="imagUrl">
        <li>
          <div class="am-gallery-item">
            <a href="http://xiwang.attop.com${pageContext.request.contextPath}${imagUrl}" class="" target="_blank">
              <img src="${pageContext.request.contextPath}${imagUrl}" style="width: 200px; height: 200px;"/>
            </a>
          </div>
        </li>
      </c:forTokens>
    </ul>
  </div>
</div>

<div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
  <div class="am-panel-hd am-cf" data-am-collapse="{target: '#audit'}">审核上传凭证：<span class="am-icon-chevron-down am-fr"></span></div>
  <div id="audit" class="am-in">
    <ul data-am-widget="gallery" class="am-gallery am-avg-sm-2 am-avg-md-3 am-avg-lg-4 am-gallery-imgbordered" data-am-gallery="{ pureview: true }" >
      <c:forTokens items="${refund.refundConfirmImg}" delims="," var="imagUrl">
        <li>
          <div class="am-gallery-item">
            <a href="http://xiwang.attop.com${pageContext.request.contextPath}${imagUrl}" class="" target="_blank">
              <img src="${pageContext.request.contextPath}${imagUrl}" style="width: 200px; height: 200px;"/>
            </a>
          </div>
        </li>
      </c:forTokens>
    </ul>
  </div>
</div>


<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <tr class="am-primary">
    <th style="width: 4%;">序号</th>
    <th style="width: 8%;">学号</th>
    <th style="width: 6%;">姓名</th>
    <th style="width: 15%;">专业</th>
    <th style="width: 5%;">层次</th>
    <th style="width: 5%;">状态</th>
    <th style="width: 6%;">退款金额</th>
    <th style="width: 19%;">退款说明</th>
    <th style="width: 19%;">审核说明</th>
    <th style="width: 6%;">操作人</th>
    <th style="width: 7%;">操作时间</th>
  </tr>
  <c:if test="${empty list}">
    <tr>
      <td colspan="99" align="center" style="color: red;">没有找到相关数据</td>
    </tr>
  </c:if>
  <c:forEach var="map" items="${list}" varStatus="status">
    <tr>
      <td align="center">${status.index+1}</td>
      <td>${map.studentCode}</td>
      <td>${map.name}</td>
      <td>${map.specName}</td>
      <td>${map.levelName}</td>
      <td>
        <c:choose>
          <c:when test="${map.state == 0}">待审核</c:when>
          <c:when test="${map.state == 1}">通过</c:when>
          <c:when test="${map.state == 2}"><span style="color: #ff0000;">未通过</span></c:when>
          <c:otherwise>未知</c:otherwise>
        </c:choose>
      </td>
      <td>${map.money}</td>
      <td>${map.refundDetail}</td>
      <td>${map.auditDetail}</td>
      <td>${map.operator}</td>
      <td>${map.operateTime}</td>
    </tr>
  </c:forEach>
  <tr>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td align="right">合计：</td>
    <td>${totalMoney}</td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td colspan="2" align="right">审核通过合计：</td>
    <td>${passTotalMoney}</td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td colspan="2" align="right">审核未通过合计：</td>
    <td>${notPassTotalMoney}</td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
</table>
