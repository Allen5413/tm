<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<form id="editForm" method="post">--%>
  <%--<input type="hidden" name="id" value="${issueChannel.id}" />--%>
  <%--<input type="hidden" name="version" value="${issueChannel.version}" />--%>
  <%--<div class="main-content">--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<label class="lab_80">渠道编号：</label>--%>
        <%--<input type="text" class="input_240" name="code" value="${issueChannel.code}" maxlength="50" />--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80">渠道名称：</label>--%>
        <%--<input type="text" class="input_240" name="name" value="${issueChannel.name}" maxlength="50" />--%>
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
          <%--required : '请输入渠道编号'--%>
        <%--},--%>
        <%--name : {--%>
          <%--required : '请输入渠道名称'--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>
  <%--});--%>

  <%--function sub(){--%>
    <%--if($("#editForm").valid()){--%>
      <%--$.ajax({--%>
        <%--cache: true,--%>
        <%--type: "POST",--%>
        <%--url:"/editIssueChannel/issueChannelEdit.htm",--%>
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
  <input type="hidden" name="id" value="${issueChannel.id}" />
  <input type="hidden" name="version" value="${issueChannel.version}" />
  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >渠道编号：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入渠道编号" required id="edit_code" name="code" value="${issueChannel.code}" />
    </div>
    <div class="am-u-sm-5">*必填，不可重复</div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >渠道名称：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入渠道名称" required id="edit_name" name="name" value="${issueChannel.name}" />
    </div>
    <div class="am-u-sm-5">*必填，不可重复</div>
  </div>
</form>