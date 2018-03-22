<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="main-content">--%>
  <%--<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findRefundPageByWhere/find.htm" method="post">--%>
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
              <%--<label class="lab_80">状态：</label>--%>
              <%--<select id="state" name="state">--%>
                <%--<option value="">请选择</option>--%>
                <%--<option value="0" <c:if test="${param.state eq '0'}">selected="selected" </c:if> >待打印</option>--%>
                <%--<option value="1" <c:if test="${param.state eq '1'}">selected="selected" </c:if> >待上传凭证</option>--%>
                <%--<option value="2" <c:if test="${param.state eq '2'}">selected="selected" </c:if> >待审核</option>--%>
                <%--<option value="3" <c:if test="${param.state eq '3'}">selected="selected" </c:if> >已审核</option>--%>
              <%--</select>--%>
            <%--</td>--%>
            <%--<td>--%>
              <%--<label class="lab_80">申请时间：</label>--%>
              <%--<input type="text" id="beginDate" name="beginDate" class="Wdate" style="width: 110px;" onfocus="WdatePicker({readOnly:true})" value="${param.beginDate}"/>---%>
              <%--<input type="text" id="endDate" name="endDate" class="Wdate" style="width: 110px;" onfocus="WdatePicker({readOnly:true})" value="${param.endDate}"/>--%>
            <%--</td>--%>
          <%--</Tr>--%>
          <%--<tr>--%>
            <%--<Td>--%>
              <%--<a class="com_btn_b" href="#" id="subBut" onclick="pageForm.submit();"><span>查&nbsp;询</span></a>&nbsp;&nbsp;--%>
              <%--<a class="com_btn_b" href="#" id="resetBut" onclick="resetForm();"><span>重&nbsp;置</span></a>&nbsp;&nbsp;--%>
            <%--</Td>--%>
          <%--</tr>--%>
        <%--</table>--%>
      <%--</li>--%>
    <%--</ul>--%>

    <%--<a class="com_btn_b" href="#" onclick="add()"><span>申请退款</span></a>--%>
    <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
      <%--<tr>--%>
        <%--<th style="width: 3%;">序号</th>--%>
        <%--<th style="width: 20%;">学习中心</th>--%>
        <%--<th style="width: 8%;">批次编号</th>--%>
        <%--<th style="width: 13%;">银行名称</th>--%>
        <%--<th style="width: 8%;">银行卡号</th>--%>
        <%--<th style="width: 10%;">收款单位</th>--%>
        <%--<th style="width: 6%;">状态</th>--%>
        <%--<th style="width: 4%;">人数</th>--%>
        <%--<th style="width: 4%;">总金额</th>--%>
        <%--<th style="width: 5%;">操作人</th>--%>
        <%--<th style="width: 8%;">操作时间</th>--%>
        <%--<th style="width: 12%;">操作</th>--%>
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
          <%--<td>${json.company}</td>--%>
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
            <%--<a href="#" onclick="detail('${json.code}')" style="color: #0092DC">查看明细</a>&nbsp;&nbsp;--%>
            <%--<a href="#" onclick="printRefund('${json.code}')" style="color: #0092DC">打印</a>--%>
            <%--<c:if test="${json.state == 1}">--%>
              <%--&nbsp;&nbsp;<a href="#" onclick="uploadApply('${json.code}')" style="color: #0092DC">上传凭证</a>--%>
            <%--</c:if>--%>
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
  <%--function add(){--%>
    <%--var spotCode = $('#spotCode').val();--%>
    <%--if(spotCode == ""){--%>
      <%--alert("请选择中心");--%>
    <%--}else{--%>
      <%--var dialog = new Dialog();--%>
      <%--dialog.ShowButtonRow=true;--%>
      <%--dialog.OKEvent = function(){--%>
        <%--dialog.innerFrame.contentWindow.sub($("#subBut"));--%>
      <%--};--%>
      <%--dialog.Width = getWindowWidthSize() * 0.8;--%>
      <%--dialog.Height = getWindowHeightSize() * 0.8;--%>
      <%--dialog.Title = "缴费";--%>
      <%--dialog.URL = "${pageContext.request.contextPath}/addRefund/open.htm?spotCode="+spotCode;--%>
      <%--dialog.show();--%>
      <%--dialog.okButton.value=" 提 交 ";--%>
      <%--dialog.cancelButton.value=" 取 消 ";--%>
    <%--}--%>
  <%--}--%>

  <%--function detail(code){--%>
    <%--var dialog = new Dialog();--%>
    <%--dialog.Width = getWindowWidthSize() * 0.8;--%>
    <%--dialog.Height = getWindowHeightSize() * 0.8;--%>
    <%--dialog.Title = "退款明细";--%>
    <%--dialog.URL = "${pageContext.request.contextPath}/findRefundStudentByCode/find.htm?code="+code;--%>
    <%--dialog.show();--%>
  <%--}--%>

  <%--function printRefund(code){--%>
    <%--open("${pageContext.request.contextPath}/printRefundStudentByCode/print.htm?code="+code);--%>
    <%--$("#subBut").click();--%>
  <%--}--%>

  <%--function uploadApply(code){--%>
    <%--var dialog = new Dialog();--%>
    <%--dialog.ShowButtonRow=true;--%>
    <%--dialog.OKEvent = function(){--%>
      <%--dialog.innerFrame.contentWindow.sub($("#subBut"));--%>
    <%--};--%>
    <%--dialog.Width = 500;--%>
    <%--dialog.Height = 300;--%>
    <%--dialog.Title = "上传凭证";--%>
    <%--dialog.URL = "${pageContext.request.contextPath}/uploadApplyImgRefund/open.htm?code="+code;--%>
    <%--dialog.show();--%>
    <%--dialog.okButton.value=" 提 交 ";--%>
    <%--dialog.cancelButton.value=" 取 消 ";--%>
  <%--}--%>

  <%--function resetForm(){--%>
    <%--$("#name").val('');--%>
  <%--}--%>
