<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div id="searchStudent" class="main-content">--%>
    <%--<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findStudentForFinanceAccList/find.htm" method="post">--%>
        <%--<input type="hidden" id="spotCode" name="spotCode" value="${param.spotCode}"/>--%>
        <%--<ul class="create_info_list">--%>
            <%--<li>--%>
                <%--<table width="80%">--%>
                    <%--<tr>--%>
                        <%--<Td>--%>
                            <%--<label class="lab_80">学习中心：</label>[${spot.code}]${spot.name}--%>
                        <%--</Td>--%>
                        <%--<Td>--%>
                            <%--<label class="lab_80">学号：</label>--%>
                            <%--<input type="text" id="studentCodes" name="studentCodes" value="${param.studentCode}" class="input_240" style="width: 150px;" />--%>
                        <%--</Td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<Td>--%>
                            <%--<label class="lab_80">入学年：</label>--%>
                            <%--<input type="text" id="enterYear" name="enterYear" value="${param.enterYear}" style="width: 100px" />--%>
                        <%--</Td>--%>
                        <%--<td>--%>
                            <%--<label class="lab_80">入学季：</label>--%>
                            <%--<select id="enterQuarter" name="enterQuarter">--%>
                                <%--<option value="">请选择</option>--%>
                                <%--<option value="0" <c:if test="${param.enterQuarter eq '0'}">selected="selected" </c:if> >春季</option>--%>
                                <%--<option value="1" <c:if test="${param.enterQuarter eq '1'}">selected="selected" </c:if> >秋季</option>--%>
                            <%--</select>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--<label class="lab_80">专业：</label>--%>
                            <%--<select id="specCode" name="specCode">--%>
                                <%--<option value="">请选择</option>--%>
                                <%--<c:forEach var="spec" items="${specList}">--%>
                                    <%--<option value="${spec.code}" <c:if test="${param.specCode eq spec.code}">selected="selected" </c:if>>[${spec.code}]${spec.name}</option>--%>
                                <%--</c:forEach>--%>
                            <%--</select>--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<label class="lab_80">层次：</label>--%>
                            <%--<select id="levelCode" name="levelCode">--%>
                                <%--<option value="">请选择</option>--%>
                                <%--<c:forEach var="level" items="${levelList}">--%>
                                    <%--<option value="${level.code}" <c:if test="${param.levelCode eq level.code}">selected="selected" </c:if>>${level.name}</option>--%>
                                <%--</c:forEach>--%>
                            <%--</select>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--<label class="lab_80">状态：</label>--%>
                            <%--<select id="state" name="state">--%>
                                <%--<option value="">请选择</option>--%>
                                <%--<option value="0" <c:if test="${param.state eq '0'}">selected="selected" </c:if>>在籍</option>--%>
                                <%--<option value="1" <c:if test="${param.state eq '1'}">selected="selected" </c:if>>停用</option>--%>
                                <%--<option value="2" <c:if test="${param.state eq '2'}">selected="selected" </c:if>>休学</option>--%>
                                <%--<option value="3" <c:if test="${param.state eq '3'}">selected="selected" </c:if>>退学</option>--%>
                                <%--<option value="4" <c:if test="${param.state eq '4'}">selected="selected" </c:if>>毕业</option>--%>
                            <%--</select>--%>
                        <%--</td>--%>
                        <%--<Td>--%>
                            <%--<a class="com_btn_b" href="#" id="subBut" onclick="pageForm.submit();"><span>查&nbsp;询</span></a>--%>
                        <%--</Td>--%>
                    <%--</tr>--%>
                <%--</table>--%>
            <%--</li>--%>
        <%--</ul>--%>
    <%--</form>--%>
    <%--退款说明：<input type="text" id="detail">--%>
    <%--<a class="com_btn_b" href="#" onclick="enterMoney()"><span>确定</span></a>--%>
    <%--<form id="addForm" name="addForm" action="${pageContext.request.contextPath}/addRefund/add.htm" method="post">--%>
        <%--<input type="hidden" id="studentMoneyDetails" name="studentMoneyDetails">--%>
        <%--<input type="hidden" name="spotCode" value="${spot.code}">--%>
        <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
            <%--<tr>--%>
                <%--<th style="width: 8%;">--%>
                    <%--<a href="#" onclick="checkAll('cb')" style="color: #0092DC">全选</a>|--%>
                    <%--<a href="#" onclick="checkNall('cb')" style="color: #0092DC">反选</a>--%>
                <%--</th>--%>
                <%--<th style="width: 8%;">学号</th>--%>
                <%--<th style="width: 8%;">姓名</th>--%>
                <%--<th style="width: 20%;">专业</th>--%>
                <%--<th style="width: 7%;">层次</th>--%>
                <%--<th style="width: 7%;">缴费金额</th>--%>
                <%--<th style="width: 7%;">消费金额</th>--%>
                <%--<th style="width: 7%;">余额</th>--%>
                <%--<th style="width: 7%;">已开发票</th>--%>
                <%--<th style="width: 7%;">退款金额</th>--%>
                <%--<th style="width: 14%;">退款说明</th>--%>
            <%--</tr>--%>
            <%--<c:if test="${empty jsonArray}">--%>
                <%--<tr>--%>
                    <%--<td colspan="99" align="center" style="color: red;">没有找到相关数据</td>--%>
                <%--</tr>--%>
            <%--</c:if>--%>
            <%--<c:forEach var="json" items="${jsonArray}" varStatus="status">--%>
                <%--<tr>--%>
                    <%--<td align="center">--%>
                        <%--<input type="checkbox" name="cb" value="${json.code}">--%>
                    <%--</td>--%>
                    <%--<td>${json.code}</td>--%>
                    <%--<td>${json.name}</td>--%>
                    <%--<td>${json.specName}</td>--%>
                    <%--<td>${json.levelName}</td>--%>
                    <%--<td>${json.pay}</td>--%>
                    <%--<td>${json.buy}</td>--%>
                    <%--<td>${json.acc}</td>--%>
                    <%--<td>${json.money}</td>--%>
                    <%--<td>--%>
                        <%--<input type="text" id="refundMonery_${json.code}" name="refundMonery" value="${json.canRefundMoney}" style="width: 50px;">--%>
                        <%--<input type="hidden" id="canRefundMoney_${json.code}" value="${json.canRefundMoney}" />--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--<input type="text" id="detail_${json.code}" name="detail" style="width: 120px;">--%>
                    <%--</td>--%>
                <%--</tr>--%>
            <%--</c:forEach>--%>
        <%--</table>--%>
        <%--银行名称：<input type="text" id="bankName" name="bankName"><br/><br/>--%>
        <%--银行卡号：<input type="text" id="bankCode" name="bankCode"><br/><br/>--%>
        <%--收款单位：<input type="text" id="company" name="company"><br/><br/>--%>
    <%--</form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
    <%--function enterMoney(){--%>
        <%--var detail = $("#detail").val().trim();--%>
        <%--$("[name=detail]").each(function(){--%>
            <%--$(this).val(detail);--%>
        <%--});--%>
    <%--}--%>

    <%--function sub(obj){--%>
        <%--var isSub = false;--%>
        <%--var msg = "";--%>
        <%--var studentMoneyDetails = "";--%>
        <%--$("[name=cb]").each(function(){--%>
            <%--if($(this).prop("checked")){--%>
                <%--isSub = true;--%>
                <%--var studentCode = $(this).val();--%>
                <%--var studentMoney = $("#refundMonery_"+studentCode).val();--%>
                <%--var detail = $("#detail_"+studentCode).val();--%>
                <%--if(!vaild.vaildMoney(studentMoney)){--%>
                    <%--msg += "学号："+studentCode+", 退款金额错误！\n";--%>
                <%--}--%>
                <%--if(detail.indexOf("_") >= 0 || detail.indexOf("|") >= 0 ){--%>
                    <%--msg += "学号："+studentCode+", 退款说明不能包含'_'、'|'等特殊字符！\n";--%>
                <%--}--%>
                <%--if(Number(studentMoney) > Number($("#canRefundMoney_"+studentCode).val())){--%>
                    <%--msg += "学号："+studentCode+", 退款金额超额了！\n";--%>
                <%--}--%>
                <%--studentMoneyDetails += studentCode+"_"+studentMoney+"_"+detail+"|";--%>
            <%--}--%>
        <%--});--%>
        <%--if(!isSub){--%>
            <%--alert("请选择要退款得学生");--%>
        <%--}else{--%>
            <%--if(msg != ""){--%>
                <%--alert(msg);--%>
            <%--}else{--%>
                <%--if("" != studentMoneyDetails && 0 < studentMoneyDetails.length){--%>
                    <%--$("#studentMoneyDetails").val(studentMoneyDetails.substr(0, studentMoneyDetails.length-1))--%>
                <%--}--%>
                <%--if($("#bankName").val() == ""){--%>
                    <%--alert("请输入银行名称");--%>
                <%--}--%>
                <%--else if($("#bankCode").val() == ""){--%>
                    <%--alert("请输入银行卡号");--%>
                <%--}--%>
                <%--else if($("#company").val() == ""){--%>
                    <%--alert("请输入收款单位");--%>
                <%--}else{--%>
                    <%--$.ajax({--%>
                        <%--cache: true,--%>
                        <%--type: "POST",--%>
                        <%--url:"${pageContext.request.contextPath}/addRefund/add.htm",--%>
                        <%--data:$('#addForm').serialize(),--%>
                        <%--async: false,--%>
                        <%--success: function(data) {--%>
                            <%--if(data.state == 0){--%>
                                <%--$(obj).click();--%>
                                <%--alert("提交成功！");--%>
                                <%--Dialog.close();--%>
                            <%--}else{--%>
                                <%--alert(data.msg);--%>
                            <%--}--%>
                        <%--}--%>
                    <%--});--%>
                <%--}--%>
            <%--}--%>
        <%--}--%>
    <%--}--%>
