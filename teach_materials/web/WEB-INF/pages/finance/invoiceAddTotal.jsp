<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="main-content">--%>
  <%--<form id="addForm" name="addForm" action="${pageContext.request.contextPath}/addInvoice/addTotal.htm" method="post">--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--[${spot.code}]${spot.name}--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<span style="color:#ff0000">*注：当前最多还能开票${canMoney}元</span><br />--%>
        <%--开票金额：<input type="text" id="money" name="money" />--%>
      <%--</li>--%>
    <%--</ul>--%>
  <%--</form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function sub(obj){--%>
    <%--var money = $("#money").val();--%>
    <%--if(!vaild.vaildMoney(money)){--%>
      <%--alert("开票金额格式错误！");--%>
    <%--}else{--%>
      <%--$.ajax({--%>
        <%--cache: true,--%>
        <%--type: "POST",--%>
        <%--url:"${pageContext.request.contextPath}/addInvoice/addTotal.htm",--%>
        <%--data:{"money" : money, "spotCode" : "${spot.code}"},--%>
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
  <%--}--%>
<%--</script>--%>

<form class="am-form" id="addForm" method="post">
  <div class="am-g am-margin-top">
    <div class="am-u-sm-8 am-text-right"><label >[${spot.code}]${spot.name}</label></div>
    <div class="am-u-sm-4"></div>
  </div>
  <div class="am-g am-margin-top">
    <div class="am-u-sm-2 am-text-right"><label >开票金额：</label></div>
    <div class="am-u-sm-3">
      <input class="am-input-sm" type="text" placeholder="输入开票金额" required id="add_total_money" name="money" />
    </div>
    <div class="am-u-sm-7">*当前最多还能开票${canMoney}元</div>
  </div>
</form>
