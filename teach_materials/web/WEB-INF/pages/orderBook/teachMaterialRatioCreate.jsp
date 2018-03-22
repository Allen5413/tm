<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%--<!DOCTYPE html>--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="main-content">--%>
  <%--<form id="addForm" name="addForm" action="${pageContext.request.contextPath}/addTeachMaterialRatio/teachMaterialRatioAdd.htm" method="post">--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<label class="lab_80">旧生系数：</label>--%>
        <%--<input type="text" id="oldRatio" name="oldRatio" value="${teachMaterialRatio.oldRatio}" class="input_240" style="width: 200px;" />--%>
        <%--<label class="lab_80">新生系数：</label>--%>
        <%--<input type="text" id="newRatio" name="newRatio" value="${teachMaterialRatio.newRatio}" class="input_240" style="width: 200px;" />--%>
        <%--&nbsp;&nbsp;&nbsp;&nbsp;--%>
        <%--<a class="com_btn_b" href="#" id="subBut" onclick="create()"><span>自动生成订单</span></a>--%>
        <%--&nbsp;&nbsp;&nbsp;&nbsp;--%>
        <%--<a class="com_btn_b" href="#" onclick="add()"><span>手动添加订单</span></a>--%>
      <%--</li>--%>
    <%--</ul>--%>
  <%--</form>--%>
<%--</div>--%>
<%--<div class="main-content">--%>
<%--<span style="color: red; font-size: 14px;"><b>生成规则说明：</b></span><br><br>--%>
<%--1：旧生教材：当前学期的开课计划中各课程的选课人数*系数-库存=当前需要订书的数量<br><br>--%>
<%--2：新生教材：当前学期的开课计划中各课程和上学期同专业、层次的招生人数*系数-库存=当前需要订书的数量--%>
<%--</div>--%>
<%--<br><br>--%>
<%--<div>--%>
  <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
    <%--<tr>--%>
      <%--<td>自动订单</td>--%>
      <%--<td>操作人：</td>--%>
      <%--<td>${teachMaterialRatio.creator}</td>--%>
      <%--<td>操作时间</td>--%>
      <%--<td>--%>
        <%--<fmt:formatDate value="${teachMaterialRatio.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />--%>
      <%--</td>--%>
      <%--<td>总码洋：</td>--%>
      <%--<td>${totalPrice}</td>--%>
    <%--</tr>--%>
  <%--</table>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--$(function(){--%>
    <%--//对数据的检测--%>
    <%--$("#addForm").validate({--%>
      <%--rules : {--%>
        <%--oldRatio : {--%>
          <%--required : true--%>
        <%--},--%>
        <%--newRatio : {--%>
          <%--required : true--%>
        <%--}--%>
      <%--},--%>
      <%--messages : {--%>
        <%--oldRatio : {--%>
          <%--required : "请输入旧生系数"--%>
        <%--},--%>
        <%--newRatio : {--%>
          <%--required : "请输入新生系数"--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>
  <%--});--%>

  <%--function create(){--%>
    <%--if($("#addForm").valid()){--%>
      <%--addForm.submit();--%>
    <%--}--%>
  <%--}--%>

  <%--function add(){--%>
    <%--parent.openDialog('手动添加采购单', 0.6, 0.6, '${pageContext.request.contextPath}/addPurchaseOrder/open.htm');--%>
  <%--}--%>
<%--</script>--%>

<form id="addForm" name="addForm" action="${pageContext.request.contextPath}/addTeachMaterialRatio/teachMaterialRatioAdd.htm" class="am-form">
  <table width="80%" style="margin-top:60px;">
    <tr>
      <td align="right"><label >旧生系数：</label></td>
      <td><input type="text" id="oldRatio" name="oldRatio" placeholder="输入旧生系数" style="width:200px;" required value="${teachMaterialRatio.oldRatio}" /></td>
      <td align="right"><label >新生系数：</label></td>
      <td><input type="text" id="newRatio" name="newRatio" value="${teachMaterialRatio.newRatio}" placeholder="输入新生系数" style="width:200px;" required/></td>
      <td>
        <button class="am-btn am-btn-secondary btn-loading-example" type="button"
                data-am-loading="{spinner: 'circle-o-notch', loadingText: '生成中...', resetText: '<span class=am-icon-cog></span> 自动生成订单'}"
                onclick="create(this)"><span class="am-icon-cog"></span> 自动生成订单</button>
        <button class="am-btn am-btn-secondary" type="button" onclick="add()"><span class="am-icon-plus"></span> 手动添加订单</button>
      </td>
    </tr>
    <tr height="250">
      <td colspan="5">
        <label style="color:#F00">生成规则说明：</label><p>
        1：旧生教材：当前学期的开课计划中各课程的选课人数*系数-库存=当前需要订书的数量<p>
        2：新生教材：当前学期的开课计划中各课程和上学期同专业、层次的招生人数*系数-库存=当前需要订书的数量
      </td>
    </tr>
  </table>
</form>

<table class="am-table am-table-bordered">
  <tr>
    <td align="center">自动订单</td>
    <td align="right">操作人：</td>
    <td id="operator">${teachMaterialRatio.creator}</td>
    <td align="right">操作时间：</td>
    <td id="operateTime">${teachMaterialRatio.createTimeStr}</td>
    <td align="right">总码洋：</td>
    <td id="totalPrice">${totalPrice}</td>
  </tr>
</table>

<script>
  function create(btnObj){
    if($("#oldRatio").val() == ""){
      app.msg("请输入旧生系数", 1);
      return;
    }
    if($("#newRatio").val() == ""){
      app.msg("请输入新生系数", 1);
      return;
    }
    $(btnObj).button('loading');
    setTimeout(function(){
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/addTeachMaterialRatio/teachMaterialRatioAdd.htm",
        data:$("#addForm").serialize(),
        async: false,
        success: function(data) {
          if(data.state == 0){
            app.msg('生成成功', 0);
            $("#operator").html(data.teachMaterialRatio.creator);
            $("#operateTime").html(data.teachMaterialRatio.createTimeStr);
            $("#totalPrice").html(data.totalPrice);
          }else{
            app.msg(data.msg, 1);
          }
          $(btnObj).button('reset');
        }
      });
    }, 100);
  }
</script>