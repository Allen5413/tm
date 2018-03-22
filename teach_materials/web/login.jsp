﻿<%--<%@ page language="java" contentType="text/html; charset=utf-8"--%>
         <%--pageEncoding="utf-8"%>--%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<script type="text/javascript">--%>
  <%--$(function(){--%>
    <%--//对数据的检测--%>
    <%--$("#loginForm").validate({--%>
      <%--rules : {--%>
        <%--loginName : {--%>
          <%--required : true--%>
        <%--},--%>
        <%--pwd : {--%>
          <%--required : true--%>
        <%--}--%>
      <%--},--%>
      <%--messages : {--%>
        <%--loginName : {--%>
          <%--required : '请输入用户名'--%>
        <%--},--%>
        <%--pwd : {--%>
          <%--required : '请输入密码'--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>

    <%--//回车事件--%>
    <%--document.onkeydown = function(e){--%>
      <%--var ev = document.all ? window.event : e;--%>
      <%--if(ev.keyCode==13) {--%>
        <%--sub();--%>
      <%--}--%>
    <%--}--%>
  <%--});--%>

  <%--function sub(){--%>
    <%--var loginName = $.trim($("#loginName").val());--%>
    <%--var pwd = $.trim($("#pwd").val());--%>
    <%--if(loginName == ""){--%>
      <%--$("#msg").html("请输入用户名！");--%>
    <%--}--%>
    <%--else if(pwd == ""){--%>
      <%--$("#msg").html("请输入密码！");--%>
    <%--}else{--%>
      <%--var params = {--%>
        <%--"loginName":loginName,--%>
        <%--"pwd":pwd--%>
      <%--};--%>
      <%--$.ajax({--%>
        <%--url:"${pageContext.request.contextPath}/loginUser/login.htm",--%>
        <%--method : 'POST',--%>
        <%--async:false,--%>
        <%--data:params,--%>
        <%--success:function(data){--%>
          <%--if(data.msg == "success"){--%>
            <%--location.href = "${pageContext.request.contextPath}/index/main.htm";--%>
          <%--}else {--%>
            <%--$("#msg").html(data.msg);--%>
          <%--}--%>
        <%--}--%>
      <%--});--%>
    <%--}--%>
  <%--}--%>
<%--</script>--%>
<%--<body style="background:#f8f8f8">--%>
  <%--<div class="login_admin">--%>
    <%--<h4>欢迎登录教材系统管理后台</h4>--%>
    <%--<div class="form_login">--%>
      <%--<span id="msg" style="padding-left: 40px; color: red;"></span>--%>
      <%--<form name="loginForm" id="loginForm" action="/loginUser/login.htm" method="post">--%>
        <%--<p><label>用户名</label><input type="text" class="input_160" name="loginName" id="loginName" /></p>--%>
        <%--<p><label>密 &nbsp; 码</label><input type="password" class="input_160" name="pwd" id="pwd" /></p>--%>
        <%--<p><label>&nbsp;</label><a class="btnSave" href="#" onclick="sub()">登陆</a></p>--%>
      <%--</form>--%>
    <%--</div>--%>
  <%--</div>--%>
<%--</body>--%>
<%--</html>--%>


<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html class="no-js">
<head>
<%@ include file="/common/meta2.jsp"%>
<%@ include file="/common/taglibs2.jsp"%>
</head>
<script type="text/javascript">
  $(function(){
    //回车事件
    document.onkeydown = function(e){
      var ev = document.all ? window.event : e;
      if(ev.keyCode==13) {
        sub();
      }
    }
  });

  function sub(){
    var loginName = $.trim($("#loginName").val());
    var pwd = $.trim($("#pwd").val());
    if(loginName == ""){
      $("#loginName").css("color", "red");
    }
    else if(pwd == ""){
      $("#pwd").css("color", "red");
    }else{
      var params = {
        "loginName":loginName,
        "pwd":pwd
      };
      $.ajax({
        url:"${pageContext.request.contextPath}/loginUser/login.htm",
        method : 'POST',
        async:false,
        data:params,
        success:function(data){
          if(data.msg == "success"){
            location.href = "${pageContext.request.contextPath}/index/main.htm";
          }else {
            $("#msg").html(data.msg);
          }
        }
      });
    }
  }
</script>
<body>
<div class="am-g myapp-login">
  <div class="myapp-login-bg">
    <div class="myapp-login-logo">
      <i class="am-icon-stumbleupon"></i>
    </div>
    <div class="am-u-sm-10 myapp-login-form">
      <form class="am-form">
        <fieldset>
          <center>
          <div class="am-form-group" style="color: #ffffff; text-align: center; font-size: 30px;">
            欢迎登录教材系统管理后台
          </div>
          <div class="am-form-group">
            <input type="text" class="" id="loginName" name="loginName" placeholder="请输入用户名" style="width: 300px;" onfocus="$(this).css('color', '#fff');">
          </div>
          <div class="am-form-group">
            <input type="password" class="" id="pwd" name="pwd" placeholder="请输入密码" style="width: 300px;" onfocus="$(this).css('color', '#fff');">
            <li id="msg" style="color: #f60; font-weight: bold; font-size: 18px;"></li>
          </div>
          <p><button type="button" id="loginBtn" class="am-btn am-btn-default" onclick="sub()" style="width: 300px;">登&nbsp;&nbsp;录</button></p>
          </center>
        </fieldset>
      </form>
    </div>
  </div>
</div>
</body>
</html>