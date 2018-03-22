<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
			<%--<tr>--%>
				  <%--<th><a href="#" onclick="checkAll();"></><font color="blue">全选</font></a>&nbsp;&nbsp;&nbsp;<a href="#" onclick="checkNall();"><font color="blue">反选</font></a></th>--%>
			      <%--<th>姓名</th>--%>
			      <%--<th>省中心</th>--%>
			      <%--<th>学习中心</th>--%>
			      <%--<th>入学年季</th>--%>
			      <%--<th>学号</th>--%>
			      <%--<th>专业</th>--%>
			      <%--<th>层次</</th>--%>
	    		<%--</tr>--%>
			<%--<c:if test="${empty pagInfo || empty pagInfo.pageResults}">--%>
	     			<%--<tr>--%>
	        			<%--<td colspan="8" align="center" style="color: red;">没有找到相关数据</td>--%>
	      			<%--</tr>--%>
	    		<%--</c:if>--%>
	    		<%--<c:forEach var="json" items="${pagInfo.pageResults}" varStatus="status">--%>
	      		<%--<tr class="datTr">--%>
	      			<%--<td><input type="checkbox" name="stuche" onclick="changeColor(this)" value="${json.toStuFie},${json.stuCode},${json.stuName}"/></td>--%>
			        <%--<td>${json.stuName}</td>--%>
			        <%--<td>${json.proCode}</td>--%>
			        <%--<td>${json.spoCode}</td>--%>
			        <%--<td>${json.toStuFie}</td>--%>
			        <%--<td>${json.stuCode}</td>--%>
			        <%--<td>${json.spe}</td>--%>
			        <%--<td>${json.level}</td>--%>
	      		<%--</tr>--%>
	    		<%--</c:forEach>--%>
	    	<%--</table>--%>

<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
	<tr>
		<td colspan="999" style="background-color:#FFF">
			<label >预交金额：</label>
			<input type="text" id="money"/>
			<label class="lab_80" id="peoSumLable"></label>
		</td>
	</tr>
	<tr class="am-primary">
		<th>
			<a href="#" onClick="app.checkAll('cb')">全选</a>|<a href="#" onClick="app.checkNAll('cb')">反选</a>
		</th>
		<th>姓名</th>
		<th>学习中心</th>
		<th>入学年季</th>
		<th>学号</th>
		<th>专业</th>
		<th>层次</th>
	</tr>
	<c:if test="${empty pagInfo || empty pagInfo.pageResults}">
		<tr>
			<td colspan="8" align="center" style="color: red;">没有找到相关数据</td>
		</tr>
	</c:if>
	<c:forEach var="json" items="${pagInfo.pageResults}" varStatus="status">
		<tr class="datTr">
			<td>
				<label class="am-checkbox am-secondary" style="margin-top:5px; margin-left:24px;">
					<input type="checkbox" name="cb" value="${json.toStuFie},${json.stuCode},${json.stuName}" onclick="changeColor(this)" data-am-ucheck>
				</label>
			</td>
			<td>${json.stuName}</td>
			<td>${json.spoCode}</td>
			<td>${json.toStuFie}</td>
			<td>${json.stuCode}</td>
			<td>${json.spe}</td>
			<td>${json.level}</td>
		</tr>
	</c:forEach>
</table>
	    	
