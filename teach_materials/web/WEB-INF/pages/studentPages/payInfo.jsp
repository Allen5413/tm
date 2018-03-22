<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
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
    <div class="title">支付详情</div>
    <div class="info-text-list">
      <ul>
        <li>
          <span class="lab-tg">缴费系统：</span>西网教材管理系统
        </li>
        <li>
          <span class="lab-tg">缴费项目：</span>教材费
        </li>
        <li>
          <span class="lab-tg">账 号：</span>${code}
        </li>
        <li>
          <span class="lab-tg">姓 名：</span>${name}
        </li>
      </ul>
      <div class="jin-num">本次缴费金额：<span class="num-f24">${price}</span>元</div>
    </div>
    <c:if test="${price > 0}">
      <div class="tit-way">请选择支付方式：</div>
      <ul class="way-list">
        <%--<li><input type="radio" name="payType" value="0"> <img src="${pageContext.request.contextPath}/student/img/ico-1.png" /> 中国银联</li>--%>
        <li><input type="radio" name="payType" value="1"> <img src="${pageContext.request.contextPath}/student/img/ico-2.png" /> 微信支付</li>
      </ul>
      <p class="btn-next"><a class="btn-com" href="#" onclick="addPay()">下一步</a></p>
    </c:if>
  </div>
</div>
<div class="footer-view">
  <div class="copyright">
    <p>版权所有：西南大学网络与继续教育学院    渝ICP备06005063号-2</p>
    <p>地址：重庆市北碚区天生路2号西南大学网络与继续教育学院    邮编：400715 </p>
  </div>
</div>
<div id="hidden_payForm"></div>
<div id="close_div" style="z-index: 999; background-color: #ffffff; width: 100%; height: 150px; position:absolute; display: none;">
  <br /><br /><br /><br />
  <span style="color: #ff0000; font-size: 18px; font-weight: bold;">你已经操作成功，点击下面按钮关闭页面</span>
  <br /><br />
  <a class="com_btn_b" href="#" onclick="closePay()"><span>关闭页面</span></a>
</div>
</body>
</html>
<script>
  function addPay(){
    var studentCode = "${code}";
    var money = (studentCode == "021011113002" ? "0.01" : "${price}");
    var payType = -1;
    $("[name=payType]").each(function(){
      if($(this).is(':checked')){
        if($(this).val() == 0){
          payType = 0;
        }
        if($(this).val() == 1){
          payType = 1;
        }
      }
    });
    debugger;
    if(payType == -1){
      alert("请先选择支付方式");
      return;
    }else{
      if(payType == 1){
        location.href = "${pageContext.request.contextPath}/wxPay/paySM.htm?code="+studentCode+"&money="+money;
      }
    }
  }
</script>

