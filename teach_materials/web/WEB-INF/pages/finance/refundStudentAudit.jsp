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
  <%--<c:if test="${pageContext.request.contextPath == ''}">--%>
    <%--<c:forTokens items="${refund.refundApplyImg}" delims="," var="imagUrl">--%>
      <%--<img onclick="openImg('${imagUrl}')" src="${imagUrl}" style="width: 395px; height: 200px;" />--%>
    <%--</c:forTokens>--%>
  <%--</c:if>--%>
  <%--<c:if test="${pageContext.request.contextPath != ''}">--%>
    <%--<c:forTokens items="${refund.refundApplyImg}" delims="," var="imagUrl">--%>
      <%--<img onclick="openImg('${pageContext.request.contextPath}${imagUrl}')" src="${pageContext.request.contextPath}${imagUrl}" style="width: 395px; height: 200px;" />--%>
    <%--</c:forTokens>--%>
  <%--</c:if>--%>
<%--</div>--%>
<%--<div id="searchStudent" class="main-content">--%>
  <%--<form id="addForm" name="addForm" method="post" enctype="multipart/form-data">--%>
    <%--<input type="hidden" name="code" value="${param.code}" />--%>
    <%--<input type="hidden" name="spotCode" value="${param.spotCode}" />--%>
    <%--<input type="hidden" id="idDetails" name="idDetails" />--%>
    <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
      <%--<tr>--%>
        <%--<th style="width: 6%;">--%>
          <%--<a href="#" onclick="checkAll('cb')" style="color: #0092DC">全选</a>|--%>
          <%--<a href="#" onclick="checkNall('cb')" style="color: #0092DC">反选</a>--%>
        <%--</th>--%>
        <%--<th style="width: 8%;">学号</th>--%>
        <%--<th style="width: 6%;">姓名</th>--%>
        <%--<th style="width: 18%;">专业</th>--%>
        <%--<th style="width: 5%;">层次</th>--%>
        <%--<th style="width: 5%;">状态</th>--%>
        <%--<th style="width: 5%;">退款金额</th>--%>
        <%--<th style="width: 26%;">退款说明</th>--%>
        <%--<th style="width: 19%;">审核说明</th>--%>
      <%--</tr>--%>
      <%--<c:if test="${empty list}">--%>
        <%--<tr>--%>
          <%--<td colspan="99" align="center" style="color: red;">没有找到相关数据</td>--%>
        <%--</tr>--%>
      <%--</c:if>--%>
      <%--<c:forEach var="map" items="${list}" varStatus="status">--%>
        <%--<tr>--%>
          <%--<td align="center">--%>
            <%--<input type="checkbox" name="cb" value="${map.id}">--%>
          <%--</td>--%>
          <%--<td>${map.studentCode}</td>--%>
          <%--<td>${map.name}</td>--%>
          <%--<td>${map.specName}</td>--%>
          <%--<td>${map.levelName}</td>--%>
          <%--<td>--%>
            <%--<c:choose>--%>
              <%--<c:when test="${map.state == 0}">待审核</c:when>--%>
              <%--<c:when test="${map.state == 1}">通过</c:when>--%>
              <%--<c:when test="${map.state == 2}">未通过</c:when>--%>
              <%--<c:otherwise>未知</c:otherwise>--%>
            <%--</c:choose>--%>
          <%--</td>--%>
          <%--<td>${map.money}</td>--%>
          <%--<td>${map.refundDetail}</td>--%>
          <%--<td>--%>
            <%--<input type="text" id="detail_${map.id}" name="detail" style="width: 200px;">--%>
          <%--</td>--%>
        <%--</tr>--%>
      <%--</c:forEach>--%>
    <%--</table>--%>
    <%--审核说明：<input type="text" id="detail">--%>
    <%--<a class="com_btn_b" href="#" onclick="enterDetail()"><span>确定</span></a>--%>
    <%--<br>--%>
    <%--退款凭证：--%>
    <%--<input name="auditImg" id="auditImg" type="file"/>--%>
    <%--<a href="#" onclick="goOnFile()" style="color: #0092DC">继续上传</a>--%>
    <%--<div id="goOnFileDiv"></div>--%>
  <%--</form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function enterDetail(){--%>
    <%--var detail = $("#detail").val().trim();--%>
    <%--$("[name=cb]").each(function() {--%>
      <%--if (!$(this).prop("checked")) {--%>
        <%--var id = $(this).val();--%>
        <%--$("#detail_"+id).val(detail);--%>
      <%--}--%>
    <%--});--%>
  <%--}--%>

  <%--function openImg(url){--%>
    <%--open("http://xiwang.attop.com"+url);--%>
  <%--}--%>

  <%--var num = 1;--%>
  <%--function goOnFile(){--%>
    <%--num++;--%>
    <%--var i=0;--%>
    <%--$("[name=auditImg]").each(function(){--%>
      <%--i++;--%>
    <%--});--%>
    <%--if(i > 4){--%>
      <%--alert("最多只能上传5张图片！");--%>
    <%--}else {--%>
      <%--var addHtml = "<div><label class='lab_80' style='width: 121px;'></label>" +--%>
              <%--"<input id='auditImg" + num + "' name='auditImg' type='file'/>" +--%>
              <%--"<a href='#' onclick='delFile(this)' style='color: #0092DC'>删除</a></div>";--%>
      <%--$("#goOnFileDiv").append(addHtml)--%>
    <%--}--%>
  <%--}--%>

  <%--function delFile(obj){--%>
    <%--$(obj).parent().remove();--%>
  <%--}--%>

  <%--function sub(obj){--%>
    <%--var vaildDetail = true;--%>
    <%--var isChecked = false;--%>
    <%--$("[name=cb]").each(function() {--%>
      <%--var id = $(this).val();--%>
      <%--if (!$(this).prop("checked")) {--%>
        <%--if($("#detail_"+id).val().trim() == ""){--%>
          <%--vaildDetail = false;--%>
        <%--}--%>
      <%--}else{--%>
        <%--isChecked = true;--%>
      <%--}--%>
    <%--});--%>
    <%--var vaildImg = false;--%>
    <%--$("[name=auditImg]").each(function() {--%>
      <%--if (!$(this).val() == "") {--%>
        <%--vaildImg = true;--%>
      <%--}--%>
    <%--});--%>
    <%--if(!vaildDetail){--%>
      <%--alert("请为不能退款的学生填写说明！");--%>
      <%--return false;--%>
    <%--}--%>
    <%--else if(!vaildImg && isChecked){--%>
      <%--alert("请上传退款凭证！");--%>
      <%--return false;--%>
    <%--}else{--%>
      <%--if(confirm("您确定要按此结果审核，一旦审核不能更改哟？")){--%>
        <%--var arrId=new Array();--%>
        <%--var i=0;--%>
        <%--$("[name = auditImg]").each(function(){--%>
          <%--arrId[i] = $(this).attr("id");--%>
          <%--i++;--%>
        <%--});--%>

        <%--var idDetails = new Array();--%>
        <%--i=0;--%>
        <%--$("[name=cb]").each(function(){--%>
          <%--var id = $(this).val();--%>
          <%--var detail = $("#detail_"+id).val();--%>
          <%--if(detail.indexOf("_") >= 0){--%>
            <%--alert("审核说明里面不能有'_'等特殊字符");--%>
            <%--return false;--%>
          <%--}--%>
          <%--idDetails[i] = id+"_"+detail;--%>
          <%--i++;--%>
        <%--});--%>
        <%--$("#idDetails").val(idDetails);--%>

        <%--$("#addForm").ajaxSubmit({--%>
          <%--url : "${pageContext.request.contextPath}/auditRefundStudent/audit.htm",--%>
          <%--dataType : 'json',--%>
          <%--success : function(result, statusText){--%>
            <%--if(0 == result.state) {--%>
              <%--$(obj).click();--%>
              <%--alert("审核成功！");--%>
              <%--Dialog.close();--%>
            <%--}--%>
            <%--if(1 == result.state) {--%>
              <%--alert(result.msg);--%>
            <%--}--%>
          <%--}--%>
        <%--});--%>
      <%--}--%>
    <%--}--%>
  <%--}--%>
