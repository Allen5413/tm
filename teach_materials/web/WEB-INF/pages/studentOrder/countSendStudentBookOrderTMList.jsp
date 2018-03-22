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
  <%--<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/countSendStudentBookOrderTM/find.htm" method="post">--%>
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
            <%--<Td>--%>
              <%--<label class="lab_80">学习中心：</label>--%>
              <%--<input type="text" id="spotCodes" name="spotCodes" class="input_240" style="width: 200px;" value="${param.spotCodes}" />--%>
            <%--</Td>--%>
            <%--<Td>--%>
              <%--<label class="lab_80">发书时间：</label>--%>
              <%--<input type="text" id="beginDate" name="beginDate" value="${param.beginDate}" class="Wdate" onfocus="WdatePicker({readOnly:true})"/>--%>
              <%-----%>
              <%--<input type="text" id="endDate" name="endDate" value="${param.endDate}" class="Wdate" onfocus="WdatePicker({readOnly:true})"/>--%>
            <%--</Td>--%>
          <%--</tr>--%>
          <%--<tr style="height: 30px;">--%>
            <%--<Td>--%>
              <%--<label class="lab_80">学号：</label>--%>
              <%--<input type="text" id="studentCode" name="studentCode" class="input_240" style="width: 200px;" value="${param.studentCode}" />--%>
            <%--</Td>--%>
            <%--<Td>--%>
              <%--<label class="lab_80">教材名称：</label>--%>
              <%--<input type="text" id="tmName" name="tmName" class="input_240" style="width: 200px;" value="${param.tmName}" />--%>
            <%--</Td>--%>
            <%--<Td>--%>
              <%--<a class="com_btn_b" href="#" id="subBut" onclick="pageForm.submit();"><span>查&nbsp;询</span></a>&nbsp;&nbsp;--%>
              <%--<a class="com_btn_b" href="#" id="resetBut" onclick="resetForm();"><span>重&nbsp;置</span></a>--%>
            <%--</Td>--%>
          <%--</tr>--%>
        <%--</table>--%>
      <%--</li>--%>
    <%--</ul>--%>
    <%--<Br><Br>--%>
    <%--<c:if test="${method eq 'search'}">--%>
      <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
        <%--<tr>--%>
          <%--<th style="width: 100px;">序号</th>--%>
          <%--<th style="width: 100px;">教材名称</th>--%>
          <%--<th style="width: 100px;">ISBN</th>--%>
          <%--<th style="width: 100px;">作者</th>--%>
          <%--<th style="width: 100px;">现价</th>--%>
          <%--<th style="width: 100px;">发出数量</th>--%>
          <%--<th style="width: 120px;">操作</th>--%>
        <%--</tr>--%>
        <%--<c:if test="${empty pageInfo || empty pageInfo.pageResults}">--%>
          <%--<tr>--%>
            <%--<td colspan="20" align="center" style="color: red;">没有找到相关数据</td>--%>
          <%--</tr>--%>
        <%--</c:if>--%>
        <%--<c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">--%>
          <%--<tr>--%>
            <%--<td>${status.index+1}</td>--%>
            <%--<td>${json.name}</td>--%>
            <%--<td>${json.isbn}</td>--%>
            <%--<td>${json.author}</td>--%>
            <%--<td>${json.price}</td>--%>
            <%--<td>${json.count}</td>--%>
            <%--<td>--%>
              <%--<a href="#" onclick="detail(${json.tmId}, ${param.semesterId}, '${param.spotCodes}', '${param.studentCode}', '${param.beginDate}', '${param.endDate}', ${json.price})" style="color: #0092DC">查看订单</a>&nbsp;&nbsp;--%>
              <%--<a href="#" onclick="editPrice(${json.tmId}, ${param.semesterId}, '${param.spotCodes}', '${param.studentCode}', '${param.beginDate}', '${param.endDate}', ${json.price})" style="color: #0092DC">改价</a>&nbsp;&nbsp;--%>
              <%--<a href="#" onclick="editCount(${json.tmId}, ${param.semesterId}, '${param.spotCodes}', '${param.studentCode}', '${param.beginDate}', '${param.endDate}', ${json.price})" style="color: #0092DC">改数量</a>--%>
            <%--</td>--%>
          <%--</tr>--%>
        <%--</c:forEach>--%>
        <%--<%@ include file="/common/page.jsp"%>--%>
      <%--</table>--%>
    <%--</c:if>--%>
  <%--</form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function detail(tmId, semesterId, spotCodes, studentCode, beginDate, endDate, price){--%>
    <%--parent.openDialog('查看订单', 0.6, 0.8, '${pageContext.request.contextPath}/countSendStudentBookOrderByTMId/find.htm?tmId='+tmId+'&semesterId='+semesterId+'&spotCodes='+spotCodes+'&studentCode='+studentCode+'&beginDate='+beginDate+'&endDate='+endDate+'&price='+price);--%>
  <%--}--%>

  <%--function editPrice(tmId, semesterId, spotCodes, studentCode, beginDate, endDate, price){--%>
    <%--parent.openDialog('修改价格', 0.3, 0.2, '${pageContext.request.contextPath}/editSendStudentBookOrderTMPrice/open.htm?tmId='+tmId+'&semesterId='+semesterId+'&spotCodes='+spotCodes+'&studentCode='+studentCode+'&beginDate='+beginDate+'&endDate='+endDate+'&price='+price);--%>
  <%--}--%>

  <%--function editCount(tmId, semesterId, spotCodes, studentCode, beginDate, endDate, price){--%>
    <%--var dialog = new Dialog();--%>
    <%--dialog.Width = 330;--%>
    <%--dialog.Height = 180;--%>
    <%--dialog.Title = "修改数量";--%>
    <%--dialog.URL = '${pageContext.request.contextPath}/editSendStudentBookOrderTMCount/open.htm?tmId='+tmId+'&semesterId='+semesterId+'&spotCodes='+spotCodes+'&studentCode='+studentCode+'&beginDate='+beginDate+'&endDate='+endDate+'&price='+price;--%>
    <%--dialog.show();--%>
  <%--}--%>

  <%--function resetForm(){--%>
    <%--$("#spotCodes, #beginDate, #endDate, #studentCode, #tmName").val('');--%>
  <%--<c:forEach var="semester" items="${semesterList}">--%>
    <%--<c:if test="${semester.isNowSemester == 0}">--%>
    <%--$("#semesterId").val(${semester.id});--%>
  <%--</c:if>--%>
  <%--</c:forEach>--%>
  <%--}--%>
