<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <%@ include file="/common/meta.jsp"%>
  <%@ include file="/common/taglibs.jsp"%>
</head>
<body>
<div class="main-content">
  <span style="font-weight: bold; font-size: 18px;">${name}同学，西网书城欢迎你！</span><br /><br />
  财务信息
  <table class="table_slist" cellpadding="0" cellspacing="0" width="70%">
    <tr>
      <th style="width: 20%; text-align: center">累计缴费金额</th>
      <th style="width: 20%; text-align: center">累计订书金额</th>
      <th style="width: 20%; text-align: center">余额</th>
      <th style="width: 40%; text-align: center">操作</th>
    </tr>
    <tr>
      <td align="center">${json.pay}</td>
      <td align="center">${json.buy}</td>
      <td align="center">${json.acc}</td>
      <td align="center">
        <a class="com_btn_b" href="#" onclick="addPay('${code}', 0, 0)"><span>交预付款</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
        <a class="com_btn_b" href="#" onclick="searchDetail('${code}')"><span>查看明细</span></a>
      </td>
    </tr>
  </table>
  <br />
  当前待确认的订购教材明细
  <form id="form" name="form" action="${pageContext.request.contextPath}/confirmStudentBookOrder/studentConfirm.htm"; method="post">
    <input type="hidden" name="studentCode" value="${code}" />
    <c:set var="orderCode" value="" />
    <table id="unconfirmedOrderTable" class="table_slist" cellpadding="0" cellspacing="0" width="70%">
      <tr>
        <th style="width: 10%; text-align: center">
          <%--<a href="#" onclick="checkAll('cb');"><font color="blue">全选</font></a> |--%>
          <%--<a href="#" onclick="checkNall('cb');"><font color="blue">反选</font></a>--%>
          序号
        </th>
        <th style="width: 25%; text-align: center">课程名称</th>
        <th style="width: 30%; text-align: center">教材名称</th>
        <th style="width: 10%; text-align: center">单价（元）</th>
        <th style="width: 10%; text-align: center">数量</th>
        <th style="width: 10%; text-align: center">金额</th>
      </tr>
      <c:if test="${empty json.unconfirmedOrderTM}">
        <tr>
          <td colspan="99" align="center" style="color:red">当前没有待确认的订购教材</td>
        </tr>
      </c:if>
      <c:if test="${!empty json.unconfirmedOrderTM}">
        <c:forEach var="unconfirmedOrderTM" items="${json.unconfirmedOrderTM}" varStatus="status">
          <c:set var="orderCode" value="${unconfirmedOrderTM.orderCode}" />
          <tr>
            <td align="center">
              <%--<input type="checkbox" id="cb_${status.index}" name="cb" value="${unconfirmedOrderTM.orderTMId}_${unconfirmedOrderTM.orderCode}_${unconfirmedOrderTM.count}">--%>
              ${status.index+1}
            </td>
            <td>(${unconfirmedOrderTM.courseCode})${unconfirmedOrderTM.courseName}</td>
            <td>${unconfirmedOrderTM.tmName}</td>
            <td align="center" id="price_${status.index}">${unconfirmedOrderTM.price}</td>
            <td align="center">
              <a href="#" onclick="addCount(${status.index}, 0)" style="color: #0092DC">+</a>&nbsp;&nbsp;&nbsp;&nbsp;
              <span id="count_${status.index}">${unconfirmedOrderTM.count}</span>&nbsp;&nbsp;&nbsp;&nbsp;
              <span id="remove_${status.index}">
                <c:if test="${unconfirmedOrderTM.count > 1}">
                  <a href="#" onclick="addCount(${status.index}, 1)" style="color: #0092DC">-</a>
                </c:if>
                <c:if test="${unconfirmedOrderTM.count <= 1}">
                  <a href="#" style="color:#808080">-</a>
                </c:if>
              </span>
            </td>
            <td align="center" id="totalPrice_${status.index}">${unconfirmedOrderTM.totalPrice}</td>
          </tr>
        </c:forEach>
        <tr style="color: red">
          <td align="center"></td>
          <td align="center"></td>
          <td align="center"></td>
          <td align="right">合计：</td>
          <td id="unconfirmedTotalCount" align="center">${json.unconfirmedTotalCount}</td>
          <td id="unconfirmedTotalPrice" align="center">${json.unconfirmedTotalPrice}</td>
        </tr>
      </c:if>
    </table>
    <%--暂时不开放给学生确认和订书--%>
    <%--<table width="70%">--%>
      <%--<tr>--%>
        <%--<td align="center">--%>
          <%--<a class="com_btn_b" href="#" onclick="confirmOrder()"><span>确认订单</span></a>&nbsp;&nbsp;&nbsp;&nbsp;--%>
          <%--<a class="com_btn_b" href="#" onclick="addTM('${code}')"><span>添加教材</span></a>--%>
        <%--</td>--%>
      <%--</tr>--%>
    <%--</table>--%>
  </form>
  <br />
  正在处理的订单
  <table class="table_slist" cellpadding="0" cellspacing="0" width="70%">
    <tr>
      <th style="width: 5%; text-align: center">序号</th>
      <th style="width: 10%; text-align: center">订单号</th>
      <th style="width: 10%; text-align: center">购买教材数量</th>
      <th style="width: 10%; text-align: center">购买教材金额（元）</th>
      <th style="width: 5%; text-align: center">订单状态</th>
      <th style="width: 10%; text-align: center">快递单号</th>
      <th style="width: 30%; text-align: center">最新快递信息</th>
      <th style="width: 10%; text-align: center">下单时间</th>
      <th style="width: 10%; text-align: center">操作</th>
    </tr>
    <c:if test="${empty json.disposeOrder}">
      <tr>
        <td colspan="99" align="center" style="color:red">当前没有正在处理的订单</td>
      </tr>
    </c:if>
    <c:if test="${!empty json.disposeOrder}">
      <c:forEach var="disposeOrder" items="${json.disposeOrder}" varStatus="status">
        <tr>
          <td align="center">${status.index + 1}</td>
          <td align="center">${disposeOrder.orderCode}</td>
          <td align="center">${disposeOrder.count}</td>
          <td align="center">${disposeOrder.price}</td>
          <td align="center">
            <c:if test="${disposeOrder.state == 1}">已确认</c:if>
            <c:if test="${disposeOrder.state == 2}">分拣中</c:if>
            <c:if test="${disposeOrder.state == 3}">已打包</c:if>
            <c:if test="${disposeOrder.state == 4}">已发出</c:if>
          </td>
          <td align="center">${disposeOrder.logisticCode}</td>
          <td align="left">
              <c:if test="${30 < fn:length(disposeOrder.kuaidiJSON.context)}">
                ${fn:substring(disposeOrder.kuaidiJSON.context, 0, 30)}...
              </c:if>
              <c:if test="${30 >= fn:length(disposeOrder.kuaidiJSON.context)}">
                ${disposeOrder.kuaidiJSON.context}
              </c:if>
          </td>
          <td align="center">${disposeOrder.createTime}</td>
          <td align="center">
            <a href="#" onclick="detail('${disposeOrder.orderCode}',${disposeOrder.state},${disposeOrder.id},'${code}')" style="color: #0092DC">查看明细</a>&nbsp;&nbsp;
          </td>
        </tr>
      </c:forEach>
      <tr style="color: red">
        <td align="center"></td>
        <td align="right">合计：</td>
        <td align="center">${json.disposeTotalCount}</td>
        <td align="center">${json.disposeTotalPrice}</td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
      </tr>
    </c:if>
  </table>
  <br />
  历史订购教材明细
  <table class="table_slist" cellpadding="0" cellspacing="0" width="70%">
    <tr>
      <th style="width: 10%; text-align: center">序号</th>
      <th style="width: 25%; text-align: center">课程名称</th>
      <th style="width: 30%; text-align: center">教材名称</th>
      <th style="width: 10%; text-align: center">单价（元）</th>
      <th style="width: 10%; text-align: center">数量</th>
      <th style="width: 10%; text-align: center">金额</th>
    </tr>
    <c:if test="${empty json.historyOrderTM}">
      <tr>
        <td colspan="99" align="center" style="color:red">当前没有历史订购教材明细</td>
      </tr>
    </c:if>
    <c:if test="${!empty json.historyOrderTM}">
      <c:forEach var="historyOrderTM" items="${json.historyOrderTM}" varStatus="status">
        <tr>
          <td align="center">${status.index + 1}</td>
          <td>(${historyOrderTM.courseCode})${historyOrderTM.courseName}</td>
          <td>${historyOrderTM.tmName}</td>
          <td align="center">${historyOrderTM.price}</td>
          <td align="center">${historyOrderTM.count}</td>
          <td align="center">${historyOrderTM.totalPrice}</td>
        </tr>
      </c:forEach>
      <tr style="color: red">
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="right">合计：</td>
        <td align="center">${json.historyTotalCount}</td>
        <td align="center">${json.historyTotalPrice}</td>
      </tr>
    </c:if>
  </table>
