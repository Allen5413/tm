<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="main-content">--%>
  <%--<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/spotFinanceTotal/find.htm" method="post">--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<label class="lab_80">学习中心：</label>[${spot.code}]${spot.name}--%>
        <%--<c:if test="${!empty param.spotCode}">--%>
          <%--<ul class="create_info_list">--%>
            <%--<li>--%>
              <%--<label class="lab_80" style="width: 130px;">学生个人账户欠费：</label><br><br>--%>
              <%--<table style="width: 400px;">--%>
                <%--<tr>--%>
                  <%--<td style="padding-left: 10px; width: 30%; color: red;">${data.studentOwn}&nbsp;元</td>--%>
                  <%--<td style="">--%>
                    <%--<a class="com_btn_b" href="#" id="resetBut" onclick="findStudentOwn();"><span>查看明细</span></a>--%>
                    <%--<a class="com_btn_b" href="#" id="resetBut" onclick="alterAddstu(1);"><span>交教材费</span></a>--%>
                  <%--</td>--%>
                <%--</tr>--%>
              <%--</table>--%>
              <%--<br>--%>
              <%--<label class="lab_80" style="width: 130px;">学生个人账户余额：</label><br><br>--%>
              <%--<table style="width: 400px;">--%>
                <%--<tr>--%>
                  <%--<td style="padding-left: 10px; width: 30%">${data.studentAcc}&nbsp;元</td>--%>
                  <%--<td style="">--%>
                    <%--<a class="com_btn_b" href="#" id="resetBut" onclick="findStudentAcc();"><span>查看明细</span></a>--%>
                    <%--<a class="com_btn_b" href="#" id="resetBut" onclick="alterAddstu(0);"><span>交预付款</span></a>--%>
                  <%--</td>--%>
                <%--</tr>--%>
              <%--</table>--%>
              <%--<br>--%>
              <%--<label class="lab_80" style="width: 130px;">学习中心账户余额：</label><br><br>--%>
              <%--<table style="width: 400px;">--%>
                <%--<tr>--%>
                  <%--<td style="padding-left: 10px; width: 30%" <c:if test='${data.isRedForSpotAcc == 0}'>color: red</c:if> >${data.spotAcc}&nbsp;元</td>--%>
                  <%--<td style="">--%>
                    <%--<c:if test="${spot.code >= 400}">--%>
                      <%--<a class="com_btn_b" href="#" id="resetBut" onclick="spotExpenseDetail();"><span>查看明细</span></a>--%>
                      <%--<a class="com_btn_b" href="#" id="resetBut" onclick="addSpotMoney();"><span>交费</span></a>--%>
                    <%--</c:if>--%>
                  <%--</td>--%>
                <%--</tr>--%>
              <%--</table>--%>
            <%--</li>--%>
          <%--</ul>--%>
          <%--<div style="border: 1px #E6F4FA solid; width: 99%; scroll:no"></div>--%>
          <%--<ul class="create_info_list">--%>
            <%--<li>--%>
              <%--<label class="lab_80" style="width: 100px;">教材订购情况：</label><br><br>--%>
              <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
                <%--<tr>--%>
                  <%--<th style="width: 15%; text-align: center">学期</th>--%>
                  <%--<th style="width: 20%;">订书码洋</th>--%>
                  <%--<th style="width: 20%;">欠费金额</th>--%>
                  <%--<th style="width: 25%; text-align: center">费用结清日期</th>--%>
                  <%--<th style="width: 10%; text-align: center">查看教材明细</th>--%>
                  <%--<th style="width: 10%;">奖励金额</th>--%>
                <%--</tr>--%>
                <%--<c:if test="${empty data.tmOrderData}">--%>
                  <%--<tr>--%>
                    <%--<Td colspan="99" align="center" style="color: #ff0000">没有找到相关数据</Td>--%>
                  <%--</tr>--%>
                <%--</c:if>--%>
                <%--<c:if test="${!empty data.tmOrderData}">--%>
                  <%--<c:set value="0" var="sumIsRed" />--%>
                  <%--<c:forEach var="tmOrderData" items="${data.tmOrderData}">--%>
                    <%--<tr>--%>
                      <%--<td align="center">${tmOrderData.semesterStr}</td>--%>
                      <%--<td>${tmOrderData.totalPrice}</td>--%>
                      <%--<c:if test="${tmOrderData.isRed == 1}">--%>
                        <%--<td style="color: #000000">--%>
                      <%--</c:if>--%>
                      <%--<c:if test="${tmOrderData.isRed == 0}">--%>
                        <%--<c:set value="1" var="sumIsRed" />--%>
                        <%--<td style="color:  red">--%>
                      <%--</c:if>--%>
                          <%--${tmOrderData.own}</td>--%>
                      <%--<td align="center">${fn:substring(tmOrderData.clearTime, 0, 10)}</td>--%>
                      <%--<td align="center">--%>
                        <%--<a href="#" onclick="findTMInfo(${tmOrderData.semesterId})" style="color: #0092DC">查看</a>--%>
                        <%--<a href="#" onclick="downTMInfo(${tmOrderData.semesterId})" style="color: #0092DC">下载</a>--%>
                      <%--</td>--%>
                      <%--<td></td>--%>
                    <%--</tr>--%>
                  <%--</c:forEach>--%>
                  <%--<tr>--%>
                    <%--<td align="right">合计：</td>--%>
                    <%--<td>${data.sumTotalPrice}</td>--%>
                    <%--<td style="<c:if test='${sumIsRed == 1}'>color:red</c:if> "> ${data.sumOwn}</td>--%>
                    <%--<td></td>--%>
                    <%--<td></td>--%>
                    <%--<td></td>--%>
                  <%--</tr>--%>
                <%--</c:if>--%>
              <%--</table>--%>
            <%--</li>--%>
          <%--</ul>--%>
          <%--<div style="border: 1px #E6F4FA solid; width: 99%; scroll:no"></div>--%>
          <%--<ul class="create_info_list">--%>
            <%--<li>--%>
              <%--<label class="lab_80" style="width: 140px;">学生账户进出帐明细：</label><br><br>--%>
              <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
                <%--<tr>--%>
                  <%--<th style="width: 15%; text-align: center">日期</th>--%>
                  <%--<th style="width: 15%;">学号</th>--%>
                  <%--<th style="width: 10%;">姓名</th>--%>
                  <%--<th style="width: 10%; text-align: center">金额</th>--%>
                  <%--<th style="width: 50%;">描述</th>--%>
                <%--</tr>--%>
                <%--<c:if test="${empty data.studentExpenseDate}">--%>
                  <%--<tr>--%>
                    <%--<Td colspan="99" align="center" style="color: #ff0000">没有找到相关数据</Td>--%>
                  <%--</tr>--%>
                <%--</c:if>--%>
                <%--<c:if test="${!empty data.studentExpenseDate}">--%>
                  <%--<c:forEach var="studentExpenseDate" items="${data.studentExpenseDate}">--%>
                    <%--<tr>--%>
                      <%--<td align="center">${studentExpenseDate.createTime}</td>--%>
                      <%--<td>${studentExpenseDate.code}</td>--%>
                      <%--<td>${studentExpenseDate.name}</td>--%>
                      <%--<td align="center">${studentExpenseDate.money}</td>--%>
                      <%--<td>${studentExpenseDate.detail}</td>--%>
                    <%--</tr>--%>
                  <%--</c:forEach>--%>
                <%--</c:if>--%>
              <%--</table>--%>
            <%--</li>--%>
            <%--<li>--%>
              <%--<label class="lab_80" style="width: 140px;">中心账户进出帐明细：</label><br><br>--%>
              <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
                <%--<tr>--%>
                  <%--<th style="width: 25%; text-align: center">日期</th>--%>
                  <%--<th style="width: 15%; text-align: center">金额</th>--%>
                  <%--<th style="width: 60%;">描述</th>--%>
                <%--</tr>--%>
                <%--<c:if test="${empty data.spotExpenseDate}">--%>
                  <%--<tr>--%>
                    <%--<Td colspan="99" align="center" style="color: #ff0000">没有找到相关数据</Td>--%>
                  <%--</tr>--%>
                <%--</c:if>--%>
                <%--<c:if test="${!empty data.spotExpenseDate}">--%>
                  <%--<c:forEach var="spotExpenseDate" items="${data.spotExpenseDate}">--%>
                    <%--<tr>--%>
                      <%--<td align="center">${spotExpenseDate.createTime}</td>--%>
                      <%--<td align="center">${spotExpenseDate.money}</td>--%>
                      <%--<td>${spotExpenseDate.detail}</td>--%>
                    <%--</tr>--%>
                  <%--</c:forEach>--%>
                <%--</c:if>--%>
              <%--</table>--%>
            <%--</li>--%>
          <%--</ul>--%>
        <%--</c:if>--%>
      <%--</li>--%>
    <%--</ul>--%>
  <%--</form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function findTMInfo(semesterId){--%>
   <%--open('${pageContext.request.contextPath}/spotFinanceTotal/findSpotOrderTM.htm?spotCode=${param.spotCode}&semesterId='+semesterId);--%>
  <%--}--%>

  <%--function findStudentOwn(){--%>
    <%--open('${pageContext.request.contextPath}/spotFinanceTotal/findStudentOwnInfo.htm?spotCode=${param.spotCode}');--%>
  <%--}--%>

  <%--function findStudentAcc(){--%>
    <%--open('${pageContext.request.contextPath}/spotFinanceTotal/findStudentAccInfo.htm?spotCode=${param.spotCode}');--%>
  <%--}--%>


  <%--function alterAddstu(type){--%>
    <%--if(0 ==  type){--%>
      <%--var url = "${pageContext.request.contextPath}/centerPay/toAddStu.htm?spotCode=${param.spotCode}&spotOwnId=0";--%>
      <%--parent.openPayDialog(url);--%>
    <%--}else{--%>
      <%--if("${data.studentOwn}" <= 0){--%>
        <%--alert("未欠费");--%>
        <%--return;--%>
      <%--}--%>
      <%--var url = "${pageContext.request.contextPath}/centerPay/toPayStu.htm?spotCode=${param.spotCode}&spotOwnId=0";--%>
      <%--parent.openOwnDialog('交费', 0.6, 0.9, url);--%>
    <%--}--%>
  <%--}--%>


  <%--function addSpotMoney(){--%>
    <%--var url = '${pageContext.request.contextPath}/addSpotEP/openAddSpotEPPage.htm?spotCode=${param.spotCode}';--%>
    <%--var dialog = new Dialog();--%>
    <%--dialog.Width = 650;--%>
    <%--dialog.Height = 330;--%>
    <%--dialog.Title = "缴费";--%>
    <%--dialog.URL = url;--%>
    <%--dialog.show();--%>
  <%--}--%>
  <%--function spotExpenseDetail(){--%>
    <%--parent.openDialog('明细', 0.9, 0.8, '${pageContext.request.contextPath}/findSpotExpenseDetail/doFindSpotExpenseDetail.htm?code=${param.spotCode}');--%>
  <%--}--%>
