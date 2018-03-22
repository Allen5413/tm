<%--<%@ page language="java" contentType="text/html; charset=utf-8"--%>
         <%--pageEncoding="utf-8"%>--%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="main-content">--%>
  <%--<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findStuEPage/findStuEPageByWhere.htm" method="post">--%>
    <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
      <%--<tr>--%>
        <%--<th style="width: 5%;">序号</th>--%>
        <%--<th style="width: 15%;">学号</th>--%>
        <%--<th style="width: 10%;">姓名</th>--%>
        <%--<th style="width: 20%;">专业</th>--%>
        <%--<th style="width: 10%;">层次</th>--%>
        <%--<th style="width: 10%;">已缴费金额</th>--%>
        <%--<th style="width: 10%;">消费金额</th>--%>
        <%--<th style="width: 10%;">余额</th>--%>
        <%--<th>操作</th>--%>
      <%--</tr>--%>
      <%--<c:if test="${empty data }">--%>
        <%--<tr>--%>
          <%--<td colspan="20" align="center" style="color: red;">没有找到相关数据</td>--%>
        <%--</tr>--%>
      <%--</c:if>--%>
      <%--<c:forEach var="json" items="${data}" varStatus="status">--%>
        <%--<tr>--%>
          <%--<td align="center">${status.index+1}</td>--%>
          <%--<td>${json.code}</td>--%>
          <%--<td>${json.name}</td>--%>
          <%--<td>${json.spec}</td>--%>
          <%--<td>${json.level}</td>--%>
          <%--<td>${json.pay}</td>--%>
          <%--<td>${json.buy}</td>--%>
          <%--<td>${json.acc}</td>--%>
          <%--<td>--%>
            <%--<a href="#" onclick="detail('${json.code}')" style="color: #0092DC">明细</a>&nbsp;&nbsp;--%>
          <%--</td>--%>
        <%--</tr>--%>
      <%--</c:forEach>--%>
    <%--</table>--%>
  <%--</form>--%>
<%--</div>--%>
<%--<div id="dialogDiv"></div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function detail(code){--%>
    <%--openD($("#dialogDiv"), '明细', 0.9, 0.8, '${pageContext.request.contextPath}/findStudentExpenseDetail/doFindStudentExpenseDetail.htm?code='+code);--%>
  <%--}--%>
<%--</script>--%>


<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html class="no-js">
<head>
  <%@ include file="/common/meta2.jsp"%>
  <%@ include file="/common/taglibs2.jsp"%>
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->

<div class="admin-content">
  <div class="am-tabs" data-am-tabs>
    <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
      <tr class="am-primary">
        <th style="width: 5%;">序号</th>
        <th style="width: 15%;">学号</th>
        <th style="width: 10%;">姓名</th>
        <th style="width: 20%;">专业</th>
        <th style="width: 10%;">层次</th>
        <th style="width: 10%;">已缴费金额</th>
        <th style="width: 10%;">消费金额</th>
        <th style="width: 10%;">余额</th>
        <th>操作</th>
      </tr>
      <c:if test="${empty data }">
        <tr>
          <td colspan="20" align="center" style="color: red;">没有找到相关数据</td>
        </tr>
      </c:if>
      <c:forEach var="json" items="${data}" varStatus="status">
        <tr>
          <td align="center">${status.index+1}</td>
          <td>${json.code}</td>
          <td>${json.name}</td>
          <td>${json.spec}</td>
          <td>${json.level}</td>
          <td>${json.pay}</td>
          <td>${json.buy}</td>
          <td>${json.acc}</td>
          <td>
            <div class="am-btn-group">
              <button type="button" class="am-btn am-btn-primary am-round" onClick="detail('${json.code}')"><span class="am-icon-th-list"></span> 明细</button>
            </div>
          </td>
        </tr>
      </c:forEach>
    </table>
  </div>
</div>
</body>
</html>
<script>
  function detail(code){
    app.openOneBtnDialog('${pageContext.request.contextPath}/findStudentExpenseDetail/doFindStudentExpenseDetail.htm?code='+code, '明细', 1000, 0.9);
  }
</script>
