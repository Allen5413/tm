<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<div align="center">--%>
  <%--<form id="editForm" name="editForm" method="post">--%>
    <%--<div class="main-content">--%>
      <%--<ul class="create_info_list">--%>
        <%--<li>--%>
          <%--<label class="lab_80">旧密码：</label>--%>
          <%--<input type="password" class="input_240" id="oldPwd" name="oldPwd" maxlength="50" />--%>
        <%--</li>--%>
        <%--<li>--%>
          <%--<label class="lab_80">新密码：</label>--%>
          <%--<input type="password" class="input_240" id="newPwd" name="newPwd" maxlength="50" />--%>
        <%--</li>--%>
        <%--<li>--%>
          <%--<label class="lab_80">确认新密码：</label>--%>
          <%--<input type="password" class="input_240" id="newPwdAgain" name="newPwdAgain" maxlength="50" />--%>
        <%--</li>--%>
      <%--</ul>--%>
    <%--</div>--%>
    <%--<div class="main-content" style="padding-left: 50px;">--%>
      <%--<ul>--%>
        <%--<li>--%>
          <%--<label class="lab_80 left">--%>
            <%--<a class="com_btn_b" href="#" onclick="sub()"><span>提&nbsp;交</span></a>--%>
          <%--</label>--%>
        <%--</li>--%>
      <%--</ul>--%>
    <%--</div>--%>
  <%--</form>--%>
<%--</div>--%>
<%--<script>--%>
  <%--$(function(){--%>
    <%--//对数据的检测--%>
    <%--$("#editForm").validate({--%>
      <%--rules : {--%>
        <%--oldPwd : {--%>
          <%--required : true--%>
        <%--},--%>
        <%--newPwd : {--%>
          <%--required : true--%>
        <%--},--%>
        <%--newPwdAgain : {--%>
          <%--required : true,--%>
          <%--equalTo : "#newPwd"--%>
        <%--}--%>
      <%--},--%>
      <%--messages : {--%>
        <%--oldPwd : {--%>
          <%--required : '请输入旧密码'--%>
        <%--},--%>
        <%--newPwd : {--%>
          <%--required : '请输入新密码'--%>
        <%--},--%>
        <%--newPwdAgain : {--%>
          <%--required : '请输入确认新密码',--%>
          <%--equalTo : '2次密码不一致'--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>
  <%--});--%>

  <%--function sub(){--%>
    <%--if($("#editForm").valid()){--%>
      <%--$.ajax({--%>
        <%--cache: true,--%>
        <%--type: "POST",--%>
        <%--url:"/editPwd/pwdEdit.htm",--%>
        <%--data:$('#editForm').serialize(),--%>
        <%--async: false,--%>
        <%--success: function(data) {--%>
          <%--if(data.state == 0){--%>
            <%--alert("提交成功！");--%>
            <%--closeDialog();--%>
          <%--}else{--%>
            <%--alert(data.msg);--%>
          <%--}--%>
        <%--}--%>
      <%--});--%>
    <%--}--%>
  <%--}--%>
<%--</script>--%>

<form class="am-form" id="editPwdForm" name="editPwdForm" method="post" data-am-validator>
  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >旧密码：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="password" placeholder="输入旧密码" required id="oldPwd" name="oldPwd" />
    </div>
    <div class="am-u-sm-5">*必填</div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >新密码：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="password" placeholder="输入新密码" required id="newPwd" name="newPwd" />
    </div>
    <div class="am-u-sm-5">*必填</div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >确认新密码：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="password" placeholder="输入确认新密码" min="0" required id="newPwdAgain" name="newPwdAgain" />
    </div>
    <div class="am-u-sm-5">*必填，必须与新密码一致</div>
  </div>
</form>