<%--</script>--%>


<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/spotFinanceTotal/find.htm" method="post">
  <label >学习中心：</label>[${spot.code}]${spot.name}&nbsp;&nbsp;&nbsp;&nbsp;
</form>
<p /><p />
<div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
  <div class="am-panel-hd am-cf" data-am-collapse="{target: '#total'}">账户统计<span class="am-icon-chevron-down am-fr"></span></div>
  <div id="total" class="am-in">
    <div class="am-g am-margin-top">
      <div class="am-u-sm-2 am-text-right"><label >学生个人账户欠费：</label></div>
      <div class="am-u-sm-1">${data.studentOwn}&nbsp;元</div>
      <div class="am-u-sm-9">
        <div class="am-btn-group">
          <button type="button" class="am-btn am-btn-primary am-round" onClick="findStudentOwn()"><span class="am-icon-th-list"></span> 查看明细</button>
          <c:if test="${data.isExistsWait == 0}">
            <button type="button" class="am-btn am-btn-primary am-round" onClick="alterAddstu(1)"><span class="am-icon-plus"></span> 交教材费</button>
          </c:if>
          <c:if test="${data.isExistsWait == 1}">
            已交费未审核
          </c:if>
        </div>
      </div>
    </div>

    <div class="am-g am-margin-top">
      <div class="am-u-sm-2 am-text-right"><label >学生个人账户余额：</label></div>
      <div class="am-u-sm-1">${data.studentAcc}&nbsp;元</div>
      <div class="am-u-sm-9">
        <div class="am-btn-group">
          <button type="button" class="am-btn am-btn-primary am-round" onClick="findStudentAcc()"><span class="am-icon-th-list"></span> 查看明细</button>
          <c:if test="${data.isExistsWait == 0}">
            <button type="button" class="am-btn am-btn-primary am-round" onClick="alterAddstu(0)"><span class="am-icon-plus"></span> 交预付款</button>
          </c:if>
          <c:if test="${data.isExistsWait == 1}">
            已交费未审核
          </c:if>
        </div>
      </div>
    </div>

    <div class="am-g am-margin-top">
      <div class="am-u-sm-2 am-text-right"><label >学习中心账户余额：</label></div>
      <div class="am-u-sm-1" style="<c:if test='${data.isRedForSpotAcc == 0}'>color: red</c:if> ">${data.spotAcc}&nbsp;元</div>
      <div class="am-u-sm-9">
        <div class="am-btn-group">
          <button type="button" class="am-btn am-btn-primary am-round" onClick="spotExpenseDetail()"><span class="am-icon-th-list"></span> 查看明细</button>
          <c:if test="${data.isExistsWait == 0}">
            <button type="button" class="am-btn am-btn-primary am-round" onClick="addSpotMoney()"><span class="am-icon-plus"></span> 交费</button>
          </c:if>
          <c:if test="${data.isExistsWait == 1}">
            已交费未审核
          </c:if>
        </div>
        <span style="color:red;">*注：该交费只针对于函授站下设点，不适用于学习中心网教交费</span>
      </div>
    </div>
    <p />
  </div>
