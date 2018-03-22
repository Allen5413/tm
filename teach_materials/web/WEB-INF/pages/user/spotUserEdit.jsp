<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<div align="center">--%>
  <%--<form id="editForm" name="editForm" method="post">--%>
    <%--<input type="hidden" name="id" value="${spot.id}" />--%>
    <%--<input type="hidden" name="code" value="${spot.code}" />--%>
    <%--<input type="hidden" name="name" value="${spot.name}" />--%>
    <%--<div class="main-content">--%>
      <%--<ul class="create_info_list">--%>
        <%--<li>--%>
          <%--<label class="lab_80">编号：</label>${spot.code}--%>
        <%--</li>--%>
        <%--<li>--%>
          <%--<label class="lab_80">名称：</label>${spot.name}--%>
        <%--</li>--%>
        <%--<li>--%>
          <%--<label class="lab_80">管理员姓名：</label>--%>
          <%--<input type="text" class="input_240" name="adminName" value="${spot.adminName}" maxlength="50" />--%>
        <%--</li>--%>
        <%--<li>--%>
          <%--<label class="lab_80">性别：</label>--%>
          <%--<input type="radio" name="sex" value="0" <c:if test="${0 == spot.sex}">checked</c:if> />男--%>
          <%--<input type="radio" name="sex" value="1" <c:if test="${1 == spot.sex}">checked</c:if> />女--%>
        <%--</li>--%>
        <%--<li>--%>
          <%--<label class="lab_80 left">电子邮箱：</label>--%>
          <%--<input type="text" class="input_240" name="email" value="${spot.email}" maxlength="50" />--%>
        <%--</li>--%>
        <%--<li>--%>
          <%--<label class="lab_80 left">电话：</label>--%>
          <%--<input type="text" class="input_240" name="tel" value="${spot.tel}" maxlength="50" />--%>
        <%--</li>--%>
        <%--<li>--%>
          <%--<label class="lab_80 left">手机：</label>--%>
          <%--<input type="text" class="input_240" id="phone" name="phone" value="${spot.phone}" maxlength="50" />--%>
        <%--</li>--%>
        <%--<li>--%>
          <%--<label class="lab_80 left">邮编：</label>--%>
          <%--<input type="text" class="input_240" name="postalCode" value="${spot.postalCode}" maxlength="50" />--%>
        <%--</li>--%>
        <%--<li>--%>
          <%--<label class="lab_80 left">地址：</label>--%>
          <%--<input type="text" class="input_240" name="address" value="${spot.address}" maxlength="50" />--%>
        <%--</li>--%>
        <%--<li>--%>
          <%--<label class="lab_80 left">开户银行：</label>--%>
          <%--<input type="text" class="input_240" name="bank" value="${spot.bank}" maxlength="50" />--%>
        <%--</li>--%>
        <%--<li>--%>
          <%--<label class="lab_80 left">银行账号：</label>--%>
          <%--<input type="text" class="input_240" name="bankCode" id="bankCode" value="${spot.bankCode}" maxlength="50" />--%>
        <%--</li>--%>
        <%--<li>--%>
          <%--<label class="lab_80 left">银行户名：</label>--%>
          <%--<input type="text" class="input_240" name="bankName" value="${spot.bankName}" maxlength="50" />--%>
        <%--</li>--%>
        <%--<li>--%>
          <%--<label class="lab_80 left">学生登录：</label>--%>
          <%--<input type="radio" name="isStudentLogin" value="0" <c:if test="${spot.isStudentLogin == 0}">checked="checked"</c:if> >允许--%>
          <%--<input type="radio" name="isStudentLogin" value="1" <c:if test="${spot.isStudentLogin != 0}">checked="checked"</c:if>>不允许--%>
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
  <%--function sub(){--%>
    <%--var phone = $("#phone").val();--%>
    <%--var bankCode = $("#bankCode").val();--%>

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
      <%--url:"${pageContext.request.contextPath}/editSpot/editor.htm",--%>
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
<%--</script>--%>


<form class="am-form" id="editForm" name="editForm" method="post">
  <input type="hidden" name="id" value="${spot.id}" />
  <input type="hidden" name="code" value="${spot.code}" />
  <input type="hidden" name="name" value="${spot.name}" />
  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >编号：</label></div>
    <div class="am-u-sm-4">${spot.code}</div>
    <div class="am-u-sm-5"></div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >名称：</label></div>
    <div class="am-u-sm-4">${spot.name}</div>
    <div class="am-u-sm-5"></div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >管理员姓名：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入管理员姓名" required name="adminName" value="${spot.adminName}"  />
    </div>
    <div class="am-u-sm-5"></div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >性别：</label></div>
    <div class="am-u-sm-4">
      <div class="am-btn-group" data-am-button="">
        <label class="am-btn am-btn-primary am-btn-s">
          <input type="radio" name="sex" value="0" <c:if test="${0 == spot.sex}">checked</c:if>> 男
        </label>
        <label class="am-btn am-btn-primary am-btn-s">
          <input type="radio" name="sex" value="1" <c:if test="${1 == spot.sex}">checked</c:if>> 女
        </label>
      </div>
    </div>
    <div class="am-u-sm-4"></div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >电子邮箱：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入电子邮箱" required name="email" value="${spot.email}"  />
    </div>
    <div class="am-u-sm-5"></div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >电话：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入电话" required name="tel" value="${spot.tel}"  />
    </div>
    <div class="am-u-sm-5"></div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >手机：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入手机" id="edit_phone" required name="phone" value="${spot.phone}"  />
    </div>
    <div class="am-u-sm-5">*必填</div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >邮编：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入电话" required name="postalCode" value="${spot.postalCode}"  />
    </div>
    <div class="am-u-sm-5"></div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >地址：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入地址" required name="address" value="${spot.address}"  />
    </div>
    <div class="am-u-sm-5"></div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >开户银行：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入开户银行" required name="bank" value="${spot.bank}" />
    </div>
    <div class="am-u-sm-5"></div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >银行账号：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入银行账号" id="edit_bankCode" required name="bankCode" value="${spot.bankCode}"  />
    </div>
    <div class="am-u-sm-5">*必填</div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >银行户名：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入银行户名" required name="bankName" value="${spot.bankName}"  />
    </div>
    <div class="am-u-sm-5"></div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >学生登录：</label></div>
    <div class="am-u-sm-4">
      <div class="am-btn-group" data-am-button="">
        <label class="am-btn am-btn-primary am-btn-s">
          <input type="radio" name="isStudentLogin" value="0" <c:if test="${0 == spot.isStudentLogin}">checked</c:if>> 允许
        </label>
        <label class="am-btn am-btn-primary am-btn-s">
          <input type="radio" name="isStudentLogin" value="1" <c:if test="${0 == spot.isStudentLogin}">checked</c:if>> 不允许
        </label>
      </div>
    </div>
    <div class="am-u-sm-4"></div>
  </div>
</form>