<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findStuEPage/findStuEPageByWhere.htm" method="post">
  <input type="hidden" name="method" value="search"/>
  <input type="hidden" id="rows" name="rows" />
  <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
  <table width="90%">
    <tr height="40">
      <td align="right"><label >学期：</label></td>
      <td>
        <select id="semesterId" name="semesterId" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <c:forEach var="semester" items="${semesterList}">
            <option value="${semester.id}" <c:if test="${param.semesterId == semester.id}">selected="selected" </c:if> >${semester.semester}</option>
          </c:forEach>
        </select>
      </td>
      <td align="right"><label >入学年：</label></td>
      <td><input type="text" id="enterYear" name="enterYear" value="${param.enterYear}" /></td>
      <td align="right"><label >入学季：</label></td>
      <td>
        <select id="enterQuarter" name="enterQuarter" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <option value="0" <c:if test="${param.enterQuarter eq '0'}">selected="selected" </c:if> >春季</option>
          <option value="1" <c:if test="${param.enterQuarter eq '1'}">selected="selected" </c:if> >秋季</option>
        </select>
      </td>
    </tr>
    <tr height="40">
      <td align="right"><label >省中心：</label></td>
      <td>
        <select id="provCode" name="provCode" onchange="app.selectProv(this, 'spotCode', '${pageContext.request.contextPath}')">
          <option value=""></option>
          <option value="null">全部</option>
          <c:forEach var="prov" items="${provinceList}">
            <option value="${prov.code}" <c:if test="${param.provCode eq prov.code}">selected="selected" </c:if> >${prov.name}</option>
          </c:forEach>
        </select>
      </td>
      <td align="right"><label >学习中心：</label></td>
      <td>
        <select id="spotCode" name="spotCode" data-am-selected="{maxHeight: 500, searchBox: 1}" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <c:forEach var="spot" items="${spotList}">
            <option value="${spot.code}" <c:if test="${param.spotCode eq spot.code}">selected="selected" </c:if> >[${spot.code}]${spot.name}</option>
          </c:forEach>
        </select>
      </td>
      <td align="right"><label >状态：</label></td>
      <td>
        <select id="state" name="state" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <option value="1" <c:if test="${param.state eq '1'}">selected="selected" </c:if> >未结算</option>
          <option value="0" <c:if test="${param.state eq '0'}">selected="selected" </c:if>>已结算</option>
        </select>
      </td>
    </tr>
    <tr height="40">
      <td align="right"><label >学号：</label></td>
      <td><input type="text" id="studentCode" name="studentCode" value="${param.studentCode}" /></td>
      <td align="right"><label >姓名：</label></td>
      <td><input type="text" id="name" name="name" value="${param.name}" /></td>
    </tr>
    <tr>
      <td colspan="99" style="padding-left:20px;">
        <button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example"
                data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '查询超时'}"
                onclick="app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'), this)"><span class="am-icon-search"></span> 查询</button>
      </td>
    </tr>
  </table>
