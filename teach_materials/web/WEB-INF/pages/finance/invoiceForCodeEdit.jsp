<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="main-content">--%>
  <%--<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/editInvoiceForCode/editor.htm" method="post">--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--发票号：<input type="text" id="code" name="code" value="${code}" />--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--开票时间：<input type="text" id="openTime" name="openTime" class="Wdate" style="width: 110px;" onfocus="WdatePicker({readOnly:true})" value="${fn:substring(openTime,0,10)}"/>--%>
      <%--</li>--%>
    <%--</ul>--%>
  <%--</form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function sub(obj){--%>
    <%--var code = $.trim($("#code").val());--%>
    <%--var openTime = $("#openTime").val();--%>
    <%--if(code == "" && openTime == ""){--%>
      <%--alert("发票号和开票时间不能都为空！");--%>
      <%--return false;--%>
    <%--}--%>
    <%--$.ajax({--%>
      <%--cache: true,--%>
      <%--type: "POST",--%>
      <%--url:"${pageContext.request.contextPath}/editInvoiceForCode/editor.htm",--%>
      <%--data:{"id":${param.id}, "code":code, "openTime":openTime},--%>
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

<form class="am-form" id="editForm" method="post">
  <input type="hidden" id="id" name="id" value="${param.id}"/>
  <input type="hidden" id="canMoney" value="${canMoney}"/>
  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >发票号：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入发票号" required id="edit_code" name="code" value="${code}" />
    </div>
    <div class="am-u-sm-5">*必填，不能重复</div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >开票时间：</label></div>
    <div class="am-u-sm-4">
      <input type="text" id="openTime" name="openTime" class="Wdate" style="width: 110px; height: 28px;" onfocus="WdatePicker({readOnly:true})" value="${fn:substring(openTime,0,10)}"/>
    </div>
    <div class="am-u-sm-5">*必填</div>
  </div>
</form>
