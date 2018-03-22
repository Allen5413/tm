<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<div class="main-content">--%>
  <%--<a class="com_btn_b" href="#" onclick="add()"><span>新&nbsp;增</span></a>--%>
  <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%" id="spotWxTable">--%>
    <%--<tr>--%>
      <%--<th style="width: 10%;">序号</th>--%>
      <%--<th style="width: 20%;">身份证号</th>--%>
      <%--<th style="width: 10%;">姓名</th>--%>
      <%--<th style="width: 10%;">是否绑定</th>--%>
      <%--<th style="width: 15%;">绑定时间</th>--%>
      <%--<th style="width: 10%;">操作人</th>--%>
      <%--<th style="width: 15%;">操作时间</th>--%>
      <%--<th>操作</th>--%>
    <%--</tr>--%>
  <%--</table>--%>
<%--</div>--%>
<%--<div id="dialogDiv2"></div>--%>
<%--<div id="dialogDiv3"></div>--%>
<%--<script>--%>
  <%--$(function($){--%>
    <%--$.ajax({--%>
      <%--cache: true,--%>
      <%--type: "POST",--%>
      <%--url:"${pageContext.request.contextPath}/findSpotWxByCode/find.htm",--%>
      <%--data:{"code" : "${param.code}"},--%>
      <%--async: false,--%>
      <%--success: function(data) {--%>
        <%--if(data.state == 0){--%>
          <%--var table = $("#spotWxTable");--%>
          <%--if(data.spotWxList.length == 0) {--%>
            <%--var tr = $("<tr style='color:red'></tr>");--%>
            <%--var td = $("<td colspan='99' align='center'>还没有添加用户</td>");--%>
            <%--tr.append(td);--%>
            <%--table.append(tr);--%>
          <%--}else{--%>
            <%--for(var i=0; i<data.spotWxList.length; i++){--%>
              <%--var tr = $("<tr></tr>");--%>
              <%--var spotWx = data.spotWxList[i];--%>
              <%--var td = $("<td>"+(i+1)+"</td>");--%>
              <%--var td2 = $("<td>"+spotWx.idcard+"</td>");--%>
              <%--var td3 = $("<td>"+spotWx.adminName+"</td>");--%>
              <%--var td4 = $("<td>"+(spotWx.isBound ? "是":"否")+"</td>");--%>
              <%--var td5 = $("<td>"+spotWx.boundTimeStr+"</td>");--%>
              <%--var td6 = $("<td>"+spotWx.operator+"</td>");--%>
              <%--var td7 = $("<td>"+spotWx.operateTimeStr+"</td>");--%>
              <%--var td8 = $("<td><a href='#' onclick='edit("+spotWx.id+")' style='color: #0092DC'>修改</a>&nbsp;&nbsp;<a href='#' onclick='del("+spotWx.id+")' style='color: #0092DC'>删除</a></td>");--%>
              <%--tr.append(td);--%>
              <%--tr.append(td2);--%>
              <%--tr.append(td3);--%>
              <%--tr.append(td4);--%>
              <%--tr.append(td5);--%>
              <%--tr.append(td6);--%>
              <%--tr.append(td7);--%>
              <%--tr.append(td8);--%>
              <%--table.append(tr);--%>
            <%--}--%>
          <%--}--%>
        <%--}else{--%>
          <%--alert(data.msg);--%>
        <%--}--%>
      <%--},--%>
      <%--error:function(XMLHttpRequest, textStatus, errorThrown) {--%>
        <%--alert(XMLHttpRequest.status);--%>
        <%--alert(XMLHttpRequest.readyState);--%>
        <%--alert(textStatus);--%>

      <%--}--%>
    <%--});--%>
  <%--});--%>

  <%--function add(){--%>
    <%--parent.openD($("#dialogDiv2"), "新增微信管理员", 0.3, 0.3, '${pageContext.request.contextPath}/addSpotWx/open.htm?code=${param.code}');--%>
  <%--}--%>

  <%--function edit(id){--%>
    <%--parent.openD($("#dialogDiv3"), "编辑微信管理员", 0.3, 0.3, '${pageContext.request.contextPath}/editSpotWx/open.htm?id='+id);--%>
  <%--}--%>

  <%--function del(id){--%>
    <%--$.ajax({--%>
      <%--cache: true,--%>
      <%--type: "POST",--%>
      <%--url:"${pageContext.request.contextPath}/delSpotWx/del.htm",--%>
      <%--data:{"id":id},--%>
      <%--async: false,--%>
      <%--success: function(data) {--%>
        <%--if(data.state == 0){--%>
          <%--alert("提交成功！");--%>
          <%--$('#index_iframe').contents().find("#resetBut").click();--%>
          <%--$('#index_iframe').contents().find("#subBut").click();--%>
          <%--refreshDialog();--%>
        <%--}else{--%>
          <%--alert(data.msg);--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>
  <%--}--%>
<%--</script>--%>


<table id="spotWxTable" class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
</table>

<script>
  $(function($){
    loadData();
  });

  function loadData(){
    var table = $("#spotWxTable");
    table.html("");

    var tr = $("<tr></tr>");
    var addBtn = "<button class=\"am-btn am-btn-primary am-btn-sm\" type=\"button\" onClick=\"addWxUser()\"><span class=\"am-icon-plus\"></span> 新增</button>";
    var addBtnTd = $("<td colspan=\"999\" style=\"background-color:#FFF\"></td>");

    var tr2 = $("<tr class='am-primary'></tr>");
    var th = $("<th style='width: 6%;'>序号</th>");
    var th2 = $("<th style='width: 20%;'>身份证号</th>");
    var th3 = $("<th style='width: 8%;'>姓名</th>");
    var th4 = $("<th style='width: 6%;'>是否绑定</th>");
    var th5 = $("<th style='width: 15%;'>绑定时间</th>");
    var th6 = $("<th style='width: 6%;'>操作人</th>");
    var th7 = $("<th style='width: 15%;'>操作时间</th>");
    var th8 = $("<th>操作</th>");

    addBtnTd.append(addBtn);
    tr.append(addBtnTd);
    tr2.append(th).append(th2).append(th3).append(th4).append(th5).append(th6).append(th7).append(th8);
    table.append(tr).append(tr2);


    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/findSpotWxByCode/find.htm",
      data:{"code" : "${param.code}"},
      async: false,
      success: function(data) {
        if(data.state == 0){
          if(data.spotWxList.length == 0) {
            var tr3 = $("<tr style='color:red'></tr>");
            var td = $("<td colspan='99' align='center'>还没有添加用户</td>");
            tr3.append(td);
            table.append(tr3);
          }else{
            for(var i=0; i<data.spotWxList.length; i++){
              var spotWx = data.spotWxList[i];
              var editBtn = "<button type=\"button\" class=\"am-btn am-btn-primary am-round\" onClick='editWxUser("+spotWx.id+")'><span class=\"am-icon-edit\"></span> 修改</button>";
              var delBtn = "<button type=\"button\" class=\"am-btn am-btn-primary am-round btn-loading-example\"" +
                      "data-am-loading=\"{spinner: 'circle-o-notch', loadingText:'正在删除...', resetText: '<span class=am-icon-cog></span> 设置为当前版次'}\"" +
                      " onClick=\"delWxUser('"+spotWx.id+"', this)\"><span class=\"am-icon-trash-o\"></span> 删除</button>";

              var tr3 = $("<tr></tr>");

              var td = $("<td>"+(i+1)+"</td>");
              var td2 = $("<td>"+spotWx.idcard+"</td>");
              var td3 = $("<td>"+spotWx.adminName+"</td>");
              var td4 = $("<td>"+(spotWx.isBound ? "是":"否")+"</td>");
              var td5 = $("<td>"+spotWx.boundTimeStr+"</td>");
              var td6 = $("<td>"+spotWx.operator+"</td>");
              var td7 = $("<td>"+spotWx.operateTimeStr+"</td>");
              var td8 = $("<td><div class='am-btn-group'></div></td>");
              td8.append(editBtn).append(delBtn);
              tr3.append(td);
              tr3.append(td2);
              tr3.append(td3);
              tr3.append(td4);
              tr3.append(td5);
              tr3.append(td6);
              tr3.append(td7);
              tr3.append(td8);
              table.append(tr3);
            }
          }
        }else{
          app.msg(data.msg, 1);
        }
      },
      error:function(XMLHttpRequest, textStatus, errorThrown) {
        app.msg(XMLHttpRequest.status, 1);
        app.msg(XMLHttpRequest.readyState, 1);
        app.msg(textStatus, 1);
      }
    });
  }

  function addWxUser(){
    app.openDialog('${pageContext.request.contextPath}/addSpotWx/open.htm?code=${param.code}', '新增微信管理员', 550, 0.35, function(index){
      var idcard = $("#idcard").val().trim();
      var adminName = $("#adminName").val().trim();
      if(idcard == ""){
        app.msg("请输入身份证号", 1);
        return false;
      }
      if(adminName == ""){
        app.msg("请输入姓名", 1);
        return false;
      }
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/addSpotWx/add.htm",
        data:$('#addSpotWxForm').serialize(),
        async: false,
        success: function(data) {
          if(data.state == 0){
            app.msg("提交成功！", 0);
            loadData();
            layer.close(index);
          }else{
            app.msg(data.msg, 1);
          }
        }
      });
    });
  }

  function editWxUser(id){
    app.openDialog('${pageContext.request.contextPath}/editSpotWx/open.htm?id='+id, '编辑微信管理员', 550, 0.35, function(index){
      var id = $("#id").val().trim();
      var idcard = $("#idcard").val().trim();
      var adminName = $("#adminName").val().trim();
      if(idcard == ""){
        app.msg("请输入身份证号", 1);
        return false;
      }
      if(adminName == ""){
        app.msg("请输入姓名", 1);
        return false;
      }
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/editSpotWx/editor.htm",
        data:{"id":id, "idCard":idcard, "name":adminName},
        async: false,
        success: function(data) {
          if(data.state == 0){
            app.msg("提交成功！", 0);
            loadData();
            layer.close(index);
          }else{
            app.msg(data.msg, 1);
          }
        }
      });
    });
  }

  function delWxUser(id, btnObj){
    app.confirm("您确定是要删除该微信管理员用户?", function(){
      $(btnObj).button('loading');
      setTimeout(function(){
        $.ajax({
          url:"${pageContext.request.contextPath}/delSpotWx/del.htm",
          method : 'POST',
          async:false,
          data:{"id":id},
          success:function(data){
            if(data.state == 0){
              app.msg("删除成功！", 0);
              loadData();
            }else {
              app.msg(data.msg, 1);
              $(btnObj).button('reset');
            }
          }
        });
      }, 100);
    });
  }
</script>
