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
  <%--<form id="editForm" name="editForm" action="${pageContext.request.contextPath}/editPlaceOrderPackageForLogisticCode/editor.htm" method="post">--%>
    <%--<input type="hidden" id="id" name="id" value="${param.id}"/>--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--物流单号：--%>
        <%--<textarea rows="10" cols="50" id="logisticCodes" name="logisticCodes">${placeOrderPackage.logisticCode}</textarea>--%>
      <%--</li>--%>
    <%--</ul>--%>
  <%--</form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function sub(obj){--%>
    <%--var logisticCodes = $("#logisticCodes").val().trim();--%>

    <%--if(logisticCodes == ""){--%>
      <%--alert("请输入物流单号");--%>
      <%--return false;--%>
    <%--}--%>
    <%--$.ajax({--%>
      <%--cache: true,--%>
      <%--type: "POST",--%>
      <%--url:"${pageContext.request.contextPath}/editPlaceOrderPackageForLogisticCode/editor.htm",--%>
      <%--data:$('#editForm').serialize(),--%>
      <%--async: false,--%>
      <%--success: function(data) {--%>
        <%--if(data.state == 0){--%>
          <%--obj.click();--%>
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
  <h3>修改物流单号注意事项</h3>
  <ul>
    <li>物流单号一定要准确无误，多个物流单号用“,”英文逗号隔开</li>
  </ul>
</div>
<form class="am-form" id="editForm" method="post">
  <input type="hidden" id="id" name="id" value="${param.id}"/>
  <div class="am-g am-margin-top-sm">
    <div class="am-u-sm-3 am-text-right"><label >物流单号：</label></div>
    <div class="am-u-sm-9">
      <textarea rows="10" cols="50" id="logisticCodes" name="logisticCodes">${placeOrderPackage.logisticCode}</textarea>
    </div>
  </div>
</form>
