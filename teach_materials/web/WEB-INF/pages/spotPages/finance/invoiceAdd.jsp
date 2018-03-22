<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="main-content">--%>
  <%--<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/addInvoice/open.htm" method="post">--%>
    <%--<input type="hidden" name="method" value="search"/>--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<table width="80%">--%>
          <%--<tr style="height: 30px;">--%>
            <%--<Td>--%>
              <%--<label class="lab_80">学习中心：</label>[${spot.code}]${spot.name}--%>
            <%--</Td>--%>
            <%--<Td>--%>
              <%--<label class="lab_80">学号：</label>--%>
              <%--<input type="text" id="studentCode" name="studentCode" class="input_240" style="width: 150px;" value="${param.studentCode}" />--%>
            <%--</Td>--%>
          <%--</tr>--%>
          <%--<tr style="height: 30px;">--%>
            <%--<Td>--%>
              <%--<label class="lab_80">专业：</label>--%>
              <%--<select id="specCode" name="specCode">--%>
                <%--<option value="">请选择</option>--%>
                <%--<c:forEach var="spec" items="${specList}">--%>
                  <%--<option value="${spec.code}" <c:if test="${param.specCode eq spec.code}">selected="selected" </c:if> >[${spec.code}]${spec.name}</option>--%>
                <%--</c:forEach>--%>
              <%--</select>--%>
            <%--</Td>--%>
            <%--<Td>--%>
              <%--<label class="lab_80">层次：</label>--%>
              <%--<select id="levelCode" name="levelCode">--%>
                <%--<option value="">请选择</option>--%>
                <%--<c:forEach var="level" items="${levelList}">--%>
                  <%--<option value="${level.code}" <c:if test="${param.levelCode eq level.code}">selected="selected" </c:if> >${level.name}</option>--%>
                <%--</c:forEach>--%>
              <%--</select>--%>
            <%--</Td>--%>
          <%--</tr>--%>
          <%--<tr style="height: 30px;">--%>
            <%--<Td>--%>
              <%--<label class="lab_80">入学年：</label>--%>
              <%--<input type="text" id="year" name="year" class="input_240" style="width: 150px;" value="${param.year}" />--%>
            <%--</Td>--%>
            <%--<Td>--%>
              <%--<label class="lab_80">入学季：</label>--%>
              <%--<select id="quarter" name="quarter">--%>
                <%--<option value="">请选择</option>--%>
                <%--<option value="0" <c:if test="${param.quarter eq '0'}">selected="selected" </c:if> >春季</option>--%>
                <%--<option value="1" <c:if test="${param.quarter eq '1'}">selected="selected" </c:if> >秋季</option>--%>
              <%--</select>--%>
            <%--</Td>--%>
          <%--</tr>--%>
          <%--<Tr style="height: 30px;">--%>
            <%--<td>--%>
              <%--<a class="com_btn_b" href="#" id="subBut" onclick="pageForm.submit();"><span>查&nbsp;询</span></a>--%>
            <%--</td>--%>
          <%--</Tr>--%>
        <%--</table>--%>
      <%--</li>--%>
    <%--</ul>--%>
  <%--</form>--%>
  <%--<c:if test="${param.method eq 'search'}">--%>
    <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
      <%--<tr>--%>
        <%--<th style="width: 10%; text-align: center">--%>
          <%--<a href="#" onclick="checkAll('cb')" style="color: #0092DC">全选</a>|--%>
          <%--<a href="#" onclick="checkNall('cb')" style="color: #0092DC">反选</a>--%>
        <%--</th>--%>
        <%--<th style="width: 10%;">学号</th>--%>
        <%--<th style="width: 10%;">姓名</th>--%>
        <%--<th style="width: 20%;">专业</th>--%>
        <%--<th style="width: 10%;">层次</th>--%>
        <%--<th style="width: 10%;">缴费</th>--%>
        <%--<th style="width: 10%;">已开票金额</th>--%>
        <%--<th style="width: 20%;">开票金额</th>--%>
      <%--</tr>--%>
      <%--<c:if test="${empty jsonArray}">--%>
        <%--<tr>--%>
          <%--<td colspan="99" align="center" style="color: red;">没有找到相关数据</td>--%>
        <%--</tr>--%>
      <%--</c:if>--%>
      <%--<c:forEach var="json" items="${jsonArray}">--%>
        <%--<tr>--%>
          <%--<td align="center">--%>
            <%--<input type='checkbox' name='cb' value="${json.studentCode}" onchange='selectTR()'>--%>
            <%--<input type="hidden" id="${json.studentCode}_maxMoney" value="${json.openMoney}"/>--%>
          <%--</td>--%>
          <%--<td>${json.studentCode}</td>--%>
          <%--<td>${json.name}</td>--%>
          <%--<td>${json.specName}</td>--%>
          <%--<td>${json.levelName}</td>--%>
          <%--<td>${json.pay}</td>--%>
          <%--<td>${json.money}</td>--%>
          <%--<td>--%>
            <%--<input type="text" id="${json.studentCode}" value="${json.openMoney}"/>--%>
          <%--</td>--%>
        <%--</tr>--%>
      <%--</c:forEach>--%>
    <%--</table>--%>
  <%--</c:if>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function sub(obj){--%>
    <%--var codeMoneys = "";--%>
    <%--var isCheck = false;--%>
    <%--var isVaild = true;--%>
    <%--$("[name=cb]").each(function(){--%>
      <%--if($(this).prop("checked")){--%>
        <%--var code = $(this).val();--%>
        <%--var money = $("#"+code).val();--%>
        <%--var maxMoney = $("#"+code+"_maxMoney").val();--%>

        <%--if(!vaild.vaildMoney(money)){--%>
          <%--alert("学号："+$(this).val()+"，开票金额格式错误！");--%>
          <%--isVaild = false;--%>
        <%--}else {--%>
          <%--if(Number(money) > Number(maxMoney)){--%>
            <%--alert("学号："+$(this).val()+"，开票金额超额！");--%>
            <%--isVaild = false;--%>
          <%--}else {--%>
            <%--codeMoneys += code + "_" + money + ","--%>
          <%--}--%>
        <%--}--%>
        <%--isCheck = true;--%>
      <%--}--%>
    <%--});--%>
    <%--if(!isCheck){--%>
      <%--alert("请选择要开票的学生");--%>
    <%--}else{--%>
      <%--if(isVaild){--%>
        <%--$.ajax({--%>
          <%--cache: true,--%>
          <%--type: "POST",--%>
          <%--url:"${pageContext.request.contextPath}/addInvoice/add.htm",--%>
          <%--data:{"codeMoneys" : codeMoneys.substr(0, codeMoneys.length-1), "spotCode":"${spot.code}"},--%>
          <%--async: false,--%>
          <%--success: function(data) {--%>
            <%--if(data.state == 0){--%>
              <%--$(obj).click();--%>
              <%--alert("提交成功！");--%>
              <%--Dialog.close();--%>
            <%--}else{--%>
              <%--alert(data.msg);--%>
            <%--}--%>
          <%--}--%>
        <%--});--%>
      <%--}--%>
    <%--}--%>
  <%--}--%>
