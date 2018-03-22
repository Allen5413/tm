<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <%@ include file="/common/meta.jsp"%>
  <%@ include file="/common/taglibs.jsp"%>
</head>
<body>
<div class="main-content">
  <form id="pageForm" name="pageForm" action="/findEduwestUserPage/findEduwestUserPageByWhere.htm" method="post">
    <input type="hidden" id="page" name="page" value="${pageInfo.currentPage}"/>
    <ul class="create_info_list">
      <li>
        <label class="lab_80">登录称：</label>
        <input type="text" id="pin" name="pin" class="input_240" style="width: 200px;" value="${param.pin}" />
        <label class="lab_80">用户名称：</label>
        <input type="text" id="name" name="name" class="input_240" style="width: 200px;" value="${param.name}" />
        &nbsp;&nbsp;&nbsp;&nbsp;
        <a class="com_btn_b" href="#" id="subBut" onclick="pageForm.submit();"><span>查&nbsp;询</span></a>&nbsp;&nbsp;
        <a class="com_btn_b" href="#" id="resetBut" onclick="resetForm();"><span>重&nbsp;置</span></a>
      </li>
    </ul>
  </form>
  <table class="table_slist" cellpadding="0" cellspacing="0" width="100%">
    <tr>
      <th style="width: 30px;">序号</th>
      <th style="width: 100px;">登录名</th>
      <th style="width: 100px;">姓名</th>
      <th style="width: 100px;">昵称</th>
      <th style="width: 100px;">性别</th>
      <th style="width: 100px;">电话</th>
      <th style="width: 100px;">手机</th>
      <th style="width: 80px;">操作时间</th>
      <th style="width: 120px;">操作</th>
    </tr>
    <c:if test="${empty pageInfo || empty pageInfo.pageResults}">
      <tr>
        <td colspan="20" align="center" style="color: red;">没有找到相关数据</td>
      </tr>
    </c:if>
    <c:forEach var="eduwestUser" items="${pageInfo.pageResults}" varStatus="status">
      <tr>
        <td align="center">${status.index+1}</td>
        <td>${eduwestUser.pin}</td>
        <td>${eduwestUser.name}</td>
        <td>${eduwestUser.nickName}</td>
        <td>
          <c:choose>
            <c:when test="${eduwestUser.sex == 0}">男</c:when>
            <c:when test="${eduwestUser.sex == 1}">女</c:when>
            <c:otherwise>未知</c:otherwise>
          </c:choose>
        </td>
        <td>${eduwestUser.tel}</td>
        <td>${eduwestUser.mobile}</td>
        <td>
          <fmt:formatDate value="${user.operateTime}" pattern="yyyy-MM-dd HH:mm:ss" />
        </td>
        <td>
          <a href="#" onclick="searchUserGroup('${eduwestUser.pin}')" style="color: #0092DC">关联用户组</a>&nbsp;&nbsp;
        </td>
      </tr>
    </c:forEach>
    <%@ include file="/common/page.jsp"%>
  </table>
</div>
</body>
</html>
<script>
  function searchUserGroup(loginName){
    parent.openDialog('用户关联用户组', 600, 700, '${pageContext.request.contextPath}/findUserGroupByLoginName/doFindUserGroupByLoginName.htm?loginName='+loginName);
  }

  function resetForm(){
    $("#name, #pin").val('');
  }
</script>
