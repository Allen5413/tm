<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
  <div class="am-panel-hd am-cf" data-am-collapse="{target: '#pay'}">入账记录<span class="am-icon-chevron-down am-fr"></span></div>
  <div id="pay" class="am-in">
    <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
      <tr class="am-primary">
        <th style="width:18%">交费时间</th>
        <th style="width:9%">交费金额</th>
        <th style="width:9%">交费方式</th>
        <th style="width:35%">备注</th>
        <th>操作</th>
      </tr>
      <c:if test="${empty payList}">
        <tr>
          <td colspan="20" align="center" style="color: #ff000c;">没有找到相关数据</td>
        </tr>
      </c:if>
      <c:forEach var="pay" items="${payList}" varStatus="status">
        <tr <c:if test="${pay.payType == 4 || pay.parentId == 0}">style="color: red;"</c:if> >
          <td>
            <fmt:formatDate value="${pay.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
          </td>
          <td>${pay.money}</td>
          <td>
            <c:choose>
              <c:when test="${pay.payType == 0}">网银</c:when>
              <c:when test="${pay.payType == 1}">银行转账</c:when>
              <c:when test="${pay.payType == 2}">现金</c:when>
              <c:when test="${pay.payType == 3}">其它</c:when>
              <c:when test="${pay.payType == 4}">红冲</c:when>
              <c:when test="${pay.payType == 5}">微信</c:when>
              <c:when test="${pay.payType == 6}">微信退费</c:when>
              <c:otherwise>未知</c:otherwise>
            </c:choose>
          </td>
          <td>${pay.remark}</td>
          <td>
            <c:if test="${pay.payType != 4 && pay.parentId == -1 && isAdmin == 0}">
              <button type="button" class="am-btn am-btn-primary am-round" onclick="redC(${pay.id})"><span class="am-icon-edit"></span> 红冲</button>
            </c:if>
          </td>
        </tr>
      </c:forEach>
    </table>
  </div>
</div>

<div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
  <div class="am-panel-hd am-cf" data-am-collapse="{target: '#buy'}">消费记录<span class="am-icon-chevron-down am-fr"></span></div>
  <div id="buy" class="am-in">
    <c:if test="${empty buyMap}">
      <span style="color: #ff000c;">没有找到相关数据</span>
    </c:if>
    <c:forEach var="map" items="${buyMap}">
      <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
        <tr>
          <td colspan="99" style="color:#0b76ac; font-weight:bold">${map.key}</td>
        </tr>
        <tr class="am-primary">
          <th style="width:30%">消费时间</th>
          <th style="width:10%">消费类型</th>
          <th style="width:10%">消费金额</th>
          <th>消费说明</th>
        </tr>
        <c:forEach var="detail" items="${map.value}">
          <c:forEach var="stuBuy" items="${detail.value}">
            <tr>
              <td>${stuBuy.createTime}</td>
              <td>
                <c:choose>
                  <c:when test="${stuBuy.type == 0}">购买教材</c:when>
                  <c:when test="${stuBuy.type == 1}">教材改价</c:when>
                  <c:otherwise>未知</c:otherwise>
                </c:choose>
              </td>
              <td>${stuBuy.money}</td>
              <td>${stuBuy.detail}</td>
            </tr>
          </c:forEach>
          <tr>
            <td></td>
            <td align="right">合计：</td>
            <td>${detail.key}</td>
            <td></td>
          </tr>
        </c:forEach>
      </table>
    </c:forEach>
  </div>
</div>
<script>
  function redC(id){
    app.openDialog('${pageContext.request.contextPath}/redCStuEP/open.htm?id='+id, '红冲', 500, 0.3, function(index){
      var remark = $("#remark").val();
      if(remark == ""){
        app.msg("请填写红冲备注", 1);
        return false;
      }
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/redCStuEP/redC.htm",
        data:$('#redCForm').serialize(),
        async: false,
        success: function(data) {
          if(data.state == 0){
            app.msg("提交成功！", 0);
            layer.closeAll();
            app.openOneBtnDialog('${pageContext.request.contextPath}/findStudentExpenseDetail/doFindStudentExpenseDetail.htm?code=${param.code}', '明细', 1000, 0.9);
          }else{
            app.msg(data.msg, 1);
          }
        }
      });
    });
  }
</script>
