<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="main-content">--%>
  <%--<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findPurchaseOrderPageByWhere/purchaseOrderPageSearch.htm" method="post">--%>
    <%--<input type="hidden" id="page" name="page" value="${pageInfo.currentPage}"/>--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<label class="lab_80">学期：</label>--%>
        <%--<select id="semesterId" name="semesterId">--%>
          <%--<c:forEach var="semester" items="${semesterList}">--%>
            <%--<option value="${semester.id}" <c:if test="${semesterId == semester.id}">selected="selected" </c:if> >${semester.semester}</option>--%>
          <%--</c:forEach>--%>
        <%--</select>--%>
        <%--<label class="lab_80">发行渠道：</label>--%>
        <%--<select id="channelId" name="channelId">--%>
          <%--<option value="">请选择</option>--%>
          <%--<c:forEach var="channel" items="${channelList}">--%>
            <%--<option value="${channel.id}" <c:if test="${param.channelId == channel.id}">selected="selected" </c:if> >${channel.name}</option>--%>
          <%--</c:forEach>--%>
        <%--</select>--%>
        <%--<label class="lab_80">教材类型：</label>--%>
        <%--<select id="tmTypeId" name="tmTypeId">--%>
          <%--<option value="">请选择</option>--%>
          <%--<c:forEach var="tmType" items="${tmTypeList}">--%>
            <%--<option value="${tmType.id}" <c:if test="${param.tmTypeId == tmType.id}">selected="selected" </c:if> >${tmType.name}</option>--%>
          <%--</c:forEach>--%>
        <%--</select>--%>
        <%--&nbsp;&nbsp;&nbsp;&nbsp;--%>
        <%--<a class="com_btn_b" href="#" id="subBut" onclick="pageForm.submit();"><span>查&nbsp;询</span></a>&nbsp;&nbsp;--%>
        <%--<a class="com_btn_b" href="#" id="resetBut" onclick="resetForm();"><span>重&nbsp;置</span></a>--%>
      <%--</li>--%>
    <%--</ul>--%>
  <%--</form>--%>
  <%--<Br><Br>--%>
  <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
    <%--<tr>--%>
      <%--<th style="width: 100px;">订单号</th>--%>
      <%--<th style="width: 100px;">发行渠道</th>--%>
      <%--<th style="width: 100px;">教材类别</th>--%>
      <%--<th style="width: 100px;">教材数量</th>--%>
      <%--<th style="width: 100px;">总码洋</th>--%>
      <%--<th style="width: 100px;">已入库数量</th>--%>
      <%--<th style="width: 100px;">未入库数量</th>--%>
      <%--<th style="width: 100px;">操作人</th>--%>
      <%--<th style="width: 80px;">操作时间</th>--%>
      <%--<th style="width: 120px;">操作</th>--%>
    <%--</tr>--%>
    <%--<c:if test="${empty pageInfo || empty pageInfo.pageResults}">--%>
      <%--<tr>--%>
        <%--<td colspan="20" align="center" style="color: red;">没有找到相关数据</td>--%>
      <%--</tr>--%>
    <%--</c:if>--%>
    <%--<c:forEach var="result" items="${pageInfo.pageResults}" varStatus="status">--%>
      <%--<tr>--%>
        <%--<td>${result.code}</td>--%>
        <%--<td>${result.channelName}</td>--%>
        <%--<td>${result.tmTypeName}</td>--%>
        <%--<td>${result.tmCount}</td>--%>
        <%--<td>${result.priceStr}</td>--%>
        <%--<td>${result.putCount}</td>--%>
        <%--<td>${result.tmCount - result.putCount}</td>--%>
        <%--<td>${result.creator}</td>--%>
        <%--<td>${result.createTime}</td>--%>
        <%--<td>--%>
          <%--<a href="#" onclick="addStock('${result.code}', ${result.channelId})" style="color: #0092DC">入库</a>&nbsp;&nbsp;--%>
          <%--<a href="#" onclick="downExcel('${result.code}', ${result.channelId})" style="color: #0092DC">下载</a>&nbsp;&nbsp;--%>
          <%--<a href="#" onclick="printOrder('${result.code}')" style="color: #0092DC">打印</a>--%>
        <%--</td>--%>
      <%--</tr>--%>
    <%--</c:forEach>--%>
      <%--<tr>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--<td align="right" style="color: red">合计：</td>--%>
        <%--<td style="color: red">${totalPrice}</td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>
      <%--</tr>--%>
    <%--<%@ include file="/common/page.jsp"%>--%>
  <%--</table>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function addStock(code, channelId){--%>
    <%--var semesterId = $("#semesterId").val();--%>
    <%--parent.openDialog('入库', 0.6, 0.6, '${pageContext.request.contextPath}/findPurchaseOrderTeachMaterialListByOrderCode/purchaseOrderTMByOrderCodeSearch.htm?orderCode='+code+'&channelId='+channelId+'&semesterId='+semesterId);--%>
  <%--}--%>

  <%--function downExcel(code, channelId){--%>
    <%--var semesterId = $("#semesterId").val();--%>
    <%--$.ajax({--%>
      <%--cache: true,--%>
      <%--type: "POST",--%>
      <%--url:"${pageContext.request.contextPath}/downPurchaseOrderTM/downPurchaseOrderTMExcel.htm",--%>
      <%--data:{"orderCode":code, "channelId":channelId, "semesterId":semesterId},--%>
      <%--async: false,--%>
      <%--success: function(data) {--%>
        <%--open("${pageContext.request.contextPath}"+data);--%>
      <%--}--%>
    <%--});--%>
  <%--}--%>

  <%--function printOrder(code){--%>
    <%--var semesterId = $("#semesterId").val();--%>
    <%--open("${pageContext.request.contextPath}/printPurchaseOrderTM/doPrintPurchaseOrderTM.htm?orderCode="+code+"&semesterId="+semesterId);--%>
  <%--}--%>

  <%--function resetForm(){--%>
    <%--$("#channelId, #tmTypeId").val('');--%>
    <%--<c:forEach var="semester" items="${semesterList}">--%>
      <%--<c:if test="${semester.isNowSemester == 0}">--%>
        <%--$("#semesterId").val(${semester.id});--%>
      <%--</c:if>--%>
    <%--</c:forEach>--%>
  <%--}--%>
