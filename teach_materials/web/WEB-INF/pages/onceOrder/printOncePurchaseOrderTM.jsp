<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <%@ include file="/common/meta.jsp"%>
  <%@ include file="/common/taglibs.jsp"%>
</head>
<body>
<div class="main-content">
  <table class="table_slist" cellpadding="0" cellspacing="0" width="100%">
    <tr>
      <th style="width: 5%;">序号</th>
      <th style="width: 10%;">学院</th>
      <th style="width: 10%;">教材名称</th>
      <th style="width: 10%;">作者</th>
      <th style="width: 10%;">出版社</th>
      <th style="width: 10%;">ISBN</th>
      <th style="width: 10%;">价格</th>
      <th style="width: 5%;">数量</th>
      <th style="width: 10%;">学生订单所需数量</th>
      <th style="width: 10%;">预订单所需数量</th>
      <th style="width: 5%;">现有库存</th>
      <th style="width: 5%;">库存差</th>
    </tr>
    <c:if test="${empty resultJson}">
      <tr>
        <td colspan="20" align="center" style="color: #ff000c;">没有找到相关数据</td>
      </tr>
    </c:if>
    <c:forEach var="result" items="${resultJson}" varStatus="status">
      <tr>
        <td>${status.index+1}</td>
        <td>${result.deptName}</td>
        <td>${result.tmName}</td>
        <td>${result.author}</td>
        <td>${result.pressName}</td>
        <td>${result.isbn}</td>
        <td>${result.price}</td>
        <td>${result.tmCount}</td>
        <td>${result.sCount}</td>
        <td>${result.pCount}</td>
        <td>${result.stock}</td>
        <td>${result.stock - result.pCount - result.sCount - result.tmCount}</td>
      </tr>
    </c:forEach>
  </table>
  <br />
  <a class="com_btn_b" href="#" onclick="print()"><span>打&nbsp;印</span></a>
</div>
</body>
</html>
<script>
  $(function(){
    var height = getWindowHeightSize();
    $(".main-content").height(height * 0.9);
  });
</script>