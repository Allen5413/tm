<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
您有<span style="color: red">${totalNum}</span>个订单需要确认;<br />
已经确认<span id="num" style="color: red">${num}</span>个订单;<br />
确认失败<span id="errorNum" style="color: red">${errorNum}</span>个订单;<br />
还有<span id="num2" style="color: red">${totalNum-num}</span>订单等待确认<br /><br />
<span id="showSucess" style="color: red; display: none">已经确认完成</span>
<script>
    var index = setInterval(function(){
      var totalNum = Number("${totalNum}");
      $.ajax({
        cache: true,
        type: "POST",
        url: "${pageContext.request.contextPath}/confirmOnceOrder/findConfirmNum.htm",
        async: false,
        success: function (data) {
          if(data.state == 0) {
            $("#num").html(data.num);
            $("#errorNum").html(data.errorNum);
            $("#num2").html(totalNum-data.num);
            if(data.num == totalNum){;
              $("#showSucess").show();
              clearInterval(index);
            }
          }
        }
      });
    }, 1000);
</script>