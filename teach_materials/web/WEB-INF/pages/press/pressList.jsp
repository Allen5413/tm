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
  <%--<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findPressPage/findPressPageByWhere.htm" method="post">--%>
    <%--<input type="hidden" id="page" name="page" value="${pageInfo.currentPage}"/>--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<label class="lab_80">编号：</label>--%>
        <%--<input type="text" id="code" name="code" class="input_240" style="width: 200px;" value="${param.code}" />--%>
        <%--<label class="lab_80">名称：</label>--%>
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
      <%--<th style="width: 100px;">编号</th>--%>
      <%--<th style="width: 150px;">单位名称</th>--%>
      <%--<th style="width: 100px;">单位电话</th>--%>
      <%--<th style="width: 100px;">手机</th>--%>
      <%--<th style="width: 100px;">联系人</th>--%>
      <%--<th style="width: 220px;">说明</th>--%>
      <%--<th style="width: 100px;">操作人</th>--%>
      <%--<th style="width: 80px;">操作时间</th>--%>
      <%--<th style="width: 80px;">操作</th>--%>
    <%--</tr>--%>
    <%--<c:if test="${empty pageInfo || empty pageInfo.pageResults}">--%>
      <%--<tr>--%>
        <%--<td colspan="11" align="center" style="color: red;">没有找到相关数据</td>--%>
      <%--</tr>--%>
    <%--</c:if>--%>
    <%--<c:forEach var="press" items="${pageInfo.pageResults}" varStatus="status">--%>
      <%--<tr>--%>
        <%--<td align="center">${status.index+1}</td>--%>
        <%--<td>${press.code}</td>--%>
        <%--<td>${press.name}</td>--%>
        <%--<td>${press.tellPhone}</td>--%>
        <%--<td>${press.cellPhone}</td>--%>
        <%--<td>${press.contact}</td>--%>
        <%--<td>${press.remark}</td>--%>
        <%--<td>${press.operator}</td>--%>
        <%--<td>--%>
          <%--<fmt:formatDate value="${press.operateTime}" pattern="yyyy-MM-dd HH:mm:ss" />--%>
        <%--</td>--%>
        <%--<td>--%>
          <%--<a href="#" onclick="edit(${press.id})" style="color: #0092DC">修改</a>&nbsp;&nbsp;--%>
          <%--<a href="#" onclick="del(${press.id})" style="color: #0092DC">删除</a>--%>
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
    <%--parent.openDialog('新建出版社', 0.4, 0.65, '${pageContext.request.contextPath}/addPress/openAddPressPage.htm');--%>
  <%--}--%>

  <%--function edit(id){--%>
    <%--parent.openDialog('编辑出版社', 0.4, 0.65, '${pageContext.request.contextPath}/editPress/openEditPressPage.htm?id='+id);--%>
  <%--}--%>

  <%--function del(id){--%>
    <%--if(confirm("您确定要删除该出版社信息？")){--%>
      <%--$.ajax({--%>
        <%--url:"${pageContext.request.contextPath}/deletePress/pressDelete.htm",--%>
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
    <%--$("#code, #name").val('');--%>
  <%--}--%>
<%--</script>--%>

<p />
<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findPressPage/findPressPageByWhere.htm" method="post">
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
  <tr>
    <td colspan="999" style="background-color:#FFF">
      <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="add()"><span class="am-icon-plus"></span> 新增出版社</button>
    </td>
  </tr>
  <tr class="am-primary">
    <th style="width: 4%;">序号</th>
    <th style="width: 4%;">编号</th>
    <th style="width: 15%;">单位名称</th>
    <th style="width: 10%;">单位电话</th>
    <th style="width: 10%;">手机</th>
    <th style="width: 7%;">联系人</th>
    <th style="width: 15%;">说明</th>
    <th style="width: 7%;">操作人</th>
    <th style="width: 12%;">操作时间</th>
    <th>操作</th>
  </tr>
  <c:if test="${empty pageInfo || empty pageInfo.pageResults}">
    <tr>
      <td colspan="11" align="center" style="color: red;">没有找到相关数据</td>
    </tr>
  </c:if>
  <c:forEach var="press" items="${pageInfo.pageResults}" varStatus="status">
    <tr>
      <td align="center">${status.index+1}</td>
      <td>${press.code}</td>
      <td>${press.name}</td>
      <td>${press.tellPhone}</td>
      <td>${press.cellPhone}</td>
      <td>${press.contact}</td>
      <td>${press.remark}</td>
      <td>${press.operator}</td>
      <td><fmt:formatDate value="${press.operateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
      <td>
        <div class="am-btn-group">
          <button type="button" class="am-btn am-btn-primary am-round" onClick="edit(${press.id})"><span class="am-icon-edit"></span> 修改</button>
          <button type="button" class="am-btn am-btn-primary am-round btn-loading-example"
                  data-am-loading="{spinner: 'circle-o-notch', loadingText:'正在删除...', resetText: '<span class=am-icon-trash-o></span> 删除'}"
                  onClick="del(${press.id}, this)"><span class="am-icon-trash-o"></span> 删除</button>
        </div>
      </td>
    </tr>
  </c:forEach>
</table>
<%@ include file="/common/page2.jsp"%>
<script>
  function edit(id){
    var url = '${pageContext.request.contextPath}/editPress/openEditPressPage.htm?id='+id;
    app.openDialog(url, '编辑出版社', 600, 0.7, function(index){
      var code = $("#edit_code").val().trim();
      var name = $("#edit_name").val().trim();
      if(code == ""){
        app.msg("请输入出版社编号", 1);
        return;
      }
      if(name == ""){
        app.msg("请输入出版社名称", 1);
        return;
      }
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/editPress/pressEdit.htm",
        data:$('#editForm').serialize(),
        async: false,
        success: function(data) {
          if(data.state == 0){
            app.msg('提交成功', 0);
            layer.close(index);
            $("#searchBtn").click();
          }else{
            app.msg(data.msg, 1);
          }
        }
      });
    });
  }

  function add(){
    var url = '${pageContext.request.contextPath}/addPress/openAddPressPage.htm';
    app.openDialog(url, '新建出版社', 600, 0.7, function(index){
      var code = $("#add_code").val().trim();
      var name = $("#add_name").val().trim();
      if(code == ""){
        app.msg("请输入出版社编号", 1);
        return;
      }
      if(name == ""){
        app.msg("请输入出版社名称", 1);
        return;
      }
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/addPress/pressAdd.htm",
        data:$('#addForm').serialize(),
        async: false,
        success: function(data) {
          if(data.state == 0){
            app.msg('提交成功', 0);
            layer.close(index);
            $("#searchBtn").click();
          }else{
            app.msg(data.msg, 1);
          }
        }
      });
    });
  }


  function del(id, btnObj){
    app.confirm("您确定要删除该出版社信息？", function(){
      $(btnObj).button('loading');
      setTimeout(function(){
        $.ajax({
          url:"${pageContext.request.contextPath}/deletePress/pressDelete.htm",
          method : 'POST',
          async:false,
          data:{"id":id},
          success:function(data){
            if(data.state == 0){
              app.msg("删除成功！", 0);
              $("#searchBtn").click();
            }else {
              app.msg(data.msg, 1);
              $(btnObj).button('reset');
            }
          }
        });
      }, 100);
    });
  }
</script>
