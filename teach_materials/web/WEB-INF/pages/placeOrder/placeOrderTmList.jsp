<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<div class="main-content" style="overflow-y:scroll; height: 95%;">--%>
  <%--<ul class="create_info_list">--%>
    <%--<li>--%>
      <%--<label class="lab_80">订单号：</label>${param.orderCode}--%>
      <%--<label class="lab_80">中心编号：</label>${spot.code}--%>
      <%--<label class="lab_80">中心名称：</label>${spot.name}--%>
    <%--</li>--%>
    <%--<li>--%>
      <%--<label class="lab_80">邮寄地址：</label>${address}--%>
    <%--</li>--%>
    <%--<li>--%>
      <%--<label class="lab_80">收件人：</label>${adminName}--%>
    <%--</li>--%>
  <%--</ul>--%>
  <%--<form id="addForm" name="addForm" method="post">--%>
    <%--<input type="hidden" id="orderCode" name="orderCode" value="${param.orderCode}" />--%>
    <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
      <%--<tr>--%>
        <%--<th style="width: 30px;">序号</th>--%>
        <%--<th>课程名称</th>--%>
        <%--<th style="width: 300px;">教材名称</th>--%>
        <%--<th style="width: 60px;">教材数量</th>--%>
        <%--<th style="width: 60px;">教材价格</th>--%>
        <%--<th style="width: 60px;">操作人</th>--%>
        <%--<th style="width: 80px;">操作时间</th>--%>
      <%--</tr>--%>
      <%--<c:if test="${empty placeOrderDetailShowList}">--%>
        <%--<tr>--%>
          <%--<td colspan="20" align="center" style="color: #ff000c;">没有找到相关数据</td>--%>
        <%--</tr>--%>
      <%--</c:if>--%>
      <%--<c:set var="beforeCourseCode" />--%>
      <%--<c:forEach var="placeOrderDetailShow" items="${placeOrderDetailShowList}" varStatus="status">--%>
        <%--<input type="hidden" value="${placeOrderDetailShow.placeOrderDetailId}" class="hidClaInp"/>--%>
        <%--<tr>--%>
          <%--<td align="center">${status.index+1}</td>--%>
          <%--<td>${placeOrderDetailShow.courseCode}${placeOrderDetailShow.courseName}</td>--%>
          <%--<td>${placeOrderDetailShow.materialName}</td>--%>
          <%--<td>--%>
            <%--<c:if test="${(param.state == 1 && beforeCourseCode ne placeOrderDetailShow.courseCode) || ('0' ne param.isAuto && param.state == 1)}">--%>
              <%--<input maxlength="6" style="width: 50px;" id="${placeOrderDetailShow.placeOrderDetailId}" value="${placeOrderDetailShow.count}" onblur="checkUserIn(this);"/>--%>
            <%--</c:if>--%>
            <%--<c:if test="${param.state > 1}">--%>
              <%--<div style="float: left">${placeOrderDetailShow.count}</div>--%>
            <%--</c:if>--%>
          <%--</td>--%>
          <%--<td>${placeOrderDetailShow.materialPrice}</td>--%>
          <%--<td>${placeOrderDetailShow.creator}</td>--%>
          <%--<td>--%>
            <%--<fmt:formatDate value="${placeOrderDetailShow.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />--%>
          <%--</td>--%>
        <%--</tr>--%>
        <%--<c:set var="beforeCourseCode" value="${placeOrderDetailShow.courseCode}" />--%>
      <%--</c:forEach>--%>
      <%--<tr>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--<td align="right">合计：</td>--%>
        <%--<td>${sumTotalPrice}</td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>
      <%--</tr>--%>
    <%--</table>--%>
  <%--</form>--%>
  <%--<br /><br />--%>
  <%--<c:if test="${param.state == 1}">--%>
    <%--<a class="com_btn_b" href="#" onclick="modifyMaterialCount()"><span>修改数量</span></a>--%>
  <%--</c:if>--%>
  <%--<br />--%>
  <%--<c:if test="${empty kuaidiMap}">--%>
    <%--<span style="color: red">没有找到快递信息</span>--%>
  <%--</c:if>--%>
  <%--<c:if test="${!empty kuaidiMap}">--%>
    <%--<c:forEach var="kuaidiMap" items="${kuaidiMap}">--%>
      <%--<label class="lab_80">快递单号：</label>${kuaidiMap.key}<br /><br />--%>
      <%--<label class="lab_80">快递信息：</label><br /><br />--%>
      <%--<c:if test="${empty kuaidiMap.value}">--%>
        <%--<span style="color: red">没有找到快递信息</span><br /><br />--%>
      <%--</c:if>--%>
      <%--<c:if test="${!empty kuaidiMap.value}">--%>
        <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
          <%--<c:forEach var="json" items="${kuaidiMap.value}">--%>
            <%--<tr style="height: 50px">--%>
              <%--<td style="width: 15%">${json.ftime}</td>--%>
              <%--<td style="width: 85%">${json.context}</td>--%>
            <%--</tr>--%>
          <%--</c:forEach>--%>
        <%--</table>--%>
        <%--<br /><br />--%>
      <%--</c:if>--%>
    <%--</c:forEach>--%>
  <%--</c:if>--%>