<%--</script>--%>


<p />
<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findPurchaseOrderPageByWhere/purchaseOrderPageSearch.htm" method="post">
  <input type="hidden" id="rows" name="rows" />
  <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
  <label >学期：</label>
  <select id="semesterId" name="semesterId">
    <c:forEach var="semester" items="${semesterList}">
      <option value="${semester.id}" <c:if test="${semesterId == semester.id}">selected="selected" </c:if> >${semester.semester}</option>
    </c:forEach>
  </select>&nbsp;&nbsp;&nbsp;&nbsp;

  <label >发行渠道：</label>
  <select id="channelId" name="channelId" onchange="app.changeSelect(this)">
    <option value=""></option>
    <option value="null">全部</option>
    <c:forEach var="channel" items="${channelList}">
      <option value="${channel.id}" <c:if test="${param.channelId == channel.id}">selected="selected" </c:if> >${channel.name}</option>
    </c:forEach>
  </select>&nbsp;&nbsp;&nbsp;&nbsp;

  <label>教材类型：</label>
  <select id="tmTypeId" name="tmTypeId" onchange="app.changeSelect(this)">
    <option value=""></option>
    <option value="null">全部</option>
    <c:forEach var="tmType" items="${tmTypeList}">
      <option value="${tmType.id}" <c:if test="${param.tmTypeId == tmType.id}">selected="selected" </c:if> >${tmType.name}</option>
    </c:forEach>
  </select>
  &nbsp;&nbsp;&nbsp;&nbsp;

  <button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example"
          data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '查询超时'}"
          onclick="app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'), this)"><span class="am-icon-search"></span> 查询</button>
</form>
<p /><p />

<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <tr class="am-primary">
    <th style="width: 8%;">订单号</th>
    <th style="width: 12%;">发行渠道</th>
    <th style="width: 7%;">教材类别</th>
    <th style="width: 7%;">教材数量</th>
    <th style="width: 10%;">总码洋</th>
    <th style="width: 8%;">已入库数量</th>
    <th style="width: 8%;">未入库数量</th>
    <th style="width: 10%;">操作人</th>
    <th style="width: 14%;">操作时间</th>
    <th>操作</th>
  </tr>
  <c:if test="${empty pageInfo || empty pageInfo.pageResults}">
    <tr>
      <td colspan="20" align="center" style="color: red;">没有找到相关数据</td>
    </tr>
  </c:if>
  <c:forEach var="result" items="${pageInfo.pageResults}" varStatus="status">
    <tr>
      <td>${result.code}</td>
      <td>${result.channelName}</td>
      <td>${result.tmTypeName}</td>
      <td>${result.tmCount}</td>
      <td>${result.priceStr}</td>
      <td>${result.putCount}</td>
      <td>${result.tmCount - result.putCount}</td>
      <td>${result.creator}</td>
      <td>${result.createTime}</td>
      <td>
        <div class="am-btn-group">
          <button type="button" class="am-btn am-btn-primary am-round" onClick="downExcel('${result.code}', ${result.channelId})"><span class="am-icon-download"></span> 下载</button>
          <button type="button" class="am-btn am-btn-primary am-round" onClick="printOrder('${result.code}')"><span class="am-icon-print"></span> 打印</button>
        </div>
      </td>
    </tr>
  </c:forEach>
  <tr>
    <td></td>
    <td></td>
    <td></td>
    <td align="right" style="color: red">合计：</td>
    <td style="color: red">${totalPrice}</td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
</table>
<%@ include file="/common/page2.jsp"%>

<script>
  $("select").selected();
  function downExcel(code, channelId){
    var semesterId = $("#semesterId").val();
    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/downPurchaseOrderTM/downPurchaseOrderTMExcel.htm",
      data:{"orderCode":code, "channelId":channelId, "semesterId":semesterId},
      async: false,
      success: function(data) {
        open("${pageContext.request.contextPath}"+data);
      }
    });
  }

  function printOrder(code){
    var semesterId = $("#semesterId").val();
    open("${pageContext.request.contextPath}/printPurchaseOrderTM/doPrintPurchaseOrderTM.htm?orderCode="+code+"&semesterId="+semesterId);
  }
</script>
