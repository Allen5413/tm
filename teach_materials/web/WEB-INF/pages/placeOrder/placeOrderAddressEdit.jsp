<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<form id="editForm" method="post">--%>
  <%--<input type="hidden" name="id" value="${teachMaterialPlaceOrder.id}" />--%>
  <%--<div class="main-content">--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<label class="lab_80">邮寄地址：</label>--%>
        <%--<input type="text" class="input_240" style="width: 400px;" id="address" name="address" value="${teachMaterialPlaceOrder.address}" maxlength="500" />--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80">收件人：</label>--%>
        <%--<input type="text" class="input_240" style="width: 400px;" id="adminName" name="adminName" value="${teachMaterialPlaceOrder.adminName}" maxlength="500" />--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80">手机：</label>--%>
        <%--<input type="text" class="input_240" style="width: 400px;" id="phone" name="phone" value="${teachMaterialPlaceOrder.phone}" maxlength="500" />--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80">座机：</label>--%>
        <%--<input type="text" class="input_240" style="width: 400px;" id="tel" name="tel" value="${teachMaterialPlaceOrder.tel}" maxlength="500" />--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80">邮政编码：</label>--%>
        <%--<input type="text" class="input_240" style="width: 400px;" id="postalCode" name="postalCode" value="${teachMaterialPlaceOrder.postalCode}" maxlength="500" />--%>
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
    <%--jQuery.validator.addMethod("validatePhone",--%>
      <%--function(value,element) {--%>
        <%--var phone = $.trim($("#phone").val());--%>
        <%--var tel = $.trim($("#tel").val());--%>
        <%--if(phone == "" && tel == ""){--%>
          <%--return false;--%>
        <%--}else{--%>
          <%--return true;--%>
        <%--}--%>
      <%--}--%>
    <%--);--%>

    <%--//对数据的检测--%>
    <%--$("#editForm").validate({--%>
      <%--rules : {--%>
        <%--address : {--%>
          <%--required : true--%>
        <%--},--%>
        <%--adminName : {--%>
          <%--required : true--%>
        <%--},--%>
        <%--phone : {--%>
          <%--validatePhone : true--%>
        <%--},--%>
        <%--tel : {--%>
          <%--validatePhone : true--%>
        <%--},--%>
        <%--postalCode : {--%>
          <%--required : true--%>
        <%--}--%>
      <%--},--%>
      <%--messages : {--%>
        <%--address : {--%>
          <%--required : '请输入邮寄地址'--%>
        <%--},--%>
        <%--adminName : {--%>
          <%--required : '请输入收件人'--%>
        <%--},--%>
        <%--phone : {--%>
          <%--validatePhone : '手机和座机不能都为空'--%>
        <%--},--%>
        <%--tel : {--%>
          <%--validatePhone : '手机和座机不能都为空'--%>
        <%--},--%>
        <%--postalCode : {--%>
          <%--required : '请输入邮政编码'--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>
  <%--});--%>

  <%--function sub(){--%>
    <%--if($("#editForm").valid()){--%>
      <%--$.ajax({--%>
        <%--cache: true,--%>
        <%--type: "POST",--%>
        <%--url:"${pageContext.request.contextPath}/editPlaceOrderForAddress/editAddress.htm",--%>
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
  <input type="hidden" name="id" value="${teachMaterialPlaceOrder.id}" />
  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >邮寄地址：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入邮寄地址" id="edit_address" name="address" value="${teachMaterialPlaceOrder.address}" />
    </div>
    <div class="am-u-sm-5">*必填</div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >收件人：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入收件人" id="edit_adminName" name="adminName" value="${teachMaterialPlaceOrder.adminName}" />
    </div>
    <div class="am-u-sm-5">*必填</div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >手机：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入手机" id="edit_phone" name="phone" value="${teachMaterialPlaceOrder.phone}" />
    </div>
    <div class="am-u-sm-5">*选填，手机和座机不能同时为空</div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >座机：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入座机" id="edit_tel" name="tel" value="${teachMaterialPlaceOrder.tel}" />
    </div>
    <div class="am-u-sm-5">*选填，手机和座机不能同时为空</div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >邮政编码：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入邮政编码" id="edit_postalCode" name="postalCode" value="${teachMaterialPlaceOrder.postalCode}" />
    </div>
    <div class="am-u-sm-5">*必填</div>
  </div>
</form>