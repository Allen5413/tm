<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/countOnceOrderForConfirm/find.htm" method="post">
  <input type="hidden" name="method" value="search" />
  <label >省中心：</label>
  <select id="provCode" name="provCode" data-am-selected="{maxHeight: 500, searchBox: 1}" onchange="app.changeSelect(this)">
    <option value=""></option>
    <option value="null">全部</option>
    <c:forEach var="prov" items="${provinceList}">
      <option value="${prov.code}" <c:if test="${param.provCode eq prov.code}">selected="selected" </c:if> >${prov.name}</option>
    </c:forEach>
  </select>&nbsp;&nbsp;&nbsp;&nbsp;

  <label >学习中心：</label>
  <select id="spotCode" name="spotCode" data-am-selected="{maxHeight: 500, searchBox: 1}" onchange="app.changeSelect(this)">
    <option value=""></option>
    <option value="null">全部</option>
    <c:forEach var="spot" items="${spotList}">
      <option value="${spot.code}" <c:if test="${param.spotCode eq spot.code}">selected="selected" </c:if> >[${spot.code}]${spot.name}</option>
    </c:forEach>
  </select>&nbsp;&nbsp;&nbsp;&nbsp;

  <button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example"
          data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '查询超时'}"
          onclick="app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'), this)"><span class="am-icon-search"></span> 查询</button>
</form>
<p /><p />
<c:if test="${param.method eq 'search'}">
  <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
    <tr class="am-primary">
      <th style="width: 8%;">序号</th>
      <th style="width: 30%;">学习中心</th>
      <th style="width: 10%;">订单总人数</th>
      <th style="width: 8%;">未确认总人数</th>
      <th style="width: 8%;">确认总人数</th>
      <th style="width: 10%;">订单确认百分比</th>
      <th style="width: 8%;">订单总码洋</th>
      <th style="width: 8%;">确认总码洋</th>
      <th style="width: 10%;">码洋确认百分比</th>
    </tr>
    <c:if test="${empty resultData}">
      <tr>
        <td colspan="20" align="center" style="color: red;">没有找到相关数据</td>
      </tr>
    </c:if>
    <c:forEach var="json" items="${resultData}" varStatus="status">
      <tr>
        <td>${status.index+1}</td>
        <td>[${json.spotCode}]${json.spotName}</td>
        <td>${json.totalCount}</td>
        <td>
          <c:if test="${json.notCount > 0}">
            <a href="#" onclick="detail('${json.spotCode}', 0)">${json.notCount}</a>
          </c:if>
          <c:if test="${json.notCount <= 0}">
            0
          </c:if>
        </td>
        <td>
          <c:if test="${json.count > 0}">
            <a href="#" onclick="detail('${json.spotCode}', 1)">${json.count}</a>
          </c:if>
          <c:if test="${json.count <= 0}">
            ${json.count}
          </c:if>
        </td>
        <td>${json.countPercent}</td>
        <td>${json.totalPrice}</td>
        <td>${json.price}</td>
        <td>${json.pricePercent}</td>
      </tr>
    </c:forEach>
    <tr style="color: red">
      <td></td>
      <td align="right">合计：</td>
      <td>${sumTotalCount}</td>
      <td>${sumNotCount}</td>
      <td>${sumCount}</td>
      <td>${sumTotalCountPercent}</td>
      <td>${sumTotalPrice}</td>
      <td>${sumPrice}</td>
      <td>${sumTotalPricePercent}</td>
    </tr>
  </table>
</c:if>
<script>
  $("select").selected();
  function detail(spotCode, flag){
    open("${pageContext.request.contextPath}/countOnceOrderForConfirm/findDetail.htm?spotCode="+spotCode+"&flag="+flag);
  }
</script>