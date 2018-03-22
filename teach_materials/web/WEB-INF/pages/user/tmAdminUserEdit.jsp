<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align="center">
  <form id="editForm" name="editForm" method="post">
    <input type="hidden" name="id" value="${user.id}" />
    <input type="hidden" name="version" value="${user.version}" />
    <input type="hidden" name="type" value="${user.type}" />
    <input type="hidden" name="loginName" value="${user.loginName}" />
    <div class="main-content">
      <ul class="create_info_list">
        <li>
          <label class="lab_80">登录名称：</label>${user.loginName}
        </li>
        <li>
          <label class="lab_80">姓名：</label>
          <input type="text" class="input_240" name="name" value="${user.name}" maxlength="50" />
        </li>
        <li>
          <label class="lab_80">手机：</label>
          <input type="text" class="input_240" name="cellPhone" value="${user.cellPhone}" maxlength="50" />
        </li>
        <li>
          <label class="lab_80">状态：</label>
          <input type="radio" name="state" value="0" <c:if test="${0 == user.state}">checked</c:if> />启用
          <input type="radio" name="state" value="1" <c:if test="${1 == user.state}">checked</c:if> />停用
        </li>
        <li>
          <label class="lab_80 left">说明：</label>
          <textarea class="textArea left" name="remark" maxlength="500" style="width: 245px;">${user.remark}</textarea>
          <div class="clear"></div>
        </li>
      </ul>
    </div>
    <div class="main-content" style="padding-left: 50px;">
      <ul>
        <li>
          <label class="lab_80 left">
            <a class="com_btn_b" href="#" onclick="sub()"><span>提&nbsp;交</span></a>
          </label>
        </li>
      </ul>
    </div>
  </form>
</div>
<script>
  $(function(){
    //对数据的检测
    $("#addForm").validate({
      rules : {
        name : {
          required : true
        },
        cellPhone : {
          required : true
        }
      },
      messages : {
        name : {
          required : '请输入用户姓名'
        },
        cellPhone : {
          required : '请输入手机号码'
        }
      }
    });
  });

  function sub(){
    if($("#editForm").valid()){
      $.ajax({
        cache: true,
        type: "POST",
        url:"/editUser/userEdit.htm",
        data:$('#editForm').serialize(),
        async: false,
        success: function(data) {
          if(data.state == 0){
            alert("提交成功！");
            $('#index_iframe').contents().find("#resetBut").click();
            $('#index_iframe').contents().find("#subBut").click();
            closeDialog();
          }else{
            alert(data.msg);
          }
        }
      });
    }
  }
</script>