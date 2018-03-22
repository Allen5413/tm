<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form class="am-form" id="doNextForm" name="doNextForm" method="post" enctype="multipart/form-data">
    <input type="hidden" value="${param.codes}" id="codes" name="codes"/>
    <input type="hidden" value="${param.money}" id="moneyStr" name="moneyStr"/>
    <input type="hidden" value="${param.spotCode}" id="spotCode" name="spotCode"/>
    <div class="am-g am-margin-top">
        <div class="am-u-sm-8 am-text-right"><label >${spoName}</label></div>
        <div class="am-u-sm-2" style="float:left">交费<font color="red" id="payFon"></font></div>
    </div>

    <div class="am-g am-margin-top">
        <div class="am-u-sm-3 am-text-right"><label >上传汇款凭证：</label></div>
        <div class="am-u-sm-5" style="float:left">
            <input id="payImg" type="file" multiple name="payImg" value="选择图片">
            <button type="button" class="am-btn am-btn-primary" onClick="goOnFile()"><span class="am-icon-plus"></span> 继续上传</button>
        </div>
        <div class="am-u-sm-4">*图片类型只能JPG,JPEG,PNG，大小不能超过400kb</div>
    </div>

    <div id="goOnFileDiv"></div>

    <div class="am-g am-margin-top">
        <div class="am-u-sm-3 am-text-right"><label >汇款方式：</label></div>
        <div class="am-u-sm-5">
            <div class="am-btn-group" data-am-button="">
                <label class="am-btn am-btn-primary am-btn-s">
                    <input type="radio" name="payWay" value="2"> 工商银行
                </label>
                <label class="am-btn am-btn-primary am-btn-s">
                    <input type="radio" name="payWay" value="3"> 支付宝
                </label>
                <label class="am-btn am-btn-primary am-btn-s">
                    <input type="radio" name="payWay" value="1"> 其它
                </label>
            </div>
        </div>
        <div class="am-u-sm-4">*必选</div>
    </div>

    <div class="am-g am-margin-top">
        <div class="am-u-sm-3 am-text-right"><label >备注：</label></div>
        <div class="am-u-sm-5">
            <input type="text" id="remark" name="remark" />
        </div>
        <div class="am-u-sm-4"></div>
    </div>
</form>

<script>
    var codes = "${param.codes}";
    var money = "${param.money}";
    $("#payFon").html(Number(codes.split(",").length*money).toFixed(2) + "元");

    var num = 1;
    function goOnFile(){
        num++;
        var addHtml = "<div class=\"am-g am-margin-top\">" +
                "<div class=\"am-u-sm-3 am-text-right\"><label >上传汇款凭证：</label></div>" +
                "<div class=\"am-u-sm-5\">" +
                "<input id='payImg"+num+"' name='payImg' type='file' multiple value='选择图片' />" +
                "</div>" +
                "<div class=\"am-u-sm-4\"><button type=\"button\" class=\"am-btn am-btn-primary\" onClick=\"delFile(this)\"><span class=\"am-icon-trash-o\"></span> 删除</button></div>" +
                "</div>";
        $("#goOnFileDiv").append(addHtml);
    }

    function delFile(obj){
        $(obj).parent().parent().remove();
    }
</script>