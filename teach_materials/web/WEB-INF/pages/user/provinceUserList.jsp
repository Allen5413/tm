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
  <form id="pageForm" name="pageForm" action="/findProvincePage/findProvincePageByWhere.htm" method="post">
    <input type="hidden" id="page" name="page" value="${pageInfo.currentPage}"/>
    <ul class="create_info_list">
      <li>
        <label class="lab_80">编号：</label>
        <input type="text" id="code" name="code" class="input_240" style="width: 200px;" value="${param.code}" />
        <label class="lab_80">省中心名称：</label>
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
      <th style="width: 100px;">编号</th>
      <th style="width: 100px;">名称</th>
      <th style="width: 80px;">操作时间</th>
      <th style="width: 120px;">操作</th>
    </tr>
    <c:if test="${empty pageInfo || empty pageInfo.pageResults}">
      <tr>
        <td colspan="20" align="center" style="color: red;">没有找到相关数据</td>
      </tr>
    </c:if>
    <c:forEach var="province" items="${pageInfo.pageResults}" varStatus="status">
      <tr>
        <td align="center">${status.index+1}</td>
        <td>${province.code}</td>
        <td>${province.name}</td>
        <td>
          <fmt:formatDate value="${province.operateTime}" pattern="yyyy-MM-dd HH:mm:ss" />
        </td>
        <td>
          <a href="#" onclick="searchUserGroup('${province.code}')" style="color: #0092DC">关联用户组</a>&nbsp;&nbsp;
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
    parent.openDialog('用户关联用户组', 0.4, 0.8, '${pageContext.request.contextPath}/findUserGroupByLoginName/doFindUserGroupByLoginName.htm?loginName='+loginName+'&type=2');
  }

  function resetForm(){
    $("#code, #name").val('');
  }
</script>
