<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <%@ include file="/common/meta.jsp"%>
  <%@ include file="/common/taglibs.jsp"%>
</head>
<body>
<div class="main-content">
  <form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findStudentBookOrderPageByWhere/purchaseOrderListSearch.htm" method="post">
    <input type="hidden" id="page" name="page" value="${pageInfo.currentPage}"/>
    <ul class="create_info_list">
      <li>
        <table width="80%">
          <tr style="height: 30px;">
            <Td>
              <label class="lab_80">学期：</label>
              <select id="semesterId" name="semesterId">
                <c:forEach var="semester" items="${semesterList}">
                  <c:choose>
                    <c:when test="${method eq 'search'}">
                      <option value="${semester.id}" <c:if test="${param.semesterId == semester.id}">selected="selected" </c:if> >${semester.semester}</option>
                    </c:when>
                    <c:otherwise>
                      <option value="${semester.id}" <c:if test="${semester.isNowSemester == 0}">selected="selected" </c:if> >${semester.semester}</option>
                    </c:otherwise>
                  </c:choose>
                </c:forEach>
              </select>
            </Td>
            <Td>
              <label class="lab_80">省中心：</label>
              <select id="provCode" name="provCode">
                <option value="">请选择</option>
                <c:forEach var="prov" items="${provinceList}">
                  <option value="${prov.code}" <c:if test="${param.provCode eq prov.code}">selected="selected" </c:if> >${prov.name}</option>
                </c:forEach>
              </select>
            </Td>
            <Td>
              <label class="lab_80">学习中心：</label>
              <select id="spotCode" name="spotCode">
                <option value="">请选择</option>
                <c:forEach var="spot" items="${spotList}">
                  <option value="${spot.code}" <c:if test="${param.spotCode eq spot.code}">selected="selected" </c:if> >${spot.name}</option>
                </c:forEach>
              </select>
            </Td>
          </tr>
          <Tr style="height: 30px;">
            <td>
              <label class="lab_80">专业：</label>
              <select id="specCode" name="specCode">
                <option value="">请选择</option>
                <c:forEach var="spec" items="${specList}">
                  <option value="${spec.code}" <c:if test="${param.specCode eq spec.code}">selected="selected" </c:if> >${spec.name}</option>
                </c:forEach>
              </select>
            </td>
            <td>
              <label class="lab_80">层次：</label>
              <select id="levelCode" name="levelCode">
                <option value="">请选择</option>
                <c:forEach var="level" items="${levelList}">
                  <option value="${level.code}" <c:if test="${param.levelCode eq level.code}">selected="selected" </c:if> >${level.name}</option>
                </c:forEach>
              </select>
            </td>
            <td>
              <label class="lab_80">状态：</label>
              <select id="state" name="state">
                <option value="">请选择</option>
                <option value="0" <c:if test="${param.state eq '0'}">selected="selected" </c:if> >未确认</option>
                <option value="1" <c:if test="${param.state eq '1'}">selected="selected" </c:if> >已确认</option>
                <option value="2" <c:if test="${param.state eq '2'}">selected="selected" </c:if> >分拣中</option>
                <option value="3" <c:if test="${param.state eq '3'}">selected="selected" </c:if> >已打包</option>
                <option value="4" <c:if test="${param.state eq '4'}">selected="selected" </c:if> >已发出</option>
                <option value="5" <c:if test="${param.state eq '5'}">selected="selected" </c:if> >已签收</option>
              </select>
            </td>
          </Tr>
          <tr style="height: 30px;">
            <Td>
              <label class="lab_80">学号：</label>
              <input type="text" id="studentCode" name="studentCode" class="input_240" style="width: 200px;" value="${param.studentCode}" />
            </Td>
            <Td>
              <label class="lab_80">姓名：</label>
              <input type="text" id="studentName" name="studentName" class="input_240" style="width: 200px;" value="${param.studentName}" />
            </Td>
            <Td>
              <a class="com_btn_b" href="#" id="subBut" onclick="pageForm.submit();"><span>查&nbsp;询</span></a>&nbsp;&nbsp;
              <a class="com_btn_b" href="#" id="resetBut" onclick="resetForm();"><span>重&nbsp;置</span></a>
            </Td>
          </tr>
        </table>
      </li>
    </ul>
  </form>

  <Br><Br>
  <c:if test="${method eq 'search'}">
    <table class="table_slist" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <th style="width: 100px;">订单号</th>
        <th style="width: 100px;">省中心</th>
        <th style="width: 100px;">学习中心</th>
        <th style="width: 100px;">学号</th>
        <th style="width: 100px;">姓名</th>
        <th style="width: 100px;">专业</th>
        <th style="width: 100px;">层次</th>
        <th style="width: 100px;">教材数量</th>
        <th style="width: 100px;">金额</th>
        <th style="width: 100px;">状态</th>
        <th style="width: 100px;">操作人</th>
        <th style="width: 80px;">操作时间</th>
        <th style="width: 120px;">操作</th>
      </tr>
      <c:if test="${empty pageInfo || empty pageInfo.pageResults}">
        <tr>
          <td colspan="20" align="center" style="color: red;">没有找到相关数据</td>
        </tr>
      </c:if>
      <c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">
        <tr>
          <td>${json.orderCode}</td>
          <td>${json.provName}</td>
          <td>(${json.spotCode})${json.spotName}</td>
          <td>${json.studentCode}</td>
          <td>${json.name}</td>
          <td>${json.specName}</td>
          <td>${json.levelName}</td>
          <td>${json.tmCount}</td>
          <td>${json.totalPrice}</td>
          <td>
            <c:choose>
              <c:when test="${json.state == 0}">未确认</c:when>
              <c:when test="${json.state == 1}">已确认</c:when>
              <c:when test="${json.state == 2}">分拣中</c:when>
              <c:when test="${json.state == 3}">已打包</c:when>
              <c:when test="${json.state == 4}">已发出</c:when>
              <c:when test="${json.state == 5}">已签收</c:when>
              <c:otherwise>未知</c:otherwise>
            </c:choose>
          </td>
          <td>${json.operator}</td>
          <td>${json.operateTime}</td>
          <td>
            <a href="#" onclick="addStock('${json.orderCode}')" style="color: #0092DC">查看明细</a>&nbsp;&nbsp;
          </td>
        </tr>
      </c:forEach>
    </table>
  </c:if>
</body>
</html>
<script>
  function addStock(code){
    parent.openDialog('查看订单教材', 0.5, 0.8, '${pageContext.request.contextPath}/findStudentBookOrderListByOrderCode/doFindStudentBookOrderListByOrderCode.htm?orderCode='+code);
  }

  function resetForm(){
    $("#provCode, #spotCode, #specCode, #levelCode, #studentCode, #studentName, #state").val('');
    <c:forEach var="semester" items="${semesterList}">
      <c:if test="${semester.isNowSemester == 0}">
        $("#semesterId").val(${semester.id});
      </c:if>
    </c:forEach>
  }
</script>