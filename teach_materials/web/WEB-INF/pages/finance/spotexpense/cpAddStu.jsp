<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;" id="datTab">
	<tr>
		<td colspan="999" style="background-color:#FFF">
			<button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="alterDoStu()"><span class="am-icon-plus"></span> 添加预付款学生</button>
		</td>
	</tr>
	<tr id="title" class="am-primary">
		<th>入学年季</th>
		<th>学号</th>
		<th>姓名</th>
		<th>金额</th>
		<th>操作</th>
	</tr>
</table>
<script>
	function alterDoStu(){
		var url = '${pageContext.request.contextPath}/centerPay/toDoAddStu.htm?spotCode=' + "${param.spotCode}";
//		try{
//			$("#dialogDiv2").dialog({
//				title: "添加学生",
//				width: getWindowWidthSize() * 0.6,
//				height: getWindowHeightSize() * 0.6,
//				href: url,
//				modal: true,
//				onClose : function(){
//					$(this).dialog("destroy").remove();
//	var createDiv = $("<div></div>");
//	createDiv.attr("id","dialogDiv2");
//	createDiv.appendTo($("#cpAddOut"));
//	}
//	}).dialog("open");
//	}catch(e){
//	alert(e);
//	}
//	}

		app.openDialog(url, '添加学生', 900, 0.7, function(index){
			var money = $("#money").val().trim();
			if(!vaild.vaildMoney(money)){
				app.msg("请填入正确的预交金额", 1);
				return;
			}
			var cheArr = document.getElementsByName("cb");
			if(cheArr.length == 0){
				app.msg("请填加学生", 1);
				return;
			}

			var sumMon = 0;
			for(var i = 0;i < cheArr.length;i ++){
				if(cheArr[i].checked){
					var cheStrArr = cheArr[i].value.split(",");
					if(null == document.getElementById("" + cheStrArr[1])){
						$("#title").after("<tr class='cpAddStuTr' id='" + cheStrArr[1] + "'><td>" + cheStrArr[0] + "</td><td>" + cheStrArr[1] + "</td><td>" + cheStrArr[2] + "</td><td><input type='text' onchange='changePaySum();' value='" + money + "'></td><td>" +
						"<div class='am-btn-group'>" +
						"<button type='button' class='am-btn am-btn-primary am-round' onClick='delTr(this)'><span class='am-icon-trash-o'></span> 删除</button>" +
						"</div>" +
						"</td></tr>");
					}
				}
			}
			sumMon = calTheSum();
			if(document.getElementById("sumTr") != null){
				$("#sumTd").text("合计:" + sumMon);
			}else{
				$("#datTab").append("<tr id='sumTr'><td></td><td></td><td id='sumTd' align='right'>" + "合计：" + sumMon + "</td><td></td><td></td></tr>");
			}
			$("#sumHid").val(sumMon);
			layer.close(index);



		});
	}

	function calTheSum(){
		var trArr = $(".cpAddStuTr");
		var sumMon = 0;
		for(var i = 0;i < trArr.length;i ++){
			var money = $(trArr[i]).find("td").eq(3).find("input").val();
			sumMon += parseFloat(money);
		}
		return sumMon.toFixed(2);
	}

	function changePaySum(){
		var trArr = $("tr");
		var sumMon = 0;
		for(var i = 0;i < trArr.length;i ++){
			if((trArr[i].id) != "title"){
				if(trArr[i].id != "sumTr"){
					var money = $(trArr[i]).find("td").eq(3).find("input").val();
					if(typeof (money) != "undefined") {
						sumMon += parseFloat(money);
					}
				}
			}
		}
		document.getElementById("sumTd").innerText = "合计：" + sumMon.toFixed(2);
		$("#sumHid").val(sumMon.toFixed(2));
	}

	function delTr(vA){
		$(vA).parent().parent().parent().remove();
		var trArr = $("tr");
		var sumMon = 0;
		for(var i = 0;i < trArr.length;i ++){
			if((trArr[i].id) != "title"){
				if(trArr[i].id != "sumTr"){
					var money = $(trArr[i]).find("td").eq(3).find("input").val();
					if(typeof (money) != "undefined") {
						sumMon += parseFloat(money);
					}
				}
			}
		}
		document.getElementById("sumTd").innerText = "合计：" + sumMon.toFixed(2);
		$("#sumHid").val(sumMon.toFixed(2));
	}
</script>