<%--</script>--%>

<form id="stuForm" name="stuForm" action="${pageContext.request.contextPath}/findStudentForFinanceAccList/find.htm" method="post">
    <input type="hidden" id="spotCode" name="spotCode" value="${param.spotCode}"/>
    <table width="100%">
        <tr height="40">
            <td align="right"><label >学习中心：</label></td>
            <td>[${spot.code}]${spot.name}</td>
            <td align="right"><label >入学年：</label></td>
            <td><input type="text" id="add_year" name="enterYear" value="${param.enterYear}" /></td>
            <td align="right"><label >入学季：</label></td>
            <td>
                <select id="add_quarter" name="enterQuarter">
                    <option value="">--请选择--</option>
                    <option value="0" <c:if test="${param.enterQuarter eq '0'}">selected="selected" </c:if> >春季</option>
                    <option value="1" <c:if test="${param.enterQuarter eq '1'}">selected="selected" </c:if> >秋季</option>
                </select>
            </td>
        </tr>
        <tr height="40">
            <td align="right"><label >专业：</label></td>
            <td>
                <select id="add_specCode" name="specCode">
                    <option value="">--请选择--</option>
                    <c:forEach var="spec" items="${specList}">
                        <option value="${spec.code}" <c:if test="${param.specCode eq spec.code}">selected="selected" </c:if>>[${spec.code}]${spec.name}</option>
                    </c:forEach>
                </select>
            </td>
            <td align="right"><label >层次：</label></td>
            <td>
                <select id="add_levelCode" name="levelCode">
                    <option value="">--请选择--</option>
                    <c:forEach var="level" items="${levelList}">
                        <option value="${level.code}" <c:if test="${param.levelCode eq level.code}">selected="selected" </c:if> >${level.name}</option>
                    </c:forEach>
                </select>
            </td>
            <td align="right"><label >学号：</label></td>
            <td><input type="text" id="add_studentCode" name="studentCodes" value="${param.studentCodes}" /></td>
        </tr>
        <tr height="40">
            <td align="right"><label>状态：</label></td>
            <td>
                <select id="state" name="state">
                    <option value="">请选择</option>
                    <option value="0" <c:if test="${param.state eq '0'}">selected="selected" </c:if>>在籍</option>
                    <option value="1" <c:if test="${param.state eq '1'}">selected="selected" </c:if>>停用</option>
                    <option value="2" <c:if test="${param.state eq '2'}">selected="selected" </c:if>>休学</option>
                    <option value="3" <c:if test="${param.state eq '3'}">selected="selected" </c:if>>退学</option>
                    <option value="4" <c:if test="${param.state eq '4'}">selected="selected" </c:if>>毕业</option>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="99" style="padding-left:20px;">
                <button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example"
                        data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '查询'}"
                        onclick="searchStu(this)"><span class="am-icon-search"></span> 查询</button>
            </td>
        </tr>
    </table>
