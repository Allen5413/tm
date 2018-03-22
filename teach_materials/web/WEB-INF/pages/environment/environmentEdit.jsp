<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="editForm" action="/editEnvironment/environmentEdit.htm" method="post">
  <input type="hidden" name="id" value="${environment.id}" />
  <input type="hidden" name="version" value="${environment.version}" />
  <div class="main-content">
    <ul class="create_info_list">
      <li>
        <label class="lab_80">变量名称：</label>
       <input type="text" class="input_240" name="name" value="${environment.name}">
        </select>
      </li>
      <li>
        <label class="lab_80">变量编号：</label>
        <input type="text" class="input_240" name="code" value="${environment.code}"/>
      </li>
      <li>
        <label class="lab_80">变量的值：</label>
        <input type="text" class="input_240" name="value" value="${environment.value}"/>
      </li>
      <li>
        <label class="lab_80 left">
          <a class="com_btn_b" href="#" onclick="sub()"><span>提&nbsp;交</span></a>
        </label>
      </li>
    </ul>
  </div>
</form>
<script>
  $(function(){
    //对数据的检测
    $("#editForm").validate({
      rules : {
        name : {
          required : true
        },
        code : {
          required : true
        },
        value : {
          required : true
        }
      },
      messages : {
        name : {
          required : '请输入环境变量名称'
        },
        code : {
          required : '请输入环境变量编号'
        },
        value : {
          required : '请输入环境变量值'
        }
      }
    });
  });

  function sub(){
    if($("#editForm").valid()){
      $.ajax({
        cache: true,
        type: "POST",
        url:"/editEnvironment/environmentEdit.htm",
        data:$('#editForm').serialize(),
        async: false,
        success: function(data) {
          if(data.state == 0){
            alert("操作成功！");
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