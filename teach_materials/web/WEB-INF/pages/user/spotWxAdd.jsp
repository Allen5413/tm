<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%--<div class="main-content">--%>
  <%--<form id="addSpotWxForm" name="addSpotWxForm" method="post">--%>
    <%--<input type="hidden" name="code" value="${param.code}" />--%>
    <%--<div class="main-content">--%>
      <%--<ul class="create_info_list">--%>
        <%--<li>--%>
          <%--<label class="lab_80">管理员姓名：</label>--%>
          <%--<input type="text" class="input_240" id="adminName" name="adminName" maxlength="50" />--%>
        <%--</li>--%>
        <%--<li>--%>
          <%--<label class="lab_80 left">身份证号：</label>--%>
          <%--<input type="text" class="input_240" id="idcard" name="idcard" maxlength="50" />--%>
        <%--</li>--%>
        <%--<li>--%>
          <%--<label class="lab_80 left">--%>
            <%--<a class="com_btn_b" href="#" onclick="addSpotWx()"><span>提&nbsp;交</span></a>--%>
          <%--</label>--%>
        <%--</li>--%>
      <%--</ul>--%>
    <%--</div>--%>
  <%--</form>--%>
<%--</div>--%>
<%--<script>--%>
  <%--function addSpotWx(){--%>
    <%--var idcard = $("#idcard").val();--%>
    <%--var adminName = $("#adminName").val();--%>

    <%--if(idcard == ""){--%>
      <%--alert("请输入身份证号");--%>
      <%--return false;--%>
    <%--}--%>
    <%--if(adminName == ""){--%>
      <%--alert("请输入姓名");--%>
      <%--return false;--%>
    <%--}--%>

    <%--$.ajax({--%>
      <%--cache: true,--%>
      <%--type: "POST",--%>
      <%--url:"${pageContext.request.contextPath}/addSpotWx/add.htm",--%>
      <%--data:$('#addSpotWxForm').serialize(),--%>
      <%--async: false,--%>
      <%--success: function(data) {--%>
        <%--if(data.state == 0){--%>
          <%--$("#idcard").val('');--%>
          <%--$("#adminName").val('');--%>
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
<%--</script>--%>


<form class="am-form" id="addSpotWxForm" name="addSpotWxForm" method="post">
  <input type="hidden" name="code" value="${param.code}" />
  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >管理员姓名：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入管理员姓名" required id="adminName" name="adminName" />
    </div>
    <div class="am-u-sm-5">*必填</div>
  </div>
  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >身份证号：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="number" placeholder="输入身份证号" required id="idcard" name="idcard" />
    </div>
    <div class="am-u-sm-5">*必填，不可重复</div>
  </div>
</form>