</div>

<div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
  <div class="am-panel-hd am-cf" data-am-collapse="{target: '#order'}">教材订购情况：<span class="am-icon-chevron-down am-fr"></span></div>
  <div id="order" class="am-in">
    <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
      <tr class="am-primary">
        <th style="width: 15%; text-align: center">学期</th>
        <th style="width: 13%;">订书码洋</th>
        <th style="width: 13%;">欠费金额</th>
        <th style="width: 25%; text-align: center">费用结清日期</th>
        <th style="width: 21%; text-align: center">查看教材明细</th>
        <th style="width: 13%;">奖励金额</th>
      </tr>
      <c:if test="${empty data.tmOrderData}">
        <tr>
          <Td colspan="99" align="center" style="color: #ff0000">没有找到相关数据</Td>
        </tr>
      </c:if>
      <c:if test="${!empty data.tmOrderData}">
        <c:set value="0" var="sumIsRed" />
        <c:forEach var="tmOrderData" items="${data.tmOrderData}">
          <tr>
            <td align="center">${tmOrderData.semesterStr}</td>
            <td>${tmOrderData.totalPrice}</td>
            <c:if test="${tmOrderData.isRed == 1}">
            <td style="color: #000000">
              </c:if>
              <c:if test="${tmOrderData.isRed == 0}">
                <c:set value="1" var="sumIsRed" />
            <td style="color:  red">
              </c:if>
                ${tmOrderData.own}</td>
            <td align="center">${fn:substring(tmOrderData.clearTime, 0, 10)}</td>
            <td align="center">
              <div class="am-btn-group">
                <button type="button" class="am-btn am-btn-primary am-round" onClick="findTMInfo(${tmOrderData.semesterId})"><span class="am-icon-th-list"></span> 查看</button>
                <button type="button" class="am-btn am-btn-primary am-round" onClick="downTMInfo(${tmOrderData.semesterId})"><span class="am-icon-download"></span> 下载</button>
              </div>
            </td>
            <td></td>
          </tr>
        </c:forEach>
        <tr>
          <td align="right">合计：</td>
          <td>${data.sumTotalPrice}</td>
          <td style="<c:if test='${sumIsRed == 1}'>color:red</c:if> "> ${data.sumOwn}</td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
      </c:if>
    </table>
  </div>
