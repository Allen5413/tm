<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<!DOCTYPE html>--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
  <%--<%@ include file="/common/meta.jsp"%>--%>
  <%--<%@ include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="main-content">--%>
  <%--<a class="com_btn_b" href="#" onclick="add()" style="margin-top: 10px;"><span>新&nbsp;增</span></a>--%>
  <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
    <%--<tr>--%>
      <%--<th style="width: 50px;">序号</th>--%>
      <%--<th style="width: 80px;">版次</th>--%>
      <%--<th style="width: 80px;">价格</th>--%>
      <%--<th style="width: 100px;">是否当前版次</th>--%>
      <%--<th style="width: 80px;">操作人</th>--%>
      <%--<th style="width: 130px;">操作时间</th>--%>
      <%--<th>操作</th>--%>
    <%--</tr>--%>
    <%--<c:if test="${empty list}">--%>
      <%--<tr>--%>
        <%--<td colspan="20" align="center" style="color: #ff000c;">没有找到相关数据</td>--%>
      <%--</tr>--%>
    <%--</c:if>--%>
    <%--<c:forEach var="tmr" items="${list}" varStatus="status">--%>
      <%--<tr>--%>
        <%--<td align="center">${status.index+1}</td>--%>
        <%--<td>--%>
          <%--${tmr.revision}--%>
          <%--<a href="#" onclick="editRevision(${tmr.id})"><div class="editDivIcon"></div></a>--%>
        <%--</td>--%>
        <%--<td>--%>
          <%--${tmr.price}--%>
          <%--<a href="#" onclick="editPrice(${tmr.id})"><div class="editDivIcon"></div></a>--%>
        <%--</td>--%>
        <%--<td>--%>
          <%--<c:choose>--%>
            <%--<c:when test="${tmr.isNow == 1}">是</c:when>--%>
            <%--<c:when test="${tmr.isNow == 0}">否</c:when>--%>
            <%--<c:otherwise>未知</c:otherwise>--%>
          <%--</c:choose>--%>
        <%--</td>--%>
        <%--<td>${tmr.operator}</td>--%>
        <%--<td>--%>
          <%--<fmt:formatDate value="${tmr.operateTime}" pattern="yyyy-MM-dd HH:mm:ss" />--%>
        <%--</td>--%>
        <%--<td>--%>
          <%--<c:if test="${tmr.isNow == 0}">--%>
            <%--<a href="#" onclick="editIsNow(${tmr.id})" style="color: #0092DC">设置为当前版次</a>--%>
          <%--</c:if>--%>
        <%--</td>--%>
      <%--</tr>--%>
    <%--</c:forEach>--%>
  <%--</table>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
  <%--function add(){--%>
    <%--var dialog = new Dialog();--%>
    <%--dialog.ShowButtonRow=true;--%>
    <%--dialog.OKEvent = function(){--%>
      <%--var obj = dialog.innerFrame.contentWindow.$("#addForm");--%>
      <%--if(obj.valid()){--%>
        <%--$.ajax({--%>
          <%--cache: true,--%>
          <%--type: "POST",--%>
          <%--url:"${pageContext.request.contextPath}/addTeachMaterialRevision/tmrAdd.htm",--%>
          <%--data:obj.serialize(),--%>
          <%--async: false,--%>
          <%--success: function(data) {--%>
            <%--if(data.state == 0){--%>
              <%--alert("提交成功！");--%>
              <%--Dialog.close();--%>
              <%--history.go(0);--%>
            <%--}else{--%>
              <%--alert(data.msg);--%>
            <%--}--%>
          <%--}--%>
        <%--});--%>
      <%--}--%>
    <%--};--%>
    <%--dialog.Width = getWindowWidthSize() * 0.5;--%>
    <%--dialog.Height = getWindowHeightSize() * 0.3;--%>
    <%--dialog.Title = "新增版次";--%>
    <%--dialog.URL = "${pageContext.request.contextPath}/addTeachMaterialRevision/openAddTMR.htm?tmId=${param.tmId}";--%>
    <%--dialog.show();--%>
    <%--dialog.okButton.value=" 提 交 ";--%>
    <%--dialog.cancelButton.value=" 取 消 ";--%>
  <%--}--%>

  <%--function editRevision(id){--%>
    <%--var dialog = new Dialog();--%>
    <%--dialog.ShowButtonRow=true;--%>
    <%--dialog.OKEvent = function(){--%>
      <%--var obj = dialog.innerFrame.contentWindow.$("#editForm");--%>
      <%--if(obj.valid()){--%>
        <%--$.ajax({--%>
          <%--cache: true,--%>
          <%--type: "POST",--%>
          <%--url:"${pageContext.request.contextPath}/editTMRevisionForRevision/tmRevisionForRevisionEdit.htm",--%>
          <%--data:obj.serialize(),--%>
          <%--async: false,--%>
          <%--success: function(data) {--%>
            <%--if(data.state == 0){--%>
              <%--alert("提交成功！");--%>
              <%--Dialog.close();--%>
              <%--history.go(0);--%>
            <%--}else{--%>
              <%--alert(data.msg);--%>
            <%--}--%>
          <%--}--%>
        <%--});--%>
      <%--}--%>
    <%--};--%>
    <%--dialog.Width = getWindowWidthSize() * 0.5;--%>
    <%--dialog.Height = getWindowHeightSize() * 0.2;--%>
    <%--dialog.Title = "编辑教材版次";--%>
    <%--dialog.URL = '${pageContext.request.contextPath}/editTMRevisionForRevision/openTMRevisionForRevisionPage.htm?id='+id;--%>
    <%--dialog.show();--%>
    <%--dialog.okButton.value=" 提 交 ";--%>
    <%--dialog.cancelButton.value=" 取 消 ";--%>
  <%--}--%>

  <%--function editPrice(id){--%>
    <%--var dialog = new Dialog();--%>
    <%--dialog.ShowButtonRow=true;--%>
    <%--dialog.OKEvent = function(){--%>
      <%--var obj = dialog.innerFrame.contentWindow.$("#editForm");--%>
      <%--if(obj.valid()){--%>
        <%--$.ajax({--%>
          <%--cache: true,--%>
          <%--type: "POST",--%>
          <%--url:"${pageContext.request.contextPath}/editTMRForPrice/tmRForPriceEdit.htm",--%>
          <%--data:obj.serialize(),--%>
          <%--async: false,--%>
          <%--success: function(data) {--%>
            <%--if(data.state == 0){--%>
              <%--alert("提交成功！");--%>
              <%--Dialog.close();--%>
              <%--history.go(0);--%>
            <%--}else{--%>
              <%--alert(data.msg);--%>
            <%--}--%>
          <%--}--%>
        <%--});--%>
      <%--}--%>
    <%--};--%>
    <%--dialog.Width = getWindowWidthSize() * 0.5;--%>
    <%--dialog.Height = getWindowHeightSize() * 0.2;--%>
    <%--dialog.Title = "编辑版次价格";--%>
    <%--dialog.URL = '${pageContext.request.contextPath}/editTMRForPrice/openEditTMRForPricePage.htm?id='+id;--%>
    <%--dialog.show();--%>
    <%--dialog.okButton.value=" 提 交 ";--%>
    <%--dialog.cancelButton.value=" 取 消 ";--%>
  <%--}--%>

  <%--function editIsNow(id){--%>
    <%--if(confirm("您确定要设置为当前版次？")){--%>
      <%--$.ajax({--%>
        <%--url:"${pageContext.request.contextPath}/editRevisionForIsNow/revisionForIsNowEdit.htm",--%>
        <%--method : 'POST',--%>
        <%--async:false,--%>
        <%--data:{"id":id},--%>
        <%--success:function(data){--%>
          <%--if(data.state == 0){--%>
            <%--alert("设置成功！");--%>
            <%--parent.parent.$('#index_iframe').contents().find("#subBut").click();--%>
            <%--Dialog.close();--%>
          <%--}else {--%>
            <%--alert(data.msg);--%>
          <%--}--%>
        <%--}--%>
      <%--});--%>
    <%--}--%>
  <%--}--%>
