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
  <%--<form id="pageForm" name="pageForm" action="/findIssueChannelPage/findIssueChannelPageByWhere.htm" method="post">--%>
    <%--<input type="hidden" id="page" name="page" value="${pageInfo.currentPage}"/>--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<label class="lab_80">渠道编号：</label>--%>
        <%--<input type="text" id="code" name="code" class="input_240" style="width: 200px;" value="${param.code}" />--%>
        <%--<label class="lab_80">渠道名称：</label>--%>
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
      <%--<th style="width: 150px;">渠道编号</th>--%>
      <%--<th style="width: 220px;">渠道名称</th>--%>
      <%--<th style="width: 100px;">操作人</th>--%>
      <%--<th style="width: 80px;">操作时间</th>--%>
      <%--<th style="width: 80px;">操作</th>--%>
    <%--</tr>--%>
    <%--<c:if test="${empty pageInfo || empty pageInfo.pageResults}">--%>
      <%--<tr>--%>
        <%--<td colspan="6" align="center" style="color: red;">没有找到相关数据</td>--%>
      <%--</tr>--%>
    <%--</c:if>--%>
    <%--<c:forEach var="channel" items="${pageInfo.pageResults}" varStatus="status">--%>
      <%--<tr>--%>
        <%--<td align="center">${status.index+1}</td>--%>
        <%--<td>${channel.code}</td>--%>
        <%--<td>${channel.name}</td>--%>
        <%--<td>${channel.operator}</td>--%>
        <%--<td>--%>
          <%--<fmt:formatDate value="${channel.operateTime}" pattern="yyyy-MM-dd HH:mm:ss" />--%>
        <%--</td>--%>
        <%--<td>--%>
          <%--<a href="#" onclick="edit(${channel.id})" style="color: #0092DC">修改</a>&nbsp;&nbsp;--%>
          <%--&lt;%&ndash;<a href="#" onclick="del(${channel.id})" style="color: #0092DC">删除</a>&ndash;%&gt;--%>
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
    <%--parent.openDialog('新建发行渠道', 0.26, 0.2, '${pageContext.request.contextPath}/addIssueChannel/openAddIssueChannel.htm');--%>
  <%--}--%>

  <%--function edit(id){--%>
    <%--parent.openDialog('编辑发行渠道', 0.26, 0.2, '${pageContext.request.contextPath}/editIssueChannel/openEditIssueChannelPage.htm?id='+id);--%>
  <%--}--%>

  <%--function del(id){--%>
    <%--if(confirm("您确定要删除该渠道信息？")){--%>
      <%--$.ajax({--%>
        <%--url:"${pageContext.request.contextPath}/delMenu/menuDel.htm",--%>
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
    <%--$("#name, #code").val('');--%>
  <%--}--%>
<%--</script>--%>


<p />
<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findIssueChannelPage/findIssueChannelPageByWhere.htm" method="post">
  <input type="hidden" id="rows" name="rows" />
  <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
  <label >渠道编号：</label>
  <input type="text" id="code" name="code" value="${param.code}" />&nbsp;&nbsp;&nbsp;&nbsp;

  <label >渠道名称：</label>
  <input type="text" id="name" name="name" value="${param.name}" />&nbsp;&nbsp;&nbsp;&nbsp;

  <button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example"
          data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '查询超时'}"
          onclick="app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'), this)"><span class="am-icon-search"></span> 查询</button>
</form>
<p /><p />

<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <tr>
    <td colspan="999" style="background-color:#FFF">
      <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="add()"><span class="am-icon-plus"></span> 新增渠道</button>
    </td>
  </tr>
  <tr class="am-primary">
    <th style="width: 8%;">序号</th>
    <th style="width: 10%;">渠道编号</th>
    <th style="width: 20%;">渠道名称</th>
    <th style="width: 10%;">操作人</th>
    <th style="width: 15%;">操作时间</th>
    <th>操作</th>
  </tr>
  <c:if test="${empty pageInfo || empty pageInfo.pageResults}">
    <tr>
      <td colspan="6" align="center" style="color: red;">没有找到相关数据</td>
    </tr>
  </c:if>
  <c:forEach var="channel" items="${pageInfo.pageResults}" varStatus="status">
    <tr>
      <td align="center">${status.index+1}</td>
      <td>${channel.code}</td>
      <td>${channel.name}</td>
      <td>${channel.operator}</td>
      <td><fmt:formatDate value="${channel.operateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
      <td>
        <div class="am-btn-group">
          <button type="button" class="am-btn am-btn-primary am-round" onClick="edit(${channel.id})"><span class="am-icon-edit"></span> 修改</button>
          <%--<button type="button" class="am-btn am-btn-primary am-round btn-loading-example"--%>
                  <%--data-am-loading="{spinner: 'circle-o-notch', loadingText:'正在删除...', resetText: '<span class=am-icon-trash-o></span> 删除'}"--%>
                  <%--onClick="del(${channel.id}, this)"><span class="am-icon-trash-o"></span> 删除</button>--%>
        </div>
      </td>
    </tr>
  </c:forEach>
</table>
<%@ include file="/common/page2.jsp"%>
<script>
  function edit(id){
    var url = '${pageContext.request.contextPath}/editIssueChannel/openEditIssueChannelPage.htm?id='+id;
    app.openDialog(url, '编辑渠道', 600, 0.4, function(index){
      var code = $("#edit_code").val().trim();
      var name = $("#edit_name").val().trim();
      if(code == ""){
        app.msg("请输入编号", 1);
        return;
      }
      if(name == ""){
        app.msg("请输入名称", 1);
        return;
      }
      app.edit("${pageContext.request.contextPath}/editIssueChannel/issueChannelEdit.htm", $('#editForm').serialize(), index);
    });
  }

  function add(){
    var url = '${pageContext.request.contextPath}/addIssueChannel/openAddIssueChannel.htm';
    app.openDialog(url, '新增渠道', 600, 0.4, function(index){
      var code = $("#add_code").val().trim();
      var name = $("#add_name").val().trim();
      if(code == ""){
        app.msg("请输入编号", 1);
        return;
      }
      if(name == ""){
        app.msg("请输入名称", 1);
        return;
      }
      app.add("${pageContext.request.contextPath}/addIssueChannel/issueChannelAdd.htm", $('#addForm').serialize(), index);
    });
  }


  function del(id, btnObj){
    app.del("您确定要删除该渠道信息？", "${pageContext.request.contextPath}/delMenu/menuDel.htm", {"id":id}, btnObj);
  }
</script>
