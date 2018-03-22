<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<style>
  .print_content{
    width: 1000px;
    min-height: 740.6px;
    margin: 0 auto;
    font-size: 12px;
    padding: 10px 0;
    text-align: left;
    position: relative;
  }
  h1,h2,h3,h4,h5,h6{
    font-size:12px;
    font-weight:normal;
    margin:0;
    padding:0;
  }
  .print_content h6{
    font-size: 16px;
    font-weight: bold;
    padding: 10px 10px 30px;
    text-align: center;
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
    float: right;
    width: 450px;
    position: absolute;
    top: 50px;
    right: 0;
    text-align:right;
  }
  .alignRight{
    text-align:right !important;
  }
  .code-img{display:inline-block; width:; text-align:center; margin-right:20px;}
  .code-img p{margin:0; padding:2px 0 0;}
</style>
<body  onload="setHeight()">
<c:forEach var="result" items="${resultJson}" varStatus="status">
  <div id="num_${status.index}" class="print_content">
    <div class="bar_code">
      <c:if test="${'1' eq param.flag}">
        <div class="code-img"><img src="${pageContext.request.contextPath}/images/xwgzh.jpg" /><p>扫描上方二维码，关注西网公众号</p></div>
        <img style="width: 220px; height: 96px;" src="${pageContext.request.contextPath}/barcode.do?type=code128&msg=${result.value.orderCode}&qz=disable&mw=1&height=65&hrp=bottom&hrsize=35pt&fmt=png&res=60" />
      </c:if>
    </div>
    <h6 >${semester.year}年 ${semester.quarter == 0 ? '春':'秋'}季教材发书清单</h6>
    <p>学习中心：<span class="line">（${result.value.spotCode}）${result.value.spotName}</span></p>
    <p>专业：<span class="line">${result.value.specName}</span></p>
    <p>姓名： <span  class="line">${result.value.name}</span> &nbsp;	学号： <span class="line">${result.value.code}</span> &nbsp;	层次：${result.value.levelName}</p>
    <div class="print_table">
      <table width="100%" cellpadding="0" cellspacing="0">
        <tr>
          <th width="35">序号</th>
          <th>课程名称</th>
          <th>教材名称</th>
          <th width="90">作者</th>
          <th width="90">单价</th>
          <th width="60">数量</th>
          <th width="90">总价</th>
        </tr>
        <c:forEach var="tm" items="${result.value.orderTM}" varStatus="status2">
          <tr>
            <td>${status2.index + 1}</td>
            <td>【${tm.courseCode}】${tm.courseName}</td>
            <td>${tm.tmName}</td>
            <td>${tm.author}</td>
            <td>${tm.price}</td>
            <td>${tm.count}</td>
            <td>${tm.totalPrice}</td>
          </tr>
        </c:forEach>
        <tr>
          <td colspan="4">${status.index+1}</td>
          <td class="alignRight">合计：</td>
          <td>${result.value.orderTmCount}</td>
          <td>${result.value.orderTotalPrice}</td>
        </tr>
      </table>
    </div>
  </div>
</c:forEach>
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