<%--</script>--%>


<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <tr>
    <td colspan="999" style="background-color:#FFF">
      <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="addRevision()"><span class="am-icon-plus"></span> 新增</button>
    </td>
  </tr>
  <tr class="am-primary">
    <th style="width: 10%;">序号</th>
    <th style="width: 20%;">版次</th>
    <th style="width: 10%;">价格</th>
    <th style="width: 10%;">是否当前版次</th>
    <th style="width: 10%;">操作人</th>
    <th style="width: 20%;">操作时间</th>
    <th>操作</th>
  </tr>
  <c:if test="${empty list}">
    <tr>
      <td colspan="20" align="center" style="color: #ff000c;">没有找到相关数据</td>
    </tr>
  </c:if>
  <c:forEach var="tmr" items="${list}" varStatus="status">
    <tr>
      <td align="center">${status.index+1}</td>
      <td>
        ${tmr.revision}
        <a href="#" onclick="editRevision(${tmr.id})"><div class="am-icon-edit"></div></a>
      </td>
      <td>
        ${tmr.price}
        <a href="#" onclick="editPrice(${tmr.id})"><div class="am-icon-edit"></div></a>
      </td>
      <td>
        <c:choose>
          <c:when test="${tmr.isNow == 1}">是</c:when>
          <c:when test="${tmr.isNow == 0}">否</c:when>
          <c:otherwise>未知</c:otherwise>
        </c:choose>
      </td>
      <td>${tmr.operator}</td>
      <td>
        <fmt:formatDate value="${tmr.operateTime}" pattern="yyyy-MM-dd HH:mm:ss" />
      </td>
      <td>
        <c:if test="${tmr.isNow == 0}">
          <div class="am-btn-group">
            <button type="button" class="am-btn am-btn-primary am-round btn-loading-example"
                    data-am-loading="{spinner: 'circle-o-notch', loadingText:'正在执行...', resetText: '<span class=am-icon-cog></span> 设置为当前版次'}"
                    onClick="editIsNow(${tmr.id}, this)"><span class="am-icon-cog"></span> 设置为当前版次</button>
          </div>
        </c:if>
      </td>
    </tr>
  </c:forEach>
