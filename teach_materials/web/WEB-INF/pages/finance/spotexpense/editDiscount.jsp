<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div id="searchStudent" class="main-content">--%>
  <%--<form id="editForm" name="editForm" action="${pageContext.request.contextPath}/editSpotExpenseDiscountByID/editDiscount.htm" method="post">--%>
    <%--<br />--%>
    <%--<br/>--%>
    <%--<input type="hidden" id="id" name="id" value="${spotExpense.id}">--%>
    <%--折扣：<input type="text" id="discount" name="discount" value="${spotExpense.discount}">--%>
  <%--</form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function sub(obj){--%>
    <%--var discount = $("#discount").val();--%>
    <%--if(isNaN(discount)){--%>
      <%--alert("请输入正确的折扣");--%>
      <%--return false;--%>
    <%--}--%>
    <%--if(discount > 100){--%>
      <%--alert("折扣不能大于100");--%>
      <%--return false;--%>
    <%--}--%>
    <%--$.ajax({--%>
      <%--cache: true,--%>
      <%--type: "POST",--%>
      <%--url:"${pageContext.request.contextPath}/editSpotExpenseDiscountByID/editDiscount.htm",--%>
      <%--data:$('#editForm').serialize(),--%>
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

<div class="am-alert am-alert-warning" data-am-alert>
  <button type="button" class="am-close">&times;</button>
  <h3>折扣注意事项</h3>
  <ul>
    <li>输入90，即9折；输入85，即85折；只能输入100以内的整数，不输入即代表不打折。</li>
  </ul>
</div>
<form class="am-form" id="editForm" method="post">
  <input type="hidden" id="id" name="id" value="${spotExpense.id}">
  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >折扣：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入折扣" required id="discount" name="discount" value="${spotExpense.discount}" />
    </div>
    <div class="am-u-sm-5">*必填</div>
  </div>
</form>
