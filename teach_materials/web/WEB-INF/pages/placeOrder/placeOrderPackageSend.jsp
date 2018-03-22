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
      <th style="width: 70%">物流单号</th>
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
  <a class="com_btn_b" href="#" onclick="saveLogisticCode()" style="margin-top: 10px;"><span>提&nbsp;交</span></a>
</form>
<script>
  //回车事件
  document.onkeydown = function(e){
    var ev = document.all ? window.event : e;
    if(ev.keyCode==13) {
      //inputLogisticCode();
    }
  }


  function inputLogisticCode(obj){
    //$(obj).next().focus();
  }

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

      //重新查询数据
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/sendPlaceOrderPackage/send.htm",
        data:$('#addForm').serialize(),
        async: false,
        success: function(data) {
          if(data.state == 0){
            alert("提交成功！");
            closeDialog();
          }else{
            alert(data.msg);
          }
        }
      });
  }
</script>