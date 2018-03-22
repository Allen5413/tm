<%--<%@ page language="java" contentType="text/html; charset=utf-8"--%>
         <%--pageEncoding="utf-8"%>--%>
<%--<!DOCTYPE html>--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body class="body-bg">--%>
<%--<div id="page">--%>
  <%--<div id="header">--%>
    <%--<div class="logo_admin">--%>
      <%--<div class="logo"><h2>重庆西网文化传播有限公司教材管理系统</h2></div>--%>
      <%--<a class="quit_ico" href="${pageContext.request.contextPath}/logoutUser/logou.htm">退出</a>--%>
    <%--</div>--%>
    <%--<div class="barNews_left">--%>
      <%--<div class="barNews_right">--%>
        <%--<label class="right">--%>
          <%--<span class="icoTime">日期：${year}年${month}月${day}日 ${week}</span>--%>
          <%--<em class="hlr">&nbsp;</em>--%>
          <%--<span class="point">&nbsp;</span>--%>
        <%--</label>--%>
        <%--<a class="toHome" href="${pageContext.request.contextPath}/index/main.htm"></a>--%>
        <%--<a class="setPwd" href="#" onclick="editPwd()"></a>--%>
      <%--</div>--%>
    <%--</div>--%>
  <%--</div>--%>
  <%--<div id="centerAdmin">--%>
    <%--<div class="admin_leftMenu">--%>
      <%--<dl class="list-menu">--%>
        <%--<c:forEach var="menu" items="${menu}" varStatus="status">--%>
          <%--<dt lang="${status.index}" onclick="clickMenu(${status.index})">--%>
            <%--<a class="tOff" href="#">${menu.key}</a>--%>
          <%--</dt>--%>
          <%--<dd lang="${status.index}" class="display_none">--%>
            <%--<ul>--%>
              <%--<c:forEach var="resource" items="${menu.value}">--%>
                <%--<li onclick="clickResource('${pageContext.request.contextPath}${resource.url}', this, '${resource.name}')">--%>
                  <%--<a href="#">${resource.name}</a>--%>
                <%--</li>--%>
              <%--</c:forEach>--%>
            <%--</ul>--%>
          <%--</dd>--%>
        <%--</c:forEach>--%>
      <%--</dl>--%>
    <%--</div>--%>
    <%--<div class="admin_rightContent">--%>
      <%--<div class="content_main">--%>
        <%--<div class="arrow_btn">--%>
        <%--</div>--%>
        <%--<div class="mainContent">--%>
          <%--<div class="mainTab">--%>
            <%--<span class="opt_right">--%>
              <%--<a class="optPrev" href="#" onclick="prevTab()">&nbsp;</a>--%>
              <%--<a class="optNext" href="#" onclick="nextTab()">&nbsp;</a>--%>
              <%--<a class="optClose" href="#" onclick="closeTab()">&nbsp;</a>--%>
            <%--</span>--%>
            <%--<a lang="${pageContext.request.contextPath}/prompt/openPromptPage.htm" class='tab_1 on' href='#' onclick='clickTab(this)'>首页</a>--%>
          <%--</div>--%>
          <%--<div class="contain_blockBg">--%>
            <%--<div class="container">--%>
              <%--<iframe id="index_iframe" frameborder="no" class="iframe-noborder" src="${pageContext.request.contextPath}/prompt/openPromptPage.htm" width="87%"></iframe>--%>
            <%--</div>--%>
          <%--</div>--%>
        <%--</div>--%>
      <%--</div>--%>
    <%--</div>--%>
  <%--</div>--%>
  <%--<div id="dialogDiv"></div>--%>
  <%--<div id="indexOutDiv"><div id="paydialogDiv"></div></duv>--%>
  <%--<div id="ownOutDiv"><div id="owndialogDiv"></div></div>--%>
  <%--<div id="footAdmin">--%>
    <%--<p><span class="right"></span>欢迎您，<a href="#">${loginName}</a> <em class="hrl_2">&nbsp;</em> 姓名：<a href="#">${name}</a></p>--%>
  <%--</div>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function closeDialog(){--%>
    <%--closeD($("#dialogDiv"));--%>
  <%--}--%>
  <%--function openDialog(title, width, height, url){--%>
    <%--openD($("#dialogDiv"), title, width, height, url);--%>
  <%--}--%>
  <%--function refreshDialog(){--%>
    <%--refreshD($("#dialogDiv"));--%>
  <%--}--%>
  <%--function detoryDialog(){--%>
	<%--destroyD($("#dialogDiv"));--%>
  <%--}--%>
  <%--function detoryPayDialog(){--%>
	<%--destroyD($("#paydialogDiv"));--%>
  <%--}--%>
  <%--function removePayDialog(){--%>
	<%--removeD($("#paydialogDiv"));--%>
  <%--}--%>
  <%--function isPayClear(){--%>
	  <%--return document.getElementById("paydialogDiv").innerHTML == "";--%>
  <%--}--%>
  <%--function editPwd(){--%>
    <%--openD($("#dialogDiv"), '修改密码', 0.3, 0.3, '${pageContext.request.contextPath}/editPwd/openEditUserPwdPage.htm');--%>
  <%--}--%>
  <%--function getDialogDivHeight(){--%>
    <%--return $("#dialogDiv").height();--%>
  <%--}--%>
  <%----%>
  <%--function openPayDialog(url){--%>
	  <%--$("#paydialogDiv").dialog({--%>
	        <%--title: "中心交费",--%>
	        <%--width: getWindowWidthSize() * 0.6,--%>
	        <%--height: getWindowHeightSize() * 0.9,--%>
	        <%--href: url,--%>
	        <%--modal: true,--%>
	        <%--onClose : function(){--%>
	        	 <%--$(this).dialog("destroy").remove();--%>
	        	 <%--var createDiv = $("<div></div>");--%>
				 <%--createDiv.attr("id","paydialogDiv");--%>
		         <%--createDiv.appendTo($("#indexOutDiv"));--%>
	        <%--}--%>
	  <%--}).dialog("open");--%>
  <%--}--%>
  <%----%>
  <%--function openOwnDialog(title, width, height, url){--%>
	  <%----%>
	 <%--// var dialogParent = $("#owndialogDiv").parent();  --%>
	  <%--//var dialogOwnClone = $("#owndialogDiv").clone();  --%>
	  <%--//dialogOwnClone.hide();  --%>
	  <%----%>
	  <%--$("#owndialogDiv").dialog({--%>
		 <%--title: "中心交费",--%>
	     <%--width: getWindowWidthSize() * 0.6,--%>
	     <%--height: getWindowHeightSize() * 0.9,--%>
	     <%--href: url,--%>
	     <%--modal: true,--%>
	     <%--onClose : function(){--%>
	    	  <%--$(this).dialog("destroy").remove();--%>
    		  <%--var createDiv = $("<div></div>");--%>
			  <%--createDiv.attr("id","owndialogDiv");--%>
	          <%--createDiv.appendTo($("#ownOutDiv"));--%>
	     <%--}--%>
	  <%--}).dialog("open");--%>
  <%--}--%>
  <%----%>
  <%--function closeOwnDialog(){--%>
	  <%--closeD($("#owndialogDiv"));  --%>
  <%--}--%>
  <%----%>
  <%--function closePayDialog(){--%>
	  <%--closeD($("#paydialogDiv"));--%>
  <%--}--%>
  <%----%>
  <%--$(function(){--%>
	    <%--$("#index_iframe").css("height", getWindowHeightSize()*0.7);--%>
	  <%--});--%>
