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
      <c:if test="${empty money}">
        <div class="title">请先交费或者等待上次开票申请审核后再申请发票</div>
      </c:if>
      <c:if test="${!empty money}">
        <div class="title">请填写发票信息</div>
        <form class="form-1">
          <ul class="list-info">
            <li><label>抬头：<input type="text" id="title" placeholder="点击输入" value="${name}" /></label></li>
            <li><label>金额：<input type="text" id="money" placeholder="点击输入" value="${money}" /></label></li>
          </ul>
          <div class="btn"><a href="#" onclick="sub()">提交</a></div>
        </form>
      </c:if>
    </div>
  </c:if>
</section>
</body>
</html>
<script>
  $(document).ready(function(){
    $(".form-1 > .list-info > li").click(function(){
      $(this).siblings().removeClass("selected").end().addClass('selected');
    });
  });

  function sub(){
    var money = $("#money").val();
    var title = $("#title").val();
    if(title == ""){
      alert("请输入抬头");
      return;
    }
    if(!vaild.vaildMoney(money)){
      alert("请输入正确的金额");
      return;
    }
    if(money > Number("${money}")){
      alert("您最多只能开${money}元的发票");
      return;
    }
    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/wxInvoice/add.htm",
      data:{"studentCode":"${studentCode}", "money":money, "title":title},
      async: false,
      success: function(data) {
        if(data.state == 0){
          alert("操作成功，您可以在“我的发票”中查看记录。");
        }else{
          alert(data.msg);
        }
      }
    });
  }
</script>