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
  <%--<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findPlaceOrderForNotStock/find.htm" method="post">--%>
    <%--<input type="hidden" id="page" name="page" value="${pageInfo.currentPage}"/>--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<table width="100%">--%>
          <%--<tr style="height: 30px;">--%>
            <%--<Td>--%>
              <%--<label class="lab_80">入学年：</label>--%>
              <%--<input type="text" id="enterYear" name="enterYear" value="${param.enterYear}" />--%>
            <%--</Td>--%>
            <%--<td>--%>
              <%--<label class="lab_80">入学季：</label>--%>
              <%--<select id="quarter" name="quarter">--%>
                <%--<option value="">请选择</option>--%>
                <%--<option value="0" <c:if test="${param.quarter eq '0'}">selected="selected" </c:if> >春季</option>--%>
                <%--<option value="1" <c:if test="${param.quarter eq '1'}">selected="selected" </c:if> >秋季</option>--%>
              <%--</select>--%>
            <%--</td>--%>
            <%--<td>--%>
              <%--<label class="lab_80" style="width: 130px;">教材数量：</label>--%>
              <%--<select id="tmCount" name="tmCount">--%>
                <%--<option value="0" <c:if test="${param.tmCount eq '0'}">selected="selected" </c:if> >大于0</option>--%>
                <%--<option value="1" <c:if test="${param.tmCount eq '1'}">selected="selected" </c:if> >所有</option>--%>
              <%--</select>--%>
            <%--</td>--%>
          <%--</tr>--%>
          <%--<tr style="height: 30px;">--%>
            <%--<Td>--%>
              <%--<label class="lab_80">学期：</label>--%>
              <%--<select id="semesterId" name="semesterId">--%>
                <%--<c:forEach var="semester" items="${semesterList}">--%>
                  <%--<c:choose>--%>
                    <%--<c:when test="${method eq 'search'}">--%>
                      <%--<option value="${semester.id}" <c:if test="${param.semesterId == semester.id}">selected="selected" </c:if> >${semester.semester}</option>--%>
                    <%--</c:when>--%>
                    <%--<c:otherwise>--%>
                      <%--<option value="${semester.id}" <c:if test="${semester.isNowSemester == 0}">selected="selected" </c:if> >${semester.semester}</option>--%>
                    <%--</c:otherwise>--%>
                  <%--</c:choose>--%>
                <%--</c:forEach>--%>
              <%--</select>--%>
            <%--</Td>--%>
            <%--<Td colspan="2">--%>
              <%--<label class="lab_80">学习中心：</label>--%>
              <%--<select id="spotCode" name="spotCode">--%>
                <%--<option value="">请选择</option>--%>
                <%--<c:forEach var="spot" items="${spotList}">--%>
                  <%--<option value="${spot.code}" <c:if test="${param.spotCode eq spot.code}">selected="selected" </c:if> >[${spot.code}]${spot.name}</option>--%>
                <%--</c:forEach>--%>
              <%--</select>--%>
            <%--</Td>--%>
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
              <%--<label class="lab_80">状态：</label>--%>
              <%--<select id="state" name="state">--%>
                <%--<option value="">请选择</option>--%>
                <%--<option value="1" <c:if test="${param.state eq '1'}">selected="selected" </c:if> >已生成</option>--%>
                <%--<option value="2" <c:if test="${param.state eq '2'}">selected="selected" </c:if> >分拣中</option>--%>
                <%--<option value="3" <c:if test="${param.state eq '3'}">selected="selected" </c:if> >已打包</option>--%>
                <%--<option value="4" <c:if test="${param.state eq '4'}">selected="selected" </c:if> >已发出</option>--%>
                <%--<option value="5" <c:if test="${param.state eq '5'}">selected="selected" </c:if> >已签收</option>--%>
              <%--</select>--%>
            <%--</td>--%>
          <%--</Tr>--%>
          <%--<tr style="height: 30px;">--%>
            <%--<Td>--%>
              <%--<label class="lab_80">订单号：</label>--%>
              <%--<input type="text" id="orderCode" name="orderCode" class="input_240" style="width: 150px;" value="${param.orderCode}" />--%>
            <%--</Td>--%>
            <%--<Td colspan="2">--%>
              <%--<a class="com_btn_b" href="#" id="subBut" onclick="pageForm.submit();"><span>查&nbsp;询</span></a>&nbsp;&nbsp;--%>
              <%--<a class="com_btn_b" href="#" id="resetBut" onclick="resetForm();"><span>重&nbsp;置</span></a>--%>
            <%--</Td>--%>
          <%--</tr>--%>
        <%--</table>--%>
      <%--</li>--%>
    <%--</ul>--%>
    <%--<Br><Br>--%>
    <%--<c:if test="${method eq 'search'}">--%>
      <%--<a class="com_btn_b" href="#" id="refresh_but_s" onclick="refreshOrderAll();"><span>刷新当前学期所有订单</span></a>&nbsp;&nbsp;--%>
      <%--<a class="com_btn_b_disable" id="refresh_but_h" style="display: none"><span>刷新当前学期所有订单</span></a>&nbsp;&nbsp;--%>
      <%--<a class="com_btn_b" href="#" id="split_but_s" onclick="splitOrderAll();"><span>拆分当前学期所有订单</span></a>--%>
      <%--<a class="com_btn_b_disable" id="split_but_h" style="display: none"><span>拆分当前学期所有订单</span></a>--%>
      <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
        <%--<tr>--%>
          <%--<th style="width: 8%;">订单号</th>--%>
          <%--<th style="width: 10%;">学习中心</th>--%>
          <%--<th style="width: 7%;">专业</th>--%>
          <%--<th style="width: 4%;">层次</th>--%>
          <%--<th style="width: 4%;">入学年</th>--%>
          <%--<th style="width: 4%;">入学季</th>--%>
          <%--<th style="width: 4%;">教材数量</th>--%>
          <%--<th style="width: 3%;">金额</th>--%>
          <%--<th style="width: 4%;">状态</th>--%>
          <%--<th style="width: 14%;">邮寄地址</th>--%>
          <%--<th style="width: 5%;">收件人</th>--%>
          <%--<th style="width: 5%;">手机</th>--%>
          <%--<th style="width: 5%;">座机</th>--%>
          <%--<th style="width: 5%;">邮编</th>--%>
          <%--<th style="width: 5%;">操作人</th>--%>
          <%--<th style="width: 5%;">操作时间</th>--%>
          <%--<th >操作</th>--%>
        <%--</tr>--%>
        <%--<c:if test="${empty pageInfo || empty pageInfo.pageResults}">--%>
          <%--<tr>--%>
            <%--<td colspan="20" align="center" style="color: red;">没有找到相关数据</td>--%>
          <%--</tr>--%>
        <%--</c:if>--%>
        <%--<c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">--%>
          <%--<tr>--%>
            <%--<td>${json.orderCode}</td>--%>
            <%--<td>(${json.spotCode})${json.spotName}</td>--%>
            <%--<td>${json.specName}</td>--%>
            <%--<td>${json.levelName}</td>--%>
            <%--<td>${json.enYear}</td>--%>
            <%--<td>--%>
              <%--<c:if test="${json.enQuarter == 0}">春季</c:if>--%>
              <%--<c:if test="${json.enQuarter == 1}">秋季</c:if>--%>
            <%--</td>--%>
            <%--<td>${json.tmCount}</td>--%>
            <%--<td>${json.totalPrice}</td>--%>
            <%--<td>--%>
              <%--<c:choose>--%>
                <%--<c:when test="${json.state == 1}">已生成</c:when>--%>
                <%--<c:when test="${json.state == 2}">分拣中</c:when>--%>
                <%--<c:when test="${json.state == 3}">已打包</c:when>--%>
                <%--<c:when test="${json.state == 4}">已发出</c:when>--%>
                <%--<c:when test="${json.state == 5}">已签收</c:when>--%>
                <%--<c:otherwise>未知</c:otherwise>--%>
              <%--</c:choose>--%>
            <%--</td>--%>
            <%--<td>${json.address}</td>--%>
            <%--<td>${json.adminName}</td>--%>
            <%--<td>${json.phone}</td>--%>
            <%--<td>${json.tel}</td>--%>
            <%--<td>${json.postalCode}</td>--%>
            <%--<td>${json.operator}</td>--%>
            <%--<td>${json.operateTime}</td>--%>
            <%--<td>--%>
              <%--<a href="#" onclick="detail('${json.orderCode}',${json.state},${json.id},'${json.spotCode}','${json.specName}','${json.levelName}','${json.enYear}','${json.enQuarter}')" style="color: #0092DC">查看明细</a>&nbsp;&nbsp;--%>
              <%--<a href="#" onclick="refreshOrder('${json.id}')" style="color: #0092DC">刷新</a>&nbsp;&nbsp;--%>
              <%--<a href="#" onclick="splitOrder('${json.id}')" style="color: #0092DC">拆分</a>&nbsp;&nbsp;--%>
              <%--<a href="#" onclick="setSign(${json.id})" style="color: #0092DC">标记为已签收</a>&nbsp;&nbsp;--%>
              <%--<a href="#" onclick="del(${json.id})" style="color: #0092DC">删除</a>--%>
            <%--</td>--%>
          <%--</tr>--%>
        <%--</c:forEach>--%>
        <%--<%@ include file="/common/page.jsp"%>--%>
      <%--</table>--%>
      <%--<br /><br />--%>
    <%--</c:if>--%>
  <%--</form>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function detail(code, state, orderId, spotCode, specName, levelName, enYear, enQuarter){--%>
    <%--var isAuto = 0;--%>
    <%--if(specName == "" && levelName == "" && enYear == "" && enQuarter == ""){--%>
      <%--isAuto = 1;--%>
    <%--}--%>
    <%--parent.openDialog('查看订单教材', 0.6, 0.8, '${pageContext.request.contextPath}/findPlaceOrderListByOrderCode/doFindPlaceOrderListByOrderCode.htm?orderCode='+code+'&state='+state+'&orderId='+orderId+'&spotCode='+spotCode+'&isAuto='+isAuto);--%>
  <%--}--%>

  <%--function del(id){--%>
    <%--if(confirm("您确定要删除该订单？")){--%>
      <%--$.ajax({--%>
        <%--cache: true,--%>
        <%--type: "POST",--%>
        <%--url:"${pageContext.request.contextPath}/delPlaceOrderByCode/del.htm",--%>
        <%--data:{"ids":id},--%>
        <%--async: false,--%>
        <%--success: function(result) {--%>
          <%--if(result.state == 0){--%>
            <%--alert("操作成功");--%>
            <%--pageForm.submit();--%>
          <%--}else{--%>
            <%--alert(msg);--%>
          <%--}--%>
        <%--}--%>
      <%--});--%>
    <%--}--%>
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

  <%--function refreshOrder(id){--%>
    <%--$.ajax({--%>
      <%--url:"${pageContext.request.contextPath}/refreshPlaceOrder/refresh.htm",--%>
      <%--method : 'POST',--%>
      <%--async:false,--%>
      <%--data:{"id":id},--%>
      <%--success:function(data){--%>
        <%--if(data.state == 0){--%>
          <%--alert("操作成功！");--%>
          <%--pageForm.submit();--%>
        <%--}else {--%>
          <%--alert(data.msg);--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>
  <%--}--%>
  <%--function refreshOrderAll(){--%>
    <%--$("#refresh_but_s").hide();--%>
    <%--$("#refresh_but_h").show();--%>
    <%--setTimeout(function(){--%>
      <%--$.ajax({--%>
        <%--url:"${pageContext.request.contextPath}/refreshPlaceOrder/refreshAll.htm",--%>
        <%--method : 'POST',--%>
        <%--async:false,--%>
        <%--success:function(data){--%>
          <%--if(data.state == 0){--%>
            <%--alert("操作成功！");--%>
            <%--pageForm.submit();--%>
          <%--}else {--%>
            <%--alert(data.msg);--%>
          <%--}--%>
          <%--$("#refresh_but_s").show();--%>
          <%--$("#refresh_but_h").hide();--%>
        <%--}--%>
      <%--});--%>
    <%--}, 1000);--%>
  <%--}--%>

  <%--function splitOrder(id){--%>
    <%--if(confirm("您确定要拆分该订单？")){--%>
      <%--$.ajax({--%>
        <%--url:"${pageContext.request.contextPath}/splitPlaceOrder/split.htm",--%>
        <%--method : 'POST',--%>
        <%--async:false,--%>
        <%--data:{"id":id},--%>
        <%--success:function(data){--%>
          <%--if(data.state == 0){--%>
            <%--alert("操作成功！");--%>
            <%--pageForm.submit();--%>
          <%--}else {--%>
            <%--alert(data.msg);--%>
          <%--}--%>
        <%--}--%>
      <%--});--%>
    <%--}--%>
  <%--}--%>
  <%--function splitOrderAll(){--%>
    <%--if(confirm("您确定要拆分所有订单？")){--%>
      <%--$("#split_but_s").hide();--%>
      <%--$("#split_but_h").show();--%>
      <%--setTimeout(function(){--%>
        <%--$.ajax({--%>
          <%--url:"${pageContext.request.contextPath}/splitPlaceOrder/splitAll.htm",--%>
          <%--method : 'POST',--%>
          <%--async:false,--%>
          <%--success:function(data){--%>
            <%--if(data.state == 0){--%>
              <%--alert("操作成功！");--%>
              <%--pageForm.submit();--%>
            <%--}else {--%>
              <%--alert(data.msg);--%>
            <%--}--%>
            <%--$("#split_but_h").hide();--%>
            <%--$("#split_but_s").show();--%>
          <%--}--%>
        <%--});--%>
      <%--}, 1000);--%>
    <%--}--%>
  <%--}--%>

  <%--function setSign(id){--%>
    <%--$.ajax({--%>
      <%--cache: true,--%>
      <%--type: "POST",--%>
      <%--url:"${pageContext.request.contextPath}/setPlaceOrderStateSignById/setSign.htm",--%>
      <%--data:{"id":id},--%>
      <%--async: false,--%>
      <%--success: function(result) {--%>
        <%--if(result.state == 0){--%>
          <%--alert("操作成功");--%>
          <%--pageForm.submit();--%>
        <%--}else{--%>
          <%--alert(msg);--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>
  <%--}--%>

  <%--function resetForm(){--%>
    <%--$("#enterYear, #quarter, #provCode, #spotCode, #specCode, #levelCode,#state").val('');--%>
  <%--<c:forEach var="semester" items="${semesterList}">--%>
    <%--<c:if test="${semester.isNowSemester == 0}">--%>
    <%--$("#semesterId").val(${semester.id});--%>
  <%--</c:if>--%>
  <%--</c:forEach>--%>
  <%--}--%>

<%--</script>--%>


<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findPlaceOrderForNotStock/find.htm" method="post">
  <input type="hidden" id="rows" name="rows" />
  <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
  <table width="90%">
    <tr height="40">
      <td align="right"><label >入学年：</label></td>
      <td><input type="text" id="enterYear" name="enterYear" value="${param.enterYear}" /></td>
      <td align="right"><label >入学季：</label></td>
      <td>
        <select id="quarter" name="quarter" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <option value="0" <c:if test="${param.quarter eq '0'}">selected="selected" </c:if> >春季</option>
          <option value="1" <c:if test="${param.quarter eq '1'}">selected="selected" </c:if> >秋季</option>
        </select>
      </td>
      <td align="right"><label >教材数量：</label></td>
      <td>
        <select id="tmCount" name="tmCount">
          <option value="0" <c:if test="${param.tmCount eq '0'}">selected="selected" </c:if> >大于0</option>
          <option value="1" <c:if test="${param.tmCount eq '1'}">selected="selected" </c:if> >所有</option>
        </select>
      </td>
    </tr>
    <tr height="40">
      <td align="right"><label >学期：</label></td>
      <td>
        <select id="semesterId" name="semesterId" onchange="app.changeSelect(this)">
          <c:forEach var="semester" items="${semesterList}">
            <c:choose>
              <c:when test="${method eq 'search'}">
                <option value="${semester.id}" <c:if test="${param.semesterId == semester.id}">selected="selected" </c:if> >${semester.semester}</option>
              </c:when>
              <c:otherwise>
                <option value="${semester.id}" <c:if test="${semester.isNowSemester == 0}">selected="selected" </c:if> >${semester.semester}</option>
              </c:otherwise>
            </c:choose>
          </c:forEach>
        </select>
      </td>
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
      <td align="right"><label >状态：</label></td>
      <td>
        <select id="state" name="state" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <option value="1" <c:if test="${param.state eq '1'}">selected="selected" </c:if> >已生成</option>
          <option value="2" <c:if test="${param.state eq '2'}">selected="selected" </c:if> >处理中</option>
          <option value="3" <c:if test="${param.state eq '3'}">selected="selected" </c:if> >分拣中</option>
          <option value="4" <c:if test="${param.state eq '4'}">selected="selected" </c:if> >已打包</option>
          <option value="5" <c:if test="${param.state eq '5'}">selected="selected" </c:if> >已发出</option>
          <option value="6" <c:if test="${param.state eq '6'}">selected="selected" </c:if> >已签收</option>
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
      <td align="right"><label >订单号：</label></td>
      <td><input type="text" id="orderCode" name="orderCode" class="input_240" style="width: 150px;" value="${param.orderCode}" /></td>
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
<c:if test="${'search' eq method}">
  <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
    <tr>
      <td colspan="999" style="background-color:#FFF">
        <button class="am-btn am-btn-primary btn-loading-example"
                data-am-loading="{spinner: 'circle-o-notch', loadingText: '执行中...', resetText: '<span class=am-icon-cog></span> 刷新当前学期所有订单'}"
                type="button" onClick="refreshOrderAll(this)"><span class="am-icon-refresh"></span> 刷新当前学期所有订单</button>&nbsp;&nbsp;
        <button class="am-btn am-btn-primary btn-loading-example"
                data-am-loading="{spinner: 'circle-o-notch', loadingText: '执行中...', resetText: '<span class=am-icon-cog></span> 拆分当前学期所有订单'}"
                type="button" onClick="splitOrderAll(this)"><span class="am-icon-cog"></span> 拆分当前学期所有订单</button>
      </td>
    </tr>
    <tr class="am-primary">
      <th style="width: 8%;">订单号</th>
      <th style="width: 10%;">学习中心</th>
      <th style="width: 7%;">专业</th>
      <th style="width: 4%;">层次</th>
      <th style="width: 4%;">入学年</th>
      <th style="width: 4%;">入学季</th>
      <th style="width: 4%;">教材数量</th>
      <th style="width: 3%;">金额</th>
      <th style="width: 4%;">状态</th>
      <th style="width: 14%;">邮寄地址</th>
      <th style="width: 5%;">收件人</th>
      <th style="width: 5%;">手机</th>
      <th style="width: 5%;">座机</th>
      <th style="width: 5%;">邮编</th>
      <th style="width: 5%;">操作人</th>
      <th style="width: 5%;">操作时间</th>
      <th >操作</th>
    </tr>
    <c:if test="${empty pageInfo || empty pageInfo.pageResults}">
      <tr>
        <td colspan="20" align="center" style="color: red;">没有找到相关数据</td>
      </tr>
    </c:if>
    <c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">
      <tr>
        <td>${json.orderCode}</td>
        <td>(${json.spotCode})${json.spotName}</td>
        <td>${json.specName}</td>
        <td>${json.levelName}</td>
        <td>${json.enYear}</td>
        <td>
          <c:if test="${json.enQuarter == 0}">春季</c:if>
          <c:if test="${json.enQuarter == 1}">秋季</c:if>
        </td>
        <td>${json.tmCount}</td>
        <td>${json.totalPrice}</td>
        <td>
          <c:choose>
            <c:when test="${json.state == 1}">已生成</c:when>
            <c:when test="${json.state == 2}">处理中</c:when>
            <c:when test="${json.state == 3}">分拣中</c:when>
            <c:when test="${json.state == 4}">已打包</c:when>
            <c:when test="${json.state == 5}">已发出</c:when>
            <c:when test="${json.state == 6}">已签收</c:when>
            <c:otherwise>未知</c:otherwise>
          </c:choose>
        </td>
        <td>${json.address}</td>
        <td>${json.adminName}</td>
        <td>${json.phone}</td>
        <td>${json.tel}</td>
        <td>${json.postalCode}</td>
        <td>${json.operator}</td>
        <td>${json.operateTime}</td>
        <td>
          <div class="doc-dropdown-justify-js">
            <div class="am-dropdown" data-am-dropdown="" id="operateBtn_${status.index+1}">
              <button class="am-btn am-btn-default am-btn-sm am-dropdown-toggle" data-am-dropdown-toggle=""><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
              <ul class="am-dropdown-content">
                <li><a href="#" onClick="detail('${json.orderCode}',${json.state},${json.id},'${json.spotCode}','${json.specName}','${json.levelName}','${json.enYear}','${json.enQuarter}')"><span style="margin-left:10px; margin-top:5px; margin-bottom:5px;" class="am-icon-th-list"></span> 查看明细</a></li>
                <li><a href="#" onClick="refreshOrder('${json.id}')"><span style="margin-left:10px; margin-top:5px; margin-bottom:5px;" class="am-icon-refresh"></span> 刷新</a></li>
                <li><a href="#" onClick="splitOrder(${json.id})"><span style="margin-left:10px; margin-top:5px; margin-bottom:5px;" class="am-icon-cog"></span> 拆分</a></li>
                <li><a href="#" onClick="setSign(${json.id})"><span style="margin-left:10px; margin-top:5px; margin-bottom:5px;" class="am-icon-edit"></span> 标记为已签收</a></li>
                <li><a href="#" onClick="del(${json.id})"><span style="margin-left:10px; margin-top:5px; margin-bottom:5px;" class="am-icon-trash-o"></span> 删除</a></li>
              </ul>
            </div>
          </div>
        </td>
      </tr>
    </c:forEach>
  </table>
  <%@ include file="/common/page2.jsp"%>
</c:if>
<script>
  $("select").selected();
  $(function() {
    for(var i=1; i<=${fn:length(pageInfo.pageResults)}; i++) {
      $('#operateBtn_'+i).dropdown({justify: '.doc-dropdown-justify-js'});
    }
  });

  function detail(code, state, orderId, spotCode, specName, levelName, enYear, enQuarter){
    var isAuto = 0;
    if(specName == "" && levelName == "" && enYear == "" && enQuarter == ""){
      isAuto = 1;
    }
    if(1 < state){
      app.openOneBtnDialog('${pageContext.request.contextPath}/findPlaceOrderListByOrderCode/doFindPlaceOrderListByOrderCode.htm?orderCode='+code+'&state='+state+'&orderId='+orderId+'&spotCode='+spotCode+'&isAuto='+isAuto, '查看订单教材', 800, 0.9);
    }else{
      app.openDialog('${pageContext.request.contextPath}/findPlaceOrderListByOrderCode/doFindPlaceOrderListByOrderCode.htm?orderCode='+code+'&state='+state+'&orderId='+orderId+'&spotCode='+spotCode+'&isAuto='+isAuto, '查看订单教材', 800, 0.9, function(index){
        var material_order_str = "";
        var dataArr = $(".hidClaInp");
        var msg = "";

        for(var i = 0;i < dataArr.length;i ++){
          var detailId = dataArr[i].value;
          var v_count = $("#" + detailId).val().trim();
          debugger;
          if(v_count == ""){
            msg = "数量不能为空";
          }
          if(isNaN(v_count)){
            msg = "请输入数字";
          }
          material_order_str += detailId + "_" + v_count + "a";
        }
        if("" != msg){
          app.msg(msg, 1);
          return;
        }
        material_order_str = material_order_str.substring(0,material_order_str.length - 1);

        app.edit("${pageContext.request.contextPath}/placeOrder/updatePlaceOrder.htm", {"placeOrderId":orderId, "material_order_str":material_order_str}, index);
      });
    }
  }

  function refreshOrderAll(btnObj){
    $(btnObj).button("loading");
    setTimeout(function(){
      app.edit("${pageContext.request.contextPath}/refreshPlaceOrder/refreshAll.htm", {});
    }, 100);
  }

  function splitOrderAll(btnObj){
    $(btnObj).button("loading");
    setTimeout(function(){
      app.edit("${pageContext.request.contextPath}/splitPlaceOrder/splitAll.htm", {"spotCode":$("#spotCode").val()});
    }, 100);
  }

  function refreshOrder(id){
    app.edit("${pageContext.request.contextPath}/refreshPlaceOrder/refresh.htm", {"id":id});
  }

  function splitOrder(id){
    app.edit("${pageContext.request.contextPath}/splitPlaceOrder/split.htm", {"id":id});
  }

  function setSign(id){
    app.edit("${pageContext.request.contextPath}/setPlaceOrderStateSignById/setSign.htm", {"id":id});
  }

  function del(id){
    app.del("您确定要删除该订单？", "${pageContext.request.contextPath}/delPlaceOrderByCode/del.htm", {"ids":id});
  }
</script>