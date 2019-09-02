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
  <%--<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findUserPage/findUserPageByWhere.htm" method="post">--%>
    <%--<input type="hidden" id="page" name="page" value="${pageInfo.currentPage}"/>--%>
    <%--<input type="hidden" id="type" name="type" value="${param.type}"/>--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<label class="lab_80">登录称：</label>--%>
        <%--<input type="text" id="loginName" name="loginName" class="input_240" style="width: 200px;" value="${param.loginName}" />--%>
        <%--<label class="lab_80">用户名称：</label>--%>
        <%--<input type="text" id="name" name="name" class="input_240" style="width: 200px;" value="${param.name}" />--%>
        <%--<label class="lab_80">状态：</label>--%>
        <%--<select id="state" name="state">--%>
          <%--<option value="">请选择</option>--%>
          <%--<option value="0" <c:if test="${0 eq param.state}">selected="selected" </c:if>>启用</option>--%>
          <%--<option value="1" <c:if test="${1 eq param.state}">selected="selected" </c:if>>停用</option>--%>
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
      <%--<th style="width: 100px;">登录名</th>--%>
      <%--<th style="width: 100px;">姓名</th>--%>
      <%--<th style="width: 100px;">手机</th>--%>
      <%--<th style="width: 60px;">状态</th>--%>
      <%--<th style="width: 220px;">说明</th>--%>
      <%--<th style="width: 100px;">操作人</th>--%>
      <%--<th style="width: 80px;">操作时间</th>--%>
      <%--<th style="width: 120px;">操作</th>--%>
    <%--</tr>--%>
    <%--<c:if test="${empty pageInfo || empty pageInfo.pageResults}">--%>
      <%--<tr>--%>
        <%--<td colspan="20" align="center" style="color: red;">没有找到相关数据</td>--%>
      <%--</tr>--%>
    <%--</c:if>--%>
    <%--<c:forEach var="user" items="${pageInfo.pageResults}" varStatus="status">--%>
      <%--<tr>--%>
        <%--<td align="center">${status.index+1}</td>--%>
        <%--<td>${user.loginName}</td>--%>
        <%--<td>${user.name}</td>--%>
        <%--<td>${user.cellPhone}</td>--%>
        <%--<td>--%>
          <%--<c:choose>--%>
            <%--<c:when test="${user.state == 0}">启用</c:when>--%>
            <%--<c:when test="${user.state == 1}">停用</c:when>--%>
            <%--<c:otherwise>未知</c:otherwise>--%>
          <%--</c:choose>--%>
        <%--</td>--%>
        <%--<td>${user.remark}</td>--%>
        <%--<td>${user.operator}</td>--%>
        <%--<td>--%>
          <%--<fmt:formatDate value="${user.operateTime}" pattern="yyyy-MM-dd HH:mm:ss" />--%>
        <%--</td>--%>
        <%--<td>--%>
          <%--<a href="#" onclick="edit(${user.id})" style="color: #0092DC">修改</a>&nbsp;&nbsp;--%>
          <%--<a href="#" onclick="searchUserGroup('${user.loginName}')" style="color: #0092DC">关联用户组</a>&nbsp;&nbsp;--%>
        <%--</td>--%>
      <%--</tr>--%>
    <%--</c:forEach>--%>
    <%--<%@ include file="/common/page.jsp"%>--%>
  <%--</table>--%>
  <%--<br />--%>
  <%--<a class="com_btn_b" href="#" onclick="add()"><span>新&nbsp;增</span></a>--%>

<%--</div>--%>
<%--</body>--%>
<%--</html>--%>



<p />
<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findUserPage/findUserPageByWhere.htm" method="post">
  <input type="hidden" id="page" name="page" value="${pageInfo.currentPage}"/>
  <input type="hidden" id="type" name="type" value="${param.type}"/>

  <label >登录称：</label>
  <input type="text" id="loginName" name="loginName" value="${param.loginName}" />
  <label >用户名称：</label>
  <input type="text" id="name" name="name" value="${param.name}" />&nbsp;&nbsp;&nbsp;
  <label >状态：</label>
  <select id="state" name="state">
    <option value="">请选择</option>
    <option value="0" <c:if test="${0 eq param.state}">selected="selected" </c:if>>启用</option>
    <option value="1" <c:if test="${1 eq param.state}">selected="selected" </c:if>>停用</option>
  </select>

  <button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example"
          data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '查询超时'}"
          onclick="app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'), this)"><span class="am-icon-search"></span> 查询</button>
</form>
<p /><p />

<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <tr>
    <td colspan="999" style="background-color:#FFF">
      <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="add()"><span class="am-icon-plus"></span> 新增</button>
    </td>
  </tr>
  <tr class="am-primary">
    <th style="width: 30px;">序号</th>
    <th style="width: 100px;">登录名</th>
    <th style="width: 100px;">姓名</th>
    <th style="width: 100px;">手机</th>
    <th style="width: 60px;">状态</th>
    <th style="width: 220px;">说明</th>
    <th style="width: 100px;">操作人</th>
    <th style="width: 80px;">操作时间</th>
    <th style="width: 120px;">操作</th>
  </tr>
  <c:if test="${empty pageInfo || empty pageInfo.pageResults}">
    <tr>
      <td colspan="9" align="center" style="color: red;">没有找到相关数据</td>
    </tr>
  </c:if>
  <c:forEach var="user" items="${pageInfo.pageResults}" varStatus="status">
    <tr>
      <td align="center">${status.index+1}</td>
      <td>${user.loginName}</td>
      <td>${user.name}</td>
      <td>${user.cellPhone}</td>
      <td>
        <c:choose>
          <c:when test="${user.state == 0}">启用</c:when>
          <c:when test="${user.state == 1}">停用</c:when>
          <c:otherwise>未知</c:otherwise>
        </c:choose>
      </td>
      <td>${user.remark}</td>
      <td>${user.operator}</td>
      <td>
        <fmt:formatDate value="${user.operateTime}" pattern="yyyy-MM-dd HH:mm:ss" />
      </td>
      <td>
        <div class="am-btn-group">
          <button type="button" class="am-btn am-btn-primary am-round" onClick="edit(${user.id})"><span class="am-icon-edit"></span> 修改</button>
          <button type="button" class="am-btn am-btn-primary am-round" onClick="searchUserGroup('${user.loginName}')"><span class="am-icon-edit"></span> 关联用户组</button>
        </div>
      </td>
    </tr>
  </c:forEach>
</table>
<%@ include file="/common/page2.jsp"%>
<script>
  function add(){
      app.openDialog("${pageContext.request.contextPath}/addUser/openAddUserPage.htm", "新增教材管理员用户", 600, 0.3, function(index){
          app.add("${pageContext.request.contextPath}/addUser/addUser.htm", $('#addForm').serialize(), index);
      });

  }

  function edit(id){
    parent.openDialog('编辑教材管理员用户', 0.3, 0.4, '${pageContext.request.contextPath}/editUser/openEditUserPage.htm?id='+id);
  }

  function searchUserGroup(loginName){
    parent.openDialog('用户关联用户组', 0.4, 0.8, '${pageContext.request.contextPath}/findUserGroupByLoginName/doFindUserGroupByLoginName.htm?loginName='+loginName+'&type=0');
  }

  function resetForm(){
    $("#name, #loginName, #state").val('');
  }
</script>
