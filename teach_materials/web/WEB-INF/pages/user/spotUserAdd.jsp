<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%--<!DOCTYPE html>--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="main-content">--%>
  <%--<form id="addForm" name="addForm" method="post">--%>
    <%--<div class="main-content">--%>
      <%--<ul class="create_info_list">--%>
        <%--<li>--%>
          <%--<label class="lab_80">编号：</label>--%>
          <%--<input type="text" class="input_240" id="code" name="code" maxlength="50" />--%>
        <%--</li>--%>
        <%--<li>--%>
          <%--<label class="lab_80">名称：</label>--%>
          <%--<input type="text" class="input_240" id="name" name="name" maxlength="50" />--%>
        <%--</li>--%>
        <%--<li>--%>
          <%--<label class="lab_80">管理员姓名：</label>--%>
          <%--<input type="text" class="input_240" name="adminName" maxlength="50" />--%>
        <%--</li>--%>
        <%--<li>--%>
          <%--<label class="lab_80">性别：</label>--%>
          <%--<input type="radio" name="sex" value="0" checked />男--%>
          <%--<input type="radio" name="sex" value="1" />女--%>
        <%--</li>--%>
        <%--<li>--%>
          <%--<label class="lab_80 left">电子邮箱：</label>--%>
          <%--<input type="text" class="input_240" name="email" maxlength="50" />--%>
        <%--</li>--%>
        <%--<li>--%>
          <%--<label class="lab_80 left">电话：</label>--%>
          <%--<input type="text" class="input_240" name="tel" maxlength="50" />--%>
        <%--</li>--%>
        <%--<li>--%>
          <%--<label class="lab_80 left">手机：</label>--%>
          <%--<input type="text" class="input_240" id="phone" name="phone" maxlength="50" />--%>
        <%--</li>--%>
        <%--<li>--%>
          <%--<label class="lab_80 left">邮编：</label>--%>
          <%--<input type="text" class="input_240" name="postalCode" maxlength="50" />--%>
        <%--</li>--%>
        <%--<li>--%>
          <%--<label class="lab_80 left">地址：</label>--%>
          <%--<input type="text" class="input_240" name="address" maxlength="50" />--%>
        <%--</li>--%>
        <%--<li>--%>
          <%--<label class="lab_80 left">开户银行：</label>--%>
          <%--<input type="text" class="input_240" name="bank" maxlength="50" />--%>
        <%--</li>--%>
        <%--<li>--%>
          <%--<label class="lab_80 left">银行账号：</label>--%>
          <%--<input type="text" class="input_240" id="bankCode" name="bankCode" maxlength="50" />--%>
        <%--</li>--%>
        <%--<li>--%>
          <%--<label class="lab_80 left">银行户名：</label>--%>
          <%--<input type="text" class="input_240" name="bankName" maxlength="50" />--%>
        <%--</li>--%>
        <%--<li>--%>
          <%--<label class="lab_80 left">学生登录：</label>--%>
          <%--<input type="radio" name="isStudentLogin" value="0" >允许--%>
          <%--<input type="radio" name="isStudentLogin" value="1" checked="checked" >不允许--%>
        <%--</li>--%>
      <%--</ul>--%>
    <%--</div>--%>
  <%--</form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function sub(obj){--%>
    <%--var code = $("#code").val();--%>
    <%--var name = $("#name").val();--%>
    <%--var phone = $("#phone").val();--%>
    <%--var bankCode = $("#bankCode").val();--%>

    <%--if(code == ""){--%>
      <%--alert("请输入学习中心编号");--%>
      <%--return false;--%>
    <%--}--%>
    <%--if(name == ""){--%>
      <%--alert("请输入学习中心名称");--%>
      <%--return false;--%>
    <%--}--%>
    <%--if(phone == ""){--%>
      <%--alert("请输入手机号码");--%>
      <%--return false;--%>
    <%--}--%>
    <%--if(bankCode != ""){--%>
      <%--if(!vaild.vaildLuhm(bankCode)){--%>
        <%--alert('银行卡号输入错误');--%>
        <%--return false;--%>
      <%--}--%>
    <%--}--%>

    <%--$.ajax({--%>
      <%--cache: true,--%>
      <%--type: "POST",--%>
      <%--url:"${pageContext.request.contextPath}/addSpot/add.htm",--%>
      <%--data:$('#addForm').serialize(),--%>
      <%--async: false,--%>
      <%--success: function(data) {--%>
        <%--if(data.state == 0){--%>
          <%--$(obj).click();--%>
          <%--alert("提交成功！");--%>
          <%--Dialog.close();--%>
        <%--}else{--%>
          <%--alert(data.msg);--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>
  <%--}--%>
<%--</script>--%>



<form class="am-form" id="addForm" name="addForm" method="post">
  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >编号：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入编号" required id="add_code" name="code"  />
    </div>
    <div class="am-u-sm-5">*必填，不可重复</div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >名称：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入名称" required id="add_name" name="name"  />
    </div>
    <div class="am-u-sm-5">*必填，不可重复</div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >管理员姓名：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入管理员姓名" required name="adminName"  />
    </div>
    <div class="am-u-sm-5"></div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >性别：</label></div>
    <div class="am-u-sm-4">
      <div class="am-btn-group" data-am-button="">
        <label class="am-btn am-btn-primary am-btn-s">
          <input type="radio" name="sex" value="0" checked> 男
        </label>
        <label class="am-btn am-btn-primary am-btn-s">
          <input type="radio" name="sex" value="1"> 女
        </label>
      </div>
    </div>
    <div class="am-u-sm-4"></div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >电子邮箱：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入电子邮箱" required name="email"  />
    </div>
    <div class="am-u-sm-5"></div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >电话：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入电话" required name="tel"  />
    </div>
    <div class="am-u-sm-5"></div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >手机：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入手机" id="add_phone" required name="phone"  />
    </div>
    <div class="am-u-sm-5">*必填</div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >邮编：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入电话" required name="postalCode"  />
    </div>
    <div class="am-u-sm-5"></div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >地址：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入地址" required name="address"  />
    </div>
    <div class="am-u-sm-5"></div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >开户银行：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入开户银行" required name="bank"  />
    </div>
    <div class="am-u-sm-5"></div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >银行账号：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入银行账号" id="add_bankCode" required name="bankCode"  />
    </div>
    <div class="am-u-sm-5">*必填</div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >银行户名：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入银行户名" required name="bankName"  />
    </div>
    <div class="am-u-sm-5"></div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >学生登录：</label></div>
    <div class="am-u-sm-4">
      <div class="am-btn-group" data-am-button="">
        <label class="am-btn am-btn-primary am-btn-s">
          <input type="radio" name="isStudentLogin" value="0"> 允许
        </label>
        <label class="am-btn am-btn-primary am-btn-s">
          <input type="radio" name="isStudentLogin" value="1" checked> 不允许
        </label>
      </div>
    </div>
    <div class="am-u-sm-4"></div>
  </div>
</form>