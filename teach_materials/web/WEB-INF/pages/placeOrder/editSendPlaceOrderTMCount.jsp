<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%--<!DOCTYPE html>--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="main-content">--%>
  <%--<form id="editForm" method="post">--%>
    <%--<input type="hidden" name="semesterId" value="${param.semesterId}" />--%>
    <%--<input type="hidden" name="spotCodes" value="${param.spotCodes}" />--%>
    <%--<input type="hidden" name="tmId" value="${param.tmId}" />--%>
    <%--<input type="hidden" name="beginDate" value="${param.beginDate}" />--%>
    <%--<input type="hidden" name="endDate" value="${param.endDate}" />--%>
    <%--<input type="hidden" name="price" value="${param.price}" />--%>
    <%--<input type="hidden" name="orderCode" value="${param.orderCode}" />--%>

    <%--<div class="main-content">--%>
      <%--<ul class="create_info_list">--%>
        <%--<li>--%>
          <%--<label class="lab_80 left">数量：</label>--%>
          <%--<input type="text" class="input_240" name="newCount" maxlength="50" />--%>
          <%--<div class="clear"></div>--%>
        <%--</li>--%>
        <%--<li>--%>
          <%--<label class="lab_80 left">--%>
            <%--<a class="com_btn_b" href="#" onclick="sub()"><span>提&nbsp;交</span></a>--%>
          <%--</label>--%>
        <%--</li>--%>
      <%--</ul>--%>
    <%--</div>--%>
  <%--</form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--$(function(){--%>
    <%--//对数据的检测--%>
    <%--$("#editForm").validate({--%>
      <%--rules : {--%>
        <%--newCount : {--%>
          <%--required : true--%>
        <%--}--%>
      <%--},--%>
      <%--messages : {--%>
        <%--newCount : {--%>
          <%--required : '请输入数量'--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>
  <%--});--%>

  <%--function sub(){--%>
    <%--if($("#editForm").valid()){--%>
      <%--$.ajax({--%>
        <%--cache: true,--%>
        <%--type: "POST",--%>
        <%--url:"${pageContext.request.contextPath}/editSendPlaceOrderTMCount/editor.htm",--%>
        <%--data:$('#editForm').serialize(),--%>
        <%--async: false,--%>
        <%--success: function(data) {--%>
          <%--if(data.state == 0){--%>
            <%--parent.$('#index_iframe').contents().find("#subBut").click();--%>
            <%--alert("提交成功！");--%>
            <%--parentDialog.close();--%>
          <%--}else{--%>
            <%--alert(data.msg);--%>
          <%--}--%>
        <%--}--%>
      <%--});--%>
    <%--}--%>
  <%--}--%>
<%--</script>--%>



<form class="am-form" id="editForm" method="post">
  <input type="hidden" name="semesterId" value="${param.semesterId}" />
  <input type="hidden" name="spotCodes" value="${param.spotCodes}" />
  <input type="hidden" name="tmId" value="${param.tmId}" />
  <input type="hidden" name="beginDate" value="${param.beginDate}" />
  <input type="hidden" name="endDate" value="${param.endDate}" />
  <input type="hidden" name="price" value="${param.price}" />
  <input type="hidden" name="orderCode" value="${param.orderCode}" />
  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >数量：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="number" placeholder="输入数量" required id="edit_newCount" name="newCount" />
    </div>
    <div class="am-u-sm-5">*必填</div>
  </div>
</form>