<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%--<!DOCTYPE html>--%>
 <%--<html>--%>
 <%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
 <%--</head>--%>
 <%--<body>--%>
  <%--<div class="main-content">--%>
   <%--<form id="pageForm" name="pageForm" action="/findSTMPage/findSTMPageByWhere.htm" method="post">--%>
    <%--<input type="hidden" id="page" name="page" value="${pageInfo.currentPage}"/>--%>
    <%--<ul class="create_info_list">--%>
     <%--<li>--%>
      <%--<label class="lab_80">套教材名称：</label>--%>
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
     <%--<th style="width: 150px;">套教材名称</th>--%>
     <%--<th style="width: 80px;">购买课程编号</th>--%>
     <%--<th style="width: 80px;">购买课程名称</th>--%>
     <%--<th style="width: 150px;">备注</th>--%>
     <%--<th style="width: 80px;">操作人</th>--%>
     <%--<th style="width: 80px;">操作时间</th>--%>
     <%--<th style="width: 150px;">操作</th>--%>
    <%--</tr>--%>
    <%--<c:if test="${empty pageInfo || empty pageInfo.pageResults}">--%>
     <%--<tr>--%>
      <%--<td colspan="20" align="center" style="color: red;">没有找到相关数据</td>--%>
     <%--</tr>--%>
    <%--</c:if>--%>
    <%--<c:forEach var="stm" items="${pageInfo.pageResults}" varStatus="status">--%>
     <%--<tr>--%>
      <%--<td align="center">${status.index+1}</td>--%>
      <%--<td>${stm.name}</td>--%>
      <%--<td>${stm.buyCourseCode}</td>--%>
      <%--<td>${stm.courseName}</td>--%>
      <%--<td>${stm.remark}</td>--%>
      <%--<td>${stm.operator}</td>--%>
      <%--<td>${stm.operateTime}</td>--%>
      <%--<td>--%>
       <%--<a href="#" onclick="edit(${stm.id})" style="color: #0092DC">修改</a>&nbsp;&nbsp;--%>
       <%--<a href="#" onclick="stmCourse(${stm.id}, '${stm.buyCourseCode}')" style="color: #0092DC">关联课程</a>&nbsp;&nbsp;--%>
       <%--<a href="#" onclick="stmTM(${stm.id})" style="color: #0092DC">关联教材</a>--%>
       <%--<a href="#" onclick="del(${stm.id})" style="color: #0092DC">删除</a>--%>
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
  <%--parent.openDialog('新增套教材', 0.4, 0.3, '${pageContext.request.contextPath}/addSTM/openAddSTM.htm');--%>
 <%--}--%>

 <%--function edit(id){--%>
  <%--parent.openDialog('编辑套教材', 0.4, 0.3, '${pageContext.request.contextPath}/editSTM/openEditSTM.htm?id='+id);--%>
 <%--}--%>

 <%--function del(id){--%>
  <%--if(confirm("您确定要删除该套教材信息？")){--%>
   <%--$.ajax({--%>
    <%--url:"${pageContext.request.contextPath}/delSetTeachMaterial/stmDel.htm",--%>
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

 <%--function stmCourse(id, buyCourseCode){--%>
  <%--parent.openDialog('关联课程', 0.4, 0.7, '${pageContext.request.contextPath}/findCourseBystmId/doFindCourseBystmId.htm?stmId='+id+'&buyCourseCode='+buyCourseCode);--%>
 <%--}--%>

 <%--function stmTM(id){--%>
  <%--parent.openDialog('关联教材', 0.4, 0.7, '${pageContext.request.contextPath}/findTeachMaterialForstmTM/doFindTeachMaterialForstmTM.htm?stmId='+id);--%>
 <%--}--%>

 <%--function resetForm(){--%>
  <%--$("#name").val('');--%>
 <%--}--%>
<%--</script>--%>



<p />
<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findSTMPage/findSTMPageByWhere.htm" method="post">
 <input type="hidden" id="rows" name="rows" />
 <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
 <label >套教材名称：</label>
 <input type="text" id="name" name="name" value="${param.name}" />&nbsp;&nbsp;&nbsp;&nbsp;

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
   <th style="width: 5%">序号</th>
   <th style="width: 15%;">套教材名称</th>
   <th style="width: 6%;">购买课程编号</th>
   <th style="width: 15%;">购买课程名称</th>
   <th style="width: 33%;">备注</th>
   <th style="width: 5%;">操作人</th>
   <th style="width: 15%;">操作时间</th>
   <th>操作</th>
 </tr>
 <c:if test="${empty pageInfo || empty pageInfo.pageResults}">
  <tr>
   <td colspan="20" align="center" style="color: red;">没有找到相关数据</td>
  </tr>
 </c:if>
 <c:forEach var="stm" items="${pageInfo.pageResults}" varStatus="status">
  <tr>
   <td align="center">${status.index+1}</td>
   <td>${stm.name}</td>
   <td>${stm.buyCourseCode}</td>
   <td>${stm.courseName}</td>
   <td>${stm.remark}</td>
   <td>${stm.operator}</td>
   <td>${stm.operateTime}</td>
   <td>
    <div class="doc-dropdown-justify-js">
     <div class="am-dropdown" data-am-dropdown="" id="operateBtn_${status.index+1}">
      <button class="am-btn am-btn-default am-btn-sm am-dropdown-toggle" data-am-dropdown-toggle=""><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
      <ul class="am-dropdown-content">
       <li><a href="#" onClick="edit(${stm.id})"><span style="margin-left:10px; margin-top:5px; margin-bottom:5px;" class="am-icon-pencil-square-o"></span> 修改</a></li>
       <li><a href="#" onClick="stmCourse(${stm.id})"><span style="margin-left:10px; margin-top:5px; margin-bottom:5px;" class="am-icon-gear"></span> 关联课程</a></li>
       <li><a href="#" onClick="stmTM(${stm.id})"><span style="margin-left:10px; margin-top:5px; margin-bottom:5px;" class="am-icon-gear"></span> 关联教材</a></li>
       <li><a href="#" onClick="del(${stm.id})"><span style="margin-left:10px; margin-top:5px; margin-bottom:5px;" class="am-icon-trash-o"></span> 删除</a></li>
      </ul>
     </div>
    </div>
   </td>
  </tr>
 </c:forEach>
</table>
<%@ include file="/common/page2.jsp"%>
<script>
 $(function() {
  for(var i=1; i<=${fn:length(pageInfo.pageResults)}; i++) {
   $('#operateBtn_'+i).dropdown({justify: '.doc-dropdown-justify-js'});
  }
 });


 function add(){
  var url = '${pageContext.request.contextPath}/addSTM/openAddSTM.htm';
  app.openDialog(url, '新增套教材', 650, 0.35, function(index){
   var name = $("#add_name").val().trim();
   var course = $("#add_course").val().trim();
   if(name == ""){
    app.msg("请输入套教材名称", 1);
    return;
   }
   if(course == ""){
    app.msg("请选择购买课程", 1);
    return;
   }
   app.add("${pageContext.request.contextPath}/addSTM/stmAdd.htm", $('#addForm').serialize(), index);
  });
 }

 function edit(id){
  var url = '${pageContext.request.contextPath}/editSTM/openEditSTM.htm?id='+id;
  app.openDialog(url, '编辑套教材', 650, 0.35, function(index){
   var name = $("#edit_name").val().trim();
   var course = $("#edit_course").val().trim();
   if(name == ""){
    app.msg("请输入套教材名称", 1);
    return;
   }
   if(course == ""){
    app.msg("请选择购买课程", 1);
    return;
   }
   app.edit("${pageContext.request.contextPath}/editSTM/stmEdit.htm", $('#editForm').serialize(), index);
  });
 }

 function stmCourse(id, buyCourseCode){
  parent.openDialog('关联课程', 0.4, 0.7, '${pageContext.request.contextPath}/findCourseBystmId/doFindCourseBystmId.htm?stmId='+id+'&buyCourseCode='+buyCourseCode);
 }

 function stmTM(id){
  var url = '${pageContext.request.contextPath}/findTeachMaterialForstmTM/doFindTeachMaterialForstmTM.htm?stmId='+id;
  app.openDialog(url, '关联教材', 800, 0.7, function(index){
   app.add("${pageContext.request.contextPath}/addsetTM/stmTMAdd.htm", {"stmId":id, "tmIds": $("#tmIds").val()}, index);
  });
 }

 function stmCourse(id, buyCourseCode){
  var url = '${pageContext.request.contextPath}/findCourseBystmId/doFindCourseBystmId.htm?stmId='+id+'&buyCourseCode='+buyCourseCode;
  app.openDialog(url, '关联课程', 800, 0.7, function(index){

   app.add("${pageContext.request.contextPath}/addSTMCourse/stmCourseAdd.htm", {"stmId":id, "courseCodes": $("#courseCodes").val()}, index);
  });
 }
</script>