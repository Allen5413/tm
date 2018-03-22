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
  <%--<form id="pageForm" name="pageForm" action="/findUserGroupPage/findUserGroupPageByWhere.htm" method="post">--%>
    <%--<input type="hidden" id="page" name="page" value="${pageInfo.currentPage}"/>--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<label class="lab_80">用户组名称：</label>--%>
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
      <%--<th style="width: 150px;">用户组名称</th>--%>
      <%--<th style="width: 220px;">说明</th>--%>
      <%--<th style="width: 100px;">操作人</th>--%>
      <%--<th style="width: 80px;">操作时间</th>--%>
      <%--<th style="width: 80px;">操作</th>--%>
    <%--</tr>--%>
    <%--<c:if test="${empty pageInfo || empty pageInfo.pageResults}">--%>
      <%--<tr>--%>
        <%--<td colspan="6" align="center" style="color: red;">没有找到相关数据</td>--%>
      <%--</tr>--%>
    <%--</c:if>--%>
    <%--<c:forEach var="userGroup" items="${pageInfo.pageResults}" varStatus="status">--%>
      <%--<tr>--%>
        <%--<td align="center">${status.index+1}</td>--%>
        <%--<td>${userGroup.name}</td>--%>
        <%--<td>${userGroup.remark}</td>--%>
        <%--<td>${userGroup.operator}</td>--%>
        <%--<td>--%>
          <%--<fmt:formatDate value="${userGroup.operateTime}" pattern="yyyy-MM-dd HH:mm:ss" />--%>
        <%--</td>--%>
        <%--<td>--%>
          <%--<a href="#" onclick="edit(${userGroup.id})" style="color: #0092DC">修改</a>&nbsp;&nbsp;--%>
          <%--<a href="#" onclick="del(${userGroup.id})" style="color: #0092DC">删除</a>--%>
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
<%--<script>--%>
  <%--function add(){--%>
    <%--parent.openDialog('新建用户组', 0.4, 0.85, '${pageContext.request.contextPath}/addUserGroup/openAddUserGroupPage.htm');--%>
  <%--}--%>

  <%--function edit(id){--%>
    <%--parent.openDialog('编辑用户组', 0.4, 0.85, '${pageContext.request.contextPath}/editUserGroup/openEditUserGroupPage.htm?id='+id);--%>
  <%--}--%>

  <%--function del(id){--%>
    <%--if(confirm("您确定要删除该用户组信息？")){--%>
      <%--$.ajax({--%>
        <%--url:"${pageContext.request.contextPath}/delUserGroup/userGroupDel.htm",--%>
        <%--method : 'POST',--%>
        <%--async:false,--%>
        <%--data:{"id":id},--%>
        <%--success:function(data){--%>
          <%--if(data.state == 0){--%>
            <%--alert("删除成功！");--%>
            <%--resetForm();--%>
            <%--pageForm.submit();--%>
          <%--}else {--%>
            <%--alert(data.msg);--%>
          <%--}--%>
        <%--}--%>
      <%--});--%>
    <%--}--%>
  <%--}--%>

  <%--function resetForm(){--%>
    <%--$("#name").val('');--%>
  <%--}--%>
<%--</script>--%>


<p />
<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findUserGroupPage/findUserGroupPageByWhere.htm" method="post">
  <input type="hidden" id="rows" name="rows" />
  <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>

  <label >用户组名称：</label>
  <input type="text" id="name" name="name" value="${param.name}" />&nbsp;&nbsp;&nbsp;&nbsp;

  <button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example"
          data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '查询超时'}"
          onclick="app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'), this)"><span class="am-icon-search"></span> 查询</button>
</form>
<p /><p />

<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <tr>
    <td colspan="999" style="background-color:#FFF">
      <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="add()"><span class="am-icon-plus"></span> 新增用户组</button>
    </td>
  </tr>
  <tr class="am-primary">
    <th style="width: 5%;">序号</th>
    <th style="width: 30%;">用户组名称</th>
    <th style="width: 20%;">备注</th>
    <th style="width: 7%;">操作人</th>
    <th style="width: 15%;">操作时间</th>
    <th>操作</th>
  </tr>
  <c:if test="${empty pageInfo || empty pageInfo.pageResults}">
    <tr>
      <td colspan="6" align="center" style="color: red;">没有找到相关数据</td>
    </tr>
  </c:if>
  <c:forEach var="userGroup" items="${pageInfo.pageResults}" varStatus="status">
    <tr>
      <td align="center">${status.index+1}</td>
      <td>${userGroup.name}</td>
      <td>${userGroup.remark}</td>
      <td>${userGroup.operator}</td>
      <td><fmt:formatDate value="${userGroup.operateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
      <td>
        <div class="am-btn-group">
          <button type="button" class="am-btn am-btn-primary am-round" onClick="edit(${userGroup.id})"><span class="am-icon-edit"></span> 修改</button>
          <button type="button" class="am-btn am-btn-primary am-round btn-loading-example"
                  data-am-loading="{spinner: 'circle-o-notch', loadingText:'正在删除...', resetText: '<span class=am-icon-trash-o></span> 删除'}"
                  onClick="del(${userGroup.id}, this)"><span class="am-icon-trash-o"></span> 删除</button>
        </div>
      </td>
    </tr>
  </c:forEach>
</table>
<%@ include file="/common/page2.jsp"%>
<script>
  function edit(id){
    var url = '${pageContext.request.contextPath}/editUserGroup/openEditUserGroupPage.htm?id='+id;
    app.openDialog(url, '编辑用户组', 600, 0.8, function(index){
      var name = $("#edit_name").val().trim();
      if(name == ""){
        app.msg("请输入名称", 1);
        return;
      }
      var treeObj = $.fn.zTree.getZTreeObj("myTree");
      var nodes = treeObj.getCheckedNodes(true);
      var resourceIds = "";
      if(nodes.length > 0){
        for(var i=0; i<nodes.length; i++){
          var node = nodes[i];
          if(node.pId > 0) {
            resourceIds += node.rId + ",";
          }
        }
        $("#resourceIds").val(resourceIds.substring(0, resourceIds.length-1));
      }
      app.edit("${pageContext.request.contextPath}/editUserGroup/userGroupEdit.htm", $('#editForm').serialize(), index);
    });
  }

  function add(){
    app.openDialog("${pageContext.request.contextPath}/addUserGroup/openAddUserGroupPage.htm", "新增用户组", 600, 0.8, function(index){
      var treeObj = $.fn.zTree.getZTreeObj("myTree");
      var nodes = treeObj.getCheckedNodes(true);
      var resourceIds = "";
      if(nodes.length > 0){
        for(var i=0; i<nodes.length; i++){
          var node = nodes[i];
          if(node.pId > 0) {
            resourceIds += node.rId + ",";
          }
        }
        $("#resourceIds").val(resourceIds.substring(0, resourceIds.length-1));
      }
      app.add("${pageContext.request.contextPath}/addUserGroup/userGroupAdd.htm", $('#addForm').serialize(), index);
    });
  }

  function del(id, btnObj){
    app.del("您确定要删除该用户组信息？", "${pageContext.request.contextPath}/delUserGroup/userGroupDel.htm", {"id":id}, btnObj);
  }
</script>