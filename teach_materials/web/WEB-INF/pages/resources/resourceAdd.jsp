<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<form id="addForm" name="addForm" method="post">--%>
  <%--<div class="main-content">--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<label class="lab_80">所属菜单：</label>--%>
        <%--<select class="select" name="menuId">--%>
          <%--<option value="">请选择</option>--%>
          <%--<c:forEach var="menu" items="${menuList}">--%>
            <%--<option value="${menu.id}">${menu.name}</option>--%>
          <%--</c:forEach>--%>
        <%--</select>--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80">资源名称：</label>--%>
        <%--<input type="text" class="input_240" name="name" maxlength="50" />--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80">url：</label>--%>
        <%--<input type="text" class="input_240" name="url" maxlength="200" />--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80 left">备注：</label>--%>
        <%--<textarea class="textArea left" name="remark" maxlength="500"></textarea>--%>
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
    <%--$("#addForm").validate({--%>
      <%--rules : {--%>
        <%--menuId : {--%>
          <%--required : true--%>
        <%--},--%>
        <%--name : {--%>
          <%--required : true--%>
        <%--},--%>
        <%--url : {--%>
          <%--required : true--%>
        <%--}--%>
      <%--},--%>
      <%--messages : {--%>
        <%--menuId : {--%>
          <%--required : '请选择所属菜单'--%>
        <%--},--%>
        <%--name : {--%>
          <%--required : '请输入资源名称'--%>
        <%--},--%>
        <%--url : {--%>
          <%--required : '请输入资源url'--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>
  <%--});--%>

  <%--function sub(){--%>
    <%--if($("#addForm").valid()){--%>
      <%--$.ajax({--%>
        <%--cache: true,--%>
        <%--type: "POST",--%>
        <%--url:"/addResource/resourceAdd.htm",--%>
        <%--data:$('#addForm').serialize(),--%>
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

<form class="am-form" id="addForm" name="addForm" method="post">
  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >所属菜单：</label></div>
    <div class="am-u-sm-4">
      <select class="select" name="menuId">
        <option value="">--请选择--</option>
        <c:forEach var="menu" items="${menuList}">
        <option value="${menu.id}">${menu.name}</option>
        </c:forEach>
      </select>
    </div>
    <div class="am-u-sm-5">*必选</div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >资源名称：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入名称" required id="add_name" name="name"  />
    </div>
    <div class="am-u-sm-5">*必填，不可重复</div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >URL：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入URL" required id="add_url" name="url"  />
    </div>
    <div class="am-u-sm-5">*必填，不可重复</div>
  </div>

  <div class="am-g am-margin-top-sm">
    <div class="am-u-sm-3 am-text-right"><label >备注：</label></div>
    <div class="am-u-sm-9">
      <textarea rows="6" placeholder="输入备注" name="remark"></textarea>
    </div>
  </div>
</form>