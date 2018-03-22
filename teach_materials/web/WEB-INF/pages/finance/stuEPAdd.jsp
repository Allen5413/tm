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
<%--<div class="main-content">--%>
<%--<form id="addForm" name="addForm" method="post">--%>
  <%--<div class="main-content">--%>
    <%--<ul class="create_info_list">--%>
      <%--<c:choose>--%>
        <%--<c:when test="${!empty student}">--%>
          <%--<input type="hidden" id="studentCode" name="studentCode" value="${student.code}"/>--%>
          <%--<li>--%>
            <%--<label class="lab_80" style="width: 90px;">学号：</label>${student.code}--%>
          <%--</li>--%>
          <%--<li>--%>
            <%--<label class="lab_80" style="width: 90px;">姓名：</label>${student.name}--%>
          <%--</li>--%>
        <%--</c:when>--%>
        <%--<c:otherwise>--%>
          <%--<li>--%>
            <%--<label class="lab_80" style="width: 90px;">学号：</label>${student.code}--%>
            <%--<input type="text" class="input_240" id="studentCode" name="studentCode" style="width: 110px;"/>--%>
            <%--<span style="color:#ff0000">*</span>--%>
          <%--</li>--%>
        <%--</c:otherwise>--%>
      <%--</c:choose>--%>
      <%--<li>--%>
        <%--<label class="lab_80" style="width: 90px;">缴费金额：</label>--%>
        <%--<input type="text" class="input_240" id="money" name="money" style="width: 110px;"/>元--%>
        <%--<span style="color:#ff0000">*</span>--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80" style="width: 90px;">到账时间：</label>--%>
        <%--<input type="hidden" id="arrivalTime" name="arrivalTime"/>--%>
        <%--<input type="text" id="wdate" name="wdate" class="Wdate" style="width: 110px;" onfocus="WdatePicker({readOnly:true})"/>--%>
        <%--<span style="color:#ff0000">*</span>--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80" style="width: 90px;">到账类型：</label>--%>
        <%--<input type="radio" name="payType" value="0">网银--%>
        <%--<input type="radio" name="payType" checked="checked" value="1">转账--%>
        <%--<input type="radio" name="payType" value="2">现金--%>
        <%--<input type="radio" name="payType" value="3">其它--%>
        <%--<span style="color:#ff0000">*</span>--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80" style="width: 90px;">备注：</label>--%>
        <%--<textarea rows="5" cols="40" name="remark"></textarea>--%>
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
  <%--function sub(){--%>
    <%--if($("#studentCode").val().trim() == ""){--%>
      <%--Dialog.alert("请输入学号");--%>
    <%--}--%>
    <%--else if(!vaild.vaildMoney($("#money").val())){--%>
      <%--Dialog.alert("请输入正确的金额");--%>
    <%--}--%>
    <%--else if($("#wdate").val().trim() == ""){--%>
      <%--Dialog.alert("请选择到账时间");--%>
    <%--}else{--%>
      <%--$("#arrivalTime").val($("#wdate").val()+" 00:00:00")--%>
      <%--$.ajax({--%>
        <%--cache: true,--%>
        <%--type: "POST",--%>
        <%--url:"${pageContext.request.contextPath}/addStuEP/stuEPAdd.htm",--%>
        <%--data:$('#addForm').serialize(),--%>
        <%--async: false,--%>
        <%--success: function(data) {--%>
          <%--if(data.state == 0){--%>
            <%--Dialog.alert("提交成功！", function(){--%>
              <%--parent.$("#subBut").click();--%>
              <%--parentDialog.close();--%>
            <%--});--%>
          <%--}else{--%>
            <%--Dialog.alert(data.msg);--%>
          <%--}--%>
        <%--}--%>
      <%--});--%>
    <%--}--%>
  <%--}--%>
<%--</script>--%>


<form class="am-form" id="addForm" name="addForm" method="post">
  <c:choose>
    <c:when test="${!empty student}">
      <input type="hidden" id="addPay_studentCode" name="studentCode" value="${student.code}"/>
      <div class="am-g am-margin-top">
        <div class="am-u-sm-2 am-text-right"><label >学号：</label></div>
        <div class="am-u-sm-4" style="float:left">${student.code}</div>
      </div>

      <div class="am-g am-margin-top">
        <div class="am-u-sm-2 am-text-right"><label >姓名：</label></div>
        <div class="am-u-sm-4" style="float:left">${student.name}</div>
      </div>
    </c:when>
    <c:otherwise>
      <div class="am-g am-margin-top">
        <div class="am-u-sm-2 am-text-right"><label >学号：</label></div>
        <div class="am-u-sm-4" style="float:left">
          <input class="am-input-sm" type="text" id="addPay_studentCode2" name="studentCode" placeholder="输入学号" style="width: 130px" required />
        </div>
        <div class="am-u-sm-4">*必填</div>
      </div>
    </c:otherwise>
  </c:choose>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-2 am-text-right"><label >交费金额：</label></div>
    <div class="am-u-sm-4" style="float:left">
      <input class="am-input-sm" type="number" placeholder="交费金额" min="0" required id="money" name="money" style="width: 130px" />
    </div>
    <div class="am-u-sm-4">*必填，单位“元”</div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-2 am-text-right"><label >到账时间：</label></div>
    <div class="am-u-sm-4" style="float:left">
      <input type="hidden" id="arrivalTime" name="arrivalTime"/>
      <input type="text" placeholder="到账时间" id="wdate" name="wdate" class="Wdate" style="width: 130px; height: 28px;" onfocus="WdatePicker({readOnly:true})"/>
    </div>
    <div class="am-u-sm-4">*必选</div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-2 am-text-right"><label >到账类型：</label></div>
    <div class="am-u-sm-6">
      <div class="am-btn-group" data-am-button="">
        <label class="am-btn am-btn-primary am-btn-s">
          <input type="radio" name="payType" value="1"> 转账
        </label>
        <label class="am-btn am-btn-primary am-btn-s">
          <input type="radio" name="payType" value="0"> 网银
        </label>
        <label class="am-btn am-btn-primary am-btn-s">
          <input type="radio" name="payType" value="2"> 现金
        </label>
        <label class="am-btn am-btn-primary am-btn-s">
          <input type="radio" name="payType" value="3"> 其它
        </label>
      </div>
    </div>
    <div class="am-u-sm-4">*必选</div>
  </div>

  <div class="am-g am-margin-top-sm">
    <div class="am-u-sm-2 am-text-right"><label >备注：</label></div>
    <div class="am-u-sm-10">
      <textarea rows="6" placeholder="输入交费备注" name="remark"></textarea>
    </div>
  </div>
</form>