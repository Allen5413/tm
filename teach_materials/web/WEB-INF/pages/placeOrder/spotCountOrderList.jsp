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
  <%--<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findPlaceOrderForSpotCount/find.htm" method="post">--%>
    <%--<input type="hidden" id="page" name="page" value="${pageInfo.currentPage}"/>--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<table width="100%">--%>
          <%--<tr style="height: 30px;">--%>
            <%--<Td>--%>
              <%--<label class="lab_80">学期：</label>--%>
              <%--<select id="semesterId" name="semesterId">--%>
                <%--<c:forEach var="semester" items="${semesterList}">--%>
                  <%--<option value="${semester.id}" <c:if test="${semesterId == semester.id}">selected="selected" </c:if> >${semester.semester}</option>--%>
                <%--</c:forEach>--%>
              <%--</select>--%>
            <%--</Td>--%>
            <%--<Td>--%>
              <%--<label class="lab_80">学习中心：</label>--%>
              <%--<select id="spotCode" name="spotCode">--%>
                <%--<option value="">请选择</option>--%>
                <%--<c:forEach var="spot" items="${spotList}">--%>
                  <%--<option value="${spot.code}" <c:if test="${param.spotCode eq spot.code}">selected="selected" </c:if> >[${spot.code}]${spot.name}</option>--%>
                <%--</c:forEach>--%>
              <%--</select>--%>
            <%--</Td>--%>
            <%--<Td>--%>
              <%--<label class="lab_80">是否打印：</label>--%>
              <%--<select id="state" name="state">--%>
                <%--<option value="1" <c:if test="${param.state eq '1'}">selected="selected" </c:if>>未打印</option>--%>
                <%--<option value="2" <c:if test="${param.state eq '2'}">selected="selected" </c:if>>已打印</option>--%>
              <%--</select>--%>
            <%--</Td>--%>
          <%--</tr>--%>
          <%--<tr style="height: 30px;">--%>
            <%--<Td>--%>
              <%--<a class="com_btn_b" href="#" id="subBut" onclick="pageForm.submit();"><span>查&nbsp;询</span></a>&nbsp;&nbsp;--%>
            <%--</Td>--%>
          <%--</tr>--%>
        <%--</table>--%>
      <%--</li>--%>
    <%--</ul>--%>
    <%--<Br><Br>--%>
    <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
      <%--<tr>--%>
        <%--<th style="width: 5%;">序号</th>--%>
        <%--<th style="width: 42%;">学习中心</th>--%>
        <%--<th style="width: 7%;">教材数量</th>--%>
        <%--<th style="width: 7%;">总金额</th>--%>
        <%--<c:if test="${param.state eq '2'}">--%>
          <%--<th style="width: 10%;">操作人</th>--%>
          <%--<th style="width: 10%;">打印时间</th>--%>
        <%--</c:if>--%>
        <%--<th>操作</th>--%>
      <%--</tr>--%>
      <%--<c:if test="${empty pageInfo || empty pageInfo.pageResults}">--%>
        <%--<tr>--%>
          <%--<td colspan="20" align="center" style="color: red;">没有找到相关数据</td>--%>
        <%--</tr>--%>
      <%--</c:if>--%>
      <%--<c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">--%>
        <%--<tr>--%>
          <%--<td>${status.index + 1}</td>--%>
          <%--<td>(${json.spotCode})${json.spotName}</td>--%>
          <%--<td>${json.tmCount}</td>--%>
          <%--<td>${json.totalPrice}</td>--%>
          <%--<c:if test="${param.state eq '2'}">--%>
            <%--<td>${json.operator}</td>--%>
            <%--<td>${json.operateTime}</td>--%>
          <%--</c:if>--%>
          <%--<td>--%>
            <%--<a href="#" onclick="printTMOrder('${json.spotCode}', '${json.operateTime}', 0)" style="color: #0092DC">查看</a>&nbsp;&nbsp;--%>
            <%--<a href="#" onclick="printTMOrder('${json.spotCode}', '${json.operateTime}', 1)" style="color: #0092DC">打印</a>--%>
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
  <%--function printTMOrder(code, operateTime, flag){--%>
    <%--var semesterId = '${semesterId}';--%>
    <%--var state = '${param.state}';--%>
    <%--<c:if test="${empty param.state}">state = 1;</c:if>--%>
    <%--open("${pageContext.request.contextPath}/findPlaceOrderTMForSpotPrint/find.htm?spotCode="+code+"&semesterId="+semesterId+"&state="+state+"&flag="+flag+"&operateTime="+operateTime);--%>
  <%--}--%>
