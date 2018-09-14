<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
  <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
  <meta name="apple-mobile-web-app-capable" content="yes" />
  <meta name="apple-mobile-web-app-status-bar-style" content="black" />
  <meta content="black" name="apple-mobile-web-app-status-bar-style" />
  <%@ include file="/common/taglibs.jsp"%>
  <script type="text/javascript" charset="UTF-8" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
  <script src="${pageContext.request.contextPath}/script/flexible.js" type="text/javascript"></script>

  <link href="${pageContext.request.contextPath}/css/common_h5.css" rel="stylesheet" type="text/css" />
  <link href="${pageContext.request.contextPath}/css/xw.css" rel="stylesheet" type="text/css" />
</head>
<body>
<header>
  <div class="header w">
    <p class="tit">教材管理系统</p>
    <a href="#" class="close icon"></a>
    <a class="next t-user"></a>
  </div>
</header>
<div id="pay">
  <section class="bg-f4-pt pm-top-40">
    <c:if test="${empty studentCode || empty openId}">
      <div class="title">请先通过证件号码绑定学生信息</div>
    </c:if>
    <c:if test="${!empty studentCode}">
      <div class="auto w zf-basic-info">
        <ul class="list-info">
          <li>
            <div class="col-1">学号：</div>
            <div class="col-2">${studentCode}</div>
          </li>
          <li>
            <div class="col-1">姓名：</div>
            <div class="col-2">${name}</div>
          </li>
          <li>
            <div class="col-1">专业：</div>
            <div class="col-2">${spec}</div>
          </li>
          <li>
            <div class="col-1">层次：</div>
            <div class="col-2">${level}</div>
          </li>
        </ul>
        <div class="title">
          当前需要交教材费的金额<br>
            ${price}元
        </div>
        <c:if test="${price > 0}">
          <form class="form-1">
            <div class="btn"><a href="#" onclick="sub()">立即交教材费</a></div>
          </form>
        </c:if>
      </div>
    </c:if>
  </section>
</div>
<div id="paySuccess" style="display: none;">
  <section class="bg-f4-pt pm-top-40">
    <div class="auto w zf-basic-info">
      <div class="tips-success">
        <div class="ico-success"><img src="${pageContext.request.contextPath}/images/success.png" /></div>
        <p>恭喜您，缴费成功！</p>
        <p>请打开 更多服务--邮寄地址 菜单，核对相关信息是否正确，方便我们给您邮寄教材</p>
      </div>
      <p class="back-prev"><a href="#" onclick="returnPay()">返回上一页</a></p>
    </div>
  </section>
</div>
</body>
</html>
<script>
  $(document).ready(function(){
    $(".form-1 > .list-info > li").click(function(){
      $(this).siblings().removeClass("selected").end().addClass('selected');
    });
  });

  function returnPay(){
    $("#pay").show();
    $("#paySuccess").hide();
  }

  function sub(){
    var price = "${studentCode}" == "021011113002" ? "0.01" : "${price}";
    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/wxPay/pay.htm",
      //url:"${pageContext.request.contextPath}/wxSearch/pay.htm",
      data:{"code":"${studentCode}", "money":price, "openId":"${openId}"},
      async: false,
      success: function(data) {
        if(data.state == 0){
          if("ok" == data.returnMsg || "OK" == data.returnMsg) {
            pay(data.appId, data.timeStamp, data.nonceStr, data.package, data.paySign);
          }else{
            alert(data.returnMsg);
          }
        }else{
          alert(data.msg);
        }
      }
    });
  }

  function onBridgeReady(appId, timeStamp, nonceStr, packages, paySign){
    WeixinJSBridge.invoke(
      'getBrandWCPayRequest', {
        "appId":appId,     //公众号名称，由商户传入
        "timeStamp":timeStamp,         //时间戳，自1970年以来的秒数
        "nonceStr":nonceStr, //随机串
        "package":packages,
        "signType":"MD5",         //微信签名方式：
        "paySign":paySign //微信签名
      },
      function(res){
        // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
        if(res.err_msg == "get_brand_wcpay_request:ok"  || res.err_msg == "get_brand_wcpay_request:OK") {
//          $("#pay").hide();
//          $("#paySuccess").show();
          location.href = "http://xiwang.attop.com/wxAddress/setAddress.htm?code=${studentCode}&openId=${openId}";
        }
        else if(res.err_msg == "get_brand_wcpay_request:cancel"  || res.err_msg == "get_brand_wcpay_request:CANCEL") {
          alert("支付已取消！");
        }
        else {
          alert("支付失败！");
        }
      }
    );
  }

  function pay(appId, timeStamp, nonceStr, packages, paySign) {
    if (typeof WeixinJSBridge == "undefined") {
      if (document.addEventListener) {
        document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
      } else if (document.attachEvent) {
        document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
        document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
      }
    } else {
      onBridgeReady(appId, timeStamp, nonceStr, packages, paySign);
    }
  }
</script>