<%--</script>--%>







<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html class="no-js">
<head>
  <%@ include file="/common/meta2.jsp"%>
  <%@ include file="/common/taglibs2.jsp"%>
</head>
<script type="text/javascript">
  function openEditPwd(){
    app.openDialog('${pageContext.request.contextPath}/editPwd/openEditUserPwdPage.htm', '修改密码', 500, 0.3, function(index){
      var oldPwd = $("#oldPwd").val();
      var newPwd = $("#newPwd").val();
      var newPwdAgain = $("#newPwdAgain").val();
      if(oldPwd == ""){
        app.msg("请输入旧密码", 1);
        return;
      }
      if(newPwd == ""){
        app.msg("请输入新密码", 1);
        return;
      }
      if(newPwd != newPwdAgain){
        app.msg("新密码不一致", 1);
        return;
      }
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/editPwd/pwdEdit.htm",
        data:$('#editPwdForm').serialize(),
        async: false,
        success: function(data) {
          if(data.state == 0){
            app.msg('提交成功', 0);
            layer.close(index);
          }else{
            alert(data.msg);
          }
        }
      });
    });
  }
</script>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->

<header class="am-topbar am-topbar-inverse admin-header">
  <div class="am-topbar-brand">
    <strong style="font-size: 20px;">重庆西网文化传播有限公司教材管理系统</strong>
  </div>

  <div class="am-collapse am-topbar-collapse" id="topbar-collapse">
    <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
      <li class="am-dropdown" data-am-dropdown>
        <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
          <span class="am-icon-users"></span> ${name} 管理员 <span class="am-icon-caret-down"></span>
        </a>
        <ul class="am-dropdown-content">
          <li><a href="#" onclick="openEditPwd()"><span class="am-icon-cog"></span> 修改密码</a></li>
          <li><a href="${pageContext.request.contextPath}/logoutUser/logou.htm"><span class="am-icon-power-off"></span> 退出</a></li>
        </ul>
      </li>
    </ul>
  </div>
