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
  <%--<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findStuEPage/findStuEPageByWhere.htm" method="post">--%>
    <%--<input type="hidden" name="method" value="search"/>--%>
    <%--<input type="hidden" id="page" name="page" value="${pageInfo.currentPage}"/>--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<label class="lab_80">学习中心：</label>--%>
        <%--[${spot.code}]${spot.name}--%>
        <%--<label class="lab_80">学期：</label>--%>
        <%--<select id="semesterId" name="semesterId">--%>
          <%--<option value="">--请选择--</option>--%>
          <%--<c:forEach var="semester" items="${semesterList}">--%>
            <%--<option value="${semester.id}" <c:if test="${param.semesterId == semester.id}">selected="selected" </c:if> >${semester.semester}</option>--%>
          <%--</c:forEach>--%>
        <%--</select>--%>
        <%--<label class="lab_80">学号：</label>--%>
        <%--<input type="text" id="studentCode" name="studentCode" class="input_240" style="width: 200px;" value="${param.studentCode}" />--%>
        <%--<label class="lab_80">姓名：</label>--%>
        <%--<input type="text" id="name" name="name" class="input_240" style="width: 200px;" value="${param.name}" />--%>
        <%--<label class="lab_80">状态：</label>--%>
        <%--<select id="state" name="state">--%>
          <%--<option value="">请选择</option>--%>
          <%--<option value="1" <c:if test="${param.state eq '1'}">selected="selected" </c:if> >未结算</option>--%>
          <%--<option value="0" <c:if test="${param.state eq '0'}">selected="selected" </c:if>>已结算</option>--%>
        <%--</select>--%>
        <%--<label class="lab_80">入学年：</label>--%>
        <%--<input type="text" id="enterYear" name="enterYear" class="input_240" style="width: 200px;" value="${param.enterYear}" />--%>
        <%--<label class="lab_80">入学季：</label>--%>
        <%--<select id="enterQuarter" name="enterQuarter">--%>
          <%--<option value="">请选择</option>--%>
          <%--<option value="0" <c:if test="${param.enterQuarter == 0}">selected="selected" </c:if> >春季</option>--%>
          <%--<option value="1" <c:if test="${param.enterQuarter == 1}">selected="selected" </c:if> >秋季</option>--%>
        <%--</select>--%>
        <%--&nbsp;&nbsp;&nbsp;&nbsp;--%>
        <%--<a class="com_btn_b" href="#" id="subBut" onclick="pageForm.submit();"><span>查&nbsp;询</span></a>&nbsp;&nbsp;--%>
        <%--<a class="com_btn_b" href="#" id="resetBut" onclick="resetForm();"><span>重&nbsp;置</span></a>--%>
      <%--</li>--%>
    <%--</ul>--%>
    <%--<a class="com_btn_b" href="#" onclick="downExcel()"><span>下&nbsp;载</span></a>--%>
    <%--<c:if test="${'search' eq param.method}">--%>
      <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
        <%--<tr>--%>
          <%--<th style="width: 3%">序号</th>--%>
          <%--<th style="width: 5%">中心编号</th>--%>
          <%--<th style="width: 26%">学习中心名称</th>--%>
          <%--<th style="width: 10%">学号</th>--%>
          <%--<th style="width: 7%">姓名</th>--%>
          <%--<th style="width: 18%">专业</th>--%>
          <%--<th style="width: 6%">层次</th>--%>
          <%--<th style="width: 6%">已缴费金额</th>--%>
          <%--<th style="width: 6%">消费金额</th>--%>
          <%--<th style="width: 6%">余额</th>--%>
          <%--<th style="width: 6%">操作</th>--%>
        <%--</tr>--%>
        <%--<c:if test="${empty pageInfo || empty pageInfo.pageResults}">--%>
          <%--<tr>--%>
            <%--<td colspan="20" align="center" style="color: red;">没有找到相关数据</td>--%>
          <%--</tr>--%>
        <%--</c:if>--%>
        <%--<c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">--%>
          <%--<tr>--%>
            <%--<td align="center">${status.index+1}</td>--%>
            <%--<td>${json.spotCode}</td>--%>
            <%--<td>${json.spotName}</td>--%>
            <%--<td>${json.code}</td>--%>
            <%--<td>${json.name}</td>--%>
            <%--<td>${json.specName}</td>--%>
            <%--<td>${json.levelName}</td>--%>
            <%--<td>${json.pay}</td>--%>
            <%--<td>${json.buy}</td>--%>
            <%--<td>${json.acc}</td>--%>
            <%--<td>--%>
              <%--<a href="#" onclick="detail('${json.code}')" style="color: #0092DC">明细</a>--%>
            <%--</td>--%>
          <%--</tr>--%>
        <%--</c:forEach>--%>
        <%--<tr>--%>
          <%--<td colspan="7" align="right">合计：</td>--%>
          <%--<td>${totalPayPrice}</td>--%>
          <%--<td>${totalBuyPrice}</td>--%>
          <%--<td>${totalAccPrice}</td>--%>
          <%--<td></td>--%>
        <%--</tr>--%>
        <%--<%@ include file="/common/page.jsp"%>--%>
      <%--</table>--%>
    <%--</c:if>--%>
  <%--</form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function addMoney(code){--%>
    <%--var url = '${pageContext.request.contextPath}/addStuEP/openAddStuEPPage.htm';--%>
    <%--if(code != "" && code.length > 0 ){--%>
      <%--url += "?code="+code;--%>
    <%--}--%>
    <%--var dialog = new Dialog();--%>
    <%--dialog.Width = 600;--%>
    <%--dialog.Height = 330;--%>
    <%--dialog.Title = "缴费";--%>
    <%--dialog.URL = url;--%>
    <%--dialog.show();--%>
  <%--}--%>

  <%--function downExcel(){--%>
    <%--var semesterId = $("#semesterId").val();--%>
    <%--var studentCode = $("#studentCode").val();--%>
    <%--var name = $("#name").val();--%>
    <%--var state = $("#state").val();--%>
    <%--var enterYear = $("#enterYear").val();--%>
    <%--var enterQuarter = $("#enterQuarter").val();--%>
    <%--$.ajax({--%>
      <%--cache: true,--%>
      <%--type: "POST",--%>
      <%--url:"${pageContext.request.contextPath}/downStuE/down.htm?semesterId="+semesterId+"&studentCode="+studentCode+"&name="+name+"&state="+state+"&enterYear="+enterYear+"&enterQuarter="+enterQuarter,--%>
      <%--data:{},--%>
      <%--async: false,--%>
      <%--success: function(data) {--%>
        <%--open("${pageContext.request.contextPath}"+data);--%>
      <%--}--%>
    <%--});--%>
  <%--}--%>

  <%--function detail(code){--%>
    <%--parent.openDialog('明细', 0.9, 0.8, '${pageContext.request.contextPath}/findStudentExpenseDetail/doFindStudentExpenseDetail.htm?code='+code);--%>
  <%--}--%>

  <%--function selectProv(){--%>
    <%--var provCode = $("#provCode").val();--%>
    <%--$.ajax({--%>
      <%--cache: true,--%>
      <%--type: "POST",--%>
      <%--url:"${pageContext.request.contextPath}/findSpotByProvCode/doFindSpotByProvCode.htm",--%>
      <%--data:{"provCode":provCode},--%>
      <%--async: false,--%>
      <%--success: function(result) {--%>
        <%--$("#spotCode option").remove();--%>
        <%--$("#spotCode").append("<option value=''>请选择</option>");--%>
        <%--if(typeof(result.spotArray) != "undefined"){--%>
          <%--for(var i=0; i<result.spotArray.length; i++){--%>
            <%--var spot = result.spotArray[i];--%>
            <%--$("#spotCode").append("<option value='"+spot.code+"'>["+spot.code+"]"+spot.name+"</option>");--%>
          <%--}--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>
  <%--}--%>

  <%--function resetForm(){--%>
    <%--$("#provCode, #spotCode, #name, #studentCode, #state").val('');--%>
  <%--}--%>
<%--</script>--%>





<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findStuEPage/findStuEPageByWhere.htm" method="post">
  <input type="hidden" name="method" value="search"/>
  <input type="hidden" id="rows" name="rows" />
  <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
  <table width="90%">
    <tr height="40">
      <td align="right"><label >学习中心：</label></td>
      <td>[${spot.code}]${spot.name}</td>
      <td align="right"><label >学期：</label></td>
      <td>
        <select id="semesterId" name="semesterId" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <c:forEach var="semester" items="${semesterList}">
            <option value="${semester.id}" <c:if test="${param.semesterId == semester.id}">selected="selected" </c:if> >${semester.semester}</option>
          </c:forEach>
        </select>
      </td>
      <td align="right"><label >状态：</label></td>
      <td>
        <select id="state" name="state" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <option value="1" <c:if test="${param.state eq '1'}">selected="selected" </c:if> >未结算</option>
          <option value="0" <c:if test="${param.state eq '0'}">selected="selected" </c:if>>已结算</option>
        </select>
      </td>
    </tr>
    <tr height="40">
      <td align="right"><label >入学年：</label></td>
      <td><input type="text" id="enterYear" name="enterYear" value="${param.enterYear}" /></td>
      <td align="right"><label >入学季：</label></td>
      <td>
        <select id="enterQuarter" name="enterQuarter" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <option value="0" <c:if test="${param.enterQuarter eq '0'}">selected="selected" </c:if> >春季</option>
          <option value="1" <c:if test="${param.enterQuarter eq '1'}">selected="selected" </c:if> >秋季</option>
        </select>
      </td>
    </tr>
    <tr height="40">
      <td align="right"><label >学号：</label></td>
      <td><input type="text" id="studentCode" name="studentCode" value="${param.studentCode}" /></td>
      <td align="right"><label >姓名：</label></td>
      <td><input type="text" id="name" name="name" value="${param.name}" /></td>
    </tr>
    <tr>
      <td colspan="99" style="padding-left:20px;">
        <button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example"
                data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '查询超时'}"
                onclick="app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'), this)"><span class="am-icon-search"></span> 查询</button>
      </td>
    </tr>
  </table>
</form>
<p /><p />
<c:if test="${'search' eq param.method}">
  <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
    <tr>
      <td colspan="999" style="background-color:#FFF">
        <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="downExcel()"><span class="am-icon-download"></span> 下载</button>
      </td>
    </tr>
    <tr class="am-primary">
      <th style="width: 3%">序号</th>
      <th style="width: 4%">中心编号</th>
      <th style="width: 22%">学习中心名称</th>
      <th style="width: 10%">学号</th>
      <th style="width: 7%">姓名</th>
      <th style="width: 18%">专业</th>
      <th style="width: 6%">层次</th>
      <th style="width: 6%">已缴费金额</th>
      <th style="width: 6%">消费金额</th>
      <th style="width: 6%">余额</th>
      <th>操作</th>
    </tr>
    <c:if test="${empty pageInfo || empty pageInfo.pageResults}">
      <tr>
        <td colspan="20" align="center" style="color: red;">没有找到相关数据</td>
      </tr>
    </c:if>
    <c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">
      <tr>
        <td align="center">${status.index+1}</td>
        <td>${json.spotCode}</td>
        <td>${json.spotName}</td>
        <td>${json.code}</td>
        <td>${json.name}</td>
        <td>${json.specName}</td>
        <td>${json.levelName}</td>
        <td>${json.pay}</td>
        <td>${json.buy}</td>
        <td>${json.acc}</td>
        <td>
          <div class="am-btn-group">
            <button type="button" class="am-btn am-btn-primary am-round" onClick="detail('${json.code}')"><span class="am-icon-th-list"></span> 明细</button>
          </div>
        </td>
      </tr>
    </c:forEach>
    <tr>
      <td colspan="7" align="right">合计：</td>
      <td>${totalPayPrice}</td>
      <td>${totalBuyPrice}</td>
      <td>${totalAccPrice}</td>
      <td></td>
    </tr>
  </table>
  <%@ include file="/common/page2.jsp"%>
</c:if>
<script>
  $("select").selected();
  function detail(code){
    app.openOneBtnDialog('${pageContext.request.contextPath}/findStudentExpenseDetail/doFindStudentExpenseDetail.htm?code='+code, '明细', 1000, 0.9);
  }

  function downExcel(){
    var semesterId = $("#semesterId").val();
    var studentCode = $("#studentCode").val();
    var name = $("#name").val();
    var state = $("#state").val();
    var enterYear = $("#enterYear").val();
    var enterQuarter = $("#enterQuarter").val();

    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/downStuE/down.htm?semesterId="+semesterId+"&studentCode="+studentCode+"&name="+name+"&state="+state+"&enterYear="+enterYear+"&enterQuarter="+enterQuarter,
      data:{},
      async: false,
      success: function(data) {
        open("${pageContext.request.contextPath}"+data);
      }
    });
  }
</script>

