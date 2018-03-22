<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<div class="main-content" style="height: 100%;">--%>
  <%--<table width="100%" height="100%">--%>
    <%--<tr>--%>
      <%--<Td width="30%" style="border-right: 1px solid #F1F8FB;" valign="top">--%>
        <%--<div class="main-content" style="overflow-y:scroll; height: 100%;">--%>
          <%--<ul class="create_info_list" style="padding: 2px 0 2px 0px;">--%>
            <%--<li>--%>
              <%--<label class="lab_80" style="width: 70px;">入账记录：</label>--%>
            <%--</li>--%>
          <%--</ul>--%>
          <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="95%">--%>
            <%--<tr>--%>
              <%--<th style="width: 150px;">缴费时间</th>--%>
              <%--<th>缴费金额</th>--%>
            <%--</tr>--%>
            <%--<c:if test="${empty payList}">--%>
              <%--<tr>--%>
                <%--<td colspan="20" align="center" style="color: #ff000c;">没有找到相关数据</td>--%>
              <%--</tr>--%>
            <%--</c:if>--%>
            <%--<c:forEach var="pay" items="${payList}" varStatus="status">--%>
              <%--<tr>--%>
                <%--<td>--%>
                  <%--<fmt:formatDate value="${pay.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />--%>
                <%--</td>--%>
                <%--<td>${pay.money}</td>--%>
              <%--</tr>--%>
            <%--</c:forEach>--%>
          <%--</table>--%>
        <%--</div>--%>
      <%--</Td>--%>
      <%--<td valign="top" style="padding-left: 17px;">--%>
        <%--<div class="main-content" style="overflow-y:scroll; height: 100%;">--%>
          <%--<ul class="create_info_list" style="padding: 2px 0 2px 0px;">--%>
            <%--<li>--%>
              <%--<label class="lab_80" style="width: 70px;">消费记录：</label>--%>
            <%--</li>--%>
          <%--</ul>--%>
          <%--<c:if test="${empty buyJSON}">--%>
            <%--<tr>--%>
              <%--<td colspan="20" align="center" style="color: #ff000c;">没有找到相关数据</td>--%>
            <%--</tr>--%>
          <%--</c:if>--%>
          <%--<c:forEach var="json" items="${buyJSON}">--%>
            <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="99%">--%>
              <%--<tr>--%>
                <%--<td colspan="20" align="left" style="font-weight: bold;">${json.key}</td>--%>
              <%--</tr>--%>
              <%--<tr>--%>
                <%--<th style="width: 150px;">消费时间</th>--%>
                <%--<th style="width: 70px;">消费类型</th>--%>
                <%--<th style="width: 70px;">消费金额</th>--%>
                <%--<th>消费说明</th>--%>
              <%--</tr>--%>
              <%--<c:forEach var="detail" items="${json.value}">--%>
                <%--<c:forEach var="spotBuy" items="${detail.value}">--%>
                  <%--<tr>--%>
                    <%--<td>${spotBuy.createTime}</td>--%>
                    <%--<td>--%>
                      <%--<c:choose>--%>
                        <%--<c:when test="${spotBuy.type == 0}">购买教材</c:when>--%>
                        <%--<c:when test="${spotBuy.type == 1}">教材改价</c:when>--%>
                        <%--<c:otherwise>未知</c:otherwise>--%>
                      <%--</c:choose>--%>
                    <%--</td>--%>
                    <%--<td>${spotBuy.money}</td>--%>
                    <%--<td>${spotBuy.detail}</td>--%>
                  <%--</tr>--%>
                <%--</c:forEach>--%>
                <%--<tr>--%>
                  <%--<td></td>--%>
                  <%--<td align="right">合计：</td>--%>
                  <%--<td>${detail.key}</td>--%>
                  <%--<td></td>--%>
                <%--</tr>--%>
              <%--</c:forEach>--%>
            <%--</table>--%>
            <%--<br /><br />--%>
          <%--</c:forEach>--%>
        <%--</div>--%>
      <%--</td>--%>
    <%--</tr>--%>
  <%--</table>--%>
<%--</div>--%>
<div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
  <div class="am-panel-hd am-cf" data-am-collapse="{target: '#pay'}">入账记录<span class="am-icon-chevron-down am-fr"></span></div>
  <div id="pay" class="am-in">
    <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
      <tr class="am-primary">
        <th style="width:60%">交费时间</th>
        <th style="width:40%">交费金额</th>
      </tr>
      <c:if test="${empty payList}">
        <tr>
          <td colspan="20" align="center" style="color: #ff000c;">没有找到相关数据</td>
        </tr>
      </c:if>
      <c:forEach var="pay" items="${payList}" varStatus="status">
        <tr <c:if test="${pay.payType == 4 || pay.payType == 0}">style="color: red;"</c:if> >
          <td>
            <fmt:formatDate value="${pay.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
          </td>
          <td>${pay.money}</td>
        </tr>
      </c:forEach>
    </table>
  </div>
</div>

<div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
  <div class="am-panel-hd am-cf" data-am-collapse="{target: '#buy'}">消费记录<span class="am-icon-chevron-down am-fr"></span></div>
  <div id="buy" class="am-in">
    <c:if test="${empty buyMap}">
      <span style="color: #ff000c;">没有找到相关数据</span>
    </c:if>
    <c:forEach var="map" items="${buyMap}">
      <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
        <tr>
          <td colspan="99" style="color:#0b76ac; font-weight:bold">${map.key}</td>
        </tr>
        <tr class="am-primary">
          <th style="width:30%">消费时间</th>
          <th style="width:10%">消费类型</th>
          <th style="width:10%">消费金额</th>
          <th>消费说明</th>
        </tr>
        <c:forEach var="detail" items="${map.value}">
          <c:forEach var="spotBuy" items="${detail.value}">
            <tr>
              <td>${stuBuy.createTime}</td>
              <td>
                <c:choose>
                  <c:when test="${spotBuy.type == 0}">购买教材</c:when>
                  <c:when test="${spotBuy.type == 1}">教材改价</c:when>
                  <c:otherwise>未知</c:otherwise>
                </c:choose>
              </td>
              <td>${spotBuy.money}</td>
              <td>${spotBuy.detail}</td>
            </tr>
          </c:forEach>
          <tr>
            <td></td>
            <td align="right">合计：</td>
            <td>${detail.key}</td>
            <td></td>
          </tr>
        </c:forEach>
      </table>
    </c:forEach>
  </div>
</div>
