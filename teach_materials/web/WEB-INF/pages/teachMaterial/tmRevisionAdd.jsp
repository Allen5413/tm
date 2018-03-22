<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%--<!DOCTYPE html>--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<form id="addForm" name="addForm" method="post">--%>
  <%--<input type="hidden" name="teachMaterialId" value="${param.tmId}">--%>
  <%--<div class="main-content">--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<label class="lab_80">版次：</label>--%>
        <%--<input type="text" class="input_240" id="revision" name="revision" maxlength="50" />--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80">价格：</label>--%>
        <%--<input type="text" class="input_240" id="price" name="price" maxlength="50" />--%>
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
    <%--$("#addForm").validate({--%>
      <%--rules : {--%>
        <%--revision : {--%>
          <%--required : true--%>
        <%--},--%>
        <%--price : {--%>
          <%--required : true--%>
        <%--}--%>
      <%--},--%>
      <%--messages : {--%>
        <%--revision : {--%>
          <%--required : '请输入版次'--%>
        <%--},--%>
        <%--price : {--%>
          <%--required : '请输入价格'--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>
  <%--});--%>
<%--</script>--%>

<form class="am-form" id="addRevisionForm" name="addRevisionForm" method="post">
  <input type="hidden" name="teachMaterialId" value="${param.tmId}">
  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >版次：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入版次" required id="add_revision" name="revision" />
    </div>
    <div class="am-u-sm-5">*必填，不可重复</div>
  </div>
  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >教材价格：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="number" placeholder="输入价格" required id="add_price" name="price" />
    </div>
    <div class="am-u-sm-5">*必填</div>
  </div>
</form>