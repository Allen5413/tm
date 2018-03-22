<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<form id="editForm" method="post">--%>
  <%--<input type="hidden" name="id" value="${issueRange.id}" />--%>
  <%--<input type="hidden" name="version" value="${issueRange.version}" />--%>
  <%--<div class="main-content">--%>
    <%--<c:choose>--%>
      <%--<c:when test="${empty spot}">没有找到学习中心数据</c:when>--%>
      <%--<c:otherwise>--%>
        <%--<ul class="create_info_list">--%>
          <%--<li>--%>
            <%--<label class="lab_80" style="width: 150px;">学习中心编号：</label>${spot.code}--%>
          <%--</li>--%>
          <%--<li>--%>
            <%--<label class="lab_80" style="width: 150px;">学习中心名称：</label>${spot.name}--%>
          <%--</li>--%>
          <%--<li>--%>
            <%--<label class="lab_80" style="width: 150px;">发行渠道：</label>--%>
            <%--<select name="issueChannelId">--%>
              <%--<option value="">请选择</option>--%>
              <%--<c:forEach var="channel" items="${issueChannelList}">--%>
                <%--<option value="${channel.id}" <c:if test="${channel.id == issueRange.issueChannelId}">selected="selected" </c:if> >${channel.name}</option>--%>
              <%--</c:forEach>--%>
            <%--</select>--%>
          <%--</li>--%>
          <%--<li>--%>
            <%--<label class="lab_80" style="width: 150px;">是否发行：</label>--%>
            <%--<input type="radio" name="isIssue" value="0" <c:if test="${0 == issueRange.isIssue}">checked</c:if> />是--%>
            <%--<input type="radio" name="isIssue" value="1" <c:if test="${1 == issueRange.isIssue}">checked</c:if> />否--%>
          <%--</li>--%>
          <%--<li>--%>
            <%--<label class="lab_80 left">--%>
              <%--<a class="com_btn_b" href="#" onclick="sub()"><span>提&nbsp;交</span></a>--%>
            <%--</label>--%>
          <%--</li>--%>
        <%--</ul>--%>
      <%--</c:otherwise>--%>
    <%--</c:choose>--%>
  <%--</div>--%>
<%--</form>--%>
<%--<script>--%>
  <%--function sub(){--%>
    <%--if($("#editForm").valid()){--%>
      <%--$.ajax({--%>
        <%--cache: true,--%>
        <%--type: "POST",--%>
        <%--url:"${pageContext.request.contextPath}/editIssueRange/issueRangeEdit.htm",--%>
        <%--data:$('#editForm').serialize(),--%>
        <%--async: false,--%>
        <%--success: function(data) {--%>
          <%--if(data.state == 0){--%>
            <%--alert("提交成功！");--%>
            <%--$('#index_iframe').contents().find("#subBut").click();--%>
            <%--closeDialog();--%>
          <%--}else{--%>
            <%--alert(data.msg);--%>
          <%--}--%>
        <%--}--%>
      <%--});--%>
    <%--}--%>
  <%--}--%>
<%--</script>--%>



<form class="am-form" id="editForm" method="post">
  <input type="hidden" name="id" value="${issueRange.id}" />
  <input type="hidden" name="version" value="${issueRange.version}" />

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >学习中心编号：</label></div>
    <div class="am-u-sm-4" style="float: left">${spot.code}</div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >学习中心名称：</label></div>
    <div class="am-u-sm-4" style="float: left">${spot.name}</div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >发行渠道：</label></div>
    <div class="am-u-sm-4" style="float: left">
      <select name="issueChannelId">
        <option value="">--请选择--</option>
        <c:forEach var="channel" items="${issueChannelList}">
          <option value="${channel.id}" <c:if test="${channel.id == issueRange.issueChannelId}">selected="selected" </c:if> >${channel.name}</option>
        </c:forEach>
      </select>
    </div>
  </div>

  <div class="am-g am-margin-top-sm">
    <div class="am-u-sm-3 am-text-right"><label >是否发行：</label></div>
    <div class="am-u-sm-4" style="float: left">
      <div class="am-btn-group" data-am-button="">
        <label class="am-btn am-btn-primary am-btn-s">
          <input type="radio" name="isIssue" value="0" <c:if test="${0 == issueRange.isIssue}">checked</c:if>> 是
        </label>
        <label class="am-btn am-btn-primary am-btn-s">
          <input type="radio" name="isIssue" value="1" <c:if test="${0 == issueRange.isIssue}">checked</c:if>> 否
        </label>
      </div>
    </div>
  </div>
</form>