<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/countSpecAllOrder/find.htm" method="post">
  <input type="hidden" name="method" value="search" />
  <label >学期：</label>
  <select id="semesterId" name="semesterId" onchange="app.changeSelect(this)">
    <c:forEach var="semester" items="${semesterList}">
      <c:choose>
        <c:when test="${method eq 'search'}">
          <option value="${semester.id}" <c:if test="${param.semesterId == semester.id}">selected="selected" </c:if> >${semester.semester}</option>
        </c:when>
        <c:otherwise>
          <option value="${semester.id}" <c:if test="${semester.isNowSemester == 0}">selected="selected" </c:if> >${semester.semester}</option>
        </c:otherwise>
      </c:choose>
    </c:forEach>
  </select>&nbsp;&nbsp;&nbsp;&nbsp;

  <label >学习中心：</label>
  <select id="spotCode" name="spotCode" data-am-selected="{maxHeight: 500, searchBox: 1}" onchange="app.changeSelect(this)">
    <option value=""></option>
    <option value="null">全部</option>
    <c:forEach var="spot" items="${spotList}">
      <option value="${spot.code}" <c:if test="${param.spotCode eq spot.code}">selected="selected" </c:if> >[${spot.code}]${spot.name}</option>
    </c:forEach>
  </select>&nbsp;&nbsp;&nbsp;&nbsp;

  <button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example"
          data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '查询超时'}"
          onclick="app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'), this)"><span class="am-icon-search"></span> 查询</button>
</form>
<p /><p />
<c:if test="${param.method eq 'search'}">
  <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
    <tr class="am-primary">
      <th style="width: 8%;">序号</th>
      <th style="width: 30%;">学习中心</th>
      <th>订单总码洋</th>
    </tr>
    <c:if test="${empty resultData}">
      <tr>
        <td colspan="20" align="center" style="color: red;">没有找到相关数据</td>
      </tr>
    </c:if>
    <c:forEach var="json" items="${resultData}" varStatus="status">
      <tr>
        <td>${status.index+1}</td>
        <td>[${json.spotCode}]${json.spotName}</td>
        <td>${json.totalPrice}</td>
      </tr>
    </c:forEach>
    <tr style="color: red">
      <td></td>
      <td align="right">合计：</td>
      <td>${sumTotalPrice}</td>
    </tr>
  </table>
</c:if>
<script>
  $("select").selected();
</script>