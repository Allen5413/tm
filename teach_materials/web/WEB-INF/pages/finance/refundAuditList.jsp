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
  <%--<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findRefundForAudit/find.htm" method="post">--%>
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
              <%--<label class="lab_80">批次编号：</label>--%>
              <%--<input type="text" id="code" name="code" class="input_240" style="width: 150px;" value="${param.code}" />--%>
            <%--</Td>--%>
          <%--</tr>--%>
          <%--<Tr style="height: 30px;">--%>
            <%--<td>--%>
              <%--<label class="lab_80">申请时间：</label>--%>
              <%--<input type="text" id="beginDate" name="beginDate" class="Wdate" style="width: 110px;" onfocus="WdatePicker({readOnly:true})" value="${param.beginDate}"/>---%>
              <%--<input type="text" id="endDate" name="endDate" class="Wdate" style="width: 110px;" onfocus="WdatePicker({readOnly:true})" value="${param.endDate}"/>--%>
            <%--</td>--%>
            <%--<Td>--%>
              <%--<a class="com_btn_b" href="#" id="subBut" onclick="pageForm.submit();"><span>查&nbsp;询</span></a>&nbsp;&nbsp;--%>
              <%--<a class="com_btn_b" href="#" id="resetBut" onclick="resetForm();"><span>重&nbsp;置</span></a>&nbsp;&nbsp;--%>
            <%--</Td>--%>
          <%--</Tr>--%>
        <%--</table>--%>
      <%--</li>--%>
    <%--</ul>--%>
  <%--</form>--%>
  <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
    <%--<tr>--%>
      <%--<th style="width: 3%;">序号</th>--%>
      <%--<th style="width: 24%;">学习中心</th>--%>
      <%--<th style="width: 10%;">批次编号</th>--%>
      <%--<th style="width: 15%;">银行名称</th>--%>
      <%--<th style="width: 12%;">银行卡号</th>--%>
      <%--<th style="width: 6%;">状态</th>--%>
      <%--<th style="width: 5%;">人数</th>--%>
      <%--<th style="width: 5%;">总金额</th>--%>
      <%--<th style="width: 5%;">操作人</th>--%>
      <%--<th style="width: 8%;">操作时间</th>--%>
      <%--<th style="width: 8%;">操作</th>--%>
    <%--</tr>--%>
    <%--<c:if test="${empty pageInfo || empty pageInfo.pageResults}">--%>
      <%--<tr>--%>
        <%--<td colspan="99" align="center" style="color: red;">没有找到相关数据</td>--%>
      <%--</tr>--%>
    <%--</c:if>--%>
    <%--<c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">--%>
      <%--<tr>--%>
        <%--<td align="center">${status.index+1}</td>--%>
        <%--<td>[${json.spotCode}]${json.spotName}</td>--%>
        <%--<td>${json.code}</td>--%>
        <%--<td>${json.bankName}</td>--%>
        <%--<td>${json.bankCode}</td>--%>
        <%--<td>--%>
          <%--<c:choose>--%>
            <%--<c:when test="${json.state == 0}">待打印</c:when>--%>
            <%--<c:when test="${json.state == 1}">待上传凭证</c:when>--%>
            <%--<c:when test="${json.state == 2}">待审核</c:when>--%>
            <%--<c:when test="${json.state == 3}">已审核</c:when>--%>
            <%--<c:otherwise>未知</c:otherwise>--%>
          <%--</c:choose>--%>
        <%--</td>--%>
        <%--<td>${json.count}</td>--%>
        <%--<td>${json.money}</td>--%>
        <%--<td>${json.operator}</td>--%>
        <%--<td>${json.operateTime}</td>--%>
        <%--<td>--%>
          <%--<a href="#" onclick="audit('${json.code}','${json.spotCode}')" style="color: #0092DC">审核</a>--%>
        <%--</td>--%>
      <%--</tr>--%>
    <%--</c:forEach>--%>
    <%--<%@ include file="/common/page.jsp"%>--%>
  <%--</table>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>

  <%--function audit(code, spotCode){--%>
    <%--var dialog = new Dialog();--%>
    <%--dialog.ShowButtonRow=true;--%>
    <%--dialog.OKEvent = function(){--%>
      <%--dialog.innerFrame.contentWindow.sub($("#subBut"));--%>
    <%--};--%>
    <%--dialog.Width = 1200;--%>
    <%--dialog.Height = 800;--%>
    <%--dialog.Title = "审核退款";--%>
    <%--dialog.MessageTitle = "审核注意事项";--%>
    <%--dialog.Message = "1：请先勾选可以退款得学生。<br>" +--%>
    <%--"2：再在审核说明里填写说明，然后点确定按钮，这时，没有勾选的学生就会在后面的审核说明中自动填上说明。<br>" +--%>
    <%--"3：上传退款凭证。<br>" +--%>
    <%--"4：确定无误后提交审核结果。";--%>
    <%--dialog.URL = "${pageContext.request.contextPath}/auditRefundStudent/open.htm?code="+code+"&spotCode="+spotCode;--%>
    <%--dialog.show();--%>
    <%--dialog.okButton.value=" 提 交 ";--%>
    <%--dialog.cancelButton.value=" 取 消 ";--%>
  <%--}--%>

  <%--function resetForm(){--%>
    <%--$("#name").val('');--%>
  <%--}--%>
<%--</script>--%>


