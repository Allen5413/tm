<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<p />
<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findSpotRewardByWhereList/find.htm" method="post">
  <input type="hidden" id="method" name="method" value="search" />

  <label >计算年份：</label>
  <input type="text" id="year" name="year" value="${param.year}" />&nbsp;&nbsp;&nbsp;&nbsp;
  <label>学习中心：</label>
  <select id="spotCode" name="spotCode" data-am-selected="{maxHeight: 500, searchBox: 1}" onchange="app.changeSelect(this)">
    <option value=""></option>
    <option value="null">全部</option>
    <c:forEach var="spot" items="${spotList}">
      <option value="${spot.code}" <c:if test="${param.spotCode eq spot.code}">selected="selected" </c:if> >[${spot.code}]${spot.name}</option>
    </c:forEach>
  </select>&nbsp;&nbsp;&nbsp;&nbsp;

  <button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example"
          data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '查询超时'}"
          onclick="sub(this);"><span class="am-icon-search"></span> 查询</button>
</form>
<p /><p />

<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <tr>
    <td>
      <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="down()"><span class="am-icon-download"></span> 下载</button>
    </td>
  </tr>
  <tr class="am-primary">
    <th style="width: 4%;">序号</th>
    <th style="width: 30%;">中心</th>
    <th style="width: 5%;">学期</th>
    <th style="width: 5%;">消费</th>
    <th style="width: 4%;">比例</th>
    <th style="width: 4%;">奖励</th>
    <th style="width: 11%;">费用结清时间</th>
    <th style="width: 5%;">学期</th>
    <th style="width: 5%;">消费</th>
    <th style="width: 4%;">比例</th>
    <th style="width: 4%;">奖励</th>
    <th style="width: 11%;">费用结清时间</th>
    <th>合计</th>
  </tr>
  <c:if test="${empty list}">
    <tr>
      <td colspan="999" align="center" style="color: red;">没有找到相关数据</td>
    </tr>
  </c:if>
  <c:forEach var="json" items="${list}" varStatus="status">
    <tr>
      <td>${status.index + 1}</td>
      <td>${json.spotName}</td>
      <td>${empty json.semester ? "上学期" : json.semester}</td>
      <td>${empty json.buy ? 0 : json.buy}</td>
      <td>
        <c:if test="${empty json.ratio}">0%</c:if>
        <c:if test="${!empty json.ratio}">${json.ratio}</c:if>
      </td>
      <td style="color:red">${empty json.rewardMoney ? 0 : json.rewardMoney}</td>
      <td>${json.clearTime}</td>
      <td>${empty json.semester2 ? "下学期" : json.semester2}</td>
      <td>${empty json.buy2 ? 0 : json.buy2}</td>
      <td>
        <c:if test="${empty json.ratio2}">0%</c:if>
        <c:if test="${!empty json.ratio2}">${json.ratio2}</c:if>
      </td>
      <td style="color:red">${empty json.rewardMoney2 ? 0 : json.rewardMoney2}</td>
      <td>${json.clearTime2}</td>
      <td style="color:red">${json.rewardMoneyTotal}</td>
    </tr>
    <tr>
      <td></td>
      <td></td>
      <td>7月15日之前交费：</td>
      <td>${json.pay}</td>
      <td></td>
      <td></td>
      <td></td>
      <td>1月15日之前交费：</td>
      <td>${json.pay4}</td>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
    </tr>
    <tr>
      <td></td>
      <td></td>
      <td>7月15日至9月15日交费：</td>
      <td>${json.pay2}</td>
      <td></td>
      <td></td>
      <td></td>
      <td>1月15日至3月15日交费：</td>
      <td>${json.pay5}</td>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
    </tr>
    <tr>
      <td></td>
      <td></td>
      <td>9月15日之后交费：</td>
      <td>${json.pay3}</td>
      <td></td>
      <td></td>
      <td></td>
      <td>3月15日之后交费：</td>
      <td>${json.pay6}</td>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
    </tr>
  </c:forEach>
  <tr>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td align="right">合计：</td>
    <td id="totalMoney" style="color:red"></td>
  </tr>
</table>
<script>
  $("select").selected();
  var totalMoney = 0;
  <c:forEach var="json" items="${list}">
  var rewardMoneyTotal = "${json.rewardMoneyTotal}";
  totalMoney += parseFloat(rewardMoneyTotal);
  </c:forEach>
  $("#totalMoney").html(totalMoney.toFixed(2));

  function sub(obj){
    if($("#year").val() == ""){
      app.msg("请输入计算年份", 1);
      return;
    }
    app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'), obj)
  }

  function down(){
    if($("#year").val() == ""){
      app.msg("请输入计算年份", 1);
      return;
    }

    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/findSpotRewardByWhereList/down.htm",
      data:{"year":$("#year").val()},
      async: false,
      success: function(data) {
        open("${pageContext.request.contextPath}"+data);
      }
    });
  }
</script>