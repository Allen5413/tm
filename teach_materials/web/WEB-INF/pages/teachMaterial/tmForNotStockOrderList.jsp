<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<!DOCTYPE html>--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="main-content">--%>
  <%--<br />--%>
  <%--<h2 style="font-size: 20px;"><strong>学生订书单:</strong></h2>--%>
  <%--<br />--%>
  <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
    <%--<tr>--%>
      <%--<th style="width: 3%;">序号</th>--%>
      <%--<th style="width: 5%;">订单号</th>--%>
      <%--<th style="width: 5%;">省中心</th>--%>
      <%--<th style="width: 30%;">学习中心</th>--%>
      <%--<th style="width: 7%;">学号</th>--%>
      <%--<th style="width: 5%;">姓名</th>--%>
      <%--<th style="width: 10%;">专业</th>--%>
      <%--<th style="width: 5%;">层次</th>--%>
      <%--<th style="width: 5%;">教材数量</th>--%>
      <%--<th style="width: 5%;">金额</th>--%>
      <%--<th style="width: 5%;">状态</th>--%>
      <%--<th style="width: 5%;">操作人</th>--%>
      <%--<th style="width: 10%;">操作时间</th>--%>
    <%--</tr>--%>
    <%--<c:if test="${empty pageInfo || empty pageInfo.pageResults}">--%>
      <%--<tr>--%>
        <%--<td colspan="20" align="center" style="color: red;">没有找到相关数据</td>--%>
      <%--</tr>--%>
    <%--</c:if>--%>
    <%--<c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">--%>
      <%--<tr>--%>
        <%--<td>${status.index+1}</td>--%>
        <%--<td>${json.orderCode}</td>--%>
        <%--<td>${json.provName}</td>--%>
        <%--<td>(${json.spotCode})${json.spotName}</td>--%>
        <%--<td>${json.studentCode}</td>--%>
        <%--<td>${json.name}</td>--%>
        <%--<td>${json.specName}</td>--%>
        <%--<td>${json.levelName}</td>--%>
        <%--<td>${json.tmCount}</td>--%>
        <%--<td>${json.totalPrice}</td>--%>
        <%--<td>--%>
          <%--<c:choose>--%>
            <%--<c:when test="${json.state == 0}">未确认</c:when>--%>
            <%--<c:when test="${json.state == 1}">已确认</c:when>--%>
            <%--<c:when test="${json.state == 2}">分拣中</c:when>--%>
            <%--<c:when test="${json.state == 3}">已打包</c:when>--%>
            <%--<c:when test="${json.state == 4}">已发出</c:when>--%>
            <%--<c:when test="${json.state == 5}">已签收</c:when>--%>
            <%--<c:otherwise>未知</c:otherwise>--%>
          <%--</c:choose>--%>
        <%--</td>--%>
        <%--<td>${json.operator}</td>--%>
        <%--<td>${json.operateTime}</td>--%>
      <%--</tr>--%>
    <%--</c:forEach>--%>
  <%--</table>--%>
  <%--<br />--%>
    <%--<h2 style="font-size: 20px;"><strong>学习中心预订单:</strong></h2>--%>
  <%--<br />--%>
  <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
    <%--<tr>--%>
      <%--<th style="width: 3%;">序号</th>--%>
      <%--<th style="width: 8%;">订单号</th>--%>
      <%--<th style="width: 10%;">学习中心</th>--%>
      <%--<th style="width: 7%;">专业</th>--%>
      <%--<th style="width: 4%;">层次</th>--%>
      <%--<th style="width: 4%;">入学年</th>--%>
      <%--<th style="width: 4%;">入学季</th>--%>
      <%--<th style="width: 4%;">教材数量</th>--%>
      <%--<th style="width: 3%;">金额</th>--%>
      <%--<th style="width: 4%;">状态</th>--%>
      <%--<th style="width: 5%;">操作人</th>--%>
      <%--<th style="width: 5%;">操作时间</th>--%>
    <%--</tr>--%>
    <%--<c:if test="${empty placePageInfo || empty placePageInfo.pageResults}">--%>
      <%--<tr>--%>
        <%--<td colspan="20" align="center" style="color: red;">没有找到相关数据</td>--%>
      <%--</tr>--%>
    <%--</c:if>--%>
    <%--<c:forEach var="json" items="${placePageInfo.pageResults}" varStatus="status">--%>
      <%--<tr>--%>
        <%--<td>${status.index + 1}</td>--%>
        <%--<td>${json.orderCode}</td>--%>
        <%--<td>(${json.spotCode})${json.spotName}</td>--%>
        <%--<td>${json.specName}</td>--%>
        <%--<td>${json.levelName}</td>--%>
        <%--<td>${json.enYear}</td>--%>
        <%--<td>--%>
          <%--<c:if test="${json.enQuarter == 0}">春季</c:if>--%>
          <%--<c:if test="${json.enQuarter == 1}">秋季</c:if>--%>
        <%--</td>--%>
        <%--<td>${json.tmCount}</td>--%>
        <%--<td>${json.totalPrice}</td>--%>
        <%--<td>--%>
          <%--<c:choose>--%>
            <%--<c:when test="${json.state == 1}">已生成</c:when>--%>
            <%--<c:when test="${json.state == 2}">分拣中</c:when>--%>
            <%--<c:when test="${json.state == 3}">已打包</c:when>--%>
            <%--<c:when test="${json.state == 4}">已发出</c:when>--%>
            <%--<c:when test="${json.state == 5}">已签收</c:when>--%>
            <%--<c:otherwise>未知</c:otherwise>--%>
          <%--</c:choose>--%>
        <%--</td>--%>
        <%--<td>${json.operator}</td>--%>
        <%--<td>${json.operateTime}</td>--%>
      <%--</tr>--%>
    <%--</c:forEach>--%>
  <%--</table>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>


