<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="main-content">--%>
  <%--<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findInvoiceByWhere/find.htm" method="post">--%>
    <%--<input type="hidden" id="page" name="page" value="${pageInfo.currentPage}"/>--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<table width="80%">--%>
          <%--<tr style="height: 30px;">--%>
            <%--<Td>--%>
              <%--<label class="lab_80">学习中心：</label>--%>
              <%--<select id="spotCode" name="spotCode">--%>
                <%--<option value="">请选择</option>--%>
                <%--<c:forEach var="spot" items="${spotList}">--%>
                  <%--<option value="${spot.code}" <c:if test="${param.spotCode eq spot.code}">selected="selected" </c:if> >[${spot.code}]${spot.name}</option>--%>
                <%--</c:forEach>--%>
              <%--</select>--%>
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
              <%--<label class="lab_80">状态：</label>--%>
              <%--<select id="state" name="state">--%>
                <%--<option value="">请选择</option>--%>
                <%--<option value="0" <c:if test="${param.state eq '0'}">selected="selected" </c:if> >待开票</option>--%>
                <%--<option value="1" <c:if test="${param.state eq '1'}">selected="selected" </c:if> >已开票</option>--%>
              <%--</select>--%>
            <%--</td>--%>
            <%--<td>--%>
              <%--<label class="lab_80">类型：</label>--%>
              <%--<select id="isTotal" name="isTotal">--%>
                <%--<option value="">请选择</option>--%>
                <%--<option value="0" <c:if test="${param.isTotal eq '0'}">selected="selected" </c:if> >明细票</option>--%>
                <%--<option value="1" <c:if test="${param.isTotal eq '1'}">selected="selected" </c:if> >总票</option>--%>
              <%--</select>--%>
            <%--</td>--%>
          <%--</Tr>--%>
          <%--<tr>--%>
            <%--<td>--%>
              <%--<label class="lab_80">开票时间：</label>--%>
              <%--<input type="text" id="openDateBegin" name="openDateBegin" class="Wdate" style="width: 110px;" onfocus="WdatePicker({readOnly:true})" value="${param.openDateBegin}"/>---%>
              <%--<input type="text" id="openDateEnd" name="openDateEnd" class="Wdate" style="width: 110px;" onfocus="WdatePicker({readOnly:true})" value="${param.openDateEnd}"/>--%>
              <%--<a class="com_btn_b" href="#" id="subBut" onclick="pageForm.submit();"><span>查&nbsp;询</span></a>--%>
            <%--</td>--%>
          <%--</tr>--%>
        <%--</table>--%>
      <%--</li>--%>
    <%--</ul>--%>

    <%--<a class="com_btn_b" href="#" onclick="add()"><span>开学生明细票</span></a>&nbsp;&nbsp;--%>
    <%--<a class="com_btn_b" href="#" onclick="addTotal()"><span>开总票</span></a>&nbsp;&nbsp;--%>
    <%--<a class="com_btn_b" href="#" onclick="downXls()"><span>下载</span></a>&nbsp;&nbsp;--%>
    <%--<a class="com_btn_b" href="#" onclick="uploadXls()"><span>上传</span></a>--%>
    <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
      <%--<tr>--%>
        <%--<th style="width: 4%;">序号</th>--%>
        <%--<th style="width: 5%;">中心编号</th>--%>
        <%--<th style="width: 8%;">学号</th>--%>
        <%--<th style="width: 8%;">姓名</th>--%>
        <%--<th style="width: 20%;">专业</th>--%>
        <%--<th style="width: 4%;">层次</th>--%>
        <%--<th style="width: 10%;">发票编号</th>--%>
        <%--<th style="width: 5%;">开票金额</th>--%>
        <%--<th style="width: 5%;">状态</th>--%>
        <%--<th style="width: 6%;">开票时间</th>--%>
        <%--<th style="width: 5%;">操作人</th>--%>
        <%--<th style="width: 10%;">操作时间</th>--%>
        <%--<th style="width: 10%;">操作</th>--%>
      <%--</tr>--%>
      <%--<c:if test="${empty pageInfo || empty pageInfo.pageResults}">--%>
        <%--<tr>--%>
          <%--<td colspan="99" align="center" style="color: red;">没有找到相关数据</td>--%>
        <%--</tr>--%>
      <%--</c:if>--%>
      <%--<c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">--%>
        <%--<tr>--%>
          <%--<td align="center">${status.index+1}</td>--%>
          <%--<td>${json.spotCode}</td>--%>
          <%--<td>${json.studentCode}</td>--%>
          <%--<td>${json.name}</td>--%>
          <%--<td>${json.specName}</td>--%>
          <%--<td>${json.levelName}</td>--%>
          <%--<td>${json.iCode}</td>--%>
          <%--<td>${json.money}</td>--%>
          <%--<td>--%>
            <%--<c:choose>--%>
              <%--<c:when test="${json.state == 0}">待开票</c:when>--%>
              <%--<c:when test="${json.state == 1}">已开票</c:when>--%>
              <%--<c:otherwise>未知</c:otherwise>--%>
            <%--</c:choose>--%>
          <%--</td>--%>
          <%--<td>${fn:substring(json.openTime, 0, 10)}</td>--%>
          <%--<td>${json.operator}</td>--%>
          <%--<td>${json.operateTime}</td>--%>
          <%--<td>--%>
            <%--<c:if test="${json.state == 0}">--%>
              <%--<a href="#" onclick="editMoney('${json.id}')" style="color: #0092DC">修改金额</a>&nbsp;&nbsp;--%>
              <%--<a href="#" onclick="editCode('${json.id}')" style="color: #0092DC">录入票号</a>--%>
            <%--</c:if>--%>
            <%--<c:if test="${json.state == 1}">--%>
              <%--<a href="#" onclick="editCode('${json.id}')" style="color: #0092DC">修改票号</a>--%>
            <%--</c:if>--%>
          <%--</td>--%>
        <%--</tr>--%>
      <%--</c:forEach>--%>
      <%--<tr>--%>
        <%--<td colspan="7" style="text-align: right">合计：</td>--%>
        <%--<td colspan="6">${totalMoney}</td>--%>
      <%--</tr>--%>
      <%--<%@ include file="/common/page.jsp"%>--%>
    <%--</table>--%>
  <%--</form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function add(){--%>
    <%--var dialog = new Dialog();--%>
    <%--dialog.ShowButtonRow=true;--%>
    <%--dialog.OKEvent = function(){--%>
      <%--alert(dialog.innerFrame.contentWindow);--%>
      <%--dialog.innerFrame.contentWindow.sub($("#subBut"));--%>
    <%--};--%>
    <%--dialog.Width = getWindowWidthSize() * 0.8;--%>
    <%--dialog.Height = getWindowHeightSize() * 0.8;--%>
    <%--dialog.Title = "开学生明细票";--%>
    <%--dialog.URL = "${pageContext.request.contextPath}/addInvoice/open.htm";--%>
    <%--dialog.show();--%>
    <%--dialog.okButton.value=" 提 交 ";--%>
    <%--dialog.cancelButton.value=" 取 消 ";--%>
  <%--}--%>

  <%--function addTotal(){--%>
    <%--var spotCode = $("#spotCode").val();--%>
    <%--if(spotCode == ""){--%>
      <%--alert("请选择中心");--%>
      <%--return false;--%>
    <%--}--%>
    <%--var dialog = new Dialog();--%>
    <%--dialog.ShowButtonRow=true;--%>
    <%--dialog.OKEvent = function(){--%>
      <%--dialog.innerFrame.contentWindow.sub($("#subBut"));--%>
    <%--};--%>
    <%--dialog.Width = getWindowWidthSize() * 0.2;--%>
    <%--dialog.Height = getWindowHeightSize() * 0.2;--%>
    <%--dialog.Title = "开总票";--%>
    <%--dialog.URL = "${pageContext.request.contextPath}/addInvoice/openTotal.htm?spotCode="+spotCode;--%>
    <%--dialog.show();--%>
    <%--dialog.okButton.value=" 提 交 ";--%>
    <%--dialog.cancelButton.value=" 取 消 ";--%>
  <%--}--%>

  <%--function downXls(){--%>
    <%--var studentCode = $("#studentCode").val();--%>
    <%--var spotCode = $("#spotCode").val();--%>
    <%--var state = $("#state").val();--%>
    <%--var specCode = $("#specCode").val();--%>
    <%--var levelCode = $("#levelCode").val();--%>
    <%--var state = $("#state").val();--%>
    <%--var year = $("#year").val();--%>
    <%--var quarter = $("#quarter").val();--%>

    <%--$.ajax({--%>
      <%--cache: true,--%>
      <%--type: "POST",--%>
      <%--url:"${pageContext.request.contextPath}/downInvocie/down.htm?spotCode="+spotCode+"&specCode="+specCode+"&levelCode="+levelCode+"&studentCode="+studentCode+"&state="+state+"&year="+year+"&quarter="+quarter,--%>
      <%--data:{},--%>
      <%--async: false,--%>
      <%--success: function(data) {--%>
        <%--open("${pageContext.request.contextPath}"+data);--%>
      <%--}--%>
    <%--});--%>
  <%--}--%>

  <%--function editMoney(id){--%>
    <%--var dialog = new Dialog();--%>
    <%--dialog.ShowButtonRow=true;--%>
    <%--dialog.OKEvent = function(){--%>
      <%--dialog.innerFrame.contentWindow.sub($("#subBut"));--%>
    <%--};--%>
    <%--dialog.Width = getWindowWidthSize() * 0.2;--%>
    <%--dialog.Height = getWindowHeightSize() * 0.2;--%>
    <%--dialog.Title = "修改金额";--%>
    <%--dialog.URL = "${pageContext.request.contextPath}/editInvoiceForMoney/open.htm?id="+id;--%>
    <%--dialog.show();--%>
    <%--dialog.okButton.value=" 提 交 ";--%>
    <%--dialog.cancelButton.value=" 取 消 ";--%>
  <%--}--%>

  <%--function editCode(id){--%>
    <%--var dialog = new Dialog();--%>
    <%--dialog.ShowButtonRow=true;--%>
    <%--dialog.OKEvent = function(){--%>
      <%--dialog.innerFrame.contentWindow.sub($("#subBut"));--%>
    <%--};--%>
    <%--dialog.Width = getWindowWidthSize() * 0.2;--%>
    <%--dialog.Height = getWindowHeightSize() * 0.2;--%>
    <%--dialog.Title = "修改发票号";--%>
    <%--dialog.URL = "${pageContext.request.contextPath}/editInvoiceForCode/open.htm?id="+id;--%>
    <%--dialog.show();--%>
    <%--dialog.okButton.value=" 提 交 ";--%>
    <%--dialog.cancelButton.value=" 取 消 ";--%>
  <%--}--%>

  <%--function uploadXls(){--%>
    <%--var dialog = new Dialog();--%>
    <%--dialog.ShowButtonRow=true;--%>
    <%--dialog.OKEvent = function(){--%>
      <%--dialog.innerFrame.contentWindow.sub($("#subBut"));--%>
    <%--};--%>
    <%--dialog.Width = getWindowWidthSize() * 0.25;--%>
    <%--dialog.Height = getWindowHeightSize() * 0.15;--%>
    <%--dialog.Title = "修改发票号";--%>
    <%--dialog.URL = "${pageContext.request.contextPath}/uploadInvoice/open.htm";--%>
    <%--dialog.show();--%>
    <%--dialog.okButton.value=" 提 交 ";--%>
    <%--dialog.cancelButton.value=" 取 消 ";--%>
  <%--}--%>