<%--</script>--%>

<p />
<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findPlaceOrderForSpotCount/find.htm" method="post">
  <input type="hidden" id="rows" name="rows" />
  <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
  <label >学期：</label>
  <select id="semesterId" name="semesterId" onchange="app.changeSelect(this)">
    <c:forEach var="semester" items="${semesterList}">
      <option value="${semester.id}" <c:if test="${semesterId == semester.id}">selected="selected" </c:if> >${semester.semester}</option>
    </c:forEach>
  </select>
  &nbsp;&nbsp;&nbsp;&nbsp;

  <label >学习中心：</label>
  <select id="spotCode" name="spotCode" data-am-selected="{maxHeight: 500, searchBox: 1}" onchange="app.changeSelect(this)">
    <option value=""></option>
    <option value="null">全部</option>
    <c:forEach var="spot" items="${spotList}">
      <option value="${spot.code}" <c:if test="${param.spotCode eq spot.code}">selected="selected" </c:if> >[${spot.code}]${spot.name}</option>
    </c:forEach>
  </select>
  &nbsp;&nbsp;&nbsp;&nbsp;

  <label>是否打印：</label>
  <select id="state" name="state" onchange="app.changeSelect(this)">
    <option value="1" <c:if test="${param.state eq '1'}">selected="selected" </c:if>>未打印</option>
    <option value="3" <c:if test="${param.state eq '3'}">selected="selected" </c:if>>已打印</option>
  </select>
  &nbsp;&nbsp;&nbsp;&nbsp;
  <button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example"
          data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '查询超时'}"
          onclick="app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'), this)"><span class="am-icon-search"></span> 查询</button>
</form>
<p /><p />

<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <tr class="am-primary">
    <th style="width: 5%;">序号</th>
    <th style="width: 42%;">学习中心</th>
    <th style="width: 7%;">教材数量</th>
    <th style="width: 7%;">总金额</th>
    <c:if test="${param.state eq '3'}">
      <th style="width: 10%;">操作人</th>
      <th style="width: 10%;">打印时间</th>
    </c:if>
    <th>操作</th>
  </tr>
  <c:if test="${empty pageInfo || empty pageInfo.pageResults}">
    <tr>
      <td colspan="20" align="center" style="color: red;">没有找到相关数据</td>
    </tr>
  </c:if>
  <c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">
    <tr>
      <td>${status.index + 1}</td>
      <td>(${json.spotCode})${json.spotName}</td>
      <td>${json.tmCount}</td>
      <td>${json.totalPrice}</td>
      <c:if test="${param.state eq '3'}">
        <td>${json.operator}</td>
        <td>${json.operateTime}</td>
      </c:if>
      <td>
        <div class="am-btn-group">
          <button type="button" class="am-btn am-btn-primary am-round" onClick="printTMOrder('${json.spotCode}', '${json.operateTime}', 0)"><span class="am-icon-th-list"></span> 查看</button>
          <button type="button" class="am-btn am-btn-primary am-round" onClick="printTMOrder('${json.spotCode}', '${json.operateTime}', 1)"><span class="am-icon-print"></span> 打印</button>
        </div>
      </td>
    </tr>
  </c:forEach>
</table>
<%@ include file="/common/page2.jsp"%>
<script>
  $("select").selected();
  function printTMOrder(code, operateTime, flag){
    var semesterId = '${semesterId}';
    var state = '${param.state}';
    <c:if test="${empty param.state}">state = 1;</c:if>
    open("${pageContext.request.contextPath}/findPlaceOrderTMForSpotPrint/find.htm?spotCode="+code+"&semesterId="+semesterId+"&state="+state+"&flag="+flag+"&operateTime="+operateTime);
    if(flag == 1) {
      setTimeout(function () {
        $("#state").val("3");
        $("#searchBtn").click();
      }, 3000);
    }
  }
</script>