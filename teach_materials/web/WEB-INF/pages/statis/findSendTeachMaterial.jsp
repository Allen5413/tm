<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<!DOCTYPE html>--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="main-content">--%>
  <%--<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findSendTeachMaterial/find.htm" method="post">--%>
    <%--<input type="hidden" name="method" value="search" />--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<label class="lab_80">学期：</label>--%>
        <%--<select id="semesterId" name="semesterId">--%>
          <%--<c:forEach var="semester" items="${semesterList}">--%>
            <%--<c:choose>--%>
              <%--<c:when test="${empty param.semesterId}">--%>
                <%--<option value="${semester.id}" <c:if test="${semester.isNowSemester == 0}">selected="selected" </c:if> >${semester.semester}</option>--%>
              <%--</c:when>--%>
              <%--<c:otherwise>--%>
                <%--<option value="${semester.id}" <c:if test="${param.semesterId == semester.id}">selected="selected" </c:if> >${semester.semester}</option>--%>
              <%--</c:otherwise>--%>
            <%--</c:choose>--%>
          <%--</c:forEach>--%>
        <%--</select>--%>
        <%--<label class="lab_80">教材名称：</label>--%>
        <%--<input type="text" id="name" name="name" value="${param.name}" />--%>
        <%--<label class="lab_80">教材类型：</label>--%>
        <%--<select id="tmTypeId" name="tmTypeId">--%>
          <%--<option value="">--请选择--</option>--%>
          <%--<option value="1" <c:if test="${param.tmTypeId == 1}">selected="selected"</c:if> >自考</option>--%>
          <%--<option value="2" <c:if test="${param.tmTypeId == 2}">selected="selected"</c:if> >网络教材</option>--%>
          <%--<option value="3" <c:if test="${param.tmTypeId == 3}">selected="selected"</c:if> >自编</option>--%>
          <%--<option value="4" <c:if test="${param.tmTypeId == 4}">selected="selected"</c:if> >统订</option>--%>
        <%--</select>--%>
        <%--<label class="lab_80">发书时间：</label>--%>
        <%--<input type="text" id="beginDate" name="beginDate" class="Wdate" style="width: 110px;" onfocus="WdatePicker({readOnly:true})" value="${param.beginDate}"/>---%>
        <%--<input type="text" id="endDate" name="endDate" class="Wdate" style="width: 110px;" onfocus="WdatePicker({readOnly:true})" value="${param.endDate}"/>--%>
        <%--&nbsp;&nbsp;&nbsp;&nbsp;--%>
        <%--<a class="com_btn_b" href="#" id="subBut" onclick="pageForm.submit();"><span>查&nbsp;询</span></a>&nbsp;&nbsp;--%>
        <%--<a class="com_btn_b" href="#" id="resetBut" onclick="down();"><span>下&nbsp;载</span></a>--%>
      <%--</li>--%>
    <%--</ul>--%>
    <%--<c:if test="${'search' eq param.method}">--%>
      <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
        <%--<tr>--%>
          <%--<th style="width: 10%;">序号</th>--%>
          <%--<th style="width: 35%;">教材名称</th>--%>
          <%--<th style="width: 20%;">出版社</th>--%>
          <%--<th style="width: 10%;">作者</th>--%>
          <%--<th style="width: 10%;">发书时的价格</th>--%>
          <%--<th style="width: 10%;">数量</th>--%>
        <%--</tr>--%>
        <%--<c:if test="${empty list}">--%>
          <%--<tr>--%>
            <%--<td colspan="20" align="center" style="color: red;">没有找到相关数据</td>--%>
          <%--</tr>--%>
        <%--</c:if>--%>
        <%--<c:forEach var="json" items="${list}" varStatus="status">--%>
          <%--<tr>--%>
            <%--<td align="center">${status.index+1}</td>--%>
            <%--<td>${json.name}</td>--%>
            <%--<td>${json.pName}</td>--%>
            <%--<td>${json.author}</td>--%>
            <%--<td>${json.price}</td>--%>
            <%--<td>${json.count}</td>--%>
          <%--</tr>--%>
        <%--</c:forEach>--%>
      <%--</table>--%>
    <%--</c:if>--%>
  <%--</form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function down(){--%>
    <%--$.ajax({--%>
      <%--cache: true,--%>
      <%--type: "POST",--%>
      <%--url:"${pageContext.request.contextPath}/findSendTeachMaterial/down.htm?semesterId=${param.semesterId}&name=${param.name}&beginDate=${param.beginDate}&endDate=${param.endDate}&tmTypeId=${param.tmTypeId}",--%>
      <%--data:{},--%>
      <%--async: false,--%>
      <%--success: function(data) {--%>
        <%--open("${pageContext.request.contextPath}"+data);--%>
      <%--}--%>
    <%--});--%>
  <%--}--%>