<%--</script>--%>


<form id="stuForm" name="stuForm" action="${pageContext.request.contextPath}/addInvoice/findStudent.htm" method="post">
  <table width="100%">
    <tr height="40">
      <td align="right"><label >学习中心：</label></td>
      <td>[${spot.code}]${spot.name}</td>
      <td align="right"><label >入学年：</label></td>
      <td><input type="text" id="add_year" name="add_year" value="${param.add_year}" /></td>
      <td align="right"><label >入学季：</label></td>
      <td>
        <select id="add_quarter" name="add_quarter">
          <option value="">--请选择--</option>
          <option value="0" <c:if test="${param.add_quarter eq '0'}">selected="selected" </c:if> >春季</option>
          <option value="1" <c:if test="${param.add_quarter eq '1'}">selected="selected" </c:if> >秋季</option>
        </select>
      </td>
    </tr>
    <tr height="40">
      <td align="right"><label >专业：</label></td>
      <td>
        <select id="add_specCode" name="add_specCode">
          <option value="">--请选择--</option>
          <c:forEach var="spec" items="${specList}">
            <option value="${spec.code}" <c:if test="${param.add_specCode eq spec.code}">selected="selected" </c:if>>[${spec.code}]${spec.name}</option>
          </c:forEach>
        </select>
      </td>
      <td align="right"><label >层次：</label></td>
      <td>
        <select id="add_levelCode" name="add_levelCode">
          <option value="">--请选择--</option>
          <c:forEach var="level" items="${levelList}">
            <option value="${level.code}" <c:if test="${param.add_levelCode eq level.code}">selected="selected" </c:if> >${level.name}</option>
          </c:forEach>
        </select>
      </td>
      <td align="right"><label >学号：</label></td>
      <td><input type="text" id="add_studentCode" name="add_studentCode" value="${param.add_studentCode}" /></td>
    </tr>
    <tr>
      <td colspan="99" style="padding-left:20px;">
        <button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example"
                data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '查询'}"
                onclick="searchStu(this)"><span class="am-icon-search"></span> 查询</button>
      </td>
    </tr>
  </table>