</form>
<p /><p />
<form id="addForm" name="addForm" action="${pageContext.request.contextPath}/addRefund/add.htm" method="post">
    <input type="hidden" id="studentMoneyDetails" name="studentMoneyDetails">
    <input type="hidden" name="spotCode" value="${spot.code}">
    <table id="stuTable" class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
        <tr>
            <td colspan="99">
                退款说明：<input type="text" id="detail">
                <button type="button" class="am-btn am-btn-primary btn-loading-example" onclick="enterMoney()"><span class="am-icon-plus"></span> 确定</button>&nbsp;&nbsp;
                银行名称(具体到支行)：<input type="text" id="bankName" name="bankName">&nbsp;&nbsp;
                银行卡号：<input type="text" id="bankCode" name="bankCode">&nbsp;&nbsp;
                收款单位：<input type="text" id="company" name="company">
            </td>
        </tr>
        <tr class="am-primary">
            <th style="width: 6%; text-align: center">
                <a href="#" onClick="app.checkAll('cb')">全选</a>|<a href="#" onClick="app.checkNAll('cb')">反选</a>
            </th>
            <th style="width: 9%;">学号</th>
            <th style="width: 6%;">姓名</th>
            <th style="width: 22%;">专业</th>
            <th style="width: 7%;">层次</th>
            <th style="width: 7%;">缴费金额</th>
            <th style="width: 7%;">消费金额</th>
            <th style="width: 7%;">余额</th>
            <th style="width: 7%;">已开发票</th>
            <th style="width: 7%;">退款金额</th>
            <th style="width: 15%;">退款说明</th>
        </tr>
    </table>
