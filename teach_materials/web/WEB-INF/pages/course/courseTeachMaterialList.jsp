<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--<!DOCTYPE html>--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="main-content">--%>
  <%--<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findCourseTeachMaterialPageByWhere/find.htm" method="post">--%>
    <%--<input type="hidden" id="page" name="page" value="${pageInfo.currentPage}"/>--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<table width="80%">--%>
          <%--<tr style="height: 30px;">--%>
            <%--<Td>--%>
              <%--<label class="lab_80">学期：</label>--%>
              <%--<select id="semesterId" name="semesterId">--%>
                <%--<c:forEach var="semester" items="${semesterList}">--%>
                  <%--<c:choose>--%>
                    <%--<c:when test="${empty param.semesterId}">--%>
                      <%--<option value="${semester.id}" <c:if test="${semester.isNowSemester == 0}">selected="selected" </c:if> >${semester.semester}</option>--%>
                    <%--</c:when>--%>
                    <%--<c:otherwise>--%>
                      <%--<option value="${semester.id}" <c:if test="${param.semesterId == semester.id}">selected="selected" </c:if> >${semester.semester}</option>--%>
                    <%--</c:otherwise>--%>
                  <%--</c:choose>--%>
                <%--</c:forEach>--%>
              <%--</select>--%>
            <%--</Td>--%>
            <%--<Td>--%>
              <%--<label class="lab_80">课程编号：</label>--%>
              <%--<input type="text" id="code" name="code" class="input_240" style="width: 150px;" value="${param.code}" />--%>
            <%--</Td>--%>
            <%--<Td>--%>
              <%--<label class="lab_80">教材名称：</label>--%>
              <%--<input type="text" id="name" name="name" class="input_240" style="width: 150px;" value="${param.name}" />--%>
            <%--</Td>--%>
          <%--</tr>--%>
          <%--<tr>--%>
            <%--<Td>--%>
              <%--<label class="lab_80">是否有教材：</label>--%>
              <%--<select id="isNotTM" name="isNotTM">--%>
                <%--<option value="">--请选择--</option>--%>
                <%--<option value="0" <c:if test="${param.isNotTM eq '0'}">selected="selected" </c:if> >否</option>--%>
                <%--<option value="1" <c:if test="${param.isNotTM eq '1'}">selected="selected" </c:if> >是</option>--%>
              <%--</select>--%>
            <%--</Td>--%>
            <%--<Td colspan="2">--%>
              <%--<a class="com_btn_b" href="#" id="subBut" onclick="pageForm.submit();"><span>查&nbsp;询</span></a>&nbsp;&nbsp;--%>
              <%--<a class="com_btn_b" href="#" id="downBut" onclick="down();"><span>下&nbsp;载</span></a>--%>
            <%--</Td>--%>
          <%--</tr>--%>
        <%--</table>--%>
      <%--</li>--%>
    <%--</ul>--%>
    <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
      <%--<tr>--%>
        <%--<th style="width: 8%;">学期</th>--%>
        <%--<th style="width: 15%;">课程名称</th>--%>
        <%--<th style="width: 28%;">教材名称</th>--%>
        <%--<th style="width: 15%;">出版社</th>--%>
        <%--<th style="width: 14%;">作者</th>--%>
        <%--<th style="width: 5%;">价格</th>--%>
        <%--<th style="width: 5%;">操作人</th>--%>
        <%--<th style="width: 10%;">操作时间</th>--%>
      <%--</tr>--%>
      <%--<c:if test="${empty resultJSON}">--%>
        <%--<tr>--%>
          <%--<td colspan="20" align="center" style="color: red;">没有找到相关数据</td>--%>
        <%--</tr>--%>
      <%--</c:if>--%>
      <%--<c:forEach var="json" items="${resultJSON}" varStatus="status">--%>
        <%--<c:set var="num" value="${fn:length(json.value)}" />--%>
        <%--<c:set var="temp" value="1" />--%>
        <%--<c:forEach var="tmJSON" items="${json.value}">--%>
          <%--<tr>--%>
            <%--<c:if test="${temp == 1}">--%>
              <%--<td rowspan="${num}">${semester.semester2}</td>--%>
              <%--<td rowspan="${num}">${json.key}</td>--%>
            <%--</c:if>--%>
            <%--<td>${tmJSON.tmName}</td>--%>
            <%--<td>${tmJSON.pName}</td>--%>
            <%--<td>${tmJSON.author}</td>--%>
            <%--<td>${tmJSON.price}</td>--%>
            <%--<td>${tmJSON.operator}</td>--%>
            <%--<td>${tmJSON.operateTime}</td>--%>
          <%--</tr>--%>
          <%--<c:set var="temp" value="${temp+1}" />--%>
        <%--</c:forEach>--%>
      <%--</c:forEach>--%>
      <%--<%@ include file="/common/page.jsp"%>--%>
    <%--</table>--%>
  <%--</form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function down(){--%>
    <%--var semesterId = $("#semesterId").val();--%>
    <%--var code = $("#code").val();--%>
    <%--var name = $("#name").val();--%>
    <%--var isNotTM = $("#isNotTM").val();--%>
    <%--$.ajax({--%>
      <%--cache: true,--%>
      <%--type: "POST",--%>
      <%--url:'${pageContext.request.contextPath}/downCourseTeachMaterial/down.htm?semesterId='+semesterId+'&code='+code+'&name='+name+'&isNotTM='+isNotTM,--%>
      <%--data:{},--%>
      <%--async: false,--%>
      <%--success: function(data) {--%>
        <%--open("${pageContext.request.contextPath}"+data);--%>
      <%--}--%>
    <%--});--%>
  <%--}--%>
