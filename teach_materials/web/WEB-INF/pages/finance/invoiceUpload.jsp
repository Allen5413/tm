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
  <%--<form id="addForm" name="addForm" enctype="multipart/form-data" method="post">--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--上传发票Excel：<input type="file" id="file" name="file" />--%>
      <%--</li>--%>
    <%--</ul>--%>
  <%--</form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function sub(obj){--%>
    <%--$("#addForm").ajaxSubmit({--%>
      <%--url : "${pageContext.request.contextPath}/uploadInvoice/invoicUpload.htm",--%>
      <%--dataType : 'json',--%>
      <%--success : function(result, statusText){--%>
        <%--if(0 == result.state) {--%>
          <%--$(obj).click();--%>
          <%--alert("上传成功！");--%>
          <%--Dialog.close();--%>
        <%--}--%>
        <%--if(1 == result.state) {--%>
          <%--alert(result.msg);--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>
  <%--}--%>
<%--</script>--%>



<form class="am-form" id="uploadForm" name="uploadForm" method="post" enctype="multipart/form-data">
  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >上传发票Excel：</label></div>
    <div class="am-u-sm-5" style="float:left">
      <div class="am-form-group am-form-file">
        <button type="button" class="am-btn am-btn-primary am-btn-sm"><i class="am-icon-cloud-upload"></i> 选择要上传的Excel</button>
        <input id="file" type="file" multiple name="file">
      </div>
    </div>
    <div class="am-u-sm-4"></div>
  </div>
</form>