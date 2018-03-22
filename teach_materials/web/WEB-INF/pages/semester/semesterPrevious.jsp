<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/common/taglibs.jsp"%>
<form id="editForm" action="#" method="post">
  <div class="main-content">
    <ul class="create_info_list">
      <li>
        <label class="lab_80">学期年份：</label>
        <label class="lab_80">${semester.year}</label>
      </li>
      <li>
        <label class="lab_80">学期季度：</label>
        <label class="lab_80"><C:if test="${semester.quarter == 1}">
          秋季
        </C:if>
        <C:if test="${semester.quarter == 0}">
          春季
        </C:if></label>
      </li>
      <li>
        <label class="lab_80">操作人员：</label>
        <label class="lab_80">${semester.operator}</label>
      </li>
      <li>
        <label class="lab_80 left">操作时间：</label>
        <label style="width: auto"><fmt:formatDate value="${semester.operateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></label>
        <div class="clear"></div>
      </li>
    </ul>
  </div>
  </form>