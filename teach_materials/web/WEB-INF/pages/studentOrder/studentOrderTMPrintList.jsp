<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <%@ include file="/common/meta.jsp"%>
  <%@ include file="/common/taglibs.jsp"%>
</head>
<style>
  .print_content{
    width:1000px;
    height: 730px;
    margin:0 auto;
    font-size:12px;
    padding:10px 0;
    text-align: left;
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
    top:50px;
  }
  .alignRight{
    text-align:right !important;
  }
</style>
<body>

<c:forEach var="result" items="${resultJson}" varStatus="status">
  <div class="print_content">
    <div class="bar_code">
      <p>订单号：<span class="line">${result.value.orderCode}</span></p>
      <p>顺序号：<span class="line">${result.value.printSort}</span></p>
    </div>
    <h6 style="margin-left: 160px;">${result.value.year}年 ${result.value.quarter == 0 ? '春':'秋'}季教材发书清单</h6>
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
        <c:forEach var="tm" items="${result.value.orderTM}" varStatus="status">
          <tr>
            <td>${status.index + 1}</td>
            <td>【${tm.courseCode}】${tm.courseName}</td>
            <td>${tm.tmName}</td>
            <td>${tm.author}</td>
            <td>${tm.price}</td>
            <td>${tm.count}</td>
            <td>${tm.price * tm.count}</td>
          </tr>
        </c:forEach>
        <tr>
          <td class="alignRight" colspan="5">合计：</td>
          <td>${result.value.orderTmCount}</td>
          <td>${result.value.orderTotalPrice}</td>
        </tr>
        <tr>
          <td colspan="3"></td>
          <td class="alignRight">学生签字：</td>
          <td></td>
          <td class="alignRight">日期：</td>
          <td>
            <fmt:formatDate value="${nowDate}" pattern="yyyy-MM-dd" />
          </td>
        </tr>
      </table>
    </div>
  </div>
</c:forEach>
</body>
</html>