<%--</script>--%>


<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findInvoiceByWhere/find.htm" method="post">
  <input type="hidden" name="method" value="search"/>
  <input type="hidden" id="rows" name="rows" />
  <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
  <table width="90%">
    <tr height="40">
      <td align="right"><label >学习中心：</label></td>
      <td>
        <select id="spotCode" name="spotCode" data-am-selected="{maxHeight: 500, searchBox: 1}" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <c:forEach var="spot" items="${spotList}">
            <option value="${spot.code}" <c:if test="${param.spotCode eq spot.code}">selected="selected" </c:if> >[${spot.code}]${spot.name}</option>
          </c:forEach>
        </select>
      </td>
      <td align="right"><label >入学年：</label></td>
      <td><input type="text" id="year" name="year" value="${param.year}" /></td>
      <td align="right"><label >入学季：</label></td>
      <td>
        <select id="quarter" name="quarter" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <option value="0" <c:if test="${param.quarter eq '0'}">selected="selected" </c:if> >春季</option>
          <option value="1" <c:if test="${param.quarter eq '1'}">selected="selected" </c:if> >秋季</option>
        </select>
      </td>
    </tr>
    <tr height="40">
      <td align="right"><label >专业：</label></td>
      <td>
        <select id="specCode" name="specCode" data-am-selected="{maxHeight: 500, searchBox: 1}" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <c:forEach var="spec" items="${specList}">
            <option value="${spec.code}" <c:if test="${param.specCode eq spec.code}">selected="selected" </c:if>>[${spec.code}]${spec.name}</option>
          </c:forEach>
        </select>
      </td>
      <td align="right"><label >层次：</label></td>
      <td>
        <select id="levelCode" name="levelCode" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <c:forEach var="level" items="${levelList}">
            <option value="${level.code}" <c:if test="${param.levelCode eq level.code}">selected="selected" </c:if> >${level.name}</option>
          </c:forEach>
        </select>
      </td>
      <td align="right"><label >学号：</label></td>
      <td><input type="text" id="studentCode" name="studentCode" value="${param.studentCode}" /></td>
    </tr>
    <tr height="40">
      <td align="right"><label >状态：</label></td>
      <td>
        <select id="state" name="state" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <option value="0" <c:if test="${param.state eq '0'}">selected="selected" </c:if> >待开票</option>
          <option value="1" <c:if test="${param.state eq '1'}">selected="selected" </c:if> >已开票</option>
        </select>
      </td>
      <td align="right"><label >类型：</label></td>
      <td>
        <select id="isTotal" name="isTotal" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <option value="0" <c:if test="${param.isTotal eq '0'}">selected="selected" </c:if> >明细票</option>
          <option value="1" <c:if test="${param.isTotal eq '1'}">selected="selected" </c:if> >总票</option>
        </select>
      </td>
      <td align="right"><label >开票时间：</label></td>
      <td>
        <input type="text" id="openDateBegin" name="openDateBegin" class="Wdate" style="width: 110px;" onfocus="WdatePicker({readOnly:true})" value="${param.openDateBegin}"/>-
        <input type="text" id="openDateEnd" name="openDateEnd" class="Wdate" style="width: 110px;" onfocus="WdatePicker({readOnly:true})" value="${param.openDateEnd}"/>
      </td>
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