<div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
  <div class="am-panel-hd am-cf" data-am-collapse="{target: '#order'}">学生订书单<span class="am-icon-chevron-down am-fr"></span></div>
  <div id="order" class="am-in">
    <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
      <tr class="am-primary">
        <th style="width: 4%;">序号</th>
        <th style="width: 5%;">订单号</th>
        <th style="width: 5%;">省中心</th>
        <th style="width: 28%;">学习中心</th>
        <th style="width: 7%;">学号</th>
        <th style="width: 5%;">姓名</th>
        <th style="width: 10%;">专业</th>
        <th style="width: 5%;">层次</th>
        <th style="width: 6%;">教材数量</th>
        <th style="width: 5%;">金额</th>
        <th style="width: 5%;">状态</th>
        <th style="width: 5%;">操作人</th>
        <th style="width: 10%;">操作时间</th>
      </tr>
      <c:if test="${empty pageInfo || empty pageInfo.pageResults}">
        <tr>
          <td colspan="20" align="center" style="color: red;">没有找到相关数据</td>
        </tr>
      </c:if>
      <c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">
        <tr>
          <td>${status.index+1}</td>
          <td>${json.orderCode}</td>
          <td>${json.provName}</td>
          <td>(${json.spotCode})${json.spotName}</td>
          <td>${json.studentCode}</td>
          <td>${json.name}</td>
          <td>${json.specName}</td>
          <td>${json.levelName}</td>
          <td>${json.tmCount}</td>
          <td>${json.totalPrice}</td>
          <td>
            <c:choose>
              <c:when test="${json.state == 0}">未确认</c:when>
              <c:when test="${json.state == 1}">已确认</c:when>
              <c:when test="${json.state == 2}">分拣中</c:when>
              <c:when test="${json.state == 3}">已打包</c:when>
              <c:when test="${json.state == 4}">已发出</c:when>
              <c:when test="${json.state == 5}">已签收</c:when>
              <c:otherwise>未知</c:otherwise>
            </c:choose>
          </td>
          <td>${json.operator}</td>
          <td>${json.operateTime}</td>
        </tr>
      </c:forEach>
    </table>
  </div>
</div>

<div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
  <div class="am-panel-hd am-cf" data-am-collapse="{target: '#spotOrder'}">学习中心预订单<span class="am-icon-chevron-down am-fr"></span></div>
  <div id="spotOrder" class="am-in">
    <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
      <tr class="am-primary">
        <th style="width: 3%;">序号</th>
        <th style="width: 8%;">订单号</th>
        <th style="width: 10%;">学习中心</th>
        <th style="width: 7%;">专业</th>
        <th style="width: 4%;">层次</th>
        <th style="width: 4%;">入学年</th>
        <th style="width: 4%;">入学季</th>
        <th style="width: 4%;">教材数量</th>
        <th style="width: 3%;">金额</th>
        <th style="width: 4%;">状态</th>
        <th style="width: 5%;">操作人</th>
        <th style="width: 5%;">操作时间</th>
      </tr>
      <c:if test="${empty placePageInfo || empty placePageInfo.pageResults}">
        <tr>
          <td colspan="20" align="center" style="color: red;">没有找到相关数据</td>
        </tr>
      </c:if>
      <c:forEach var="json" items="${placePageInfo.pageResults}" varStatus="status">
        <tr>
          <td>${status.index + 1}</td>
          <td>${json.orderCode}</td>
          <td>(${json.spotCode})${json.spotName}</td>
          <td>${json.specName}</td>
          <td>${json.levelName}</td>
          <td>${json.enYear}</td>
          <td>
            <c:if test="${json.enQuarter == 0}">春季</c:if>
            <c:if test="${json.enQuarter == 1}">秋季</c:if>
          </td>
          <td>${json.tmCount}</td>
          <td>${json.totalPrice}</td>
          <td>
            <c:choose>
              <c:when test="${json.state == 1}">已生成</c:when>
              <c:when test="${json.state == 2}">分拣中</c:when>
              <c:when test="${json.state == 3}">已打包</c:when>
              <c:when test="${json.state == 4}">已发出</c:when>
              <c:when test="${json.state == 5}">已签收</c:when>
              <c:otherwise>未知</c:otherwise>
            </c:choose>
          </td>
          <td>${json.operator}</td>
          <td>${json.operateTime}</td>
        </tr>
      </c:forEach>
    </table>
  </div>
</div>