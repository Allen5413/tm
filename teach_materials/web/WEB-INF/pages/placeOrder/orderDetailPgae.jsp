<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<div class="main-content">
		<label>订单号：${teachMaterialPlaceOrder.orderCode}， </label>
		<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
			<tr class="am-primary">
		      <th style="width:3%">序号</th>
				<th>课程名称</th>
		      <th>教材名称</th>
		      <th>数量</th>
		      <th>单价</th>
    		</tr>
			<c:set var="beforeCourseCode" />
    		<c:forEach var="detail" items="${placeOrderDetailShowList}" varStatus="status">
    			<div><input type="hidden" value="${detail.placeOrderDetailId}" class="hidClaInp"/></div>
    			<tr>
    				<td>${status.index+1}</td>
					<td>${detail.courseCode}${detail.courseName}</td>
    				<td>${detail.materialName}</td>
    				<td>
						<c:choose>
							<c:when test="${beforeCourseCode ne detail.courseCode}">
								<input maxlength="6" id="${detail.placeOrderDetailId}" value="${detail.count}" onblur="checkUserIn(this);"/>
							</c:when>
							<c:otherwise>
								${detail.count}
							</c:otherwise>
						</c:choose>
					</td>
    				<td>${detail.materialPrice}</td>
    			</tr>
				<c:set var="beforeCourseCode" value="${detail.courseCode}" />
    		</c:forEach>
		</table>
		<button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example"
				data-am-loading="{spinner: 'circle-o-notch', loadingText: '修改中...', resetText: '修改'}"
				onclick="modifyMaterialCount()"><span class="am-icon-edit"></span> 修改</button>
	</div>
	<script type="text/javascript">
		function modifyMaterialCount(){
			var material_order_str = "";
			var dataArr = $(".hidClaInp");
			for(var i = 0;i < dataArr.length;i ++){
				var detailId = dataArr[i].value;
				var v_count = $("#" + detailId).val().trim();
				if(v_count == ""){
					app.msg("数量不能为空", 1);
					return;
				}
				if(isNaN(v_count)){
					app.msg("请输入数字", 1);
					return;
				}
				material_order_str += detailId + "_" + v_count + "a";
			}
			
			material_order_str = material_order_str.substring(0,material_order_str.length - 1);
			$.ajax({
			type: "POST",
			url:"${pageContext.request.contextPath}/placeOrder/updatePlaceOrder.htm",
			data:"placeOrderId=" + "${teachMaterialPlaceOrder.id}" + "&material_order_str=" + material_order_str,
			async: false,
			success: function(data) {
				if(data.state == 0){
					app.msg("提交成功！", 0);
				}else{
					app.msg(data.msg, 1);
				}
			}
		  });
		}
	</script>