<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <tr>
    <td colspan="999" style="background-color:#FFF">
      <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="add()"><span class="am-icon-plus"></span> 开学生明细票</button>
      <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="addTotal()"><span class="am-icon-plus"></span> 开总票</button>
      <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="downXls()"><span class="am-icon-download"></span> 下载</button>
      <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="uploadXls()"><span class="am-icon-upload"></span> 上传</button>
    </td>
  </tr>
  <tr class="am-primary">
    <th style="width: 4%;">序号</th>
    <th style="width: 6%;">中心编号</th>
    <th style="width: 8%;">学号</th>
    <th style="width: 7%;">姓名</th>
    <th style="width: 20%;">专业</th>
    <th style="width: 5%;">层次</th>
    <th style="width: 9%;">发票编号</th>
    <th style="width: 6%;">开票金额</th>
    <th style="width: 5%;">状态</th>
    <th style="width: 8%;">开票时间</th>
    <th style="width: 5%;">操作人</th>
    <th style="width: 8%;">操作时间</th>
    <th>操作</th>
  </tr>
  <c:if test="${empty pageInfo || empty pageInfo.pageResults}">
    <tr>
      <td colspan="99" align="center" style="color: red;">没有找到相关数据</td>
    </tr>
  </c:if>
  <c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">
    <tr>
      <td align="center">${status.index+1}</td>
      <td>${json.spotCode}</td>
      <td>${json.studentCode}</td>
      <td>${json.name}</td>
      <td>${json.specName}</td>
      <td>${json.levelName}</td>
      <td>${json.iCode}</td>
      <td>${json.money}</td>
      <td>
        <c:choose>
        <c:when test="${json.state == 0}">待开票</c:when>
        <c:when test="${json.state == 1}">已开票</c:when>
        <c:otherwise>未知</c:otherwise>
        </c:choose>
      </td>
      <td>${fn:substring(json.openTime, 0, 10)}</td>
      <td>${json.operator}</td>
      <td>${json.operateTime}</td>
      <td>
        <div class="doc-dropdown-justify-js">
          <div class="am-dropdown " data-am-dropdown="" id="operateBtn_${status.index+1}">
            <button class="am-btn am-btn-default am-btn-sm am-dropdown-toggle" data-am-dropdown-toggle=""><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
            <ul class="am-dropdown-content">
              <c:if test="${json.state == 0}">
                <li><a href="#" onClick="editMoney('${json.id}')"><span style="margin-left:10px; margin-top:5px; margin-bottom:5px;" class="am-icon-pencil-square-o"></span> 修改金额</a></li>
                <li><a href="#" onClick="editCode('${json.id}')"><span style="margin-left:10px; margin-top:5px; margin-bottom:5px;" class="am-icon-pencil-square-o"></span> 录入票号</a></li>
              </c:if>
              <c:if test="${json.state == 1}">
                <li><a href="#" onClick="editCode('${json.id}')"><span style="margin-left:10px; margin-top:5px; margin-bottom:5px;" class="am-icon-pencil-square-o"></span> 修改票号</a></li>
              </c:if>
            </ul>
          </div>
        </div>
      </td>
    </tr>
  </c:forEach>
  <tr>
    <td colspan="7" style="text-align: right">合计：</td>
    <td colspan="6">${totalMoney}</td>
  </tr>