</div>

<div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
  <div class="am-panel-hd am-cf" data-am-collapse="{target: '#stu'}">学生账户进出帐明细：<span class="am-icon-chevron-down am-fr"></span></div>
  <div id="stu" class="am-in">
    <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
      <tr class="am-primary">
        <th style="width: 15%; text-align: center">日期</th>
        <th style="width: 15%;">学号</th>
        <th style="width: 10%;">姓名</th>
        <th style="width: 10%; text-align: center">金额</th>
        <th style="width: 50%;">描述</th>
      </tr>
      <c:if test="${empty data.studentExpenseDate}">
        <tr>
          <Td colspan="99" align="center" style="color: #ff0000">没有找到相关数据</Td>
        </tr>
      </c:if>
      <c:if test="${!empty data.studentExpenseDate}">
        <c:forEach var="studentExpenseDate" items="${data.studentExpenseDate}">
          <tr>
            <td align="center">${studentExpenseDate.createTime}</td>
            <td>${studentExpenseDate.code}</td>
            <td>${studentExpenseDate.name}</td>
            <td align="center">${studentExpenseDate.money}</td>
            <td>${studentExpenseDate.detail}</td>
          </tr>
        </c:forEach>
      </c:if>
    </table>
  </div>
</div>


<div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
  <div class="am-panel-hd am-cf" data-am-collapse="{target: '#spot'}">中心账户进出帐明细：<span class="am-icon-chevron-down am-fr"></span></div>
  <div id="spot" class="am-in">
    <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
      <tr class="am-primary">
        <th style="width: 25%; text-align: center">日期</th>
        <th style="width: 15%; text-align: center">金额</th>
        <th style="width: 60%;">描述</th>
      </tr>
      <c:if test="${empty data.spotExpenseDate}">
        <tr>
          <Td colspan="99" align="center" style="color: #ff0000">没有找到相关数据</Td>
        </tr>
      </c:if>
      <c:if test="${!empty data.spotExpenseDate}">
        <c:forEach var="spotExpenseDate" items="${data.spotExpenseDate}">
          <tr>
            <td align="center">${spotExpenseDate.createTime}</td>
            <td align="center">${spotExpenseDate.money}</td>
            <td>${spotExpenseDate.detail}</td>
          </tr>
        </c:forEach>
      </c:if>
    </table>
  </div>
