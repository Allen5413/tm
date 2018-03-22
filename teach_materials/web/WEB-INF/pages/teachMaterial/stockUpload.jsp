<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <%@ include file="/common/meta.jsp"%>
  <%@ include file="/common/taglibs.jsp"%>
</head>
<body>
<div class="main-content">
  <form id="addForm" name="addForm" enctype="multipart/form-data" method="post">
    <ul class="create_info_list">
      <li>
        方式：<input type="radio" name="types" value="0" checked />累加
        <input type="radio" name="types" value="1" />替换
      </li>
      <li>
        上传库存表Excel：<input type="file" id="file" name="file" />
      </li>
    </ul>
  </form>
</div>
</body>
</html>
<script>
  function sub(){
    $("#addForm").ajaxSubmit({
      url : "${pageContext.request.contextPath}/uploadStock/stockUpload.htm",
      dataType : 'json',
      success : function(result, statusText){
        if(0 == result.state) {
          if("" == result.str) {
            alert("上传成功！");
            Dialog.close();
          }else {
            alert(result.str);
          }
        }
        if(1 == result.state) {
          alert(result.msg);
        }
      }
    });
  }
</script>