</table>
<script>
  function addRevision(){
    app.openDialog('${pageContext.request.contextPath}/addTeachMaterialRevision/openAddTMR.htm?tmId=${param.tmId}', '新增版次', 400, 0.35, function(index){
      var revision = $("#add_revision").val().trim();
      var price = $("#add_price").val().trim();
      if(revision == ""){
        app.msg("请输入版次", 1);
        return false;
      }
      if(!vaild.vaildMoney(price)){
        app.msg("请输入正确的价格", 1);
        return false;
      }
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/addTeachMaterialRevision/tmrAdd.htm",
        data:$('#addRevisionForm').serialize(),
        async: false,
        success: function(data) {
          if(data.state == 0){
            app.msg("提交成功！", 0);
            layer.closeAll();
            $("#searchBtn").click();
            app.openOneBtnDialog('${pageContext.request.contextPath}/findTeachMaterialRevisionBytmId/doFindTeachMaterialRevisionBytmId.htm?tmId=${param.tmId}', '版次管理', 1200, 0.8);
          }else{
            app.msg(data.msg, 1);
          }
        }
      });
    });
  }

  function editRevision(id){
    app.openDialog('${pageContext.request.contextPath}/editTMRevisionForRevision/openTMRevisionForRevisionPage.htm?id='+id, '编辑教材版次', 400, 0.25, function(index){
      var revision = $("#edit_revision").val().trim();
      if(revision == ""){
        app.msg("请输入版次", 1);
        return false;
      }
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/editTMRevisionForRevision/tmRevisionForRevisionEdit.htm",
        data:$('#editForm').serialize(),
        async: false,
        success: function(data) {
          if(data.state == 0){
            app.msg("提交成功！", 0);
            layer.closeAll();
            $("#searchBtn").click();
            app.openOneBtnDialog('${pageContext.request.contextPath}/findTeachMaterialRevisionBytmId/doFindTeachMaterialRevisionBytmId.htm?tmId=${param.tmId}', '版次管理', 1200, 0.8);
          }else{
            app.msg(data.msg, 1);
          }
        }
      });
    });
  }

  function editPrice(id){
    app.openDialog('${pageContext.request.contextPath}/editTMRForPrice/openEditTMRForPricePage.htm?id='+id, '编辑版次价格', 400, 0.25, function(index){
      var price = $("#edit_price").val().trim();
      if(!vaild.vaildMoney(price)){
        app.msg("请输入正确的价格", 1);
        return false;
      }
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/editTMRForPrice/tmRForPriceEdit.htm",
        data:$('#editForm').serialize(),
        async: false,
        success: function(data) {
          if(data.state == 0){
            app.msg("提交成功！", 0);
            layer.closeAll();
            $("#searchBtn").click();
            app.openOneBtnDialog('${pageContext.request.contextPath}/findTeachMaterialRevisionBytmId/doFindTeachMaterialRevisionBytmId.htm?tmId=${param.tmId}', '版次管理', 1200, 0.8);
          }else{
            app.msg(data.msg, 1);
          }
        }
      });
    });
  }

  function editIsNow(id, btnObj){
    app.confirm("您确定要设置为当前版次？", function(){
      $(btnObj).button('loading');
      setTimeout(function(){
        $.ajax({
          url:"${pageContext.request.contextPath}/editRevisionForIsNow/revisionForIsNowEdit.htm",
          method : 'POST',
          async:false,
          data:{"id":id},
          success:function(data){
            if(data.state == 0){
              app.msg("操作成功！", 0);
              layer.closeAll();
              $("#searchBtn").click();
              app.openOneBtnDialog('${pageContext.request.contextPath}/findTeachMaterialRevisionBytmId/doFindTeachMaterialRevisionBytmId.htm?tmId=${param.tmId}', '版次管理', 1200, 0.8);
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