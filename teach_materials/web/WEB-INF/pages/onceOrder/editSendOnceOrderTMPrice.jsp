<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%--<form id="editForm" method="post">--%>
  <%--<input type="hidden" name="semesterId" value="${param.semesterId}" />--%>
  <%--<input type="hidden" name="spotCodes" value="${param.spotCodes}" />--%>
  <%--<input type="hidden" name="studentCode" value="${param.studentCode}" />--%>
  <%--<input type="hidden" name="tmId" value="${param.tmId}" />--%>
  <%--<input type="hidden" name="beginDate" value="${param.beginDate}" />--%>
  <%--<input type="hidden" name="endDate" value="${param.endDate}" />--%>
  <%--<input type="hidden" name="price" value="${param.price}" />--%>

  <%--<div class="main-content">--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<label class="lab_80 left">价格：</label>--%>
        <%--<input type="text" class="input_240" name="newPrice" value="${param.price}" maxlength="50" />--%>
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
        <%--newPrice : {--%>
          <%--required : true--%>
        <%--}--%>
      <%--},--%>
      <%--messages : {--%>
        <%--code : {--%>
          <%--required : '请输入价格'--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>
  <%--});--%>

  <%--function sub(){--%>
    <%--if($("#editForm").valid()){--%>
      <%--$.ajax({--%>
        <%--cache: true,--%>
        <%--type: "POST",--%>
        <%--url:"${pageContext.request.contextPath}/editSendOnceOrderTMPrice/editor.htm",--%>
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
  <input type="hidden" name="semesterId" value="${param.semesterId}" />
  <input type="hidden" name="spotCodes" value="${param.spotCodes}" />
  <input type="hidden" name="studentCode" value="${param.studentCode}" />
  <input type="hidden" name="tmId" value="${param.tmId}" />
  <input type="hidden" name="beginDate" value="${param.beginDate}" />
  <input type="hidden" name="endDate" value="${param.endDate}" />
  <input type="hidden" name="price" value="${param.price}" />
  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >价格：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="number" placeholder="输入价格" required id="edit_newPrice" name="newPrice" value="${param.price}" />
    </div>
    <div class="am-u-sm-5">*必填</div>
  </div>
</form>