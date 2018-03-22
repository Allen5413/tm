<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <title>在线缴费系统</title>
  <%@ include file="/common/studentTaglibs.jsp"%>
</head>
<body onload="findNotify()">
<div class="header-view">
  <div class="banner"><img src="${pageContext.request.contextPath}/student/img/banner.png" />
    <div class="contact">023-68217166</div>
  </div>
</div>
<div class="main-view">
  <div class="wx-pay-detail">
    <div class="title"><img src="${pageContext.request.contextPath}/student/img/ico-2.png" /></div>
    <div class="wx-code">
      <div class="code-pic"><img src="${pageContext.request.contextPath}${imgurl}" /></div>
      <div class="tips-red">
        <p>注：${codeUrl}</p>
        <p>1、该二维码有效期为两个小时，请尽快扫描支付！</p>
      </div>
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
  function findNotify(){
    setInterval(function(){
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/wxNotify/findByOrderCode.htm",
        data:{code:"${orderCode}"},
        async: false,
        success: function(data) {
          if(data.state == 0 && data.notifyList.length > 0){
            for(var i=0; i<data.notifyList.length; i++){
              var notify = data.notifyList[i];
              if(notify.returnCode == "SUCCESS"){
                location.href = "${pageContext.request.contextPath}/wxPay/openPayResult.htm?money=${param.money}&code=${param.code}";
              }
            }
          }
        }
      });
    }, 5000);
  }
</script>
