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
    width:1000px;
    height: 740.6px;
    margin:0 auto;
    font-size:12px;
    padding:10px 0;
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
    border-top:solid #888 0px;
    border-right:solid #888 0px;
    font-size: 18px;
  }
  .print_content th,.print_content td{
    border-left:solid #888 0px;
    border-bottom:solid #888 0px;
    padding:7px;
    text-align:left;
  }
  .bar_code{
    float:right;
    width:300px;
    position:relative;
    top:50px;
  }
  .alignRight{
    text-align:right !important;
  }
</style>
<body>
  <div class="print_content">
    <div class="print_table">
      <table width="100%" cellpadding="0" cellspacing="0">
        <tr>
          <th colspan="2">中心编号</th>
          <th></th>
          <th></th>
          <th></th>
          <th></th>
          <th></th>
          <th></th>
        </tr>
        <tr>
          <th>【${spot.code}】</th>
          <th></th>
          <th></th>
          <th></th>
          <th></th>
          <th></th>
          <th></th>
          <th></th>
        </tr>
        <tr>
          <th colspan="2">${spot.postalCode}</th>
          <th></th>
          <th></th>
          <th></th>
          <th></th>
          <th></th>
          <th style="text-align: right">印刷品</th>
        </tr>
        <tr>
          <th colspan="8" style="text-align: center">${spot.address}</th>
        </tr>
        <tr>
          <th></th>
          <th></th>
          <th></th>
          <th></th>
          <th style="text-align: right" colspan="2">${spot.adminName}收</th>
          <th></th>
          <th></th>
        </tr>
        <tr>
          <th colspan="3">
            TEL:
            <c:if test="${spot.phone eq spot.tel}">${spot.phone}</c:if>
            <c:if test="${spot.phone ne spot.tel}">${spot.phone}、${spot.tel}</c:if>
          </th>
          <th></th>
          <th></th>
          <th></th>
          <th></th>
          <th></th>
        </tr>
        <tr>
          <th></th>
          <th></th>
          <th></th>
          <th></th>
          <th></th>
          <th colspan="3" style="text-align: right">重庆西网文化传播有限公司</th>
        </tr>
        <tr>
          <th></th>
          <th></th>
          <th></th>
          <th></th>
          <th></th>
          <th></th>
          <th></th>
          <th style="text-align: right">400715</th>
        </tr>
        <tr style="height: 250px">
          <th colspan="999">
            <p style="margin-left: 100px; font-size: 18px;">学生人数：</p>
            <p style="margin-left: 100px; font-size: 18px;">总册数：</p>
            <p style="margin-left: 100px; font-size: 18px;">总件数：</p>
          </th>
        </tr>
      </table>
    </div>
  </div>
</body>
</html>