<%--</div>--%>
<%--<script>--%>
  <%--function checkUserIn(inputObj){--%>
    <%--var str = $.trim($(inputObj).val());--%>
    <%--if(str == ""){--%>
      <%--return;--%>
    <%--}--%>
    <%--if(!isNumber(str)){--%>
      <%--alert("请输入数字");--%>
      <%--$(inputObj).val("");--%>
    <%--}--%>
  <%--}--%>

  <%--function modifyMaterialCount(){--%>
    <%--var material_order_str = "";--%>
    <%--var dataArr = $(".hidClaInp");--%>
    <%--for(var i = 0;i < dataArr.length;i ++){--%>
      <%--var detailId = dataArr[i].value;--%>
      <%--var v_count = $("#" + detailId).val();--%>
      <%--if(trim(v_count) == ""){--%>
        <%--alert("数量不能为空");--%>
        <%--return;--%>
      <%--}--%>
      <%--if(!isNumber(v_count)){--%>
        <%--alert("请输入数字");--%>
        <%--return;--%>
      <%--}--%>
      <%--material_order_str += detailId + "_" + v_count + "a";--%>
    <%--}--%>
    <%--material_order_str = material_order_str.substring(0,material_order_str.length - 1);--%>

    <%--$.ajax({--%>
      <%--type: "POST",--%>
      <%--url:"${pageContext.request.contextPath}/placeOrder/updatePlaceOrder.htm",--%>
      <%--data:"placeOrderId=" + "${param.orderId}" + "&material_order_str=" + material_order_str,--%>
      <%--async: false,--%>
      <%--success: function(data) {--%>
        <%--if(data.state == 0){--%>
          <%--alert("提交成功！");--%>
          <%--$('#index_iframe').contents().find("#subBut").click();--%>
          <%--closeDialog();--%>
        <%--}else{--%>
          <%--alert(data.msg);--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>
  <%--}--%>
<%--</script>--%>

<div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
  <div class="am-panel-hd am-cf" data-am-collapse="{target: '#order'}">订单信息<span class="am-icon-chevron-down am-fr"></span></div>
  <div id="order" class="am-in">
    <div class="am-g am-margin-top">
      <div class="am-u-sm-2 am-text-right"><label >订单号：</label></div>
      <div class="am-u-sm-8" style="float:left">${param.orderCode}</div>
    </div>

    <div class="am-g am-margin-top">
      <div class="am-u-sm-2 am-text-right"><label >学习中心：</label></div>
      <div class="am-u-sm-8" style="float:left">[${spot.code}]${spot.name}</div>
    </div>

    <div class="am-g am-margin-top">
      <div class="am-u-sm-2 am-text-right"><label >邮寄地址：</label></div>
      <div class="am-u-sm-8" style="float:left">${address}</div>
    </div>

    <div class="am-g am-margin-top">
      <div class="am-u-sm-2 am-text-right"><label >收件人：</label></div>
      <div class="am-u-sm-8" style="float:left">${adminName}</div>
    </div>
  </div>
</div>

<div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
  <div class="am-panel-hd am-cf" data-am-collapse="{target: '#orderTM'}">订单明细<span class="am-icon-chevron-down am-fr"></span></div>
  <div id="orderTM" class="am-in">
    <form id="addForm" name="addForm" method="post">
      <input type="hidden" id="orderCode" name="orderCode" value="${param.orderCode}" />
      <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
        <tr class="am-primary">
          <th style="width: 5%;">序号</th>
          <th style="width: 18%;">课程名称</th>
          <th style="width: 30%;">教材名称</th>
          <th style="width: 9%;">教材数量</th>
          <th style="width: 9%;">教材价格</th>
          <th style="width: 8%;">操作人</th>
          <th>操作时间</th>
        </tr>
        <c:if test="${empty placeOrderDetailShowList}">
          <tr>
            <td colspan="20" align="center" style="color: #ff000c;">没有找到相关数据</td>
          </tr>
        </c:if>
        <c:set var="beforeCourseCode" />
        <c:forEach var="placeOrderDetailShow" items="${placeOrderDetailShowList}" varStatus="status">
          <input type="hidden" value="${placeOrderDetailShow.placeOrderDetailId}" class="hidClaInp"/>
          <tr>
            <td align="center">${status.index+1}</td>
            <td>${placeOrderDetailShow.courseCode}${placeOrderDetailShow.courseName}</td>
            <td>${placeOrderDetailShow.materialName}</td>
            <td>
              <c:if test="${(param.state == 1 && beforeCourseCode ne placeOrderDetailShow.courseCode) || ('0' ne param.isAuto && param.state == 1)}">
                <input maxlength="6" style="width: 50px;" id="${placeOrderDetailShow.placeOrderDetailId}" value="${placeOrderDetailShow.count}"/>
              </c:if>
              <c:if test="${param.state > 1}">
                <div style="float: left">${placeOrderDetailShow.count}</div>
              </c:if>
            </td>
            <td>${placeOrderDetailShow.materialPrice}</td>
            <td>${placeOrderDetailShow.creator}</td>
            <td>
              <fmt:formatDate value="${placeOrderDetailShow.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
            </td>
          </tr>
          <c:set var="beforeCourseCode" value="${placeOrderDetailShow.courseCode}" />
        </c:forEach>
        <tr>
          <td></td>
          <td></td>
          <td></td>
          <td align="right">合计：</td>
          <td>${sumTotalPrice}</td>
          <td></td>
          <td></td>
        </tr>
      </table>
    </form>
  </div>
</div>

<c:if test="${!empty kuaidiMap}">
  <c:forEach var="kuaidiMap" items="${kuaidiMap}" varStatus="status">
    <div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
      <div class="am-panel-hd am-cf" data-am-collapse="{target: '#kuaidi_${status}'}">快递信息，单号：${kuaidiMap.key}<span class="am-icon-chevron-down am-fr"></span></div>
      <div id="kuaidi_${status}" class="am-in">
        <c:if test="${empty kuaidiMap.value}">
          <span style="color: red">没有找到快递信息</span><br /><br />
        </c:if>
        <c:if test="${!empty kuaidiMap.value}">
          <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
            <c:forEach var="json" items="${kuaidiMap.value}">
              <tr style="height: 50px">
                <td style="width: 15%">${json.ftime}</td>
                <td style="width: 85%">${json.context}</td>
              </tr>
            </c:forEach>
          </table>
          <br /><br />
        </c:if>
      </div>
    </div>
  </c:forEach>
</c:if>

