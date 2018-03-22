<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<!DOCTYPE html>--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<form id="addForm" name="addForm" target="_blank" action="${pageContext.request.contextPath}/placeOrderPackage/addPlacePackage.htm" method="post">--%>
  <%--<input type="text" style="display: none;">--%>
  <%--<div class="main-content">--%>
      <%--<ul class="create_info_list">--%>
        <%--<li>--%>
          <%--<label class="lab_80">学习中心：</label>--%>
          <%--<select id="spotCode" name="spotCode" onchange="changeSpot()">--%>
            <%--<option value="">请选择</option>--%>
            <%--<c:forEach var="spot" items="${spotList}">--%>
              <%--<option value="${spot.code}" <c:if test="${param.spotCode eq spot.code}">selected="selected" </c:if> >[${spot.code}]${spot.name}</option>--%>
            <%--</c:forEach>--%>
          <%--</select>--%>
          <%--<label class="lab_80">订单号：</label>--%>
          <%--<input type="text" id="orderCode" name="orderCode" style="width: 200px" />--%>
          <%--<label class="lab_80">快递单号：</label>--%>
          <%--<input type="text" id="logisticCode" name="logisticCode" onfocus="logisticCodeOnfocus()" style="width: 200px" />--%>
          <%--<a class="com_btn_b" href="#" id="subBut" onclick="addOrder()"><span>确&nbsp;定</span></a>&nbsp;&nbsp;--%>
          <%--<a class="com_btn_b" href="#" onclick="mergePackage()"><span>合并打包</span></a>--%>
        <%--</li>--%>
      <%--</ul>--%>
  <%--</div>--%>
  <%--<div id="packageCount_div" align="left" style="display: none">--%>
    <%--当前打包的是<span style="color: red" id="spotCode2"></span><span style="color: red" id="spotName"></span>，已扫描中心包<span style="color: red" id="orderCount"></span>个，已打包<span style="color: red" id="packageCount"></span>个，现在是第<span style="color: red" id="packageCount2"></span>包。<br />--%>
    <%--<hr />--%>
    <%--第<span style="color: red" id="packageCount3"></span>包的快递单号--%>
    <%--<br /><br />--%>
    <%--<table id="logisticCodeInfo" class="table_slist" cellpadding="0" cellspacing="0" width="600">--%>
      <%--<tr>--%>
        <%--<th width="30%">序号</th>--%>
        <%--<th width="30%">快递单号</th>--%>
      <%--</tr>--%>
    <%--</table>--%>
    <%--<br /><br />--%>
    <%--<input type="hidden" id="logisticCodes" name="logisticCodes">--%>
    <%--<a class="com_btn_b" href="#" onclick="addPackage()"><span>扫描结束，打印清单</span></a>--%>
  <%--</div>--%>