<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findRefundForAudit/find.htm" method="post">
  <input type="hidden" id="rows" name="rows" />
  <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
  <label >学习中心：</label>
  <select id="spotCode" name="spotCode" data-am-selected="{maxHeight: 500, searchBox: 1}" onchange="app.changeSelect(this)">
    <option value=""></option>
    <option value="null">全部</option>
    <c:forEach var="spot" items="${spotList}">
      <option value="${spot.code}" <c:if test="${param.spotCode eq spot.code}">selected="selected" </c:if> >[${spot.code}]${spot.name}</option>
    </c:forEach>
  </select>&nbsp;&nbsp;&nbsp;&nbsp;

  <label>批次编号：</label>
  <input type="text" id="code" name="code" value="${param.code}" />&nbsp;&nbsp;&nbsp;&nbsp;

  <label class="lab_80">申请时间：</label>
  <input type="text" id="beginDate" name="beginDate" class="Wdate" style="width: 110px;" onfocus="WdatePicker({readOnly:true})" value="${param.beginDate}"/>-
  <input type="text" id="endDate" name="endDate" class="Wdate" style="width: 110px;" onfocus="WdatePicker({readOnly:true})" value="${param.endDate}"/><p />

  <button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example"
    data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '查询超时'}"
    onclick="app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'), this)"><span class="am-icon-search"></span> 查询</button>
</form>
<p /><p />
<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <tr>
    <td colspan="999" style="background-color:#FFF">
      <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="printRefundBatch()"><span class="am-icon-print"></span> 打印</button>
    </td>
  </tr>
  <tr class="am-primary">
    <th style="width: 5%;">
      <a href="#" onclick="app.checkAll('ids');">全选</a>|
      <a href="#" onclick="app.checkNAll('ids');">反选</a>
    </th>
    <th style="width: 12%;">学习中心</th>
    <th style="width: 8%;">批次编号</th>
    <th style="width: 12%;">银行名称</th>
    <th style="width: 12%;">银行卡号</th>
    <th style="width: 10%;">收款单位</th>
    <th style="width: 5%;">状态</th>
    <th style="width: 5%;">人数</th>
    <th style="width: 5%;">总金额</th>
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
      <td align="center">
        <c:if test="${json.state >= 2}">
          <input type="checkbox" name="ids" value="${json.code}">
        </c:if>
      </td>
      <td>[${json.spotCode}]${json.spotName}</td>
      <td>${json.code}</td>
      <td>${json.bankName}</td>
      <td>${json.bankCode}</td>
      <td>${json.company}</td>
      <td>
        <c:choose>
          <c:when test="${json.state == 0}">待打印</c:when>
          <c:when test="${json.state == 1}">待上传凭证</c:when>
          <c:when test="${json.state == 2}">待审核</c:when>
          <c:when test="${json.state == 3}">已审核</c:when>
          <c:otherwise>未知</c:otherwise>
        </c:choose>
      </td>
      <td>${json.count}</td>
      <td>${json.money}</td>
      <td>${json.operator}</td>
      <td>${json.operateTime}</td>
      <td>
        <div class="am-btn-group">
          <button type="button" class="am-btn am-btn-primary am-round" onClick="audit('${json.code}','${json.spotCode}')"><span class="am-icon-cog"></span> 审核</button>
          <c:if test="${json.state >= 2}">
            <button type="button" class="am-btn am-btn-primary am-round" onClick="printRefund('${json.code}')"><span class="am-icon-print"></span> 打印</button>
          </c:if>
        </div>
      </td>
    </tr>
  </c:forEach>
</table>
<%@ include file="/common/page2.jsp"%>
<script>
  $("select").selected();
  function audit(code, spotCode){
    app.openDialog("${pageContext.request.contextPath}/auditRefundStudent/open.htm?code="+code+"&spotCode="+spotCode, '审核退款', 1200, 0.9, function(index){
      var vaildDetail = true;
      var isChecked = false;
      $("[name=cb]").each(function() {
        var id = $(this).val();
        if (!$(this).prop("checked")) {
          if($("#detail_"+id).val().trim() == ""){
            vaildDetail = false;
          }
        }else{
          isChecked = true;
        }
      });
      var vaildImg = false;
      $("[name=auditImg]").each(function() {
        if (!$(this).val() == "") {
          vaildImg = true;
        }
      });
      if(!vaildDetail){
        app.msg("请为不能退款的学生填写说明！", 1);
        return false;
      }
      else if(!vaildImg && isChecked){
        app.msg("请上传退款凭证！", 1);
        return false;
      }else{
        app.confirm("您确定要按此结果审核，一旦审核不能更改哟？", function(){
          var arrId=new Array();
          var i=0;
          $("[name = auditImg]").each(function(){
            arrId[i] = $(this).attr("id");
            i++;
          });

          var idDetails = new Array();
          i=0;
          var msg = "";
          $("[name=cb]").each(function(){
            var id = $(this).val();
            var detail = $("#detail_"+id).val();
            if(detail.indexOf("_") >= 0){
              msg = "审核说明里面不能有'_'等特殊字符";
              return false;
            }
            idDetails[i] = id+"_"+detail;
            i++;
          });
          if(msg != ""){
            app.msg(msg, 1);
            return;
          }
          $("#idDetails").val(idDetails);
          app.addForFile("${pageContext.request.contextPath}/auditRefundStudent/audit.htm", "addForm", index);
        });
      }
    });
  }

  function printRefund(code){
    open("${pageContext.request.contextPath}/printRefundStudentByCode/print.htm?code="+code);
  }

  function printRefundBatch(){
    var chk_value =[];
    $('input[name="ids"]:checked').each(function(){
      chk_value.push($(this).val());
    });
    if(chk_value.length < 1){
      app.msg("请选择要打印的退款记录", 1);
      return false;
    }
    open("${pageContext.request.contextPath}/printRefundStudentByCode/printBatch.htm?codes="+chk_value);
  }
</script>
