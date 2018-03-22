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
  <%--<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findSpotEPage/findSpotEPageByWhere.htm" method="post">--%>
    <%--<input type="hidden" id="page" name="page" value="${pageInfo.currentPage}"/>--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<label class="lab_80">学期：</label>--%>
        <%--<select id="semesterId" name="semesterId">--%>
          <%--<option value="">--请选择--</option>--%>
          <%--<c:forEach var="semester" items="${semesterList}">--%>
            <%--<option value="${semester.id}" <c:if test="${param.semesterId == semester.id}">selected="selected" </c:if> >${semester.semester}</option>--%>
          <%--</c:forEach>--%>
        <%--</select>--%>
        <%--<label class="lab_80">省中心：</label>--%>
        <%--<select id="provCode" name="provCode" onchange="selectProv()">--%>
          <%--<option value="">请选择</option>--%>
          <%--<c:forEach var="prov" items="${provinceList}">--%>
            <%--<option value="${prov.code}" <c:if test="${param.provCode eq prov.code}">selected="selected" </c:if> >${prov.name}</option>--%>
          <%--</c:forEach>--%>
        <%--</select>--%>
        <%--<label class="lab_80">学习中心：</label>--%>
        <%--<select id="spotCode" name="spotCode">--%>
          <%--<option value="">请选择</option>--%>
          <%--<c:forEach var="spot" items="${spotList}">--%>
            <%--<option value="${spot.code}" <c:if test="${param.spotCode eq spot.code}">selected="selected" </c:if> >[${spot.code}]${spot.name}</option>--%>
          <%--</c:forEach>--%>
        <%--</select>--%>
        <%--&nbsp;&nbsp;&nbsp;&nbsp;--%>
        <%--<a class="com_btn_b" href="#" id="subBut" onclick="pageForm.submit();"><span>查&nbsp;询</span></a>&nbsp;&nbsp;--%>
        <%--<a class="com_btn_b" href="#" id="resetBut" onclick="resetForm();"><span>重&nbsp;置</span></a>--%>
      <%--</li>--%>
    <%--</ul>--%>
    <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
      <%--<tr>--%>
        <%--<th style="width: 30px;">序号</th>--%>
        <%--<th style="width: 100px;">学习中心编号</th>--%>
        <%--<th>学习中心名称</th>--%>
        <%--<th style="width: 100px;">学期</th>--%>
        <%--<th style="width: 100px;">已缴费金额</th>--%>
        <%--<th style="width: 100px;">消费金额</th>--%>
        <%--<th style="width: 100px;">折扣</th>--%>
        <%--<th style="width: 100px;">余额</th>--%>
        <%--<th style="width: 200px;">操作</th>--%>
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
          <%--<td>${json.spotName}</td>--%>
          <%--<td>${json.semester}</td>--%>
          <%--<td>${json.pay}</td>--%>
          <%--<td>${json.buy}</td>--%>
          <%--<td>${json.discount}</td>--%>
          <%--<td>${json.acc}</td>--%>
          <%--<td>--%>
            <%--<a href="#" onclick="addMoney('${json.spotCode}')" style="color: #0092DC">缴费</a>&nbsp;&nbsp;--%>
            <%--<a href="#" onclick="detail('${json.spotCode}')" style="color: #0092DC">明细</a>&nbsp;&nbsp;--%>
            <%--<a href="#" onclick="discount('${json.id}')" style="color: #0092DC">折扣</a>--%>
          <%--</td>--%>
        <%--</tr>--%>
      <%--</c:forEach>--%>
      <%--<%@ include file="/common/page.jsp"%>--%>
    <%--</table>--%>
  <%--</form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function resetForm(){--%>
    <%--$("#provCode, #spotCode").val('');--%>
  <%--}--%>

  <%--function addMoney(code){--%>
    <%--var url = '${pageContext.request.contextPath}/addSpotEP/openAddSpotEPPage.htm';--%>
    <%--if(code != "" && code.length > 0 ){--%>
      <%--url += "?spotCode="+code;--%>
    <%--}--%>
    <%--var dialog = new Dialog();--%>
    <%--dialog.Width = 650;--%>
    <%--dialog.Height = 330;--%>
    <%--dialog.Title = "缴费";--%>
    <%--dialog.URL = url;--%>
    <%--dialog.show();--%>
  <%--}--%>

  <%--function detail(code){--%>
    <%--parent.openDialog('明细', 0.9, 0.8, '${pageContext.request.contextPath}/findSpotExpenseDetail/doFindSpotExpenseDetail.htm?code='+code);--%>
  <%--}--%>

  <%--function discount(id){--%>
    <%--var dialog = new Dialog();--%>
    <%--dialog.ShowButtonRow=true;--%>
    <%--dialog.OKEvent = function(){--%>
      <%--dialog.innerFrame.contentWindow.sub($("#subBut"));--%>
    <%--};--%>
    <%--dialog.Width = getWindowWidthSize() * 0.2;--%>
    <%--dialog.Height = getWindowHeightSize() * 0.15;--%>
    <%--dialog.MessageTitle = "折扣注意事项";--%>
    <%--dialog.Message = "<span style='color: #ff0000'>输入90，即9折；输入85，即85折；只能输入100以内的整数，不输入即代表不打折。</span>";--%>
    <%--dialog.Title = "折扣";--%>
    <%--dialog.URL = "${pageContext.request.contextPath}/editSpotExpenseDiscountByID/open.htm?id="+id;--%>
    <%--dialog.show();--%>
    <%--dialog.okButton.value=" 提 交 ";--%>
    <%--dialog.cancelButton.value=" 取 消 ";--%>
  <%--}--%>
