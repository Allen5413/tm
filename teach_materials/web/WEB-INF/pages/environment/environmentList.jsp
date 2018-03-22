<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="main-content">--%>
  <%--<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findEPage/findEPageByWhere.htm" method="post">--%>
    <%--<input type="hidden" id="page" name="page" value="${pageInfo.currentPage}"/>--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<label class="lab_80">变量名称：</label>--%>
        <%--<input type="text" id="name" name="name" class="input_240" style="width: 200px;" value="${param.name}" />--%>
        <%--&nbsp;&nbsp;&nbsp;&nbsp;--%>
        <%--<a class="com_btn_b" href="#" id="subBut" onclick="pageForm.submit();"><span>查&nbsp;询</span></a>&nbsp;&nbsp;--%>
        <%--<a class="com_btn_b" href="#" id="resetBut" onclick="resetForm();"><span>重&nbsp;置</span></a>--%>
      <%--</li>--%>
    <%--</ul>--%>
  <%--</form>--%>
  <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
    <%--<tr>--%>
      <%--<th style="width: 30px;">序号</th>--%>
      <%--<th>变量名称</th>--%>
      <%--<th style="width: 100px;">变量值</th>--%>
      <%--<th style="width: 100px;">操作人</th>--%>
      <%--<th style="width: 180px;">操作时间</th>--%>
      <%--<th style="width: 80px;">操作</th>--%>
    <%--</tr>--%>
    <%--<c:if test="${empty pageInfo || empty pageInfo.pageResults}">--%>
      <%--<tr>--%>
        <%--<td colspan="7" align="center" style="color: red;">没有找到相关数据</td>--%>
      <%--</tr>--%>
    <%--</c:if>--%>
    <%--<c:forEach var="environment" items="${pageInfo.pageResults}" varStatus="status">--%>
      <%--<tr>--%>
        <%--<td align="center">${status.index+1}</td>--%>
        <%--<td>${environment.name}</td>--%>
        <%--<td>--%>
          <%--<c:choose>--%>
            <%--<c:when test="${environment.value == 0}">否</c:when>--%>
            <%--<c:when test="${environment.value == 1}">是</c:when>--%>
            <%--<c:otherwise>未知</c:otherwise>--%>
          <%--</c:choose>--%>
        <%--</td>--%>
        <%--<td>${environment.operator}</td>--%>
        <%--<td>--%>
          <%--<fmt:formatDate value="${environment.operateTime}" pattern="yyyy-MM-dd HH:mm:ss" />--%>
        <%--</td>--%>
        <%--<td>--%>
          <%--<c:if test="${environment.value == 0}">--%>
            <%--<a href="#" onclick="edit(${environment.id}, 1, ${environment.version})" style="color: #0092DC">是</a>--%>
          <%--</c:if>--%>
          <%--<c:if test="${environment.value == 1}">--%>
              <%--<a href="#" onclick="edit(${environment.id}, 0, ${environment.version})" style="color: #0092DC">否</a>--%>
          <%--</c:if>--%>
        <%--</td>--%>
      <%--</tr>--%>
    <%--</c:forEach>--%>
    <%--<%@ include file="/common/page.jsp"%>--%>
  <%--</table>--%>
  <%--<br />--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function edit(id, value, version){--%>
    <%--$.ajax({--%>
      <%--cache: true,--%>
      <%--type: "POST",--%>
      <%--url:"${pageContext.request.contextPath}/editEnvironment/environmentEdit.htm",--%>
      <%--data:{--%>
        <%--"id":id,--%>
        <%--"value":value,--%>
        <%--"version":version--%>
      <%--},--%>
      <%--async: false,--%>
      <%--success: function(data) {--%>
        <%--if(data.state == 0){--%>
          <%--alert("提交成功！");--%>
          <%--pageForm.submit();--%>
        <%--}else{--%>
          <%--alert(data.msg);--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>
  <%--}--%>
  <%--function resetForm(){--%>
    <%--$("#name").val('');--%>
  <%--}--%>
<%--</script>--%>


<p />
<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findEPage/findEPageByWhere.htm" method="post">
  <input type="hidden" id="rows" name="rows" />
  <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>

  <label >变量名称：</label>
  <input type="text" id="name" name="name" value="${param.name}" />&nbsp;&nbsp;&nbsp;&nbsp;

  <button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example"
          data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '查询超时'}"
          onclick="app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'), this)"><span class="am-icon-search"></span> 查询</button>
</form>
<p /><p />

<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <tr>
    <td colspan="999" style="background-color:#FFF">
      <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="add()"><span class="am-icon-plus"></span> 新增教材类别</button>
    </td>
  </tr>
  <tr class="am-primary">
    <th style="width: 10%;">序号</th>
    <th>变量名称</th>
    <th style="width: 10%;">变量值</th>
    <th style="width: 10%;">操作人</th>
    <th style="width: 20%;">操作时间</th>
    <th style="width: 10%;">操作</th>
  </tr>
  <c:if test="${empty pageInfo || empty pageInfo.pageResults}">
    <tr>
      <td colspan="7" align="center" style="color: red;">没有找到相关数据</td>
    </tr>
  </c:if>
  <c:forEach var="environment" items="${pageInfo.pageResults}" varStatus="status">
    <tr>
      <td align="center">${status.index+1}</td>
      <td>${environment.name}</td>
      <td>
        <c:choose>
          <c:when test="${environment.value == 0}">否</c:when>
          <c:when test="${environment.value == 1}">是</c:when>
          <c:otherwise>未知</c:otherwise>
        </c:choose>
      </td>
      <td>${environment.operator}</td>
      <td>
        <fmt:formatDate value="${environment.operateTime}" pattern="yyyy-MM-dd HH:mm:ss" />
      </td>
      <td>
        <c:if test="${environment.value == 0}">
          <a href="#" onclick="edit(${environment.id}, 1, ${environment.version})" style="color: #0092DC">是</a>
        </c:if>
        <c:if test="${environment.value == 1}">
          <a href="#" onclick="edit(${environment.id}, 0, ${environment.version})" style="color: #0092DC">否</a>
        </c:if>
      </td>
    </tr>
  </c:forEach>
</table>
<%@ include file="/common/page2.jsp"%>
<script>
  function edit(id, value, version){
    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/editEnvironment/environmentEdit.htm",
      data:{
        "id":id,
        "value":value,
        "version":version
      },
      async: false,
      success: function(data) {
        if(data.state == 0){
          app.msg("提交成功！", 0);
          $("#searchBtn").click();
        }else{
          app.msg(data.msg, 1);
        }
      }
    });
  }
</script>