<%--</script>--%>




<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/countSendStudentBookOrderTM/find.htm" method="post">
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
      <td align="right"><label >学习中心：</label></td>
      <td>
        <input type="text" id="spotCodes" name="spotCodes" class="input_240" style="width: 200px;" value="${param.spotCodes}" />
      </td>
      <td align="right"><label >教材名称：</label></td>
      <td>
        <input type="text" id="tmName" name="tmName" class="input_240" style="width: 200px;" value="${param.tmName}" />
      </td>
    </tr>
    <tr height="40">
      <td align="right"><label >学号：</label></td>
      <td>
        <input type="text" id="studentCode" name="studentCode" style="width: 200px;" value="${param.studentCode}" />
      </td>
      <td align="right"><label >发书时间：</label></td>
      <td>
        <input type="text" id="beginDate" name="beginDate" value="${param.beginDate}" class="Wdate" onfocus="WdatePicker({readOnly:true})"/>
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
<c:if test="${'search' eq method}">
  <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
    <tr class="am-primary">
      <th style="width: 4%;">序号</th>
      <th style="width: 20%;">教材名称</th>
      <th style="width: 10%;">ISBN</th>
      <th style="width: 20%;">作者</th>
      <th style="width: 7%;">现价</th>
      <th style="width: 7%;">发出数量</th>
      <th>操作</th>
    </tr>
    <c:if test="${empty pageInfo || empty pageInfo.pageResults}">
      <tr>
        <td colspan="20" align="center" style="color: red;">没有找到相关数据</td>
      </tr>
    </c:if>
    <c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">
      <tr>
        <td>${status.index+1}</td>
        <td>${json.name}</td>
        <td>${json.isbn}</td>
        <td>${json.author}</td>
        <td>${json.price}</td>
        <td>${json.count}</td>
        <td>
          <div class="am-btn-group">
            <button type="button" class="am-btn am-btn-primary am-round" onClick="detail(${json.tmId}, ${param.semesterId}, '${param.spotCodes}', '${param.studentCode}', '${param.beginDate}', '${param.endDate}', ${json.price})"><span class="am-icon-th-list"></span> 查看订单</button>
            <button type="button" class="am-btn am-btn-primary am-round" onClick="editPrice(${json.tmId}, ${param.semesterId}, '${param.spotCodes}', '${param.studentCode}', '${param.beginDate}', '${param.endDate}', ${json.price})"><span class="am-icon-edit"></span> 改价</button>
            <button type="button" class="am-btn am-btn-primary am-round" onClick="editCount(${json.tmId}, ${param.semesterId}, '${param.spotCodes}', '${param.studentCode}', '${param.beginDate}', '${param.endDate}', ${json.price})"><span class="am-icon-edit"></span> 改数量</button>
          </div>
        </td>
      </tr>
    </c:forEach>
  </table>
  <%@ include file="/common/page2.jsp"%>
</c:if>
<script>
  $("select").selected();
  function detail(tmId, semesterId, spotCodes, studentCode, beginDate, endDate, price){
    app.openOneBtnDialog('${pageContext.request.contextPath}/countSendStudentBookOrderByTMId/find.htm?tmId='+tmId+'&semesterId='+semesterId+'&spotCodes='+spotCodes+'&studentCode='+studentCode+'&beginDate='+beginDate+'&endDate='+endDate+'&price='+price, '查看订单', 0.6, 0.8);
  }

  function editPrice(tmId, semesterId, spotCodes, studentCode, beginDate, endDate, price){
    app.openDialog('${pageContext.request.contextPath}/editSendStudentBookOrderTMPrice/open.htm?tmId='+tmId+'&semesterId='+semesterId+'&spotCodes='+spotCodes+'&studentCode='+studentCode+'&beginDate='+beginDate+'&endDate='+endDate+'&price='+price, "修改价格", 400, 0.25, function(index){
      var newPrice = $("#edit_newPrice").val().trim();
      if(!vaild.vaildMoney(newPrice)){
        app.msg("请输入正确的价格", 1);
        return;
      }
      app.edit("${pageContext.request.contextPath}/editSendStudentBookOrderTMPrice/editor.htm", $('#editForm').serialize(), index);
    });
  }

  function editCount(tmId, semesterId, spotCodes, studentCode, beginDate, endDate, price){
    app.openDialog('${pageContext.request.contextPath}/editSendStudentBookOrderTMCount/open.htm?tmId='+tmId+'&semesterId='+semesterId+'&spotCodes='+spotCodes+'&studentCode='+studentCode+'&beginDate='+beginDate+'&endDate='+endDate+'&price='+price, "修改数量", 400, 0.25, function(index){
      var newCount = $("#edit_newCount").val().trim();
      if(isNaN(newCount)){
        app.msg("请输入数量", 1);
        return;
      }
      app.edit("${pageContext.request.contextPath}/editSendStudentBookOrderTMCount/editor.htm", $('#editForm').serialize(), index);
    });
  }
</script>