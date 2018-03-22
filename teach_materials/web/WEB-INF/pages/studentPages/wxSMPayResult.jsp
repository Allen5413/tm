<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <title>在线缴费系统</title>
  <%@ include file="/common/studentTaglibs.jsp"%>
</head>
<body>
<div class="header-view">
  <div class="banner"><img src="${pageContext.request.contextPath}/student/img/banner.png" />
    <div class="contact">023-68217166</div>
  </div>
</div>
<div class="main-view">
  <div class="view-detail">
    <div class="title">支付情况</div>
    <div class="tips-success">
      <div class="text-bar">
        <form id="editForm" name="editForm" action="${pageContext.request.contextPath}/editStudentForIsSendStudentByCode/editor.htm" method="post">
          您已成功缴教材费 <span class="num-f24">${param.money}</span> 元<br />
          请核对您的收件人手机、地址、邮编等信息，以方便我们邮寄给您。<br />
          <input type="hidden" name="code" value="${student.code}" />
          <input type="hidden" name="isSendStudent" value="1"/>
          收件人手机：<input type="text" id="mobile" name="mobile" value="${student.sendPhone}" /><br />
          收件人邮编：<input type="text" id="postalCode" name="postalCode" value="${student.sendZipCode}" /><br />
          <c:set var="addressArray" value="${fn:split(student.sendAddress, '|')}"/>
          <c:set var="addressArrayLength" value="${fn:length(addressArray)}"/>
          <c:if test="${addressArrayLength == 3}">
            <c:set var="province" value="${addressArray[0]}"/>
            <c:set var="city" value="${addressArray[1]}"/>
            <c:set var="sendAddress" value="${addressArray[2]}"/>
          </c:if>
          <c:if test="${addressArrayLength == 1}">
            <c:set var="sendAddress" value="${addressArray[0]}"/>
          </c:if>
          收件人地址：<input type="text" id="province" name="province" value="${province}" placeholder="输入省市" style="width: 70px;" />&nbsp;
          <input type="text" id="city" name="city" value="${city}" placeholder="输入市区" style="width: 70px; " />&nbsp;
          <input type="text" id="sendAddress" name="sendAddress" value="${sendAddress}" placeholder="输入详细地址" style="width: 160px; " />
          <p class="btn-next"><a class="btn-com" href="#" onclick="isSendStudent()">提交保存</a></p>
        </form>
      </div>

      <table style="width: 100%">
        <tr>
          <td style="text-align: center"><img src="${pageContext.request.contextPath}/images/xwgzh.jpg" /></td>
        </tr>
        <tr>
          <td style="text-align: center">扫描二维码，关注并用身份证号绑定，查询更多教材财务，教材订单信息</td>
        </tr>
      </table>
    </div>
  </div>
</div>
<div class="footer-view">
  <div class="copyright">
    <p>版权所有：西南大学网络与继续教育学院    渝ICP备06005063号-2</p>
    <p>地址：重庆市北碚区天生路2号西南大学网络与继续教育学院    邮编：400715 </p>
  </div>
</div>
</body>
</html>
<script>
  function isSendStudent(){
      var mobile = $("#mobile").val().trim();
      var postalCode = $("#postalCode").val().trim();
      var province = $("#province").val().trim();
      var city = $("#city").val().trim();
      var sendAddress = $("#sendAddress").val().trim();
      if(!vaild.vaildPhone(mobile)){
        app.msg("请输入正确的手机号码", 1);
        return false;
      }
      if(postalCode == ""){
        app.msg("请输入邮政编码", 1);
        return false;
      }
      if(province == "" || province.length < 1){
        app.msg("请输入省市", 1);
        return false;
      }
      if(city == "" || city.length < 1){
        app.msg("请输入市区", 1);
        return false;
      }
      if(sendAddress == "" || sendAddress.length < 1){
        app.msg("请输入详细地址", 1);
        return false;
      }
      $("#sendAddress").val(province+"|"+city+"|"+sendAddress);
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/editStudentForIsSendStudentByCode/editor.htm",
        data:$('#editForm').serialize(),
        async: false,
        success: function(data) {
          if(data.state == 0){
            app.msg('提交成功', 0);
            setTimeout(function(){history.go(0);}, 500);
          }else{
            app.msg(data.msg, 1);
          }
        }
      });
  }
</script>
