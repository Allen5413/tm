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
        <!-- 确认添加订单的教材 -->
        <div id="addOrderTM_div" style="height: 99%;">
          <%@ include file="confirmOrderTMAdd.jsp" %>
        </div>
        <!-- 查询教材，选择教材添加 -->
        <div id="searchTM" style="display: none; height: 99%;">
          <%@ include file="searchTM.jsp" %>
        </div>
    </div>
</body>
</html>