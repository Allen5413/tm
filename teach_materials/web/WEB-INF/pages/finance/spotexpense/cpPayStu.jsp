<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="stuForm" name="stuForm" action="${pageContext.request.contextPath}/centerPay/doFindAddStu.htm" method="post">
	<input type="hidden" value="${param.spotCode}" id="spotCode" name="spotCode"/>
	<input type="hidden" value="${param.semesterId}" id="semesterId" name="semesterId"/>
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
						data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '<span class=am-icon-search></span> 查询'}"
						onclick="doSubForm(this)"><span class="am-icon-search"></span> 查询</button>
			</td>
		</tr>
	</table>
</form>
<p /><p />
<table id="dataTable" class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
	<tr>
		<td colspan="999" style="background-color:#FFF">
			<button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="down()"><span class="am-icon-download"></span> 下载</button>
		</td>
	</tr>
	<tr class="am-primary">
		<th>入学年季</th>
		<th>学号</th>
		<th>姓名</th>
		<th>欠款金额</th>
		<th>操作</th>
	</tr>
	<c:if test="${empty jsonObject || empty jsonObject.data}">
		<tr>
			<td colspan="8" align="center" style="color: red;">没有找到相关数据</td>
		</tr>
	</c:if>
	<c:forEach var="json" items="${jsonObject.data}" varStatus="status">
		<tr class="cpAddStuTr">
			<td>${json.toStuYear}</td>
			<td>${json.stuCode}</td>
			<td>${json.stuName}</td>
			<td><input type="text" value="${json.stuOwn}" id="ownTex" name="ownTex" onchange="doCaluteSum();"></td>
			<td>
				<div class='am-btn-group'>
					<button type='button' class='am-btn am-btn-primary am-round' onClick='delTr(this)'><span class='am-icon-trash-o'></span> 删除</button>
					<button type='button' class='am-btn am-btn-primary am-round' onClick="detail('${json.stuCode}')"><span class='am-icon-th-list'></span> 查看明细</button>
				</div>
			</td>
		</tr>
	</c:forEach>
	<tr id='sumTr'>
		<td></td>
		<td></td>
		<td id='sumTd'>合计：${jsonObject.totalPrice}</td>
		<td></td>
		<td></td>
	</tr>
</table>
<script>
	$("select").selected();
	function doCaluteSum(){
		var trArr = $(".cpAddStuTr");
		var sumMon = 0;
		for(var i = 0;i < trArr.length;i ++){
			if((trArr[i].id) != "title"){
				if(trArr[i].id != "sumTr"){
					var money = $(trArr[i]).find("td").eq(3).find("input").val();
					if(typeof (money) == "undefined" || money == ""){
						money = 0;
					}
					sumMon += parseFloat(money);
				}
			}
		}
		document.getElementById("sumTd").innerText = "合计：" + sumMon.toFixed(2);
		$("#sumHid").val(sumMon.toFixed(2));
	}

	function delTr(vA){
		$(vA).parent().parent().parent().remove();
		doCaluteSum();
	}

	function detail(code){
		app.openOneBtnDialog('${pageContext.request.contextPath}/findStudentExpenseDetail/doFindStudentExpenseDetail.htm?code='+code, '明细', 1000, 0.9);
	}

	function down(){
		$.ajax({
			cache: true,
			type: "POST",
			url:"${pageContext.request.contextPath}/centerPay/downPayStu.htm",
			data:$("#stuForm").serialize(),
			async: false,
			success: function(data) {
				open("${pageContext.request.contextPath}"+data);
			}
		});
	}

	function doSubForm(btnObj){
		$(btnObj).button('loading');
		setTimeout(function(){
			$.ajax({
				cache: true,
				type: "POST",
				url: "${pageContext.request.contextPath}/centerPay/toFindPayStu.htm",
				async: false,
				data: $('#stuForm').serialize(),
				success: function (result) {
					var num = 0;
					var table = $("#dataTable");
					table.find("tr").each(function(){
						num++;
						if(num > 2){
							$(this).remove();
						}
					});
					if(result.data.length > 0){
						for(var i=0; i<result.data.length; i++){
							var json = result.data[i];
							var tr = $("<tr class='cpAddStuTr'></tr>");
							var td = $("<td>"+json.toStuYear+"</td>");
							var td2 = $("<td>"+json.stuCode+"</td>");
							var td3 = $("<td>"+json.stuName+"</td>");
							var td4 = $("<td><input type='text' value='"+json.stuOwn+"' id='ownTex' name='ownTex' onchange='doCaluteSum();'></td>");
							var td5 = $("<td><div class='am-btn-group'> " +
							"<button type='button' class='am-btn am-btn-primary am-round' onClick='delTr(this)'><span class='am-icon-trash-o'></span> 删除</button> " +
							"<button type='button' class='am-btn am-btn-primary am-round' onClick='detail("+json.stuCode+")'><span class='am-icon-th-list'></span> 查看明细</button> " +
							"</div></td>");
							tr.append(td).append(td2).append(td3).append(td4).append(td5);
							table.append(tr);
						}
						var tr = $("<tr></tr>");
						var td = $("<td></td>");
						var td2 = $("<td></td>");
						var td3 = $("<td id='sumTd'>合计："+result.totalPrice+"</td>");
						var td4 = $("<td></td>");
						var td5 = $("<td></td>");
						tr.append(td).append(td2).append(td3).append(td4).append(td5);
						table.append(tr);
					}else {
						var tr = $("<tr></tr>");
						var td = $("<td colspan='8' align='center' style='color: red;'>没有找到相关数据</td>");
						tr.append(td);
						table.append(tr);
					}
					$(btnObj).button('reset');
				}
			});
		}, 100);
	}
</script>