<%--</script>--%>



<p />
<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findCourseTeachMaterialPageByWhere/find.htm" method="post">
  <input type="hidden" name="method" value="search"/>
  <input type="hidden" id="rows" name="rows" />
  <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
  <table width="90%">
    <tr height="40">
      <td align="right"><label >学期：</label></td>
      <td>
        <select id="semesterId" name="semesterId" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <c:forEach var="semester" items="${semesterList}">
            <option value="${semester.id}" <c:if test="${param.semesterId == semester.id}">selected="selected" </c:if> >${semester.semester}</option>
          </c:forEach>
        </select>
      </td>
      <td align="right"><label >课程编号：</label></td>
      <td><input type="text" id="code" name="code" value="${param.code}" /></td>
      <td align="right"><label >教材名称：</label></td>
      <td><input type="text" id="name" name="name" value="${param.name}" /></td>
    </tr>
    <tr height="40">
      <td align="right"><label >是否有教材：</label></td>
      <td>
        <select id="isNotTM" name="isNotTM" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <option value="0" <c:if test="${param.isNotTM eq '0'}">selected="selected" </c:if> >否</option>
          <option value="1" <c:if test="${param.isNotTM eq '1'}">selected="selected" </c:if> >是</option>
        </select>
      </td>
    </tr>
    <tr>
      <td colspan="99" style="padding-left:20px;">
        <button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example"
          data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '查询超时'}"
          onclick="app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'), this)"><span class="am-icon-search"></span> 查询</button>
      </td>
    </tr>
  </table>
</form>
<p /><p />
<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <tr>
    <td colspan="999" style="background-color:#FFF">
      <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="down()"><span class="am-icon-download"></span> 下载</button>
    </td>
  </tr>
  <tr class="am-primary">
    <th style="width: 8%;">学期</th>
    <th style="width: 15%;">课程名称</th>
    <th style="width: 28%;">教材名称</th>
    <th style="width: 15%;">出版社</th>
    <th style="width: 14%;">作者</th>
    <th style="width: 5%;">价格</th>
    <th style="width: 5%;">操作人</th>
    <th style="width: 10%;">操作时间</th>
  </tr>
  <c:if test="${empty resultJSON}">
    <tr>
    <td colspan="20" align="center" style="color: red;">没有找到相关数据</td>
    </tr>
  </c:if>
  <c:forEach var="json" items="${resultJSON}" varStatus="status">
    <c:set var="num" value="${fn:length(json.value)}" />
    <c:set var="temp" value="1" />
    <c:forEach var="tmJSON" items="${json.value}">
      <tr>
        <c:if test="${temp == 1}">
        <td rowspan="${num}">${semester.semester2}</td>
        <td rowspan="${num}">${json.key}</td>
        </c:if>
        <td>${tmJSON.tmName}</td>
        <td>${tmJSON.pName}</td>
        <td>${tmJSON.author}</td>
        <td>${tmJSON.price}</td>
        <td>${tmJSON.operator}</td>
        <td>${tmJSON.operateTime}</td>
      </tr>
      <c:set var="temp" value="${temp+1}" />
    </c:forEach>
  </c:forEach>
</table>
<%@ include file="/common/page2.jsp"%>
<script>
  $("select").selected();
  function down(){
    var semesterId = $("#semesterId").val();
    var code = $("#code").val();
    var name = $("#name").val();
    var isNotTM = $("#isNotTM").val();
    $.ajax({
      cache: true,
      type: "POST",
      url:'${pageContext.request.contextPath}/downCourseTeachMaterial/down.htm?semesterId='+semesterId+'&code='+code+'&name='+name+'&isNotTM='+isNotTM,
      data:{},
      async: false,
      success: function(data) {
        open("${pageContext.request.contextPath}"+data);
      }
    });
  }
</script>