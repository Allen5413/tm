<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<form id="addForm" method="post">--%>
  <%--<input type="hidden" name="teachMaterialId" value="${param.tmId}" />--%>
  <%--<div class="main-content">--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<label class="lab_80">发行渠道：</label>--%>
        <%--<select name="issueChannelId" id="issueChannelId">--%>
          <%--<c:forEach var="channel" items="${issueChannelList}">--%>
            <%--<option value="${channel.id}" >${channel.name}</option>--%>
          <%--</c:forEach>--%>
        <%--</select>--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80">库存：</label>--%>
        <%--<input type="text" class="input_240" id="add_stock" name="stock" value="${teachMaterialStock.stock}" maxlength="50" />--%>
        <%--<div class="clear"></div>--%>
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
  <%--$(function(){--%>
    <%--//对数据的检测--%>
    <%--$("#editForm").validate({--%>
      <%--rules : {--%>
        <%--stock : {--%>
          <%--required : true--%>
        <%--}--%>
      <%--},--%>
      <%--messages : {--%>
        <%--stock : {--%>
          <%--required : '请输入库存'--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>
  <%--});--%>

  <%--function sub(){--%>
    <%--if($("#addForm").valid()){--%>
      <%--$.ajax({--%>
        <%--cache: true,--%>
        <%--type: "POST",--%>
        <%--url:"${pageContext.request.contextPath}/addTeachMaterialStock/stockAdd.htm",--%>
        <%--data:$('#addForm').serialize(),--%>
        <%--async: false,--%>
        <%--success: function(data) {--%>
          <%--if(data.state == 0){--%>
            <%--alert("提交成功！");--%>
            <%--$('#index_iframe').contents().find("#resetBut").click();--%>
            <%--$('#index_iframe').contents().find("#subBut").click();--%>
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
  <input type="hidden" name="teachMaterialId" value="${param.tmId}" />
  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >发行渠道：</label></div>
    <div class="am-u-sm-4">
      <select name="issueChannelId" id="issueChannelId">
        <c:forEach var="channel" items="${issueChannelList}">
          <option value="${channel.id}" >${channel.name}</option>
        </c:forEach>
      </select>
    </div>
    <div class="am-u-sm-5">*必选</div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >库存：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="number" placeholder="输入库存" required id="add_stock" name="stock"  />
    </div>
    <div class="am-u-sm-5">*必填</div>
  </div>
</form>