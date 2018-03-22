<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <%@ include file="/common/taglibs.jsp"%>
</head>
<style>
  .print_content{
    width:1000px;
    min-height: 740.6px;
    margin:0 auto;
    font-size:12px;
    padding:10px 0;
    text-align:left;
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
  .print_content .line{
    text-decoration:underline;
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
    width:230px;
    position:relative;
    top:5px;
  }
  .alignRight{
    text-align:right !important;
  }
</style>
<body onload="setHeight()">
  <div id="num_${status.index}" class="print_content">
    <p>订单号：<span class="line">${param.orderCode}</span></p>
    <p>学习中心：<span class="line">（${spot.code}）${spot.name}</span></p>
    <p>收货地址：<span class="line">${address}</span></p>
    <p>收件人：<span class="line">${adminName}</span></p>
    <div class="print_table">
      <table width="100%" cellpadding="0" cellspacing="0">
        <tr>
          <th style="width: 30px;">序号</th>
          <th>课程名称</th>
          <th style="width: 300px;">教材名称</th>
          <th style="width: 60px;">教材数量</th>
          <th style="width: 60px;">教材价格</th>
          <th style="width: 60px;">操作人</th>
          <th style="width: 80px;">操作时间</th>
        </tr>
        <c:forEach var="placeOrderDetailShow" items="${placeOrderDetailShowList}" varStatus="status">
          <tr>
            <td>${status.index + 1}</td>
            <td>
              <c:if test="${!empty placeOrderDetailShow.courseCode}">【${placeOrderDetailShow.courseCode}】${placeOrderDetailShow.courseName}</c:if>
            </td>
            <td>${placeOrderDetailShow.materialName}</td>
            <td>${placeOrderDetailShow.count}</td>
            <td>${placeOrderDetailShow.materialPrice}</td>
            <td>${placeOrderDetailShow.creator}</td>
            <td><fmt:formatDate value="${placeOrderDetailShow.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
          </tr>
        </c:forEach>
        <tr>
          <td></td>
          <td></td>
          <td></td>
          <td align="right">合计：</td>
          <td>${sumTotalPrice}</td>
          <td></td>
          <td></td>
        </tr>
      </table>
    </div>
  </div>
</body>
</html>
<script>
  function setHeight(){
  <c:forEach var="result" items="${resultJson}" varStatus="status">
    var divHeight = $("#num_${status.index}").height();
    if((divHeight / 740.6).toFixed(2) > 1){
      $("#num_${status.index}").css("min-height", 740.6 * parseInt(divHeight / 740.6 + 1));
    }
  </c:forEach>
  }
</script>