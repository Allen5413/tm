<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

  <script src="${pageContext.request.contextPath}/script/flexible.js" type="text/javascript"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery/jquery-1.9.1.js" charset="utf-8"></script>

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
  <div class="auto w order-info-list">
    <c:forEach var="result" items="${resultJson}" varStatus="status">
    <div class="mod-order">
      <c:if test="${0 == status.index}">
        <div class="title">订单号：${param.code}</div>
      </c:if>
      <div class="info">
        <p><span class="tg">课程名称</span>${result.courseCode} ${result.courseName}</p>
        <p><span class="tg">教材名称</span>${result.name}</p>
        <p><span class="tg">数量</span>${result.count}</p>
        <p><span class="tg">单价</span>${result.price}</p>
      </div>
    </div>
    </c:forEach>
  </div>
</section>
<div class="auto w zf-basic-info" style="height: 100px;">
  <div class="btn"><a href="javascript:confirmOrder()">确认订单</a></div>
</div>
</body>
</html>
<script type="text/javascript">
  $(document).ready(function(){
    $(".t-more").click(function(){
      $(".t-more-list").fadeIn();
    })
    $(document).click(function(){
      $(".t-more-list").fadeOut();
    });
    $(".t-more,.t-more-list").click(function(event){
      event.stopPropagation();
    });
  })

  function confirmOrder(){
    if(confirm("您确定要确认该订单？")){
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/wxConfirmStudentBookOrder/cf.htm",
        data:{"studentCode":"${param.studentCode}", "code":"${param.code}"},
        async: false,
        success: function(data) {
          if(data.state == 0){
            alert("操作成功。");
            location.href = "${pageContext.request.contextPath}/wxSearch/finance.htm?code=${param.studentCode}&isSpotUser=1";
          }else{
            alert(data.msg);
          }
        }
      });
    }
  }
</script>