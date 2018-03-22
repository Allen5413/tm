<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="addForm" name="addForm" method="post">
  <ul class="create_info_list">
    <li>
      <label class="lab_80">发行渠道：</label>
      <select name="issueChannelId">
        <c:forEach var="issueChannel" items="${issueChannelList}">
          <option value="${issueChannel.id}">${issueChannel.name}</option>
        </c:forEach>
      </select>
    </li>
  </ul>
  <table id="change_tm_table" class="table_slist" cellpadding="0" cellspacing="0" width="100%">
    <tr>
      <th style="width: 60px;">序号</th>
      <th style="width: 150px;">教材名称</th>
      <th style="width: 100px;">作者</th>
      <th style="width: 150px;">出版社</th>
      <th style="width: 150px;">ISBN</th>
      <th style="width: 70px;">价格</th>
      <th style="width: 70px;">数量</th>
      <th style="width: 50px;">操作</th>
    </tr>
  </table>
</form>
<a class="com_btn_b" href="#" onclick="openSearchTMDiv()" style="margin-top: 10px;"><span>添加教材</span></a>&nbsp;&nbsp;
<a class="com_btn_b" href="#" onclick="sub()" style="margin-top: 10px;"><span>提&nbsp;交</span></a>
<script>
  function openSearchTMDiv(){
    $("#addOrderTM_div").hide();
    $("#searchTM").show();
  }

  function del(obj){
    $(obj).parent().parent().remove();
  }

  function sub(){
    var valid = true;
    if($("#change_tm_table").find("input[type=text]").length < 1){
      alert("请添加教材");
      valid = false;
    }
    $("#change_tm_table").find("input[type=text]").each(function(){
      if(isNaN($.trim($(this).val())) && valid){
        alert("请输入正确的数量");
        valid = false;
      }
      if($.trim($(this).val()) < 1 && valid){
        alert("请至少输入1本教材");
        valid = false;
      }
    });
    if(valid){
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/addPurchaseOrder/add.htm",
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
  }
</script>