<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="main-content">--%>
<%--<form id="addForm" name="addForm" method="post">--%>
  <%--<div class="main-content">--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<input type="hidden" id="spotCode" value="${spot.code}" />--%>
        <%--<label class="lab_80" style="width: 120px;">学习中心：</label>[${spot.code}]${spot.name}--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80" style="width: 120px;">支付金额：</label>--%>
        <%--<input type="text" class="input_240" id="money" name="money" style="width: 110px;"/>元--%>
        <%--<span style="color:#ff0000">*</span>--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80" style="width: 120px;">上传汇款凭证：</label>--%>
        <%--<input name="payImg" id="payImg" type="file"/><font color="red">图片类型只能JPG,JPEG,PNG，大小不能超过400kb</font>--%>
        <%--<a href="#" onclick="goOnFile()" style="color: #0092DC">继续上传</a>--%>
        <%--<div id="goOnFileDiv"></div>--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80" style="width: 120px;">汇款方式：</label>--%>
        <%--<select id="payWay" name="payWay">--%>
          <%--<option value="1">其他</option>--%>
          <%--<option value="2">工商银行</option>--%>
          <%--<option value="3">支付宝</option>--%>
        <%--</select>--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80 left">--%>
          <%--<a class="com_btn_b" href="#" onclick="sub()"><span>提&nbsp;交</span></a>--%>
        <%--</label>--%>
      <%--</li>--%>
    <%--</ul>--%>
  <%--</div>--%>
<%--</form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--$(document).ready(function(){--%>
    <%--var state = "${state}";--%>
    <%--if(state == "0"){--%>
      <%--Dialog.alert("操作成功, 请等待审核！");--%>
      <%--parentDialog.close();--%>
    <%--}--%>
    <%--if(state == "1"){--%>
      <%--Dialog.alert("${msg}");--%>
    <%--}--%>
  <%--});--%>

  <%--function sub(){--%>
    <%--if(!vaild.vaildMoney($("#money").val())){--%>
      <%--Dialog.alert("请输入正确的金额");--%>
    <%--}else{--%>
      <%--var arrId=new Array();--%>
      <%--var i=0;--%>
      <%--$("[name = payImg]").each(function(){--%>
        <%--arrId[i] = $(this).attr("id");--%>
        <%--i++;--%>
      <%--});--%>
      <%--$.ajaxFileUpload({--%>
        <%--url:"${pageContext.request.contextPath}/addSpotEP/spotEPAdd.htm",--%>
        <%--secureuri: false, //是否需要安全协议，一般设置为false--%>
        <%--fileElementId: arrId, //文件上传域的ID--%>
        <%--dataType: 'json', //返回值类型 一般设置为json--%>
        <%--type : 'post',--%>
        <%--data : {"money":$("#money").val(), "spotCode":"${spot.code}", "payWay":$("#payWay").val()}--%>
      <%--});--%>
    <%--}--%>
  <%--}--%>

  <%--var num = 1;--%>
  <%--function goOnFile(){--%>
    <%--num++;--%>
    <%--var addHtml = "<div><label class='lab_80' style='width: 121px;'></label>" +--%>
            <%--"<input id='payImg"+num+"' name='payImg' type='file'/>" +--%>
            <%--"<a href='#' onclick='delFile(this)' style='color: #0092DC'>删除</a></div>";--%>
    <%--$("#goOnFileDiv").append(addHtml)--%>
  <%--}--%>

  <%--function delFile(obj){--%>
    <%--$(obj).parent().remove();--%>
  <%--}--%>
<%--</script>--%>

<form class="am-form" id="addForm" name="addForm" method="post" enctype="multipart/form-data">
  <input type="hidden" id="spotCode" name="spotCode" value="${spot.code}" />
  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >学习中心：</label></div>
    <div class="am-u-sm-6" style="float:left">[${spot.code}]${spot.name}</div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >支付金额：</label></div>
    <div class="am-u-sm-5" style="float:left">
      <input class="am-input-sm" type="number" placeholder="输入支付金额" min="0" required id="money" name="money" style="width: 130px" />
    </div>
    <div class="am-u-sm-4">*必填，单位“元”</div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >上传汇款凭证：</label></div>
    <div class="am-u-sm-5" style="float:left">
      <div class="am-form-group am-form-file">
        <button type="button" class="am-btn am-btn-primary am-btn-sm"><i class="am-icon-cloud-upload"></i> 选择要上传的照片</button>
        <input id="payImg" type="file" multiple name="payImg">
      </div>
      <button type="button" class="am-btn am-btn-primary" onClick="goOnFile()"><span class="am-icon-plus"></span> 继续上传</button>
    </div>
    <div class="am-u-sm-4">*图片类型只能JPG,JPEG,PNG，大小不能超过400kb</div>
  </div>

  <div id="goOnFileDiv"></div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >汇款方式：</label></div>
    <div class="am-u-sm-5">
      <div class="am-btn-group" data-am-button="">
        <label class="am-btn am-btn-primary am-btn-s">
          <input type="radio" name="payWay" value="2"> 工商银行
        </label>
        <label class="am-btn am-btn-primary am-btn-s">
          <input type="radio" name="payWay" value="3"> 支付宝
        </label>
        <label class="am-btn am-btn-primary am-btn-s">
          <input type="radio" name="payWay" value="1"> 其它
        </label>
      </div>
    </div>
    <div class="am-u-sm-4">*必选</div>
  </div>
</form>
<script>

  var num = 1;
  function goOnFile(){
    num++;
    var addHtml = "<div class=\"am-g am-margin-top\">" +
                    "<div class=\"am-u-sm-3 am-text-right\"><label >上传汇款凭证：</label></div>" +
                    "<div class=\"am-u-sm-5\">" +
                      "<div class=\"am-form-group am-form-file\">" +
                        "<button type=\"button\" class=\"am-btn am-btn-primary am-btn-sm\"><i class=\"am-icon-cloud-upload\"></i> 选择要上传的照片</button>" +
                        "<input id='payImg"+num+"' name='payImg' type='file' multiple />" +
                      "</div>" +
                    "</div>" +
                    "<div class=\"am-u-sm-4\"><button type=\"button\" class=\"am-btn am-btn-primary\" onClick=\"delFile(this)\"><span class=\"am-icon-trash-o\"></span> 删除</button></div>" +
                  "</div>";
    $("#goOnFileDiv").append(addHtml);
  }

  function delFile(obj){
    $(obj).parent().parent().remove();
  }
</script>