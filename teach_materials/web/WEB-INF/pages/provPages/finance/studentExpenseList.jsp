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
  <form id="pageForm" name="pageForm" action="/findStuEPage/findStuEPageByWhere.htm" method="post">
    <input type="hidden" id="page" name="page" value="${pageInfo.currentPage}"/>
    <ul class="create_info_list">
      <li>
        <label class="lab_80">省中心：</label>
        <select id="provCode" name="provCode" onchange="selectProv()">
          <option value="">请选择</option>
          <c:forEach var="prov" items="${provinceList}">
            <option value="${prov.code}" <c:if test="${param.provCode eq prov.code}">selected="selected" </c:if> >${prov.name}</option>
          </c:forEach>
        </select>
        <label class="lab_80">学习中心：</label>
        <select id="spotCode" name="spotCode">
          <option value="">请选择</option>
          <c:forEach var="spot" items="${spotList}">
            <option value="${spot.code}" <c:if test="${param.spotCode eq spot.code}">selected="selected" </c:if> >${spot.name}</option>
          </c:forEach>
        </select>
        <label class="lab_80">学号：</label>
        <input type="text" id="studentCode" name="studentCode" class="input_240" style="width: 200px;" value="${param.studentCode}" />
        <label class="lab_80">姓名：</label>
        <input type="text" id="name" name="name" class="input_240" style="width: 200px;" value="${param.name}" />
        <label class="lab_80">状态：</label>
        <select id="state" name="state">
          <option value="">请选择</option>
          <option value="1" <c:if test="${param.state eq '1'}">selected="selected" </c:if> >未结算</option>
          <option value="0" <c:if test="${param.state eq '0'}">selected="selected" </c:if>>已结算</option>
        </select>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <a class="com_btn_b" href="#" id="subBut" onclick="pageForm.submit();"><span>查&nbsp;询</span></a>&nbsp;&nbsp;
        <a class="com_btn_b" href="#" id="resetBut" onclick="resetForm();"><span>重&nbsp;置</span></a>
      </li>
    </ul>
  </form>
  <table class="table_slist" cellpadding="0" cellspacing="0" width="100%">
    <tr>
      <th style="width: 30px;">序号</th>
      <th style="width: 80px;">学习中心编号</th>
      <th style="width: 350px;">学习中心名称</th>
      <th style="width: 120px;">学号</th>
      <th style="width: 120px;">姓名</th>
      <th style="width: 100px;">已缴费金额</th>
      <th style="width: 100px;">消费金额</th>
      <th style="width: 100px;">余额</th>
      <th style="width: 60px;">状态</th>
      <th>操作</th>
    </tr>
    <c:if test="${empty pageInfo || empty pageInfo.pageResults}">
      <tr>
        <td colspan="20" align="center" style="color: red;">没有找到相关数据</td>
      </tr>
    </c:if>
    <c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">
      <tr>
        <td align="center">${status.index+1}</td>
        <td>${json.spotCode}</td>
        <td>${json.spotName}</td>
        <td>${json.code}</td>
        <td>${json.name}</td>
        <td>${json.pay}</td>
        <td>${json.buy}</td>
        <td>${json.pay - json.buy}</td>
        <td>
          <c:choose>
            <c:when test="${json.state == 0}">已结算</c:when>
            <c:when test="${json.state == 1}">未结算</c:when>
            <c:otherwise>未知</c:otherwise>
          </c:choose>
        </td>
        <td>
          <a href="#" onclick="addMoney('${json.code}')" style="color: #0092DC">缴费</a>&nbsp;&nbsp;
          <a href="#" onclick="detail(${json.code})" style="color: #0092DC">明细</a>&nbsp;&nbsp;
        </td>
      </tr>
    </c:forEach>
    <%@ include file="/common/page.jsp"%>
  </table>
</div>
</body>
</html>
<script>
  function addMoney(code){
    parent.openDialog('缴费', 0.25, 0.23, '${pageContext.request.contextPath}/addStuEP/openAddStuEPPage.htm?code='+code);
  }

  function detail(code){
    parent.openDialog('明细', 0.9, 0.8, '${pageContext.request.contextPath}/findStudentExpenseDetail/doFindStudentExpenseDetail.htm?code='+code);
  }

  function selectProv(){
    var provCode = $("#provCode").val();
    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/findSpotByProvCode/doFindSpotByProvCode.htm",
      data:{"provCode":provCode},
      async: false,
      success: function(result) {
        $("#spotCode option").remove();
        $("#spotCode").append("<option value=''>请选择</option>");
        if(typeof(result.spotArray) != "undefined"){
          for(var i=0; i<result.spotArray.length; i++){
            var spot = result.spotArray[i];
            $("#spotCode").append("<option value='"+spot.code+"'>"+spot.name+"</option>");
          }
        }
      }
    });
  }

  function resetForm(){
    $("#provCode, #spotCode, #name, #studentCode, #state").val('');
  }
</script>
