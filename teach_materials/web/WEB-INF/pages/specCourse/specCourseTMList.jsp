<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <tr class="am-primary">
    <th style="width:6%">序号</th>
    <th style="width:6%">课程编号</th>
    <th style="width:16%">课程姓名</th>
    <th style="width:25%">教材名称</th>
    <th style="width:15%">作者</th>
    <th style="width:15%">出版社</th>
    <th style="width:10%">ISBN</th>
    <th>价格</th>
  </tr>
  <c:if test="${empty resultJSON}">
    <tr>
      <td colspan="99" align="center" style="color: red;">没有找到相关数据</td>
    </tr>
  </c:if>
  <c:forEach var="json" items="${resultJSON}" varStatus="status">
    <c:set var="course_name" value="${fn:split(json.key, '_')}" />
    <c:set var="tmList" value="${json.value}" />
    <c:set var="tmSize" value="${fn:length(tmList)}" />
    <tr>
      <td align="center" style="vertical-align:middle" <c:if test="${tmSize < 2}"> rowspan="1" </c:if><c:if test="${tmSize > 1}"> rowspan="${tmSize}" </c:if>>${status.index+1}</td>
      <td style="vertical-align:middle" <c:if test="${tmSize < 2}"> rowspan="1" </c:if><c:if test="${tmSize > 1}"> rowspan="${tmSize}" </c:if>>${course_name[0]}</td>
      <td style="vertical-align:middle" <c:if test="${tmSize < 2}"> rowspan="1" </c:if><c:if test="${tmSize > 1}"> rowspan="${tmSize}" </c:if>>${course_name[1]}</td>
      <c:if test="${empty tmList}">
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
      </c:if>
      <c:if test="${!empty tmList}">
        <c:forEach var="tm" items="${tmList}" varStatus="status2">
          <c:if test="${status2.index > 0}">
            <tr>
          </c:if>
          <td>${tm.tmName}</td>
          <td>${tm.author}</td>
          <td>${tm.pName}</td>
          <td>${tm.isbn}</td>
          <td>${tm.price}</td>
          <c:if test="${status2.index > 0}">
            </tr>
          </c:if>
        </c:forEach>
      </c:if>
    </tr>
  </c:forEach>
</table>