</header>

<div class="am-cf admin-main">
  <!-- sidebar start -->
  <div class="admin-sidebar" style="width: 200px;">
    <ul class="am-list admin-sidebar-list">
      <c:forEach var="menu" items="${menu}" varStatus="status">
        <li class="admin-parent" style="background-color:#19a7f0">
          <a class="am-cf" data-am-collapse="{target: '#menu_${status.index}'}" style="color:#fff;" onClick="app.clickMenu(${status.index})">
            <span class="am-icon-file"></span> ${menu.key} <span id="${status.index}" class="am-icon-angle-right am-fr am-margin-right"></span>
          </a>
          <ul class="am-list am-collapse admin-sidebar-sub" id="menu_${status.index}" style="padding-left:0px;">
            <c:forEach var="resource" items="${menu.value}" varStatus="status2">
              <li style="background-color:#f9f9f9">
                <a href="#" style="color:#19a7f0;" onclick="app.addTab('${pageContext.request.contextPath}${resource.url}', '${resource.name}', ${status.index}, ${status2.index}, 0)"><span class="am-icon-table" style="padding-left:14px;"></span> ${resource.name}</a>
              </li>
            </c:forEach>
          </ul>
        </li>
      </c:forEach>
    </ul>
  </div>
  <!-- sidebar end -->



  <div class="admin-content">
    <div class="am-tabs" data-am-tabs>

      <ul id="tab" class="am-tabs-nav am-nav am-nav-tabs">
        <li id="homePageTab" class="am-active" lang="0"><a lang="${pageContext.request.contextPath}/index/main.htm" href="#contentPage" onclick='app.clickTab(this)' style="float: left">首页</a></li>
      </ul>
      <span class="am-icon-remove" style="margin-top:-26px; float:right"> <a href="#" onclick="app.removeTab()">关闭</a></span>

      <div class="am-tabs-bd" style="height:1110px; min-height:1110px; overflow-y:auto;">
        <div class="am-tab-panel am-fade am-in am-active" id="contentPage">
          <table id="totalTable" class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
            <tr>
              <td colspan="999" style="background-color:#FFF">
                <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="findTotal()"><span class="am-icon-refresh"></span> 刷新统计</button>
              </td>
            </tr>
            <tr id="waitAuditTR" style="display: none; cursor:pointer" onclick="aa('/centerPay/verifySpotPay.htm')">
              <td width="15%" align="right">交费待审核条数：</td>
              <td id="waitAuditCount"></td>
            </tr>
            <tr id="notPrintSpotOrderTR" style="display: none; cursor:pointer" onclick="aa('/findStudentBookOrderForSpotCount/find.htm')">
              <td width="15%" align="right">中心发书单未打印条数：</td>
              <td id="notPrintSpotOrderCount"></td>
            </tr>
            <tr id="notPrintStudentOrderTR" style="display: none; cursor:pointer" onclick="aa('/findStudentBookOrderForStudentCount/find.htm')">
              <td width="15%" align="right">学生发书单未打印条数：</td>
              <td id="notPrintStudentOrderCount"></td>
            </tr>
            <tr id="notPackageSpotOrderTR" style="display: none; cursor:pointer" onclick="aa('/addStudentBookOrderPackage/open.htm')">
              <td width="15%" align="right">中心发书单未打包条数：</td>
              <td id="notPackageSpotOrderCount"></td>
            </tr>
            <tr id="notPackageStudentOrderTR" style="display: none; cursor:pointer" onclick="aa('/addStudentBookOrderPackageForStudent/open.htm')">
              <td width="15%" align="right">学生发书单未打包条数：</td>
              <td id="notPackageStudentOrderCount"></td>
            </tr>
            <tr id="notPrintSpotOnceOrderTR" style="display: none; cursor:pointer" onclick="aa('/findOnceOrderForSpotCount/find.htm')">
              <td width="15%" align="right">中心一次性发书单未打印条数：</td>
              <td id="notPrintSpotOnceOrderCount"></td>
            </tr>
            <tr id="notPrintStudentOnceOrderTR" style="display: none; cursor:pointer" onclick="aa('/findOnceOrderForStudentCount/find.htm)">
              <td width="15%" align="right">学生一次性发书单未打印条数：</td>
              <td id="notPrintStudentOnceOrderCount"></td>
            </tr>
            <tr id="notPackageSpotOnceOrderTR" style="display: none; cursor:pointer" onclick="aa('/addOnceOrderPackage/open.htm')">
              <td width="15%" align="right">中心一次性发书单未打包条数：</td>
              <td id="notPackageSpotOnceOrderCount"></td>
            </tr>
            <tr id="notPackageStudentOnceOrderTR" style="display: none; cursor:pointer" onclick="aa('/addOnceOrderPackageForStudent/open.htm')">
              <td width="15%" align="right">学生一次性发书单未打包条数：</td>
              <td id="notPackageStudentOnceOrderCount"></td>
            </tr>
            <tr id="notPrintSpotPlaceOrderTR" style="display: none; cursor:pointer" onclick="aa('/findPlaceOrderForSpotCount/find.htm')">
              <td width="15%" align="right">预订单发书单未打印条数：</td>
              <td id="notPrintSpotPlaceOrderCount"></td>
            </tr>
            <tr id="notPackageSpotPlaceOrderTR" style="display: none; cursor:pointer" onclick="aa('/placeOrderPackage/toPlaceOrderPackagePage.htm')">
              <td width="15%" align="right">预订单发书单未打包条数：</td>
              <td id="notPackageSpotPlaceOrderCount"></td>
            </tr>
          </table>
        </div>
      </div>
      <footer data-am-widget="footer" class="am-footer am-footer-default" data-am-footer="{  }">
        <div class="am-footer-miscs ">
          <%--<p>由 <a href="http://www.attop.com/" title="至善网" target="_blank" class="">至善网</a> 提供技术支持</p><br/>--%>
          <p>版权所有 Copyright © 2010-2016 </p><br/>
          <p>渝ICP备11006229号 渝公网安备 50010902000107号</p><br/>
            <p class="browsehappy">如果你样式有问题，可能是你正在使用<strong>过时</strong>的浏览器，UI 不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
              以获得更好的体验！</p>
        </div>
      </footer>
    </div>
  </div>