<%--</script>--%>

<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findRefundPageByWhere/find.htm" method="post">
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
      <td align="right"><label >批次编号：</label></td>
      <td><input type="text" id="code" name="code" value="${param.code}" /></td>
    </tr>
    <tr height="40">
      <td align="right"><label >状态：</label></td>
      <td>
        <select id="state" name="state" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <option value="0" <c:if test="${param.state eq '0'}">selected="selected" </c:if> >待打印</option>
          <option value="1" <c:if test="${param.state eq '1'}">selected="selected" </c:if> >待上传凭证</option>
          <option value="2" <c:if test="${param.state eq '2'}">selected="selected" </c:if> >待审核</option>
          <option value="3" <c:if test="${param.state eq '3'}">selected="selected" </c:if> >已审核</option>
        </select>
      </td>
      <td align="right"><label >申请时间：</label></td>
      <td>
        <input type="text" id="beginDate" name="beginDate" class="Wdate" style="width: 110px;" onfocus="WdatePicker({readOnly:true})" value="${param.beginDate}"/>-
        <input type="text" id="endDate" name="endDate" class="Wdate" style="width: 110px;" onfocus="WdatePicker({readOnly:true})" value="${param.endDate}"/>
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
<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <tr>
    <td colspan="999" style="background-color:#FFF">
      <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="add()"><span class="am-icon-plus"></span> 申请退款</button>
    </td>
  </tr>
  <tr class="am-primary">
    <th style="width: 4%;">序号</th>
    <th style="width: 20%;">学习中心</th>
    <th style="width: 8%;">批次编号</th>
    <th style="width: 15%;">银行名称</th>
    <th style="width: 8%;">银行卡号</th>
    <th style="width: 10%;">收款单位</th>
    <th style="width: 6%;">状态</th>
    <th style="width: 4%;">人数</th>
    <th style="width: 5%;">总金额</th>
    <th style="width: 5%;">操作人</th>
    <th style="width: 8%;">操作时间</th>
    <th style="width: 6%;">操作</th>
  </tr>
  <c:if test="${empty pageInfo || empty pageInfo.pageResults}">
    <tr>
      <td colspan="99" align="center" style="color: red;">没有找到相关数据</td>
    </tr>
  </c:if>
  <c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">
    <tr>
      <td align="center">${status.index+1}</td>
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
        <div class="doc-dropdown-justify-js">
          <div class="am-dropdown" data-am-dropdown="" id="operateBtn_${status.index+1}">
            <button class="am-btn am-btn-default am-btn-sm am-dropdown-toggle" data-am-dropdown-toggle=""><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
            <ul class="am-dropdown-content">
              <li><a href="#" onClick="detail('${json.code}')"><span style="margin-left:10px; margin-top:5px; margin-bottom:5px;" class="am-icon-th-list"></span> 查看明细</a></li>
              <li><a href="#" onClick="printRefund('${json.code}')"><span style="margin-left:10px; margin-top:5px; margin-bottom:5px;" class="am-icon-print"></span> 打印</a></li>
              <c:if test="${json.state == 1}">
                <li><a href="#" onClick="uploadApply('${json.code}')"><span style="margin-left:10px; margin-top:5px; margin-bottom:5px;" class="am-icon-upload"></span> 上传凭证</a></li>
              </c:if>
              <c:if test="${json.state < 2}">
                <li><a href="#" onClick="del('${json.code}')"><span style="margin-left:10px; margin-top:5px; margin-bottom:5px;" class="am-icon-trash"></span> 删除</a></li>
              </c:if>
            </ul>
          </div>
        </div>
      </td>
    </tr>
  </c:forEach>
