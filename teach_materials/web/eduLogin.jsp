<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
  String pin = request.getParameter("pin");
  String pwd = request.getParameter("auth");

  request.setAttribute("pin", pin);
  request.setAttribute("pwd", pwd);


%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/taglibs.jsp"%>
</head>
<form id="loginForm" name="loginForm" method="post" action="${pageContext.request.contextPath}/loginUser/eduLogin.htm">
  <input type="hidden" name="loginName" value="${pin}" />
  <input type="hidden" name="pwd" value="${pwd}" />
  <input type="hidden" name="isValid" value="NotpwD" />
</form>
<script type="text/javascript">
$(function(){
  loginForm.submit();
});
</script>
</html>

