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
		<form id="queryStuForm" name="queryStuForm" action="${pageContext.request.contextPath}/centerPay/querySpotPay.htm" method="post">
			<input type="hidden" id="page" name="page" value="${pageInfo.currentPage}"/>
			<ul class="create_info_list">
				 <li>
				 	<label class="lab_80">省中心：</label>
			        <select id="provCode" name="provinceId" onchange="selectProv()">
			          <option value="">请选择</option>
			          <c:forEach var="prov" items="${provinceList}">
			            <option value="${prov.code}" <c:if test="${param.provinceId eq prov.code}">selected="selected" </c:if> >${prov.name}</option>
			          </c:forEach>
			        </select>
			        <label class="lab_80">学习中心：</label>
			        <select id="spotCode" name="spotId">
			          <option value="">请选择</option>
			          <c:forEach var="spot" items="${spotList}">
			            <option value="${spot.code}" <c:if test="${param.spotId eq spot.code}">selected="selected" </c:if> >[${spot.code}]${spot.name}</option>
			          </c:forEach>
			        </select>
			       <label class="lab_80">学期：</label>
			        <select id="semesterId" name="semesterId">
			        	<option value="">请选择</option>
			          <c:forEach var="semester" items="${semesterList}">
			            <option value="${semester.id}" <c:if test="${param.semesterId == semester.id}">selected="selected" </c:if> >${semester.semester}</option>
			          </c:forEach>
       				 </select>
        			
        			 &nbsp;&nbsp;&nbsp;&nbsp;
			        <a class="com_btn_b" href="#" id="subBut" onclick="pageForm.submit();"><span>查&nbsp;询</span></a>&nbsp;&nbsp;
			        <a class="com_btn_b" href="#" id="resetBut" onclick="resetForm();"><span>重&nbsp;置</span></a>
				 </li>
			</ul>
		</form>
	</div>
</body>