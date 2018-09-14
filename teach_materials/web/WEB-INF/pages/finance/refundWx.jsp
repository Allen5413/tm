<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form class="am-form" id="addForm" name="addForm" method="post">
  <div class="am-g am-margin-top">
    <div class="am-u-sm-2 am-text-right"><label >退费金额：</label></div>
    <div class="am-u-sm-4" style="float:left">
      <input class="am-input-sm" type="number" placeholder="退费金额" min="0" required id="money" name="money" style="width: 130px" />
    </div>
    <div class="am-u-sm-4">*必填，单位“元”</div>
  </div>
</form>