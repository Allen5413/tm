<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findSpotReplacePayPageByWhere/find.htm" method="post">
  <input type="hidden" id="rows" name="rows" />
  <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
  <label >学习中心：</label>[${spot.code}]${spot.name}
  &nbsp;&nbsp;&nbsp;&nbsp;
  <label >状态：</label>
  <select id="state" name="state" onchange="app.changeSelect(this)">
    <option value=""></option>
    <option value="null">全部</option>
    <option value="0" <c:if test="${param.state eq '0'}">selected="selected" </c:if> >待审核</option>
    <option value="1" <c:if test="${param.state eq '1'}">selected="selected" </c:if> >审核通过</option>
    <option value="2" <c:if test="${param.state eq '2'}">selected="selected" </c:if> >审核不通过</option>
  </select>
  &nbsp;&nbsp;&nbsp;&nbsp;

  <button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example"
          data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '查询超时'}"
          onclick="app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'), this)"><span class="am-icon-search"></span> 查询</button>
</form>
<p /><p />
<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <tr class="am-primary">
    <th style="width: 30px">序号</th>
    <th>中心编号</th>
    <th>中心名称</th>
    <th>交费金额</th>
    <th>支付方式</th>
    <th>交费人</th>
    <th>交费日期</th>
    <th>审核人</th>
    <th>审核日期</th>
    <th>状态</th>
    <th>备注</th>
    <th>操作</th>
  </tr>
  <c:if test="${empty pageInfo || empty pageInfo.pageResults}">
    <tr>
      <td colspan="8" align="center" style="color: red;">没有找到相关数据</td>
    </tr>
  </c:if>
  <c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">
    <tr>
      <td align="center">${status.index+1}</td>
      <td>${json.spotCode}</td>
      <td>${json.spotName}</td>
      <td>${json.money}</td>
      <c:if test="${json.payWay == 1}">
        <td>其他</td>
      </c:if>
      <c:if test="${json.payWay == 2}">
        <td>工商银行</td>
      </c:if>
      <c:if test="${json.payWay == 3}">
        <td>支付宝</td>
      </c:if>
      <c:if test="${json.payWay == null || json.payWay == '' }">
        <td></td>
      </c:if>
      <td>${json.creator}</td>
      <td>${json.createTime}</td>
      <td>${json.verifyer}</td>
      <td>${json.verifyTime}</td>
      <td>
        <c:if test="${json.state == 0}">待审核</c:if>
        <c:if test="${json.state == 1}">审核通过</c:if>
        <c:if test="${json.state == 2}">审核未通过</c:if>
      </td>
      <td>${json.remark}</td>
      <td>
        <div class="am-btn-group">
          <button type="button" class="am-btn am-btn-primary am-round" onClick="findDetail(${json.id}, ${json.state})"><span class="am-icon-th-list"></span> 查看</button>
          <c:if test="${json.state == 1}">
            <button type="button" class="am-btn am-btn-primary am-round" onClick="downDetail('${json.polId}')"><span class="am-icon-download"></span> 下载</button>
          </c:if>
        </div>
      </td>
    </tr>
  </c:forEach>
</table>
<%@ include file="/common/page2.jsp"%>
<script>
  $("select").selected();
  function findDetail(id, state){
    if(0 == state){
      app.openAuditBtnDialog("${pageContext.request.contextPath}/findSpotReplacePayDetailBySrpId/find.htm?id="+id, "查看交费明细", 900, 0.8, function(index, flag){
        audit(index, flag);
      });
    }else{
      app.openOneBtnDialog("${pageContext.request.contextPath}/findSpotReplacePayDetailBySrpId/find.htm?id="+id, "查看交费明细", 900, 0.8);
    }
  }

  function downDetail(polId){
    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/centerPay/downSpotDetail.htm?polId=" + polId,
      data:{},
      async: false,
      success: function(data) {
        open("${pageContext.request.contextPath}"+data);
      }
    });
  }

  function audit(index, ope){
    if(0 == ope){
      if($("#wdate").val().trim() == ""){
        app.msg("请选择到账时间", 1);
        return;
      }
      $("#arrivalTime").val($("#wdate").val() + " 00:00:00");
      $("#state2").val(1);
    }else{
      $("#state2").val(2);
    }
    $.ajax({
      type: "POST",
      url:"${pageContext.request.contextPath}/auditSpotReplacePay/audit.htm",
      data:$('#hidForm').serialize(),
      async: false,
      success: function(data) {
        if(data.state == 0){
          app.msg("操作成功！", 0);
          $("#searchBtn").click();
          layer.close(index);
        }else{
          app.msg("操作失败！"+data.msg, 1);
        }
      }
    });
  }
</script>
