<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<!DOCTYPE html>--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<form id="editForm" method="post">--%>
  <%--<input type="hidden" name="id" value="${tmr.id}" />--%>
  <%--<input type="hidden" name="version" value="${tmr.version}" />--%>
  <%--<div class="main-content">--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<label class="lab_80">版次：</label>--%>
        <%--<input type="text" class="input_240" name="revision" value="${tmr.revision}" maxlength="50" />--%>
        <%--<div class="clear"></div>--%>
      <%--</li>--%>
    <%--</ul>--%>
  <%--</div>--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--$(function(){--%>
    <%--//对数据的检测--%>
    <%--$("#editForm").validate({--%>
      <%--rules : {--%>
        <%--revision : {--%>
          <%--required : true--%>
        <%--}--%>
      <%--},--%>
      <%--messages : {--%>
        <%--revision : {--%>
          <%--required : '请输入版次'--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>
  <%--});--%>
<%--</script>--%>

<form class="am-form" id="editForm" name="editForm" method="post">
  <input type="hidden" name="id" value="${tmr.id}" />
  <input type="hidden" name="version" value="${tmr.version}" />
  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >版次：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入版次" required id="edit_revision" name="revision" value="${tmr.revision}"  />
    </div>
    <div class="am-u-sm-5">*必填</div>
  </div>
</form>