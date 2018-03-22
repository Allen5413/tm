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
<section class="bg-f4-pt pm-top-40">
  <div class="auto w order-info-list">
    <div class="mod-order">
      <div class="title">入账记录</div>
      <ul class="record-list">
        <c:if test="${empty payList}">
          <li>
            <div class="col-5">没有找到相关数据</div>
          </li>
        </c:if>
        <c:if test="${!empty payList}">
          <c:forEach var="pay" items="${payList}" varStatus="status">
            <li>
              <div class="col-5"><fmt:formatDate value="${pay.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></div>
              <div class="col s-num">${pay.money}</div>
            </li>
          </c:forEach>
        </c:if>
      </ul>
    </div>
    <c:if test="${empty buyMap}">
      <div class="mod-order">
        <div class="title">没有找到相关记录</div>
      </div>
    </c:if>
    <c:if test="${!empty buyMap}">
      <c:forEach var="json" items="${buyMap}">
        <div class="mod-order">
          <div class="title">消费记录<span class="sit-rt">${json.key}</span></div>
          <ul class="record-list">
            <c:set var="totalMoney" />
            <c:forEach var="detail" items="${json.value}">
              <c:forEach var="spotBuy" items="${detail.value}">
                <li>
                  <div class="col-1">
                    <p class="f-1"><fmt:formatDate value="${spotBuy.createTime}" pattern="yyyy-MM-dd" /></p>
                    <p class="f-2"><fmt:formatDate value="${spotBuy.createTime}" pattern="HH:mm:ss" /></p>
                  </div>
                  <div class="col-2">${spotBuy.money}</div>
                  <div class="col-3">${spotBuy.detail}</div>
                </li>
              </c:forEach>
              <c:set var="totalMoney" value="${detail.key}" />
            </c:forEach>
          </ul>
          <div class="sum-right">合计：${totalMoney}</div>
        </div>
      </c:forEach>
  </c:if>
  </div>
</section>
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
</script>