</div>
</body>
</html>
<script>
  var addPayDiag;
  var addTmDiag;
  function detail(code, state, orderId, studentCode){
    open('${pageContext.request.contextPath}/findStudentBookOrderListByOrderCode/doFindStudentBookOrderListByOrderCode.htm?orderCode='+code+'&state='+state+'&orderId='+orderId+'&studentCode='+studentCode);
  }

  function searchDetail(code){
    open('${pageContext.request.contextPath}/findStudentExpenseDetail/doFindStudentExpenseDetail.htm?code='+code);
  }

  function addTM(code){
    addTmDiag = new Dialog();
    addTmDiag.Width = 1000;
    addTmDiag.Height = 600;
    addTmDiag.Title = "添加教材";
    addTmDiag.URL = '${pageContext.request.contextPath}/addStudentBookOrderTM/open.htm?code='+code;
    addTmDiag.show();
  }

  function addPay(code, orderPrice, acc){
    addPayDiag = new Dialog();
    addPayDiag.Width = 500;
    addPayDiag.Height = 150;
    addPayDiag.Title = "交预付款";
    addPayDiag.URL = '${pageContext.request.contextPath}/addPay/open.htm?code='+code+'&orderPrice='+orderPrice+'&acc='+acc+'&payUserType=0';
    addPayDiag.show();
  }

  function addCount(num, flag){
    if(0 == flag){
      var count = Number($("#count_" + num).html()) + 1;
      $("#count_"+num).html(count);
      if (count > 1) {
        $("#remove_" + num).html("<a href='#' onclick='addCount("+num+", 1)' style='color: #0092DC'>-</a>");
      }
      //总合计也要加1
      $("#unconfirmedTotalCount").html(Number($("#unconfirmedTotalCount").html())+1);
      //每项金额也要加
      $("#totalPrice_"+num).html((Number($("#totalPrice_"+num).html()) + Number($("#price_"+num).html())).toFixed(2));
      //总金额也要加
      $("#unconfirmedTotalPrice").html((Number($("#unconfirmedTotalPrice").html()) + Number($("#price_"+num).html())).toFixed(2));
      //修改选中cb后的数量
      debugger;
      var cbVal = $("#cb_"+num).val();
      var obj = cbVal.split("_");
      var oldCount = Number(obj[2]);
      var newCount = oldCount+1;
      $("#cb_"+num).val(obj[0]+"_"+obj[1]+"_"+newCount);
    }else {
      var count = Number($("#count_" + num).html()) - 1;
      $("#count_" + num).html(count);
      if (count <= 1) {
        $("#remove_" + num).html("<a href='#' style='color:#808080'>-</a>");
      }
      //总合计也要-1
      $("#unconfirmedTotalCount").html(Number($("#unconfirmedTotalCount").html())-1);
      //每项金额也要减
      $("#totalPrice_"+num).html((Number($("#totalPrice_"+num).html()) - Number($("#price_"+num).html())).toFixed(2));
      //总金额也要减
      $("#unconfirmedTotalPrice").html((Number($("#unconfirmedTotalPrice").html()) - Number($("#price_"+num).html())).toFixed(2));
      //修改选中cb后的数量
      var cbVal = $("#cb_"+num).val();
      var obj = cbVal.split("_");
      var oldCount = Number(obj[2]);
      var newCount = oldCount-1;
      $("#cb_"+num).val(obj[0]+"_"+obj[1]+"_"+newCount);
    }
  }

  function confirmOrder(){
    var isChecked = false;
    $("[name=cb]").each(function(){
      if(this.checked){
        isChecked = true;
      }
    });
    if(!isChecked){
      Dialog.alert("请选择要确认的教材！");
    }else{
        Dialog.confirm('您确定要提交选中的教材？',function(){
          $.ajax({
            cache: true,
            type: "POST",
            url: "${pageContext.request.contextPath}/confirmStudentBookOrder/studentConfirm.htm",
            data: $('#form').serialize(),
            async: false,
            success: function (data) {
              if (data.state == 0) {
                Dialog.alert("提交成功！");
                history.go(0);
              } else if(data.state == -1) {
                Dialog.alert("你当前选择的教材共计"+data.confirmOrderTotalPrice+"元，你的账户余额为"+(0 > data.acc ? 0 : data.acc)+"元，不足以支付以上订单的教材款，请您先交预付款后再次提交订单。", function(){
                  addPay('${code}', data.confirmOrderTotalPrice, data.acc);
                });
              }else {
                Dialog.alert(data.msg);
              }
            }
          });
        });
    }
  }
</script>