<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--<!DOCTYPE html>--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="main-content">--%>
  <%--<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findStuEPPageByWhere/find.htm" method="post">--%>
    <%--<input type="hidden" id="page" name="page" value="${pageInfo.currentPage}"/>--%>
    <%--<input type="hidden" id="method" name="method" value="search"/>--%>
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
              <%--<label class="lab_80">入学年：</label>--%>
              <%--<input type="text" id="enterYear" name="enterYear" value="${param.enterYear}" style="width: 100px" />--%>
            <%--</Td>--%>
            <%--<td>--%>
              <%--<label class="lab_80">入学季：</label>--%>
              <%--<select id="enterQuarter" name="enterQuarter">--%>
                <%--<option value="">请选择</option>--%>
                <%--<option value="0" <c:if test="${param.enterQuarter eq '0'}">selected="selected" </c:if> >春季</option>--%>
                <%--<option value="1" <c:if test="${param.enterQuarter eq '1'}">selected="selected" </c:if> >秋季</option>--%>
              <%--</select>--%>
            <%--</td>--%>
          <%--</tr>--%>
          <%--<Tr style="height: 30px;">--%>
            <%--<td>--%>
              <%--<label class="lab_80">专业：</label>--%>
              <%--<select id="specCode" name="specCode">--%>
                <%--<option value="">请选择</option>--%>
                <%--<c:forEach var="spec" items="${specList}">--%>
                  <%--<option value="${spec.code}" <c:if test="${param.specCode eq spec.code}">selected="selected" </c:if>>[${spec.code}]${spec.name}</option>--%>
                <%--</c:forEach>--%>
              <%--</select>--%>
            <%--</td>--%>
            <%--<td>--%>
              <%--<label class="lab_80">层次：</label>--%>
              <%--<select id="levelCode" name="levelCode">--%>
                <%--<option value="">请选择</option>--%>
                <%--<c:forEach var="level" items="${levelList}">--%>
                  <%--<option value="${level.code}" <c:if test="${param.levelCode eq level.code}">selected="selected" </c:if> >${level.name}</option>--%>
                <%--</c:forEach>--%>
              <%--</select>--%>
            <%--</td>--%>
            <%--<td>--%>
              <%--<label class="lab_80">交费类型：</label>--%>
              <%--<select id="payType" name="payType">--%>
                <%--<option value="">请选择</option>--%>
                <%--<option value="0" <c:if test="${param.payType eq '0'}">selected="selected" </c:if> >网银</option>--%>
                <%--<option value="1" <c:if test="${param.payType eq '1'}">selected="selected" </c:if> >银行转账</option>--%>
                <%--<option value="2" <c:if test="${param.payType eq '2'}">selected="selected" </c:if> >现金</option>--%>
                <%--<option value="3" <c:if test="${param.payType eq '3'}">selected="selected" </c:if> >其它</option>--%>
                <%--<option value="4" <c:if test="${param.payType eq '4'}">selected="selected" </c:if> >红冲</option>--%>
                <%--<option value="5" <c:if test="${param.payType eq '5'}">selected="selected" </c:if> >微信</option>--%>
              <%--</select>--%>
            <%--</td>--%>
          <%--</Tr>--%>
          <%--<tr style="height: 30px;">--%>
            <%--<Td>--%>
              <%--<label class="lab_80">学号：</label>--%>
              <%--<input type="text" id="studentCode" name="studentCode" class="input_240" style="width: 150px;" value="${param.studentCode}" />--%>
            <%--</Td>--%>
            <%--<Td>--%>
              <%--<label class="lab_80">姓名：</label>--%>
              <%--<input type="text" id="studentName" name="studentName" class="input_240" style="width: 150px;" value="${param.studentName}" />--%>
            <%--</Td>--%>
            <%--<td>--%>
              <%--<label class="lab_80">创建日期：</label><input type="text" id="beginDate" name="beginDate" class="Wdate" style="width: 110px;" onfocus="WdatePicker({readOnly:true})" value="${param.beginDate}"/>--%>
              <%--- <input type="text" id="endDate" name="endDate" class="Wdate" style="width: 110px;" onfocus="WdatePicker({readOnly:true})" value="${param.endDate}"/>--%>
            <%--</td>--%>
          <%--</tr>--%>
          <%--<tr>--%>
            <%--<Td>--%>
              <%--<a class="com_btn_b" href="#" id="subBut" onclick="pageForm.submit();"><span>查&nbsp;询</span></a>&nbsp;&nbsp;--%>
              <%--<a class="com_btn_b" href="#" id="resetBut" onclick="resetForm();"><span>重&nbsp;置</span></a>&nbsp;&nbsp;--%>
            <%--</Td>--%>
          <%--</tr>--%>
        <%--</table>--%>
      <%--</li>--%>
    <%--</ul>--%>
    <%--<Br><Br>--%>
    <%--<c:if test="${param.method eq 'search'}">--%>
      <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
        <%--<tr>--%>
          <%--<th style="width: 5%;">序号</th>--%>
          <%--<th style="width: 8%;">学号</th>--%>
          <%--<th style="width: 6%;">姓名</th>--%>
          <%--<th style="width: 20%;">专业</th>--%>
          <%--<th style="width: 5%;">层次</th>--%>
          <%--<th style="width: 5%;">交费金额</th>--%>
          <%--<th style="width: 5%;">交费类型</th>--%>
          <%--<th style="width: 10%;">创建时间</th>--%>
          <%--<th style="width: 7%;">到账时间</th>--%>
          <%--<th style="width: 29%;">备注</th>--%>
        <%--</tr>--%>
        <%--<c:if test="${empty pageInfo || empty pageInfo.pageResults}">--%>
          <%--<tr>--%>
            <%--<td colspan="20" align="center" style="color: red;">没有找到相关数据</td>--%>
          <%--</tr>--%>
        <%--</c:if>--%>
        <%--<c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">--%>
          <%--<tr>--%>
            <%--<td>${status.index+1}</td>--%>
            <%--<td>${json.code}</td>--%>
            <%--<td>${json.name}</td>--%>
            <%--<td>${json.spec}</td>--%>
            <%--<td>${json.level}</td>--%>
            <%--<td>${json.money}</td>--%>
            <%--<td>${json.payType}</td>--%>
            <%--<td>${json.createTime}</td>--%>
            <%--<td>${fn:substring(json.arrivalTime, 0, 10)}</td>--%>
            <%--<td>${json.remark}</td>--%>
          <%--</tr>--%>
        <%--</c:forEach>--%>
        <%--<tr>--%>
          <%--<td colspan="5" align="right">合计：</td>--%>
          <%--<td>${totalPayPrice}</td>--%>
          <%--<td></td>--%>
          <%--<td></td>--%>
          <%--<td></td>--%>
          <%--<td></td>--%>
        <%--</tr>--%>
        <%--<%@ include file="/common/page.jsp"%>--%>
      <%--</table>--%>
    <%--</c:if>--%>
  <%--</form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>


