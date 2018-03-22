<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<form id="editForm" name="addForm" method="post">--%>
 <%--<input type="hidden" name="id" value="${setTeachMaterial.id}" />--%>
 <%--<input type="hidden" name="version" value="${setTeachMaterial.version}" />--%>
 <%--<div class="main-content">--%>
  <%--<ul class="create_info_list">--%>
   <%--<li>--%>
    <%--<label class="lab_80">套教材名称：</label>--%>
    <%--<input type="text" class="input_240" name="name" maxlength="50" value="${setTeachMaterial.name}" />--%>
   <%--</li>--%>
   <%--<li>--%>
    <%--<label class="lab_80">购买课程：</label>--%>
    <%--<select class="select" name="buyCourseCode">--%>
     <%--<option value="">请选择</option>--%>
     <%--<c:forEach var="course" items="${courseList}">--%>
      <%--<option value="${course.code}" <c:if test="${setTeachMaterial.buyCourseCode eq course.code}">selected="selected"</c:if>>--%>
       <%--${course.name}--%>
      <%--</option>--%>
     <%--</c:forEach>--%>
    <%--</select>--%>
   <%--</li>--%>
   <%--<li>--%>
    <%--<label class="lab_80 left">备注：</label>--%>
    <%--<textarea class="textArea left" name="remark" maxlength="500">${setTeachMaterial.remark}</textarea>--%>
    <%--<div class="clear"></div>--%>
   <%--</li>--%>
   <%--<li>--%>
    <%--<label class="lab_80 left">--%>
     <%--<a class="com_btn_b" href="#" onclick="sub()"><span>提&nbsp;交</span></a>--%>
    <%--</label>--%>
   <%--</li>--%>
  <%--</ul>--%>
 <%--</div>--%>
<%--</form>--%>
<%--<script>--%>
 <%--$(function(){--%>
  <%--//对数据的检测--%>
  <%--$("#editForm").validate({--%>
   <%--rules : {--%>
    <%--name : {--%>
     <%--required : true--%>
    <%--},--%>
    <%--buyCourseCode : {--%>
     <%--required : true--%>
    <%--}--%>
   <%--},--%>
   <%--messages : {--%>
    <%--name : {--%>
     <%--required : '请输入套教材名称'--%>
    <%--},--%>
    <%--buyCourseCode : {--%>
     <%--required : '请选择购买课程'--%>
    <%--}--%>
   <%--}--%>
  <%--});--%>
 <%--});--%>

 <%--function sub(){--%>
  <%--if($("#editForm").valid()){--%>
   <%--$.ajax({--%>
    <%--cache: true,--%>
    <%--type: "POST",--%>
    <%--url:"/editSTM/stmEdit.htm",--%>
    <%--data:$('#editForm').serialize(),--%>
    <%--async: false,--%>
    <%--success: function(data) {--%>
     <%--if(data.state == 0){--%>
      <%--alert("提交成功！");--%>
      <%--$('#index_iframe').contents().find("#resetBut").click();--%>
      <%--$('#index_iframe').contents().find("#subBut").click();--%>
      <%--closeDialog();--%>
     <%--}else{--%>
      <%--alert(data.msg);--%>
     <%--}--%>
    <%--}--%>
   <%--});--%>
  <%--}--%>
 <%--}--%>
<%--</script>--%>


<form class="am-form" id="editForm" name="editForm" method="post">
 <input type="hidden" name="id" value="${setTeachMaterial.id}" />
 <input type="hidden" name="version" value="${setTeachMaterial.version}" />
 <div class="am-g am-margin-top">
  <div class="am-u-sm-2 am-text-right"><label >套教材名称：</label></div>
  <div class="am-u-sm-4" style="float:left">
   <input type="text" placeholder="输入教材名称" id="edit_name" name="name" value="${setTeachMaterial.name}"/>
  </div>
  <div class="am-u-sm-4">*必填，不能重复</div>
 </div>

 <div class="am-g am-margin-top">
  <div class="am-u-sm-2 am-text-right"><label >购买课程：</label></div>
  <div class="am-u-sm-4" style="float:left">
   <select class="select" id="edit_course" name="buyCourseCode" data-am-selected="{searchBox: 1, maxHeight: 500}">
    <option value="">--请选择--</option>
    <c:forEach var="course" items="${courseList}">
     <option value="${course.code}" <c:if test="${setTeachMaterial.buyCourseCode eq course.code}">selected="selected"</c:if>>[${course.code}]${course.name}</option>
    </c:forEach>
   </select>
  </div>
  <div class="am-u-sm-4">*必选</div>
 </div>

 <div class="am-g am-margin-top-sm">
  <div class="am-u-sm-2 am-text-right"><label >备注：</label></div>
  <div class="am-u-sm-10">
   <textarea rows="6" placeholder="输入备注" name="remark">${setTeachMaterial.remark}</textarea>
  </div>
 </div>
</form>
<script>
 $("select").selected();
</script>