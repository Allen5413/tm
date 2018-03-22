<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p />
<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findStudentBookOrderForStudentCount/find.htm" method="post">
  <label >学期：</label>
  <select id="semesterId" name="semesterId">
    <c:forEach var="semester" items="${semesterList}">
      <option value="${semester.id}" <c:if test="${semesterId == semester.id}">selected="selected" </c:if> >${semester.semester}</option>
    </c:forEach>
  </select>
  &nbsp;&nbsp;&nbsp;&nbsp;

  <label >学习中心：</label>
  <select id="spotCode" name="spotCode" data-am-selected="{maxHeight: 500, searchBox: 1}" onchange="app.changeSelect(this)">
    <option value=""></option>
    <option value="null">全部</option>
    <c:forEach var="spot" items="${spotList}">
      <option value="${spot.code}" <c:if test="${param.spotCode eq spot.code}">selected="selected" </c:if> >[${spot.code}]${spot.name}</option>
    </c:forEach>
  </select>
  &nbsp;&nbsp;&nbsp;&nbsp;

  <label >打印订单数：</label>
  <input type="text" id="countNum" name="countNum" value="${countNum}" />
  &nbsp;&nbsp;&nbsp;&nbsp;

  <label>是否打印：</label>
  <select id="state" name="state">
    <option value="1" <c:if test="${param.state eq '1'}">selected="selected" </c:if>>未打印</option>
    <option value="2" <c:if test="${param.state eq '2'}">selected="selected" </c:if>>已打印</option>
  </select>
  &nbsp;&nbsp;&nbsp;&nbsp;
  <button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example"
          data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '查询超时'}"
          onclick="app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'), this)"><span class="am-icon-search"></span> 查询</button>
</form>
<p /><p />

<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <tr class="am-primary">
    <th style="width: 5%;">序号</th>
    <th style="width: 7%;">订单数量</th>
    <th style="width: 7%;">教材数量</th>
    <th style="width: 7%;">总金额</th>
    <c:if test="${param.state eq '2'}">
      <th style="width: 7%;">操作人</th>
      <th style="width: 15%;">打印时间</th>
    </c:if>
    <th>操作</th>
  </tr>
  <c:if test="${empty list}">
    <tr>
      <td colspan="20" align="center" style="color: red;">没有找到相关数据</td>
    </tr>
  </c:if>
  <c:forEach var="json" items="${list}" varStatus="status">
    <tr>
      <td>${status.index + 1}</td>
      <td>${json.orderCount}</td>
      <td>${json.tmCount}</td>
      <td>${json.priceTotal}</td>
      <c:if test="${param.state eq '2'}">
        <td>${json.operator}</td>
        <td>${json.operateTime}</td>
      </c:if>
      <td>
        <div class="am-btn-group">
          <button type="button" class="am-btn am-btn-primary am-round" onClick="printTMOrder(${status.index}, '${json.spotCode}', '${json.operateTime}', 0)"><span class="am-icon-th-list"></span> 查看</button>
          <button type="button" class="am-btn am-btn-primary am-round" onClick="printTMOrder(${status.index}, '${json.spotCode}', '${json.operateTime}', 1)"><span class="am-icon-print"></span> 打印</button>
          <c:if test="${param.state eq '2'}">
            <button type="button" class="am-btn am-btn-primary am-round" onClick="downExcel('${json.operateTime}')"><span class="am-icon-download"></span> 下载</button>
          </c:if>
        </div>
      </td>
    </tr>
  </c:forEach>
</table>
<script>
  $("select").selected();
  function printTMOrder(pageNum, code, operateTime, flag){
    var semesterId = '${semesterId}';
    var state = '${param.state}';
    <c:if test="${empty param.state}">state = 1;</c:if>
    var countNum =  '${param.countNum}';
    if(countNum == ""){
      countNum = 1000;
    }
    open("${pageContext.request.contextPath}/findStudentBookOrderTMForStudentPrint/find.htm?pageNum="+pageNum+"&countNum="+countNum+"&semesterId="+semesterId+"&state="+state+"&flag="+flag+"&operateTime="+operateTime+"&spotCode=${param.spotCode}");
    if(flag == 1) {
      setTimeout(function () {
        $("#state").val("2");
        $("#searchBtn").click();
      }, 3000);
    }
  }

  function downExcel(operateTime){
    var semesterId = '${semesterId}';
    var state = '${param.state}';
    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/downStudentBookTMForStudentPrint/down.htm?semesterId="+semesterId+"&state="+state+"&operateTime="+operateTime+"&spotCode=${param.spotCode}",
      data:{},
      async: false,
      success: function(data) {
        open("${pageContext.request.contextPath}"+data);
      }
    });
  }
</script>