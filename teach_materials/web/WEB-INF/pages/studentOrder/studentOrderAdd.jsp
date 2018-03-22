<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!-- 确认添加订单的教材 -->
<div id="addOrderTM_div" style="height: 99%;">
  <ul class="create_info_list">
    <li>
      <label class="lab_80">学号：</label>
      <input type="text" id="studentCodeText" />
    </li>
  </ul>
  <%@ include file="confirmOrderTMAdd.jsp" %>
</div>
<!-- 查询教材，选择教材添加 -->
<div id="searchTM" style="display: none; height: 99%;">
  <%@ include file="searchTM.jsp" %>
</div>