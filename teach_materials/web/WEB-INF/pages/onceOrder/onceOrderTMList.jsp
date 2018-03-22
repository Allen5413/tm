<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html class="no-js">
<head>
  <%@ include file="/common/meta2.jsp"%>
  <%@ include file="/common/taglibs2.jsp"%>
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->

<div class="admin-content">
  <div class="am-tabs" data-am-tabs>
    <div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
      <div class="am-panel-hd am-cf" data-am-collapse="{target: '#order'}">订单信息<span class="am-icon-chevron-down am-fr"></span></div>
      <div id="order" class="am-in">
        <div class="am-g am-margin-top">
          <div class="am-u-sm-2 am-text-right"><label >订单号：</label></div>
          <div class="am-u-sm-8" style="float:left">${param.orderCode}</div>
        </div>

        <div class="am-g am-margin-top">
          <div class="am-u-sm-2 am-text-right"><label >学号：</label></div>
          <div class="am-u-sm-8" style="float:left">${student.code}</div>
        </div>

        <div class="am-g am-margin-top">
          <div class="am-u-sm-2 am-text-right"><label >姓名：</label></div>
          <div class="am-u-sm-8" style="float:left">${student.name}</div>
        </div>
      </div>
    </div>

    <div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
      <div class="am-panel-hd am-cf" data-am-collapse="{target: '#orderTM'}">订单明细<span class="am-icon-chevron-down am-fr"></span></div>
      <div id="orderTM" class="am-in">
        <form id="addForm" name="addForm" method="post">
          <input type="hidden" id="orderCode" name="orderCode" value="${param.orderCode}" />
          <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
            <c:if test="${param.state == 0}">
              <tr>
                <td colspan="99">
                  <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="confirmOrder('${param.orderId}')"><span class="am-icon-cog"></span> 确认订单</button>
                </td>
              </tr>
            </c:if>
            <tr class="am-primary">
              <th style="width: 5%;">序号</th>
              <th style="width: 20%;">课程名称</th>
              <th style="width: 23%;">教材名称</th>
              <th style="width: 7%;">教材价格</th>
              <th style="width: 7%;">教材数量</th>
              <th style="width: 7%;">操作人</th>
              <th style="width: 10%;">操作时间</th>
            </tr>
            <c:if test="${empty resultJson}">
              <tr>
                <td colspan="20" align="center" style="color: #ff000c;">没有找到相关数据</td>
              </tr>
            </c:if>
            <c:forEach var="result" items="${resultJson}" varStatus="status">
              <tr>
                <td align="center">${status.index+1}</td>
                <td>${result.courseCode}${result.courseName}</td>
                <td>${result.name}</td>
                <td>${result.price}</td>
                <td>
                  <div style="float: left">${result.count}</div>
                  <c:if test="${param.state == 0}">
                    <div style="float:right">
                      <c:if test="${result.count < 1}">
                        <input type="button" id="add_${result.id}_but" onclick="add(${result.id})" style="width: 20px; padding-right: 1px;" value="+" />
                      </c:if>
                      <c:if test="${result.count > 0}">
                        <input type="button" id="minus_${result.id}_but" onclick="minus(${result.id})" style="width: 20px;  padding-right: 1px;" value="-"/>
                      </c:if>
                    </div>
                  </c:if>
                </td>
                <td>${result.operator}</td>
                <td>${result.operateTime}</td>
              </tr>
            </c:forEach>
          </table>
        </form>
      </div>
    </div>

    <c:if test="${!empty studentBookOrderPackage}">
      <div class="am-g am-margin-top">
        <div class="am-u-sm-2 am-text-right"><label >第${studentBookOrderPackage.sort}件：</label></div>
        <div class="am-u-sm-8" style="float:left"></div>
      </div>

      <div class="am-g am-margin-top">
        <div class="am-u-sm-2 am-text-right"><label >大包编号：</label></div>
        <div class="am-u-sm-8" style="float:left">${studentBookOrderPackage.code}</div>
      </div>

      <div class="am-g am-margin-top">
        <div class="am-u-sm-2 am-text-right"><label >邮寄地址：</label></div>
        <div class="am-u-sm-8" style="float:left">${spot.address}</div>
      </div>
      <c:if test="${empty kuaidiMap}">
        <span style="color: red">没有找到快递信息</span>
      </c:if>
      <c:if test="${!empty kuaidiMap}">
        <c:forEach var="kuaidiMap" items="${kuaidiMap}" varStatus="status">
          <div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
            <div class="am-panel-hd am-cf" data-am-collapse="{target: '#kuaidi_${status.index}'}">快递信息，单号：${kuaidiMap.key}<span class="am-icon-chevron-down am-fr"></span></div>
            <div id="kuaidi_${status.index}" class="am-in">
              <c:if test="${empty kuaidiMap.value}">
                <span style="color: red">没有找到快递信息</span><br /><br />
              </c:if>
              <c:if test="${!empty kuaidiMap.value}">
                <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
                  <c:forEach var="json" items="${kuaidiMap.value}">
                    <tr style="height: 50px">
                      <td style="width: 15%">${json.ftime}</td>
                      <td style="width: 85%">${json.context}</td>
                    </tr>
                  </c:forEach>
                </table>
                <br /><br />
              </c:if>
            </div>
          </div>
        </c:forEach>
      </c:if>
    </c:if>
  </div>
</div>
</body>
</html>
<script>
  function confirmOrder(id){
    app.confirm("您确定要确认该订单？", function(){
      app.edit("${pageContext.request.contextPath}/confirmOnceOrder/confirm.htm", {"id":id});
    });
  }

  function add(id){
    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/addOnceOrderTMCount/add.htm",
      data:{id:id, count:1},
      async: false,
      success: function(data) {
        if(data.state == 0){
          history.go(0);
        }else{
          app.msg(data.msg, 1);
        }
      }
    });
  }

  function minus(id){
    $("#minus_"+id+"_but").hide();
    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/addOnceOrderTMCount/add.htm",
      data:{id:id, count:-1},
      async: false,
      success: function(data) {
        if(data.state == 0){
          history.go(0);
        }else{
          app.msg(data.msg, 1);
          $("#minus_"+id+"_but").show();
        }
      }
    });
  }
</script>