<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findStuEPPageByWhere/find.htm" method="post">
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
      <td align="right"><label >交费类型：</label></td>
      <td>
        <select id="payType" name="payType" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <option value="0" <c:if test="${param.payType eq '0'}">selected="selected" </c:if> >网银</option>
          <option value="1" <c:if test="${param.payType eq '1'}">selected="selected" </c:if> >银行转账</option>
          <option value="2" <c:if test="${param.payType eq '2'}">selected="selected" </c:if> >现金</option>
          <option value="3" <c:if test="${param.payType eq '3'}">selected="selected" </c:if> >其它</option>
          <option value="4" <c:if test="${param.payType eq '4'}">selected="selected" </c:if> >红冲</option>
          <option value="5" <c:if test="${param.payType eq '5'}">selected="selected" </c:if> >微信</option>
        </select>
      </td>
    </tr>
    <tr height="40">
      <td align="right"><label >学号：</label></td>
      <td><input type="text" id="studentCode" name="studentCode" value="${param.studentCode}" /></td>
      <td align="right"><label >姓名：</label></td>
      <td><input type="text" id="name" name="name" value="${param.name}" /></td>
      <td align="right"><label >创建日期：</label></td>
      <td>
        <input type="text" id="beginDate" name="beginDate" class="Wdate" style="width: 110px;" onfocus="WdatePicker({readOnly:true})" value="${param.beginDate}"/>
        - <input type="text" id="endDate" name="endDate" class="Wdate" style="width: 110px;" onfocus="WdatePicker({readOnly:true})" value="${param.endDate}"/>
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
<c:if test="${'search' eq param.method}">
  <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
    <tr>
      <td colspan="999" style="background-color:#FFF">
        <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="downXls()"><span class="am-icon-download"></span> 下载</button>
      </td>
    </tr>
    <tr class="am-primary">
        <th style="width: 5%;">序号</th>
        <th style="width: 8%;">学号</th>
        <th style="width: 6%;">姓名</th>
        <th style="width: 20%;">专业</th>
        <th style="width: 5%;">层次</th>
        <th style="width: 6%;">交费金额</th>
        <th style="width: 6%;">交费类型</th>
        <th style="width: 10%;">创建时间</th>
        <th style="width: 7%;">到账时间</th>
        <th style="width: 27%;">备注</th>
    </tr>
    <c:if test="${empty pageInfo || empty pageInfo.pageResults}">
      <tr>
        <td colspan="20" align="center" style="color: red;">没有找到相关数据</td>
      </tr>
    </c:if>
    <c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">
      <tr>
        <td>${status.index+1}</td>
        <td>${json.code}</td>
        <td>${json.name}</td>
        <td>${json.spec}</td>
        <td>${json.level}</td>
        <td>${json.money}</td>
        <td>${json.payType}</td>
        <td>${json.createTime}</td>
        <td>${fn:substring(json.arrivalTime, 0, 10)}</td>
        <td>${json.remark}</td>
      </tr>
    </c:forEach>
    <tr>
      <td colspan="5" align="right">合计：</td>
      <td>${totalPayPrice}</td>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
    </tr>
  </table>
  <%@ include file="/common/page2.jsp"%>
</c:if>
<script>
  $("select").selected();
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
      url:"${pageContext.request.contextPath}/downStuEP/down.htm",
      data:$("#pageForm").serialize(),
      async: false,
      success: function(data) {
        open("${pageContext.request.contextPath}"+data);
      }
    });
  }
</script>