</table>
<%@ include file="/common/page2.jsp"%>
<script>
  $("select").selected();
  $(function() {
    for(var i=1; i<=${fn:length(pageInfo.pageResults)}; i++) {
      $('#operateBtn_'+i).dropdown({justify: '.doc-dropdown-justify-js'});
    }
  });

  function downXls(){
    var studentCode = $("#studentCode").val();
    var spotCode = $("#spotCode").val();
    var state = $("#state").val();
    var specCode = $("#specCode").val();
    var levelCode = $("#levelCode").val();
    var state = $("#state").val();
    var year = $("#year").val();
    var quarter = $("#quarter").val();

    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/downInvocie/down.htm?spotCode="+spotCode+"&specCode="+specCode+"&levelCode="+levelCode+"&studentCode="+studentCode+"&state="+state+"&year="+year+"&quarter="+quarter,
      data:{},
      async: false,
      success: function(data) {
        open("${pageContext.request.contextPath}"+data);
      }
    });
  }

  function editMoney(id){
    var url = "${pageContext.request.contextPath}/editInvoiceForMoney/open.htm?id="+id;
    app.openDialog(url, '修改金额', 500, 0.25, function(index){
      var money = $("#edit_money").val().trim();
      var canMoney = $("#canMoney").val()
      if(!vaild.vaildMoney(money)){
        app.msg("开票金额格式错误！", 1);
        return false;
      }
      if(Number(money) > Number(canMoney)){
        app.msg("开票金额超额！", 1);
        return false;
      }
      app.edit("${pageContext.request.contextPath}/editInvoiceForMoney/editor.htm", $('#editForm').serialize(), index);
    });
  }

  function editCode(id){
    var url = "${pageContext.request.contextPath}/editInvoiceForCode/open.htm?id="+id;
    app.openDialog(url, '修改发票号', 500, 0.25, function(index){
      var code = $.trim($("#code").val());
      var openTime = $("#openTime").val();
      if(code == "" && openTime == ""){
        app.msg("发票号和开票时间不能都为空！", 1);
        return false;
      }
      app.edit("${pageContext.request.contextPath}/editInvoiceForCode/editor.htm", $('#editForm').serialize(), index);
    });
  }

  function uploadXls(){
    var url = "${pageContext.request.contextPath}/uploadInvoice/open.htm";
    app.openDialog(url, '修改发票号', 500, 0.2, function(index){
      app.addForFile("${pageContext.request.contextPath}/uploadInvoice/invoicUpload.htm", "uploadForm", index);
    });
  }

  function addTotal(){
    var spotCode = $("#spotCode").val();
    if(spotCode == ""){
      app.msg("请选择中心", 1);
      return false;
    }
    app.openDialog("${pageContext.request.contextPath}/addInvoice/openTotal.htm?spotCode="+spotCode, '开总票', 550, 0.25, function(index){
      var money = $("#add_total_money").val();
      if(!vaild.vaildMoney(money)){
        app.msg("开票金额格式错误！", 1);
        return false;
      }
      app.edit("${pageContext.request.contextPath}/addInvoice/addTotal.htm", {"money" : money, "spotCode" : spotCode}, index);
    });
  }

  function add(){
    app.openDialog("${pageContext.request.contextPath}/addInvoice/open.htm", '开学生明细票', 1000, 0.7, function(index){

      var spotCode = $("#add_spotCode").val();
      var codeMoneys = "";
      var isCheck = false;

      if(spotCode == ""){
        app.msg("请选择学习中心", 1);
        return;
      }
      var msg="";
      $("[name=cb]").each(function(){
        if($(this).prop("checked")){
          var code = $(this).val()
          var money = $("#"+code).val();
          var maxMoney = $("#"+code+"_maxMoney").val();
          if(!vaild.vaildMoney(money)){
            msg = "学号："+code+"，开票金额格式错误！";
            return;
          }else {
            if(Number(money) > Number(maxMoney)){
              msg = "学号："+code+"，开票金额超额！";
              return;
            }else {
              codeMoneys += code + "_" + money + ",";
            }
          }
          isCheck = true;
        }
      });
      if("" != msg){
        app.msg(msg, 1);
        return;
      }else {
        if (!isCheck) {
          app.msg("请选择要开票的学生", 1);
          return;
        }
      }
      app.add("${pageContext.request.contextPath}/addInvoice/add.htm", {"codeMoneys" : codeMoneys.substr(0, codeMoneys.length-1), "spotCode":spotCode}, index);
    });
  }
</script>
