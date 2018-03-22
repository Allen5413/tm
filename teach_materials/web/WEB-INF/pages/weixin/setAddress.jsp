<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
  <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
  <meta name="apple-mobile-web-app-capable" content="yes" />
  <meta name="apple-mobile-web-app-status-bar-style" content="black" />
  <meta content="black" name="apple-mobile-web-app-status-bar-style" />
  <%@ include file="/common/taglibs.jsp"%>
  <script src="${pageContext.request.contextPath}/script/flexible.js" type="text/javascript"></script>

  <link href="${pageContext.request.contextPath}/css/common_h5.css" rel="stylesheet" type="text/css" />
  <link href="${pageContext.request.contextPath}/css/xw.css" rel="stylesheet" type="text/css" />
</head>
<body>
<header>
  <div class="header w">
    <p class="tit">教材管理系统</p>
    <a href="#" class="close icon"></a>
    <a class="next t-user"></a>
  </div>
</header>
<section class="bg-f4-pt pm-top-40">
  <c:if test="${empty student || empty openId}">
    <div class="title">请先通过证件号码绑定学生信息</div>
  </c:if>
  <c:if test="${!empty student}">
    <div class="auto w zf-basic-info">
      <ul class="list-info">
        <li>
          <div class="col-1">学号：</div>
          <div class="col-2">${student.code}</div>
        </li>
        <li>
          <div class="col-1">姓名：</div>
          <div class="col-2">${student.name}</div>
        </li>
      </ul>
      <div class="title">请填写邮寄地址信息</div>
      <form class="form-1">
        <ul class="list-info">
          <c:set var="addressArray" value="${fn:split(student.sendAddress, '|')}"/>
          <c:set var="addressArrayLength" value="${fn:length(addressArray)}"/>
          <c:if test="${addressArrayLength == 3}">
            <c:set var="province" value="${addressArray[0]}"/>
            <c:set var="city" value="${addressArray[1]}"/>
            <c:set var="sendAddress" value="${addressArray[2]}"/>
          </c:if>
          <c:if test="${addressArrayLength == 1}">
            <c:set var="sendAddress" value="${addressArray[0]}"/>
          </c:if>
          <li><label>手机号码：<input type="text" id="phone" placeholder="点击输入" value="${student.sendPhone}" /></label></li>
          <li><label>邮政编码：<input type="text" id="postalCode" placeholder="点击输入" value="${student.sendZipCode}" /></label></li>
          <li><label>邮寄地址/省：<input type="text" id="province" placeholder="点击输入" value="${province}" /></label></li>
          <li><label>市：<input type="text" id="city" placeholder="点击输入" value="${city}" /></label></li>
          <li><label>详细地址：<input type="text" id="sendAddress" placeholder="点击输入" value="${sendAddress}" /></label></li>
        </ul>
        <div class="btn"><a href="#" onclick="sub()">提交</a></div>
      </form>
    </c:if>
  </div>
</section>
</body>
</html>
<script>
  $(document).ready(function(){
    $(".form-1 > .list-info > li").click(function(){
      $(this).siblings().removeClass("selected").end().addClass('selected');
    });
  });

  function sub(){
    var phone = $("#phone").val();
    var postalCode = $("#postalCode").val();
    var province = $("#province").val();
    var city = $("#city").val();
    var sendAddress = $("#sendAddress").val();
    if(phone == "" || phone.length != 11){
      alert("请输入正确的手机号码");
      return;
    }
    if(postalCode == ""){
      alert("请输入邮政编码");
      return;
    }
    if(province == ""){
      alert("请输入省");
      return;
    }
    if(city == ""){
      alert("请输入市");
      return;
    }
    if(sendAddress == ""){
      alert("请输入详细地址");
      return;
    }
    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/wxAddress/editor.htm",
      data:{"code":"${student.code}", "phone":phone, "postalCode":postalCode, "province":province, "city":city, "sendAddress":sendAddress},
      async: false,
      success: function(data) {
        if(data.state == 0){
          alert("操作成功。");
        }else{
          alert(data.msg);
        }
      }
    });
  }
</script>