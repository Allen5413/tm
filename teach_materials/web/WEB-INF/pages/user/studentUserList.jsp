<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<p />
<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findStudentPage/findStudentPageByWhere.htm" method="post">
  <input type="hidden" id="rows" name="rows" />
  <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
  <label >编号：</label>
  <input type="text" id="code" name="code" value="${param.code}" />&nbsp;&nbsp;&nbsp;&nbsp;

  <label >名称：</label>
  <input type="text" id="name" name="name" value="${param.name}" />&nbsp;&nbsp;&nbsp;&nbsp;

  <button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example"
          data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '查询超时'}"
          onclick="app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'), this)"><span class="am-icon-search"></span> 查询</button>
</form>
<p /><p />

<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <tr class="am-primary">
    <th style="width: 5%;">序号</th>
    <th style="width: 30%;">学习中心</th>
    <th style="width: 5%;">入学年季</th>
    <th style="width: 10%;">学号</th>
    <th style="width: 10%;">姓名</th>
    <th style="width: 5%;">专业</th>
    <th style="width: 5%;">层次</th>
    <th style="width: 10%;">是否默认发教材</th>
  </tr>
  <c:if test="${empty pageInfo || empty pageInfo.pageResults}">
    <tr>
      <td colspan="20" align="center" style="color: red;">没有找到相关数据</td>
    </tr>
  </c:if>
  <c:forEach var="student" items="${pageInfo.pageResults}" varStatus="status">
    <tr>
      <td align="center">${status.index+1}</td>
      <td>${student[0]}</td>
      <td>${student[1]}</td>
      <td>${student[2]}</td>
      <td>${student[3]}</td>
      <td>${student[4]}</td>
      <td>${student[5]}</td>
      <td>
        <c:if test="${student[6] eq '0'}">
          否&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="setIsForeverSendTm(${student[7]}, 1)">是</a>
        </c:if>
        <c:if test="${student[6] eq '1'}">
          是&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="setIsForeverSendTm(${student[7]}, 0)">否</a>
        </c:if>
      </td>
    </tr>
  </c:forEach>
</table>
<%@ include file="/common/page2.jsp"%>
<script>
  function setIsForeverSendTm(id, isForeverSendTm){
    app.edit("${pageContext.request.contextPath}/editStudentIsForeverSendTmById/editor.htm", {id:id, isForeverSendTm:isForeverSendTm});
  }
</script>