<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="stuForm" name="stuForm" action="${pageContext.request.contextPath}/centerPay/doAddStu.htm" method="post">
	<input type="hidden" value="${spotCode}" id="spotCode" name="spotCode"/>
	<table width="90%">
		<tr height="40">
			<td align="right"><label >入学年：</label></td>
			<td>
				<input type="text" id="toStuYear_temp" name="toStuYear" />
			</td>
			<td align="right"><label >入学季：</label></td>
			<td>
				<select id="toStuQuarter" name="toStuQuarter">
					<option value="">--请选择-</option>
					<option value="0">春季</option>
					<option value="1">秋季</option>
				</select>
			</td>
			<td align="right"><label >专业：</label></td>
			<td>
				<select id="spec" name="spec" data-am-selected="{maxHeight: 500, searchBox: 1}">
					<option value="">--请选择-</option>
					<c:forEach var="spe" items="${speList}">
					<option value="${spe.code}" <c:if test="${param.spec == spe.code}">selected="selected" </c:if> >[${spe.code}]${spe.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr height="40">
			<td align="right"><label >层次：</label></td>
			<td>
				<select id="level" name="level">
					<option value="">--请选择--</option>
					<c:forEach var="level" items="${levelList}">
						<option value="${level.code}" <c:if test="${param.level == level.code}">selected="selected" </c:if> >${level.name}</option>
					</c:forEach>
				</select>
			</td>
			<td align="right"><label >学号：</label></td>
			<td>
				<input type="text" id="stuCode" name="stuCode" />
			</td>
			<td align="right"><label >姓名：</label></td>
			<td>
				<input type="text" id="stuName" name="stuName"/>
			</td>
		</tr>
		<tr>
			<td colspan="99" style="padding-left:20px;">
				<button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example"
						data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '查询'}"
						onclick="doSubForm(this)"><span class="am-icon-search"></span> 查询</button>
			</td>
		</tr>
	</table>
</form>
<p /><p />
<div id="datDiv"></div>
<script>
	$("select").selected();
	function doSubForm(btnObj){
		if(document.getElementById("toStuYear_temp").value == "" && document.getElementById("spec").value == "" && document.getElementById("level").value == "" && document.getElementById("stuCode").value == "" && document.getElementById("stuName").value == ""){
			app.msg("请输入入学年", 1);
			return;
		}

		$(btnObj).button('loading');
		setTimeout(function(){
			$.ajax({
				cache: true,
				type: "POST",
				url: "${pageContext.request.contextPath}/centerPay/doAddStu.htm",
				async: false,
				data: $('#stuForm').serialize(),
				success: function (data) {
					document.getElementById("datDiv").innerHTML = data;
					$("#peoSumLable").text("人数:" + $(".datTr").length);
					$(btnObj).button('reset');
				}
			});
		}, 100);
	}
</script>

