<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="main-content">--%>
  <%--<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findStudentExpenseDetail/doFindStudentExpenseDetail.htm">--%>
    <%--<input type="hidden" name="code" value="${param.code}" />--%>
    <%--<table width="100%" height="100%">--%>
      <%--<tr>--%>
        <%--<Td width="40%" style="border-right: 1px solid #F1F8FB;" valign="top">--%>
          <%--<div class="main-content" style="overflow-y:scroll; height: 100%;">--%>
            <%--<ul class="create_info_list" style="padding: 2px 0 2px 0px;">--%>
              <%--<li>--%>
                <%--<label class="lab_80" style="width: 70px;">入账记录：</label>--%>
              <%--</li>--%>
            <%--</ul>--%>
            <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="95%">--%>
              <%--<tr>--%>
                <%--<th style="width: 25%;">缴费时间</th>--%>
                <%--<th style="width: 15%;">缴费金额</th>--%>
                <%--<th style="width: 15%;">缴费方式</th>--%>
                <%--<th style="width: 35%;">备注</th>--%>
                <%--<th>操作</th>--%>
              <%--</tr>--%>
              <%--<c:if test="${empty payList}">--%>
                <%--<tr>--%>
                  <%--<td colspan="20" align="center" style="color: #ff000c;">没有找到相关数据</td>--%>
                <%--</tr>--%>
              <%--</c:if>--%>
              <%--<c:forEach var="pay" items="${payList}" varStatus="status">--%>
                <%--<tr <c:if test="${pay.payType == 4 || pay.parentId == 0}">style="color: red;"</c:if> >--%>
                  <%--<td>--%>
                    <%--<fmt:formatDate value="${pay.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />--%>
                  <%--</td>--%>
                  <%--<td>${pay.money}</td>--%>
                  <%--<td>--%>
                    <%--<c:choose>--%>
                      <%--<c:when test="${pay.payType == 0}">网银</c:when>--%>
                      <%--<c:when test="${pay.payType == 1}">银行转账</c:when>--%>
                      <%--<c:when test="${pay.payType == 2}">现金</c:when>--%>
                      <%--<c:when test="${pay.payType == 3}">其它</c:when>--%>
                      <%--<c:when test="${pay.payType == 4}">红冲</c:when>--%>
                      <%--<c:when test="${pay.payType == 5}">微信</c:when>--%>
                      <%--<c:otherwise>未知</c:otherwise>--%>
                    <%--</c:choose>--%>
                  <%--</td>--%>
                  <%--<td>${pay.remark}</td>--%>
                  <%--<td>--%>
                    <%--<c:if test="${pay.payType != 4 && pay.parentId == -1 && isAdmin == 0}">--%>
                      <%--<a href="#" onclick="redC(${pay.id})" style="color: #0092DC">红冲</a>--%>
                    <%--</c:if>--%>
                  <%--</td>--%>
                <%--</tr>--%>
              <%--</c:forEach>--%>
            <%--</table>--%>
          <%--</div>--%>
        <%--</Td>--%>
        <%--<td valign="top" style="padding-left: 17px;">--%>
          <%--<div class="main-content" style="overflow-y:scroll; height: 600px;">--%>
            <%--<ul class="create_info_list" style="padding: 2px 0 2px 0px;">--%>
              <%--<li>--%>
                <%--<label class="lab_80" style="width: 70px;">消费记录：</label>--%>
              <%--</li>--%>
            <%--</ul>--%>
            <%--<c:if test="${empty buyMap}">--%>
              <%--<tr>--%>
                <%--<td colspan="20" align="center" style="color: #ff000c;">没有找到相关数据</td>--%>
              <%--</tr>--%>
            <%--</c:if>--%>
            <%--<c:forEach var="map" items="${buyMap}">--%>
              <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="99%">--%>
                <%--<tr>--%>
                  <%--<td colspan="20" align="left" style="font-weight: bold;">${map.key}</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                  <%--<th style="width: 150px;">消费时间</th>--%>
                  <%--<th style="width: 70px;">消费类型</th>--%>
                  <%--<th style="width: 70px;">消费金额</th>--%>
                  <%--<th>消费说明</th>--%>
                <%--</tr>--%>
                <%--<c:forEach var="detail" items="${map.value}">--%>
                  <%--<c:forEach var="stuBuy" items="${detail.value}">--%>
                    <%--<tr>--%>
                      <%--<td>${stuBuy.createTime}</td>--%>
                      <%--<td>--%>
                        <%--<c:choose>--%>
                          <%--<c:when test="${stuBuy.type == 0}">购买教材</c:when>--%>
                          <%--<c:when test="${stuBuy.type == 1}">教材改价</c:when>--%>
                          <%--<c:otherwise>未知</c:otherwise>--%>
                        <%--</c:choose>--%>
                      <%--</td>--%>
                      <%--<td>${stuBuy.money}</td>--%>
                      <%--<td>${stuBuy.detail}</td>--%>
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
  <%--</form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function redC(id){--%>
    <%--var dialog = new Dialog();--%>
    <%--dialog.ShowButtonRow=true;--%>
    <%--dialog.OKEvent = function(){--%>
      <%--dialog.innerFrame.contentWindow.sub($("#pageForm"));--%>
    <%--};--%>
    <%--dialog.Width = getWindowWidthSize() * 0.2;--%>
    <%--dialog.Height = getWindowHeightSize() * 0.05;--%>
    <%--dialog.Title = "红冲";--%>
    <%--dialog.URL = "${pageContext.request.contextPath}/redCStuEP/open.htm?id="+id;--%>
    <%--dialog.show();--%>
    <%--dialog.okButton.value=" 提 交 ";--%>
    <%--dialog.cancelButton.value=" 取 消 ";--%>
  <%--}--%>
