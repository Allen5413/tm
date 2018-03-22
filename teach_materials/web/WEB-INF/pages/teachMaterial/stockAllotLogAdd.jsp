<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<form id="addForm" name="addForm" method="post">--%>
  <%--<input type="hidden" name="teachMaterialId" value="${param.tmId}">--%>
  <%--<input type="hidden" name="oldIssueChannelId" value="${param.oldIssueChannelId}">--%>
  <%--<div class="main-content">--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<label class="lab_80">发行渠道：</label>--%>
        <%--<select name="newIssueChannelId" id="newIssueChannelId">--%>
          <%--<option value="">请选择</option>--%>
          <%--<c:forEach var="channel" items="${issueChannelList}">--%>
            <%--<option value="${channel.id}" >${channel.name}</option>--%>
          <%--</c:forEach>--%>
        <%--</select>--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80">调拨数量：</label>--%>
        <%--<input type="text" class="input_240" style="width: 120px;" name="stock" id="stock" maxlength="50" />--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80 left">--%>
          <%--<a class="com_btn_b" href="#" onclick="sub()"><span>提&nbsp;交</span></a>--%>
        <%--</label>--%>
      <%--</li>--%>
    <%--</ul>--%>
  <%--</div>--%>
<%--</form>--%>
<%--<script>--%>
  <%--$.validator.addMethod("isSameChannel",function(value,element){--%>
    <%--if($("#newIssueChannelId").val() == ${param.oldIssueChannelId}){--%>
      <%--return false;--%>
    <%--}else{--%>
      <%--return true;--%>
    <%--}--%>
  <%--},"渠道不能调拨给自己");--%>

  <%--$(function(){--%>
    <%--//对数据的检测--%>
    <%--$("#addForm").validate({--%>
      <%--rules : {--%>
        <%--newIssueChannelId : {--%>
          <%--required : true,--%>
          <%--isSameChannel : true--%>
        <%--},--%>
        <%--stock : {--%>
          <%--required : true--%>
        <%--}--%>
      <%--},--%>
      <%--messages : {--%>
        <%--newIssueChannelId: {--%>
          <%--required: '请选择发行渠道'--%>
        <%--},--%>
        <%--stock: {--%>
          <%--required: '请输入调拨库存'--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>
  <%--});--%>

  <%--function sub(){--%>
    <%--if($("#addForm").valid()){--%>
      <%--$.ajax({--%>
        <%--cache: true,--%>
        <%--type: "POST",--%>
        <%--url:"${pageContext.request.contextPath}/addStockAllotLog/atockAllotLogAdd.htm",--%>
        <%--data:$('#addForm').serialize(),--%>
        <%--async: false,--%>
        <%--success: function(data) {--%>
          <%--if(data.state == 0){--%>
            <%--alert("提交成功！");--%>
            <%--refreshDialog();--%>
            <%--destroyD($("#dialogDiv2"));--%>
          <%--}else{--%>
            <%--alert(data.msg);--%>
          <%--}--%>
        <%--}--%>
      <%--});--%>
    <%--}--%>
  <%--}--%>
<%--</script>--%>

<form class="am-form" id="addForm" name="addForm" method="post">
  <input type="hidden" name="teachMaterialId" value="${param.tmId}">
  <input type="hidden" name="oldIssueChannelId" value="${param.oldIssueChannelId}">
  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >发行渠道：</label></div>
    <div class="am-u-sm-4">
      <select name="newIssueChannelId" id="newIssueChannelId">
        <option value="">--请选择--</option>
        <c:forEach var="channel" items="${issueChannelList}">
          <option value="${channel.id}" >${channel.name}</option>
        </c:forEach>
      </select>
    </div>
    <div class="am-u-sm-5">*必填</div>
  </div>
  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >调拨数量：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="number" placeholder="输入调拨数量" required id="add_stock" name="stock"  />
    </div>
    <div class="am-u-sm-5">*必填</div>
  </div>
</form>