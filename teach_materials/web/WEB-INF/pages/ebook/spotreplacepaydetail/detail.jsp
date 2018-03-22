<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul data-am-widget="gallery" class="am-gallery am-avg-sm-2 am-avg-md-3 am-avg-lg-4 am-gallery-imgbordered" data-am-gallery="{ pureview: true }" >
  <c:forTokens items="${spotReplacePay.imagUrl}" delims="," var="imagUrl">
    <li>
      <div class="am-gallery-item">
        <a href="http://xiwang.attop.com${pageContext.request.contextPath}${imagUrl}" class="" target="_blank">
          <img src="${pageContext.request.contextPath}${imagUrl}" style="width: 200px; height: 200px;"/>
        </a>
      </div>
    </li>
  </c:forTokens>
</ul>
<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <tr class="am-primary">
    <th style="width: 100px;">
      <a href="#" onclick="app.checkAll('ids');">全选</a>|
      <a href="#" onclick="app.checkNAll('ids');">反选</a>
    </th>
    <th style="width: 50px">序号</th>
    <th>学号</th>
    <th>学生姓名</th>
    <th>交费金额</th>
  </tr>
  <c:if test="${empty list}">
    <tr>
      <td colspan="8" align="center" style="color: red;">没有找到相关数据</td>
    </tr>
  </c:if>
  <c:forEach var="json" items="${list}" varStatus="status">
    <tr>
      <td align="center">
        <input type="checkbox" name="ids" value="" onclick="changeColor(this)">
      </td>
      <td align="center">${status.index+1}</td>
      <td>${json.code}</td>
      <td>${json.name}</td>
      <td>${json.money}</td>
    </tr>
  </c:forEach>
</table>

<form id="hidForm" name="hidForm" method="post" class="am-form">
  <input type="hidden" name="id" value="${spotReplacePay.id}" />
  <input type="hidden" id="state2" name="state" value="" />
  <div class="am-g am-margin-top">
    <div class="am-u-sm-2 am-text-right"><label >到账时间：</label></div>
    <div class="am-u-sm-2">
      <input type="hidden" id="arrivalTime" name="arrivalTime"/>
      <input type="text" id="wdate" name="wdate" class="Wdate" style="width: 110px;" onfocus="WdatePicker({readOnly:true})"/>
    </div>
    <div class="am-u-sm-7">*必填</div>
  </div>
  <div class="am-g am-margin-top">
    <div class="am-u-sm-2 am-text-right"><label >到账类型：</label></div>
    <div class="am-u-sm-4" style="float:left">
      <div class="am-btn-group" data-am-button="">
        <label class="am-btn am-btn-primary am-btn-s">
          <input type="radio" name="payType" value="1" checked> 转账
        </label>
        <label class="am-btn am-btn-primary am-btn-s">
          <input type="radio" name="payType" value="0"> 网银
        </label>
        <label class="am-btn am-btn-primary am-btn-s">
          <input type="radio" name="payType" value="2"> 现金
        </label>
        <label class="am-btn am-btn-primary am-btn-s">
          <input type="radio" name="payType" value="3"> 其它
        </label>
      </div>
    </div>
  </div>
  <div class="am-g am-margin-top">
    <div class="am-u-sm-2 am-text-right"><label >备注：</label></div>
    <div class="am-u-sm-6" style="float:left">
      <textarea rows="6" cols="50" name="remark" placeholder="输入备注"></textarea>
    </div>
  </div>
</form>
<script>
  function downStudentTm(){
    var chk_value = "";
    $('input[name="ids"]:checked').each(function(){
      chk_value += $(this).val()+",";
    });
    if(chk_value.length < 1){
      app.msg("请选择要下载的学生", 1);
      return false;
    }
    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/centerPay/downStudentOrderTm.htm?codeMoneys="+chk_value.substring(0, chk_value.length-1),
      data:{},
      async: false,
      success: function(data) {
        open("${pageContext.request.contextPath}"+data);
      }
    });
  }
</script>


