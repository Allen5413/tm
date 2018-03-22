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
  <%--<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findPlaceOrderPackagePageByWhere/find.htm" method="post">--%>
    <%--<input type="hidden" id="page" name="page" value="${pageInfo.currentPage}"/>--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<table width="80%">--%>
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
            <%--<td>--%>
              <%--<label class="lab_80">包裹编号：</label>--%>
              <%--<input type="text" id="code" name="code" class="input_240" style="width: 200px;" value="${param.code}" />--%>
            <%--</td>--%>
          <%--</tr>--%>
          <%--<Tr style="height: 30px;">--%>
            <%--<td>--%>
              <%--<label class="lab_80">邮寄日期：</label>--%>
              <%--<input type="text" id="startDate" name="startDate" value="${param.startDate}" class="Wdate" onfocus="WdatePicker({readOnly:true})"/>--%>
              <%-----%>
              <%--<input type="text" id="endDate" name="endDate" value="${param.endDate}" class="Wdate" onfocus="WdatePicker({readOnly:true})"/>--%>
            <%--</td>--%>
            <%--<td>--%>
              <%--<label class="lab_80">是否签收：</label>--%>
              <%--<select id="isSign" name="isSign">--%>
                <%--<option value="">请选择</option>--%>
                <%--<option value="0" <c:if test="${param.isSign eq '0'}">selected="selected" </c:if>>是</option>--%>
                <%--<option value="1" <c:if test="${param.isSign eq '1'}">selected="selected" </c:if>>否</option>--%>
              <%--</select>--%>
            <%--</td>--%>
          <%--</Tr>--%>
          <%--<tr style="height: 30px;">--%>
            <%--<Td>--%>
              <%--<a class="com_btn_b" href="#" id="subBut" onclick="pageForm.submit();"><span>查&nbsp;询</span></a>&nbsp;&nbsp;--%>
              <%--<a class="com_btn_b" href="#" id="resetBut" onclick="resetForm();"><span>重&nbsp;置</span></a>--%>
            <%--</Td>--%>
          <%--</tr>--%>
        <%--</table>--%>
      <%--</li>--%>
    <%--</ul>--%>
    <%--<c:if test="${method eq 'search'}">--%>
      <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
        <%--<tr>--%>
          <%--<th style="width: 30px;">序号</th>--%>
          <%--<th style="width: 160px;">学习中心</th>--%>
          <%--<th style="width: 6%;">包裹顺序号</th>--%>
          <%--<th style="width: 14%;">最新物流信息</th>--%>
          <%--<th style="width: 100px;">邮寄日期</th>--%>
          <%--<th style="width: 120px;">包裹单号</th>--%>
          <%--<th style="width: 120px;">物流单号</th>--%>
          <%--<th style="width: 60px;">小包数量</th>--%>
          <%--<th style="width: 100px;">是否签收</th>--%>
          <%--<th style="width: 120px;">操作</th>--%>
        <%--</tr>--%>
        <%--<c:if test="${empty pageInfo || empty pageInfo.pageResults}">--%>
          <%--<tr>--%>
            <%--<td colspan="20" align="center" style="color: red;">没有找到相关数据</td>--%>
          <%--</tr>--%>
        <%--</c:if>--%>
        <%--<c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">--%>
          <%--<tr>--%>
            <%--<td align="center">${status.index+1}</td>--%>
            <%--<td>(${json.spotCode})${json.spotName}</td>--%>
            <%--<td>${json.sort}</td>--%>
            <%--<td title="${json.kuaidiJSON.context}">--%>
              <%--<a href="#" onclick="searchKuaidi('${json.logisticCode}')" style="color: #0092DC">--%>
                <%--<c:if test="${14 < fn:length(json.kuaidiJSON.context)}">--%>
                  <%--${fn:substring(json.kuaidiJSON.context, 0, 14)}...--%>
                <%--</c:if>--%>
                <%--<c:if test="${14 >= fn:length(json.kuaidiJSON.context)}">--%>
                  <%--${json.kuaidiJSON.context}--%>
                <%--</c:if>--%>
              <%--</a>--%>
            <%--</td>--%>
            <%--<td>${json.sendTime}</td>--%>
            <%--<td>${json.code}</td>--%>
            <%--<td><a href="#" onclick="searchKuaidi('${json.logisticCode}')" style="color: #0092DC">${json.logisticCode}</a></td>--%>
            <%--<td>--%>
              <%--<a href="#" onclick="searchOrder(${json.id})" style="color: #0092DC">${json.orderCount}</a>--%>
            <%--</td>--%>
            <%--<td>--%>
                <%--<c:choose>--%>
                  <%--<c:when test="${json.isSign == 0}">是</c:when>--%>
                  <%--<c:when test="${json.isSign == 1}">否</c:when>--%>
                  <%--<c:otherwise>未知</c:otherwise>--%>
                <%--</c:choose>--%>
            <%--</td>--%>
            <%--<td>--%>
              <%--<a href="#" onclick="printPackage(${json.id})" style="color: #0092DC">查看</a>&nbsp;&nbsp;--%>
            <%--</td>--%>
          <%--</tr>--%>
        <%--</c:forEach>--%>
        <%--<%@ include file="/common/page.jsp"%>--%>
      <%--</table>--%>
    <%--</c:if>--%>
  <%--</form>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function searchOrder(id){--%>
    <%--parent.openDialog('教材包的学生发书单', 0.8, 0.8, '${pageContext.request.contextPath}/findPlaceOrderByPackageId/find.htm?packageId='+id);--%>
  <%--}--%>

  <%--function printPackage(id){--%>
    <%--open("${pageContext.request.contextPath}/printPlaceOrderPackage/open.htm?id="+id);--%>
  <%--}--%>

  <%--function resetForm(){--%>
    <%--$("#spotCode, #code, #startDate, #endDate").val('');--%>
    <%--<c:forEach var="semester" items="${semesterList}">--%>
    <%--<c:if test="${semester.isNowSemester == 0}">--%>
    <%--$("#semesterId").val(${semester.id});--%>
    <%--</c:if>--%>
    <%--</c:forEach>--%>
  <%--}--%>

  <%--function searchKuaidi(logisticCode){--%>
    <%--parent.openDialog('快递信息', 0.8, 0.8, '${pageContext.request.contextPath}/findKuaidiPushByNu/find.htm?logisticCode='+logisticCode);--%>
  <%--}--%>
