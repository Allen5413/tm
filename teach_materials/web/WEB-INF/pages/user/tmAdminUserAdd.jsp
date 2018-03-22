<%@ page contentType="text/html;charset=UTF-8"%>

<div align="center">
  <form id="addForm" name="addForm" method="post">
    <input type="hidden" name="type" value="0" />
    <div class="main-content">
      <ul class="create_info_list">
        <li>
          <label class="lab_80">登录名称：</label>
          <input type="text" class="input_240" name="loginName" maxlength="50" />
        </li>
        <li>
          <label class="lab_80">姓名：</label>
          <input type="text" class="input_240" name="name" maxlength="50" />
        </li>
        <li>
          <label class="lab_80">手机：</label>
          <input type="text" class="input_240" name="cellPhone" maxlength="50" />
        </li>
        <li>
          <label class="lab_80">状态：</label>
          <input type="radio" name="state" value="0" checked />启用
          <input type="radio" name="state" value="1" />停用
        </li>
        <li>
          <label class="lab_80 left">说明：</label>
          <textarea class="textArea left" name="remark" maxlength="500" style="width: 245px;"></textarea>
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
        loginName : {
          required : true
        },
        name : {
          required : true
        },
        cellPhone : {
          required : true
        }
      },
      messages : {
        loginName : {
          required : '请输入用户登录名'
        },
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
    if($("#addForm").valid()){
      $.ajax({
        cache: true,
        type: "POST",
        url:"/addUser/userAdd.htm",
        data:$('#addForm').serialize(),
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