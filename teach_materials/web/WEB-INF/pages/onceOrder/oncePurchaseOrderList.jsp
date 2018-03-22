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
  <%--<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findOncePurchaseOrderPageByWhere/find.htm" method="post">--%>
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
        <%--<label class="lab_80">状态：</label>--%>
        <%--<select id="state" name="state">--%>
          <%--<option value="">请选择</option>--%>
          <%--<option value="0" <c:if test="${param.state eq '0'}">selected="selected"</c:if> >已采购</option>--%>
          <%--<option value="1" <c:if test="${param.state eq '1'}">selected="selected"</c:if> >未采购</option>--%>
        <%--</select>--%>
        <%--&nbsp;&nbsp;&nbsp;&nbsp;--%>
        <%--<a class="com_btn_b" href="#" id="subBut" onclick="pageForm.submit();"><span>查&nbsp;询</span></a>--%>
      <%--</li>--%>
    <%--</ul>--%>
  <%--</form>--%>
  <%--<Br><Br>--%>
  <%--<a class="com_btn_b" href="#" onclick="addOncePrintOrder();"><span>生成采购单</span></a>--%>
  <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
    <%--<tr>--%>
      <%--<th style="width: 10%">订单号</th>--%>
      <%--<th style="width: 10%">发行渠道</th>--%>
      <%--<th style="width: 5%">教材类别</th>--%>
      <%--<th style="width: 5%">教材数量</th>--%>
      <%--<th style="width: 10%">总码洋</th>--%>
      <%--<th style="width: 10%">状态</th>--%>
      <%--<th style="width: 10%">操作人</th>--%>
      <%--<th style="width: 10%">操作时间</th>--%>
      <%--<th style="width: 10%">操作</th>--%>
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
        <%--<td>--%>
          <%--<c:if test="${result.state == 0}">已采购</c:if>--%>
          <%--<c:if test="${result.state == 1}">未采购</c:if>--%>
        <%--</td>--%>
        <%--<td>${result.creator}</td>--%>
        <%--<td>${result.createTime}</td>--%>
        <%--<td>--%>
          <%--<c:if test="${result.state == 1}">--%>
            <%--<a href="#" onclick="buy('${result.code}')" style="color: #0092DC">已采购</a>&nbsp;&nbsp;--%>
          <%--</c:if>--%>
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
    <%--</tr>--%>
    <%--<%@ include file="/common/page.jsp"%>--%>
  <%--</table>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function addOncePrintOrder(){--%>
    <%--$.ajax({--%>
      <%--url:"${pageContext.request.contextPath}/addOncePurchaseOrder/add.htm",--%>
      <%--method : 'POST',--%>
      <%--async:false,--%>
      <%--success:function(data){--%>
        <%--if(data.state == 0){--%>
          <%--alert("操作成功！");--%>
          <%--pageForm.submit();--%>
        <%--}else {--%>
          <%--alert(data.msg);--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>
  <%--}--%>

  <%--function buy(code){--%>
    <%--$.ajax({--%>
      <%--url:"${pageContext.request.contextPath}/editOncePurchaseOrderStateByCode/editor.htm",--%>
      <%--method : 'POST',--%>
      <%--async:false,--%>
      <%--data:{"code":code},--%>
      <%--success:function(data){--%>
        <%--if(data.state == 0){--%>
          <%--alert("操作成功！");--%>
          <%--pageForm.submit();--%>
        <%--}else {--%>
          <%--alert(data.msg);--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>
  <%--}--%>

  <%--function downExcel(code, channelId){--%>
    <%--var semesterId = $("#semesterId").val();--%>
    <%--$.ajax({--%>
      <%--cache: true,--%>
      <%--type: "POST",--%>
      <%--url:"${pageContext.request.contextPath}/downOncePurchaseOrderTM/downPurchaseOrderTMExcel.htm?orderCode="+code+"&channelId="+channelId+"&semesterId="+semesterId,--%>
      <%--data:{},--%>
      <%--async: false,--%>
      <%--success: function(data) {--%>
        <%--open("${pageContext.request.contextPath}"+data);--%>
      <%--}--%>
    <%--});--%>
  <%--}--%>

  <%--function printOrder(code){--%>
    <%--var semesterId = $("#semesterId").val();--%>
    <%--open("${pageContext.request.contextPath}/printOncePurchaseOrderTM/doPrintPurchaseOrderTM.htm?orderCode="+code+"&semesterId="+semesterId);--%>
  <%--}--%>
<%--</script>--%>



<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findOncePurchaseOrderPageByWhere/find.htm" method="post">
  <input type="hidden" id="rows" name="rows" />
  <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
  <table width="90%">
    <tr height="40">
      <td align="right"><label >学期：</label></td>
      <td>
        <select id="semesterId" name="semesterId" onchange="app.changeSelect(this)">
          <c:forEach var="semester" items="${semesterList}">
            <option value="${semester.id}" <c:if test="${semesterId == semester.id}">selected="selected" </c:if> >${semester.semester}</option>
          </c:forEach>
        </select>
      </td>
      <td align="right"><label >发行渠道：</label></td>
      <td>
        <select id="channelId" name="channelId" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <c:forEach var="channel" items="${channelList}">
            <option value="${channel.id}" <c:if test="${param.channelId == channel.id}">selected="selected" </c:if> >${channel.name}</option>
          </c:forEach>
        </select>
      </td>
    </tr>
    <tr height="40">
      <td align="right"><label >教材类型：</label></td>
      <td>
        <select id="tmTypeId" name="tmTypeId" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <c:forEach var="tmType" items="${tmTypeList}">
            <option value="${tmType.id}" <c:if test="${param.tmTypeId == tmType.id}">selected="selected" </c:if> >${tmType.name}</option>
          </c:forEach>
        </select>
      </td>
      <td align="right"><label >状态：</label></td>
      <td>
        <select id="state" name="state" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <option value="0" <c:if test="${param.state eq '0'}">selected="selected"</c:if> >已采购</option>
          <option value="1" <c:if test="${param.state eq '1'}">selected="selected"</c:if> >未采购</option>
        </select>
      </td>
    </tr>
    <tr>
      <td colspan="99" style="padding-left:20px;">
        <button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example"
                data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '查询超时'}"
                onclick="app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'), this)"><span class="am-icon-search"></span> 查询</button>
      </td>
    </tr>
  </table>
</form>
<p /><p />
<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <tr>
    <td colspan="999" style="background-color:#FFF">
      <button class="am-btn am-btn-primary am-btn-sm btn-loading-example" type="button"
              data-am-loading="{spinner: 'circle-o-notch', loadingText: '生成中...', resetText: '生成采购单'}"
              onClick="addOncePrintOrder(this)"><span class="am-icon-plus"></span> 生成采购单</button>
    </td>
  </tr>
  <tr class="am-primary">
    <th style="width: 10%">订单号</th>
    <th style="width: 10%">发行渠道</th>
    <th style="width: 6%">教材类别</th>
    <th style="width: 6%">教材数量</th>
    <th style="width: 8%">总码洋</th>
    <th style="width: 8%">状态</th>
    <th style="width: 8%">操作人</th>
    <th style="width: 12%">操作时间</th>
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
      <td>
        <c:if test="${result.state == 0}">已采购</c:if>
        <c:if test="${result.state == 1}">未采购</c:if>
      </td>
      <td>${result.creator}</td>
      <td>${result.createTime}</td>
      <td>
        <div class="am-btn-group">
          <c:if test="${result.state == 1}">
            <button type="button" class="am-btn am-btn-primary am-round" onClick="buy('${result.code}')"><span class="am-icon-cog"></span> 已采购</button>
          </c:if>
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
  </tr>
</table>
<%@ include file="/common/page2.jsp"%>
<script>
  $("select").selected();
  function buy(code){
    app.add("${pageContext.request.contextPath}/editOncePurchaseOrderStateByCode/editor.htm", {"code":code});
  }

  function downExcel(code, channelId){
    var semesterId = $("#semesterId").val();
    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/downOncePurchaseOrderTM/downPurchaseOrderTMExcel.htm?orderCode="+code+"&channelId="+channelId+"&semesterId="+semesterId,
      data:{},
      async: false,
      success: function(data) {
        open("${pageContext.request.contextPath}"+data);
      }
    });
  }

  function printOrder(code){
    var semesterId = $("#semesterId").val();
    open("${pageContext.request.contextPath}/printOncePurchaseOrderTM/doPrintPurchaseOrderTM.htm?orderCode="+code+"&semesterId="+semesterId);
  }

  function addOncePrintOrder(btnObj){
    $(btnObj).button('loading');
    setTimeout(function(){
      $.ajax({
        url:"${pageContext.request.contextPath}/addOncePurchaseOrder/add.htm",
        method : 'POST',
        async:false,
        success:function(data){
          if(data.state == 0){
            app.msg("操作成功！", 0);
            $("#searchBtn").click();
          }else {
            app.msg(data.msg, 1);
            $(btnObj).button('reset');
          }
        }
      });
    }, 100);
  }
</script>