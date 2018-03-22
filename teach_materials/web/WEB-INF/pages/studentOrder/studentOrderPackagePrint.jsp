<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery/jquery-1.9.1.js" charset="utf-8"></script>
  <style>
    .print_content{
      width:1000px;
      height: 500px;
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
    .order_list{float:right; width:480px;}
    .print_content:after{clear:both; content:""; display:block;}
    .info_order{float:left; width:500px;}
  </style></head>
<body>
  <div class="print_content">
    <div class="order_list">
      <table id="studentInfo" style="font-size: 20px;" cellspacing="0" cellpadding="0" width="500" >
        <tr>
          <th width="60">序号</th>
          <th width="150">订单号</th>
          <th width="150">学号</th>
          <th width="130">姓名</th>
        </tr>
        <c:forEach var="studentBookOrderMap" items="${studentBookOrderMap}" varStatus="status">
          <tr>
            <td>${status.index+1}</td>
            <td>${studentBookOrderMap.key.orderCode}</td>
            <td>${studentBookOrderMap.key.studentCode}</td>
            <td>${studentBookOrderMap.value}</td>
          </tr>
        </c:forEach>
      </table>
    </div>
    <div class="info_order">
      <p><span class="line" style="font-size: 20px; font-weight: bold">(${spot.code})${spot.name}</span></p>
      <p style="margin-left: 100px; font-size: 18px;">编号：<span class="line">${studentBookOrderPackage.code}</span></p>
      <p style="margin-left: 100px; font-size: 18px;">电话：<span class="line">${studentBookOrderPackage.phone}</span></p>
      <p style="margin-left: 100px; font-size: 18px;">邮编：<span class="line">${studentBookOrderPackage.zipCode}</span></p>
      <p style="margin-left: 100px; font-size: 18px;">地址：<span class="line">${studentBookOrderPackage.address}</span></p>
      <p style="margin-left: 100px; font-size: 18px;">快递单号：<span class="line">${studentBookOrderPackage.logisticCode}</span></p>
      <p style="margin-left: 40px; font-size: 100px; font-weight: bold">第${studentBookOrderPackage.sort}件</p>
    </div>
  </div>
</body>
</html>
