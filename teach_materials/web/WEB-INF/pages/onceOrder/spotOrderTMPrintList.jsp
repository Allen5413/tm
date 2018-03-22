<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><HTML
xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  <META content="IE=11.0000" http-equiv="X-UA-Compatible">
  <META http-equiv="Content-Type" content="text/html; charset=utf-8">
    <STYLE>
      .print_content{
        width: 1000px;
        min-height: 794px;
        margin: 0 auto;
        font-size: 12px;
        text-align: left;
        position: relative;
      }
      .text-content{padding:10px 0;}
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
    </STYLE>
  <META name="GENERATOR" content="MSHTML 11.00.10570.1001"></HEAD>
<body  >
<c:forEach var="result" items="${resultJson}" varStatus="status">
  <div id="num_${status.index}" class="print_content">
    <div class="text_content">
      <div class="bar_code">
        <c:if test="${'1' eq param.flag}">
          <div class="code-img"><img src="${pageContext.request.contextPath}/images/xwgzh.jpg" /><p>扫描上方二维码，关注西网公众号</p></div>
          <img style="width: 220px; height: 96px;" src="${pageContext.request.contextPath}/barcode.do?type=code128&msg=${result.value.orderCode}&qz=disable&mw=1&height=65&hrp=bottom&hrsize=35pt&fmt=png&res=60" />
        </c:if>
      </div>
      <h6 style="margin-left: 160px;">${semester.year}年 ${semester.quarter == 0 ? '春':'秋'}季教材发书清单</h6>
      <p>学习中心：<span class="line">（${spot.code}）${spot.name}</span></p>
      <p>专业：<span class="line">${result.value.specName}</span></p>
      <p>姓名： <span  class="line">${result.value.name}</span> &nbsp;	学号： <span class="line">${result.value.code}</span> &nbsp;	层次：${result.value.levelName}</p>
      <div class="print_table">
        <table width="100%" cellpadding="0" cellspacing="0">
          <TBODY>
          <tr>
            <th width="35">序号</th>
            <th>课程名称</th>
            <th>教材名称</th>
            <th width="90">作者</th>
            <th width="90">单价</th>
            <th width="60">数量</th>
            <th width="90">总价</th>
          </tr>
          <c:forEach var="tm" items="${result.value.orderTM}" varStatus="status">
            <tr>
              <td>${status.index + 1}</td>
              <td>【${tm.courseCode}】${tm.courseName}</td>
              <td>${tm.tmName}</td>
              <td>${tm.author}</td>
              <td>${tm.price}</td>
              <td>${tm.count}</td>
              <td>${tm.totalPrice}</td>
            </tr>
          </c:forEach>
          <tr>
            <td class="alignRight" colspan="5">合计：</td>
            <td>${result.value.orderTmCount}</td>
            <td>${result.value.orderTotalPrice}</td>
          </tr>
          <TBODY>
        </table>
      </div>
    </div>
  </div>
</c:forEach>
</body>
</html>
