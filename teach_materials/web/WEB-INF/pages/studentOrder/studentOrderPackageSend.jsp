<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="create_info_list">
  <li>
    <label style="width: 500px;">(${param.spotCode})${spotName}, 本次配送共有${pageInfo.totalCount}件。</label>
  </li>
</ul>
<form id="addForm" name="addForm" method="post">
  <table id="tm_table" class="table_slist" cellpadding="0" cellspacing="0" width="100%">
    <tr>
      <th style="width: 30%; text-align: center">包裹号码</th>
      <th style="width: 70%">快递单号</th>
    </tr>
    <c:forEach var="json" items="${pageInfo.pageResults}">
      <tr>
        <Td align="center">第${json.sort}件</Td>
        <Td>
          <input type="hidden" name="ids" value="${json.id}" />
          <input type="text" name="logisticCodes" style="width: 300px;" />
        </Td>
      </tr>
    </c:forEach>
  </table>
  <a class="com_btn_b" id="but_s" href="#" onclick="saveLogisticCode()" style="margin-top: 10px;"><span>提&nbsp;交</span></a>
  <a class="com_btn_b_disable" id="but_h" style="display: none"><span>提&nbsp;交</span></a>
</form>
<script>
  $(function () {
    // 获取表单中的所有输入框
    var inputs = $("#addForm input:text");
    inputs[0].focus(); // 设置焦点
    $("#addForm input:text").keypress(function (e) {
      if (e.which == 13) {// 判断所按是否回车键
        var idx = inputs.index(this); // 获取当前焦点输入框所处的位置
        if (idx == inputs.length - 1) {// 判断是否是最后一个输入框
          // 用户确认
          if (confirm("最后一个输入框已经输入,是否提交?")) {
            saveLogisticCode();
          }
        } else {
          inputs[idx + 1].focus(); // 设置焦点
          inputs[idx + 1].select(); // 选中文字
        }
        return false; // 取消默认的提交行为
      }
    });
  });

  function saveLogisticCode(){
      var isValid = true;
      $("[name=logisticCodes]").each(function(){
        if($.trim($(this).val()) == ""){
          isValid = false;
        }
      });
      if(!isValid) {
        alert("请填写完物流单号");
        return;
      }

      $("#but_s").hide();
      $("#but_h").show();
      //重新查询数据
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/sendStudentBookOrderPackage/send.htm",
        data:$('#addForm').serialize(),
        async: false,
        success: function(data) {
          if(data.state == 0){
            alert("提交成功！");
            closeDialog();
          }else{
            alert(data.msg);
          }
          $("#but_s").show();
          $("#but_h").hide();
        }
      });
  }
</script>