<%--</form>--%>
<%--<form id="openPageForm" name="openPageForm" action="${pageContext.request.contextPath}/printPlaceOrderPackage/open.htm" method="post" target="_blank">--%>
  <%--<input type="hidden" id="id" name="id" />--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--var num = 0;--%>
  <%--//回车事件--%>
  <%--document.onkeydown = function(e){--%>
    <%--var ev = document.all ? window.event : e;--%>
    <%--if(ev.keyCode==13) {--%>
      <%--if (0 == num) {--%>
        <%--var spotCode = $.trim($("#spotCode").val());--%>
        <%--var orderCode = $.trim($("#orderCode").val());--%>
        <%--if(spotCode == ""){--%>
          <%--alert("请选择学习中心");--%>
          <%--return;--%>
        <%--}--%>
        <%--if(orderCode == ""){--%>
          <%--$("#orderCode").focus();--%>
          <%--$("#logisticCode").val('');--%>
          <%--alert("请扫描订单号");--%>
          <%--return;--%>
        <%--}--%>

        <%--$.ajax({--%>
          <%--cache: true,--%>
          <%--type: "POST",--%>
          <%--url:"${pageContext.request.contextPath}/placeOrderPackage/searchOrderSpot.htm",--%>
          <%--data:{--%>
            <%--"spotCode":spotCode,--%>
            <%--"orderCode":orderCode--%>
          <%--},--%>
          <%--async: false,--%>
          <%--success: function(data) {--%>
            <%--if(data.state == 0){--%>
              <%--$("#logisticCode").focus();--%>
              <%--num = 1;--%>
            <%--}else{--%>
              <%--alert(data.msg);--%>
              <%--$("#orderCode").val('');--%>
              <%--$("#orderCode").focus();--%>
            <%--}--%>
          <%--}--%>
        <%--});--%>
      <%--}else{--%>
        <%--addOrder();--%>
      <%--}--%>
    <%--}--%>
  <%--}--%>

  <%--function addOrder(){--%>
    <%--var spotCode = $.trim($("#spotCode").val());--%>
    <%--var logisticCode = $.trim($("#logisticCode").val());--%>
    <%--var logisticCodes = $.trim($("#logisticCodes").val());--%>

    <%--$("#logisticCode").val("");--%>
    <%--if(spotCode == ""){--%>
      <%--alert("请选择学习中心");--%>
      <%--return;--%>
    <%--}--%>
    <%--if(logisticCode == ""){--%>
      <%--alert("请输入快递单号");--%>
      <%--return;--%>
    <%--}--%>
    <%--if(logisticCodes.indexOf(logisticCode) > -1){--%>
      <%--alert("该快递单号已经扫描");--%>
      <%--return;--%>
    <%--}--%>

    <%--var table = $("#logisticCodeInfo");--%>
    <%--var tr = $("<tr></tr>");--%>
    <%--var td = $("<td>"+num+"</td>");--%>
    <%--var td2 = $("<td style='white-space:nowrap;'>"+logisticCode+"</td>");--%>
    <%--tr.append(td);--%>
    <%--tr.append(td2);--%>
    <%--table.append(tr);--%>
    <%--$("#logisticCodes").val($("#logisticCodes").val()+logisticCode+",");--%>

    <%--num++;--%>
  <%--}--%>

  <%--function changeSpot(){--%>
    <%--var spotCode = $.trim($("#spotCode").val());--%>
    <%--if(spotCode == ""){--%>
      <%--$("#packageCount_div").hide();--%>
    <%--}else{--%>
      <%--$("#packageCount_div").show();--%>
      <%--$.ajax({--%>
        <%--cache: true,--%>
        <%--type: "POST",--%>
        <%--url:"${pageContext.request.contextPath}/placeOrderPackage/searchPackCount.htm",--%>
        <%--data:{"spotCode":spotCode},--%>
        <%--async: false,--%>
        <%--success: function(data) {--%>
          <%--if(data.state == 0){--%>
            <%--orderCount = data.orderCount;--%>
            <%--packageCount = data.packageCount;--%>
            <%--$("#spotCode2").html("("+data.spot.code+")");--%>
            <%--$("#spotName").html(data.spot.name);--%>
            <%--$("#orderCount").html(orderCount);--%>
            <%--$("#packageCount").html(packageCount);--%>
            <%--$("#packageCount2").html(packageCount + 1);--%>
            <%--$("#packageCount3").html(packageCount + 1);--%>

            <%--$("#logisticCode, #orderCode").val("");--%>
            <%--$("#orderCode").focus();--%>
            <%--//清掉已扫描的表格--%>
            <%--var i=0;--%>
            <%--$("#logisticCodeInfo").find("tr").each(function(){--%>
              <%--if(i > 0){--%>
                <%--$(this).remove();--%>
              <%--}--%>
              <%--i++;--%>
            <%--});--%>
            <%--$("#logisticCodes").val("");--%>
            <%--$("#spotName_h").val(data.spot.name);--%>
            <%--num = 0;--%>
          <%--}else{--%>
            <%--alert(data.msg);--%>
          <%--}--%>
        <%--}--%>
      <%--});--%>
    <%--}--%>
  <%--}--%>

  <%--function addPackage(){--%>
    <%--var orderCode = $.trim($("#orderCode").val());--%>
    <%--var logisticCodes = $.trim($("#logisticCodes").val());--%>
    <%--if(orderCode == ""){--%>
      <%--alert("请扫描预订单");--%>
      <%--$("#orderCode").focus();--%>
      <%--return;--%>
    <%--}--%>
    <%--if(logisticCodes == ""){--%>
      <%--alert("请扫描快递单号");--%>
      <%--$("#logisticCode").focus();--%>
      <%--return;--%>
    <%--}--%>
    <%--$("#logisticCodes").val(logisticCodes.substring(0, logisticCodes.length-1));--%>

    <%--$.ajax({--%>
      <%--cache: true,--%>
      <%--type: "POST",--%>
      <%--url:"${pageContext.request.contextPath}/placeOrderPackage/addPlacePackage.htm",--%>
      <%--data:$('#addForm').serialize(),--%>
      <%--async: false,--%>
      <%--success: function(data) {--%>
        <%--if(data.state == 0){--%>
          <%--changeSpot();--%>
          <%--$("#id").val(data.packageId);--%>
          <%--openPageForm.submit();--%>
          <%--//模拟a标签打开新页面，open要被浏览器拦截--%>
          <%--&lt;%&ndash;var url = "${pageContext.request.contextPath}/printPlaceOrderPackage/open.htm?id="+data.packageId;&ndash;%&gt;--%>
          <%--&lt;%&ndash;var a = $("<a href='"+url+"' target='_blank'>Apple</a>").get(0);&ndash;%&gt;--%>
          <%--&lt;%&ndash;var e = document.createEvent('MouseEvents');&ndash;%&gt;--%>
          <%--&lt;%&ndash;e.initEvent('click', true, true);&ndash;%&gt;--%>
          <%--&lt;%&ndash;a.dispatchEvent(e);&ndash;%&gt;--%>
          <%--&lt;%&ndash;open("${pageContext.request.contextPath}/printPlaceOrderPackage/open.htm?id="+data.packageId);&ndash;%&gt;--%>
        <%--}else{--%>
          <%--alert(data.msg);--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>
  <%--}--%>

  <%--function mergePackage(){--%>
    <%--location.href = "${pageContext.request.contextPath}/placeOrderPackage/toMergePlaceOrderPackagePage.htm";--%>
  <%--}--%>
<%--</script>--%>

<form id="addForm" name="addForm" target="_blank" action="${pageContext.request.contextPath}/placeOrderPackage/addPlacePackage.htm" method="post">
  <label >学习中心：</label>
  <select id="spotCode" name="spotCode" data-am-selected="{maxHeight: 500, searchBox: 1}" onchange="changeSpot()">
    <option value=""></option>
    <option value="null">全部</option>
    <c:forEach var="spot" items="${spotList}">
      <option value="${spot.code}" <c:if test="${param.spotCode eq spot.code}">selected="selected" </c:if> >[${spot.code}]${spot.name}</option>
    </c:forEach>
  </select>&nbsp;&nbsp;&nbsp;&nbsp;
  <label >订单号：</label>
  <input type="text" id="orderCode" name="orderCode" style="width: 200px" />&nbsp;&nbsp;&nbsp;&nbsp;
  <label >快递单号：</label>
  <input type="text" id="logisticCode" name="logisticCode" style="width: 200px" />&nbsp;&nbsp;&nbsp;&nbsp;
  <button class="am-btn am-btn-primary am-btn-s" type="button" onClick="addOrder()"><span class="am-icon-plus"></span> 确定</button>&nbsp;&nbsp;
  <button class="am-btn am-btn-primary am-btn-s" type="button" onClick="mergePackage()"><span class="am-icon-refresh"></span> 合并打包</button>

  <p /><p />
  <div id="packageCount_div" align="left" style="display: none">
    当前打包的是<span style="color: red" id="spotCode2"></span><span style="color: red" id="spotName"></span>，
    已扫描中心包<span style="color: red" id="orderCount"></span>个，已打包<span style="color: red" id="packageCount"></span>个，
    现在是第<span style="color: red" id="packageCount2"></span>包。<br />
    <hr />
    <br /><br />
    <table id="logisticCodeInfo" class="am-table am-table-bordered am-table-striped am-table-hover" style="width:30%;">
      <tr class="am-primary">
        <th>序号</th>
        <th>快递单号</th>
      </tr>
    </table>
    <input type="hidden" id="logisticCodes" name="logisticCodes">
    <button class="am-btn am-btn-primary am-btn-sm" type="button" onclick="addPackage()"><span class="am-icon-save"></span> 扫描结束，打印清单</button>
  </div>
</form>
<script>
  $("select").selected();
  //回车事件
  document.onkeydown = function(e){
    var ev = document.all ? window.event : e;
    if(ev.keyCode==13) {
      addOrder();
    }
  }

  var num = 0;
  function changeSpot(){
    app.changeSelect(this);
    var spotCode = $("#spotCode").val().trim();
    if(spotCode == ""){
      $("#packageCount_div").hide();
    }else{
      $("#packageCount_div").show();
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/placeOrderPackage/searchPackCount.htm",
        data:{"spotCode":spotCode},
        async: false,
        success: function(data) {
          if(data.state == 0){
            orderCount = data.orderCount;
            packageCount = data.packageCount;
            $("#spotCode2").html("("+data.spot.code+")");
            $("#spotName").html(data.spot.name);
            $("#orderCount").html(orderCount);
            $("#packageCount").html(packageCount);
            $("#packageCount2").html(packageCount + 1);
            $("#packageCount3").html(packageCount + 1);

            $("#logisticCode, #orderCode").val("");
            $("#orderCode").focus();
            //清掉已扫描的表格
            var i=0;
            $("#logisticCodeInfo").find("tr").each(function(){
              if(i > 0){
                $(this).remove();
              }
              i++;
            });
            $("#logisticCodes").val("");
            $("#spotName_h").val(data.spot.name);
            num = 0;
          }else{
            app.msg(data.msg, 1);
          }
        }
      });
    }
  }

  function addOrder(){
    var spotCode = $.trim($("#spotCode").val());
    var logisticCode = $.trim($("#logisticCode").val());
    var logisticCodes = $.trim($("#logisticCodes").val());

    $("#logisticCode").val("");
    if(spotCode == ""){
      app.msg("请选择学习中心", 1);
      return;
    }
    if(logisticCode == ""){
      app.msg("请输入快递单号", 1);
      return;
    }
    if(logisticCodes.indexOf(logisticCode) > -1){
      app.msg("该快递单号已经扫描", 1);
      return;
    }

    var table = $("#logisticCodeInfo");
    var tr = $("<tr></tr>");
    var td = $("<td>"+(num+1)+"</td>");
    var td2 = $("<td style='white-space:nowrap;'>"+logisticCode+"</td>");
    tr.append(td);
    tr.append(td2);
    table.append(tr);
    $("#logisticCodes").val($("#logisticCodes").val()+logisticCode+",");
    num++;
  }

  function addPackage(){
    debugger;
    var orderCode = $("#orderCode").val().trim();
    var logisticCodes = $("#logisticCodes").val().trim();
    if(orderCode == ""){
      app.msg("请扫描预订单", 1);
      $("#orderCode").focus();
      return;
    }
    if(logisticCodes == ""){
      app.msg("请扫描快递单号", 1);
      $("#logisticCode").focus();
      return;
    }
    $("#logisticCodes").val(logisticCodes.substring(0, logisticCodes.length-1));

    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/placeOrderPackage/addPlacePackage.htm",
      data:$('#addForm').serialize(),
      async: false,
      success: function(data) {
        if(data.state == 0){
          changeSpot();
          $("#id").val(data.packageId);
          //openPageForm.submit();
          //模拟a标签打开新页面，open要被浏览器拦截
          <%--var url = "${pageContext.request.contextPath}/printPlaceOrderPackage/open.htm?id="+data.packageId;--%>
          <%--var a = $("<a href='"+url+"' target='_blank'>Apple</a>").get(0);--%>
          <%--var e = document.createEvent('MouseEvents');--%>
          <%--e.initEvent('click', true, true);--%>
          <%--a.dispatchEvent(e);--%>
          open("${pageContext.request.contextPath}/printPlaceOrderPackage/open.htm?id="+data.packageId);
        }else{
          app.msg(data.msg, 1);
        }
      }
    });
  }

  function mergePackage(){
    var url = "${pageContext.request.contextPath}/placeOrderPackage/toMergePlaceOrderPackagePage.htm";
    var falg;
    if(0 <= url.indexOf("?")) {
      falg = url.substring(0, url.indexOf("?")).replace("/", "").replace("/", "").replace(".htm", "");
    }else{
      falg = url.replace("/", "").replace("/", "").replace(".htm", "");
    }
    $.ajax({
      cache: true,
      type: "POST",
      url: url,
      async: false,
      success: function (data) {
        $("#contentPage").html(data);
        app.pageHtmlJSON[falg] = data;
      }
    });
  }
</script>