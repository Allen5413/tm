<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="main-content" style="height: 100%;">--%>
  <%--<form id="redCForm" name="redCForm" action="${pageContext.request.contextPath}/redCStuEP/redC" method="post">--%>
    <%--<input type="hidden" id="id" name="id" value="${param.id}" />--%>
    <%--备注：<input type="text" id="remark" name="remark">--%>
    <%--</form>--%>
  <%--</form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function sub(obj){--%>
    <%--var remark = $("#remark").val();--%>
    <%--if(remark == ""){--%>
      <%--alert("请填写红冲备注");--%>
      <%--return false;--%>
    <%--}--%>
    <%--$.ajax({--%>
      <%--cache: true,--%>
      <%--type: "POST",--%>
      <%--url:"${pageContext.request.contextPath}/redCStuEP/redC.htm",--%>
      <%--data:$('#redCForm').serialize(),--%>
      <%--async: false,--%>
      <%--success: function(data) {--%>
        <%--if(data.state == 0){--%>
          <%--obj.submit();--%>
          <%--alert("提交成功！");--%>
          <%--Dialog.close();--%>
        <%--}else{--%>
          <%--alert(data.msg);--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>
  <%--}--%>
<%--</script>--%>



<form class="am-form" id="redCForm" name="redCForm" method="post" data-am-validator>
  <input type="hidden" id="id" name="id" value="${param.id}" />
  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >金额：</label></div>
    <div class="am-u-sm-5">
      <input class="am-input-sm" type="text" placeholder="输入红冲金额" required id="money" name="money" value="${studentExpensePay.money}" />
    </div>
    <div class="am-u-sm-4">*必填</div>
  </div>
  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >备注：</label></div>
    <div class="am-u-sm-5">
      <input class="am-input-sm" type="text" placeholder="输入红冲备注" required id="remark" name="remark" />
    </div>
    <div class="am-u-sm-4">*必填</div>
  </div>
</form>
