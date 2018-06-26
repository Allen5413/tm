<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/placeOrder/queryBeginSchdeu.htm" method="post">
	<table width="90%">
		<tr height="40">
			<td align="right"><label >学习中心：</label></td>
			<td>
				<select id="spotCode" name="spotCode" data-am-selected="{maxHeight: 500, searchBox: 1}" onchange="app.changeSelect(this)">
					<option value=""></option>
					<option value="null">全部</option>
					<c:forEach var="spot" items="${spotList}">
						<option value="${spot.code}" <c:if test="${param.spotCode eq spot.code}">selected="selected" </c:if>>(${spot.code})${spot.name}</option>
					</c:forEach>
				</select>
			</td>
			<td align="right"><label >入学年：</label></td>
			<td>
				<select id="enYear" name="enYear" onchange="app.changeSelect(this)">
					<option value=""></option>
					<option value="null">全部</option>
					<c:forEach var="enYearBean" items="${enYearList}">
						<option value="${enYearBean}" <c:if test="${param.enYear eq enYearBean}">selected="selected" </c:if> >${enYearBean}</option>
					</c:forEach>
				</select>
			</td>
			<td align="right"><label >入学季：</label></td>
			<td>
				<select id="enQuarter" name="enQuarter" onchange="app.changeSelect(this)">
					<option value=""></option>
					<option value="null">全部</option>
					<option value="0" <c:if test="${param.enQuarter eq 0}">selected="selected" </c:if>>春季</option>
					<option value="1" <c:if test="${param.enQuarter eq 1}">selected="selected" </c:if>>秋季</option>
				</select>
			</td>
		</tr>
		<tr height="40">
			<td align="right"><label >专业：</label></td>
			<td>
				<select id="specCode" name="specCode" data-am-selected="{maxHeight: 500, searchBox: 1}" onchange="app.changeSelect(this)">
					<option value=""></option>
					<option value="null">全部</option>
					<c:forEach var="specCodeBean" items="${specList}">
						<option value="${specCodeBean.code}" <c:if test="${param.specCode eq specCodeBean.code}">selected="selected" </c:if> >[${specCodeBean.code}]${specCodeBean.name}</option>
					</c:forEach>
				</select>
			</td>
			<td align="right"><label >层次：</label></td>
			<td>
				<select id="levelCode" name="levelCode" onchange="app.changeSelect(this)">
					<option value=""></option>
					<option value="null">全部</option>
					<c:forEach var="levelCodeBean" items="${levelList}">
						<option value="${levelCodeBean.code}" <c:if test="${param.levelCode eq levelCodeBean.code}">selected="selected" </c:if> >${levelCodeBean.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="99" style="padding-left:20px;">
				<button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example"
						data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '查询超时'}"
						onclick="subTheForm(this)"><span class="am-icon-search"></span> 查询</button>
			</td>
		</tr>
	</table>
</form>
<p /><p />
<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
	<tr class="am-primary">
		<th style="width:5%">序号</th>
		<th style="width:5%">入学年</th>
		<th style="width:5%">入学季</th>
		<th style="width:15%">专业</th>
		<th style="width:5%">层次</th>
		<th style="width:30%">邮寄地址</th>
		<th style="width:5%">收件人</th>
		<th style="width:5%">手机</th>
		<th style="width:5%">座机</th>
		<th style="width:5%">邮编</th>
		<th style="width:5%">人数</th>
		<th style="width:10%">操作</th>
	</tr>
	<c:if test="${empty beginEduList}">
		<tr>
			<td colspan="999" align="center" style="color: red;">没有找到相关数据</td>
		</tr>
	</c:if>
	<c:forEach var="edu" items="${beginEduList}" varStatus="status">
		<tr>
			<td>${status.index+1}</td>
			<td>${edu.enterYear}</td>
			<c:if test="${edu.quarter == 0}">
				<td>春季</td>
			</c:if>
			<c:if test="${edu.quarter == 1}">
				<td>秋季</td>
			</c:if>
			<td>${edu.specName}</td>
			<td>${edu.levelName}</td>
			<td><input type="text" id="address" value="${spot.address}" style="width: 300px;" /></td>
			<td><input type="text" id="adminName" value="${spot.adminName}" style="width: 50px;" /></td>
			<td><input type="text" id="phone" value="${spot.phone}" /></td>
			<td><input type="text" id="tel" value="${spot.tel}" /></td>
			<td><input type="text" id="postalCode" value="${spot.postalCode}" style="width: 50px;" maxlength="6" /></td>
			<td><input type="text" id="personNumInp" style="width: 50px;" /></td>
			<td>
				<div class="am-btn-group">
					<button id="sub" type="button" class="am-btn am-btn-primary am-round" onClick="toCreateOrder('${edu.enterYear}','${edu.quarter}','${edu.specCode}','${edu.levelCode}')"><span class="am-icon-plus"></span> 提交</button>
				</div>
			</td>
		</tr>
	</c:forEach>
</table>
<div id="orderDetailDiv"></div>

<script>
	$("select").selected();
	function subTheForm(btnObj){
		if($("#spotCode").val() == ""){
			app.msg("请选择学习中心", 1);
			return;
		}
		if($("#enYear").val() == ""){
			app.msg("请选择学年", 1);
			return;
		}
		if($("#enQuarter").val() == ""){
			app.msg("请选择学季", 1);
			return;
		}
		if($("#specCode").val() == ""){
			app.msg("请选择专业", 1);
			return;
		}
		if($("#levelCode").val() == ""){
			app.msg("请选择层次", 1);
			return;
		}
		app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'), btnObj)
	}

	function toCreateOrder(enYear,enQuarter,specCode,levelCode){
		var personNumInp = $("#personNumInp").val().trim();
		var address = $("#address").val().trim();
		var adminName = $("#adminName").val().trim();
		var phone = $("#phone").val().trim();
		var tel = $("#tel").val().trim();
		var postalCode = $("#postalCode").val().trim();
		if(address == ""){
			alert("邮寄地址不能为空");
			return;
		}
		if(adminName == ""){
			alert("收件人不能为空");
			return;
		}
		if(phone == "" && tel == ""){
			alert("手机和座机不能都为空");
			return;
		}
		if(postalCode == ""){
			alert("邮政编码不能为空");
			return;
		}
		if(personNumInp == ""){
			alert("数量不能为空");
			return;
		}

		if(isNaN(personNumInp)){
			alert("请输入数字");
			return;
		}
		$.ajax({
			type: "POST",
			url:"${pageContext.request.contextPath}/placeOrder/createPlaceOrder.htm",
			data:{"enYear":enYear,
				"enQuarter":enQuarter,
				"specCode":specCode,
				"levelCode":levelCode,
				"personNum":$("#personNumInp").val(),
				"spotCode":"${spot.code}",
				"address":address,
				"adminName":adminName,
				"phone":phone,
				"tel":tel,
				"postalCode":postalCode},
			async: false,
			success: function(data) {
				$("#orderDetailDiv").html(data);
				$("#sub").hide();
			}
		});
	}
</script>