<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery/jquery-1.9.1.js" charset="utf-8"></script>
</head>
<style>
  .print_content{
    width:800px;
    height: 1139px;
    margin:10px auto;
    font-size:12px;
  }
  h1,h2,h3,h4,h5,h6{
    font-size:12px;
    font-weight:normal;
    margin:0;
    padding:0;
  }
  .print_content h6{
    font-size:16px;
    font-weight:bold;
    padding:10px;
    text-align:center;
  }
  .print_content p{
    font-size:12px;
    line-height:22px;
  }

  .print_content table{
    border-top:solid #888 1px;
    border-right:solid #888 1px;
  }
  .print_content th,.print_content td{
    border-left:solid #888 1px;
    border-bottom:solid #888 1px;
    padding:7px;
    text-align:left;
  }
  .bar_code{
    float:right;
    width:340px;
    position:relative;
    top:-5px;
  }
  .alignRight{
    text-align:right !important;
  }
</style>
<body>
<c:forEach var="logisticCode" items="${logisticCodes}" varStatus="status">
  <div class="print_content">
    <div class="bar_code">
      <table id="studentInfo" width="300" cellpadding="0" cellspacing="0" style="font-size: 16px;">
        <tr>
          <th width="50">序号</th>
          <th width="150">预订单号</th>
        </tr>
        <c:set var="address" value=""/>
        <c:set var="adminName" value=""/>
        <c:set var="phone" value=""/>
        <c:set var="tel" value=""/>
        <c:set var="postalCode" value=""/>
        <c:forEach var="placeOrder" items="${placeOrderList}" varStatus="status2">
          <c:set var="address" value="${placeOrder.address}"/>
          <c:set var="adminName" value="${placeOrder.adminName}"/>
          <c:set var="phone" value="${placeOrder.phone}"/>
          <c:set var="tel" value="${placeOrder.tel}"/>
          <c:set var="postalCode" value="${placeOrder.postalCode}"/>
          <tr>
            <td>${status2.index+1}</td>
            <td>${placeOrder.orderCode}</td>
          </tr>
        </c:forEach>
      </table>
    </div>
    <p><span class="line" style="font-size: 20px; font-weight: bold">(${spot.code})${spot.name}</span></p>
    <p style="margin-left: 100px; font-size: 18px;">包裹单号：<span class="line">${placeOrderPackage.code}</span></p>
    <p style="margin-left: 100px; font-size: 18px;">邮寄地址：<span class="line">${address}</span></p>
    <p style="margin-left: 100px; font-size: 18px;">收件人：<span class="line">${adminName}</span></p>
    <p style="margin-left: 100px; font-size: 18px;">手机：<span class="line">${phone}</span></p>
    <p style="margin-left: 100px; font-size: 18px;">座机：<span class="line">${tel}</span></p>
    <p style="margin-left: 100px; font-size: 18px;">邮政编码：<span class="line">${postalCode}</span></p><p style="margin-left: 100px; font-size: 18px;">快递编号：<span class="line">${logisticCode}</span></p>
    <p style="margin-left: 40px; font-size: 100px; font-weight: bold">第${placeOrderPackage.sort - logisticCodesLength + 1 + status.index}件</p>
  </div>
</c:forEach>
</body>
</html>
