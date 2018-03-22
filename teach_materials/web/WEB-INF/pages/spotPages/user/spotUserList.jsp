<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<!DOCTYPE html>--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="main-content">--%>
  <%--<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findSpotPage/findSpotPageByWhere.htm" method="post">--%>
    <%--<a style="display: none" class="com_btn_b" href="#" id="subBut" onclick="pageForm.submit();"><span>查&nbsp;询</span></a>&nbsp;&nbsp;--%>
    <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
      <%--<tr>--%>
        <%--<th style="width: 3%;">序号</th>--%>
        <%--<th style="width: 3%;">编号</th>--%>
        <%--<th style="width: 14%;">名称</th>--%>
        <%--<th style="width: 5%;">管理员</th>--%>
        <%--<th style="width: 5%;">电话</th>--%>
        <%--<th style="width: 5%;">手机</th>--%>
        <%--<th style="width: 5%;">邮编</th>--%>
        <%--<th style="width: 14%;">地址</th>--%>
        <%--<th style="width: 12%;">开户银行</th>--%>
        <%--<th style="width: 11%;">银行账号</th>--%>
        <%--<th style="width: 5%;">银行户名</th>--%>
        <%--<th style="width: 8%;">操作时间</th>--%>
        <%--<th style="width: 10%;">操作</th>--%>
      <%--</tr>--%>
      <%--<c:if test="${empty pageInfo || empty pageInfo.pageResults}">--%>
        <%--<tr>--%>
          <%--<td colspan="20" align="center" style="color: red;">没有找到相关数据</td>--%>
        <%--</tr>--%>
      <%--</c:if>--%>
      <%--<c:forEach var="spot" items="${pageInfo.pageResults}" varStatus="status">--%>
        <%--<tr>--%>
          <%--<td align="center">${status.index+1}</td>--%>
          <%--<td>${spot.code}</td>--%>
          <%--<td>${spot.name}</td>--%>
          <%--<td>${spot.adminName}</td>--%>
          <%--<td>${spot.tel}</td>--%>
          <%--<td>${spot.phone}</td>--%>
          <%--<td>${spot.postalCode}</td>--%>
          <%--<td>${spot.address}</td>--%>
          <%--<td>${spot.bank}</td>--%>
          <%--<td>${spot.bankCode}</td>--%>
          <%--<td>${spot.bankName}</td>--%>
          <%--<td>--%>
            <%--<fmt:formatDate value="${spot.operateTime}" pattern="yyyy-MM-dd HH:mm:ss" />--%>
          <%--</td>--%>
          <%--<td>--%>
            <%--<a href="#" onclick="edit(${spot.id})" style="color: #0092DC">修改</a>&nbsp;&nbsp;--%>
            <%--<a href="#" onclick="printInfo(${spot.id})" style="color: #0092DC">打印</a>&nbsp;&nbsp;--%>
            <%--<a href="#" onclick="wxUser('${spot.code}')" style="color: #0092DC">微信管理员</a>--%>
          <%--</td>--%>
        <%--</tr>--%>
      <%--</c:forEach>--%>
    <%--</table>--%>
  <%--</form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function edit(id){--%>
    <%--parent.openDialog('编辑学习中心', 0.5, 0.6, '${pageContext.request.contextPath}/editSpot/open.htm?id='+id);--%>
  <%--}--%>

  <%--function printInfo(id){--%>
    <%--open('${pageContext.request.contextPath}/printSpot/print.htm?id='+id)--%>
  <%--}--%>

  <%--function wxUser(code){--%>
    <%--parent.openDialog('微信管理员', 0.8, 0.8, '${pageContext.request.contextPath}/findSpotWxByCode/open.htm?code='+code);--%>
  <%--}--%>

<%--</script>--%>




<p />
<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <tr class="am-primary">
    <th style="width: 3%;">序号</th>
    <th style="width: 3%;">编号</th>
    <th style="width: 14%;">名称</th>
    <th style="width: 5%;">管理员</th>
    <th style="width: 5%;">电话</th>
    <th style="width: 5%;">手机</th>
    <th style="width: 5%;">邮编</th>
    <th style="width: 14%;">地址</th>
    <th style="width: 10%;">开户银行</th>
    <th style="width: 10%;">银行账号</th>
    <th style="width: 5%;">银行户名</th>
    <th style="width: 8%;">操作时间</th>
    <th>操作</th>
  </tr>
  <c:if test="${empty pageInfo || empty pageInfo.pageResults}">
    <tr>
      <td colspan="20" align="center" style="color: red;">没有找到相关数据</td>
    </tr>
  </c:if>
  <c:forEach var="spot" items="${pageInfo.pageResults}" varStatus="status">
    <tr>
      <td align="center">${status.index+1}</td>
      <td>${spot.code}</td>
      <td>${spot.name}</td>
      <td>${spot.adminName}</td>
      <td>${spot.tel}</td>
      <td>${spot.phone}</td>
      <td>${spot.postalCode}</td>
      <td>${spot.address}</td>
      <td>${spot.bank}</td>
      <td>${spot.bankCode}</td>
      <td>${spot.bankName}</td>
      <td><fmt:formatDate value="${spot.operateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
      <td>
        <div class="doc-dropdown-justify-js">
          <div class="am-dropdown" data-am-dropdown="" id="operateBtn_${status.index+1}">
            <button class="am-btn am-btn-default am-btn-sm am-dropdown-toggle" data-am-dropdown-toggle=""><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
            <ul class="am-dropdown-content">
              <li><a href="#" onClick="edit(${spot.id})"><span style="margin-left:10px; margin-top:5px; margin-bottom:5px;" class="am-icon-pencil-square-o"></span> 修改</a></li>
              <li><a href="#" onClick="printInfo(${spot.id})"><span style="margin-left:10px; margin-top:5px; margin-bottom:5px;" class="am-icon-print"></span> 打印</a></li>
              <li><a href="#" onClick="wxUser('${spot.code}')"><span style="margin-left:10px; margin-top:5px; margin-bottom:5px;" class="am-icon-pencil-square-o"></span> 微信管理员</a></li>
            </ul>
          </div>
        </div>
      </td>
    </tr>
  </c:forEach>
</table>
<%@ include file="/common/page2.jsp"%>
<script>
  $(function() {
    for(var i=1; i<=${fn:length(pageInfo.pageResults)}; i++) {
      $('#operateBtn_'+i).dropdown({justify: '.doc-dropdown-justify-js'});
    }
  });


  function edit(id){
    var url = '${pageContext.request.contextPath}/editSpot/open.htm?id='+id;
    app.openDialog(url, '编辑中心', 700, 0.8, function(index){
      var phone = $("#edit_phone").val().trim();
      var bankCode = $("#edit_bankCode").val().trim();

      if(phone == ""){
        app.msg("请输入手机号码", 1);
        return false;
      }
      if(bankCode != ""){
        if(!vaild.vaildLuhm(bankCode)){
          app.msg('银行卡号输入错误', 1);
          return false;
        }
      }
      app.edit("${pageContext.request.contextPath}/editSpot/editor.htm", $('#editForm').serialize(), index);
    });
  }

  function printInfo(id){
    open('${pageContext.request.contextPath}/printSpot/print.htm?id='+id)
  }

  function wxUser(code){
    app.openOneBtnDialog('${pageContext.request.contextPath}/findSpotWxByCode/open.htm?code='+code, '微信管理员', 1200, 0.8);
  }
</script>

