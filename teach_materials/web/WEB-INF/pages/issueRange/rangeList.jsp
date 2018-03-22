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
  <%--<form id="pageForm" name="pageForm" action="/findIssueRangePage/findIssueRangePageByWhere.htm" method="post">--%>
    <%--<input type="hidden" id="page" name="page" value="${pageInfo.currentPage}"/>--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<label class="lab_80">省中心：</label>--%>
        <%--<select id="provCode" name="provCode">--%>
          <%--<option value="">请选择</option>--%>
          <%--<c:forEach var="prov" items="${provinceList}">--%>
            <%--<option value="${prov.code}" <c:if test="${param.provCode eq prov.code}">selected="selected" </c:if> >${prov.name}</option>--%>
          <%--</c:forEach>--%>
        <%--</select>--%>
        <%--<label class="lab_80">学习中心：</label>--%>
        <%--<select id="spotCode" name="spotCode">--%>
          <%--<option value="">请选择</option>--%>
          <%--<c:forEach var="spot" items="${spotList}">--%>
            <%--<option value="${spot.code}" <c:if test="${param.spotCode eq spot.code}">selected="selected" </c:if> >[${spot.code}]${spot.name}</option>--%>
          <%--</c:forEach>--%>
        <%--</select>--%>
        <%--<label class="lab_80">发行渠道：</label>--%>
        <%--<select id="issueChannelId" name="issueChannelId">--%>
          <%--<option value="">请选择</option>--%>
          <%--<c:forEach var="channel" items="${issueChannelList}">--%>
            <%--<option value="${channel.id}" <c:if test="${param.issueChannelId == channel.id}">selected="selected" </c:if> >${channel.name}</option>--%>
          <%--</c:forEach>--%>
        <%--</select>--%>
        <%--&nbsp;&nbsp;&nbsp;&nbsp;--%>
        <%--<a class="com_btn_b" href="#" id="subBut" onclick="pageForm.submit();"><span>查&nbsp;询</span></a>&nbsp;&nbsp;--%>
        <%--<a class="com_btn_b" href="#" id="resetBut" onclick="resetForm();"><span>重&nbsp;置</span></a>--%>
      <%--</li>--%>
    <%--</ul>--%>
  <%--</form>--%>
  <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
    <%--<tr>--%>
      <%--<th style="width: 30px;">序号</th>--%>
      <%--<th style="width: 150px;">省中心</th>--%>
      <%--<th style="width: 220px;">学习中心</th>--%>
      <%--<th style="width: 100px;">发行渠道</th>--%>
      <%--<th style="width: 100px;">是否发行</th>--%>
      <%--<th style="width: 100px;">操作人</th>--%>
      <%--<th style="width: 100px;">操作时间</th>--%>
      <%--<th style="width: 80px;">操作</th>--%>
    <%--</tr>--%>
    <%--<c:if test="${empty pageInfo || empty pageInfo.pageResults}">--%>
      <%--<tr>--%>
        <%--<td colspan="6" align="center" style="color: red;">没有找到相关数据</td>--%>
      <%--</tr>--%>
    <%--</c:if>--%>
    <%--<c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">--%>
      <%--<tr>--%>
        <%--<td align="center">${status.index+1}</td>--%>
        <%--<td>${json.provName}</td>--%>
        <%--<td>（${json.code}）${json.spotName}</td>--%>
        <%--<td>${json.channelName}</td>--%>
        <%--<td>--%>
          <%--<c:choose>--%>
            <%--<c:when test="${json.isIssue == 0}">是</c:when>--%>
            <%--<c:when test="${json.isIssue == 1}">否</c:when>--%>
            <%--<c:when test="${empty json.isIssue}"></c:when>--%>
            <%--<c:otherwise>未知</c:otherwise>--%>
          <%--</c:choose>--%>
        <%--</td>--%>
        <%--<td>${json.operator}</td>--%>
        <%--<td>${json.operateTime}</td>--%>
        <%--<td>--%>
          <%--<c:choose>--%>
            <%--<c:when test="${!empty json.isIssue && !empty json.operator && !empty json.operateTime}">--%>
              <%--<a href="#" onclick="edit('${json.code}');" style="color: #0092DC">关联渠道</a>--%>
            <%--</c:when>--%>
            <%--<c:otherwise>--%>
              <%--<a href="#" onclick="add('${json.code}');" style="color: #0092DC">关联渠道</a>--%>
            <%--</c:otherwise>--%>
          <%--</c:choose>--%>
        <%--</td>--%>
      <%--</tr>--%>
    <%--</c:forEach>--%>
    <%--<%@ include file="/common/page.jsp"%>--%>
  <%--</table>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function edit(code){--%>
    <%--parent.openDialog('关联渠道', 0.35, 0.25, '${pageContext.request.contextPath}/editIssueRange/openEditIssueRangePage.htm?spotCode='+code);--%>
  <%--}--%>

  <%--function add(code){--%>
    <%--parent.openDialog('关联渠道', 0.35, 0.25, '${pageContext.request.contextPath}/addIssueRange/openAddIssueRange.htm?spotCode='+code);--%>
  <%--}--%>
  <%--function resetForm(){--%>
    <%--$("#provCode, #spotCode, #issueChannelId").val('');--%>
  <%--}--%>