<%--</script>--%>


<div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
  <div class="am-panel-hd am-cf" data-am-collapse="{target: '#pay'}">入账记录<span class="am-icon-chevron-down am-fr"></span></div>
  <div id="pay" class="am-in">
    <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
      <tr class="am-primary">
        <th style="width:18%">交费时间</th>
        <th style="width:9%">交费金额</th>
        <th style="width:9%">交费方式</th>
        <th style="width:35%">备注</th>
        <th>操作</th>
      </tr>
      <c:if test="${empty payList}">
        <tr>
          <td colspan="20" align="center" style="color: #ff000c;">没有找到相关数据</td>
        </tr>
      </c:if>
      <c:forEach var="pay" items="${payList}" varStatus="status">
        <tr <c:if test="${pay.payType == 4 || pay.parentId == 0}">style="color: red;"</c:if> >
          <td>
            <fmt:formatDate value="${pay.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
          </td>
          <td>${pay.money}</td>
          <td>
            <c:choose>
              <c:when test="${pay.payType == 0}">网银</c:when>
              <c:when test="${pay.payType == 1}">银行转账</c:when>
              <c:when test="${pay.payType == 2}">现金</c:when>
              <c:when test="${pay.payType == 3}">其它</c:when>
              <c:when test="${pay.payType == 4}">红冲</c:when>
              <c:when test="${pay.payType == 5}">微信</c:when>
              <c:otherwise>未知</c:otherwise>
            </c:choose>
          </td>
          <td>${pay.remark}</td>
          <td>
            <c:if test="${pay.payType != 4 && pay.parentId == -1 && isAdmin == 0}">
              <button type="button" class="am-btn am-btn-primary am-round" onclick="redC(${pay.id})"><span class="am-icon-edit"></span> 红冲</button>
            </c:if>
          </td>
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
          <c:forEach var="stuBuy" items="${detail.value}">
            <tr>
              <td>${stuBuy.createTime}</td>
              <td>
                <c:choose>
                  <c:when test="${stuBuy.type == 0}">购买教材</c:when>
                  <c:when test="${stuBuy.type == 1}">教材改价</c:when>
                  <c:otherwise>未知</c:otherwise>
                </c:choose>
              </td>
              <td>${stuBuy.money}</td>
              <td>${stuBuy.detail}</td>
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
<script>
  function redC(id){
    app.openDialog('${pageContext.request.contextPath}/redCStuEP/open.htm?id='+id, '红冲', 500, 0.3, function(index){
      var remark = $("#remark").val();
      if(remark == ""){
        app.msg("请填写红冲备注", 1);
        return false;
      }
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/redCStuEP/redC.htm",
        data:$('#redCForm').serialize(),
        async: false,
        success: function(data) {
          if(data.state == 0){
            app.msg("提交成功！", 0);
            layer.closeAll();
            app.openOneBtnDialog('${pageContext.request.contextPath}/findStudentExpenseDetail/doFindStudentExpenseDetail.htm?code=${param.code}', '明细', 1000, 0.9);
          }else{
            app.msg(data.msg, 1);
          }
        }
      });
    });
  }
</script>