</div>
</body>
</html>
<script>
  $(function(){
    findTotal();
  });

  function findTotal(){
    setTimeout(function(){
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/index/findTotal.htm",
        data:{},
        async: false,
        success: function(data) {
          if(data.state == 0){
            var isShow = data.isShow;
            if(isShow == 0){
              $("#totalTable").hide();
            }
            if(isShow == 1 || isShow == 2){
              if(data.waitAuditCount > 0){
                $("#waitAuditTR").show();
                $("#waitAuditCount").html(data.waitAuditCount);
              }
            }
            if(isShow == 1 || isShow == 3){
              if(data.notPrintSpotOrderCount > 0){
                $("#notPrintSpotOrderTR").show();
                $("#notPrintSpotOrderCount").html(data.notPrintSpotOrderCount);
              }
              if(data.notPrintStudentOrderCount > 0){
                $("#notPrintStudentOrderTR").show();
                $("#notPrintStudentOrderCount").html(data.notPrintStudentOrderCount);
              }
              if(data.notPackageSpotOrderCount > 0){
                $("#notPackageSpotOrderTR").show();
                $("#notPackageSpotOrderCount").html(data.notPackageSpotOrderCount);
              }
              if(data.notPackageStudentOrderCount > 0){
                $("#notPackageStudentOrderTR").show();
                $("#notPackageStudentOrderCount").html(data.notPackageStudentOrderCount);
              }
              if(data.notPrintSpotOnceOrderCount > 0){
                $("#notPrintSpotOnceOrderTR").show();
                $("#notPrintSpotOnceOrderCount").html(data.notPrintSpotOnceOrderCount);
              }
              if(data.notPrintStudentOnceOrderCount > 0){
                $("#notPrintStudentOnceOrderTR").show();
                $("#notPrintStudentOnceOrderCount").html(data.notPrintStudentOnceOrderCount);
              }
              if(data.notPackageSpotOnceOrderCount > 0){
                $("#notPackageSpotOnceOrderTR").show();
                $("#notPackageSpotOnceOrderCount").html(data.notPackageSpotOnceOrderCount);
              }
              if(data.notPackageStudentOnceOrderCount > 0){
                $("#notPackageStudentOnceOrderTR").show();
                $("#notPackageStudentOnceOrderCount").html(data.notPackageStudentOnceOrderCount);
              }
              if(data.notPrintSpotPlaceOrderCount > 0){
                $("#notPrintSpotPlaceOrderTR").show();
                $("#notPrintSpotPlaceOrderCount").html(data.notPrintSpotPlaceOrderCount);
              }
              if(data.notPackageSpotPlaceOrderCount > 0){
                $("#notPackageSpotPlaceOrderTR").show();
                $("#notPackageSpotPlaceOrderCount").html(data.notPackageSpotPlaceOrderCount);
              }
            }
          }else{
            $("#contentPage").html("");
          }
          app.pageHtmlJSON["indexmain"] = $("#contentPage").html();
        }
      });
    }, 1);
  }

  function aa(url){
    $("a").each(function(){
      var str = $(this).attr("onclick");
      if(typeof(str) != "undefined") {
        if(str.indexOf(url) > -1){
          $(this).click();
        }
      }
    });
  }
</script>