</table>
<%@ include file="/common/page2.jsp"%>
<script>
  $("select").selected();
  $(function() {
    for(var i=1; i<=${fn:length(pageInfo.pageResults)}; i++) {
      $('#operateBtn_'+i).dropdown({justify: '.doc-dropdown-justify-js'});
    }
  });

  function detail(code){
    app.openOneBtnDialog('${pageContext.request.contextPath}/findRefundStudentByCode/find.htm?code='+code, '退款明细', 1200, 0.9);
  }

  function del(code){
    app.del("你确定要删除该退款申请？", "${pageContext.request.contextPath}/delRefundByCode/del.htm", {"code":code});
  }

  function printRefund(code){
    open("${pageContext.request.contextPath}/printRefundStudentByCode/print.htm?code="+code);
    $("#searchBtn").click();
  }

  function uploadApply(code){
    app.openDialog("${pageContext.request.contextPath}/uploadApplyImgRefund/open.htm?code="+code, '上传凭证', 650, 0.4, function(index){
      app.addForFile("${pageContext.request.contextPath}/uploadApplyImgRefund/applyUpload.htm", "addForm", index);
    });
  }

  function add(){
    var spotCode = $('#spotCode').val();
    if(spotCode == ""){
      app.msg("请选择中心", 1);
      return;
    }else{
      app.openDialog("${pageContext.request.contextPath}/addRefund/open.htm?spotCode="+spotCode, '申请退款', 1200, 0.7, function(index){
        var isSub = false;
        var msg = "";
        var studentMoneyDetails = "";
        $("[name=cb]").each(function(){
          if($(this).prop("checked")){
            isSub = true;
            var studentCode = $(this).val();
            var studentMoney = $("#refundMonery_"+studentCode).val();
            var detail = $("#detail_"+studentCode).val();
            if(!vaild.vaildMoney(studentMoney)){
              msg += "学号："+studentCode+", 退款金额错误！\n";
            }
            if(detail.indexOf("_") >= 0 || detail.indexOf("|") >= 0 ){
              msg += "学号："+studentCode+", 退款说明不能包含'_'、'|'等特殊字符！\n";
            }
            if(Number(studentMoney) > Number($("#canRefundMoney_"+studentCode).val())){
              msg += "学号："+studentCode+", 退款金额超额了！\n";
            }
            studentMoneyDetails += studentCode+"_"+studentMoney+"_"+detail+"|";
          }
        });
        if(!isSub){
          app.msg("请选择要退款得学生", 1);
        }else{
          if(msg != ""){
            app.msg(msg, 1);
          }else{
            if("" != studentMoneyDetails && 0 < studentMoneyDetails.length){
              $("#studentMoneyDetails").val(studentMoneyDetails.substr(0, studentMoneyDetails.length-1))
            }
            if($("#bankName").val() == ""){
              app.msg("请输入银行名称", 1);
            }
            else if($("#bankCode").val() == ""){
              app.msg("请输入银行卡号", 1);
            }
            else if($("#company").val() == ""){
              app.msg("请输入收款单位", 1);
            }else{
              app.add("${pageContext.request.contextPath}/addRefund/add.htm", $('#addForm').serialize(), index);
            }
          }
        }
      });
    }
  }
</script>