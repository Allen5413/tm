<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<body>--%>
 <%--<div class="main-content">--%>
 	<%--<form action="${pageContext.request.contextPath}/centerPay/sureTemPay.htm" id="doNextForm" method="post"  enctype="multipart/form-data" target="hidIfr">--%>
		<%--<input type="text" value="" id="stuTempPay" name="stuTempPay"/>--%>
		<%--<input type="text" value="" id="sumHid" name="sumHid"/>--%>
		<%--<input type="text" value="" id="spotOwnId" name="spotOwnId"/>--%>
		<%--<input type="text" value="" id="spotCode" name="spotCode"/>--%>
		<%--<ul class="create_info_list">--%>
        <%--<li>--%>
            <%--<label class="lab_80" style="width: auto">${spoName},交费<font color="red" id="payFon"></font></label>--%>
        <%--</li>--%>
        <%--<li>--%>
            <%--<label class="lab_80" style="width: auto">上传汇款凭证：</label>--%>
            <%--<input name="payImg" id="payImg" type="file"/><font color="red">图片类型只能JPG,JPEG,PNG，大小不能超过400kb</font>--%>
            <%--<a href="#" onclick="goOnFile()" style="color: #0092DC">继续上传</a>--%>
            <%--<div id="goOnFileDiv"></div>--%>
        <%--</li>--%>
        <%--<li>--%>
            <%--<label class="lab_80" style="width: auto">汇款方式：<select id="payWay" name="payWay"><option value="1">其他</option><option value="2">工商银行</option><option value="3">支付宝</option></select></label>--%>
        <%--</li>--%>
        <%--<li>--%>
            <%--<label class="lab_80" style="width: auto">备注：</label>--%>
            <%--<input type="text" id="remark" name="remark" />--%>
        <%--</li>--%>
    <%--</ul>--%>
	<%--</form>--%>
   <%--<!-- <iframe id="hidIfr" name="hidIfr" style="display:none"></iframe> --> --%>
    <%--<a class="com_btn_b" href="#" onclick="doSub();"><span>确定</span></a>--%>
  <%--</div>--%>
  <%--<script type="text/javascript">--%>
  <%--$(function(){--%>
	  <%--calSumStr();--%>
  <%--});--%>
  <%----%>
  <%--function calSumStr(){--%>
		<%--var trArr = $(".cpAddStuTr");--%>
		<%--var subStr = "";--%>
		<%--var sumMon = 0;--%>
		<%--for(var i = 0;i < trArr.length;i ++){--%>
			<%--if((trArr[i].id) != "title"){--%>
				<%--if(trArr[i].id != "sumTr"){--%>
					<%----%>
					<%--var stuCode = $(trArr[i]).find("td").eq(1).text();--%>
					<%--var stuName = $(trArr[i]).find("td").eq(2).text();--%>
					<%--var money = $(trArr[i]).find("td").eq(3).find("input").val();--%>
					<%--sumMon += parseFloat(money);--%>
                    <%--if(!vaild.vaildMoney(money)){--%>
                        <%--alert("学号："+stuCode+", 金额错误，或者金额小于等于0");--%>
                        <%--closeD($("#dialogDiv3"));--%>
                    <%--}--%>
					<%--subStr += stuCode + "$" + stuName + "$" + money;--%>
					<%--if(i < trArr.length - 1){--%>
						<%--subStr += "%";--%>
					<%--}--%>
				<%--}--%>
			<%--}--%>
		<%--}--%>
		<%--$("#stuTempPay").val(subStr);--%>
		<%--$("#spotOwnId").val("${spotOwnId}");--%>
		<%--$("#spotCode").val("${spotCode}");--%>
		<%--$("#sumHid").val(sumMon.toFixed(2));--%>
		<%--document.getElementById("payFon").innerHTML = sumMon.toFixed(2) + "元";--%>
	<%--}--%>
  <%----%>
  <%--function doSub(){--%>
      <%--var arrId=new Array();--%>
      <%--var i=0;--%>
      <%--$("[name = payImg]").each(function(){--%>
          <%--arrId[i] = $(this).attr("id");--%>
          <%--i++;--%>
      <%--});--%>
	  <%--$.ajaxFileUpload({--%>
          <%--url: '${pageContext.request.contextPath}/centerPay/sureTemPay.htm', //用于文件上传的服务器端请求地址--%>
          <%--secureuri: false, //是否需要安全协议，一般设置为false--%>
          <%--fileElementId: arrId, //文件上传域的ID--%>
          <%--dataType: 'json', //返回值类型 一般设置为json--%>
          <%--type : 'post',--%>
          <%--data : {"stuTempPay":$("#stuTempPay").val(),"sumHid":$("#sumHid").val(),"spotOwnId":$("#spotOwnId").val(),"spotCode":$("#spotCode").val(),"payWay":$("#payWay").val(),"remark":$("#remark").val()},--%>
          <%--success: function (data, status) {--%>
              <%--alert(data.sureRe);--%>
              <%--if("操作成功!" == data.sureRe) {--%>
                  <%--$('#index_iframe').contents().find(".payPageForm").submit();--%>
                  <%--closeLastPage();--%>
              <%--}--%>
          <%--},--%>
          <%--error: function (data, status, e){--%>
             <%--//服务器响应失败处理函数--%>
             <%--alert(e);--%>
             <%--closeLastPageOndo();--%>
          <%--}--%>
      <%--});--%>
      <%--return false;--%>
  <%--}--%>

  <%--var num = 1;--%>
  <%--function goOnFile(){--%>
      <%--num++;--%>
      <%--var addHtml = "<div><label class='lab_80' style='width: 100px;'></label>" +--%>
              <%--"<input id='payImg"+num+"' name='payImg' type='file'/>" +--%>
              <%--"<a href='#' onclick='delFile(this)' style='color: #0092DC'>删除</a></div>";--%>
      <%--$("#goOnFileDiv").append(addHtml)--%>
  <%--}--%>

  <%--function delFile(obj){--%>
      <%--$(obj).parent().remove();--%>
  <%--}--%>
  <%--</script>--%>
 <%--</body>--%>


<form class="am-form" id="doNextForm" name="doNextForm" method="post" enctype="multipart/form-data">
    <input type="hidden" value="" id="stuTempPay" name="stuTempPay"/>
    <input type="hidden" value="" id="sumHid" name="sumHid"/>
    <input type="hidden" value="" id="spotOwnId" name="spotOwnId"/>
    <input type="hidden" value="" id="last_spotCode" name="spotCode"/>
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
    var trArr = $(".cpAddStuTr");
    var subStr = "";
    var sumMon = 0;
    for(var i = 0;i < trArr.length;i ++){
        if((trArr[i].id) != "title"){
            if(trArr[i].id != "sumTr"){
                var stuCode = $(trArr[i]).find("td").eq(1).text();
                var stuName = $(trArr[i]).find("td").eq(2).text();
                var money = $(trArr[i]).find("td").eq(3).find("input").val();
                sumMon += parseFloat(money);
                subStr += stuCode + "$" + stuName + "$" + money;
                if(i < trArr.length - 1){
                    subStr += "%";
                }
            }
        }
    }
    $("#stuTempPay").val(subStr);
    $("#spotOwnId").val("${spotOwnId}");
    $("#last_spotCode").val("${spotCode}");
    $("#sumHid").val(sumMon.toFixed(2));
    $("#payFon").html(sumMon.toFixed(2) + "元");




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