</form>
<p /><p />
<table id="stuTable" class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <tr class="am-primary">
    <th style="width: 10%; text-align: center">
      <a href="#" onClick="app.checkAll('cb')">全选</a>|<a href="#" onClick="app.checkNAll('cb')">反选</a>
    </th>
    <th style="width: 10%;">学号</th>
    <th style="width: 10%;">姓名</th>
    <th style="width: 20%;">专业</th>
    <th style="width: 10%;">层次</th>
    <th style="width: 10%;">缴费</th>
    <th style="width: 10%;">已开票金额</th>
    <th style="width: 20%;">开票金额</th>
  </tr>
</table>
<script>
  function searchStu(btnObj){
    var i = 0;
    $("#stuTable").find("tr").each(function(){
      if(0 < i){
        $(this).remove();
      }
      i++;
    });
    $(btnObj).button('loading');
    setTimeout(function(){
      $.ajax({
        cache: true,
        type: "POST",
        url: "${pageContext.request.contextPath}/addInvoice/findStudent.htm",
        async: false,
        data: $("#stuForm").serialize(),
        success: function (data) {
          var table = $("#stuTable");
          if(typeof (data.jsonData) == "undefined" || 0 == data.jsonData.length){
            var tr = $("<tr></tr>");
            var td = $("<td colspan=\"99\" align=\"center\" style=\"color: red;\">没有找到相关数据</td>");
            tr.append(td);
            table.append(tr);
          }else{
            for(var i=0; i<data.jsonData.length; i++){
              var json = data.jsonData[i];
              var tr = $("<tr></tr>");
              var td = $("<td>" +
              "<label class=\"am-checkbox am-secondary\" style=\"margin-top:5px; margin-left:24px;\">" +
              "<input type=\"checkbox\" name=\"cb\" value="+json.studentCode+" onclick=\"changeColor(this)\" data-am-ucheck>" +
              "<input type=\"hidden\" id="+json.studentCode+"_maxMoney"+" value="+json.openMoney+" >" +
              "</label></td>");
              tr.append(td);
              for ( var key in json ){
                if("openMoney" == key){
                  td = $("<td><input type=\"text\" id="+json.studentCode+" value="+json[key]+"></td>");
                }else {
                  td = $("<td>" + json[key] + "</td>");
                }
                tr.append(td);
              }
              table.append(tr);
            }
          }
          $(btnObj).button('reset');
        }
      });
    }, 100);
  }
</script>