</form>
<script>
    function searchStu(btnObj){
        var i = 0;
        $("#stuTable").find("tr").each(function(){
            if(1 < i){
                $(this).remove();
            }
            i++;
        });
        $(btnObj).button('loading');
        setTimeout(function(){
            $.ajax({
                cache: true,
                type: "POST",
                url: "${pageContext.request.contextPath}/findStudentForFinanceAccList/find.htm",
                async: false,
                data: $("#stuForm").serialize(),
                success: function (data) {
                    var table = $("#stuTable");
                    if(null == data.jsonData || typeof (data.jsonData) == "undefined" || 0 == data.jsonData.length){
                        var tr = $("<tr></tr>");
                        var td = $("<td colspan=\"99\" align=\"center\" style=\"color: red;\">没有找到相关数据</td>");
                        tr.append(td);
                        table.append(tr);
                    }else{
                        for(var i=0; i<data.jsonData.length; i++){
                            var json = data.jsonData[i];
                            var tr = $("<tr></tr>");
                            var td = $("<td>" +
                            "<label class=\"am-checkbox am-secondary\" style=\"margin-top:5px; margin-left:24px;\">" +
                            "<input type=\"checkbox\" name=\"cb\" value="+json.code+" data-am-ucheck>" +
                            "</label></td>");
                            tr.append(td);
                            for ( var key in json ){
                                if("canRefundMoney" == key){
                                    td = $("<td><input type=\"text\" id=\"refundMonery_"+json.code+"\" value="+json[key]+" style=\"width: 50px;\">" +
                                            "<input type=\"hidden\" id=\"canRefundMoney_"+json.code+"\" value="+json.canRefundMoney+" /></td>");
                                }else {
                                    td = $("<td>" + json[key] + "</td>");
                                }
                                tr.append(td);
                            }
                            td = $("<td><input type=\"text\" id=\"detail_"+json.code+"\" name=\"detail\"></td>");
                            tr.append(td);
                            table.append(tr);
                        }
                    }
                    $(btnObj).button('reset');
                }
            });
        }, 100);
    }

    function enterMoney(){
        var detail = $("#detail").val().trim();
        $("[name=detail]").each(function(){
            $(this).val(detail);
        });
    }
</script>