<%--</script>--%>



<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findPlaceOrderPackagePageByWhere/find.htm" method="post">
  <input type="hidden" id="rows" name="rows" />
  <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
  <table width="90%">
    <tr height="40">
      <td align="right"><label >学期：</label></td>
      <td>
        <select id="semesterId" name="semesterId">
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
      <td align="right"><label >包裹编号：</label></td>
      <td>
        <input type="text" id="code" name="code" class="input_240" style="width: 200px;" value="${param.code}" />
      </td>
    </tr>
    <tr height="40">
      <td align="right"><label >是否签收：</label></td>
      <td>
        <select id="isSign" name="isSign" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <option value="0" <c:if test="${param.isSign eq '0'}">selected="selected" </c:if>>是</option>
          <option value="1" <c:if test="${param.isSign eq '1'}">selected="selected" </c:if>>否</option>
        </select>
      </td>
      <td align="right"><label >邮寄日期：</label></td>
      <td>
        <input type="text" id="startDate" name="startDate" value="${param.startDate}" class="Wdate" onfocus="WdatePicker({readOnly:true})"/>
        -
        <input type="text" id="endDate" name="endDate" value="${param.endDate}" class="Wdate" onfocus="WdatePicker({readOnly:true})"/>
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
<c:if test="${method eq 'search'}">
  <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
    <tr class="am-primary">
      <th style="width: 4%;">序号</th>
      <th style="width: 20%;">学习中心</th>
      <th style="width: 6%;">包裹顺序号</th>
      <th style="width: 14%;">最新物流信息</th>
      <th style="width: 10%;">邮寄日期</th>
      <th style="width: 8%;">包裹单号</th>
      <th style="width: 12%;">物流单号</th>
      <th style="width: 5%;">小包数量</th>
      <th style="width: 5%;">是否签收</th>
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
        <td>(${json.spotCode})${json.spotName}</td>
        <td>${json.sort}</td>
        <td title="${json.kuaidiJSON.context}">
          <a href="#" onclick="searchKuaidi('${json.logisticCode}')" style="color: #0092DC">
            <c:if test="${14 < fn:length(json.kuaidiJSON.context)}">
              ${fn:substring(json.kuaidiJSON.context, 0, 14)}...
            </c:if>
            <c:if test="${14 >= fn:length(json.kuaidiJSON.context)}">
              ${json.kuaidiJSON.context}
            </c:if>
          </a>
        </td>
        <td>${json.sendTime}</td>
        <td>${json.code}</td>
        <td><a href="#" onclick="searchKuaidi('${json.logisticCode}')" style="color: #0092DC">${json.logisticCode}</a></td>
        <td>
          <a href="#" onclick="searchOrder(${json.id})" style="color: #0092DC">${json.orderCount}</a>
        </td>
        <td>
          <c:choose>
            <c:when test="${json.isSign == 0}">是</c:when>
            <c:when test="${json.isSign == 1}">否</c:when>
            <c:otherwise>未知</c:otherwise>
          </c:choose>
        </td>
        <td>
          <div class="am-btn-group">
            <button type="button" class="am-btn am-btn-primary am-round" onClick="printPackage(${json.id})"><span class="am-icon-print"></span> 查看</button>
          </div>
        </td>
      </tr>
    </c:forEach>
  </table>
  <%@ include file="/common/page2.jsp"%>
</c:if>
<script>
  $("select").selected();
  function printPackage(id){
    open("${pageContext.request.contextPath}/printPlaceOrderPackage/open.htm?id="+id);
  }

  function searchKuaidi(logisticCode){
    app.openOneBtnDialog('${pageContext.request.contextPath}/findKuaidiPushByNu/find.htm?logisticCode='+logisticCode, "快递信息", 600, 0.8)
  }

  function searchOrder(id){
    app.openOneBtnDialog('${pageContext.request.contextPath}/findPlaceOrderByPackageId/find.htm?packageId='+id, '教材包的学生发书单', 1400, 0.8);
  }
</script>