<%--</script>--%>


<p />
<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findSendTeachMaterial/find.htm" method="post">
  <input type="hidden" name="method" value="search" />
  <table width="90%">
    <tr height="40">
      <td align="right"><label >学期：</label></td>
      <td>
        <select id="semesterId" name="semesterId">
          <c:forEach var="semester" items="${semesterList}">
            <c:choose>
              <c:when test="${empty param.semesterId}">
                <option value="${semester.id}" <c:if test="${semester.isNowSemester == 0}">selected="selected" </c:if> >${semester.semester}</option>
              </c:when>
              <c:otherwise>
                <option value="${semester.id}" <c:if test="${param.semesterId == semester.id}">selected="selected" </c:if> >${semester.semester}</option>
              </c:otherwise>
            </c:choose>
          </c:forEach>
        </select>
      </td>
      <td align="right"><label >教材名称：</label></td>
      <td><input type="text" id="name" name="name" value="${param.name}" /></td>
      <td align="right"><label >教材类型：</label></td>
      <td>
        <select id="tmTypeId" name="tmTypeId">
          <option value="">--请选择--</option>
          <option value="1" <c:if test="${param.tmTypeId == 1}">selected="selected"</c:if> >自考</option>
          <option value="2" <c:if test="${param.tmTypeId == 2}">selected="selected"</c:if> >网络教材</option>
          <option value="3" <c:if test="${param.tmTypeId == 3}">selected="selected"</c:if> >自编</option>
          <option value="4" <c:if test="${param.tmTypeId == 4}">selected="selected"</c:if> >统订</option>
        </select>
      </td>
    </tr>
    <tr height="40">
      <td align="right"><label >发书开始时间：</label></td>
      <td>
        <input type="text" id="beginDate" name="beginDate" class="Wdate" style="width: 110px;" onfocus="WdatePicker({readOnly:true})" value="${param.beginDate}"/>
      </td>
      <td align="right"><label >发书结束时间：</label></td>
      <td>
        <input type="text" id="endDate" name="endDate" class="Wdate" style="width: 110px;" onfocus="WdatePicker({readOnly:true})" value="${param.endDate}"/>
      </td>
      <td align="right"></td>
      <td></td>
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
      <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="down()"><span class="am-icon-download"></span> 下载</button>
    </td>
  </tr>
  <tr class="am-primary">
    <th style="width: 5%;">序号</th>
    <th style="width: 20%;">教材名称</th>
    <th style="width: 20%;">出版社</th>
    <th style="width: 10%;">作者</th>
    <th style="width: 10%;">发书时的价格</th>
    <th style="width: 10%;">数量</th>
  </tr>
  <c:if test="${empty list}">
    <tr>
      <td colspan="20" align="center" style="color: red;">没有找到相关数据</td>
    </tr>
  </c:if>
  <c:forEach var="json" items="${list}" varStatus="status">
    <tr>
      <td align="center">${status.index+1}</td>
      <td>${json.name}</td>
      <td>${json.pName}</td>
      <td>${json.author}</td>
      <td>${json.price}</td>
      <td>${json.count}</td>
    </tr>
  </c:forEach>
</table>
<script>
  function down(){
    $.ajax({
    cache: true,
    type: "POST",
    url:"${pageContext.request.contextPath}/findSendTeachMaterial/down.htm?semesterId=${param.semesterId}&name=${param.name}&beginDate=${param.beginDate}&endDate=${param.endDate}&tmTypeId=${param.tmTypeId}",
    data:{},
    async: false,
      success: function(data) {
        open("${pageContext.request.contextPath}"+data);
      }
    });
  }
</script>