<%--</script>--%>


<p />
<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findSpotEPage/findSpotEPageByWhere.htm" method="post">
  <input type="hidden" id="rows" name="rows" />
  <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
  <label >学期：</label>
  <select id="semesterId" name="semesterId" onchange="app.changeSelect(this)">
    <option value=""></option>
    <option value="null">全部</option>
    <c:forEach var="semester" items="${semesterList}">
      <option value="${semester.id}" <c:if test="${param.semesterId == semester.id}">selected="selected" </c:if> >${semester.semester}</option>
    </c:forEach>
  </select>&nbsp;&nbsp;&nbsp;&nbsp;

  <label >省中心：</label>
  <select id="provCode" name="provCode" onchange="app.selectProv(this, 'spotCode', '${pageContext.request.contextPath}')">
    <option value=""></option>
    <option value="null">全部</option>
    <c:forEach var="prov" items="${provinceList}">
      <option value="${prov.code}" <c:if test="${param.provCode eq prov.code}">selected="selected" </c:if> >${prov.name}</option>
    </c:forEach>
  </select>&nbsp;&nbsp;&nbsp;&nbsp;

  <label >学习中心：</label>
  <select id="spotCode" name="spotCode" data-am-selected="{maxHeight: 500, searchBox: 1}" onchange="app.changeSelect(this)">
    <option value=""></option>
    <option value="null">全部</option>
    <c:forEach var="spot" items="${spotList}">
      <option value="${spot.code}" <c:if test="${param.spotCode eq spot.code}">selected="selected" </c:if> >[${spot.code}]${spot.name}</option>
    </c:forEach>
  </select>&nbsp;&nbsp;&nbsp;&nbsp;
  <button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example"
          data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '查询超时'}"
          onclick="app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'), this)"><span class="am-icon-search"></span> 查询</button>
</form>
<p /><p />

<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <tr class="am-primary">
    <th style="width:3%">序号</th>
    <th style="width:8%">学习中心编号</th>
    <th style="width:30%">学习中心名称</th>
    <th style="width:8%">学期</th>
    <th style="width:8%">已缴费金额</th>
    <th style="width:8%">消费金额</th>
    <th style="width:8%">折扣</th>
    <th style="width:8%">余额</th>
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
      <td>${json.spotName}</td>
      <td>${json.semester}</td>
      <td>${json.pay}</td>
      <td>${json.buy}</td>
      <td>${json.discount}</td>
      <td>${json.acc}</td>
      <td>
        <div class="am-btn-group">
          <button type="button" class="am-btn am-btn-primary am-round" onClick="addMoney('${json.spotCode}')"><span class="am-icon-plus"></span> 缴费</button>
          <button type="button" class="am-btn am-btn-primary am-round" onClick="detail('${json.spotCode}')"><span class="am-icon-th-list"></span> 明细</button>
          <button type="button" class="am-btn am-btn-primary am-round" onClick="discount('${json.id}')"><span class="am-icon-cog"></span> 折扣</button>
        </div>
      </td>
    </tr>
  </c:forEach>
</table>
<%@ include file="/common/page2.jsp"%>
<script>
  $("select").selected();
  function addMoney(code){
    var url = '${pageContext.request.contextPath}/addSpotEP/openAddSpotEPPage.htm';
    if(code != "" && code.length > 0 ) {
      url += "?spotCode=" + code;
    }
    app.openDialog(url, '交费', 700, 0.45, function(index){
      var money = $("#money").val().trim();
      if(!vaild.vaildMoney(money)){
        app.msg("请输入正确的金额", 1);
        return;
      }
      app.addForFile("${pageContext.request.contextPath}/addSpotEP/spotEPAdd.htm", "addForm", index);
    });
  }

  function detail(code){
    app.openOneBtnDialog('${pageContext.request.contextPath}/findSpotExpenseDetail/doFindSpotExpenseDetail.htm?code='+code, '明细', 1000, 0.9);
  }

  function discount(id){
    var url = '${pageContext.request.contextPath}/editSpotExpenseDiscountByID/open.htm?id='+id;
    app.openDialog(url, '折扣', 600, 0.4, function(index){
      var discount = $("#discount").val().trim();
      if(isNaN(discount)){
        app.msg("请输入正确的折扣", 1);
        return false;
      }
      if(discount > 100){
        app.msg("折扣不能大于100", 1);
        return false;
      }
      app.edit("${pageContext.request.contextPath}/editSpotExpenseDiscountByID/editDiscount.htm", $('#editForm').serialize(), index);
    });
  }
</script>