<%--</script>--%>


<div class="am-alert am-alert-warning" data-am-alert>
  <button type="button" class="am-close">&times;</button>
  <h3>审核注意事项</h3>
  <ul>
    <li>请先勾选允许退款得学生，如果不勾选，就视为审核不通过处理</li>
    <li>在快捷审核说明里填写说明，然后点确定按钮，这时，没有勾选的学生就会在后面的审核说明中自动填上说明</li>
    <li>上传退款凭证</li>
    <li>确定无误后提交审核结果</li>
  </ul>
</div>
<div class="am-panel am-panel-primary" style=" width:99.5%; margin-left:2px; text-align:left; border: 0;">
  <div class="am-panel-hd">申请退款上传凭证</div>
  <ul data-am-widget="gallery" class="am-gallery am-avg-sm-2 am-avg-md-3 am-avg-lg-4 am-gallery-imgbordered" data-am-gallery="{ pureview: true }" >
    <c:forTokens items="${refund.refundApplyImg}" delims="," var="imagUrl">
      <li>
        <div class="am-gallery-item">
          <a href="${pageContext.request.contextPath}${imagUrl}" target="_blank">
            <img src="${pageContext.request.contextPath}${imagUrl}" style="width: 395px; height: 200px;"/>
          </a>
        </div>
      </li>
    </c:forTokens>
  </ul>

  <div class="am-panel-hd">退款明细</div>
  <form id="addForm" name="addForm" method="post" enctype="multipart/form-data">
    <input type="hidden" name="code" value="${param.code}" />
    <input type="hidden" name="spotCode" value="${param.spotCode}" />
    <input type="hidden" id="idDetails" name="idDetails" />
    <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
      <tr>
        <td colspan="999">
          快捷审核说明：<input type="text" id="detail">&nbsp;&nbsp;&nbsp;&nbsp;
          <button class="am-btn am-btn-primary am-btn-sm" type="button" onclick="enterDetail()"><span class="am-icon-plus"></span> 确定</button>
        </td>
      </tr>
      <tr class="am-primary">
        <th style="width:8%">
          <a href="#" onClick="app.checkAll('cb')">全选</a>|<a href="#" onClick="app.checkNAll('cb')">反选</a>
        </th>
        <th style="width:10%">学号</th>
        <th style="width:6%">姓名</th>
        <th style="width:6%">层次</th>
        <th style="width:15%">专业</th>
        <th style="width:7%">状态</th>
        <th style="width:8%">退款金额</th>
        <th style="width:15%">退款说明</th>
        <th>审核说明</th>
      </tr>
      <c:if test="${empty list}">
        <tr>
          <td colspan="99" align="center" style="color: red;">没有找到相关数据</td>
        </tr>
      </c:if>
      <c:forEach var="map" items="${list}" varStatus="status">
        <tr>
          <td align="center">
            <label class="am-checkbox am-secondary" style="margin-top:5px; margin-left:24px;">
              <input type="checkbox" name="cb" value="${map.id}" data-am-ucheck>
            </label>
          </td>
          <td>${map.studentCode}</td>
          <td>${map.name}</td>
          <td>${map.specName}</td>
          <td>${map.levelName}</td>
          <td>
            <c:choose>
              <c:when test="${map.state == 0}">待审核</c:when>
              <c:when test="${map.state == 1}">通过</c:when>
              <c:when test="${map.state == 2}">未通过</c:when>
              <c:otherwise>未知</c:otherwise>
            </c:choose>
          </td>
          <td>${map.money}</td>
          <td>${map.refundDetail}</td>
          <td>
            <input type="text" id="detail_${map.id}" name="detail" style="width: 200px;">
          </td>
        </tr>
      </c:forEach>
    </table>
    <div class="am-g am-margin-top">
      <div class="am-u-sm-2 am-text-right"><label >上传退款凭证：</label></div>
      <div class="am-u-sm-2" style="float:left">
        <div class="am-form-group am-form-file">
          <button type="button" class="am-btn am-btn-primary am-btn-sm"><i class="am-icon-cloud-upload"></i> 选择要上传的照片</button>
          <input id="auditImg" type="file" name="auditImg" multiple>
        </div>
        <button type="button" class="am-btn am-btn-primary" onClick="goOnFile()"><span class="am-icon-plus"></span> 继续上传</button>
      </div>
      <div class="am-u-sm-8">*必填 片类型只能JPG,JPEG,PNG，大小不能超过400kb</div>
    </div>
    <div id="goOnFileDiv"></div>
  </form>
</div>
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
              "<div class=\"am-form-group am-form-file\">" +
              "<button type=\"button\" class=\"am-btn am-btn-primary am-btn-sm\"><i class=\"am-icon-cloud-upload\"></i> 选择要上传的照片</button>" +
              "<input id='auditImg" + num + "' name='auditImg' type='file' multiple />" +
              "</div>" +
              "</div>" +
              "<div class=\"am-u-sm-4\"><button type=\"button\" class=\"am-btn am-btn-primary\" onClick=\"delFile(this)\"><span class=\"am-icon-trash-o\"></span> 删除</button></div>" +
              "</div>";
      $("#goOnFileDiv").append(addHtml);
    }
  }

  function enterDetail(){
    var detail = $("#detail").val().trim();
    $("[name=cb]").each(function() {
      if (!$(this).prop("checked")) {
        var id = $(this).val();
        $("#detail_"+id).val(detail);
      }
    });
  }

  function delFile(obj){
    $(obj).parent().parent().remove();
  }
</script>

