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
  <c:if test="${empty orderList}">
    没有找到相关数据
  </c:if>
  <c:if test="${!empty orderList}">
    <section class="bg-f4-pt pm-top-40">
      <div class="auto w order-info-list">
        <c:forEach var="json" items="${orderList}">
            <div class="mod-order" onclick="searchDetail('${json.flag}', '${json.orderCode}', ${json.orderId})">
              <div class="tit-wt">订单状态 <span class="green">${json.state}</span></div>
              <div class="info">
                <p><span class="tg">订单时间</span>${json.semester}</p>
                <p><span class="tg">订单号</span>${json.orderCode}</p>
                <p><span class="tg">数量</span>${json.count}</p>
                <p><span class="tg">金额</span><em class="org">${json.money}</em></p>
                <c:if test="${!empty json.kuaidi}">
                  <p><span class="tg">最新快递</span><em class="org">${json.kuaidi}</em></p>
                </c:if>
              </div>
            </div>
        </c:forEach>
      </div>
    </section>
  </c:if>
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

  function searchDetail(flag, orderCode, orderId){
    if(0 == flag || "0" == flag){
      location.href = "${pageContext.request.contextPath}/wxSearch/orderDetail.htm?code="+orderCode;
    }
    if(1 == flag || "1" == flag){
      location.href = "${pageContext.request.contextPath}/wxSearch/onceOrderDetail.htm?orderId="+orderId;
    }
  }
</script>

