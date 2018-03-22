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
<%--<div class="main-content" style="overflow-y: scroll; height: 100%;">--%>
  <%--<c:if test="${empty kuaidiPushMap}">--%>
    <%--<span style="color: red">没有找到快递信息</span>--%>
  <%--</c:if>--%>
  <%--<c:if test="${!empty kuaidiPushMap}">--%>
    <%--<c:forEach var="kuaidiPushMap" items="${kuaidiPushMap}">--%>
      <%--<label class="lab_80" style="color: #0092DC">快递单号：${kuaidiPushMap.key}</label><br /><br />--%>
      <%--<label class="lab_80"><strong>快递信息：</strong></label><br />--%>
      <%--<label class="lab_80" style="width: 99%; text-align: left">--%>
        <%--<c:if test="${empty kuaidiPushMap.value}">--%>
          <%--<span style="color: red">没有找到快递信息</span>--%>
        <%--</c:if>--%>
        <%--<c:if test="${!empty kuaidiPushMap.value}">--%>
          <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
            <%--<c:forEach var="json" items="${kuaidiPushMap.value}">--%>
              <%--<tr style="height: 50px">--%>
                <%--<td style="width: 15%">${json.ftime}</td>--%>
                <%--<td style="width: 85%">${json.context}</td>--%>
              <%--</tr>--%>
            <%--</c:forEach>--%>
          <%--</table>--%>
        <%--</c:if>--%>
      <%--</label>--%>
    <%--<br /><br />--%>
    <%--</c:forEach>--%>
  <%--</c:if>--%>
<%--</body>--%>
<%--</html>--%>


<c:if test="${empty kuaidiPushMap}">
  <span style="color: red">没有找到快递信息</span>
</c:if>
<c:if test="${!empty kuaidiPushMap}">
  <c:forEach var="kuaidiPushMap" items="${kuaidiPushMap}">
    <label class="lab_80" style="color: #0092DC">快递单号：${kuaidiPushMap.key}</label><br /><br />
    <label class="lab_80"><strong>快递信息：</strong></label><br />
    <label class="lab_80" style="width: 99%; text-align: left">
      <c:if test="${empty kuaidiPushMap.value}">
        <span style="color: red">没有找到快递信息</span>
      </c:if>
      <c:if test="${!empty kuaidiPushMap.value}">
        <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
          <c:forEach var="json" items="${kuaidiPushMap.value}">
            <tr style="height: 50px">
              <td style="width: 15%">${json.ftime}</td>
              <td style="width: 85%">${json.context}</td>
            </tr>
          </c:forEach>
        </table>
      </c:if>
    </label>
    <br /><br />
  </c:forEach>
</c:if>

