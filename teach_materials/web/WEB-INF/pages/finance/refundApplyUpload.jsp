<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="main-content">--%>
  <%--<form id="addForm" name="addForm" method="post" enctype="multipart/form-data">--%>
    <%--<input type="hidden" name="code" value="${param.code}" >--%>
    <%--<div class="main-content">--%>
      <%--<ul class="create_info_list">--%>
        <%--<li>--%>
          <%--<font color="red">图片类型只能JPG,JPEG,PNG，大小不能超过400kb</font>--%>
          <%--<br>--%>
          <%--<label class="lab_80" style="width: 120px;">上传退款凭证：</label>--%>
          <%--<input name="applyImg" id="applyImg" type="file"/>--%>
          <%--<a href="#" onclick="goOnFile()" style="color: #0092DC">继续上传</a>--%>
          <%--<div id="goOnFileDiv"></div>--%>
        <%--</li>--%>
      <%--</ul>--%>
    <%--</div>--%>
  <%--</form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function sub(obj){--%>
    <%--$("#addForm").ajaxSubmit({--%>
      <%--url : "${pageContext.request.contextPath}/uploadApplyImgRefund/applyUpload.htm",--%>
      <%--dataType : 'json',--%>
      <%--success : function(result, statusText){--%>
        <%--if(0 == result.state) {--%>
          <%--$(obj).click();--%>
          <%--alert("上传成功，请等待审核！");--%>
          <%--Dialog.close();--%>
        <%--}--%>
        <%--if(1 == result.state) {--%>
          <%--alert(result.msg);--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>
  <%--}--%>

  <%--var num = 1;--%>
  <%--function goOnFile(){--%>
    <%--num++;--%>
    <%--var i=0;--%>
    <%--$("[name=applyImg]").each(function(){--%>
      <%--i++;--%>
    <%--});--%>
    <%--if(i > 4){--%>
      <%--alert("最多只能上传5张图片！");--%>
    <%--}else {--%>
      <%--var addHtml = "<div><label class='lab_80' style='width: 121px;'></label>" +--%>
              <%--"<input id='applyImg" + num + "' name='applyImg' type='file'/>" +--%>
              <%--"<a href='#' onclick='delFile(this)' style='color: #0092DC'>删除</a></div>";--%>
      <%--$("#goOnFileDiv").append(addHtml)--%>
    <%--}--%>
  <%--}--%>

  <%--function delFile(obj){--%>
    <%--$(obj).parent().remove();--%>
  <%--}--%>
<%--</script>--%>


<form class="am-form" id="addForm" name="addForm" method="post" enctype="multipart/form-data">
  <input type="hidden" name="code" value="${param.code}" >
  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >上传退款凭证：</label></div>
    <div class="am-u-sm-4" style="float:left">
      <input id="applyImg" type="file" multiple name="applyImg" value="选择要上传的照片">
      <button type="button" class="am-btn am-btn-primary" onClick="goOnFile()"><span class="am-icon-plus"></span> 继续上传</button>
    </div>
    <div class="am-u-sm-5">*图片类型只能JPG,JPEG,PNG，大小不能超过400kb</div>
  </div>

  <div id="goOnFileDiv"></div>
</form>
<script>
  var num = 1;
  function goOnFile() {
    num++;
    var i=0;
    $("[name=applyImg]").each(function(){
      i++;
    });
    if(i > 4){
      app.msg("最多只能上传5张图片！", 1);
      return;
    }else {
      var addHtml = "<div class=\"am-g am-margin-top\">" +
              "<div class=\"am-u-sm-3 am-text-right\"><label >上传汇款凭证：</label></div>" +
              "<div class=\"am-u-sm-5\">" +
              "<input id='applyImg" + num + "' name='applyImg' type='file' multiple value='选择要上传的照片' />" +
              "</div>" +
              "<div class=\"am-u-sm-4\"><button type=\"button\" class=\"am-btn am-btn-primary\" onClick=\"delFile(this)\"><span class=\"am-icon-trash-o\"></span> 删除</button></div>" +
              "</div>";
      $("#goOnFileDiv").append(addHtml);
    }
  }

  function delFile(obj){
    $(obj).parent().parent().remove();
  }
</script>