</form>
<p /><p />
<c:if test="${'search' eq param.method}">
  <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
    <tr>
      <td colspan="999" style="background-color:#FFF">
        <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="downExcel()"><span class="am-icon-download"></span> 下载</button>
      </td>
    </tr>
    <tr class="am-primary">
      <th style="width: 3%">序号</th>
      <th style="width: 22%">学习中心</th>
      <th style="width: 10%">学号</th>
      <th style="width: 6%">姓名</th>
      <th style="width: 18%">专业</th>
      <th style="width: 6%">层次</th>
      <th style="width: 6%">已缴费金额</th>
      <th style="width: 6%">消费金额</th>
      <th style="width: 5%">余额</th>
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
        <td>[${json.spotCode}]${json.spotName}</td>
        <td>${json.code}</td>
        <td>${json.name}</td>
        <td>${json.specName}</td>
        <td>${json.levelName}</td>
        <td>${json.pay}</td>
        <td>${json.buy}</td>
        <td>${json.acc}</td>
        <td>
          <div class="am-btn-group">
            <button type="button" class="am-btn am-btn-primary am-round" onClick="addMoney('${json.code}')"><span class="am-icon-plus"></span> 交费</button>
            <button type="button" class="am-btn am-btn-primary am-round" onClick="detail('${json.code}')"><span class="am-icon-th-list"></span> 明细</button>
            <button type="button" class="am-btn am-btn-primary am-round" onClick="downStuEB('${json.code}')"><span class="am-icon-download"></span> 下载明细</button>
            <button type="button" class="am-btn am-btn-primary am-round" onClick="refundWX('${json.code}')"><span class="am-icon-download"></span> 微信退费</button>
          </div>
        </td>
      </tr>
    </c:forEach>
    <tr>
      <td colspan="7" align="right">合计：</td>
      <td>${totalPayPrice}</td>
      <td>${totalBuyPrice}</td>
      <td>${totalAccPrice}</td>
      <td></td>
    </tr>
  </table>
  <%@ include file="/common/page2.jsp"%>
</c:if>
<script>
  $("select").selected();
  function addMoney(code){
    var url = '${pageContext.request.contextPath}/addStuEP/openAddStuEPPage.htm';
    if(code != "" && code.length > 0 ){
      url += "?code="+code;
    }
    app.openDialog(url, '交费', 600, 0.45, function(index){
      var studentCode = $("#addPay_studentCode").val().trim();
      var money = $("#money").val().trim();
      var wdate = $("#wdate").val().trim();
      var payType = $('input:radio:checked').val();
      if(studentCode == ""){
        app.msg("请输入学号", 1);
        return;
      }
      if(!vaild.vaildMoney(money)){
        app.msg("请输入正确的金额", 1);
        return;
      }
      if(wdate == ""){
        app.msg("请选择到账时间", 1);
        return;
      }
      if(typeof (payType) == "undefined"){
        app.msg("请选择到账类型", 1);
        return;
      }
      $("#arrivalTime").val(wdate+" 00:00:00");
      app.add("${pageContext.request.contextPath}/addStuEP/stuEPAdd.htm", $('#addForm').serialize(), index);
    });
  }

  function refundWX(code){
    app.openDialog('${pageContext.request.contextPath}/refundWxStuEByCode/open.htm', '微信退款', 550, 0.4, function(index){
      var money = $("#money").val().trim();
      if(!vaild.vaildMoney(money)){
        app.msg("请输入正确的金额", 1);
        return;
      }
      app.add("${pageContext.request.contextPath}/wxRefund/refund.htm", {"code":code, "money":money}, index);
    });
  }

  function detail(code){
    app.openOneBtnDialog('${pageContext.request.contextPath}/findStudentExpenseDetail/doFindStudentExpenseDetail.htm?code='+code, '明细', 1000, 0.9);
  }

  function downExcel(){
    var semesterId = $("#semesterId").val();
    var provCode = $("#provCode").val();
    var spotCode = $("#spotCode").val();
    var studentCode = $("#studentCode").val();
    var name = $("#name").val();
    var state = $("#state").val();
    var enterYear = $("#enterYear").val();
    var enterQuarter = $("#enterQuarter").val();

    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/downStuE/down.htm?spotCode="+spotCode+"&semesterId="+semesterId+"&provCode="+provCode+"&studentCode="+studentCode+"&name="+name+"&state="+state+"&enterYear="+enterYear+"&enterQuarter="+enterQuarter,
      data:{},
      async: false,
      success: function(data) {
        open("${pageContext.request.contextPath}"+data);
      }
    });
  }

  function downStuEB(code){
    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/downStuEB/down.htm?code="+code,
      data:{},
      async: false,
      success: function(data) {
        open("${pageContext.request.contextPath}"+data);
      }
    });
  }
</script>