</div>


<script>
  function findStudentOwn(){
    open('${pageContext.request.contextPath}/spotFinanceTotal/findStudentOwnInfo.htm?spotCode=${spot.code}');
  }

  function findStudentAcc(){
    open('${pageContext.request.contextPath}/spotFinanceTotal/findStudentAccInfo.htm?spotCode=${spot.code}');
  }

  function spotExpenseDetail(){
    app.openOneBtnDialog('${pageContext.request.contextPath}/findSpotExpenseDetail/doFindSpotExpenseDetail.htm?code=${spot.code}', '明细', 1000, 0.9);
  }

  function findTMInfo(semesterId){
    open('${pageContext.request.contextPath}/spotFinanceTotal/findSpotOrderTM.htm?spotCode=${spot.code}&semesterId='+semesterId);
  }

  function downTMInfo(semesterId){
    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/spotFinanceTotal/downSpotOrderTM.htm?spotCode=${spot.code}&semesterId="+semesterId,
      data:{},
      async: false,
      success: function(data) {
        open("${pageContext.request.contextPath}"+data);
      }
    });
  }

  function alterAddstu(type){
    if(0 ==  type){
      var url = "${pageContext.request.contextPath}/centerPay/toAddStu.htm?spotCode=${spot.code}&spotOwnId=0";
      app.openNextBtnDialog(url, "交预付款", 1100, 0.9, function(index){
        var trArr = $(".cpAddStuTr");
        if(trArr.length == 0){
          app.msg("无记录", 1);
          return;
        }

        var trArr = $(".cpAddStuTr");
        for(var i = 0;i < trArr.length;i ++){
          if((trArr[i].id) != "title"){
            if(trArr[i].id != "sumTr"){
              var stuCode = $(trArr[i]).find("td").eq(1).text();
              var money = $(trArr[i]).find("td").eq(3).find("input").val();
              if(!vaild.vaildMoney(money)){
                app.msg("学号："+stuCode+", 金额错误，或者金额小于等于0", 1);
                return;
              }
            }
          }
        }
        var url = '${pageContext.request.contextPath}/centerPay/toNextPage.htm?'+ "spotCode=${spot.code}&spotOwnId=0";
        app.openDialog(url, "交预付款", 700, 0.45, function(index2){
          var arrId=new Array();
          var i=0;
          $("[name = payImg]").each(function(){
            arrId[i] = $(this).attr("id");
            i++;
          });

          app.addForFile('${pageContext.request.contextPath}/centerPay/sureTemPay.htm', "doNextForm", index2);
          layer.close(index);
        });
      });
    }else{
      if("${data.studentOwn}" <= 0){
        app.msg("未欠费", 0);
        return;
      }
      var url = "${pageContext.request.contextPath}/centerPay/toPayStu.htm?spotCode=${spot.code}&spotOwnId=0";
      app.openNextBtnDialog(url, "交教材费", 1100, 0.9, function(index){
        var trArr = $(".cpAddStuTr");
        if(trArr.length == 0){
          app.msg("无记录", 1);
          return;
        }

        var trArr = $(".cpAddStuTr");
        for(var i = 0;i < trArr.length;i ++){
          if((trArr[i].id) != "title"){
            if(trArr[i].id != "sumTr"){
              var stuCode = $(trArr[i]).find("td").eq(1).text();
              var money = $(trArr[i]).find("td").eq(3).find("input").val();
              if(!vaild.vaildMoney(money)){
                app.msg("学号："+stuCode+", 金额错误，或者金额小于等于0", 1);
                return;
              }
            }
          }
        }
        var url = '${pageContext.request.contextPath}/centerPay/toNextPage.htm?'+ "spotCode=${spot.code}&spotOwnId=0";;
        app.openDialog(url, "中心确认交费", 700, 0.45, function(index2){
          var arrId=new Array();
          var i=0;
          $("[name = payImg]").each(function(){
            arrId[i] = $(this).attr("id");
            i++;
          });

          app.addForFile('${pageContext.request.contextPath}/centerPay/sureTemPay.htm', "doNextForm", index2);
          layer.close(index);
        });
      });
    }
  }

  function addSpotMoney(){
    var url = '${pageContext.request.contextPath}/addSpotEP/openAddSpotEPPage.htm?spotCode=${spot.code}';
    app.openDialog(url, '交费', 700, 0.45, function(index){
      var money = $("#money").val().trim();
      if(!vaild.vaildMoney(money)){
        app.msg("请输入正确的金额", 1);
        return;
      }
      app.addForFile("${pageContext.request.contextPath}/addSpotEP/spotEPAdd.htm", "addForm", index);
    });
  }
</script>
