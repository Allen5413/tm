<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="addForm" name="addForm" target="_blank" action="${pageContext.request.contextPath}/placeOrderPackage/addMergePlacePackage.htm" method="post">
  <label >学习中心：</label>
  <select id="spotCode" name="spotCode" data-am-selected="{maxHeight: 500, searchBox: 1}" onchange="changeSpot()">
    <option value="">请选择</option>
    <c:forEach var="spot" items="${spotList}">
      <option value="${spot.code}" <c:if test="${param.spotCode eq spot.code}">selected="selected" </c:if> >[${spot.code}]${spot.name}</option>
    </c:forEach>
  </select>&nbsp;&nbsp;&nbsp;&nbsp;
  <label >订单号：</label>
  <input type="text" id="orderCode" name="orderCode" style="width: 200px" />&nbsp;&nbsp;&nbsp;&nbsp;
  <label >快递单号：</label>
  <input type="text" id="logisticCode" name="logisticCode" onfocus="logisticCodeOnfocus()" style="width: 200px" />&nbsp;&nbsp;&nbsp;&nbsp;
  <button class="am-btn am-btn-primary am-btn-s" type="button" onClick="addOrder()"><span class="am-icon-plus"></span> 确定</button>
  <button class="am-btn am-btn-primary am-btn-s" type="button" onClick="onePackage()"><span class="am-icon-refresh"></span> 合并打包</button>

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
        <th>订单号</th>
      </tr>
    </table>
    <input type="hidden" id="logisticCodes" name="logisticCodes">
    <input type="hidden" id="spotName_h" name="spotName">
    <button class="am-btn am-btn-primary am-btn-sm" type="button" onclick="addPackage()"><span class="am-icon-save"></span> 扫描结束，打印清单</button>
  </div>
</form>
<script>
  $("select").selected();
  var number = 0;
  var orderCount = 0;
  var packageCount = 0;

  //回车事件
  document.onkeydown = function(e){
    var ev = document.all ? window.event : e;
    if(ev.keyCode==13) {
      if(0 == number){
        var spotCode = $("#spotCode").val().trim();
        var orderCode = $("#orderCode").val().trim();
        var logisticCode = $("#logisticCode").val().trim();

        if(spotCode == ""){
          app.msg("请选择学习中心", 1);
          return;
        }
        if(orderCode == ""){
          $("#orderCode").focus();
          $("#logisticCode").val('');
          app.msg("请扫订单号", 1);
          return;
        }
        if(logisticCode.indexOf("FS") == 0){
          $("#logisticCode").val('');
          app.msg("快递单号扫描错误", 1);
          $("#logisticCode").focus();
          return;
        }
        $("#logisticCode").focus();
        number = 1;
      }else {
        addOrder();
      }
    }
  }

  function changeSpot(){
    var spotCode = $("#spotCode").val().trim();
    if(spotCode == ""){
      $("#packageCount_div").hide();
    }else{
      $("#packageCount_div").show();
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/addOnceOrderPackage/searchPackCount.htm",
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
            number = 0;
          }else{
            app.msg(data.msg);
          }
        }
      });
    }
  }

  function addOrder(){
    setTimeout(function(){
      var spotCode = $("#spotCode").val().trim();
      var orderCode = $("#orderCode").val();
      var logisticCode = $("#logisticCode").val();
      var logisticCodes = $("#logisticCodes").val().trim();
      $("#logisticCode").val("");
      if(spotCode == ""){
        app.msg("请选择学习中心", 1);
        return;
      }
      if(orderCode == ""){
        app.msg("请输入订单编号", 1);
        return;
      }
      if(logisticCodes.indexOf(logisticCode) > -1){
        app.msg("该快递单号已经扫描", 1);
        return;
      }

      var table = $("#logisticCodeInfo");
      var tr = $("<tr></tr>");
      var td = $("<td>"+(number)+"</td>");
      var td2 = $("<td style='white-space:nowrap;'>"+logisticCode+"</td>");
      tr.append(td);
      tr.append(td2);
      table.append(tr);
      $("#logisticCodes").val($("#logisticCodes").val()+logisticCode+",");
      number += 1;

    }, 500);
  }

  function addPackage(){
    var orderCode = $("#orderCode").val().trim();
    var logisticCodes = $("#logisticCodes").val().trim();
    if(orderCode == ""){
      app.msg("请扫描订单单号", 1);
      return;
    }
    if(logisticCodes.indexOf("FS") == 0){
      app.msg("快递单号错误", 1);
      return;
    }
    if(logisticCodes == ""){
      app.msg("请扫描快递单", 1);
      return;
    }
    $("#logisticCodes").val(logisticCodes.substring(0, logisticCodes.length-1));

    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/addOnceOrderPackage/addMerge.htm",
      data:$('#addForm').serialize(),
      async: false,
      success: function(data) {
        if(data.state == 0){
          changeSpot();
          $("#id").val(data.packageId);
          open("${pageContext.request.contextPath}/printStudentBookOrderPackage/open.htm?id="+data.packageId);
        }else{
          app.msg(data.msg, 1);
        }
      }
    });
  }

  function onePackage(){
    var url = "${pageContext.request.contextPath}/addOnceOrderPackage/open.htm";
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