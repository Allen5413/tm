<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<form id="addForm" name="addForm" method="post">--%>
  <%--<div class="main-content">--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<label class="lab_80">编号：</label>--%>
        <%--<input type="text" class="input_240" name="code" maxlength="50" />--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80">名称：</label>--%>
        <%--<input type="text" class="input_240" name="name" maxlength="50" />--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80">单位电话：</label>--%>
        <%--<input type="text" class="input_240" name="tellPhone" maxlength="50" />--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80">手机：</label>--%>
        <%--<input type="text" class="input_240" name="cellPhone" maxlength="50" />--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80">联系人：</label>--%>
        <%--<input type="text" class="input_240" name="contact" maxlength="50" />--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80">开户银行：</label>--%>
        <%--<input type="text" class="input_240" name="accountBank" maxlength="50" />--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80">户名：</label>--%>
        <%--<input type="text" class="input_240" name="accountName" maxlength="50" />--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80">银行账号：</label>--%>
        <%--<input type="text" class="input_240" name="bankCode" maxlength="50" />--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80">地址：</label>--%>
        <%--<input type="text" class="input_240" name="address" maxlength="500" />--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80">税号：</label>--%>
        <%--<input type="text" class="input_240" name="tariffCode" maxlength="50" />--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80 left">说明：</label>--%>
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
        <%--code : {--%>
          <%--required : true--%>
        <%--},--%>
        <%--name : {--%>
          <%--required : true--%>
        <%--}--%>
      <%--},--%>
      <%--messages : {--%>
        <%--code : {--%>
          <%--required : '请输入编号'--%>
        <%--},--%>
        <%--name : {--%>
          <%--required : '请输入名称'--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>
  <%--});--%>

  <%--function sub(){--%>
    <%--if($("#addForm").valid()){--%>
      <%--$.ajax({--%>
        <%--cache: true,--%>
        <%--type: "POST",--%>
        <%--url:"/addPress/pressAdd.htm",--%>
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
    <div class="am-u-sm-3 am-text-right"><label >出版社编号：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入出版社编号" required id="add_code" name="code"  />
    </div>
    <div class="am-u-sm-5">*必填，不可重复</div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >出版社名称：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入出版社名称" required id="add_name" name="name"  />
    </div>
    <div class="am-u-sm-5">*必填，不可重复</div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >单位电话：</label></div>
    <div class="am-u-sm-4" style="float:left">
      <input class="am-input-sm" type="text" placeholder="输入单位电话" name="tellPhone" />
    </div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >联系人：</label></div>
    <div class="am-u-sm-4" style="float:left">
      <input class="am-input-sm" type="text" placeholder="输入联系人" name="contact" />
    </div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >开户银行：</label></div>
    <div class="am-u-sm-4" style="float:left">
      <input class="am-input-sm" type="text" placeholder="输入开户银行" name="accountBank" />
    </div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >户名：</label></div>
    <div class="am-u-sm-4" style="float:left">
      <input class="am-input-sm" type="text" placeholder="输入户名" name="accountName" />
    </div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >银行账号：</label></div>
    <div class="am-u-sm-4" style="float:left">
      <input class="am-input-sm" type="text" placeholder="输入银行账号" name="bankCode" />
    </div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >地址：</label></div>
    <div class="am-u-sm-4" style="float:left">
      <input class="am-input-sm" type="text" placeholder="输入地址" name="address"  />
    </div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >税号：</label></div>
    <div class="am-u-sm-4" style="float:left">
      <input class="am-input-sm" type="text" placeholder="输入税号" name="tariffCode"  />
    </div>
  </div>

  <div class="am-g am-margin-top-sm">
    <div class="am-u-sm-3 am-text-right"><label >说明：</label></div>
    <div class="am-u-sm-9">
      <textarea rows="6" placeholder="输入说明" name="remark"></textarea>
    </div>
  </div>
</form>