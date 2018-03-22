<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="addForm" name="addForm" target="_blank" action="${pageContext.request.contextPath}/addStudentBookOrderPackage/add.htm" method="post">
  <label >学习中心：</label>
  <select id="spotCode" name="spotCode" data-am-selected="{maxHeight: 500, searchBox: 1}" onchange="changeSpot()">
    <option value="">请选择</option>
    <c:forEach var="spot" items="${spotList}">
      <option value="${spot.code}" <c:if test="${param.spotCode eq spot.code}">selected="selected" </c:if> >[${spot.code}]${spot.name}</option>
    </c:forEach>
  </select>&nbsp;&nbsp;&nbsp;&nbsp;
  <label >快递单号：</label>
  <input type="text" id="logisticCode" name="logisticCode" onfocus="logisticCodeOnfocus()" style="width: 200px" />&nbsp;&nbsp;&nbsp;&nbsp;
  <label >订单号：</label>
  <input type="text" id="orderCode" name="orderCode" style="width: 200px" />&nbsp;&nbsp;&nbsp;&nbsp;
  <button class="am-btn am-btn-primary am-btn-s" type="button" onClick="addOrder()"><span class="am-icon-plus"></span> 确定</button>

  <p /><p />
  <div id="packageCount_div" align="left" style="display: none">
    当前打包的是<span style="color: red" id="spotCode2"></span><span style="color: red" id="spotName"></span>，
    已扫描学生包<span style="color: red" id="orderCount"></span>个，已打包<span style="color: red" id="packageCount"></span>个，
    现在是第<span style="color: red" id="packageCount2"></span>包。<br />
    <hr />
    第<span style="color: red" id="packageCount3"></span>包的学生明细
    <br /><br />
    <table id="studentInfoHead" class="am-table am-table-bordered am-table-striped am-table-hover" style="width:30%;">
      <tr class="am-primary">
        <th width="10%">序号</th>
        <th width="45%">学号</th>
        <th width="45%">姓名</th>
      </tr>
    </table>
    <table id="studentInfo" class="am-table am-table-bordered am-table-striped am-table-hover" style="width:30%; margin-top: -13px;">
    </table>
    <input type="hidden" id="orderCodes" name="orderCodes">
    <input type="hidden" id="spotName_h" name="spotName">
    <input type="hidden" id="student_code_names" name="student_code_names">
    <button class="am-btn am-btn-primary am-btn-sm" type="button" onclick="addPackage()"><span class="am-icon-save"></span> 扫描结束，打印清单</button>
  </div>
