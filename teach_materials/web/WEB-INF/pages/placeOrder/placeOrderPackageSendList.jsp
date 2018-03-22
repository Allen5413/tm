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
  <%--<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findPlaceOrderPackageForSpotCountPage/find.htm" method="post">--%>
    <%--<input type="hidden" id="page" name="page" value="${pageInfo.currentPage}"/>--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<table width="80%">--%>
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
          <%--</tr>--%>
          <%--<tr style="height: 30px;">--%>
            <%--<Td>--%>
              <%--<a class="com_btn_b" href="#" id="subBut" onclick="pageForm.submit();"><span>查&nbsp;询</span></a>&nbsp;&nbsp;--%>
              <%--<a class="com_btn_b" href="#" id="resetBut" onclick="resetForm();"><span>重&nbsp;置</span></a>--%>
            <%--</Td>--%>
          <%--</tr>--%>
        <%--</table>--%>
      <%--</li>--%>
    <%--</ul>--%>
    <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
      <%--<tr>--%>
        <%--<th style="width: 160px;">学习中心</th>--%>
        <%--<th style="width: 120px;">包裹数量</th>--%>
        <%--<th style="width: 120px;">操作</th>--%>
      <%--</tr>--%>
      <%--<c:if test="${empty pageInfo || empty pageInfo.pageResults}">--%>
        <%--<tr>--%>
          <%--<td colspan="20" align="center" style="color: red;">没有找到相关数据</td>--%>
        <%--</tr>--%>
      <%--</c:if>--%>
      <%--<c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">--%>
        <%--<tr>--%>
          <%--<td>(${json.code})${json.name}</td>--%>
          <%--<td>${json.count}</td>--%>
          <%--<td>--%>
            <%--<a href="#" onclick="send('${json.code}')" style="color: #0092DC">录入物流单号</a>--%>
          <%--</td>--%>
        <%--</tr>--%>
      <%--</c:forEach>--%>
      <%--<%@ include file="/common/page.jsp"%>--%>
    <%--</table>--%>
  <%--</form>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function send(spotCode){--%>
    <%--var semesterId = $("#semesterId").val();--%>
    <%--parent.openDialog('填写物流单号', 0.3, 0.6, '${pageContext.request.contextPath}/sendPlaceOrderPackage/open.htm?spotCode='+spotCode+'&semesterId='+semesterId);--%>
  <%--}--%>

  <%--function resetForm(){--%>
    <%--$("#spotCode").val('');--%>
  <%--<c:forEach var="semester" items="${semesterList}">--%>
    <%--<c:if test="${semester.isNowSemester == 0}">--%>
    <%--$("#semesterId").val(${semester.id});--%>
  <%--</c:if>--%>
  <%--</c:forEach>--%>
  <%--}--%>
<%--</script>--%>


<p />
<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findPlaceOrderPackageForSpotCountPage/find.htm" method="post">
  <input type="hidden" id="rows" name="rows" />
  <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
  <label >学期：</label>
  <select id="semesterId" name="semesterId" onchange="app.changeSelect(this)">
    <c:forEach var="semester" items="${semesterList}">
      <option value="${semester.id}" <c:if test="${semesterId == semester.id}">selected="selected" </c:if> >${semester.semester}</option>
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

<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <tr class="am-primary">
    <th style="width: 50%">学习中心</th>
    <th style="width: 20%">包裹数量</th>
    <th>操作</th>
  </tr>
  <c:if test="${empty pageInfo || empty pageInfo.pageResults}">
    <tr>
      <td colspan="20" align="center" style="color: red;">没有找到相关数据</td>
    </tr>
  </c:if>
  <c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">
    <tr>
      <td>(${json.code})${json.name}</td>
      <td>${json.count}</td>
      <td>
        <div class="am-btn-group">
          <button type="button" class="am-btn am-btn-primary am-round" onClick="send('${json.code}')"><span class="am-icon-plus"></span> 录入物流单号</button>
        </div>
      </td>
    </tr>
  </c:forEach>
</table>
<%@ include file="/common/page2.jsp"%>
<script>
  $("select").selected();
  function send(spotCode){
    var semesterId = $("#semesterId").val();
    app.openDialog('${pageContext.request.contextPath}/sendPlaceOrderPackage/open.htm?spotCode='+spotCode+'&semesterId='+semesterId, '填写物流单号', 0.3, 0.6, function(index){
      var isValid = true;
      $("[name=logisticCodes]").each(function(){
        if($(this).val().trim() == ""){
          isValid = false;
        }
      });
      if(!isValid) {
        app.msg("请填写完物流单号", 1);
        return;
      }
      app.add("${pageContext.request.contextPath}/sendPlaceOrderPackage/send.htm", $('#addForm').serialize(), index);
    });
  }
</script>