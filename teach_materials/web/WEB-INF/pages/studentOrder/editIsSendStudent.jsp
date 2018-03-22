<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<form class="am-form" id="editForm" method="post">
    <input type="hidden" id="orderCode" name="orderCode" value="${orderCode}"/>
    <div class="am-g am-margin-top">
        <div class="am-u-sm-3 am-text-right"><label >学号：</label></div>
        <div class="am-u-sm-4">${studentCode}</div>
        <div class="am-u-sm-5"></div>
    </div>

    <div class="am-g am-margin-top">
        <div class="am-u-sm-3 am-text-right"><label >姓名：</label></div>
        <div class="am-u-sm-4">${name}</div>
        <div class="am-u-sm-5"></div>
    </div>

    <div class="am-g am-margin-top-sm">
        <div class="am-u-sm-3 am-text-right"><label >层次：</label></div>
        <div class="am-u-sm-4">${level}</div>
        <div class="am-u-sm-5"></div>
    </div>

    <div class="am-g am-margin-top-sm">
        <div class="am-u-sm-3 am-text-right"><label >专业：</label></div>
        <div class="am-u-sm-4">${spec}</div>
        <div class="am-u-sm-5"></div>
    </div>

    <div class="am-g am-margin-top">
        <div class="am-u-sm-3 am-text-right"><label >快递给学生：</label></div>
        <div class="am-u-sm-4">
            <div class="am-btn-group" data-am-button="">
                <label class="am-btn am-btn-primary am-btn-s">
                    <input type="radio" name="isSendStudent" value="0" /> 否
                </label>
                <label class="am-btn am-btn-primary am-btn-s <c:if test="${isSendStudent eq '1'}">am-active</c:if>">
                    <input type="radio" name="isSendStudent" value="1" <c:if test="${isSendStudent eq '1'}">checked</c:if> /> 是
                </label>
            </div>
        </div>
        <div class="am-u-sm-5">*必选</div>
    </div>

    <div class="am-g am-margin-top">
        <div class="am-u-sm-3 am-text-right"><label >手机号码：</label></div>
        <div class="am-u-sm-4"><input type="number" id="mobile" name="mobile" placeholder="输入手机号码" value="${mobile}" /></div>
        <div class="am-u-sm-5">*必填</div>
    </div>

    <div class="am-g am-margin-top-sm">
        <div class="am-u-sm-3 am-text-right"><label >邮政编码：</label></div>
        <div class="am-u-sm-4"><input type="number" id="postalCode" name="postalCode" value="${postalCode}" placeholder="输入邮政编码" /></div>
        <div class="am-u-sm-5">*必填</div>
    </div>

    <div class="am-g am-margin-top-sm">
        <div class="am-u-sm-3 am-text-right"><label >邮寄地址：</label></div>
        <div class="am-u-sm-9">
            <c:set var="addressArray" value="${fn:split(sendAddress, '|')}"/>
            <c:set var="addressArrayLength" value="${fn:length(addressArray)}"/>
            <c:if test="${addressArrayLength == 3}">
                <c:set var="province" value="${addressArray[0]}"/>
                <c:set var="city" value="${addressArray[1]}"/>
                <c:set var="sendAddress" value="${addressArray[2]}"/>
            </c:if>
            <c:if test="${addressArrayLength == 1}">
                <c:set var="sendAddress" value="${addressArray[0]}"/>
            </c:if>
            <input type="text" id="province" name="province" value="${province}" placeholder="输入省市" style="width: 70px; float: left" />&nbsp;
            <input type="text" id="city" name="city" value="${city}" placeholder="输入市区" style="width: 70px;  float: left" />&nbsp;
            <input type="text" id="sendAddress" name="sendAddress" value="${sendAddress}" placeholder="输入详细地址" style="width: 160px;  float: left" />
        </div>
        <div class="am-u-sm-9">*省市，市区，详细地址都为必填。请务必填写学生准确的详细地址，以便快递好发货</div>
    </div>
</form>