</form>
<script>
  $("select").selected();
  var num = 0;
  var orderCount = 0;
  var packageCount = 0;

  //回车事件
  document.onkeydown = function(e){
    var ev = document.all ? window.event : e;
    if(ev.keyCode==13) {
      if(0 == num){
        var spotCode = $("#spotCode").val().trim();
        var logisticCode = $("#logisticCode").val().trim();

        if(spotCode == ""){
          app.msg("请选择学习中心", 1);
          return;
        }
        if(logisticCode == ""){
          $("#logisticCode").focus();
          $("#orderCode").val('');
          app.msg("请扫快递单号", 1);
          return;
        }
        if(logisticCode.indexOf("FS") == 0){
          $("#logisticCode").val('');
          $("#orderCode").val('');
          app.msg("快递单号扫描错误", 1);
          $("#logisticCode").focus();
          return;
        }

        $("#orderCode").focus();
        num = 1;
      }else {
        addOrder();
      }
    }
  }

  function logisticCodeOnfocus(){
    num = 0;
  }

  function changeSpot(){
    app.changeSelect(this);
    var spotCode = $.trim($("#spotCode").val());
    if(spotCode == ""){
      $("#packageCount_div").hide();
    }else{
      $("#packageCount_div").show();
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/addStudentBookOrderPackage/searchPackCount.htm",
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
            $("#logisticCode").focus();
            //清掉已扫描的表格
            $("#studentInfo").find("tr").each(function(){
              $(this).remove();
            });
            isSendStudent = -1;
            $("#pageType").html("");
            $("#orderCodes").val("");
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
    if(isSendStudent == 1){
      app.msg("快递给学生的订单，一次只能打一个包，不能在扫描多个包。", 1);
    }else{
      setTimeout(function(){
        var spotCode = $("#spotCode").val().trim();
        var orderCode = $("#orderCode").val().trim();
        var orderCodes = $("#orderCodes").val().trim();
        $("#orderCode").val("");
        if(spotCode == ""){
          app.msg("请选择学习中心", 1);
          return;
        }
        if(orderCode == ""){
          app.msg("请输入订单编号", 1);
          return;
        }
        if(orderCodes.indexOf(orderCode) > -1){
          app.msg("该订单号已经扫描", 1);
          return;
        }
        $.ajax({
          cache: true,
          type: "POST",
          url:"${pageContext.request.contextPath}/addStudentBookOrderPackage/searchOrderStudent.htm",
          data:{
            "spotCode":spotCode,
            "orderCode":orderCode,
            "isSendStudent":isSendStudent
          },
          async: false,
          success: function(data) {
            if(data.state == 0){
              isSendStudent = data.isSendStudent;
              if(isSendStudent == 0){
                $("#pageType").html("本次打包是快递给中心的");
              }
              if(isSendStudent == 1){
                $("#pageType").html("本次打包是快递给学生的，一次只能扫一个包");
              }
              var table = $("#studentInfo");
              var tableHtml = table.html();
              table.html("");
              var tr = $("<tr></tr>");
              var td = $("<td style='width: 10%'>"+num+"</td>");
              var td2 = $("<td style='width: 45%'>"+data.code+"</td>");
              var td3 = $("<td style='width: 45%'>"+data.name+"</td>");
              tr.append(td);
              tr.append(td2);
              tr.append(td3);
              table.append(tr);
              table.append(tableHtml);
              $("#orderCodes").val($("#orderCodes").val()+orderCode+",");
              $("#student_code_names").val($("#student_code_names").val()+data.code+"_"+data.name+",");
              num += 1;
            }else{
              app.msg(data.msg, 1);
            }
          }
        });
      }, 500);
    }
  }

  function addPackage(){
    var student_code_names = $("#student_code_names").val().trim();
    var orderCodes = $("#orderCodes").val().trim();
    var logisticCode = $("#logisticCode").val().trim();
    if(logisticCode == ""){
      app.msg("请扫描快递单号", 1);
      return;
    }
    if(logisticCode.indexOf("FS") == 0){
      app.msg("快递单号错误", 1);
      return;
    }
    if(orderCodes == "" || student_code_names == ""){
      app.msg("请扫描发书单", 1);
      return;
    }
    $("#orderCodes").val(orderCodes.substring(0, orderCodes.length-1));
    $("#student_code_names").val(student_code_names.substring(0, student_code_names.length-1));

    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/addStudentBookOrderPackage/add.htm",
      data:$('#addForm').serialize(),
      async: false,
      success: function(data) {
        if(data.state == 0){
          changeSpot();
          $("#id").val(data.packageId);
          //openPageForm.submit();
          //模拟a标签打开新页面，open要被浏览器拦截
          <%--var url = "${pageContext.request.contextPath}/printStudentBookOrderPackage/open.htm?id="+data.packageId;--%>
          <%--var a = $("<a href='"+url+"' target='_blank'>Apple</a>").get(0);--%>
          <%--var e = document.createEvent('MouseEvents');--%>
          <%--e.initEvent( 'click', true, true );--%>
          <%--a.dispatchEvent(e);--%>
          open("${pageContext.request.contextPath}/printStudentBookOrderPackage/open.htm?id="+data.packageId);
        }else{
          app.msg(data.msg);
        }
      }
    });
  }
</script>