<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<form id="editForm" method="post">--%>
  <%--<input type="hidden" name="id" value="${teachMaterialType.id}" />--%>
  <%--<input type="hidden" name="version" value="${teachMaterialType.version}" />--%>
  <%--<div class="main-content">--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<label class="lab_80 left">编号：</label>--%>
        <%--<input type="text" class="input_240" name="code" value="${teachMaterialType.code}" maxlength="50" />--%>
        <%--<div class="clear"></div>--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80">类别名称：</label>--%>
        <%--<input type="text" class="input_240" name="name" value="${teachMaterialType.name}" maxlength="50" />--%>
        <%--<div class="clear"></div>--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80 left">说明：</label>--%>
        <%--<textarea class="textArea left" name="remark" maxlength="500">${teachMaterialType.remark}</textarea>--%>
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
        <%--code : {--%>
          <%--required : true--%>
        <%--},--%>
        <%--name : {--%>
          <%--required : true--%>
        <%--}--%>
      <%--},--%>
      <%--messages : {--%>
        <%--code : {--%>
          <%--required : '请输入类型编号'--%>
        <%--},--%>
        <%--name : {--%>
          <%--required : '请输入类型名称'--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>
  <%--});--%>

  <%--function sub(){--%>
    <%--if($("#editForm").valid()){--%>
      <%--$.ajax({--%>
        <%--cache: true,--%>
        <%--type: "POST",--%>
        <%--url:"/editTMT/tmtEdit.htm",--%>
        <%--data:$('#editForm').serialize(),--%>
        <%--async: false,--%>
        <%--success: function(data) {--%>
          <%--if(data.state == 0){--%>
            <%--alert("提交成功！");--%>
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

<form class="am-form" id="editForm" method="post">
  <input type="hidden" name="id" value="${teachMaterialType.id}" />
  <input type="hidden" name="version" value="${teachMaterialType.version}" />
  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >编号：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入编号" required id="edit_code" name="code" value="${teachMaterialType.code}" />
    </div>
    <div class="am-u-sm-5">*必填，不可重复</div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >类型名称：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入类型名称" required id="edit_name" name="name" value="${teachMaterialType.name}" />
    </div>
    <div class="am-u-sm-5">*必填，不可重复</div>
  </div>

  <div class="am-g am-margin-top-sm">
    <div class="am-u-sm-3 am-text-right"><label >说明：</label></div>
    <div class="am-u-sm-9">
      <textarea rows="6" placeholder="输入说明" name="remark">${teachMaterialType.remark}</textarea>
    </div>
  </div>
</form>