<%--</script>--%>

<p />
<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findIssueRangePage/findIssueRangePageByWhere.htm" method="post">
  <input type="hidden" id="rows" name="rows" />
  <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>

  <label >省中心：</label>
  <select id="provCode" name="provCode" onchange="app.changeSelect(this)">
    <option value=""></option>
    <option value="null">全部</option>
    <c:forEach var="prov" items="${provinceList}">
      <option value="${prov.code}" <c:if test="${param.provCode eq prov.code}">selected="selected" </c:if> >${prov.name}</option>
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

  <label class="lab_80">发行渠道：</label>
  <select id="issueChannelId" name="issueChannelId" onchange="app.changeSelect(this)">
    <option value=""></option>
    <option value="null">全部</option>
    <c:forEach var="channel" items="${issueChannelList}">
      <option value="${channel.id}" <c:if test="${param.issueChannelId == channel.id}">selected="selected" </c:if> >${channel.name}</option>
    </c:forEach>
  </select>&nbsp;&nbsp;&nbsp;&nbsp;

  <button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example"
          data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '查询超时'}"
          onclick="app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'), this)"><span class="am-icon-search"></span> 查询</button>
</form>
<p /><p />

<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <tr class="am-primary">
    <th style="width: 5%;">序号</th>
    <th style="width: 10%;">省中心</th>
    <th style="width: 25%;">学习中心</th>
    <th style="width: 20%;">发行渠道</th>
    <th style="width: 8%;">是否发行</th>
    <th style="width: 8%;">操作人</th>
    <th style="width: 15%;">操作时间</th>
    <th>操作</th>
  </tr>
  <c:if test="${empty pageInfo || empty pageInfo.pageResults}">
    <tr>
      <td colspan="6" align="center" style="color: red;">没有找到相关数据</td>
    </tr>
  </c:if>
  <c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">
    <tr>
      <td align="center">${status.index+1}</td>
      <td>${json.provName}</td>
      <td>（${json.code}）${json.spotName}</td>
      <td>${json.channelName}</td>
      <td>
        <c:choose>
          <c:when test="${json.isIssue == 0}">是</c:when>
          <c:when test="${json.isIssue == 1}">否</c:when>
          <c:when test="${empty json.isIssue}"></c:when>
          <c:otherwise>未知</c:otherwise>
        </c:choose>
      </td>
      <td>${json.operator}</td>
      <td>${json.operateTime}</td>
      <td>
        <div class="am-btn-group">
          <c:choose>
            <c:when test="${!empty json.isIssue && !empty json.operator && !empty json.operateTime}">
              <button type="button" class="am-btn am-btn-primary am-round" onClick="edit('${json.code}')"><span class="am-icon-edit"></span> 关联渠道</button>
            </c:when>
            <c:otherwise>
              <button type="button" class="am-btn am-btn-primary am-round" onClick="add('${json.code}')"><span class="am-icon-plus"></span> 关联渠道</button>
            </c:otherwise>
          </c:choose>
        </div>

      </td>
    </tr>
  </c:forEach>
</table>
<%@ include file="/common/page2.jsp"%>
<script>
  $("select").selected();
  function edit(code){
    var url = '${pageContext.request.contextPath}/editIssueRange/openEditIssueRangePage.htm?spotCode='+code;
    app.openDialog(url, '关联渠道', 600, 0.3, function(index){
      app.edit("${pageContext.request.contextPath}/editIssueRange/issueRangeEdit.htm", $('#editForm').serialize(), index);
    });
  }

  function add(code){
    app.openDialog("${pageContext.request.contextPath}/addIssueRange/openAddIssueRange.htm?spotCode="+code, "关联渠道", 600, 0.3, function(index){
      app.add("${pageContext.request.contextPath}/addIssueRange/issueRangeAdd.htm", $('#addForm').serialize(), index);
    });
  }
</script>
