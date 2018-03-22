<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form id="addForm" name="addForm" target="_blank" action="${pageContext.request.contextPath}/addStudentBookOrderPackageForStudent/add.htm" method="post">
  <label >快递单号：</label>
  <input type="text" id="logisticCode" name="logisticCode" onfocus="logisticCodeOnfocus()" style="width: 200px" />&nbsp;&nbsp;&nbsp;&nbsp;
  <label >订单号：</label>
  <input type="text" id="orderCode" name="orderCode" style="width: 200px" />&nbsp;&nbsp;&nbsp;&nbsp;
  <button class="am-btn am-btn-primary am-btn-s" type="button" onClick="addPackage()"><span class="am-icon-plus"></span> 确定</button>

  <p /><p />
  <div id="packageCount_div" align="left" style="display: none">
    已打包<span style="color: red" id="packageCount"></span>个
    <hr />
    <br /><br />
    <table id="studentInfo" class="am-table am-table-bordered am-table-striped am-table-hover" style="width:30%;">
      <tr class="am-primary">
        <th width="10%">序号</th>
        <th width="45%">学号</th>
        <th width="45%">姓名</th>
      </tr>
    </table>
  </div>
</form>
<script>
  var num = 0;
  var orderCount = 0;
  var packageCount = 0;

  //回车事件
  document.onkeydown = function(e){
    var ev = document.all ? window.event : e;
    if(ev.keyCode==13) {
      if(0 == num){
        var logisticCode = $("#logisticCode").val().trim();
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
        addPackage();
      }
    }
  }

  function addPackage(){
    var orderCode = $("#orderCode").val().trim();
    var logisticCode = $("#logisticCode").val().trim();
    if(logisticCode == ""){
      app.msg("请扫描快递单号", 1);
      return;
    }
    if(logisticCode.indexOf("FS") == 0){
      app.msg("快递单号错误", 1);
      return;
    }
    if(orderCode == ""){
      app.msg("请扫描发书单", 1);
      return;
    }

    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/addStudentBookOrderPackageForStudent/add.htm",
      data:$('#addForm').serialize(),
      async: false,
      success: function(data) {
        if(data.state == 0){
          packageCount++;
          $("#packageCount_div").show();
          $("#orderCode").val("");
          $("#logisticCode").val("");
          var table = $("#studentInfo");
          var tr = $("<tr></tr>");
          var td = $("<td>"+packageCount+"</td>");
          var td2 = $("<td>"+data.studentCode+"</td>");
          var td3 = $("<td>"+data.studentName+"</td>");
          tr.append(td);
          tr.append(td2);
          tr.append(td3);
          table.append(tr);
          $("#packageCount").html(packageCount);
          $("#logisticCode").focus();
        }else{
          app.msg(data.msg);
        }
      }
    });
  }
</script>